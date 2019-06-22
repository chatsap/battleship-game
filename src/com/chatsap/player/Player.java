package com.chatsap.player;

import com.chatsap.battleArea.IBattleArea;
import com.chatsap.command.CommandFactory;
import com.chatsap.command.ICommand;
import com.chatsap.missile.IMissile;

import java.util.Queue;

class Player implements IPlayer{

    private final String name;
    private Queue<ICommand> commands;
    private IBattleArea battleArea;

    Player(String name) {
        this.name = name;
    }

    @Override
    public ICommand getNextCommand(){
        ICommand command = null;
        if(commands != null) {
            command = commands.poll();
        }
        return command;
    }


    @Override
    public int receiveMissile(IMissile missile) {
        return battleArea.applyMissile(missile);
    }

    @Override
    public boolean hasMoreCommands() {
        return !(commands == null || commands.isEmpty());
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void printBattleArea() {
        this.battleArea.printBattleArea();
    }

    @Override
    public boolean isBattleAreaDestroyed() {
        return this.battleArea.isBattleAreaDestroyed();
    }


    public void addBattleArea(IBattleArea battleArea) {
        this.battleArea = battleArea;
    }


    public void addCommands(Queue<ICommand> commands) {
        this.commands = commands;
    }
}
