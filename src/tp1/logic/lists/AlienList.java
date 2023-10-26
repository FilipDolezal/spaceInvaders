package tp1.logic.lists;

import tp1.logic.gameobjects.Alien;

public class AlienList {
    public Alien[] aliens;
    public int num;

    public AlienList(int num) {
        this.num = num;
        this.aliens = new Alien[num];
    }

    public void remove(Alien alien){
        for(int i = 0; i < aliens.length; i++) {
            if(aliens[i] == null) continue;
            if (aliens[i].equals(alien)) {
                aliens[i] = null;
                num--;
            }
        }
    }
}
