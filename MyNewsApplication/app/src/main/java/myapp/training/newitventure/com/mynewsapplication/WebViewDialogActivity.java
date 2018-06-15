package myapp.training.newitventure.com.mynewsapplication;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class WebViewDialogActivity extends AppCompatActivity {

    Button btnLunch;
    TextView textView;
    Dialog web;
    Button buttonBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webviewdialog);

        btnLunch = (Button) findViewById(R.id.btnLunch);
        textView = (TextView) findViewById(R.id.textView);
        buttonBack = (Button) findViewById(R.id.buttonBack);

        btnLunch.setOnClickListener(
                new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(WebViewDialogActivity.this, WebActivity.class);
                startActivity(intent);
                web.show();
            }
        });

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onBackPressed();
                Intent intent = new Intent(WebViewDialogActivity.this, NewsActivity.class);
                startActivity(intent);

            }
        });

    }
}
