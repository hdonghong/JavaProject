package pers.hdh.beans;

/**
 * 任务的实体类
 */
public class Task {
    private String tid;     // 任务id
    private String category;// 任务类型
    private String desc;    // 任务要求
    private Long create_at; // 任务发布时间
    private Long update_at; // 任务修改时间
    private Integer state;  // 额外字段，代表个人对此任务的状态。0:未领取 1进行中 2审核中 3已完成 4已失败

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Long getCreate_at() {
        return create_at;
    }

    public void setCreate_at(Long create_at) {
        this.create_at = create_at;
    }

    public Long getUpdate_at() {
        return update_at;
    }

    public void setUpdate_at(Long update_at) {
        this.update_at = update_at;
    }
    public Integer getState() {
        return state;
    }
    public void setState(Integer state) {
        this.state = state;
    }
}
