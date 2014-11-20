package rdi.mobapp.passwordpanacea.application;

import android.app.Application;
import java.util.ArrayList;
import java.util.List;
import rdi.mobapp.passwordpanacea.bean.Items;

public class PasswordPanacea
  extends Application
{
  private static String addGroupText;
  private static String addKeyText;
  private static boolean appLock;
  private static boolean copyOutsideApp;
  private static boolean disableAnim;
  private static boolean disableKeyAnim;
  private static boolean footerKeyPressed;
  private static String groupActivity;
  private static int groupMasterId;
  private static boolean groupSearchEnable;
  private static boolean groupSelected;
  private static boolean groupTabON;
  private static String groupTitle;
  private static boolean isAddNewFieldClicked;
  private static boolean isFirstCalled;
  private static boolean isGroupHeaderTabVisible;
  private static boolean isHelpHeaderTabVisible;
  private static boolean isKeyHeaderTabVisible;
  private static boolean isTemplateHeaderTabVisible;
  private static boolean isTrashHeaderTabVisible;
  private static String keyActivity;
  private static List<String> keyEnteredFields;
  private static int keyMasterId;
  private static boolean keySearchEnable;
  private static String keyTitle;
  private static String oldTemplateSpinnerVal;
  private static boolean passwordDisplay;
  private static boolean removeRestoreCalled;
  private static List<Items> selectedGroups;
  private static List<Items> selectedItems;
  private static String settingActivity;
  private static boolean showOldMasterKeyInput;
  private static int tempGroupMasterId;
  private static String tempGroupTitle;
  private static String templateActivity;
  private static List<String> templateInputFields;
  private static String templateSpinnerVal;
  private static String trashActivity;
  private static boolean viewAllKeyPressed;
  
  public static String getAddGroupText()
  {
    return addGroupText;
  }
  
  public static String getAddKeyText()
  {
    return addKeyText;
  }
  
  public static boolean getAppLock()
  {
    return appLock;
  }
  
  public static boolean getCopyOutsideApp()
  {
    return copyOutsideApp;
  }
  
  public static boolean getDisableAnim()
  {
    return disableAnim;
  }
  
  public static boolean getDisableKeyAnim()
  {
    return disableKeyAnim;
  }
  
  public static boolean getFooterKeyPressed()
  {
    return footerKeyPressed;
  }
  
  public static String getGroupActivity()
  {
    return groupActivity;
  }
  
  public static int getGroupMasterId()
  {
    return groupMasterId;
  }
  
  public static boolean getGroupSearchEnable()
  {
    return groupSearchEnable;
  }
  
  public static boolean getGroupSelected()
  {
    return groupSelected;
  }
  
  public static boolean getGroupTabON()
  {
    return groupTabON;
  }
  
  public static String getGroupTitle()
  {
    return groupTitle;
  }
  
  public static boolean getIsGroupHeaderTabVisible()
  {
    return isGroupHeaderTabVisible;
  }
  
  public static boolean getIsHelpHeaderTabVisible()
  {
    return isHelpHeaderTabVisible;
  }
  
  public static boolean getIsKeyHeaderTabVisible()
  {
    return isKeyHeaderTabVisible;
  }
  
  public static boolean getIsTemplateHeaderTabVisible()
  {
    return isTemplateHeaderTabVisible;
  }
  
  public static boolean getIsTrashHeaderTabVisible()
  {
    return isTrashHeaderTabVisible;
  }
  
  public static String getKeyActivity()
  {
    return keyActivity;
  }
  
  public static List<String> getKeyEnteredFields()
  {
    return keyEnteredFields;
  }
  
  public static int getKeyMasterId()
  {
    return keyMasterId;
  }
  
  public static boolean getKeySearchEnable()
  {
    return keySearchEnable;
  }
  
  public static String getKeyTitle()
  {
    return keyTitle;
  }
  
  public static String getOldTemplateSpinnerVal()
  {
    return oldTemplateSpinnerVal;
  }
  
  public static boolean getPasswordDisplay()
  {
    return passwordDisplay;
  }
  
  public static boolean getRemoveRestoreCalled()
  {
    return removeRestoreCalled;
  }
  
  public static List<Items> getSelectedGroups()
  {
    return selectedGroups;
  }
  
  public static List<Items> getSelectedItems()
  {
    return selectedItems;
  }
  
  public static String getSettingActivity()
  {
    return settingActivity;
  }
  
  public static boolean getShowOldMasterKeyInput()
  {
    return showOldMasterKeyInput;
  }
  
  public static int getTempGroupMasterId()
  {
    return tempGroupMasterId;
  }
  
  public static String getTempGroupTitle()
  {
    return tempGroupTitle;
  }
  
  public static String getTemplateActivity()
  {
    return templateActivity;
  }
  
  public static List<String> getTemplateInputFields()
  {
    return templateInputFields;
  }
  
  public static String getTemplateSpinnerVal()
  {
    return templateSpinnerVal;
  }
  
  public static String getTrashActivity()
  {
    return trashActivity;
  }
  
  public static boolean getViewAllKeyPressed()
  {
    return viewAllKeyPressed;
  }
  
  public static boolean isAddNewFieldClicked()
  {
    return isAddNewFieldClicked;
  }
  
  public static boolean isFirstCalled()
  {
    return isFirstCalled;
  }
  
  public static void setAddGroupText(String paramString)
  {
    addGroupText = paramString;
  }
  
  public static void setAddKeyText(String paramString)
  {
    addKeyText = paramString;
  }
  
  public static void setAddNewFieldClicked(boolean paramBoolean)
  {
    isAddNewFieldClicked = paramBoolean;
  }
  
  public static void setAppLock(boolean paramBoolean)
  {
    appLock = paramBoolean;
  }
  
  public static void setCopyOutsideApp(boolean paramBoolean)
  {
    copyOutsideApp = paramBoolean;
  }
  
  public static void setDisableAnim(boolean paramBoolean)
  {
    disableAnim = paramBoolean;
  }
  
  public static void setDisableKeyAnim(boolean paramBoolean)
  {
    disableKeyAnim = paramBoolean;
  }
  
  public static void setFirstCalled(boolean paramBoolean)
  {
    isFirstCalled = paramBoolean;
  }
  
  public static void setFooterKeyPressed(boolean paramBoolean)
  {
    footerKeyPressed = paramBoolean;
  }
  
  public static void setGroupActivity(String paramString)
  {
    groupActivity = paramString;
  }
  
  public static void setGroupMasterId(int paramInt)
  {
    groupMasterId = paramInt;
  }
  
  public static void setGroupSearchEnable(boolean paramBoolean)
  {
    groupSearchEnable = paramBoolean;
  }
  
  public static void setGroupSelected(boolean paramBoolean)
  {
    groupSelected = paramBoolean;
  }
  
  public static void setGroupTabON(boolean paramBoolean)
  {
    groupTabON = paramBoolean;
  }
  
  public static void setGroupTitle(String paramString)
  {
    groupTitle = paramString;
  }
  
  public static void setIsGroupHeaderTabVisible(boolean paramBoolean)
  {
    isGroupHeaderTabVisible = paramBoolean;
  }
  
  public static void setIsHelpHeaderTabVisible(boolean paramBoolean)
  {
    isHelpHeaderTabVisible = paramBoolean;
  }
  
  public static void setIsKeyHeaderTabVisible(boolean paramBoolean)
  {
    isKeyHeaderTabVisible = paramBoolean;
  }
  
  public static void setIsTemplateHeaderTabVisible(boolean paramBoolean)
  {
    isTemplateHeaderTabVisible = paramBoolean;
  }
  
  public static void setIsTrashHeaderTabVisible(boolean paramBoolean)
  {
    isTrashHeaderTabVisible = paramBoolean;
  }
  
  public static void setKeyActivity(String paramString)
  {
    keyActivity = paramString;
  }
  
  public static void setKeyEnteredFields(List<String> paramList)
  {
    keyEnteredFields = paramList;
  }
  
  public static void setKeyMasterId(int paramInt)
  {
    keyMasterId = paramInt;
  }
  
  public static void setKeySearchEnable(boolean paramBoolean)
  {
    keySearchEnable = paramBoolean;
  }
  
  public static void setKeyTitle(String paramString)
  {
    keyTitle = paramString;
  }
  
  public static void setOldTemplateSpinnerVal(String paramString)
  {
    oldTemplateSpinnerVal = paramString;
  }
  
  public static void setPasswordDisplay(boolean paramBoolean)
  {
    passwordDisplay = paramBoolean;
  }
  
  public static void setRemoveRestoreCalled(boolean paramBoolean)
  {
    removeRestoreCalled = paramBoolean;
  }
  
  public static void setSelectedGroups(List<Items> paramList)
  {
    selectedGroups = paramList;
  }
  
  public static void setSelectedItems(List<Items> paramList)
  {
    selectedItems = paramList;
  }
  
  public static void setSettingActivity(String paramString)
  {
    settingActivity = paramString;
  }
  
  public static void setShowOldMasterKeyInput(boolean paramBoolean)
  {
    showOldMasterKeyInput = paramBoolean;
  }
  
  public static void setTempGroupMasterId(int paramInt)
  {
    tempGroupMasterId = paramInt;
  }
  
  public static void setTempGroupTitle(String paramString)
  {
    tempGroupTitle = paramString;
  }
  
  public static void setTemplateActivity(String paramString)
  {
    templateActivity = paramString;
  }
  
  public static void setTemplateInputFields(List<String> paramList)
  {
    templateInputFields = paramList;
  }
  
  public static void setTemplateSpinnerVal(String paramString)
  {
    templateSpinnerVal = paramString;
  }
  
  public static void setTrashActivity(String paramString)
  {
    trashActivity = paramString;
  }
  
  public static void setViewAllKeyPressed(boolean paramBoolean)
  {
    viewAllKeyPressed = paramBoolean;
  }
  
  public void onCreate()
  {
    super.onCreate();
    groupMasterId = 0;
    groupTitle = null;
    keyMasterId = 0;
    keyTitle = null;
    footerKeyPressed = false;
    viewAllKeyPressed = false;
    appLock = false;
    passwordDisplay = false;
    groupSearchEnable = false;
    keySearchEnable = true;
    copyOutsideApp = false;
    keyActivity = null;
    groupActivity = null;
    settingActivity = null;
    trashActivity = null;
    templateActivity = null;
    disableAnim = false;
    disableKeyAnim = false;
    showOldMasterKeyInput = false;
    addGroupText = null;
    addKeyText = null;
    tempGroupMasterId = 0;
    tempGroupTitle = null;
    selectedItems = new ArrayList();
    selectedGroups = new ArrayList();
    templateInputFields = new ArrayList();
    keyEnteredFields = new ArrayList();
    groupSelected = false;
    groupTabON = true;
    removeRestoreCalled = false;
    isGroupHeaderTabVisible = false;
    isKeyHeaderTabVisible = false;
    isTrashHeaderTabVisible = false;
    isHelpHeaderTabVisible = false;
    isTemplateHeaderTabVisible = false;
    isAddNewFieldClicked = false;
    templateSpinnerVal = null;
    oldTemplateSpinnerVal = null;
    isFirstCalled = false;
  }
}


/* Location:           D:\Apkdb\Craining\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     rdi.mobapp.passwordpanacea.application.PasswordPanacea
 * JD-Core Version:    0.7.0.1
 */