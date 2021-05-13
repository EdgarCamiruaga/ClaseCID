package examples.universidad;

import jade.core.AID;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
  @author Giovanni Caire - TILAB
 */
class Interfaz extends JFrame {	
	private Agente myAgent;
	
	private JTextField myX;
	
	Interfaz(Agente a) {
		super(a.getLocalName());
		
		myAgent = a;
		
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(2, 2));
		p.add(new JLabel("X = "));
		myX = new JTextField(15);
		p.add(myX);
		getContentPane().add(p, BorderLayout.CENTER);
		
		JButton addButton = new JButton("Calcular");
		addButton.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				try {
					String x = myX.getText().trim();
					myAgent.calcular(x);
					myX.setText("");
				}
				catch (Exception e) {
					JOptionPane.showMessageDialog(Interfaz.this, "Invalid values. "+e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE); 
				}
			}
		} );
		p = new JPanel();
		p.add(addButton);
		getContentPane().add(p, BorderLayout.SOUTH);
		
		// Make the agent terminate when the user closes 
		// the GUI using the button on the upper right corner	
		addWindowListener(new	WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				myAgent.doDelete();
			}
		} );
		
		setResizable(false);
	}
	
	public void showGui() {
		pack();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int centerX = (int)screenSize.getWidth() / 2;
		int centerY = (int)screenSize.getHeight() / 2;
		setLocation(centerX - getWidth() / 2, centerY - getHeight() / 2);
		super.setVisible(true);
	}	
}