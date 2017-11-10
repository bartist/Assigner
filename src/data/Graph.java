package data;

import java.util.ArrayList;

public class Graph {
	private ArrayList<Vertex> vertices;
	private ArrayList<Edge> edges;
	private Vertex start;
	private Vertex end;
	private int size;
	
	public Graph(){
		start = new Vertex(true, null);
		end = new Vertex(false, null);
		vertices = new ArrayList<Vertex>();
		edges = new ArrayList<Edge>();
		size = 0;
	}
	
	public void addData(Entry input){
		size++;
		Vertex left = new Vertex(true, input);
		Vertex right = new Vertex(false, input);
		
		edges.add(new Edge(start,left,4));
		edges.add(new Edge(right,end,4));
		
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

	public boolean flowcheck(int resflow) {
		return resflow == 4*vertices.size()/2;
	}

    public void fixFlow(int resflow) {
	    int residu = 4*vertices.size()/2 - resflow;
	    if(end.fixFlow(residu) != residu)
            System.out.println("No viable solution");
    }
}
