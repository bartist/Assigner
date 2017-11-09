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
		to.addFrom(this);
		capacity = c;
		flow = 0;
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

    public int fixFlow(int residu) {
        if((flow == capacity) || (residu == 0))
            return 0;

        int fix = pushFixFlow(residu,from);
        flow += fix;
        return fix;
    }

    public int reverseFixFlow(int residu) {
        if((flow == 0) || (residu == 0))
            return 0;

        int fix = pushFixFlow(residu,to);
        flow -= fix;
        return fix;
    }

    private int pushFixFlow(int residu, Vertex direction) {
        if(residu > (capacity-flow)){
            return direction.fixFlow(capacity-flow);
        }
        else
        {
            return direction.fixFlow(residu);
        }
    }
	@Override
	public String toString(){
		if(flow == 0)
			return "";

		return to.toString();
	}
}
