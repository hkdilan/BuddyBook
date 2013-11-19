package com.hkdilan.android.buddybook.dal;

import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.util.Log;

import com.hkdilan.android.buddybook.dao.DParagraphDAO;
import com.hkdilan.android.buddybook.provider.BuddyBookContract.DParagraph;
import com.hkdilan.android.buddybook.utils.DateTimeUtil;

public class DiaryDAL {
	private final static String TAG = DiaryDAL.class.getSimpleName();
	
	public static Uri saveDParagraph(Context context, DParagraphDAO dParagraph){
		Log.v(TAG, "saveDParagraph()");
		
		final ContentValues values = new ContentValues();
		values.put(DParagraph._ID, dParagraph.getId());
		values.put(DParagraph.CONTENT, dParagraph.getContent());
		values.put(DParagraph.DATE, DateTimeUtil.getDefaultDateString(dParagraph.getDate()));
		values.put(DParagraph.DATE_TIME_LAST_UPDATE, DateTimeUtil.getDefaultDateString(dParagraph.getDateTimeLastUpdate()));
		values.put(DParagraph.DATE_TIME_SUBMIT, DateTimeUtil.getDefaultDateString(dParagraph.getdateTimeSubmit()));
		values.put(DParagraph.IMAGE, dParagraph.getImage());
		values.put(DParagraph.STATUS, dParagraph.getStatus());
		final Uri uri = context.getContentResolver().insert(DParagraph.CONTENT_URI, values);
		values.clear();
		
		Log.d(TAG, uri.toString());
		return uri;
	}
	
	public static int updateDParagraph(Context context, DParagraphDAO dParagraph){
		Log.v(TAG, "updateDParagraph()");
		
		final ContentValues values = new ContentValues();
		values.put(DParagraph.CONTENT, dParagraph.getContent());
		values.put(DParagraph.DATE, DateTimeUtil.getDefaultDateString(dParagraph.getDate()));
		values.put(DParagraph.DATE_TIME_LAST_UPDATE, DateTimeUtil.getDefaultDateString(dParagraph.getDateTimeLastUpdate()));
		values.put(DParagraph.DATE_TIME_SUBMIT, DateTimeUtil.getDefaultDateString(dParagraph.getdateTimeSubmit()));
		values.put(DParagraph.IMAGE, dParagraph.getImage());
		values.put(DParagraph.STATUS, dParagraph.getStatus());
		final int result = context.getContentResolver().update(DParagraph.buildUriById(dParagraph.getId()), values, null, null);//.insert(DParagraph.CONTENT_URI, values);
		values.clear();
		
		Log.d(TAG, "result: "+result);
		return result;
	}
}
