//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.IOException;
import java.io.*;
import java.util.ArrayList;

class Encoder{
    public static void main(String[] args){
	//Note to self: make nextID a static variable of TrieNode
	int bufMax=256, nextID=1;
	File fileOut;
	FileInputStream in;
	FileOutputStream out;
	TrieNode trie=new TrieNode(0,(byte)0);
	byte c, buf[]=new byte[bufMax], temp;
	String s, pair="", next="";
	
        try{
	    /*if(args.length!=2)
	    	throw new IOException();
            in=new FileInputStream(args[0]);
	    fileOut=new File(args[1]);
	    if(!fileOut.createNewFile()){
		System.out.println("Doing this will override file: "+args[1]);
		System.out.println("Are you sure you wish to continue?(y/n)");
		boolean cont=false;
		while(!cont){
		    s=System.console().readLine();
		    if(s.equals("y"))
			cont=true;
		    else if(s.equals("n"))
			System.exit(0);
		    else
			System.out.println("please enter 'y' for yes, or 'n' for no. ('n' will close the program) ");
		}
		fileOut.delete();
		fileOut.createNewFile();
	    }
	    out=new FileOutputStream(args[1]);
	    */
            while((c=(byte)System.in.read())!=(byte)-1){
		for(int i=0;i<buf.length;i++)
		    if(buf[i]==(byte)0){
			buf[i]=c;
			i=buf.length;
		    }
		if((next=trie.find(buf)).length()==2){
		    pair=next;
		}else{
		    System.out.println(pair);
		    //is there a way to add the child without a second traverse of the trie?
		    trie.add(nextID, buf);
		    nextID++;
		    temp=buf[buf.length-1];
		    buf=new byte[bufMax];
		    buf[0]=temp;
		}
            }
	    if(buf[0]!=(byte)0)
		System.out.print(pair);
	    System.out.print("\u0004");
	    
	    //}catch(FileNotFoundException e){
	    //System.out.println("Invalid file given as argument");
	    //System.out.println("Usage: java Encoder <File Input> <File Output>");
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
    final byte DATA;
    private ArrayList<TrieNode> children=new ArrayList<TrieNode>();
    
    TrieNode(int id,byte data){
	ID=id;
	DATA=data;
    }
    void add(int id,byte[] data){
	if(data[1]!=(byte)0){
	    byte[] tail=new byte[data.length-1];
	    System.arraycopy(data,1,tail,0,tail.length);
	    for(TrieNode t:children)
		if(t.DATA==data[0])
		    t.add(id,data);
	}else
	    children.add(new TrieNode(id,data[0]));
    }
    String find(byte[] data){
	for(TrieNode t:children)
	    if(t.DATA==data[0]){
		if(data[1]!=(byte)0){
		    byte[] tail=new byte[data.length-1];
		    System.arraycopy(data,1,tail,0,tail.length);
		    //System.out.println(tail.toString());
		    return t.find(tail);
		}else
		    return Integer.toString(t.ID);
	    }
	return Integer.toString(ID)+(char)data[0];
    }
}
