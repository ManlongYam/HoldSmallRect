package com.thunder.testrectwithlibgdx.actor;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.thunder.testrectwithlibgdx.GameConstant;
import com.thunder.testrectwithlibgdx.GameConstant.GameState;

/**
 * 背景类
 * 
 * @author Thunder
 * @version 2012-9-5
 */
public class LeftButtomRectActor extends BaseActor {

	/**
	 * 构造函数
	 * 
	 * @param assetManager
	 */
	public LeftButtomRectActor(AssetManager assetManager) {
		super();
		this.mAssetManager = assetManager;

		/* 初始化一些数据 */
		init();
	}
	
	/**
	 * 初始化函数
	 */
	private void init() {
		// 初始化演员的属性
		originX = x = 20;
		originY = y = 200;
		width = 40;
		height = 150;
		
		// 指定方向
		isMoveDown = true;
	}

	@Override
	public void initResource() {
		// 加载图片
		Texture img = mAssetManager.get(GameConstant.IMG_RECTS, Texture.class);
		mTextureRegionNormal = new TextureRegion(img, 36, 296, 45, 160);
		mTextureRegionHit = new TextureRegion(img, 96, 296, 45, 160);
		mTextureRegion = mTextureRegionNormal;
	}

	@Override
	public void draw(SpriteBatch spriteBatch, float arg1) {
		spriteBatch.draw(mTextureRegion, x, y, width, height);
		
		if (GameConstant.mGameState == GameState.START) {
			if (isCrash()) { // 判断是否发生碰撞
				GameConstant.mGameState = GameState.OVER;
				setState(ActorState.HIT);
			}
			/* 移动方块  */
			move();
		}
	}
	
	@Override
	public void reset() {
		x = originX;
		y = originY;
		mTextureRegion = mTextureRegionNormal;
	}
	
}
