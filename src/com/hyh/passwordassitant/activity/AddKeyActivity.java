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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import rdi.mobapp.passwordpanacea.CryptoHelper;
import rdi.mobapp.passwordpanacea.CryptoHelperException;
import rdi.mobapp.passwordpanacea.adapter.SpinnerArrayAdapter;
import rdi.mobapp.passwordpanacea.application.PasswordPanacea;
import rdi.mobapp.passwordpanacea.bean.CustomKeyFields;
import rdi.mobapp.passwordpanacea.bean.GetCustomKeyFields;
import rdi.mobapp.passwordpanacea.bean.Items;
import rdi.mobapp.passwordpanacea.bean.Key;
import rdi.mobapp.passwordpanacea.bean.KeyDetail;
import rdi.mobapp.passwordpanacea.databasehelper.DBHelper;
import rdi.mobapp.passwordpanacea.utility.CustomDialog;
import rdi.mobapp.passwordpanacea.utility.CustomDialogNewField;
import rdi.mobapp.passwordpanacea.utility.CustomDialogSingleButton;
import rdi.mobapp.passwordpanacea.utility.MyLinearLayout;

public class AddKeyActivity
  extends BaseActivity
{
  private Button btnCancle;
  private ImageButton btnKey;
  private Button btnSave;
  private CheckBox chkToAddMore;
  private CryptoHelper cryptoHelper;
  private ArrayList<GetCustomKeyFields> customKeyFieldDetail;
  private DBHelper dbHelper;
  private CustomDialogNewField dialog;
  private EditText[] editText;
  private LinearLayout editTextContainer;
  private Drawable errorIcon;
  private Animation fadeIn;
  private Animation fadeOut;
  private LinearLayout footerKey;
  private int groupId;
  private ArrayList<Items> groupList;
  private ImageView headerIcon;
  private int j;
  private List<String> keyEnteredFields;
  private ArrayList<Integer> keyIdByGroupId;
  private int keyIdRef;
  private RelativeLayout layoutListviewContainer;
  private RelativeLayout layoutListviewContainerTemplate;
  private LinearLayout layoutMain;
  private LinearLayout.LayoutParams layoutParams;
  private RelativeLayout layoutSpinnerDialog;
  private RelativeLayout layoutSpinnerDialogTemplate;
  private ListView listSpinner;
  private ListView listSpinnerTemplate;
  private MyLinearLayout myLinearLayout;
  private int numberInputField;
  private ImageButton optnMore;
  private SpinnerArrayAdapter spinnerArrayAdapter;
  private SpinnerArrayAdapter spinnerArrayAdapterTemplate;
  private LinearLayout spinnerDialogContainer;
  private LinearLayout spinnerDialogContainerTemplate;
  private String spinnerTemplateVal = null;
  private TableRow tableAddMore;
  private ArrayList<String> templateField;
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
  private TextView txtToAddMore;
  private TextView txtViewTemplate;
  private View viewLeftShadow;
  private View viewRightShadow;
  private Animation zoomIn;
  
  public void addMore(View paramView)
  {
    if (this.chkToAddMore.isChecked())
    {
      this.chkToAddMore.setChecked(false);
      this.txtToAddMore.setSelected(false);
      return;
    }
    this.chkToAddMore.setChecked(true);
    this.txtToAddMore.setSelected(true);
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
    Boolean localBoolean = Boolean.valueOf(true);
    for(int i = 0;i<this.templateField.size();i++){
    	 if (this.editText[i].getText().toString().length() != 0) {
    		 localBoolean = Boolean.valueOf(false);
    		 break;
         }
         
    }
    if (!localBoolean.booleanValue()) {
    	final CustomDialog localCustomDialog = new CustomDialog(this, "Alert!", "确定保存变更吗?");
        localCustomDialog.show();
        localCustomDialog.customAlertBoxNegitiveButton.setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            Intent localIntent = new Intent(AddKeyActivity.this.getApplicationContext(), KeyActivity.class);
            localIntent.addFlags(65536);
            PasswordPanacea.setDisableAnim(false);
            AddKeyActivity.this.startActivity(localIntent);
            AddKeyActivity.this.finish();
            localCustomDialog.dismiss();
          }
        });
        localCustomDialog.customAlertBoxPositiveButton.setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            localCustomDialog.dismiss();
          }
        });
    }else{
    	Intent localIntent = new Intent(getApplicationContext(), KeyActivity.class);
        localIntent.addFlags(65536);
        PasswordPanacea.setDisableKeyAnim(false);
        startActivity(localIntent);
        finish();
    }
  }
  
  public void btnSave(View paramView)
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
      new CustomDialogSingleButton(this, "Invalid!", "链接格式错误!").show();
      return;
    }
    this.groupId = this.dbHelper.getGroupId(this.txtSpinner.getText().toString());
    int k = this.dbHelper.getTemplateIdByName(this.txtSpinnerTemplate.getText().toString());
    this.customKeyFieldDetail = this.dbHelper.getCustomKeyFields(k);
    this.keyIdByGroupId = this.dbHelper.getKeyIdByGroupId(this.groupId);
    ArrayList localArrayList1 = new ArrayList();
    int m = 0;
    while(m < this.keyIdByGroupId.size()){
    	localArrayList1.add(Integer.valueOf(this.dbHelper.getTemplateIdRefByKeyId(((Integer)this.keyIdByGroupId.get(m)).intValue())));
    	m++;
    }
    Log.e("a","a");
    ArrayList localArrayList2 = new ArrayList();
    int n=0;
    while (n < localArrayList1.size()) {
    	localArrayList2.add(Integer.valueOf(this.dbHelper.getCustomKeyFieldId(((Integer)localArrayList1.get(n)).intValue())));
        n++;
    }
    Log.e("b","b");
    
    ArrayList localArrayList3 = new ArrayList();
    int i1=0;
    while (i1 < localArrayList2.size()) {
    	localArrayList3.add(this.dbHelper.getCustomFieldTitleVal(((Integer)localArrayList2.get(i1)).intValue(), ((Integer)this.keyIdByGroupId.get(i1)).intValue()));
        i1++;
    }
    Log.e("c","c");
    Boolean localBoolean;
    
    if (!localArrayList3.contains(this.editText[0].getText().toString().toLowerCase())) {
    	localBoolean = Boolean.valueOf(false);
    }else
    	localBoolean = Boolean.valueOf(true);
    Log.e("d","d "+this.customKeyFieldDetail.size()); 
	  if (localBoolean.booleanValue())
	  {
		  new CustomDialogSingleButton(this, "Duplicate!", "密码命名重复.").show();
	  }else{
		  Key localKey = new Key(this.groupId, 0, k);
	      this.dbHelper.addKeyId(localKey);
	      this.keyIdRef = this.dbHelper.getLastKeyId();
	      
	      for(int i2=0;;i2++){
	      
	    	 // Log.e("e","e "+i2); 
		      if (i2 >= this.customKeyFieldDetail.size())
		      {
		    	  
		            View localView = getLayoutInflater().inflate(2130903048, (ViewGroup)findViewById(2131296314));
		            TextView localTextView = (TextView)localView.findViewById(2131296315);
		            localTextView.setTypeface(this.tf);
		            localTextView.setText("密码创建成功!");
		            Toast localToast = new Toast(getApplicationContext());
		            localToast.setDuration(0);
		            localToast.setView(localView);
		            localToast.show();
		            if (this.chkToAddMore.isChecked()) {
		            	for (this.j = 0;; this.j = (1 + this.j))
		                {
		                  if (this.j >= this.numberInputField)
		                  {
		                    this.editText[0].requestFocus();
		                    return;
		                  }
		                  this.editText[this.j].setText("");
		                  this.editText[this.j].setError(null);
		                }
		            }else{
		            	Intent localIntent = new Intent(getApplicationContext(), KeyActivity.class);
		                localIntent.addFlags(65536);
		                startActivity(localIntent);
		                finish();
		            }
		            return;
		      }
		      else if (i2 == 2)
		      {
		          Object localObject = this.editText[2].getText().toString();
		          this.cryptoHelper = new CryptoHelper(2);
		          try
		          {
		            this.cryptoHelper.setPassword("madhur+sonu");
		            String str2 = this.cryptoHelper.encrypt((String)localObject);
		            localObject = str2;
		            KeyDetail localKeyDetail4;
		            localKeyDetail4 = new KeyDetail(this.keyIdRef, ((GetCustomKeyFields)this.customKeyFieldDetail.get(i2)).getCustomKeyFieldId(), (String)localObject);
		            this.dbHelper.addKeyDetail(localKeyDetail4);
		          }
		          catch (CryptoHelperException localCryptoHelperException)
		          {
		              localCryptoHelperException.printStackTrace();
		              localCryptoHelperException.getMessage();		            
		          }
		          
		      }else if ((i2 == 3) && (this.editText[3].getText().toString().equalsIgnoreCase("http://")))
	          {
		            KeyDetail localKeyDetail3 = new KeyDetail(this.keyIdRef, ((GetCustomKeyFields)this.customKeyFieldDetail.get(i2)).getCustomKeyFieldId(), "");
		            this.dbHelper.addKeyDetail(localKeyDetail3);
	          }
	          else if (i2 == 0)
	          {
		            char[] arrayOfChar = this.editText[i2].getText().toString().toCharArray();
		            arrayOfChar[0] = Character.toUpperCase(arrayOfChar[0]);
		            EditText localEditText = this.editText[i2];
		            String str1 = new String(arrayOfChar);
		            localEditText.setText(str1);
		            KeyDetail localKeyDetail2 = new KeyDetail(this.keyIdRef, ((GetCustomKeyFields)this.customKeyFieldDetail.get(i2)).getCustomKeyFieldId(), this.editText[i2].getText().toString());
		            this.dbHelper.addKeyDetail(localKeyDetail2);
	          }
	          else
	          {
		            KeyDetail localKeyDetail1 = new KeyDetail(this.keyIdRef, ((GetCustomKeyFields)this.customKeyFieldDetail.get(i2)).getCustomKeyFieldId(), this.editText[i2].getText().toString());
		            this.dbHelper.addKeyDetail(localKeyDetail1);
	          }
	      }
	  }   
  }
  
  public void dynamicEditBox()
  {
    this.spinnerTemplateVal = this.txtSpinnerTemplate.getText().toString();
    Log.e("spinnerTemplateVal",this.spinnerTemplateVal);
    this.templateIdRef = this.dbHelper.getTemplateIdByName(this.spinnerTemplateVal);
    this.templateField = this.dbHelper.getTemplateField(this.templateIdRef);
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
    for (int k = 0;; k++)
    {
      if (k >= this.templateField.size())
      {
        resumeEditTextValues();
        return;
      }
      RelativeLayout localRelativeLayout = new RelativeLayout(this);
      arrayOfRelativeLayout[k] = localRelativeLayout;
      arrayOfRelativeLayout[k].setId(k);
      RelativeLayout.LayoutParams localLayoutParams1 = new RelativeLayout.LayoutParams(-1, -2);
      localLayoutParams1.setMargins(0, (int)(0.5F + 5.0F * f), 0, 0);
      arrayOfRelativeLayout[k].setLayoutParams(localLayoutParams1);
      View localView = new View(this);
      arrayOfView[k] = localView;
      arrayOfView[k].setId(k);
      RelativeLayout.LayoutParams localLayoutParams2 = new RelativeLayout.LayoutParams(-1, (int)(0.5F + 5.0F * f));
      localLayoutParams2.addRule(12);
      arrayOfView[k].setLayoutParams(localLayoutParams2);
      arrayOfView[k].setBackgroundColor(Color.parseColor("#777777"));
      EditText[] arrayOfEditText = this.editText;
      EditText localEditText1 = new EditText(this);
      arrayOfEditText[k] = localEditText1;
      this.editText[k].setId(k);
      RelativeLayout.LayoutParams localLayoutParams3 = new RelativeLayout.LayoutParams(-1, -2);
      localLayoutParams3.addRule(15);
      int m = (int)(0.5F + 1.5F * f);
      int n = (int)(0.5F + 2.5F * f);
      localLayoutParams3.setMargins(m, n, m, n);
      this.editText[k].setLayoutParams(localLayoutParams3);
      this.editText[k].setBackgroundColor(Color.parseColor("#efefef"));
      int i1 = (int)(0.5F + 10.0F * f);
      int i2 = (int)(0.5F + 5.0F * f);
      this.editText[k].setPadding(i1, i2, i1, i2);
      EditText localEditText2 = this.editText[k];
      StringBuilder localStringBuilder = new StringBuilder("Enter ");
      localEditText2.setHint(((String)this.templateField.get(k)).toString());
      this.editText[k].setTypeface(this.tf);
      this.editText[k].setSingleLine(true);
      arrayOfRelativeLayout[k].addView(arrayOfView[k]);
      arrayOfRelativeLayout[k].addView(this.editText[k]);
      this.editTextContainer.addView(arrayOfRelativeLayout[k], this.layoutParams);
    }
  }
  
  public void headerBackPressed(View paramView)
  {
    backPressed();
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
    super.onCreate(paramBundle, 2130903042, "Add Key");
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
    this.zoomIn = AnimationUtils.loadAnimation(this, 2130968592);
    this.btnSave = ((Button)findViewById(2131296269));
    this.btnSave.setTypeface(this.tf);
    this.btnCancle = ((Button)findViewById(2131296270));
    this.btnCancle.setTypeface(this.tf);
    this.optnMore = ((ImageButton)findViewById(2131296442));
    this.optnMore.setVisibility(8);
    this.chkToAddMore = ((CheckBox)findViewById(2131296273));
    this.txtToAddMore = ((TextView)findViewById(2131296272));
    this.txtToAddMore.setTypeface(this.tf);
    this.tableAddMore = ((TableRow)findViewById(2131296271));
    this.tableAddMore.setVisibility(0);
    this.txtAddNewField = ((TextView)findViewById(2131296287));
    this.txtAddNewField.setTypeface(this.tf);
    this.txtViewTemplate = ((TextView)findViewById(2131296288));
    this.txtViewTemplate.setTypeface(this.tf);
    this.dbHelper = new DBHelper(this);
    this.templateField = new ArrayList();
    this.layoutSpinnerDialog = ((RelativeLayout)findViewById(2131296294));
    this.spinnerDialogContainer = ((LinearLayout)findViewById(2131296295));
    this.listSpinner = ((ListView)findViewById(2131296298));
    this.txtSpinner = ((TextView)findViewById(2131296282));
    this.txtSpinner.setTypeface(this.tf);
    this.layoutSpinnerDialogTemplate = ((RelativeLayout)findViewById(2131296299));
    this.spinnerDialogContainerTemplate = ((LinearLayout)findViewById(2131296300));
    this.listSpinnerTemplate = ((ListView)findViewById(2131296303));
    View localView = ((LayoutInflater)getSystemService("layout_inflater")).inflate(2130903059, null, false);
    this.listSpinnerTemplate.addFooterView(localView);
    this.txtSpinnerTemplate = ((TextView)findViewById(2131296283));
    this.txtSpinnerTemplate.setTypeface(this.tf);
    this.txtSpinnerDialogBtn = ((TextView)findViewById(2131296304));
    this.txtSpinnerDialogBtn.setTypeface(this.tf);
    this.layoutListviewContainerTemplate = ((RelativeLayout)findViewById(2131296302));
    this.txtSpinnerTemplate.setText(PasswordPanacea.getTemplateSpinnerVal());
    this.txtSpinner.setText(PasswordPanacea.getGroupTitle());
    this.txtSpinnerDialogTitle = ((TextView)findViewById(2131296296));
    this.txtSpinnerDialogTitle.setTypeface(this.tf_title);
    this.txtSpinnerDialogTitleTemplate = ((TextView)findViewById(2131296301));
    this.txtSpinnerDialogTitleTemplate.setTypeface(this.tf_title);
    this.layoutListviewContainer = ((RelativeLayout)findViewById(2131296297));
    this.txtDivSelect = ((TextView)findViewById(2131296278));
    this.txtDivSelect.setTypeface(this.tf);
    this.txtDivEnter = ((TextView)findViewById(2131296265));
    this.txtDivEnter.setTypeface(this.tf);
    this.txtDivActions = ((TextView)findViewById(2131296285));
    this.txtDivActions.setTypeface(this.tf);
    this.txtSpinner.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        ((InputMethodManager)AddKeyActivity.this.getSystemService("input_method")).hideSoftInputFromWindow(AddKeyActivity.this.getCurrentFocus().getWindowToken(), 2);
        AddKeyActivity.this.animateVisibilityOn(AddKeyActivity.this.layoutSpinnerDialog);
        AddKeyActivity.this.spinnerDialogContainer.startAnimation(AddKeyActivity.this.zoomIn);
      }
    });
    this.txtSpinnerTemplate.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        PasswordPanacea.setAddNewFieldClicked(false);
        PasswordPanacea.setAddKeyText(null);
        AddKeyActivity.this.takeResumeFirstFourValues();
        AddKeyActivity.this.spinnerArrayAdapterTemplate = new SpinnerArrayAdapter(AddKeyActivity.this, AddKeyActivity.this.templateList, AddKeyActivity.this.listSpinnerTemplate, AddKeyActivity.this.layoutSpinnerDialogTemplate, AddKeyActivity.this.txtSpinnerTemplate, "AddKeyActivity", "Template", AddKeyActivity.this.txtSpinnerTemplate.getText().toString(), AddKeyActivity.this.txtSpinner.getText().toString());
        AddKeyActivity.this.listSpinnerTemplate.setAdapter(AddKeyActivity.this.spinnerArrayAdapterTemplate);
        ((InputMethodManager)AddKeyActivity.this.getSystemService("input_method")).hideSoftInputFromWindow(AddKeyActivity.this.getCurrentFocus().getWindowToken(), 2);
        AddKeyActivity.this.animateVisibilityOn(AddKeyActivity.this.layoutSpinnerDialogTemplate);
        AddKeyActivity.this.spinnerDialogContainerTemplate.startAnimation(AddKeyActivity.this.zoomIn);
      }
    });
    this.txtSpinnerDialogBtn.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        AddKeyActivity.this.layoutSpinnerDialogTemplate.setVisibility(4);
        AddKeyActivity.this.layoutSpinnerDialogTemplate.startAnimation(AddKeyActivity.this.fadeOut);
        AddKeyActivity.this.layoutSpinnerDialogTemplate.setVisibility(8);
        AddKeyActivity.this.takeResumeValues();
        PasswordPanacea.setGroupActivity(null);
        AddKeyActivity.this.layoutSpinnerDialog.setVisibility(8);
        AddKeyActivity.this.dialog = new CustomDialogNewField(AddKeyActivity.this, "添加模板", "请输入新模板名称: ");
        AddKeyActivity.this.dialog.show();
        AddKeyActivity.this.dialog.customAlertBoxNegitiveButton.setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymous2View)
          {
            AddKeyActivity.this.dialog.customAlertBoxField.setText("");
            AddKeyActivity.this.dialog.customAlertBoxErrorMsg.setVisibility(4);
            AddKeyActivity.this.dialog.dismiss();
          }
        });
        AddKeyActivity.this.dialog.customAlertBoxPositiveButton.setText("添加");
        AddKeyActivity.this.dialog.customAlertBoxPositiveButton.setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymous2View)
          {
            AddKeyActivity.this.dialog.customAlertBoxField.setText(AddKeyActivity.this.dialog.customAlertBoxField.getText().toString().trim());
            if (AddKeyActivity.this.dialog.customAlertBoxField.getText().toString().length() != 0)
            {
              if (Pattern.compile("[^a-z0-9 ]", 2).matcher(AddKeyActivity.this.dialog.customAlertBoxField.getText().toString()).find())
              {
                new CustomDialogSingleButton(AddKeyActivity.this, "Invalid", "请避免输入特殊符号.").show();
                return;
              }
              if (AddKeyActivity.this.dbHelper.templateAlreadyExists(AddKeyActivity.this.dialog.customAlertBoxField.getText().toString()) == 0)
              {
                AddKeyActivity.this.dbHelper.addTemplate(AddKeyActivity.this.dialog.customAlertBoxField.getText().toString());
                PasswordPanacea.setTemplateSpinnerVal(AddKeyActivity.this.dialog.customAlertBoxField.getText().toString());
                AddKeyActivity.this.showToast("模板 \"" + AddKeyActivity.this.dialog.customAlertBoxField.getText().toString() + "\" 创建成功." + "\n" + "请为新模板添加新的条目.");
                int i = AddKeyActivity.this.dbHelper.getTemplateIdByName(AddKeyActivity.this.dialog.customAlertBoxField.getText().toString());
                AddKeyActivity.this.dbHelper.addDefaultTemplateFields(i);
                Intent localIntent = new Intent(AddKeyActivity.this, TemplateActivity.class);
                Bundle localBundle = new Bundle();
                localBundle.putInt("templateIdRef", i);
                localBundle.putString("templateTitle", AddKeyActivity.this.dialog.customAlertBoxField.getText().toString());
                localBundle.putString("activity", "AddKeyActivity");
                localIntent.putExtras(localBundle);
                AddKeyActivity.this.startActivity(localIntent);
                return;
              }
              AddKeyActivity.this.dialog.customAlertBoxErrorMsg.setVisibility(0);
              AddKeyActivity.this.dialog.customAlertBoxErrorMsg.setText("模板命名重复.");
              return;
            }
            AddKeyActivity.this.dialog.customAlertBoxErrorMsg.setVisibility(0);
            AddKeyActivity.this.dialog.customAlertBoxErrorMsg.setText("请输入模板名称.");
          }
        });
      }
    });
    this.groupList = new ArrayList();
    this.groupList = this.dbHelper.getGroupList(0);
    float f1 = getResources().getDisplayMetrics().density;
    float f2;
    if (this.groupList.size() == 1)
    {
      LinearLayout.LayoutParams localLayoutParams1 = new LinearLayout.LayoutParams(-1, (int)(0.5F + 48.0F * f1));
      this.layoutListviewContainer.setLayoutParams(localLayoutParams1);
    }else  if (this.groupList.size() == 2)
    {
        LinearLayout.LayoutParams localLayoutParams6 = new LinearLayout.LayoutParams(-1, (int)(0.5F + 96.0F * f1));
        this.layoutListviewContainer.setLayoutParams(localLayoutParams6);
    }
    else if (this.groupList.size() == 3)
    {
        LinearLayout.LayoutParams localLayoutParams7 = new LinearLayout.LayoutParams(-1, (int)(0.5F + 144.0F * f1));
        this.layoutListviewContainer.setLayoutParams(localLayoutParams7);
    }
    else if (this.groupList.size() >= 4) 
    {    	
		  LinearLayout.LayoutParams localLayoutParams8 = new LinearLayout.LayoutParams(-1, (int)(0.5F + 192.0F * f1));
	      this.layoutListviewContainer.setLayoutParams(localLayoutParams8);
    }
      this.spinnerArrayAdapter = new SpinnerArrayAdapter(this, this.groupList, this.listSpinner, this.layoutSpinnerDialog, this.txtSpinner, "AddKeyActivity", "Group", "", "");
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
      this.spinnerArrayAdapterTemplate = new SpinnerArrayAdapter(this, this.templateList, this.listSpinnerTemplate, this.layoutSpinnerDialogTemplate, this.txtSpinnerTemplate, "AddKeyActivity", "Template", this.txtSpinnerTemplate.getText().toString(), this.txtSpinner.getText().toString());
      this.listSpinnerTemplate.setAdapter(this.spinnerArrayAdapterTemplate);
      
      dynamicEditBox();
      this.txtAddNewField.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          PasswordPanacea.setAddKeyText(null);
          PasswordPanacea.setAddNewFieldClicked(true);
          AddKeyActivity.this.takeResumeValues();
          AddKeyActivity.this.templateIdRef = AddKeyActivity.this.dbHelper.getTemplateIdByName(AddKeyActivity.this.txtSpinnerTemplate.getText().toString());
          AddKeyActivity.this.dialog = new CustomDialogNewField(AddKeyActivity.this, "新条目", "请输入条目名称: ");
          AddKeyActivity.this.dialog.show();
          AddKeyActivity.this.dialog.customAlertBoxErrorMsg.setVisibility(0);
          AddKeyActivity.this.dialog.customAlertBoxErrorMsg.setTextColor(Color.parseColor("#666666"));
          AddKeyActivity.this.dialog.customAlertBoxErrorMsg.setText("Note: 新条目将添加到模板 \"" + AddKeyActivity.this.txtSpinnerTemplate.getText().toString() + "\" .");
          AddKeyActivity.this.dialog.customAlertBoxNegitiveButton.setOnClickListener(new View.OnClickListener()
          {
            public void onClick(View paramAnonymous2View)
            {
              AddKeyActivity.this.dialog.customAlertBoxField.setText("");
              AddKeyActivity.this.dialog.customAlertBoxErrorMsg.setVisibility(4);
              AddKeyActivity.this.dialog.dismiss();
            }
          });
          AddKeyActivity.this.dialog.customAlertBoxPositiveButton.setText("添加");
          AddKeyActivity.this.dialog.customAlertBoxPositiveButton.setOnClickListener(new View.OnClickListener()
          {
            public void onClick(View paramAnonymous2View)
            {
              AddKeyActivity.this.dialog.customAlertBoxField.setText(AddKeyActivity.this.dialog.customAlertBoxField.getText().toString().trim());
              if (AddKeyActivity.this.dialog.customAlertBoxField.getText().toString().length() != 0)
              {
                if (AddKeyActivity.this.dbHelper.templateFieldAlreadyExists(AddKeyActivity.this.templateIdRef, AddKeyActivity.this.dialog.customAlertBoxField.getText().toString()) == 0)
                {
                  CustomKeyFields localCustomKeyFields = new CustomKeyFields(AddKeyActivity.this.templateIdRef, AddKeyActivity.this.dialog.customAlertBoxField.getText().toString(), 0, null, 0);
                  AddKeyActivity.this.dbHelper.addCustomTemplateKeyFields(localCustomKeyFields);
                  AddKeyActivity.this.showToast("模板条目 \"" + AddKeyActivity.this.dialog.customAlertBoxField.getText().toString() + "\" 添加成功.");
                  Intent localIntent = new Intent(AddKeyActivity.this.getApplicationContext(), AddKeyActivity.class);
                  localIntent.addFlags(65536);
                  AddKeyActivity.this.startActivity(localIntent);
                  return;
                }
                AddKeyActivity.this.dialog.customAlertBoxErrorMsg.setTextColor(Color.parseColor("#dc1010"));
                AddKeyActivity.this.dialog.customAlertBoxErrorMsg.setVisibility(0);
                AddKeyActivity.this.dialog.customAlertBoxErrorMsg.setText("条目命名重复.");
                return;
              }
              AddKeyActivity.this.dialog.customAlertBoxErrorMsg.setTextColor(Color.parseColor("#dc1010"));
              AddKeyActivity.this.dialog.customAlertBoxErrorMsg.setVisibility(0);
              AddKeyActivity.this.dialog.customAlertBoxErrorMsg.setText("请输入条目名称.");
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
          AddKeyActivity.this.takeResumeValues();
          Intent localIntent = new Intent(AddKeyActivity.this.getApplicationContext(), TemplateActivity.class);
          Bundle localBundle = new Bundle();
          localBundle.putInt("templateIdRef", AddKeyActivity.this.dbHelper.getTemplateIdByName(AddKeyActivity.this.txtSpinnerTemplate.getText().toString()));
          localBundle.putString("templateTitle", AddKeyActivity.this.txtSpinnerTemplate.getText().toString());
          localBundle.putString("activity", "AddKeyActivity");
          localIntent.putExtras(localBundle);
          AddKeyActivity.this.startActivity(localIntent);
        }
      });
      PasswordPanacea.setKeyActivity("AddKeyActivity");
      if (PasswordPanacea.getAddKeyText() != null) {
        this.editText[0].setText(PasswordPanacea.getAddKeyText());
      }
      if (!PasswordPanacea.getPasswordDisplay()) {
    	  this.editText[2].setTransformationMethod(PasswordTransformationMethod.getInstance());
      }else
    	  this.editText[2].setInputType(144);
      
      this.editText[3].setOnFocusChangeListener(new View.OnFocusChangeListener()
      {
        public void onFocusChange(View paramAnonymousView, boolean paramAnonymousBoolean)
        {
          if ((paramAnonymousBoolean) && (AddKeyActivity.this.editText[3].getText().toString().length() == 0)) {
            AddKeyActivity.this.editText[3].append("http://");
          }
        }
      });
    
    
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
    for (int k = 0;; k++)
    {
      if (k >= i)
      {
        if (!PasswordPanacea.isFirstCalled()) {
          this.editText[(-1 + this.templateField.size())].requestFocus();
        }
        PasswordPanacea.setKeyEnteredFields(null);
        return;
      }
      if (PasswordPanacea.getKeyEnteredFields().size() > k) {
        this.editText[k].setText(((String)PasswordPanacea.getKeyEnteredFields().get(k)).toString());
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

 * Qualified Name:     rdi.mobapp.passwordpanacea.activity.AddKeyActivity

 * JD-Core Version:    0.7.0.1

 */