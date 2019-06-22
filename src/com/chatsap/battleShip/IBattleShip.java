package com.chatsap.battleShip;

public interface IBattleShip {

    int getHeight();

    int getWidth();

    public static enum TYPE {P,Q};

    int getRowId();

    int getColId();

    int getCellStrength();
}
