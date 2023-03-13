import GLOOP.*;

public class Ufospiel {
    private GLKamera kamera;
    private GLLicht licht;
    private GLTastatur tastatur;
    //private GLHimmel himmel;
    private GLQuader hintergrund;
    private Ufo dasUfo;
    private GLTafel testTafel;
    double asteroidPX;
    double asteroidPY;
    double asteroidPZ;
    double ufoPX;
    double ufoPY;
    double ufoPZ;
    double milisek = 0;
    int rundenNR = 1;
    int backsetzer  =0;

    int asteroidenAnzahl = 500;
    private Asteroid[] asteroiden;

    public Ufospiel() {
        kamera = new GLKamera();
        kamera.verschiebe(600, 40, 0);

        licht = new GLLicht();
        tastatur = new GLTastatur();
        //himmel = new GLHimmel("src/img/8k Sterne2.jpg");


        hintergrund = new GLQuader(600, 0, -2000, 8000, 4000, 1);
       // hintergrund.setzeTextur("src/img/Sterne.jpg");

        dasUfo = new Ufo();
        testTafel = new GLTafel(1300, -500, 0, 50, 50);

        asteroiden = new Asteroid[asteroidenAnzahl];
        for (int i = 0; i < asteroidenAnzahl; i++) {
            asteroiden[i] = new Asteroid(dasUfo,Math.random() * 30 + 10);
        }

        while (0 == 0) {
            ausfuehrung();
        }
    }

    public void ausfuehrung() {
        ufobewegung();
        asteroidbewegung();
        crash();
        pause();
        rundenanzahl();
        kameraFolge();
        Sys.warte();
    }
    public void ufobewegung() {

        //Bewegung nach links
        if (tastatur.links()) {
            dasUfo.bewegeLinks();
            backsetzer = 1;
        }
        //GegenbewegungLinksRand
        if (dasUfo.gibX() < 100) {
            dasUfo.bewegeRechts();
        }
        //Bewegung nach rechts
        if (tastatur.rechts()) {
            dasUfo.bewegeRechts();
            backsetzer = 1;
        }


        //GegenbewegungRechtsRand
        if (dasUfo.gibX() > 900) {
            dasUfo.bewegeLinks();
        }
        //Bewegung nach oben
        if (tastatur.oben()) {
            dasUfo.bewegeOben();
            backsetzer= 1;
        }
        //GegenbewegungObenRand
        if (dasUfo.gibY() > 350) {
            dasUfo.bewegeUnten();
        }
        //Bewegung nach unten
        if (tastatur.unten()) {
            dasUfo.bewegeUnten();
            backsetzer = 1;
        }
        //GegenbewegungUntenRand
        if (dasUfo.gibY() < -350) {
            dasUfo.bewegeOben();
        }
        if ((!tastatur.rechts()) && (!tastatur.links())&&(!tastatur.oben())&&(!tastatur.unten())) {
            backsetzer= 0;

        }
        if (backsetzer==0 ) {
         dasUfo.DrehungZuruecksetzen();
        }
    }
    public void asteroidbewegung() {
        for (int i = 0; i < asteroidenAnzahl; i++) {
            asteroiden[i].fallen();
        }
    }
    public void asteroidKoordinatenTest() {
        for (int i = 0; i < asteroidenAnzahl; i++) {
            asteroidPX = asteroiden[i].gibX();
            testTafel.setzeText("" + asteroidPX, 50);
        }
    }
    public void rundenanzahl() {
        milisek = milisek + 1;
        //Level 1

        if (milisek == 1) {
            hintergrund.setzeTextur("src/img/Sterne.jpg");
            rundenNR = 1;
            dasUfo.ufoZuruecksetzen();
            for (int i = 0; i < asteroidenAnzahl; i++) {
               asteroiden[i].asteroidZuruecksetzen();
               asteroiden[i].level1();
            }
        }
        //Level 2
        if (milisek == 10000) {
            hintergrund.setzeTextur("src/img/8k Sterne2.jpg");
            rundenNR = rundenNR + 1;
           dasUfo.ufoZuruecksetzen();
           for (int i = 0; i < asteroidenAnzahl; i++) {
           asteroiden[i].asteroidZuruecksetzen();
           asteroiden[i].level2();
            }
        }

        //Level 3
        if (milisek == 20000) {
            hintergrund.setzeTextur("src/img/8kSterne3.jpg");
            rundenNR = rundenNR + 1;
            dasUfo.ufoZuruecksetzen();
            for (int e = 0; e < asteroidenAnzahl; e++) {
                    asteroiden[e].asteroidZuruecksetzen();
                asteroiden[e].level3();
            }
        }
         // Level 4
        if (milisek == 30000) {
            hintergrund.setzeTextur("src/img/8k Sterne4.jpg");
            rundenNR = rundenNR + 1;
            dasUfo.ufoZuruecksetzen();
            for (int f = 0; f < asteroidenAnzahl; f++) {
                asteroiden[f].asteroidZuruecksetzen();
                asteroiden[f].level4();
            }
        }

        testTafel.setzeText("Level: " + rundenNR, 50);

    }
    public void crash() {
        for (int i = 0; i < asteroidenAnzahl; i++) {
          if (asteroiden[i].crash() == true) {

              dasUfo.explosion();
              Sys.warte(100);
              for (int e = 0; e < asteroidenAnzahl; e++)
                  asteroiden[e].asteroidZuruecksetzen();
              dasUfo.ufoZuruecksetzen();
              milisek = 0;

            }

        }
    }
    public void pause() {
        if (tastatur.istGedrueckt('p')) {
            Sys.warte(1000);
            while (!tastatur.istGedrueckt('p')) {
            }
            Sys.warte(100);
        }
    }
    public void kameraFolge(){

        ufoPX = dasUfo.gibX();
        ufoPY = dasUfo.gibY();
        ufoPZ = dasUfo.gibZ();
        kamera.setzePosition(ufoPX,ufoPY+10,ufoPZ+40);
     kamera.setzeBlickpunkt(ufoPX,ufoPY,-2000)   ;

    }
}