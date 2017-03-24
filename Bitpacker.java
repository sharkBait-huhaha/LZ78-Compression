import java.util.LinkedList<E>;

class Bitpacker{
    private int nextOut=0;
    public static void main(String[] args){
        List<byte> buf = new LinkedList<byte>();
	byte c;
	while((c=System.in.read())!=-1){
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
    }
    private static void pack(List<byte> buf){

    }
    private static void packNum(List<byte> buf){

    }
}
