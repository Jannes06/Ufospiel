import GLOOP.*;
public class Schuss {
    GLZylinder schussRechts, schussLinks;
    private Ufo dasUfo;


    public Schuss(Ufo pUfo) {
      dasUfo = pUfo;
       schussRechts = new GLZylinder(dasUfo.gibX()+5,dasUfo.gibY(),dasUfo.gibZ()-30,0.8,10) ;
       schussRechts.drehe(0,0,0);
       schussRechts.setzeGlanz(1000,0,0,1);

       schussLinks = new GLZylinder(dasUfo.gibX()-5,dasUfo.gibY(),dasUfo.gibZ()-30,0.8,10) ;
       schussLinks.drehe(0,0,0);
       schussLinks.setzeGlanz(1000,0,0,1);

    }

public void abschuss(){
  schussLinks.verschiebe(0,0,-1);
  schussRechts.verschiebe(0,0,-1);




}
    public void daneben() {
        if (schussLinks.gibZ() < -600) {
         schussLinks.loesche();
         schussRechts.loesche();
        }
    }
    public void loesche(){
        schussLinks.loesche();
        schussRechts.loesche();
    }

    public float gibX(){
    return schussLinks.gibX()+5;
    }
    public float gibY(){
        return schussLinks.gibY();
    }
    public float gibZ(){
        return schussLinks.gibZ();
    }
}