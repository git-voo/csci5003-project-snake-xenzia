
# Snake Xenzia Game

Snake Xenzia is a modern take on the classic Snake game. This version adds new features, including different types of meals that affect gameplay, walls that the snake can collide with, and a score and lives system displayed on a top panel.

## Features

- **Classic Snake Gameplay**: Navigate the snake to eat meals and grow longer without hitting the walls or yourself.
- **Walls**: Collision with the walls ends the game.
- **Meals**: Includes Regular Meals, Bonus Meals, Health Packs, and Danger Meals, each with different effects on the snake.
- **Top Panel**: Displays the current score, lives left, and the game level.

## Requirements

- Java Development Kit (JDK) 8 or higher
- An Integrated Development Environment (IDE) like IntelliJ IDEA, Eclipse, or NetBeans, or a text editor with Java support
- Apache Maven (Optional, if you use Maven for dependency management)

## How to Run the Game

### 1. Clone the Repository

First, clone the repository to your local machine using the following command:

```bash
git clone https://github.com/jsvoo/csci5003-project-snake-xenzia.git
cd snake-xenzia-game
cd src
```

### 2. Compile the Project

If you’re using an IDE, simply import the project and it should handle the compilation for you. If you’re using the command line, navigate to the project directory and run:

```bash
javac /*.java
```

### 3. Run the Game

Once compiled, you can start the game by running the following command:

```bash
java -cp SnakeGameGUI
```

### 4. Play the Game

Use the arrow keys to control the snake. Try to eat the meals to grow longer while avoiding the walls and yourself. The game ends if you collide with a wall or your own body.

### 5. Customization

You can modify the wall dimensions, meal types, and snake properties by editing the respective classes in the `src` directory. To change the appearance, update the methods inside the `paintComponent` method in `GamePanelGUI.java`.
