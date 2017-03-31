//1255668
//Amarjot Parmar
//http://stackoverflow.com/questions/6974335/converting-us-ascii-encoded-byte-to-integer-and-back


import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


class decoder
{
    private static int index =1;
    private static List<Character> Mismatch = new ArrayList<Character>();
    private static List<Integer> phraseNumber = new ArrayList<Integer>();
    
    public static void main(String []args)
    {
        byte input;
        Scanner scan = new Scanner(System.in);

        try
        {
            reset();
            boolean Enter =false;

            //Going through file
            while(scan.hasNext())
            {
                String line = scan.nextLine();
                String []  ExtractedData = line.split(",");

                if(ExtractedData.length == 2)
                {
                    System.err.println("ID : " + ExtractedData[0] + " Data : " + ExtractedData[1]);
                    int phrasenumber = Integer.parseInt(ExtractedData[0]);
                    int data = Integer.parseInt(ExtractedData[1]);
                    cleanprocess(phrasenumber, data);
                }
                else
                {
                    System.err.print("LENGTH OF Extracted : "+ ExtractedData.length);
                    ExtractedData = line.split(" ");
                    System.err.print("LENGTH OF Extracted : "+ ExtractedData.length);
                    int temp = Integer.parseInt(ExtractedData[0]);
                    if(temp == 27)
                    {
                        reset();
                    }
                    else
                    {
                        List<Character> linetoPrint = new ArrayList<Character>();

                        while(temp != 0)
                        {
                            linetoPrint.add(Mismatch.get(temp));
                            temp = phraseNumber.get(temp);
                        }
                        for(int x = linetoPrint.size(); x> 0; x--)
                        {
                            System.out.write(linetoPrint.get(x-1));
                        }
                    }

                }

            }


        }
        catch(Exception e)
        {
            System.err.println("Error : " + e);
            e.printStackTrace();
        }

    }

    private static void cleanprocess(int phrasenumber, int data)
    {
        phraseNumber.add(phrasenumber);
        Mismatch.add((char)data);

        int temp = phrasenumber;
        List<Character> linetoPrint = new ArrayList<Character>();

        while(temp != 0)
        {
            linetoPrint.add(Mismatch.get(temp));
            temp = phraseNumber.get(temp);
        }
        for(int x = linetoPrint.size(); x> 0; x--)
        {
            System.out.write(linetoPrint.get(x-1));
        }
        System.out.write((byte)data);
    }

    //throws away all acquired phrases and continue as if starting from scratch
    private static void reset()
    {
        Mismatch.clear();
        Mismatch.add((char)0);
        
        phraseNumber.clear();
        phraseNumber.add(0);

        index = 1;
    }

}

