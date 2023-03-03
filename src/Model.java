

// Ist das Objekt, in dem man das Blender Objekt sieht
import GLOOP.GLObjekt;
import GLOOP.GLTextur;
import GLOOP.GLVektor;
import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.glu.GLU;
import java.io.File;
import java.util.ArrayList;


    public class Model extends GLObjekt {
        private File file;
        private String ship;
        public Model(double pX, double pY, double pZ, double pLX, double pLY, double pLZ, File file,String pShip) {
            this(pX, pY, pZ, pLX, pLY, pLZ);
            this.file = file;
            ship = pShip;
        }
        public Model(double pX, double pY, double pZ, double pLX, double pLY, double pLZ,File file, GLTextur pT) {
            this(pX, pY, pZ, pLX, pLY, pLZ);
            this.setzeTextur(pT);
            this.file = file;
        }
        public Model(double pX, double pY, double pZ, double pLX, double pLY, double pLZ,File file) {
            this(pX, pY, pZ, pLX, pLY, pLZ);
            //this.setzeTextur(new GLTextur(pT));
            this.file = file;
        }
        public Model(double pX, double pY, double pZ, double pLX, double pLY, double pLZ) {
        }
        @Override
        public void zeichneObjekt(GL2 gl, GLU glu) {
            ArrayList<GLVektor> vertices = STLParser.importModel(this.file);
            //Made by TubYoub
            gl.glBegin(GL.GL_TRIANGLE_STRIP);
            gl.glEnable(GL.GL_DEPTH_TEST);
            //gl.glLineWidth(12.0f);
            for (int i = 0; i < vertices.size() / 3; i += 4) {
                GLVektor edge1 = vertices.get(i+1);
                GLVektor edge2 = vertices.get(i+2);

                edge1.gibKreuzprodukt(edge2).normiere();
                // gl.glNormal3d((float)edge1.x, (float)edge1.z, (float)edge1.y);
                gl.glNormal3d((float)vertices.get(i).x, (float)vertices.get(i).z, (float)vertices.get(i).y);
                gl.glVertex3f((float)vertices.get(i+1).x, (float)vertices.get(i+1).z, (float)vertices.get(i+1).y);
                gl.glVertex3f((float)vertices.get(i+2).x, (float)vertices.get(i+1).z, (float)vertices.get(i+2).y);
                gl.glVertex3f((float)vertices.get(i+3).x, (float)vertices.get(i+1).z, (float)vertices.get(i+3).y);
            }
            gl.glEnd();
        }
    }

