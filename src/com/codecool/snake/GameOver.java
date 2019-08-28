package com.codecool.snake;

import com.codecool.snake.Globals;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.Snake;


public class GameOver extends GameEntity{

    GameOver(){
        setImage(Globals.getInstance().getImage("GameOver"));
        double preX = 0;
        double preY = 0;
        setX(preX);
        setY(preY);

    }


}