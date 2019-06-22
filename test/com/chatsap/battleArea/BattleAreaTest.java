package com.chatsap.battleArea;

import com.chatsap.battleShip.BattleShipBuilder;
import com.chatsap.battleShip.BattleShipTypes;
import com.chatsap.battleShip.IBattleShip;
import com.chatsap.missile.IMissile;
import com.chatsap.missile.MissileBuilder;
import com.chatsap.missile.MissileTypes;
import org.junit.Assert;
import org.junit.Test;

public class BattleAreaTest {

    @Test
    public void build_battlearea_successfully() {
        IBattleArea battleArea = BattleAreaBuilder.getNewInstance()
                .setMaxColCode('5')
                .setMaxRowCode('E')
                .build();
        int width = battleArea.getBattleAreaWidth();
        Assert.assertEquals(5, width);

        int height = battleArea.getBattleAreaHeight();
        Assert.assertEquals(5, height);

        int totBattleAreaStrength = battleArea.getTotalBattleAreaStrength();
        Assert.assertEquals(0, totBattleAreaStrength);
    }

    @Test
    public void build_battlearea_with_invalid_width() {
        try {
            IBattleArea battleArea = BattleAreaBuilder.getNewInstance()
                    .setDimensionCodes("10 PP")
                    .build();
        }catch (Exception e){
            Assert.assertEquals("Column code is invalid" , e.getMessage());
        }
    }

    @Test
    public void build_battlearea_without_dimensions() {

        try {
            IBattleArea battleArea = BattleAreaBuilder.getNewInstance()
                    .build();
        } catch (Exception e) {
            Assert.assertEquals("Battlearea is not setup properly", e.getMessage());
        }
    }

    @Test
    public void install_one_ship_successfully(){
        IBattleArea battleArea = BattleAreaBuilder.getNewInstance()
                .setMaxColCode('5')
                .setMaxRowCode('E')
                .build();
        IBattleShip battleShip = BattleShipBuilder.getNewInstance()
                .setType("P")
                .setDimensions("3","2")
                .setCorordinates("B2")
                .build();
        battleArea.installShip(battleShip);


        int width = battleArea.getBattleAreaWidth();
        Assert.assertEquals(5, width);

        int height = battleArea.getBattleAreaHeight();
        Assert.assertEquals(5, height);

        int totBattleAreaStrength = battleArea.getTotalBattleAreaStrength();
        Assert.assertEquals(6, totBattleAreaStrength);

    }

    @Test
    public void install_one_ship_per_cell(){
        IBattleArea battleArea = BattleAreaBuilder.getNewInstance()
                .setMaxColCode('2')
                .setMaxRowCode('B')
                .build();

        int width = battleArea.getBattleAreaWidth();
        Assert.assertEquals(2, width);

        int height = battleArea.getBattleAreaHeight();
        Assert.assertEquals(2, height);

        battleArea.installShip(BattleShipBuilder.getNewInstance()
                .setType("P")
                .setDimensions("1","1")
                .setCorordinates("A1")
                .build());
        Assert.assertEquals(1, battleArea.getTotalBattleAreaStrength());


        battleArea.installShip(BattleShipBuilder.getNewInstance()
                .setType("P")
                .setDimensions("1","1")
                .setCorordinates("A2")
                .build());
        Assert.assertEquals(2, battleArea.getTotalBattleAreaStrength());

        battleArea.installShip(BattleShipBuilder.getNewInstance()
                .setType("P")
                .setDimensions("1","1")
                .setCorordinates("B1")
                .build());
        Assert.assertEquals(3, battleArea.getTotalBattleAreaStrength());

        battleArea.installShip(BattleShipBuilder.getNewInstance()
                .setType("P")
                .setDimensions("1","1")
                .setCorordinates("B2")
                .build());
        Assert.assertEquals(4, battleArea.getTotalBattleAreaStrength());

    }

    @Test
    public void install_one_ship_breaching_eastern_border(){
        IBattleArea battleArea = BattleAreaBuilder.getNewInstance()
                .setMaxColCode('5')
                .setMaxRowCode('E')
                .build();
        IBattleShip battleShip = BattleShipBuilder.getNewInstance()
                .setType("P")
                .setDimensions("5","2")
                .setCorordinates("B2")
                .build();

        try {
            battleArea.installShip(battleShip);
        }catch (Exception e){
            Assert.assertEquals("Ship doesn't fit in battlefield", e.getMessage());
        }
    }

    @Test
    public void install_one_ship_breaching_western_border(){
        IBattleArea battleArea = BattleAreaBuilder.getNewInstance()
                .setMaxColCode('5')
                .setMaxRowCode('E')
                .build();
        IBattleShip battleShip = BattleShipBuilder.getNewInstance()
                .setType("P")
                .setDimensions("5","2")
                .setCorordinates("B0")
                .build();

        try {
            battleArea.installShip(battleShip);
        }catch (Exception e){
            Assert.assertEquals("Ship doesn't fit in battlefield", e.getMessage());
        }
    }

