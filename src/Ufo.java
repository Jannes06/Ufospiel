import GLOOP.*;

public class Ufo {
    GLQuader ufo;

    public Ufo() {

        ufo = new GLQuader(0, 0, 0, 20, 40, 15);

    }

    public void bewegeLinks() {
        ufo.verschiebe(-1, 0, 0);
    }

    public void bewegeRechts() {
        ufo.verschiebe(1, 0, 0);
    }

    public void bewegeOben() {
        ufo.verschiebe(0, 1, 0);
    }

    public void bewegeUnten() {
        ufo.verschiebe(0, -1, 0);
    }

    public double gibX() {
        return ufo.gibX();
    }

    public double gibY() {
        return ufo.gibY();
    }


}
