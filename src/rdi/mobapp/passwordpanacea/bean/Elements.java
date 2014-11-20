package rdi.mobapp.passwordpanacea.bean;

public class Elements
{
  private int mId;
  private String mText;
  
  public Elements(String paramString, int paramInt)
  {
    this.mText = paramString;
    this.mId = paramInt;
  }
  
  public int getId()
  {
    return this.mId;
  }
  
  public String getmText()
  {
    return this.mText;
  }
  
  public void setId(int paramInt)
  {
    this.mId = paramInt;
  }
  
  public void setmText(String paramString)
  {
    this.mText = paramString;
  }
  
  public String toString()
  {
    return this.mText;
  }
}


/* Location:           D:\Apkdb\Craining\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     rdi.mobapp.passwordpanacea.bean.Elements
 * JD-Core Version:    0.7.0.1
 */