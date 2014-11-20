package rdi.mobapp.passwordpanacea.bean;

public class Settings
{
  private Boolean copyOutsideApp;
  private Boolean groupSearch;
  private Boolean isFirstRun;
  private Boolean keySearch;
  private Boolean passwordDisplay;
  private Boolean setApplicationLock;
  private String theme;
  
  public Settings(Boolean paramBoolean1, Boolean paramBoolean2, Boolean paramBoolean3, Boolean paramBoolean4, String paramString, Boolean paramBoolean5, Boolean paramBoolean6)
  {
    this.passwordDisplay = paramBoolean1;
    this.keySearch = paramBoolean2;
    this.groupSearch = paramBoolean3;
    this.copyOutsideApp = paramBoolean4;
    this.theme = paramString;
    this.isFirstRun = paramBoolean5;
    this.setApplicationLock = paramBoolean6;
  }
  
  public Boolean getCopyOutsideApp()
  {
    return this.copyOutsideApp;
  }
  
  public Boolean getGroupSearch()
  {
    return this.groupSearch;
  }
  
  public Boolean getIsFirstRun()
  {
    return this.isFirstRun;
  }
  
  public Boolean getKeySearch()
  {
    return this.keySearch;
  }
  
  public Boolean getPasswordDisplay()
  {
    return this.passwordDisplay;
  }
  
  public Boolean getSetApplicationLock()
  {
    return this.setApplicationLock;
  }
  
  public String getTheme()
  {
    return this.theme;
  }
  
  public void setCopyOutsideApp(Boolean paramBoolean)
  {
    this.copyOutsideApp = paramBoolean;
  }
  
  public void setGroupSearch(Boolean paramBoolean)
  {
    this.groupSearch = paramBoolean;
  }
  
  public void setIsFirstRun(Boolean paramBoolean)
  {
    this.isFirstRun = paramBoolean;
  }
  
  public void setKeySearch(Boolean paramBoolean)
  {
    this.keySearch = paramBoolean;
  }
  
  public void setPasswordDisplay(Boolean paramBoolean)
  {
    this.passwordDisplay = paramBoolean;
  }
  
  public void setSetApplicationLock(Boolean paramBoolean)
  {
    this.setApplicationLock = paramBoolean;
  }
  
  public void setTheme(String paramString)
  {
    this.theme = paramString;
  }
}


/* Location:           D:\Apkdb\Craining\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     rdi.mobapp.passwordpanacea.bean.Settings
 * JD-Core Version:    0.7.0.1
 */