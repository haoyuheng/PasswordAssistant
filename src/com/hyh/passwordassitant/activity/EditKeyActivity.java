package com.hyh.passwordassitant.activity;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.method.PasswordTransformationMethod;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import rdi.mobapp.passwordpanacea.CryptoHelper;
import rdi.mobapp.passwordpanacea.CryptoHelperException;
import rdi.mobapp.passwordpanacea.adapter.SpinnerArrayAdapter;
import rdi.mobapp.passwordpanacea.application.PasswordPanacea;
import rdi.mobapp.passwordpanacea.bean.CustomKeyFields;
import rdi.mobapp.passwordpanacea.bean.FieldValue;
import rdi.mobapp.passwordpanacea.bean.GetCustomKeyFields;
import rdi.mobapp.passwordpanacea.bean.Group;
import rdi.mobapp.passwordpanacea.bean.Items;
import rdi.mobapp.passwordpanacea.bean.Key;
import rdi.mobapp.passwordpanacea.bean.KeyDetail;
import rdi.mobapp.passwordpanacea.databasehelper.DBHelper;
import rdi.mobapp.passwordpanacea.utility.CustomDialogNewField;
import rdi.mobapp.passwordpanacea.utility.CustomDialogSingleButton;
import rdi.mobapp.passwordpanacea.utility.MyLinearLayout;

