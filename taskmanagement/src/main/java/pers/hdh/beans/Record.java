package pers.hdh.beans;

/**
 * record的实体类
 */
public class Record {
    private String rid;     // record的主键id
    private Integer state;// 任务状态
    private Long create_at; // 记录创建时间
    private Long update_at; // 记录上一次修改时间
    private User user;      // 外键
    private Task task;      // 外键

    public Record() {
        state = 0;
        user = new User();
        task = new Task();
    }

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }
}
