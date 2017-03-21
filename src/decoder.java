//1255668
//Amarjot Parmar
//http://stackoverflow.com/questions/6974335/converting-us-ascii-encoded-byte-to-integer-and-back


import javax.annotation.processing.SupportedSourceVersion;
import java.io.*;
import java.util.ArrayList;
import java.util.List;


class decoder
{
    protected static int index =1;

    protected static List<Byte> Data = new ArrayList<Byte>();
    protected static List<Integer> ID = new ArrayList<Integer>();
    protected static  ByteArrayOutputStream stream;

    public static void main(String []args)
    {
        System.err.println();
        System.err.println("-- Decoder Started --");
        byte input;

        int inputInINT;
        byte data;
        String temp;
        //Get Standard Input
        try
        {
            FileOutputStream file = new FileOutputStream("decoder_out.txt");
            List<Byte> lineList = new ArrayList<Byte>();
            Data.add((byte)0);
            ID.add(0);
            boolean Enter;

            //Going through file
            while((input = (byte) System.in.read()) != -1)
            {

                Enter = false;

                //ESCAPE Symbol encountered
                if(input == 27)
                {
                    reset();
                    //skipping line (default 13 10 which occres after every line)
                    input = (byte) System.in.read();
                    input = (byte) System.in.read();
                    //New byte in new line
                    input = (byte) System.in.read();
                    if(input == -1)
                    {
                        break;
                    }
                }
                //Since new line
                lineList.clear();
                //storing all bytes
                while(input != 13)
                {
                 //   System.err.print(getIntToString(input) + " INPUT IN BYTES : " + input +" ");
                    lineList.add(input);
                    input = (byte) System.in.read();
                }

                // Checking if 13 is also part of data
                input = (byte) System.in.read();
                if(input == 13)
                {
                    input = (byte) System.in.read();
                    Enter = true;
                }

                if(Enter)
                {
                    data = 13;
                }
                else
                {
                    //Extratcing mismatch & removing it
                    data = lineList.get(lineList.size()-1);
                    lineList.remove(lineList.size()-1);
                }


                //Putting phrase number together
                temp ="";
                for(int x= 0; x < lineList.size(); x++)
                {
                    temp = temp + getIntToString(lineList.get(x));

                }
               // System.out.println(temp + " LINE SIZE " + lineList.size());


                inputInINT = ConvertBytoToIntString(temp);
                ID.add(inputInINT);
                Data.add(data);


                //go up tree and storing data in this list to print
                List<Byte> lineListToAdd = new ArrayList<Byte>();
                while(inputInINT != 0)
                {
                    lineListToAdd.add(Data.get(inputInINT));
                    inputInINT = ID.get(inputInINT);
                }



                //Stream that will be used to ouput data
                 stream = new ByteArrayOutputStream();

                for(int x = lineListToAdd.size(); x> 0; x--)
                {
                   // System.out.print(lineListToAdd.get(x-1));
                    stream.write(lineListToAdd.get(x-1));
                }
                stream.write(Data.get(index));
                stream.writeTo(file);

                index++;
            }
        }
        catch(Exception e)
        {
            System.err.println("Error : " + e);
            e.printStackTrace();
        }

    }
    //Takes a byte array and determines if its a reset symbol
    private static Boolean NeedReset(List<Byte> line)
    {
        Boolean result = false;
        List<String> lineInString = new ArrayList<String>();

        for(int x = 0; x< line.size(); x++)
        {
            lineInString.add(getIntToString(line.get(x)));
        }

        String temp ="";
        Integer tempint= 0;
        //putting all chars together to get index
        for(int x= 0; x < line.size(); x++)
        {
            temp = temp + getIntToString(line.get(x));
        }
        if(temp.equals("\u001B"))
        {
            result = true;
        }

        return result;
    }

    private static void reset()
    {
        Data.clear();
        Data.add((byte)0);

        ID.clear();
        ID.add(0);

        index = 1;
    }

    private static int getIntFromAxcii(byte n)
    {
        try
        {
            //e.g 48
            byte nn = (byte) n;
            String byteToString = new String(new byte[]{nn},"us-ascii");
            int x = Integer.parseInt(byteToString);
            //e.g 1
            return x;
        }
        catch (NumberFormatException e)
        {
            System.err.println("Error : " + e);
            return 100001;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return 1000001;
        }
    }

    private static String getIntToString(byte n)  {
        try {
            byte nn = (byte) n;
            String byteToString = new String(new byte[]{nn}, "us-ascii");
         // System.err.println("Byte : " + n + " Calculated Value : " + byteToString);
            return byteToString;
        }
        catch (NumberFormatException x)
        {
            return "Error : " + x;
        }
        catch ( UnsupportedEncodingException e)
        {
            return "Error : " + e;
        }
    }
    private static int ConvertBytoToIntString(String x)
    {
        try {
            int inputInINT = Integer.parseInt(x);
            return inputInINT;
        }
        catch(NumberFormatException e)
        {
            System.out.println("COULD NOT CONVERT int");
            return 100001;
        }
    }
}
