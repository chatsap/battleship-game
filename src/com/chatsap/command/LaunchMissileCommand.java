package com.chatsap.command;

import com.chatsap.common.BattleShipConstants;
import com.chatsap.missile.IMissile;
import com.chatsap.player.IPlayer;


class LaunchMissileCommand implements ICommand {
    private IPlayer originator;
    private IPlayer opponent;
    private IMissile missile;
    private int result;

    LaunchMissileCommand(IPlayer originator, IMissile missile) {
        this.originator = originator;
        this.missile = missile;
    }

    @Override
    public void setOpponent(IPlayer opponent) {
        this.opponent = opponent;
    }

    @Override
    public int execute() {
        if (opponent == null) {
            throw new RuntimeException("Opponent player is not set");
        }

        if (missile == null) {
            this.result = BattleShipConstants.NO_MISSILES_LEFT;
        } else {
            this.result = opponent.receiveMissile(this.missile);
        }

        return this.result;

    }

    @Override
    public String getResultString() {
        StringBuffer strBuff = new StringBuffer();

        strBuff.append(
                String.format(
                        "Player-%s fires a missile with target %s which got %s",
                        this.originator.getName(),
                        this.missile.getTargetCode(),
                        this.result == 0 ? "miss" : "hit"));

        return strBuff.toString();
    }
}
