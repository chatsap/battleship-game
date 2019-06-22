package com.chatsap.command;

import com.chatsap.player.IPlayer;


public interface ICommand {
    void setOpponent(IPlayer opponent);

    int execute();

    String getResultString();
}
