package com.chatsap.missile;

import com.chatsap.battleArea.BattleAreaBuilder;

public class MissileBuilder {

    private String targetCode;
    private MissileTypes type = MissileTypes.TYPE_REGULAR;

    public static MissileBuilder getNewInstance() {
        return new MissileBuilder();
    }

    public MissileBuilder setTargetCordinateCode(String targetCode){
        this.targetCode = targetCode;
        return this;
    }

    public IMissile build(){

        validateInputs();

        Missile2D missile = new Missile2D(type, targetCode.toUpperCase());



        return missile;

    }

    private void validateInputs() {
        if(targetCode == null || targetCode.trim().length() ==0){
            throw new RuntimeException("Missile target is empty");
        }
    }

    public MissileBuilder setType(MissileTypes type) {
        this.type = type;
        return this;
    }
}
