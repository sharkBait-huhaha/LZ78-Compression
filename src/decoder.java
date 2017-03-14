//1255668
//Amarjot Parmar

//http://stackoverflow.com/questions/8654141/convert-byte-to-string-in-java
//https://docs.oracle.com/javase/7/docs/api/java/io/InputStream.html

import sun.misc.IOUtils;
import sun.nio.ch.IOUtil;

import java.io.*;
import java.util.Scanner;

class decoder
{
    public static void main(String []args)
    {
        System.err.println();
        System.err.println("-- Decoder Started --");
        byte input;

        //Get Standard Input
        try
        {
            System.err.println("");
            System.err.println("--  INPUT  --");
            while((input = (byte) System.in.read()) != -1)
            {
                //Store it into list
                //Work with list and output original text

                //Printing output as String
                System.err.print(""+ new String(new byte[] {input}));
                //Printing output as Byte
                System.err.print(input);

                input = (byte) System.in.read();
                //Work with list and output original text
                //Store it into list
                System.err.print(""+ new String(new byte[] {input}));
                System.err.print(input);
            }

        }
        catch(Exception e)
        {
            System.err.println("Error : " + e);
        }



























        /*
        try
        {
            //if input/output file not given
            if(args.length != 2)
            {
                System.out.println("Useage: java decoder <Input File> <Output File> !!");
                return;
            }
            //Storing data into a list/array
            ExtractInput(args[0]);
        }
        catch(Exception e)
        {
            System.out.println("Error : " + e);
        }
        */
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
