//Amarjot Parmar
//1255668

class bitunpacker
{
    private static double currentMax = 0;
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

    private static void processIfCan()
    {
        processMaxPossibleByte();
        if(Line.length() >= (8 + BitsThresh))
        {
            processLine();

        }
    }

    private static void processLine()
    {
        if(!isRST())
        {
            String phrasenumber = Line.substring(0, BitsThresh);
            //System.err.println("Before Phrase Number Extraction  : " + Line);
            Line = Line.substring(BitsThresh);
            //System.err.println("After Phrase Number Extraction  : " + Line + " BitsThresh "+ BitsThresh  + "MaxValue" + currentMax);
            print(phrasenumber, true);


            // System.err.println("Before Mismatch Extraction  : " + Line);
            String mismatch = Line.substring(0, 8);
            Line = Line.substring(8);
            //  System.err.println("After Mismatch Extraction  : " + Line);
            print(mismatch, false);

            currentMax++;
        }
        else
        {
          reset();
        }

    }

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
        //Turn 100110 into a number
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
