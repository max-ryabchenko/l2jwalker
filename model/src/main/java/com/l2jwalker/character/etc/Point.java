package com.l2jwalker.character.etc;

public class Point implements Cloneable {

    private int x;
    private int y;
    private int z;

    public Point() {
    }

    public Point(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void setXYZ(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Point getPoint() {
        return new Point(this.x, this.y, this.z);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getZ() {
        return z;
    }

    public void setZ(int z) {
        this.z = z;
    }

    public Point sub(Point p) {
        return new Point(this.x - p.x, this.y - p.y, this.z - p.z);
    }

    public Point add(Point p) {
        return new Point(this.x + p.x, this.y + p.y, this.z + p.z);
    }

    public void addThis(Point p) {
        this.x += p.x;
        this.y += p.y;
        this.z += p.z;
    }

    public int dist2D(Point p) {
        return (int) Math.sqrt(
                (this.x - p.x) * (this.x - p.x)
                        + (this.y - p.y) * (this.y - p.y)
        );
    }

    public int dist3D(Point p) {
        return (int) Math.sqrt(
                (this.x - p.x) * (this.x - p.x)
                        + (this.y - p.y) * (this.y - p.y)
                        + (this.z - p.z) * (this.z - p.z)
        );
    }

    public void copyFrom(Point p) {
        this.x = p.x;
        this.y = p.y;
        this.z = p.z;
    }

    @Override
    public Point clone() {
        return new Point(this.x, this.y, this.z);
    }

    @Override
    public String toString() {
        return "[" + Integer.toHexString(this.x) + ";"
                + Integer.toHexString(this.y) + ";"
                + Integer.toHexString(this.z) + "]";
    }

}
