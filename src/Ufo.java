import GLOOP.*;

public class Ufo {
    GLTorus ufo;

    public Ufo() {

        ufo = new GLTorus(600, 0, 30, 20, 5);
       // ufo.setzeTextur("src/img/Rocket Bild.png");
        ufo.setzeFarbe(0,0,0);
        ufo.drehe(90,0,0);
    }

    public void bewegeLinks() {
        ufo.verschiebe(-1, 0, 0);
    }

    public void bewegeRechts() {
        ufo.verschiebe(1, 0, 0);
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
