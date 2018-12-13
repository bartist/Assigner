package data;

import data.vertex.EndVertex;
import data.vertex.StartVertex;

import java.util.ArrayList;

public class Graph {
	private ArrayList<Vertex> vertices;
	private ArrayList<Edge> edges;
	private Vertex start;
	private Vertex end;
	private int size;
	private int capacity;
	
	public Graph(int capacity){
		start = new StartVertex();
		end = new EndVertex();
		vertices = new ArrayList<Vertex>();
		edges = new ArrayList<Edge>();
		this.capacity = capacity;
		size = 0;
	}
	
	public void addData(Entry input){
		size++;
		Vertex left = new Vertex(true, input);
		Vertex right = new Vertex(false, input);
		
		edges.add(new Edge(start,left,capacity));
		edges.add(new Edge(right,end,capacity));
		
		for(Vertex v: vertices){
			if(!v.isLeft()) {   // connect the new left vertex to all the right vertices (if able)
                if(left.canSentTo(v) && v.canReceiveFrom(left))
                    edges.add(new Edge(left, v, 1));
            } else {            // connect all the left vertices to the new right vertex (if able)
                if(right.canReceiveFrom(v) && v.canSentTo(right))
                    edges.add(new Edge(v, right, 1));
            }
		}
		
		vertices.add(left);
		vertices.add(right);
	}
	
	public int overflow(){
		return start.overflow(Integer.MAX_VALUE);
	}
	
	public String[] allToString(){
		String[] res = new String[size];
		int i = 0;
		
		for(Vertex v: vertices){
			if(v.isLeft()){
				res[i] = v.toString();
				i++;
			}
				
		}
				
		return res;
	}

	public boolean isOptimalFlow(int resultFlow) {
		return resultFlow == capacity*vertices.size()/2;
	}

    public boolean fixFlow(int resultFlow) {
	    int residu = capacity*vertices.size()/2 - resultFlow;
	    int fixFlowResult = end.fixFlow(residu);
		return this.isOptimalFlow(fixFlowResult + resultFlow);
    }
}
