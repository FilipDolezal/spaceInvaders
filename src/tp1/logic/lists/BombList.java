package tp1.logic.lists;

import tp1.logic.gameobjects.Bomb;

public class BombList {

    public Bomb[] bombList;
    public int numBombs;
    //NumBombs is the size of the array of bombs which is equal to the number of destroyers(?)
    public BombList(int numBombs){
        this.numBombs = numBombs;
        this.bombList = new Bomb[numBombs];
    }
    public Bomb[] getBombList() {
        return bombList;
    }

    public void setBombList(Bomb[] bombList) {
        this.bombList = bombList;
    }
}
