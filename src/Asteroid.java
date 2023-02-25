import GLOOP.*;
public class Asteroid {
    GLKugel asteroid;
    double speedRadius; //Damit man die Geschwindigkeit auf den Radius anpassen kann, und die Hitbox angegeben werden kann.

    public Asteroid(double Radius) {
        asteroid = new GLKugel(Math.random() * 1201, 700+Math.random() * 2001, 0, Radius);
        asteroid.setzeTextur("src/img/Krater.jpg");
        asteroid.setzeSkalierung(Math.random()*1.1+0.4,Math.random()*1.1+0.4,Math.random()*1.1+0.4);
        speedRadius = Radius;
    }

    public void fallen() {
        asteroid.verschiebe(0, -20/speedRadius, 0);
        asteroid.drehe(10/speedRadius,10/speedRadius,10/speedRadius);
        if (asteroid.gibY() < -600) {
           asteroid.setzePosition(Math.random() * 1201,700+Math.random() * 2001,0);

        }
    }

    public void asteroidZuruecksetzen(){

        asteroid.setzePosition(Math.random() * 1201,700+Math.random() * 2001,0);

    }
    public double gibX(){
      return asteroid.gibX();

    }

    public double gibY(){
        return asteroid.gibY();

    }

    public double radiusHitbox(){
        return speedRadius*0.95;
    }

    public void level1 () {
        asteroid.setzeTextur("src/img/Krater.jpg");
    }
    public void level2 () {
        asteroid.setzeTextur("src/img/img.png");
    }

    public void level3 (){
        asteroid.setzeTextur("src/img/Krater.jpg");
    }
    public void level4 (){
        asteroid.setzeTextur("src/img/img_1.png");
    }
}
