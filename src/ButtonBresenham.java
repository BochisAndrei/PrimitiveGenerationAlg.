import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ButtonBresenham extends JButton implements Command {
    protected JLabel image;

    public ButtonBresenham(JLabel image){
        super("Bresenham Alg.");
        this.image = image;
    }
    @Override
    public void execute() {
        BufferedImage processedImg = DocumentManager.getInstance().getImageCopy();
        int w = processedImg.getWidth();
        int h = processedImg.getHeight();
        //set 2 points (x0, y0) and (x1,y1)
        int x0 = 0 + w/3;
        int y0 = 0 + h/3;

        int x1 = w - w/3;
        int y1 = h - h/3;

        int dx = x1 - x0;
        int dy = y1 - y0;
        int p = 2*dy - dx, y = y0;
        for(int x = x0; x < x1; x++){
            if(p>=0){
                p = p + 2*dy - 2*dx;
                y = y+1;
            }else{
                p = p + 2*dy;
            }
            processedImg.setRGB(x, y, new Color(0,0,0).getRGB());

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
