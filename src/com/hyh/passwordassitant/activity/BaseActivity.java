package com.hyh.passwordassitant.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;
import rdi.mobapp.passwordpanacea.application.PasswordPanacea;

public class BaseActivity
  extends Activity
{
  private Typeface font;
  private Typeface font2;
  private LinearLayout group = null;
  private TextView header;
  private LinearLayout help = null;
  private LinearLayout key = null;
  private LinearLayout setting = null;
  private TextView txtGroup;
  private TextView txtHelp;
  private TextView txtKey;
  private TextView txtSetting;
  private View viewFooterBottom;
  private View viewFooterBottomGrp;
  private LinearLayout viewFooterSeperator;
  private LinearLayout viewFooterSeperator2;
  private View viewFooterTop;
  private View viewFooterTopGrp;
  
  public void onCreate(Bundle paramBundle, int paramInt, String paramString)
  {
    super.onCreate(paramBundle);
    requestWindowFeature(1);
    setContentView(paramInt);
    getWindow().setSoftInputMode(3);
    this.viewFooterTopGrp = findViewById(2131296364);
    this.viewFooterBottomGrp = findViewById(2131296386);
    this.font = Typeface.createFromAsset(getAssets(), "fonts/deepblue.ttf");
    this.font2 = Typeface.createFromAsset(getAssets(), "fonts/galette.otf");
    this.txtGroup = ((TextView)findViewById(2131296370));
    this.txtGroup.setTypeface(this.font2);
    this.txtKey = ((TextView)findViewById(2131296375));
    this.txtKey.setTypeface(this.font2);
    this.txtSetting = ((TextView)findViewById(2131296380));
    this.txtSetting.setTypeface(this.font2);
    this.txtHelp = ((TextView)findViewById(2131296385));
    this.txtHelp.setTypeface(this.font2);
    this.header = ((TextView)findViewById(2131296441));
    this.header.setTypeface(this.font);
    if (paramString.equalsIgnoreCase("Key"))
    {
      this.header.setText("");
    }else{
    	this.header.setText(paramString);
    }
      if ((!paramString.equalsIgnoreCase("Key")) && (!paramString.equalsIgnoreCase("Add Key")) && (!paramString.equalsIgnoreCase("Edit Key")) && (!paramString.equalsIgnoreCase("View Key"))) {
    	  if ((paramString.equalsIgnoreCase("Tools")) || (paramString.equalsIgnoreCase("Template")) || (paramString.equalsIgnoreCase("Backups")) || (paramString.equalsIgnoreCase("Trash")) || (paramString.equalsIgnoreCase("")))
          {
            this.txtSetting.setTextColor(Color.parseColor("#000000"));
            this.viewFooterTopGrp.setBackgroundColor(Color.parseColor("#666666"));
            this.viewFooterBottomGrp.setBackgroundColor(Color.parseColor("#666666"));
            this.viewFooterTop = findViewById(2131296366);
            this.viewFooterTop.setBackgroundColor(Color.parseColor("#ff8e0d"));
            this.viewFooterBottom = findViewById(2131296388);
            this.viewFooterBottom.setBackgroundColor(Color.parseColor("#ff8e0d"));
            this.viewFooterSeperator = ((LinearLayout)findViewById(2131296372));
            this.viewFooterSeperator.setVisibility(0);
          }
          else if ((paramString.equalsIgnoreCase("Help")) || (paramString.equalsIgnoreCase("Feedback")) || (paramString.equalsIgnoreCase("More")))
          {
            this.txtHelp.setTextColor(Color.parseColor("#000000"));
            this.viewFooterTopGrp.setBackgroundColor(Color.parseColor("#666666"));
            this.viewFooterBottomGrp.setBackgroundColor(Color.parseColor("#666666"));
            this.viewFooterTop = findViewById(2131296367);
            this.viewFooterTop.setBackgroundColor(Color.parseColor("#ff8e0d"));
            this.viewFooterBottom = findViewById(2131296389);
            this.viewFooterBottom.setBackgroundColor(Color.parseColor("#ff8e0d"));
            this.viewFooterSeperator = ((LinearLayout)findViewById(2131296372));
            this.viewFooterSeperator.setVisibility(0);
            this.viewFooterSeperator2 = ((LinearLayout)findViewById(2131296377));
            this.viewFooterSeperator2.setVisibility(0);
          }
          else if ((paramString.equalsIgnoreCase("Group")) || (paramString.equalsIgnoreCase("Add Group")) || (paramString.equalsIgnoreCase("Edit Group")) || (paramString.equalsIgnoreCase("View Group")))
          {
            this.txtGroup.setTextColor(Color.parseColor("#000000"));
            this.viewFooterSeperator = ((LinearLayout)findViewById(2131296377));
            this.viewFooterSeperator.setVisibility(0);
            this.viewFooterSeperator2 = ((LinearLayout)findViewById(2131296382));
            this.viewFooterSeperator2.setVisibility(0);
          }
      }else{
	      this.txtKey.setTextColor(Color.parseColor("#000000"));
	      this.viewFooterTopGrp.setBackgroundColor(Color.parseColor("#666666"));
	      this.viewFooterBottomGrp.setBackgroundColor(Color.parseColor("#666666"));
	      this.viewFooterTop = findViewById(2131296365);
	      this.viewFooterTop.setBackgroundColor(Color.parseColor("#ff8e0d"));
	      this.viewFooterBottom = findViewById(2131296387);
	      this.viewFooterBottom.setBackgroundColor(Color.parseColor("#ff8e0d"));
	      this.viewFooterSeperator = ((LinearLayout)findViewById(2131296382));
	      this.viewFooterSeperator.setVisibility(0);
      }
    
      this.group = ((LinearLayout)findViewById(2131296368));
      this.group.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          Intent localIntent = new Intent(BaseActivity.this.getApplicationContext(), GroupActivity.class);
          PasswordPanacea.setDisableAnim(false);
          localIntent.addFlags(65536);
          BaseActivity.this.startActivity(localIntent);
          BaseActivity.this.finish();
        }
      });
      this.key = ((LinearLayout)findViewById(2131296373));
      this.key.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          Intent localIntent = new Intent(BaseActivity.this.getApplicationContext(), KeyActivity.class);
          PasswordPanacea.setDisableKeyAnim(false);
          PasswordPanacea.setFooterKeyPressed(true);
          localIntent.addFlags(65536);
          BaseActivity.this.startActivity(localIntent);
          BaseActivity.this.finish();
        }
      });
      this.setting = ((LinearLayout)findViewById(2131296378));
      this.setting.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          PasswordPanacea.setTrashActivity("SettingActivity");
          Intent localIntent = new Intent(BaseActivity.this.getApplicationContext(), SettingActivity.class);
          localIntent.addFlags(65536);
          BaseActivity.this.startActivity(localIntent);
          BaseActivity.this.finish();
        }
      });
      this.help = ((LinearLayout)findViewById(2131296383));
      this.help.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          Intent localIntent = new Intent(BaseActivity.this.getApplicationContext(), MoreActivity.class);
          localIntent.addFlags(65536);
          BaseActivity.this.startActivity(localIntent);
          BaseActivity.this.finish();
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
}


/* Location:           D:\Apkdb\Craining\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     rdi.mobapp.passwordpanacea.activity.BaseActivity
 * JD-Core Version:    0.7.0.1
 */