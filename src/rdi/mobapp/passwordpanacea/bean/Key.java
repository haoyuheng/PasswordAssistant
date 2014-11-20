package rdi.mobapp.passwordpanacea.bean;

public class Key
{
  private int groupIdRef;
  private int keyDeleted;
  private int templateIdRef;
  
  public Key(int paramInt1, int paramInt2, int paramInt3)
  {
    this.groupIdRef = paramInt1;
    this.keyDeleted = paramInt2;
    this.templateIdRef = paramInt3;
  }
  
  public int getGroupIdRef()
  {
    return this.groupIdRef;
  }
  
  public int getKeyDeleted()
  {
    return this.keyDeleted;
  }
  
  public int getTemplateIdRef()
  {
    return this.templateIdRef;
  }
  
  public void setGroupIdRef(int paramInt)
  {
    this.groupIdRef = paramInt;
  }
  
  public void setKeyDeleted(int paramInt)
  {
    this.keyDeleted = paramInt;
  }
  
  public void setTemplateIdRef(int paramInt)
  {
    this.templateIdRef = paramInt;
  }
}


/* Location:           D:\Apkdb\Craining\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     rdi.mobapp.passwordpanacea.bean.Key
 * JD-Core Version:    0.7.0.1
 */