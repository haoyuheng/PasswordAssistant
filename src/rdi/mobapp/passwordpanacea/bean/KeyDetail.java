package rdi.mobapp.passwordpanacea.bean;

public class KeyDetail
{
  private int customKeyFieldIdRef;
  private int keyIdRef;
  private String keyValue;
  
  public KeyDetail(int paramInt1, int paramInt2, String paramString)
  {
    this.keyIdRef = paramInt1;
    this.customKeyFieldIdRef = paramInt2;
    this.keyValue = paramString;
  }
  
  public int getCustomKeyFieldIdRef()
  {
    return this.customKeyFieldIdRef;
  }
  
  public int getKeyIdRef()
  {
    return this.keyIdRef;
  }
  
  public String getKeyValue()
  {
    return this.keyValue;
  }
  
  public void setCustomKeyFieldIdRef(int paramInt)
  {
    this.customKeyFieldIdRef = paramInt;
  }
  
  public void setKeyIdRef(int paramInt)
  {
    this.keyIdRef = paramInt;
  }
  
  public void setKeyValue(String paramString)
  {
    this.keyValue = paramString;
  }
}


/* Location:           D:\Apkdb\Craining\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     rdi.mobapp.passwordpanacea.bean.KeyDetail
 * JD-Core Version:    0.7.0.1
 */