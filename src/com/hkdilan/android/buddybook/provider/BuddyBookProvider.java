package com.hkdilan.android.buddybook.provider;

import java.util.Arrays;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

import com.hkdilan.android.buddybook.provider.BuddyBookContract.DParagraph;
import com.hkdilan.android.buddybook.provider.BuddyBookDatabase.Tables;

public class BuddyBookProvider extends ContentProvider {
	private static final String TAG = BuddyBookProvider.class.getSimpleName();

	private static final UriMatcher sUriMatcher = buildUriMatcher();
	private BuddyBookDatabase mOpenHelper;
	
	//uris
	private static final int DPARAGRAPH = 100;
	private static final int DPARAGRAPH_ID = 101;
	
	
	private static UriMatcher buildUriMatcher() {
		final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
	    final String authority = BuddyBookContract.CONTENT_AUTHORITY;
	    
	    matcher.addURI(authority, BuddyBookContract.PATH_PARAGRAPH, DPARAGRAPH);
	    matcher.addURI(authority, BuddyBookContract.PATH_PARAGRAPH + "/*", DPARAGRAPH_ID);
		return matcher;
	}
	
	@Override
	public boolean onCreate() {
		mOpenHelper = new BuddyBookDatabase(getContext());
		return true;
	}
	
	@Override
	public String getType(Uri uri) {
		final int match = sUriMatcher.match(uri);
        switch (match) {
            case DPARAGRAPH:
                return DParagraph.CONTENT_TYPE;
            case DPARAGRAPH_ID:
                return DParagraph.CONTENT_ITEM_TYPE;	
                
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		Log.d(TAG, "insert(uri=" + uri + ", values=" + values.toString() + ")");
		
		
		final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        final int match = sUriMatcher.match(uri);
        
		switch (match) {
			case DPARAGRAPH: 
				db.insertOrThrow(Tables.DPARAGRAPH, null, values);
				getContext().getContentResolver().notifyChange(uri, null);
				return DParagraph.buildUriById(values.getAsString(DParagraph._ID));
			
			default: 
				throw new UnsupportedOperationException("Unknown uri: " + uri);
		}
	}
	
	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		Log.d(TAG, "update(uri=" + uri + ", values=" + values.toString() + ")");
		
		final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
	    final SelectionBuilder builder = buildSimpleSelection(uri);
	    int retVal = builder.where(selection, selectionArgs).update(db, values);
	    
	    getContext().getContentResolver().notifyChange(uri, null);
	    
        return retVal;
	}
	
	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		Log.d(TAG, "delete(uri=" + uri + ")");		
		final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		
        final SelectionBuilder builder = buildSimpleSelection(uri);
        int retVal = builder.where(selection, selectionArgs).delete(db);
        getContext().getContentResolver().notifyChange(uri, null);
        
        return retVal;
	}
	
	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		Log.d(TAG, "query(uri=" + uri + ", proj=" + Arrays.toString(projection) + ")");
		
		final SQLiteDatabase db = mOpenHelper.getReadableDatabase();
	   
	    
	    final SelectionBuilder builder = buildExpandedSelection(uri);
	    return builder.where(selection, selectionArgs).query(db, projection, sortOrder);
	}
	
	private SelectionBuilder buildSimpleSelection(Uri uri) {
        final SelectionBuilder builder = new SelectionBuilder();
        final int match = sUriMatcher.match(uri);
        switch (match) {
	        case DPARAGRAPH:
	        	return builder.table(Tables.DPARAGRAPH);
	        
	        case DPARAGRAPH_ID:
	        	final String eventId = DParagraph.getId(uri);
	            return builder.table(Tables.DPARAGRAPH).where(DParagraph._ID + " =? ", eventId);
	            
	        default: 
	            throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
	}
	
	private SelectionBuilder buildExpandedSelection(Uri uri) {
        final SelectionBuilder builder = new SelectionBuilder();
        final int match = sUriMatcher.match(uri);
        switch (match) {
	        case DPARAGRAPH: 
	        	 return builder.table(Tables.DPARAGRAPH);
	        
	        case DPARAGRAPH_ID:
	        	final String eventId = DParagraph.getId(uri);
	            return builder.table(Tables.DPARAGRAPH).where(DParagraph._ID + " =? ", eventId);
	            
	        default: 
	        	throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
	}
}
