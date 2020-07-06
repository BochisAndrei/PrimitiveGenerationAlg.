import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;

public class DocumentManager {

    private static DocumentManager instance=null;
    private BufferedImage image;
    private BufferedImage processedImage;

    public BufferedImage getProcessedImage() {
        return processedImage;
    }

    public void setProcessedImage(BufferedImage processedImage) {
        this.processedImage = processedImage;
    }

    public BufferedImage getImageCopy() {
        return deepCopy(image);
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    private DocumentManager(){
    }

    public static DocumentManager getInstance(){
        if(instance==null){
            instance=new DocumentManager();
        }
        return instance;
    }
    static BufferedImage deepCopy(BufferedImage bi) {
        ColorModel cm = bi.getColorModel();
        boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
        WritableRaster raster = bi.copyData(null);
        return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
    }

}
