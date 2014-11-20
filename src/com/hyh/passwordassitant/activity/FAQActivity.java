package com.hyh.passwordassitant.activity;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import rdi.mobapp.passwordpanacea.ExpandAnimation;

public class FAQActivity
  extends Activity
{
  private RelativeLayout[] faqContainer = new RelativeLayout[12];
  private LinearLayout[] faqLayout = new LinearLayout[12];
  private Animation[] slideLeftIn = new Animation[12];
  private TextView[] txtFaq = new TextView[12];
  private TextView txtHeaderFaq;
  
  public void collapseExpandedViews()
  {
    for (int i = 1;; i++)
    {
      if (i >= 12) {
        return;
      }
      if (this.faqContainer[i].getVisibility() == 0)
      {
        ExpandAnimation localExpandAnimation = new ExpandAnimation(this.faqContainer[i], 300);
        this.faqContainer[i].startAnimation(localExpandAnimation);
      }
    }
  }
  
  public void disableViewsTillAnimationDone()
  {
    for (int i = 1;; i++)
    {
      if (i >= 12)
      {
        new Handler().postDelayed(new Runnable()
        {
          public void run()
          {
            for (int i = 1;; i++)
            {
              if (i >= 12) {
                return;
              }
              FAQActivity.this.txtFaq[i].setClickable(true);
              FAQActivity.this.txtFaq[i].setEnabled(true);
            }
          }
        }, 300L);
        return;
      }
      this.txtFaq[i].setClickable(false);
      this.txtFaq[i].setEnabled(false);
    }
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    requestWindowFeature(1);
    setContentView(2130903049);
    Typeface localTypeface = Typeface.createFromAsset(getAssets(), "fonts/deepblue.ttf");
    this.txtHeaderFaq = ((TextView)findViewById(2131296317));
    this.txtHeaderFaq.setTypeface(localTypeface);
    
    for(int i=1;i<=11;i++){
    	 int i1 = getResources().getIdentifier("txt_faq_" + i, "id", getPackageName());
         this.txtFaq[i] = ((TextView)findViewById(i1));
         int m = getResources().getIdentifier("layout_faq_" + i + "_container", "id", getPackageName());
         this.faqContainer[i] = ((RelativeLayout)findViewById(m));
         int j = getResources().getIdentifier("layout_faq_" + i, "id", getPackageName());
         this.faqLayout[i] = ((LinearLayout)findViewById(j));
         this.slideLeftIn[i] = AnimationUtils.loadAnimation(this, 2130968583);
         this.slideLeftIn[i].setDuration(100 + i * 100);
         this.faqLayout[i].startAnimation(this.slideLeftIn[i]);
         final int kk = i;
         this.txtFaq[kk].setOnClickListener(new View.OnClickListener()
         {
           public void onClick(View paramAnonymousView)
           {
             FAQActivity.this.disableViewsTillAnimationDone();
             FAQActivity.this.collapseExpandedViews();
             ExpandAnimation localExpandAnimation = new ExpandAnimation(FAQActivity.this.faqContainer[kk], 300);
             FAQActivity.this.faqContainer[kk].startAnimation(localExpandAnimation);
           }
         });
         
    }
    
  }
}


/* Location:           D:\Apkdb\Craining\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     rdi.mobapp.passwordpanacea.activity.FAQActivity
 * JD-Core Version:    0.7.0.1
 */