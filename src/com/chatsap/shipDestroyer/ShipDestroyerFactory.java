package com.chatsap.shipDestroyer;

import com.chatsap.missile.MissileTypes;

public class ShipDestroyerFactory {
    public static IShipDestroyer getDestroyer(MissileTypes type) {
        if(type.equals(MissileTypes.TYPE_ALPHA)){
            return new AlphaMissileDestroyer();
        }
        return new RegularMissileDestroyer();
    }
}
