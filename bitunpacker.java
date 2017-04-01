//Amarjot Parmar
//1255668

class bitunpacker
{
    private static int  MaxValue = 1;
    private static int currentMax = 1;
    private static int BitsThresh =1;
    private static String Line = "";
    public static void main(String args [])
    {
        boolean isphraseNumber = true;
        int x = 0;
        int input;
        try
        {
            // READ 8 Bits
            while ((input = System.in.read()) != -1 )
            {

                System.out.println("Before :  " + currentMax + " BITS THRESH : " + BitsThresh);
                processMaxPossibleByte();


                System.out.println("After  :  " + currentMax + " BITS THRESH : " + BitsThresh);
                currentMax++;
                System.out.println();
              //  System.out.println("Max :  " + MaxPosPhraseNum + " Count: " + count);

                /*
                Line = Line + Integer.toBinaryString(input);
                if(processIfCan(isphraseNumber))
                {
                    count++;
                    if(isphraseNumber)
                    {
                        isphraseNumber = false;
                    }
                    else
                    {
                        isphraseNumber = true;
                    }
                }
                */
            }
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
               // String phrasenumber = Line.substring(0,MaxPosPhraseNum);
              //  Line = Line.substring(MaxPosPhraseNum);
            }


        }
        else
        {

        }


        return success;
    }

    private static void processMaxPossibleByte()
    {
        double n = Math.pow(2, BitsThresh);
        n = n -1;
        if(n < currentMax )
        {
            BitsThresh++;
            //processMaxPossibleByte();
        }

    }

    private static void unpack(String line, boolean isphraseNumber)
    {
        int temp = 0;
        if(isphraseNumber)
        {

            System.out.print(temp +",");
        }
        else
        {
            System.out.println(temp);
        }
    }

















































}