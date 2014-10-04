package com.palisade.kilobolt.stat;

public class Offense {
    private int power;
    private int attackRange;

    public Offense(){
        new Offense(0);
    }
    public Offense(int power){
        new Offense(power, 0);
    }
    public Offense(int power, int attackRange){
        setPower(power);
        setAttackRange(attackRange);
    }

    public int getPower() {
        return power;
    }
    public void setPower(int power) {
        this.power = power;
    }

    public int getAttackRange(){
        return attackRange;
    }
    public void setAttackRange(int attackRange){
        this.attackRange = attackRange;
    }
}
