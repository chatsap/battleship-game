package com.chatsap.battleArea;

public class BattleAreaBuilder {


    private char maxRowCode=' ';
    private char maxColCode = ' ';

    public static BattleAreaBuilder getNewInstance() {
        return new BattleAreaBuilder();
    }

    public BattleAreaBuilder setMaxRowCode(char maxRowCode){
        this.maxRowCode = maxRowCode;
        return this;
    }

    public BattleAreaBuilder setMaxColCode(char maxColCode){
        this.maxColCode = maxColCode;
        return this;
    }

    public IBattleArea build(){
        validateInputs();

        int rows = Character.toUpperCase(maxRowCode) - 'A' +1;
        int cols = Character.toUpperCase(maxColCode) - '1' +1;

        if(rows < 0 || cols < 0){
            throw new RuntimeException("Battlearea dimensions incorrect");
        }

        BattleArea2D battleArea = new BattleArea2D(rows , cols);

        return battleArea;
    }

    private void validateInputs() {
        if(maxRowCode == ' ' || maxColCode == ' '){
            throw new RuntimeException("Battlearea is not setup properly");
        }

        if(maxRowCode < 'A'  || maxRowCode > 'Z' ){
            throw new RuntimeException("Invalid height");
        }
        if(maxColCode < '1'  || maxColCode > '9' ){
            throw new RuntimeException("Invalid width");
        }
    }

    public BattleAreaBuilder setDimensionCodes(String dimensionCodes) {
        if(dimensionCodes == null || dimensionCodes.trim().length() == 0 ){
            throw new RuntimeException("Invalid battle area dimensions");
        }
        String[] coordArr = dimensionCodes.trim().toUpperCase().split(" ");

        if(coordArr.length != 2){
            throw new RuntimeException("Invalid battle area dimensions");
        }

        if(coordArr[0].trim().length() !=1){
            throw new RuntimeException("Column code is invalid");
        }
        if(coordArr[1].trim().length() !=1){
            throw new RuntimeException("Row code is invalid");
        }

        this.maxColCode = coordArr[0].trim().charAt(0);
        this.maxRowCode = coordArr[1].trim().charAt(0);

        return this;

    }
}
