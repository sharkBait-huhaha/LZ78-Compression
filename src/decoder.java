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
    protected static  ByteArrayOutputStream stream = new ByteArrayOutputStream();


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

            boolean Enter =false;
	

            //Going through file
            while((input = (byte) System.in.read()) != -1)
            {
               // System.out.print(input);
                //ESCAPE Symbol encountered
                if(input == 27)
                {
                    if(lineList.size() != 0)
                    {
                        processline(lineList, file);
                    }
                    reset();
                    lineList.clear();
                    System.out.println("RESET DETECTED");
                    //Reading the 10
                    System.in.read();
                    Enter = false;
                    continue;
                }
                else
                {
                    //First 10
                    if (input == 10 && !Enter) {
                        Enter = true;
                        lineList.add(input);

                    }
                    //Secound 10
                    else if (input == 10 && Enter) {
                        lineList.add(input);

                        processline(lineList, file);
                        lineList.clear();
                        Enter = false;

                    }
                    //No Second 10
                    else if (Enter)
                    {
                        System.out.println("LineList Size: " + lineList.size());
                        processline(lineList, file);
                        lineList.clear();
                        lineList.add(input);

                        Enter = false;

                    }
                    else
                    {
                        lineList.add(input);
                        Enter = false;

                    }
                }


            }

	    if(lineList.size() != 1 && lineList.size() != 0)
		{
			lineList.remove(lineList.size() -1);
            int PhraseNumber = getID(lineList);
            ID.add(PhraseNumber);

            printtoFile(PhraseNumber, file);
            stream.writeTo(file);
		}
     
	}
        catch(Exception e)
        {
            System.err.println("Error : " + e);
            e.printStackTrace();
        }

    }

    private static void processline(List<Byte> line, FileOutputStream file)
    {
	       line.remove(line.size() -1);
            //System.out.println("LINE SIZE :" +line.size());
            Data.add(line.get(line.size()-1));
            line.remove(line.size()-1);

            //System.out.println( "INDEX : "+ index +" ID : " + line.get(0) + "  DATA : " + Data.get(index) );

            int PhraseNumber = getID(line);
            ID.add(PhraseNumber);

            printtoFile(PhraseNumber, file);
            stream.write(Data.get(index));
            try {
                stream.writeTo(file);
            }
            catch (Exception e)
            {

            }
        index++;
    }
    private static int getID(List<Byte> line)
    {
        int PhraseNumber = 0;
        //Putting phrase number together
        String temp ="";
        for(int x= 0; x < line.size(); x++)
        {
            temp = temp + getIntToString(line.get(x));

        }

        PhraseNumber = ConvertByteToIntString(temp);

        return PhraseNumber;
    }
    private static void justprinttofile(FileOutputStream file, byte b)
    {
        stream.write(b);
    }
    private static void printtoFile(int PhraseNumber, FileOutputStream file)
    {
        //go up tree and storing data in this list to print
        List<Byte> lineListToAdd = new ArrayList<Byte>();
        while(PhraseNumber != 0)
        {
            lineListToAdd.add(Data.get(PhraseNumber));
            PhraseNumber = ID.get(PhraseNumber);
        }

        //Stream that will be used to ouput data
        stream = new ByteArrayOutputStream();

        for(int x = lineListToAdd.size(); x> 0; x--)
        {
            stream.write(lineListToAdd.get(x-1));
        }

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
    private static int ConvertByteToIntString(String x)
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

