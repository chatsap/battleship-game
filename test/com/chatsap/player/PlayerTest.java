package com.chatsap.player;

import com.chatsap.battleArea.BattleAreaBuilder;
import com.chatsap.battleShip.BattleShipBuilder;
import com.chatsap.command.ICommand;
import com.chatsap.missile.MissileBuilder;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerTest {

    @Test
    public void create_player_successfully(){

        PlayerBuilder playerBuilderA = PlayerBuilder.getNewInstance()
                .setName("A");

        playerBuilderA.setBattleArea(BattleAreaBuilder.getNewInstance()
                    .setDimensionCodes("5 E")
                    .build());

        playerBuilderA.addMissile(MissileBuilder.getNewInstance()
            .setTargetCordinateCode("B2")
            .build());

        playerBuilderA.addMissile(MissileBuilder.getNewInstance()
            .setTargetCordinateCode("B3")
            .build());

        playerBuilderA.addMissile(MissileBuilder.getNewInstance()
                .setTargetCordinateCode("C2")
                .build());

        playerBuilderA.addMissile(MissileBuilder.getNewInstance()
                .setTargetCordinateCode("C3")
                .build());


        PlayerBuilder playerBuilderB = PlayerBuilder.getNewInstance()
                .setName("B");

        playerBuilderB.setBattleArea(BattleAreaBuilder.getNewInstance()
                .setDimensionCodes("5 E")
                .build());

        playerBuilderB.addShip(BattleShipBuilder.getNewInstance()
                .setCorordinates("B2")
                .setDimensions("2" , "2")
                .setType("P")
                .build());

        IPlayer playerA = playerBuilderA.build();
        IPlayer playerB = playerBuilderB.build();

        while(true){
            ICommand command = playerA.getNextCommand();
            if(command == null){
                break;
            }
            command.setOpponent(playerB);
            int result = command.execute();
            Assert.assertEquals(1, result);
        }




    }


}