package com.hyh.passwordassitant.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.EditText;
import android.widget.Scroller;
import android.widget.TextView;
import java.util.ArrayList;
import rdi.mobapp.passwordpanacea.bean.MasterKey;
import rdi.mobapp.passwordpanacea.databasehelper.DBSettingsHelper;
import rdi.mobapp.passwordpanacea.utility.CustomDialogSingleButton;

public class ForgotMasterKeyActivity
  extends Activity
{
  private DBSettingsHelper dbSettingsHelper;
  private Drawable errorIcon;
  private Typeface font;
  private EditText keySecAns;
  private String masterKey;
  private ArrayList<MasterKey> masterKeyDetail;
  private String secAns;
  private String secQue;
  private TextView txtSubmit;
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    requestWindowFeature(1);
    setContentView(2130903065);
    getWindow().setSoftInputMode(3);
    this.errorIcon = getResources().getDrawable(2130837593);
    this.errorIcon.setBounds(new Rect(0, 0, this.errorIcon.getIntrinsicWidth(), this.errorIcon.getIntrinsicHeight()));
    this.font = Typeface.createFromAsset(getAssets(), "fonts/galette.otf");
    this.keySecAns = ((EditText)findViewById(2131296499));
    this.keySecAns.setTypeface(this.font);
    this.keySecAns.setScroller(new Scroller(this));
    this.keySecAns.setMaxLines(1);
    this.keySecAns.setVerticalScrollBarEnabled(true);
    this.keySecAns.setMovementMethod(new ScrollingMovementMethod());
    this.txtSubmit = ((TextView)findViewById(2131296500));
    this.txtSubmit.setTypeface(this.font);
    this.masterKeyDetail = new ArrayList();
    this.dbSettingsHelper = new DBSettingsHelper(this);
    this.masterKeyDetail = this.dbSettingsHelper.getMasterKeyDetail();
    this.masterKey = ((MasterKey)this.masterKeyDetail.get(0)).getMasterKey();
    this.secQue = ((MasterKey)this.masterKeyDetail.get(0)).getKeySecQue();
    this.secAns = ((MasterKey)this.masterKeyDetail.get(0)).getKeySecAns();
    this.keySecAns.setHint(this.secQue);
    this.keySecAns.setScroller(new Scroller(this));
    this.keySecAns.setMaxLines(1);
    this.keySecAns.setVerticalScrollBarEnabled(true);
    this.keySecAns.setMovementMethod(new ScrollingMovementMethod());
    this.txtSubmit.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (ForgotMasterKeyActivity.this.keySecAns.getText().toString().toLowerCase().equals(ForgotMasterKeyActivity.this.secAns.toLowerCase()))
        {
          CustomDialogSingleButton localCustomDialogSingleButton = new CustomDialogSingleButton(ForgotMasterKeyActivity.this, "Master Key!", ForgotMasterKeyActivity.this.masterKey);
          localCustomDialogSingleButton.show();
          localCustomDialogSingleButton.customAlertBoxPositiveButton.setOnClickListener(new View.OnClickListener()
          {
            public void onClick(View paramAnonymous2View)
            {
              Intent localIntent = new Intent(ForgotMasterKeyActivity.this.getApplicationContext(), MasterKeyLockActivity.class);
              localIntent.addFlags(65536);
              ForgotMasterKeyActivity.this.startActivity(localIntent);
              ForgotMasterKeyActivity.this.finish();
            }
          });
          return;
        }
        if (ForgotMasterKeyActivity.this.keySecAns.getText().toString().length() == 0)
        {
          new CustomDialogSingleButton(ForgotMasterKeyActivity.this, "Invalid!", "请回答密保问题.").show();
          ForgotMasterKeyActivity.this.keySecAns.setError(null, ForgotMasterKeyActivity.this.errorIcon);
          return;
        }
        new CustomDialogSingleButton(ForgotMasterKeyActivity.this, "Invalid!", "密保问题回答错误.").show();
      }
    });
  }
}



/* Location:           D:\Apkdb\Craining\dex2jar-0.0.9.15\classes_dex2jar.jar

 * Qualified Name:     rdi.mobapp.passwordpanacea.activity.ForgotMasterKeyActivity

 * JD-Core Version:    0.7.0.1

 */