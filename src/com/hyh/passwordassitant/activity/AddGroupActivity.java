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
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import rdi.mobapp.passwordpanacea.application.PasswordPanacea;
import rdi.mobapp.passwordpanacea.bean.Group;
import rdi.mobapp.passwordpanacea.databasehelper.DBHelper;
import rdi.mobapp.passwordpanacea.utility.CustomDialog;
import rdi.mobapp.passwordpanacea.utility.CustomDialogSingleButton;

public class AddGroupActivity
  extends BaseActivity
{
  private Button btnCancle;
  private ImageButton btnGroup;
  private Button btnSave;
  private CheckBox chkToAddMore;
  private DBHelper dbHelper = new DBHelper(this);
  private Drawable errorIcon;
  private LinearLayout footerGroup;
  private ImageButton headerIcon;
  private LinearLayout layoutMain;
  private ImageButton optnMore;
  private TableRow tableAddMore;
  private Typeface tf;
  private TextView txtDivEnter;
  private TextView txtGroup;
  private EditText txtGroupDesc;
  private EditText txtGroupName;
  private TextView txtToAddMore;
  private View viewRightShadow;
  
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
  
  public void backPressed()
  {
    getWindow().setSoftInputMode(3);
    Intent localIntent = new Intent(getApplicationContext(), GroupActivity.class);
    PasswordPanacea.setGroupActivity(null);
    localIntent.addFlags(65536);
    PasswordPanacea.setDisableAnim(false);
    startActivity(localIntent);
    finish();
  }
  
  public void btnCancel(View paramView)
  {
    getWindow().setSoftInputMode(3);
    if ((this.txtGroupName.getText().toString().length() != 0) || (this.txtGroupDesc.getText().toString().length() != 0))
    {
      final CustomDialog localCustomDialog = new CustomDialog(this, "Note", "确定保存更改吗?");
      localCustomDialog.show();
      localCustomDialog.customAlertBoxNegitiveButton.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          Intent localIntent = new Intent(AddGroupActivity.this.getApplicationContext(), GroupActivity.class);
          localIntent.addFlags(65536);
          PasswordPanacea.setDisableAnim(false);
          AddGroupActivity.this.startActivity(localIntent);
          AddGroupActivity.this.finish();
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
      return;
    }
    Intent localIntent = new Intent(getApplicationContext(), GroupActivity.class);
    localIntent.addFlags(65536);
    PasswordPanacea.setDisableAnim(false);
    startActivity(localIntent);
    finish();
  }
  
  public void btnSave(View paramView)
  {
    Boolean.valueOf(false);
    Boolean.valueOf(false);
    this.txtGroupName.setText(this.txtGroupName.getText().toString().trim());
    this.txtGroupDesc.setText(this.txtGroupDesc.getText().toString().trim());
    Boolean localBoolean1;
    Boolean localBoolean2;
    if (this.txtGroupName.getText().length() != 0)
    {
      localBoolean1 = this.dbHelper.groupAlreadyExist(this.txtGroupName.getText().toString());
      localBoolean2 = this.dbHelper.groupExistInTrash(this.txtGroupName.getText().toString());
      if (localBoolean1.booleanValue()) {
    	  new CustomDialogSingleButton(this, "Duplicate!", "分组命名重复").show();
          return;
      }else if(localBoolean2.booleanValue()){
    	  new CustomDialogSingleButton(this, "Duplicate!", "分组命名已存在于回收站.").show();
          return;
      }else{
    	  try
          {
           
    		  Group localGroup = new Group(this.txtGroupName.getText().toString(), this.txtGroupDesc.getText().toString(), Boolean.valueOf(false));
	          this.dbHelper.addGroup(localGroup);
	          View localView = getLayoutInflater().inflate(2130903048, (ViewGroup)findViewById(2131296314));
	          TextView localTextView = (TextView)localView.findViewById(2131296315);
	          localTextView.setTypeface(this.tf);
	          localTextView.setText("分组创建成功");
	          Toast localToast = new Toast(getApplicationContext());
	          localToast.setDuration(0);
	          localToast.setView(localView);
	          localToast.show();
	          if (!this.chkToAddMore.isChecked())
	          {
	            Intent localIntent = new Intent(getApplicationContext(), GroupActivity.class);
	            localIntent.addFlags(65536);
	            PasswordPanacea.setDisableAnim(false);
	            startActivity(localIntent);
	            finish();
	            return;
	          }else{
	          	this.txtGroupName.setText("");
	              this.txtGroupDesc.setText("");
	              this.txtGroupName.setError(null);
	              this.txtGroupDesc.setError(null);
	              this.txtGroupName.requestFocus();
	              return;
	          }
          }
          catch (Exception localException)
          {
              new CustomDialogSingleButton(this, "Invalid!", "无效输入.").show();
          }
      }
    }else{
    	 new CustomDialogSingleButton(this, "Invalid!", "标题不能为空.").show();
   	  	 this.txtGroupName.setError(null, this.errorIcon);
   	  	 return;
    }
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
    super.onCreate(paramBundle, 2130903041, "Add Group");
    getWindow().setSoftInputMode(3);
    this.tf = Typeface.createFromAsset(getAssets(), "fonts/galette.otf");
    this.btnGroup = ((ImageButton)findViewById(2131296369));
    this.btnGroup.setImageResource(2130837598);
    this.txtGroup = ((TextView)findViewById(2131296370));
    this.txtGroup.setTextColor(Color.parseColor("#000000"));
    this.txtGroup.setTypeface(this.tf);
    this.headerIcon = ((ImageButton)findViewById(2131296440));
    this.headerIcon.setImageResource(2130837510);
    this.viewRightShadow = findViewById(2131296371);
    this.viewRightShadow.setVisibility(0);
    this.viewRightShadow.setBackgroundResource(2130837578);
    PasswordPanacea.setTemplateActivity("AddGroupActivity");
    this.layoutMain = ((LinearLayout)findViewById(2131296264));
    Animation localAnimation = AnimationUtils.loadAnimation(this, 2130968590);
    localAnimation.setDuration(600L);
    this.layoutMain.startAnimation(localAnimation);
    this.errorIcon = getResources().getDrawable(2130837593);
    this.errorIcon.setBounds(new Rect(0, 0, this.errorIcon.getIntrinsicWidth(), this.errorIcon.getIntrinsicHeight()));
    this.txtGroupName = ((EditText)findViewById(2131296266));
    this.txtGroupName.setTypeface(this.tf);
    this.txtGroupDesc = ((EditText)findViewById(2131296267));
    this.txtGroupDesc.setTypeface(this.tf);
    this.optnMore = ((ImageButton)findViewById(2131296442));
    this.optnMore.setVisibility(8);
    this.btnSave = ((Button)findViewById(2131296269));
    this.btnSave.setTypeface(this.tf);
    this.btnCancle = ((Button)findViewById(2131296270));
    this.btnCancle.setTypeface(this.tf);
    this.footerGroup = ((LinearLayout)findViewById(2131296368));
    this.footerGroup.setBackgroundResource(2130837537);
    this.footerGroup.setEnabled(true);
    this.chkToAddMore = ((CheckBox)findViewById(2131296273));
    this.txtToAddMore = ((TextView)findViewById(2131296272));
    this.txtToAddMore.setTypeface(this.tf);
    this.txtDivEnter = ((TextView)findViewById(2131296265));
    this.txtDivEnter.setTypeface(this.tf);
    this.tableAddMore = ((TableRow)findViewById(2131296271));
    this.tableAddMore.setVisibility(0);
    this.dbHelper = new DBHelper(this);
    PasswordPanacea.setGroupActivity("AddGroupActivity");
    if (PasswordPanacea.getAddGroupText() != null) {
      this.txtGroupName.setText(PasswordPanacea.getAddGroupText());
    }
  }
  
  public void onStart()
  {
    super.onStart();
    this.tf = Typeface.createFromAsset(getAssets(), "fonts/galette.otf");
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

 * Qualified Name:     rdi.mobapp.passwordpanacea.activity.AddGroupActivity

 * JD-Core Version:    0.7.0.1

 */