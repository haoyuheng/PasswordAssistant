package rdi.mobapp.passwordpanacea.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import rdi.mobapp.passwordpanacea.application.PasswordPanacea;
import rdi.mobapp.passwordpanacea.bean.Items;
import rdi.mobapp.passwordpanacea.databasehelper.DBHelper;

public class SpinnerArrayAdapter
  extends ArrayAdapter<Items>
{
  private String GroupVal;
  private String activity;
  private int adapterCount;
  private ArrayList<Integer> arrayRadioSelected = new ArrayList();
  private ArrayList<Boolean> arrayTextSelected = new ArrayList();
  private Context context;
  private DBHelper dbHelper;
  private String defaultObject;
  private ImageView imgRadioBtn;
  private RelativeLayout layoutSpinnerDailog;
  private ListView listView;
  private Boolean rowClicked = Boolean.valueOf(false);
  private TextView spinnerItemTitle;
  private String templateVal;
  private Typeface tf;
  private TextView txtSpinner;
  private String type;
  private List<Items> values;
  
  public SpinnerArrayAdapter(Context paramContext, List<Items> paramList, ListView paramListView, RelativeLayout paramRelativeLayout, TextView paramTextView, String paramString1, String paramString2, String paramString3, String paramString4)
  {
    super(paramContext, 2130903073, paramList);
    this.context = paramContext;
    this.listView = paramListView;
    this.values = paramList;
    this.layoutSpinnerDailog = paramRelativeLayout;
    this.txtSpinner = paramTextView;
    this.adapterCount = getCount();
    this.activity = paramString1;
    this.type = paramString2;
    this.templateVal = paramString3;
    this.GroupVal = paramString4;
    for (int i = 0;; i++)
    {
      if (i >= this.adapterCount) {
        return;
      }
      this.arrayRadioSelected.add(i, Integer.valueOf(2130837647));
      this.arrayTextSelected.add(i, Boolean.valueOf(false));
    }
  }
  
  public View getView(final int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    if (paramView == null) {
      paramView = ((LayoutInflater)this.context.getSystemService("layout_inflater")).inflate(2130903073, null);
    }
    this.tf = Typeface.createFromAsset(this.context.getAssets(), "fonts/galette.otf");
    this.spinnerItemTitle = ((TextView)paramView.findViewById(2131296614));
    this.spinnerItemTitle.setTypeface(this.tf);
    this.spinnerItemTitle.setText(((Items)this.values.get(paramInt)).getItemTitle());
    this.imgRadioBtn = ((ImageView)paramView.findViewById(2131296615));
    this.dbHelper = new DBHelper(this.context);
    if (this.type.equalsIgnoreCase("Group")) 
    {
    	Log.e("group",((Items)this.values.get(paramInt)).getItemTitle().toString()+" "+PasswordPanacea.getGroupTitle().toString());
    	 if ((((Items)this.values.get(paramInt)).getItemTitle().toString().equalsIgnoreCase(PasswordPanacea.getGroupTitle().toString())) && (!this.rowClicked.booleanValue()))
         {
           this.arrayRadioSelected.set(paramInt, Integer.valueOf(2130837646));
           this.arrayTextSelected.set(paramInt, Boolean.valueOf(true));
         }
    	 
    }else{
    	Log.e("temple",((Items)this.values.get(paramInt)).getItemTitle().toString()+" "+PasswordPanacea.getTemplateSpinnerVal().toString());
    	if ((((Items)this.values.get(paramInt)).getItemTitle().toString().equalsIgnoreCase(PasswordPanacea.getTemplateSpinnerVal().toString())) && (!this.rowClicked.booleanValue()))
        {
          this.arrayRadioSelected.set(paramInt, Integer.valueOf(2130837646));
          this.arrayTextSelected.set(paramInt, Boolean.valueOf(true));
        }
    }
      paramView.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
        	
          SpinnerArrayAdapter.this.rowClicked = Boolean.valueOf(true);
          TextView localTextView = (TextView)paramAnonymousView.findViewById(2131296614);
          ImageView localImageView = (ImageView)paramAnonymousView.findViewById(2131296615);
          int i = 0;
          int k;
          while(i < SpinnerArrayAdapter.this.adapterCount){
        	  Log.e("b","b"+i);
        	  SpinnerArrayAdapter.this.arrayRadioSelected.set(i, Integer.valueOf(2130837647));
              SpinnerArrayAdapter.this.arrayTextSelected.set(i, Boolean.valueOf(false));
              i++;
          }
          SpinnerArrayAdapter.this.arrayRadioSelected.set(paramInt, Integer.valueOf(2130837646));
          SpinnerArrayAdapter.this.arrayTextSelected.set(paramInt, Boolean.valueOf(true));
          
          int kk = SpinnerArrayAdapter.this.listView.getLastVisiblePosition() - SpinnerArrayAdapter.this.listView.getFirstVisiblePosition();
          if(SpinnerArrayAdapter.this.type.equalsIgnoreCase("Template")){
        	  kk --;
          }        	  
          k = 0; 
          Log.e("size", " "+SpinnerArrayAdapter.this.listView.getCount()+" "+ SpinnerArrayAdapter.this.listView.getLastVisiblePosition()+" "+SpinnerArrayAdapter.this.listView.getFirstVisiblePosition());
          while (k <= kk ) {
        	  Log.e("h","h"+k);
        	  View localView2 = SpinnerArrayAdapter.this.listView.getChildAt(k);
              ((TextView)localView2.findViewById(2131296614)).setSelected(false);
              ((ImageView)localView2.findViewById(2131296615)).setImageResource(2130837647);
              k++;
          }	  
          
          localTextView.setSelected(true);
          localImageView.setImageResource(2130837646);
          new Handler().postDelayed(new Runnable()
          {
            public void run()
            {
              SpinnerArrayAdapter.this.layoutSpinnerDailog.setVisibility(8);
              SpinnerArrayAdapter.this.txtSpinner.setText(((Items)SpinnerArrayAdapter.this.values.get(paramInt)).getItemTitle().toString());
            }
          }, 800L);
          
          if (SpinnerArrayAdapter.this.type.equalsIgnoreCase("Group")){
        	  Log.e("ddfsdfsd",((Items)SpinnerArrayAdapter.this.values.get(paramInt)).getItemTitle().toString()+" "+SpinnerArrayAdapter.this.dbHelper.getGroupId(((Items)SpinnerArrayAdapter.this.values.get(paramInt)).getItemTitle().toString()));
        	  PasswordPanacea.setGroupTitle(((Items)SpinnerArrayAdapter.this.values.get(paramInt)).getItemTitle().toString());
              PasswordPanacea.setGroupMasterId(SpinnerArrayAdapter.this.dbHelper.getGroupId(((Items)SpinnerArrayAdapter.this.values.get(paramInt)).getItemTitle().toString()));
          }
          else if((SpinnerArrayAdapter.this.type.equalsIgnoreCase("Template")) && (!SpinnerArrayAdapter.this.templateVal.equalsIgnoreCase(((Items)SpinnerArrayAdapter.this.values.get(paramInt)).getItemTitle().toString())))
          {
        	  PasswordPanacea.setGroupTitle(SpinnerArrayAdapter.this.GroupVal);
	          PasswordPanacea.setGroupMasterId(SpinnerArrayAdapter.this.dbHelper.getGroupId(SpinnerArrayAdapter.this.GroupVal));
	          PasswordPanacea.setOldTemplateSpinnerVal(PasswordPanacea.getTemplateSpinnerVal().toString());
	          PasswordPanacea.setTemplateSpinnerVal(((Items)SpinnerArrayAdapter.this.values.get(paramInt)).getItemTitle().toString());
	          Intent localIntent = new Intent();
	          localIntent.setClassName("com.hyh.passwordassitant.activity", "com.hyh.passwordassitant.activity." + SpinnerArrayAdapter.this.activity);
	          SpinnerArrayAdapter.this.context.startActivity(localIntent);
	          return;
          }
        }
      });
      this.imgRadioBtn.setImageResource(((Integer)this.arrayRadioSelected.get(paramInt)).intValue());
      this.spinnerItemTitle.setSelected(((Boolean)this.arrayTextSelected.get(paramInt)).booleanValue());
      return paramView;
    
          
  }
}
      


/* Location:           D:\Apkdb\Craining\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     rdi.mobapp.passwordpanacea.adapter.SpinnerArrayAdapter
 * JD-Core Version:    0.7.0.1
 */