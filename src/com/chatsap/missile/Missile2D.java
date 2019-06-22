package com.chatsap.missile;

class Missile2D implements IMissile {


    private final String targetCode;
    private final MissileTypes type;
    private int targetCol;
    private int targetRow;

    Missile2D(MissileTypes type, String targetCode) {
        this.targetCode = targetCode;
        this.type = type;
        char[] targetCoordArray = targetCode.toUpperCase().toCharArray();

        int targetRow = targetCoordArray[0] - 'A';
        int targetCol = targetCoordArray[1] - '1';

        this.setTargetRow(targetRow);
        this.setTargetCol(targetCol);
    }

    @Override
    public MissileTypes getType(){
        return this.type;
    }

    @Override
    public int getTargetRow() {
        return this.targetRow;
    }

    public void setTargetRow(int targetRow) {
        this.targetRow = targetRow;
    }

    @Override
    public int getTargetCol() {
        return this.targetCol;
    }

    public void setTargetCol(int targetCol) {
        this.targetCol = targetCol;
    }

    @Override
    public String getTargetCode() {
        return this.targetCode;
    }
}
