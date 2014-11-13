package org.xiangbalao.softkeyboard;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

public class KeyboardListenRelativeLayout extends RelativeLayout {
	
	private static final String TAG = KeyboardListenRelativeLayout.class.getSimpleName();
	
	public static final byte KEYBOARD_STATE_SHOW = -3;
	public static final byte KEYBOARD_STATE_HIDE = -2;
	public static final byte KEYBOARD_STATE_INIT = -1;
	
	private boolean mHasInit = false;
	private boolean mHasKeyboard = false;
	private int mHeight;
	
	private IOnKeyboardStateChangedListener onKeyboardStateChangedListener;
	
	public KeyboardListenRelativeLayout(Context context) {
		super(context);
	}
	public KeyboardListenRelativeLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	public KeyboardListenRelativeLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}
	
	public void setOnKeyboardStateChangedListener(IOnKeyboardStateChangedListener onKeyboardStateChangedListener) {
		this.onKeyboardStateChangedListener = onKeyboardStateChangedListener;
	}
	
	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		super.onLayout(changed, l, t, r, b);
		System.out.println("***b="+b);
		if(!mHasInit) {
			mHasInit = true;
			mHeight = b;   
			System.out.println("mHeight= "+b);
			if(onKeyboardStateChangedListener != null) {
				onKeyboardStateChangedListener.onKeyboardStateChanged(KEYBOARD_STATE_INIT);
			}
		} else {
			mHeight = mHeight < b ? b : mHeight;
		}
		
		if(mHasInit && mHeight > b) {                  //mHeight代表键盘的真实高度 ,b代表在窗口中的高度 mHeight>b 说明键盘隐藏
			mHasKeyboard = true;
			if(onKeyboardStateChangedListener != null) {
				onKeyboardStateChangedListener.onKeyboardStateChanged(KEYBOARD_STATE_SHOW);
			}
		}
		if(mHasInit && mHasKeyboard && mHeight == b) {  // mHeight = b 说明已经弹出
			mHasKeyboard = false;
			if(onKeyboardStateChangedListener != null) {
				onKeyboardStateChangedListener.onKeyboardStateChanged(KEYBOARD_STATE_HIDE);
			}
		}
	}
	
	public interface IOnKeyboardStateChangedListener {
		public void onKeyboardStateChanged(int state);
	}
}
