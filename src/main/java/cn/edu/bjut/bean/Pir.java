package cn.edu.bjut.bean;

import java.sql.Timestamp;

/**
 * Created by ray on 2016/9/14.
 */
public class Pir {

    private int id;

    private String value;

    private Timestamp createTime;

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

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }
}
