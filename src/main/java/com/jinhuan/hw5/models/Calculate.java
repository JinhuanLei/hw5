package com.jinhuan.hw5.models;

public class Calculate {
    private String x;
    private String y;
    private String operation;
    private String result;
    private String hash;
    private String hash_alg;

    public String getHash() {
        return hash;
    }

    public String getHash_alg() {
        return hash_alg;
    }

    public void setHash_alg(String hash_alg) {
        this.hash_alg = hash_alg;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public static Calculate create(Calculate c) {
        Calculate newC = new Calculate(new Builder()
                .x(c.getX())
                .y(c.getY())
        .operation(c.getOperation())
        .result(c.getResult()));
//        Calculate newC = new Calculate();
//        newC.setX(c.getX());
//        newC.setY(c.getY());
//        newC.setOperation(c.getOperation());
//        newC.setResult(c.getResult());
        return newC;
    }
    public Calculate() {

    }
    public Calculate(Builder b) {
        this.x = b.x;
        this.y = b.y;
        this.operation = b.operation;
        this.result = b.result;
        this.hash = b.hash;
        this.hash_alg = b.hash_alg;
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public static class Builder {
        private String x;
        private String y;
        private String operation;
        private String result;
        private String hash;
        private String hash_alg;

        public Builder x(String x) {
            this.x = x;
            return this;
        }

        public Builder y(String y) {
            this.y = y;
            return this;
        }

        public Builder operation(String operation) {
            this.operation = operation;
            return this;
        }

        public Builder result(String result) {
            this.result = result;
            return this;
        }

        public Builder hash(String hash) {
            this.hash = hash;
            return this;
        }

        public Builder hash_alg(String hash_alg) {
            this.hash_alg = hash_alg;
            return this;
        }
    }
}