public class EditKeyActivity
  extends BaseActivity
{
  private Button btnCancle;
  private ImageButton btnKey;
  private Button btnSave;
  private CryptoHelper cryptoHelper;
  private ArrayList<GetCustomKeyFields> customKeyFieldDetail;
  private DBHelper dbHelper;
  private CustomDialogNewField dialog;
  private EditText[] editText;
  private LinearLayout editTextContainer;
  private Drawable errorIcon;
  private Animation fadeIn;
  private Animation fadeOut;
  private ArrayList<FieldValue> fieldValue;
  private LinearLayout footerKey;
  private int groupId;
  private ArrayList<Items> groupList;
  private ImageView headerIcon;
  private List<String> keyEnteredFields;
  private ArrayList<Integer> keyIdByGroupId;
  private RelativeLayout layoutListviewContainer;
  private RelativeLayout layoutListviewContainerTemplate;
  private LinearLayout layoutMain;
  private LinearLayout.LayoutParams layoutParams;
  private RelativeLayout layoutSpinnerDialog;
  private RelativeLayout layoutSpinnerDialogTemplate;
  private ListView listSpinner;
  private ListView listSpinnerTemplate;
  private MyLinearLayout myLinearLayout;
  private String newSpinnerValue = null;
  private String newSpinnerValueTemplate = null;
  private int numberInputField;
  private String oldSpinnerValue = null;
  private String oldSpinnerValueTemplate = null;
  private ImageButton optnMore;
  private SpinnerArrayAdapter spinnerArrayAdapter;
  private SpinnerArrayAdapter spinnerArrayAdapterTemplate;
  private LinearLayout spinnerDialogContainer;
  private LinearLayout spinnerDialogContainerTemplate;
  private String spinnerTemplateVal = null;
  private ArrayList<String> templateField;
  private int templateId;
  private int templateIdRef;
  private ArrayList<Items> templateList;
  private Typeface tf = null;
  private Typeface tf_title;
  private TextView txtAddNewField;
  private TextView txtDivActions;
  private TextView txtDivEnter;
  private TextView txtDivSelect;
  private TextView txtKey;
  private TextView txtSelectGroup;
  private TextView txtSelectTemplate;
  private TextView txtSpinner;
  private TextView txtSpinnerDialogBtn;
  private TextView txtSpinnerDialogTitle;
  private TextView txtSpinnerDialogTitleTemplate;
  private TextView txtSpinnerTemplate;
  private TextView txtViewTemplate;
  private View viewLeftShadow;
  private View viewRightShadow;
  private Animation zoomIn;
  
  public void addNewKey() throws CryptoHelperException
  {
    this.dbHelper.deleteKey(PasswordPanacea.getKeyMasterId());
    this.groupId = this.dbHelper.getGroupId(this.txtSpinner.getText().toString());
    this.templateId = this.dbHelper.getTemplateIdByName(this.txtSpinnerTemplate.getText().toString());
    this.customKeyFieldDetail = this.dbHelper.getCustomKeyFields(this.templateId);
    Key localKey = new Key(this.groupId, 0, this.templateId);
    this.dbHelper.addKeyId(localKey);
    int i = this.dbHelper.getLastKeyId();
    int j = 0;
    
    for (;;)
    {
    	if (j >= this.customKeyFieldDetail.size())
        {
          showToast("密码更新成功!");
          Intent localIntent = new Intent(getApplicationContext(), KeyActivity.class);
          localIntent.addFlags(65536);
          startActivity(localIntent);
          finish();
          return;
        }
        if (j == 2)
        {
          Object localObject = this.editText[2].getText().toString();
          this.cryptoHelper = new CryptoHelper(2);
          this.cryptoHelper.setPassword("madhur+sonu");
          String str = this.cryptoHelper.encrypt((String)localObject);
          localObject = str;      
          KeyDetail localKeyDetail4;      
          localKeyDetail4 = new KeyDetail(i, ((GetCustomKeyFields)this.customKeyFieldDetail.get(j)).getCustomKeyFieldId(), (String)localObject);
          this.dbHelper.addKeyDetail(localKeyDetail4);
        }
      
        else if ((j == 3) && (this.editText[3].getText().toString().equalsIgnoreCase("http://")))
      {
        KeyDetail localKeyDetail3 = new KeyDetail(i, ((GetCustomKeyFields)this.customKeyFieldDetail.get(j)).getCustomKeyFieldId(), "");
        this.dbHelper.addKeyDetail(localKeyDetail3);
      }
      else if (j == 0)
      {
        char[] arrayOfChar = this.editText[j].getText().toString().toCharArray();
        arrayOfChar[0] = Character.toUpperCase(arrayOfChar[0]);
        this.editText[j].setText(new String(arrayOfChar));
        KeyDetail localKeyDetail2 = new KeyDetail(i, ((GetCustomKeyFields)this.customKeyFieldDetail.get(j)).getCustomKeyFieldId(), this.editText[j].getText().toString());
        this.dbHelper.addKeyDetail(localKeyDetail2);
      }
      else
      {
        KeyDetail localKeyDetail1 = new KeyDetail(i, ((GetCustomKeyFields)this.customKeyFieldDetail.get(j)).getCustomKeyFieldId(), this.editText[j].getText().toString());
        this.dbHelper.addKeyDetail(localKeyDetail1);
      }
      j++;
    }
  }
  
  public void animateVisibilityOn(View paramView)
  {
    paramView.setVisibility(4);
    paramView.startAnimation(this.fadeIn);
    paramView.setVisibility(0);
  }
  
  public void backPressed()
  {
    getWindow().setSoftInputMode(3);
    Intent localIntent = new Intent(getApplicationContext(), KeyActivity.class);
    localIntent.addFlags(65536);
    PasswordPanacea.setDisableKeyAnim(false);
    startActivity(localIntent);
    finish();
  }
  
  public void btnCancel(View paramView)
  {
    Intent localIntent = new Intent(getApplicationContext(), KeyActivity.class);
    localIntent.addFlags(65536);
    startActivity(localIntent);
    finish();
  }
  
  public void btnSave(View paramView) throws CryptoHelperException
  {
    this.customKeyFieldDetail = new ArrayList();
    this.keyIdByGroupId = new ArrayList();
    Boolean.valueOf(false);
    for (int i = 0;; i++)
    {
      if (i >= this.numberInputField)
      {
        if ((this.editText[0].getText().toString().length() != 0) && (this.editText[1].getText().toString().length() != 0) && (this.editText[2].getText().toString().length() != 0)) {
          break;
        }
        new CustomDialogSingleButton(this, "Invalid!", "标题，用户名，密码不能为空.").show();
        if (this.editText[0].getText().toString().length() == 0) {
          this.editText[0].setError(null, this.errorIcon);
        }
        if (this.editText[1].getText().toString().length() == 0) {
          this.editText[1].setError(null, this.errorIcon);
        }
        if (this.editText[2].getText().toString().length() == 0) {
          this.editText[2].setError(null, this.errorIcon);
        }
        return;
      }
      this.editText[i].setText(this.editText[i].getText().toString().trim());
    }
    if ((this.editText[3].getText().toString().length() != 0) && (!this.editText[3].getText().toString().equalsIgnoreCase("http://")) && (!Patterns.WEB_URL.matcher(this.editText[3].getText().toString()).matches()))
    {
      new CustomDialogSingleButton(this, "Alert!", "链接格式错误.").show();
      return;
    }
    this.groupId = this.dbHelper.getGroupId(this.txtSpinner.getText().toString());
    this.templateId = this.dbHelper.getTemplateIdByName(this.txtSpinnerTemplate.getText().toString());
    this.customKeyFieldDetail = this.dbHelper.getCustomKeyFields(this.templateId);
    this.keyIdByGroupId = this.dbHelper.getKeyIdByGroupId(this.groupId);
    
    ArrayList localArrayList1 = new ArrayList();
    int j =0;
    while( j < this.keyIdByGroupId.size()){
    	localArrayList1.add(Integer.valueOf(this.dbHelper.getTemplateIdRefByKeyId(((Integer)this.keyIdByGroupId.get(j)).intValue())));
        j++;
    }
    
    ArrayList localArrayList2 = new ArrayList();
    int k =0;
    while (k < localArrayList1.size()) {
  	  	localArrayList2.add(Integer.valueOf(this.dbHelper.getCustomKeyFieldId(((Integer)localArrayList1.get(k)).intValue())));
        k++;
    }
    
    ArrayList localArrayList3 = new ArrayList();
    int m = 0;
    while (m < localArrayList2.size()) {
    	Log.e("dddd",m+""+this.dbHelper.getCustomFieldTitleVal(((Integer)localArrayList2.get(m)).intValue(), ((Integer)this.keyIdByGroupId.get(m)).intValue()));
  	  	localArrayList3.add(this.dbHelper.getCustomFieldTitleVal(((Integer)localArrayList2.get(m)).intValue(), ((Integer)this.keyIdByGroupId.get(m)).intValue()));
        m++;
    }
    
    Log.e("ffffffffffffff",""+this.editText[0].getText().toString().toLowerCase()+""+localArrayList3.contains(this.editText[0].getText().toString().toLowerCase()));
    Log.e("fdsfds",""+isSpinnervalueUpdated().booleanValue());
    
    Boolean localBoolean = localArrayList3.contains(this.editText[0].getText().toString().toLowerCase());
    
    if(isSpinnervalueUpdated().booleanValue()){//修改了分组
    	if(localBoolean){//是否存在于修改后的分组
    		new CustomDialogSingleButton(this, "Cannot Move!", "改命名已存在与分组 \"" + this.txtSpinner.getText().toString() + "\" 中.").show();	   
    	}else{
    		addNewKey();
    		return;
    	}
    	
    }else{//分组不变
    	 Boolean localBoolean1 =  PasswordPanacea.getKeyTitle().equalsIgnoreCase(this.editText[0].getText().toString());//是否修改名字
    	 if(localBoolean && !localBoolean1){
    		 new CustomDialogSingleButton(this, "Duplicate!", "密码命名重复.").show();
     		 return;
    	 }else{
    		 addNewKey();
 			 return;
    	 }
    }
 }
  
  public void dynamicEditBox()
  {
    this.spinnerTemplateVal = this.txtSpinnerTemplate.getText().toString();
    this.templateIdRef = this.dbHelper.getTemplateIdByName(this.spinnerTemplateVal);
    this.templateField = this.dbHelper.getTemplateField(this.templateIdRef);
    Log.e("dynamicEditBox templateField",""+templateField.size());
    this.editTextContainer = ((LinearLayout)findViewById(2131296284));
    float f = getResources().getDisplayMetrics().density;
    int i = (int)(0.5F + 5.0F * f);
    this.layoutParams = new LinearLayout.LayoutParams(-1, -2);
    this.layoutParams.setMargins(0, i, 0, 0);
    RelativeLayout[] arrayOfRelativeLayout = new RelativeLayout[this.templateField.size()];
    this.editText = new EditText[this.templateField.size()];
    View[] arrayOfView = new View[this.templateField.size()];
    if (this.templateField.size() != 0) {
      this.numberInputField = this.templateField.size();
    }
    for (int j = 0;; j++)
    {
      if (j >= this.templateField.size()) {
        return;
      }
      RelativeLayout localRelativeLayout = new RelativeLayout(this);
      arrayOfRelativeLayout[j] = localRelativeLayout;
      arrayOfRelativeLayout[j].setId(j);
      RelativeLayout.LayoutParams localLayoutParams1 = new RelativeLayout.LayoutParams(-1, -2);
      localLayoutParams1.setMargins(0, (int)(0.5F + 5.0F * f), 0, 0);
      arrayOfRelativeLayout[j].setLayoutParams(localLayoutParams1);
      View localView = new View(this);
      arrayOfView[j] = localView;
      arrayOfView[j].setId(j);
      RelativeLayout.LayoutParams localLayoutParams2 = new RelativeLayout.LayoutParams(-1, (int)(0.5F + 5.0F * f));
      localLayoutParams2.addRule(12);
      arrayOfView[j].setLayoutParams(localLayoutParams2);
      arrayOfView[j].setBackgroundColor(Color.parseColor("#777777"));
      EditText[] arrayOfEditText = this.editText;
      EditText localEditText1 = new EditText(this);
      arrayOfEditText[j] = localEditText1;
      this.editText[j].setId(j);
      RelativeLayout.LayoutParams localLayoutParams3 = new RelativeLayout.LayoutParams(-1, -2);
      localLayoutParams3.addRule(15);
      int k = (int)(0.5F + 1.5F * f);
      int m = (int)(0.5F + 2.5F * f);
      localLayoutParams3.setMargins(k, m, k, m);
      this.editText[j].setLayoutParams(localLayoutParams3);
      this.editText[j].setBackgroundColor(Color.parseColor("#efefef"));
      int n = (int)(0.5F + 10.0F * f);
      int i1 = (int)(0.5F + 5.0F * f);
      this.editText[j].setPadding(n, i1, n, i1);
      EditText localEditText2 = this.editText[j];
      StringBuilder localStringBuilder = new StringBuilder("Enter ");
      localEditText2.setHint(((String)this.templateField.get(j)).toString());
      this.editText[j].setTypeface(this.tf);
      this.editText[j].setSingleLine(true);
      arrayOfRelativeLayout[j].addView(arrayOfView[j]);
      arrayOfRelativeLayout[j].addView(this.editText[j]);
      this.editTextContainer.addView(arrayOfRelativeLayout[j], this.layoutParams);
    }
  }
  
  public void headerBackPressed(View paramView)
  {
    backPressed();
  }
  
  public Boolean isSpinnervalueUpdated()
  {
    Boolean localBoolean = Boolean.valueOf(false);
    this.newSpinnerValue = this.txtSpinner.getText().toString();
    int i = this.dbHelper.getGroupIdByKeyId(PasswordPanacea.getKeyMasterId());
    
    ArrayList localArrayList = this.dbHelper.getGroupDetail(i);
    Log.e("isSpinnervalueUpdated",""+this.newSpinnerValue+" "+((Group)localArrayList.get(0)).getGroupTitle());
    if (!this.newSpinnerValue.equalsIgnoreCase(((Group)localArrayList.get(0)).getGroupTitle())) {
      localBoolean = Boolean.valueOf(true);
    }
    return localBoolean;
  }
  
  public void layoutClickDisable(View paramView)
  {
    paramView.setEnabled(false);
  }
  
  public void onBackPressed()
  {
    if (this.layoutSpinnerDialog.getVisibility() == 0)
    {
      this.layoutSpinnerDialog.setVisibility(4);
      this.layoutSpinnerDialog.startAnimation(this.fadeOut);
      this.layoutSpinnerDialog.setVisibility(8);
      return;
    }
    if (this.layoutSpinnerDialogTemplate.getVisibility() == 0)
    {
      this.layoutSpinnerDialogTemplate.setVisibility(4);
      this.layoutSpinnerDialogTemplate.startAnimation(this.fadeOut);
      this.layoutSpinnerDialogTemplate.setVisibility(8);
      return;
    }
    backPressed();
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle, 2130903042, "Edit Key");
    getWindow().setSoftInputMode(3);
    this.tf = Typeface.createFromAsset(getAssets(), "fonts/galette.otf");
    this.tf_title = Typeface.createFromAsset(getAssets(), "fonts/deepblue.ttf");
    this.btnKey = ((ImageButton)findViewById(2131296374));
    this.btnKey.setImageResource(2130837611);
    this.txtKey = ((TextView)findViewById(2131296375));
    this.txtKey.setTextColor(Color.parseColor("#000000"));
    this.txtKey.setTypeface(this.tf);
    this.headerIcon = ((ImageView)findViewById(2131296440));
    this.headerIcon.setImageResource(2130837510);
    this.viewLeftShadow = findViewById(2131296371);
    this.viewLeftShadow.setVisibility(0);
    this.viewLeftShadow.setBackgroundResource(2130837577);
    this.viewRightShadow = findViewById(2131296376);
    this.viewRightShadow.setVisibility(0);
    this.viewRightShadow.setBackgroundResource(2130837578);
    this.layoutMain = ((LinearLayout)findViewById(2131296264));
    if (PasswordPanacea.isFirstCalled())
    {
      Animation localAnimation = AnimationUtils.loadAnimation(this, 2130968590);
      localAnimation.setDuration(600L);
      this.layoutMain.startAnimation(localAnimation);
    }
    this.fadeIn = AnimationUtils.loadAnimation(this, 2130968576);
    this.fadeIn.setDuration(200L);
    this.zoomIn = AnimationUtils.loadAnimation(this, 2130968592);
    this.fadeOut = AnimationUtils.loadAnimation(this, 2130968591);
    this.fadeOut.setDuration(300L);
    this.myLinearLayout = ((MyLinearLayout)findViewById(2131296275));
    this.myLinearLayout.setMyListActivity(this);
    this.errorIcon = getResources().getDrawable(2130837593);
    this.errorIcon.setBounds(new Rect(0, 0, this.errorIcon.getIntrinsicWidth(), this.errorIcon.getIntrinsicHeight()));
    this.footerKey = ((LinearLayout)findViewById(2131296373));
    this.footerKey.setBackgroundResource(2130837537);
    this.txtSelectGroup = ((TextView)findViewById(2131296279));
    this.txtSelectGroup.setTypeface(this.tf);
    this.txtSelectTemplate = ((TextView)findViewById(2131296280));
    this.txtSelectTemplate.setTypeface(this.tf);
    this.btnSave = ((Button)findViewById(2131296269));
    this.btnSave.setTypeface(this.tf);
    this.btnCancle = ((Button)findViewById(2131296270));
    this.btnCancle.setTypeface(this.tf);
    this.optnMore = ((ImageButton)findViewById(2131296442));
    this.optnMore.setVisibility(8);
    this.layoutSpinnerDialog = ((RelativeLayout)findViewById(2131296294));
    this.spinnerDialogContainer = ((LinearLayout)findViewById(2131296295));
    this.listSpinner = ((ListView)findViewById(2131296298));
    this.txtSpinner = ((TextView)findViewById(2131296282));
    this.txtSpinner.setTypeface(this.tf);
    this.txtSpinner.setText(PasswordPanacea.getGroupTitle().toString());
    this.layoutSpinnerDialogTemplate = ((RelativeLayout)findViewById(2131296299));
    this.spinnerDialogContainerTemplate = ((LinearLayout)findViewById(2131296300));
    this.listSpinnerTemplate = ((ListView)findViewById(2131296303));
    this.txtSpinnerTemplate = ((TextView)findViewById(2131296283));
    this.txtSpinnerTemplate.setTypeface(this.tf);
    this.txtSpinnerDialogBtn = ((TextView)findViewById(2131296304));
    this.txtSpinnerDialogBtn.setTypeface(this.tf);
    this.layoutListviewContainerTemplate = ((RelativeLayout)findViewById(2131296302));
    this.oldSpinnerValue = this.txtSpinner.getText().toString();
    this.txtSpinnerDialogTitle = ((TextView)findViewById(2131296296));
    this.txtSpinnerDialogTitle.setTypeface(this.tf_title);
    this.txtSpinnerDialogTitleTemplate = ((TextView)findViewById(2131296301));
    this.txtSpinnerDialogTitleTemplate.setTypeface(this.tf_title);
    this.layoutListviewContainer = ((RelativeLayout)findViewById(2131296297));
    this.txtAddNewField = ((TextView)findViewById(2131296287));
    this.txtAddNewField.setTypeface(this.tf);
    this.txtViewTemplate = ((TextView)findViewById(2131296288));
    this.txtViewTemplate.setTypeface(this.tf);
    this.txtDivSelect = ((TextView)findViewById(2131296278));
    this.txtDivSelect.setTypeface(this.tf);
    this.txtDivEnter = ((TextView)findViewById(2131296265));
    this.txtDivEnter.setTypeface(this.tf);
    this.txtDivActions = ((TextView)findViewById(2131296285));
    this.txtDivActions.setTypeface(this.tf);
    this.dbHelper = new DBHelper(this);
    this.templateField = new ArrayList();
    this.fieldValue = new ArrayList();
    this.templateId = this.dbHelper.getTemplateIdRefByKeyId(PasswordPanacea.getKeyMasterId());
    this.txtSpinnerTemplate.setText(PasswordPanacea.getTemplateSpinnerVal());
    float f1;
    float f2;
    
      this.txtSpinner.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          ((InputMethodManager)EditKeyActivity.this.getSystemService("input_method")).hideSoftInputFromWindow(EditKeyActivity.this.getCurrentFocus().getWindowToken(), 2);
          EditKeyActivity.this.animateVisibilityOn(EditKeyActivity.this.layoutSpinnerDialog);
          EditKeyActivity.this.spinnerDialogContainer.startAnimation(EditKeyActivity.this.zoomIn);
        }
      });
      this.txtSpinnerTemplate.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          PasswordPanacea.setAddNewFieldClicked(false);
          PasswordPanacea.setAddKeyText(null);
          EditKeyActivity.this.takeResumeFirstFourValues();
          EditKeyActivity.this.spinnerArrayAdapterTemplate = new SpinnerArrayAdapter(EditKeyActivity.this, EditKeyActivity.this.templateList, EditKeyActivity.this.listSpinnerTemplate, EditKeyActivity.this.layoutSpinnerDialogTemplate, EditKeyActivity.this.txtSpinnerTemplate, "EditKeyActivity", "Template", EditKeyActivity.this.txtSpinnerTemplate.getText().toString(), EditKeyActivity.this.txtSpinner.getText().toString());
          EditKeyActivity.this.listSpinnerTemplate.setAdapter(EditKeyActivity.this.spinnerArrayAdapterTemplate);
          ((InputMethodManager)EditKeyActivity.this.getSystemService("input_method")).hideSoftInputFromWindow(EditKeyActivity.this.getCurrentFocus().getWindowToken(), 2);
          EditKeyActivity.this.animateVisibilityOn(EditKeyActivity.this.layoutSpinnerDialogTemplate);
          EditKeyActivity.this.spinnerDialogContainerTemplate.startAnimation(EditKeyActivity.this.zoomIn);
        }
      });
      this.txtSpinnerDialogBtn.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          EditKeyActivity.this.layoutSpinnerDialogTemplate.setVisibility(4);
          EditKeyActivity.this.layoutSpinnerDialogTemplate.startAnimation(EditKeyActivity.this.fadeOut);
          EditKeyActivity.this.layoutSpinnerDialogTemplate.setVisibility(8);
          EditKeyActivity.this.takeResumeValues();
          PasswordPanacea.setGroupActivity(null);
          EditKeyActivity.this.layoutSpinnerDialog.setVisibility(8);
          EditKeyActivity.this.dialog = new CustomDialogNewField(EditKeyActivity.this, "添加模板", "请输入新模板名称: ");
          EditKeyActivity.this.dialog.show();
          EditKeyActivity.this.dialog.customAlertBoxNegitiveButton.setOnClickListener(new View.OnClickListener()
          {
            public void onClick(View paramAnonymous2View)
            {
              EditKeyActivity.this.dialog.customAlertBoxField.setText("");
              EditKeyActivity.this.dialog.customAlertBoxErrorMsg.setVisibility(4);
              EditKeyActivity.this.dialog.dismiss();
            }
          });
          EditKeyActivity.this.dialog.customAlertBoxPositiveButton.setText("添加");
          EditKeyActivity.this.dialog.customAlertBoxPositiveButton.setOnClickListener(new View.OnClickListener()
          {
            public void onClick(View paramAnonymous2View)
            {
              EditKeyActivity.this.dialog.customAlertBoxField.setText(EditKeyActivity.this.dialog.customAlertBoxField.getText().toString().trim());
              if (EditKeyActivity.this.dialog.customAlertBoxField.getText().toString().length() != 0)
              {
                if (Pattern.compile("[^a-z0-9 ]", 2).matcher(EditKeyActivity.this.dialog.customAlertBoxField.getText().toString()).find())
                {
                  new CustomDialogSingleButton(EditKeyActivity.this, "Invalid", "请避免特殊字符.").show();
                  return;
                }
                if (EditKeyActivity.this.dbHelper.templateAlreadyExists(EditKeyActivity.this.dialog.customAlertBoxField.getText().toString()) == 0)
                {
                  EditKeyActivity.this.dbHelper.addTemplate(EditKeyActivity.this.dialog.customAlertBoxField.getText().toString());
                  PasswordPanacea.setTemplateSpinnerVal(EditKeyActivity.this.dialog.customAlertBoxField.getText().toString());
                  EditKeyActivity.this.showToast("模板 \"" + EditKeyActivity.this.dialog.customAlertBoxField.getText().toString() + "\" 创建成功." + "\n" + "请为模板添加新条目.");
                  int i = EditKeyActivity.this.dbHelper.getTemplateIdByName(EditKeyActivity.this.dialog.customAlertBoxField.getText().toString());
                  EditKeyActivity.this.dbHelper.addDefaultTemplateFields(i);
                  Intent localIntent = new Intent(EditKeyActivity.this, TemplateActivity.class);
                  Bundle localBundle = new Bundle();
                  localBundle.putInt("templateIdRef", i);
                  localBundle.putString("templateTitle", EditKeyActivity.this.dialog.customAlertBoxField.getText().toString());
                  localBundle.putString("activity", "AddGroupActivity");
                  localIntent.putExtras(localBundle);
                  EditKeyActivity.this.startActivity(localIntent);
                  return;
                }
                EditKeyActivity.this.dialog.customAlertBoxErrorMsg.setVisibility(0);
                EditKeyActivity.this.dialog.customAlertBoxErrorMsg.setText("模板命名重复.");
                return;
              }
              EditKeyActivity.this.dialog.customAlertBoxErrorMsg.setVisibility(0);
              EditKeyActivity.this.dialog.customAlertBoxErrorMsg.setText("请输入模板名称.");
            }
          });
        }
      });
      this.groupList = new ArrayList();
      this.groupList = this.dbHelper.getGroupList(0);
      f1 = getResources().getDisplayMetrics().density;
      if (this.groupList.size() != 1) {
    	  if (this.groupList.size() == 2)
          {
            LinearLayout.LayoutParams localLayoutParams6 = new LinearLayout.LayoutParams(-1, (int)(0.5F + 96.0F * f1));
            this.layoutListviewContainer.setLayoutParams(localLayoutParams6);
          }
          if (this.groupList.size() == 3)
          {
            LinearLayout.LayoutParams localLayoutParams7 = new LinearLayout.LayoutParams(-1, (int)(0.5F + 144.0F * f1));
            this.layoutListviewContainer.setLayoutParams(localLayoutParams7);
          }
          if (this.groupList.size() >= 4) {
        	  LinearLayout.LayoutParams localLayoutParams8 = new LinearLayout.LayoutParams(-1, (int)(0.5F + 192.0F * f1));
              this.layoutListviewContainer.setLayoutParams(localLayoutParams8);
          }          
      }else{
	      LinearLayout.LayoutParams localLayoutParams1 = new LinearLayout.LayoutParams(-1, (int)(0.5F + 48.0F * f1));
	      this.layoutListviewContainer.setLayoutParams(localLayoutParams1);
      }
      this.spinnerArrayAdapter = new SpinnerArrayAdapter(this, this.groupList, this.listSpinner, this.layoutSpinnerDialog, this.txtSpinner, "EditKeyActivity", "Group", "", "");
      this.listSpinner.setAdapter(this.spinnerArrayAdapter);
      
      this.templateList = new ArrayList();
      this.templateList = this.dbHelper.getTemplateList();
      f2 = getResources().getDisplayMetrics().density;
      if (this.templateList.size() != 1) {
    	  if (this.templateList.size() == 2)
          {
            LinearLayout.LayoutParams localLayoutParams3 = new LinearLayout.LayoutParams(-1, (int)(0.5F + 148.0F * f2));
            this.layoutListviewContainerTemplate.setLayoutParams(localLayoutParams3);
          }
          if (this.templateList.size() == 3)
          {
            LinearLayout.LayoutParams localLayoutParams4 = new LinearLayout.LayoutParams(-1, (int)(0.5F + 196.0F * f2));
            this.layoutListviewContainerTemplate.setLayoutParams(localLayoutParams4);           
          }
          if (this.templateList.size() >= 4) {
        	  LinearLayout.LayoutParams localLayoutParams5 = new LinearLayout.LayoutParams(-1, (int)(0.5F + 244.0F * f2));
              this.layoutListviewContainerTemplate.setLayoutParams(localLayoutParams5);
          }
          
      }else{
    	  LinearLayout.LayoutParams localLayoutParams2 = new LinearLayout.LayoutParams(-1, (int)(0.5F + 100.0F * f2));
    	  this.layoutListviewContainerTemplate.setLayoutParams(localLayoutParams2);
      }
      this.spinnerArrayAdapterTemplate = new SpinnerArrayAdapter(this, this.templateList, this.listSpinnerTemplate, this.layoutSpinnerDialogTemplate, this.txtSpinnerTemplate, "EditKeyActivity", "Template", this.txtSpinnerTemplate.getText().toString(), this.txtSpinner.getText().toString());
      this.listSpinnerTemplate.setAdapter(this.spinnerArrayAdapterTemplate);
      dynamicEditBox();
      this.txtAddNewField.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          PasswordPanacea.setAddKeyText(null);
          PasswordPanacea.setAddNewFieldClicked(true);
          EditKeyActivity.this.takeResumeValues();
          EditKeyActivity.this.templateIdRef = EditKeyActivity.this.dbHelper.getTemplateIdByName(EditKeyActivity.this.txtSpinnerTemplate.getText().toString());
          final CustomDialogNewField localCustomDialogNewField = new CustomDialogNewField(EditKeyActivity.this, "New Field", "Enter New Field Name: ");
          localCustomDialogNewField.show();
          localCustomDialogNewField.customAlertBoxErrorMsg.setVisibility(0);
          localCustomDialogNewField.customAlertBoxErrorMsg.setTextColor(Color.parseColor("#666666"));
          localCustomDialogNewField.customAlertBoxErrorMsg.setText("Note: 新条目将被添加到模板 \"" + EditKeyActivity.this.txtSpinnerTemplate.getText().toString() + "\" .");
          localCustomDialogNewField.customAlertBoxNegitiveButton.setOnClickListener(new View.OnClickListener()
          {
            public void onClick(View paramAnonymous2View)
            {
              localCustomDialogNewField.customAlertBoxField.setText("");
              localCustomDialogNewField.customAlertBoxErrorMsg.setVisibility(4);
              localCustomDialogNewField.dismiss();
            }
          });
          localCustomDialogNewField.customAlertBoxPositiveButton.setText("添加");
          localCustomDialogNewField.customAlertBoxPositiveButton.setOnClickListener(new View.OnClickListener()
          {
            public void onClick(View paramAnonymous2View)
            {
              localCustomDialogNewField.customAlertBoxField.setText(localCustomDialogNewField.customAlertBoxField.getText().toString().trim());
              if (localCustomDialogNewField.customAlertBoxField.getText().toString().length() != 0)
              {
                if (EditKeyActivity.this.dbHelper.templateFieldAlreadyExists(EditKeyActivity.this.templateIdRef, localCustomDialogNewField.customAlertBoxField.getText().toString()) == 0)
                {
                  CustomKeyFields localCustomKeyFields = new CustomKeyFields(EditKeyActivity.this.templateIdRef, localCustomDialogNewField.customAlertBoxField.getText().toString(), 0, null, 0);
                  EditKeyActivity.this.dbHelper.addCustomTemplateKeyFields(localCustomKeyFields);
                  EditKeyActivity.this.showToast("模板条目 \"" + localCustomDialogNewField.customAlertBoxField.getText().toString() + "\" 添加成功.");
                  Intent localIntent = new Intent(EditKeyActivity.this.getApplicationContext(), EditKeyActivity.class);
                  localIntent.addFlags(65536);
                  EditKeyActivity.this.startActivity(localIntent);
                  return;
                }
                localCustomDialogNewField.customAlertBoxErrorMsg.setTextColor(Color.parseColor("#dc1010"));
                localCustomDialogNewField.customAlertBoxErrorMsg.setVisibility(0);
                localCustomDialogNewField.customAlertBoxErrorMsg.setText("模板命名重复.");
                return;
              }
              localCustomDialogNewField.customAlertBoxErrorMsg.setTextColor(Color.parseColor("#dc1010"));
              localCustomDialogNewField.customAlertBoxErrorMsg.setVisibility(0);
              localCustomDialogNewField.customAlertBoxErrorMsg.setText("请输入模板名称.");
            }
          });
        }
      });
      this.txtViewTemplate.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          PasswordPanacea.setAddNewFieldClicked(false);
          PasswordPanacea.setAddKeyText(null);
          PasswordPanacea.setGroupActivity(null);
          EditKeyActivity.this.takeResumeValues();
          Intent localIntent = new Intent(EditKeyActivity.this.getApplicationContext(), TemplateActivity.class);
          Bundle localBundle = new Bundle();
          localBundle.putInt("templateIdRef", EditKeyActivity.this.dbHelper.getTemplateIdByName(EditKeyActivity.this.txtSpinnerTemplate.getText().toString()));
          localBundle.putString("templateTitle", EditKeyActivity.this.txtSpinnerTemplate.getText().toString());
          localBundle.putString("activity", "EditKeyActivity");
          localIntent.putExtras(localBundle);
          EditKeyActivity.this.startActivity(localIntent);
        }
      });
      PasswordPanacea.setKeyActivity("EditKeyActivity");
      if (!PasswordPanacea.getPasswordDisplay()) {
    	  this.editText[2].setTransformationMethod(PasswordTransformationMethod.getInstance());
      }else{
    	  this.editText[2].setInputType(144);
      }
      this.editText[3].setOnFocusChangeListener(new View.OnFocusChangeListener()
      {
        public void onFocusChange(View paramAnonymousView, boolean paramAnonymousBoolean)
        {
          if ((paramAnonymousBoolean) && (EditKeyActivity.this.editText[3].getText().toString().length() == 0)) {
            EditKeyActivity.this.editText[3].append("http://");
          }
        }
      });
    
  
  
  	if (PasswordPanacea.getOldTemplateSpinnerVal() == null)
  	{
  		this.oldSpinnerValueTemplate = this.txtSpinnerTemplate.getText().toString();
    }else{
    	this.oldSpinnerValueTemplate = PasswordPanacea.getOldTemplateSpinnerVal();
        PasswordPanacea.setOldTemplateSpinnerVal(null);
    }
    int i;
    this.fieldValue = this.dbHelper.getFieldValue(this.dbHelper.getTemplateIdByName(this.txtSpinnerTemplate.getText().toString()), PasswordPanacea.getKeyMasterId());
    for (i=0;i < this.fieldValue.size();i++)
    {
    	if (i == 2)
        {
          Object localObject = ((FieldValue)this.fieldValue.get(i)).getCustomKeyFieldValue();
          this.cryptoHelper = new CryptoHelper(2);
          try
          {
            this.cryptoHelper.setPassword("madhur+sonu");
            String str = this.cryptoHelper.decrypt((String)localObject);
            localObject = str;
          }
          catch (CryptoHelperException localCryptoHelperException)
          {
              localCryptoHelperException.printStackTrace();
              localCryptoHelperException.getMessage();
          }
          this.editText[i].append((CharSequence)localObject);
        }
    	else{
    		System.out.println("editText" + this.editText[i].getText().toString());
    		System.out.println("FieldValue" + ((FieldValue)this.fieldValue.get(i)).getCustomKeyFieldValue());
    		if (((FieldValue)this.fieldValue.get(i)).getCustomKeyFieldValue() != null) {
    			this.editText[i].append(((FieldValue)this.fieldValue.get(i)).getCustomKeyFieldValue());
    		}
        }
    }
    resumeEditTextValues();  
    
  }
  
  public void onStart()
  {
    super.onStart();
  }
  
  public void resumeEditTextValues()
  {
    int i = 0;
    if (PasswordPanacea.getKeyEnteredFields() != null)
    {
      if (!PasswordPanacea.isAddNewFieldClicked()) {
    	  i = this.templateField.size();
      }else{
    	  i = -1 + this.templateField.size();
    	  PasswordPanacea.setAddNewFieldClicked(false);
      }      
    }
    Log.e("templateField",this.templateField.size()+" "+i);
    for (int j = 0;; j++)
    {
      if (j >= i)
      {
        if (!PasswordPanacea.isFirstCalled()) {
          this.editText[(-1 + this.templateField.size())].requestFocus();
        }
        PasswordPanacea.setKeyEnteredFields(null);
        return;
      }
      if (PasswordPanacea.getKeyEnteredFields().size() > j)
      {
        this.editText[j].setText("");
        this.editText[j].setText(((String)PasswordPanacea.getKeyEnteredFields().get(j)).toString());
      }
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
  
  public void takeResumeFirstFourValues()
  {
    PasswordPanacea.setFirstCalled(false);
    this.keyEnteredFields = new ArrayList();
    for (int i = 0;; i++)
    {
      if (i >= 4)
      {
        PasswordPanacea.setKeyEnteredFields(this.keyEnteredFields);
        return;
      }
      this.keyEnteredFields.add(i, this.editText[i].getText().toString());
    }
  }
  
  public void takeResumeValues()
  {
    PasswordPanacea.setFirstCalled(false);
    this.keyEnteredFields = new ArrayList();
    for (int i = 0;; i++)
    {
      if (i >= this.templateField.size())
      {
        PasswordPanacea.setKeyEnteredFields(this.keyEnteredFields);
        return;
      }
      this.keyEnteredFields.add(i, this.editText[i].getText().toString());
    }
  }
}



/* Location:           D:\Apkdb\Craining\dex2jar-0.0.9.15\classes_dex2jar.jar

 * Qualified Name:     rdi.mobapp.passwordpanacea.activity.EditKeyActivity

 * JD-Core Version:    0.7.0.1

 */