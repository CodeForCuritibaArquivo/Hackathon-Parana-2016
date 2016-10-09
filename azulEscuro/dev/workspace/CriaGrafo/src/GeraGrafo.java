import java.util.ArrayList;
import java.util.List;

import org.jgrapht.alg.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph; 
import org.jgrapht.*; 


public class GeraGrafo {
	 SimpleWeightedGraph<String, DefaultWeightedEdge>  graph ;
    
    
    public GeraGrafo(){
    	this.graph = new SimpleWeightedGraph<String, DefaultWeightedEdge>(DefaultWeightedEdge.class); 
    }
    
    public void addVertex(ArrayList<String> vertices){
    	for (String string : vertices) {
			graph.addVertex(string);
		}
    	
    }

    public void addConnectionWeight(String v1, String v2){
    	this.graph.addEdge(v1, v2);
    	
        DefaultWeightedEdge e10 = graph.getEdge(v1, v2);
        
        graph.setEdgeWeight(e10, 0); 
    }

    
    public void incConnectionWeight(String v1, String v2){
    	DijkstraShortestPath<SimpleWeightedGraph<String, DefaultWeightedEdge> , DefaultWeightedEdge> pathSolver = new DijkstraShortestPath(
                graph, v1, v2);
    	
    	 List<DefaultWeightedEdge> edgeList = pathSolver.getPathEdgeList();
    	 for (int i = 0; i < edgeList.size() - 1; i++) {
    		 DefaultWeightedEdge e10 = graph.getEdgeWeight(edgeList.get(i), edgeList.get(i + 1));
    	     graph.setEdgeWeight(e10, graph.getEdgeWeight(e10) + 1); 
			
		}
       
    }

	public String exportGraphModel() {
		 return graph.toString();
	}

	
	
}
