package rdi.mobapp.passwordpanacea.databasehelper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import rdi.mobapp.passwordpanacea.bean.MasterKey;
import rdi.mobapp.passwordpanacea.bean.Settings;

public class DBSettingsHelper
  extends SQLiteOpenHelper
{
  private static final String copyOutsideApp = "CopyOutsideApp";
  private static final String dbName = "DBSettings";
  private static final int dbVersion = 1;
  private static final String groupSearch = "GroupSearch";
  private static final String isFirstRun = "IsFirstRun";
  private static final String keySearch = "KeySearch";
  private static final String masterKeySecurityAns = "MasterKeySecurityAns";
  private static final String masterKeySecurityQue = "MasterKeySecurityQue";
  private static final String masterKeyTable = "MasterKey";
  private static final String masterKeyValue = "MasterKeyValue";
  private static final String passwordDisplay = "PasswordDisplay";
  private static final String setAppLock = "SetAppLock";
  private static final String settingMasterTable = "SettingMaster";
  private static final String theme = "Theme";
  private Cursor cursor;
  private SQLiteDatabase db;
  
  public DBSettingsHelper(Context paramContext)
  {
    super(paramContext, "DBSettings", null, 1);
  }
  
  public ArrayList<MasterKey> getMasterKeyDetail()
  {
    ArrayList localArrayList = new ArrayList();
    this.db = getReadableDatabase();
    this.cursor = this.db.rawQuery("SELECT * FROM MasterKey", null);
    if ((this.cursor != null) && (this.cursor.moveToFirst())) {
      do
      {
        localArrayList.add(new MasterKey(this.cursor.getString(this.cursor.getColumnIndex("MasterKeyValue")), this.cursor.getString(this.cursor.getColumnIndex("MasterKeySecurityQue")), this.cursor.getString(this.cursor.getColumnIndex("MasterKeySecurityAns"))));
      } while (this.cursor.moveToNext());
    }
    this.cursor.close();
    this.db.close();
    return localArrayList;
  }
  
  public ArrayList<Settings> getSettingMasterDetail()
  {
    ArrayList localArrayList = new ArrayList();
    Boolean.valueOf(false);
    Boolean.valueOf(false);
    Boolean.valueOf(false);
    Boolean.valueOf(false);
    Boolean.valueOf(false);
    Boolean.valueOf(false);
    this.db = getReadableDatabase();
    this.cursor = this.db.rawQuery("SELECT * FROM SettingMaster", null);
    if ((this.cursor != null) && (this.cursor.moveToFirst())) {
      do
      {
        Boolean localBoolean = Boolean.valueOf(this.cursor.getString(this.cursor.getColumnIndex("SetAppLock")).contains("true"));
        localArrayList.add(new Settings(Boolean.valueOf(this.cursor.getString(this.cursor.getColumnIndex("PasswordDisplay")).contains("true")), Boolean.valueOf(this.cursor.getString(this.cursor.getColumnIndex("KeySearch")).contains("true")), Boolean.valueOf(this.cursor.getString(this.cursor.getColumnIndex("GroupSearch")).contains("true")), Boolean.valueOf(this.cursor.getString(this.cursor.getColumnIndex("CopyOutsideApp")).contains("true")), this.cursor.getString(this.cursor.getColumnIndex("Theme")), Boolean.valueOf(this.cursor.getString(this.cursor.getColumnIndex("IsFirstRun")).contains("true")), localBoolean));
      } while (this.cursor.moveToNext());
    }
    this.cursor.close();
    this.db.close();
    return localArrayList;
  }
  
  public void onCreate(SQLiteDatabase paramSQLiteDatabase)
  {
    paramSQLiteDatabase.execSQL("CREATE TABLE MasterKey (MasterKeyValue TEXT, MasterKeySecurityQue TEXT, MasterKeySecurityAns TEXT)");
    paramSQLiteDatabase.execSQL("CREATE TABLE SettingMaster (SetAppLock BOOLEAN, PasswordDisplay BOOLEAN, KeySearch BOOLEAN, GroupSearch BOOLEAN, CopyOutsideApp BOOLEAN, Theme BOOLEAN, IsFirstRun BOOLEAN)");
    paramSQLiteDatabase.execSQL("INSERT INTO SettingMaster (SetAppLock, PasswordDisplay, KeySearch, GroupSearch, CopyOutsideApp, Theme, IsFirstRun) VALUES ('false', 'false', 'false', 'false', 'false', 'false', 'true')");
    paramSQLiteDatabase.execSQL("INSERT INTO MasterKey (MasterKeyValue, MasterKeySecurityQue, MasterKeySecurityAns) VALUES ('', '', '')");
  }
  
  public void onUpgrade(SQLiteDatabase paramSQLiteDatabase, int paramInt1, int paramInt2) {}
  
  public void updateCopyOutsideApp(Boolean paramBoolean)
  {
    this.db = getWritableDatabase();
    this.db.execSQL("UPDATE SettingMaster SET CopyOutsideApp = '" + paramBoolean + "'");
    this.db.close();
  }
  
  public void updateGroupSearch(Boolean paramBoolean)
  {
    this.db = getWritableDatabase();
    this.db.execSQL("UPDATE SettingMaster SET GroupSearch = '" + paramBoolean + "'");
    this.db.close();
  }
  
  public void updateIsFirstRun(Boolean paramBoolean)
  {
    this.db = getWritableDatabase();
    this.db.execSQL("UPDATE SettingMaster SET IsFirstRun = '" + paramBoolean + "'");
    this.db.close();
  }
  
  public void updateKeySearch(Boolean paramBoolean)
  {
    this.db = getWritableDatabase();
    this.db.execSQL("UPDATE SettingMaster SET KeySearch = '" + paramBoolean + "'");
    this.db.close();
  }
  
  public void updateMasterKeyDetail(String paramString1, String paramString2, String paramString3)
  {
    this.db = getWritableDatabase();
    this.db.execSQL("UPDATE MasterKey SET MasterKeyValue = '" + paramString1 + "', MasterKeySecurityQue = '" + paramString2 + "', MasterKeySecurityAns = '" + paramString3 + "'");
    this.db.close();
  }
  
  public void updateMasterKeyValue(String paramString)
  {
    this.db = getWritableDatabase();
    this.db.execSQL("UPDATE MasterKey SET MasterKeyValue = '" + paramString + "'");
    this.db.close();
  }
  
  public void updatePasswordDisplay(Boolean paramBoolean)
  {
    this.db = getWritableDatabase();
    this.db.execSQL("UPDATE SettingMaster SET PasswordDisplay = '" + paramBoolean + "'");
    this.db.close();
  }
  
  public void updateSetAppLock(Boolean paramBoolean)
  {
    this.db = getWritableDatabase();
    this.db.execSQL("UPDATE SettingMaster SET SetAppLock = '" + paramBoolean + "'");
    this.db.close();
  }
}


/* Location:           D:\Apkdb\Craining\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     rdi.mobapp.passwordpanacea.databasehelper.DBSettingsHelper
 * JD-Core Version:    0.7.0.1
 */