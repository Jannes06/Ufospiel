import GLOOP.*;

import java.io.File;

public class FuelTank {
    //Model coin;
    GLTafel tank;
    private Ufo dasUfo;
    double ufoPX;
    double ufoPY;
    double ufoPZ;
    double tankPX;
    double tankPY;
    double tankPZ;
    public FuelTank(Ufo pUfo) {
        tank= new GLTafel (100+Math.random() * 601, -350 + Math.random() * 701, -5700 + Math.random() * 600,30,30);
        tank.drehe(0,0,180);
        tank.drehe(0,180,0);
        tank.setzeTextur("src/img/fuelTank.png");


        dasUfo = pUfo;
    }
    public void tankbewegen() {
        tank.verschiebe(0, 0, 1);
        if (tank.gibZ() > 600) {
            tank.setzePosition(100+Math.random() * 601, -350 + Math.random() * 701, -5700 + Math.random() * 600);
        }
    }
    public void tankZuruecksetzen() {
        tank.setzePosition(100+Math.random() * 601, -350 + Math.random() * 701, -5700 + Math.random() * 600);
    }

    public boolean collected() {
        ufoPX = dasUfo.gibX();
        ufoPY = dasUfo.gibY();
        ufoPZ = dasUfo.gibZ();
        tankPX = tank.gibX();
        tankPY = tank.gibY();
        tankPZ = tank.gibZ();
        double individuelleHitbox = 40;
        //Hier wird gecheckt, ob das Ufo den Tank berührt.
        if (((ufoPX < tankPX + individuelleHitbox) & (ufoPX > tankPX - individuelleHitbox)) & ((ufoPY < tankPY + individuelleHitbox) & (ufoPY > tankPY - individuelleHitbox)) & ((ufoPZ < tankPZ + individuelleHitbox+20) & (ufoPZ > tankPZ - individuelleHitbox))) {
            return true;

        } else {
            return false;
        }

    }
    public double gibX() {
        return tank.gibX();
    }

    public double gibY() {
        return tank.gibY();
    }

    public double gibZ() {
        return tank.gibZ();
    }

    }