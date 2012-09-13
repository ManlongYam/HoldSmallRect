package com.thunder.testrectwithlibgdx.view;

import java.util.ArrayList;
import java.util.List;

import javax.microedition.khronos.opengles.GL10;

import android.app.Activity;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.thunder.testrectwithlibgdx.GameConstant;
import com.thunder.testrectwithlibgdx.GameConstant.GameState;
import com.thunder.testrectwithlibgdx.actor.BackgroundActor;
import com.thunder.testrectwithlibgdx.actor.BaseActor;
import com.thunder.testrectwithlibgdx.actor.CenterRectActor;
import com.thunder.testrectwithlibgdx.actor.LeftButtomRectActor;
import com.thunder.testrectwithlibgdx.actor.LeftTopRectActor;
import com.thunder.testrectwithlibgdx.actor.LineActor;
import com.thunder.testrectwithlibgdx.actor.RightButtomRectActor;
import com.thunder.testrectwithlibgdx.actor.RightTopRectActor;
import com.thunder.testrectwithlibgdx.actor.TextActor;
import com.thunder.testrectwithlibgdx.widget.ContinueGameDialog;
import com.thunder.testrectwithlibgdx.widget.ExitGameDialog;
import com.thunder.testrectwithlibgdx.widget.ProgressBar;
import com.thunder.testrectwithlibgdx.widget.ReplayGameDialog;

/**
 * 游戏主视图
 * @author Thunder
 * @version 
 * 2012-9-5
 */
public class SmallRectView implements ApplicationListener, InputProcessor {
	
	/* Activity 对象  */
	public Activity mActivity = null;
	
	/* 舞台 */
	public Stage mStage = null;
	
	/* 自定义的进度条   */
	private ProgressBar mProgressBar = null;
	
	/* 自定义对话框  */
	private ExitGameDialog mExitGameDialog = null;            // 退出游戏提示对话框
	private ReplayGameDialog mReplayGameDialog = null;        // 重新开始游戏提示对话框
	private ContinueGameDialog mContinueGameDialog = null;    // 暂停游戏提示对话框
	
	/* 背景演员   */
	private List<BaseActor> mActorList = null;                 // 演员集合
	private BackgroundActor mBackgroundActor = null;           // 背景演员
	private LineActor mLineActor = null;                       // 底线演员
	private LeftTopRectActor mLeftTopRectActor = null;         // 左上角演员
	private RightTopRectActor mRightTopRectActor = null;       // 右上角演员
	private LeftButtomRectActor mLeftButtomRectActor = null;   // 左下角演员
	private RightButtomRectActor mRightButtomRectActor = null; // 右下角演员
	private TextActor mTextActor = null;                       // 字体演员
	public static CenterRectActor mCenterRectActor = null;     // 中间演员
	
	/* 资源加载器  */
	private AssetManager mAssetManager = null;
	
	/* 加载完毕标志位  */
	private boolean isFinishLoad = false;
	private boolean isShowExitGameDialog = false;
	public GameState mOldState = GameState.WAIT;
	
	/* 游戏计时器  */
	public Timer mTimer = new Timer();
	
	/* 游戏的速度  */
	public static float mSpeed = 2.5f;
	
	/* 游戏的时间  */
	public static int mTime = 0;
			
	/**
	 * 构造函数
	 * @param activity
	 */
	public SmallRectView(Activity activity) {
		this.mActivity = activity;
	}

	@Override
	public void create() {
		/* 初始化   */
		init();
	}
	
	/**
	 * 初始化
	 */
	private void init() {
		// 初始化舞台
		mStage = new Stage(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), false);
		
		// 退出对话框
		mExitGameDialog = new ExitGameDialog(this);
		mReplayGameDialog = new ReplayGameDialog(this);
		mContinueGameDialog = new ContinueGameDialog(this);
		
		// 实例化进度条
		mProgressBar = new ProgressBar(0, 0);
				
		// 添加舞台的触摸事件
		InputMultiplexer multiplexer = new InputMultiplexer();
		multiplexer.addProcessor(mStage);
		multiplexer.addProcessor(this);
		Gdx.input.setInputProcessor(multiplexer);
		Gdx.input.setCatchBackKey(true);
		
		// 初始化资源管理器
		mAssetManager = new AssetManager();
		
		// 预加载资源
		mAssetManager.load(GameConstant.IMG_BACK_GROUND, Texture.class);
		mAssetManager.load(GameConstant.IMG_RECTS, Texture.class);
		mAssetManager.load(GameConstant.IMG_LINE, Texture.class);
		
