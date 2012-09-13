package com.thunder.testrectwithlibgdx.actor;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.thunder.testrectwithlibgdx.GameConstant;
import com.thunder.testrectwithlibgdx.GameConstant.GameState;

/**
 * 右上角方块
 * 
 * @author Thunder
 * @version 2012-9-5
 */
public class RightTopRectActor extends BaseActor {

	/**
	 * 构造函数
	 * 
	 * @param assetManager
	 */
	public RightTopRectActor(AssetManager assetManager) {
		super();
		mAssetManager = assetManager;
	
		/* 初始化  */
		init();
	}
	
	/**
	 * 初始化
	 */
	private void init() {
		originX = x = 340;
		originY = y = 794;
		width = 120;
		height = 40;
		
		// 指定方向
		isMoveDown = true;
	}

	@Override
	public void initResource() {
		// 加载图片
		Texture img = mAssetManager.get(GameConstant.IMG_RECTS, Texture.class);
		mTextureRegionNormal = new TextureRegion(img, 308, 15, 125, 40);
		mTextureRegionHit = new TextureRegion(img, 308, 73, 125, 40);
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
