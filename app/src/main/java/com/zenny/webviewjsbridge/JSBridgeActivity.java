package com.zenny.webviewjsbridge;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;


public class JSBridgeActivity extends Activity {
    public final static String EXTRA_MESSAGE = "com.amadeus.webviewjsbridge.MESSAGE";
    private Button sendMessage;
    private ProgressDialog progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_jsbridge);

        sendMessage = (Button)findViewById(R.id.dummy_button);
       final Intent intent = new Intent(this, WebViewActivity.class);
        sendMessage.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showLoadingDialog();
                startActivity(intent);
                new CountDownTimer(2000, 1000) {

                    public void onTick(long millisUntilFinished) {

                    }

                    public void onFinish() {


                        dismissLoadingDialog();
                    }
                }.start();

            }
        });

    }

    public void showLoadingDialog() {

        if (progress == null) {
            progress = new ProgressDialog(this);
            progress.setTitle("JS Bridge");
            progress.setMessage("Loading....");
        }
        progress.show();
    }

    public void dismissLoadingDialog() {

        if (progress != null && progress.isShowing()) {
            progress.dismiss();
        }
    }


}
