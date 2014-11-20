package com.hyh.passwordassitant.activity;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import rdi.mobapp.passwordpanacea.adapter.TemplateListArrayAdapter;
import rdi.mobapp.passwordpanacea.application.PasswordPanacea;
import rdi.mobapp.passwordpanacea.bean.Items;
import rdi.mobapp.passwordpanacea.databasehelper.DBHelper;
import rdi.mobapp.passwordpanacea.utility.CustomDialog;
import rdi.mobapp.passwordpanacea.utility.CustomDialogNewField;
import rdi.mobapp.passwordpanacea.utility.CustomDialogSingleButton;
import rdi.mobapp.passwordpanacea.utility.MyLinearLayoutDialog;

public class TemplateListActivity
  extends BaseActivity
{
  private Boolean arrowClicked = Boolean.valueOf(false);
  private ImageButton btnSetting;
  private DBHelper dbHelper = new DBHelper(this);
  private CustomDialogNewField dialog;
  private Drawable errorIcon;
  private ViewFlipper flipper;
  private ImageView headerIcon;
  private ImageView img_check;
  private boolean ischeckBoxVisible = false;
  private ListView listTemplate;
  private MyLinearLayoutDialog myLinearLayout;
  private ImageButton optnMore;
  private Boolean selectAll = Boolean.valueOf(false);
  private ArrayList<Items> templateList = new ArrayList();
  private TemplateListArrayAdapter templateListArrayAdapter;
  private Typeface tf;
  private LinearLayout toolTabContainer;
  private TextView txtSetting;
  private TextView txt_add;
  private TextView txt_check;
  private TextView txt_delete;
  private TextView txt_select;
  private View viewLeftShadow;
  private View viewRightShadow;
  
  private Animation inFromLeftAnimation()
  {
    TranslateAnimation localTranslateAnimation = new TranslateAnimation(2, -1.0F, 2, 0.0F, 2, 0.0F, 2, 0.0F);
    localTranslateAnimation.setDuration(500L);
    localTranslateAnimation.setInterpolator(new AccelerateInterpolator());
    return localTranslateAnimation;
  }
  
  private Animation inFromRightAnimation()
  {
    TranslateAnimation localTranslateAnimation = new TranslateAnimation(2, 1.0F, 2, 0.0F, 2, 0.0F, 2, 0.0F);
    localTranslateAnimation.setDuration(500L);
    localTranslateAnimation.setInterpolator(new AccelerateInterpolator());
    return localTranslateAnimation;
  }
  
  private Animation outToLeftAnimation()
  {
    TranslateAnimation localTranslateAnimation = new TranslateAnimation(2, 0.0F, 2, -1.0F, 2, 0.0F, 2, 0.0F);
    localTranslateAnimation.setDuration(500L);
    localTranslateAnimation.setInterpolator(new AccelerateInterpolator());
    return localTranslateAnimation;
  }
  
  private Animation outToRightAnimation()
  {
    TranslateAnimation localTranslateAnimation = new TranslateAnimation(2, 0.0F, 2, 1.0F, 2, 0.0F, 2, 0.0F);
    localTranslateAnimation.setDuration(500L);
    localTranslateAnimation.setInterpolator(new AccelerateInterpolator());
    return localTranslateAnimation;
  }
  
  public void backPressed()
  {
    getWindow().setSoftInputMode(3);
    String str = PasswordPanacea.getTemplateActivity();
    if ((str.length() != 0) || (str.equalsIgnoreCase("")))
    {
      Intent localIntent1 = new Intent();
      localIntent1.setClassName("com.hyh.passwordassitant.activity", "com.hyh.passwordassitant.activity." + str);
      localIntent1.addFlags(65536);
      PasswordPanacea.setDisableAnim(false);
      PasswordPanacea.setSettingActivity(null);
      startActivity(localIntent1);
      finish();
      return;
    }
    Intent localIntent2 = new Intent(getApplicationContext(), SettingActivity.class);
    localIntent2.addFlags(65536);
    PasswordPanacea.setDisableAnim(false);
    startActivity(localIntent2);
    finish();
  }
  
  public void headerBackPressed(View paramView)
  {
    backPressed();
  }
  
  public void headerMore(View paramView)
  {
    if (!this.arrowClicked.booleanValue())
    {
      this.toolTabContainer.setVisibility(0);
      this.optnMore.setSelected(true);
      this.arrowClicked = Boolean.valueOf(true);
      PasswordPanacea.setIsGroupHeaderTabVisible(true);
    }else{
    	this.toolTabContainer.setVisibility(8);
        this.optnMore.setSelected(false);
        this.arrowClicked = Boolean.valueOf(false);
        PasswordPanacea.setIsGroupHeaderTabVisible(false);
    }
    if(this.ischeckBoxVisible){
	    this.flipper.setInAnimation(inFromLeftAnimation());
	    this.flipper.setOutAnimation(outToRightAnimation());
	    this.flipper.showNext();
	    this.ischeckBoxVisible = false;
	    this.templateListArrayAdapter = new TemplateListArrayAdapter(this, this.templateList, this.listTemplate, Boolean.valueOf(false), Boolean.valueOf(false), Boolean.valueOf(false), Boolean.valueOf(false));
	    this.listTemplate.setAdapter(this.templateListArrayAdapter);
	 }
  }
  
  public void onBackPressed()
  {
    backPressed();
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle, 2130903078, "Template");
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
    this.errorIcon = getResources().getDrawable(2130837593);
    this.errorIcon.setBounds(new Rect(0, 0, this.errorIcon.getIntrinsicWidth(), this.errorIcon.getIntrinsicHeight()));
    this.myLinearLayout = ((MyLinearLayoutDialog)findViewById(2131296275));
    this.myLinearLayout.setMyListActivity(this);
    this.img_check = ((ImageView)findViewById(2131296425));
    this.listTemplate = ((ListView)findViewById(2131296623));
    View localView = ((LayoutInflater)getSystemService("layout_inflater")).inflate(2130903059, null, false);
    this.listTemplate.addFooterView(localView, null, false);
    this.flipper = ((ViewFlipper)findViewById(2131296391));
    this.toolTabContainer = ((LinearLayout)findViewById(2131296409));
    this.txt_add = ((TextView)findViewById(2131296414));
    this.txt_select = ((TextView)findViewById(2131296417));
    this.txt_check = ((TextView)findViewById(2131296426));
    this.txt_delete = ((TextView)findViewById(2131296429));
    this.txt_add.setTypeface(this.tf);
    this.txt_select.setTypeface(this.tf);
    this.txt_check.setTypeface(this.tf);
    this.txt_delete.setTypeface(this.tf);
    this.templateList = this.dbHelper.getTemplateList();
    this.templateListArrayAdapter = new TemplateListArrayAdapter(this, this.templateList, this.listTemplate, Boolean.valueOf(false), Boolean.valueOf(false), Boolean.valueOf(false), Boolean.valueOf(true));
    this.listTemplate.setAdapter(this.templateListArrayAdapter);
    PasswordPanacea.setSettingActivity("TemplateListActivity");
  }
  
  protected void onStart()
  {
    super.onStart();
    if (PasswordPanacea.getIsGroupHeaderTabVisible())
    {
      this.toolTabContainer.setVisibility(0);
      this.optnMore.setSelected(true);
      this.arrowClicked = Boolean.valueOf(true);
    }
  }
  
  public void optionAdd(View paramView)
  {
    this.dialog = new CustomDialogNewField(this, "添加模板", "请输入模板名称: ");
    this.dialog.show();
    this.dialog.customAlertBoxNegitiveButton.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        TemplateListActivity.this.dialog.customAlertBoxField.setText("");
        TemplateListActivity.this.dialog.customAlertBoxErrorMsg.setVisibility(4);
        TemplateListActivity.this.dialog.dismiss();
      }
    });
    this.dialog.customAlertBoxPositiveButton.setText("添加");
    this.dialog.customAlertBoxPositiveButton.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        TemplateListActivity.this.dialog.customAlertBoxField.setText(TemplateListActivity.this.dialog.customAlertBoxField.getText().toString().trim());
        if (TemplateListActivity.this.dialog.customAlertBoxField.getText().toString().length() != 0)
        {
          if (Pattern.compile("[^a-z0-9 ]", 2).matcher(TemplateListActivity.this.dialog.customAlertBoxField.getText().toString()).find())
          {
            new CustomDialogSingleButton(TemplateListActivity.this, "Invalid", "请避免无效特殊字符.").show();
            return;
          }
          if (TemplateListActivity.this.dbHelper.templateAlreadyExists(TemplateListActivity.this.dialog.customAlertBoxField.getText().toString()) == 0)
          {
            TemplateListActivity.this.dbHelper.addTemplate(TemplateListActivity.this.dialog.customAlertBoxField.getText().toString());
            TemplateListActivity.this.showToast("模板 \"" + TemplateListActivity.this.dialog.customAlertBoxField.getText().toString() + "\" 以创建." + "\n" + "请输入条目名称.");
            int i = TemplateListActivity.this.dbHelper.getTemplateIdByName(TemplateListActivity.this.dialog.customAlertBoxField.getText().toString());
            TemplateListActivity.this.dbHelper.addDefaultTemplateFields(i);
            Intent localIntent = new Intent(TemplateListActivity.this, TemplateActivity.class);
            Bundle localBundle = new Bundle();
            localBundle.putInt("templateIdRef", i);
            localBundle.putString("templateTitle", TemplateListActivity.this.dialog.customAlertBoxField.getText().toString());
            localIntent.putExtras(localBundle);
            TemplateListActivity.this.startActivity(localIntent);
            return;
          }
          TemplateListActivity.this.dialog.customAlertBoxErrorMsg.setVisibility(0);
          TemplateListActivity.this.dialog.customAlertBoxErrorMsg.setText("模板名称已存在.");
          return;
        }
        TemplateListActivity.this.dialog.customAlertBoxErrorMsg.setVisibility(0);
        TemplateListActivity.this.dialog.customAlertBoxErrorMsg.setText("请输入分组名称.");
      }
    });
  }
  
  public void optionDone(View paramView)
  {
    this.flipper.setInAnimation(inFromLeftAnimation());
    this.flipper.setOutAnimation(outToRightAnimation());
    this.flipper.showNext();
    this.ischeckBoxVisible = false;
    this.templateListArrayAdapter = new TemplateListArrayAdapter(this, this.templateList, this.listTemplate, Boolean.valueOf(false), Boolean.valueOf(false), Boolean.valueOf(false), Boolean.valueOf(false));
    this.listTemplate.setAdapter(this.templateListArrayAdapter);
    this.img_check.setImageResource(2130837669);
    this.txt_check.setText("全选");
    this.selectAll = Boolean.valueOf(false);
  }
  
  public void optionRemove(View paramView)
  {
    if (PasswordPanacea.getSelectedItems().size() != this.templateList.size())
    {
      this.img_check.setImageResource(2130837669);
      this.txt_check.setText("全选");
      this.selectAll = Boolean.valueOf(false);
    }
    if (PasswordPanacea.getSelectedItems().size() == 0)
    {
      new CustomDialogSingleButton(this, "Invalid!", "请选择模板.").show();
      return;
    }
    Boolean localBoolean = Boolean.valueOf(false);
    for (int i = 0;i < PasswordPanacea.getSelectedItems().size(); i++)
    {
      if (((Items)PasswordPanacea.getSelectedItems().get(i)).getItemTitle().equals("General")) {
    	  localBoolean = Boolean.valueOf(true);
    	  break;
      }      
    }
    if (!localBoolean.booleanValue()) {
    	final CustomDialog localCustomDialog = new CustomDialog(this, "Delete?", "删除该条目将同时删除与之相关的所有数据.");
        localCustomDialog.show();
        localCustomDialog.customAlertBoxPositiveButton.setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            for (int i = 0;; i++)
            {
              if (i >= PasswordPanacea.getSelectedItems().size())
              {
                Intent localIntent = new Intent(TemplateListActivity.this.getApplicationContext(), TemplateListActivity.class);
                localIntent.addFlags(65536);
                TemplateListActivity.this.startActivity(localIntent);
                return;
              }
              TemplateListActivity.this.dbHelper.deleteTemplate(((Items)PasswordPanacea.getSelectedItems().get(i)).getItemId());
            }
          }
        });
        localCustomDialog.customAlertBoxNegitiveButton.setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            localCustomDialog.dismiss();
          }
        });
    }else{
        final CustomDialogSingleButton localCustomDialogSingleButton = new CustomDialogSingleButton(this, "Invalid!", "默认模板无法删除.");
        localCustomDialogSingleButton.show();
        localCustomDialogSingleButton.customAlertBoxPositiveButton.setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            localCustomDialogSingleButton.dismiss();
          }
        });
    }
    
  }
  
  public void optionSelect(View paramView)
  {
    this.flipper.setInAnimation(inFromRightAnimation());
    this.flipper.setOutAnimation(outToLeftAnimation());
    this.flipper.showNext();
    this.ischeckBoxVisible = true;
    this.templateListArrayAdapter = new TemplateListArrayAdapter(this, this.templateList, this.listTemplate, Boolean.valueOf(true), Boolean.valueOf(false), Boolean.valueOf(false), Boolean.valueOf(false));
    this.listTemplate.setAdapter(this.templateListArrayAdapter);
  }
  
  public void optionSelectAll(View paramView)
  {
    if (!this.selectAll.booleanValue())
    {
      this.img_check.setImageResource(2130837665);
      this.txt_check.setText("全不选");
      this.templateListArrayAdapter = new TemplateListArrayAdapter(this, this.templateList, this.listTemplate, Boolean.valueOf(true), Boolean.valueOf(true), Boolean.valueOf(false), Boolean.valueOf(true));
      this.listTemplate.setAdapter(this.templateListArrayAdapter);
      this.selectAll = Boolean.valueOf(true);
      return;
    }
    this.img_check.setImageResource(2130837669);
    this.txt_check.setText("全选");
    this.templateListArrayAdapter = new TemplateListArrayAdapter(this, this.templateList, this.listTemplate, Boolean.valueOf(true), Boolean.valueOf(false), Boolean.valueOf(false), Boolean.valueOf(true));
    this.listTemplate.setAdapter(this.templateListArrayAdapter);
    this.selectAll = Boolean.valueOf(false);
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
 * Qualified Name:     rdi.mobapp.passwordpanacea.activity.TemplateListActivity
 * JD-Core Version:    0.7.0.1
 */