		// 初始化资源
		mActorList = new ArrayList<BaseActor>();
		mBackgroundActor = new BackgroundActor(mAssetManager);
		mLineActor = new LineActor(mAssetManager);
		mLeftTopRectActor = new LeftTopRectActor(mAssetManager);
		mRightTopRectActor = new RightTopRectActor(mAssetManager);
		mCenterRectActor = new CenterRectActor(this, mAssetManager);
		mLeftButtomRectActor = new LeftButtomRectActor(mAssetManager);
		mRightButtomRectActor = new RightButtomRectActor(mAssetManager);
		mTextActor = new TextActor();
		
		// 添加到演员列表
		mActorList.add(mBackgroundActor);
		mActorList.add(mLineActor);
		mActorList.add(mLeftTopRectActor);
		mActorList.add(mRightTopRectActor);
		mActorList.add(mCenterRectActor);
		mActorList.add(mLeftButtomRectActor);
		mActorList.add(mRightButtomRectActor);
		mActorList.add(mTextActor);
		
		// 向舞台添加演员
		mStage.addActor(mProgressBar);
	}
	
	@Override
	public void dispose() {
		mStage.dispose();
	}

	@Override
	public void pause() {
		if (GameConstant.mGameState == GameState.START) { // 暂停状态
			GameConstant.mGameState = GameState.PAUSE;
		}
	}

	@Override
	public void render() {
		// 刷屏 
		mStage.act(Gdx.graphics.getDeltaTime());
		mStage.draw();
		
		// 状态判断
		if (GameConstant.mGameState == GameState.OVER) {
			mStage.addActor(mReplayGameDialog.dialog);
			// 停止线程
			mTimer.flag = false;
		} else if (GameConstant.mGameState == GameState.PAUSE) {
			mStage.addActor(mContinueGameDialog.dialog);
		}
		
		if (!isFinishLoad && mAssetManager.update()) { // 等待其加载完毕
			mStage.removeActor(mProgressBar);
			for (BaseActor tempActor : mActorList) {
				tempActor.initResource();
				mStage.addActor(tempActor);
			}
			isFinishLoad = true;
		} else if (!isFinishLoad && !mAssetManager.update()) {
			// 刷屏 
			Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
			Gdx.gl.glClearColor(1f, 1f, 1f, 0f);
			mProgressBar.setProgress(mAssetManager.getProgress() * 100);
		}
	}
	
	/**
	 * 重置游戏
	 */
	public void resetGame() {
		for (BaseActor tempActor : mActorList) {
			tempActor.reset();
		}
		GameConstant.mGameState = GameState.WAIT;
		mSpeed = 3f;
		mTime = 0;
	}

	@Override
	public void resize(int arg0, int arg1) {
	}

	@Override
	public void resume() {
	}

	@Override
	public boolean keyDown(int keyCode) {
		if (keyCode == Input.Keys.BACK) {
			if (!isShowExitGameDialog) {
				mOldState = GameConstant.mGameState; // 保存当前游戏状态
				GameConstant.mGameState = GameState.BREAK;
				mStage.addActor(mExitGameDialog.dialog);
				isShowExitGameDialog = true;
			} else {
				GameConstant.mGameState = mOldState; // 恢复游戏状态
				mStage.removeActor(mExitGameDialog.dialog);
				isShowExitGameDialog = false;
			}
		}
		return false;
	}

	@Override
	public boolean keyTyped(char arg0) {
		return false;
	}

	@Override
	public boolean keyUp(int arg0) {
		return false;
	}

	@Override
	public boolean scrolled(int arg0) {
		return false;
	}

	@Override
	public boolean touchDown(int arg0, int arg1, int arg2, int arg3) {
		return false;
	}

	@Override
	public boolean touchDragged(int arg0, int arg1, int arg2) {
		return false;
	}

	@Override
	public boolean touchMoved(int arg0, int arg1) {
		return false;
	}

	@Override
	public boolean touchUp(int arg0, int arg1, int arg2, int arg3) {
		return false;
	}

	/**
	 * 计时器
	 * @author Thunder
	 * @version 
	 * 2012-9-12
	 */
	public final class Timer implements Runnable {
		
		/* 线程标志位  */
		public boolean flag = true;
		/* 睡眠时间  */
		private static final long sleepTime = 1000;
		
		@Override
		public void run() {
			while (flag) { // 循环
				if (GameConstant.mGameState == GameState.START) {
					mTime++;
					if (mTime % 2 == 0) {
						mSpeed += 1;
					}
					try {
						Thread.sleep(sleepTime);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
	};
	
}
