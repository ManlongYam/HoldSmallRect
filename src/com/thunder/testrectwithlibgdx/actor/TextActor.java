package com.thunder.testrectwithlibgdx.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.thunder.testrectwithlibgdx.view.SmallRectView;

/**
 * 字符演员
 * @author Thunder
 * @version 
 * 2012-9-12
 */
public class TextActor extends BaseActor {
	
	/* BitmapFont 画刷  */
	private BitmapFont fontText = null;

	/**
	 * 构造函数
	 */
	public TextActor() {
		super();
	}
	
	@Override
	public void initResource() {
		fontText = new BitmapFont(Gdx.files.internal("res/font/font.fnt"), 
				Gdx.files.internal("res/font/font.png"), false);
	}

	@Override
	public void draw(SpriteBatch spriteBatch, float arg1) {
		/* 画上文字   */
		fontText.draw(spriteBatch, "您已经坚持了:" + SmallRectView.mTime + "秒", 30, 80);
		super.draw(spriteBatch, arg1);
	}

	@Override
	public void reset() {
	}

}
