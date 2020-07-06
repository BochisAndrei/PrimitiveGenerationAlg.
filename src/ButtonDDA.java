import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ButtonDDA extends JButton implements Command{
    protected JLabel image;

    public ButtonDDA(JLabel image){
        super("DDA Alg.");
        this.image = image;
    }

    @Override
    public void execute() {
        BufferedImage processedImg = DocumentManager.getInstance().getImageCopy();
        int w = processedImg.getWidth();
        int h = processedImg.getHeight();
        //set 2 points (x0, y0) and (x1,y1)
        float x0 = 0 + w/3;
        float y0 = h - h/3;

        float x1 = w - w/3;
        float y1 = 0 + h/3;

        float dx = x1 - x0;
        float dy = y1 -y0;
        float x = x0, y = y0, xinc, yinc, steps;

        if(Math.abs(dx) > Math.abs(dy)){
            steps = Math.abs(dx);
        }else{
            steps = Math.abs(dy);
        }
        xinc = dx / steps;
        yinc = dy / steps;
        processedImg.setRGB(Math.round(x), Math.round(y), new Color(0,0,0).getRGB());
        for (int i=0; i<steps-1;i++){
            x = x + xinc;
            y = y + yinc;
            if(x0<w-1 && y0<h-1 && y0>0 &&x0 >0)
                processedImg.setRGB(Math.round(x), Math.round(y), new Color(0,0,0).getRGB());
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
