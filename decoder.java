//1255668
//Amarjot Parmar
//http://stackoverflow.com/questions/6974335/converting-us-ascii-encoded-byte-to-integer-and-back


import javax.annotation.processing.SupportedSourceVersion;
import java.io.*;
import java.util.ArrayList;
import java.util.List;


class decoder
{
    private static int index =1;
    
    private static List<Byte> Mismatch = new ArrayList<Byte>();
    private static List<Integer> phraseNumber = new ArrayList<Integer>();
    
    private static  ByteArrayOutputStream stream = new ByteArrayOutputStream();

    
    public static void main(String []args)
    {
        byte input;

        try
        {
            List<Byte> lineList = new ArrayList<Byte>();

            Mismatch.add((byte)0);
            phraseNumber.add(0);

            boolean Enter =false;

            //Going through file
            while((input = (byte) System.in.read()) != -1)
            {
                //ESCAPE Symbol encountered
                if(input == 27)
                {
                    if(lineList.size() != 0)
                    {
                        processline(lineList);
                    }
                    reset();
                    lineList.clear();
                    System.err.println("RESET DETECTED");
                    //Reading the 10
                    System.in.read();
                    Enter = false;
                }
                else
                {
                    //First 10
                    if (input == 10 && !Enter)
                    {
                        Enter = true;
                        lineList.add(input);
                    }
                    //Secound 10
                    else if (input == 10 && Enter)
                    {
                        lineList.add(input);
                        processline(lineList);
                        lineList.clear();
                        Enter = false;
                    }
                    //No Second 10
                    else if (Enter)
                    {
                        processline(lineList);
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
            //Last input couldve just been a phrase number so this proccesses that
            if(lineList.size() != 1 && lineList.size() != 0)
            {
                lineList.remove(lineList.size() -1);
                int PhraseNumber = getphraseNumber(lineList);
                phraseNumber.add(PhraseNumber);

                printtoFile(PhraseNumber);
                stream.writeTo(System.out);
            }
        }
        catch(Exception e)
        {
            System.err.println("Error : " + e);
            e.printStackTrace();
        }

    }
    //Takes a list of bytes which has a format of [<PhraseNumber>, <Data>, 10], puts mismatch into array and outputs corrosponding data
    private static void processline(List<Byte> line)
    {
	        line.remove(line.size() -1);
            Mismatch.add(line.get(line.size()-1));
            line.remove(line.size()-1);

            int PhraseNumber = getphraseNumber(line);
            phraseNumber.add(PhraseNumber);

            printtoFile(PhraseNumber);
            stream.write(Mismatch.get(index));
            try {
                stream.writeTo(System.out);
            }
            catch (Exception e)
            {
                System.err.println("Error : " + e);
                e.printStackTrace();
            }
            index++;
    }
    
    //Puts the phrase number togther from bits [49,48] = 10
    private static int getphraseNumber(List<Byte> line)
    {
        int PhraseNumber = 0;
        //Putting phrase number together
        String temp ="";
        for(int x= 0; x < line.size(); x++)
        {
            temp = temp + ConvertToString(line.get(x));

        }

        PhraseNumber = ConvertToInt(temp);

        return PhraseNumber;
    }
    
    //Uses Recursion to output mismatchs in the correct order
    private static void printtoFile(int PhraseNumber)
    {
        //go up tree and storing Mismatch in this list to print
        List<Byte> lineListToAdd = new ArrayList<Byte>();
        while(PhraseNumber != 0)
        {
            lineListToAdd.add(Mismatch.get(PhraseNumber));
            PhraseNumber = phraseNumber.get(PhraseNumber);
        }

        //Stream that will be used to ouput Mismatch
        stream = new ByteArrayOutputStream();

        for(int x = lineListToAdd.size(); x> 0; x--)
        {
            stream.write(lineListToAdd.get(x-1));
        }
    }


    //throws away all acquired phrases and continue as if starting from scratch
    private static void reset()
    {
        Mismatch.clear();
        Mismatch.add((byte)0);
        
        phraseNumber.clear();
        phraseNumber.add(0);

        index = 1;
    }

    //Turns byte into strings, used when putting together phrase number (e.g 49 -> 1)
    private static String ConvertToString(byte n)
    {
        try
        {
            byte nn = (byte) n;
            String byteToString = new String(new byte[]{nn}, "us-ascii");
            return byteToString;
        }
        catch ( UnsupportedEncodingException e)
        {
            return "Error : " + e;
        }
    }
    
    //Turns a String to Int, used to convert phrase number into int
    private static int ConvertToInt(String x)
    {
        try
        {
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

