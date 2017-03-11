//1255668
//Amarjot Parmar

import java.io.*;

class decoder
{
    public static void main(String []args)
    {
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
    
    private static void ExtractInput(String Input)
    {
        
        
    }


}
