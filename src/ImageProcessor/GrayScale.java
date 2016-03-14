package ImageProcessor;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GrayScale extends JPanel {

	BufferedImage image;
	 int width;
	 int height;
	 
	 public GrayScale() {
		 
	 try {
	 File input = new File("image.jpg");
	 image = ImageIO.read(input);
	 width = image.getWidth();
	 height = image.getHeight();
	 for(int i=0; i<height; i++){
		 
		 for(int j=0; j<width; j++){
			 
			 Color c = new Color(image.getRGB(j, i));
			 int red = (int)(c.getRed() * 0.299);
			 int green = (int)(c.getGreen() * 0.587);
			 int blue = (int)(c.getBlue() *0.114);
			 Color newColor = new Color(red+green+blue,
			 red+green+blue,red+green+blue);
			 image.setRGB(j,i,newColor.getRGB());
			 }
			 }
	         File ouptut = new File("grayscale.jpg");
		     ImageIO.write(image, "jpg", ouptut);
	         showGray();
			
			 } catch (Exception e) {
			 
        	 System.out.println("Error");
			 }
	 }
	 
	 
	 protected void paintComponent(Graphics g) {
	        super.paintComponent(g);
	        
	        
	        this.setSize(new Dimension(650,375));
	        g.drawImage(image, 0, 0,500,375, null);           
	    }
	 
	 public void showGray() {
		 
		 JFrame frame = new JFrame("GrayScale");
		 frame.setSize(new Dimension(510,410));
		
		 frame.setLocation(200,200);
		 frame.setVisible(true);
		 frame.setResizable(true);
		 frame.setContentPane(this);
		 //frame.add(this);
		 
	 }
	 
			 
	 
	}
