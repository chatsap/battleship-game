package com.chatsap.command;

import com.chatsap.missile.IMissile;
import com.chatsap.player.IPlayer;

public class CommandFactory {
    public static ICommand newLaunchCommand(IPlayer player, IMissile missile) {
        return new LaunchMissileCommand(player, missile);
    }

    public static ICommand newEmptyCommand(IPlayer player) {
        return new EmptyCommand(player);
    }
}
