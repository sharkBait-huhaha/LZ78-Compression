import java.util.*;
import java.lang.*;
import java.nio.ByteBuffer;

class Bitpacker{
    private static int nextOut=0, nextBit=0, maxphrase=0;
    public static void main(String[] args){
        LinkedList<Byte> buf = new LinkedList<Byte>();
	byte c, temp;
	try{
	    buf.addLast((byte)System.in.read());
	    while((c=(byte)System.in.read())>-1){
		if(buf.getLast()==10){
		    buf.removeLast();
		    if(c==10){
			buf.addLast(c);
			pack(buf);
			buf.clear();
		    }else{
			pack(buf);
			buf.clear();
			buf.addLast(c);
		    }
		}else
		    buf.addLast(c);
	    }
	    if(buf.size()>0)
		packNum(buf);
	}catch(Exception e){
	    e.printStackTrace();
	}
    }
    private static void pack(LinkedList<Byte> buf){
	byte data=buf.removeLast();
	int num=0;
	for(Byte b:buf){
	    num*=10;
	    num+=Integer.parseInt(new String(new byte[] {b}));
	}
	
	
    }
    private static void packNum(List<Byte> buf){

    }
}
