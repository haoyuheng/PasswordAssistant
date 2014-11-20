package rdi.mobapp.passwordpanacea.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Filter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ViewFlipper;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.hyh.passwordassitant.activity.EditGroupActivity;
import com.hyh.passwordassitant.activity.KeyActivity;

import rdi.mobapp.passwordpanacea.application.PasswordPanacea;
import rdi.mobapp.passwordpanacea.bean.Group;
import rdi.mobapp.passwordpanacea.bean.Items;
import rdi.mobapp.passwordpanacea.databasehelper.DBHelper;
import rdi.mobapp.passwordpanacea.utility.CustomDialog;
import rdi.mobapp.passwordpanacea.utility.CustomDialogSingleButton;

public class GroupArrayAdapter
  extends ArrayAdapter<Items>
{
  private List<Items> allData;
  private ArrayList<Integer> arrowPosition = new ArrayList();
  private CheckBox checkListItem;
  private final Context context;
  private int count;
  private DBHelper dbHelper;
  private Filter filter;
  private List<Items> filtered;
  private Boolean firstCalled;
  private GroupArrayAdapter groupArrayAdapter;
  private ArrayList<Group> groupDetail = new ArrayList();
  private ImageView groupIcon;
  private TextView groupName;
  private LinearLayout groupToolDelete;
  private LinearLayout groupToolEdit;
  private LinearLayout groupTools;
  private ImageButton imgDownArrow;
  private Boolean isCheckAll;
  private Boolean isCheckBoxVisible;
  private ArrayList<Boolean> itemChecked = new ArrayList();
  private ArrayList<Boolean> itemSelected = new ArrayList();
  private ArrayList<Integer> itemVisible = new ArrayList();
  private ArrayList<Integer> keyId;
  private TextView keySize;
  private List<Items> list = new ArrayList();
  private ListView listView;
  private int pos;
  private Boolean selectAllChecked = Boolean.valueOf(false);
  private Boolean selectNoneChecked = Boolean.valueOf(false);
  private Typeface tf;
  private TextView txtGroupDesc;
  private List<Items> values;
  private ViewFlipper viewFlipper;
  private View viewSeperator;
  
  public GroupArrayAdapter(Context paramContext, List<Items> paramList, ListView paramListView, Boolean paramBoolean1, Boolean paramBoolean2, Boolean paramBoolean3)
  {
    super(paramContext, 2130903051, paramList);
    this.context = paramContext;
    this.allData = new ArrayList(paramList.size());
    Iterator localIterator = paramList.iterator();
    while (localIterator.hasNext())
    {
    	Items localItems = (Items)localIterator.next();
        this.allData.add(localItems);
    }
    this.listView = paramListView;
    this.filtered = paramList;
    this.values = this.filtered;
    this.filter = new ElementsNameFilter();
    this.isCheckBoxVisible = paramBoolean1;
    this.isCheckAll = paramBoolean2;
    this.firstCalled = paramBoolean3;
    
    for (int i = 0;i< getCount(); i++)
    {
      this.itemChecked.add(i, Boolean.valueOf(false));
      this.itemSelected.add(i, Boolean.valueOf(false));
      this.itemVisible.add(i, Integer.valueOf(8));
      this.arrowPosition.add(i, Integer.valueOf(2130837658));
      this.list.add(new Items(((Items)paramList.get(i)).getItemId(), ((Items)paramList.get(i)).getItemTitle()));
    }
  }
  
  public Filter getFilter()
  {
    System.out.println("filter++++++++++++++++++++++++" + this.filter);
    if (this.filter == null) {
      this.filter = new ElementsNameFilter();
    }
    return this.filter;
  }
  
  public View getView(final int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    if (paramView == null) {
      paramView = ((LayoutInflater)this.context.getSystemService("layout_inflater")).inflate(2130903051, null);
    }
    this.tf = Typeface.createFromAsset(this.context.getAssets(), "fonts/galette.otf");
    this.groupName = ((TextView)paramView.findViewById(2131296395));
    this.groupName.setFocusable(false);
    this.groupName.setTypeface(this.tf);
    Log.e("ddddddd"," "+paramInt);
    this.groupName.setText(((Items)this.values.get(paramInt)).getItemTitle());
    this.keySize = ((TextView)paramView.findViewById(2131296396));
    this.keySize.setTypeface(this.tf);
    this.groupIcon = ((ImageView)paramView.findViewById(2131296393));
    this.checkListItem = ((CheckBox)paramView.findViewById(2131296394));
    this.dbHelper = new DBHelper(this.context);
    this.keyId = new ArrayList();
    this.txtGroupDesc = ((TextView)paramView.findViewById(2131296401));
    this.txtGroupDesc.setTypeface(this.tf);
    this.imgDownArrow = ((ImageButton)paramView.findViewById(2131296398));
    this.groupTools = ((LinearLayout)paramView.findViewById(2131296399));
    this.groupToolEdit = ((LinearLayout)paramView.findViewById(2131296402));
    this.groupToolDelete = ((LinearLayout)paramView.findViewById(2131296405));
    this.viewSeperator = paramView.findViewById(2131296397);
    this.viewFlipper = ((ViewFlipper)paramView.findViewById(2131296391));
    this.keyId = this.dbHelper.getKeyCount(((Items)this.values.get(paramInt)).getItemId(), 0);
    this.keySize.setText(this.keyId.size()+"");
    this.groupName.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        GroupArrayAdapter.this.redirectToKey();
      }
    });
    if (this.isCheckBoxVisible.booleanValue())
    {
      this.groupIcon.setVisibility(8);
      this.checkListItem.setVisibility(0);
      this.viewSeperator.setVisibility(8);
      this.imgDownArrow.setVisibility(8);
    }
    int i;
    if (this.isCheckAll.booleanValue()) {
      
        i = 0;
        while (i < getCount()) {
        	this.checkListItem.setChecked(true);
            this.itemChecked.set(i, Boolean.valueOf(true));
            i++;          
        }
        PasswordPanacea.setSelectedItems(this.list);
    }else{  
    	  this.checkListItem.setChecked(false);
          this.itemChecked.set(paramInt, Boolean.valueOf(false));
          this.list.clear();
          PasswordPanacea.setSelectedItems(this.list);
      }
     
    this.checkListItem.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        GroupArrayAdapter.this.selectAllChecked = Boolean.valueOf(true);
        GroupArrayAdapter.this.selectNoneChecked = Boolean.valueOf(true);
        CheckBox localCheckBox = (CheckBox)paramAnonymousView;
        if (localCheckBox.isChecked())
        {
          GroupArrayAdapter.this.itemChecked.set(paramInt, Boolean.valueOf(true));
          if (!GroupArrayAdapter.this.list.contains(((Items)GroupArrayAdapter.this.values.get(paramInt)).getItemTitle()))
          {
            GroupArrayAdapter.this.list.add(new Items(((Items)GroupArrayAdapter.this.values.get(paramInt)).getItemId(), ((Items)GroupArrayAdapter.this.values.get(paramInt)).getItemTitle()));
            PasswordPanacea.setSelectedItems(GroupArrayAdapter.this.list);
          }
        }else{
        	GroupArrayAdapter.this.itemChecked.set(paramInt, Boolean.valueOf(false));
        	if (GroupArrayAdapter.this.list.contains(((Items)GroupArrayAdapter.this.values.get(paramInt)).getItemTitle()))
            {
        		for (int i = 0;i < GroupArrayAdapter.this.list.size(); i++)
                {                      
                    if (((Items)GroupArrayAdapter.this.list.get(i)).getItemTitle().equals(((Items)GroupArrayAdapter.this.values.get(paramInt)).getItemTitle())) {
                    	GroupArrayAdapter.this.list.remove(i);
                    	break;
                    }                        
                }   
        		PasswordPanacea.setSelectedItems(GroupArrayAdapter.this.list);
            }
        }            
      }
    });
    if (!this.isCheckBoxVisible.booleanValue()) {
    	this.groupName.setSelected(((Boolean)this.itemSelected.get(paramInt)).booleanValue());
    }else{
    	this.groupName.setSelected(((Boolean)this.itemChecked.get(paramInt)).booleanValue());
    }
      
    
    for (;;)
    { 
      final GestureDetector localGestureDetector = new GestureDetector(new MyGestureDetector(paramInt, this.viewFlipper, this.count, this.checkListItem, this.itemChecked, this.groupName));
      this.groupName.setOnTouchListener(new View.OnTouchListener()
      {
        public boolean onTouch(View paramAnonymousView, MotionEvent paramAnonymousMotionEvent)
        {
          PasswordPanacea.setGroupMasterId(((Items)GroupArrayAdapter.this.values.get(paramInt)).getItemId());
          PasswordPanacea.setGroupTitle(((Items)GroupArrayAdapter.this.values.get(paramInt)).getItemTitle());
          return localGestureDetector.onTouchEvent(paramAnonymousMotionEvent);
        }
      });
      this.imgDownArrow.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          View localView1;
          LinearLayout localLinearLayout;
          TextView localTextView1;
          TextView localTextView2;
          ImageButton localImageButton;
          int i;
          int j;
          if (!GroupArrayAdapter.this.isCheckBoxVisible.booleanValue())
          {
            localView1 = GroupArrayAdapter.this.listView.getChildAt(paramInt - GroupArrayAdapter.this.listView.getFirstVisiblePosition());
            localLinearLayout = (LinearLayout)localView1.findViewById(2131296399);
            localTextView1 = (TextView)localView1.findViewById(2131296401);
            localTextView2 = (TextView)localView1.findViewById(2131296395);
            localImageButton = (ImageButton)localView1.findViewById(2131296398);
            GroupArrayAdapter.this.groupDetail = GroupArrayAdapter.this.dbHelper.getGroupDetail(((Items)GroupArrayAdapter.this.values.get(paramInt)).getItemId());
            if ((!((Group)GroupArrayAdapter.this.groupDetail.get(0)).getGroupDescription().equals("")) && (((Group)GroupArrayAdapter.this.groupDetail.get(0)).getGroupDescription() != null)) {
            	localTextView1.setText(((Group)GroupArrayAdapter.this.groupDetail.get(0)).getGroupDescription());
                
            }else
            	localTextView1.setText("无描述.");
            i = 0;
            while (i < GroupArrayAdapter.this.getCount()) {
            	GroupArrayAdapter.this.itemVisible.set(i, Integer.valueOf(8));
                GroupArrayAdapter.this.itemSelected.set(i, Boolean.valueOf(false));
                GroupArrayAdapter.this.arrowPosition.set(i, Integer.valueOf(2130837658));
                i++;
            }
            j = 0;
            while (j < GroupArrayAdapter.this.listView.getLastVisiblePosition() - GroupArrayAdapter.this.listView.getFirstVisiblePosition()) {
            	View localView2 = GroupArrayAdapter.this.listView.getChildAt(j);
                ((TextView)localView2.findViewById(2131296395)).setSelected(false);
                ((ImageButton)localView2.findViewById(2131296398)).setImageResource(2130837658);
                j++;
            }
            if (localLinearLayout.getVisibility() != 0) {
            	for (int k = 0;; k++)
                {
                  if (k >= GroupArrayAdapter.this.listView.getLastVisiblePosition() - GroupArrayAdapter.this.listView.getFirstVisiblePosition())
                  {
                    GroupArrayAdapter.this.itemVisible.set(paramInt, Integer.valueOf(0));
                    GroupArrayAdapter.this.itemSelected.set(paramInt, Boolean.valueOf(true));
                    GroupArrayAdapter.this.arrowPosition.set(paramInt, Integer.valueOf(2130837660));
                    localTextView2.setSelected(true);
                    localLinearLayout.setVisibility(0);
                    localImageButton.setImageResource(2130837660);
                    return;
                  }
                  ((LinearLayout)GroupArrayAdapter.this.listView.getChildAt(k).findViewById(2131296399)).setVisibility(8);
                  ((ImageButton)localView1.findViewById(2131296398)).setImageResource(2130837658);
                }
            }
            for (int m = 0;m < GroupArrayAdapter.this.listView.getLastVisiblePosition() - GroupArrayAdapter.this.listView.getFirstVisiblePosition(); m++)
            {
              ((LinearLayout)GroupArrayAdapter.this.listView.getChildAt(m).findViewById(2131296399)).setVisibility(8);
              ((ImageButton)localView1.findViewById(2131296398)).setImageResource(2130837658);
            }
          }          
        
      }
      });
      this.groupToolEdit.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          final LinearLayout localLinearLayout = (LinearLayout)GroupArrayAdapter.this.listView.getChildAt(paramInt - GroupArrayAdapter.this.listView.getFirstVisiblePosition()).findViewById(2131296402);
          localLinearLayout.setSelected(true);
          PasswordPanacea.setGroupTitle(((Items)GroupArrayAdapter.this.values.get(paramInt)).getItemTitle());
          PasswordPanacea.setGroupMasterId(((Items)GroupArrayAdapter.this.values.get(paramInt)).getItemId());
          if (PasswordPanacea.getGroupMasterId() != 1)
          {
            Intent localIntent = new Intent(GroupArrayAdapter.this.context, EditGroupActivity.class);
            localIntent.addFlags(65536);
            GroupArrayAdapter.this.context.startActivity(localIntent);
            return;
          }
          final CustomDialogSingleButton localCustomDialogSingleButton = new CustomDialogSingleButton(GroupArrayAdapter.this.context, "Invalid!", "默认分组无法删除.");
          localCustomDialogSingleButton.show();
          localCustomDialogSingleButton.customAlertBoxPositiveButton.setOnClickListener(new View.OnClickListener()
          {
            public void onClick(View paramAnonymous2View)
            {
              localLinearLayout.setSelected(false);
              localCustomDialogSingleButton.dismiss();
            }
          });
        }
      });
      this.groupToolDelete.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          PasswordPanacea.setGroupTitle(((Items)GroupArrayAdapter.this.values.get(paramInt)).getItemTitle());
          PasswordPanacea.setGroupMasterId(((Items)GroupArrayAdapter.this.values.get(paramInt)).getItemId());
          final LinearLayout localLinearLayout = (LinearLayout)GroupArrayAdapter.this.listView.getChildAt(paramInt - GroupArrayAdapter.this.listView.getFirstVisiblePosition()).findViewById(2131296405);
          localLinearLayout.setSelected(true);
          if (PasswordPanacea.getGroupMasterId() != 1)
          {
            final CustomDialog localCustomDialog;
            if (GroupArrayAdapter.this.count != 0)
            {
              localCustomDialog = new CustomDialog(GroupArrayAdapter.this.context, "Delete?", "分组\"" + PasswordPanacea.getGroupTitle() + "\" 非空. 确定要删除吗?");
              localCustomDialog.show();
              localCustomDialog.customAlertBoxPositiveButton.setOnClickListener(new View.OnClickListener()
              {
                public void onClick(View paramAnonymous2View)
                {
                  GroupArrayAdapter.this.dbHelper.getGroupInTrash(PasswordPanacea.getGroupMasterId());
                  GroupArrayAdapter.this.values.remove(paramInt);
                  GroupArrayAdapter.this.groupArrayAdapter = new GroupArrayAdapter(GroupArrayAdapter.this.context, GroupArrayAdapter.this.values, GroupArrayAdapter.this.listView, Boolean.valueOf(false), Boolean.valueOf(false), Boolean.valueOf(false));
                  GroupArrayAdapter.this.groupArrayAdapter.notifyDataSetChanged();
                  GroupArrayAdapter.this.listView.setAdapter(GroupArrayAdapter.this.groupArrayAdapter);
                  localLinearLayout.setSelected(false);
                  localCustomDialog.dismiss();
                }
              });
              localCustomDialog.customAlertBoxNegitiveButton.setOnClickListener(new View.OnClickListener()
              {
                public void onClick(View paramAnonymous2View)
                {
                  localLinearLayout.setSelected(false);
                  localCustomDialog.dismiss();
                }
              });
              return;
            }else{
            	localCustomDialog = new CustomDialog(GroupArrayAdapter.this.context, "Delete?", "确定删除分组 \"" + PasswordPanacea.getGroupTitle() + "\" 吗?");
                localCustomDialog.show();  
                localCustomDialog.customAlertBoxPositiveButton.setOnClickListener(new View.OnClickListener()
                {
                  public void onClick(View paramAnonymous2View)
                  {
                    GroupArrayAdapter.this.dbHelper.getGroupInTrash(PasswordPanacea.getGroupMasterId());
                    GroupArrayAdapter.this.values.remove(paramInt);
                    GroupArrayAdapter.this.groupArrayAdapter = new GroupArrayAdapter(GroupArrayAdapter.this.context, GroupArrayAdapter.this.values, GroupArrayAdapter.this.listView, Boolean.valueOf(false), Boolean.valueOf(false), Boolean.valueOf(false));
                    GroupArrayAdapter.this.groupArrayAdapter.notifyDataSetChanged();
                    GroupArrayAdapter.this.listView.setAdapter(GroupArrayAdapter.this.groupArrayAdapter);
                    localLinearLayout.setSelected(false);
                    localCustomDialog.dismiss();
                  }
                });
                localCustomDialog.customAlertBoxNegitiveButton.setOnClickListener(new View.OnClickListener()
                {
                  public void onClick(View paramAnonymous2View)
                  {
                    localLinearLayout.setSelected(false);
                    localCustomDialog.dismiss();
                  }
                });
            }
          }else{
        	  final CustomDialogSingleButton localCustomDialogSingleButton = new CustomDialogSingleButton(GroupArrayAdapter.this.context, "Invalid!", "默认分组无法删除.");
              localCustomDialogSingleButton.show();
              localCustomDialogSingleButton.customAlertBoxPositiveButton.setOnClickListener(new View.OnClickListener()
              {
                public void onClick(View paramAnonymous2View)
                {
                  localLinearLayout.setSelected(false);
                  localCustomDialogSingleButton.dismiss();
                }
              });
          }
          
        }
      });
      this.checkListItem.setChecked(((Boolean)this.itemChecked.get(paramInt)).booleanValue());
      this.groupTools.setVisibility(((Integer)this.itemVisible.get(paramInt)).intValue());
      this.imgDownArrow.setImageResource(((Integer)this.arrowPosition.get(paramInt)).intValue());
      return paramView;
      
    }
  }
  
  public void redirectToKey()
  {
    Intent localIntent = new Intent(this.context, KeyActivity.class);
    PasswordPanacea.setGroupMasterId(((Items)this.values.get(this.pos)).getItemId());
    PasswordPanacea.setGroupTitle(((Items)this.values.get(this.pos)).getItemTitle());
    PasswordPanacea.setFooterKeyPressed(false);
    localIntent.addFlags(65536);
    this.context.startActivity(localIntent);
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
        localArrayList2.addAll(GroupArrayAdapter.this.allData);
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
        localFilterResults.values = GroupArrayAdapter.this.allData;
        localFilterResults.count = GroupArrayAdapter.this.values.size();
        return localFilterResults;
      }
    }
    
    protected void publishResults(CharSequence paramCharSequence, Filter.FilterResults paramFilterResults)
    {
      GroupArrayAdapter.this.filtered = ((ArrayList)paramFilterResults.values);
      GroupArrayAdapter.this.notifyDataSetChanged();
      GroupArrayAdapter.this.clear();
      int i = 0;
      int j = GroupArrayAdapter.this.filtered.size();
      for (;;)
      {
        if (i >= j)
        {
          GroupArrayAdapter.this.notifyDataSetInvalidated();
          return;
        }
        GroupArrayAdapter.this.add((Items)GroupArrayAdapter.this.filtered.get(i));
        i++;
      }
    }
  }
  
  public class MyGestureDetector
    extends GestureDetector.SimpleOnGestureListener
  {
    private CheckBox checkBoxItem;
    private int count;
    private TextView groupNameText;
    private ArrayList<Boolean> itemChecked = new ArrayList();
    private int position;
    private ViewFlipper viewFlipper;
    
    public MyGestureDetector(int paramInt1, ViewFlipper paramViewFlipper, int paramInt, CheckBox paramCheckBox, ArrayList<Boolean> paramArrayList, TextView paramTextView)
    {
    	this.position = paramInt1;
      this.viewFlipper = paramViewFlipper;
      this.count = paramInt;
      this.checkBoxItem = paramCheckBox;
      this.itemChecked = paramArrayList;
      this.groupNameText = paramTextView;
    }
    
    public boolean onDown(MotionEvent paramMotionEvent)
    {
      return true;
    }
    
    public boolean onScroll(MotionEvent paramMotionEvent1, MotionEvent paramMotionEvent2, float paramFloat1, float paramFloat2)
    {
      return false;
    }
    
    public void onShowPress(MotionEvent paramMotionEvent) {}
    
    public boolean onSingleTapUp(MotionEvent paramMotionEvent)
    {
      if (!GroupArrayAdapter.this.isCheckBoxVisible.booleanValue())
      {
        this.groupNameText.setSelected(true);
        Intent localIntent = new Intent(GroupArrayAdapter.this.context, KeyActivity.class);
        PasswordPanacea.setFooterKeyPressed(false);
        localIntent.addFlags(65536);
        GroupArrayAdapter.this.context.startActivity(localIntent);
      }else{
    	  GroupArrayAdapter.this.selectAllChecked = Boolean.valueOf(true);
          GroupArrayAdapter.this.selectNoneChecked = Boolean.valueOf(true);
          if (this.checkBoxItem.isChecked()) {
        	  this.groupNameText.setSelected(false);
              this.checkBoxItem.setChecked(false);
              this.itemChecked.set(this.position, Boolean.valueOf(false));
              if (!GroupArrayAdapter.this.list.contains(((Items)GroupArrayAdapter.this.values.get(this.position)).getItemTitle()))
	          {
            	  for (int i = 0;i<GroupArrayAdapter.this.list.size(); i++)
                  {   
                      if (((Items)GroupArrayAdapter.this.list.get(i)).getItemTitle().equals(((Items)GroupArrayAdapter.this.values.get(this.position)).getItemTitle())) {
                    	  GroupArrayAdapter.this.list.remove(i);
                     	 break;
                      }
                      
                   }
              	PasswordPanacea.setSelectedItems(GroupArrayAdapter.this.list);
	          }
          }else{
	          this.groupNameText.setSelected(true);
	          this.checkBoxItem.setChecked(true);
	          this.itemChecked.set(this.position, Boolean.valueOf(true));
	          if (!GroupArrayAdapter.this.list.contains(((Items)GroupArrayAdapter.this.values.get(this.position)).getItemTitle()))
	          {
	            GroupArrayAdapter.this.list.add(new Items(((Items)GroupArrayAdapter.this.values.get(this.position)).getItemId(), ((Items)GroupArrayAdapter.this.values.get(this.position)).getItemTitle()));
	            PasswordPanacea.setSelectedItems(GroupArrayAdapter.this.list);
	          }
          }
      }
      return super.onSingleTapUp(paramMotionEvent);      
      
    }
  }
}



/* Location:           D:\Apkdb\Craining\dex2jar-0.0.9.15\classes_dex2jar.jar

 * Qualified Name:     rdi.mobapp.passwordpanacea.adapter.GroupArrayAdapter

 * JD-Core Version:    0.7.0.1

 */