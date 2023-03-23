package old_code;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class OptionPane extends WindowAdapter {
	
	JFrame f;   
	
	public void OptionPaneExample(){
		f= new JFrame();
		f.addWindowListener(this);  
	    f.setSize(300, 300);  
	    f.setLayout(null);  
	    f.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);  
	    f.setVisible(true);  
	}  
	
	public void windowClosing(WindowEvent e) {  
	    int a=JOptionPane.showConfirmDialog(f,"Continue the fight ?");  
    	if(a==JOptionPane.YES_OPTION){  
   			f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
    	}  
    	if(a==JOptionPane.NO_OPTION) {
    		combat.AttamptFlee();
    		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
    	}
	}  
	
	 
	
}
