import GLOOP.*;

import java.io.File;

public class Schild {
    //Model coin;
    GLTafel schild;
    private Ufo dasUfo;
    double ufoPX;
    double ufoPY;
    double ufoPZ;
    double schildPX;
    double schildPY;
    double schildPZ;
    public Schild(Ufo pUfo) {
        schild= new GLTafel (100+Math.random() * 601, -350 + Math.random() * 701, -6700 - Math.random() * 6000,30,30);
        schild.drehe(0,0,180);
        schild.drehe(0,180,0);
        schild.setzeTextur("src/img/Schild.png");


        dasUfo = pUfo;
    }
    public void schildbewegen() {
        schild.verschiebe(0, 0, 1);
        if (schild.gibZ() > 600) {
            schild.setzePosition(100+Math.random() * 601, -350 + Math.random() * 701, -6700 - Math.random() * 6000);
        }
    }
    public void Zuruecksetzen() {
        schild.setzePosition(100+Math.random() * 601, -350 + Math.random() * 701, -8700 - Math.random() * 6000);
    }

    public boolean collected() {
        ufoPX = dasUfo.gibX();
        ufoPY = dasUfo.gibY();
        ufoPZ = dasUfo.gibZ();
        schildPX = schild.gibX();
        schildPY = schild.gibY();
        schildPZ = schild.gibZ();
        double individuelleHitbox = 40;
        //Hier wird gecheckt, ob das Ufo den Tank berührt.
        if (((ufoPX < schildPX + individuelleHitbox) & (ufoPX > schildPX - individuelleHitbox)) & ((ufoPY < schildPY + individuelleHitbox) & (ufoPY > schildPY - individuelleHitbox)) & ((ufoPZ < schildPZ + individuelleHitbox+20) & (ufoPZ > schildPZ - individuelleHitbox))) {
            return true;

        } else {
            return false;
        }

    }
    public double gibX() {
        return schild.gibX();
    }

    public double gibY() {
        return schild.gibY();
    }

    public double gibZ() {
        return schild.gibZ();
    }

}
