package com.haanhgs.game.models;

public interface GameListener {
    void gameStart(Repo repo);
    void gameShapeLand(Repo repo);
    void gameEnd(Repo repo);
    void gameShapeDrop(Repo repo);
    void gameLevelUp(Repo repo);
}
