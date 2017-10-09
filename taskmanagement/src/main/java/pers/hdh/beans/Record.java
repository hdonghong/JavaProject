package pers.hdh.beans;

/**
 * record的实体类
 */
public class Record {
    private String rid;     // record的主键id
    private Integer state;  // 任务状态
    private Long create_at; // 记录创建时间
    private Long update_at; // 记录上一次修改时间
    private User user;      // 外键
    private Task task;      // 外键
    private Integer is_read;// 消息状态

    // 额外提供三个javabean属性，方便获取数据，没想到更好的办法，做个标记 TODO
    private String category;
    private String desc;
    private String stuid;

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

    public String getStuid() {
        return stuid;
    }

    public void setStuid(String stuid) {
        this.stuid = stuid;
    }

    public Record() {
        state = 0;
        is_read = 0;
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

    public Integer getIs_read() {
        return is_read;
    }

    public void setIs_read(Integer is_read) {
        this.is_read = is_read;
    }
}
