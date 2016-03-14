package ImageProcessor;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

public class Main {

	
	
	public Main() {
		
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (Exception e) {
		    System.out.println("not found");
		}
		
		GUI ui = new GUI();
	}
	
	public static void main(String[] args) {
		
		
		new Main();
	
	}
	
}
	