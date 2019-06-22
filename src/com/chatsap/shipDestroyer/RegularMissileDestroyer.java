package com.chatsap.shipDestroyer;

import com.chatsap.battleArea.IBattleArea;
import com.chatsap.missile.IMissile;

public class RegularMissileDestroyer implements IShipDestroyer {
    @Override
    public int destroy(IBattleArea battleArea, IMissile missile) {

        int totalRows = battleArea.getBattleAreaHeight();
        int totalCols = battleArea.getBattleAreaWidth();


        int row = missile.getTargetRow();
        int col = missile.getTargetCol();

        if (row < 0 || col < 0 || row > totalRows - 1 || col > totalCols - 1) {
            throw new RuntimeException("Missile target is outside battlearea");
        }


        return battleArea.destroyCell(row, col);


    }
}
