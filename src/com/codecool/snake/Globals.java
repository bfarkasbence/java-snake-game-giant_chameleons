package com.codecool.snake;

import com.codecool.snake.resources.Resources;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

// class for holding all static stuff
public class Globals {
    private static Globals instance = null;

    public static final double WINDOW_WIDTH = 800;
    public static final double WINDOW_HEIGHT = 600;

    public Display display;
    public Game game;

    private GameLoop gameLoop;
    private Resources resources;





    public static Globals getInstance() {
        if(instance == null) instance = new Globals();
        return instance;
    }

    public void setGameLoop(GameLoop gameLoop) {
        this.gameLoop = gameLoop;
    }

    public void setupResources() {
        resources = new Resources();
        resources.addImage("SnakeHead", new Image("snake_head.png"));
        resources.addImage("SnakeHeadUpLeft", new Image("snake_head_up_left.png"));
        resources.addImage("SnakeHeadDownRight", new Image("snake_head_down_right.png"));
        resources.addImage("SnakeBody", new Image("snake_body.png"));
        resources.addImage("SnakeBodyUpLeft", new Image("snake_body_up_left.png"));
        resources.addImage("SnakeBodyDownRight", new Image("snake_body_down_right.png"));
        resources.addImage("SimpleEnemy", new Image("simple_enemy.png"));
        resources.addImage("PowerUpBerry", new Image("powerup_berry.png"));
        resources.addImage("GameOver", new Image("gamover.png"));
        resources.addImage("PowerUpBerry", new Image("simple_power_up.png"));
        resources.addImage("Background", new Image("background.png"));
        resources.addImage("ExtraBerry1", new Image("extra_berry1.png"));
        resources.addImage("ExtraBerry2", new Image("extra_berry2.png"));
        resources.addImage("ExtraBerry3", new Image("extra_berry3.png"));
        resources.addImage("ExtraBerry4", new Image("extra_berry4.png"));
    }

    public Image getImage(String name) { return resources.getImage(name); }

    public void startGame() { gameLoop.start(); }

    public void stopGame() { gameLoop.stop(); }

    private Globals() {
        // singleton needs the class to have private constructor
    }
}
