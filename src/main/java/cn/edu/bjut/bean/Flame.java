package cn.edu.bjut.bean;

import java.sql.Timestamp;

import cn.edu.bjut.util.Config;

/**
 * Created by ray on 2016/9/14.
 */
public class Flame {

    private int id;

    //火灾数值
    private String value;

    private Timestamp createTime;

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
    
    public Flame() {
		// TODO Auto-generated constructor stub
	}
    
    /**
     * 根据火灾数值生成Flame对象
     * @param value
     */
    public Flame(int fire_num) {
		// TODO Auto-generated constructor stub
    	this.createTime = new Timestamp(System.currentTimeMillis());

		if (fire_num < 100)
			this.value = Config.FLAME_NORMAL;
		else if (fire_num >= 100 && fire_num < 200)
			this.value = Config.FLAME_DANGER;
		else
			this.value = Config.FLAME_FIRE;
	}
}
