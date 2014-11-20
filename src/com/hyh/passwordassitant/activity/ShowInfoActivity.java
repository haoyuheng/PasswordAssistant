package com.hyh.passwordassitant.activity;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ShowInfoActivity
  extends Activity
{
  private TextView header;
  private TextView helpMessage;
  private Typeface tf;
  private Typeface tf_title;
  private TextView txtOk;
  
  public void finishActivity(View paramView)
  {
    finish();
  }
  
  public void onBackPressed()
  {
    finish();
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    requestWindowFeature(1);
    setContentView(2130903072);
    this.tf = Typeface.createFromAsset(getAssets(), "fonts/galette.otf");
    this.tf_title = Typeface.createFromAsset(getAssets(), "fonts/deepblue.ttf");
    this.header = ((TextView)findViewById(2131296441));
    this.helpMessage = ((TextView)findViewById(2131296611));
    this.txtOk = ((TextView)findViewById(2131296612));
    this.header.setTypeface(this.tf_title);
    this.helpMessage.setTypeface(this.tf);
    this.txtOk.setTypeface(this.tf);
  }
}


/* Location:           D:\Apkdb\Craining\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     rdi.mobapp.passwordpanacea.activity.ShowInfoActivity
 * JD-Core Version:    0.7.0.1
 */