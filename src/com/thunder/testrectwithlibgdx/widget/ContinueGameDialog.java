package com.thunder.testrectwithlibgdx.widget;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ClickListener;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.thunder.testrectwithlibgdx.GameConstant;
import com.thunder.testrectwithlibgdx.GameConstant.GameState;
import com.thunder.testrectwithlibgdx.view.SmallRectView;

/**
 * 退出游戏对话框
 * @author Thunder
 * @version 
 * 2012-9-12
 */
public class ContinueGameDialog {
	
	/* SmallRectView 对象  */
	private SmallRectView mSmallRectView = null;
	
	/* 舞台 */
	private Stage mStage = null;
	
	/* 对话框  */
	public Window dialog = null;
	
	/* 对话框上的两个按钮  */
	private Button button_continue = null;
	private Button button_exit_game = null;
	
	/**
	 * 构造函数
	 * @param context
	 */
	public ContinueGameDialog(SmallRectView smallRectView) {
		super();
		mSmallRectView = smallRectView;
		mStage = mSmallRectView.mStage;
		/* 初始化  */
		init();
	}
	
	/**
	 * 初始化函数
	 */
	private void init() {
		TextureRegion textureRegion = new TextureRegion(new Texture(
				Gdx.files.internal("res/img/continue_game_dialog.png")), 58, 32, 392, 188);
		dialog = new Window("dialog", new Window.WindowStyle(new BitmapFont(), new Color(),
				new NinePatch(textureRegion)));
		// 做一个简单的适配,乘以1.2是为了让图片显示出来的时候大一
		dialog.width = 512 * 1.2f * Gdx.graphics.getWidth() / 800f;
		dialog.height = 256 * 1.2f * Gdx.graphics.getWidth() / 800f;
		
		// 为了让图片保持居中
		dialog.x = (Gdx.graphics.getWidth() - dialog.width) / 2;
		dialog.y = (Gdx.graphics.getHeight() - dialog.height) / 2;
		
		/* 添加Button */
		addButton();
	}
	
	/**
	 * 对话框上的Button
	 */
	private void addButton() {
		button_continue = new Button(new TextureRegion(new Texture(
				Gdx.files.internal("res/img/button_continue.png")), 46, 26, 160, 72));
		button_continue.x = 20;
		button_continue.y = 20;
		button_continue.width = 135;
		button_continue.height = 55;

		button_continue.setClickListener(new ClickListener() {
			@Override
			public void click(Actor arg0, float arg1, float arg2) {
				if (GameConstant.mGameState != GameState.WAIT) {
					mStage.removeActor(dialog);
					GameConstant.mGameState = GameState.START;
				}
			}
		});

		button_exit_game = new Button(new TextureRegion(new Texture(
				Gdx.files.internal("res/img/button_exit_game.png")), 46, 26, 160, 72));
		button_exit_game.x = 210;
		button_exit_game.y = 20;
		button_exit_game.width = 135;
		button_exit_game.height = 55;
		
		button_exit_game.setClickListener(new ClickListener() {
			@Override
			public void click(Actor arg0, float arg1, float arg2) {
				// 关闭程序
				mSmallRectView.mActivity.finish();
				android.os.Process.killProcess(android.os.Process.myPid());
			}
		});
		dialog.addActor(button_continue);
		dialog.addActor(button_exit_game);
	}
}
