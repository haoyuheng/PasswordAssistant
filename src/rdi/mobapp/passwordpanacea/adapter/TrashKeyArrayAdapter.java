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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.hyh.passwordassitant.activity.RecycleBinActivity;

import rdi.mobapp.passwordpanacea.application.PasswordPanacea;
import rdi.mobapp.passwordpanacea.bean.Items;
import rdi.mobapp.passwordpanacea.databasehelper.DBHelper;
import rdi.mobapp.passwordpanacea.utility.CustomDialog;

public class TrashKeyArrayAdapter
  extends ArrayAdapter<Items>
{
  private List<Items> allData;
  private CheckBox checkListItem;
  private final Context context;
  private DBHelper dbHelper;
  private Animation fadeIn;
  private Animation fadeOut;
  private List<Items> filtered;
  private Boolean firstCalled;
  private ImageView imageView;
  private ImageView imgDelete;
  private ImageView imgRestore;
  private Boolean isCheckAll;
  private Boolean isCheckBoxVisible;
  private ArrayList<Boolean> itemChecked = new ArrayList();
  private List<Items> list = new ArrayList();
  private ListView listView;
  private Boolean selectAllChecked = Boolean.valueOf(false);
  private Boolean selectNoneChecked = Boolean.valueOf(false);
  private TextView textView;
  private Typeface tf;
  private TrashKeyArrayAdapter trashKeyArrayAdapter;
  private List<Items> values;
  private View viewSeperator;
  
  public TrashKeyArrayAdapter(Context paramContext, List<Items> paramList, ListView paramListView, Boolean paramBoolean1, Boolean paramBoolean2, Boolean paramBoolean3)
  {
    super(paramContext, 2130903081, paramList);
    this.context = paramContext;
    this.allData = new ArrayList(paramList.size());
    Iterator localIterator = paramList.iterator();
    int i;
    while (localIterator.hasNext())
    {	
    	Items localItems = (Items)localIterator.next();
    	this.allData.add(localItems);         
    }
    this.filtered = paramList;
    this.values = this.filtered;
    this.listView = paramListView;
    this.isCheckBoxVisible = paramBoolean1;
    this.isCheckAll = paramBoolean2;
    this.firstCalled = paramBoolean3;   
    for (int j = 0;; j++)
    {
      if (j >= getCount())
      {
        PasswordPanacea.setRemoveRestoreCalled(false);
        return;
      }
      this.itemChecked.add(j, Boolean.valueOf(false));
      this.list.add(new Items(((Items)paramList.get(j)).getItemId(), ((Items)paramList.get(j)).getItemTitle()));
      PasswordPanacea.setSelectedItems(this.list);
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
      paramView = ((LayoutInflater)this.context.getSystemService("layout_inflater")).inflate(2130903081, null);
    }
    this.tf = Typeface.createFromAsset(this.context.getAssets(), "fonts/galette.otf");
    this.fadeIn = AnimationUtils.loadAnimation(this.context, 2130968582);
    this.fadeOut = AnimationUtils.loadAnimation(this.context, 2130968591);
    this.textView = ((TextView)paramView.findViewById(2131296454));
    this.imageView = ((ImageView)paramView.findViewById(2131296286));
    this.checkListItem = ((CheckBox)paramView.findViewById(2131296453));
    this.textView.setTypeface(this.tf);
    this.textView.setText(((Items)this.values.get(paramInt)).getItemTitle());
    this.imgDelete = ((ImageView)paramView.findViewById(2131296633));
    this.imgRestore = ((ImageView)paramView.findViewById(2131296632));
    this.viewSeperator = paramView.findViewById(2131296634);
    this.dbHelper = new DBHelper(this.context);
    if (this.isCheckBoxVisible.booleanValue())
    {
      this.imageView.setVisibility(8);
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
        PasswordPanacea.setSelectedItems(this.list);
      }
      if (this.selectNoneChecked.booleanValue())
      {
        this.checkListItem.setChecked(false);
        this.itemChecked.set(paramInt, Boolean.valueOf(false));
        this.list.clear();
        PasswordPanacea.setSelectedItems(this.list);
      }
    }
    
      this.checkListItem.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          CheckBox localCheckBox;
          if (TrashKeyArrayAdapter.this.isCheckBoxVisible.booleanValue())
          {
            TrashKeyArrayAdapter.this.selectAllChecked = Boolean.valueOf(true);
            TrashKeyArrayAdapter.this.selectNoneChecked = Boolean.valueOf(true);
            localCheckBox = (CheckBox)paramAnonymousView.findViewById(2131296453);
            if (!localCheckBox.isChecked()) {
            	TrashKeyArrayAdapter.this.itemChecked.set(paramInt, Boolean.valueOf(false));
            	if (!TrashKeyArrayAdapter.this.list.contains(((Items)TrashKeyArrayAdapter.this.values.get(paramInt)).getItemTitle()))
	            {
            		for (int i = 0;i < TrashKeyArrayAdapter.this.list.size(); i++)
                    {
                        if (((Items)TrashKeyArrayAdapter.this.list.get(i)).getItemTitle().equals(((Items)TrashKeyArrayAdapter.this.values.get(paramInt)).getItemTitle())) {
                        	TrashKeyArrayAdapter.this.list.remove(i);
                        	break;
                        }
                        
                    }
            		PasswordPanacea.setSelectedGroups(TrashKeyArrayAdapter.this.list);                    
	            }
            }else{
            	TrashKeyArrayAdapter.this.itemChecked.set(paramInt, Boolean.valueOf(true));
	            if (!TrashKeyArrayAdapter.this.list.contains(((Items)TrashKeyArrayAdapter.this.values.get(paramInt)).getItemTitle()))
	            {
	              TrashKeyArrayAdapter.this.list.add(new Items(((Items)TrashKeyArrayAdapter.this.values.get(paramInt)).getItemId(), ((Items)TrashKeyArrayAdapter.this.values.get(paramInt)).getItemTitle()));
	              PasswordPanacea.setSelectedGroups(TrashKeyArrayAdapter.this.list);
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
          final CustomDialog localCustomDialog = new CustomDialog(TrashKeyArrayAdapter.this.context, "Delete?", "确定要删除次密码吗?");
          localCustomDialog.show();
          localCustomDialog.customAlertBoxPositiveButton.setOnClickListener(new View.OnClickListener()
          {
            public void onClick(View paramAnonymous2View)
            {
              TrashKeyArrayAdapter.this.dbHelper.deleteKey(((Items)TrashKeyArrayAdapter.this.values.get(paramInt)).getItemId());
              TrashKeyArrayAdapter.this.showToast("密码删除成功.");
              TrashKeyArrayAdapter.this.values.remove(paramInt);
              localImageView.setSelected(false);
              PasswordPanacea.setGroupTabON(false);
              PasswordPanacea.setRemoveRestoreCalled(true);
              Intent localIntent = new Intent(TrashKeyArrayAdapter.this.context, RecycleBinActivity.class);
              localIntent.addFlags(65536);
              TrashKeyArrayAdapter.this.context.startActivity(localIntent);
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
      this.imgRestore.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          final ImageView localImageView = (ImageView)paramAnonymousView.findViewById(2131296632);
          localImageView.setSelected(true);
          final CustomDialog localCustomDialog = new CustomDialog(TrashKeyArrayAdapter.this.context, "Restore!", "确定要恢复此密码吗?");
          localCustomDialog.show();
          localCustomDialog.customAlertBoxPositiveButton.setOnClickListener(new View.OnClickListener()
          {
            public void onClick(View paramAnonymous2View)
            {
              int i = TrashKeyArrayAdapter.this.dbHelper.getGroupIdByKeyId(((Items)TrashKeyArrayAdapter.this.values.get(paramInt)).getItemId());
              TrashKeyArrayAdapter.this.dbHelper.getGroupRestoreIndividually(i);
              TrashKeyArrayAdapter.this.dbHelper.getKeyRestore(((Items)TrashKeyArrayAdapter.this.values.get(paramInt)).getItemId());
              TrashKeyArrayAdapter.this.showToast("密码恢复成功.");
              TrashKeyArrayAdapter.this.values.remove(paramInt);
              localImageView.setSelected(false);
              PasswordPanacea.setGroupTabON(false);
              PasswordPanacea.setRemoveRestoreCalled(true);
              Intent localIntent = new Intent(TrashKeyArrayAdapter.this.context, RecycleBinActivity.class);
              localIntent.addFlags(65536);
              TrashKeyArrayAdapter.this.context.startActivity(localIntent);
              localCustomDialog.dismiss();
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
      this.textView.setSelected(((Boolean)this.itemChecked.get(paramInt)).booleanValue());
      this.checkListItem.setChecked(((Boolean)this.itemChecked.get(paramInt)).booleanValue());
      final GestureDetector localGestureDetector = new GestureDetector(new MyGestureDetector(paramInt, this.checkListItem, this.itemChecked, this.textView));
      this.textView.setOnTouchListener(new View.OnTouchListener()
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
      if (TrashKeyArrayAdapter.this.isCheckBoxVisible.booleanValue())
      {
        TrashKeyArrayAdapter.this.selectAllChecked = Boolean.valueOf(true);
        TrashKeyArrayAdapter.this.selectNoneChecked = Boolean.valueOf(true);
        if (this.checkBoxItem.isChecked()) {
        	 this.textView.setSelected(false);
             this.checkBoxItem.setChecked(false);
             this.itemChecked.set(this.position, Boolean.valueOf(false));
             if (!TrashKeyArrayAdapter.this.list.contains(((Items)TrashKeyArrayAdapter.this.values.get(this.position)).getItemTitle()))
			{
            	 for (int i = 0;i<TrashKeyArrayAdapter.this.list.size(); i++)
                 {   
                     if (!((Items)TrashKeyArrayAdapter.this.list.get(i)).getItemTitle().equals(((Items)TrashKeyArrayAdapter.this.values.get(this.position)).getItemTitle())) {
                    	 TrashKeyArrayAdapter.this.list.remove(i);
                    	 break;
                     }
                     
                   }
                 }
             	PasswordPanacea.setSelectedItems(TrashKeyArrayAdapter.this.list);
             	
 	        }else{
	        this.textView.setSelected(true);
	        this.checkBoxItem.setChecked(true);
	        this.itemChecked.set(this.position, Boolean.valueOf(true));
	        if (!TrashKeyArrayAdapter.this.list.contains(((Items)TrashKeyArrayAdapter.this.values.get(this.position)).getItemTitle()))
	        {
	          TrashKeyArrayAdapter.this.list.add(new Items(((Items)TrashKeyArrayAdapter.this.values.get(this.position)).getItemId(), ((Items)TrashKeyArrayAdapter.this.values.get(this.position)).getItemTitle()));
	          PasswordPanacea.setSelectedItems(TrashKeyArrayAdapter.this.list);
	        }
 	        }
      	}

      	return super.onSingleTapUp(paramMotionEvent);     
      
    }
  }
}


/* Location:           D:\Apkdb\Craining\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     rdi.mobapp.passwordpanacea.adapter.TrashKeyArrayAdapter
 * JD-Core Version:    0.7.0.1
 */