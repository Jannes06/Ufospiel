import GLOOP.*; 
public class Ufospiel {
    private GLKamera kamera;
    private GLLicht licht;
    private GLTastatur tastatur;
    private GLHimmel himmel;
    private Ufo dasUfo;
    private GLTafel testTafel;
    double asteroidPX ;
    double asteroidPY;
    double ufoPX ;
    double ufoPY;
    double rundenNR;

    int asteroidenAnzahl = 82;
    private Asteroid [] asteroiden;
    public Ufospiel() {
        kamera = new GLSchwenkkamera();
        kamera.verschiebe(600, 0, 500);
        licht = new GLLicht();
        tastatur = new GLTastatur();
        himmel = new GLHimmel("src/img/Sterne.jpg");
        dasUfo = new Ufo();
        testTafel = new GLTafel(1300, -500, 0, 50, 50);

          asteroiden = new Asteroid[asteroidenAnzahl];
          for (int i = 0; i < asteroidenAnzahl; i++) {
            asteroiden[i] = new Asteroid(20);
        }

        while (0 == 0) {
            ausfuehrung();
        }
    }
    public void ausfuehrung(){
        ufobewegung();
        asteroidbewegung();
        crash();
        Sys.warte();
    }
    public void ufobewegung() {
        //Bewegung nach links
        if (tastatur.links()) {
            dasUfo.bewegeLinks();
        }
        //GegenbewegungLinksRand
        if(dasUfo.gibX()<0){
            dasUfo.bewegeRechts();
        }
        //Bewegung nach rechts
        if (tastatur.rechts()) {
            dasUfo.bewegeRechts();
        }
        //GegenbewegungRechtsRand
        if(dasUfo.gibX()>1200){
            dasUfo.bewegeLinks();
        }
        //Bewegung nach oben
        if (tastatur.oben()) {
            dasUfo.bewegeOben();
        }
        //GegenbewegungObenRand
        if(dasUfo.gibY()>500) {
            dasUfo.bewegeUnten();
        }
        //Bewegung nach unten
        if (tastatur.unten()) {
            dasUfo.bewegeUnten();
        }
            //GegenbewegungUntenRand
            if(dasUfo.gibY()<-500) {
                dasUfo.bewegeOben();
            }


    }

    public void asteroidbewegung(){
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

    public void rundenanzahl(){
        rundenNR = rundenNR +1;
        testTafel.setzeText("" + rundenNR, 50);
    }
    public void crash(){
     for (int i = 0; i < asteroidenAnzahl; i++) {

            ufoPX = dasUfo.gibX();
            ufoPY = dasUfo.gibY();
            asteroidPX = asteroiden[i].gibX();
            asteroidPY = asteroiden[i].gibY();
           double individuelleHitbox = asteroiden[i].radiusHitbox();
            //Hier wird gecheckt, ob das Ufo ein Asteroiden berührt.
          if (((ufoPX<asteroidPX+individuelleHitbox) & (ufoPX>asteroidPX-individuelleHitbox)) & ((ufoPY<asteroidPY+individuelleHitbox) & (ufoPY>asteroidPY-individuelleHitbox)) ) {
                testTafel.setzeFarbe(1,0,0);
                Sys.warte(3000);
                testTafel.setzeFarbe(1,1,1);

              for (int e = 0; e < asteroidenAnzahl; e++) {
                  asteroiden[e].asteroidZuruecksetzen();
              }
              dasUfo.ufoZuruecksetzen();
              Sys.warte(2000);
         }

     }
     }


}