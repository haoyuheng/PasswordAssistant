package rdi.mobapp.passwordpanacea.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.hyh.passwordassitant.activity.RecycleBinActivity;

import rdi.mobapp.passwordpanacea.application.PasswordPanacea;
import rdi.mobapp.passwordpanacea.bean.Items;
import rdi.mobapp.passwordpanacea.databasehelper.DBHelper;
import rdi.mobapp.passwordpanacea.utility.CustomDialog;

public class TrashGroupArrayAdapter
  extends ArrayAdapter<Items>
{
  private List<Items> allData;
  private CheckBox checkListItem;
  private final Context context;
  private DBHelper dbHelper;
  private Animation fadeIn;
  private Animation fadeOut;
  private Boolean firstCalled;
  private ImageView groupIcon;
  private int groupId;
  private TextView groupName;
  private ImageView imgDelete;
  private ImageView imgRestore;
  private Boolean isCheckAll;
  private Boolean isCheckBoxVisible;
  private ArrayList<Boolean> itemChecked = new ArrayList();
  private ArrayList<Integer> keyId;
  private TextView keySize;
  private List<Items> list = new ArrayList();
  private ListView listView;
  private Boolean selectAllChecked = Boolean.valueOf(false);
  private Boolean selectNoneChecked = Boolean.valueOf(false);
  private Typeface tf;
  private List<Items> values;
  private View viewSeperator;
  
  public TrashGroupArrayAdapter(Context paramContext, List<Items> paramList, ListView paramListView, Boolean paramBoolean1, Boolean paramBoolean2, Boolean paramBoolean3)
  {
    super(paramContext, 2130903080, paramList);
    this.context = paramContext;
    this.allData = new ArrayList(paramList.size());
    Iterator localIterator = paramList.iterator();
    while (localIterator.hasNext())
    {
    	Items localItems = (Items)localIterator.next();
    	this.allData.add(localItems);
      
    }
    this.listView = paramListView;
    this.values = paramList;
    this.isCheckBoxVisible = paramBoolean1;
    this.isCheckAll = paramBoolean2;
    this.firstCalled = paramBoolean3;
    for (int i = 0;; i++)
    {
      if (i >= getCount())
      {
        PasswordPanacea.setRemoveRestoreCalled(false);
        return;
      }
      this.itemChecked.add(i, Boolean.valueOf(false));
      this.list.add(new Items(((Items)paramList.get(i)).getItemId(), ((Items)paramList.get(i)).getItemTitle()));
      PasswordPanacea.setSelectedGroups(this.list);
    }
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
  
  public View getView(final int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    if (paramView == null) {
      paramView = ((LayoutInflater)this.context.getSystemService("layout_inflater")).inflate(2130903080, null);
    }
    this.tf = Typeface.createFromAsset(this.context.getAssets(), "fonts/galette.otf");
    this.fadeIn = AnimationUtils.loadAnimation(this.context, 2130968582);
    this.fadeOut = AnimationUtils.loadAnimation(this.context, 2130968591);
    this.groupName = ((TextView)paramView.findViewById(2131296395));
    this.groupName.setTypeface(this.tf);
    this.groupName.setText(((Items)this.values.get(paramInt)).getItemTitle());
    this.keySize = ((TextView)paramView.findViewById(2131296396));
    this.keySize.setTypeface(this.tf);
    this.groupIcon = ((ImageView)paramView.findViewById(2131296393));
    this.checkListItem = ((CheckBox)paramView.findViewById(2131296394));
    this.imgDelete = ((ImageView)paramView.findViewById(2131296633));
    this.imgRestore = ((ImageView)paramView.findViewById(2131296632));
    this.viewSeperator = paramView.findViewById(2131296631);
    this.dbHelper = new DBHelper(this.context);
    this.keyId = new ArrayList();
    this.keyId = this.dbHelper.getKeyCount(((Items)this.values.get(paramInt)).getItemId(), 1);
    this.keySize.setText(this.keyId.size()+"");
    if (this.isCheckBoxVisible.booleanValue())
    {
      this.groupIcon.setVisibility(8);
      this.checkListItem.setVisibility(0);
      this.viewSeperator.setVisibility(8);
      this.imgDelete.setVisibility(8);
      this.imgRestore.setVisibility(8);
    }
    int i;
    if (this.isCheckAll.booleanValue()) {
      if (this.selectAllChecked.booleanValue())
      {
        i = 0;
        while (i < this.listView.getCount()) {
        	this.checkListItem.setChecked(true);
            this.itemChecked.set(i, Boolean.valueOf(true));
            i++;
        }
        PasswordPanacea.setSelectedGroups(this.list);
        PasswordPanacea.setGroupSelected(true);
      }
      else if (this.selectNoneChecked.booleanValue())
      {
        this.checkListItem.setChecked(false);
        this.itemChecked.set(paramInt, Boolean.valueOf(false));
        this.list.clear();
        PasswordPanacea.setSelectedGroups(this.list);
      }
    }
    
      this.checkListItem.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          CheckBox localCheckBox;
          if (TrashGroupArrayAdapter.this.isCheckBoxVisible.booleanValue())
          {
            PasswordPanacea.setGroupSelected(true);
            TrashGroupArrayAdapter.this.selectAllChecked = Boolean.valueOf(true);
            TrashGroupArrayAdapter.this.selectNoneChecked = Boolean.valueOf(true);
            localCheckBox = (CheckBox)paramAnonymousView.findViewById(2131296394);
            if (!localCheckBox.isChecked()) {
            	TrashGroupArrayAdapter.this.itemChecked.set(paramInt, Boolean.valueOf(false));
            	for (int i = 0;i < TrashGroupArrayAdapter.this.list.size(); i++)
                {
                    if (((Items)TrashGroupArrayAdapter.this.list.get(i)).getItemTitle().equals(((Items)TrashGroupArrayAdapter.this.values.get(paramInt)).getItemTitle())) {
                    	TrashGroupArrayAdapter.this.list.remove(i);
                    	break;
                    }
                }
            	 PasswordPanacea.setSelectedItems(TrashGroupArrayAdapter.this.list);
            }else{
	            TrashGroupArrayAdapter.this.itemChecked.set(paramInt, Boolean.valueOf(true));
	            if (!TrashGroupArrayAdapter.this.list.contains(((Items)TrashGroupArrayAdapter.this.values.get(paramInt)).getItemTitle()))
	            {
	              TrashGroupArrayAdapter.this.list.add(new Items(((Items)TrashGroupArrayAdapter.this.values.get(paramInt)).getItemId(), ((Items)TrashGroupArrayAdapter.this.values.get(paramInt)).getItemTitle()));
	              PasswordPanacea.setSelectedItems(TrashGroupArrayAdapter.this.list);
	            }
            }
          }
        }
      });
      this.imgDelete.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          final ImageView localImageView = (ImageView)paramAnonymousView.findViewById(2131296633);
          localImageView.setSelected(true);
          TrashGroupArrayAdapter.this.groupId = ((Items)TrashGroupArrayAdapter.this.values.get(paramInt)).getItemId();
          final CustomDialog localCustomDialog = new CustomDialog(TrashGroupArrayAdapter.this.context, "Delete?", "确认删除此分组码?");
          localCustomDialog.show();
          localCustomDialog.customAlertBoxPositiveButton.setOnClickListener(new View.OnClickListener()
          {
            public void onClick(View paramAnonymous2View)
            {
              TrashGroupArrayAdapter.this.keyId = TrashGroupArrayAdapter.this.dbHelper.getKeyIdByGroupId(TrashGroupArrayAdapter.this.groupId);
              System.out.println("keyId===========" + TrashGroupArrayAdapter.this.keyId);
              for (int i = 0;; i++)
              {
                if (i >= TrashGroupArrayAdapter.this.keyId.size())
                {
                  TrashGroupArrayAdapter.this.dbHelper.deleteGroup(TrashGroupArrayAdapter.this.groupId);
                  TrashGroupArrayAdapter.this.showToast("分组删除成功.");
                  localImageView.setSelected(false);
                  TrashGroupArrayAdapter.this.values.remove(paramInt);
                  PasswordPanacea.setGroupTabON(true);
                  PasswordPanacea.setRemoveRestoreCalled(true);
                  Intent localIntent = new Intent(TrashGroupArrayAdapter.this.context, RecycleBinActivity.class);
                  localIntent.addFlags(65536);
                  TrashGroupArrayAdapter.this.context.startActivity(localIntent);
                  localCustomDialog.dismiss();
                  return;
                }
                TrashGroupArrayAdapter.this.dbHelper.deleteKey(((Integer)TrashGroupArrayAdapter.this.keyId.get(i)).intValue());
              }
            }
          });
          localCustomDialog.customAlertBoxNegitiveButton.setOnClickListener(new View.OnClickListener()
          {
            public void onClick(View paramAnonymous2View)
            {
              localCustomDialog.dismiss();
              localImageView.setSelected(false);
            }
          });
        }
      });
      this.imgRestore.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          final ImageView localImageView = (ImageView)paramAnonymousView.findViewById(2131296632);
          localImageView.setSelected(true);
          TrashGroupArrayAdapter.this.groupId = ((Items)TrashGroupArrayAdapter.this.values.get(paramInt)).getItemId();
          final CustomDialog localCustomDialog = new CustomDialog(TrashGroupArrayAdapter.this.context, "Restore!", "确认恢复次分组吗?");
          localCustomDialog.show();
          localCustomDialog.customAlertBoxPositiveButton.setOnClickListener(new View.OnClickListener()
          {
            public void onClick(View paramAnonymous2View)
            {
              TrashGroupArrayAdapter.this.dbHelper.getGroupRestore(TrashGroupArrayAdapter.this.groupId);
              TrashGroupArrayAdapter.this.showToast("分组恢复成功.");
              TrashGroupArrayAdapter.this.values.remove(paramInt);
              localImageView.setSelected(false);
              PasswordPanacea.setGroupTabON(true);
              PasswordPanacea.setRemoveRestoreCalled(true);
              Intent localIntent = new Intent(TrashGroupArrayAdapter.this.context, RecycleBinActivity.class);
              localIntent.addFlags(65536);
              TrashGroupArrayAdapter.this.context.startActivity(localIntent);
              localCustomDialog.dismiss();
            }
          });
          localCustomDialog.customAlertBoxNegitiveButton.setOnClickListener(new View.OnClickListener()
          {
            public void onClick(View paramAnonymous2View)
            {
              localCustomDialog.dismiss();
              localImageView.setSelected(false);
            }
          });
        }
      });
      this.groupName.setSelected(((Boolean)this.itemChecked.get(paramInt)).booleanValue());
      this.checkListItem.setChecked(((Boolean)this.itemChecked.get(paramInt)).booleanValue());
      final GestureDetector localGestureDetector = new GestureDetector(new MyGestureDetector(paramInt, this.checkListItem, this.itemChecked, this.groupName));
      this.groupName.setOnTouchListener(new View.OnTouchListener()
      {
        public boolean onTouch(View paramAnonymousView, MotionEvent paramAnonymousMotionEvent)
        {
          return localGestureDetector.onTouchEvent(paramAnonymousMotionEvent);
        }
      });
      return paramView;
      
    
  }
  
  class MyGestureDetector
    extends GestureDetector.SimpleOnGestureListener
  {
    private CheckBox checkBoxItem;
    private ArrayList<Boolean> itemChecked = new ArrayList();
    private int position;
    private TextView textView;
    
    public MyGestureDetector(int paramInt,CheckBox paramCheckBox, ArrayList<Boolean> paramArrayList, TextView paramTextView)
    {
    	this.position = paramInt;
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
      if (TrashGroupArrayAdapter.this.isCheckBoxVisible.booleanValue())
      {
        TrashGroupArrayAdapter.this.selectAllChecked = Boolean.valueOf(true);
        TrashGroupArrayAdapter.this.selectNoneChecked = Boolean.valueOf(true);
        if (this.checkBoxItem.isChecked()) {
        	this.textView.setSelected(false);
            this.checkBoxItem.setChecked(false);
            this.itemChecked.set(this.position, Boolean.valueOf(false));
            if (!TrashGroupArrayAdapter.this.list.contains(((Items)TrashGroupArrayAdapter.this.values.get(this.position)).getItemTitle()))
	        {
	            for (int i = 0;i<TrashGroupArrayAdapter.this.list.size(); i++)
	            {   
	                if (((Items)TrashGroupArrayAdapter.this.list.get(i)).getItemTitle().equals(((Items)TrashGroupArrayAdapter.this.values.get(this.position)).getItemTitle())) {
	                	TrashGroupArrayAdapter.this.list.remove(i);
	                	break;
	                }
	              }
	            }
            	PasswordPanacea.setSelectedItems(TrashGroupArrayAdapter.this.list);            
	        }
        }else{
	        this.textView.setSelected(true);
	        this.checkBoxItem.setChecked(true);
	        this.itemChecked.set(this.position, Boolean.valueOf(true));
	        if (!TrashGroupArrayAdapter.this.list.contains(((Items)TrashGroupArrayAdapter.this.values.get(this.position)).getItemTitle()))
	        {
	          TrashGroupArrayAdapter.this.list.add(new Items(((Items)TrashGroupArrayAdapter.this.values.get(this.position)).getItemId(), ((Items)TrashGroupArrayAdapter.this.values.get(this.position)).getItemTitle()));
	          PasswordPanacea.setSelectedItems(TrashGroupArrayAdapter.this.list);
	        }
        }
      return super.onSingleTapUp(paramMotionEvent);
    }
    
  }
}


/* Location:           D:\Apkdb\Craining\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     rdi.mobapp.passwordpanacea.adapter.TrashGroupArrayAdapter
 * JD-Core Version:    0.7.0.1
 */