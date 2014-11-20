package rdi.mobapp.passwordpanacea.bean;

public class GetCustomKeyFields
{
  private int customKeyFieldDisplayOrder;
  private int customKeyFieldId;
  private String customKeyFieldInputType;
  private int customKeyFieldSize;
  private String customKeyFieldTitle;
  private int templateIdRef;
  
  public GetCustomKeyFields(int paramInt1, int paramInt2, String paramString1, int paramInt3, String paramString2, int paramInt4)
  {
    this.customKeyFieldId = paramInt1;
    this.templateIdRef = paramInt2;
    this.customKeyFieldTitle = paramString1;
    this.customKeyFieldSize = paramInt3;
    this.customKeyFieldInputType = paramString2;
    this.customKeyFieldDisplayOrder = paramInt4;
  }
  
  public int getCustomKeyFieldDisplayOrder()
  {
    return this.customKeyFieldDisplayOrder;
  }
  
  public int getCustomKeyFieldId()
  {
    return this.customKeyFieldId;
  }
  
  public String getCustomKeyFieldInputType()
  {
    return this.customKeyFieldInputType;
  }
  
  public int getCustomKeyFieldSize()
  {
    return this.customKeyFieldSize;
  }
  
  public String getCustomKeyFieldTitle()
  {
    return this.customKeyFieldTitle;
  }
  
  public int getTemplateIdRef()
  {
    return this.templateIdRef;
  }
  
  public void setCustomKeyFieldDisplayOrder(int paramInt)
  {
    this.customKeyFieldDisplayOrder = paramInt;
  }
  
  public void setCustomKeyFieldId(int paramInt)
  {
    this.customKeyFieldId = paramInt;
  }
  
  public void setCustomKeyFieldInputType(String paramString)
  {
    this.customKeyFieldInputType = paramString;
  }
  
  public void setCustomKeyFieldSize(int paramInt)
  {
    this.customKeyFieldSize = paramInt;
  }
  
  public void setCustomKeyFieldTitle(String paramString)
  {
    this.customKeyFieldTitle = paramString;
  }
  
  public void setTemplateIdRef(int paramInt)
  {
    this.templateIdRef = paramInt;
  }
}


/* Location:           D:\Apkdb\Craining\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     rdi.mobapp.passwordpanacea.bean.GetCustomKeyFields
 * JD-Core Version:    0.7.0.1
 */