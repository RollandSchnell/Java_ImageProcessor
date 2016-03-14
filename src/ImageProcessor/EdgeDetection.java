package ImageProcessor;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;








import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class EdgeDetection extends JPanel  {

	BufferedImage image;
	
	public  EdgeDetection() {
		
		try {
	         int kernelSize = 9;
	         System.loadLibrary( Core.NATIVE_LIBRARY_NAME );
	         
	         Mat source = Imgcodecs.imread("grayscale.jpg", Imgcodecs.CV_LOAD_IMAGE_GRAYSCALE);
	         Mat destination = new Mat(source.rows(),source.cols(),source.type());
	         
	         Mat kernel = new Mat(kernelSize,kernelSize, CvType.CV_32F){
	            {
	               put(0,0,-1);
	               put(0,1,0);
	               put(0,2,1);

	               put(1,0-1);
	               put(1,1,0);
	               put(1,2,1);

	               put(2,0,-1);
	               put(2,1,0);
	               put(2,2,1);
	            }
	         };	 
	         
	         Imgproc.filter2D(source, destination, -1, kernel);
	         Imgcodecs.imwrite("output.jpg", destination);
	         
	      } catch (Exception e) {
	         System.out.println("Error: " + e.getMessage());
	      }
	
		
	showEdge();
		
	}

	protected void paintComponent(Graphics g) {
		
	        super.paintComponent(g);
	        File input = new File("output.jpg");
	   	    try {
				image = ImageIO.read(input);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        this.setSize(new Dimension(650,375));
	        g.drawImage(image, 0, 0,500,375, this);           
	    }
	 
	 public void showEdge() {
		 
		 JFrame frame = new JFrame("EdgeDetection");
		 frame.setSize(new Dimension(510,410));
		
		 frame.setLocation(200,200);
		 frame.setVisible(true);
		 frame.setResizable(true);
		 frame.setContentPane(this);
		 //frame.add(this);
		 
	 }
	
	
	
	
	
}
