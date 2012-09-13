package com.thunder.testrectwithlibgdx.actor;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.thunder.testrectwithlibgdx.GameConstant;

/**
 * 背景
 * @author Thunder
 * @version 
 * 2012-9-5
 */
public class LineActor extends BaseActor {

	/* AssetManager 资源管理器 */
	private AssetManager mAssetManager = null;
	
	/* 背景贴图  */
	private Texture mTextureBackground = null;
	
	/* 线的顶部坐标  */
	public static final float LINE_TOP = 120;
	
	/* 线高  */
	public static final float LINE_HEIGHT = 32;
	
	/**
	 * 构造函数
	 * @param assetManager
	 */
	public LineActor(AssetManager assetManager) {
		super();
		this.mAssetManager = assetManager;
	}
	
	@Override
	public void initResource() {
		// 加载图片
		mTextureBackground = mAssetManager.get(GameConstant.IMG_LINE, 
				Texture.class);
	}
	
	@Override
	public void dispose() {
		// 释放资源
		mTextureBackground.dispose();
	}

	@Override
	public void draw(SpriteBatch spriteBatch, float arg1) {
		spriteBatch.draw(mTextureBackground, 0, LINE_TOP, 
				SCREEN_WIDTH, LINE_HEIGHT); 
	}

	@Override
	public void reset() {
	}

}
