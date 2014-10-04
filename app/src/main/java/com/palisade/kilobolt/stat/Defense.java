package com.palisade.kilobolt.stat;

public class Defense {
    private int armor;
    public Defense(int armorValue){
        this.armor = armorValue;
    }
    public int getArmor(){
        return armor;
    }
    public void setArmor(int armorValue){
        this.armor = armorValue;
    }
    public interface HasArmor{
        public int reduceDamage(int damage);
    }
}
