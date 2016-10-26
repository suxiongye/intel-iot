package cn.edu.bjut.util;

/**
 * Created by ray on 2016/9/14.
 */
public class TimeUtil {

    public static int getMinte(long s)
    {
        int mint = (int) (s/(1000*60));
        return mint;
    }
}
