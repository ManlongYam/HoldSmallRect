package com.thunder.testrectwithlibgdx.actor;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.thunder.testrectwithlibgdx.GameConstant;
import com.thunder.testrectwithlibgdx.GameConstant.GameState;

/**
 * 右下角方块
 * 
 * @author Thunder
 * @version 2012-9-5
 */
public class RightButtomRectActor extends BaseActor {

	/**
	 * 构造函数
	 * 
	 * @param assetManager
	 */
	public RightButtomRectActor(AssetManager assetManager) {
		super();
		mAssetManager = assetManager;
		
		/* 初始化  */
		init();
	}
	
	/**
	 * 初始化
	 */
	private void init() {
		originX = x = 280;
		originY = y = 310;
		width = 180;
		height = 40;
		
		// 指定方向
		isMoveRight = false;
		isMoveDown = true;
	}

	@Override
	public void initResource() {
		// 加载图片
		Texture img = mAssetManager.get(GameConstant.IMG_RECTS, Texture.class);
		mTextureRegionNormal = new TextureRegion(img, 270, 296, 215, 42);
		mTextureRegionHit = new TextureRegion(img, 270, 356, 215, 42);
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
