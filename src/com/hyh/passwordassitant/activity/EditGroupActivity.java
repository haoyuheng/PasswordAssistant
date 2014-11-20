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
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import rdi.mobapp.passwordpanacea.application.PasswordPanacea;
import rdi.mobapp.passwordpanacea.bean.Group;
import rdi.mobapp.passwordpanacea.databasehelper.DBHelper;
import rdi.mobapp.passwordpanacea.utility.CustomDialogSingleButton;

public class EditGroupActivity
  extends BaseActivity
{
  private Button btnCancle;
  private ImageButton btnGroup;
  private Button btnSave;
  private DBHelper dbHelper = new DBHelper(this);
  private Drawable errorIcon;
  private LinearLayout footerGroup;
  private ArrayList<Group> groupDetail = new ArrayList();
  private ImageView headerIcon;
  private ImageButton optnMore;
  private String storedGroupTitle;
  private Typeface tf = null;
  private TextView txtDivEnter;
  private TextView txtGroup;
  private EditText txtgroupDescription;
  private EditText txtgroupName;
  private View viewRightShadow;
  
  public void backPressed()
  {
    getWindow().setSoftInputMode(3);
    Intent localIntent = new Intent(getApplicationContext(), GroupActivity.class);
    localIntent.addFlags(65536);
    startActivity(localIntent);
    finish();
  }
  
  public void btnCancel(View paramView)
  {
    Intent localIntent = new Intent(getApplicationContext(), GroupActivity.class);
    PasswordPanacea.setGroupActivity(null);
    localIntent.addFlags(65536);
    startActivity(localIntent);
    finish();
  }
  
  public void btnSave(View paramView)
  {
    Boolean.valueOf(false);
    Boolean.valueOf(false);
    this.txtgroupName.setText(this.txtgroupName.getText().toString().trim());
    this.txtgroupDescription.setText(this.txtgroupDescription.getText().toString().trim());
    Boolean localBoolean1;
    Boolean localBoolean2;
    if (this.txtgroupName.getText().length() != 0)
    {
      localBoolean1 = this.dbHelper.groupAlreadyExist(this.txtgroupName.getText().toString());
      localBoolean2 = this.dbHelper.groupExistInTrash(this.txtgroupName.getText().toString());
      
      if ((localBoolean1.booleanValue())){ 
    	  new CustomDialogSingleButton(this, "Duplicate!", "分组命名重复.").show();
    	  return;
      }else if(localBoolean2.booleanValue())
      {
        new CustomDialogSingleButton(this, "Duplicate!", "分组命名已存在于回收站").show();
        return;
      }else if ((this.storedGroupTitle.equals(this.txtgroupName.getText().toString())) || (!localBoolean1.booleanValue()))
      {
        this.dbHelper.updateGroupDetail(PasswordPanacea.getGroupMasterId(), this.txtgroupName.getText().toString(), this.txtgroupDescription.getText().toString());
        this.txtgroupName.setText("");
        this.txtgroupDescription.setText("");
        PasswordPanacea.setGroupActivity(null);
        View localView = getLayoutInflater().inflate(2130903048, (ViewGroup)findViewById(2131296314));
        TextView localTextView = (TextView)localView.findViewById(2131296315);
        localTextView.setTypeface(this.tf);
        localTextView.setText("Group edit done!");
        Toast localToast = new Toast(getApplicationContext());
        localToast.setDuration(0);
        localToast.setView(localView);
        localToast.show();
        Intent localIntent = new Intent(getApplicationContext(), GroupActivity.class);
        localIntent.addFlags(65536);
        startActivity(localIntent);
        finish();
      }
      
    }else{
    	new CustomDialogSingleButton(this, "Invalid!", "标题不能为空.").show();
    	this.txtgroupName.setError(null, this.errorIcon);
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
    super.onCreate(paramBundle, 2130903041, "Edit Group");
    this.tf = Typeface.createFromAsset(getAssets(), "fonts/galette.otf");
    getWindow().setSoftInputMode(3);
    this.btnGroup = ((ImageButton)findViewById(2131296369));
    this.btnGroup.setImageResource(2130837598);
    this.txtGroup = ((TextView)findViewById(2131296370));
    this.txtGroup.setTextColor(Color.parseColor("#000000"));
    this.txtGroup.setTypeface(this.tf);
    this.optnMore = ((ImageButton)findViewById(2131296442));
    this.optnMore.setVisibility(8);
    this.headerIcon = ((ImageView)findViewById(2131296440));
    this.headerIcon.setImageResource(2130837510);
    this.viewRightShadow = findViewById(2131296371);
    this.viewRightShadow.setVisibility(0);
    this.viewRightShadow.setBackgroundResource(2130837578);
    PasswordPanacea.setTemplateActivity("EditGroupActivity");
    if ((PasswordPanacea.getGroupActivity() == null) || (PasswordPanacea.getGroupActivity().equals("")))
    {
      PasswordPanacea.setTempGroupMasterId(PasswordPanacea.getGroupMasterId());
      PasswordPanacea.setTempGroupTitle(PasswordPanacea.getGroupTitle());
      PasswordPanacea.setGroupActivity("EditGroupActivity");
      this.errorIcon = getResources().getDrawable(2130837593);
      this.errorIcon.setBounds(new Rect(0, 0, this.errorIcon.getIntrinsicWidth(), this.errorIcon.getIntrinsicHeight()));
      this.btnSave = ((Button)findViewById(2131296269));
      this.btnSave.setTypeface(this.tf);
      this.btnCancle = ((Button)findViewById(2131296270));
      this.btnCancle.setTypeface(this.tf);
      this.txtgroupName = ((EditText)findViewById(2131296266));
      this.txtgroupName.setTypeface(this.tf);
      this.txtgroupDescription = ((EditText)findViewById(2131296267));
      this.txtgroupDescription.setTypeface(this.tf);
      this.footerGroup = ((LinearLayout)findViewById(2131296368));
      this.footerGroup.setBackgroundResource(2130837537);
      this.txtDivEnter = ((TextView)findViewById(2131296265));
      this.txtDivEnter.setTypeface(this.tf);
      this.footerGroup.setEnabled(true);
      this.dbHelper = new DBHelper(this);
      this.groupDetail = this.dbHelper.getGroupDetail(PasswordPanacea.getGroupMasterId());
      
    }else{
    	PasswordPanacea.setGroupMasterId(PasswordPanacea.getTempGroupMasterId());
        PasswordPanacea.setGroupTitle(PasswordPanacea.getTempGroupTitle());
    }
    for (int i = 0;i < this.groupDetail.size(); i++)
    {
      this.txtgroupName.append(((Group)this.groupDetail.get(i)).getGroupTitle().toString());
      this.txtgroupDescription.append(((Group)this.groupDetail.get(i)).getGroupDescription().toString());
      this.storedGroupTitle = ((Group)this.groupDetail.get(i)).getGroupTitle().toString();
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

 * Qualified Name:     rdi.mobapp.passwordpanacea.activity.EditGroupActivity

 * JD-Core Version:    0.7.0.1

 */