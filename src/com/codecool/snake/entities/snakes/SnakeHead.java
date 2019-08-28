package com.codecool.snake.entities.snakes;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.powerups.SimplePowerUp;

import javafx.geometry.Point2D;


public class SnakeHead extends GameEntity implements Interactable {
    private static final float turnRate = 2;
    private Snake snake;

    public SnakeHead(Snake snake, Point2D position) {
        this.snake = snake;
        setImage(Globals.getInstance().getImage("SnakeHeadDownRight"));
        setPosition(position);
    }

    public void updateRotation(SnakeControl turnDirection, double speed) {
        double headRotation = getRotate();

        if (turnDirection.equals(SnakeControl.TURN_LEFT)) {
            if (headRotation != 90) {
                setImage(Globals.getInstance().getImage("SnakeHeadUpLeft"));
                headRotation = 270;
            }
        }

        if (turnDirection.equals(SnakeControl.TURN_RIGHT)) {
            if (headRotation != 270) {
                setImage(Globals.getInstance().getImage("SnakeHeadDownRight"));
                headRotation = 90;
            }
        }
        if (turnDirection.equals(SnakeControl.TURN_UP)) {
            if (headRotation != 180) {
                setImage(Globals.getInstance().getImage("SnakeHeadDownRight"));
                headRotation = 0;
            }
        }
        if (turnDirection.equals(SnakeControl.TURN_DOWN)) {
            if (headRotation != 0) {
                setImage(Globals.getInstance().getImage("SnakeHeadUpLeft"));
                headRotation = 180;
            }
        }

        // set rotation and position
        setRotate(headRotation);
        Point2D heading = Utils.directionToVector(headRotation, speed);
        if (getX() + heading.getX() > 800) {
            setX(0);
        }
        else if (getX() + heading.getX() < 0) {
            setX(798);
        }
        else
        {
            setX(getX() + heading.getX());
        }
        if (getY() + heading.getY() > 600) {
            setY(0);
        }
        else if (getY() + heading.getY() < 0) {
            setY(600);
        }
        else
        {
            setY(getY() + heading.getY());
        }
    }

    @Override
    public void apply(GameEntity entity) {
        if(entity instanceof SimplePowerUp){
            System.out.println(getMessage());
            snake.addPart(1);
        }
        if(entity instanceof SnakeBody) {
            if (entity != snake.getBody().getList().get(0) && entity != snake.getBody().getList().get(1) )
            snake.changeHealth(-100);
        }
    }

    @Override
    public String getMessage() {
        return "IMMA SNAEK HED! SPITTIN' MAH WENOM! SPITJU-SPITJU!";
    }
}
