package com.chatsap.shipDestroyer;


import com.chatsap.battleArea.IBattleArea;
import com.chatsap.missile.IMissile;

public interface IShipDestroyer {
    public int destroy(IBattleArea battleArea2D, IMissile missile);
}
