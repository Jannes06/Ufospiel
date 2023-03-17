import GLOOP.*;

public class Ufospiel {
    private GLKamera kamera;
    private GLLicht licht;
    private GLTastatur tastatur;
    //private GLHimmel himmel;

    private Ufo dasUfo;
    private Asteroid[] asteroiden;

    private Coin[] coin;
    private Coin coinNR1;
    private GLTafel coinAnzeige, levelAnzeige,hintergrund1,hintergrund2,hintergrund3,hintergrund4;
    double asteroidPX;
    double asteroidPY;
    double asteroidPZ;
    double ufoPX;
    double ufoPY;
    double ufoPZ;
    double milisek = 0;
    int rundenNR = 1;
    int backsetzer = 0;

    int asteroidenAnzahl = 400;
    int coinAnzahl = 0;
    int gesammelteCoins = 0;
    boolean autopilot = false;
    boolean letzteAbbiegung = false; //false ist Rechts|| true ist links
    double hitradius;


    public Ufospiel() {
        kamera = new GLKamera();
        kamera.verschiebe(600, 40, 0);

        licht = new GLLicht();
        tastatur = new GLTastatur();
        //himmel = new GLHimmel("src/img/8k Sterne2.jpg");




        hintergrund1 = new GLTafel(600, 0, -2001, 8000, 4000);
        hintergrund1.setzeTextur("src/img/Sterne.jpg");

        hintergrund2 = new GLTafel(600, 0, -2002, 8000, 4000);
        hintergrund2.setzeTextur("src/img/8k Sterne2.jpg");

        hintergrund3 = new GLTafel(600, 0, -2003, 8000, 4000);
        hintergrund3.setzeTextur("src/img/8kSterne3.jpg");

        hintergrund4 = new GLTafel(600, 0, -2004, 8000, 4000);
        hintergrund4.setzeTextur("src/img/8k Sterne4.jpg");

        dasUfo = new Ufo();

        coinAnzeige = new GLTafel(670, 0, 400, 10, 10);
        coinAnzeige.setzeTextur("src/img/img_2.png");
        coinAnzeige.setzeKamerafixierung(true);

        levelAnzeige = new GLTafel(670, 10, 400, 10, 10);
        levelAnzeige.setzeKamerafixierung(true);

        asteroiden = new Asteroid[asteroidenAnzahl];
        for (int i = 0; i < asteroidenAnzahl; i++) {
            asteroiden[i] = new Asteroid(dasUfo, coinNR1, Math.random() * 30 + 10);
        }
        //coinNR1 ist unser Standart Coin, ihn gibt es immer und der autopilot kann diesen Anwisieren
        coinNR1 = new Coin(dasUfo);

        coin = new Coin[coinAnzahl];
        for (int i = 0; i < coinAnzahl; i++) {
            coin[i] = new Coin(dasUfo);
        }

        while (0 == 0) {
            ausfuehrung();
        }

    }


    public void ausfuehrung() {
        ufobewegung();
        asteroidbewegung();
        coinbewegung();
        crash();
        pause();
        rundenanzahl();
        collected();
        coinErschaffung();
        kameraFolge();
        autopilot();
        Sys.warte();
    }

    public void ufobewegung() {
        if (tastatur.links()) {
            linksbewegung();
        }
        if (tastatur.rechts()) {
            rechtsbewegung();
        }
        if (tastatur.oben()) {
            obenbewegung();
        }
        if (tastatur.unten()) {
            untenbewegung();
        }
        drehungsZuruecksetzung();
    }

    public void linksbewegung() {
        //Bewegung nach links if (tastatur.links()) {
        dasUfo.bewegeLinks();
        backsetzer = 1;

        //GegenbewegungLinksRand
        if (dasUfo.gibX() < 100) {
            dasUfo.bewegeRechts();
        }
    }

    public void rechtsbewegung() {
        //Bewegung nach rechts

        dasUfo.bewegeRechts();
        backsetzer = 1;

        //GegenbewegungRechtsRand
        if (dasUfo.gibX() > 900) {
            dasUfo.bewegeLinks();
        }
    }

    public void obenbewegung() {
        //Bewegung nach oben

        dasUfo.bewegeOben();
        backsetzer = 1;

        //GegenbewegungObenRand
        if (dasUfo.gibY() > 350) {
            dasUfo.bewegeUnten();
        }
    }

    public void untenbewegung() {
        //Bewegung nach unten

        dasUfo.bewegeUnten();
        backsetzer = 1;


        //GegenbewegungUntenRand
        if (dasUfo.gibY() < -350) {
            dasUfo.bewegeOben();
        }
    }

