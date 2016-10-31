package cn.edu.bjut.util;

public class Config {
	//最后一次提醒时间
	public static long LAST_PIR = 0L;
	public static long LAST_PRESS = 0L;
	public static long LAST_FLAME = 0L;
	//短信间隔时间
	public static int SMS_INTERNEL = 1;
	
	//位置判断时间
	public static int MUST_TIME = 1;
	public static int MAYBE_TIME = 5;
	
	//火灾级别
    public static String FLAME_NORMAL= "Normal";

    public static String FLAME_DANGER = "Danger";

    public static String FLAME_FIRE = "Fire";
    
    //pir位置
    public static String PIR_BATHROOM = "Bathroom";
    
    //press位置
    public static String PRESS_HAND = "Hand";
    public static String PRESS_BEDROOM = "Bedroom";
}
