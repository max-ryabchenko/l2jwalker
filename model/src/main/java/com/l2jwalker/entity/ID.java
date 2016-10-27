package com.l2jwalker.entity;

import com.l2jwalker.packet.Version;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class ID implements Serializable, Cloneable {

    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "version", nullable = false)
    private Version version;

    public ID() {

    }

    public ID(Long id, Version version) {
        setId(id);
        setVersion(version);
    }

    public ID(Integer id, Version version) {
        setId(id.longValue());
        setVersion(version);
    }

    @Override
    public int hashCode() {
        return getId().intValue();
    }

    @Override
    public boolean equals(Object o) {
        if (null == o || !(o instanceof ID)) {
            return false;
        }
        ID other = (ID) o;
        return
                other.getVersion() == getVersion() &&
                        (
                                (null == getId() && null == other.getId()) ||
                                        (null != getId() && getId().equals(other.getId()))
                        );
    }

    @Override
    public ID clone() {
        return new ID(getId(), getVersion());
    }

    @Override
    public String toString() {
        return "ID:{id:" + getId() + ",version:" + getVersion() + "}";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Version getVersion() {
        return version;
    }

    public void setVersion(Version version) {
        this.version = version;
    }

}
