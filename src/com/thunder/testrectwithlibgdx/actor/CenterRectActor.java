package com.thunder.testrectwithlibgdx.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.thunder.testrectwithlibgdx.GameConstant;
import com.thunder.testrectwithlibgdx.GameConstant.GameState;
import com.thunder.testrectwithlibgdx.view.SmallRectView;

/**
 * 中间红色方块
 * 
 * @author Thunder
 * @version 2012-9-5
 */
public class CenterRectActor extends BaseActor {
	
	/* SmallRectView 对象  */
	private SmallRectView mSmallRectView = null;
	
	/**
	 * 方块的中心
	 */
	private float halfWidth = 0f;
	private float halfHeight = 0f;

	/**
	 * 构造函数
	 * 
	 * @param assetManager
	 */
	public CenterRectActor(SmallRectView smallRectView, AssetManager assetManager) {
		super();
		mSmallRectView = smallRectView;
		mAssetManager = assetManager;
		
		/* 初始化   */
		init();
	}
	
	/**
	 * 初始化函数
	 */
	private void init() {
		width = height = 80;
		originX = x = (SCREEN_WIDTH - width) / 2;
		originY = y = (SCREEN_HEIGHT - height) / 2 + 80;
		
		halfWidth = width / 2;
		halfHeight = height / 2;
	}

	@Override
	public void initResource() {
		// 加载图片
		mTextureRegion = new TextureRegion(mAssetManager.get(
				GameConstant.IMG_RECTS, Texture.class), 186, 170, 85, 85);
	}

	@Override
	public void draw(SpriteBatch spriteBatch, float arg1) {
		spriteBatch.draw(mTextureRegion, x, y, width, height);
	}

	@Override
	public boolean touchDown(float x, float y, int pointer) {
		if (GameConstant.mGameState == GameState.WAIT) {
			GameConstant.mGameState = GameState.START;
			// 启动计时器线程
			mSmallRectView.mTimer.flag = true;
			new Thread(mSmallRectView.mTimer).start();
		} else if (GameConstant.mGameState == GameState.OVER) {
			return false;
		}
		return true;
	}

	@Override
	public void touchDragged(float x, float y, int pointer) {
		if (GameConstant.mGameState == GameState.OVER) {
			return;
		}
		
		float inputX = Gdx.input.getX();
		float inputY = Gdx.input.getY();
		
		if (inputX - halfWidth <= 0) {
			this.x = 0;
		} else if (inputX + halfWidth >= SCREEN_WIDTH) {
			this.x = SCREEN_WIDTH - width;
		} else {
			this.x = inputX - halfWidth;
		}
		
		if (inputY - halfHeight <= 0) {
			this.y = SCREEN_HEIGHT - height;
		} else if (inputY + halfHeight >= SCREEN_HEIGHT - (LineActor.LINE_HEIGHT + LineActor.LINE_TOP)) {
			this.y = LineActor.LINE_HEIGHT + LineActor.LINE_TOP;
		} else {
			this.y = SCREEN_HEIGHT - inputY - halfHeight;
		}
		
		super.touchDragged(x, y, pointer);
	}

	@Override
	public Actor hit(float arg0, float arg1) {
		if (arg0 > 0 && arg1 > 0 && arg0 < width && arg1 < height) {
			return this;
		}
		return null;
	}
	
	@Override
	public void reset() {
		x = originX;
		y = originY;
	}
	
}
