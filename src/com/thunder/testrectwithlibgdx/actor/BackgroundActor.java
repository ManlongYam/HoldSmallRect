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
public class BackgroundActor extends BaseActor {

	/* AssetManager 资源管理器 */
	private AssetManager mAssetManager = null;
	
	/* 背景贴图  */
	private Texture mTextureBackground = null;
	
	/**
	 * 构造函数
	 * @param assetManager
	 */
	public BackgroundActor(AssetManager assetManager) {
		super();
		this.mAssetManager = assetManager;
	}
	
	@Override
	public void initResource() {
		// 加载图片
		mTextureBackground = mAssetManager.get(GameConstant.IMG_BACK_GROUND, 
				Texture.class);
	}
	
	@Override
	public void dispose() {
		// 释放资源
		mTextureBackground.dispose();
	}

	@Override
	public void draw(SpriteBatch spriteBatch, float arg1) {
		spriteBatch.draw(mTextureBackground, 0, 0, SCREEN_WIDTH, SCREEN_HEIGHT); 
	}

	@Override
	public void reset() {
	}

}
