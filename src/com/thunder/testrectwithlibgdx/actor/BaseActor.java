package com.thunder.testrectwithlibgdx.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Disposable;
import com.thunder.testrectwithlibgdx.view.SmallRectView;

/**
 * 演员的基类
 * @author Thunder
 * @version 
 * 2012-9-11
 */
public abstract class BaseActor extends Actor implements Disposable {
	
	/**
	 * 屏幕的宽、高
	 */
	public static final float SCREEN_WIDTH = Gdx.graphics.getWidth();
	public static final float SCREEN_HEIGHT = Gdx.graphics.getHeight();

	/**
	 * 移动方向
	 */
	public boolean isMoveRight = false;
	public boolean isMoveDown = false;

	/* AssetManager 资源管理器 */
	public AssetManager mAssetManager = null;
	
	/* 背景贴图 */
	public TextureRegion mTextureRegion = null;
	public TextureRegion mTextureRegionNormal = null;
	public TextureRegion mTextureRegionHit = null;
	
	/* 枚举角色状态   */
	public enum ActorState {
		NORMAL/* 正常状态  */, HIT/* 击中状态  */
	};
	
	/**
	 * 初始化资源(抽象方法)
	 */
	public abstract void initResource();
	
	/**
	 * 重置(抽象方法)
	 */
	public abstract void reset();
	
	/**
	 * 设置游戏的状态
	 */
	public void setState(ActorState state) {
		if (state == ActorState.NORMAL) {
			mTextureRegion = mTextureRegionNormal;
		} else if (state == ActorState.HIT) {
			mTextureRegion = mTextureRegionHit;
		}
	}
	
	/**
	 * 演员移动
	 */
	public void move() {
		// 移动的速度 
		float moveSpeed = SmallRectView.mSpeed;
		
		// X边界的判断
		if (x + width + moveSpeed >= SCREEN_WIDTH) {
			isMoveRight = false;
		} else if (x - moveSpeed <= 0) {
			isMoveRight = true;
		}

		// Y边界的判断
		if (y + height + moveSpeed >= SCREEN_HEIGHT) {
			isMoveDown = false;
		} else if (y - moveSpeed <= LineActor.LINE_HEIGHT + LineActor.LINE_TOP) {
			isMoveDown = true;
		}

		// 改变方块的位置
		if (isMoveRight && isMoveDown) {
			x += moveSpeed;
			y += moveSpeed;
		} else if (!isMoveRight && isMoveDown) {
			x -= moveSpeed;
			y += moveSpeed;
		} else if (isMoveRight && !isMoveDown) {
			x += moveSpeed;
			y -= moveSpeed;
		} else if (!isMoveRight && !isMoveDown) {
			x -= moveSpeed;
			y -= moveSpeed;
		}
	}
	
	/**
	 * 判断是否发生碰撞
	 * @return
	 */
	public boolean isCrash() {
		float left = x;
		float right = x + width;
		float top = y;
		float bottom = y + height;
		
		// 得到红色方块
		BaseActor center = SmallRectView.mCenterRectActor;
		float centerLeft = center.x;
		float centerRight = center.x + center.width;
		float centerTop = center.y;
		float centerBottom = center.y + center.height;
		
		// 根据边界来判断是否进入了中间红色正方形的范围内
		if ((left > centerLeft && left < centerRight)
				&& (top > centerTop && top < centerBottom)) {
			return true;
		} else if ((right > centerLeft && right < centerRight)
				&& (top > centerTop && top < centerBottom)) {
			return true;
		} else if ((left > centerLeft && left < centerRight)
				&& (bottom > centerTop && bottom < centerBottom)) {
			return true;
		} else if ((right > centerLeft && right < centerRight)
				&& (bottom > centerTop && bottom < centerBottom)) {
			return true;
		}

		return false;
	}
	
	@Override
	public void dispose() {
	}

	@Override
	public void draw(SpriteBatch arg0, float arg1) {
	}

	@Override
	public Actor hit(float arg0, float arg1) {
		return null;
	}

}
