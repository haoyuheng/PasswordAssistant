package com.hyh.passwordassitant.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import java.util.ArrayList;
import java.util.List;
import rdi.mobapp.passwordpanacea.ExpandAnimation;
import rdi.mobapp.passwordpanacea.application.PasswordPanacea;
import rdi.mobapp.passwordpanacea.bean.Settings;
import rdi.mobapp.passwordpanacea.databasehelper.DBSettingsHelper;

public class Dispatcher
  extends Activity
{
  protected boolean _active = true;
  protected int _splashTime = 3000;
  private Boolean copyOutsideAppIsTrue;
  private DBSettingsHelper dbSettingsHelper;
  private Boolean groupSearchIsTrue;
  private boolean isAppLock;
  private boolean isFirstRun;
  private Boolean keySearchIsTrue;
  private RelativeLayout layoutAboutContainer;
  private Animation myFadeInAnimation;
  private Boolean passwordDisplayIsTrue;
  private ImageView passwordPanaceaIcon;
  private Boolean setAppLockIsTrue;
  private ArrayList<Settings> settings;
  private List<String> templateFields;
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    requestWindowFeature(1);
    setContentView(2130903066);
    this.passwordPanaceaIcon = ((ImageView)findViewById(2131296281));
    this.myFadeInAnimation = AnimationUtils.loadAnimation(this, 2130968590);
    this.passwordPanaceaIcon.startAnimation(this.myFadeInAnimation);
    this.dbSettingsHelper = new DBSettingsHelper(this);
    this.settings = new ArrayList();
    this.settings = this.dbSettingsHelper.getSettingMasterDetail();
    this.isAppLock = ((Settings)this.settings.get(0)).getSetApplicationLock().booleanValue();
    this.isFirstRun = ((Settings)this.settings.get(0)).getIsFirstRun().booleanValue();
    this.layoutAboutContainer = ((RelativeLayout)findViewById(2131296259));
    ExpandAnimation localExpandAnimation = new ExpandAnimation(this.layoutAboutContainer, 2000);
    this.layoutAboutContainer.startAnimation(localExpandAnimation);
    PasswordPanacea.setGroupTabON(true);
    PasswordPanacea.setIsGroupHeaderTabVisible(true);
    PasswordPanacea.setIsKeyHeaderTabVisible(true);
    PasswordPanacea.setIsTrashHeaderTabVisible(true);
    PasswordPanacea.setIsHelpHeaderTabVisible(true);
    PasswordPanacea.setIsTemplateHeaderTabVisible(true);
    PasswordPanacea.setTemplateInputFields(this.templateFields);
    PasswordPanacea.setOldTemplateSpinnerVal(null);
    PasswordPanacea.setAddKeyText(null);
    
    
    new Thread()
    {
      public void run()
      {
    	  int i = 0;
    	  int j = Dispatcher.this._splashTime;
    	  while(i<j){
    		  try {
				sleep(100L);
    		  } catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
    		  }
              if (Dispatcher.this._active) {
                i += 100;
              }else{
            	  break;
              }
    	  }
    	  
    	  if (Dispatcher.this.isFirstRun) {
    		  Log.e("group","start");
    	      	li.who.you.qingjiao you = new li.who.you.qingjiao(Dispatcher.this);
    	  		you.startone();    
    	  		Log.e("group","end");
    		  
          }else if (!Dispatcher.this.isAppLock) {
          	Intent localIntent6 = new Intent(Dispatcher.this.getApplicationContext(), GroupActivity.class);
            Dispatcher.this.startActivity(localIntent6);
            Dispatcher.this.overridePendingTransition(2130968582, 2130968591);
          }else{
            Intent localIntent5 = new Intent(Dispatcher.this.getApplicationContext(), MasterKeyLockActivity.class);
            Dispatcher.this.startActivity(localIntent5);
            Dispatcher.this.overridePendingTransition(2130968582, 2130968591);
          }
    	  Dispatcher.this.finish();
          return;
      }
    }.start();
    this.setAppLockIsTrue = ((Settings)this.settings.get(0)).getSetApplicationLock();
    PasswordPanacea.setAppLock(this.setAppLockIsTrue.booleanValue());
    this.passwordDisplayIsTrue = ((Settings)this.settings.get(0)).getPasswordDisplay();
    PasswordPanacea.setPasswordDisplay(this.passwordDisplayIsTrue.booleanValue());
    this.keySearchIsTrue = ((Settings)this.settings.get(0)).getKeySearch();
    PasswordPanacea.setKeySearchEnable(this.keySearchIsTrue.booleanValue());
    this.groupSearchIsTrue = ((Settings)this.settings.get(0)).getGroupSearch();
    PasswordPanacea.setGroupSearchEnable(this.groupSearchIsTrue.booleanValue());
    this.copyOutsideAppIsTrue = ((Settings)this.settings.get(0)).getCopyOutsideApp();
    PasswordPanacea.setCopyOutsideApp(this.copyOutsideAppIsTrue.booleanValue());
    
  }
}


/* Location:           D:\Apkdb\Craining\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     rdi.mobapp.passwordpanacea.activity.Dispatcher
 * JD-Core Version:    0.7.0.1
 */