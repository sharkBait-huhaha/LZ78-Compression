//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.IOException;
import java.io.*;
import java.util.ArrayList;

class Encoder{
    public static void main(String[] args){
	int bufMax=256;
	TrieNode trie=new TrieNode(0,(byte)0);
	int c, buf[]=new int[bufMax], temp;
	String s, pair="", next="";
	
        try{
	    /*if(args.length!=2)
	      throw new IOException();*/
            while((c=System.in.read())!=-1){
		//System.out.println(c);
		for(int i=0;i<buf.length;i++)
		    if(buf[i]==0){
			buf[i]=c;
			i=buf.length;
		    }
		if((pair=trie.find(buf)).length()==2){
		    //pair=next;
		    //}else{
		    System.out.println(pair);
		    buf=new int[bufMax];
		    //buf[0]=c;
		}
            }
	    if(buf[0]!=0)
		System.out.println(pair);
	}catch(IOException e){
	    System.err.println("Usage: java Encoder <File Input> <File Output>");
	}catch(Exception e){
	    System.err.println("error:");
	    e.printStackTrace();
	}
    }
}

class TrieNode{
    final int ID;
    final int DATA;
    static int nextID = 1;
    private ArrayList<TrieNode> children=new ArrayList<TrieNode>();
    
    TrieNode(int id,int data){
	ID=id;
	DATA=data;
    }
    void add(int id,int[] data){
	if(data[1]!=0){
	    int[] tail=new int[data.length-1];
	    System.arraycopy(data,1,tail,0,tail.length);
	    for(TrieNode t:children)
		if(t.DATA==data[0])
		    t.add(id,data);
	}else
	    children.add(new TrieNode(id,data[0]));
    }
    String find(int[] data){
	for(TrieNode t:children)
	    if(t.DATA==data[0]){
		if(data[1]!=0){
		    int[] tail=new int[data.length-1];
		    System.arraycopy(data,1,tail,0,tail.length);
		    //System.out.println(tail.toString());
		    return t.find(tail);
		}else
		    return Integer.toString(t.ID);
	    }
	TrieNode t = new TrieNode(nextID, data[0]);
	nextID++;
	children.add(t);
	return Integer.toString(ID) + (char)data[0];
    }
}
