package com.palisade.kilobolt.actor;


import com.palisade.kilobolt.StartingClass;
import com.palisade.kilobolt.actor.AbstractEnemy;
import com.palisade.kilobolt.constant.Constants;

public class EnemyOne extends AbstractEnemy {

    public EnemyOne(StartingClass app){
        super(app);
    }

    @Override
    protected String getResNormal() {
        return Constants.RES_ENEMY_ONE_NORMAL;
    }

    @Override
    protected String getResAttacking() {
        return Constants.RES_ENEMY_ONE_ATTACKING;
    }

    @Override
    protected String getResJumping() {
        return Constants.RES_ENEMY_ONE_JUMPING;
    }
}
