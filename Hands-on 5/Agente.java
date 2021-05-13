package examples.universidad;

import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;

public class Agente extends Agent {

    private Interfaz myGui;
    private Calculadora myCalc;

    protected void setup() {
        System.out.println("Agent "+getLocalName()+" started.");
        myGui = new Interfaz(this);
        myGui.showGui();
        addBehaviour(new MyOneShotBehaviour());
    }

    public void calcular(final String x) {
		addBehaviour(new OneShotBehaviour() {
			public void action() {
				myCalc = new Calculadora(x);
			}
		} );
	}    
}


