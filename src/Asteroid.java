import GLOOP.*;
public class Asteroid {
    GLKugel asteroid;

    public Asteroid(int Radius) {
        asteroid = new GLKugel(500+(Math.random() * 501), 0, 0, Radius);
        asteroid.setzeTextur("src/img/Krater.jpg");
    }

    public void fallen() {
        asteroid.verschiebe(0, -0.5, 0);

        if (asteroid.gibY() < -600) {
           asteroid.setzePosition(Math.random() * 501,500,0);

        }
    }

    public double gibX(){
      return asteroid.gibX();

    }

    public double gibY(){
        return asteroid.gibY();

    }
}
