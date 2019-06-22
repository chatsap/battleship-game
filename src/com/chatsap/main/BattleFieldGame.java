package com.chatsap.main;

import com.chatsap.command.ICommand;
import com.chatsap.engine.FileBased2PlayerGameEngine;
import com.chatsap.engine.IGameEngine;
import com.chatsap.player.IPlayer;

import java.io.File;
import java.util.List;

public class BattleFieldGame {

    public static void main(String[] args) throws Exception {
        IGameEngine gameEngine = new FileBased2PlayerGameEngine(new File("resource/game_config_file.txt"));
        IPlayer winner = gameEngine.execute();
        for(ICommand command : gameEngine.getCommandHistory()){
            System.out.println(command.getResultString());
        }
        if(winner == null){
            System.out.println("It's a draw");
        }else {
            System.out.println("Player-" + winner.getName() + " won the battle");
        }
    }
}
