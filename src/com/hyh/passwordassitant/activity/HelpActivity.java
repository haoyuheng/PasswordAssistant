package com.hyh.passwordassitant.activity;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class HelpActivity
  extends Activity
{
  private RelativeLayout layoutOverview1;
  private RelativeLayout layoutOverview2;
  private LinearLayout layoutOverview3;
  private RelativeLayout layoutOverview4;
  private LinearLayout layoutOverview4_1;
  private RelativeLayout layoutOverview5;
  private Animation slideLeftIn1;
  private Animation slideLeftIn2;
  private Animation slideLeftIn3;
  private Animation slideLeftIn4;
  private Animation slideLeftIn5;
  private Animation slideLeftIn6;
  private TextView txtBackup;
  private TextView txtFieldNewField;
  private TextView txtFieldPsw;
  private TextView txtFieldTitle;
  private TextView txtFieldUname;
  private TextView txtFieldUrl;
  private TextView txtGenSettings;
  private TextView txtKeyTemplate;
  private TextView txtKeytoolDelete;
  private TextView txtKeytoolEdit;
  private TextView txtKeytoolPassword;
  private TextView txtKeytoolUrl;
  private TextView txtKeytoolUsename;
  private TextView txtMasterKey;
  private TextView txtOverview1;
  private TextView txtOverview2;
  private TextView txtOverview3;
  private TextView txtOverview4;
  private TextView txtRestore;
  private TextView txtTrash;
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    requestWindowFeature(1);
    setContentView(2130903067);
    getWindow().setSoftInputMode(3);
    Typeface localTypeface1 = Typeface.createFromAsset(getAssets(), "fonts/deepblue.ttf");
    Typeface localTypeface2 = Typeface.createFromAsset(getAssets(), "fonts/galette.otf");
    this.slideLeftIn1 = AnimationUtils.loadAnimation(this, 2130968583);
    this.slideLeftIn1.setDuration(300L);
    this.slideLeftIn2 = AnimationUtils.loadAnimation(this, 2130968583);
    this.slideLeftIn2.setDuration(500L);
    this.slideLeftIn3 = AnimationUtils.loadAnimation(this, 2130968583);
    this.slideLeftIn3.setDuration(700L);
    this.slideLeftIn4 = AnimationUtils.loadAnimation(this, 2130968583);
    this.slideLeftIn4.setDuration(900L);
    this.slideLeftIn5 = AnimationUtils.loadAnimation(this, 2130968583);
    this.slideLeftIn5.setDuration(1100L);
    this.slideLeftIn6 = AnimationUtils.loadAnimation(this, 2130968583);
    this.slideLeftIn6.setDuration(1300L);
    this.layoutOverview1 = ((RelativeLayout)findViewById(2131296504));
    this.layoutOverview1.startAnimation(this.slideLeftIn1);
    this.layoutOverview2 = ((RelativeLayout)findViewById(2131296506));
    this.layoutOverview2.startAnimation(this.slideLeftIn2);
    this.layoutOverview3 = ((LinearLayout)findViewById(2131296508));
    this.layoutOverview3.startAnimation(this.slideLeftIn3);
    this.layoutOverview4 = ((RelativeLayout)findViewById(2131296509));
    this.layoutOverview4.startAnimation(this.slideLeftIn4);
    this.layoutOverview4_1 = ((LinearLayout)findViewById(2131296511));
    this.layoutOverview4_1.startAnimation(this.slideLeftIn5);
    this.layoutOverview5 = ((RelativeLayout)findViewById(2131296521));
    this.layoutOverview5.startAnimation(this.slideLeftIn6);
    this.txtOverview1 = ((TextView)findViewById(2131296505));
    this.txtOverview1.setTypeface(localTypeface1);
    this.txtOverview2 = ((TextView)findViewById(2131296507));
    this.txtOverview2.setTypeface(localTypeface1);
    this.txtOverview3 = ((TextView)findViewById(2131296510));
    this.txtOverview3.setTypeface(localTypeface1);
    this.txtOverview4 = ((TextView)findViewById(2131296522));
    this.txtOverview4.setTypeface(localTypeface1);
    this.txtFieldTitle = ((TextView)findViewById(2131296512));
    this.txtFieldTitle.setTypeface(localTypeface2);
    this.txtFieldUname = ((TextView)findViewById(2131296514));
    this.txtFieldUname.setTypeface(localTypeface2);
    this.txtFieldPsw = ((TextView)findViewById(2131296515));
    this.txtFieldPsw.setTypeface(localTypeface2);
    this.txtFieldUrl = ((TextView)findViewById(2131296516));
    this.txtFieldUrl.setTypeface(localTypeface2);
    this.txtFieldNewField = ((TextView)findViewById(2131296517));
    this.txtFieldNewField.setTypeface(localTypeface2);
    this.txtKeytoolUsename = ((TextView)findViewById(2131296459));
    this.txtKeytoolUsename.setTypeface(localTypeface2);
    this.txtKeytoolPassword = ((TextView)findViewById(2131296462));
    this.txtKeytoolPassword.setTypeface(localTypeface2);
    this.txtKeytoolUrl = ((TextView)findViewById(2131296465));
    this.txtKeytoolUrl.setTypeface(localTypeface2);
    this.txtKeytoolEdit = ((TextView)findViewById(2131296404));
    this.txtKeytoolEdit.setTypeface(localTypeface2);
    this.txtKeytoolDelete = ((TextView)findViewById(2131296407));
    this.txtKeytoolDelete.setTypeface(localTypeface2);
    this.txtGenSettings = ((TextView)findViewById(2131296523));
    this.txtGenSettings.setTypeface(localTypeface2);
    this.txtBackup = ((TextView)findViewById(2131296524));
    this.txtBackup.setTypeface(localTypeface2);
    this.txtRestore = ((TextView)findViewById(2131296525));
    this.txtRestore.setTypeface(localTypeface2);
    this.txtTrash = ((TextView)findViewById(2131296526));
    this.txtTrash.setTypeface(localTypeface2);
    this.txtKeyTemplate = ((TextView)findViewById(2131296527));
    this.txtKeyTemplate.setTypeface(localTypeface2);
    this.txtMasterKey = ((TextView)findViewById(2131296528));
    this.txtMasterKey.setTypeface(localTypeface2);
  }
}


/* Location:           D:\Apkdb\Craining\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     rdi.mobapp.passwordpanacea.activity.HelpActivity
 * JD-Core Version:    0.7.0.1
 */