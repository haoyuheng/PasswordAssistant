package rdi.mobapp.passwordpanacea.bean;

public class Group
{
  private Boolean groupDeleted;
  private String groupDescription;
  private int groupId;
  private String groupTitle;
  
  public Group(String paramString1, String paramString2, Boolean paramBoolean)
  {
    this.groupTitle = paramString1;
    this.groupDescription = paramString2;
    this.groupDeleted = paramBoolean;
  }
  
  public Boolean getGroupDeleted()
  {
    return this.groupDeleted;
  }
  
  public String getGroupDescription()
  {
    return this.groupDescription;
  }
  
  public int getGroupId()
  {
    return this.groupId;
  }
  
  public String getGroupTitle()
  {
    return this.groupTitle;
  }
  
  public void setGroupDeleted(Boolean paramBoolean)
  {
    this.groupDeleted = paramBoolean;
  }
  
  public void setGroupDescription(String paramString)
  {
    this.groupDescription = paramString;
  }
  
  public void setGroupId(int paramInt)
  {
    this.groupId = paramInt;
  }
  
  public void setGroupTitle(String paramString)
  {
    this.groupTitle = paramString;
  }
}


/* Location:           D:\Apkdb\Craining\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     rdi.mobapp.passwordpanacea.bean.Group
 * JD-Core Version:    0.7.0.1
 */