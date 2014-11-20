package rdi.mobapp.passwordpanacea.bean;

public class KeyDetailByKeyId
{
  private int keyId;
  private String keyPassword;
  private String keyTitle;
  private String keyUrl;
  private String keyUsername;
  
  public KeyDetailByKeyId(int paramInt, String paramString1, String paramString2, String paramString3, String paramString4)
  {
    this.keyId = paramInt;
    this.keyTitle = paramString1;
    this.keyUsername = paramString2;
    this.keyPassword = paramString3;
    this.keyUrl = paramString4;
  }
  
  public int getKeyId()
  {
    return this.keyId;
  }
  
  public String getKeyPassword()
  {
    return this.keyPassword;
  }
  
  public String getKeyTitle()
  {
    return this.keyTitle;
  }
  
  public String getKeyUrl()
  {
    return this.keyUrl;
  }
  
  public String getKeyUsername()
  {
    return this.keyUsername;
  }
  
  public void setKeyId(int paramInt)
  {
    this.keyId = paramInt;
  }
  
  public void setKeyPassword(String paramString)
  {
    this.keyPassword = paramString;
  }
  
  public void setKeyTitle(String paramString)
  {
    this.keyTitle = paramString;
  }
  
  public void setKeyUrl(String paramString)
  {
    this.keyUrl = paramString;
  }
  
  public void setKeyUsername(String paramString)
  {
    this.keyUsername = paramString;
  }
}


/* Location:           D:\Apkdb\Craining\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     rdi.mobapp.passwordpanacea.bean.KeyDetailByKeyId
 * JD-Core Version:    0.7.0.1
 */