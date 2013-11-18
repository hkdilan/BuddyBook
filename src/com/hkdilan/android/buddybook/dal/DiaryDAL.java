package com.hkdilan.android.buddybook.dal;

import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;

import com.hkdilan.android.buddybook.dao.DParagraphDAO;
import com.hkdilan.android.buddybook.provider.BuddyBookContract.DParagraph;

public class DiaryDAL {

	public static Uri saveDParagraph(Context context, DParagraphDAO dParagraph){
		final ContentValues values = new ContentValues();
		values.put(DParagraph._ID, dParagraph.getId());
		values.put(DParagraph.CONTENT, dParagraph.getContent());
		//values.put(DParagraph.DATE, dParagraph.getDate().getTime());
		//values.put(DParagraph.DATE_TIME_LAST_UPDATE, dParagraph.getDateTimeLastUpdate().getTime());
		//values.put(DParagraph.DATE_TIME_SUBMIT, dParagraph.getdateTimeSubmit());
		values.put(DParagraph.IMAGE, dParagraph.getImage());
		values.put(DParagraph.ORDER, dParagraph.getOrder());
		values.put(DParagraph.PRIVACY, dParagraph.getPrivacy());
		values.put(DParagraph.STATUS, dParagraph.getStatus());
		final Uri reminderUri = context.getContentResolver().insert(DParagraph.CONTENT_URI, values);
		values.clear();
		return reminderUri;
	}
}
