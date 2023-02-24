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
    private Asteroid [] asteroiden;
    public Ufospiel() {
        kamera = new GLSchwenkkamera();
        kamera.verschiebe(500, 0, 500);
        licht = new GLLicht();
        tastatur = new GLTastatur();
        himmel = new GLHimmel("src/img/Sterne.jpg");
        dasUfo = new Ufo();
        testTafel = new GLTafel(1300, -500, 0, 50, 50);

          asteroiden = new Asteroid[1];
          for (int i = 0; i < 1; i++) {
            asteroiden[i] = new Asteroid(100);
        }

        while (0 == 0) {
            ausfuehrung();
        }
    }
    public void ausfuehrung(){
        ufobewegung();
        asteroidbewegung();
        asteroidKoordinatenTest();
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
        for (int i = 0; i < 1; i++) {
            asteroiden[i].fallen();
        }
    }

    public void asteroidKoordinatenTest() {
        for (int i = 0; i < 1; i++) {
            asteroidPX = asteroiden[i].gibX();
            testTafel.setzeText("" + asteroidPX, 50);
        }
    }
    public void crash(){
     for (int i = 0; i < 1; i++) {

            ufoPX = dasUfo.gibX();
            ufoPY = dasUfo.gibY();
            asteroidPX = asteroiden[i].gibX();
            asteroidPY = asteroiden[i].gibY();

          if (((ufoPX<asteroidPX+100) & (ufoPX>asteroidPX-100)) & ((ufoPY<asteroidPY+100) & (ufoPY>asteroidPY-100)) ) {
           testTafel.setzeFarbe(1,0,0);
           Sys.warte(3000);
              testTafel.setzeFarbe(1,1,1);
              asteroiden[i].asteroidZuruecksetzen();
              dasUfo.ufoZuruecksetzen();
              Sys.warte(2000);
         }

     }
     }
}