package com.hkdilan.android.buddybook.provider;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

public class BuddyBookContract {

	public static final String CONTENT_AUTHORITY = "com.buddybook.buddybookprovider";
	public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
	
	public static final String PATH_PARAGRAPH = "paragraph";
	
	private interface DParagraphCol{
		String CONTENT = "Content";
		String DATE = "Date";
		String DATE_TIME_LAST_UPDATE = "DateTimeLastUpdate";
		String DATE_TIME_SUBMIT = "DateTimeSubmit";
		String IMAGE = "Image";
		String ORDER = "Order";
		String PRIVACY = "Privacy";
		String STATUS = "Status";
	}
	
	public static class DParagraph implements DParagraphCol, BaseColumns{
		
		public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_PARAGRAPH).build();
		
		public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE+ "/vnd.buddybook.dparagraph";
		public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE+ "/vnd.buddybook.dparagraph";
		
		public static Uri buildUriById(String id) {
			return CONTENT_URI.buildUpon().appendPath(id).build();
		}

		public static String getId(Uri uri) {
			return uri.getPathSegments().get(1);
		}
	}
}
