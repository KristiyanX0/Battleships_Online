# Battleships Online

## Project Overview
Battleships Online is a networked implementation of the classic Battleships game. This project allows players to play the game over a network using a client-server architecture.

## Architecture

### Server Components
- **Server**: Handles client connections and processes game commands
- **CommandExecutor**: Interprets player commands and manages game state
- **BattleshipsAPI**: Main game engine that coordinates game flow and rules

### Game Components
- **Board**: Represents the game board where ships are placed and tracked
- **Matrix**: Handles the visual representation of the game board
- **Ship**: Abstract class representing ship functionality with concrete implementations:
  - Carrier (5 spaces)
  - Battleship (4 spaces)
  - Destroyer (3 spaces)
  - Submarine (2 spaces)
- **Position**: Represents coordinates on the game board

## How to Play

### Starting the Game
1. Start the server by running `ServerStart`
2. Connect players by running `Client1` and `Client2`

### Game Commands
- `create game [name]`: Create a new game
- `create profile [name]`: Create a new player profile
- `login [name]`: Login with existing profile
- `join [game]`: Join an existing game
- `saved`: View saved games
- `load [game]`: Load a saved game
- `delete game [name]`: Delete a saved game
- `delete profile [name]`: Delete a player profile
- `score`: View scoreboard

## Technical Details

### Network Communication
The game uses Java NIO for non-blocking network communication:
- ServerSocketChannel for accepting connections
- SocketChannel for client communication
- Selector for managing multiple client connections

### Game Logic
- Ships are placed on a 10x10 grid
- Each ship occupies multiple cells depending on its type
- Players take turns firing at positions on the opponent's board
- The game tracks hits and misses, and determines when ships are sunk

### File Structure
- `src/game`: Core game logic
- `src/io`: Network communication
- `src/command`: Command processing
- `src/files`: Game resources
- `src/operations`: Utility operations
- `src/exception`: Custom exceptions

## Development
This project is implemented in Java and utilizes:
- Object-oriented design with inheritance (Ship hierarchy)
- Functional programming with Java Streams
- NIO for network programming
- Command pattern for user input handling
