
import jade.core.AID;

import jade.core.Agent;
import jade.core.behaviours.*;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class AgenteMainSlr extends Agent {
	
	private SlrGui myGui;
	
	protected void setup() {
		System.out.println("Hello World! My name is "+getLocalName());
		myGui = new SlrGui(this);
		myGui.showGui();
		
	}

	protected void calcular(double x){
		addBehaviour(new OneShotBehaviour() {
			public void action() {
				new Calculo(x);
			}
		} );
	}	

}


class SlrGui extends JFrame {
	private AgenteMainSlr myAgent;
	
	private JTextField x;
	
	SlrGui(AgenteMainSlr a){
		myAgent = a;
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(2, 2));
		p.add(new JLabel("Escribe el Advertising:"));
		x = new JTextField(15);
		p.add(x);
		getContentPane().add(p, BorderLayout.CENTER);
		JButton addButton = new JButton("Confirmar");
		addButton.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				try {
					double advertising = Double.valueOf(x.getText().trim());
					myAgent.calcular(advertising);
					x.setText("");
					}
				catch (Exception e) {
					JOptionPane.showMessageDialog(SlrGui.this, "Invalid values. "+e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE); 
				}
			}
		} );
		p = new JPanel();
		p.add(addButton);
		getContentPane().add(p, BorderLayout.SOUTH);
		
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

class Calculo {

	static final int ADVERTISING = 0;
	static final int SALES = 1;
	static final int N = 9;
	static final int TOTAL_COLUMNAS = 2;

	Calculo(double x){
		calculoIlr(x);
	}
	public void calculoIlr(double x){
		int muestra[][] = new int[TOTAL_COLUMNAS][N];
		double promedioAdvertising = 0, promedioSales = 0, desviacionAdvertising = 0, desviacionSales = 0;
		double covarianza = 0, sales, beta1 = 0, beta0 = 0, advertising = x;
		//double coeficientePearson = 0;
		muestra[ADVERTISING][0] = 23;
		muestra[ADVERTISING][1] = 26;
		muestra[ADVERTISING][2] = 30;
		muestra[ADVERTISING][3] = 34;
		muestra[ADVERTISING][4] = 43;
		muestra[ADVERTISING][5] = 48;
		muestra[ADVERTISING][6] = 52;
		muestra[ADVERTISING][7] = 57;
		muestra[ADVERTISING][8] = 58;
		muestra[SALES][0] = 651;
		muestra[SALES][1] = 762;
		muestra[SALES][2] = 856;
		muestra[SALES][3] = 1063;
		muestra[SALES][4] = 1190;
		muestra[SALES][5] = 1298;
		muestra[SALES][6] = 1421;
		muestra[SALES][7] = 1440;
		muestra[SALES][8] = 1518;
		// Imprime los valores
		//for(int i=0; i<TOTAL_COLUMNAS;i++)
			//for(int j=0; j<N;j++)
				//System.out.println(muestra[i][j]);
		for(int i = 0; i < N; i++) {
			promedioAdvertising += muestra[ADVERTISING][i];
			desviacionAdvertising += (muestra[ADVERTISING][i]*muestra[ADVERTISING][i]);
			promedioSales += muestra[SALES][i];
			desviacionSales += (muestra[SALES][i]*muestra[SALES][i]);
			covarianza += muestra[ADVERTISING][i] * muestra[SALES][i];
		}

		promedioAdvertising /= N;
		//System.out.println(promedioAdvertising);			
		promedioSales /= N;
		//System.out.println(promedioSales);
		desviacionAdvertising /= N;
		desviacionSales /= N;
		
		desviacionAdvertising -= (promedioAdvertising*promedioAdvertising);
		desviacionSales -= (promedioSales*promedioSales);
		
		desviacionAdvertising = Math.sqrt(desviacionAdvertising);
		desviacionSales = Math.sqrt(desviacionSales);
		
		covarianza /= N;
		covarianza -= promedioAdvertising * promedioSales;
		
		//coeficientePearson = covarianza/(desviacionAdvertising*desviacionSales);
				
		//System.out.println("Advertising: " + desviacionAdvertising + " | " + promedioAdvertising);
		//System.out.println("Sales: " + desviacionSales + " | " + promedioSales);
		//System.out.println("Covarianza: " + covarianza);
		//System.out.println("Coeficiente de correlaci�n de pearson: " +coeficientePearson);
		
		beta1 = covarianza / ( desviacionAdvertising * desviacionAdvertising);
		beta0 = -promedioAdvertising * beta1 + promedioSales;
		
		sales = beta1 * advertising + beta0;
		
		System.out.printf("y =  %.0fx + %.0f\n", beta1, beta0);
		
		System.out.printf("Sales: %.0f", sales);	
	}
}