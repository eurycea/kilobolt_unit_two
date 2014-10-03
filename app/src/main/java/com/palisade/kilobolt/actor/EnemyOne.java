package com.palisade.kilobolt.actor;


import com.palisade.kilobolt.StartingClass;
import com.palisade.kilobolt.actor.AbstractEnemy;

public class EnemyOne extends AbstractEnemy {

    public EnemyOne(StartingClass app){
        super(app);
    }

    @Override
    protected String getResNormal() {
        return "NormalResource.png";
    }

    @Override
    protected String getResAttacking() {
        return "AttackingResource.png";
    }

    @Override
    protected String getResJumping() {
        return "JumpingResource.png";
    }
}
