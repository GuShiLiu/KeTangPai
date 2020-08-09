package mybatis.demo.entity;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.io.Serializable;
@EntityScan
public class User_Role implements Serializable {
    static final long serialVersionUID = 1L;
    //角色表本身的id
    private long id;
    //用户id
    private long uid;
    //角色id
    private long rid;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public long getRid() {
        return rid;
    }

    public void setRid(long rid) {
        this.rid = rid;
    }
}
