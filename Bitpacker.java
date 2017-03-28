import java.util.*;
import java.lang.*;
import java.nio.ByteBuffer;

class Bitpacker{
    private static int nextOut=0, nextBit=0, phraseLength=1, lineNum=0;
    public static void main(String[] args){
        LinkedList<Byte> buf = new LinkedList<Byte>();
	byte c, temp;
	try
	{
		buf.addLast((byte)System.in.read());
	    while((c=(byte)System.in.read())>-1){
		//System.out.println("input: "+Integer.toBinaryString(c));
		if(buf.getLast()==10){
		    buf.removeLast();
		    if(c==10){
			buf.addLast(c);
			process(buf);
			buf.clear();
		    }else if(buf.size()!=0){
			process(buf);
			buf.clear();
			buf.addLast(c);
		    }else
			buf.addLast(c);
		}else
		    buf.addLast(c);
	    }
	    if(buf.size()>0)
		buf.addLast((byte)0);
	    process(buf);
	    System.out.print(String.format("%32s", Integer.toBinaryString(nextOut)).replace(" ", "0"));
	}catch(Exception e){
	    e.printStackTrace();
	}
    }
    private static void process(LinkedList<Byte> buf){
	//TO DO: find out when an empty list is passed to process
	if(buf.size()!=0){
	    byte data=buf.removeLast();
	    int num=0;
	    for(byte b:buf){
		num*=10;
		if(b!=(byte)0)
		    num+=Integer.parseInt(new String(new byte[] {b}));
	    }
	    pack(num, phraseLength);
	    pack(data, 8);
	    lineNum++;
	    if(dropZeros(Integer.toBinaryString(lineNum)).length()>phraseLength)
		phraseLength++;
	}
    }
    private static void pack(int input, int bits){
	int leftovers=0;
	int shift=32-bits-nextBit;
	if(shift<0){
	    leftovers=(int)Math.pow(2,-shift)-1&input;
	    //leftovers=(2^(-shift))&input;
	    input>>>=-shift;
	}else{
	    input<<=shift;
	}
	nextOut|=input;
	nextBit+=bits;
	if(nextBit>=32){
	    //print nextOut as binary
	    System.out.print(String.format("%32s", Integer.toBinaryString(nextOut)).replace(" ", "0"));
	    nextOut=leftovers<<32+shift;
	    nextBit=-shift;
	}
    }
    private static String dropZeros(String str){
	while(str.length()!=0&&str.charAt(0)=='0')
	    str=str.substring(1);
	return str;
    }
}
