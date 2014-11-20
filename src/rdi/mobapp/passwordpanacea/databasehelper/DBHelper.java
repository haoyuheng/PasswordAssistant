package rdi.mobapp.passwordpanacea.databasehelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.Iterator;
import rdi.mobapp.passwordpanacea.bean.CustomKeyFields;
import rdi.mobapp.passwordpanacea.bean.FieldValue;
import rdi.mobapp.passwordpanacea.bean.GetCustomKeyFields;
import rdi.mobapp.passwordpanacea.bean.Group;
import rdi.mobapp.passwordpanacea.bean.Items;
import rdi.mobapp.passwordpanacea.bean.Key;
import rdi.mobapp.passwordpanacea.bean.KeyDetail;
import rdi.mobapp.passwordpanacea.bean.KeyDetailByKeyId;
import rdi.mobapp.passwordpanacea.utility.CustomDialogSingleButton;

public class DBHelper
  extends SQLiteOpenHelper
{
  private static final String customKeyFieldDisplayOrder = "CustomKeyFieldDisplayOrder";
  private static final String customKeyFieldId = "CustomKeyFieldId";
  private static final String customKeyFieldIdRef = "CustomKeyFieldIdRef";
  private static final String customKeyFieldInputType = "CustomKeyFieldInputType";
  private static final String customKeyFieldMasterTable = "CustomKeyFieldMaster";
  private static final String customKeyFieldSize = "CustomKeyFieldSize";
  private static final String customKeyFieldTitle = "CustomKeyFieldTitle";
  private static final String dbName = "DBDetails";
  private static final int dbVersion = 1;
  private static final String groupDeleted = "GroupDeleted";
  private static final String groupDescription = "GroupDescription";
  private static final String groupId = "GroupId";
  private static final String groupIdRef = "GroupIdRef";
  private static final String groupMasterTable = "GroupMaster";
  private static final String groupTitle = "GroupTitle";
  private static final String keyDeleted = "KeyDeleted";
  private static final String keyDetailTable = "KeyDetail";
  private static final String keyId = "KeyId";
  private static final String keyIdRef = "KeyIdRef";
  private static final String keyMasterTable = "KeyMaster";
  private static final String keyValue = "KeyValue";
  private static final String templateId = "TemplateId";
  private static final String templateIdRef = "TemplateIdRef";
  private static final String templateIdRef_customField = "TemplateIdRef";
  private static final String templateMasterTable = "TemplateMaster";
  private static final String templateName = "TemplateName";
  private Context context;
  private Cursor cursor;
  private SQLiteDatabase db;
  private int generalTemplateId = 0;
  
  public DBHelper(Context paramContext)
  {
    super(paramContext, "DBDetails", null, 1);
    this.context = paramContext;
  }
  
  public void addCustomKeyFields(CustomKeyFields paramCustomKeyFields)
  {
    try
    {
      this.db = getWritableDatabase();
      ContentValues localContentValues = new ContentValues();
      localContentValues.put("TemplateIdRef", Integer.valueOf(paramCustomKeyFields.getTemplateIdRef()));
      localContentValues.put("CustomKeyFieldTitle", paramCustomKeyFields.getCustomKeyFieldTitle());
      localContentValues.put("CustomKeyFieldSize", Integer.valueOf(paramCustomKeyFields.getCustomKeyFieldSize()));
      localContentValues.put("CustomKeyFieldInputType", paramCustomKeyFields.getCustomKeyFieldInputType());
      localContentValues.put("CustomKeyFieldDisplayOrder", Integer.valueOf(paramCustomKeyFields.getCustomKeyFieldDisplayOrder()));
      this.db.insert("CustomKeyFieldMaster", "TemplateIdRef", localContentValues);
      this.db.close();
      return;
    }
    catch (Exception localException)
    {
      errorDialog();
    }
  }
  
  public void addCustomTemplateKeyFields(CustomKeyFields paramCustomKeyFields)
  {
    try
    {
      SQLiteDatabase localSQLiteDatabase = getWritableDatabase();
      ContentValues localContentValues = new ContentValues();
      localContentValues.put("TemplateIdRef", Integer.valueOf(paramCustomKeyFields.getTemplateIdRef()));
      localContentValues.put("CustomKeyFieldTitle", paramCustomKeyFields.getCustomKeyFieldTitle());
      localContentValues.put("CustomKeyFieldSize", Integer.valueOf(paramCustomKeyFields.getCustomKeyFieldSize()));
      localContentValues.put("CustomKeyFieldInputType", paramCustomKeyFields.getCustomKeyFieldInputType());
      localContentValues.put("CustomKeyFieldDisplayOrder", Integer.valueOf(paramCustomKeyFields.getCustomKeyFieldDisplayOrder()));
      localSQLiteDatabase.insert("CustomKeyFieldMaster", "CustomKeyFieldTitle", localContentValues);
      localSQLiteDatabase.close();
      return;
    }
    catch (Exception localException)
    {
      errorDialog();
    }
  }
  
  public void addDefaultTemplateFields(int paramInt)
  {
    addCustomTemplateKeyFields(new CustomKeyFields(paramInt, "Title", 0, null, 0));
    addCustomTemplateKeyFields(new CustomKeyFields(paramInt, "Username", 0, null, 0));
    addCustomTemplateKeyFields(new CustomKeyFields(paramInt, "Password", 0, null, 0));
    addCustomTemplateKeyFields(new CustomKeyFields(paramInt, "Url", 0, null, 0));
  }
  
  public void addGroup(Group paramGroup)
  {
    try
    {
      this.db = getWritableDatabase();
      ContentValues localContentValues = new ContentValues();
      localContentValues.put("GroupTitle", paramGroup.getGroupTitle());
      localContentValues.put("GroupDescription", paramGroup.getGroupDescription());
      localContentValues.put("GroupDeleted", paramGroup.getGroupDeleted());
      this.db.insert("GroupMaster", "GroupTitle", localContentValues);
      this.db.close();
      return;
    }
    catch (Exception localException)
    {
      errorDialog();
    }
  }
  
  public void addKeyDetail(KeyDetail paramKeyDetail)
  {
    try
    {
      this.db = getWritableDatabase();
      ContentValues localContentValues = new ContentValues();
      localContentValues.put("KeyIdRef", Integer.valueOf(paramKeyDetail.getKeyIdRef()));
      localContentValues.put("CustomKeyFieldIdRef", Integer.valueOf(paramKeyDetail.getCustomKeyFieldIdRef()));
      localContentValues.put("KeyValue", paramKeyDetail.getKeyValue());
      this.db.insert("KeyDetail", "KeyIdRef", localContentValues);
      this.db.close();
      return;
    }
    catch (Exception localException)
    {
      errorDialog();
    }
  }
  
  public void addKeyId(Key paramKey)
  {
    try
    {
      this.db = getWritableDatabase();
      ContentValues localContentValues = new ContentValues();
      localContentValues.put("GroupIdRef", Integer.valueOf(paramKey.getGroupIdRef()));
      localContentValues.put("KeyDeleted", Integer.valueOf(paramKey.getKeyDeleted()));
      localContentValues.put("TemplateIdRef", Integer.valueOf(paramKey.getTemplateIdRef()));
      this.db.insert("KeyMaster", "GroupIdRef", localContentValues);
      this.db.close();
      return;
    }
    catch (Exception localException)
    {
      errorDialog();
    }
  }
  
  public void addTemplate(String paramString)
  {
    try
    {
      SQLiteDatabase localSQLiteDatabase = getWritableDatabase();
      ContentValues localContentValues = new ContentValues();
      localContentValues.put("TemplateName", paramString);
      localSQLiteDatabase.insert("TemplateMaster", "TemplateName", localContentValues);
      localSQLiteDatabase.close();
      return;
    }
    catch (Exception localException)
    {
      errorDialog();
    }
  }
  
  public void createCustomKeyFieldMaster(String paramString, int paramInt)
  {
    addCustomKeyFields(new CustomKeyFields(paramInt, "Title", 0, "", 0));
    addCustomKeyFields(new CustomKeyFields(paramInt, "Username", 0, "", 0));
    addCustomKeyFields(new CustomKeyFields(paramInt, "Password", 0, "", 0));
    addCustomKeyFields(new CustomKeyFields(paramInt, "Url", 0, "", 0));
  }
  
  public void deleteGroup(int paramInt)
  {
    try
    {
      this.db = getWritableDatabase();
      this.db.execSQL("DELETE From GroupMaster WHERE GroupId = '" + paramInt + "'");
      this.db.close();
      return;
    }
    catch (Exception localException)
    {
      errorDialog();
    }
  }
  
  public void deleteKey(int paramInt)
  {
    try
    {
      this.db = getWritableDatabase();
      this.db.execSQL("DELETE From KeyMaster WHERE KeyId = '" + paramInt + "'");
      this.db.execSQL("DELETE From KeyDetail WHERE KeyIdRef = '" + paramInt + "'");
      this.db.close();
      return;
    }
    catch (Exception localException)
    {
      errorDialog();
    }
  }
  
  public void deleteTemplate(int paramInt)
  {
    try
    {
      ArrayList localArrayList = new ArrayList();
      this.db = getReadableDatabase();
      this.cursor = this.db.rawQuery("SELECT KeyId FROM KeyMaster WHERE TemplateIdRef = '" + paramInt + "'", null);
      if ((this.cursor != null) && (this.cursor.moveToFirst())) {
        do
        {
          localArrayList.add(Integer.valueOf(this.cursor.getInt(this.cursor.getColumnIndex("KeyId"))));
        } while (this.cursor.moveToNext());
      }
      this.cursor.close();
      this.db.close();
      Iterator localIterator = localArrayList.iterator();
      while(localIterator.hasNext())
      {        
        deleteKey(((Integer)localIterator.next()).intValue());
      }
      if (!localIterator.hasNext())
      {
        this.db = getWritableDatabase();
        this.db.execSQL("DELETE From CustomKeyFieldMaster WHERE TemplateIdRef = '" + paramInt + "'");
        this.db.execSQL("DELETE From TemplateMaster WHERE TemplateId = '" + paramInt + "'");
        this.db.close();
      }
      return;
    }
    catch (Exception localException)
    {
      errorDialog();
    }
  }
  
  public void deleteTemplateField(int paramInt)
  {
    try
    {
      this.db = getWritableDatabase();
      this.db.execSQL("DELETE From KeyDetail WHERE CustomKeyFieldIdRef = '" + paramInt + "'");
      this.db.execSQL("DELETE From CustomKeyFieldMaster WHERE CustomKeyFieldId = '" + paramInt + "'");
      this.db.close();
      return;
    }
    catch (Exception localException)
    {
      errorDialog();
    }
  }
  
  public void errorDialog()
  {
    new CustomDialogSingleButton(this.context, "Oops!", "Something went wrong...").show();
  }
  
  public String getCustomFieldTitleVal(int paramInt1, int paramInt2)
  {
    String str = null;
    try
    {
      this.db = getReadableDatabase();
      this.cursor = this.db.rawQuery("SELECT KeyValue FROM KeyDetail WHERE CustomKeyFieldIdRef = '" + paramInt1 + "' AND KeyIdRef = '" + paramInt2 + "'", null);
      Cursor localCursor = this.cursor;
      str = null;
      if (localCursor != null)
      {
        boolean bool = this.cursor.moveToFirst();
        str = null;
        if (bool) {
          do
          {
            str = this.cursor.getString(this.cursor.getColumnIndex("KeyValue")).toLowerCase();
          } while (this.cursor.moveToNext());
        }
      }
      this.cursor.close();
      this.db.close();
      return str;
    }
    catch (Exception localException)
    {
      errorDialog();
    }
    return str;
  }
  
  public int getCustomKeyFieldId(int paramInt)
  {
    int i = 0;
    try
    {
      this.db = getReadableDatabase();
      this.cursor = this.db.rawQuery("SELECT CustomKeyFieldId FROM CustomKeyFieldMaster WHERE CustomKeyFieldTitle = 'Title' AND TemplateIdRef = '" + paramInt + "' ", null);
      Cursor localCursor = this.cursor;
      i = 0;
      if (localCursor != null)
      {
        boolean bool = this.cursor.moveToFirst();
        i = 0;
        if (bool) {
          do
          {
            i = this.cursor.getInt(this.cursor.getColumnIndex("CustomKeyFieldId"));
          } while (this.cursor.moveToNext());
        }
      }
      this.cursor.close();
      this.db.close();
      return i;
    }
    catch (Exception localException)
    {
      errorDialog();
    }
    return i;
  }
  
  public ArrayList<GetCustomKeyFields> getCustomKeyFields(int paramInt)
  {
    ArrayList localArrayList = new ArrayList();
    try
    {
      SQLiteDatabase localSQLiteDatabase = getReadableDatabase();
      this.cursor = localSQLiteDatabase.rawQuery("SELECT * FROM CustomKeyFieldMaster WHERE TemplateIdRef = '" + paramInt + "'", null);
      if ((this.cursor != null) && (this.cursor.moveToFirst())) {
    	  localArrayList.add(new GetCustomKeyFields(this.cursor.getInt(this.cursor.getColumnIndex("CustomKeyFieldId")), this.cursor.getInt(this.cursor.getColumnIndex("TemplateIdRef")), this.cursor.getString(this.cursor.getColumnIndex("CustomKeyFieldTitle")), this.cursor.getInt(this.cursor.getColumnIndex("CustomKeyFieldSize")), this.cursor.getString(this.cursor.getColumnIndex("CustomKeyFieldInputType")), this.cursor.getInt(this.cursor.getColumnIndex("CustomKeyFieldDisplayOrder"))));
    	  while (this.cursor.moveToNext()){
        	  localArrayList.add(new GetCustomKeyFields(this.cursor.getInt(this.cursor.getColumnIndex("CustomKeyFieldId")), this.cursor.getInt(this.cursor.getColumnIndex("TemplateIdRef")), this.cursor.getString(this.cursor.getColumnIndex("CustomKeyFieldTitle")), this.cursor.getInt(this.cursor.getColumnIndex("CustomKeyFieldSize")), this.cursor.getString(this.cursor.getColumnIndex("CustomKeyFieldInputType")), this.cursor.getInt(this.cursor.getColumnIndex("CustomKeyFieldDisplayOrder"))));
              
          }
      }
      
      this.cursor.close();
      localSQLiteDatabase.close();
      return localArrayList;
    }
    catch (Exception localException)
    {
      errorDialog();
    }
    return localArrayList;
  }
  
  public ArrayList<FieldValue> getFieldValue(int paramInt1, int paramInt2)
  {
	  
	ArrayList localArrayList1 = new ArrayList();   
    ArrayList localArrayList2 = getCustomKeyFields(paramInt1);
    ArrayList localArrayList3 = getKeyValueByKeyId(paramInt2, localArrayList2);
    Log.e(" getFieldValue",paramInt1+" "+paramInt2+" "+localArrayList2.size()+" "+localArrayList3.size()+"");
    int i = 0;
    try
    {
      while(i < localArrayList3.size())
      {
        localArrayList1.add(new FieldValue(((GetCustomKeyFields)localArrayList2.get(i)).getCustomKeyFieldId(), ((GetCustomKeyFields)localArrayList2.get(i)).getCustomKeyFieldTitle(), (String)localArrayList3.get(i)));
        i++;
      }
      return localArrayList1;
    }
    catch (Exception localException)
    {
      errorDialog();
      return null;
    }
  }
  
  public ArrayList<Group> getGroupDetail(int paramInt)
  {
    ArrayList localArrayList = new ArrayList();
    try
    {
      this.db = getReadableDatabase();
      this.cursor = this.db.rawQuery("SELECT * FROM GroupMaster WHERE GroupId = '" + paramInt + "' ", null);
      if ((this.cursor != null) && (this.cursor.moveToFirst())) {
        do
        {
          localArrayList.add(new Group(this.cursor.getString(this.cursor.getColumnIndex("GroupTitle")), this.cursor.getString(this.cursor.getColumnIndex("GroupDescription")), Boolean.valueOf(this.cursor.getString(this.cursor.getColumnIndex("GroupDeleted")).contains("true"))));
        } while (this.cursor.moveToNext());
      }
      this.cursor.close();
      this.db.close();
      return localArrayList;
    }
    catch (Exception localException)
    {
      errorDialog();
    }
    return localArrayList;
  }
  
  public int getGroupId(String paramString)
  {
    int i = 0;
    try
    {
      this.db = getReadableDatabase();
      this.cursor = this.db.rawQuery("SELECT GroupId FROM GroupMaster WHERE GroupTitle = '" + paramString + "'", null);
      Cursor localCursor = this.cursor;
      i = 0;
      if (localCursor != null)
      {
        boolean bool = this.cursor.moveToFirst();
        i = 0;
        if (bool) {
          do
          {
            i = this.cursor.getInt(this.cursor.getColumnIndex("GroupId"));
          } while (this.cursor.moveToNext());
        }
      }
      this.cursor.close();
      this.db.close();
      return i;
    }
    catch (Exception localException)
    {
      errorDialog();
    }
    return i;
  }
  
  public int getGroupIdByKeyId(int paramInt)
  {
    int i = 0;
    try
    {
      this.db = getReadableDatabase();
      this.cursor = this.db.rawQuery("SELECT GroupIdRef FROM KeyMaster WHERE KeyId = '" + paramInt + "'", null);
      Cursor localCursor = this.cursor;
      i = 0;
      if (localCursor != null)
      {
        boolean bool = this.cursor.moveToFirst();
        i = 0;
        if (bool) {
          do
          {
            i = this.cursor.getInt(this.cursor.getColumnIndex("GroupIdRef"));
          } while (this.cursor.moveToNext());
        }
      }
      this.cursor.close();
      this.db.close();
      return i;
    }
    catch (Exception localException)
    {
      errorDialog();
    }
    return i;
  }
  
  public void getGroupInTrash(int paramInt)
  {
    try
    {
      this.db = getReadableDatabase();
      this.db.execSQL("UPDATE KeyMaster SET KeyDeleted = 1 WHERE GroupIdRef = '" + paramInt + "'");
      this.db.execSQL("UPDATE GroupMaster SET GroupDeleted = 1 WHERE GroupId = '" + paramInt + "'");
      this.db.close();
      return;
    }
    catch (Exception localException)
    {
      errorDialog();
    }
  }
  
  public ArrayList<Items> getGroupList(int paramInt)
  {
    ArrayList localArrayList = new ArrayList();
    try
    {
      this.db = getReadableDatabase();
      this.cursor = this.db.rawQuery("SELECT * FROM GroupMaster WHERE GroupDeleted = '" + paramInt + "' ", null);
      if ((this.cursor != null) && (this.cursor.moveToFirst())) {
        do
        {
          localArrayList.add(new Items(this.cursor.getInt(this.cursor.getColumnIndex("GroupId")), this.cursor.getString(this.cursor.getColumnIndex("GroupTitle"))));
        } while (this.cursor.moveToNext());
      }
      this.cursor.close();
      this.db.close();
      return localArrayList;
    }
    catch (Exception localException)
    {
      errorDialog();
    }
    return localArrayList;
  }
  
  public void getGroupRestore(int paramInt)
  {
    try
    {
      this.db = getWritableDatabase();
      this.db.execSQL("UPDATE KeyMaster SET KeyDeleted = 0 WHERE GroupIdRef = '" + paramInt + "'");
      this.db.execSQL("UPDATE GroupMaster SET GroupDeleted = 0 WHERE GroupId = '" + paramInt + "'");
      this.db.close();
      return;
    }
    catch (Exception localException)
    {
      errorDialog();
    }
  }
  
  public void getGroupRestoreIndividually(int paramInt)
  {
    try
    {
      this.db = getWritableDatabase();
      this.db.execSQL("UPDATE GroupMaster SET GroupDeleted = 0 WHERE GroupId = '" + paramInt + "'");
      this.db.close();
      return;
    }
    catch (Exception localException)
    {
      errorDialog();
    }
  }
  
  public int getGroupTemplateIdRef(int paramInt)
  {
    int i = 0;
    try
    {
      this.db = getReadableDatabase();
      this.cursor = this.db.rawQuery("SELECT TemplateIdRef FROM GroupMaster WHERE GroupId ='" + paramInt + "'", null);
      Cursor localCursor = this.cursor;
      i = 0;
      if (localCursor != null)
      {
        boolean bool = this.cursor.moveToFirst();
        i = 0;
        if (bool) {
          do
          {
            i = this.cursor.getInt(this.cursor.getColumnIndex("TemplateIdRef"));
          } while (this.cursor.moveToNext());
        }
      }
      this.cursor.close();
      this.db.close();
      return i;
    }
    catch (Exception localException)
    {
      errorDialog();
    }
    return i;
  }
  
  public ArrayList<String> getGroupTitleList(int paramInt)
  {
    ArrayList localArrayList = new ArrayList();
    try
    {
      this.db = getReadableDatabase();
      this.cursor = this.db.rawQuery("SELECT * FROM GroupMaster WHERE GroupDeleted = '" + paramInt + "' ", null);
      if ((this.cursor != null) && (this.cursor.moveToFirst())) {
        do
        {
          localArrayList.add(this.cursor.getString(this.cursor.getColumnIndex("GroupTitle")));
        } while (this.cursor.moveToNext());
      }
      this.cursor.close();
      this.db.close();
      return localArrayList;
    }
    catch (Exception localException)
    {
      errorDialog();
    }
    return localArrayList;
  }
  
  public ArrayList<Integer> getKeyCount(int paramInt1, int paramInt2)
  {
    ArrayList localArrayList = new ArrayList();
    try
    {
      this.db = getReadableDatabase();
      this.cursor = this.db.rawQuery("SELECT KeyId FROM KeyMaster WHERE GroupIdRef = '" + paramInt1 + "' AND KeyDeleted = '" + paramInt2 + "'", null);
      if ((this.cursor != null) && (this.cursor.moveToFirst())) {
    	  localArrayList.add(Integer.valueOf(this.cursor.getInt(this.cursor.getColumnIndex("KeyId"))));       
      }
      while((this.cursor != null) && (this.cursor.moveToNext())){
    	  localArrayList.add(Integer.valueOf(this.cursor.getInt(this.cursor.getColumnIndex("KeyId"))));
      }
      this.cursor.close();
      this.db.close();
      return localArrayList;
    }
    catch (Exception localException)
    {
      errorDialog();
    }
    return localArrayList;
  }
  
  public ArrayList<KeyDetailByKeyId> getKeyDetail()
  {
	ArrayList localArrayList1 = new ArrayList();
   
    try
    {
      this.db = getReadableDatabase();
      Cursor localCursor = this.db.rawQuery("SELECT * FROM KeyMaster WHERE KeyDeleted ='0'", null);
      ArrayList localArrayList2 = new ArrayList();;
      if ((localCursor != null) && (localCursor.moveToFirst())) {
        localArrayList2 = getKeyDetail(localCursor.getInt(localCursor.getColumnIndex("KeyId")));
        localArrayList2 = getKeyDetail(localCursor.getInt(localCursor.getColumnIndex("KeyId")));
  	  	for (int i = 0;i < localArrayList2.size(); i++)
  	  	{
	        localArrayList1.add(new KeyDetailByKeyId(((KeyDetailByKeyId)localArrayList2.get(i)).getKeyId(), ((KeyDetailByKeyId)localArrayList2.get(i)).getKeyTitle(), ((KeyDetailByKeyId)localArrayList2.get(i)).getKeyUsername(), ((KeyDetailByKeyId)localArrayList2.get(i)).getKeyPassword(), ((KeyDetailByKeyId)localArrayList2.get(i)).getKeyUrl()));
  	  	}
      }	
      while(localCursor.moveToNext()){
	  	  localArrayList2 = getKeyDetail(localCursor.getInt(localCursor.getColumnIndex("KeyId")));
	      for (int i = 0;i < localArrayList2.size(); i++)
	      {
	        localArrayList1.add(new KeyDetailByKeyId(((KeyDetailByKeyId)localArrayList2.get(i)).getKeyId(), ((KeyDetailByKeyId)localArrayList2.get(i)).getKeyTitle(), ((KeyDetailByKeyId)localArrayList2.get(i)).getKeyUsername(), ((KeyDetailByKeyId)localArrayList2.get(i)).getKeyPassword(), ((KeyDetailByKeyId)localArrayList2.get(i)).getKeyUrl()));
	      }
      }
	  localCursor.close();
	  this.db.close();
	  return localArrayList1;
    }
    catch (Exception localException)
    {
      errorDialog();
      return null;
    }
  }
  
  public ArrayList<KeyDetailByKeyId> getKeyDetail(int paramInt)
  {
	  Log.e("getKeyDetail","a "+ paramInt);
	ArrayList localArrayList = new ArrayList();
    int i = 1;
    try
    {
      this.db = getReadableDatabase();
      this.cursor = this.db.rawQuery("SELECT * FROM KeyDetail WHERE KeyIdRef = '" + paramInt + "' ", null);
      String str1;
      String str2;
      String str3;
      if (this.cursor != null)
      {
    	  Log.e("getKeyDetail","v ");
        boolean bool = this.cursor.moveToFirst();
        str1 = null;
        str2 = null;
        str3 = null;
        if (bool) {
        	if (i == 1)
            {
              str1 = this.cursor.getString(this.cursor.getColumnIndex("KeyValue"));
              i++;
            }
        	while (this.cursor.moveToNext())
            {   
              if (i == 2)
              {
                str2 = this.cursor.getString(this.cursor.getColumnIndex("KeyValue"));
                i++;
              }
              else if (i == 3)
              {
                str3 = this.cursor.getString(this.cursor.getColumnIndex("KeyValue"));
                i++;
              }
              else if (i == 4)
              {
                localArrayList.add(new KeyDetailByKeyId(paramInt, str1, str2, str3, this.cursor.getString(this.cursor.getColumnIndex("KeyValue"))));
                
                i++;
              }
            }
        }
        this.cursor.close();
        this.db.close();
        return localArrayList;
      }
    }
    catch (Exception localException)
    {
      errorDialog();
      return null;
    }
	return localArrayList;
  }
  
  public ArrayList<KeyDetailByKeyId> getKeyDetailById(int paramInt)
  {
	  ArrayList localArrayList1 = new ArrayList();  
	  ArrayList localArrayList2= new ArrayList();
    try
    {
      this.db = getReadableDatabase();
      Cursor localCursor = this.db.rawQuery("SELECT KeyId FROM KeyMaster WHERE GroupIdRef = '" + paramInt + "' AND KeyDeleted = 0 ", null);
      
      if ((localCursor != null) && (localCursor.moveToFirst())) {
    	  localArrayList2 = getKeyDetail(localCursor.getInt(localCursor.getColumnIndex("KeyId")));
    	  for (int i = 0;i < localArrayList2.size(); i++)
	      {
	        localArrayList1.add(new KeyDetailByKeyId(((KeyDetailByKeyId)localArrayList2.get(i)).getKeyId(), ((KeyDetailByKeyId)localArrayList2.get(i)).getKeyTitle(), ((KeyDetailByKeyId)localArrayList2.get(i)).getKeyUsername(), ((KeyDetailByKeyId)localArrayList2.get(i)).getKeyPassword(), ((KeyDetailByKeyId)localArrayList2.get(i)).getKeyUrl()));
	      }
      }
      while(localCursor.moveToNext()){
    	  localArrayList2 = getKeyDetail(localCursor.getInt(localCursor.getColumnIndex("KeyId")));
	      for (int i = 0;i < localArrayList2.size(); i++)
	      {
	        localArrayList1.add(new KeyDetailByKeyId(((KeyDetailByKeyId)localArrayList2.get(i)).getKeyId(), ((KeyDetailByKeyId)localArrayList2.get(i)).getKeyTitle(), ((KeyDetailByKeyId)localArrayList2.get(i)).getKeyUsername(), ((KeyDetailByKeyId)localArrayList2.get(i)).getKeyPassword(), ((KeyDetailByKeyId)localArrayList2.get(i)).getKeyUrl()));
	      }
      }
	  localCursor.close();
	  this.db.close();
	  return localArrayList1;
    }
    catch (Exception localException)
    {
      errorDialog();
      return null;
    }
  }
  
  public ArrayList<KeyDetailByKeyId> getKeyDetail_deleted()
  {
	  ArrayList localArrayList1 = new ArrayList();
    new ArrayList();
    try
    {
      this.db = getReadableDatabase();
      Cursor localCursor = this.db.rawQuery("SELECT * FROM KeyMaster WHERE KeyDeleted ='1'", null);
      ArrayList localArrayList2 = new ArrayList();
      if ((localCursor != null) && (localCursor.moveToFirst())) {
        localArrayList2 = getKeyDetail(localCursor.getInt(localCursor.getColumnIndex("KeyId")));
        for (int i = 0;i < localArrayList2.size(); i++)
      	{
        	localArrayList1.add(new KeyDetailByKeyId(((KeyDetailByKeyId)localArrayList2.get(i)).getKeyId(), ((KeyDetailByKeyId)localArrayList2.get(i)).getKeyTitle(), ((KeyDetailByKeyId)localArrayList2.get(i)).getKeyUsername(), ((KeyDetailByKeyId)localArrayList2.get(i)).getKeyPassword(), ((KeyDetailByKeyId)localArrayList2.get(i)).getKeyUrl()));
      	}
        while(localCursor.moveToNext()){
        	localArrayList2 = getKeyDetail(localCursor.getInt(localCursor.getColumnIndex("KeyId")));
        	for (int i = 0;i < localArrayList2.size(); i++)
  	      	{
        		localArrayList1.add(new KeyDetailByKeyId(((KeyDetailByKeyId)localArrayList2.get(i)).getKeyId(), ((KeyDetailByKeyId)localArrayList2.get(i)).getKeyTitle(), ((KeyDetailByKeyId)localArrayList2.get(i)).getKeyUsername(), ((KeyDetailByKeyId)localArrayList2.get(i)).getKeyPassword(), ((KeyDetailByKeyId)localArrayList2.get(i)).getKeyUrl()));
  	      	}
        }
      }
    
          localCursor.close();
          this.db.close();
          return localArrayList1;
        
    }
    catch (Exception localException)
    {
      errorDialog();
      return null;
    }
  }
  
  public ArrayList<Integer> getKeyIdByGroupId(int paramInt)
  {
    ArrayList localArrayList = new ArrayList();
    try
    {
      this.db = getReadableDatabase();
      this.cursor = this.db.rawQuery("SELECT KeyId FROM KeyMaster WHERE GroupIdRef = '" + paramInt + "'", null);
      if ((this.cursor != null) && (this.cursor.moveToFirst())) {
        do
        {
          localArrayList.add(Integer.valueOf(this.cursor.getInt(this.cursor.getColumnIndex("KeyId"))));
        } while (this.cursor.moveToNext());
      }
      this.cursor.close();
      this.db.close();
      return localArrayList;
    }
    catch (Exception localException)
    {
      errorDialog();
    }
    return localArrayList;
  }
  
  public ArrayList<Integer> getKeyIdByGroupIdInTrash(int paramInt)
  {
    ArrayList localArrayList = new ArrayList();
    try
    {
      this.db = getReadableDatabase();
      this.cursor = this.db.rawQuery("SELECT KeyId FROM KeyMaster WHERE GroupIdRef = '" + paramInt + "' AND KeyDeleted ='1'", null);
      if ((this.cursor != null) && (this.cursor.moveToFirst())) {
        do
        {
          localArrayList.add(Integer.valueOf(this.cursor.getInt(this.cursor.getColumnIndex("KeyId"))));
        } while (this.cursor.moveToNext());
      }
      this.cursor.close();
      this.db.close();
      return localArrayList;
    }
    catch (Exception localException)
    {
      errorDialog();
    }
    return localArrayList;
  }
  
  public ArrayList<Integer> getKeyIdList(int paramInt)
  {
    ArrayList localArrayList = new ArrayList();
    try
    {
      this.db = getReadableDatabase();
      this.cursor = this.db.rawQuery("SELECT KeyId FROM KeyMaster WHERE KeyDeleted = '" + paramInt + "'", null);
      if ((this.cursor != null) && (this.cursor.moveToFirst())) {
        do
        {
          localArrayList.add(Integer.valueOf(this.cursor.getInt(this.cursor.getColumnIndex("KeyId"))));
        } while (this.cursor.moveToNext());
      }
      this.cursor.close();
      this.db.close();
      return localArrayList;
    }
    catch (Exception localException)
    {
      errorDialog();
    }
    return localArrayList;
  }
  
  public void getKeyInTrash(int paramInt)
  {
    try
    {
      this.db = getWritableDatabase();
      this.db.execSQL("UPDATE KeyMaster SET KeyDeleted = 1 WHERE KeyId = '" + paramInt + "'");
      this.db.close();
      return;
    }
    catch (Exception localException)
    {
      errorDialog();
    }
  }
  
  public void getKeyRestore(int paramInt)
  {
    try
    {
      this.db = getWritableDatabase();
      this.db.execSQL("UPDATE KeyMaster SET KeyDeleted = 0 WHERE KeyId = '" + paramInt + "'");
      this.db.close();
      return;
    }
    catch (Exception localException)
    {
      errorDialog();
    }
  }
  
  public ArrayList<String> getKeyValue(int paramInt, ArrayList<Integer> paramArrayList)
  {
	  ArrayList localArrayList = new ArrayList();
    int i = 0;
    try
    {
      for (;;)
      {
        if (i >= paramArrayList.size()) {
          return localArrayList;
        }
        this.db = getReadableDatabase();
        this.cursor = this.db.rawQuery("SELECT KeyValue FROM KeyDetail WHERE CustomKeyFieldIdRef = '" + paramInt + "' AND KeyIdRef = '" + paramArrayList.get(i) + "'", null);
        if ((this.cursor != null) && (this.cursor.moveToFirst())) {
          localArrayList.add(this.cursor.getString(this.cursor.getColumnIndex("KeyValue")).toLowerCase());
          while (this.cursor.moveToNext()){
        	  localArrayList.add(this.cursor.getString(this.cursor.getColumnIndex("KeyValue")).toLowerCase());
          }
        }
        this.cursor.close();
        this.db.close();
        i++;
      }
      
    }
    catch (Exception localException)
    {
      errorDialog();
    }
    return localArrayList;
  }
  
  public ArrayList<String> getKeyValueByKeyId(int paramInt, ArrayList<GetCustomKeyFields> paramArrayList)
  {
	  ArrayList localArrayList = new ArrayList();
    int i = 0;
    try
    {
      for (;;)
      {
        if (i >= paramArrayList.size()) {
          return localArrayList;
        }
        this.db = getReadableDatabase();
        this.cursor = this.db.rawQuery("SELECT KeyValue FROM KeyDetail WHERE CustomKeyFieldIdRef = '" + ((GetCustomKeyFields)paramArrayList.get(i)).getCustomKeyFieldId() + "' AND KeyIdRef = '" + paramInt + "'", null);
        if ((this.cursor != null) && (this.cursor.moveToFirst())) {
          
            localArrayList.add(this.cursor.getString(this.cursor.getColumnIndex("KeyValue")));
            while (this.cursor.moveToNext()){
            	localArrayList.add(this.cursor.getString(this.cursor.getColumnIndex("KeyValue")));
            }
        }
        
        this.cursor.close();
        this.db.close();
        i++;
      }
    }
    catch (Exception localException)
    {
      errorDialog();
    }

    return localArrayList;
  }
  
  public int getLastKeyId()
  {
    int i = 0;
    try
    {
      this.db = getReadableDatabase();
      this.cursor = this.db.rawQuery("SELECT KeyId FROM KeyMaster order by KeyId DESC limit 1", null);
      Cursor localCursor = this.cursor;
      i = 0;
      if (localCursor != null)
      {
        boolean bool = this.cursor.moveToFirst();
        i = 0;
        if (bool) {
          do
          {
            i = this.cursor.getInt(this.cursor.getColumnIndex("KeyId"));
          } while (this.cursor.moveToNext());
        }
      }
      this.cursor.close();
      this.db.close();
      return i;
    }
    catch (Exception localException)
    {
      errorDialog();
    }
    return i;
  }
  
  public ArrayList<String> getTemplateField(int paramInt)
  {
    ArrayList localArrayList = new ArrayList();
    try
    {
      this.db = getReadableDatabase();
      this.cursor = this.db.rawQuery("SELECT CustomKeyFieldTitle FROM CustomKeyFieldMaster WHERE TemplateIdRef =" + paramInt, null);
      if ((this.cursor != null) && (this.cursor.moveToFirst())) {
        do
        {
          localArrayList.add(this.cursor.getString(this.cursor.getColumnIndex("CustomKeyFieldTitle")));
        } while (this.cursor.moveToNext());
      }
      this.cursor.close();
      this.db.close();
      return localArrayList;
    }
    catch (Exception localException)
    {
      errorDialog();
    }
    return localArrayList;
  }
  
  public int getTemplateIdByName(String paramString)
  {
    SQLiteDatabase localSQLiteDatabase = getReadableDatabase();
    int i = 0;
    try
    {
      this.cursor = localSQLiteDatabase.rawQuery("SELECT TemplateId FROM TemplateMaster WHERE TemplateName = '" + paramString + "'", null);
      Cursor localCursor = this.cursor;
      i = 0;
      if (localCursor != null)
      {
        boolean bool = this.cursor.moveToFirst();
        i = 0;
        if (bool) {
          do
          {
            i = this.cursor.getInt(this.cursor.getColumnIndex("TemplateId"));
          } while (this.cursor.moveToNext());
        }
      }
      this.cursor.close();
      localSQLiteDatabase.close();
      return i;
    }
    catch (Exception localException)
    {
      errorDialog();
    }
    return i;
  }
  
  public int getTemplateIdRefByKeyId(int paramInt)
  {
    int i = 0;
    try
    {
      this.db = getReadableDatabase();
      this.cursor = this.db.rawQuery("SELECT TemplateIdRef FROM KeyMaster WHERE KeyId = '" + paramInt + "' ", null);
      Cursor localCursor = this.cursor;
      i = 0;
      if (localCursor != null)
      {
        boolean bool = this.cursor.moveToFirst();
        i = 0;
        if (bool) {
          do
          {
            i = this.cursor.getInt(this.cursor.getColumnIndex("TemplateIdRef"));
          } while (this.cursor.moveToNext());
        }
      }
      this.cursor.close();
      this.db.close();
      return i;
    }
    catch (Exception localException)
    {
      errorDialog();
    }
    return i;
  }
  
  public ArrayList<Items> getTemplateList()
  {
    ArrayList localArrayList = new ArrayList();
    SQLiteDatabase localSQLiteDatabase = getReadableDatabase();
    try
    {
      this.cursor = localSQLiteDatabase.rawQuery("SELECT * FROM TemplateMaster", null);
      if ((this.cursor != null) && (this.cursor.moveToFirst())) {
        do
        {
          localArrayList.add(new Items(this.cursor.getInt(this.cursor.getColumnIndex("TemplateId")), this.cursor.getString(this.cursor.getColumnIndex("TemplateName"))));
        } while (this.cursor.moveToNext());
      }
      this.cursor.close();
      localSQLiteDatabase.close();
      return localArrayList;
    }
    catch (Exception localException)
    {
      errorDialog();
    }
    return localArrayList;
  }
  
  public String getTemplateName(int paramInt)
  {
    this.db = getReadableDatabase();
    String str = null;
    try
    {
      this.cursor = this.db.rawQuery("SELECT * FROM TemplateMaster WHERE TemplateId = '" + paramInt + "'", null);
      Cursor localCursor = this.cursor;
      str = null;
      if (localCursor != null)
      {
        boolean bool = this.cursor.moveToFirst();
        str = null;
        if (bool) {
          do
          {
            str = this.cursor.getString(this.cursor.getColumnIndex("TemplateName"));
          } while (this.cursor.moveToNext());
        }
      }
      this.cursor.close();
      this.db.close();
      return str;
    }
    catch (Exception localException)
    {
      errorDialog();
    }
    return str;
  }
  
  public Boolean groupAlreadyExist(String paramString)
  {
    Boolean localBoolean1 = Boolean.valueOf(false);
    try
    {
      this.db = getReadableDatabase();
      this.cursor = this.db.rawQuery("SELECT GroupTitle FROM GroupMaster WHERE LOWER(GroupTitle) = '" + paramString.toLowerCase() + "'", null);
      int i = this.cursor.getCount();
      this.cursor.close();
      this.db.close();
      if (i == 0) {
        return Boolean.valueOf(false);
      }
      Boolean localBoolean2 = Boolean.valueOf(true);
      return localBoolean2;
    }
    catch (Exception localException)
    {
      errorDialog();
    }
    return localBoolean1;
  }
  
  public Boolean groupExistInTrash(String paramString)
  {
    Boolean localBoolean1 = Boolean.valueOf(false);
    try
    {
      this.db = getReadableDatabase();
      this.cursor = this.db.rawQuery("SELECT GroupTitle FROM GroupMaster WHERE LOWER(GroupTitle) = '" + paramString.toLowerCase() + "' AND GroupDeleted = 1", null);
      int i = this.cursor.getCount();
      this.cursor.close();
      this.db.close();
      if (i == 0) {
        return Boolean.valueOf(false);
      }
      Boolean localBoolean2 = Boolean.valueOf(true);
      return localBoolean2;
    }
    catch (Exception localException)
    {
      errorDialog();
    }
    return localBoolean1;
  }
  
  public int keyAlreadyExist(String paramString)
  {
    try
    {
      this.db = getReadableDatabase();
      this.cursor = this.db.rawQuery("SELECT GroupTitle FROM GroupMaster WHERE LOWER(GroupTitle) = '" + "GroupTitle".toLowerCase() + "'", null);
      int i = this.cursor.getCount();
      this.cursor.close();
      this.db.close();
      return i;
    }
    catch (Exception localException)
    {
      errorDialog();
    }
    return 0;
  }
  
  public void onCreate(SQLiteDatabase paramSQLiteDatabase)
  {
    paramSQLiteDatabase.execSQL("CREATE TABLE GroupMaster (GroupId INTEGER PRIMARY KEY AUTOINCREMENT, GroupTitle TEXT, GroupDescription TEXT, GroupDeleted BOOLEAN)");
    paramSQLiteDatabase.execSQL("CREATE TABLE CustomKeyFieldMaster (CustomKeyFieldId INTEGER PRIMARY KEY AUTOINCREMENT, TemplateIdRef INTEGER, CustomKeyFieldTitle TEXT, CustomKeyFieldSize INTEGER, CustomKeyFieldInputType TEXT, CustomKeyFieldDisplayOrder INTEGER)");
    paramSQLiteDatabase.execSQL("CREATE TABLE KeyMaster (KeyId INTEGER PRIMARY KEY AUTOINCREMENT, GroupIdRef INTEGER, KeyDeleted BOOLEAN, TemplateIdRef INTEGER)");
    paramSQLiteDatabase.execSQL("CREATE TABLE KeyDetail (KeyIdRef INTEGER, CustomKeyFieldIdRef INTEGER, KeyValue TEXT)");
    paramSQLiteDatabase.execSQL("CREATE TABLE TemplateMaster (TemplateId INTEGER PRIMARY KEY AUTOINCREMENT, TemplateName TEXT)");
    paramSQLiteDatabase.execSQL("INSERT INTO TemplateMaster (TemplateName) VALUES ('默认模板')");
    this.cursor = paramSQLiteDatabase.rawQuery("SELECT TemplateId FROM TemplateMaster WHERE TemplateName = '默认模板'", null);
    if ((this.cursor != null) && (this.cursor.moveToFirst())) {
      do
      {
        this.generalTemplateId = this.cursor.getInt(this.cursor.getColumnIndex("TemplateId"));
      } while (this.cursor.moveToNext());
    }
    this.cursor.close();
    paramSQLiteDatabase.execSQL("INSERT INTO CustomKeyFieldMaster (TemplateIdRef, CustomKeyFieldTitle, CustomKeyFieldSize, CustomKeyFieldInputType, CustomKeyFieldDisplayOrder) VALUES ('" + this.generalTemplateId + "', 'Title', '" + 0 + "', '" + null + "', '" + 0 + "')");
    paramSQLiteDatabase.execSQL("INSERT INTO CustomKeyFieldMaster (TemplateIdRef, CustomKeyFieldTitle, CustomKeyFieldSize, CustomKeyFieldInputType, CustomKeyFieldDisplayOrder) VALUES ('" + this.generalTemplateId + "', 'Username', '" + 0 + "', '" + null + "', '" + 0 + "')");
    paramSQLiteDatabase.execSQL("INSERT INTO CustomKeyFieldMaster (TemplateIdRef, CustomKeyFieldTitle, CustomKeyFieldSize, CustomKeyFieldInputType, CustomKeyFieldDisplayOrder) VALUES ('" + this.generalTemplateId + "', 'Password', '" + 0 + "', '" + null + "', '" + 0 + "')");
    paramSQLiteDatabase.execSQL("INSERT INTO CustomKeyFieldMaster (TemplateIdRef, CustomKeyFieldTitle, CustomKeyFieldSize, CustomKeyFieldInputType, CustomKeyFieldDisplayOrder) VALUES ('" + this.generalTemplateId + "', 'Url', '" + 0 + "', '" + null + "', '" + 0 + "')");
    paramSQLiteDatabase.execSQL("INSERT INTO GroupMaster (GroupTitle, GroupDescription, GroupDeleted) VALUES ('General', 'No Description', '0')");
  }
  
  public void onUpgrade(SQLiteDatabase paramSQLiteDatabase, int paramInt1, int paramInt2) {}
  
  public int templateAlreadyExists(String paramString)
  {
    int i = 0;
    try
    {
      SQLiteDatabase localSQLiteDatabase = getReadableDatabase();
      this.cursor = localSQLiteDatabase.rawQuery("SELECT * FROM TemplateMaster WHERE LOWER(TemplateName) = '" + paramString.toLowerCase() + "'", null);
      i = this.cursor.getCount();
      this.cursor.close();
      localSQLiteDatabase.close();
      return i;
    }
    catch (Exception localException)
    {
      errorDialog();
    }
    return i;
  }
  
  public int templateFieldAlreadyExists(int paramInt, String paramString)
  {
    int i = 0;
    try
    {
      SQLiteDatabase localSQLiteDatabase = getWritableDatabase();
      this.cursor = localSQLiteDatabase.rawQuery("SELECT * FROM CustomKeyFieldMaster WHERE TemplateIdRef = '" + paramInt + "' AND LOWER(CustomKeyFieldTitle) = '" + paramString.toLowerCase() + "'", null);
      i = this.cursor.getCount();
      this.cursor.close();
      localSQLiteDatabase.close();
      return i;
    }
    catch (Exception localException)
    {
      errorDialog();
    }
    return i;
  }
  
  public void updateCustomKeyFieldTitle(String paramString1, String paramString2, int paramInt)
  {
    try
    {
      this.db = getReadableDatabase();
      this.db.execSQL("UPDATE CustomKeyFieldMaster SET CustomKeyFieldTitle = '" + paramString1 + "' WHERE CustomKeyFieldTitle = '" + paramString2 + "' AND CustomKeyFieldId = '" + paramInt + "'");
      this.db.close();
      return;
    }
    catch (Exception localException)
    {
      errorDialog();
    }
  }
  
  public void updateGroupDetail(int paramInt, String paramString1, String paramString2)
  {
    try
    {
      this.db = getWritableDatabase();
      String str = "UPDATE GroupMaster SET GroupTitle = '" + paramString1 + "', GroupDescription ='" + paramString2 + "'WHERE GroupId = '" + paramInt + "' ";
      this.db.execSQL(str);
      this.db.close();
      return;
    }
    catch (Exception localException)
    {
      errorDialog();
    }
  }
  
  public void updateKeyDetail(int paramInt1, int paramInt2, String paramString)
  {
    try
    {
      this.db = getReadableDatabase();
      this.db.execSQL("UPDATE KeyDetail SET KeyValue = '" + paramString + "' WHERE KeyIdRef = '" + paramInt1 + "' AND CustomKeyFieldIdRef = '" + paramInt2 + "'");
      this.db.close();
      return;
    }
    catch (Exception localException)
    {
      errorDialog();
    }
  }
  
  public void updateKeyMaster(int paramInt1, int paramInt2, int paramInt3)
  {
    try
    {
      this.db = getWritableDatabase();
      String str = "UPDATE KeyMaster SET GroupIdRef = '" + paramInt1 + "', TemplateIdRef = '" + paramInt2 + "'WHERE KeyId = '" + paramInt3 + "' ";
      this.db.execSQL(str);
      this.db.close();
      return;
    }
    catch (Exception localException)
    {
      errorDialog();
    }
  }
  
  public void updateTemplateName(int paramInt, String paramString)
  {
    try
    {
      this.db = getWritableDatabase();
      this.db.execSQL("UPDATE TemplateMaster SET TemplateName = '" + paramString + "' WHERE TemplateId = '" + paramInt + "'");
      this.db.close();
      return;
    }
    catch (Exception localException)
    {
      errorDialog();
    }
  }
}


/* Location:           D:\Apkdb\Craining\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     rdi.mobapp.passwordpanacea.databasehelper.DBHelper
 * JD-Core Version:    0.7.0.1
 */