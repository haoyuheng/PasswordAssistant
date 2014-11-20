package rdi.mobapp.passwordpanacea.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import java.util.List;

import com.hyh.passwordassitant.activity.AboutActivity;
import com.hyh.passwordassitant.activity.FAQActivity;
import com.hyh.passwordassitant.activity.FeedbackActivity;
import com.hyh.passwordassitant.activity.HelpActivity;

public class MoreArrayAdapter
  extends ArrayAdapter<String>
{
  private Context context;
  private ListView listView;
  private StringBuilder message;
  private Typeface tf;
  private TextView title;
  private List<String> values;
  
  public MoreArrayAdapter(Context paramContext, List<String> paramList, ListView paramListView)
  {
    super(paramContext, 2130903062, paramList);
    this.context = paramContext;
    this.listView = paramListView;
    this.values = paramList;
  }
  
  public View getView(final int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    if (paramView == null) {
      paramView = ((LayoutInflater)this.context.getSystemService("layout_inflater")).inflate(2130903062, null);
    }
    this.tf = Typeface.createFromAsset(this.context.getAssets(), "fonts/galette.otf");
    this.title = ((TextView)paramView.findViewById(2131296395));
    this.title.setFocusable(false);
    this.title.setTypeface(this.tf);
    this.title.setText((CharSequence)this.values.get(paramInt));
    this.message = new StringBuilder();
    this.message.append("越来越多的账户密码是不是让你头疼不已，不用担心，万能密码助手（Password Assistant）就是一剂良药，让您不再烦恼.");
    this.message.append("\n");
    this.message.append("\n");
    this.message.append("万能密码助手（Password Assistant）就像一个保险箱，保存你所有的账户密码，而你需要的只是记住保险箱的密码，也就是我们的管理密码.");
    this.title.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (((String)MoreArrayAdapter.this.values.get(paramInt)).equalsIgnoreCase("概述"))
        {
          Intent localIntent1 = new Intent(MoreArrayAdapter.this.context, HelpActivity.class);
          localIntent1.addFlags(65536);
          MoreArrayAdapter.this.context.startActivity(localIntent1);
        }else if (((String)MoreArrayAdapter.this.values.get(paramInt)).equalsIgnoreCase("反馈"))
	      {
	        Intent localIntent2 = new Intent(MoreArrayAdapter.this.context, FeedbackActivity.class);
	        Bundle localBundle = new Bundle();
	        localBundle.putString("Activity", "MoreActivity");
	        localIntent2.putExtras(localBundle);
	        MoreArrayAdapter.this.context.startActivity(localIntent2);	        
	      }else if (((String)MoreArrayAdapter.this.values.get(paramInt)).equalsIgnoreCase("FAQ"))
          {
              Intent localIntent2 = new Intent(MoreArrayAdapter.this.context, FAQActivity.class);
              Bundle localBundle = new Bundle();
              localBundle.putString("Activity", "FAQActivity");
              localIntent2.putExtras(localBundle);
              MoreArrayAdapter.this.context.startActivity(localIntent2);             
        }else if(((String)MoreArrayAdapter.this.values.get(paramInt)).equalsIgnoreCase("关于")){
            Intent localIntent4 = new Intent(MoreArrayAdapter.this.context, AboutActivity.class);
            localIntent4.addFlags(65536);
            MoreArrayAdapter.this.context.startActivity(localIntent4);
        }       
      }
    });
    return paramView;
  }
}


/* Location:           D:\Apkdb\Craining\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     rdi.mobapp.passwordpanacea.adapter.MoreArrayAdapter
 * JD-Core Version:    0.7.0.1
 */