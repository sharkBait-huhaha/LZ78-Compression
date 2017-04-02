import java.util.*;
import java.lang.*;
import java.nio.ByteBuffer;
import java.io.BufferedOutputStream;
import java.io.IOException;

class Bitpacker{
    private static BufferedOutputStream out = new BufferedOutputStream(System.out);
    private static int nextOut=0, nextBit=0, phraseLength=1, lineNum=0;
    public static void main(String[] args){
        LinkedList<Byte> buf = new LinkedList<Byte>();
	byte c, temp;
	try
	{
	    while((c=(byte)System.in.read())>-1){
		//newline
		if(buf.size()!=0&&buf.getLast()==10){
		    buf.removeLast();
		    //woops, that newline was actually the data...I'll just stick it back on...
		    if(c==10){
			buf.addLast(c);
			process(buf);
			buf.clear();
		    //reset
		    }else if(c==0){
			process(buf);
			buf.clear();
			nextBit+=8+phraseLength;
			lineNum=0;
			phraseLength=1;
		    //process the line
		    }else if(buf.size()!=0){
			process(buf);
			buf.clear();
			buf.addLast(c);
		    //will this ever actually be reached? Oh well, I'm sure I put it here for a reason.
		    }else
			buf.addLast(c);
		}else
		    buf.addLast(c);
	    }
	    if(buf.size()>0){
		buf.addLast(0);
		process(buf);
	    }
	    //print the last few bytes in nextOut
	    print(nextOut, (nextBit+8-1)/8);
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
	    input>>>=-shift;
	}else{
	    input<<=shift;
	}
	nextOut|=input;
	nextBit+=bits;
	if(nextBit>=32){
	    //print nextOut as binary
	    print(nextOut, 4);
	    nextOut=leftovers<<32+shift;
	    nextBit=-shift;
	}
    }
    private static void print(int x, int n){
	int b;
	try{
	    for(int i=24; i>=(4-n)*8; i-=8){
		b=x>>>i;
		out.write(b);
		out.flush();
	    }
	}catch(IOException e){
	    e.printStackTrace();
	    return;
	}
    }
    private static String dropZeros(String str){
	while(str.length()!=0&&str.charAt(0)=='0')
	    str=str.substring(1);
	return str;
    }
}
