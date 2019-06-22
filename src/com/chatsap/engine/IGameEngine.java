package com.chatsap.engine;

import com.chatsap.command.ICommand;
import com.chatsap.player.IPlayer;

import java.util.List;

public interface IGameEngine {


    List<ICommand> getCommandHistory();

    IPlayer execute() throws Exception;


}
