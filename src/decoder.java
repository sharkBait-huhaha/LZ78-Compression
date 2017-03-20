//1255668
//Amarjot Parmar
//http://stackoverflow.com/questions/6974335/converting-us-ascii-encoded-byte-to-integer-and-back

import java.io.*;
import java.util.ArrayList;
import java.util.List;


class decoder
{
    protected static int index =1;

    protected static List<Byte> Data = new ArrayList<Byte>();
    protected static List<Byte> ID = new ArrayList<Byte>();

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
            List<Byte> lineList = new ArrayList<Byte>();
            Data.add((byte)0);
            ID.add((byte)0);
         //   System.err.println("");
         //   System.err.println("--  INPUT  --");
            boolean Enter;

            //Going through file
            while((input = (byte) System.in.read()) != -1)
            {
                Enter = false;

            //    System.err.println("");
            //    System.err.println("Reading Line : " + index);


                if(input == 27)
                {
                //    System.err.println("ESCAPE Symbol found! Reseting !!");
                    reset();
                    input = (byte) System.in.read();
                    input = (byte) System.in.read();
                    input = (byte) System.in.read();
                    if(input == -1)
                    {
                //        System.err.println("END OF FILE ??");
                        break;
                    }
                }

                lineList.clear();

                while(input != 13)
                {
                    lineList.add(input);
                //    System.err.print(input);
                    input = (byte) System.in.read();
                }
            //    System.err.println();
            //    System.err.println("Chars Read in Line: " + lineList.size());

                input = (byte) System.in.read();
            //    System.err.println("Should be 10 :" + input);

                if(input == 13)
                {
                    input = (byte) System.in.read();
                    Enter = true;
                }

                if(Enter)
                {
                    data = 13;
                }
                else
                {
                    data = lineList.get(lineList.size()-1);
                    lineList.remove(lineList.size()-1);
                }

                temp ="";

                for(int x= 0; x < lineList.size(); x++)
                {
                    temp = temp + getIntFromAxcii(lineList.get(x));
                }
                if (lineList.size() == 0)
                {
            //        System.err.println("Amount of Lines Read : " + index);
            //        System.err.println("No data found on line");
                    break;
                }

            //    System.err.println("Turning Index into Int : " + temp );
                inputInINT = Integer.parseInt(temp);

                ID.add((byte)inputInINT);
                Data.add(data);

                temp ="";

                List<Byte> lineListToAdd = new ArrayList<Byte>();
                //go up tree and print data
                while(inputInINT != 0)
                {
                    Boolean ADDENTER = false;

                    byte DataInByte =Data.get(inputInINT);
                    String DataInString = getIntToString(Data.get(inputInINT));

                    if(DataInByte == 13)
                    {
                        ADDENTER = true;
            //            System.err.println("ENTER DETECTED");

                        byte n = 10;
                        String Ten = getIntToString(n);
                        //NOT ADDING THE 10 ATM
                        temp =  getIntToString(Data.get(inputInINT)) + temp ;
                    //    lineListToAdd.add(Data.get(inputInINT));
                    }
                    if(!ADDENTER)
                    {
                        temp =  getIntToString(Data.get(inputInINT)) + temp ;
                        lineListToAdd.add(Data.get(inputInINT));
                    }
                    inputInINT = ID.get(inputInINT);

                }

                /*
                for(int x = 0; x< lineListToAdd.size(); x++)
                {
                    System.out.print(lineListToAdd.get(x));
                }

                System.out.print(Data.get(index));
                */

                System.out.print(temp);

                System.out.print(getIntToString(Data.get(index)));
                index++;
            }
         //   System.err.println();
         //   System.err.println("Amount of Lines Read : " + index);

        }
        catch(Exception e)
        {
            System.err.println("Error : " + e);
            e.printStackTrace();
        }

    }

    private static Boolean NeedReset(List<Byte> line)
    {
        Boolean result = false;
        List<String> lineInString = new ArrayList<String>();

        for(int x = 0; x< line.size(); x++)
        {
            lineInString.add(getIntToString(line.get(x)));
        }

        String temp ="";
        Integer tempint= 0;
        //putting all chars together to get index
        for(int x= 0; x < line.size(); x++)
        {
            temp = temp + getIntToString(line.get(x));
        }
        if(temp.equals("\u001B"))
        {
            result = true;
        }

        return result;
    }
    private static void reset()
   {
        Data.clear();
        Data.add((byte)0);

        ID.clear();
        ID.add((byte)0);

        index = 1;
    }
    private static int getIntFromAxcii(int n)
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
            System.err.println("WRONG INPUT !!!!!!!");
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
        catch (NumberFormatException x)
        {
            return "FAIL e";
        }
        catch ( UnsupportedEncodingException e)
        {
            return "Fail";
        }
    }
}
