import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;


public class EditorFrame extends JFrame implements ActionListener {

    protected JLabel picLabel; //original pic
    protected JLabel edited = new JLabel();

    protected JPanel panelMsg;//panel for text original/edited photo
    protected JPanel panelImage;//panel for the image
    protected JPanel p2; //panel for all the filters
    protected JPanel p3; //panel for save button
    protected JPanel pAll; //panel where all the panels are addade on y axis

    protected JButton slopeIntercept;
    protected JButton dda;
    protected JButton bresenham;
    protected JButton midPoint;
    protected JButton deCasteljau;
    protected JButton deBoor;
    protected JButton save;

    protected String path = "./src/startPhoto.jpg";


    public EditorFrame(){
        setResizable(false);
        setTitle("Editare photo");

        //edited/original text labels
        this.panelMsg = new JPanel();
        this.edited.setText("Edited");
        this.edited.setPreferredSize(new Dimension(550, 20));
        this.edited.setHorizontalAlignment(JLabel.CENTER);
        this.panelMsg.add(edited);

        //set start photo
        this.panelImage = new JPanel();
        this.picLabel = setImage(this.path);
        this.panelImage.add(picLabel);

        //initialize buttons
        this.slopeIntercept = new ButtonSlopeIntercept(this.picLabel);
        this.slopeIntercept.addActionListener(this);
        this.dda = new ButtonDDA(this.picLabel);
        this.dda.addActionListener(this);
        this.bresenham = new ButtonBresenham(this.picLabel);
        this.bresenham.addActionListener(this);
        this.midPoint = new ButtonMidPoint(this.picLabel);
        this.midPoint.addActionListener(this);
        //this.deCasteljau = new ButtonDeCasteljau(this.picLabel);
        //this.deCasteljau.addActionListener(this);
        //this.deBoor = new ButtonDeBoor(this.picLabel);
        //this.deBoor.addActionListener(this);
        this.save = new ButtonSave();
        this.save.addActionListener(this);

        //filter selector
        this.p2=new JPanel();
        this.p2.add(slopeIntercept);
        this.p2.add(dda);
        this.p2.add(bresenham);
        this.p2.add(midPoint);
        //this.p2.add(deCasteljau);
        //this.p2.add(deBoor);
        this.p2.setBorder(BorderFactory.createTitledBorder("Filter"));
        this.p2.setBounds(10, 36, 227, 83);

        //submit button
        this.p3=new JPanel();
        this.p3.add(save);

        //add all panels to pAll
        this.pAll=new JPanel();
        this.pAll.add(panelMsg);
        this.pAll.add(panelImage);
        this.pAll.add(p2);
        this.pAll.add(p3);

        //set y axis box layout and than pack the GUI
        BoxLayout layout = new BoxLayout(pAll, BoxLayout.Y_AXIS); //structurare layout
        this.pAll.setLayout(layout);
        this.add(pAll);
        this.pack();
        super.setVisible(true);
        super.setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

    //static func for setting the first images when the app is opened
    public static JLabel setImage(String path){
        BufferedImage myPicture = null;
        try {
            myPicture = ImageIO.read(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        int height = myPicture.getHeight();
        int width = myPicture.getWidth();
        DocumentManager.getInstance().setImage(myPicture);

        while(height>513){
            height = height /3;
            width = width /3 ;
        }
        Image scaled = myPicture.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        ImageIcon icon = new ImageIcon(scaled);
        JLabel picLabel = new JLabel();
        picLabel.setIcon(icon);
        return picLabel;
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        ((Command)e.getSource()).execute();
    }

}
