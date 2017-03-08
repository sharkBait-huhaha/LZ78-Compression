import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

class Encoder{
    public static void main(String[] args){
	int bufMax=256, nextID=1;
	FileInputStream in=null;
	TrieNode trie=new TrieNode(0,(byte)0);
	byte c, buf[]=new byte[bufMax];
	String pair="";
	
        try{
            in=new FileInputStream(args[0]);
	    
            while((c=(byte)in.read())!=(byte)-1){
		for(int i=0;i<buf.length;i++)
		    if(buf[i]==(byte)0){
			buf[i]=c;
			i=buf.length;
		    }
		if((pair=trie.find(buf)).length()==2){
		    System.out.println(pair);
		    //is there a way to add the child without a second traverse of the trie?
		    trie.add(nextID, buf);
		    nextID++;
		    buf=new byte[bufMax];
		}
            }
	    if(buf[0]!=(byte)0)
		System.out.println(pair+"\u0004");
	}catch(Exception e){
	    System.out.println("error:");
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
