//Joseph Boyd    (1264974)
//Amarjot Parmar (1255668)
//http://stackoverflow.com/questions/6974335/converting-us-ascii-encoded-byte-to-integer-and-back

import java.io.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Scanner;


class decoder
{
    private static int index =1;
    private static List<Byte> Mismatch = new ArrayList<Byte>();
    private static List<Integer> phraseNumber = new ArrayList<Integer>();
    private static List<Byte> toPrint = new ArrayList<Byte>();
    private static ByteArrayOutputStream stream = new ByteArrayOutputStream();

    public static void main(String []args)
    {
        Scanner scan = new Scanner(System.in);
        try
        {
            reset();

            int phrasenumber;
            int data;

            //Going through file
            while(scan.hasNext())
            {
                String line = scan.nextLine();
                String []  ExtractedData = line.split(",");

                if(ExtractedData.length == 2)
                {
                    phrasenumber = Integer.parseInt(ExtractedData[0]);
                    data = Integer.parseInt(ExtractedData[1]);
                    //if its the last line and its just a phrase number
                    if(!scan.hasNext() && data == 0)
                    {
                        cleanprocess(phrasenumber);
                    }
                    else
                    {
                        phraseNumber.add(phrasenumber);
                        Mismatch.add((byte) data);

                        cleanprocess(phrasenumber);

                        toPrint.add((byte) data);
                    }
                }
                //this will only happen when rst symbol but checking just in case.
                else
                {
                    line = line + ", 0";
                    ExtractedData = line.split(",");

                    if(ExtractedData[0].equals("\u0000"))
                    {
                        System.err.println("RESET");
                        reset();
                    }
                }
            }


            byte [] bytearray = new byte[toPrint.size()];
            for(int x = 0; x < toPrint.size(); x++)
            {
                    bytearray[x] = toPrint.get(x);
            }

            System.out.write(bytearray);

        }
        catch(Exception e)
        {
            System.err.println("Error : " + e);
            e.printStackTrace();
        }

    }
    //recursivly prints mismatches using phrasenumber given
    private static void cleanprocess(int p)
    {
        int temp = p;
        List<Byte> linetoPrint = new ArrayList<Byte>();

        while (temp != 0)
        {

            linetoPrint.add(Mismatch.get(temp));
            temp = phraseNumber.get(temp);
        }

        for (int x = linetoPrint.size(); x > 0; x--)
        {
            toPrint.add(linetoPrint.get(x - 1));
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
}

