package rdi.mobapp.passwordpanacea.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import com.hyh.passwordassitant.activity.TemplateActivity;
import com.hyh.passwordassitant.activity.TemplateListActivity;

import rdi.mobapp.passwordpanacea.application.PasswordPanacea;
import rdi.mobapp.passwordpanacea.bean.Items;
import rdi.mobapp.passwordpanacea.databasehelper.DBHelper;
import rdi.mobapp.passwordpanacea.utility.CustomDialog;
import rdi.mobapp.passwordpanacea.utility.CustomDialogNewField;
import rdi.mobapp.passwordpanacea.utility.CustomDialogSingleButton;

public class TemplateListArrayAdapter
  extends ArrayAdapter<Items>
{
  private CheckBox checkListItem;
  private Context context;
  private DBHelper dbHelper;
  private Drawable errorIcon;
  private Animation fadeIn;
  private Animation fadeOut;
  private Boolean firstCalled;
  private ImageView iconTemplate;
  private ImageButton imgEdit;
  private ImageButton imgRemove;
  private Boolean isCheckAll;
  private Boolean isCheckBoxVisible;
  private Boolean isRemove;
  private ArrayList<Boolean> itemChecked = new ArrayList();
  private List<Items> listItem = new ArrayList();
  private ListView listView;
  private Boolean selectAllChecked = Boolean.valueOf(false);
  private Boolean selectNoneChecked = Boolean.valueOf(false);
  private ArrayList<Items> templateList = new ArrayList();
  private TemplateListArrayAdapter templateListArrayAdapter;
  private Typeface tf;
  private TextView txtTemplateName;
  private View viewSeperator;
  
  public TemplateListArrayAdapter(Context paramContext, ArrayList<Items> paramArrayList, ListView paramListView, Boolean paramBoolean1, Boolean paramBoolean2, Boolean paramBoolean3, Boolean paramBoolean4)
  {
    super(paramContext, 2130903079, paramArrayList);
    this.context = paramContext;
    this.templateList = paramArrayList;
    this.listView = paramListView;
    this.isCheckBoxVisible = paramBoolean1;
    this.isCheckAll = paramBoolean2;
    this.isRemove = paramBoolean3;
    this.firstCalled = paramBoolean4;
   
    for (int j = 0;j < getCount(); j++)
    {
      
      this.itemChecked.add(j, Boolean.valueOf(false));       
      this.listItem.add(new Items(((Items)this.templateList.get(j)).getItemId(), ((Items)this.templateList.get(j)).getItemTitle()));
    }
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
      paramView = ((LayoutInflater)this.context.getSystemService("layout_inflater")).inflate(2130903079, null);
    }
    this.dbHelper = new DBHelper(this.context);
    this.errorIcon = this.context.getResources().getDrawable(2130837593);
    this.errorIcon.setBounds(new Rect(0, 0, this.errorIcon.getIntrinsicWidth(), this.errorIcon.getIntrinsicHeight()));
    this.tf = Typeface.createFromAsset(this.context.getAssets(), "fonts/galette.otf");
    this.fadeIn = AnimationUtils.loadAnimation(this.context, 2130968576);
    this.fadeOut = AnimationUtils.loadAnimation(this.context, 2130968577);
    this.txtTemplateName = ((TextView)paramView.findViewById(2131296626));
    this.imgEdit = ((ImageButton)paramView.findViewById(2131296628));
    this.imgRemove = ((ImageButton)paramView.findViewById(2131296629));
    this.iconTemplate = ((ImageView)paramView.findViewById(2131296624));
    this.checkListItem = ((CheckBox)paramView.findViewById(2131296625));
    this.txtTemplateName.setText(((Items)this.templateList.get(paramInt)).getItemTitle());
    this.txtTemplateName.setTypeface(this.tf);
    this.viewSeperator = paramView.findViewById(2131296627);
    this.txtTemplateName.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (!TemplateListArrayAdapter.this.isCheckBoxVisible.booleanValue())
        {
          Intent localIntent = new Intent(TemplateListArrayAdapter.this.context, TemplateActivity.class);
          Bundle localBundle = new Bundle();
          localBundle.putInt("templateIdRef", ((Items)TemplateListArrayAdapter.this.templateList.get(paramInt)).getItemId());
          localBundle.putString("templateTitle", ((Items)TemplateListArrayAdapter.this.templateList.get(paramInt)).getItemTitle());
          localBundle.putString("activity", "TemplateListActivity");
          localIntent.putExtras(localBundle);
          TemplateListArrayAdapter.this.context.startActivity(localIntent);
        }else{
          CheckBox localCheckBox;
          TextView localTextView;
              TemplateListArrayAdapter.this.selectAllChecked = Boolean.valueOf(true);
              TemplateListArrayAdapter.this.selectNoneChecked = Boolean.valueOf(true);
              View localView = TemplateListArrayAdapter.this.listView.getChildAt(paramInt - TemplateListArrayAdapter.this.listView.getFirstVisiblePosition());
              localCheckBox = (CheckBox)localView.findViewById(2131296625);
              localTextView = (TextView)localView.findViewById(2131296626);
              if (localCheckBox.isChecked()) {
            	  localCheckBox.setChecked(false);
                  localTextView.setSelected(false);
                  TemplateListArrayAdapter.this.itemChecked.set(paramInt, Boolean.valueOf(false));
                  if(TemplateListArrayAdapter.this.listItem.contains(((Items)TemplateListArrayAdapter.this.templateList.get(paramInt)).getItemTitle())){
                	  for (int i = 0;i < TemplateListArrayAdapter.this.listItem.size(); i++)
                      {
                          if (((Items)TemplateListArrayAdapter.this.listItem.get(i)).getItemTitle().equals(((Items)TemplateListArrayAdapter.this.templateList.get(paramInt)).getItemTitle())) {
                        	  TemplateListArrayAdapter.this.listItem.remove(i);
                        	  break;
                          }
                      }
		              PasswordPanacea.setSelectedItems(TemplateListArrayAdapter.this.listItem);
		              System.out.println("PasswordPanacea.setSelectedItems(listItem);" + PasswordPanacea.getSelectedItems());                  
                  }
              }else{
            	  localCheckBox.setChecked(true);
                  localTextView.setSelected(true);
                  TemplateListArrayAdapter.this.itemChecked.set(paramInt, Boolean.valueOf(true));
  				  if(!TemplateListArrayAdapter.this.listItem.contains(((Items)TemplateListArrayAdapter.this.templateList.get(paramInt)).getItemTitle())){
  					TemplateListArrayAdapter.this.listItem.add(new Items(((Items)TemplateListArrayAdapter.this.templateList.get(paramInt)).getItemId(), ((Items)TemplateListArrayAdapter.this.templateList.get(paramInt)).getItemTitle()));
  		            PasswordPanacea.setSelectedItems(TemplateListArrayAdapter.this.listItem);
  		            System.out.println("PasswordPanacea.setSelectedItems(listItem);" + PasswordPanacea.getSelectedItems());  		           
                  }
              }
        }
              
      }
    });
    if (this.isCheckBoxVisible.booleanValue())
    {
      this.iconTemplate.setVisibility(8);
      this.checkListItem.setVisibility(0);
      this.viewSeperator.setVisibility(8);
      this.imgEdit.setVisibility(8);
      this.imgRemove.setVisibility(8);
    }
    if (this.isCheckAll.booleanValue()) {
      if (!this.selectAllChecked.booleanValue())
      {
        int j = 0;
        while (j < getCount()) {
        	this.checkListItem.setChecked(true);
            this.itemChecked.set(j, Boolean.valueOf(true));
            j++;
        }
        PasswordPanacea.setSelectedItems(this.listItem);
      }else{
    	  int i=0;
    	  while (i < getCount())
    	  {
        	this.checkListItem.setChecked(false);
            this.itemChecked.set(i, Boolean.valueOf(false));
            this.listItem.clear();
    	  }    	  
    	  PasswordPanacea.setSelectedItems(this.listItem);
      }
    }
    
        this.imgEdit.setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            final CustomDialogNewField localCustomDialogNewField = new CustomDialogNewField(TemplateListArrayAdapter.this.context, "重命名", "请输入新的模板名称: ");
            final ImageButton localImageButton = (ImageButton)paramAnonymousView.findViewById(2131296628);
            localImageButton.setSelected(true);
            if (((Items)TemplateListArrayAdapter.this.templateList.get(paramInt)).getItemId() != 1)
            {
              localCustomDialogNewField.show();
              localCustomDialogNewField.customAlertBoxField.append(((Items)TemplateListArrayAdapter.this.templateList.get(paramInt)).getItemTitle());
              localCustomDialogNewField.customAlertBoxField.setSelectAllOnFocus(true);
              localCustomDialogNewField.customAlertBoxNegitiveButton.setOnClickListener(new View.OnClickListener()
              {
                public void onClick(View paramAnonymous2View)
                {
                  localCustomDialogNewField.customAlertBoxErrorMsg.setVisibility(4);
                  localCustomDialogNewField.dismiss();
                  localCustomDialogNewField.customAlertBoxField.setText("");
                  localImageButton.setSelected(false);
                }
              });
              localCustomDialogNewField.customAlertBoxPositiveButton.setText("EDIT");
              localCustomDialogNewField.customAlertBoxPositiveButton.setOnClickListener(new View.OnClickListener()
              {
                public void onClick(View paramAnonymous2View)
                {
                  localCustomDialogNewField.customAlertBoxField.setText(localCustomDialogNewField.customAlertBoxField.getText().toString().trim());
                  if (localCustomDialogNewField.customAlertBoxField.getText().toString().length() != 0)
                  {
                    if ((TemplateListArrayAdapter.this.dbHelper.templateAlreadyExists(localCustomDialogNewField.customAlertBoxField.getText().toString()) == 0) || (localCustomDialogNewField.customAlertBoxField.getText().toString().equalsIgnoreCase(((Items)TemplateListArrayAdapter.this.templateList.get(paramInt)).getItemTitle())))
                    {
                      TemplateListArrayAdapter.this.dbHelper.updateTemplateName(((Items)TemplateListArrayAdapter.this.templateList.get(paramInt)).getItemId(), localCustomDialogNewField.customAlertBoxField.getText().toString());
                      localCustomDialogNewField.dismiss();
                      TemplateListArrayAdapter.this.templateList = TemplateListArrayAdapter.this.dbHelper.getTemplateList();
                      TemplateListArrayAdapter.this.templateListArrayAdapter = new TemplateListArrayAdapter(TemplateListArrayAdapter.this.context, TemplateListArrayAdapter.this.templateList, TemplateListArrayAdapter.this.listView, Boolean.valueOf(false), Boolean.valueOf(false), Boolean.valueOf(false), Boolean.valueOf(false));
                      TemplateListArrayAdapter.this.templateListArrayAdapter.notifyDataSetChanged();
                      TemplateListArrayAdapter.this.listView.setAdapter(TemplateListArrayAdapter.this.templateListArrayAdapter);
                      return;
                    }
                    localCustomDialogNewField.customAlertBoxErrorMsg.setVisibility(0);
                    localCustomDialogNewField.customAlertBoxErrorMsg.setText("模板命名重复.");
                    return;
                  }
                  localCustomDialogNewField.customAlertBoxErrorMsg.setVisibility(0);
                  localCustomDialogNewField.customAlertBoxErrorMsg.setText("请输入模板名称.");
                }
              });
              return;
            }
            final CustomDialogSingleButton localCustomDialogSingleButton = new CustomDialogSingleButton(TemplateListArrayAdapter.this.context, "Invalid!", "默认模板无法删除.");
            localCustomDialogNewField.dismiss();
            localCustomDialogSingleButton.show();
            localCustomDialogSingleButton.customAlertBoxPositiveButton.setOnClickListener(new View.OnClickListener()
            {
              public void onClick(View paramAnonymous2View)
              {
                localImageButton.setSelected(false);
                localCustomDialogSingleButton.dismiss();
              }
            });
          }
        });
        this.imgRemove.setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            final ImageButton localImageButton = (ImageButton)paramAnonymousView.findViewById(2131296629);
            localImageButton.setSelected(true);
            final CustomDialog localCustomDialog = new CustomDialog(TemplateListArrayAdapter.this.context, "Delete?", "删除模板 \"" + ((Items)TemplateListArrayAdapter.this.templateList.get(paramInt)).getItemTitle() + "\" 将会删除与之相关的密码.");
            if (((Items)TemplateListArrayAdapter.this.templateList.get(paramInt)).getItemId() != 1)
            {
              localCustomDialog.show();
              localCustomDialog.customAlertBoxPositiveButton.setOnClickListener(new View.OnClickListener()
              {
                public void onClick(View paramAnonymous2View)
                {
                  TemplateListArrayAdapter.this.dbHelper.deleteTemplate(((Items)TemplateListArrayAdapter.this.templateList.get(paramInt)).getItemId());
                  localCustomDialog.dismiss();
                  TemplateListArrayAdapter.this.templateList.remove(paramInt);
                  Intent localIntent = new Intent(TemplateListArrayAdapter.this.context, TemplateListActivity.class);
                  localIntent.addFlags(65536);
                  TemplateListArrayAdapter.this.context.startActivity(localIntent);
                }
              });
              localCustomDialog.customAlertBoxNegitiveButton.setOnClickListener(new View.OnClickListener()
              {
                public void onClick(View paramAnonymous2View)
                {
                  localImageButton.setSelected(false);
                  localCustomDialog.dismiss();
                }
              });
              return;
            }
            final CustomDialogSingleButton localCustomDialogSingleButton = new CustomDialogSingleButton(TemplateListArrayAdapter.this.context, "Invalid!", "默认模板无法删除.");
            localCustomDialog.dismiss();
            localCustomDialogSingleButton.show();
            localCustomDialogSingleButton.customAlertBoxPositiveButton.setOnClickListener(new View.OnClickListener()
            {
              public void onClick(View paramAnonymous2View)
              {
                localImageButton.setSelected(false);
                localCustomDialogSingleButton.dismiss();
              }
            });
          }
        });
        this.checkListItem.setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            TemplateListArrayAdapter.this.selectAllChecked = Boolean.valueOf(true);
            TemplateListArrayAdapter.this.selectNoneChecked = Boolean.valueOf(true);
            CheckBox localCheckBox = (CheckBox)paramAnonymousView;
            if (localCheckBox.isChecked())
            {
              TemplateListArrayAdapter.this.itemChecked.set(paramInt, Boolean.valueOf(true));
              if (!TemplateListArrayAdapter.this.listItem.contains(((Items)TemplateListArrayAdapter.this.templateList.get(paramInt)).getItemTitle()))
              {
                TemplateListArrayAdapter.this.listItem.add(new Items(((Items)TemplateListArrayAdapter.this.templateList.get(paramInt)).getItemId(), ((Items)TemplateListArrayAdapter.this.templateList.get(paramInt)).getItemTitle()));
                PasswordPanacea.setSelectedItems(TemplateListArrayAdapter.this.listItem);
              }
            }else{
            	TemplateListArrayAdapter.this.itemChecked.set(paramInt, Boolean.valueOf(false));
            	 for (int i = 0;i < TemplateListArrayAdapter.this.listItem.size(); i++)
                 {
                     if (!((Items)TemplateListArrayAdapter.this.listItem.get(i)).getItemTitle().equals(((Items)TemplateListArrayAdapter.this.templateList.get(paramInt)).getItemTitle())) {
                    	 TemplateListArrayAdapter.this.listItem.remove(i);
                    	 break;
                     }
                 }
            	 PasswordPanacea.setSelectedItems(TemplateListArrayAdapter.this.listItem);
            }
          }
        });
        this.checkListItem.setChecked(((Boolean)this.itemChecked.get(paramInt)).booleanValue());
        this.txtTemplateName.setSelected(((Boolean)this.itemChecked.get(paramInt)).booleanValue());
        return paramView;       
    
  }
}


/* Location:           D:\Apkdb\Craining\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     rdi.mobapp.passwordpanacea.adapter.TemplateListArrayAdapter
 * JD-Core Version:    0.7.0.1
 */