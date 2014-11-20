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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import rdi.mobapp.passwordpanacea.adapter.TemplateArrayAdapter;
import rdi.mobapp.passwordpanacea.application.PasswordPanacea;
import rdi.mobapp.passwordpanacea.bean.CustomKeyFields;
import rdi.mobapp.passwordpanacea.bean.GetCustomKeyFields;
import rdi.mobapp.passwordpanacea.databasehelper.DBHelper;
import rdi.mobapp.passwordpanacea.utility.CustomDialogNewField;
import rdi.mobapp.passwordpanacea.utility.CustomDialogSingleButton;
import rdi.mobapp.passwordpanacea.utility.MyLinearLayoutDialog;

public class TemplateActivity
  extends BaseActivity
{
  private String activity;
  private Boolean arrowClicked = Boolean.valueOf(false);
  private ImageButton btnSetting;
  private DBHelper dbHelper = new DBHelper(this);
  private CustomDialogNewField dialog;
  private Drawable errorIcon;
  private ImageView headerIcon;
  private ListView listTemplateItems;
  private MyLinearLayoutDialog myLinearLayout;
  private ImageButton optnMore;
  private LinearLayout setting;
  private TemplateArrayAdapter templateArrayAdapter;
  private int templateIdRef;
  private ArrayList<GetCustomKeyFields> templateItems = new ArrayList();
  private String templateTitle;
  private Typeface tf;
  private LinearLayout toolAdd;
  private LinearLayout toolInfo;
  private LinearLayout toolTabContainer;
  private TextView txtAdd;
  private TextView txtHeader;
  private TextView txtInfo;
  private TextView txtSetting;
  private View viewLeftShadow;
  private View viewRightShadow;
  
  public void backPressed()
  {
    getWindow().setSoftInputMode(3);
    if (PasswordPanacea.getKeyActivity() != null)
    {
      String str2 = PasswordPanacea.getKeyActivity();
      Intent localIntent3 = new Intent();
      localIntent3.setClassName("com.hyh.passwordassitant.activity", "com.hyh.passwordassitant.activity." + str2);
      localIntent3.addFlags(65536);
      PasswordPanacea.setDisableAnim(false);
      PasswordPanacea.setSettingActivity(null);
      startActivity(localIntent3);
      finish();
      return;
    }
    if (PasswordPanacea.getGroupActivity() != null)
    {
      String str1 = PasswordPanacea.getGroupActivity();
      PasswordPanacea.setDisableAnim(true);
      Intent localIntent2 = new Intent();
      localIntent2.setClassName("com.hyh.passwordassitant.activity", "com.hyh.passwordassitant.activity." + str1);
      localIntent2.addFlags(65536);
      startActivity(localIntent2);
      finish();
      return;
    }
    Intent localIntent1 = new Intent(getApplicationContext(), TemplateListActivity.class);
    localIntent1.addFlags(65536);
    PasswordPanacea.setDisableAnim(false);
    startActivity(localIntent1);
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
      this.arrowClicked = Boolean.valueOf(true);
      PasswordPanacea.setIsGroupHeaderTabVisible(true);
      return;
    }
    this.toolTabContainer.setVisibility(8);
    this.arrowClicked = Boolean.valueOf(false);
    PasswordPanacea.setIsGroupHeaderTabVisible(false);
  }
  
  public void onBackPressed()
  {
    backPressed();
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle, 2130903075, "");
    getWindow().setSoftInputMode(3);
    this.tf = Typeface.createFromAsset(getAssets(), "fonts/galette.otf");
    Bundle localBundle = getIntent().getExtras();
    this.templateIdRef = localBundle.getInt("templateIdRef");
    this.templateTitle = localBundle.getString("templateTitle");
    this.activity = localBundle.getString("activity");
    this.dialog = new CustomDialogNewField(this, "新条目", "请输入条目名称: ");
    this.txtSetting = ((TextView)findViewById(2131296380));
    this.txtSetting.setTextColor(Color.parseColor("#000000"));
    this.txtSetting.setTypeface(this.tf);
    this.txtHeader = ((TextView)findViewById(2131296441));
    this.txtHeader.setText(this.templateTitle);
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
    this.setting = ((LinearLayout)findViewById(2131296378));
    this.setting.setEnabled(false);
    this.errorIcon = getResources().getDrawable(2130837593);
    this.errorIcon.setBounds(new Rect(0, 0, this.errorIcon.getIntrinsicWidth(), this.errorIcon.getIntrinsicHeight()));
    this.myLinearLayout = ((MyLinearLayoutDialog)findViewById(2131296275));
    this.myLinearLayout.setMyListActivity(this);
    this.toolTabContainer = ((LinearLayout)findViewById(2131296617));
    this.toolAdd = ((LinearLayout)findViewById(2131296412));
    this.toolInfo = ((LinearLayout)findViewById(2131296618));
    this.txtAdd = ((TextView)findViewById(2131296414));
    this.txtInfo = ((TextView)findViewById(2131296620));
    this.txtAdd.setTypeface(this.tf);
    this.txtInfo.setTypeface(this.tf);
    this.listTemplateItems = ((ListView)findViewById(2131296621));
    View localView = ((LayoutInflater)getSystemService("layout_inflater")).inflate(2130903060, null, false);
    this.listTemplateItems.addFooterView(localView, null, false);
    this.templateItems = this.dbHelper.getCustomKeyFields(this.templateIdRef);
    this.templateArrayAdapter = new TemplateArrayAdapter(this, this.templateItems, this.listTemplateItems, this.templateIdRef, this.templateTitle);
    this.listTemplateItems.setAdapter(this.templateArrayAdapter);
    this.toolInfo.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        final CustomDialogSingleButton localCustomDialogSingleButton = new CustomDialogSingleButton(TemplateActivity.this, "Info", "通过自定义模板添加你需要的条目，只需要点击添加，输入条目名称即可.");
        localCustomDialogSingleButton.show();
        localCustomDialogSingleButton.customAlertBoxPositiveButton.setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymous2View)
          {
            localCustomDialogSingleButton.dismiss();
          }
        });
      }
    });
    this.toolAdd.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        TemplateActivity.this.dialog.show();
        TemplateActivity.this.dialog.customAlertBoxNegitiveButton.setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymous2View)
          {
            TemplateActivity.this.dialog.customAlertBoxField.setText("");
            TemplateActivity.this.dialog.customAlertBoxErrorMsg.setVisibility(4);
            TemplateActivity.this.dialog.dismiss();
          }
        });
        TemplateActivity.this.dialog.customAlertBoxPositiveButton.setText("添加");
        TemplateActivity.this.dialog.customAlertBoxPositiveButton.setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymous2View)
          {
            TemplateActivity.this.dialog.customAlertBoxField.setText(TemplateActivity.this.dialog.customAlertBoxField.getText().toString().trim());
            if (TemplateActivity.this.dialog.customAlertBoxField.getText().toString().length() != 0)
            {
              if (TemplateActivity.this.dbHelper.templateFieldAlreadyExists(TemplateActivity.this.templateIdRef, TemplateActivity.this.dialog.customAlertBoxField.getText().toString()) == 0)
              {
                CustomKeyFields localCustomKeyFields = new CustomKeyFields(TemplateActivity.this.templateIdRef, TemplateActivity.this.dialog.customAlertBoxField.getText().toString(), 0, null, 0);
                TemplateActivity.this.dbHelper.addCustomTemplateKeyFields(localCustomKeyFields);
                TemplateActivity.this.showToast("模板条目 \"" + TemplateActivity.this.dialog.customAlertBoxField.getText().toString() + "\" 已添加.");
                if (PasswordPanacea.getKeyEnteredFields() != null) {
                  PasswordPanacea.getKeyEnteredFields().add("");
                }
                Intent localIntent = new Intent(TemplateActivity.this, TemplateActivity.class);
                Bundle localBundle = new Bundle();
                localBundle.putInt("templateIdRef", TemplateActivity.this.templateIdRef);
                localBundle.putString("templateTitle", TemplateActivity.this.templateTitle);
                localBundle.putString("activity", TemplateActivity.this.activity);
                localIntent.putExtras(localBundle);
                TemplateActivity.this.startActivity(localIntent);
                return;
              }
              TemplateActivity.this.dialog.customAlertBoxErrorMsg.setVisibility(0);
              TemplateActivity.this.dialog.customAlertBoxErrorMsg.setText("该条目已存在.");
              return;
            }
            TemplateActivity.this.dialog.customAlertBoxErrorMsg.setVisibility(0);
            TemplateActivity.this.dialog.customAlertBoxErrorMsg.setText("请输入条目名称.");
          }
        });
      }
    });
  }
  
  protected void onStart()
  {
    super.onStart();
    if (PasswordPanacea.getIsTemplateHeaderTabVisible())
    {
      this.toolTabContainer.setVisibility(0);
      this.optnMore.setSelected(true);
      this.arrowClicked = Boolean.valueOf(true);
    }
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
 * Qualified Name:     rdi.mobapp.passwordpanacea.activity.TemplateActivity
 * JD-Core Version:    0.7.0.1
 */