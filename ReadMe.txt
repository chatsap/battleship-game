

Class level design:
===================
    1. BattleArea:
        - Battle area encapsulates the logic to installing ships in the area
        - IBattleArea is extended by BattleArea2D
        - BattleAreaBuilder encapsulates the logic to build battle area

    2. BattleShip:
        - Battle ship encapsulates details of a battle ship like dimension, type, position etc.
        - IBattleShip is extended by BattleShip2D
        - BattleShipBuilder encapsulates the logic to build a given battles ship

    3. Missile:
        - Missile encapsulates details like target coordinates etc.
        - IMissile is extended by Missile2D
        - MissileBuilder encapsulates the logic to build a missile

    4. Command:
        - Command encapsulates  details of a command given by originator and the missile attached the to the command.
        - Command also contains the result of execution as state
        - If player is not able to provide a command, game engine creates empty command
        - Commands are stored in history. This helps to track path of execution
            and also provided "undo" opportunity if required in future.
        - ICommand is extended by LaunchMissileCommand and EmptyCommand
        - CommandFactory encapsulates the logic to construct an object of any command class

    5. Player:
        - IPlayer is extended by Player
        - PlayerBuilder encapsulates the logic to build a player

    6. GameEngine
        - Game engine is responsible for enforcing rules of the game
        - IGameEngine is extended by FileBased2PlayerGameEngine
        - In future we can have a different implementation for interactive game engine
