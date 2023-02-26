import GLOOP.*;

public class Ufospiel {
    private GLKamera kamera;
    private GLLicht licht;
    private GLTastatur tastatur;
    private GLHimmel himmel;
    private GLQuader hintergrund;
    private Ufo dasUfo;
    private GLTafel testTafel;
    double asteroidPX;
    double asteroidPY;
    double ufoPX;
    double ufoPY;
    double milisek = 0;
    int rundenNR = 1;

    int asteroidenAnzahl = 82;
    private Asteroid[] asteroiden;

    public Ufospiel() {
        kamera = new GLSchwenkkamera();
        kamera.verschiebe(600, 0, 500);
        licht = new GLLicht();
        tastatur = new GLTastatur();
        himmel = new GLHimmel("src/img/8k Sterne2.jpg");


        hintergrund = new GLQuader(600, 35, -800, 4000, 2000, 1);
       // hintergrund.setzeTextur("src/img/Sterne.jpg");

        dasUfo = new Ufo();
        testTafel = new GLTafel(1300, -500, 0, 50, 50);

        asteroiden = new Asteroid[asteroidenAnzahl];
        for (int i = 0; i < asteroidenAnzahl; i++) {
            asteroiden[i] = new Asteroid(Math.random() * 30 + 10);
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
        Sys.warte();
    }

    public void ufobewegung() {
        //Bewegung nach links
        if (tastatur.links()) {
            dasUfo.bewegeLinks();
        }
        //GegenbewegungLinksRand
        if (dasUfo.gibX() < 0) {
            dasUfo.bewegeRechts();
        }
        //Bewegung nach rechts
        if (tastatur.rechts()) {
            dasUfo.bewegeRechts();
        }
        //GegenbewegungRechtsRand
        if (dasUfo.gibX() > 1200) {
            dasUfo.bewegeLinks();
        }
        //Bewegung nach oben
        if (tastatur.oben()) {
            dasUfo.bewegeOben();
        }
        //GegenbewegungObenRand
        if (dasUfo.gibY() > 500) {
            dasUfo.bewegeUnten();
        }
        //Bewegung nach unten
        if (tastatur.unten()) {
            dasUfo.bewegeUnten();
        }
        //GegenbewegungUntenRand
        if (dasUfo.gibY() < -500) {
            dasUfo.bewegeOben();
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
        if (milisek == 4000) {
            hintergrund.setzeTextur("src/img/8k Sterne2.jpg");

            rundenNR = rundenNR + 1;
            dasUfo.ufoZuruecksetzen();
            for (int i = 0; i < asteroidenAnzahl; i++) {
                asteroiden[i].asteroidZuruecksetzen();
                asteroiden[i].level2();
            }
        }

        //Level 3
        if (milisek == 8000) {
            hintergrund.setzeTextur("src/img/8kSterne3.jpg");
            rundenNR = rundenNR + 1;
            dasUfo.ufoZuruecksetzen();
            for (int e = 0; e < asteroidenAnzahl; e++) {
                asteroiden[e].asteroidZuruecksetzen();
                asteroiden[e].level3();
            }
        }
         // Level 4
        if (milisek == 12000) {
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

            ufoPX = dasUfo.gibX();
            ufoPY = dasUfo.gibY();
            asteroidPX = asteroiden[i].gibX();
            asteroidPY = asteroiden[i].gibY();
            double individuelleHitbox = asteroiden[i].radiusHitbox();
            //Hier wird gecheckt, ob das Ufo ein Asteroiden berührt.
            if (((ufoPX < asteroidPX + individuelleHitbox) & (ufoPX > asteroidPX - individuelleHitbox)) & ((ufoPY < asteroidPY + individuelleHitbox) & (ufoPY > asteroidPY - individuelleHitbox))) {
                testTafel.setzeFarbe(1, 0, 0);
                dasUfo.explosion();
                Sys.warte(3000);
                testTafel.setzeFarbe(1, 1, 1);

                milisek= 0;
                Sys.warte(2000);
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
}