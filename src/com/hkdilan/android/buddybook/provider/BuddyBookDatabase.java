package com.hkdilan.android.buddybook.provider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.hkdilan.android.buddybook.provider.BuddyBookContract.DParagraph;

public class BuddyBookDatabase extends SQLiteOpenHelper {

	private static final String DATABASE_NAME ="BuddyBook.db";
	private static final int DATABASE_VERSION = 1;
	
	interface Tables{
		String DPARAGRAPH = "DParagraph";
	}
	
	public BuddyBookDatabase(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(DPARAGRAPH_CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + Tables.DPARAGRAPH);
		onCreate(db);
	}
	
	private static final String DPARAGRAPH_CREATE = "CREATE TABLE " + Tables.DPARAGRAPH + " (" +
			DParagraph._ID + " GUID NOT NULL, " +
			DParagraph.CONTENT + " VARCHAR NOT NULL, " +
			DParagraph.DATE + " DATETIME NOT NULL, " + 
			DParagraph.DATE_TIME_LAST_UPDATE + " DATETIME NOT NULL, " +
			DParagraph.DATE_TIME_SUBMIT + " DATETIME NOT NULL, " +
			DParagraph.IMAGE + " VARCHAR NULL, " +
			DParagraph.STATUS + " INT NOT NULL DEFAULT(0), " +
			"PRIMARY KEY(" + DParagraph._ID + "))";
}
