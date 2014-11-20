package rdi.mobapp.passwordpanacea.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.text.ClipboardManager;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.URLUtil;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.hyh.passwordassitant.activity.EditKeyActivity;
import com.hyh.passwordassitant.activity.WebViewActivity;

import rdi.mobapp.passwordpanacea.CryptoHelper;
import rdi.mobapp.passwordpanacea.CryptoHelperException;
import rdi.mobapp.passwordpanacea.application.PasswordPanacea;
import rdi.mobapp.passwordpanacea.bean.FieldValue;
import rdi.mobapp.passwordpanacea.bean.Group;
import rdi.mobapp.passwordpanacea.bean.Items;
import rdi.mobapp.passwordpanacea.bean.KeyDetailByKeyId;
import rdi.mobapp.passwordpanacea.databasehelper.DBHelper;
import rdi.mobapp.passwordpanacea.utility.CustomDialog;
import rdi.mobapp.passwordpanacea.utility.CustomDialogSingleButton;
import rdi.mobapp.passwordpanacea.utility.CustomQuickView;

public class KeyArrayAdapter
  extends ArrayAdapter<Items>
  implements Filterable
{
  private KeyArrayAdapter KeyArrayAdapter;
  private final List<Items> allData;
  private CheckBox checkListItem;
  private ClipboardManager clipboard;
  private final Context context;
  private CryptoHelper cryptoHelper;
  private DBHelper dbHelper;
  private Animation fadeIn;
  private Animation fadeOut;
  private ArrayList<FieldValue> fieldValue;
  private Filter filter;
  private List<Items> filtered;
  private Boolean firstCalled;
  private int groupId;
  private ImageView imageView;
  private ImageButton imgQuickViewKey;
  private Boolean isCheckAll;
  private Boolean isCheckBoxVisible;
  private ArrayList<Boolean> itemChecked = new ArrayList();
  private ArrayList<Boolean> itemSelected = new ArrayList();
  private ArrayList<Integer> itemVisible = new ArrayList();
  private ArrayList<KeyDetailByKeyId> keyDetail;
  private LinearLayout keyTool;
  private LinearLayout keyToolDelete;
  private LinearLayout keyToolEdit;
  private LinearLayout keyToolPassword;
  private LinearLayout keyToolUrl;
  private LinearLayout keyToolUserName;
  private List<Items> list = new ArrayList();
  private ListView listView;
  private StringBuilder message;
  private String password;
  private Boolean selectAllChecked = Boolean.valueOf(false);
  private Boolean selectNoneChecked = Boolean.valueOf(false);
  private int templateId;
  private TextView textView;
  private Typeface tf;
  private TextView txtKeytoolDelete;
  private TextView txtKeytoolEdit;
  private TextView txtKeytoolPassword;
  private TextView txtKeytoolUrl;
  private TextView txtKeytoolUsename;
  private String url;
  private String userName;
  private List<Items> values;
  private TextView viewA;
  private ViewFlipper viewFlipper;
  
  public KeyArrayAdapter(Context paramContext, List<Items> paramList, ListView paramListView, Boolean paramBoolean1, Boolean paramBoolean2, Boolean paramBoolean3)
  {
    super(paramContext, 2130903057, paramList);
    this.context = paramContext;
    this.allData = new ArrayList(paramList.size());
    Iterator localIterator = paramList.iterator();
    while (localIterator.hasNext())
    {
    	Items localItems = (Items)localIterator.next();
        this.allData.add(localItems);
    }
    this.filtered = paramList;
    this.values = this.filtered;
    this.filter = new ElementsNameFilter();
    this.listView = paramListView;
    this.isCheckBoxVisible = paramBoolean1;
    this.isCheckAll = paramBoolean2;
    this.firstCalled = paramBoolean3;
    for (int i = 0;i < getCount(); i++)
    {
      this.itemChecked.add(i, Boolean.valueOf(false));
      this.itemVisible.add(i, Integer.valueOf(8));
      this.itemSelected.add(i, Boolean.valueOf(false));
      this.list.add(new Items(((Items)paramList.get(i)).getItemId(), ((Items)paramList.get(i)).getItemTitle()));
    }
  }
  
  private void share(String paramString1, String paramString2)
  {
    Intent localIntent = new Intent("android.intent.action.SEND");
    localIntent.setType("text/plain");
    localIntent.putExtra("android.intent.extra.TEXT", paramString1);
    this.context.startActivity(Intent.createChooser(localIntent, paramString2));
  }
  
  private void showToast(String paramString)
  {
    View localView = ((LayoutInflater)this.context.getSystemService("layout_inflater")).inflate(2130903048, null);
    TextView localTextView = (TextView)localView.findViewById(2131296315);
    localTextView.setTypeface(this.tf);
    localTextView.setText(paramString);
    Toast localToast = new Toast(this.context);
    localToast.setDuration(0);
    localToast.setView(localView);
    localToast.show();
  }
  
  public void animateVisibilityGone(View paramView)
  {
    paramView.setVisibility(4);
    paramView.startAnimation(this.fadeOut);
    paramView.setVisibility(8);
  }
  
  public void animateVisibilityOn(View paramView)
  {
    paramView.setVisibility(4);
    paramView.startAnimation(this.fadeIn);
    paramView.setVisibility(0);
  }
  
  protected void disableOnclick(boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3)
  {
    if (paramBoolean1)
    {
      this.keyToolUserName.setEnabled(false);
      this.keyToolPassword.setEnabled(true);
    }
    if (paramBoolean2)
    {
      this.keyToolUserName.setEnabled(true);
      this.keyToolPassword.setEnabled(false);
    }
    if (paramBoolean3)
    {
      this.keyToolUserName.setEnabled(true);
      this.keyToolPassword.setEnabled(true);
    }
  }
  
  public Filter getFilter()
  {
    if (this.filter == null) {
      this.filter = new ElementsNameFilter();
    }
    return this.filter;
  }
  
  public View getView(final int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    if (paramView == null) {
      paramView = ((LayoutInflater)this.context.getSystemService("layout_inflater")).inflate(2130903057, null);
    }
    this.tf = Typeface.createFromAsset(this.context.getAssets(), "fonts/galette.otf");
    this.fadeIn = AnimationUtils.loadAnimation(this.context, 2130968582);
    this.fadeOut = AnimationUtils.loadAnimation(this.context, 2130968591);
    this.textView = ((TextView)paramView.findViewById(2131296454));
    this.textView.setFocusable(false);
    this.imageView = ((ImageView)paramView.findViewById(2131296286));
    this.checkListItem = ((CheckBox)paramView.findViewById(2131296453));
    this.textView.setTypeface(this.tf);
    this.textView.setText(((Items)this.values.get(paramInt)).getItemTitle());
    this.keyTool = ((LinearLayout)paramView.findViewById(2131296363));
    this.imgQuickViewKey = ((ImageButton)paramView.findViewById(2131296456));
    this.keyToolUserName = ((LinearLayout)paramView.findViewById(2131296457));
    this.keyToolPassword = ((LinearLayout)paramView.findViewById(2131296460));
    this.keyToolUrl = ((LinearLayout)paramView.findViewById(2131296463));
    this.keyToolEdit = ((LinearLayout)paramView.findViewById(2131296402));
    this.keyToolDelete = ((LinearLayout)paramView.findViewById(2131296405));
    this.txtKeytoolUsename = ((TextView)paramView.findViewById(2131296459));
    this.txtKeytoolUsename.setTypeface(this.tf);
    this.txtKeytoolPassword = ((TextView)paramView.findViewById(2131296462));
    this.txtKeytoolPassword.setTypeface(this.tf);
    this.txtKeytoolUrl = ((TextView)paramView.findViewById(2131296465));
    this.txtKeytoolUrl.setTypeface(this.tf);
    this.txtKeytoolEdit = ((TextView)paramView.findViewById(2131296404));
    this.txtKeytoolEdit.setTypeface(this.tf);
    this.txtKeytoolDelete = ((TextView)paramView.findViewById(2131296407));
    this.txtKeytoolDelete.setTypeface(this.tf);
    this.clipboard = ((ClipboardManager)this.context.getSystemService("clipboard"));
    this.viewFlipper = ((ViewFlipper)paramView.findViewById(2131296391));
    this.dbHelper = new DBHelper(this.context);
    if (this.isCheckBoxVisible.booleanValue())
    {
      this.imageView.setVisibility(8);
      this.checkListItem.setVisibility(0);
    }
    int i;
    Log.e("bool",""+this.isCheckAll.booleanValue()+" "+this.selectAllChecked.booleanValue()+" "+this.selectNoneChecked.booleanValue());
    if (this.isCheckAll.booleanValue()) {
      //if (this.selectAllChecked.booleanValue())
      //{
        i = 0;
        while (i < getCount()) {
        	this.checkListItem.setChecked(true);
            this.itemChecked.set(i, Boolean.valueOf(true));
            i++;
        }
        PasswordPanacea.setSelectedItems(this.list);
        Log.e("selectNoneChecked",PasswordPanacea.getSelectedItems().size()+"");
      //}
      //else if (this.selectNoneChecked.booleanValue()) {
    	  
      //}
    }else{
    	this.checkListItem.setChecked(false);
        this.itemChecked.set(paramInt, Boolean.valueOf(false));
        this.list.clear();
        PasswordPanacea.setSelectedItems(this.list);
        Log.e("selectAllChecked",PasswordPanacea.getSelectedItems().size()+"");
    }
    if (!this.isCheckBoxVisible.booleanValue()) {
    	this.textView.setSelected(((Boolean)this.itemSelected.get(paramInt)).booleanValue());
    }else
    	this.textView.setSelected(((Boolean)this.itemChecked.get(paramInt)).booleanValue());
    
    
      this.checkListItem.setChecked(((Boolean)this.itemChecked.get(paramInt)).booleanValue());
      this.keyTool.setVisibility(((Integer)this.itemVisible.get(paramInt)).intValue());
      this.imgQuickViewKey.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          final ImageButton localImageButton = (ImageButton)paramAnonymousView.findViewById(2131296456);
          localImageButton.setSelected(true);
          KeyArrayAdapter.this.fieldValue = new ArrayList();
          KeyArrayAdapter.this.message = new StringBuilder();
          PasswordPanacea.setKeyMasterId(((Items)KeyArrayAdapter.this.values.get(paramInt)).getItemId());
          PasswordPanacea.setKeyTitle(((Items)KeyArrayAdapter.this.values.get(paramInt)).getItemTitle());
          int i = KeyArrayAdapter.this.dbHelper.getGroupIdByKeyId(PasswordPanacea.getKeyMasterId());
          new ArrayList();
          ArrayList localArrayList = KeyArrayAdapter.this.dbHelper.getGroupDetail(i);
          KeyArrayAdapter.this.message.append("\n分组: " + ((Group)localArrayList.get(0)).getGroupTitle() + "\n");
          KeyArrayAdapter.this.message.append("\n描述: ");
          KeyArrayAdapter.this.templateId = KeyArrayAdapter.this.dbHelper.getTemplateIdRefByKeyId(((Items)KeyArrayAdapter.this.values.get(paramInt)).getItemId());
          KeyArrayAdapter.this.fieldValue = KeyArrayAdapter.this.dbHelper.getFieldValue(KeyArrayAdapter.this.templateId, ((Items)KeyArrayAdapter.this.values.get(paramInt)).getItemId());
          
          for (int j=0;;j++){
	          if (j >= KeyArrayAdapter.this.fieldValue.size())
	          {
	            final CustomQuickView localCustomQuickView = new CustomQuickView(KeyArrayAdapter.this.context, ((FieldValue)KeyArrayAdapter.this.fieldValue.get(0)).getCustomKeyFieldValue(), KeyArrayAdapter.this.message.toString());
	            localCustomQuickView.show();
	            localCustomQuickView.quickviewButton.setOnClickListener(new View.OnClickListener()
	            {
	              public void onClick(View paramAnonymous2View)
	              {
	                KeyArrayAdapter.this.message.append("\n\n-\n万能密码助手，您的密码管家");
	                KeyArrayAdapter.this.share(KeyArrayAdapter.this.message.toString(), "Share...");
	                localCustomQuickView.dismiss();
	                localImageButton.setSelected(false);
	              }
	            });
	            localCustomQuickView.customAlertBoxPositiveButton.setOnClickListener(new View.OnClickListener()
	            {
	              public void onClick(View paramAnonymous2View)
	              {
	                localCustomQuickView.dismiss();
	                localImageButton.setSelected(false);
	              }
	            });
	            return;
	          }
	          else if (j == 2)
	          {
	            Object localObject = ((FieldValue)KeyArrayAdapter.this.fieldValue.get(j)).getCustomKeyFieldValue();
	            KeyArrayAdapter.this.cryptoHelper = new CryptoHelper(2);
	            try
	            {
	              KeyArrayAdapter.this.cryptoHelper.setPassword("madhur+sonu");
	              String str = KeyArrayAdapter.this.cryptoHelper.decrypt((String)localObject);
	              localObject = str;
	              KeyArrayAdapter.this.message.append("\n");
	              KeyArrayAdapter.this.message.append("• " + ((FieldValue)KeyArrayAdapter.this.fieldValue.get(j)).getCustomKeyFieldName() + ": " + (String)localObject);
	            
	            }
	            catch (CryptoHelperException localCryptoHelperException)
	            {
	                localCryptoHelperException.printStackTrace();
	                localCryptoHelperException.getMessage();
	            }
	           }
	          else
	          {
	            if ((((FieldValue)KeyArrayAdapter.this.fieldValue.get(j)).getCustomKeyFieldValue() != null) && (!((FieldValue)KeyArrayAdapter.this.fieldValue.get(j)).getCustomKeyFieldValue().equals("")))
	            {
	              KeyArrayAdapter.this.message.append("\n");
	              KeyArrayAdapter.this.message.append("• " + ((FieldValue)KeyArrayAdapter.this.fieldValue.get(j)).getCustomKeyFieldName() + ": " + ((FieldValue)KeyArrayAdapter.this.fieldValue.get(j)).getCustomKeyFieldValue());
	            }
	          }
          }
        }
      });
      this.keyToolEdit.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          PasswordPanacea.setFirstCalled(true);
          PasswordPanacea.setKeyMasterId(((Items)KeyArrayAdapter.this.values.get(paramInt)).getItemId());
          PasswordPanacea.setKeyTitle(((Items)KeyArrayAdapter.this.values.get(paramInt)).getItemTitle());
          KeyArrayAdapter.this.templateId = KeyArrayAdapter.this.dbHelper.getTemplateIdRefByKeyId(((Items)KeyArrayAdapter.this.values.get(paramInt)).getItemId());
          PasswordPanacea.setTemplateSpinnerVal(KeyArrayAdapter.this.dbHelper.getTemplateName(KeyArrayAdapter.this.templateId));
          ((ImageButton)paramAnonymousView.findViewById(2131296403)).setSelected(true);
          PasswordPanacea.setKeyMasterId(((Items)KeyArrayAdapter.this.values.get(paramInt)).getItemId());
          PasswordPanacea.setKeyTitle(((Items)KeyArrayAdapter.this.values.get(paramInt)).getItemTitle());
          Intent localIntent = new Intent(KeyArrayAdapter.this.context, EditKeyActivity.class);
          localIntent.addFlags(65536);
          KeyArrayAdapter.this.context.startActivity(localIntent);
        }
      });
      this.keyToolDelete.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          final ImageButton localImageButton = (ImageButton)paramAnonymousView.findViewById(2131296406);
          localImageButton.setSelected(true);
          PasswordPanacea.setKeyMasterId(((Items)KeyArrayAdapter.this.values.get(paramInt)).getItemId());
          PasswordPanacea.setKeyTitle(((Items)KeyArrayAdapter.this.values.get(paramInt)).getItemTitle());
          final CustomDialog localCustomDialog = new CustomDialog(KeyArrayAdapter.this.context, "Delete?", "确认删除密码 \"" + PasswordPanacea.getKeyTitle() + "\" 吗?");
          localCustomDialog.show(); 
          localCustomDialog.customAlertBoxPositiveButton.setOnClickListener(new View.OnClickListener()
          {
            public void onClick(View paramAnonymous2View)
            {
              KeyArrayAdapter.this.dbHelper.getKeyInTrash(((Items)KeyArrayAdapter.this.values.get(paramInt)).getItemId());
              KeyArrayAdapter.this.values.remove(paramInt);
              KeyArrayAdapter.this.KeyArrayAdapter = new KeyArrayAdapter(KeyArrayAdapter.this.context, KeyArrayAdapter.this.values, KeyArrayAdapter.this.listView, Boolean.valueOf(false), Boolean.valueOf(false), Boolean.valueOf(false));
              KeyArrayAdapter.this.KeyArrayAdapter.notifyDataSetChanged();
              KeyArrayAdapter.this.listView.setAdapter(KeyArrayAdapter.this.KeyArrayAdapter);
              localImageButton.setSelected(false);
              localCustomDialog.dismiss();
            }
          });
          localCustomDialog.customAlertBoxNegitiveButton.setOnClickListener(new View.OnClickListener()
          {
            public void onClick(View paramAnonymous2View)
            {
              localCustomDialog.dismiss();
              localImageButton.setSelected(false);
            }
          });
        }
      });
      this.keyToolUserName.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          if (KeyArrayAdapter.this.userName.length() != 0)
          {
            KeyArrayAdapter.this.clipboard.setText(KeyArrayAdapter.this.userName);
            KeyArrayAdapter.this.showToast("用户名已复制!");
            return;
          }
          new CustomDialogSingleButton(KeyArrayAdapter.this.context, "Alert!", "未发现有效值.").show();
        }
      });
      this.keyToolPassword.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          if (KeyArrayAdapter.this.password.length() != 0)
          {
            KeyArrayAdapter.this.clipboard.setText(KeyArrayAdapter.this.password);
            KeyArrayAdapter.this.showToast("密码已复制!");
            return;
          }
          new CustomDialogSingleButton(KeyArrayAdapter.this.context, "Alert!", "未发现有效值.").show();
        }
      });
      this.keyToolUrl.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          if (!URLUtil.isValidUrl(KeyArrayAdapter.this.url))
          {
            new CustomDialogSingleButton(KeyArrayAdapter.this.context, "Invalid!", "未发现有效连接.").show();
            return;
          }
          if ((KeyArrayAdapter.this.url.length() != 0) && (!KeyArrayAdapter.this.url.equalsIgnoreCase("http://")))
          {
            if (!KeyArrayAdapter.this.url.contains("://")) {
              KeyArrayAdapter.this.url = ("http://" + KeyArrayAdapter.this.url);
            }
            if (URLUtil.isValidUrl(KeyArrayAdapter.this.url))
            {
              KeyArrayAdapter.this.showToast("正在打开链接...");
              Intent localIntent = new Intent(KeyArrayAdapter.this.context, WebViewActivity.class);
              Bundle localBundle = new Bundle();
              localBundle.putString("url", KeyArrayAdapter.this.url);
              localBundle.putString("username", KeyArrayAdapter.this.userName);
              localBundle.putString("password", KeyArrayAdapter.this.password);
              localIntent.putExtras(localBundle);
              localIntent.addFlags(65536);
              KeyArrayAdapter.this.context.startActivity(localIntent);
              return;
            }
            new CustomDialogSingleButton(KeyArrayAdapter.this.context, "Invalid!", "链接错误.").show();
            return;
          }
          new CustomDialogSingleButton(KeyArrayAdapter.this.context, "Alert!", "未发现有效值.").show();
        }
      });
      this.keyToolUrl.setLongClickable(true);
      this.keyToolUrl.setOnLongClickListener(new View.OnLongClickListener()
      {
        public boolean onLongClick(View paramAnonymousView)
        {
          if (!URLUtil.isValidUrl(KeyArrayAdapter.this.url))
          {
            new CustomDialogSingleButton(KeyArrayAdapter.this.context, "Alert!", "链接错误.").show();
            return true;
          }
          if (KeyArrayAdapter.this.url.length() != 0) {
            if (URLUtil.isValidUrl(KeyArrayAdapter.this.url))
            {
              KeyArrayAdapter.this.showToast("重定向到浏览器...");
              Intent localIntent = new Intent("android.intent.action.VIEW");
              localIntent.setData(Uri.parse(KeyArrayAdapter.this.url));
              KeyArrayAdapter.this.context.startActivity(localIntent);
            }else{
            	new CustomDialogSingleButton(KeyArrayAdapter.this.context, "Invalid!", "链接错误.").show();
            }
          }else{
        	  new CustomDialogSingleButton(KeyArrayAdapter.this.context, "Alert!", "未发现有效值.").show();
              
          }
          KeyArrayAdapter.this.keyToolUserName.setEnabled(true);
          KeyArrayAdapter.this.keyToolPassword.setEnabled(true);
          KeyArrayAdapter.this.keyToolUrl.setEnabled(false);
          return true;
        }
      });
      final GestureDetector localGestureDetector = new GestureDetector(new MyGestureDetector(paramInt, this.viewFlipper, this.keyTool, this.checkListItem, this.itemChecked, this.textView));
      this.textView.setOnTouchListener(new View.OnTouchListener()
      {
        public boolean onTouch(View paramAnonymousView, MotionEvent paramAnonymousMotionEvent)
        {
          PasswordPanacea.setKeyMasterId(((Items)KeyArrayAdapter.this.values.get(paramInt)).getItemId());
          PasswordPanacea.setKeyTitle(((Items)KeyArrayAdapter.this.values.get(paramInt)).getItemTitle());
          KeyArrayAdapter.this.groupId = KeyArrayAdapter.this.dbHelper.getGroupIdByKeyId(((Items)KeyArrayAdapter.this.values.get(paramInt)).getItemId());
          PasswordPanacea.setGroupMasterId(KeyArrayAdapter.this.groupId);
          new ArrayList();
          Log.e("fds",""+KeyArrayAdapter.this.groupId);
          PasswordPanacea.setGroupTitle(((Group)KeyArrayAdapter.this.dbHelper.getGroupDetail(KeyArrayAdapter.this.groupId).get(0)).getGroupTitle());
          return localGestureDetector.onTouchEvent(paramAnonymousMotionEvent);
        }
      });
      this.checkListItem.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
        	 Log.e("fddddds",""+"fdsfsdfdsf");
          KeyArrayAdapter.this.selectAllChecked = Boolean.valueOf(true);
          KeyArrayAdapter.this.selectNoneChecked = Boolean.valueOf(true);
          CheckBox localCheckBox = (CheckBox)paramAnonymousView;
          if (localCheckBox.isChecked())
          {
            KeyArrayAdapter.this.itemChecked.set(paramInt, Boolean.valueOf(true));
            if (!KeyArrayAdapter.this.list.contains(((Items)KeyArrayAdapter.this.values.get(paramInt)).getItemTitle()))
            {
              KeyArrayAdapter.this.list.add(new Items(((Items)KeyArrayAdapter.this.values.get(paramInt)).getItemId(), ((Items)KeyArrayAdapter.this.values.get(paramInt)).getItemTitle()));
              PasswordPanacea.setSelectedItems(KeyArrayAdapter.this.list);
            }
          }else{
          	KeyArrayAdapter.this.itemChecked.set(paramInt, Boolean.valueOf(false));
          	if (!KeyArrayAdapter.this.list.contains(((Items)KeyArrayAdapter.this.values.get(paramInt)).getItemTitle()))
              {
          		 for (int i = 0;i >= KeyArrayAdapter.this.list.size(); i++)
                   {
                     if (((Items)KeyArrayAdapter.this.list.get(i)).getItemTitle().equals(((Items)KeyArrayAdapter.this.values.get(paramInt)).getItemTitle())) {
                  	   KeyArrayAdapter.this.list.remove(i);
                  	   break;
                     }                       
                   }
          		 PasswordPanacea.setSelectedItems(KeyArrayAdapter.this.list);
              }
          }  
          Log.e("checkListItem",PasswordPanacea.getSelectedItems().size()+"");
        }
      });
      
      return paramView;
      
    
  }
  
  private class ElementsNameFilter
    extends Filter
  {
    private ElementsNameFilter() {}
    
    protected Filter.FilterResults performFiltering(CharSequence paramCharSequence)
    {
      String str = paramCharSequence.toString().toLowerCase();
      Filter.FilterResults localFilterResults = new Filter.FilterResults();
      
      if ((str != null) && (str.toString().length() > 0))
      {
        ArrayList localArrayList1 = new ArrayList();
        ArrayList localArrayList2 = new ArrayList();
        localArrayList2.addAll(KeyArrayAdapter.this.allData);
        for(int i=0;i< localArrayList2.size();i++){
        	Items localItems = (Items)localArrayList2.get(i);
            if (localItems.getItemTitle().toLowerCase().contains(str))
              localArrayList1.add(localItems);
        }
        localFilterResults.count = localArrayList1.size();
        localFilterResults.values = localArrayList1;
        return localFilterResults;
        
        
        }
      else{
        localFilterResults.values = KeyArrayAdapter.this.allData;
        localFilterResults.count = KeyArrayAdapter.this.values.size();
        return localFilterResults;
      }      
    }
    
    protected void publishResults(CharSequence paramCharSequence, Filter.FilterResults paramFilterResults)
    {
      KeyArrayAdapter.this.filtered = ((ArrayList)paramFilterResults.values);
      KeyArrayAdapter.this.notifyDataSetChanged();
      KeyArrayAdapter.this.clear();
      int i = 0;
      int j = KeyArrayAdapter.this.filtered.size();
      for (;;)
      {
        if (i >= j)
        {
          KeyArrayAdapter.this.notifyDataSetInvalidated();
          return;
        }
        KeyArrayAdapter.this.add((Items)KeyArrayAdapter.this.filtered.get(i));
        i++;
      }
    }
  }
  
  class MyGestureDetector
    extends GestureDetector.SimpleOnGestureListener
  {
    private CheckBox checkBoxItem;
    private ArrayList<Boolean> itemChecked = new ArrayList();
    private LinearLayout keyTool;
    private int position;
    private TextView textView;
    private ViewFlipper viewFlipper;
    
    public MyGestureDetector(int paramInt,ViewFlipper paramViewFlipper, LinearLayout paramLinearLayout, CheckBox paramCheckBox, ArrayList<Boolean> paramArrayList, TextView paramTextView)
    {
      this.position = paramInt;
      this.viewFlipper = paramViewFlipper;
      this.keyTool = paramLinearLayout;
      this.checkBoxItem = paramCheckBox;
      this.itemChecked = paramArrayList;
      this.textView = paramTextView;
    }
    
    public boolean onDown(MotionEvent paramMotionEvent)
    {
      return true;
    }
    
    public boolean onSingleTapUp(MotionEvent paramMotionEvent)
    {
      int j;
      int n;
      int m;
      Log.e("dfs",KeyArrayAdapter.this.isCheckBoxVisible.booleanValue()+"");
	  if (!KeyArrayAdapter.this.isCheckBoxVisible.booleanValue())
	  {
		  Log.e("a","a"+(KeyArrayAdapter.this.listView.getLastVisiblePosition() - KeyArrayAdapter.this.listView.getFirstVisiblePosition()));
	    this.textView.setSelected(true);
	    j = 0;
	    while (j < KeyArrayAdapter.this.allData.size())
	    {
	    	Log.e("b","b"+j);
	    	KeyArrayAdapter.this.itemVisible.set(j, Integer.valueOf(8));
	        KeyArrayAdapter.this.itemSelected.set(j, Boolean.valueOf(false));
	        j++;
	    }
	    if (this.keyTool.getVisibility() != 0) {
    	  for (int k = 0;; k++)
          {
    		  Log.e("c","c"+k);
            if (k >= KeyArrayAdapter.this.listView.getLastVisiblePosition() - KeyArrayAdapter.this.listView.getFirstVisiblePosition())
            {
              this.textView.setSelected(true);
              this.keyTool.setVisibility(0);
              KeyArrayAdapter.this.itemVisible.set(this.position, Integer.valueOf(0));
              KeyArrayAdapter.this.itemSelected.set(this.position, Boolean.valueOf(true));
              System.out.println("======================= ==================" + (this.position - KeyArrayAdapter.this.listView.getFirstVisiblePosition()));
              break;
            }
            View localView1 = KeyArrayAdapter.this.listView.getChildAt(k);
            LinearLayout localLinearLayout1 = (LinearLayout)localView1.findViewById(2131296363);
            ((TextView)localView1.findViewById(2131296454)).setSelected(false);
            localLinearLayout1.setVisibility(8);
          }
	    }else{
	    	this.textView.setSelected(false);
	    	this.keyTool.setVisibility(8);
	    }
	  //  n = 0;
	  //  while (n < KeyArrayAdapter.this.listView.getLastVisiblePosition() - KeyArrayAdapter.this.listView.getFirstVisiblePosition()) {
	  //  	Log.e("d","d"+n);
	  //  	View localView2 = KeyArrayAdapter.this.listView.getChildAt(n);
	  //  	LinearLayout localLinearLayout2 = (LinearLayout)localView2.findViewById(2131296363);
	  //  	((TextView)localView2.findViewById(2131296454)).setSelected(false);
	  //  	localLinearLayout2.setVisibility(8);
	  //  	n++;
	   //   }
	      KeyArrayAdapter.this.keyDetail = new ArrayList();
	      KeyArrayAdapter.this.keyDetail = KeyArrayAdapter.this.dbHelper.getKeyDetail(PasswordPanacea.getKeyMasterId());
	      m = 0;
	      while (m < KeyArrayAdapter.this.keyDetail.size()) {
	    	  Log.e("e","e"+m);
	    	  KeyArrayAdapter.this.userName = ((KeyDetailByKeyId)KeyArrayAdapter.this.keyDetail.get(m)).getKeyUsername();
	          KeyArrayAdapter.this.password = ((KeyDetailByKeyId)KeyArrayAdapter.this.keyDetail.get(m)).getKeyPassword();
	          KeyArrayAdapter.this.url = ((KeyDetailByKeyId)KeyArrayAdapter.this.keyDetail.get(m)).getKeyUrl();
	          KeyArrayAdapter.this.cryptoHelper = new CryptoHelper(2);
	          try
	          {
	            KeyArrayAdapter.this.cryptoHelper.setPassword("madhur+sonu");
	            KeyArrayAdapter.this.password = KeyArrayAdapter.this.cryptoHelper.decrypt(KeyArrayAdapter.this.password);
	            m++;
	          }
	          catch (CryptoHelperException localCryptoHelperException)
	          {
	              localCryptoHelperException.printStackTrace();
	              localCryptoHelperException.getMessage();
	          }
	      }
      }else{
    	  Log.e("f","f");
    	  KeyArrayAdapter.this.selectAllChecked = Boolean.valueOf(true);
          KeyArrayAdapter.this.selectNoneChecked = Boolean.valueOf(true);
          if (this.checkBoxItem.isChecked()) {
        	  this.textView.setSelected(false);
              this.checkBoxItem.setChecked(false);
              this.itemChecked.set(this.position, Boolean.valueOf(false));        	  
    		  if (!KeyArrayAdapter.this.list.contains(((Items)KeyArrayAdapter.this.values.get(this.position)).getItemTitle()))
	          {
    			  for (int i = 0;i < KeyArrayAdapter.this.list.size(); i++)
                  {
    				  if (((Items)KeyArrayAdapter.this.list.get(i)).getItemTitle().equals(((Items)KeyArrayAdapter.this.values.get(this.position)).getItemTitle())) {
    					  KeyArrayAdapter.this.list.remove(i);
    					  break;
	                  }
    	                  
                  }
    			  PasswordPanacea.setSelectedItems(KeyArrayAdapter.this.list);
	          }
          }else{
	          this.textView.setSelected(true);
	          this.checkBoxItem.setChecked(true);
	          this.itemChecked.set(this.position, Boolean.valueOf(true));
	          if (!KeyArrayAdapter.this.list.contains(((Items)KeyArrayAdapter.this.values.get(this.position)).getItemTitle()))
	          {
	            KeyArrayAdapter.this.list.add(new Items(((Items)KeyArrayAdapter.this.values.get(this.position)).getItemId(), ((Items)KeyArrayAdapter.this.values.get(this.position)).getItemTitle()));
	            PasswordPanacea.setSelectedItems(KeyArrayAdapter.this.list);
	          }
          }
      }
	  return super.onSingleTapUp(paramMotionEvent);
      }  
  }
}


/* Location:           D:\Apkdb\Craining\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     rdi.mobapp.passwordpanacea.adapter.KeyArrayAdapter
 * JD-Core Version:    0.7.0.1
 */