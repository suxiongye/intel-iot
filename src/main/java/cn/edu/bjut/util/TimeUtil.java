package cn.edu.bjut.util;

/**
 * Created by ray on 2016/9/14.
 */
public class TimeUtil {

    public static int getMinute(long s)
    {
        int mint = (int) (s/(1000*60));
        return mint;
    }
    
    /**
	 * 判断是否在固定分钟内
	 * 
	 * @param lastTime
	 * @return
	 */
	public static boolean nearTime(long lastTime, int min) {
		long now = System.currentTimeMillis();
		long near = Math.abs(now - lastTime);
		if (TimeUtil.getMinute(near) < min)
			return true;
		return false;
	}
}
