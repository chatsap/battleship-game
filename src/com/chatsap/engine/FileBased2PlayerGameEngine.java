package com.chatsap.engine;

import com.chatsap.battleArea.BattleAreaBuilder;
import com.chatsap.battleShip.BattleShipBuilder;
import com.chatsap.command.CommandFactory;
import com.chatsap.command.ICommand;
import com.chatsap.missile.MissileBuilder;
import com.chatsap.player.IPlayer;
import com.chatsap.player.PlayerBuilder;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileBased2PlayerGameEngine implements IGameEngine {

    private final File file;
    private IPlayer player1 = null;
    private IPlayer player2 = null;

    private List<ICommand> commandHistory = new ArrayList<>();

    public FileBased2PlayerGameEngine(File file) {
        this.file = file;
    }

    @Override
    public List<ICommand> getCommandHistory(){
        return this.commandHistory;
    }

    @Override
    public IPlayer execute() throws Exception {
        setupPlayers();
        startGame();
        IPlayer winner = findWinner();
        return winner;
    }

    private IPlayer findWinner() {
        IPlayer winner = null;
        if(player1.isBattleAreaDestroyed()){
            if(!player2.isBattleAreaDestroyed()){
                winner = player2;
            }
        }else{
            if(player2.isBattleAreaDestroyed()){
                winner = player1;
            }
        }
        return winner;
    }

    private void setupPlayers() throws IOException {

        try (BufferedReader reader = new BufferedReader(new FileReader(file))){

            PlayerBuilder playerBuilderA = PlayerBuilder.getNewInstance()
                    .setName("1");
            PlayerBuilder playerBuilderB = PlayerBuilder.getNewInstance()
                    .setName("2");

            createBattleAreas(reader, playerBuilderA, playerBuilderB);
            createBattleShips(reader, playerBuilderA, playerBuilderB);
            createMissileSequence(reader, playerBuilderA);
            createMissileSequence(reader, playerBuilderB);

            player1 = playerBuilderA.build();
            player2 = playerBuilderB.build();
        }
    }

    private void createMissileSequence(BufferedReader reader , PlayerBuilder playerBuilder) throws IOException {
        String missileSequenceConfigString = reader.readLine();
        if (missileSequenceConfigString != null && missileSequenceConfigString.trim().length() != 0) {
            String[] missileConfigArr = missileSequenceConfigString.split(" ");
            for (int i = 0; i < missileConfigArr.length; i++) {
                String missileTargetCodeString = missileConfigArr[i];
                playerBuilder.addMissile(
                        MissileBuilder.getNewInstance()
                                .setTargetCordinateCode(missileTargetCodeString)
                                .build());
            }
        }
    }

    private void createBattleShips(BufferedReader reader, PlayerBuilder playerBuilderA, PlayerBuilder playerBuilderB) throws IOException {
        String noOfBattleShipsString = reader.readLine();
        if (noOfBattleShipsString != null && noOfBattleShipsString.trim().length() != 0) {
            int noOfBattleShips = Integer.parseInt(noOfBattleShipsString);

            if(noOfBattleShips < 1
                    || noOfBattleShips > playerBuilderA.getBattleArea().getBattleAreaHeight() * playerBuilderA.getBattleArea().getBattleAreaWidth() ){
                throw new RuntimeException("Total number of battleships exceed number of cells in battle area");
            }

            for (int i = 0; i < noOfBattleShips; i++) {
                String battleShipConfigLine = reader.readLine();
                if (battleShipConfigLine != null && battleShipConfigLine.trim().length() != 0) {
                    String[] configArray = battleShipConfigLine.trim().split(" ");
                    if (configArray.length != 5) {
                        throw new RuntimeException("Battleship config line in file is invalid");
                    } else {
                        playerBuilderA.addShip(
                                BattleShipBuilder.getNewInstance()
                                        .setType(configArray[0].trim())
                                        .setDimensions(configArray[1].trim(), configArray[2].trim())
                                        .setCorordinates(configArray[3].trim())
                                        .build());

                        playerBuilderB.addShip(
                                BattleShipBuilder.getNewInstance()
                                        .setType(configArray[0].trim())
                                        .setDimensions(configArray[1].trim(), configArray[2].trim())
                                        .setCorordinates(configArray[4].trim())
                                        .build());
                    }
                }
            }
        }
    }

    private void createBattleAreas(BufferedReader reader, PlayerBuilder playerBuilderA, PlayerBuilder playerBuilderB) throws IOException {
        String battleAreaDimensionLine = reader.readLine();

        playerBuilderA.setBattleArea(
                BattleAreaBuilder.getNewInstance()
                        .setDimensionCodes(battleAreaDimensionLine)
                        .build());

        playerBuilderB.setBattleArea(
                BattleAreaBuilder.getNewInstance()
                        .setDimensionCodes(battleAreaDimensionLine)
                        .build());
    }

    private List<ICommand> startGame() {
        IPlayer attacker = player1;
        IPlayer defender = player2;

        while (player1.hasMoreCommands()
                || player2.hasMoreCommands()) {

            if(player1.isBattleAreaDestroyed() || player2.isBattleAreaDestroyed()){
                break;
            }

            ICommand command = attacker.getNextCommand();
            if (command == null) {
                command = CommandFactory.newEmptyCommand(attacker);
            }
            command.setOpponent(defender);

            int result = command.execute();
            commandHistory.add(command);

            // If it's hit, switch attacker and defender
            if (result == 0) {
                IPlayer temp = attacker;
                attacker = defender;
                defender = temp;
            }
        }

        return commandHistory;
    }
}
