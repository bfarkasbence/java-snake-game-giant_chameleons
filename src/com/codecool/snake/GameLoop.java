package com.codecool.snake;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.powerups.ExtraPowerUp;
import com.codecool.snake.entities.powerups.SimplePowerUp;
import com.codecool.snake.entities.snakes.Snake;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class GameLoop {
    private Snake snake;
    private boolean running = false;
    private int simplePowerUpCounter = 0;

    public GameLoop(Snake snake) { this.snake = snake; }

    public void start() {
        running = true;
    }

    public void stop() {
        running = false;
    }

    public void step() {
        snake.step();
        int numberOfPowerUps = 0;
        for (GameEntity gameObject : Globals.getInstance().display.getObjectList()) {
            if (gameObject instanceof Animatable) {
                ((Animatable) gameObject).step();
            }
            if (gameObject instanceof Interactable) {
                if (gameObject instanceof SimplePowerUp) {
                    numberOfPowerUps = 1;
                }
            }
        }
        checkCollisions();
        if (numberOfPowerUps < 1) {
            simplePowerUpCounter++;
            new SimplePowerUp();
            if(simplePowerUpCounter % 5 == 0){
                new ExtraPowerUp();
            }

        }
        Globals.getInstance().display.frameFinished();
    }

    private void checkCollisions() {
        List<GameEntity> gameObjs = Globals.getInstance().display.getObjectList();
        for (int idxToCheck = 0; idxToCheck < gameObjs.size(); ++idxToCheck) {
            GameEntity objToCheck = gameObjs.get(idxToCheck);
            if (objToCheck instanceof Interactable) {
                for (int otherObjIdx = idxToCheck + 1; otherObjIdx < gameObjs.size(); ++otherObjIdx) {
                    GameEntity otherObj = gameObjs.get(otherObjIdx);
                    if (otherObj instanceof Interactable){
                        if(objToCheck.getBoundsInParent().intersects(otherObj.getBoundsInParent())){
                            ((Interactable) objToCheck).apply(otherObj);
                            ((Interactable) otherObj).apply(objToCheck);
                        }
                    }
                }
            }
        }
    }
}
