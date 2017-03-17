//1255668
//Amarjot Parmar
//http://stackoverflow.com/questions/6974335/converting-us-ascii-encoded-byte-to-integer-and-back

import java.io.*;
import java.lang.reflect.Array;
import java.util.Arrays;


class decoder
{
    protected static int index =1;
    protected static int[] IDArray = new int[20];
    protected static int[] DataArray = new int[20];


    public static void main(String []args)
    {
        System.err.println();
        System.err.println("-- Decoder Started --");
        int input;

        //Get Standard Input
        try
        {
            IDArray[0] = 0;
            DataArray[0] = 0;
            System.err.println("");
            System.err.println("--  INPUT  --");
            //Store Nothing in 0 index of arrays
            while((input =System.in.read()) != -1)
            {

                int inputInINT = getIntFromAxcii(input);

                    if (inputInINT != 100001) {
                        if (DataArray[inputInINT] == 0) {
                            IDArray[index] = inputInINT;

                            input = System.in.read();
                            DataArray[index] = input;

                            System.out.print(getIntToString(DataArray[index]));

                        } else {
                            System.out.print(getIntToString(DataArray[inputInINT]));
                            IDArray[index] = inputInINT;

                            input = System.in.read();
                            DataArray[index] = input;
                            System.out.print(getIntToString(DataArray[index]));

                        }
                        index++;
                    }
            }
            System.out.println("");
            System.out.println("Printing Arrays");
            dumpArrays(IDArray,true);
            dumpArrays(DataArray,false);

        }
        catch(Exception e)
        {
            System.err.println("Error : " + e);
            e.printStackTrace();
        }

    }

    private static void Reset()
    {
        IDArray = new int[20];
        DataArray = new int[20];
        index = 1;
    }
    private static void dumpArrays(int [] array,boolean ID) throws UnsupportedEncodingException {
        for(int x =1; x <array.length; x++ )
        {
            if(ID) {
                System.out.print(array[x]);
            }
            else {
                System.out.print(getIntToString(array[x]));
            }
        }
        System.out.println("");
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
    private static String getIntToString(int n) throws UnsupportedEncodingException {
        byte nn = (byte) n;
        String byteToString = new String(new byte[]{nn},"us-ascii");
        return byteToString;
    }
    private static void ExtractInput(String inputFile) throws IOException {
        FileInputStream in  = new FileInputStream(inputFile);
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String line;

        boolean EndofFile = false;
        System.err.println("Extracting data from input..");
        while(!EndofFile)
        {
            line = br.readLine();
            if(line != null)
            {
                System.err.println(line);
            }
            else
            {
                EndofFile = true;
            }

        }
        System.err.println("Data Extracted !!");
    }


}
