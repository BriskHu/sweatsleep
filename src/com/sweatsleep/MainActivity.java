package com.sweatsleep;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity {

	private static String className = "MainActivity";
	
	// Main view components.
	private Button buttonOpenMenuUser;
	private boolean menuIsOpen = false;
	private SlidingMenuView slidingMenu;
	
	// User menu components.
	private ImageView userImage;
	private ImageView newMessage;
	private Button buttonMessage;
	private Button buttonMyRecord;
	private Button buttonSetting;
	private Button buttonAbout;
	private Button buttonFeedback;
	private Button buttonExitAccount;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		initView();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	protected void initView()
	{
		getComponent();
		
		if(SlidingMenuView.menuOpen)
		{
			buttonOpenMenuUser.setBackgroundResource(R.drawable.back);
			menuIsOpen = true;
		}
		else
		{
			buttonOpenMenuUser.setBackgroundResource(R.drawable.forward);
			menuIsOpen = false;
		}
		
		Log.d(className, "View has initiated.");
	}
	
	protected void getComponent()
	{
		buttonOpenMenuUser = (Button) findViewById(R.id.buttonOpenMenuUser);
		
		slidingMenu = (SlidingMenuView) findViewById(R.id.slidingMenu);
		
		userImage = (ImageView) findViewById(R.id.userImage);
		newMessage = (ImageView) findViewById(R.id.newMessage);
		buttonMessage = (Button) findViewById(R.id.buttonMessage);
		buttonMyRecord = (Button) findViewById(R.id.buttonMyRecord);
		buttonSetting = (Button) findViewById(R.id.buttonSetting);
		buttonAbout = (Button) findViewById(R.id.buttonAbout);
		buttonFeedback = (Button) findViewById(R.id.buttonFeedback);
		buttonExitAccount = (Button) findViewById(R.id.buttonExitAccount);
		
		Log.d(className, "All components have gotten.");
	}
	
	public void shiftMenuUser(View view)
	{
		if(!menuIsOpen)
		{
			Log.d(className, "Open user menu.");
			
			slidingMenu.openMenu();
			menuIsOpen = true;
			buttonOpenMenuUser.setBackgroundResource(R.drawable.back);
		}
		else
		{
			Log.d(className, "Close user menu.");
			
			slidingMenu.closeMenu();
			menuIsOpen = false;
			buttonOpenMenuUser.setBackgroundResource(R.drawable.forward);
		}
	}
	
	public void readMessage(View view)
	{
		
	}
	
}
