package rdi.mobapp.passwordpanacea.bean;

public class MasterKey
{
  private String keySecAns;
  private String keySecQue;
  private String masterKey;
  
  public MasterKey(String paramString1, String paramString2, String paramString3)
  {
    this.masterKey = paramString1;
    this.keySecQue = paramString2;
    this.keySecAns = paramString3;
  }
  
  public String getKeySecAns()
  {
    return this.keySecAns;
  }
  
  public String getKeySecQue()
  {
    return this.keySecQue;
  }
  
  public String getMasterKey()
  {
    return this.masterKey;
  }
  
  public void setKeySecAns(String paramString)
  {
    this.keySecAns = paramString;
  }
  
  public void setKeySecQue(String paramString)
  {
    this.keySecQue = paramString;
  }
  
  public void setMasterKey(String paramString)
  {
    this.masterKey = paramString;
  }
}


/* Location:           D:\Apkdb\Craining\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     rdi.mobapp.passwordpanacea.bean.MasterKey
 * JD-Core Version:    0.7.0.1
 */