    @Test
    public void install_one_ship_breaching_northern_border(){
        IBattleArea battleArea = BattleAreaBuilder.getNewInstance()
                .setMaxColCode('5')
                .setMaxRowCode('E')
                .build();
        IBattleShip battleShip = BattleShipBuilder.getNewInstance()
                .setType("P")
                .setDimensions("3","4")
                .setCorordinates("@2")
                .build();

        try {
            battleArea.installShip(battleShip);
        }catch (Exception e){
            Assert.assertEquals("Ship doesn't fit in battlefield", e.getMessage());
        }
    }

    @Test
    public void install_one_ship_breaching_southern_border(){
        IBattleArea battleArea = BattleAreaBuilder.getNewInstance()
                .setMaxColCode('5')
                .setMaxRowCode('E')
                .build();
        IBattleShip battleShip = BattleShipBuilder.getNewInstance()
                .setType("P")
                .setDimensions("3","6")
                .setCorordinates("A2")
                .build();

        try {
            battleArea.installShip(battleShip);
        }catch (Exception e){
            Assert.assertEquals("Ship doesn't fit in battlefield", e.getMessage());
        }
    }

    @Test
    public void install_one_ship_with_inconsistent_dimension_and_parameters(){
        IBattleArea battleArea = BattleAreaBuilder.getNewInstance()
                .setMaxColCode('5')
                .setMaxRowCode('E')
                .build();
        try {
            IBattleShip battleShip = BattleShipBuilder.getNewInstance()
                    .setType("P")
                    .setDimensions("2","6")
                    .setCorordinates("A2")
                    .build();

        }catch (Exception e){
            Assert.assertEquals("Height is not matching with ship coordinates", e.getMessage());
        }
    }

    @Test
    public void install_two_ships_without_overlapping(){
        IBattleArea battleArea = BattleAreaBuilder.getNewInstance()
                .setMaxColCode('5')
                .setMaxRowCode('E')
                .build();
        IBattleShip battleShip = BattleShipBuilder.getNewInstance()
                .setType("P")
                .setDimensions("2","5")
                .setCorordinates("A1")
                .build();
        battleArea.installShip(battleShip);

        battleShip = BattleShipBuilder.getNewInstance()
                .setType("Q")
                .setDimensions("3","2")
                .setCorordinates("C3")
                .build();
        battleArea.installShip(battleShip);

        //battleArea.printBattleArea();


        int width = battleArea.getBattleAreaWidth();
        Assert.assertEquals(5, width);

        int height = battleArea.getBattleAreaHeight();
        Assert.assertEquals(5, height);

        int totBattleAreaStrength = battleArea.getTotalBattleAreaStrength();
        Assert.assertEquals(22, totBattleAreaStrength);

    }


    @Test
    public void install_one_typeP_ship_and_hit_one_cell(){
        IBattleArea battleArea = BattleAreaBuilder.getNewInstance()
                .setMaxColCode('5')
                .setMaxRowCode('E')
                .build();
        IBattleShip battleShip = BattleShipBuilder.getNewInstance()
                .setType("P")
                .setDimensions("3","2")
                .setCorordinates("B2")
                .build();
        battleArea.installShip(battleShip);

        int width = battleArea.getBattleAreaWidth();
        Assert.assertEquals(5, width);

        int height = battleArea.getBattleAreaHeight();
        Assert.assertEquals(5, height);

        int totBattleAreaStrength = battleArea.getTotalBattleAreaStrength();
        Assert.assertEquals(6, totBattleAreaStrength);

        IMissile missile = MissileBuilder.getNewInstance()
                .setTargetCordinateCode("B2")
                .build();

        int result = battleArea.applyMissile(missile);
        Assert.assertEquals(1,result);

        int cellStrength = battleArea.getCellStrength("B2");
        Assert.assertEquals(0, cellStrength);


    }

    @Test
    public void install_one_typeQ_ship_and_hit_one_cell(){
        IBattleArea battleArea = BattleAreaBuilder.getNewInstance()
                .setMaxColCode('5')
                .setMaxRowCode('E')
                .build();
        IBattleShip battleShip = BattleShipBuilder.getNewInstance()
                .setType("Q")
                .setDimensions("3","2")
                .setCorordinates("B2")
                .build();
        battleArea.installShip(battleShip);

        int width = battleArea.getBattleAreaWidth();
        Assert.assertEquals(5, width);

        int height = battleArea.getBattleAreaHeight();
        Assert.assertEquals(5, height);

        int totBattleAreaStrength = battleArea.getTotalBattleAreaStrength();
        Assert.assertEquals(12, totBattleAreaStrength);


        int cellStrengthBeforeHit = battleArea.getCellStrength("B2");
        Assert.assertEquals(2, cellStrengthBeforeHit);

        IMissile missile = MissileBuilder.getNewInstance()
                .setTargetCordinateCode("B2")
                .build();

        int result = battleArea.applyMissile(missile);
        Assert.assertEquals(1,result);

        //battleArea.printBattleArea();

        int cellStrengthAfterHit = battleArea.getCellStrength("B2");
        Assert.assertEquals(1, cellStrengthAfterHit);

    }

