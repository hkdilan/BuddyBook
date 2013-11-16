package com.hkdilan.android.buddybook;

import android.os.Bundle;
import android.view.View;

import com.slidingmenu.lib.SlidingMenu;
import com.slidingmenu.lib.app.SlidingFragmentActivity;

public class HomeActivity extends SlidingFragmentActivity {

	private SlidingMenu sm;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		
		sm = getSlidingMenu();
		
		//check left menu set
		if(findViewById(R.id.home_left_menu_frame) == null){
			setBehindContentView(R.layout.activity_home_left_menu_container);
			sm.setEnabled(true);
			sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		}else{
			setBehindContentView(new View(this));
			sm.setEnabled(false);
			sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
		}
		
		//configure sliding menu
		sm.setBehindWidth(300);//set width
	}

}
