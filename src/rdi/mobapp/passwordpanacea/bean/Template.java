package rdi.mobapp.passwordpanacea.bean;

public class Template
{
  private int templateId;
  private String templateName;
  
  public Template(int paramInt, String paramString)
  {
    this.templateId = paramInt;
    this.templateName = paramString;
  }
  
  public int getTemplateId()
  {
    return this.templateId;
  }
  
  public String getTemplateName()
  {
    return this.templateName;
  }
  
  public void setTemplateId(int paramInt)
  {
    this.templateId = paramInt;
  }
  
  public void setTemplateName(String paramString)
  {
    this.templateName = paramString;
  }
}


/* Location:           D:\Apkdb\Craining\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     rdi.mobapp.passwordpanacea.bean.Template
 * JD-Core Version:    0.7.0.1
 */