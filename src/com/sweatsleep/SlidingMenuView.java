package com.sweatsleep;

import android.R.menu;
import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

public class SlidingMenuView extends HorizontalScrollView
{
	public static String className = "SlidingMenuView";
	private int screenWidth;
	private int menuWidth;
	private int menuRightPadding = 50;
	private int halfMenuWidth = menuWidth / 2;
	private boolean once =false;
	
	private LinearLayout linearLayout;
	private ViewGroup menuView;
	private ViewGroup contentView;
	public static boolean menuOpen = false;
	
	public SlidingMenuView(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		
		// To get the screen width by using DisplayMetrics object.
		WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics metric = new DisplayMetrics();
		windowManager.getDefaultDisplay().getMetrics(metric);
		screenWidth = metric.widthPixels;
		Log.d(className, "The screen width is " + screenWidth);
		
		// To convert the dip to pixel.
		menuRightPadding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50, context.getResources().getDisplayMetrics());
	}
	
	// To configure the parameters of sub-view by overwriting the onMeasure method. 
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
	{
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		
		if(!once)
		{
			linearLayout = (LinearLayout) this.getChildAt(0);
			menuView = (ViewGroup) linearLayout.getChildAt(0);
			contentView = (ViewGroup) linearLayout.getChildAt(1);
			
			menuWidth = menuView.getLayoutParams().width = screenWidth - menuRightPadding;
			contentView.getLayoutParams().width = screenWidth;
			once = true;
		}
	}
	
	// To lay out the sub-view.
	@Override
	protected void onLayout(boolean changed, int l, int t,int r, int b)
	{
		super.onLayout(changed, l, t, r, b);
		
		if(changed)
		{
			this.scrollTo(menuWidth, 0);
		}
	}
	
	// To handle the touch event.
	@Override
	public boolean onTouchEvent(MotionEvent event)
	{
		int action = event.getAction();
		switch(action)
		{
		case MotionEvent.ACTION_UP:
		//{
			int scrollX = this.getScrollX();
			
			if(scrollX >= halfMenuWidth)
			{
				this.smoothScrollTo(menuWidth, 0);
				menuOpen = true;
				break;
			}
			else
			{
				this.smoothScrollTo(0, 0);
				menuOpen = false;
			}
			
			return true;
		//}
		}
		
		return super.onTouchEvent(event);
	}
	
	public void openMenu()
	{
		if(menuOpen)
		{
			return ;
		}
		else
		{
			this.smoothScrollTo(menuWidth, 0);
			menuOpen = true;
		}
	}
	
	public void closeMenu()
	{
		if(!menuOpen)
		{
			return;
		}
		else
		{
			this.smoothScrollTo(0,0);
			menuOpen = false;
		}
	}
}
