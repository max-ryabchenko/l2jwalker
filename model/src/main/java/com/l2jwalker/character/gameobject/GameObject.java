package com.l2jwalker.character.gameobject;

import com.l2jwalker.character.etc.KnownObject;
import com.l2jwalker.character.etc.Point;
import com.l2jwalker.packet.Version;
import org.apache.log4j.Logger;

import java.util.Map;

public abstract class GameObject implements KnownObject {

    private static final Logger LOG = Logger.getLogger(GameObject.class);
    protected final long id;
    protected final long createTime = System.currentTimeMillis();
    private final Version version;
    protected GameObjectType gameObjectType;
    protected int vehicleId;
    protected int heading;
    protected Point point = new Point();
    protected String name;
    protected String title;

    public GameObject(long id, Version version) {
        this.id = id;
        this.version = version;
    }

    public final long getLiveTime() {
        return System.currentTimeMillis() - this.createTime;
    }

    public abstract boolean isUnknown();

    @Override
    public boolean equals(Object o) {
        return null != o && o instanceof GameObject && ((GameObject) o).id == this.id;
    }

    @Override
    public int hashCode() {
        return (int) getId();
    }

    @Override
    public String toString() {
        return null == this.name ? ("[" + this.id + "]") : this.name;
    }

    protected abstract boolean refreshPosition();

    public boolean isHaveTarget() {
        return false;
    }

    public int getTargetId() {
        return 0;
    }

    public boolean isMoving() {
        return false;
    }

    public abstract void statusUpdate(Map<Integer, Integer> stats);

    public GameObjectType getGameObjectType() {
        return this.gameObjectType;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public int getHeading() {
        return heading;
    }

    public void setHeading(int heading) {
        this.heading = heading;
    }

    @Override
    public long getId() {
        return id;
    }

    public Point getPoint() {
        refreshPosition();
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    @Override
    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }

    public abstract int getCurrentHP();

    public abstract int getMaxHP();

    public abstract int getCurrentMP();

    public abstract int getMaxMP();

    public Version getVersion() {
        return version;
    }
}
