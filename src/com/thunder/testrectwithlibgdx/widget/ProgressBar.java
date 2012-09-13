package com.thunder.testrectwithlibgdx.widget;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Disposable;

/**
 * 进度条类
 * 
 * @author Thunder
 * @version 2012-9-5
 */
public class ProgressBar extends Actor implements Disposable {

	/**
	 * 纹理贴图
	 */
	private Texture mTexturePlatform = null; // 进度条背景
	private Texture mTextureBar = null; // 进度条

	/**
	 * 进度相关
	 */
	public float mProgress = 0f; // 当前进度
	
	public float m = 0.7f;
	
	/**
	 * 构造函数
	 * @param x
	 * @param y
	 */
	public ProgressBar(int x, int y) {
		super();
		this.x = x;
		this.y = y;
		
		/* 初始化  */
		init();
	}
	
	/**
	 * 初始化处理
	 */
	private void init() {
		/* 加载资源   */
		loadResource();

		width = Gdx.graphics.getWidth();
		height = Gdx.graphics.getHeight();
	}
	
	/**
	 * 加载资源
	 */
	private void loadResource() {
		mTexturePlatform = new Texture(Gdx.files.internal("res/black.png"));
		mTextureBar = new Texture(Gdx.files.internal("res/green.png"));
	}
	
	@Override
	public void draw(SpriteBatch batch, float arg1) {
		// 进度条背景
		batch.draw(mTexturePlatform,
				(width - mTextureBar.getWidth() * m) / 2, 
				0, 
				mTexturePlatform.getWidth() * m,
				mTexturePlatform.getHeight());
		// 进度条
		batch.draw(
				mTextureBar,
				(width - mTextureBar.getWidth() * m) / 2 + 2,
				0, 
				mTextureBar.getWidth() * mProgress / 100f * m,
				mTextureBar.getHeight());
	}

	@Override
	public Actor hit(float arg0, float arg1) {
		return null;
	}

	public void setProgress(float progress) {
		this.mProgress = progress;
	}

	@Override
	public void dispose() {
		// 释放资源
		mTexturePlatform.dispose();
		mTextureBar.dispose();
	}

}
