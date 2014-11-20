package com.hyh.passwordassitant.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import rdi.mobapp.passwordpanacea.databasehelper.DBSettingsHelper;

public class HelpActivityAtFirstRun
  extends Activity
{
  private LinearLayout layoutReadyToUse;
  private RelativeLayout layoutWelcome;
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
  private TextView txtReadyToUse;
  private TextView txtRestore;
  private TextView txtTakeGuide;
  private TextView txtTrash;
  private TextView txtWelcome;
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    requestWindowFeature(1);
    setContentView(2130903067);
    getWindow().setSoftInputMode(3);
    Typeface localTypeface1 = Typeface.createFromAsset(getAssets(), "fonts/deepblue.ttf");
    Typeface localTypeface2 = Typeface.createFromAsset(getAssets(), "fonts/galette.otf");
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
    this.layoutWelcome = ((RelativeLayout)findViewById(2131296501));
    this.layoutWelcome.setVisibility(0);
    this.layoutReadyToUse = ((LinearLayout)findViewById(2131296529));
    this.layoutReadyToUse.setVisibility(0);
    this.txtReadyToUse = ((TextView)findViewById(2131296530));
    this.txtReadyToUse.setTypeface(localTypeface1);
    this.txtWelcome = ((TextView)findViewById(2131296502));
    this.txtWelcome.setTypeface(localTypeface1);
    this.txtTakeGuide = ((TextView)findViewById(2131296503));
    this.txtTakeGuide.setTypeface(localTypeface2);
    this.txtReadyToUse.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        new DBSettingsHelper(HelpActivityAtFirstRun.this).updateIsFirstRun(Boolean.valueOf(false));
        Intent localIntent = new Intent(HelpActivityAtFirstRun.this.getApplicationContext(), GroupActivity.class);
        HelpActivityAtFirstRun.this.startActivity(localIntent);
        HelpActivityAtFirstRun.this.overridePendingTransition(2130968582, 2130968591);
      }
    });
  }
}


/* Location:           D:\Apkdb\Craining\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     rdi.mobapp.passwordpanacea.activity.HelpActivityAtFirstRun
 * JD-Core Version:    0.7.0.1
 */