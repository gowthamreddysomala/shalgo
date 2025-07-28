package com.shamiralgo.shalgo.entity;

import java.math.BigInteger;

public class Point {
    private int x;
    private BigInteger y;

    public Point(int x, BigInteger y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public BigInteger getY() {
        return y;
    }
}


