package com.chatsap.command;

import com.chatsap.player.IPlayer;

class EmptyCommand implements ICommand {

    private final IPlayer originator;
    private IPlayer opponent;

    EmptyCommand(IPlayer originator){
        this.originator = originator;
    }

    @Override
    public void setOpponent(IPlayer opponent) {
        this.opponent = opponent;
    }

    @Override
    public int execute() {
        return 0;
    }

    @Override
    public String getResultString() {
        StringBuffer strBuff = new StringBuffer();

        strBuff.append(
                String.format(
                        "Player-%s has no more missiles to fire",
                        this.originator.getName()));

        return strBuff.toString();
    }
}
