package com.chatsap.player;

import com.chatsap.battleArea.IBattleArea;
import com.chatsap.battleShip.IBattleShip;
import com.chatsap.command.CommandFactory;
import com.chatsap.command.ICommand;
import com.chatsap.missile.IMissile;
import jdk.nashorn.internal.objects.AccessorPropertyDescriptor;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class PlayerBuilder {

    private IBattleArea battleArea;
    private List<IBattleShip> ships;
    private String name;
    private Queue<IMissile> missiles = new LinkedList<>();

    public static PlayerBuilder getNewInstance() {
        return new PlayerBuilder();
    }


    public PlayerBuilder setBattleArea(IBattleArea battleArea){
        this.battleArea = battleArea;
        return this;
    }

    public IBattleArea getBattleArea(){
        return this.battleArea;
    }

    public PlayerBuilder setShips(List<IBattleShip> ships){
        this.ships = ships;
        return this;
    }

    public PlayerBuilder addShip(IBattleShip ship){
        if(ships == null){
            ships = new ArrayList<>();
        }
        ships.add(ship);
        return this;
    }

    public PlayerBuilder setName(String name){
        this.name = name;
        return this;
    }

    public PlayerBuilder addMissile(IMissile missile){
        missiles.add(missile);
        return this;
    }

    public IPlayer build(){
        validateInputs();

        Player player = new Player(this.name);
        addBattleArea(player);
        addShips();
        addLaunchCommands(player);

        return player;
    }

    private void addBattleArea(Player player) {
        player.addBattleArea(this.battleArea);
    }

    private void addShips() {
        if(ships!=null) {
            for (IBattleShip battleShip : ships) {
                this.battleArea.installShip(battleShip);
            }
        }
    }

    private void addLaunchCommands(Player player) {
        Queue<ICommand> commands = new LinkedList<>();
        if(missiles != null) {
            for (IMissile missile : missiles) {
                commands.add(CommandFactory.newLaunchCommand(player, missile));
            }
        }
        player.addCommands(commands);

    }

    private void validateInputs() {
        if(this.name == null || this.name.trim().length() == 0){
            throw new RuntimeException("Player name is empty");
        }

        if(this.battleArea == null ){
            throw new RuntimeException("Battle area is not set for player");
        }

    }
}
