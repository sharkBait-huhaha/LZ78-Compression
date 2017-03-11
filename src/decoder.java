//1255668
//Amarjot Parmar

import java.io.*;
import java.util.Scanner;

class decoder
{
    public static void main(String []args)
    {
        System.out.println();
        System.out.println("-- Decoder Started --");
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
    }
    
    private static void ExtractInput(String inputFile) throws IOException {
        FileInputStream in  = new FileInputStream(inputFile);
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String line;

        boolean EndofFile = false;
        System.out.println("Extracting data from input..");
        while(!EndofFile)
        {
            line = br.readLine();
            if(line != null)
            {
                System.out.println(line);
            }
            else
            {
                EndofFile = true;
            }

        }
        System.out.println("Data Extracted !!");
    }


}
