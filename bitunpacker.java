//Amarjot Parmar
//1255668

class bitunpacker
{
    private static int currentMax = 1;
    private static int BitsThresh =1;
    private static String Line = "";
    private static  boolean isphraseNumber = true;
    public static void main(String args [])
    {
        int input;
        try
        {
            // READ 8 Bits
            while ((input = System.in.read()) != -1 )
            {
                //Carefull toBinary seems to cut off zeros that are not needed e.g 000101 = 101
                Line = Line + Integer.toBinaryString(input);
                /*
                if(processIfCan(isphraseNumber))
                {
                    currentMax++;
                }
                */

            }
            System.out.println(Line);
        }
        catch (Exception e)
        {
            System.out.println("Error :" + e);
            e.printStackTrace();
        }
    }

    private static boolean processIfCan(boolean isphraseNumber)
    {
        boolean success = false;
        processMaxPossibleByte();


        if(isphraseNumber)
        {
            if(Line.length() >= BitsThresh)
            {
                String phrasenumber = Line.substring(0,BitsThresh);
                Line = Line.substring(BitsThresh);
                print(phrasenumber,isphraseNumber);
                isphraseNumber = false;
            }
            success = true;
        }
        else
        {
            if(Line.length() >= 8)
            {
                String mismatch = Line.substring(0,7);
                Line = Line.substring(7);
                print(mismatch, isphraseNumber);
                isphraseNumber = true;
            }
            success = true;
        }

        return success;
    }

    private static void print(String line, boolean isphraseNumber)
    {
        //Turn 100110 into a number
        int temp = 0;
        temp = Integer.parseInt(line, 2);

        if(isphraseNumber)
        {
            System.out.print(temp);
            System.out.print(", ");
        }
        else
        {
            System.out.println(temp);
        }
    }

    private static void processMaxPossibleByte()
    {
        double n = Math.pow(2, BitsThresh);
        n = n -1;
        if(n < currentMax )
        {
            BitsThresh++;
        }
    }

}