package com.domeastudio.application.dataAccess.POJO;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by domea on 16-1-15.
 */
@Entity
@Table(name = "TUser", schema = "UserPermission", catalog = "")
public class TUserEntity {
    private String fuId;
    private String fuLoginName;
    private String fuPwd;

    @Basic
    @Column(name = "FUId")
    public String getFuId() {
        return fuId;
    }

    public void setFuId(String fuId) {
        this.fuId = fuId;
    }

    @Basic
    @Column(name = "FULoginName")
    public String getFuLoginName() {
        return fuLoginName;
    }

    public void setFuLoginName(String fuLoginName) {
        this.fuLoginName = fuLoginName;
    }

    @Basic
    @Column(name = "FUPwd")
    public String getFuPwd() {
        return fuPwd;
    }

    public void setFuPwd(String fuPwd) {
        this.fuPwd = fuPwd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TUserEntity that = (TUserEntity) o;

        if (fuId != null ? !fuId.equals(that.fuId) : that.fuId != null) return false;
        if (fuLoginName != null ? !fuLoginName.equals(that.fuLoginName) : that.fuLoginName != null) return false;
        if (fuPwd != null ? !fuPwd.equals(that.fuPwd) : that.fuPwd != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = fuId != null ? fuId.hashCode() : 0;
        result = 31 * result + (fuLoginName != null ? fuLoginName.hashCode() : 0);
        result = 31 * result + (fuPwd != null ? fuPwd.hashCode() : 0);
        return result;
    }
}
