package com.chatsap.missile;

public interface IMissile {
    MissileTypes getType();

    public int getTargetRow() ;

    public int getTargetCol() ;

    String getTargetCode();
}
