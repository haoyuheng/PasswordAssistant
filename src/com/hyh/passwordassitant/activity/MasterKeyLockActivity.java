package com.hyh.passwordassitant.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Process;
import android.text.ClipboardManager;
import android.text.Editable;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;
import java.util.ArrayList;
import rdi.mobapp.passwordpanacea.application.PasswordPanacea;
import rdi.mobapp.passwordpanacea.bean.MasterKey;
import rdi.mobapp.passwordpanacea.databasehelper.DBSettingsHelper;
import rdi.mobapp.passwordpanacea.utility.CustomDialog;
import rdi.mobapp.passwordpanacea.utility.CustomDialogSingleButton;

public class MasterKeyLockActivity
  extends Activity
{
  private TextView btnUnblock;
  private DBSettingsHelper dbSettingsHelper;
  private Typeface font_;
  private String key;
  private EditText masterKey;
  private ArrayList<MasterKey> masterKeyDetail;
  private TextView txtForgot;
  
  public void btnUnlock(View paramView)
  {
    if (this.masterKey.getText().length() != 0)
    {
      String str = this.masterKey.getText().toString();
      this.masterKeyDetail = new ArrayList();
      this.masterKeyDetail = this.dbSettingsHelper.getMasterKeyDetail();
      this.key = ((MasterKey)this.masterKeyDetail.get(0)).getMasterKey();
      if (this.key.equals(str))
      {
        this.masterKey.setText("");
        startActivity(new Intent(getApplicationContext(), GroupActivity.class));
        return;
      }
      new CustomDialogSingleButton(this, "Invalid!", "管理密码错误.").show();
      return;
    }
    new CustomDialogSingleButton(this, "Invalid!", "请输入管理密码.").show();
  }
  
  public void forgotPassword(View paramView)
  {
    startActivity(new Intent(getApplicationContext(), ForgotMasterKeyActivity.class));
  }
  
  public void onBackPressed()
  {
    final ClipboardManager localClipboardManager = (ClipboardManager)getSystemService("clipboard");
    CustomDialog localCustomDialog = new CustomDialog(this, "Exit?", "确定要退出吗?");
    localCustomDialog.show();
    localCustomDialog.customAlertBoxPositiveButton.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (!PasswordPanacea.getCopyOutsideApp()) {
          localClipboardManager.setText("");
        }
        Intent localIntent = new Intent(MasterKeyLockActivity.this.getApplicationContext(), Dispatcher.class);
        localIntent.addFlags(65536);
        PasswordPanacea.setGroupActivity(null);
        PasswordPanacea.setKeyActivity(null);
        MasterKeyLockActivity.this.startActivity(localIntent);
        MasterKeyLockActivity.this.moveTaskToBack(true);
        MasterKeyLockActivity.this.finish();
        Process.killProcess(Process.myPid());
      }
    });
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    requestWindowFeature(1);
    setContentView(2130903063);
    getWindow().setSoftInputMode(3);
    this.font_ = Typeface.createFromAsset(getAssets(), "fonts/galette.otf");
    this.txtForgot = ((TextView)findViewById(2131296476));
    this.txtForgot.setTypeface(this.font_);
    this.btnUnblock = ((TextView)findViewById(2131296474));
    this.btnUnblock.setTypeface(this.font_);
    this.dbSettingsHelper = new DBSettingsHelper(this);
    this.masterKey = ((EditText)findViewById(2131296473));
    this.masterKey.setTypeface(this.font_);
  }
}


/* Location:           D:\Apkdb\Craining\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     rdi.mobapp.passwordpanacea.activity.MasterKeyLockActivity
 * JD-Core Version:    0.7.0.1
 */