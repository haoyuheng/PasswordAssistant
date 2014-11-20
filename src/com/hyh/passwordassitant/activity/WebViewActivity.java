package com.hyh.passwordassitant.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.ClipboardManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import rdi.mobapp.passwordpanacea.utility.CustomDialogSingleButton;

public class WebViewActivity
  extends Activity
{
  private ClipboardManager clipboard;
  private ImageButton imgPassword;
  private ImageButton imgUsername;
  private String password;
  private ProgressBar progressBar;
  private String url;
  private String userName;
  private View viewUsernamePasswordDivider;
  private WebView webView;
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    requestWindowFeature(1);
    setContentView(2130903086);
    Bundle localBundle = getIntent().getExtras();
    this.url = localBundle.getString("url");
    this.userName = localBundle.getString("username");
    this.password = localBundle.getString("password");
    this.webView = ((WebView)findViewById(2131296652));
    this.clipboard = ((ClipboardManager)getSystemService("clipboard"));
    this.imgUsername = ((ImageButton)findViewById(2131296649));
    this.imgPassword = ((ImageButton)findViewById(2131296651));
    this.viewUsernamePasswordDivider = findViewById(2131296650);
    this.progressBar = ((ProgressBar)findViewById(2131296648));
    this.progressBar.setProgress(0);
    this.progressBar.setVisibility(0);
    this.webView.getSettings().setBuiltInZoomControls(true);
    this.webView.getSettings().setSupportZoom(true);
    this.webView.getSettings().setJavaScriptEnabled(true);
    this.webView.getSettings().setDomStorageEnabled(true);
    this.webView.getSettings().setLoadWithOverviewMode(true);
    this.webView.getSettings().setUseWideViewPort(true);
    this.webView.setScrollBarStyle(33554432);
    this.webView.requestFocus(130);
    if ((this.userName == null) && (this.password == null))
    {
      this.imgUsername.setVisibility(8);
      this.imgPassword.setVisibility(8);
      this.viewUsernamePasswordDivider.setVisibility(8);
    }
    this.imgUsername.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (WebViewActivity.this.userName.length() != 0)
        {
          WebViewActivity.this.clipboard.setText(WebViewActivity.this.userName);
          WebViewActivity.this.webView.loadUrl("javascript:var textField = document.activeElement; textField.value ='" + WebViewActivity.this.clipboard.getText() + "'");
          return;
        }
        new CustomDialogSingleButton(WebViewActivity.this, "Error!", "未发现有效值").show();
      }
    });
    this.imgPassword.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (WebViewActivity.this.password.length() != 0)
        {
          WebViewActivity.this.clipboard.setText(WebViewActivity.this.password);
          WebViewActivity.this.webView.loadUrl("javascript:var textField = document.activeElement; textField.value ='" + WebViewActivity.this.clipboard.getText() + "'");
          return;
        }
        new CustomDialogSingleButton(WebViewActivity.this, "Error!", "未发现有效值").show();
      }
    });
    this.webView.setWebChromeClient(new WebChromeClient()
    {
      public void onProgressChanged(WebView paramAnonymousWebView, int paramAnonymousInt)
      {
        WebViewActivity.this.progressBar.setProgress(paramAnonymousInt);
        if (paramAnonymousInt == 100)
        {
          WebViewActivity.this.progressBar.setVisibility(8);
          WebViewActivity.this.webView.loadUrl("javascript:function findMatchingElements( user, pass ){ var userMatch = new RegExp( user, 'i' ); var passMatch = new RegExp( pass, 'i' ); var elems = document.getElementsByTagName('input'); var matches = []; for ( var e = 0; e < elems.length; ++e ) { if ( elems[e].name.match(userMatch) && (elems[e].getAttribute('type').toLowerCase() == 'text' || elems[e].getAttribute('type').toLowerCase() == 'email')) elems[e].value='" + WebViewActivity.this.userName + "'; if ( elems[e].name.match(passMatch) && elems[e].getAttribute('type').toLowerCase() == 'password') elems[e].value='" + WebViewActivity.this.password + "';} } findMatchingElements( 'email|user|id|login', 'pass' );");
        }
      }
    });
    this.webView.setWebViewClient(new WebViewClient()
    {
      public void onReceivedError(WebView paramAnonymousWebView, int paramAnonymousInt, String paramAnonymousString1, String paramAnonymousString2) {}
      
      public boolean shouldOverrideUrlLoading(WebView paramAnonymousWebView, String paramAnonymousString)
      {
        paramAnonymousWebView.loadUrl(paramAnonymousString);
        return true;
      }
    });
    this.webView.loadUrl(this.url);
  }
}


/* Location:           D:\Apkdb\Craining\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     rdi.mobapp.passwordpanacea.activity.WebViewActivity
 * JD-Core Version:    0.7.0.1
 */