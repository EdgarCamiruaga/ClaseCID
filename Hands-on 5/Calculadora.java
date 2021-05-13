package examples.universidad;

class Calculadora {

	static final int ADVERTISING = 0;
	static final int SALES = 1;
	static final int N = 9;
	static final int TOTAL_COLUMNAS = 2;

	Calculadora(string x){
		calculoIlr(x);
	}
	private void calculoIlr(string x){
		int muestra[][] = new int[TOTAL_COLUMNAS][N];
		double promedioAdvertising = 0, promedioSales = 0, desviacionAdvertising = 0, desviacionSales = 0;
		double covarianza = 0, sales, beta1 = 0, beta0 = 0, advertising = Double.parseDouble(x);
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