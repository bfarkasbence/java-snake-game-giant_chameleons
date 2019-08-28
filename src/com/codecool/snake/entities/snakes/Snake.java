package com.codecool.snake.entities.snakes;

import com.codecool.snake.DelayedModificationList;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.eventhandler.InputHandler;
import com.codecool.snake.Game;

import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;

import java.security.Timestamp;


public class Snake implements Animatable {
    private static final double speed = 3;
    private int health = 100;

    private SnakeHead head;
    private DelayedModificationList<GameEntity> body;
    private SnakeControl savedTurn;


    public Snake(Point2D position) {
        head = new SnakeHead(this, position);
        body = new DelayedModificationList<>();

        addPart(2);
    }

    public DelayedModificationList<GameEntity> getBody() {
        return body;
    }

    public void step() {
        savedTurn = getUserInput();
        SnakeControl turnDir;
        if ((savedTurn == SnakeControl.TURN_DOWN ||
                savedTurn == SnakeControl.TURN_UP) &&
                (int)Math.round(head.getPosition().getX()) % 39 == 0 ) {
            turnDir = savedTurn;
        }
        else if ((savedTurn == SnakeControl.TURN_LEFT ||
                savedTurn == SnakeControl.TURN_RIGHT )&&
                (int)Math.round(head.getPosition().getY()) % 39 == 0 ) {
            turnDir = savedTurn;
        }
        else {
            turnDir = SnakeControl.INVALID;
        }

        head.updateRotation(turnDir, speed);

        updateSnakeBodyHistory();
        checkForGameOverConditions();

        body.doPendingModifications();
    }

    private SnakeControl getUserInput() {
        SnakeControl turnDir = savedTurn;
        if(InputHandler.getInstance().isKeyPressed(KeyCode.LEFT)) turnDir = SnakeControl.TURN_LEFT;
        if(InputHandler.getInstance().isKeyPressed(KeyCode.RIGHT)) turnDir = SnakeControl.TURN_RIGHT;
        if(InputHandler.getInstance().isKeyPressed(KeyCode.DOWN)) turnDir = SnakeControl.TURN_DOWN;
        if(InputHandler.getInstance().isKeyPressed(KeyCode.UP)) turnDir = SnakeControl.TURN_UP;

        return turnDir;
    }

    public void addPart(int numParts) {
        GameEntity parent = getLastPart();
        Point2D position = parent.getPosition();

        for (int i = 0; i < numParts; i++) {
            SnakeBody newBodyPart = new SnakeBody(position);
            body.add(newBodyPart);
        }
        Globals.getInstance().display.updateSnakeHeadDrawPosition(head);
    }

    public void changeHealth(int diff) {
        health += diff;
    }

    private void checkForGameOverConditions() {
        if (head.isOutOfBounds() || health <= 0) {
            System.out.println("Game Over");
            Game.spawnGameOver();
            Globals.getInstance().stopGame();
        }
    }

    private void updateSnakeBodyHistory() {
        GameEntity prev = head;
        for(GameEntity currentPart : body.getList()) {
            if (currentPart.getX() == prev.getX() || currentPart.getY() == prev.getY()){
                if (currentPart.getX() < prev.getX() || currentPart.getY() > prev.getY()){
                    currentPart.setImage(Globals.getInstance().getImage("SnakeBodyUpLeft"));
                }
                if (currentPart.getX() > prev.getX() || currentPart.getY() < prev.getY()){
                    currentPart.setImage(Globals.getInstance().getImage("SnakeBodyDownRight"));
                }
                currentPart.setRotate(prev.getRotate());
            }

            currentPart.setPosition(prev.getPosition());
            prev = currentPart;
        }
    }



    private GameEntity getLastPart() {
        GameEntity result = body.getLast();

        if(result != null) return result;
        return head;
    }
}
