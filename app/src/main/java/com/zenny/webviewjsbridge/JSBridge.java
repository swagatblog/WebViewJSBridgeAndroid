package com.zenny.webviewjsbridge;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by zenny on 12/8/2015.
 */
public class JSBridge {

        Context mContext;
       WebView _webView;
        /** Instantiate the interface and set the context */
        JSBridge(Context c, WebView webView) {
            mContext = c;
            _webView=webView;
        }

        /** Show a toast from the web page */
        @JavascriptInterface
        public void showToast(String toast) {
            Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show();
        }

    @JavascriptInterface
    public void withCallback(String msg, String callback) throws JSONException {
        JSONObject obj= new JSONObject();
        obj.put("name","fName");
        obj.put("country", "india");
        final String callbackData= obj.toString();
        _webView.post(new Runnable() {
            public void run() {
                _webView.loadUrl("javascript: JSPluginCallbackHandler('"+callbackData+"')");
            }
        });


    }
    @JavascriptInterface
    public void showDialog(String dialogMsg){
        AlertDialog alertDialog = new AlertDialog.Builder(mContext).create();

        // Setting Dialog Title
        alertDialog.setTitle("JS triggered Dialog");

        // Setting Dialog Message
        alertDialog.setMessage(dialogMsg);

        // Setting alert dialog icon
        //alertDialog.setIcon((status) ? R.drawable.success : R.drawable.fail);

        // Setting OK Button
        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(mContext, "Dialog dismissed!", Toast.LENGTH_SHORT).show();
            }
        });

        // Showing Alert Message
        alertDialog.show();
    }
}
