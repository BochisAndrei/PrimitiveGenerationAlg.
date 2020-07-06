import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ButtonMidPoint extends JButton implements Command{

    protected JLabel image;

    public ButtonMidPoint(JLabel image){
        super("Mid Point Alg.");
        this.image = image;
    }

    @Override
    public void execute() {
        BufferedImage processedImg = DocumentManager.getInstance().getImageCopy();
        int w = processedImg.getWidth();
        int h = processedImg.getHeight();

        //midpoint cordinates
        int xc = w/2;
        int yc = h/2;
        //radius
        int r = 50;

        int x = r;
        int y = 0;
        int p = 1 - r;

        circlePoints(xc,yc,x,y,processedImg);
        while( x > y ){
            y = y+1;

            if(p <= 0){
                p = p + 2*y + 1;
            }else{
                x = x-1;
                p = p + 2*(y-x) + 1;
            }
            circlePoints(xc, yc, x, y, processedImg);
        }
        setImage(processedImg, this.image);

    }

    public static void circlePoints(int xc, int yc, int x, int y, BufferedImage img){
        if(x == 0){
            img.setRGB(xc, yc + y, new Color(0,0,0).getRGB());
            img.setRGB(xc, yc - y, new Color(0,0,0).getRGB());
            img.setRGB(xc + y, yc, new Color(0,0,0).getRGB());
            img.setRGB(xc - y, yc, new Color(0,0,0).getRGB());
        }
        if(x == y){
            img.setRGB(xc + x, yc + y, new Color(0,0,0).getRGB());
            img.setRGB(xc - x, yc + y, new Color(0,0,0).getRGB());
            img.setRGB(xc + x, yc - y, new Color(0,0,0).getRGB());
            img.setRGB(xc - x, yc - y, new Color(0,0,0).getRGB());
        }
        if(x > y){
            img.setRGB(xc + x, yc + y, new Color(0,0,0).getRGB());
            img.setRGB(xc - x, yc + y, new Color(0,0,0).getRGB());
            img.setRGB(xc + x, yc - y, new Color(0,0,0).getRGB());
            img.setRGB(xc - x, yc - y, new Color(0,0,0).getRGB());

            img.setRGB(xc + y, yc + x, new Color(0,0,0).getRGB());
            img.setRGB(xc - y, yc + x, new Color(0,0,0).getRGB());
            img.setRGB(xc + y, yc - x, new Color(0,0,0).getRGB());
            img.setRGB(xc - y, yc - x, new Color(0,0,0).getRGB());
        }
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
