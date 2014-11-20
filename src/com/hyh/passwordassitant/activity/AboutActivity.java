package com.hyh.passwordassitant.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import rdi.mobapp.passwordpanacea.ExpandAnimation;

public class AboutActivity
  extends Activity
{
  private Animation fadeIn;
  private ImageView imgLogo;
  private RelativeLayout layoutAboutContainer;
  private TextView txtTitle;
  private TextView txtVersion;
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903040);
    getWindow().setSoftInputMode(3);
    this.fadeIn = AnimationUtils.loadAnimation(this, 2130968582);
    this.imgLogo = ((ImageView)findViewById(2131296256));
    this.imgLogo.startAnimation(this.fadeIn);
    this.txtVersion = ((TextView)findViewById(2131296257));
    this.txtVersion.startAnimation(this.fadeIn);
    this.txtTitle = ((TextView)findViewById(2131296258));
    this.txtTitle.startAnimation(this.fadeIn);
    this.layoutAboutContainer = ((RelativeLayout)findViewById(2131296259));
    ExpandAnimation localExpandAnimation = new ExpandAnimation(this.layoutAboutContainer, 1000);
    this.layoutAboutContainer.startAnimation(localExpandAnimation);
  }
}


/* Location:           D:\Apkdb\Craining\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     rdi.mobapp.passwordpanacea.activity.AboutActivity
 * JD-Core Version:    0.7.0.1
 */