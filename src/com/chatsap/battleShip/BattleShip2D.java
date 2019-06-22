package com.chatsap.battleShip;

class BattleShip2D implements IBattleShip {

    private int rowId;
    private int colId;
    private BattleShipTypes type;
    private int width;
    private int height;

    @Override
    public int getRowId() {
        return rowId;
    }

    void setRowId(int rowId) {
        this.rowId = rowId;
    }

    @Override
    public int getColId() {
        return colId;
    }

    void setColId(int colId) {
        this.colId = colId;
    }


    @Override
    public int getCellStrength() {
        return this.type == BattleShipTypes.TYPE_P ? 1 : 2;
    }


    public BattleShipTypes getType() {
        return type;
    }

    void setType(BattleShipTypes type) {
        this.type = type;
    }

    @Override
    public int getWidth() {
        return width;
    }

    void setWidth(int width) {
        this.width = width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    void setHeight(int height) {
        this.height = height;
    }
}
