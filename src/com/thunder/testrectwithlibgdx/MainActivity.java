package com.thunder.testrectwithlibgdx;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.thunder.testrectwithlibgdx.view.SmallRectView;

/**
 * 主Activitys
 * @author Thunder
 * @version 
 * 2012-9-5
 */
public class MainActivity extends AndroidApplication {
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        /* 显示  */
        initialize(new SmallRectView(this), false);
    }
}