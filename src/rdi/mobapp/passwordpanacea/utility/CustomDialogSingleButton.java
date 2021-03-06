package rdi.mobapp.passwordpanacea.utility;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.TextView;

public class CustomDialogSingleButton
  extends Dialog
  implements View.OnClickListener
{
  private Context context;
  private TextView customAlertBoxMessage;
  public TextView customAlertBoxPositiveButton;
  private TextView customAlertBoxTitle;
  private String message;
  private Typeface tf;
  private Typeface tf_title;
  private String title;
  
  public CustomDialogSingleButton(Context paramContext, String paramString1, String paramString2)
  {
    super(paramContext);
    this.context = paramContext;
    this.title = paramString1;
    this.message = paramString2;
  }
  
  public CustomDialogSingleButton(Context paramContext,
		String paramString1, int stringid) {
	  super(paramContext);
	  this.context = paramContext;
	  this.title = paramString1;
	  this.message = this.context.getResources().getString(stringid);
}

public void onClick(View paramView)
  {
    dismiss();
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    requestWindowFeature(1);
    setContentView(2130903046);
    setCancelable(false);
    getWindow().setBackgroundDrawable(new ColorDrawable(0));
    this.tf_title = Typeface.createFromAsset(this.context.getAssets(), "fonts/deepblue.ttf");
    this.tf = Typeface.createFromAsset(this.context.getAssets(), "fonts/galette.otf");
    this.customAlertBoxTitle = ((TextView)findViewById(2131296307));
    this.customAlertBoxMessage = ((TextView)findViewById(2131296308));
    this.customAlertBoxPositiveButton = ((TextView)findViewById(2131296309));
    this.customAlertBoxTitle.setText(this.title);
    this.customAlertBoxTitle.setTypeface(this.tf_title);
    this.customAlertBoxMessage.setText(this.message);
    this.customAlertBoxMessage.setTypeface(this.tf);
    this.customAlertBoxPositiveButton.setTypeface(this.tf);
    this.customAlertBoxPositiveButton.setOnClickListener(this);
  }
}


/* Location:           D:\Apkdb\Craining\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     rdi.mobapp.passwordpanacea.utility.CustomDialogSingleButton
 * JD-Core Version:    0.7.0.1
 */