    @Test
    public void install_one_typeP_ship_and_hit_one_APHA_missile(){
        IBattleArea battleArea = BattleAreaBuilder.getNewInstance()
                .setMaxColCode('5')
                .setMaxRowCode('E')
                .build();

        int width = battleArea.getBattleAreaWidth();
        Assert.assertEquals(5, width);

        int height = battleArea.getBattleAreaHeight();
        Assert.assertEquals(5, height);


        IBattleShip battleShip = BattleShipBuilder.getNewInstance()
                .setType("P")
                .setDimensions("3","3")
                .setCorordinates("A1")
                .build();
        battleArea.installShip(battleShip);

        int totBattleAreaStrength = battleArea.getTotalBattleAreaStrength();
        Assert.assertEquals(9, totBattleAreaStrength);


        int cellStrengthBeforeHit = battleArea.getCellStrength("B2");
        Assert.assertEquals(1, cellStrengthBeforeHit);



        IMissile missile = MissileBuilder.getNewInstance()
                .setTargetCordinateCode("B2")
                .setType(MissileTypes.TYPE_ALPHA)
                .build();

        int result = battleArea.applyMissile(missile);
        Assert.assertEquals(1,result);

        //battleArea.printBattleArea();

        int cellStrengthAfterHit = battleArea.getCellStrength("B2");
        Assert.assertEquals(0, cellStrengthAfterHit);

         totBattleAreaStrength = battleArea.getTotalBattleAreaStrength();
        Assert.assertEquals(0, totBattleAreaStrength);


    }


    @Test
    public void install_typeQ_ship_hit_twice_miss_last(){
        IBattleArea battleArea = BattleAreaBuilder.getNewInstance()
                .setMaxColCode('5')
                .setMaxRowCode('E')
                .build();
        IBattleShip battleShip = BattleShipBuilder.getNewInstance()
                .setType("Q")
                .setDimensions("3","2")
                .setCorordinates("B2")
                .build();
        battleArea.installShip(battleShip);

        int width = battleArea.getBattleAreaWidth();
        Assert.assertEquals(5, width);

        int height = battleArea.getBattleAreaHeight();
        Assert.assertEquals(5, height);

        int totBattleAreaStrength = battleArea.getTotalBattleAreaStrength();
        Assert.assertEquals(12, totBattleAreaStrength);


        int cellStrengthBeforeHit = battleArea.getCellStrength("B2");
        Assert.assertEquals(2, cellStrengthBeforeHit);

        IMissile missile = MissileBuilder.getNewInstance()
                .setTargetCordinateCode("B2")
                .build();

        int result = battleArea.applyMissile(missile);
        Assert.assertEquals(1,result);

        int cellStrengthAfterHit = battleArea.getCellStrength("B2");
        Assert.assertEquals(1, cellStrengthAfterHit);

        result = battleArea.applyMissile(missile);
        Assert.assertEquals(1,result);

        cellStrengthAfterHit = battleArea.getCellStrength("B2");
        Assert.assertEquals(0, cellStrengthAfterHit);


        result = battleArea.applyMissile(missile);
        Assert.assertEquals(0,result);

        cellStrengthAfterHit = battleArea.getCellStrength("B2");
        Assert.assertEquals(0, cellStrengthAfterHit);

        Assert.assertEquals(false, battleArea.isBattleAreaDestroyed());
    }

    @Test
    public void install_typeQ_ship_and_destroy_completely(){
        IBattleArea battleArea = BattleAreaBuilder.getNewInstance()
                .setMaxColCode('5')
                .setMaxRowCode('E')
                .build();
        IBattleShip battleShip = BattleShipBuilder.getNewInstance()
                .setType("Q")
                .setDimensions("2","2")
                .setCorordinates("B2")
                .build();
        battleArea.installShip(battleShip);


        int totBattleAreaStrength = battleArea.getTotalBattleAreaStrength();
        Assert.assertEquals(8, totBattleAreaStrength);


        IMissile missile = MissileBuilder.getNewInstance()
                .setTargetCordinateCode("B2")
                .build();

        int result = battleArea.applyMissile(missile);
        Assert.assertEquals(1,result);

        result = battleArea.applyMissile(missile);
        Assert.assertEquals(1,result);

        missile = MissileBuilder.getNewInstance()
                .setTargetCordinateCode("B3")
                .build();

        result = battleArea.applyMissile(missile);
        Assert.assertEquals(1,result);

        result = battleArea.applyMissile(missile);
        Assert.assertEquals(1,result);

        missile = MissileBuilder.getNewInstance()
                .setTargetCordinateCode("C2")
                .build();

        result = battleArea.applyMissile(missile);
        Assert.assertEquals(1,result);

        result = battleArea.applyMissile(missile);
        Assert.assertEquals(1,result);

        missile = MissileBuilder.getNewInstance()
                .setTargetCordinateCode("C3")
                .build();

        result = battleArea.applyMissile(missile);
        Assert.assertEquals(1,result);

        result = battleArea.applyMissile(missile);
        Assert.assertEquals(1,result);

        Assert.assertEquals(true, battleArea.isBattleAreaDestroyed());

    }

}