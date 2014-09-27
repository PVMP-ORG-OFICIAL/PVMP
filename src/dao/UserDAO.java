package dao;

import java.util.ArrayList;
import java.util.List;

import database.PersistenceHelper;

import models.User;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class UserDAO {

	public static final String TABLE_NAME = "USER";
	public static final String COLUMN_USERNAME = "USERNAME";
	public static final String COLUMN_PASSWORD = "PASSWORD";
	public static final String COLUMN_NAME = "NAME";
	public static final String COLUMN_EMAIL = "EMAIL";
	public static final String COLUMN_AGE = "AGE";
	public static final String COLUMN_EDUCATION = "EDUCATION";
	public static final String COLUMN_SEX = "SEX";
	
	public static final String SCRIPT_TABLE_CREATION_USER = 
			"CREATE TABLE " + TABLE_NAME + "(" 
		    + COLUMN_USERNAME + " VARCHAR(15) NOT NULL PRIMARY KEY,"
			+ COLUMN_PASSWORD + " VARCHAR(15) NOT NULL,"
			+ COLUMN_NAME + " VARCHAR(50) NOT NULL,"
			+ COLUMN_EMAIL + " VARCHAR(40) NOT NULL,"
			+ COLUMN_AGE + " INTEGER NOT NULL,"
			+ COLUMN_EDUCATION + " VARCHAR(11) NOT NULL"
			+ COLUMN_SEX + " VARCHAR(9) NOT NULL"
			+ ");";
	
	public static final String SCRIPT_TABLE_DELETION = 
			"DROP TABLE IF EXISTS " + TABLE_NAME;
	
	private SQLiteDatabase database = null;
	
	private static UserDAO instance;
	
	public static UserDAO getInstance(Context context) {
		if(instance == null)
			instance = new UserDAO(context);
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
	
	public List<User> recoverAll() {
		String queryReturnAll = "SELECT FROM " + TABLE_NAME;
		Cursor cursor = database.rawQuery(queryReturnAll, null);
		List<User> users = buildUserByCursor(cursor);
		
		return users;
	}
	
	public void delete(User user) {
		String[] valuesToReplace = {
				String.valueOf(user.getUserName())
		};
		
		database.delete(TABLE_NAME, COLUMN_USERNAME + " = ?", valuesToReplace);
	}
	
	public void edit(User user) {
		ContentValues values = generateContentValuesUser(user);
		
		String[] valuesToReplace = {
				String.valueOf(user.getUserName())
		};
		
		database.update(TABLE_NAME, values, COLUMN_USERNAME + " = ?", valuesToReplace);
	}
	
	public void closeConnection() {
		if(database != null && database.isOpen())
			database.close();
	}
	
	private List<User> buildUserByCursor(Cursor cursor) {
		List<User> users = new ArrayList<User>();
		if(cursor == null)
			return users;
		try {
			if(cursor.moveToFirst()) {
				do{
					int indexUserName = cursor.getColumnIndex(COLUMN_USERNAME);
					int indexPassword = cursor.getColumnIndex(COLUMN_PASSWORD);
					int indexName = cursor.getColumnIndex(COLUMN_NAME);
					int indexEmail = cursor.getColumnIndex(COLUMN_EMAIL);
					int indexAge = cursor.getColumnIndex(COLUMN_AGE);
					int indexEducation = cursor.getColumnIndex(COLUMN_EDUCATION);
					int indexSex = cursor.getColumnIndex(COLUMN_SEX);
					
					String userName = cursor.getString(indexUserName);
					String password = cursor.getString(indexPassword);
					String name = cursor.getString(indexName);
					String email = cursor.getString(indexEmail);
					int age = cursor.getInt(indexAge);
					String education = cursor.getString(indexEducation);
					String sex = cursor.getString(indexSex);
					
					User user = new User(userName, password, name, email, age, education, sex);
					
					users.add(user);
					
				} while(cursor.moveToNext());
			}
		} finally {
			cursor.close();
		}
		return users;
	}
	
	private ContentValues generateContentValuesUser(User user) {
		ContentValues values = new ContentValues();
		values.put(COLUMN_USERNAME, user.getUserName());
		values.put(COLUMN_PASSWORD, user.getPassword());
		values.put(COLUMN_NAME, user.getName());
		values.put(COLUMN_EMAIL, user.getEmail());
		values.put(COLUMN_AGE, user.getAge());
		values.put(COLUMN_EDUCATION, user.getEducation());
		values.put(COLUMN_SEX, user.getSex());
		return values;
	}
}