    public void drehungsZuruecksetzung() {
        if ((!tastatur.rechts()) && (!tastatur.links()) && (!tastatur.oben()) && (!tastatur.unten())) {
            backsetzer = 0;

        }
        if (backsetzer == 0) {
            dasUfo.DrehungZuruecksetzen();
        }
    }

    public void asteroidbewegung() {
        for (int i = 0; i < asteroidenAnzahl; i++) {
            asteroiden[i].fallen();
        }
    }

    public void coinbewegung() {
        for (int i = 0; i < coinAnzahl; i++) {
            coin[i].coinbewegen();
        }
        coinNR1.coinbewegen();
    }

    public void rundenanzahl() {
        milisek = milisek + 1;
        //Level 1

        if (milisek == 1) {
            hintergrund1.setzeSichtbarkeit(true);
            hintergrund2.setzeSichtbarkeit(false);
            hintergrund3.setzeSichtbarkeit(false);
            hintergrund4.setzeSichtbarkeit(false);
            rundenNR = 1;
            dasUfo.ufoZuruecksetzen();
            for (int i = 0; i < asteroidenAnzahl; i++) {
                asteroiden[i].asteroidZuruecksetzen();
                asteroiden[i].level1();
            }
        }
        //Level 2
        if (milisek == 10000) {
            hintergrund1.setzeSichtbarkeit(false);
            hintergrund2.setzeSichtbarkeit(true);
            hintergrund3.setzeSichtbarkeit(false);
            hintergrund4.setzeSichtbarkeit(false);
            rundenNR = rundenNR + 1;
            dasUfo.ufoZuruecksetzen();
            for (int i = 0; i < asteroidenAnzahl; i++) {
                asteroiden[i].asteroidZuruecksetzen();
                asteroiden[i].level2();
            }
        }

        //Level 3
        if (milisek == 20000) {
            hintergrund1.setzeSichtbarkeit(false);
            hintergrund2.setzeSichtbarkeit(false);
            hintergrund3.setzeSichtbarkeit(true);
            hintergrund4.setzeSichtbarkeit(false);
            rundenNR = rundenNR + 1;
            dasUfo.ufoZuruecksetzen();
            for (int e = 0; e < asteroidenAnzahl; e++) {
                asteroiden[e].asteroidZuruecksetzen();
                asteroiden[e].level3();
            }
        }
        // Level 4
        if (milisek == 30000) {
            hintergrund1.setzeSichtbarkeit(false);
            hintergrund2.setzeSichtbarkeit(false);
            hintergrund3.setzeSichtbarkeit(false);
            hintergrund4.setzeSichtbarkeit(true);


        }
        if (milisek%30000==0) {
            rundenNR = rundenNR + 1;
            dasUfo.ufoZuruecksetzen();
            for (int l = 0; l < asteroidenAnzahl; l++) {
                asteroiden[l].asteroidZuruecksetzen();
                asteroiden[l].level4();

            }
        }
        levelAnzeige.setzeText("Level: " +rundenNR,10 );
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
                gesammelteCoins = 0;

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

    public void coinErschaffung() {

        for (int e = 0; e < coinAnzahl; e++) {
            if (coin[e].istCoinGeloescht() == true) {

                coin[e].loesche();

                coin[e] = new Coin(dasUfo);
            }

        }
        if (coinNR1.istCoinGeloescht() == true) {
            coinNR1.loesche();
            coinNR1 = new Coin(dasUfo);
        }
    }

    public void collected() {
        for (int i = 0; i < coinAnzahl; i++) {
            if (coin[i].collected() == true) {

                gesammelteCoins = gesammelteCoins + 1;
                coin[i].loesche();
                coin[i] = new Coin(dasUfo);
            }

        }
        if (coinNR1.collected() == true) {

            gesammelteCoins = gesammelteCoins + 1;
            coinNR1.loesche();
            coinNR1 = new Coin(dasUfo);
        }
        coinAnzeige.setzeText("Coins: " + gesammelteCoins, 10);
    }

    public void kameraFolge() {

        ufoPX = dasUfo.gibX();
        ufoPY = dasUfo.gibY();
        ufoPZ = dasUfo.gibZ();
        kamera.setzePosition(ufoPX, ufoPY + 10, ufoPZ + 40);
        kamera.setzeBlickpunkt(ufoPX, ufoPY, -2000);

    }

    public void autopilot() {

        if (tastatur.istGedrueckt('k')) {
            autopilot = true;
            Sys.warte(20);
        }

        if (tastatur.istGedrueckt('l')) {
            autopilot = false;
            Sys.warte(20);
        }


        if (autopilot == true) {
            coinAnzeige.setzeFarbe(0, 1, 0);
            //Weicht den Asteroiden aus
            for (int i = 0; i < asteroidenAnzahl; i++) {

                hitradius = asteroiden[i].radius();
                ufoPX = dasUfo.gibX();
                ufoPY = dasUfo.gibY();
                ufoPZ = dasUfo.gibZ();
                asteroidPX = asteroiden[i].gibX();
                asteroidPY = asteroiden[i].gibY();
                asteroidPZ = asteroiden[i].gibZ();
                double individuelleHitbox = hitradius * 0.95;
                //Hier wird gecheckt, ob das Ufo einen Asteroiden berühren würde und dementsprechend nach links bewegt.-------------------------------------------------------------------------------------------------
                if (((ufoPX < asteroidPX + individuelleHitbox + 30) & (ufoPX > asteroidPX - individuelleHitbox - 30)) & ((ufoPY < asteroidPY + individuelleHitbox) & (ufoPY > asteroidPY - individuelleHitbox)) & ((ufoPZ < asteroidPZ + 500) & (ufoPZ > asteroidPZ)) & (letzteAbbiegung == false)) {
                    dasUfo.autopilotLinks();
                    if ((ufoPZ < asteroidPZ + 200) & (ufoPZ > asteroidPZ)) {
                        for (int oben = 0; oben < asteroidenAnzahl; oben++) {

                            hitradius = asteroiden[oben].radius();
                            ufoPX = dasUfo.gibX();
                            ufoPY = dasUfo.gibY();
                            ufoPZ = dasUfo.gibZ();
                            asteroidPX = asteroiden[oben].gibX();
                            asteroidPY = asteroiden[oben].gibY();
                            asteroidPZ = asteroiden[oben].gibZ();
                            double individuelleHitbox5 = hitradius * 0.95;


                            //Nach Oben, wenn unter ihm die hitbox ist
                            if (((ufoPX < asteroidPX + individuelleHitbox5) & (ufoPX > asteroidPX - individuelleHitbox5)) & ((ufoPY < asteroidPY + individuelleHitbox5 + 400) & (ufoPY > asteroidPY)) & ((ufoPZ < asteroidPZ + individuelleHitbox5 + 120) & (ufoPZ > asteroidPZ - individuelleHitbox5 - 50))) {
                                dasUfo.autopilotOben();
                            }
                            //Nach Unten, wenn über ihm die hitbox ist
                            if (((ufoPX < asteroidPX + individuelleHitbox5) & (ufoPX > asteroidPX - individuelleHitbox5)) & ((ufoPY > asteroidPY - individuelleHitbox5 - 400) & (ufoPY < asteroidPY)) & ((ufoPZ < asteroidPZ + individuelleHitbox5 + 120) & (ufoPZ > asteroidPZ - individuelleHitbox5 - 50))) {
                                dasUfo.autopilotUnten();

                            }
                        }
                    }
                    //Falls sich links bereits ein anderer Asteroid befindet:
                    for (int links = 0; links < asteroidenAnzahl; links++) {
                        hitradius = asteroiden[links].radius();
                        ufoPX = dasUfo.gibX();
                        ufoPY = dasUfo.gibY();
                        ufoPZ = dasUfo.gibZ();
                        asteroidPX = asteroiden[links].gibX();
                        asteroidPY = asteroiden[links].gibY();
                        asteroidPZ = asteroiden[links].gibZ();
                        double individuelleHitbox2 = hitradius * 0.95;

                        if (((ufoPX < asteroidPX + individuelleHitbox2 + 100) & (ufoPX > asteroidPX)) & ((ufoPY < asteroidPY + individuelleHitbox2) & (ufoPY > asteroidPY - individuelleHitbox2)) & ((ufoPZ < asteroidPZ + individuelleHitbox2 + 50) & (ufoPZ > asteroidPZ - individuelleHitbox2 - 10))) {

                            letzteAbbiegung = true;
                        }
                    }
                }

                //wenn das ufo schon am linken rand war, lenkt er ab jetzt nach rechts
                if (ufoPX < 110) {
                    letzteAbbiegung = true;
                }

                //Hier bewegt er sich nach rechts-------------------------------------------------------------------------------------------------------------------------------------------------------------------
                if (((ufoPX < asteroidPX + individuelleHitbox + 30) & (ufoPX > asteroidPX - individuelleHitbox - 30)) & ((ufoPY < asteroidPY + individuelleHitbox) & (ufoPY > asteroidPY - individuelleHitbox)) & ((ufoPZ < asteroidPZ + 500) & (ufoPZ > asteroidPZ)) & (letzteAbbiegung == true)) {
                    if ((ufoPZ < asteroidPZ + 500) & (ufoPZ > asteroidPZ + 200)) {
                        dasUfo.autopilotRechts();
                    }
                    //Falls er schon zu nah ist, weicht er nach oben oder unten aus----------------------------------------
                    if ((ufoPZ < asteroidPZ + 200) & (ufoPZ > asteroidPZ)) {
                        for (int oben = 0; oben < asteroidenAnzahl; oben++) {

                            hitradius = asteroiden[oben].radius();
                            ufoPX = dasUfo.gibX();
                            ufoPY = dasUfo.gibY();
                            ufoPZ = dasUfo.gibZ();
                            asteroidPX = asteroiden[oben].gibX();
                            asteroidPY = asteroiden[oben].gibY();
                            asteroidPZ = asteroiden[oben].gibZ();
                            double individuelleHitbox4 = hitradius * 0.95;
                            //Nach Unten, wenn über ihm die hitbox ist
                            if (((ufoPX < asteroidPX + individuelleHitbox4) & (ufoPX > asteroidPX - individuelleHitbox4)) & ((ufoPY > asteroidPY - individuelleHitbox4 - 400) & (ufoPY < asteroidPY)) & ((ufoPZ < asteroidPZ + individuelleHitbox4 + 100) & (ufoPZ > asteroidPZ - individuelleHitbox4 - 10))) {
                                dasUfo.autopilotUnten();

                            }
                            //Nach Oben, wenn unter ihm die hitbox ist
                            if (((ufoPX < asteroidPX + individuelleHitbox4) & (ufoPX > asteroidPX - individuelleHitbox4)) & ((ufoPY < asteroidPY + individuelleHitbox4 + 400) & (ufoPY > asteroidPY)) & ((ufoPZ < asteroidPZ + individuelleHitbox4 + 100) & (ufoPZ > asteroidPZ - individuelleHitbox4 - 10))) {
                                dasUfo.autopilotOben();
                            }
                        }
                    }
                    //Falls sich rechts bereits ein anderer Asteroid befindet:-------------------------------------------
                    for (int rechts = 0; rechts < asteroidenAnzahl; rechts++) {

                        hitradius = asteroiden[rechts].radius();
                        ufoPX = dasUfo.gibX();
                        ufoPY = dasUfo.gibY();
                        ufoPZ = dasUfo.gibZ();
                        asteroidPX = asteroiden[rechts].gibX();
                        asteroidPY = asteroiden[rechts].gibY();
                        asteroidPZ = asteroiden[rechts].gibZ();
                        double individuelleHitbox3 = hitradius * 0.95;

                        if (((ufoPX < asteroidPX) & (ufoPX > asteroidPX - individuelleHitbox3 - 100)) & ((ufoPY < asteroidPY + individuelleHitbox3) & (ufoPY > asteroidPY - individuelleHitbox3)) & ((ufoPZ < asteroidPZ + individuelleHitbox3 + 50) & (ufoPZ > asteroidPZ - individuelleHitbox3 - 10))) {

                            letzteAbbiegung = false;
                        }
                    }
                }
                //wenn das ufo schon am rechten rand war, lenkt er ab jetzt nach links
                if (ufoPX > 890) {
                    letzteAbbiegung = false;
                }


            }
        }
        //Verfolgt die Punkte


        if (autopilot == false) {
            coinAnzeige.setzeFarbe(1, 1, 1);
        }
    }

    public void punktverfolgung() {
        for (int i = 0; i < asteroidenAnzahl; i++) {

            hitradius = asteroiden[i].radius();
            ufoPX = dasUfo.gibX();
            ufoPY = dasUfo.gibY();
            ufoPZ = dasUfo.gibZ();
            asteroidPX = asteroiden[i].gibX();
            asteroidPY = asteroiden[i].gibY();
            asteroidPZ = asteroiden[i].gibZ();
            double individuelleHitbox = hitradius * 0.95;
            //Hier wird gecheckt, ob das Ufo einen Asteroiden berühren würde und dementsprechend nach links bewegt.-------------------------------------------------------------------------------------------------
            if (((ufoPX < asteroidPX + individuelleHitbox + 30) & (ufoPX > asteroidPX - individuelleHitbox - 30)) & ((ufoPY < asteroidPY + individuelleHitbox) & (ufoPY > asteroidPY - individuelleHitbox)) & ((ufoPZ < asteroidPZ + 500) & (ufoPZ > asteroidPZ)) & (letzteAbbiegung == false)) {

            }


        }
    }
}