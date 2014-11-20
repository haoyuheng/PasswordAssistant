package rdi.mobapp.passwordpanacea.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import rdi.mobapp.passwordpanacea.application.PasswordPanacea;
import rdi.mobapp.passwordpanacea.bean.GetCustomKeyFields;
import rdi.mobapp.passwordpanacea.databasehelper.DBHelper;
import rdi.mobapp.passwordpanacea.utility.CustomDialog;
import rdi.mobapp.passwordpanacea.utility.CustomDialogNewField;

public class TemplateArrayAdapter
  extends ArrayAdapter<GetCustomKeyFields>
{
  private ImageButton btnEdit;
  private ImageButton btnRemove;
  private final Context context;
  private DBHelper dbHelper;
  private Drawable errorIcon;
  private ArrayList<String> itemChecked = new ArrayList();
  private LinearLayout layoutTemplateItemTools;
  private ListView listView;
  private TemplateArrayAdapter templateArrayAdapter;
  private int templateId;
  private String templateTitle;
  private Typeface tf;
  private TextView txtFieldName;
  private List<GetCustomKeyFields> values;
  
  public TemplateArrayAdapter(Context paramContext, List<GetCustomKeyFields> paramList, ListView paramListView, int paramInt, String paramString)
  {
    super(paramContext, 2130903077, paramList);
    this.context = paramContext;
    this.values = paramList;
    this.listView = paramListView;
    this.templateId = paramInt;
    this.templateTitle = paramString;
    for (int i = 0;; i++)
    {
      if (i >= getCount()) {
        return;
      }
      this.itemChecked.add(i, "show_gray");
    }
  }
  
  public View getView(final int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    if (paramView == null) {
      paramView = ((LayoutInflater)this.context.getSystemService("layout_inflater")).inflate(2130903077, null);
    }
    this.errorIcon = this.context.getResources().getDrawable(2130837593);
    this.errorIcon.setBounds(new Rect(0, 0, this.errorIcon.getIntrinsicWidth(), this.errorIcon.getIntrinsicHeight()));
    this.tf = Typeface.createFromAsset(this.context.getAssets(), "fonts/galette.otf");
    this.txtFieldName = ((TextView)paramView.findViewById(2131296622));
    this.txtFieldName.setTypeface(this.tf);
    this.txtFieldName.setText(((GetCustomKeyFields)this.values.get(paramInt)).getCustomKeyFieldTitle());
    this.btnRemove = ((ImageButton)paramView.findViewById(2131296520));
    this.btnEdit = ((ImageButton)paramView.findViewById(2131296519));
    this.layoutTemplateItemTools = ((LinearLayout)paramView.findViewById(2131296513));
    this.dbHelper = new DBHelper(this.context);
    this.btnRemove.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        final ImageButton localImageButton = (ImageButton)paramAnonymousView.findViewById(2131296520);
        localImageButton.setSelected(true);
        final CustomDialog localCustomDialog = new CustomDialog(TemplateArrayAdapter.this.context, "Delete?", "删除 \"" + ((GetCustomKeyFields)TemplateArrayAdapter.this.values.get(paramInt)).getCustomKeyFieldTitle() + "\" 将同时删除与之相关的所有数据.");
        localCustomDialog.show();
        localCustomDialog.customAlertBoxNegitiveButton.setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymous2View)
          {
            localCustomDialog.dismiss();
            localImageButton.setSelected(false);
          }
        });
        localCustomDialog.customAlertBoxPositiveButton.setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymous2View)
          {
            TemplateArrayAdapter.this.dbHelper.deleteTemplateField(((GetCustomKeyFields)TemplateArrayAdapter.this.values.get(paramInt)).getCustomKeyFieldId());
            TemplateArrayAdapter.this.values.remove(paramInt);
            if (PasswordPanacea.getKeyEnteredFields() != null) {
              PasswordPanacea.getKeyEnteredFields().remove(paramInt);
            }
            TemplateArrayAdapter.this.templateArrayAdapter = new TemplateArrayAdapter(TemplateArrayAdapter.this.context, TemplateArrayAdapter.this.values, TemplateArrayAdapter.this.listView, paramInt, TemplateArrayAdapter.this.templateTitle);
            TemplateArrayAdapter.this.templateArrayAdapter.notifyDataSetChanged();
            TemplateArrayAdapter.this.listView.setAdapter(TemplateArrayAdapter.this.templateArrayAdapter);
            localImageButton.setSelected(false);
            localCustomDialog.dismiss();
          }
        });
      }
    });
    this.btnEdit.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        final ImageButton localImageButton = (ImageButton)paramAnonymousView.findViewById(2131296519);
        localImageButton.setSelected(true);
        final CustomDialogNewField localCustomDialogNewField = new CustomDialogNewField(TemplateArrayAdapter.this.context, "编辑条目", "请输入条目名称: ");
        localCustomDialogNewField.show();
        localCustomDialogNewField.customAlertBoxField.append(((GetCustomKeyFields)TemplateArrayAdapter.this.values.get(paramInt)).getCustomKeyFieldTitle());
        localCustomDialogNewField.customAlertBoxField.setSelectAllOnFocus(true);
        localCustomDialogNewField.customAlertBoxNegitiveButton.setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymous2View)
          {
            localCustomDialogNewField.customAlertBoxField.setText("");
            localCustomDialogNewField.customAlertBoxErrorMsg.setVisibility(4);
            localCustomDialogNewField.dismiss();
            localImageButton.setSelected(false);
          }
        });
        localCustomDialogNewField.customAlertBoxPositiveButton.setText("编辑");
        localCustomDialogNewField.customAlertBoxPositiveButton.setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymous2View)
          {
            localCustomDialogNewField.customAlertBoxField.setText(localCustomDialogNewField.customAlertBoxField.getText().toString().trim());
            if (localCustomDialogNewField.customAlertBoxField.getText().toString().length() != 0)
            {
              if ((TemplateArrayAdapter.this.dbHelper.templateFieldAlreadyExists(TemplateArrayAdapter.this.templateId, localCustomDialogNewField.customAlertBoxField.getText().toString()) == 0) || (localCustomDialogNewField.customAlertBoxField.getText().toString().equalsIgnoreCase(((GetCustomKeyFields)TemplateArrayAdapter.this.values.get(paramInt)).getCustomKeyFieldTitle())))
              {
                TemplateArrayAdapter.this.dbHelper.updateCustomKeyFieldTitle(localCustomDialogNewField.customAlertBoxField.getText().toString(), ((GetCustomKeyFields)TemplateArrayAdapter.this.values.get(paramInt)).getCustomKeyFieldTitle(), ((GetCustomKeyFields)TemplateArrayAdapter.this.values.get(paramInt)).getCustomKeyFieldId());
                ArrayList localArrayList = TemplateArrayAdapter.this.dbHelper.getCustomKeyFields(TemplateArrayAdapter.this.templateId);
                TemplateArrayAdapter.this.templateArrayAdapter = new TemplateArrayAdapter(TemplateArrayAdapter.this.context, localArrayList, TemplateArrayAdapter.this.listView, TemplateArrayAdapter.this.templateId, TemplateArrayAdapter.this.templateTitle);
                TemplateArrayAdapter.this.templateArrayAdapter.notifyDataSetChanged();
                TemplateArrayAdapter.this.listView.setAdapter(TemplateArrayAdapter.this.templateArrayAdapter);
                localCustomDialogNewField.dismiss();
                return;
              }
              localCustomDialogNewField.customAlertBoxErrorMsg.setVisibility(0);
              localCustomDialogNewField.customAlertBoxErrorMsg.setText("条目命名重复.");
              return;
            }
            localCustomDialogNewField.customAlertBoxErrorMsg.setVisibility(0);
            localCustomDialogNewField.customAlertBoxErrorMsg.setText("请输入条目名称.");
          }
        });
      }
    });
    if (paramInt < 4)
    {
      this.layoutTemplateItemTools.setVisibility(8);
      return paramView;
    }
    this.layoutTemplateItemTools.setVisibility(0);
    return paramView;
  }
}


/* Location:           D:\Apkdb\Craining\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     rdi.mobapp.passwordpanacea.adapter.TemplateArrayAdapter
 * JD-Core Version:    0.7.0.1
 */