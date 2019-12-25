package mandelbrot.set;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.Image;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import mandelbrot.complex.Complex;

public class Controller {
    public Canvas canvas;										// "Płótno" do rysowania
    public TextField sizeField;
    public TextField imField;
    public TextField reField;
    private GraphicsContext gc;									// Kontekst graficzny do "płótna"
    public Slider slider;
    private double x1, y1, x2, y2;								// Współrzędne ramki
    private boolean mandelbrotIsActive;
    private int size = 512;
    private MandelFractal mandel = new MandelFractal(size/2, size/2, 0);
    

    public void initialize() {
        gc = canvas.getGraphicsContext2D();
        clear(gc);
        sizeField.setText("512");
        mandelbrotIsActive = false;

    }
    private void clear(GraphicsContext gc) {
        mandelbrotIsActive = false;
        gc.setFill(Color.WHITE);
        gc.setGlobalBlendMode(BlendMode.SRC_OVER);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }
    public void mouseMoves(MouseEvent mouseEvent) {
        double x = mouseEvent.getX();
        double y = mouseEvent.getY();

        gc.setGlobalBlendMode(BlendMode.DIFFERENCE);
        gc.setStroke(Color.WHITE);
        rect();
        x2 = x;
        y2 = y;
        rect();
    }

    public void mousePressed(MouseEvent mouseEvent) {
        x1 = mouseEvent.getX();
        y1 = mouseEvent.getY();
        x2 = x1;
        y2 = y1;
    }

    public void mouseReleased(MouseEvent mouseEvent) {
        rect();
        if(mandelbrotIsActive){
            zoomMandelbrot();
        }

    }

    public void clearCanvas() {
        mandelbrotIsActive = false;
        mandel.refresh();

        gc.setFill(Color.WHITE);
        gc.setGlobalBlendMode(BlendMode.SRC_OVER);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

    private void rect() {
        // Metoda rysuje prostokąt o rogach (x1, y1) i (x2, y2)
        double x = x1;
        double y = y1;
        double w = x2 - x1;
        double h = y2 - y1;

        if (w < 0) {
            x = x2;
            w = -w;
        }

        if (h < 0) {
            y = y2;
            h = -h;
        }

        gc.strokeRect(x + 0.5, y + 0.5, w, h);
    }

    public void drawMandelbrot()  {
        clearCanvas();
        mandel.setR(slider.getValue());
        if(sizeField.getText() != "" ){
            int value  =  Integer.parseInt(sizeField.getText());
            if(value > 0 & value < 512){
                this.size = value;
                mandel.refresh(this.size/2, 150.0/(512.0/this.size));
            }
        }



        WritableImage pic = new WritableImage(size, size);  //// change it
        PixelWriter picWriter = pic.getPixelWriter();
    
        mandel.draw(picWriter,  new Complex(size/2, size/2), new Complex(0, 0), size, size);

        gc.setGlobalBlendMode(BlendMode.SRC_OVER);
        gc.drawImage(pic, 0, 0, size, size);
        mandelbrotIsActive = true;

    }
    private void zoomMandelbrot(){
        WritableImage pic = new WritableImage(size, size);  //// change it
        PixelWriter picWriter = pic.getPixelWriter();
        mandel.draw(picWriter,  new Complex(x1, y1), new Complex(x2, y2), size, size);

        gc.setGlobalBlendMode(BlendMode.SRC_OVER);
        gc.drawImage(pic, 0, 0, size, size);

    }

    public void onSliderChanged(MouseEvent mouseEvent) {
        System.out.println("r = " + slider.getValue());
        mandel.setR(slider.getValue());
    }

    public void setComplex(ActionEvent actionEvent) {
        if(reField.getText() != null && imField.getText()!=null){
            clearCanvas();
            double re = Double.parseDouble(reField.getText());
            double im = Double.parseDouble(imField.getText());
            WritableImage pic = new WritableImage(size, size);  
            PixelWriter picWriter = pic.getPixelWriter();
            mandel = new MandelFractal(re, im, 0);
            mandel.draw(picWriter,  new Complex(re/2, im/2), new Complex(0, 0), size, size);

            gc.setGlobalBlendMode(BlendMode.SRC_OVER);
            gc.drawImage(pic, 0, 0, size, size);
            mandelbrotIsActive = true;
        }


        System.out.println("setting new complex2");

    }

}
