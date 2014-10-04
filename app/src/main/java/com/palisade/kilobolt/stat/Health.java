package com.palisade.kilobolt.stat;

public class Health {
    private int max, current;
    public Health(int max){
        setCurrent(max);
        setMax(max);
    }
    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }
}
