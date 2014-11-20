package rdi.mobapp.passwordpanacea;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.LinearLayout;

public class ExpandAnimation
  extends Animation
{
  private View mAnimatedView;
  private boolean mIsVisibleAfter = false;
  private int mMarginEnd;
  private int mMarginStart;
  private LinearLayout.LayoutParams mViewLayoutParams;
  private boolean mWasEndedAlready = false;
  /*
  public ExpandAnimation(View paramView, int paramInt)
  {
    setDuration(paramInt);
    this.mAnimatedView = paramView;
    this.mViewLayoutParams = ((LinearLayout.LayoutParams)paramView.getLayoutParams());
    boolean bool;
    if (paramView.getVisibility() == 0)
    {
      this.mIsVisibleAfter = true;
      this.mMarginStart = this.mViewLayoutParams.bottomMargin;
      if (this.mMarginStart != 0) {
    	  for (int i = 0 - paramView.getHeight();; i = 0)
    	    {
    	      this.mMarginEnd = i;
    	      paramView.setVisibility(0);
    	      return;
    	    }
      }
    }
  }
  */
  public ExpandAnimation(View view, int duration) {

      setDuration(duration);
      mAnimatedView = view;
      mViewLayoutParams = (LinearLayout.LayoutParams) view.getLayoutParams();

      // decide to show or hide the view
      mIsVisibleAfter = (view.getVisibility() == View.VISIBLE);

      mMarginStart = mViewLayoutParams.bottomMargin;
      mMarginEnd = (mMarginStart == 0 ? (0- view.getHeight()) : 0);

      view.setVisibility(View.VISIBLE);
  }

  
  protected void applyTransformation(float paramFloat, Transformation paramTransformation)
  {
    super.applyTransformation(paramFloat, paramTransformation);
    if (paramFloat < 1.0F)
    {
      this.mViewLayoutParams.bottomMargin = (this.mMarginStart + (int)(paramFloat * (this.mMarginEnd - this.mMarginStart)));
      this.mAnimatedView.requestLayout();
    }
    while (this.mWasEndedAlready) {
      return;
    }
    this.mViewLayoutParams.bottomMargin = this.mMarginEnd;
    this.mAnimatedView.requestLayout();
    if (this.mIsVisibleAfter) {
      this.mAnimatedView.setVisibility(8);
    }
    this.mWasEndedAlready = true;
  }
}


/* Location:           D:\Apkdb\Craining\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     rdi.mobapp.passwordpanacea.ExpandAnimation
 * JD-Core Version:    0.7.0.1
 */