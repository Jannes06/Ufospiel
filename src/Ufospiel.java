import GLOOP.*;

public class Ufospiel {
    private GLKamera kamera;
    private GLLicht licht;
    private GLTastatur tastatur;
    //private GLHimmel himmel;

    private Ufo dasUfo;
    private Schuss[] schuss;
    private FuelTank tank;
    private Schild schild;
    private Asteroid[] asteroiden;

    private Coin[] coin;
    private Coin coinNR1;
    private GLTafel munitionAnzeige,munitionStand,schildanzeige,fuelAnzeige,fuelStand,fuelStandRahmen,fuelLeerText,sidebar,sidebarRahmen, coinAnzeige, levelAnzeige,levelUpAnzeige,hintergrund1,hintergrund2,hintergrund3,hintergrund4;
    double asteroidPX;
    double asteroidPY;
    double asteroidPZ;
    double ufoPX;
    double ufoPY;
    double ufoPZ;
    double milisek = 0;
    int rundenNR = 1;
    int backsetzer = 0;
    int asteroidenAnzahl = 300;
    int coinAnzahl = 0;
    int gesammelteCoins = 0;
    boolean autopilot = false;
    boolean letzteAbbiegung = false; //false ist Rechts|| true ist links
    double hitradius;
    int levelUpAnzeigeDauer = 500;
    boolean auffuellen = true;
    boolean tankleer=false;

    double fuelZahl= 0;
    double neueXkoordinate;
    double neueYkoordinate;
    double neueZkoordinate;
    boolean unzerstoerbar = false;
    int unzerstoerbarkeitsCooldown;
    double energieZahl= 0;
    double energieSkalierung = 0.999;
    int munitionsstand = 10;

    int aufladetimer = 0;
    int schusspause = 0;
    boolean geschossen= false;
    int schussAnzahl =0;
    double schussPX;
    double schussPY;
    double schussPZ;


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

       //Die Widgets bzw Anzeigen-------------------------------------------------------------------------------------------
        coinAnzeige = new GLTafel(692, -5, 401, 15, 15);
        coinAnzeige.setzeTextur("src/img/Coin.png");
        coinAnzeige.setzeKamerafixierung(true);

        fuelAnzeige = new GLTafel(505, -5, 401, 11, 11);
        fuelAnzeige.drehe(0,0,180);
        fuelAnzeige.drehe(0,180,0);
        fuelAnzeige.setzeTextur("src/img/fuelTank.png");
        fuelAnzeige.setzeKamerafixierung(true);

        fuelStandRahmen = new GLTafel(540, -5.1, 400.5, 60, 10);
        fuelStandRahmen.setzeTextur("src/img/FuelHintergrund.jpg");
        fuelStandRahmen.setzeKamerafixierung(true);

        fuelStand = new GLTafel(540, -5.1, 401, 55, 8);
        fuelStand.setzeFarbe(0,0.5,0);
        fuelStand.setzeKamerafixierung(true);

        fuelLeerText= new GLTafel(540, -5.1, 401, 55, 9);
        fuelLeerText.setzeFarbe(0.5,0,0);
        fuelLeerText.setzeText("Der Tank ist leer!",4);
        fuelLeerText.setzeKamerafixierung(true);
        fuelLeerText.setzeSichtbarkeit(false);



        munitionAnzeige = new GLTafel(670, -5, 401.5, 12, 12);
        munitionAnzeige.drehe(0,0,180);
        munitionAnzeige.drehe(0,180,0);
        munitionAnzeige.setzeTextur("src/img/Munition.png");
        munitionAnzeige.setzeKamerafixierung(true);


        munitionStand = new GLTafel(660, -5.1, 401, 25, 9);
        munitionStand.setzeTextur("src/img/FuelHintergrund.jpg");
        munitionStand.setzeTextfarbe(1,1,1);
        munitionStand.setzeKamerafixierung(true);



        levelAnzeige = new GLTafel(603, -5, 401, 9, 9);
        levelAnzeige.setzeTextur("src/img/LeisteRahmen.png");
        levelAnzeige.setzeKamerafixierung(true);

