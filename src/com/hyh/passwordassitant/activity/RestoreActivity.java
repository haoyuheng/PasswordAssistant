package com.hyh.passwordassitant.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import rdi.mobapp.passwordpanacea.adapter.RestoreArrayAdapter;
import rdi.mobapp.passwordpanacea.application.PasswordPanacea;
import rdi.mobapp.passwordpanacea.bean.Elements;
import rdi.mobapp.passwordpanacea.utility.CustomDialogSingleButton;

public class RestoreActivity
  extends BaseActivity
{
  private List<Elements> Items;
  private ImageButton btnSetting;
  private LinearLayout footerSetting;
  private ImageView headerIcon;
  private ListView listView;
  private ImageButton optnMore;
  private RestoreArrayAdapter restoreArrayAdapter;
  private LinearLayout setting;
  private Typeface tf;
  private TextView txtSetting;
  private View viewLeftShadow;
  private View viewRightShadow;
  
  public void backPressed()
  {
    getWindow().setSoftInputMode(3);
    Intent localIntent = new Intent(getApplicationContext(), SettingActivity.class);
    PasswordPanacea.setSettingActivity(null);
    localIntent.addFlags(65536);
    PasswordPanacea.setDisableAnim(false);
    startActivity(localIntent);
    finish();
  }
  
  public void headerBackPressed(View paramView)
  {
    backPressed();
  }
  
  public void onBackPressed()
  {
    backPressed();
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle, 2130903070, "Backups");
    PasswordPanacea.setSettingActivity("RestoreActivity");
    this.tf = Typeface.createFromAsset(getAssets(), "fonts/galette.otf");
    this.txtSetting = ((TextView)findViewById(2131296380));
    this.txtSetting.setTextColor(Color.parseColor("#000000"));
    this.txtSetting.setTypeface(this.tf);
    this.headerIcon = ((ImageView)findViewById(2131296440));
    this.headerIcon.setImageResource(2130837510);
    this.viewLeftShadow = findViewById(2131296376);
    this.viewLeftShadow.setVisibility(0);
    this.viewLeftShadow.setBackgroundResource(2130837577);
    this.viewRightShadow = findViewById(2131296381);
    this.viewRightShadow.setVisibility(0);
    this.viewRightShadow.setBackgroundResource(2130837578);
    this.btnSetting = ((ImageButton)findViewById(2131296379));
    this.btnSetting.setImageResource(2130837653);
    this.optnMore = ((ImageButton)findViewById(2131296442));
    this.optnMore.setVisibility(8);
    this.setting = ((LinearLayout)findViewById(2131296378));
    this.setting.setEnabled(false);
    this.footerSetting = ((LinearLayout)findViewById(2131296378));
    this.footerSetting.setBackgroundResource(2130837537);
    this.footerSetting.setEnabled(false);
    this.listView = ((ListView)findViewById(2131296551));
    File[] arrayOfFile = Environment.getExternalStorageDirectory().listFiles();
    this.Items = new ArrayList();
    int i = arrayOfFile.length;
    for (int j = 0;; j++)
    {
      if (j >= i)
      {
        if (this.Items.size() != 0) {
          break;
        }
        new CustomDialogSingleButton(this, "Alert!", "没有有效的备份文件.").show();
        return;
      }
      if (arrayOfFile[j].getName().contains("DBDetails")) {
        this.Items.add(new Elements(arrayOfFile[j].getName(), j));
      }
    }
    this.restoreArrayAdapter = new RestoreArrayAdapter(this, this.Items, this.listView);
    this.listView.setAdapter(this.restoreArrayAdapter);
  }
}


/* Location:           D:\Apkdb\Craining\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     rdi.mobapp.passwordpanacea.activity.RestoreActivity
 * JD-Core Version:    0.7.0.1
 */