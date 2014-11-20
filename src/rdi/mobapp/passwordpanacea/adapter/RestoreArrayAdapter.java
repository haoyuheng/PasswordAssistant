package rdi.mobapp.passwordpanacea.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import rdi.mobapp.passwordpanacea.bean.Elements;
import rdi.mobapp.passwordpanacea.utility.CustomDialog;

public class RestoreArrayAdapter
  extends ArrayAdapter<Elements>
{
  private List<Elements> allData;
  private final Context context;
  private ImageButton deleteBackUpFiles;
  private TextView fileName;
  private ListView listView;
  private RestoreArrayAdapter restoreArrayAdapter;
  private Typeface tf;
  private List<Elements> values;
  
  public RestoreArrayAdapter(Context paramContext, List<Elements> paramList, ListView paramListView)
  {
    super(paramContext, 2130903069, paramList);
    this.context = paramContext;
    this.allData = new ArrayList(paramList.size());
    Iterator localIterator = paramList.iterator();
    for (;;)
    {
      if (!localIterator.hasNext())
      {
        this.values = paramList;
        this.listView = paramListView;
        return;
      }
      Elements localElements = (Elements)localIterator.next();
      this.allData.add(localElements);
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
  
  public View getView(final int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    View localView = ((LayoutInflater)this.context.getSystemService("layout_inflater")).inflate(2130903069, paramViewGroup, false);
    this.tf = Typeface.createFromAsset(this.context.getAssets(), "fonts/galette.otf");
    this.fileName = ((TextView)localView.findViewById(2131296547));
    this.fileName.setTypeface(this.tf);
    this.fileName.setText(((Elements)this.values.get(paramInt)).getmText().replace('-', '/').replace('_', ':').replace("DBDetails", ""));
    this.deleteBackUpFiles = ((ImageButton)localView.findViewById(2131296548));
    this.deleteBackUpFiles.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        final ImageButton localImageButton = (ImageButton)paramAnonymousView.findViewById(2131296548);
        localImageButton.setSelected(true);
        final CustomDialog localCustomDialog = new CustomDialog(RestoreArrayAdapter.this.context, "Delete?", "确定要删除备份 \"" + ((Elements)RestoreArrayAdapter.this.values.get(paramInt)).getmText().replace('-', '/').replace('_', ':').replace("DBDetails", "") + "\"吗?");
        localCustomDialog.show();
        localCustomDialog.customAlertBoxPositiveButton.setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymous2View)
          {
            File localFile = new File(Environment.getExternalStorageDirectory(), ((Elements)RestoreArrayAdapter.this.values.get(paramInt)).getmText().replace('/', '-').replace(':', '_'));
            if (localFile.exists())
            {
            	localFile.delete();
            	RestoreArrayAdapter.this.showToast("\"" + ((Elements)RestoreArrayAdapter.this.values.get(paramInt)).getmText().replace('-', '/').replace('_', ':').replace("DBDetails", "") + "\" 已删除!");
            }else{
            	RestoreArrayAdapter.this.showToast("删除失败!");
            }
            
	          RestoreArrayAdapter.this.values.remove(paramInt);
	          RestoreArrayAdapter.this.restoreArrayAdapter = new RestoreArrayAdapter(RestoreArrayAdapter.this.context, RestoreArrayAdapter.this.values, RestoreArrayAdapter.this.listView);
	          RestoreArrayAdapter.this.restoreArrayAdapter.notifyDataSetChanged();
	          RestoreArrayAdapter.this.listView.setAdapter(RestoreArrayAdapter.this.restoreArrayAdapter);
	          localCustomDialog.dismiss();
	          return;
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
    this.fileName.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        final CustomDialog localCustomDialog = new CustomDialog(RestoreArrayAdapter.this.context, "Restore?", "确定恢复备份 \"" + ((Elements)RestoreArrayAdapter.this.values.get(paramInt)).getmText().replace('-', '/').replace('_', ':').replace("DBDetails", "") + "\"吗?");
        localCustomDialog.show();
        localCustomDialog.customAlertBoxPositiveButton.setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymous2View)
          {
            try
            {
              File localFile1 = Environment.getExternalStorageDirectory();
              File localFile2 = Environment.getDataDirectory();
              if (localFile1.canWrite())
              {
                String str = ((Elements)RestoreArrayAdapter.this.values.get(paramInt)).getmText();
                File localFile3 = new File(localFile2, "/data/com.hyh.passwordassitant.activity/databases/DBDetails");
                File localFile4 = new File(localFile1, str);
                if (!localFile4.exists()) {
                	RestoreArrayAdapter.this.showToast("备份文件不存在!");
                }else{
	                FileChannel localFileChannel1 = new FileInputStream(localFile4).getChannel();
	                FileChannel localFileChannel2 = new FileOutputStream(localFile3).getChannel();
	                localFileChannel2.transferFrom(localFileChannel1, 0L, localFileChannel1.size());
	                localFileChannel1.close();
	                localFileChannel2.close();
	                RestoreArrayAdapter.this.showToast("备份恢复成功!");
                }
              }
                RestoreArrayAdapter.this.restoreArrayAdapter = new RestoreArrayAdapter(RestoreArrayAdapter.this.context, RestoreArrayAdapter.this.values, RestoreArrayAdapter.this.listView);
                RestoreArrayAdapter.this.restoreArrayAdapter.notifyDataSetChanged();
                RestoreArrayAdapter.this.listView.setAdapter(RestoreArrayAdapter.this.restoreArrayAdapter);
                localCustomDialog.dismiss();
                return;
            }
            catch (Exception localException)
            {
                RestoreArrayAdapter.this.showToast("恢复失败!");
            }
          }
        });
      }
    });
    return localView;
  }
}


/* Location:           D:\Apkdb\Craining\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     rdi.mobapp.passwordpanacea.adapter.RestoreArrayAdapter
 * JD-Core Version:    0.7.0.1
 */