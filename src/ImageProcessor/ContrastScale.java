package ImageProcessor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class ContrastScale extends JComponent implements ChangeListener {

	
 
    static double alpha =0.7;
    static double beta = 100;
    BufferedImage image;  
    JPanel gui;
    JLabel imageCanvas;
    
    public ContrastScale() 
    {
  	
    render();
    
    File img = new File("contrast.jpg");
    setBackground(Color.black);
       try {
           image = ImageIO.read(img);
       } catch (Exception ex) {
           ex.printStackTrace();
      
       }
     showContrastScale();
	 }
    
    public void render() {
    	
    	try{
     
    	System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        Mat source = Imgcodecs.imread("image.jpg",
        Imgcodecs.CV_LOAD_IMAGE_GRAYSCALE);
        Mat destination = new
        Mat(source.rows(),source.cols(),source.type());
        
        Imgproc.equalizeHist(source, destination);
        source.convertTo(destination, -1, alpha, beta);
        Imgcodecs.imwrite("contrast.jpg", destination);
        
        
        
           }catch (Exception e) {
	         System.out.println("error: " + e.getMessage());
	 }
    }
    
    
    public void initComponents() {
 	   
        if (gui == null) {
            gui = new JPanel(new BorderLayout());
            gui.setBorder(new EmptyBorder(5, 5, 5, 5));
            imageCanvas = new JLabel();
            JPanel imageCenter = new JPanel(new GridBagLayout());
            imageCenter.add(imageCanvas);
            JScrollPane imageScroll = new JScrollPane(imageCenter);
            imageScroll.setPreferredSize(new Dimension(200, 100));
            gui.add(imageScroll, BorderLayout.CENTER);
        }
    }
  
    
    
  
  public Container getGui() {
      initComponents();
      return gui;
  }
  
  public void setImage(Image image) {
 	   
      imageCanvas.setIcon(new ImageIcon(image));
  }
  

  private JSlider getControl() {
	  
      JSlider slider = new JSlider(JSlider.HORIZONTAL, 0, 400, 132);
      slider.setMajorTickSpacing(50);
      slider.setMinorTickSpacing(25);
      slider.setPaintTicks(true);
      slider.setPaintLabels(true);
      slider.addChangeListener(this);
      return slider;
  }
  
    public void showContrastScale() {
    	
 	   JFrame frame = new JFrame();
 	    
 	    frame.setTitle("ContrastScale");
 	    frame.setContentPane(this.getGui());
 	    this.setImage(this.image);

 	    
 	    frame.getContentPane().add(this .getControl(), "Last");
 	    frame.setSize(535, 475);
 	    frame.setLocation(200, 200);
 	    frame.setVisible(true);
    }


	@Override
	public void stateChanged(ChangeEvent e) {
		
		double value = ((JSlider) e.getSource()).getValue();
	 	 
	     alpha =   2 - (value / 100.0);
	 	 System.out.println(alpha);
	    render();
	    
	    File img = new File("contrast.jpg");
	    setBackground(Color.black);
	       try {
	           image = ImageIO.read(img);
	       } catch (Exception ex) {
	           ex.printStackTrace();
	      
	       } 
	       setImage(image);
 	
	}
	
}