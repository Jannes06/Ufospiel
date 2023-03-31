import GLOOP.*;

import java.io.File;

public class Ufo {
    Model ufo;
    GLTafel schild,explosion;
    double ufoPX;
    double ufoPY;
    double ufoPZ;

    public Ufo() {

        ufo = new Model(0, 0, 0, 0, 0, 0, new File("src/t-fighter (1).stl"));
        ufo.skaliere(0.01, 0.01, 0.01);
        ufo.setzeFarbe(1, 0, 0);
        ufo.drehe(0, 0, 0);
        schild = new GLTafel(500,-50,-200,10,10);
        schild.setzeTextur("src/img/SchildBubble.png");
        schild.setzeSichtbarkeit(false);
        explosion = new GLTafel(500,-50,-200,20,20);
        explosion.setzeTextur("src/img/Explosion.png");
        explosion.setzeSichtbarkeit(true);
    }


    public void schildsichtbar(){
        schild.setzeSichtbarkeit(true);
    }
    public void schildunsichtbar(){
        schild.setzeSichtbarkeit(false);
    }
    public void bewegeLinks() {
        ufo.verschiebe(-1, 0, 0);
        ufo.setzeDrehung(0, 0, 15);
        schild.verschiebe(-1, 0, 0);
        schild.setzeDrehung(0, 0, 15);
        explosion.verschiebe(-1, 0, 0);
        explosion.setzeDrehung(0, 0, 15);
    }
    public void autopilotLinks(){
        ufo.verschiebe(-0.35, 0, 0);
        ufo.setzeDrehung(0, 0, 15);
        schild.verschiebe(-0.35, 0, 0);
        schild.setzeDrehung(0, 0, 15);
        explosion.verschiebe(-0.35, 0, 0);
        explosion.setzeDrehung(0, 0, 15);
     }
    public void autopilotRechts(){
        ufo.verschiebe(0.35, 0, 0);
        ufo.setzeDrehung(0, 0, -15);
        schild.verschiebe(0.35, 0, 0);
        schild.setzeDrehung(0, 0, -15);
        explosion.verschiebe(0.35, 0, 0);
        explosion.setzeDrehung(0, 0, -15);
    }
    public void autopilotOben() {
        ufo.verschiebe(0, 0.7, 0);
        ufo.setzeDrehung(10, 0, -15);
        schild.verschiebe(0, 0.7, 0);
        schild.setzeDrehung(10, 0, -15);
        explosion.verschiebe(0, 0.7, 0);
        explosion.setzeDrehung(10, 0, -15);
    }
    public void autopilotUnten() {
        ufo.verschiebe(0, -0.7, 0);
        ufo.setzeDrehung(-10, 0, -15);
        schild.verschiebe(0, -0.7, 0);
        schild.setzeDrehung(-10, 0, -15);
        explosion.verschiebe(0, -0.7, 0);
        explosion.setzeDrehung(-10, 0, -15);

    }
    public void DrehungZuruecksetzen() {
        ufo.setzeDrehung(0, 0, 0);
        schild.setzeDrehung(0, 0, 0);
    }

    public void bewegeRechts() {
        ufo.verschiebe(1, 0, 0);
        ufo.setzeDrehung(0, 0, -15);
        schild.verschiebe(1, 0, 0);
        schild.setzeDrehung(0, 0, -15);
        explosion.verschiebe(1, 0, 0);
        explosion.setzeDrehung(0, 0, -15);
    }

    public void bewegeOben() {
        ufo.verschiebe(0, 1, 0);
        ufo.setzeDrehung(10, 0, 0);
        schild.verschiebe(0, 1, 0);
        schild.setzeDrehung(10, 0, 0);
        explosion.verschiebe(0, 1, 0);
        explosion.setzeDrehung(10, 0, 0);
    }

    public void bewegeUnten() {
        ufo.verschiebe(0, -1, 0);
        ufo.setzeDrehung(-10, 0, 0);
        schild.verschiebe(0, -1, 0);
        schild.setzeDrehung(-10, 0, 0);
        explosion.verschiebe(0, -1, 0);
        explosion.setzeDrehung(-10, 0, 0);
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

    public void explosion() {
        //ufo.setzeTextur("src/img/Explosion für Ufo.png");

        explosion.setzeSichtbarkeit(true);
        Sys.warte(100);
        explosion.skaliere(1.1,1.1,1);
        Sys.warte(100);
        explosion.skaliere(1.15,1.15,1);
        Sys.warte(100);
        explosion.skaliere(1.2,1.2,1);
        Sys.warte(100);
        explosion.skaliere(1.25,1.25,1);
        Sys.warte(100);
        explosion.skaliere(1.3,1.3,1);
        ufo.setzeSichtbarkeit(false);
        Sys.warte(100);
        explosion.skaliere(1.3,1.3,1);
        Sys.warte(100);
        explosion.skaliere(1.3,1.3,1);
        Sys.warte(100);
        explosion.skaliere(1.3,1.3,1);
        Sys.warte(100);


    }


    public void ufoZuruecksetzen() {
        ufo.setzePosition(500, 0, 0);
        // ufo.setzeTextur("src/img/Rocket Bild.png");
        ufo.setzeFarbe(1, 1, 1);
        schild.setzePosition(500,0,0);
        explosion.setzePosition(500,0,0);
        explosion.setzeSichtbarkeit(false);
        explosion.setzeSkalierung(1,1,1);
        ufo.setzeSichtbarkeit(true);
    }

}
