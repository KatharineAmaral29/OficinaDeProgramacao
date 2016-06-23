package a371839.katharine.webdownloader;

import android.app.Dialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {

    private EditText url;
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        url = (EditText) findViewById(R.id.editText);
        webView = (WebView) findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient() {
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Toast.makeText(MainActivity.this, description, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getURL(View view) {
        webView.loadUrl(url.getText().toString());
        String body = "";
        DownloaderTask task = new DownloaderTask(body);
        task.execute();
        Toast.makeText(MainActivity.this,body,Toast.LENGTH_SHORT).show();
    }

    public class DownloaderTask extends AsyncTask<String, String, String> {

        private String textView;

        public DownloaderTask(String textView)
        {
            this.textView = textView;
        }

        private ConnectivityManager connManager;
        public void checkConnection( View view) {
            connManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connManager.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isConnected()) {
                doInBackground();
            } else {
                Toast.makeText(MainActivity.this,"Sem conexão",Toast.LENGTH_LONG).show();
            }
        }

        protected String doInBackground(String... params) {
            try {
                String link = (String) params[0];
                URL url = new URL(link);
                URLConnection con = url.openConnection();
                InputStream in = con.getInputStream();
                String encoding = con.getContentEncoding();
                String body = in.toString();
                return body;
            }catch (Exception e){
                return new String(" Exception: " + e.getMessage());
            }
        }

        @Override
        protected void onProgressUpdate(String... values)
        {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            this.textView = s;
        }
    }
}