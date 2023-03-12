import GLOOP.*;

import java.io.File;

public class Coin {
   Model coin;
    public Coin () {
        coin = new Model(Math.random() * 1001,-500+Math.random() * 801,1700+Math.random() * 600,0,0,0,new File("src/coin.stl"));
        coin.skaliere(1,1,1);
        coin.setzeFarbe(0,1,0);
        coin.drehe(0,0,0);


    }
    public void coinbewegen() {
        coin.verschiebe(0, 0, 1);
        if (coin.gibZ() > 600) {
            coin.setzePosition(100+Math.random() * 701, -400 + Math.random() * 701, -1700 + Math.random() * 600);
          }
        }
    public void coinZuruecksetzen(){

        coin.setzePosition(Math.random() * 1001,-500+Math.random() * 801,-1700+Math.random() * 600);

    }
    public double gibX(){
        return coin.gibX();
    }

    public double gibY(){
        return coin.gibY();
    }
    public double gibZ(){
        return coin.gibZ();
    }
}
