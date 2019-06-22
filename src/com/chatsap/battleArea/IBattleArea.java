package com.chatsap.battleArea;

import com.chatsap.missile.IMissile;
import com.chatsap.battleShip.IBattleShip;

public interface IBattleArea {
    boolean isBattleAreaDestroyed();

    int applyMissile(IMissile missile);

    void installShip(IBattleShip battleShip);

    void printBattleArea();

    int getBattleAreaWidth();

    int getBattleAreaHeight();

    int getTotalBattleAreaStrength();

    int getCellStrength(String cellCode);


    int destroyCell(int row, int col);
}
