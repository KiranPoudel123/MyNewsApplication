package myapp.training.newitventure.com.mynewsapplication;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;


public class WebActivity extends WebViewDialogActivity {
    WebView webView;
    TextView tv_title;
    Button btnClose;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        tv_title = (TextView) findViewById(R.id.tv_title);
        btnClose = (Button) findViewById(R.id.btnClose);
        WebView webView = (WebView)findViewById(R.id.webView);

        btnClose.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(WebActivity.this, WebViewDialogActivity.class);
                startActivity(intent);
            }
        });

        web = new Dialog(this);

        web.requestWindowFeature(Window.FEATURE_NO_TITLE);

        web.setContentView(R.layout.activity_web);

        web.setCancelable(true);

        webView.setScrollbarFadingEnabled(false);

        webView.setHorizontalScrollBarEnabled(false);

        webView.getSettings().setUserAgentString("AndroidWebView");

        webView.clearCache(true);

        String url = "http:\\/\\/www.nepaljapan.com";

        webView.loadUrl(url);

    }

}
