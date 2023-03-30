import GLOOP.*;

import java.io.File;

public class Coin {
    //Model coin;
    GLZylinder coin;
    private Ufo dasUfo;
    double coinPX;
    double coinPY;
    double coinPZ;
    double ufoPX;
    double ufoPY;
    double ufoPZ;

    boolean coinGeloescht = false;

    public Coin(Ufo pUfo) {
        //coin = new Model(Math.random() * 1001, -500 + Math.random() * 801, 1700 + Math.random() * 600, 10, 10, 10, new File("src/coin.stl"));
        coin= new GLZylinder (100+Math.random() * 601, -350 + Math.random() * 701, -1700 + Math.random() * 600,15,2);
        coin.skaliere(1, 1, 1);
        //coin.setzeFarbe(0, 1, 0);
        coin.setzeTextur("src/img/Coin.png");
        coin.drehe(0, 0, 0);

        dasUfo = pUfo;
    }

    public void coinbewegen() {
        coin.verschiebe(0, 0, 1);
        if (coin.gibZ() > 600) {

            coinGeloescht = true;
        }
    }

    public void loesche() {

        coin.loesche();
       coinGeloescht = true;
    }
    public boolean istCoinGeloescht(){
        return coinGeloescht;
    }
    public double gibX() {
        return coin.gibX();
    }

    public double gibY() {
        return coin.gibY();
    }

    public double gibZ() {
        return coin.gibZ();
    }

    public boolean collected() {
        ufoPX = dasUfo.gibX();
        ufoPY = dasUfo.gibY();
        ufoPZ = dasUfo.gibZ();
        coinPX = coin.gibX();
        coinPY = coin.gibY();
        coinPZ = coin.gibZ();
        double individuelleHitbox = 30;
        //Hier wird gecheckt, ob das Ufo den Coin berührt.
        if (((ufoPX < coinPX + individuelleHitbox) & (ufoPX > coinPX - individuelleHitbox)) & ((ufoPY < coinPY + individuelleHitbox) & (ufoPY > coinPY - individuelleHitbox)) & ((ufoPZ < coinPZ + individuelleHitbox+20) & (ufoPZ > coinPZ - individuelleHitbox))) {
            return true;
        } else {
            return false;
        }

    }
}
