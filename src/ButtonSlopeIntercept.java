import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ButtonSlopeIntercept extends JButton implements Command{
    protected JLabel image;

    public ButtonSlopeIntercept(JLabel image){
        super("Slope Intercept Alg.");
        this.image = image;
    }

    @Override
    public void execute() {
        BufferedImage processedImg = DocumentManager.getInstance().getImageCopy();
        int w = processedImg.getWidth();
        int h = processedImg.getHeight();
        //set 2 points (x0, y0) and (x1,y1)
        float x1 = 0 + w/3;
        float y1 = h - h/3;

        float x0 = w - w/3;
        float y0 = 0 + h/3;

        processedImg.setRGB(Math.round(x0), Math.round(y0), new Color(0,0,0).getRGB());

        float dx = x1 - x0;
        float dy = y1 - y0;

        if(Math.abs(dx) > Math.abs(dy)){
            float m = dy / dx;
            float b = y0 - (m*x0);
            dx = (x1 > x0)? 1 : -1;

            while(x0 != x1){
                x0 = x0 + dx;
                y0 = (m*x0) + b;
                if(x0<w-1 && y0<h-1 && y0>0 &&x0 >0)
                processedImg.setRGB(Math.round(x0), Math.round(y0), new Color(0,0,0).getRGB());
            }
        }
        setImage(processedImg, this.image);
    }

    public static void setImage(BufferedImage myPicture, JLabel picLabel){
        //add image into singleton
        DocumentManager.getInstance().setProcessedImage(myPicture);

        int height = myPicture.getHeight();
        int width = myPicture.getWidth();

        while(height>400 || width > 650){
            height = height /2;
            width = width /2 ;
        }
        Image scaled = myPicture.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        ImageIcon icon = new ImageIcon(scaled);
        picLabel.setIcon(icon);
        System.out.println("Edited image loaded");
    }
}
