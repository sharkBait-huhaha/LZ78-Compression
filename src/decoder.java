//1255668
//Amarjot Parmar
//http://stackoverflow.com/questions/6974335/converting-us-ascii-encoded-byte-to-integer-and-back

import java.io.*;
import java.util.ArrayList;
import java.util.List;


class decoder
{
    protected static int index =1;

    protected static List<Integer> Data = new ArrayList<Integer>();
    protected static List<Integer> ID = new ArrayList<Integer>();

    public static void main(String []args)
    {
        System.err.println();
        System.err.println("-- Decoder Started --");
        int input;
        int inputInINT;
        int data;
        String temp;
        //Get Standard Input
        try
        {
            List<Integer> lineList = new ArrayList<Integer>();
            Data.add(0);
            ID.add(0);
            System.err.println("");
            System.err.println("--  INPUT  --");

            //Going through file
            while((input =System.in.read()) != -1)
            {
                //Reads the whole line , 13 indicates line is going to end
                while(input != 13)
                {
                    //adding chars to list
                    lineList.add(input);
                    input = System.in.read();
                }
                //Getting the last element of list
                data = lineList.get(lineList.size()-1);

                lineList.remove(lineList.size()-1);
                //hopefully adding "" to these doesnt corrupt data
                temp ="";
                //putting all chars together to get index
                for(int x= 0; x < lineList.size(); x++)
                {
                    temp = temp + getIntToString(lineList.get(x));
                }

                inputInINT = Integer.parseInt(temp);
                ID.add(inputInINT);
                Data.add(data);
                temp ="";

                //go up tree and print data
                while(inputInINT != 0)
                {
                    temp =  getIntToString(Data.get(inputInINT)) + temp ;
                    inputInINT = ID.get(inputInINT);
                }

                System.out.print(temp);
                System.out.print(getIntToString(Data.get(index)));
                index++;

                //Should return 10
                 input = System.in.read();
                //Clearing list for new line
                lineList.clear();
            }
        }
        catch(Exception e)
        {
            System.err.println("Error : " + e);
            e.printStackTrace();
        }

    }

    private static void Reset()
    {
        Data.clear();
        Data.add(0);

        ID.clear();
        ID.add(0);

        index = 1;
    }
    private static int getIntFromAxcii(int n)
    {
        try
        {
            byte nn = (byte) n;
            String byteToString = new String(new byte[]{nn},"us-ascii");
            int x = Integer.parseInt(byteToString);
            return x;
        }
        catch (NumberFormatException e)
        {
            return 100001;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return 1000001;
        }
    }
    private static String getIntToString(int n)  {
        try {
            byte nn = (byte) n;
            String byteToString = new String(new byte[]{nn}, "us-ascii");
            return byteToString;
        }
        catch ( UnsupportedEncodingException e)
        {
            return "Fail";
        }
    }
}
