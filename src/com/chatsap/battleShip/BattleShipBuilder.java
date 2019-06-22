package com.chatsap.battleShip;

import com.chatsap.player.PlayerBuilder;

public class BattleShipBuilder {

    private String coordinateString=null;
    private int width = -1;
    private int height = -1;
    private BattleShipTypes type;

    public static BattleShipBuilder getNewInstance() {
        return new BattleShipBuilder();
    }

    public BattleShipBuilder setCorordinates(String coordinates){
        this.coordinateString = coordinates;
        return this;
    }

    public BattleShipBuilder setDimensions(String width, String height){
        this.width = Integer.parseInt(width);
        this.height = Integer.parseInt(height);
        return this;
    }

//    public BattleShipBuilder setType(BattleShipTypes type){
//        this.type = type;
//        return this;
//    }


    public IBattleShip build(){
        validateInputs();


        BattleShip2D battleShip = new BattleShip2D();

        addType(battleShip);
        addCoordinate(battleShip);
        addDimensions(battleShip);

        return battleShip;

    }



    private void addType(BattleShip2D battleShip) {
        battleShip.setType(this.type);

    }

    private void addDimensions(BattleShip2D battleShip) {
        battleShip.setWidth(this.width);
        battleShip.setHeight(this.height);
    }



    private void addCoordinate(BattleShip2D battleShip) {
        char[] coordArr = this.coordinateString.toUpperCase().toCharArray();
        int rowId = coordArr[0] - 'A';
        int colId = coordArr[1] - '1';

        battleShip.setRowId(rowId);
        battleShip.setColId(colId);

    }



    private void validateInputs() {

        validateCoordinate(this.coordinateString);
        validateDimensions();
        validateType();
    }

    private void validateType() {
        if(this.type != BattleShipTypes.TYPE_P && this.type != BattleShipTypes.TYPE_Q){
            throw new RuntimeException("Invalid ship type");
        }
    }

    private void validateDimensions() {
        if(this.width ==-1 || this.height == -1){
            throw new RuntimeException("Invalid dimensions");
        }
    }

    private void validateCoordinate(String coordinateString) {
        if(coordinateString==null || coordinateString.trim().length()!=2){
            throw new RuntimeException("Invalid coordinates "+coordinateString);
        }
    }


    public BattleShipBuilder setType(String type) {
        switch (type.toUpperCase()){
            case "P" :
                this.type = BattleShipTypes.TYPE_P;
                break;
            case "Q" :
                this.type = BattleShipTypes.TYPE_Q;
                break;
            default:
                throw new RuntimeException("Battle ship type is ot supported");
        }

        return this;
    }
}
