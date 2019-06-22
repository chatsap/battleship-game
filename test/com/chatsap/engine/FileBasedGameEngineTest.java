package com.chatsap.engine;

import com.chatsap.command.ICommand;
import com.chatsap.player.IPlayer;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.util.List;

import static org.junit.Assert.*;

public class FileBasedGameEngineTest {

    @Test
    public void testSampleDataProvided() {
        FileBased2PlayerGameEngine gameEngine = new FileBased2PlayerGameEngine(new File("resource/game_config_file.txt"));
        try {
            IPlayer winner = gameEngine.execute();
            for(ICommand command : gameEngine.getCommandHistory()){
                System.out.println(command.getResultString());
            }
            System.out.println("Player-"+winner.getName()+" won the battle");

            Assert.assertEquals("2", winner.getName());
            Assert.assertEquals(17, gameEngine.getCommandHistory().size());
        }catch (Exception e){
            e.printStackTrace();
            fail(e.getMessage());
        }

    }

    @Test
    public void test_2x2_battlearea_2_ships() {
        FileBased2PlayerGameEngine gameEngine = new FileBased2PlayerGameEngine(new File("resource/game_config_file_2.txt"));
        try {
            IPlayer winner = gameEngine.execute();
            for(ICommand command : gameEngine.getCommandHistory()){
                System.out.println(command.getResultString());
            }
            System.out.println("Player-"+winner.getName()+" won the battle");

            Assert.assertEquals("2", winner.getName());
            Assert.assertEquals(6, gameEngine.getCommandHistory().size());
        }catch (Exception e){
            e.printStackTrace();
            fail(e.getMessage());
        }
    }

    @Test
    public void test_2x2_battlearea_4_ships() {
        FileBased2PlayerGameEngine gameEngine = new FileBased2PlayerGameEngine(new File("resource/game_config_file_3.txt"));
        try {
            IPlayer winner = gameEngine.execute();
            for(ICommand command : gameEngine.getCommandHistory()){
                System.out.println(command.getResultString());
            }
            if(winner == null){
                System.out.println("It's a draw");
            }else {
                System.out.println("Player-" + winner.getName() + " won the battle");
            }

                Assert.assertEquals("2", winner.getName());
            Assert.assertEquals(9, gameEngine.getCommandHistory().size());
        }catch (Exception e){
            e.printStackTrace();
            fail(e.getMessage());
        }
    }
}