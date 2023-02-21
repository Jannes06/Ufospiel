import GLOOP.*; 
public class Ufospiel {
    private GLKamera kamera;
    private GLLicht licht;
    private GLTastatur tastatur;
    private GLHimmel himmel;

    private Ufo dasUfo;

    public Ufospiel() {
        kamera = new GLSchwenkkamera();
        kamera.verschiebe(0,0 , 500);
        licht = new GLLicht();
        tastatur = new GLTastatur();
        himmel = new GLHimmel("src/img/Sterne.jpg");

        dasUfo = new Ufo();

        while (0 == 0) {
        ufobewegung();
        Sys.warte();
        }
    }

    public void ufobewegung() {
        //Bewegung nach links
        if (tastatur.links()) {
            dasUfo.bewegeLinks();
        }
        //GegenbewegungLinksRand
        if(dasUfo.gibX()<-700){
            dasUfo.bewegeRechts();
        }
        //Bewegung nach rechts
        if (tastatur.rechts()) {
            dasUfo.bewegeRechts();
        }
        //GegenbewegungRechtsRand
        if(dasUfo.gibX()>600){
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
}