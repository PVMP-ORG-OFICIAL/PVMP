package com.pvmp.models;

import java.util.Date;
import java.util.List;

import android.content.ContentValues;

import com.pvmp.dao.DAOAbstract;

public class Proposition extends DAOAbstract
{

	public static final String COLUMN_ID_PROP = "id_prop";
	public static final String COLUMN_YEAR = "year";
	public static final String COLUMN_MENU = "menu";
	public static final String COLUMN_AUTHOR = "author";
	public static final String COLUMN_ACRONYM = "acronym";
	public static final String COLUMN_SITUATION = "situation";
	public static final String COLUMN_NUMBER = "number";
	public static final String COLUMN_CATEGORY = "category";
	
	private Integer id;
	private String year;
	private String menu;
	private String acronym;
	private String author;
	private String situation;
	private Integer number;
	private String category;
	
	
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
		this.id = _contentValues.getAsInteger(COLUMN_ID_PROP);
        this.year = _contentValues.getAsString(COLUMN_YEAR);
        this.menu = _contentValues.getAsString(COLUMN_MENU);
        this.author = _contentValues.getAsString(COLUMN_AUTHOR);
        this.acronym = _contentValues.getAsString(COLUMN_ACRONYM);
        this.situation = _contentValues.getAsString(COLUMN_SITUATION);
        this.number = _contentValues.getAsInteger(COLUMN_NUMBER);
        this.category = _contentValues.getAsString(COLUMN_CATEGORY);
        
        return this;
	}
}