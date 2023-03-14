import GLOOP.*;

import java.io.File;

public class Coin {
    Model coin;
    private Ufo dasUfo;
    double coinPX;
    double coinPY;
    double coinPZ;
    double ufoPX;
    double ufoPY;
    double ufoPZ;

    public Coin(Ufo pUfo) {
        coin = new Model(Math.random() * 1001, -500 + Math.random() * 801, 1700 + Math.random() * 600, 10, 10, 10, new File("src/coin.stl"));
        coin.skaliere(1, 1, 1);
        coin.setzeFarbe(0, 1, 0);
        coin.drehe(0, 0, 0);

        dasUfo = pUfo;
    }

    public void coinbewegen() {
        coin.verschiebe(0, 0, 1);
        if (coin.gibZ() > 600) {
            coin.setzePosition(100 + Math.random() * 701, -400 + Math.random() * 701, -1700 + Math.random() * 600);
        }
    }

    public void coinZuruecksetzen() {

        coin.setzePosition(Math.random() * 1001, -500 + Math.random() * 801, -1700 + Math.random() * 600);

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
        double individuelleHitbox = 10;
        //Hier wird gecheckt, ob das Ufo ein Asteroiden berührt.
        if (((ufoPX < coinPX + individuelleHitbox) & (ufoPX > coinPX - individuelleHitbox)) & ((ufoPY < coinPY + individuelleHitbox) & (ufoPY > coinPY - individuelleHitbox)) & ((ufoPZ < coinPZ + individuelleHitbox) & (ufoPZ > coinPZ - individuelleHitbox))) {
            return true;
        } else {
            return false;
        }

    }
}
