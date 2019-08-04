package com.datarheum.olusegun.healthylifebot;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WelcomeScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);

        WebView web = findViewById(R.id.webview);
        web.setWebViewClient(new WebViewClient());
        WebSettings webSettings = web.getSettings();
        webSettings.setJavaScriptEnabled(true);
        web.loadUrl("https://bot.dialogflow.com/5d803b22-3f89-4f78-8728-cd320e0bb6ca");
    }
}
