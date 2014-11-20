package com.hyh.passwordassitant.activity;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.text.Editable;
import android.text.method.PasswordTransformationMethod;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import rdi.mobapp.passwordpanacea.ExpandAnimation;
import rdi.mobapp.passwordpanacea.application.PasswordPanacea;
import rdi.mobapp.passwordpanacea.bean.MasterKey;
import rdi.mobapp.passwordpanacea.bean.Settings;
import rdi.mobapp.passwordpanacea.databasehelper.DBSettingsHelper;
import rdi.mobapp.passwordpanacea.utility.CustomDialogSingleButton;
import rdi.mobapp.passwordpanacea.utility.MyLinearLayoutDialog;

public class SettingActivity
  extends BaseActivity
{
  private Button btnCancle;
  private Button btnSave;
  private ImageButton btnSetting;
  private TextView changeMasterKey;
  private TextView copyOutsideApp;
  private Boolean copyOutsideAppIsTrue;
  private TextView copyOutsideAppOff;
  private TextView copyOutsideAppOn;
  private DBSettingsHelper dbSettingHelper;
  private ImageView downArrow;
  private Drawable errorIcon;
  private Boolean firstClick = Boolean.valueOf(false);
  private LinearLayout footerSetting;
  private TextView generalSettings;
  private Boolean generalSettingsClicked = Boolean.valueOf(false);
  private TextView groupSearch;
  private Boolean groupSearchIsTrue;
  private TextView groupSearchOff;
  private TextView groupSearchOn;
  private Handler handler;
  private ImageView headerIcon;
  private TextView keySearch;
  private Boolean keySearchIsTrue;
  private TextView keySearchOff;
  private TextView keySearchOn;
  private RelativeLayout layoutMasterKeyOld;
  private LinearLayout linearLayoutBackupRestore;
  private LinearLayout linearLayoutChangeMasterKey;
  private LinearLayout linearLayoutChangeMasterKeyContainer;
  private LinearLayout linearLayoutChangeSecQueAns;
  private LinearLayout linearLayoutChangeSecQueAnsContainer;
  private LinearLayout linearLayoutChangeSecQueAnsTextImage;
  private LinearLayout linearLayoutGeneralSettings;
  private LinearLayout linearLayoutGeneralSettingsContainer;
  private LinearLayout linearLayoutRecycleBin;
  private LinearLayout linearLayoutTemplate;
  private String masterKey;
  private ArrayList<MasterKey> masterKeyDetail;
  private Boolean masterKeyFirstClick = Boolean.valueOf(false);
  private MyLinearLayoutDialog myLinearLayout;
  private ImageButton optnMore;
  private TextView passwordDisplay;
  private Boolean passwordDisplayIsTrue;
  private TextView passwordDisplayOff;
  private TextView passwordDisplayOn;
  private ScrollView scrollSettings;
  private Boolean setAppLockIsTrue;
  private TextView setApplicationLock;
  private TextView setApplicationLockOff;
  private TextView setApplicationLockOn;
  private ArrayList<Settings> settingDetail;
  private Animation slideLeftIn1;
  private Animation slideLeftIn2;
  private Animation slideLeftIn3;
  private Animation slideLeftIn4;
  private Animation slideLeftIn5;
  private Typeface tf;
  private TextView txtBackup;
  private EditText txtMasterKeyConfirm;
  private EditText txtMasterKeyNew;
  private EditText txtMasterKeyOld;
  private EditText txtMasterKeySecAns;
  private EditText txtMasterKeySecQue;
  private TextView txtRecycleBin;
  private TextView txtRestore;
  private TextView txtSecQueAns;
  private TextView txtSetting;
  private TextView txtTemplateSetting;
  private View viewLeftShadow;
  private View viewRightShadow;
  
  public void backPressed()
  {
    Intent localIntent = new Intent(getApplicationContext(), GroupActivity.class);
    localIntent.addFlags(65536);
    startActivity(localIntent);
    finish();
  }
  
  public void headerBackPressed(View paramView) {}
  
  public void onBackPressed()
  {
    backPressed();
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle, 2130903071, "Tools");
    getWindow().setSoftInputMode(3);
    this.tf = Typeface.createFromAsset(getAssets(), "fonts/galette.otf");
    this.txtSetting = ((TextView)findViewById(2131296380));
    this.txtSetting.setTextColor(Color.parseColor("#000000"));
    this.txtSetting.setTypeface(this.tf);
    this.headerIcon = ((ImageView)findViewById(2131296440));
    this.headerIcon.setImageResource(2130837638);
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
    this.slideLeftIn1 = AnimationUtils.loadAnimation(this, 2130968583);
    this.slideLeftIn1.setDuration(300L);
    this.slideLeftIn2 = AnimationUtils.loadAnimation(this, 2130968583);
    this.slideLeftIn2.setDuration(400L);
    this.slideLeftIn3 = AnimationUtils.loadAnimation(this, 2130968583);
    this.slideLeftIn3.setDuration(500L);
    this.slideLeftIn4 = AnimationUtils.loadAnimation(this, 2130968583);
    this.slideLeftIn4.setDuration(600L);
    this.slideLeftIn5 = AnimationUtils.loadAnimation(this, 2130968583);
    this.slideLeftIn5.setDuration(700L);
    this.errorIcon = getResources().getDrawable(2130837593);
    this.errorIcon.setBounds(new Rect(0, 0, this.errorIcon.getIntrinsicWidth(), this.errorIcon.getIntrinsicHeight()));
    this.myLinearLayout = ((MyLinearLayoutDialog)findViewById(2131296275));
    this.myLinearLayout.setMyListActivity(this);
    this.footerSetting = ((LinearLayout)findViewById(2131296378));
    this.footerSetting.setBackgroundResource(2130837537);
    this.footerSetting.setEnabled(false);
    this.downArrow = ((ImageView)findViewById(2131296604));
    this.setApplicationLock = ((TextView)findViewById(2131296577));
    this.setApplicationLock.setTypeface(this.tf);
    this.passwordDisplay = ((TextView)findViewById(2131296561));
    this.passwordDisplay.setTypeface(this.tf);
    this.groupSearch = ((TextView)findViewById(2131296565));
    this.groupSearch.setTypeface(this.tf);
    this.keySearch = ((TextView)findViewById(2131296569));
    this.keySearch.setTypeface(this.tf);
    this.copyOutsideApp = ((TextView)findViewById(2131296573));
    this.copyOutsideApp.setTypeface(this.tf);
    this.txtBackup = ((TextView)findViewById(2131296584));
    this.txtBackup.setTypeface(this.tf);
    this.txtRestore = ((TextView)findViewById(2131296586));
    this.txtRestore.setTypeface(this.tf);
    this.changeMasterKey = ((TextView)findViewById(2131296596));
    this.changeMasterKey.setTypeface(this.tf);
    this.txtSecQueAns = ((TextView)findViewById(2131296605));
    this.txtSecQueAns.setTypeface(this.tf);
    this.txtRecycleBin = ((TextView)findViewById(2131296589));
    this.txtRecycleBin.setTypeface(this.tf);
    this.txtTemplateSetting = ((TextView)findViewById(2131296592));
    this.txtTemplateSetting.setTypeface(this.tf);
    this.generalSettings = ((TextView)findViewById(2131296558));
    this.generalSettings.setTypeface(this.tf);
    this.linearLayoutGeneralSettings = ((LinearLayout)findViewById(2131296555));
    this.linearLayoutGeneralSettings.startAnimation(this.slideLeftIn1);
    this.linearLayoutGeneralSettingsContainer = ((LinearLayout)findViewById(2131296559));
    this.linearLayoutBackupRestore = ((LinearLayout)findViewById(2131296580));
    this.linearLayoutBackupRestore.startAnimation(this.slideLeftIn2);
    this.linearLayoutRecycleBin = ((LinearLayout)findViewById(2131296587));
    this.linearLayoutRecycleBin.startAnimation(this.slideLeftIn3);
    this.linearLayoutChangeMasterKey = ((LinearLayout)findViewById(2131296593));
    this.linearLayoutChangeMasterKey.startAnimation(this.slideLeftIn5);
    this.linearLayoutChangeSecQueAns = ((LinearLayout)findViewById(2131296602));
    this.linearLayoutChangeSecQueAnsContainer = ((LinearLayout)findViewById(2131296606));
    this.linearLayoutTemplate = ((LinearLayout)findViewById(2131296590));
    this.linearLayoutTemplate.startAnimation(this.slideLeftIn4);
    this.txtMasterKeyConfirm = ((EditText)findViewById(2131296601));
    this.txtMasterKeyConfirm.setTypeface(this.tf);
    this.txtMasterKeyNew = ((EditText)findViewById(2131296600));
    this.txtMasterKeyNew.setTypeface(this.tf);
    this.layoutMasterKeyOld = ((RelativeLayout)findViewById(2131296598));
    this.txtMasterKeyOld = ((EditText)findViewById(2131296599));
    this.txtMasterKeyOld.setTypeface(this.tf);
    this.txtMasterKeySecQue = ((EditText)findViewById(2131296607));
    this.txtMasterKeySecQue.setTypeface(this.tf);
    this.txtMasterKeySecAns = ((EditText)findViewById(2131296608));
    this.txtMasterKeySecAns.setTypeface(this.tf);
    this.btnSave = ((Button)findViewById(2131296269));
    this.btnSave.setTypeface(this.tf);
    this.btnCancle = ((Button)findViewById(2131296270));
    this.btnCancle.setTypeface(this.tf);
    this.setApplicationLockOn = ((TextView)findViewById(2131296578));
    this.setApplicationLockOn.setTypeface(this.tf);
    this.setApplicationLockOff = ((TextView)findViewById(2131296579));
    this.setApplicationLockOff.setTypeface(this.tf);
    this.passwordDisplayOn = ((TextView)findViewById(2131296562));
    this.passwordDisplayOn.setTypeface(this.tf);
    this.passwordDisplayOff = ((TextView)findViewById(2131296563));
    this.passwordDisplayOff.setTypeface(this.tf);
    this.groupSearchOn = ((TextView)findViewById(2131296566));
    this.groupSearchOn.setTypeface(this.tf);
    this.groupSearchOff = ((TextView)findViewById(2131296567));
    this.groupSearchOff.setTypeface(this.tf);
    this.keySearchOn = ((TextView)findViewById(2131296570));
    this.keySearchOn.setTypeface(this.tf);
    this.keySearchOff = ((TextView)findViewById(2131296571));
    this.keySearchOff.setTypeface(this.tf);
    this.copyOutsideAppOn = ((TextView)findViewById(2131296574));
    this.copyOutsideAppOn.setTypeface(this.tf);
    this.copyOutsideAppOff = ((TextView)findViewById(2131296575));
    this.copyOutsideAppOff.setTypeface(this.tf);
    this.linearLayoutChangeMasterKeyContainer = ((LinearLayout)findViewById(2131296597));
    this.linearLayoutChangeSecQueAnsTextImage = ((LinearLayout)findViewById(2131296603));
    this.scrollSettings = ((ScrollView)findViewById(2131296554));
    this.settingDetail = new ArrayList();
    this.masterKeyDetail = new ArrayList();
    this.dbSettingHelper = new DBSettingsHelper(this);
    PasswordPanacea.setSettingActivity(null);
    PasswordPanacea.setTemplateActivity(null);
    this.generalSettings.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (!SettingActivity.this.generalSettingsClicked.booleanValue())
        {
          if (SettingActivity.this.linearLayoutChangeMasterKeyContainer.getVisibility() == 0)
          {
            ExpandAnimation localExpandAnimation3 = new ExpandAnimation(SettingActivity.this.linearLayoutChangeMasterKeyContainer, 500);
            SettingActivity.this.linearLayoutChangeMasterKey.startAnimation(localExpandAnimation3);
          }
          SettingActivity.this.generalSettings.setClickable(false);
          SettingActivity.this.generalSettings.setEnabled(false);
          ExpandAnimation localExpandAnimation4 = new ExpandAnimation(SettingActivity.this.linearLayoutGeneralSettingsContainer, 500);
          SettingActivity.this.linearLayoutGeneralSettings.startAnimation(localExpandAnimation4);
          SettingActivity.this.generalSettingsClicked = Boolean.valueOf(true);
          SettingActivity.this.handler = new Handler();
          SettingActivity.this.handler.postDelayed(new Runnable()
          {
            public void run()
            {
              SettingActivity.this.generalSettings.setClickable(true);
              SettingActivity.this.generalSettings.setEnabled(true);
            }
          }, 600L);
          return;
        }
        if (SettingActivity.this.linearLayoutChangeMasterKeyContainer.getVisibility() == 0)
        {
          ExpandAnimation localExpandAnimation1 = new ExpandAnimation(SettingActivity.this.linearLayoutChangeMasterKeyContainer, 500);
          SettingActivity.this.linearLayoutChangeMasterKey.startAnimation(localExpandAnimation1);
        }
        SettingActivity.this.generalSettings.setClickable(false);
        SettingActivity.this.generalSettings.setEnabled(false);
        ExpandAnimation localExpandAnimation2 = new ExpandAnimation(SettingActivity.this.linearLayoutGeneralSettingsContainer, 500);
        SettingActivity.this.linearLayoutGeneralSettings.startAnimation(localExpandAnimation2);
        SettingActivity.this.generalSettingsClicked = Boolean.valueOf(false);
        SettingActivity.this.handler = new Handler();
        SettingActivity.this.handler.postDelayed(new Runnable()
        {
          public void run()
          {
            SettingActivity.this.generalSettings.setClickable(true);
            SettingActivity.this.generalSettings.setEnabled(true);
          }
        }, 600L);
      }
    });
    this.masterKeyDetail = this.dbSettingHelper.getMasterKeyDetail();
    this.masterKey = ((MasterKey)this.masterKeyDetail.get(0)).getMasterKey();
    this.changeMasterKey.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        paramAnonymousView.requestFocus();
        if ((!PasswordPanacea.getAppLock()) && (!SettingActivity.this.masterKey.equals("")))
        {
          new CustomDialogSingleButton(SettingActivity.this, "Alert!", "请先启用应用设置.").show();
          return;
        }
        if (!SettingActivity.this.masterKeyFirstClick.booleanValue())
        {
          if (SettingActivity.this.linearLayoutGeneralSettingsContainer.getVisibility() == 0)
          {
            ExpandAnimation localExpandAnimation3 = new ExpandAnimation(SettingActivity.this.linearLayoutGeneralSettingsContainer, 500);
            SettingActivity.this.linearLayoutGeneralSettings.startAnimation(localExpandAnimation3);
          }
          SettingActivity.this.changeMasterKey.setClickable(false);
          SettingActivity.this.changeMasterKey.setEnabled(false);
          ExpandAnimation localExpandAnimation4 = new ExpandAnimation(SettingActivity.this.linearLayoutChangeMasterKeyContainer, 500);
          SettingActivity.this.linearLayoutChangeMasterKey.startAnimation(localExpandAnimation4);
          SettingActivity.this.masterKeyFirstClick = Boolean.valueOf(true);
          SettingActivity.this.handler = new Handler();
          SettingActivity.this.handler.postDelayed(new Runnable()
          {
            public void run()
            {
              SettingActivity.this.changeMasterKey.setClickable(true);
              SettingActivity.this.changeMasterKey.setEnabled(true);
              SettingActivity.this.scrollSettings.fullScroll(130);
              SettingActivity.this.txtMasterKeyOld.requestFocus();
            }
          }, 600L);
          return;
        }
        if (SettingActivity.this.linearLayoutGeneralSettingsContainer.getVisibility() == 0)
        {
          ExpandAnimation localExpandAnimation1 = new ExpandAnimation(SettingActivity.this.linearLayoutGeneralSettingsContainer, 500);
          SettingActivity.this.linearLayoutGeneralSettings.startAnimation(localExpandAnimation1);
        }
        SettingActivity.this.changeMasterKey.setClickable(false);
        SettingActivity.this.changeMasterKey.setEnabled(false);
        ExpandAnimation localExpandAnimation2 = new ExpandAnimation(SettingActivity.this.linearLayoutChangeMasterKeyContainer, 500);
        SettingActivity.this.linearLayoutChangeMasterKey.startAnimation(localExpandAnimation2);
        SettingActivity.this.masterKeyFirstClick = Boolean.valueOf(false);
        SettingActivity.this.handler = new Handler();
        SettingActivity.this.handler.postDelayed(new Runnable()
        {
          public void run()
          {
            SettingActivity.this.changeMasterKey.setClickable(true);
            SettingActivity.this.changeMasterKey.setEnabled(true);
          }
        }, 600L);
      }
    });
    this.txtTemplateSetting.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        PasswordPanacea.setKeyEnteredFields(null);
        PasswordPanacea.setKeyActivity(null);
        PasswordPanacea.setGroupActivity(null);
        Intent localIntent = new Intent(SettingActivity.this.getApplicationContext(), TemplateListActivity.class);
        localIntent.addFlags(65536);
        PasswordPanacea.setTemplateActivity("SettingActivity");
        SettingActivity.this.startActivity(localIntent);
        SettingActivity.this.finish();
      }
    });
    this.txtMasterKeyConfirm.setOnEditorActionListener(new TextView.OnEditorActionListener()
    {
      public boolean onEditorAction(TextView paramAnonymousTextView, int paramAnonymousInt, KeyEvent paramAnonymousKeyEvent)
      {
        if (paramAnonymousInt == 6) {
          SettingActivity.this.btnSave.requestFocus();
        }
        return false;
      }
    });
    this.txtRecycleBin.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        Intent localIntent = new Intent(SettingActivity.this.getApplicationContext(), RecycleBinActivity.class);
        PasswordPanacea.setTrashActivity("SettingActivity");
        localIntent.addFlags(65536);
        SettingActivity.this.startActivity(localIntent);
        SettingActivity.this.finish();
      }
    });
   
      this.linearLayoutChangeSecQueAnsTextImage.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          if (!SettingActivity.this.firstClick.booleanValue())
          {
            SettingActivity.this.linearLayoutChangeSecQueAnsTextImage.setClickable(false);
            SettingActivity.this.linearLayoutChangeSecQueAnsTextImage.setEnabled(false);
            SettingActivity.this.txtSecQueAns.setTextColor(-16777216);
            SettingActivity.this.downArrow.setImageResource(2130837656);
            ExpandAnimation localExpandAnimation2 = new ExpandAnimation(SettingActivity.this.linearLayoutChangeSecQueAnsContainer, 500);
            SettingActivity.this.linearLayoutChangeSecQueAns.startAnimation(localExpandAnimation2);
            SettingActivity.this.firstClick = Boolean.valueOf(true);
            SettingActivity.this.handler = new Handler();
            SettingActivity.this.handler.postDelayed(new Runnable()
            {
              public void run()
              {
                SettingActivity.this.linearLayoutChangeSecQueAnsTextImage.setClickable(true);
                SettingActivity.this.linearLayoutChangeSecQueAnsTextImage.setEnabled(true);
                SettingActivity.this.scrollSettings.fullScroll(130);
              }
            }, 600L);
            return;
          }
          SettingActivity.this.linearLayoutChangeSecQueAnsTextImage.setClickable(false);
          SettingActivity.this.linearLayoutChangeSecQueAnsTextImage.setEnabled(false);
          SettingActivity.this.txtSecQueAns.setTextColor(-7829368);
          SettingActivity.this.downArrow.setImageResource(2130837655);
          ExpandAnimation localExpandAnimation1 = new ExpandAnimation(SettingActivity.this.linearLayoutChangeSecQueAnsContainer, 500);
          SettingActivity.this.linearLayoutChangeSecQueAns.startAnimation(localExpandAnimation1);
          SettingActivity.this.firstClick = Boolean.valueOf(false);
          SettingActivity.this.handler = new Handler();
          SettingActivity.this.handler.postDelayed(new Runnable()
          {
            public void run()
            {
              SettingActivity.this.linearLayoutChangeSecQueAnsTextImage.setClickable(true);
              SettingActivity.this.linearLayoutChangeSecQueAnsTextImage.setEnabled(true);
            }
          }, 600L);
        }
      });
      this.settingDetail = this.dbSettingHelper.getSettingMasterDetail();
      this.setAppLockIsTrue = ((Settings)this.settingDetail.get(0)).getSetApplicationLock();
      PasswordPanacea.setAppLock(this.setAppLockIsTrue.booleanValue());
      this.passwordDisplayIsTrue = ((Settings)this.settingDetail.get(0)).getPasswordDisplay();
      PasswordPanacea.setPasswordDisplay(this.passwordDisplayIsTrue.booleanValue());
      this.keySearchIsTrue = ((Settings)this.settingDetail.get(0)).getKeySearch();
      PasswordPanacea.setKeySearchEnable(this.keySearchIsTrue.booleanValue());
      this.groupSearchIsTrue = ((Settings)this.settingDetail.get(0)).getGroupSearch();
      PasswordPanacea.setGroupSearchEnable(this.groupSearchIsTrue.booleanValue());
      this.copyOutsideAppIsTrue = ((Settings)this.settingDetail.get(0)).getCopyOutsideApp();
      PasswordPanacea.setCopyOutsideApp(this.copyOutsideAppIsTrue.booleanValue());
      if (!PasswordPanacea.getPasswordDisplay()) {
    	  this.txtMasterKeyOld.setTransformationMethod(PasswordTransformationMethod.getInstance());
          this.txtMasterKeyNew.setTransformationMethod(PasswordTransformationMethod.getInstance());
          this.txtMasterKeyConfirm.setTransformationMethod(PasswordTransformationMethod.getInstance());
      }else{
	      this.txtMasterKeyOld.setInputType(144);
	      this.txtMasterKeyNew.setInputType(144);
	      this.txtMasterKeyConfirm.setInputType(144);
	  }
      if (!this.passwordDisplayIsTrue.booleanValue()) {
    	  this.passwordDisplayOn.setTextColor(Color.parseColor("#C0C0C0"));
          this.passwordDisplayOff.setTextColor(Color.parseColor("#ff8e0d"));
          this.passwordDisplayOff.setEnabled(false);
      }else{
	      this.passwordDisplayOn.setTextColor(Color.parseColor("#ff8e0d"));
	      this.passwordDisplayOn.setEnabled(false);
	      this.passwordDisplayOff.setTextColor(Color.parseColor("#C0C0C0"));
      }
      if (!this.setAppLockIsTrue.booleanValue()) {
    	  if (!this.masterKey.equals(""))
          {
            this.setApplicationLockOn.setTextColor(Color.parseColor("#ff8e0d"));
            this.setApplicationLockOn.setEnabled(false);
            this.setApplicationLockOff.setTextColor(Color.parseColor("#C0C0C0"));
          }
      }else{
	      if (!this.masterKey.equals(""))
	      {
	        this.setApplicationLockOff.setTextColor(Color.parseColor("#ff8e0d"));
	        this.setApplicationLockOff.setEnabled(false);
	        this.setApplicationLockOn.setTextColor(Color.parseColor("#C0C0C0"));
	      }
      }
      if (!PasswordPanacea.getAppLock()) {
    	  if (this.masterKey.equals("")) {
    		  this.linearLayoutChangeMasterKey.setVisibility(View.VISIBLE);
    	  }
      }else{
    	  this.linearLayoutChangeMasterKey.setVisibility(View.VISIBLE);
      }
      if (!this.keySearchIsTrue.booleanValue()) {
    	  this.keySearchOn.setTextColor(Color.parseColor("#C0C0C0"));
          this.keySearchOff.setTextColor(Color.parseColor("#ff8e0d"));
          this.keySearchOff.setEnabled(false);
      }else{
	      this.keySearchOn.setTextColor(Color.parseColor("#ff8e0d"));
	      this.keySearchOn.setEnabled(false);
	      this.keySearchOff.setTextColor(Color.parseColor("#C0C0C0"));
      }
      if (!this.groupSearchIsTrue.booleanValue()) {
    	  this.groupSearchOn.setTextColor(Color.parseColor("#C0C0C0"));
          this.groupSearchOff.setTextColor(Color.parseColor("#ff8e0d"));
          this.groupSearchOff.setEnabled(false);
      }else{
	      this.groupSearchOn.setTextColor(Color.parseColor("#ff8e0d"));
	      this.groupSearchOn.setEnabled(false);
	      this.groupSearchOff.setTextColor(Color.parseColor("#C0C0C0"));
      }
      if (!this.copyOutsideAppIsTrue.booleanValue()) {
    	  this.copyOutsideAppOn.setTextColor(Color.parseColor("#C0C0C0"));
          this.copyOutsideAppOff.setTextColor(Color.parseColor("#ff8e0d"));
          this.copyOutsideAppOff.setEnabled(false);
      }else{
	      this.copyOutsideAppOn.setTextColor(Color.parseColor("#ff8e0d"));
	      this.copyOutsideAppOn.setEnabled(false);
	      this.copyOutsideAppOff.setTextColor(Color.parseColor("#C0C0C0"));
      }
      if (this.masterKey.equals(""))
      {
        this.setApplicationLockOn.setTextColor(Color.parseColor("#C0C0C0"));
        this.setApplicationLockOff.setTextColor(Color.parseColor("#C0C0C0"));
        this.linearLayoutChangeMasterKey.setVisibility(0);
        this.downArrow.setVisibility(0);
      }else{
    	  this.changeMasterKey.setText("设置管理密码");
          this.txtSecQueAns.setText("修改密保问题和答案.");
          this.setApplicationLockOn.setClickable(true);
          this.setApplicationLockOff.setClickable(true);
          this.layoutMasterKeyOld.setVisibility(0);
          this.linearLayoutChangeSecQueAnsContainer.setVisibility(8);
      }
    
    
      this.btnSave.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          SettingActivity.this.masterKeyDetail = SettingActivity.this.dbSettingHelper.getMasterKeyDetail();
          SettingActivity.this.masterKey = ((MasterKey)SettingActivity.this.masterKeyDetail.get(0)).getMasterKey();
          if (SettingActivity.this.masterKey.equals("")) {
            if ((SettingActivity.this.txtMasterKeyNew.getText().length() != 0) && (SettingActivity.this.txtMasterKeyConfirm.getText().length() != 0) && (SettingActivity.this.txtMasterKeySecQue.getText().length() != 0) && (SettingActivity.this.txtMasterKeySecAns.getText().length() != 0))
            {
              String str7 = SettingActivity.this.txtMasterKeyNew.getText().toString();
              String str8 = SettingActivity.this.txtMasterKeyConfirm.getText().toString();
              String str9 = SettingActivity.this.txtMasterKeySecQue.getText().toString();
              String str10 = SettingActivity.this.txtMasterKeySecAns.getText().toString();
              if (str7.equals(str8))
              {
                SettingActivity.this.dbSettingHelper.updateMasterKeyDetail(str7, str9, str10);
                SettingActivity.this.txtMasterKeyNew.setText("");
                SettingActivity.this.txtMasterKeyConfirm.setText("");
                SettingActivity.this.txtMasterKeySecQue.setText("");
                SettingActivity.this.txtMasterKeySecAns.setText("");
                SettingActivity.this.dbSettingHelper.updateSetAppLock(Boolean.valueOf(true));
                PasswordPanacea.setAppLock(true);
                SettingActivity.this.setApplicationLockOn.setClickable(true);
                SettingActivity.this.setApplicationLockOn.setTextColor(Color.parseColor("#ff8e0d"));
                SettingActivity.this.setApplicationLockOn.setEnabled(false);
                SettingActivity.this.setApplicationLockOff.setClickable(true);
                SettingActivity.this.setApplicationLockOff.setTextColor(Color.parseColor("#C0C0C0"));
                SettingActivity.this.setApplicationLockOff.setEnabled(true);
                SettingActivity.this.changeMasterKey.setText("MASTER KEY SETTING");
                SettingActivity.this.txtSecQueAns.setText("Change Key Security Question & Answer.");
                SettingActivity.this.showToast("The MasterKey has been added successfully!");
                SettingActivity.this.layoutMasterKeyOld.setVisibility(0);
                SettingActivity.this.txtMasterKeyOld.requestFocus();
                Intent localIntent = new Intent(SettingActivity.this.getApplicationContext(), SettingActivity.class);
                SettingActivity.this.startActivity(localIntent);
                SettingActivity.this.finish();
              }else{
            	  new CustomDialogSingleButton(SettingActivity.this, "Invalid!", "密码不匹配.").show();
              }
            }else{
            	new CustomDialogSingleButton(SettingActivity.this, "Invalid!", "请确保各项不为空.").show();
            	if (SettingActivity.this.txtMasterKeyNew.getText().length() == 0) {
                    SettingActivity.this.txtMasterKeyNew.setError(null, SettingActivity.this.errorIcon);
                  }
                  if (SettingActivity.this.txtMasterKeyConfirm.getText().length() == 0) {
                    SettingActivity.this.txtMasterKeyConfirm.setError(null, SettingActivity.this.errorIcon);
                  }
                  if (SettingActivity.this.txtMasterKeySecQue.getText().length() == 0) {
                    SettingActivity.this.txtMasterKeySecQue.setError(null, SettingActivity.this.errorIcon);
                  }
                  if (SettingActivity.this.txtMasterKeySecAns.getText().length() == 0) {
                    SettingActivity.this.txtMasterKeySecAns.setError(null, SettingActivity.this.errorIcon);
                  }
                  if ((SettingActivity.this.txtMasterKeySecQue.getText().length() == 0) && (SettingActivity.this.txtMasterKeySecAns.getText().length() == 0) && (SettingActivity.this.firstClick.booleanValue())){
	                  SettingActivity.this.txtSecQueAns.setTextColor(-16777216);
	                  SettingActivity.this.downArrow.setImageResource(2130837656);
	                  ExpandAnimation localExpandAnimation = new ExpandAnimation(SettingActivity.this.linearLayoutChangeSecQueAnsContainer, 500);
	                  SettingActivity.this.linearLayoutChangeSecQueAns.startAnimation(localExpandAnimation);
	                  SettingActivity.this.firstClick = Boolean.valueOf(true);
                  }
            }
          }else{
	            
	            if ((SettingActivity.this.txtMasterKeyNew.getText().length() != 0) || (SettingActivity.this.txtMasterKeyOld.getText().length() != 0) || (SettingActivity.this.txtMasterKeyConfirm.getText().length() != 0))
	            {
	              if ((SettingActivity.this.txtMasterKeyNew.getText().length() != 0) && (SettingActivity.this.txtMasterKeyOld.getText().length() != 0) && (SettingActivity.this.txtMasterKeyConfirm.getText().length() != 0))
	              {
	                if ((SettingActivity.this.txtMasterKeySecQue.getText().length() != 0) || (SettingActivity.this.txtMasterKeySecAns.getText().length() != 0))
	                {
	                  if ((SettingActivity.this.txtMasterKeySecQue.getText().length() != 0) && (SettingActivity.this.txtMasterKeySecAns.getText().length() != 0))
	                  {
	                	  String str1 = SettingActivity.this.txtMasterKeyOld.getText().toString();
	                      String str2 = SettingActivity.this.txtMasterKeyNew.getText().toString();
	                      String str3 = SettingActivity.this.txtMasterKeyConfirm.getText().toString();
	                      if ((str1.equals(SettingActivity.this.masterKey)) && (str2.equals(str3)))
	                      {
	                        SettingActivity.this.dbSettingHelper.updateMasterKeyDetail(str2, SettingActivity.this.txtMasterKeySecQue.getText().toString(), SettingActivity.this.txtMasterKeySecAns.getText().toString());
	                        SettingActivity.this.txtMasterKeyNew.setText("");
	                        SettingActivity.this.txtMasterKeyOld.setText("");
	                        SettingActivity.this.txtMasterKeyConfirm.setText("");
	                        SettingActivity.this.txtMasterKeyOld.requestFocus();
	                        SettingActivity.this.txtMasterKeyOld.setError(null);
	                        SettingActivity.this.txtMasterKeyConfirm.setError(null);
	                        SettingActivity.this.txtMasterKeyNew.setError(null);
	                        SettingActivity.this.txtMasterKeySecQue.setText("");
	                        SettingActivity.this.txtMasterKeySecAns.setText("");
	                        SettingActivity.this.txtMasterKeySecQue.setError(null);
	                        SettingActivity.this.txtMasterKeySecAns.setError(null);
	                        SettingActivity.this.showToast("管理密码已成功设置!");
	                        return;
	                      }
	                      else if (!str1.equals(SettingActivity.this.masterKey)) {
	                        new CustomDialogSingleButton(SettingActivity.this, "Invalid!", "管理密码错误.").show();
	                      }
	                      else if(!str2.equals(str3))
	                    	new CustomDialogSingleButton(SettingActivity.this, "Invalid!", "密码不匹配.").show();	                      
	                  }else{
	                	  SettingActivity.this.txtMasterKeySecQue.setError(null);
	                      SettingActivity.this.txtMasterKeySecAns.setError(null);
	                      SettingActivity.this.txtMasterKeyOld.setError(null);
	                      SettingActivity.this.txtMasterKeyConfirm.setError(null);
	                      SettingActivity.this.txtMasterKeyNew.setError(null);
	                      new CustomDialogSingleButton(SettingActivity.this, "Invalid!", "请确保各项不为空.").show();
	                      if (SettingActivity.this.txtMasterKeySecQue.getText().length() == 0) {
	                        SettingActivity.this.txtMasterKeySecQue.setError(null, SettingActivity.this.errorIcon);
	                      }
	                      if (SettingActivity.this.txtMasterKeySecAns.getText().length() == 0) {
	                        SettingActivity.this.txtMasterKeySecAns.setError(null, SettingActivity.this.errorIcon);
	                      }
	                      if (SettingActivity.this.txtMasterKeyOld.getText().length() == 0) {
	                        SettingActivity.this.txtMasterKeyOld.setError(null, SettingActivity.this.errorIcon);
	                      }
	                      if (SettingActivity.this.txtMasterKeyNew.getText().length() == 0) {
	                        SettingActivity.this.txtMasterKeyNew.setError(null, SettingActivity.this.errorIcon);
	                      }
	                      if (SettingActivity.this.txtMasterKeyConfirm.getText().length() == 0) {
		                        SettingActivity.this.txtMasterKeyConfirm.setError(null, SettingActivity.this.errorIcon);
		                  }
	                  }
	                }else{
	                	String str4 = SettingActivity.this.txtMasterKeyOld.getText().toString();
	                	String str5 = SettingActivity.this.txtMasterKeyNew.getText().toString();
	                	String str6 = SettingActivity.this.txtMasterKeyConfirm.getText().toString();
	                    if ((str4.equals(SettingActivity.this.masterKey)) && (str5.equals(str6)))
	                    {
	                      SettingActivity.this.dbSettingHelper.updateMasterKeyValue(str5);
	                      SettingActivity.this.txtMasterKeyNew.setText("");
	                      SettingActivity.this.txtMasterKeyOld.setText("");
	                      SettingActivity.this.txtMasterKeyConfirm.setText("");
	                      SettingActivity.this.txtMasterKeyOld.requestFocus();
	                      SettingActivity.this.txtMasterKeyOld.setError(null);
	                      SettingActivity.this.txtMasterKeyConfirm.setError(null);
	                      SettingActivity.this.txtMasterKeyNew.setError(null);
	                      SettingActivity.this.showToast("管理密码设置成功!");
	                      return;
	                    }
	                    if (!str4.equals(SettingActivity.this.masterKey)) {
	                      new CustomDialogSingleButton(SettingActivity.this, "Invalid!", "管理密码错误.").show();
	                    }
	                    if(!str5.equals(str6))
	                    	new CustomDialogSingleButton(SettingActivity.this, "Invalid!", "密码不匹配.").show();               
	                  
	                }
	              }else{
	            	  SettingActivity.this.txtMasterKeyOld.setError(null);
	                  SettingActivity.this.txtMasterKeyConfirm.setError(null);
	                  SettingActivity.this.txtMasterKeyNew.setError(null);
	                  new CustomDialogSingleButton(SettingActivity.this, "Invalid!", "请确保各项不为空.").show();
	                  if (SettingActivity.this.txtMasterKeyOld.getText().length() == 0) {
	                    SettingActivity.this.txtMasterKeyOld.setError(null, SettingActivity.this.errorIcon);
	                  }
	                  if (SettingActivity.this.txtMasterKeyNew.getText().length() == 0) {
	                    SettingActivity.this.txtMasterKeyNew.setError(null, SettingActivity.this.errorIcon);
	                  }
	                  if (SettingActivity.this.txtMasterKeyConfirm.getText().length() == 0) {
		                    SettingActivity.this.txtMasterKeyConfirm.setError(null, SettingActivity.this.errorIcon);
	                  }
	              }
	            }else{
	            	 SettingActivity.this.txtMasterKeyOld.setError(null);
	                 SettingActivity.this.txtMasterKeyConfirm.setError(null);
	                 SettingActivity.this.txtMasterKeyNew.setError(null);
	                 new CustomDialogSingleButton(SettingActivity.this, "Invalid!", "请确保各项不为空.").show();
	                 if (SettingActivity.this.txtMasterKeyOld.getText().length() == 0) {
	                   SettingActivity.this.txtMasterKeyOld.setError(null, SettingActivity.this.errorIcon);
	                 }
	                 if (SettingActivity.this.txtMasterKeyNew.getText().length() == 0) {
	                   SettingActivity.this.txtMasterKeyNew.setError(null, SettingActivity.this.errorIcon);
	                 }
	                 if (SettingActivity.this.txtMasterKeyConfirm.getText().length() == 0) {
		                   SettingActivity.this.txtMasterKeyConfirm.setError(null, SettingActivity.this.errorIcon);
	                 }
	            }
          }
        }
      });
      this.btnCancle.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          SettingActivity.this.txtMasterKeyOld.setText("");
          SettingActivity.this.txtMasterKeyOld.setError(null);
          SettingActivity.this.txtMasterKeyNew.setText("");
          SettingActivity.this.txtMasterKeyNew.setError(null);
          SettingActivity.this.txtMasterKeyConfirm.setText("");
          SettingActivity.this.txtMasterKeyConfirm.setError(null);
          SettingActivity.this.txtMasterKeySecQue.setText("");
          SettingActivity.this.txtMasterKeySecQue.setError(null);
          SettingActivity.this.txtMasterKeySecAns.setText("");
          SettingActivity.this.txtMasterKeySecAns.setError(null);
          SettingActivity.this.txtMasterKeyOld.requestFocus();
        }
      });
      this.txtBackup.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          try
          {
            File localFile1 = Environment.getExternalStorageDirectory();
            File localFile2 = Environment.getDataDirectory();
            if (localFile1.canWrite())
            {
              Calendar localCalendar = Calendar.getInstance();
              String str = new SimpleDateFormat("dd-MM-yyyy, HH_mm_ss").format(localCalendar.getTime()) + "DBDetails";
              File localFile3 = new File(localFile2, "/data/com.hyh.passwordassitant.activity/databases/DBDetails");
              File localFile4 = new File(localFile1, str);
              if (localFile3.exists())
              {
                FileChannel localFileChannel1 = new FileInputStream(localFile3).getChannel();
                FileChannel localFileChannel2 = new FileOutputStream(localFile4).getChannel();
                localFileChannel2.transferFrom(localFileChannel1, 0L, localFileChannel1.size());
                localFileChannel1.close();
                localFileChannel2.close();
                SettingActivity.this.showToast("备份成功!");
                return;
              }
              SettingActivity.this.showToast("备份文件不存在!");
              return;
            }
          }
          catch (Exception localException)
          {
            SettingActivity.this.showToast("备份失败!");
          }
        }
      });
      this.txtRestore.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          Intent localIntent = new Intent(SettingActivity.this.getApplicationContext(), RestoreActivity.class);
          localIntent.addFlags(65536);
          SettingActivity.this.startActivity(localIntent);
        }
      });
      this.setApplicationLockOn.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          if (SettingActivity.this.masterKey.equals(""))
          {
            new CustomDialogSingleButton(SettingActivity.this, "Invalid!", "请设置管理密码.").show();
            return;
          }
          SettingActivity.this.dbSettingHelper.updateSetAppLock(Boolean.valueOf(true));
          PasswordPanacea.setAppLock(true);
          SettingActivity.this.txtMasterKeyOld.setText("");
          SettingActivity.this.txtMasterKeyNew.setText("");
          SettingActivity.this.txtMasterKeyConfirm.setText("");
          if (PasswordPanacea.getPasswordDisplay())
          {
            SettingActivity.this.txtMasterKeyOld.setInputType(144);
            SettingActivity.this.txtMasterKeyNew.setInputType(144);
            SettingActivity.this.txtMasterKeyConfirm.setInputType(144);
          }else{
        	  SettingActivity.this.txtMasterKeyOld.setTransformationMethod(PasswordTransformationMethod.getInstance());
              SettingActivity.this.txtMasterKeyNew.setTransformationMethod(PasswordTransformationMethod.getInstance());
              SettingActivity.this.txtMasterKeyConfirm.setTransformationMethod(PasswordTransformationMethod.getInstance());
          }          
	        SettingActivity.this.setApplicationLockOn.setTextColor(Color.parseColor("#ff8e0d"));
	        SettingActivity.this.setApplicationLockOn.setEnabled(false);
	        SettingActivity.this.setApplicationLockOff.setTextColor(Color.parseColor("#C0C0C0"));
	        SettingActivity.this.setApplicationLockOff.setEnabled(true);
	        SettingActivity.this.showToast("应用锁定启用!");
	        return;
          
        }
      });
      this.setApplicationLockOff.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          if (SettingActivity.this.masterKey.equals(""))
          {
            new CustomDialogSingleButton(SettingActivity.this, "Invalid!", "请设置管理密码.").show();
            return;
          }
          SettingActivity.this.dbSettingHelper.updateSetAppLock(Boolean.valueOf(false));
          PasswordPanacea.setAppLock(false);
          SettingActivity.this.setApplicationLockOff.setTextColor(Color.parseColor("#ff8e0d"));
          SettingActivity.this.setApplicationLockOff.setEnabled(false);
          SettingActivity.this.setApplicationLockOn.setTextColor(Color.parseColor("#C0C0C0"));
          SettingActivity.this.setApplicationLockOn.setEnabled(true);
          SettingActivity.this.showToast("应用锁定禁用!");
        }
      });
      this.passwordDisplayOn.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          SettingActivity.this.dbSettingHelper.updatePasswordDisplay(Boolean.valueOf(true));
          PasswordPanacea.setPasswordDisplay(true);
          SettingActivity.this.txtMasterKeyOld.setInputType(144);
          SettingActivity.this.txtMasterKeyNew.setInputType(144);
          SettingActivity.this.txtMasterKeyConfirm.setInputType(144);
          SettingActivity.this.passwordDisplayOn.setTextColor(Color.parseColor("#ff8e0d"));
          SettingActivity.this.passwordDisplayOn.setEnabled(false);
          SettingActivity.this.passwordDisplayOff.setTextColor(Color.parseColor("#C0C0C0"));
          SettingActivity.this.passwordDisplayOff.setEnabled(true);
          SettingActivity.this.showToast("密码展示启用!");
        }
      });
      this.passwordDisplayOff.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          SettingActivity.this.dbSettingHelper.updatePasswordDisplay(Boolean.valueOf(false));
          PasswordPanacea.setPasswordDisplay(false);
          SettingActivity.this.txtMasterKeyOld.setTransformationMethod(PasswordTransformationMethod.getInstance());
          SettingActivity.this.txtMasterKeyNew.setTransformationMethod(PasswordTransformationMethod.getInstance());
          SettingActivity.this.txtMasterKeyConfirm.setTransformationMethod(PasswordTransformationMethod.getInstance());
          SettingActivity.this.passwordDisplayOff.setTextColor(Color.parseColor("#ff8e0d"));
          SettingActivity.this.passwordDisplayOff.setEnabled(false);
          SettingActivity.this.passwordDisplayOn.setTextColor(Color.parseColor("#C0C0C0"));
          SettingActivity.this.passwordDisplayOn.setEnabled(true);
          SettingActivity.this.showToast("密码展示禁用!");
        }
      });
      this.groupSearchOn.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          SettingActivity.this.dbSettingHelper.updateGroupSearch(Boolean.valueOf(true));
          PasswordPanacea.setGroupSearchEnable(true);
          SettingActivity.this.groupSearchOn.setTextColor(Color.parseColor("#ff8e0d"));
          SettingActivity.this.groupSearchOn.setEnabled(false);
          SettingActivity.this.groupSearchOff.setTextColor(Color.parseColor("#C0C0C0"));
          SettingActivity.this.groupSearchOff.setEnabled(true);
          SettingActivity.this.showToast("分组搜索启用!");
        }
      });
      this.groupSearchOff.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          SettingActivity.this.dbSettingHelper.updateGroupSearch(Boolean.valueOf(false));
          PasswordPanacea.setGroupSearchEnable(false);
          SettingActivity.this.groupSearchOff.setTextColor(Color.parseColor("#ff8e0d"));
          SettingActivity.this.groupSearchOff.setEnabled(false);
          SettingActivity.this.groupSearchOn.setTextColor(Color.parseColor("#C0C0C0"));
          SettingActivity.this.groupSearchOn.setEnabled(true);
          SettingActivity.this.showToast("分组搜索禁用!");
        }
      });
      this.keySearchOn.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          SettingActivity.this.dbSettingHelper.updateKeySearch(Boolean.valueOf(true));
          PasswordPanacea.setKeySearchEnable(true);
          SettingActivity.this.keySearchOn.setTextColor(Color.parseColor("#ff8e0d"));
          SettingActivity.this.keySearchOn.setEnabled(false);
          SettingActivity.this.keySearchOff.setTextColor(Color.parseColor("#C0C0C0"));
          SettingActivity.this.keySearchOff.setEnabled(true);
          SettingActivity.this.showToast("密码搜索启用!");
        }
      });
      this.keySearchOff.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          SettingActivity.this.dbSettingHelper.updateKeySearch(Boolean.valueOf(false));
          PasswordPanacea.setKeySearchEnable(false);
          SettingActivity.this.keySearchOff.setTextColor(Color.parseColor("#ff8e0d"));
          SettingActivity.this.keySearchOff.setEnabled(false);
          SettingActivity.this.keySearchOn.setTextColor(Color.parseColor("#C0C0C0"));
          SettingActivity.this.keySearchOn.setEnabled(true);
          SettingActivity.this.showToast("密码搜禁用!");
        }
      });
      this.copyOutsideAppOn.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          SettingActivity.this.dbSettingHelper.updateCopyOutsideApp(Boolean.valueOf(true));
          PasswordPanacea.setCopyOutsideApp(true);
          SettingActivity.this.copyOutsideAppOn.setTextColor(Color.parseColor("#ff8e0d"));
          SettingActivity.this.copyOutsideAppOn.setEnabled(false);
          SettingActivity.this.copyOutsideAppOff.setTextColor(Color.parseColor("#C0C0C0"));
          SettingActivity.this.copyOutsideAppOff.setEnabled(true);
          SettingActivity.this.showToast("应用外复制启用!");
        }
      });
      this.copyOutsideAppOff.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          SettingActivity.this.dbSettingHelper.updateCopyOutsideApp(Boolean.valueOf(false));
          PasswordPanacea.setCopyOutsideApp(false);
          SettingActivity.this.copyOutsideAppOff.setTextColor(Color.parseColor("#ff8e0d"));
          SettingActivity.this.copyOutsideAppOff.setEnabled(false);
          SettingActivity.this.copyOutsideAppOn.setTextColor(Color.parseColor("#C0C0C0"));
          SettingActivity.this.copyOutsideAppOn.setEnabled(true);
          SettingActivity.this.showToast("应用外复制禁用!");
        }
      });
      return;
     
     
  }
  
  public void showToast(String paramString)
  {
    View localView = getLayoutInflater().inflate(2130903048, (ViewGroup)findViewById(2131296314));
    TextView localTextView = (TextView)localView.findViewById(2131296315);
    localTextView.setTypeface(this.tf);
    localTextView.setText(paramString);
    Toast localToast = new Toast(getApplicationContext());
    localToast.setDuration(0);
    localToast.setView(localView);
    localToast.show();
  }
}


/* Location:           D:\Apkdb\Craining\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     rdi.mobapp.passwordpanacea.activity.SettingActivity
 * JD-Core Version:    0.7.0.1
 */