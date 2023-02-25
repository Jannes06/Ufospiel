import GLOOP.*;

public class Ufo {
    GLQuader ufo;

    public Ufo() {

        ufo = new GLQuader(600, 0, 0, 20, 40, 0.5);
        ufo.setzeTextur("src/img/Rocket Bild.png");
        ufo.drehe(0,0,180);
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

    public void explosion(){
      ufo.setzeTextur("src/img/Explosion für Ufo.png");
    }

    public void ufoZuruecksetzen(){
       ufo.setzePosition(500,-200,0) ;
        ufo.setzeTextur("src/img/Rocket Bild.png");
    }

}
