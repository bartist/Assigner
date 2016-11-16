package data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Vertex {
	private boolean left;
	private ArrayList<Edge> to;
	private boolean end;
	
	private String name;
	private String mail;
	private String address;
	private String[] optData;
	
	public Vertex(boolean l){
		left = l;
		end = false;
		to = new ArrayList<Edge>();
	}
	public void setEnd(){
		end = true;
	}
	
	public void addEdge(Edge e){
		to.add(e);
	}
	
	public boolean isLeft(){
		return left;
	}

	public int overflow(int stream) {
		if(end) return stream;
		int flow = 0;
		
		Collections.shuffle(to);
		
		for(Edge e : to){
			flow += e.overflow(stream - flow);
			if(flow == stream) break;
		}
		
		return flow;
	}
	
	// Set name data, necessary for both Sender and Receiver
	public void setName(String name, String mail, String address) {
		this.name = name;
		this.mail = mail;
		this.address = address.replaceAll("\n", ", ");
	}
	// Set all data, only necessary for Receiver
	public void setAllData(String[] data) {
		this.setName(data[1], data[2], data[3]);
		optData = Arrays.copyOfRange(data, 4, data.length-1);
	}
	
	@Override
	public String toString(){
		// Case toString Sender
		if(left){
			String res = name + " - " + mail;
			for(Edge e : to){
				res = res + e.toString();
			}
			return res;
		}
		else // Case toString Receiver
		{
			String res = "name: " + name + "\n";
			res += "address: " + address + "\n\n";
			String[] before = "Favorite Series:\n   ,Favorite Color:\n   ,Favorite Pet:\n   ,2 Fun/Geeky things about me:\n,What I wanted to add:\n   ".split(",");
			for(int i = 0; i < before.length; i++){
				if(!optData[i].equals(""))
					res += before[i] + optData[i] + "\n";
			}
			return res;
		}
	}
}
