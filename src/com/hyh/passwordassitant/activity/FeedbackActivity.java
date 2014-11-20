package com.hyh.passwordassitant.activity;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class FeedbackActivity
  extends Activity
{
  private Animation fadeIn;
  private Animation fadeOut;
  private Typeface font;
  private RelativeLayout layoutFollow;
  private RelativeLayout layoutLike;
  private LinearLayout layoutPPImprove;
  private LinearLayout layoutPPImproveBack;
  private LinearLayout layoutPPImproveInner;
  private LinearLayout layoutPPLike;
  private LinearLayout layoutPPLikeBack;
  private LinearLayout layoutPPLikeInner;
  private RelativeLayout layoutRate;
  private RelativeLayout layoutReport;
  private RelativeLayout layoutRequest;
  private Animation slideLeftIn;
  private Animation slideLeftIn1;
  private Animation slideLeftIn2;
  private Animation slideLeftIn3;
  private Typeface tf;
  private TextView txtImprove;
  private TextView txtLike;
  private TextView txtPPFollow;
  private TextView txtPPLike;
  private TextView txtPPRate;
  private TextView txtPPReport;
  private TextView txtPPRequest;
  
  public void FAQ()
  {
    Intent localIntent = new Intent("android.intent.action.SEND");
    localIntent.setClassName("com.google.android.gm", "com.google.android.gm.ComposeActivityGmail");
    localIntent.putExtra("android.intent.extra.EMAIL", new String[] { "1161459106@qq.com" });
    localIntent.setData(Uri.parse("1161459106@qq.com"));
    localIntent.putExtra("android.intent.extra.SUBJECT", "FAQ");
    localIntent.setType("plain/text");
    try
    {
      startActivity(Intent.createChooser(localIntent, "Send mail..."));
      return;
    }
    catch (ActivityNotFoundException localActivityNotFoundException)
    {
      View localView = getLayoutInflater().inflate(2130903048, (ViewGroup)findViewById(2131296314));
      TextView localTextView = (TextView)localView.findViewById(2131296315);
      localTextView.setTypeface(this.tf);
      localTextView.setText("There are no email client installed!");
      Toast localToast = new Toast(getApplicationContext());
      localToast.setDuration(0);
      localToast.setView(localView);
      localToast.show();
    }
  }
  
  public void animateVisibilityGone(View paramView)
  {
    paramView.setVisibility(4);
    paramView.startAnimation(this.fadeOut);
    paramView.setVisibility(8);
  }
  
  public void animateVisibilityOn(View paramView)
  {
    paramView.setVisibility(4);
    paramView.startAnimation(this.fadeIn);
    paramView.setVisibility(0);
  }
  
  public void disableViewDelay(final View paramView1, final View paramView2)
  {
    paramView1.setClickable(false);
    paramView1.setEnabled(false);
    paramView2.setClickable(false);
    paramView2.setEnabled(false);
    new Handler().postDelayed(new Runnable()
    {
      public void run()
      {
        paramView1.setClickable(true);
        paramView1.setEnabled(true);
        paramView2.setClickable(true);
        paramView2.setEnabled(true);
      }
    }, 1300L);
  }
  
  public void followOnTwitter()
  {
  }
  
  public void onBackPressed()
  {
    String str = getIntent().getExtras().getString("Activity");
    Intent localIntent = new Intent();
    localIntent.setClassName("com.hyh.passwordassitant.activity", "com.hyh.passwordassitant.activity." + str);
    localIntent.addFlags(65536);
    startActivity(localIntent);
    finish();
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903064);
    getWindow().setSoftInputMode(3);
    this.tf = Typeface.createFromAsset(getAssets(), "fonts/deepblue.ttf");
    this.font = Typeface.createFromAsset(getAssets(), "fonts/galette.otf");
    this.slideLeftIn = AnimationUtils.loadAnimation(this, 2130968583);
    this.slideLeftIn.setDuration(350L);
    this.slideLeftIn1 = AnimationUtils.loadAnimation(this, 2130968583);
    this.slideLeftIn1.setDuration(700L);
    this.slideLeftIn2 = AnimationUtils.loadAnimation(this, 2130968583);
    this.slideLeftIn2.setDuration(1000L);
    this.slideLeftIn3 = AnimationUtils.loadAnimation(this, 2130968583);
    this.slideLeftIn3.setDuration(1300L);
    this.fadeIn = AnimationUtils.loadAnimation(this, 2130968582);
    this.fadeOut = AnimationUtils.loadAnimation(this, 2130968591);
    this.txtLike = ((TextView)findViewById(2131296480));
    this.txtLike.setTypeface(this.tf);
    this.txtImprove = ((TextView)findViewById(2131296492));
    this.txtImprove.setTypeface(this.tf);
    this.txtPPRate = ((TextView)findViewById(2131296484));
    this.txtPPRate.setTypeface(this.font);
    this.txtPPFollow = ((TextView)findViewById(2131296452));
    this.txtPPFollow.setTypeface(this.font);
    this.txtPPLike = ((TextView)findViewById(2131296488));
    this.txtPPLike.setTypeface(this.font);
    this.txtPPRequest = ((TextView)findViewById(2131296496));
    this.txtPPRequest.setTypeface(this.font);
    this.txtPPReport = ((TextView)findViewById(2131296498));
    this.txtPPReport.setTypeface(this.font);
    this.layoutPPLike = ((LinearLayout)findViewById(2131296479));
    this.layoutPPLike.startAnimation(this.slideLeftIn);
    this.layoutPPLikeInner = ((LinearLayout)findViewById(2131296481));
    this.layoutPPLikeBack = ((LinearLayout)findViewById(2131296482));
    this.layoutPPImprove = ((LinearLayout)findViewById(2131296491));
    this.layoutPPImprove.startAnimation(this.slideLeftIn1);
    this.layoutPPImproveInner = ((LinearLayout)findViewById(2131296493));
    this.layoutPPImproveBack = ((LinearLayout)findViewById(2131296494));
    this.layoutRate = ((RelativeLayout)findViewById(2131296483));
    this.layoutFollow = ((RelativeLayout)findViewById(2131296451));
    this.layoutLike = ((RelativeLayout)findViewById(2131296487));
    this.layoutRequest = ((RelativeLayout)findViewById(2131296495));
    this.layoutReport = ((RelativeLayout)findViewById(2131296497));
    this.layoutPPLike.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        FeedbackActivity.this.disableViewDelay(FeedbackActivity.this.layoutPPLike, FeedbackActivity.this.layoutPPImprove);
        ((TextView)paramAnonymousView.findViewById(2131296480)).setSelected(true);
        if (FeedbackActivity.this.layoutPPImproveInner.getVisibility() == 0)
        {
          FeedbackActivity.this.layoutPPImproveInner.setVisibility(8);
          FeedbackActivity.this.animateVisibilityOn(FeedbackActivity.this.layoutPPImprove);
        }
        FeedbackActivity.this.animateVisibilityGone(FeedbackActivity.this.layoutPPLike);
        FeedbackActivity.this.animateVisibilityOn(FeedbackActivity.this.layoutPPLikeInner);
        FeedbackActivity.this.layoutRate.startAnimation(FeedbackActivity.this.slideLeftIn1);
        FeedbackActivity.this.layoutFollow.startAnimation(FeedbackActivity.this.slideLeftIn2);
        FeedbackActivity.this.layoutLike.startAnimation(FeedbackActivity.this.slideLeftIn3);
      }
    });
    this.layoutPPLikeBack.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        FeedbackActivity.this.animateVisibilityGone(FeedbackActivity.this.layoutPPLikeInner);
        FeedbackActivity.this.animateVisibilityOn(FeedbackActivity.this.layoutPPLike);
      }
    });
    this.layoutPPImprove.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        FeedbackActivity.this.disableViewDelay(FeedbackActivity.this.layoutPPImprove, FeedbackActivity.this.layoutPPLike);
        ((TextView)paramAnonymousView.findViewById(2131296492)).setSelected(true);
        if (FeedbackActivity.this.layoutPPLikeInner.getVisibility() == 0)
        {
          FeedbackActivity.this.layoutPPLikeInner.setVisibility(8);
          FeedbackActivity.this.animateVisibilityOn(FeedbackActivity.this.layoutPPLike);
        }
        FeedbackActivity.this.animateVisibilityGone(FeedbackActivity.this.layoutPPImprove);
        FeedbackActivity.this.animateVisibilityOn(FeedbackActivity.this.layoutPPImproveInner);
        FeedbackActivity.this.layoutRequest.startAnimation(FeedbackActivity.this.slideLeftIn1);
        FeedbackActivity.this.layoutReport.startAnimation(FeedbackActivity.this.slideLeftIn3);
      }
    });
    this.layoutPPImproveBack.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        FeedbackActivity.this.animateVisibilityGone(FeedbackActivity.this.layoutPPImproveInner);
        FeedbackActivity.this.animateVisibilityOn(FeedbackActivity.this.layoutPPImprove);
      }
    });
    this.layoutRate.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        FeedbackActivity.this.rateUsOnGooglePlay();
      }
    });
    this.layoutLike.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        FeedbackActivity.this.shareWithFacebook();
      }
    });
    this.layoutFollow.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        FeedbackActivity.this.followOnTwitter();
      }
    });
    this.layoutRequest.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        FeedbackActivity.this.requestNewFeature();
      }
    });
    this.layoutReport.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        FeedbackActivity.this.reportBug();
      }
    });
  }
  
  public void onPause()
  {
    super.onPause();
    SharedPreferences.Editor localEditor = getSharedPreferences("X", 0).edit();
    localEditor.putString("lastActivity", getClass().getName());
    localEditor.commit();
  }
  
  protected void onRestart()
  {
    super.onRestart();
    SharedPreferences.Editor localEditor = getSharedPreferences("X", 0).edit();
    localEditor.putString("lastActivity", getClass().getName());
    localEditor.commit();
  }
  
  protected void onResume()
  {
    super.onResume();
    SharedPreferences.Editor localEditor = getSharedPreferences("X", 0).edit();
    localEditor.putString("lastActivity", getClass().getName());
    localEditor.commit();
  }
  
  protected void onStop()
  {
    super.onStop();
  }
  
  public void rateUsOnGooglePlay()
  {
    //Intent localIntent = new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + getPackageName()));
    //try
   // {
      //startActivity(localIntent);
     // return;
    //}
    //catch (ActivityNotFoundException localActivityNotFoundException)
    //{
      Toast.makeText(this, "取法启动市场", 1).show();
    //}
  }
  
  public void reportBug()
  {
    Intent localIntent = new Intent("android.intent.action.SEND");
    localIntent.setClassName("com.google.android.gm", "com.google.android.gm.ComposeActivityGmail");
    localIntent.putExtra("android.intent.extra.EMAIL", new String[] { "passwordpanacea@riverdeltaindia.com" });
    localIntent.setData(Uri.parse("1161459106@qq.com"));
    localIntent.putExtra("android.intent.extra.SUBJECT", "Caught a Bug");
    localIntent.setType("plain/text");
    try
    {
      startActivity(Intent.createChooser(localIntent, "发送邮件..."));
      return;
    }
    catch (ActivityNotFoundException localActivityNotFoundException)
    {
      View localView = getLayoutInflater().inflate(2130903048, (ViewGroup)findViewById(2131296314));
      TextView localTextView = (TextView)localView.findViewById(2131296315);
      localTextView.setTypeface(this.tf);
      localTextView.setText("未安装邮件客户端!");
      Toast localToast = new Toast(getApplicationContext());
      localToast.setDuration(0);
      localToast.setView(localView);
      localToast.show();
    }
  }
  
  public void requestNewFeature()
  {
    Intent localIntent = new Intent("android.intent.action.SEND");
    localIntent.setClassName("com.google.android.gm", "com.google.android.gm.ComposeActivityGmail");
    localIntent.putExtra("android.intent.extra.EMAIL", new String[] { "passwordpanacea@riverdeltaindia.com" });
    localIntent.setData(Uri.parse("1161459106@qq.com"));
    localIntent.putExtra("android.intent.extra.SUBJECT", "Request New Feature");
    localIntent.setType("plain/text");
    try
    {
      startActivity(Intent.createChooser(localIntent, "发送邮件..."));
      return;
    }
    catch (ActivityNotFoundException localActivityNotFoundException)
    {
      View localView = getLayoutInflater().inflate(2130903048, (ViewGroup)findViewById(2131296314));
      TextView localTextView = (TextView)localView.findViewById(2131296315);
      localTextView.setTypeface(this.tf);
      localTextView.setText("未安装邮件客户端!");
      Toast localToast = new Toast(getApplicationContext());
      localToast.setDuration(0);
      localToast.setView(localView);
      localToast.show();
    }
  }
  
  public void shareWithFacebook()
  {
  }
}



/* Location:           D:\Apkdb\Craining\dex2jar-0.0.9.15\classes_dex2jar.jar

 * Qualified Name:     rdi.mobapp.passwordpanacea.activity.FeedbackActivity

 * JD-Core Version:    0.7.0.1

 */