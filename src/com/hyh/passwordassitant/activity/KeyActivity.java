package com.hyh.passwordassitant.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
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
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import rdi.mobapp.passwordpanacea.CryptoHelper;
import rdi.mobapp.passwordpanacea.CryptoHelperException;
import rdi.mobapp.passwordpanacea.adapter.GroupArrayAdapter;
import rdi.mobapp.passwordpanacea.adapter.KeyArrayAdapter;
import rdi.mobapp.passwordpanacea.application.PasswordPanacea;
import rdi.mobapp.passwordpanacea.bean.FieldValue;
import rdi.mobapp.passwordpanacea.bean.Group;
import rdi.mobapp.passwordpanacea.bean.Items;
import rdi.mobapp.passwordpanacea.bean.KeyDetailByKeyId;
import rdi.mobapp.passwordpanacea.databasehelper.DBHelper;
import rdi.mobapp.passwordpanacea.utility.CustomDialogSingleButton;
import rdi.mobapp.passwordpanacea.utility.MyLinearLayoutDialog;

public class KeyActivity
  extends BaseActivity
{
  private ArrayList<Integer> allKeyDetail;
  private Boolean arrowClicked = Boolean.valueOf(false);
  private ImageButton btnKey;
  private CryptoHelper cryptoHelper;
  private DBHelper dbHelper;
  private ArrayList<FieldValue> fieldValue;
  private TextWatcher filterTextWatcher = new TextWatcher()
  {
    public void afterTextChanged(Editable paramAnonymousEditable) {}
    
    public void beforeTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3) {}
    
    public void onTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3)
    {
      KeyActivity.this.keyArrayAdapter.getFilter().filter(paramAnonymousCharSequence);
    }
  };
  private ViewFlipper flipper;
  private LinearLayout footerKey;
  private ImageButton headerIcon;
  private TextView headerText;
  private ImageButton imgToolViewAll;
  private ImageView imgToolsSelect;
  private Boolean isHeaderBackEnable;
  private boolean ischeckBoxVisible = false;
  private KeyArrayAdapter keyArrayAdapter;
  private ArrayList<KeyDetailByKeyId> keyDetail;
  private List<Items> keyItems;
  private RelativeLayout keySearch;
  private TextView listEmpty;
  private ListView listView;
  private MyLinearLayoutDialog myLinearLayout;
  private ImageButton optnMore;
  private EditText searchBox;
  private Boolean selectAll = Boolean.valueOf(false);
  private int templateId;
  private Typeface tf = null;
  private LinearLayout toolRemove;
  private LinearLayout toolSelectAll;
  private LinearLayout toolShare;
  private LinearLayout toolTabContainer;
  private LinearLayout toolViewAll;
  private TextView txtKey;
  private TextView txtToolAdd;
  private TextView txtToolCheck;
  private TextView txtToolRecycleBin;
  private TextView txtToolRemove;
  private TextView txtToolSelectAll;
  private TextView txtToolShare;
  private TextView txtToolViewAll;
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
  
  private void share(String paramString1, String paramString2)
  {
    Intent localIntent = new Intent("android.intent.action.SEND");
    localIntent.setType("text/plain");
    localIntent.putExtra("android.intent.extra.TEXT", paramString1);
    startActivity(Intent.createChooser(localIntent, paramString2));
  }
  
  public void addKey(View paramView)
  {
    PasswordPanacea.setAddKeyText(null);
    PasswordPanacea.setFirstCalled(true);
    PasswordPanacea.setTemplateSpinnerVal("默认模板");
    Intent localIntent = new Intent(getApplicationContext(), AddKeyActivity.class);
    localIntent.addFlags(65536);
    startActivity(localIntent);
    finish();
  }
  
  public void backPressed()
  {
    Intent localIntent = new Intent(getApplicationContext(), GroupActivity.class);
    localIntent.addFlags(65536);
    startActivity(localIntent);
    finish();
  }
  
  public void getKeyItem()
  {
    this.keyItems = new ArrayList();
    if ((PasswordPanacea.getFooterKeyPressed()) || (PasswordPanacea.getViewAllKeyPressed()) || (PasswordPanacea.getGroupMasterId() == 0))
    {
      this.isHeaderBackEnable = Boolean.valueOf(false);
      this.headerIcon.setImageResource(2130837638);
      this.headerText.setText("Key");
      this.keyDetail = this.dbHelper.getKeyDetail();
      PasswordPanacea.setGroupMasterId(0);
      PasswordPanacea.setGroupTitle("General");
      PasswordPanacea.setViewAllKeyPressed(false);
      Log.e("keyDetail size1",""+this.keyDetail.size());
    }else{
    	this.isHeaderBackEnable = Boolean.valueOf(true);
        this.headerIcon.setImageResource(2130837510);
        this.headerText.setText(PasswordPanacea.getGroupTitle());
        this.keyDetail = this.dbHelper.getKeyDetailById(PasswordPanacea.getGroupMasterId());
        Log.e("keyDetail size2",PasswordPanacea.getGroupMasterId()+" "+this.keyDetail.size());
    }
    Log.e("keyDetail size",""+this.keyDetail.size());
    
    for (int i = 0;; i++)
    {
      if (i >= this.keyDetail.size())
      {
        return;
      }
      this.keyItems.add(new Items(((KeyDetailByKeyId)this.keyDetail.get(i)).getKeyId(), ((KeyDetailByKeyId)this.keyDetail.get(i)).getKeyTitle()));
    }
  }
  
  public void headerBackPressed(View paramView)
  {
    if (this.isHeaderBackEnable.booleanValue()) {
      backPressed();
    }
  }
  
  public void headerMore(View paramView)
  {
    if (!this.arrowClicked.booleanValue())
    {
      this.toolTabContainer.setVisibility(0);
      this.optnMore.setSelected(true);
      this.arrowClicked = Boolean.valueOf(true);
      PasswordPanacea.setIsKeyHeaderTabVisible(true);
    }else{
    	this.toolTabContainer.setVisibility(8);
        this.optnMore.setSelected(false);
        this.arrowClicked = Boolean.valueOf(false);
        PasswordPanacea.setIsKeyHeaderTabVisible(false);
    }
    if (this.ischeckBoxVisible)
    {
      this.flipper.setInAnimation(inFromLeftAnimation());
      this.flipper.setOutAnimation(outToRightAnimation());
      this.flipper.showNext();
      this.ischeckBoxVisible = false;
      this.keyArrayAdapter = new KeyArrayAdapter(this, this.keyItems, this.listView, Boolean.valueOf(false), Boolean.valueOf(false), Boolean.valueOf(false));
      this.listView.setAdapter(this.keyArrayAdapter);
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
        PasswordPanacea.setFirstCalled(true);
        PasswordPanacea.setTemplateSpinnerVal("默认模板");
        Intent localIntent = new Intent(KeyActivity.this.getApplicationContext(), AddKeyActivity.class);
        PasswordPanacea.setAddKeyText(KeyActivity.this.searchBox.getText().toString());
        localIntent.addFlags(65536);
        KeyActivity.this.startActivity(localIntent);
        KeyActivity.this.finish();
      }
    });
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle, 2130903058, "Key");
    this.myLinearLayout = ((MyLinearLayoutDialog)findViewById(2131296275));
    this.myLinearLayout.setMyListActivity(this);
    this.headerIcon = ((ImageButton)findViewById(2131296440));
    this.headerText = ((TextView)findViewById(2131296441));
    this.keySearch = ((RelativeLayout)findViewById(2131296469));
    this.toolViewAll = ((LinearLayout)findViewById(2131296466));
    this.toolTabContainer = ((LinearLayout)findViewById(2131296409));
    this.toolSelectAll = ((LinearLayout)findViewById(2131296424));
    this.toolRemove = ((LinearLayout)findViewById(2131296427));
    this.toolShare = ((LinearLayout)findViewById(2131296430));
    this.toolSelectAll.setEnabled(true);
    this.toolRemove.setEnabled(true);
    this.toolShare.setEnabled(true);
    this.flipper = ((ViewFlipper)findViewById(2131296391));
    this.tf = Typeface.createFromAsset(getAssets(), "fonts/galette.otf");
    this.txtToolAdd = ((TextView)findViewById(2131296414));
    this.txtToolAdd.setTypeface(this.tf);
    this.txtToolViewAll = ((TextView)findViewById(2131296468));
    this.txtToolViewAll.setTypeface(this.tf);
    this.imgToolViewAll = ((ImageButton)findViewById(2131296467));
    this.txtToolCheck = ((TextView)findViewById(2131296417));
    this.txtToolCheck.setTypeface(this.tf);
    this.txtToolRecycleBin = ((TextView)findViewById(2131296420));
    this.txtToolRecycleBin.setTypeface(this.tf);
    this.txtToolSelectAll = ((TextView)findViewById(2131296426));
    this.txtToolSelectAll.setTypeface(this.tf);
    this.imgToolsSelect = ((ImageView)findViewById(2131296425));
    this.txtToolRemove = ((TextView)findViewById(2131296429));
    this.txtToolRemove.setTypeface(this.tf);
    this.txtToolShare = ((TextView)findViewById(2131296432));
    this.txtToolShare.setTypeface(this.tf);
    this.optnMore = ((ImageButton)findViewById(2131296442));
    this.searchBox = ((EditText)findViewById(2131296470));
    this.searchBox.setTypeface(this.tf);
    this.listView = ((ListView)findViewById(2131296437));
    View localView = ((LayoutInflater)getSystemService("layout_inflater")).inflate(2130903059, null, false);
    this.listView.addFooterView(localView, null, false);
    this.footerKey = ((LinearLayout)findViewById(2131296373));
    this.footerKey.setBackgroundResource(2130837537);
    this.footerKey.setEnabled(false);
    this.keyDetail = new ArrayList();
    this.allKeyDetail = new ArrayList();
    this.dbHelper = new DBHelper(this);
    this.fieldValue = new ArrayList();
    this.btnKey = ((ImageButton)findViewById(2131296374));
    this.btnKey.setImageResource(2130837611);
    this.txtKey = ((TextView)findViewById(2131296375));
    this.txtKey.setTextColor(Color.parseColor("#000000"));
    this.txtKey.setTypeface(this.tf);
    this.viewLeftShadow = findViewById(2131296371);
    this.viewLeftShadow.setVisibility(0);
    this.viewLeftShadow.setBackgroundResource(2130837577);
    this.viewRightShadow = findViewById(2131296376);
    this.viewRightShadow.setVisibility(0);
    this.viewRightShadow.setBackgroundResource(2130837578);
    PasswordPanacea.setKeyActivity(null);
    this.searchBox.addTextChangedListener(this.filterTextWatcher);
    getKeyItem();
    Collections.sort(this.keyItems, new  Comparator<Items>() { 
		@Override
		public int compare(Items item1, Items item2) {
			// TODO Auto-generated method stub
			return item1.getItemTitle().compareTo(item2.getItemTitle());
		}  
    });  
    Log.e("ketitem size",""+this.keyItems.size());
    this.keyArrayAdapter = new KeyArrayAdapter(this, this.keyItems, this.listView, Boolean.valueOf(false), Boolean.valueOf(false), Boolean.valueOf(true));
    this.listView.setAdapter(this.keyArrayAdapter);
    
    
    this.allKeyDetail = this.dbHelper.getKeyIdList(0);
    if(this.allKeyDetail.size() == this.keyItems.size()){
	    this.imgToolViewAll.setImageResource(0x7f0200a7);
	    this.txtToolViewAll.setTextColor(Color.parseColor("#000000"));
	    this.toolViewAll.setEnabled(true);
    }else{
    	this.imgToolViewAll.setImageResource(0x7f0200a8);
	    this.txtToolViewAll.setTextColor(Color.parseColor("#999999"));
	    this.toolViewAll.setEnabled(false);
    }
    
    this.toolViewAll.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			
		}
	});
    
    if(PasswordPanacea.getKeySearchEnable()){    	
    	//if(this.listView.getCount()<=0)    		
    	this.keySearch.setVisibility(View.VISIBLE);
    }else{
    	this.keySearch.setVisibility(View.GONE);
    }
    
  }
  
  protected void onDestroy()
  {
    super.onDestroy();
    this.searchBox.removeTextChangedListener(this.filterTextWatcher);
  }
  
  protected void onStart()
  {
    super.onStart();
    if (PasswordPanacea.getIsKeyHeaderTabVisible())
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
    this.getKeyItem();
    this.keyArrayAdapter = new KeyArrayAdapter(this, this.keyItems, this.listView, Boolean.valueOf(false), Boolean.valueOf(false), Boolean.valueOf(true));
    this.listView.setAdapter(this.keyArrayAdapter);
    this.imgToolsSelect.setImageResource(0x7f0200a5);
    this.txtToolSelectAll.setText("全选");
    this.selectAll = Boolean.valueOf(false);
  }
  
  public void optionRemove(View paramView)
  {
    //if (PasswordPanacea.getSelectedItems().size() != this.groupItems.size())
    //{
      this.imgToolsSelect.setImageResource(0x7f0200a5);
      this.txtToolSelectAll.setText("全选");
      this.selectAll = Boolean.valueOf(false);
    //}
    if (this.keyItems.size() == 0)
    {
      new CustomDialogSingleButton(this, "Alert!", "未选中任何密码.").show();
      return;
    }else{
    	if(PasswordPanacea.getSelectedItems().size() == 0){
    		new CustomDialogSingleButton(this, "Invalid!", "请先选择密码.").show();
    	    return;
    	}
    }
    
    Log.e("dfds",PasswordPanacea.getSelectedItems().size()+"");
    for (int i = 0;; i++)
    {
      if (i >= PasswordPanacea.getSelectedItems().size()) {
    	  Intent localIntent = new Intent(getApplicationContext(), KeyActivity.class);
          localIntent.addFlags(65536);
          startActivity(localIntent);
          return;
      }
      this.dbHelper.getKeyInTrash(((Items)PasswordPanacea.getSelectedItems().get(i)).getItemId());
		
    }
  }
  

  public void optionSelect(View paramView)
  {
    this.flipper.setInAnimation(inFromRightAnimation());
    this.flipper.setOutAnimation(outToLeftAnimation());
    this.flipper.showNext();
    this.ischeckBoxVisible = true;
    this.searchBox.setText("");
    this.getKeyItem();
    this.keyArrayAdapter = new KeyArrayAdapter(this, this.keyItems, this.listView, Boolean.valueOf(true), Boolean.valueOf(false), Boolean.valueOf(false));
    this.listView.setAdapter(this.keyArrayAdapter);
  }
  
  public void optionSelectAll(View paramView)
  {
	  Log.e("fds","fdsfsd"+""+this.selectAll.booleanValue());
	  if(this.keyItems.size() == 0){
		  new CustomDialogSingleButton(this, "Alert!", "未选中任何密码.").show();
	      return;
	  }
	  
	  
    if (!this.selectAll.booleanValue())
    {
      this.imgToolsSelect.setImageResource(0x7f0200a1);
      this.txtToolSelectAll.setText("None");
      this.keyArrayAdapter = new KeyArrayAdapter(this, this.keyItems, this.listView, Boolean.valueOf(true), Boolean.valueOf(true), Boolean.valueOf(true));
      this.listView.setAdapter(this.keyArrayAdapter);
      this.selectAll = Boolean.valueOf(true);
      return;
    }
    this.imgToolsSelect.setImageResource(0x7f0200a5);
    this.txtToolSelectAll.setText("All");
    this.keyArrayAdapter = new KeyArrayAdapter(this, this.keyItems, this.listView, Boolean.valueOf(true), Boolean.valueOf(false), Boolean.valueOf(true));
    this.listView.setAdapter(this.keyArrayAdapter);
    this.selectAll = Boolean.valueOf(false);
  }
  
  public void optionShare(View paramView)
  {
    if (this.keyItems.size() == 0)
    {
      new CustomDialogSingleButton(this, "alert!", "未选中任何密码.").show();
      return;
    }
    if(PasswordPanacea.getSelectedItems().size() == 0){
  		new CustomDialogSingleButton(this, "Invalid!", "请先选择密码.").show();
  	    return;
	  }
    
    StringBuilder localStringBuilder = new StringBuilder();
    this.fieldValue = new ArrayList();
    
    
    for(int j=0;j<PasswordPanacea.getSelectedItems().size();j++){
    	localStringBuilder.append("\n 密码");
        this.templateId = this.dbHelper.getTemplateIdRefByKeyId(((Integer)PasswordPanacea.getSelectedItems().get(j).getItemId()));
        this.fieldValue = this.dbHelper.getFieldValue(this.templateId, ((Integer)PasswordPanacea.getSelectedItems().get(j).getItemId()));
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
        
        if (j != PasswordPanacea.getSelectedItems().size()) {
            localStringBuilder.append("\n");
        }
        if (j == -1 + PasswordPanacea.getSelectedItems().size()) {
            localStringBuilder.append("\n\n-\n万能密码助手，您的密码管家");
        }
    }
    share(localStringBuilder.toString(), "Share");
    return;    
  }
  
  public void optionRecyclebin(View paramView)
  {
    Intent localIntent = new Intent(getApplicationContext(), RecycleBinActivity.class);
    PasswordPanacea.setTrashActivity("KeyActivity");
    PasswordPanacea.setGroupTabON(false);
    localIntent.addFlags(65536);
    startActivity(localIntent);
    finish();
  }
  
  
}