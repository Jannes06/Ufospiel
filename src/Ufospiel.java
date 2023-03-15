import GLOOP.*;

public class Ufospiel {
    private GLKamera kamera;
    private GLLicht licht;
    private GLTastatur tastatur;
    //private GLHimmel himmel;
    private GLQuader hintergrund;
    private Ufo dasUfo;
    private Asteroid[] asteroiden;

    private Coin [] coin;
    private Coin coinNR1;
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

    int asteroidenAnzahl = 400;
    int coinAnzahl  = 0;
    int gesammelteCoins= 0;
    boolean autopilot = false;
    boolean letzteAbbiegung = false; //false ist Rechts|| true ist links
    double hitradius;


    public Ufospiel() {
        kamera = new GLKamera();
        kamera.verschiebe(600, 40, 0);

        licht = new GLLicht();
        tastatur = new GLTastatur();
        //himmel = new GLHimmel("src/img/8k Sterne2.jpg");


        hintergrund = new GLQuader(600, 0, -2000, 8000, 4000, 1);
       // hintergrund.setzeTextur("src/img/Sterne.jpg");

        dasUfo = new Ufo();
        testTafel = new GLTafel(670, 0, 400, 10, 10);
        testTafel.setzeKamerafixierung(true);
        asteroiden = new Asteroid[asteroidenAnzahl];
        for (int i = 0; i < asteroidenAnzahl; i++) {
            asteroiden[i] = new Asteroid(dasUfo,coinNR1,Math.random() * 30 + 10);
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
        kuenstlicheIntelligenz();
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
    public void coinbewegung(){
        for (int i = 0; i < coinAnzahl; i++) {
            coin[i].coinbewegen();
        }
      coinNR1.coinbewegen();
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
              gesammelteCoins= 0;

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
        if (coinNR1.istCoinGeloescht()==true) {
            coinNR1.loesche();
            coinNR1 = new Coin (dasUfo);
        }
    }

    public void collected() {
        for (int i = 0; i < coinAnzahl; i++) {
            if (coin[i].collected() == true) {

             gesammelteCoins= gesammelteCoins+1;
             coin[i].loesche();
             coin[i]=new Coin(dasUfo);
            }
          testTafel.setzeText("Coins: "+gesammelteCoins,10);
        }
    }
    public void kameraFolge(){

        ufoPX = dasUfo.gibX();
        ufoPY = dasUfo.gibY();
        ufoPZ = dasUfo.gibZ();
        kamera.setzePosition(ufoPX,ufoPY+10,ufoPZ+40);
     kamera.setzeBlickpunkt(ufoPX,ufoPY,-2000)   ;

    }

    public void kuenstlicheIntelligenz(){

        if (tastatur.istGedrueckt('k')) {
         autopilot = true;

         Sys.warte(20);
            if (tastatur.istGedrueckt('k')) {
                autopilot = false;
            }
        }

        if (autopilot == true) {
            testTafel.setzeFarbe(0,1,0);
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
                    //Hier wird gecheckt, ob das Ufo einen Asteroiden berühren würde und dementsprechend zu den Seiten bewegt.
                    if (((ufoPX < asteroidPX + individuelleHitbox+30) & (ufoPX > asteroidPX - individuelleHitbox-30)) & ((ufoPY < asteroidPY + individuelleHitbox) & (ufoPY > asteroidPY - individuelleHitbox)) & ((ufoPZ < asteroidPZ + 400) & (ufoPZ > asteroidPZ))&(letzteAbbiegung==false) ) {
                        dasUfo.autopilotLinks();

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


                    if (((ufoPX < asteroidPX + individuelleHitbox+30) & (ufoPX > asteroidPX - individuelleHitbox-30)) & ((ufoPY < asteroidPY + individuelleHitbox) & (ufoPY > asteroidPY - individuelleHitbox)) & ((ufoPZ < asteroidPZ + 400) & (ufoPZ > asteroidPZ))&(letzteAbbiegung==true) ) {
                        dasUfo.autopilotRechts();

                        //Falls sich rechts bereits ein anderer Asteroid befindet:
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
            testTafel.setzeFarbe(1, 1, 1);
        }
    }




}