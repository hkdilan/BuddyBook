package com.hkdilan.android.buddybook;

import java.util.Calendar;

import com.hkdilan.android.buddybook.bll.Enums.Status;
import com.hkdilan.android.buddybook.dal.DiaryDAL;
import com.hkdilan.android.buddybook.dao.DParagraphDAO;
import com.hkdilan.android.buddybook.provider.BuddyBookContract.DParagraph;
import com.hkdilan.android.buddybook.utils.DateTimeUtil;
import com.hkdilan.android.buddybook.utils.Util;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;

public class DParagraphAddActivity extends Activity {
	private final static String TAG = DParagraphAddActivity.class.getSimpleName();

	public final static String EX_NEW = "new";
	public final static String EX_DATE = "date";
	public final static String EX_ID = "id";
	
	private EditText mEtDParagraph;
	
	private boolean mNew = false;
	private Calendar mDate;
	private String mId;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dparagraph_add);
		
		mNew = getIntent().getBooleanExtra(EX_NEW, false);
		final String dateString = getIntent().getStringExtra(EX_DATE);
		mDate = DateTimeUtil.getDefaultCalendarFromString(dateString);
		mId = getIntent().getStringExtra(EX_ID);
		
		mEtDParagraph = (EditText) findViewById(R.id.etDParagraph);
		
	}
	
	@Override
	protected void onStop() {
		super.onStop();
		
		if(TextUtils.isEmpty(mEtDParagraph.getText().toString())){
			Log.w(TAG, "no text.");
			return;
		}
		
		final DParagraphDAO para = new DParagraphDAO();
		para.setContent(mEtDParagraph.getText().toString());
		para.setDate(mDate);
		para.setDateTimeLastUpdate(DateTimeUtil.getNow());
		para.setImage(null);
		para.setStatus(Status.ENABLE);
		
		if(mNew){
			para.setDateTimeSubmit(DateTimeUtil.getNow());
			para.setId(Util.getNewGuid());
			DiaryDAL.saveDParagraph(this, para);
		}else{
			para.setId(mId);
			DiaryDAL.updateDParagraph(this, para);
		}
	}
}
