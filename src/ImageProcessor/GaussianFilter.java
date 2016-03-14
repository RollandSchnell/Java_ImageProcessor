package ImageProcessor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
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
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class GaussianFilter extends JComponent implements ChangeListener {

	static int alpha = 1;
	BufferedImage image; 
    JPanel gui;
    JLabel imageCanvas;
	 
	public GaussianFilter() {
		
		render();
		
		File img = new File("GaussianFilteredImage.jpg");
	      setBackground(Color.black);
	         try {
	             image = ImageIO.read(img);
	         } catch (Exception ex) {
	             ex.printStackTrace();
	        
	         }
		
		showGaussianFilter();
		
		
	}
	
	private void showGaussianFilter() {
		
JFrame frame = new JFrame();
	    
	    frame.setTitle("GaussianFilter");
	    frame.setContentPane(this.getGui());
	    this.setImage(this.image);

	    
	    frame.getContentPane().add(this .getControl(), "Last");
	    frame.setSize(535, 475);
	    frame.setLocation(200, 200);
	    frame.setVisible(true);
		
	}

	public void render() {
		
		try {
	         System.loadLibrary( Core.NATIVE_LIBRARY_NAME );
	         
	         Mat source = Imgcodecs.imread("image.jpg",
	         Imgcodecs.CV_LOAD_IMAGE_COLOR);
	         
	         Mat destination = new Mat(source.rows(),source.cols(),source.type());
	         Imgproc.GaussianBlur(source, destination,new Size(alpha,alpha), 0);
	         Imgcodecs.imwrite("GaussianFilteredImage.jpg", destination);
	      
	      } catch (Exception e) {
	         System.out.println("Error: " + e.getMessage());
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
	     JSlider slider = new JSlider(JSlider.HORIZONTAL, 1, 51, 1);
	     slider.setMajorTickSpacing(50);
	     slider.setMinorTickSpacing(25);
	     slider.setPaintTicks(true);
	     slider.setPaintLabels(true);
	     slider.addChangeListener(this);
	     return slider;
	 }
	 
	 
	@Override
	public void stateChanged(ChangeEvent e) {
		
		
		alpha = ((JSlider) e.getSource()).getValue();
		
		if( (alpha % 2) == 1) {
			//System.out.println(alpha);
		render();
		 
		}
		   File img = new File("GaussianFilteredImage.jpg");
		   setBackground(Color.black);
		      try {
		          image = ImageIO.read(img);
		      } catch (Exception ex) {
		          ex.printStackTrace();
		     
		      } 
		      setImage(image);
		
		}
	
	
	
	

}
