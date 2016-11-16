package data;

import java.util.ArrayList;

public class Graph {
	private ArrayList<Vertex> vertices;
	private ArrayList<Edge> edges;
	private Vertex start;
	private Vertex end;
	private int size;
	
	public Graph(){
		start = new Vertex(true);
		end = new Vertex(false);
		end.setEnd();
		vertices = new ArrayList<Vertex>();
		edges = new ArrayList<Edge>();
		size = 0;
	}
	
	public void addData(String[] data){
		size++;
		Vertex left = new Vertex(true);
		left.setName(data[1], data[2], data[3]);
		Vertex right = new Vertex(false);
		right.setAllData(data);		
		
		edges.add(new Edge(start,left,4));
		edges.add(new Edge(right,end,4));
		
		for(Vertex v: vertices){
			if(!v.isLeft()) // connect the new left vertex to all the right vertices
				edges.add(new Edge(left,v,1));
			else			// connect all the left vertices to the new right vertex
				edges.add(new Edge(v,right,1));
		}
		
		vertices.add(left);
		vertices.add(right);
	}
	
	public void overflow(){
		start.overflow(Integer.MAX_VALUE);
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

	public void print() {
		System.out.println(String.join("\n\n",this.allToString()));		
	}
}
