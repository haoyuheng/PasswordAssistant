package com.hyh.passwordassitant.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import rdi.mobapp.passwordpanacea.adapter.MoreArrayAdapter;

public class MoreActivity
  extends BaseActivity
{
  private ImageButton btnMore;
  private ListView listView;
  private LinearLayout more;
  private MoreArrayAdapter moreArrayAdapter;
  private List<String> moreItems;
  private ImageButton optnMore;
  private Typeface tf;
  private TextView txtMore;
  private View viewRightShadow;
  
  public void headerMore(View paramView) {}
  
  public void onBackPressed()
  {
    Intent localIntent = new Intent(getApplicationContext(), GroupActivity.class);
    localIntent.addFlags(65536);
    startActivity(localIntent);
    finish();
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle, 2130903054, "Help");
    getWindow().setSoftInputMode(3);
    this.tf = Typeface.createFromAsset(getAssets(), "fonts/galette.otf");
    this.listView = ((ListView)findViewById(2131296437));
    this.optnMore = ((ImageButton)findViewById(2131296442));
    this.optnMore.setVisibility(8);
    this.moreItems = new ArrayList();
    this.btnMore = ((ImageButton)findViewById(2131296384));
    this.btnMore.setImageResource(2130837626);
    this.more = ((LinearLayout)findViewById(2131296383));
    this.more.setEnabled(false);
    this.txtMore = ((TextView)findViewById(2131296385));
    this.txtMore.setTextColor(Color.parseColor("#000000"));
    this.txtMore.setTypeface(this.tf);
    this.viewRightShadow = findViewById(2131296381);
    this.viewRightShadow.setVisibility(0);
    this.viewRightShadow.setBackgroundResource(2130837577);
    this.moreItems.add("概述");
    this.moreItems.add("FAQ");
    this.moreItems.add("反馈");
    this.moreItems.add("关于");
    this.moreArrayAdapter = new MoreArrayAdapter(this, this.moreItems, this.listView);
    this.listView.setAdapter(this.moreArrayAdapter);
  }
}


/* Location:           D:\Apkdb\Craining\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     rdi.mobapp.passwordpanacea.activity.MoreActivity
 * JD-Core Version:    0.7.0.1
 */