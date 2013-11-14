package com.naderdabour.myrecipebook.data;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.naderdabour.myrecipebook.models.Category;
import com.naderdabour.myrecipebook.models.Ingredient;
import com.naderdabour.myrecipebook.models.Recipe;

public class RecipeDatasource extends GenericDatasource<Recipe> {

	private static final String[] allColumns = {
		DatabaseHelper.TABLE_RECIPE_ID,
		DatabaseHelper.TABLE_RECIPE_NAME,
		DatabaseHelper.TABLE_RECIPE_DETAILS,
		DatabaseHelper.TABLE_RECIPE_IMAGE,
		DatabaseHelper.TABLE_RECIPE_CATEGORY_ID
	};
	
	private Context context;
	
	public RecipeDatasource(Context context) {
		super(context, DatabaseHelper.TABLE_RECIPE, allColumns);
		this.context = context;
	}
	
	@Override
	protected ContentValues entryToContentValues(Recipe entry) {
		
		ContentValues values = new ContentValues();
		Log.v("RecipeDatasource","entryToContentValues begin");
		values.put(DatabaseHelper.TABLE_RECIPE_NAME, entry.getName());
		Log.v("RecipeDatasource","Name OK");
		values.put(DatabaseHelper.TABLE_RECIPE_DETAILS, entry.getDetails());
		Log.v("RecipeDatasource","Details OK");
		values.put(DatabaseHelper.TABLE_RECIPE_IMAGE, entry.getImage());
		Log.v("RecipeDatasource","Image OK");
		values.put(DatabaseHelper.TABLE_RECIPE_CATEGORY_ID, entry.getCategoryId());
		Log.v("RecipeDatasource","CategoryId OK");
		return values;
	}

	@Override
	protected long getEntryId(Recipe entry) {
		
		return entry.getId();
	}

	@Override
	protected void setEntryId(Recipe entry, long insertId) {
		entry.setId(insertId);
		
	}

	@Override
	protected List<Recipe> cursorToList(Cursor cursor) {
		
		List<Recipe> recipes = new ArrayList<Recipe>();
		if(cursor.getCount() > 0){
			
			while (cursor.moveToNext()) {
				Log.v("RecipeDatasource","cursorToList begin");
				Recipe recipe = new Recipe();
				recipe.setId(cursor.getLong(cursor.getColumnIndex(DatabaseHelper.TABLE_RECIPE_ID)));
				Log.v("RecipeDatasource","cursorToList setId");
				recipe.setName(cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_RECIPE_NAME)));
				Log.v("RecipeDatasource","cursorToList setName");
				recipe.setImage(cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_RECIPE_IMAGE)));
				Log.v("RecipeDatasource","cursorToList setImage");
				recipe.setDetails(cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_RECIPE_DETAILS)));
				Log.v("RecipeDatasource","cursorToList setDetails");
				recipe.setCategoryId(cursor.getLong(cursor.getColumnIndex(DatabaseHelper.TABLE_RECIPE_CATEGORY_ID)));
				Log.v("RecipeDatasource","cursorToList setCategory");
				
				recipes.add(recipe);
			}
		}
		return recipes;

	}

}
