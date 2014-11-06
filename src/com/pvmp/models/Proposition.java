package com.pvmp.models;

import android.content.ContentValues;

import com.pvmp.dao.DAOAbstract;

public class Proposition extends DAOAbstract
{
	// !--- DATABASE ATTRIBUTES ---! \\ 
	public static final String COLUMN_ID_PROP = "id_prop";
	public static final String COLUMN_YEAR = "year";
	public static final String COLUMN_MENU = "menu";
	public static final String COLUMN_AUTHOR = "author";
	public static final String COLUMN_ACRONYM = "acronym";
	public static final String COLUMN_SITUATION = "situation";
	public static final String COLUMN_NUMBER = "number";
	public static final String COLUMN_CATEGORY = "category";
	
	// !--- OTHER ATTRIBUTES ---! \\
	private Integer id;
	private String year;
	private String menu;
	private String acronym;
	private String author;
	private String situation;
	private Integer number;
	private String category;
	
	public Proposition () {
		super();
		this.TABLE_NAME = "Proposition";
		this.id = null;
		this.year = null;
		this.menu = null;
		this.acronym = null;
		this.author = null;
		this.situation = null;
		this.number = null;
		this.category = null;
	}
	
	public Proposition(Integer id, String year, String menu, String acronym,
			String author, String situation, Integer number, String category) {
		super();
		this.TABLE_NAME = "Proposition";
		this.id = id;
		this.year = year;
		this.menu = menu;
		this.acronym = acronym;
		this.author = author;
		this.situation = situation;
		this.number = number;
		this.category = category;
	}

	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getMenu() {
		return menu;
	}
	public void setMenu(String menu) {
		this.menu = menu;
	}
	public String getAcronym() {
		return acronym;
	}
	public void setAcronym(String acronym) {
		this.acronym = acronym;
	}
	public String getSituation() {
		return situation;
	}
	public void setSituation(String situation) {
		this.situation = situation;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}

	public ContentValues generateContentValues() 
	{
		ContentValues values = new ContentValues();
		
		values.put(COLUMN_ID_PROP, this.id);
		values.put(COLUMN_YEAR, this.year);
		values.put(COLUMN_MENU, this.menu);
		values.put(COLUMN_AUTHOR, this.author);
		values.put(COLUMN_ACRONYM, this.acronym);
		values.put(COLUMN_SITUATION, this.situation);
		values.put(COLUMN_NUMBER, this.number);
		values.put(COLUMN_CATEGORY, this.category);
		
		return values;
	}

	@Override
	public Proposition contentValuesToModel(ContentValues _contentValues) 
	{
		Proposition proposition = new Proposition ();
		
		proposition.setId(_contentValues.getAsInteger(COLUMN_ID_PROP));
		proposition.setYear(_contentValues.getAsString(COLUMN_YEAR));
		proposition.setMenu(_contentValues.getAsString(COLUMN_MENU));
		proposition.setAuthor(_contentValues.getAsString(COLUMN_AUTHOR));
		proposition.setAcronym(_contentValues.getAsString(COLUMN_ACRONYM));
		proposition.setSituation(_contentValues.getAsString(COLUMN_SITUATION));
		proposition.setNumber(_contentValues.getAsInteger(COLUMN_NUMBER));
		proposition.setCategory(_contentValues.getAsString(COLUMN_CATEGORY));
        
        return proposition;
	}
}
