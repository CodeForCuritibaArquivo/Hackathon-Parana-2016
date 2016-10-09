import java.util.ArrayList;

public class TestaGeraGrafo {
	private static final String V1 = "Terminal Campo Comprido";
	private static final String V2 = "U.S. Campo Comprido";
	private static final String V3 = "Imperial";
	private static final String V4 = "Mossungue";
	private static final String V5 = "Sao Grato";
	
	public static void main(String args[]){
		GeraGrafo grafo = new GeraGrafo();
		
		ArrayList<String> listaPontos = new ArrayList<>();
		listaPontos.add(V1);
		listaPontos.add(V2);
		listaPontos.add(V3);
		listaPontos.add(V4);
		listaPontos.add(V5);
		
		grafo.addVertex(listaPontos);
		
		grafo.addConnectionWeight(V1, V2);
		grafo.addConnectionWeight(V2, V3);
		grafo.addConnectionWeight(V3, V4);
		grafo.addConnectionWeight(V4, V5);
		
		grafo.incConnectionWeight(V1, V5);
		grafo.incConnectionWeight(V2, V5);
		grafo.incConnectionWeight(V3, V5);
		grafo.incConnectionWeight(V4, V5);
		grafo.incConnectionWeight(V5, V1);
		grafo.incConnectionWeight(V3, V1);
		grafo.incConnectionWeight(V1, V2);
		
		System.out.println(grafo.exportGraphModel());		
	}
}
