package rdi.mobapp.passwordpanacea;

import android.util.Log;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.DigestInputStream;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

public class CryptoHelper
{
  public static final int EncryptionMedium = 1;
  public static final int EncryptionStrong = 2;
  private static String TAG = "CryptoHelper";
  protected static String algorithmMedium = "PBEWithMD5And128BitAES-CBC-OpenSSL";
  protected static String algorithmStrong = "PBEWithSHA1And256BitAES-CBC-BC";
  private static final int count = 20;
  protected static String desAlgorithm = "DES";
  protected static SecretKeyFactory keyFac;
  protected static String password = null;
  protected static Cipher pbeCipher;
  protected static SecretKey pbeKey;
  protected static PBEKeySpec pbeKeySpec;
  protected static PBEParameterSpec pbeParamSpec;
  private static final byte[] salt = { -4, 118, -128, -82, -3, -126, -66, -18 };
  private String algorithm = "";
  private boolean status = false;
  
  CryptoHelper()
  {
    initialize(1);
  }
  
  public CryptoHelper(int paramInt)
  {
    initialize(paramInt);
  }
  
  public static String generateDESKey()
  {
    try
    {
      String str = toHexString(KeyGenerator.getInstance(desAlgorithm).generateKey().getEncoded());
      return str;
    }
    catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
    {
      Log.e(TAG, "generateDESKey(): " + localNoSuchAlgorithmException.toString());
    }
    return null;
  }
  
  public static byte[] hexStringToBytes(String paramString)
  {
	  byte [] bytes = new byte [paramString.length() / 2];
      int j = 0;
      for (int i = 0; i < paramString.length(); i += 2)
      {
              try {
                      String hexByte=paramString.substring(i, i+2);

                      Integer I = new Integer (0);
                      I = Integer.decode("0x"+hexByte);
                      int k = I.intValue ();
                      bytes[j++] = new Integer(k).byteValue();
              } catch (NumberFormatException e)
              {
                      Log.i(TAG,e.getLocalizedMessage());
                      return bytes;
              } catch (StringIndexOutOfBoundsException e)
              {
                      Log.i(TAG,"StringIndexOutOfBoundsException");
                      return bytes;
              }
      }
      return bytes;
  }
  
  private void initialize(int Strength)
  {
	  switch (Strength) {
      case EncryptionMedium:
              algorithm=algorithmMedium;
              break;
      case EncryptionStrong:
              algorithm=algorithmStrong;
              break;
      }
      pbeParamSpec = new PBEParameterSpec(salt,count);
      try {
          keyFac = SecretKeyFactory
          .getInstance(algorithm,"BC");
      } catch (NoSuchAlgorithmException e) {
          Log.e(TAG,"CryptoHelper(): "+e.toString());
      } catch (NoSuchProviderException e) {
          Log.e(TAG,"CryptoHelper(): "+e.toString());         
      }
    
  }
  
  /* Error */
  public static byte[] md5String(String paramString)
  {
	  byte[] input = paramString.getBytes();
      
      MessageDigest hash;
      ByteArrayInputStream    bIn = null;
      DigestInputStream       dIn = null;

      try {
          hash = MessageDigest.getInstance("MD5");

          bIn = new ByteArrayInputStream(input);
          dIn = new DigestInputStream(bIn, hash);

          for(int i=0;i<input.length;i++) {
              dIn.read();
          }

      } catch (NoSuchAlgorithmException e) {
          Log.e(TAG,"md5String(): "+e.toString());
      } catch (IOException e) {
          Log.e(TAG,"md5String(): "+e.toString());
      }
      return dIn.getMessageDigest().digest();
  }
  
