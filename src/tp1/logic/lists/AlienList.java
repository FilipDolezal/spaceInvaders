package tp1.logic.lists;

import tp1.logic.gameobjects.Alien;

public class AlienList {
    public Alien[] aliens;
    public final int initSize;

    public AlienList(int initSize) {
        this.initSize = initSize;
        this.aliens = new Alien[initSize];
    }

    public void remove(Alien alien){
        Alien[] newArr = new Alien[aliens.length-1];
        for(int i = 0, x = 0; i < aliens.length; i++) {
            if(aliens[i].equals(alien)) continue;
            newArr[x++] = aliens[i];
        }
        this.aliens = newArr;
    }
}
