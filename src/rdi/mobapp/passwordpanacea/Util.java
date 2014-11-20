package rdi.mobapp.passwordpanacea;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

public final class Util
{
  private static boolean ENABLE_LOG = false;
  
  public static void clearCookies(Context paramContext)
  {
    CookieSyncManager.createInstance(paramContext);
    CookieManager.getInstance().removeAllCookie();
  }
  
  public static Bundle decodeUrl(String paramString)
  {
    Bundle localBundle = new Bundle();
    String[] arrayOfString1;
    int i;
    if (paramString != null)
    {
      arrayOfString1 = paramString.split("&");
      i = arrayOfString1.length;
      for (int j = 0;j< i; j++)
      {
        String[] arrayOfString2 = arrayOfString1[j].split("=");
        if (arrayOfString2.length == 2) {
          localBundle.putString(URLDecoder.decode(arrayOfString2[0]), URLDecoder.decode(arrayOfString2[1]));
        }
      }
    }
    return localBundle;
  }
  
  public static String encodePostBody(Bundle paramBundle, String paramString)
  {
    if (paramBundle == null) {
      return "";
    }
    StringBuilder localStringBuilder = new StringBuilder();
    Iterator localIterator = paramBundle.keySet().iterator();
    for (;;)
    {
      if (!localIterator.hasNext()) {
        return localStringBuilder.toString();
      }
      String str = (String)localIterator.next();
      Object localObject = paramBundle.get(str);
      if ((localObject instanceof String))
      {
        localStringBuilder.append("Content-Disposition: form-data; name=\"" + str + "\"\r\n\r\n" + (String)localObject);
        localStringBuilder.append("\r\n--" + paramString + "\r\n");
      }
    }
  }
  
  public static String encodeUrl(Bundle paramBundle)
  {
    if (paramBundle == null) {
      return "";
    }
    StringBuilder localStringBuilder = new StringBuilder();
    
    Iterator localIterator = paramBundle.keySet().iterator();
    String str;
    while(localIterator.hasNext()){
    	str = (String)localIterator.next();
    	if((paramBundle.get(str) instanceof String)){
    		localStringBuilder.append(URLEncoder.encode(str) + "=" + URLEncoder.encode(paramBundle.getString(str)));
    	}else
    		localStringBuilder.append("&");
    }
    return localStringBuilder.toString();
   
  }
  
  public static void logd(String paramString1, String paramString2)
  {
    if (ENABLE_LOG) {
      Log.d(paramString1, paramString2);
    }
  }
  
