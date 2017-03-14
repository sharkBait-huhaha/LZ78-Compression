//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.IOException;
import java.io.*;
import java.util.ArrayList;

class Encoder{
    public static void main(String[] args){
	int bufMax=256;
	int maxSize;
	TrieNode trie=new TrieNode(0);
	int c, buf[]=new int[bufMax];
	String[] pair = {""};
	
        try{
	    if(args.length!=1)
	      throw new IOException();
	    maxSize=Integer.parseInt(args[0]);
            while((c=System.in.read())!=-1){
		//System.out.println(c);
		for(int i=0;i<buf.length;i++)
		    if(buf[i]==0){
			buf[i]=c;
			i=buf.length;
		    }
		if((pair=trie.find(buf).split(" ")).length==2){
		    System.out.print(pair[0]);
		    System.out.println(pair[1]);
		    if(dropZeros(Integer.toBinaryString(trie.nextID)).length()>maxSize){
			System.out.println("\u001B");
			trie.reset();
		    }
		    buf=new int[bufMax];
		}
            }
	    if(buf[0]!=0)
		System.out.println(pair);
	}catch(IOException e){
	    System.err.println("Usage: java Encoder <Dictionary Size(bits)>");
	}catch(Exception e){
	    System.err.println("error:");
	    e.printStackTrace();
	}
    }
    private static String dropZeros(String str){
	while(str.charAt(0)=='0')
	    str=str.substring(1);
	return str;
    }
}

class TrieNode{
    final int ID;
    final int DATA;
    static int nextID = 0;
    private ArrayList<TrieNode> children=new ArrayList<TrieNode>();
    
    TrieNode(int data){
	ID=nextID;
	DATA=data;
	nextID++;
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
	TrieNode t = new TrieNode(data[0]);
	children.add(t);
	return Integer.toString(ID) +" "+ (char)data[0];
    }
    void reset(){
	children=new ArrayList<TrieNode>();
	nextID=1;
    }
}
