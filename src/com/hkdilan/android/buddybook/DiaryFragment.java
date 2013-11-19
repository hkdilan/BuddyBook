package com.hkdilan.android.buddybook;

import java.util.Calendar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.hkdilan.android.buddybook.utils.DateTimeUtil;

public class DiaryFragment extends SherlockFragment {
	private final static String TAG = DiaryFragment.class.getSimpleName();
	
	private View mRoot;
	private ImageButton mIbPrevDate;
	private ImageButton mIbNextDate;
	private TextView mTvDate;
	
	private Calendar mDate;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		Log.v(TAG, "onCreate()");
		
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
		
		mDate = DateTimeUtil.getNow();
		
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Log.v(TAG, "onCreateView()");
		
		mRoot = inflater.inflate(R.layout.fragment_diary, null);
		
		getSherlockActivity().getSupportActionBar().setTitle(getResources().getString(R.string.title_diary));
		
		mIbPrevDate = (ImageButton) mRoot.findViewById(R.id.ibPrevDate);
		mIbNextDate = (ImageButton) mRoot.findViewById(R.id.ibNextDate);
		mTvDate = (TextView) mRoot.findViewById(R.id.tvDate);
		
		mIbPrevDate.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mDate.add(Calendar.DATE, -1);
				setDate();
			}
		});
		
		mIbNextDate.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mDate.add(Calendar.DATE, 1);
				setDate();
			}
		});
		
		setDate();
		
		return mRoot;
	}
	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		Log.v(TAG, "onCreateOptionsMenu()");
		
		inflater.inflate(R.menu.diary, menu);
		super.onCreateOptionsMenu(menu, inflater);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Log.v(TAG, "onOptionsItemSelected()");
		
		switch(item.getItemId()){
		case R.id.menu_new_para:
			final Intent i = new Intent(getSherlockActivity(), DParagraphAddActivity.class);
			i.putExtra(DParagraphAddActivity.EX_NEW, true);
			i.putExtra(DParagraphAddActivity.EX_DATE, DateTimeUtil.getDefaultDateString(mDate));
			startActivity(i);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	private void setDate(){
		mTvDate.setText(DateTimeUtil.getDateString(mDate, DateTimeUtil.DIARY_DATE_TIME_FORMAT));
	}
}
