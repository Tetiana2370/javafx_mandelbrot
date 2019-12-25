package mandelbrot.set;

import mandelbrot.complex.Complex;
import javafx.scene.image.PixelWriter;
import javafx.scene.paint.Color;

public class MandelFractal implements ComplexDrawable {
    private double zoom = 150;
    private double xCen = 256;
    private double yCen = 256;
    private double r = 4.0;
    private final int MAX = 100;

    MandelFractal(double xc, double yc, double zoom){
        this.xCen = xc;
        this.yCen = yc;

        if(zoom != 0){
            this.zoom = zoom;
        }
    }

    private int isInSet(Complex point) {
        Complex z0 = new Complex(0, 0);
        for (int t = 0; t < MAX; t++) {
            if (z0.abs() > r) {
                return t;
            }
            z0.mul(z0).add(point);
        }
        return MAX;
    }

    public void draw(PixelWriter picWriter, Complex a, Complex b, int w, int h){
        // if it's not the first function call
        if(b.abs() != 0){
            System.out.println("in if");
            double  zoomVal=w/Math.max(Math.abs(a.re()-b.re()), Math.abs(a.im()-b.im()));
            zoom *= zoomVal;

            double rectCenterX;
            double rectCenterY;
            // find centers of x, y axes of selected rectangle
            rectCenterX=Math.min(a.re(), b.re())+Math.abs(a.re()-b.re())/2;
            rectCenterY=Math.min(a.im(), b.im())+Math.abs(a.im()-b.im())/2;
            // find relative position to center (more left/right & upper/ lower)
            xCen = xCen -rectCenterX;
            yCen = yCen -rectCenterY;
            // increase with moved center
            xCen =zoomVal*xCen+w/2;
            yCen =zoomVal*yCen+w/2;
        }

        for (int col = 0; col < w; col++) {
            for (int row = 0; row < h; row++) {

                Complex z0 = new Complex((col - xCen)/zoom, (row - yCen)/zoom);

                int saturation = isInSet(z0);
                Color color;
                if(saturation != 100) {
                    saturation = 150 - saturation;
                    color = Color.rgb(saturation, saturation, 240);
                }else{
                    // series goes to infinity: black color
                    color = Color.rgb(1, 6, 0);
                }
                picWriter.setColor(col, row, color);
            }
        }
    }

    // button sets new r value
    void setR(double r){
        this.r = r;
    }
    // ser default values of class fields
    void refresh(){
        this.zoom = 150;
        this.xCen = 256;
        this.yCen = 256;
    }
    // set new center & zoom
    void refresh(double cen, double zoom){
        this.zoom = zoom;
        this.xCen = cen;
        this.yCen = cen;
    }
    void setZoom(double zoom){
        this.zoom = zoom;
    }

}



