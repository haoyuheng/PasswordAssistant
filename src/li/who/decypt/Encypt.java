package li.who.decypt;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;


public class Encypt {
	/**
	  * byte数组转换成16进制字符串
	  * @param src
	  * @return
	  */
	 public static String bytesToHexString(byte[] src){      
	        StringBuilder stringBuilder = new StringBuilder();      
	        if (src == null || src.length <= 0) {      
	            return null;      
	        }      
	        for (int i = 0; i < src.length; i++) {      
	            int v = src[i] & 0xFF;      
	            String hv = Integer.toHexString(v);      
	            if (hv.length() < 2) {      
	                stringBuilder.append(0);      
	            }      
	            stringBuilder.append(hv);      
	        }      
	        return stringBuilder.toString();      
	    } 
	 
	 /**
	  * 根据文件流读取图片文件真实类型
	  * @param filepath
	  * @return
	 * @throws FileNotFoundException 
	  */
	 public static String getTypeByStream(String filepath) throws FileNotFoundException{
		 FileInputStream file =  new FileInputStream(filepath);
	     byte[] b = new byte[4];   
	        try {
	     file.read(b, 0, b.length);
	     file.close();
	  } catch (IOException e) {
	   e.printStackTrace();
	  }
	        String type = bytesToHexString(b).toUpperCase();
	        if(type.contains("FFD8FF")){
	         return "jpg";
	        }else if(type.contains("89504E47")){
	         return "png";
	        }else if(type.contains("47494638")){
	         return "gif";
	        }else if(type.contains("49492A00")){
	         return "tif";
	        }else if(type.contains("424D")){
	         return "bmp";
	        }else if(type.contains("504B0304")){
		         return "apk";
		    }
	        
	        return type;
	    }
	 
	 public static byte[] getByteTypeByStream(String filepath) throws FileNotFoundException{
		 FileInputStream file =  new FileInputStream(filepath);
	     byte[] b = new byte[4];   
	        try {
	     file.read(b, 0, b.length);
	     file.close();
	  } catch (IOException e) {
	   e.printStackTrace();
	  }
	        return b;
	  }
	 
	 
	 public static void UpateFileType(String fileName, byte[] type) {   
	        RandomAccessFile randomFile = null;  
	        try {     
	            // 打开一个随机访问文件流，按读写方式     
	            randomFile = new RandomAccessFile(fileName, "rw");     
	            // 文件长度，字节数 
	            randomFile.seek(0);     
	            randomFile.write(type);      
	        } catch (IOException e) {     
	            e.printStackTrace();     
	        } finally{  
	            if(randomFile != null){  
	                try {
	                    randomFile.close();  
	                } catch (IOException e) {  
	                    e.printStackTrace();  
	                }  
	            }  
	        }  
	    } 
	 
	 	static boolean copy(String fileFrom, String fileTo) {  
	        try {  
	            FileInputStream in = new java.io.FileInputStream(fileFrom);  
	            FileOutputStream out = new FileOutputStream(fileTo);  
	            byte[] bt = new byte[1024];  
	            int count;  
	            while ((count = in.read(bt)) > 0) {  
	                out.write(bt, 0, count);  
	            }  
	            in.close();  
	            out.close();  
	            return true;  
	        } catch (IOException ex) {  
	            return false;  
	        }  
	    }  
	 
}
