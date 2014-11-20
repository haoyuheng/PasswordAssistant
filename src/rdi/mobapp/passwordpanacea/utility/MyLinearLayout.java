package rdi.mobapp.passwordpanacea.utility;

import com.hyh.passwordassitant.activity.BaseActivity;

import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.LinearLayout;
import android.widget.TableRow;

public class MyLinearLayout
  extends LinearLayout
{
  private MyLinearLayout container;
  private LinearLayout footer;
  private BaseActivity mMyListActivity;
  private TableRow row;
  private int valInDp;
  private int valInPx;
  
  public MyLinearLayout(Context paramContext)
  {
    super(paramContext);
  }
  
  public MyLinearLayout(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }
  
  protected void onSizeChanged(int paramInt1, final int paramInt2, int paramInt3, final int paramInt4)
  {
    if (this.mMyListActivity != null)
    {
      this.footer = ((LinearLayout)this.mMyListActivity.findViewById(2131296293));
      this.container = ((MyLinearLayout)this.mMyListActivity.findViewById(2131296275));
      this.row = ((TableRow)findViewById(2131296290));
      this.valInPx = 50;
      this.valInDp = ((int)TypedValue.applyDimension(1, this.valInPx, getResources().getDisplayMetrics()));
      ((ViewGroup.MarginLayoutParams)this.container.getLayoutParams()).setMargins(0, this.valInDp, 0, 0);
      if (paramInt2 < paramInt4)
      {
        this.footer.setVisibility(8);
        this.row.setVisibility(8);
        ((ViewGroup.MarginLayoutParams)this.container.getLayoutParams()).setMargins(0, this.valInDp, 0, 0);
      }
      new Handler(new Handler.Callback()
      {
        public boolean handleMessage(Message paramAnonymousMessage)
        {
          MyLinearLayout.this.footer.invalidate();
          MyLinearLayout.this.footer.requestLayout();
          if (paramInt2 > paramInt4)
          {
            MyLinearLayout.this.footer.setVisibility(0);
            MyLinearLayout.this.row.setVisibility(4);
          }
          return true;
        }
      }).sendEmptyMessage(0);
    }
    super.onSizeChanged(paramInt1, paramInt2, paramInt3, paramInt4);
  }
  
  public void setMyListActivity(BaseActivity paramBaseActivity)
  {
    this.mMyListActivity = paramBaseActivity;
  }
}


/* Location:           D:\Apkdb\Craining\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     rdi.mobapp.passwordpanacea.utility.MyLinearLayout
 * JD-Core Version:    0.7.0.1
 */