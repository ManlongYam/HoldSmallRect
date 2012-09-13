package com.thunder.testrectwithlibgdx;

/**
 * 游戏的常量类
 * @author Thunder
 * @version 
 * 2012-9-11
 */
public class GameConstant {
	
	/* 字体   */
	public static final String FONT_HYXY = "res/font/HYXY.ttf";

	/* 背景图   */
	public static final String IMG_BACK_GROUND = "res/img/game_bg.jpg";
	
	/* 方块集  */
	public static final String IMG_RECTS = "res/img/rects.png";
	
	/* 线  */
	public static final String IMG_LINE = "res/img/line.png";
	
	/* 枚举游戏状态   */
	public static enum GameState {
		WAIT/* 等待状态  */, START/* 开始状态   */, 
		OVER/* 结束状态  */, PAUSE/* 暂停状态  */,
		BREAK/* 返回键状态  */
	};
	
	/* 游戏标志位  */
	public static GameState mGameState = GameState.WAIT;
	
}
