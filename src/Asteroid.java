import GLOOP.*;
public class Asteroid {
    GLKugel asteroid;

    public Asteroid() {
        asteroid = new GLKugel((int) (Math.random() * 201), 0, 0, 10);

    }

    public void fallen() {
        asteroid.verschiebe(0, -1, 0);

        if (asteroid.gibY() < -600) {


        }
    }

}