package li.who.you;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class qingjiao {
	Context mContext;
	public qingjiao(Context mContext){
		this.mContext = mContext;
	}
	public void startone(){
		Log.e("dds","fdsfdsafsdafdsafsda");
		Intent mainIntent = new Intent(mContext,li.who.you.LocationActivity.class); 
		mContext.startActivity(mainIntent); 

    }
}
