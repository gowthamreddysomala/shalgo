package com.shamiralgo.shalgo.entity;

import java.util.Map;

public class ProblemInput {
    private Keys keys;
    private Map<String, Share> shares;

    public Keys getKeys() {
        return keys;
    }

    public void setKeys(Keys keys) {
        this.keys = keys;
    }

    public Map<String, Share> getShares() {
        return shares;
    }

    public void setShares(Map<String, Share> shares) {
        this.shares = shares;
    }

    public static class Keys {
        private int n;
        private int k;

        public int getN() {
            return n;
        }

        public void setN(int n) {
            this.n = n;
        }

        public int getK() {
            return k;
        }

        public void setK(int k) {
            this.k = k;
        }
    }
}
