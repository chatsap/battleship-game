package com.chatsap.shipDestroyer;

import com.chatsap.battleArea.IBattleArea;
import com.chatsap.missile.IMissile;

public class AlphaMissileDestroyer implements IShipDestroyer {
    @Override
    public int destroy(IBattleArea battleArea, IMissile missile) {

        int totalRows = battleArea.getBattleAreaHeight();
        int totalCols = battleArea.getBattleAreaWidth();


        int row = missile.getTargetRow();
        int col = missile.getTargetCol();

        if (row < 0 || col < 0 || row > totalRows - 1 || col > totalCols - 1) {
            throw new RuntimeException("Missile target is outside battlearea");
        }

        int min_row = missile.getTargetRow() > 0 ? missile.getTargetRow()-1 : missile.getTargetRow();
        int min_col = missile.getTargetCol() > 0 ? missile.getTargetCol()-1 : missile.getTargetCol();

        int result = 0;
        for (int i = min_row ; i <= missile.getTargetRow()+1 && i<battleArea.getBattleAreaHeight() ; i++){
            for(int j = min_col ; j <=missile.getTargetCol() + 1 && j < battleArea.getBattleAreaWidth(); j++){
                result = result + battleArea.destroyCell(i,j);
            }
        }

        return result > 0? 1 : 0;

    }
}
