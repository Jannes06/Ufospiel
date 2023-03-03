import GLOOP.*;

import java.io.File;

public class Ufo {
    Model ufo;

    public Ufo() {

        ufo = new Model(0,0,0,0,0,0,new File("src/m-falcon.stl"));
       // ufo.setzeTextur("src/img/Rocket Bild.png");
        ufo.skaliere(0.4,0.4,0.4);
        ufo.setzeFarbe(0,0,0);
        ufo.drehe(0,0,0);
    }

    public void bewegeLinks() {
        ufo.verschiebe(-1, 0, 0);
        ufo.setzeDrehung(0,0,15);

    }public void DrehungZuruecksetzen(){
        ufo.setzeDrehung(0,0,0);
    }

    public void bewegeRechts() {
        ufo.verschiebe(1, 0, 0);
        ufo.setzeDrehung(0,0,-15);

    }

    public void bewegeOben() {
        ufo.verschiebe(0, 1, 0);
    }

    public void bewegeUnten() {
        ufo.verschiebe(0, -1, 0);
    }

    public double gibX() {
        return ufo.gibX();
    }

    public double gibY() {
        return ufo.gibY();
    }
    public double gibZ() {
        return ufo.gibZ();
    }

    public void explosion(){
      //ufo.setzeTextur("src/img/Explosion für Ufo.png");
        ufo.setzeFarbe(1,0,0);
    }

    public void ufoZuruecksetzen(){
       ufo.setzePosition(500,-200,0) ;
       // ufo.setzeTextur("src/img/Rocket Bild.png");
       ufo.setzeFarbe(0,0,0);
    }

}
