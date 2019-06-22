package com.chatsap.battleArea;

import com.chatsap.battleShip.IBattleShip;
import com.chatsap.missile.IMissile;
import com.chatsap.shipDestroyer.IShipDestroyer;
import com.chatsap.shipDestroyer.ShipDestroyerFactory;

import java.util.Arrays;

class BattleArea2D implements IBattleArea {

    private final int totalCols;
    private final int totalRows;
    private int[][] grid = null;
    private int totBattleAreaStrength = 0;

    BattleArea2D(int rows, int cols) {
        this.totalRows = rows;
        this.totalCols = cols;
        grid = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            Arrays.fill(grid[i], 0);
        }

    }

    @Override
    public boolean isBattleAreaDestroyed() {
        return totBattleAreaStrength == 0;
    }

    @Override
    public int applyMissile(IMissile missile) {

        IShipDestroyer shipDestroyer = ShipDestroyerFactory.getDestroyer(missile.getType());

        return shipDestroyer.destroy(this, missile);


    }

    @Override
    public void installShip(IBattleShip battleShip) {

        if (isShipCoordinateInvalid(battleShip)
                || isShipDimensionInvalid(battleShip)) {
            throw new RuntimeException("Ship doesn't fit in battlefield");
        }

        for (int i = 0; i < battleShip.getHeight(); i++) {
            int row_pos = battleShip.getRowId() + i;
            for (int j = 0; j < battleShip.getWidth(); j++) {
                int col_pos = battleShip.getColId() + j;
                if (grid[row_pos][col_pos] == 0) {
                    grid[row_pos][col_pos] = battleShip.getCellStrength();
                    totBattleAreaStrength = totBattleAreaStrength + battleShip.getCellStrength();
                } else {
                    throw new RuntimeException("Battleship is overlapping");
                }
            }
        }

    }

    private boolean isShipDimensionInvalid(IBattleShip battleShip) {
        return battleShip.getRowId() + battleShip.getHeight() > this.totalRows
                || battleShip.getColId() + battleShip.getWidth() > this.totalCols;
    }

    private boolean isShipCoordinateInvalid(IBattleShip battleShip) {
        return battleShip.getRowId() < 0 || battleShip.getRowId() > this.totalRows - 1
                || battleShip.getColId() < 0 || battleShip.getColId() > this.totalCols - 1 ;
    }


    @Override
    public void printBattleArea() {
        System.out.println("[");
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                System.out.print(grid[i][j] + ",");
            }
            System.out.println("");
        }
        System.out.println("]");
    }

    @Override
    public int getBattleAreaWidth() {
        return this.totalCols;
    }

    @Override
    public int getBattleAreaHeight() {
        return this.totalRows;
    }

    @Override
    public int getTotalBattleAreaStrength() {
        return this.totBattleAreaStrength;
    }

    @Override
    public int getCellStrength(String cellCode) {
        char[] coordArr = cellCode.toUpperCase().toCharArray();
        int rowId = coordArr[0] - 'A';
        int colId = coordArr[1] - '1';

        if (rowId < 0 || rowId > this.totalRows - 1
                || colId < 0 || colId > this.totalCols - 1) {
            throw new RuntimeException("Invalid cell code");
        }

        return grid[rowId][colId];
    }




    @Override
    public int destroyCell(int row, int col){

        if (grid[row][col] <= 0)
            return 0;

        grid[row][col] = grid[row][col] - 1;
        totBattleAreaStrength--;
        return 1;
    }


}