        schildanzeige = new GLTafel(578, -5, 402, 9, 9);
        schildanzeige.drehe(0,0,180);
        schildanzeige.setzeTextur("src/img/Schild.png");
        schildanzeige.setzeKamerafixierung(true);
        schildanzeige.setzeSichtbarkeit(false);

        //Stellt den Hintergund fü die anderen Widgets dar---------------------
        sidebar = new GLTafel(599, -5, 400, 202, 15);
        sidebar.setzeTextur("src/img/Leiste.png");
        sidebar.setzeKamerafixierung(true);

        sidebarRahmen = new GLTafel(600, -5, 399, 212, 17);
        sidebarRahmen.setzeTextur("src/img/LeisteRahmen.png");
        sidebarRahmen.setzeKamerafixierung(true);

        //erscheint bei dem Erreichen der nächsten Runde----------------------------------------------------------------------
        levelUpAnzeige = new GLTafel(600, -5, 402, 20, 20);
        levelUpAnzeige.setzeTextur("src/img/LevelUpAnzeige.png");
        levelUpAnzeige.drehe(0,0,180);
        levelUpAnzeige.setzeKamerafixierung(true);
        levelUpAnzeige.setzeSichtbarkeit(false);

        schuss = new Schuss[10000];


            asteroiden = new Asteroid[asteroidenAnzahl];
            for (int i = 0; i < asteroidenAnzahl; i++) {
                asteroiden[i] = new Asteroid(dasUfo,coinNR1, Math.random() * 30 + 10);
            }

        //coinNR1 ist unser Standart Coin, ihn gibt es immer und der autopilot kann diesen Anvisieren
        coinNR1 = new Coin(dasUfo);

        coin = new Coin[coinAnzahl];
        for (int i = 0; i < coinAnzahl; i++) {
            coin[i] = new Coin(dasUfo);
        }

        //Der Benzintank in der Map

        tank = new FuelTank(dasUfo);
        schild = new Schild(dasUfo);

