package rdi.mobapp.passwordpanacea;

public class DialogError
  extends Throwable
{
  private static final long serialVersionUID = 1L;
  private int mErrorCode;
  private String mFailingUrl;
  
  public DialogError(String paramString1, int paramInt, String paramString2)
  {
    super(paramString1);
    this.mErrorCode = paramInt;
    this.mFailingUrl = paramString2;
  }
  
  int getErrorCode()
  {
    return this.mErrorCode;
  }
  
  String getFailingUrl()
  {
    return this.mFailingUrl;
  }
}


/* Location:           D:\Apkdb\Craining\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     rdi.mobapp.passwordpanacea.DialogError
 * JD-Core Version:    0.7.0.1
 */