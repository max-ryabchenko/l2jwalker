package com.l2jwalker.entity;

import com.l2jwalker.packet.PacketDirection;
import com.l2jwalker.packet.ServerType;
import org.hibernate.annotations.Cache;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;

import static org.hibernate.annotations.CacheConcurrencyStrategy.NONSTRICT_READ_WRITE;

@Entity
@Table(name = "packet")
@Cache(usage = NONSTRICT_READ_WRITE)
public class PacketCodeData implements Identifiable<ID>, Serializable, Copyable<PacketCodeData> {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private ID id;

    @Column(name = "name")
    private String name;

    @Column(name = "direction", columnDefinition = "byte")
    @Enumerated(EnumType.ORDINAL)
    private PacketDirection direction;

    @Column(name = "server", columnDefinition = "byte")
    @Enumerated(EnumType.ORDINAL)
    private ServerType serverType;

    @Column(name = "code", columnDefinition = "blob")
    private byte[] code;

    @Override
    public boolean equals(final Object o) {
        return null != o && o instanceof PacketCodeData && ((PacketCodeData) o).getId().equals(this.getId());
    }

    @Override
    public int hashCode() {
        return null == getId() ? 0 : getId().hashCode();
    }

    @Override
    public PacketCodeData copy() {
        PacketCodeData packetCodeData = new PacketCodeData();
        copyTo(packetCodeData);
        return packetCodeData;
    }

    @Override
    public void copyTo(PacketCodeData packetCodeData) {
        packetCodeData.setId(getId());
        packetCodeData.setName(getName());
        packetCodeData.setDirection(getDirection());
        packetCodeData.setServerType(getServerType());
        packetCodeData.setCode(getCode());
    }

    @Override
    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }

    @Transient
    public boolean isIdSet() {
        return getId() != null;
    }


    public PacketDirection getDirection() {
        return direction;
    }

    public void setDirection(PacketDirection direction) {
        this.direction = direction;
    }

    public ServerType getServerType() {
        return serverType;
    }

    public void setServerType(ServerType serverType) {
        this.serverType = serverType;
    }

    public byte[] getCode() {
        return code;
    }

    public void setCode(byte[] code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
