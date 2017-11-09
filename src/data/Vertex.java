package data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Vertex {
	private boolean isleft;
	private ArrayList<Edge> to;
	private ArrayList<Edge> from;

	private int totalflow;
	private Entry data;
    private boolean fixflag;
	
	public Vertex(boolean isleft, Entry data){
		this.isleft = isleft;
		totalflow = 0;
        to = new ArrayList<Edge>();
        from = new ArrayList<Edge>();
		this.data = data;
		fixflag = false;
	}
	
	public void addEdge(Edge e){
		to.add(e);
	}

	public void addFrom(Edge e) { from.add(e); }
	
	public boolean isLeft(){
	    return isleft;
	}

	public int overflow(int stream) {
		if(to.size()==0)
		    return stream;
		int flow = 0;
		
		Collections.shuffle(to);
		
		for(Edge e : to){
			flow += e.overflow(stream - flow);
			if(flow == stream) break;
		}
		totalflow += flow;
		return flow;
	}
	
	@Override
	public String toString(){
		// Case toString Sender
		if(isLeft()){
			String res = data.getContactInformation();
			for(Edge e : to){
				res = res + e.toString();
			}
			return res;
		}
		else // Case toString Receiver
		{
			return data.getAllInformation();
		}
	}

    public int getFlow() {
        return totalflow;
    }

    public int fixFlow(int residu) {
	    if(from.size()==0) // start is found
	        return residu;

	    if(fixflag) // if this vertex is already in the fixpath.
	        return 0;

	    fixflag=true;
        Collections.shuffle(from);

	    int remainder = residu;
	    for(Edge e : from) {
	        remainder -= e.fixFlow(remainder);
	        if(remainder==0)
	            break;
        }

        // When no path towards the start is possible
        if(remainder == residu){
            Collections.shuffle(to);
	        for(Edge e: to) {
                remainder -= e.reverseFixFlow(remainder);
                if(remainder==0)
                    break;
            }
        }

        fixflag=false;
        return residu-remainder;
    }
}
