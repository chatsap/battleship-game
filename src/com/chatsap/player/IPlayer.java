package com.chatsap.player;

import com.chatsap.command.ICommand;
import com.chatsap.missile.IMissile;

import java.util.Queue;

public interface IPlayer {

    ICommand getNextCommand();

    int receiveMissile(IMissile missile);

    boolean hasMoreCommands();

    String getName();

    void printBattleArea();

    boolean isBattleAreaDestroyed();
}
