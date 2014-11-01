/*
 * This class is responsible for manage all operations related to the database.
 * Here is defined the methods of insertion, query, delete, and update 
 * data in the database
 */


/*

======== ATTENTION: THIS CLASS MUST TO DIE!!!! REPLACE IT FOR REAL DAO! ==========
Obs.: Based on that, I will not refactoring it.

*/

package com.pvmp.dao;


import com.pvmp.models.User;
import com.pvmp.util.Util;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;

public class UserDAO {
	public static final String TABLE_NAME = "User";
	public static final String COLUMN_USERNAME = "username";
	public static final String COLUMN_PASSWORD = "password";
	public static final String COLUMN_NAME = "name";
	public static final String COLUMN_EMAIL = "email";
	public static final String COLUMN_AGE = "age";
	public static final String COLUMN_EDUCATION = "education";
	public static final String COLUMN_SEX = "sex";
	public static final String COLUMN_DEFAULT = "default_user";
	public static final String SCRIPT_TABLE_CREATION_USER = 
			"CREATE TABLE " + TABLE_NAME + "(" 
		    + COLUMN_USERNAME + " VARCHAR(15) NOT NULL PRIMARY KEY,"
			+ COLUMN_PASSWORD + " VARCHAR(15) NOT NULL,"
			+ COLUMN_NAME + " VARCHAR(50) NOT NULL,"
			+ COLUMN_EMAIL + " VARCHAR(40) NOT NULL,"
			+ COLUMN_AGE + " INTEGER NOT NULL,"
			+ COLUMN_EDUCATION + " VARCHAR(11) NOT NULL,"
			+ COLUMN_SEX + " VARCHAR(9) NOT NULL,"
			+ COLUMN_DEFAULT + " VARCHAR(1) NOT NULL"
			+ ");";
	 
	public static final String SCRIPT_TABLE_DELETION = 
			"DROP TABLE IF EXISTS " + TABLE_NAME;
	
	private static SQLiteDatabase database = null;
	
	private static UserDAO instance = null;
	
	public static UserDAO getInstance(Context context) {
		if(instance == null)
		{
			Util.debug("UserDAO: Try to get data");
			instance = new UserDAO(context.getApplicationContext());
			Util.debug("UserDAO: Returned");
		}
		return instance;
	}
	
	private UserDAO(Context context) {
		PersistenceHelper persistenceHelper = PersistenceHelper.getInstance(context);
		database = persistenceHelper.getWritableDatabase();
	}
	
	public void save(User user) {
		ContentValues values = generateContentValuesUser(user);
		database.insert(TABLE_NAME, null, values);
	}
	
	public User selectByUsername(String username) {
        String queryUser = "SELECT * FROM " + TABLE_NAME + " where " + COLUMN_USERNAME + " = ?";
        User user = recoverByQuery(queryUser, username);
        
        return user;   
    }
	
	public User selectByEmail(String email) {
        String queryUser = "SELECT * FROM " + TABLE_NAME + " where " + COLUMN_EMAIL + " = ?";
        User user = recoverByQuery(queryUser, email);
        
        return user;   
    }
	
	public User selectByDefault(String defaultUser){
		String queryUser = "SELECT * FROM " + TABLE_NAME + " where " + COLUMN_DEFAULT + " = ?";
		User user = recoverByQuery(queryUser, defaultUser);
		return user;
	}
	
	public User recoverByQuery (String query, String valor) {
		Cursor cursor = database.rawQuery(query, new String [] {valor});
 
        User user = new User();
        
        if (cursor.moveToFirst()){
        	ContentValues contentValues = new ContentValues();
        	DatabaseUtils.cursorRowToContentValues(cursor, contentValues);
        	user = contentValuesToUser(contentValues);
        }
        
        return user; 
	}
	
	public void delete(User user) {
		String[] valuesToReplace = {
				String.valueOf(user.getUsername())
		};		
		database.delete(TABLE_NAME, COLUMN_USERNAME + " = ?", valuesToReplace);
	}
	
	public void edit(User user) {
		ContentValues values = generateContentValuesUser(user);
		
		String[] valuesToReplace = {
				String.valueOf(user.getUsername())
		};
		
		database.update(TABLE_NAME, values, COLUMN_USERNAME + " = ?", valuesToReplace);
	}
	
	public void closeConnection() {
		if(database != null && database.isOpen())
			database.close();
	}
	
	 public User contentValuesToUser(ContentValues contentValues) {
        User user = new User();
        user.setUsername(contentValues.getAsString(COLUMN_USERNAME));
        user.setPassword(contentValues.getAsString(COLUMN_PASSWORD));
        user.setName(contentValues.getAsString(COLUMN_NAME));
        user.setEmail(contentValues.getAsString(COLUMN_EMAIL));
        user.setAge(contentValues.getAsInteger(COLUMN_AGE));
        user.setEducation(contentValues.getAsString(COLUMN_EDUCATION));
        user.setSex(contentValues.getAsString(COLUMN_SEX));
        user.setDefaultUser(contentValues.getAsString(COLUMN_DEFAULT));
        
        return user;
    }
		
	private ContentValues generateContentValuesUser(User user) {
		ContentValues values = new ContentValues();
		values.put(COLUMN_USERNAME, user.getUsername());
		values.put(COLUMN_PASSWORD, user.getPassword());
		values.put(COLUMN_NAME, user.getName());
		values.put(COLUMN_EMAIL, user.getEmail());
		values.put(COLUMN_AGE, user.getAge());
		values.put(COLUMN_EDUCATION, user.getEducation());
		values.put(COLUMN_SEX, user.getSex());
		values.put(COLUMN_DEFAULT, user.getDefaultUser());
		return values;
	}
}
