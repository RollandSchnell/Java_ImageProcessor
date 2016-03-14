package ImageProcessor;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ImageRendering extends JPanel{

	
	
	 private BufferedImage image;

	    public ImageRendering(String name) {
	       try {                
	          image = ImageIO.read(new File(name));
	       } catch (IOException ex) {
	            
	       }
	    }

	    @Override
	    protected void paintComponent(Graphics g) {
	        super.paintComponent(g);
	        this.setSize(new Dimension(750,400));
	        g.drawImage(image, 50, 0,500,375, null);           
	    }
}
