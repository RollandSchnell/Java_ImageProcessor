package ImageProcessor;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Point;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class GUI extends JFrame {

	
	JButton brightnessbutton = new JButton("BrightnessScale");
	JButton compressbutton = new JButton("CompressImage");
	JButton sharpbutton = new JButton("SharpnessScale");
	JButton grayscale = new JButton("GrayScale");
	JButton contrastbutton = new JButton("ContrastScale");
	JButton gaussianbutton = new JButton("GaussianFilter");
	JButton edgebutton = new JButton("EdgeDetection");
	GridBagLayout layout = new GridBagLayout();
	GridBagConstraints cs = new GridBagConstraints();
	JPanel panel = new JPanel();
	JLabel label = new JLabel("Original:");
	BorderLayout mainLayout = new BorderLayout();
	
	public GUI() {
		
		super("Image Processor Demo");
		
	    events(true);
		panel.setLayout(layout);
		panel.setSize(new Dimension(650,200));
		//
		cs.anchor = GridBagConstraints.WEST;
		
	    cs.insets = new Insets(0,10,20,0);
	    
	    cs.gridx = 0;
	    cs.gridy = 0;
	    
	    panel.add(grayscale,cs);
	    
	    cs.gridx = 0;
	    cs.gridy = 1;
	    
	    
	    panel.add(brightnessbutton,cs);
    
	    cs.gridx = 1;
	    cs.gridy = 1;
	    
	    label.setFont(new Font("Serif", Font.PLAIN, 20));
		panel.add(label,cs);
	
        cs.gridx = 0;
	    cs.gridy = 2;
	  
	    
	    panel.add(compressbutton,cs);
	    
	    
	    cs.gridx = 0;
	    cs.gridy = 3;
	    
	    panel.add(sharpbutton,cs);
	    
	    cs.gridx = 0;
	    cs.gridy = 4;
	    
	    panel.add(contrastbutton,cs);
	    
	    cs.gridx = 0;
	    cs.gridy = 5;
	    
	    panel.add(gaussianbutton,cs);
	    
	    cs.gridx = 0;
	    cs.gridy = 6;
	    
	    panel.add(edgebutton,cs);
   //
	    
	    this.setLayout(mainLayout);
	    this.add(panel,BorderLayout.WEST);
	    this.add(new ImageRendering("image.jpg"),BorderLayout.CENTER);
	
		this.setSize(new Dimension(750,400));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocation(new Point(375,125));
		this.setResizable(true);
		this.setVisible(true);
		
	}
	
	public void events(Boolean x) {
		
		if(x) {
			
		grayscale.addActionListener(e -> {
			
			new GrayScale();
		});
		
		contrastbutton.addActionListener(e -> {
			
			new ContrastScale();
		});
		
		brightnessbutton.addActionListener(e -> {
			
			new BrightnessScale();
		});
		
		sharpbutton.addActionListener(e -> {
			
			new SharpnessScale();
		});
		
		compressbutton.addActionListener(e -> {
			
			try {
				new CompressImage();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
		gaussianbutton.addActionListener(e -> {
			
			new GaussianFilter();
		});
		
		edgebutton.addActionListener(e -> {
			
			new EdgeDetection();
		});
		} else {
			System.out.println("Items disabled");
		}
	}
	

}