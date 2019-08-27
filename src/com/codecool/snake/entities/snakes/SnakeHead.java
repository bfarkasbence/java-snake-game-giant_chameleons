package com.codecool.snake.entities.snakes;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.enemies.Enemy;
import com.codecool.snake.entities.powerups.SimplePowerUp;

import javafx.geometry.Point2D;


public class SnakeHead extends GameEntity implements Interactable {
    private static final float turnRate = 2;
    private Snake snake;

    public SnakeHead(Snake snake, Point2D position) {
        this.snake = snake;
        setImage(Globals.getInstance().getImage("SnakeHead"));
        setPosition(position);
    }

    public void updateRotation(SnakeControl turnDirection, double speed) {
        double headRotation = getRotate();

        if (turnDirection.equals(SnakeControl.TURN_LEFT)) {
            if (headRotation != 90) {
                headRotation = 270;
            }
        }

        if (turnDirection.equals(SnakeControl.TURN_RIGHT)) {
            if (headRotation != 270) {
                headRotation = 90;
            }
        }
        if (turnDirection.equals(SnakeControl.TURN_UP)) {
            if (headRotation != 180) {
                headRotation = 0;
            }
        }
        if (turnDirection.equals(SnakeControl.TURN_DOWN)) {
            if (headRotation != 0) {
                headRotation = 180;
            }
        }

        // set rotation and position
        setRotate(headRotation);
        Point2D heading = Utils.directionToVector(headRotation, speed);
        setX(getX() + heading.getX());
        setY(getY() + heading.getY());
    }

    @Override
    public void apply(GameEntity entity) {
        if(entity instanceof Enemy){
            System.out.println(getMessage());
            snake.changeHealth(((Enemy) entity).getDamage());
        }
        if(entity instanceof SimplePowerUp){
            System.out.println(getMessage());
            snake.addPart(1);
        }
    }

    @Override
    public String getMessage() {
        return "IMMA SNAEK HED! SPITTIN' MAH WENOM! SPITJU-SPITJU!";
    }
}
