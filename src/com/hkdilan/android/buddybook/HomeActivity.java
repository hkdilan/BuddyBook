package com.hkdilan.android.buddybook;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.View;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.view.MenuItem;
import com.slidingmenu.lib.SlidingMenu;
import com.slidingmenu.lib.app.SlidingFragmentActivity;

public class HomeActivity extends SlidingFragmentActivity {

	private SlidingMenu mSMenu;
	private ActionBar mABar;
	private FragmentManager mFragManager;
	
	//override mehtod are public as sliding menu
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		
		mSMenu = getSlidingMenu();
		mABar = getSupportActionBar();
		mFragManager = getSupportFragmentManager();
		
		//check left menu set
		if(findViewById(R.id.home_left_menu_frame) == null){
			setBehindContentView(R.layout.activity_home_left_menu_container);
			mSMenu.setEnabled(true);
			mSMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		}else{
			setBehindContentView(new View(this));
			mSMenu.setEnabled(false);
			mSMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
		}
		
		//configure sliding menu
		mSMenu.setBehindWidth(300);//set width
		mSMenu.setFadeDegree(0.35f);
		
		//add sliding menu fragment
		mFragManager.beginTransaction().replace(R.id.home_left_menu_frame, new LeftSlidingMenuFragment()).commit();
		
		//add fragment for selected item of sliding menu
		mFragManager.beginTransaction().replace(R.id.home_frame, new DiaryFragment()).commit();
		
		//show actionbar up button
		mABar.setDisplayHomeAsUpEnabled(true);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()){
		case android.R.id.home:
			toggle();//toggle sliding menu
			break;
		}
		return super.onOptionsItemSelected(item);
	}

}