        while (0 == 0) {
            ausfuehrung();
        }

    }


    public void ausfuehrung() {
        ufobewegung();
        asteroidbewegung();
        coinUndTankUndSchildbewegung();
        crash();
        pause();
        rundenanzahl();
        collected();
        coinErschaffung();
        kameraFolge();
        autopilot();
        tankGesammelt();
        fuelAuffuellen();
        schildGesammelt();
        schiessen();
        munitionAufladen();
        Sys.warte();
    }
    public void schiessen() {
        schusspause = schusspause + 1;

        if ((tastatur.enter()) && (schusspause > 100) && (munitionsstand > 0)) {

            schuss[schussAnzahl] = new Schuss(dasUfo);
            schussAnzahl = schussAnzahl + 1;

            geschossen = true;
            schusspause = 0;
            munitionVerbrauchen();
        }

        for (int y = 0; y < schussAnzahl ;y++) {
            //if (geschossen == true) {
                schuss[y].abschuss();

            for (int i = 0; i < asteroidenAnzahl; i++) {

                hitradius = asteroiden[i].radius();
                schussPX = schuss[y].gibX();
                schussPY = schuss[y].gibY();
                schussPZ = schuss[y].gibZ();
                asteroidPX = asteroiden[i].gibX();
                asteroidPY = asteroiden[i].gibY();
                asteroidPZ = asteroiden[i].gibZ();
                double individuelleHitbox = hitradius * 0.95;
                //Hier wird gecheckt, ob der Schuss einen Asteroiden berühren würde und dementsprechend nach links bewegt.-------------------------------------------------------------------------------------------------
                if (((schussPX < asteroidPX + individuelleHitbox ) & (schussPX > asteroidPX - individuelleHitbox )) & ((schussPY < asteroidPY + individuelleHitbox) & (schussPY > asteroidPY - individuelleHitbox)) & ((schussPZ < asteroidPZ + individuelleHitbox) & (schussPZ > asteroidPZ)) ) {
                  asteroiden[i].asteroidZuruecksetzen();
                    schuss[y].loesche();
                }
            }
        }
    }

            public void ufobewegung() {
        if (tankleer==false) {
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
    }

    public void linksbewegung() {
        //Bewegung nach links if (tastatur.links()) {
        dasUfo.bewegeLinks();
        backsetzer = 1;
        fuelVerbrauchen();
        //GegenbewegungLinksRand
        if (dasUfo.gibX() < 100) {
            dasUfo.bewegeRechts();
        }
    }

    public void rechtsbewegung() {
        //Bewegung nach rechts

        dasUfo.bewegeRechts();
        backsetzer = 1;
        fuelVerbrauchen();
        //GegenbewegungRechtsRand
        if (dasUfo.gibX() > 900) {
            dasUfo.bewegeLinks();
        }
    }

    public void obenbewegung() {
        //Bewegung nach oben

        dasUfo.bewegeOben();
        backsetzer = 1;
        fuelVerbrauchen();
        //GegenbewegungObenRand
        if (dasUfo.gibY() > 350) {
            dasUfo.bewegeUnten();
        }
    }

    public void untenbewegung() {
        //Bewegung nach unten

        dasUfo.bewegeUnten();
        backsetzer = 1;
        fuelVerbrauchen();

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

    public void coinUndTankUndSchildbewegung() {
        for (int i = 0; i < coinAnzahl; i++) {
            coin[i].coinbewegen();
        }
        coinNR1.coinbewegen();
        tank.tankbewegen();
        schild.schildbewegen();
    }

    public void rundenanzahl() {
        milisek = milisek + 1;
        //Level 1
        levelUpAnzeigeDauer = levelUpAnzeigeDauer+1;
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
            levelUpAnzeigeDauer = 0;
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
            levelUpAnzeige.setzeSichtbarkeit(true);
            Sys.warte(300);
        }

        //Level 3
        if (milisek == 20000) {
            levelUpAnzeigeDauer = 0;
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
            levelUpAnzeige.setzeSichtbarkeit(true);
            Sys.warte(300);
        }
        // Level 4
        if (milisek == 30000) {

            hintergrund1.setzeSichtbarkeit(false);
            hintergrund2.setzeSichtbarkeit(false);
            hintergrund3.setzeSichtbarkeit(false);
            hintergrund4.setzeSichtbarkeit(true);


        }
        if (milisek%30000==0) {
            levelUpAnzeigeDauer = 0;
            rundenNR = rundenNR + 1;
            dasUfo.ufoZuruecksetzen();
            for (int l = 0; l < asteroidenAnzahl; l++) {
                asteroiden[l].asteroidZuruecksetzen();
                asteroiden[l].level4();

            }

            Sys.warte(300);
        }

        levelAnzeige.setzeText("Galaxy: " +rundenNR,7 );

        // Aktiviert die LevelUp Tafel 400ms lang nach einem level up
        if ( levelUpAnzeigeDauer <400 ) {

            levelUpAnzeige.setzeSichtbarkeit(true);
        }
        // Deaktiviert die LevelUp Tafel nach den 400ms
        if ( levelUpAnzeigeDauer >400 ) {

            levelUpAnzeige.setzeSichtbarkeit(false);
        }
    }

    public void crash() {
        if (unzerstoerbar == false) {
            for (int i = 0; i < asteroidenAnzahl; i++) {
                if (asteroiden[i].crash() == true) {

                    dasUfo.explosion();
                    Sys.warte(100);
                    for (int e = 0; e < asteroidenAnzahl; e++)
                        asteroiden[e].asteroidZuruecksetzen();
                    dasUfo.ufoZuruecksetzen();
                    milisek = 0;
                    gesammelteCoins = 0;
                    auffuellen = true;
                    fuelZahl = 0;
                    tankleer = false;
                    munitionsstand = 10;
                }

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
        coinAnzeige.setzeText( ""+gesammelteCoins, 10);
    }

    public void kameraFolge() {

        ufoPX = dasUfo.gibX();
        ufoPY = dasUfo.gibY();
        ufoPZ = dasUfo.gibZ();
        kamera.setzePosition(ufoPX, ufoPY +3, ufoPZ + 40);
        kamera.setzeBlickpunkt(ufoPX, ufoPY, -2000);

       // x und y Koordinate um die fuelleiste Anzupassen
        neueXkoordinate=kamera.gibX()-60;
        neueYkoordinate=kamera.gibY()-45.1;
        neueZkoordinate= kamera.gibZ()-99;
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

    public void tankGesammelt(){
        if (tank.collected() == true) {
            auffuellen=true;
            tank.Zuruecksetzen();
        }
    }
    public void schildGesammelt(){
        if (schild.collected() == true) {
            unzerstoerbar=true;
            schild.Zuruecksetzen();
        schildanzeige.setzeSichtbarkeit(true);

        }
        if (unzerstoerbar == true){
             unzerstoerbarkeitsCooldown = unzerstoerbarkeitsCooldown+1;
             dasUfo.schildsichtbar();
             //Hier blinkt die Anzeige, wenn der Schutz sich dem Ende neigt
             if (unzerstoerbarkeitsCooldown >4400 && unzerstoerbarkeitsCooldown<4500) {
                 schildanzeige.setzeSichtbarkeit(false);
             }
            if (unzerstoerbarkeitsCooldown >4500 && unzerstoerbarkeitsCooldown<4620) {
                schildanzeige.setzeSichtbarkeit(true);
            }
            if (unzerstoerbarkeitsCooldown >4620 && unzerstoerbarkeitsCooldown<4700) {
                schildanzeige.setzeSichtbarkeit(false);
            }
            if (unzerstoerbarkeitsCooldown >4700 && unzerstoerbarkeitsCooldown<4760) {
                schildanzeige.setzeSichtbarkeit(true);
            }
            if (unzerstoerbarkeitsCooldown >4760 && unzerstoerbarkeitsCooldown<4830) {
                schildanzeige.setzeSichtbarkeit(false);
            }
            if (unzerstoerbarkeitsCooldown >4830 && unzerstoerbarkeitsCooldown<4900) {
                schildanzeige.setzeSichtbarkeit(true);
            }
            if (unzerstoerbarkeitsCooldown >4900 ) {
                schildanzeige.setzeSichtbarkeit(false);
            }
                if (unzerstoerbarkeitsCooldown>5000)  {
                 unzerstoerbar=false;
                 unzerstoerbarkeitsCooldown = 0;
                 dasUfo.schildunsichtbar();

             }
        }
    }
   public void fuelAuffuellen() {

       if( auffuellen == true) {
       fuelStand.setzeSkalierung(1,1,1);
       fuelStand.setzeKamerafixierung(false);
           fuelStand.setzePosition(neueXkoordinate, neueYkoordinate, neueZkoordinate);
           fuelStand.setzeKamerafixierung(true);
           fuelZahl=0;
           fuelStand.setzeFarbe(0,0.5,0);
           fuelStand.setzeSichtbarkeit(true);
           fuelLeerText.setzeSichtbarkeit(false);
       }
        auffuellen=false;
   }
    public void fuelVerbrauchen() {
        fuelZahl = (fuelZahl + 0.0078) * 0.99971;

        fuelStand.skaliere(0.9997, 1, 1);

        if (fuelZahl > 21 && fuelZahl<=26) {
            fuelStand.setzeFarbe(0.5, 0, 0);
        }
        if (fuelZahl > 11 && fuelZahl <= 21) {
            fuelStand.setzeFarbe(0.5, 0.5, 0);
        }
        if (fuelZahl < 11) {
            fuelStand.setzeFarbe(0, 0.5, 0);

        }

        double neueXkoordinateAngepasst = neueXkoordinate - fuelZahl;
        fuelStand.setzeKamerafixierung(false);
        fuelStand.setzePosition(neueXkoordinateAngepasst, neueYkoordinate, neueZkoordinate);
        fuelStand.setzeKamerafixierung(true);

        if (fuelZahl > 26) {
            fuelLeerText.setzeSichtbarkeit(true);
            tankleer = true;
            fuelStand.setzeSichtbarkeit(false);
        }
    }
    public void munitionVerbrauchen() {
    munitionsstand = munitionsstand -1 ;


    }

    public void munitionAufladen() {
        munitionStand.setzeText(""+munitionsstand,7);

        if (munitionsstand<10) {
            aufladetimer = aufladetimer + 1;
        }
    if ((aufladetimer > 1800) &&(munitionsstand<10) ){
        aufladetimer = 0;
        munitionsstand = munitionsstand + 1;
    }
    }
}