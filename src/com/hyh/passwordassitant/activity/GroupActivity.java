package com.hyh.passwordassitant.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Process;
import android.text.ClipboardManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;
import java.util.ArrayList;
import java.util.List;
import rdi.mobapp.passwordpanacea.CryptoHelper;
import rdi.mobapp.passwordpanacea.CryptoHelperException;
import rdi.mobapp.passwordpanacea.adapter.GroupArrayAdapter;
import rdi.mobapp.passwordpanacea.application.PasswordPanacea;
import rdi.mobapp.passwordpanacea.bean.FieldValue;
import rdi.mobapp.passwordpanacea.bean.Group;
import rdi.mobapp.passwordpanacea.bean.Items;
import rdi.mobapp.passwordpanacea.databasehelper.DBHelper;
import rdi.mobapp.passwordpanacea.utility.CustomDialog;
import rdi.mobapp.passwordpanacea.utility.CustomDialogSingleButton;
import rdi.mobapp.passwordpanacea.utility.MyLinearLayoutDialog;

public class GroupActivity
  extends BaseActivity
{
  private Boolean arrowClicked = Boolean.valueOf(false);
  private ImageButton btnGroup;
  private CryptoHelper cryptoHelper;
  private DBHelper dbHelper;
  private ArrayList<FieldValue> fieldValue;
  private TextWatcher filterTextWatcher = new TextWatcher()
  {
    public void afterTextChanged(Editable paramAnonymousEditable) {}
    
    public void beforeTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3) {}
    
    public void onTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3)
    {
    	Log.e("filterTextWatcher",paramAnonymousCharSequence+"");
    	GroupActivity.this.groupArrayAdapter.getFilter().filter(paramAnonymousCharSequence);
    }
  };
  private ViewFlipper flipper;
  private LinearLayout footerGroup;
  private GroupArrayAdapter groupArrayAdapter;
  private ArrayList<Group> groupDetail;
  private List<Items> groupItems;
  private RelativeLayout groupSearch;
  private ImageView img_check;
  private Boolean isGeneralExists;
  private boolean ischeckBoxVisible = false;
  private ArrayList<Integer> keyId;
  private TextView listEmpty;
  private ListView listView;
  private MyLinearLayoutDialog myLinearLayout;
  private ImageButton optnMore;
  private EditText searchBox;
  private Boolean selectAll = Boolean.valueOf(false);
  private int templateId;
  private Typeface tf;
  private LinearLayout toolRemoveGroup;
  private LinearLayout toolTabContainer;
  private TextView txtGroup;
  private TextView txt_add;
  private TextView txt_check;
  private TextView txt_delete;
  private TextView txt_select;
  private TextView txt_share;
  private TextView txt_trash;
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
  
  private void share(String paramString1, String paramString2)
  {
    Intent localIntent = new Intent("android.intent.action.SEND");
    localIntent.setType("text/plain");
    localIntent.putExtra("android.intent.extra.TEXT", paramString1);
    startActivity(Intent.createChooser(localIntent, paramString2));
  }
  
  public void addGroup(View paramView)
  {
    PasswordPanacea.setAddGroupText("");
    Intent localIntent = new Intent(getApplicationContext(), AddGroupActivity.class);
    localIntent.addFlags(65536);
    startActivity(localIntent);
    finish();
  }
  
  public void backPressed()
  {
    final ClipboardManager localClipboardManager = (ClipboardManager)getSystemService("clipboard");
    CustomDialog localCustomDialog = new CustomDialog(this, "Exit?", "Do you want to exit?");
    localCustomDialog.show();
    localCustomDialog.customAlertBoxPositiveButton.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (!PasswordPanacea.getCopyOutsideApp()) {
          localClipboardManager.setText("");
        }
        Intent localIntent = new Intent(GroupActivity.this.getApplicationContext(), Dispatcher.class);
        localIntent.addFlags(65536);
        PasswordPanacea.setGroupActivity(null);
        PasswordPanacea.setKeyActivity(null);
        PasswordPanacea.setSettingActivity(null);
        GroupActivity.this.startActivity(localIntent);
        GroupActivity.this.moveTaskToBack(true);
        GroupActivity.this.finish();
        Process.killProcess(Process.myPid());
      }
    });
  }
  
  public void headerBackPressed(View paramView) {}
  
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
	    this.groupArrayAdapter = new GroupArrayAdapter(this, this.groupItems, this.listView, Boolean.valueOf(false), Boolean.valueOf(false), Boolean.valueOf(true));
	    this.listView.setAdapter(this.groupArrayAdapter);
    }
  }
  
  public void onBackPressed()
  {
    backPressed();
  }
  
  public void onContentChanged()
  {
    super.onContentChanged();
    View localView = findViewById(2131296438);
    localView.setVisibility(0);
    ((ListView)findViewById(2131296437)).setEmptyView(localView);
    Typeface localTypeface = Typeface.createFromAsset(getAssets(), "fonts/galette.otf");
    this.listEmpty = ((TextView)findViewById(2131296439));
    this.listEmpty.setTypeface(localTypeface);
    Animation localAnimation = AnimationUtils.loadAnimation(this, 2130968590);
    localAnimation.setDuration(500L);
    this.listEmpty.startAnimation(localAnimation);
    this.listEmpty.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        Intent localIntent = new Intent(GroupActivity.this.getApplicationContext(), AddGroupActivity.class);
        PasswordPanacea.setAddGroupText(GroupActivity.this.searchBox.getText().toString());
        localIntent.addFlags(65536);
        GroupActivity.this.startActivity(localIntent);
        GroupActivity.this.finish();
      }
    });
  }
  
  public void onCreate(Bundle paramBundle)
  {
	
	  
    super.onCreate(paramBundle, 2130903052, "Group");
    
    
    this.myLinearLayout = ((MyLinearLayoutDialog)findViewById(2131296275));
    this.myLinearLayout.setMyListActivity(this);
    this.groupSearch = ((RelativeLayout)findViewById(2131296434));
    this.tf = Typeface.createFromAsset(getAssets(), "fonts/galette.otf");
    this.txt_add = ((TextView)findViewById(2131296414));
    this.txt_select = ((TextView)findViewById(2131296417));
    this.txt_trash = ((TextView)findViewById(2131296420));
    this.txt_check = ((TextView)findViewById(2131296426));
    this.img_check = ((ImageView)findViewById(2131296425));
    this.txt_delete = ((TextView)findViewById(2131296429));
    this.txt_share = ((TextView)findViewById(2131296432));
    this.txt_add.setTypeface(this.tf);
    this.txt_select.setTypeface(this.tf);
    this.txt_trash.setTypeface(this.tf);
    this.txt_check.setTypeface(this.tf);
    this.txt_delete.setTypeface(this.tf);
    this.txt_share.setTypeface(this.tf);
    this.footerGroup = ((LinearLayout)findViewById(2131296368));
    this.footerGroup.setBackgroundResource(2130837537);
    this.footerGroup.setEnabled(false);
    this.flipper = ((ViewFlipper)findViewById(2131296391));
    this.toolTabContainer = ((LinearLayout)findViewById(2131296409));
    this.toolRemoveGroup = ((LinearLayout)findViewById(2131296427));
    this.toolRemoveGroup.setEnabled(true);
    this.searchBox = ((EditText)findViewById(2131296436));
    this.searchBox.setTypeface(this.tf);
    this.searchBox.addTextChangedListener(this.filterTextWatcher);
    this.listView = ((ListView)findViewById(2131296437));
    View localView = ((LayoutInflater)getSystemService("layout_inflater")).inflate(2130903059, null, false);
    this.listView.addFooterView(localView, null, false);
    this.optnMore = ((ImageButton)findViewById(2131296442));
    this.dbHelper = new DBHelper(this);
    this.btnGroup = ((ImageButton)findViewById(2131296369));
    this.btnGroup.setImageResource(2130837598);
    this.txtGroup = ((TextView)findViewById(2131296370));
    this.txtGroup.setTextColor(Color.parseColor("#000000"));
    this.txtGroup.setTypeface(this.tf);
    this.viewRightShadow = findViewById(2131296371);
    this.viewRightShadow.setVisibility(0);
    this.viewRightShadow.setBackgroundResource(2130837578);
    PasswordPanacea.setGroupActivity(null);
    if (PasswordPanacea.getGroupSearchEnable()) {
      this.groupSearch.setVisibility(0);
    }else{
    	this.groupSearch.setVisibility(8);
    }
    
      this.groupItems = new ArrayList();
      this.groupItems = this.dbHelper.getGroupList(0);
      this.groupArrayAdapter = new GroupArrayAdapter(this, this.groupItems, this.listView, Boolean.valueOf(false), Boolean.valueOf(false), Boolean.valueOf(true));
      this.listView.setAdapter(this.groupArrayAdapter);
      
      	
      return;
  }
  
  protected void onDestroy()
  {
    super.onDestroy();
    this.searchBox.removeTextChangedListener(this.filterTextWatcher);
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
  
  public void optionDone(View paramView)
  {
    this.flipper.setInAnimation(inFromLeftAnimation());
    this.flipper.setOutAnimation(outToRightAnimation());
    this.flipper.showNext();
    this.ischeckBoxVisible = false;
    this.searchBox.setText("");
    this.groupItems = this.dbHelper.getGroupList(0);
    this.groupArrayAdapter = new GroupArrayAdapter(this, this.groupItems, this.listView, Boolean.valueOf(false), Boolean.valueOf(false), Boolean.valueOf(true));
    this.listView.setAdapter(this.groupArrayAdapter);
    this.img_check.setImageResource(2130837669);
    this.txt_check.setText("全选");
    this.selectAll = Boolean.valueOf(false);
  }
  
  public void optionRemove(View paramView)
  {
    if (PasswordPanacea.getSelectedItems().size() != this.groupItems.size())
    {
      this.img_check.setImageResource(2130837669);
      this.txt_check.setText("All");
      this.selectAll = Boolean.valueOf(false);
    }
    Log.e("optionRemove",""+PasswordPanacea.getSelectedItems().size());
    if (PasswordPanacea.getSelectedItems().size() == 0)
    {
      new CustomDialogSingleButton(this, "Invalid!", "请先选择分组.").show();
      return;
    }
    this.isGeneralExists = Boolean.valueOf(false);
    for (int i = 0;i < PasswordPanacea.getSelectedItems().size(); i++)
    {
      if (((Items)PasswordPanacea.getSelectedItems().get(i)).getItemTitle().equals("General")) {
          
      	final CustomDialogSingleButton localCustomDialogSingleButton = new CustomDialogSingleButton(this, "Invalid!", "无法删除 \"默认分组\".");
          localCustomDialogSingleButton.show();
          localCustomDialogSingleButton.customAlertBoxPositiveButton.setOnClickListener(new View.OnClickListener()
          {
            public void onClick(View paramAnonymousView)
            {
              localCustomDialogSingleButton.dismiss();
            }
          });
          this.isGeneralExists = Boolean.valueOf(true);
          return;
      }
    }
    Log.e("isGeneralExists",""+isGeneralExists);
    for (int i = 0;; i++)
    {
      if (i >= PasswordPanacea.getSelectedItems().size()) {
    	  Intent localIntent = new Intent(getApplicationContext(), GroupActivity.class);
          localIntent.addFlags(65536);
          startActivity(localIntent);
          return;
      }
		if (!this.isGeneralExists.booleanValue()) {
			this.dbHelper.getGroupInTrash(((Items)PasswordPanacea.getSelectedItems().get(i)).getItemId());
		}
    }
  }
  
  public void optionSelect(View paramView)
  {
    this.flipper.setInAnimation(inFromRightAnimation());
    this.flipper.setOutAnimation(outToLeftAnimation());
    this.flipper.showNext();
    this.ischeckBoxVisible = true;
    this.searchBox.setText("");
    this.groupItems = this.dbHelper.getGroupList(0);
    this.groupArrayAdapter = new GroupArrayAdapter(this, this.groupItems, this.listView, Boolean.valueOf(true), Boolean.valueOf(false), Boolean.valueOf(false));
    this.listView.setAdapter(this.groupArrayAdapter);
  }
  
  public void optionSelectAll(View paramView)
  {
    if (!this.selectAll.booleanValue())
    {
      this.img_check.setImageResource(2130837665);
      this.txt_check.setText("全不选");
      this.groupArrayAdapter = new GroupArrayAdapter(this, this.groupItems, this.listView, Boolean.valueOf(true), Boolean.valueOf(true), Boolean.valueOf(true));
      this.listView.setAdapter(this.groupArrayAdapter);
      this.selectAll = Boolean.valueOf(true);
      return;
    }
    this.img_check.setImageResource(2130837669);
    this.txt_check.setText("全选");
    this.groupArrayAdapter = new GroupArrayAdapter(this, this.groupItems, this.listView, Boolean.valueOf(true), Boolean.valueOf(false), Boolean.valueOf(true));
    this.listView.setAdapter(this.groupArrayAdapter);
    this.selectAll = Boolean.valueOf(false);
  }
  
  public void optionShare(View paramView)
  {
    if (PasswordPanacea.getSelectedItems().size() == 0)
    {
      new CustomDialogSingleButton(this, "Invalid!", "请先选择分组.").show();
      return;
    }
    StringBuilder localStringBuilder = new StringBuilder();
    this.fieldValue = new ArrayList();
    this.groupDetail = new ArrayList();
    this.keyId = new ArrayList();
    
    
    for(int i=0;i<PasswordPanacea.getSelectedItems().size();i++){
    	this.groupDetail = this.dbHelper.getGroupDetail(((Items)PasswordPanacea.getSelectedItems().get(i)).getItemId());
        this.keyId = this.dbHelper.getKeyIdByGroupId(((Items)PasswordPanacea.getSelectedItems().get(i)).getItemId());
        if (i != 0) {
          localStringBuilder.append("\n");
        }
        localStringBuilder.append("\n----- Group -----\n");
        localStringBuilder.append("Title: " + ((Group)this.groupDetail.get(0)).getGroupTitle());
        if ((((Group)this.groupDetail.get(0)).getGroupDescription() != null) && (!((Group)this.groupDetail.get(0)).getGroupDescription().equals(""))) {
          localStringBuilder.append("\nDescription: " + ((Group)this.groupDetail.get(0)).getGroupDescription());
        }
        for(int j=0;j<this.keyId.size();j++){
        	localStringBuilder.append("\n Key");
            this.templateId = this.dbHelper.getTemplateIdRefByKeyId(((Integer)this.keyId.get(j)).intValue());
            this.fieldValue = this.dbHelper.getFieldValue(this.templateId, ((Integer)this.keyId.get(j)).intValue());
            for(int k =0;k<this.fieldValue.size();k++){
            	if (k == 2)
                {
                  Object localObject = ((FieldValue)this.fieldValue.get(k)).getCustomKeyFieldValue();
                  this.cryptoHelper = new CryptoHelper(2);
                  try
                  {
                    this.cryptoHelper.setPassword("madhur+sonu");
                    String str = this.cryptoHelper.decrypt((String)localObject);
                    localObject = str;
                  }
                  catch (CryptoHelperException localCryptoHelperException)
                  {
                    for (;;)
                    {
                      localCryptoHelperException.printStackTrace();
                      localCryptoHelperException.getMessage();
                    }
                  }
                  localStringBuilder.append("\n");
                  localStringBuilder.append("\t\t\t" + ((FieldValue)this.fieldValue.get(k)).getCustomKeyFieldName() + ": " + (String)localObject);
                }
            	else if ((((FieldValue)this.fieldValue.get(k)).getCustomKeyFieldValue() != null) && (!((FieldValue)this.fieldValue.get(k)).getCustomKeyFieldValue().equals("")))
                 {
                   localStringBuilder.append("\n");
                   localStringBuilder.append("\t\t\t" + ((FieldValue)this.fieldValue.get(k)).getCustomKeyFieldName() + ": " + ((FieldValue)this.fieldValue.get(k)).getCustomKeyFieldValue());
                 }
            }            
        }
        if (i != PasswordPanacea.getSelectedItems().size()) {
            localStringBuilder.append("\n");
        }
        if (i == -1 + PasswordPanacea.getSelectedItems().size()) {
            localStringBuilder.append("\n\n-\n万能密码助手，你的密码管家");
        }
    }
    share(localStringBuilder.toString(), "Share");
    return;    
  }
  
  public void recyclebin(View paramView)
  {
    Intent localIntent = new Intent(getApplicationContext(), RecycleBinActivity.class);
    PasswordPanacea.setTrashActivity("GroupActivity");
    PasswordPanacea.setGroupTabON(true);
    localIntent.addFlags(65536);
    startActivity(localIntent);
    finish();
  }
}


/* Location:           D:\Apkdb\Craining\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     rdi.mobapp.passwordpanacea.activity.GroupActivity
 * JD-Core Version:    0.7.0.1
 */