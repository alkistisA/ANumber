import java.io.*;
import java.lang.*;
import java.util.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class aNumber {
	
	public static void main (String[] args) {
		
		design designGUI = new design();	

        //Schedule a job for the event-dispatching thread
        //creating and showing this application's GUI

		SwingUtilities.invokeLater(new Runnable() {
          public void run() {
            designGUI.designInputWindow();
          }
        });

	}			
	
}

class design extends JFrame{
	
    createANASI fileANASI = new createANASI();	
	
	void designInputWindow () {
	    
		// Create the frame
		JFrame frame = new JFrame("Analysis of A-Number");
		//What happens when the frame closes
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel(new GridBagLayout());	
		panel.setBorder(BorderFactory.createEmptyBorder());
		
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets = new Insets(10, 10, 10, 10);
		
	   	JLabel insertPrintoutLabel = new JLabel("A-Number Analysis Data");
		constraints.gridx = 0;
		constraints.gridy = 0;		
        panel.add(insertPrintoutLabel,constraints);		
		
	   	JTextField insertPrintoutLocation = new JTextField(20);		
		insertPrintoutLocation.setEditable(false);
		constraints.gridx = 1;
        panel.add(insertPrintoutLocation,constraints);	

	    JButton browseButton = new JButton("Browse");
        browseButton.setFocusPainted(false);		
		constraints.gridx = 2;
        panel.add(browseButton,constraints);
		
	    JButton submitButton = new JButton("Submit");
        submitButton.setFocusPainted(false);	
		constraints.gridx = 1;
		constraints.gridy = 1;		
        panel.add(submitButton,constraints);		

        submitButton.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            fileANASI.writeANASIcommands();
          }          
        });	

		frame.add(panel);
		
		// disable button of resizing
		//frame.setResizable(false); 
        // shize the frame - give a suitable size to window automatically
		frame.pack(); 	
		// frame is located at the center of the screen
        frame.setLocationRelativeTo(null);  
        // make the frame visible		
		frame.setVisible(true);	

    }
	
}

class createANASI {
	
	void writeANASIcommands () {
		
      String PrintoutFileName = "ANumberAnalysisData.txt";
	  String ANASIFileName = "ANASI.txt";	  

      String s="";
	  
	  try {	  
		
	  File Printout = new File(PrintoutFileName);
	  File ANASI = new File(ANASIFileName);		  

	  Scanner readANASPPrintout = new Scanner(Printout);
	  
      BufferedWriter writeANASI = new BufferedWriter(new FileWriter(ANASI));		  

      while (readANASPPrintout.hasNextLine()) {
        s = readANASPPrintout.nextLine();		  
		if (s.equals("A-NUMBER                      ROUTINF   ACO   AO    L    MISCELL")) { break; }
      }	
	  
      while (readANASPPrintout.hasNextLine()) {
        s = readANASPPrintout.nextLine();
		if (s.equals("END")) { break; }  
        writeANASI.append("ANASI:A=");
		
        if ((s.length()<=30)) {
           writeANASI.append(s.substring(0,s.length()));	
		} else if (((s.length()>30)&&(s.length()<=40))) {
		   s = s.substring(0,30) + ",ROUTINF=" + s.substring(30,s.length());
		   s = s.replaceAll("\\s+",""); 
           writeANASI.append(s);		   
           //writeANASI.append(s.substring(0,30) + ",ROUTINF=" + s.substring(30,s.length()));	

		} else if (((s.length()>40)&&(s.length()<=46))) {
		   s = s.substring(0,30) + ",ROUTINF=" + s.substring(30,40) + ",ACO=" + s.substring(40,s.length());	
		   s = s.replaceAll("\\s+","");  
		   s = s.replaceAll("ROUTINF=,A","A");		   
           writeANASI.append(s);				
           //writeANASI.append(s.substring(0,30) + ",ROUTINF=" + s.substring(30,40) + ",ACO=" + s.substring(40,s.length()));	

		} else if (((s.length()>46)&&(s.length()<=52))) {
		   s = s.substring(0,30) + ",ROUTINF=" + s.substring(30,40) + ",ACO=" + s.substring(40,46) + ",AO=" + s.substring(46,s.length());				
		   s = s.replaceAll("\\s+","");  
		   s = s.replaceAll("ROUTINF=,A","A");	
		   s = s.replaceAll("ACO=,A","A");		   
           writeANASI.append(s);				
           //writeANASI.append(s.substring(0,30) + ",ROUTINF=" + s.substring(30,40) + ",ACO=" + s.substring(40,46) + ",AO=" + s.substring(46,s.length()));	

		} else if (((s.length()>52)&&(s.length()<=57))) {
		   s = s.substring(0,30) + ",ROUTINF=" + s.substring(30,40) + ",ACO=" + s.substring(40,46) + ",AO=" + s.substring(46,52) + ",L=" + s.substring(52,s.length());				
		   s = s.replaceAll("\\s+","");  
		   s = s.replaceAll("ROUTINF=,A","A");	
		   s = s.replaceAll("ACO=,A","A");
		   s = s.replaceAll("AO=,L","L");
           writeANASI.append(s);				
           //writeANASI.append(s.substring(0,30) + ",ROUTINF=" + s.substring(30,40) + ",ACO=" + s.substring(40,46) + ",AO=" + s.substring(46,52) + ",L=" + s.substring(52,s.length()));	

		} else {
		   s = s.substring(0,30) + ",ROUTINF=" + s.substring(30,40) + ",ACO=" + s.substring(40,46) + ",AO=" + s.substring(46,52) + ",L=" + s.substring(52,57) + "," + s.substring(57);				
		   s = s.replaceAll("\\s+","");  
		   s = s.replaceAll("ROUTINF=,A","A");	
		   s = s.replaceAll("ACO=,A","A");
		   s = s.replaceAll("AO=,L","L");
		   s = s.replaceAll("L=,F","F");		   
           writeANASI.append(s);				
           //writeANASI.append(s.substring(0,30) + ",ROUTINF=" + s.substring(30,40) + ",ACO=" + s.substring(40,46) + ",AO=" + s.substring(46,52) + ",L=" + s.substring(52,57) + "," + s.substring(57));	

        }		
		
        writeANASI.append(";");	
		writeANASI.newLine();			
      }		

      writeANASI.close();
      readANASPPrintout.close(); 
	  
      } catch (IOException e) {
		  
        System.err.println("\n" + "I/O error occured.");
		System.out.println();		
        e.printStackTrace();		  
		  
	  }			
		
	}
	
}	