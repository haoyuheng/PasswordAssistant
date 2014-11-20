package rdi.mobapp.passwordpanacea.bean;

public class Items
{
  private int itemId;
  private String itemTitle;
  
  public Items(int paramInt, String paramString)
  {
    this.itemId = paramInt;
    this.itemTitle = paramString;
  }
  
  public int getItemId()
  {
    return this.itemId;
  }
  
  public String getItemTitle()
  {
    return this.itemTitle;
  }
  
  public void setItemId(int paramInt)
  {
    this.itemId = paramInt;
  }
  
  public void setItemTitle(String paramString)
  {
    this.itemTitle = paramString;
  }
}


/* Location:           D:\Apkdb\Craining\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     rdi.mobapp.passwordpanacea.bean.Items
 * JD-Core Version:    0.7.0.1
 */