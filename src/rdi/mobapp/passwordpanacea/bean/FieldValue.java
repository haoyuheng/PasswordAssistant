package rdi.mobapp.passwordpanacea.bean;

public class FieldValue
{
  private String CustomKeyFieldValue;
  private int customKeyFieldId;
  private String customKeyFieldName;
  
  public FieldValue(int paramInt, String paramString1, String paramString2)
  {
    this.customKeyFieldId = paramInt;
    this.customKeyFieldName = paramString1;
    this.CustomKeyFieldValue = paramString2;
  }
  
  public int getCustomKeyFieldId()
  {
    return this.customKeyFieldId;
  }
  
  public String getCustomKeyFieldName()
  {
    return this.customKeyFieldName;
  }
  
  public String getCustomKeyFieldValue()
  {
    return this.CustomKeyFieldValue;
  }
  
  public void setCustomKeyFieldId(int paramInt)
  {
    this.customKeyFieldId = paramInt;
  }
  
  public void setCustomKeyFieldName(String paramString)
  {
    this.customKeyFieldName = paramString;
  }
  
  public void setCustomKeyFieldValue(String paramString)
  {
    this.CustomKeyFieldValue = paramString;
  }
}


/* Location:           D:\Apkdb\Craining\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     rdi.mobapp.passwordpanacea.bean.FieldValue
 * JD-Core Version:    0.7.0.1
 */