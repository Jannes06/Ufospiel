import GLOOP.*;
public class Asteroid {
    GLKugel asteroid;
    private Ufo dasUfo;
    double speedRadius; //Damit man die Geschwindigkeit auf den Radius anpassen kann, und die Hitbox angegeben werden kann.

    double asteroidPX;
    double asteroidPY;
    double asteroidPZ;
    double ufoPX;
    double ufoPY;
    double ufoPZ;


    public Asteroid(Ufo pUfo,double Radius) {
        asteroid = new GLKugel(Math.random() * 1001, -500+Math.random() * 801, -1700+Math.random() * 600, Radius);
        asteroid.setzeTextur("src/img/Krater.jpg");
        asteroid.setzeSkalierung(Math.random()*1.1+0.4,Math.random()*1.1+0.4,Math.random()*1.1+0.4);
        speedRadius = Radius;
        dasUfo = pUfo;
    }

    public void fallen() {
        asteroid.verschiebe(0, 0, +20/speedRadius);
        asteroid.drehe(10/speedRadius,10/speedRadius,10/speedRadius);
        if (asteroid.gibZ() > 600) {
           asteroid.setzePosition(Math.random() * 1001,-500+Math.random() * 801,-1700+Math.random() * 600);

        }
    }

    public void asteroidZuruecksetzen(){

        asteroid.setzePosition(Math.random() * 1001,-500+Math.random() * 801,-1700+Math.random() * 600);

    }
    public double gibX(){
      return asteroid.gibX();
    }

    public double gibY(){
        return asteroid.gibY();
    }
    public double gibZ(){
        return asteroid.gibZ();
    }

    //public double radiusHitbox(){
      //  return speedRadius*0.90;
    //}

    public boolean crash() {

            ufoPX = dasUfo.gibX();
            ufoPY = dasUfo.gibY();
            ufoPZ = dasUfo.gibZ();
            asteroidPX = asteroid.gibX();
            asteroidPY = asteroid.gibY();
            asteroidPZ = asteroid.gibZ();
            double individuelleHitbox = speedRadius;
            //Hier wird gecheckt, ob das Ufo ein Asteroiden berührt.
            if (((ufoPX < asteroidPX + individuelleHitbox) & (ufoPX > asteroidPX - individuelleHitbox)) & ((ufoPY < asteroidPY + individuelleHitbox) & (ufoPY > asteroidPY - individuelleHitbox))& ((ufoPZ < asteroidPZ + individuelleHitbox) & (ufoPZ > asteroidPZ - individuelleHitbox))) {
                return true;

        }
            else {
                return false;
            }
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
