package data;

public class Edge {
	private Vertex from;
	private Vertex to;
	private int capacity;
	private int flow;

	public Edge(Vertex f, Vertex t, int c){
		from = f;
		from.addEdge(this);
		to = t;
		capacity = c;
		flow =0;
	}
	
	public int overflow(int stream){
		if((flow == capacity) || (stream == 0)) return 0;
		
		int f;
		if(stream > (capacity-flow)){
			f = to.overflow(capacity-flow);
		}
		else
		{
			f = to.overflow(stream);
		}
		flow += f;
		return f;
	}
	
	@Override
	public String toString(){
		if(flow == 0) return "";
		
		//Not so fancy way of splitting one Receiver from another
		return "X__X" + to.toString();
	}
}
