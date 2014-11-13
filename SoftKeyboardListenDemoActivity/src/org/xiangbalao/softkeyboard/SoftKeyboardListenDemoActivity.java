package org.xiangbalao.softkeyboard;

import org.xiangbalao.softkeyboard.KeyboardListenRelativeLayout.IOnKeyboardStateChangedListener;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

/**
 * 软键盘监听Demo
 * @author qiaoning
 *
 */
public class SoftKeyboardListenDemoActivity extends Activity {
	
	private EditText editText;
	KeyboardListenRelativeLayout relativeLayout;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        relativeLayout = (KeyboardListenRelativeLayout) findViewById(R.id.keyboardRelativeLayout);
        editText = (EditText) findViewById(R.id.editText);
        
        relativeLayout.setOnKeyboardStateChangedListener(new IOnKeyboardStateChangedListener() {
			
			public void onKeyboardStateChanged(int state) {
				switch (state) {
				case KeyboardListenRelativeLayout.KEYBOARD_STATE_HIDE://软键盘隐藏
					editText.setVisibility(View.VISIBLE);
					break;
				case KeyboardListenRelativeLayout.KEYBOARD_STATE_SHOW://软键盘显示
					editText.setVisibility(View.GONE);
					break;
				default:
					break;
				}
			}
		});
    }
}