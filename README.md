# 🎮 Tic Tac Toe - Clean OOP Java Edition

A modular, extensible, and professional-grade CLI implementation of Tic Tac Toe written in Java. Designed with clean architecture, separation of concerns, and extensibility in mind — perfect for plugging in an AI player, GUI, or network layer later.

---

## 📦 Features

- ✅ Clean OOP design (no static spaghetti)
- 🧠 Strategy Pattern for swappable players (Human, AI, etc.)
- 🎨 Console-based Renderer (easy to replace with GUI/REST)
- 🏁 Game flow controller with proper game state management
- 🚫 Input validation and safe error handling
- 🔧 Easily extendable to support:
  - AI players (`MinimaxPlayer`)
  - JavaFX GUI / WebSockets
  - Multiplayer (via REST APIs)

---

## 📁 Project Structure

```

TicTacToeApp.java       // Entry point
Board.java              // Game board logic
Player.java             // Strategy interface
HumanPlayer.java        // CLI-based human player
Move.java               // Immutable move object
ConsoleRenderer.java    // Renders board to console
Game.java               // Game loop & controller

````

---

## 🏃 Getting Started

### 🛠 Requirements
- Java 17+
- Any IDE or terminal

### ▶️ Run the game

```bash
javac *.java
java TicTacToeApp
````

---

## 🤖 Extend the Game (Suggestions)

| Task              | How-To Idea                                 |
| ----------------- | ------------------------------------------- |
| Add AI            | Create `MinimaxPlayer implements Player`    |
| GUI Version       | Swap `ConsoleRenderer` with JavaFX or Swing |
| REST Multiplayer  | Replace `HumanPlayer` input with API calls  |
| Undo/Redo Feature | Add move history to `Board`                 |
| Unit Testing      | Use JUnit and mock `Player` input           |

---

## 📚 License

MIT License. Use it, extend it, ship it. 🚀




