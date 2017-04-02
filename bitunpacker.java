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
                processLine();
            }


            System.out.println();
            System.out.println("Left over line : " +Line);
        }
        catch (Exception e)
        {
            System.out.println("Error :" + e);
            e.printStackTrace();
        }
    }

    private static void processLine()
    {
        if(processIfCan(isphraseNumber))
        {
            currentMax = currentMax + 0.5;
            if(isphraseNumber)
            {
                isphraseNumber = false;
            }
            else
            {
                isphraseNumber = true;
            }
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
                System.err.println("Before Phrase Number Extraction  : " + Line);
                Line = Line.substring(BitsThresh);
                System.err.println("After Phrase Number Extraction  : " + Line + " BitsThresh "+ BitsThresh  + "MaxValue" + currentMax);
                print(phrasenumber,isphraseNumber);

            }
            success = true;
        }
        else
        {
            if(Line.length() >= 8)
            {
                System.err.println("Before Mismatch Extraction  : " + Line);
                String mismatch = Line.substring(0,8);
                Line = Line.substring(8);
                System.err.println("After Mismatch Extraction  : " + Line);
                print(mismatch, isphraseNumber);
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
            System.out.print(",");
        }
        else
        {
            System.out.println(temp);
        }
    }
    private static void printRST()
    {
        System.out.println("\u001B");
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