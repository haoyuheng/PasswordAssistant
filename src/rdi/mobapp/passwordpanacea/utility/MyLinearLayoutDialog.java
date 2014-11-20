package rdi.mobapp.passwordpanacea.utility;

import android.app.Activity;
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

public class MyLinearLayoutDialog
  extends LinearLayout
{
  private MyLinearLayoutDialog container;
  private LinearLayout footer;
  private Activity mMyListActivity;
  private int valInDp;
  private int valInPx;
  
  public MyLinearLayoutDialog(Context paramContext)
  {
    super(paramContext);
  }
  
  public MyLinearLayoutDialog(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }
  
  protected void onSizeChanged(int paramInt1, final int paramInt2, int paramInt3, final int paramInt4)
  {
    if (this.mMyListActivity != null)
    {
      this.footer = ((LinearLayout)this.mMyListActivity.findViewById(2131296293));
      this.container = ((MyLinearLayoutDialog)this.mMyListActivity.findViewById(2131296275));
      this.valInPx = 50;
      this.valInDp = ((int)TypedValue.applyDimension(1, this.valInPx, getResources().getDisplayMetrics()));
      ((ViewGroup.MarginLayoutParams)this.container.getLayoutParams()).setMargins(0, this.valInDp, 0, 0);
      if (paramInt2 < paramInt4)
      {
    	  this.footer.setVisibility(8);
          ((ViewGroup.MarginLayoutParams)this.container.getLayoutParams()).setMargins(0, this.valInDp, 0, 0);
        }
      new Handler(new Handler.Callback()
      {
        public boolean handleMessage(Message paramAnonymousMessage)
        {
          MyLinearLayoutDialog.this.footer.invalidate();
          MyLinearLayoutDialog.this.footer.requestLayout();
          if (paramInt2 > paramInt4) {
            MyLinearLayoutDialog.this.footer.setVisibility(0);
          }
          return true;
        }
      }).sendEmptyMessage(0);
    }
    super.onSizeChanged(paramInt1, paramInt2, paramInt3, paramInt4);
  }
  
  public void setMyListActivity(Activity paramActivity)
  {
    this.mMyListActivity = paramActivity;
  }
}


/* Location:           D:\Apkdb\Craining\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     rdi.mobapp.passwordpanacea.utility.MyLinearLayoutDialog
 * JD-Core Version:    0.7.0.1
 */