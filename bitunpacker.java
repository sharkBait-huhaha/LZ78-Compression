//Joseph Boyd    (1264974)
//Amarjot Parmar (1255668)
class bitunpacker
{
    private static double currentMax = 0;
    private static int BitsThresh =1;
    private static String Line = "";

    public static void main(String args [])
    {
        int input;
        try
        {
            // READ 8 Bits
            while ((input = System.in.read()) != -1 )
            {
                //so 00110 stays 00110
                Line = Line + String.format("%8s", Integer.toBinaryString(input)).replace(" ", "0");
                processIfCan();
            }

            System.err.println();
            System.err.println("Left over line : " +Line);
        }
        catch (Exception e)
        {
            System.out.println("Error :" + e);
            e.printStackTrace();
        }
    }
    //Checks if its phrase number and mismatch is present in Line
    private static void processIfCan()
    {
        processMaxPossibleByte();
        if(Line.length() >= (8 + BitsThresh))
        {
            processLine();

        }
    }
    //Takes phrase number and mismatch out of Line and prints it unless its a RST sybol t
    private static void processLine()
    {
        if(!isRST())
        {
            String phrasenumber = Line.substring(0, BitsThresh);
            Line = Line.substring(BitsThresh);
            print(phrasenumber, true);

            String mismatch = Line.substring(0, 8);
            Line = Line.substring(8);
            print(mismatch, false);

            currentMax++;
        }
        else
        {
          reset();
        }
    }
    // if its a reset symbol then print it and reset
    private static boolean isRST ()
    {
        String tempLine = Line;
        tempLine = tempLine.substring(0, BitsThresh + 8);
        String x = "";

        for(int y =0; y < tempLine.length(); y ++)
        {
            x = x + "0";
        }

        if(tempLine.equals(x))
        {
            printRST();
            Line = Line.substring(BitsThresh + 8);
            return true;
        }
        else
        {
            return false;
        }
    }
    private static void reset()
    {
        currentMax = 0;
        BitsThresh = 1;
        processMaxPossibleByte();
    }
    private static void print(String line, boolean isphraseNumber)
    {
        //Turn 100110 into decimal
        int temp = 0;
        temp = Integer.parseInt(line, 2);

        if(isphraseNumber)
        {
            System.out.print(temp);
            System.out.print(",");
        }
        else
        {
            System.out.println(temp);
        }
    }
    private static void printRST()
    {
        System.out.println("\u0000");
    }
    //cant fit 4 in 2 bits so increase max amount of bits expected for phrase number
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

