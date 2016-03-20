package com.hack.asksepp.asksepp_demo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.URLSpan;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import static android.util.Log.*;

import com.hack.asksepp.asksepp_demo.kleidung.AsyncResponse;
import com.hack.asksepp.asksepp_demo.kleidung.Kleidung;
import com.hack.asksepp.asksepp_demo.kleidung.LoadKleiderTask;
import com.hack.asksepp.asksepp_demo.model.StreamData;
import com.samsung.multiscreen.*;
import com.samsung.multiscreen.Error;

import org.codehaus.jackson.map.ObjectMapper;


public class MainActivity extends Activity implements OnClickListener, AsyncResponse<List<Kleidung>> {

    public final String LOGTAG = "info";

    protected static final int REQUEST_OK = 1;

    private static Search search;

    private static Service appService;

    private Application application;

    private ChatArrayAdapter chatArrayAdapter;

    private Kleidung kleidung;

    AsyncResponse<List<Kleidung>> self;

    int viewPos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        self = this;
        setContentView(R.layout.activity_main);
        findViewById(R.id.button1).setOnClickListener(this);
        findViewById(R.id.buttonSend).setOnClickListener(this);
        findViewById(R.id.button2).setOnClickListener(this);

        Log.d(LOGTAG, "onCreate()");

        connectToApp();

    }


    public void connectToApp() {
        Uri uri = Uri.parse("http://10.100.105.190:8001/api/v2/");
        //Uri uri = appService.getUri();
        Result<Service> result = null;
        Service.getByURI(uri, new Result<Service>() {
            @Override
            public void onSuccess(Service service) {
                appService = service;

                String appId = "wbXeC1XuAx.test";
                String channelId = "asksepp";

                application = appService.createApplication(appId, channelId);

                application.connect(new Result<Client>() {

                    @Override
                    public void onSuccess(Client client) {
                        Log.d(LOGTAG, "Application.connect onSuccess()");

                    }

                    @Override
                    public void onError(Error error) {
                        Log.d(LOGTAG, "Application.connect onError() " + error.getMessage() + " - String: " + error.toString());
                    }
                });


                application.addOnMessageListener("getTimeResponse", new Channel.OnMessageListener() {

                    @Override
                    public void onMessage(Message message) {
                        Log.d(LOGTAG, message.toString());
                        if (message.getData() instanceof String) {
                            String data = (String) message.getData();
                            Log.d(LOGTAG, data);

                            try {
                                ObjectMapper objectMapper = new ObjectMapper();
                                StreamData streamData = objectMapper.readValue(data, StreamData.class);
                                Log.d(LOGTAG, streamData.toString());

                                new LoadKleiderTask(self).execute(streamData.getTime());

                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        }
                        ;
                    }
                });


            }

            @Override
            public void onError(Error error) {
                Log.d(LOGTAG, error.getMessage());
            }

        });


    }

    @Override
    public void onClick(View v) {
        Log.d(LOGTAG, ""+v.getId());
        if (v.getId() == R.id.button1) {
            Intent i = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
            i.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, Locale.GERMANY.toString());
            try {
                startActivityForResult(i, REQUEST_OK);
            } catch (Exception e) {
                Toast.makeText(this, "Error initializing speech to text engine.", Toast.LENGTH_LONG).show();
            }
        } else if (v.getId() == R.id.button2) {
            Log.d(LOGTAG, "here");
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(kleidung.getUrl()));
            startActivity(browserIntent);
        } else {
            String request = ((EditText) findViewById(R.id.chatText)).getText().toString();
            sendResult(request);
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(LOGTAG, "onActivityResult");
        Log.d(LOGTAG, "RSCode: " + resultCode);
        if (requestCode == REQUEST_OK && resultCode == RESULT_OK) {
            ArrayList<String> thingsYouSaid = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            sendResult(thingsYouSaid.get(0));
        }
    }

    private void sendResult(String youSaid) {
        application.publish("getTimeRequest", "testText", Message.TARGET_HOST);
        ((TextView) findViewById(R.id.text1)).setText(youSaid);

    }


    @Override
    public void asyncFinished(List<Kleidung> kleidungs) {
        kleidung = kleidungs.get(0);
        TextView t = (TextView) findViewById(R.id.text1);
        t.setText(kleidung.getTyp());
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.container1);
        relativeLayout.setVisibility(LinearLayout.VISIBLE);
    }
}
