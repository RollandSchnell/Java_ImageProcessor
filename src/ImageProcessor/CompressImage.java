package ImageProcessor;

import java.io.*;
import java.util.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.image.*;

import javax.imageio.*;
import javax.imageio.stream.ImageOutputStream;
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

 public class CompressImage extends JComponent implements ChangeListener{

	 BufferedImage image; 
     JPanel gui;
	 JLabel imageCanvas;
	 float scale = 1;
	 
   public CompressImage() throws IOException {
	   
	   render();
	   
	      
	      File img = new File("compress.jpg");
	      setBackground(Color.black);
	         try {
	             image = ImageIO.read(img);
	         } catch (Exception ex) {
	             ex.printStackTrace();
	        
	         }
	       
	   
	   //////
    
      showCompresedImage();
   }
   
   public void render() throws IOException {
	   
	   File input = new File("image.jpg");
	      image = ImageIO.read(input);

	      File compressedImageFile = new File("compress.jpg");
	      OutputStream os =new FileOutputStream(compressedImageFile);

	      Iterator<ImageWriter>writers =  ImageIO.getImageWritersByFormatName("jpg");
	      ImageWriter writer = (ImageWriter) writers.next();

	      ImageOutputStream ios = ImageIO.createImageOutputStream(os);
	      writer.setOutput(ios);

	      ImageWriteParam param = writer.getDefaultWriteParam();
	      
	      param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
	      param.setCompressionQuality(scale);
	      writer.write(null, new IIOImage(image, null, null), param);
	      
	      os.close();
	      ios.close();
	      writer.dispose();
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
     JSlider slider = new JSlider(JSlider.HORIZONTAL, 0, 100, 0);
     slider.setMajorTickSpacing(50);
     slider.setMinorTickSpacing(25);
     slider.setPaintTicks(true);
     slider.setPaintLabels(true);
     slider.addChangeListener(this);
     return slider;
 }
 
   public void showCompresedImage() {
   	
	   JFrame frame = new JFrame();
	    
	    frame.setTitle("ImageCompress");
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
	 
    scale = 1 - (float) (value / 100.0);
	 
   try {
	render();
} catch (IOException e1) {
	
	e1.printStackTrace();
}
   
  
   File img = new File("compress.jpg");
   setBackground(Color.black);
      try {
          image = ImageIO.read(img);
      } catch (Exception ex) {
          ex.printStackTrace();
     
      } 
      setImage(image);
}
	

}