  public static String openUrl(String paramString1, String paramString2, Bundle paramBundle)
    throws MalformedURLException, IOException
  {
    if (paramString2.equals("GET")) {
      paramString1 = paramString1 + "?" + encodeUrl(paramBundle);
    }
    logd("Facebook-Util", paramString2 + " URL: " + paramString1);
    HttpURLConnection localHttpURLConnection = (HttpURLConnection)new URL(paramString1).openConnection();
    localHttpURLConnection.setRequestProperty("User-Agent", System.getProperties().getProperty("http.agent") + " FacebookAndroidSDK");
    Bundle localBundle;
    Iterator localIterator1;
    BufferedOutputStream localBufferedOutputStream;
    Iterator localIterator2;
    if (!paramString2.equals("GET"))
    {
      localBundle = new Bundle();
      localIterator1 = paramBundle.keySet().iterator();
      if (localIterator1.hasNext()) {
    	  String str1 = (String)localIterator1.next();
          Object localObject = paramBundle.get(str1);
          if ((localObject instanceof byte[])) {
        	  localBundle.putByteArray(str1, (byte[])localObject);
          }          
      }
      if (!paramBundle.containsKey("method")) {
        paramBundle.putString("method", paramString2);
      }
      if (paramBundle.containsKey("access_token")) {
        paramBundle.putString("access_token", URLDecoder.decode(paramBundle.getString("access_token")));
      }
      localHttpURLConnection.setRequestMethod("POST");
      localHttpURLConnection.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + "3i2ndDfv2rTHiSisAbouNdArYfORhtTPEefj3q2f");
      localHttpURLConnection.setDoOutput(true);
      localHttpURLConnection.setDoInput(true);
      localHttpURLConnection.setRequestProperty("Connection", "Keep-Alive");
      localHttpURLConnection.connect();
      localBufferedOutputStream = new BufferedOutputStream(localHttpURLConnection.getOutputStream());
      localBufferedOutputStream.write(("--" + "3i2ndDfv2rTHiSisAbouNdArYfORhtTPEefj3q2f" + "\r\n").getBytes());
      localBufferedOutputStream.write(encodePostBody(paramBundle, "3i2ndDfv2rTHiSisAbouNdArYfORhtTPEefj3q2f").getBytes());
      localBufferedOutputStream.write(("\r\n" + "--" + "3i2ndDfv2rTHiSisAbouNdArYfORhtTPEefj3q2f" + "\r\n").getBytes());
      if (!localBundle.isEmpty()) {
        localIterator2 = localBundle.keySet().iterator();
        while(localIterator2.hasNext()){
	        String str3 = (String)localIterator2.next();
	        localBufferedOutputStream.write(("Content-Disposition: form-data; filename=\"" + str3 + "\"" + "\r\n").getBytes());
	        localBufferedOutputStream.write(("Content-Type: content/unknown" + "\r\n" + "\r\n").getBytes());
	        localBufferedOutputStream.write(localBundle.getByteArray(str3));
	        localBufferedOutputStream.write(("\r\n" + "--" + "3i2ndDfv2rTHiSisAbouNdArYfORhtTPEefj3q2f" + "\r\n").getBytes());
        }
        localBufferedOutputStream.flush();
      }
      try
      {
        String str2 = read(localHttpURLConnection.getInputStream());
        return str2;
      }
      catch (FileNotFoundException localFileNotFoundException) {
    	  
      }
      
    }
    
    return read(localHttpURLConnection.getErrorStream());
  }
  
  public static JSONObject parseJson(String paramString)
    throws JSONException
  {
    if (paramString.equals("false")) {
    }
    if (paramString.equals("true")) {
      paramString = "{value : true}";
    }
    JSONObject localJSONObject1 = new JSONObject(paramString);
    if (localJSONObject1.has("error"))
    {
      JSONObject localJSONObject2 = localJSONObject1.getJSONObject("error");
      }
    if ((localJSONObject1.has("error_code")) && (localJSONObject1.has("error_msg"))) {
     }
    if (localJSONObject1.has("error_code")) {
       }
    if (localJSONObject1.has("error_msg")) {
      }
    if (localJSONObject1.has("error_reason")) {
      }
    return localJSONObject1;
  }
  
  public static Bundle parseUrl(String paramString)
  {
    String str = paramString.replace("fbconnect", "http");
    try
    {
      URL localURL = new URL(str);
      Bundle localBundle = decodeUrl(localURL.getQuery());
      localBundle.putAll(decodeUrl(localURL.getRef()));
      return localBundle;
    }
    catch (MalformedURLException localMalformedURLException) {}
    return new Bundle();
  }
  
  private static String read(InputStream paramInputStream)
    throws IOException
  {
    StringBuilder localStringBuilder = new StringBuilder();
    BufferedReader localBufferedReader = new BufferedReader(new InputStreamReader(paramInputStream), 1000);
    for (String str = localBufferedReader.readLine();; str = localBufferedReader.readLine())
    {
      if (str == null)
      {
        paramInputStream.close();
        return localStringBuilder.toString();
      }
      localStringBuilder.append(str);
    }
  }
  
  public static void showAlert(Context paramContext, String paramString1, String paramString2)
  {
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(paramContext);
    localBuilder.setTitle(paramString1);
    localBuilder.setMessage(paramString2);
    localBuilder.create().show();
  }
}


/* Location:           D:\Apkdb\Craining\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     rdi.mobapp.passwordpanacea.Util
 * JD-Core Version:    0.7.0.1
 */