  public static String toHexString(byte[] paramArrayOfByte)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    for (int i = 0;; i++)
    {
      if (i >= paramArrayOfByte.length) {
        return localStringBuffer.toString();
      }
      localStringBuffer.append(Integer.toHexString(256 + (0xFF & paramArrayOfByte[i])).substring(1));
    }
  }
  
  public String decrypt(String paramString)
    throws CryptoHelperException
  {
    this.status = false;
    if (password == null) {
      throw new CryptoHelperException("Must call setPassword before running decrypt.");
    }
    if ((paramString == null) || (paramString == "")) {
      return "";
    }
    byte[] arrayOfByte1 = hexStringToBytes(paramString);
    byte[] arrayOfByte2 = new byte[0];
    try
    {
      pbeCipher.init(2, pbeKey, pbeParamSpec);
      arrayOfByte2 = pbeCipher.doFinal(arrayOfByte1);
      this.status = true;      
    }
    catch (IllegalBlockSizeException localIllegalBlockSizeException)
    {
        Log.e(TAG, "decrypt(): " + localIllegalBlockSizeException.toString());
      
    }
    catch (BadPaddingException localBadPaddingException)
    {
        Log.e(TAG, "decrypt(): " + localBadPaddingException.toString());
      
    }
    catch (InvalidKeyException localInvalidKeyException)
    {
      
        Log.e(TAG, "decrypt(): " + localInvalidKeyException.toString());
      
    }
    catch (InvalidAlgorithmParameterException localInvalidAlgorithmParameterException)
    {
        Log.e(TAG, "decrypt(): " + localInvalidAlgorithmParameterException.toString());
      
    }
    return new String(arrayOfByte2);
  }
  
  public String encrypt(String paramString)
    throws CryptoHelperException
  {
    this.status = false;
    if (password == null) {
      throw new CryptoHelperException("Must call setPassword before runing encrypt.");
    }
    byte[] arrayOfByte = new byte[0];
    try
    {
      pbeCipher.init(1, pbeKey, pbeParamSpec);
      arrayOfByte = pbeCipher.doFinal(paramString.getBytes());
      this.status = true;
      
    }
    catch (IllegalBlockSizeException localIllegalBlockSizeException)
    {
        Log.e(TAG, "encrypt(): " + localIllegalBlockSizeException.toString());
      
    }
    catch (BadPaddingException localBadPaddingException)
    {
        Log.e(TAG, "encrypt(): " + localBadPaddingException.toString());
      
    }
    catch (InvalidKeyException localInvalidKeyException)
    {
     
        Log.e(TAG, "encrypt(): " + localInvalidKeyException.toString());
      
    }
    catch (InvalidAlgorithmParameterException localInvalidAlgorithmParameterException)
    {
        Log.e(TAG, "encrypt(): " + localInvalidAlgorithmParameterException.toString());
     
    }
    return toHexString(arrayOfByte);
  }
  
  public boolean getStatus()
  {
    return this.status;
  }
  
  public void setPassword(String paramString)
  {
    password = paramString;
    pbeKeySpec = new PBEKeySpec(password.toCharArray());
    try
    {
      pbeKey = keyFac.generateSecret(pbeKeySpec);
      pbeCipher = Cipher.getInstance(this.algorithm, "BC");
      return;
    }
    catch (InvalidKeySpecException localInvalidKeySpecException)
    {
      Log.e(TAG, "setPassword(): " + localInvalidKeySpecException.toString());
      return;
    }
    catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
    {
      Log.e(TAG, "setPassword(): " + localNoSuchAlgorithmException.toString());
      return;
    }
    catch (NoSuchProviderException localNoSuchProviderException)
    {
      Log.e(TAG, "setPassword(): " + localNoSuchProviderException.toString());
      return;
    }
    catch (NoSuchPaddingException localNoSuchPaddingException)
    {
      Log.e(TAG, "setPassword(): " + localNoSuchPaddingException.toString());
    }
  }
}


/* Location:           D:\Apkdb\Craining\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     rdi.mobapp.passwordpanacea.CryptoHelper
 * JD-Core Version:    0.7.0.1
 */