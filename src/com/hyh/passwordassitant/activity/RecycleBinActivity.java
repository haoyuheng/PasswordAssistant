package com.hyh.passwordassitant.activity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;
import java.util.ArrayList;
import java.util.List;
import rdi.mobapp.passwordpanacea.adapter.TrashGroupArrayAdapter;
import rdi.mobapp.passwordpanacea.adapter.TrashKeyArrayAdapter;
import rdi.mobapp.passwordpanacea.application.PasswordPanacea;
import rdi.mobapp.passwordpanacea.bean.Items;
import rdi.mobapp.passwordpanacea.bean.KeyDetailByKeyId;
import rdi.mobapp.passwordpanacea.databasehelper.DBHelper;
import rdi.mobapp.passwordpanacea.utility.CustomDialog;
import rdi.mobapp.passwordpanacea.utility.CustomDialogSingleButton;

public class RecycleBinActivity
  extends BaseActivity
{
  private Boolean KeyTabOn = Boolean.valueOf(false);
  private Boolean arrowClicked = Boolean.valueOf(false);
  private ImageButton btnSetting;
  private SQLiteDatabase db;
  private DBHelper dbHelper;
  private ViewFlipper flipper;
  private List<Items> groupItems;
  private LinearLayout groupListView;
  private LinearLayout groupTab;
  private Boolean groupTabOn = Boolean.valueOf(true);
  private ImageView headerIcon;
  private ImageView imgGroupIcon;
  private ImageView imgKeyIcon;
  private ImageButton img_check;
  private boolean ischeckBoxVisible = false;
  private ArrayList<KeyDetailByKeyId> keyDetail;
  private ArrayList<Integer> keyId;
  private List<Items> keyItems;
  private LinearLayout keyListView;
  private LinearLayout keyTab;
  private ListView listViewGroup;
  private ListView listViewKey;
  private ImageButton optnMore;
  private Boolean selectAll = Boolean.valueOf(false);
  private LinearLayout setting;
  private Typeface tf;
  private LinearLayout toolTabContainer;
  private TrashGroupArrayAdapter trashGroupArrayAdapter;
  private TrashKeyArrayAdapter trashKeyArrayAdapter;
  private TextView txtSetting;
  private TextView txtTabGroup;
  private TextView txtTabKey;
  private TextView txt_check;
  private TextView txt_delete;
  private TextView txt_empty_trash;
  private TextView txt_restore;
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
    String str = PasswordPanacea.getTrashActivity();
    if (str != null)
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
  
  public void groupTab(View paramView)
  {
    this.groupListView.setVisibility(0);
    this.keyListView.setVisibility(8);
    this.groupTab.setEnabled(false);
    this.keyTab.setEnabled(true);
    this.KeyTabOn = Boolean.valueOf(false);
    this.groupTabOn = Boolean.valueOf(true);
    this.groupTab.setBackgroundResource(2130837687);
    this.keyTab.setBackgroundColor(0);
    this.imgGroupIcon.setImageResource(2130837598);
    this.imgKeyIcon.setImageResource(2130837612);
    this.txtTabGroup.setTextColor(Color.parseColor("#000000"));
    this.txtTabKey.setTextColor(Color.parseColor("#777777"));
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
      PasswordPanacea.setIsTrashHeaderTabVisible(true);
    }else{
    	this.toolTabContainer.setVisibility(8);
        this.optnMore.setSelected(false);
        this.arrowClicked = Boolean.valueOf(false);
        PasswordPanacea.setIsTrashHeaderTabVisible(false);
    }
    if(this.ischeckBoxVisible){
	    this.flipper.setInAnimation(inFromLeftAnimation());
	    this.flipper.setOutAnimation(outToRightAnimation());
	    this.flipper.showNext();
	    this.ischeckBoxVisible = false;
	    this.trashKeyArrayAdapter = new TrashKeyArrayAdapter(this, this.keyItems, this.listViewKey, Boolean.valueOf(false), Boolean.valueOf(false), Boolean.valueOf(true));
	    this.listViewKey.setAdapter(this.trashKeyArrayAdapter);
	    this.trashGroupArrayAdapter = new TrashGroupArrayAdapter(this, this.groupItems, this.listViewGroup, Boolean.valueOf(false), Boolean.valueOf(false), Boolean.valueOf(true));
	    this.listViewGroup.setAdapter(this.trashGroupArrayAdapter);
    }
  }
  
  public void keyTab(View paramView)
  {
    this.groupListView.setVisibility(8);
    this.keyListView.setVisibility(0);
    this.groupTab.setEnabled(true);
    this.keyTab.setEnabled(false);
    this.KeyTabOn = Boolean.valueOf(true);
    this.groupTabOn = Boolean.valueOf(false);
    this.groupTab.setBackgroundColor(0);
    this.keyTab.setBackgroundResource(2130837687);
    this.imgGroupIcon.setImageResource(2130837599);
    this.imgKeyIcon.setImageResource(2130837611);
    this.txtTabGroup.setTextColor(Color.parseColor("#777777"));
    this.txtTabKey.setTextColor(Color.parseColor("#000000"));
  }
  
  public void onBackPressed()
  {
    backPressed();
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle, 2130903068, "Trash");
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
    this.setting = ((LinearLayout)findViewById(2131296378));
    this.setting.setEnabled(false);
    this.tf = Typeface.createFromAsset(getAssets(), "fonts/galette.otf");
    this.txt_select = ((TextView)findViewById(2131296417));
    this.txt_empty_trash = ((TextView)findViewById(2131296420));
    this.txt_check = ((TextView)findViewById(2131296426));
    this.txt_delete = ((TextView)findViewById(2131296429));
    this.txt_restore = ((TextView)findViewById(2131296534));
    this.txtTabGroup = ((TextView)findViewById(2131296537));
    this.txtTabKey = ((TextView)findViewById(2131296540));
    this.imgGroupIcon = ((ImageView)findViewById(2131296536));
    this.imgKeyIcon = ((ImageView)findViewById(2131296539));
    this.txt_select.setTypeface(this.tf);
    this.txt_empty_trash.setTypeface(this.tf);
    this.txt_check.setTypeface(this.tf);
    this.txt_delete.setTypeface(this.tf);
    this.txt_restore.setTypeface(this.tf);
    this.txtTabGroup.setTypeface(this.tf);
    this.txtTabKey.setTypeface(this.tf);
    this.toolTabContainer = ((LinearLayout)findViewById(2131296409));
    this.groupTab = ((LinearLayout)findViewById(2131296535));
    this.keyTab = ((LinearLayout)findViewById(2131296538));
    this.groupListView = ((LinearLayout)findViewById(2131296541));
    this.keyListView = ((LinearLayout)findViewById(2131296543));
    this.groupTab.setEnabled(false);
    this.keyTab.setEnabled(true);
    this.optnMore = ((ImageButton)findViewById(2131296442));
    this.listViewGroup = ((ListView)findViewById(2131296542));
    this.listViewKey = ((ListView)findViewById(2131296544));
    this.flipper = ((ViewFlipper)findViewById(2131296391));
    this.dbHelper = new DBHelper(this);
    this.keyDetail = new ArrayList();
    this.keyId = new ArrayList();
    this.img_check = ((ImageButton)findViewById(2131296531));
    this.groupItems = new ArrayList();
    this.groupItems = this.dbHelper.getGroupList(1);
    
    this.keyItems = new ArrayList();
    this.keyDetail = this.dbHelper.getKeyDetail_deleted();
    Log.e("keyItems",keyDetail.size()+"");
    for (int i = 0;i<this.keyDetail.size();i++)
    {
      this.keyItems.add(new Items(((KeyDetailByKeyId)this.keyDetail.get(i)).getKeyId(), ((KeyDetailByKeyId)this.keyDetail.get(i)).getKeyTitle()));
    }
     if (!PasswordPanacea.getGroupTabON()) {
    	 this.groupListView.setVisibility(8);
        this.keyListView.setVisibility(0);
        this.groupTab.setEnabled(true);
        this.keyTab.setEnabled(false);
        this.KeyTabOn = Boolean.valueOf(true);
        this.groupTabOn = Boolean.valueOf(false);
        this.groupTab.setBackgroundColor(0);
        this.keyTab.setBackgroundResource(2130837687);
        this.imgGroupIcon.setImageResource(2130837599);
        this.imgKeyIcon.setImageResource(2130837611);
        this.txtTabGroup.setTextColor(Color.parseColor("#777777"));
        this.txtTabKey.setTextColor(Color.parseColor("#000000"));
    }else{
	      this.groupListView.setVisibility(0);
	      this.keyListView.setVisibility(8);
	      this.groupTab.setEnabled(false);
	      this.keyTab.setEnabled(true);
	      this.KeyTabOn = Boolean.valueOf(false);
	      this.groupTabOn = Boolean.valueOf(true);
	      this.groupTab.setBackgroundResource(2130837687);
	      this.keyTab.setBackgroundColor(0);
	      this.imgGroupIcon.setImageResource(2130837598);
	      this.imgKeyIcon.setImageResource(2130837612);
	      this.txtTabGroup.setTextColor(Color.parseColor("#000000"));
	      this.txtTabKey.setTextColor(Color.parseColor("#777777"));
	   }
     		if (PasswordPanacea.getTrashActivity().equalsIgnoreCase("SettingActivity")) {
        PasswordPanacea.setSettingActivity("RecycleBinActivity");
      }
 		this.trashGroupArrayAdapter = new TrashGroupArrayAdapter(this, this.groupItems, this.listViewGroup, Boolean.valueOf(false), Boolean.valueOf(false), Boolean.valueOf(true));
 	    this.listViewGroup.setAdapter(this.trashGroupArrayAdapter);
		this.trashKeyArrayAdapter = new TrashKeyArrayAdapter(this, this.keyItems, this.listViewKey, Boolean.valueOf(false), Boolean.valueOf(false), Boolean.valueOf(true));
	    this.listViewKey.setAdapter(this.trashKeyArrayAdapter);
	   
      if (PasswordPanacea.getRemoveRestoreCalled())
      {
        this.trashKeyArrayAdapter = new TrashKeyArrayAdapter(this, this.keyItems, this.listViewKey, Boolean.valueOf(true), Boolean.valueOf(false), Boolean.valueOf(false));
        this.listViewKey.setAdapter(this.trashKeyArrayAdapter);
        this.trashGroupArrayAdapter = new TrashGroupArrayAdapter(this, this.groupItems, this.listViewGroup, Boolean.valueOf(true), Boolean.valueOf(false), Boolean.valueOf(false));
        this.listViewGroup.setAdapter(this.trashGroupArrayAdapter);
      }
      return;
  }
  
  protected void onStart()
  {
    super.onStart();
    if (PasswordPanacea.getIsTrashHeaderTabVisible())
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
    this.trashKeyArrayAdapter = new TrashKeyArrayAdapter(this, this.keyItems, this.listViewKey, Boolean.valueOf(false), Boolean.valueOf(false), Boolean.valueOf(false));
    this.listViewKey.setAdapter(this.trashKeyArrayAdapter);
    this.trashGroupArrayAdapter = new TrashGroupArrayAdapter(this, this.groupItems, this.listViewGroup, Boolean.valueOf(false), Boolean.valueOf(false), Boolean.valueOf(false));
    this.listViewGroup.setAdapter(this.trashGroupArrayAdapter);
  }
  
  public void optionRecycleBin(View paramView)
  {
    if ((this.groupItems.size() != 0) || (this.keyItems.size() != 0))
    {
      final CustomDialog localCustomDialog = new CustomDialog(this, "Alert!", "确定清空回收站吗？");
      localCustomDialog.show();
      localCustomDialog.customAlertBoxPositiveButton.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          int j;
          if (RecycleBinActivity.this.keyItems.size() != 0)
          {
            j = 0;
            while (j < RecycleBinActivity.this.keyItems.size()) {
            	RecycleBinActivity.this.dbHelper.deleteKey(((Items)RecycleBinActivity.this.keyItems.get(j)).getItemId());
                j++;
            }
          }
          else if (RecycleBinActivity.this.groupItems.size() == 0) {}
          for (int i = 0;; i++)
          {
            if (i >= RecycleBinActivity.this.groupItems.size())
            {
              RecycleBinActivity.this.showToast("回收站已清空.");
              PasswordPanacea.setRemoveRestoreCalled(true);
              Intent localIntent = new Intent(RecycleBinActivity.this.getApplicationContext(), RecycleBinActivity.class);
              RecycleBinActivity.this.startActivity(localIntent);
              RecycleBinActivity.this.finish();
              localCustomDialog.dismiss();
              return;
            }
            RecycleBinActivity.this.db = RecycleBinActivity.this.dbHelper.getWritableDatabase();
            RecycleBinActivity.this.db.execSQL("DELETE From GroupMaster WHERE GroupId = '" + ((Items)RecycleBinActivity.this.groupItems.get(i)).getItemId() + "'");
            RecycleBinActivity.this.db.close();
          }
        }
      });
      return;
    }
    new CustomDialogSingleButton(this, "Alert!", "未发现有效项.").show();
  }
  
  public void optionRemove(View paramView)
  {
    if ((this.groupItems.size() != 0) || (this.keyItems.size() != 0))
    {
      if (this.KeyTabOn.booleanValue())
      {
        if (PasswordPanacea.getSelectedItems().size() == 0) {
        	new CustomDialogSingleButton(this, "Invalid!", "请选择密码.").show();
        }else{
	        final CustomDialog localCustomDialog2 = new CustomDialog(this, "Alert!", "确定要清空回收站中的密码吗？");
	        localCustomDialog2.show();
	        localCustomDialog2.customAlertBoxNegitiveButton.setOnClickListener(new View.OnClickListener()
	        {
	          public void onClick(View paramAnonymousView)
	          {
	            localCustomDialog2.dismiss();
	          }
	        });
	        localCustomDialog2.customAlertBoxPositiveButton.setOnClickListener(new View.OnClickListener()
	        {
	          public void onClick(View paramAnonymousView)
	          {
	            for (int i = 0;; i++)
	            {
	              if (i >= PasswordPanacea.getSelectedItems().size())
	              {
	                RecycleBinActivity.this.showToast("密码删除成功.");
	                PasswordPanacea.setGroupTabON(false);
	                PasswordPanacea.setRemoveRestoreCalled(true);
	                Intent localIntent = new Intent(RecycleBinActivity.this.getApplicationContext(), RecycleBinActivity.class);
	                localIntent.addFlags(65536);
	                RecycleBinActivity.this.startActivity(localIntent);
	                localCustomDialog2.dismiss();
	                return;
	              }
	              RecycleBinActivity.this.dbHelper.deleteKey(((Items)PasswordPanacea.getSelectedItems().get(i)).getItemId());
	            }
	          }
	        });
        }
      }
      
    if (this.groupTabOn.booleanValue())
    {
      if (PasswordPanacea.getSelectedGroups().size() == 0) {
    	  new CustomDialogSingleButton(this, "Invalid!", "请先选择分组.").show();
      }else{
          final CustomDialog localCustomDialog1 = new CustomDialog(this, "Alert!", "确定清空回收站中的分组吗？");
          localCustomDialog1.show();
          localCustomDialog1.customAlertBoxNegitiveButton.setOnClickListener(new View.OnClickListener()
          {
            public void onClick(View paramAnonymousView)
            {
              localCustomDialog1.dismiss();
            }
          });
          localCustomDialog1.customAlertBoxPositiveButton.setOnClickListener(new View.OnClickListener()
          {
            public void onClick(View paramAnonymousView)
            {
              for(int i = 0;; i++){
	              if (i >= PasswordPanacea.getSelectedGroups().size())
	              {
	                RecycleBinActivity.this.showToast("分组删除成功.");
	                PasswordPanacea.setGroupTabON(true);
	                PasswordPanacea.setRemoveRestoreCalled(true);
	                Intent localIntent = new Intent(RecycleBinActivity.this.getApplicationContext(), RecycleBinActivity.class);
	                localIntent.addFlags(65536);
	                RecycleBinActivity.this.startActivity(localIntent);
	                localCustomDialog1.dismiss();
	                return;
	              }
	              RecycleBinActivity.this.keyId = RecycleBinActivity.this.dbHelper.getKeyIdByGroupId(((Items)PasswordPanacea.getSelectedGroups().get(i)).getItemId());
	              for (int j = 0;j < RecycleBinActivity.this.keyId.size(); j++)
	              {
	                RecycleBinActivity.this.dbHelper.deleteKey(((Integer)RecycleBinActivity.this.keyId.get(j)).intValue());
	              }
	              RecycleBinActivity.this.dbHelper.deleteGroup(((Items)PasswordPanacea.getSelectedGroups().get(i)).getItemId());
                  i++;
              }
            }
          });
      	}
      }
    }else{
    	new CustomDialogSingleButton(this, "Alert!", "未发现有效项.").show();
    }
  }
  
  public void optionRestore(View paramView)
  {
    if ((this.groupItems.size() != 0) || (this.keyItems.size() != 0))
    {
      if (this.KeyTabOn.booleanValue())
      {
        if (PasswordPanacea.getSelectedItems().size() == 0) {
        	new CustomDialogSingleButton(this, "Invalid!", "请先选择没密码.").show();
        }else{
        	for(int j =0 ;j<PasswordPanacea.getSelectedItems().size();j++){
        		int k = this.dbHelper.getGroupIdByKeyId(((Items)PasswordPanacea.getSelectedItems().get(j)).getItemId());
                this.dbHelper.getGroupRestoreIndividually(k);
                this.dbHelper.getKeyRestore(((Items)PasswordPanacea.getSelectedItems().get(j)).getItemId());                
        	}
			 showToast("密码恢复成功.");
		     PasswordPanacea.setGroupTabON(false);
		     PasswordPanacea.setRemoveRestoreCalled(true);
		     Intent localIntent2 = new Intent(getApplicationContext(), RecycleBinActivity.class);
		     localIntent2.addFlags(65536);
		     startActivity(localIntent2);
		     return;
        }
      }
      else
      {
        if (this.groupTabOn.booleanValue()) {
          if (PasswordPanacea.getSelectedGroups().size() == 0) {
        	  new CustomDialogSingleButton(this, "Invalid!", "请先选择分组.").show();
          }else{
        	  for (int i = 0;; i++)
              {
                if (i >= PasswordPanacea.getSelectedGroups().size())
                {
                  showToast("分组恢复成功.");
                  PasswordPanacea.setGroupTabON(true);
                  PasswordPanacea.setRemoveRestoreCalled(true);
                  Intent localIntent1 = new Intent(getApplicationContext(), RecycleBinActivity.class);
                  localIntent1.addFlags(65536);
                  startActivity(localIntent1);
                  return;
                }else{                	
                    this.dbHelper.getGroupRestore(((Items)PasswordPanacea.getSelectedGroups().get(i)).getItemId());
                    
                }
              }
          }
        }
      }
    }else
    	new CustomDialogSingleButton(this, "Alert!", "未发现有效项.").show();
  }
  
  public void optionSelect(View paramView)
  {
    this.img_check.setImageResource(2130837669);
    this.txt_check.setText("全选");
    this.selectAll = Boolean.valueOf(false);
    this.flipper.setInAnimation(inFromRightAnimation());
    this.flipper.setOutAnimation(outToLeftAnimation());
    this.flipper.showNext();
    this.ischeckBoxVisible = true;
    this.trashKeyArrayAdapter = new TrashKeyArrayAdapter(this, this.keyItems, this.listViewKey, Boolean.valueOf(true), Boolean.valueOf(false), Boolean.valueOf(false));
    this.listViewKey.setAdapter(this.trashKeyArrayAdapter);
    this.trashGroupArrayAdapter = new TrashGroupArrayAdapter(this, this.groupItems, this.listViewGroup, Boolean.valueOf(true), Boolean.valueOf(false), Boolean.valueOf(false));
    this.listViewGroup.setAdapter(this.trashGroupArrayAdapter);
  }
  
  public void optionSelectAll(View paramView)
  {
    if ((this.groupItems.size() != 0) || (this.keyItems.size() != 0))
    {
      if (!this.selectAll.booleanValue())
      {
        this.img_check.setImageResource(2130837665);
        this.txt_check.setText("全不选");
        this.trashKeyArrayAdapter = new TrashKeyArrayAdapter(this, this.keyItems, this.listViewKey, Boolean.valueOf(true), Boolean.valueOf(true), Boolean.valueOf(false));
        this.listViewKey.setAdapter(this.trashKeyArrayAdapter);
        this.trashGroupArrayAdapter = new TrashGroupArrayAdapter(this, this.groupItems, this.listViewGroup, Boolean.valueOf(true), Boolean.valueOf(true), Boolean.valueOf(false));
        this.listViewGroup.setAdapter(this.trashGroupArrayAdapter);
        this.selectAll = Boolean.valueOf(true);
        return;
      }
      this.img_check.setImageResource(2130837669);
      this.txt_check.setText("全选");
      this.trashKeyArrayAdapter = new TrashKeyArrayAdapter(this, this.keyItems, this.listViewKey, Boolean.valueOf(true), Boolean.valueOf(false), Boolean.valueOf(false));
      this.listViewKey.setAdapter(this.trashKeyArrayAdapter);
      this.trashGroupArrayAdapter = new TrashGroupArrayAdapter(this, this.groupItems, this.listViewGroup, Boolean.valueOf(true), Boolean.valueOf(false), Boolean.valueOf(false));
      this.listViewGroup.setAdapter(this.trashGroupArrayAdapter);
      this.selectAll = Boolean.valueOf(false);
      return;
    }
    new CustomDialogSingleButton(this, "Alert!", "未发现有效项.").show();
  }
  
  public void showToast(String paramString)
  {
    Typeface localTypeface = Typeface.createFromAsset(getAssets(), "fonts/galette.otf");
    View localView = getLayoutInflater().inflate(2130903048, (ViewGroup)findViewById(2131296314));
    TextView localTextView = (TextView)localView.findViewById(2131296315);
    localTextView.setTypeface(localTypeface);
    localTextView.setText(paramString);
    Toast localToast = new Toast(getApplicationContext());
    localToast.setDuration(0);
    localToast.setView(localView);
    localToast.show();
  }
}


/* Location:           D:\Apkdb\Craining\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     rdi.mobapp.passwordpanacea.activity.RecycleBinActivity
 * JD-Core Version:    0.7.0.1
 */