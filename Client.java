import java.net.*;
import java.io.*;
import java.util.*;

public class Client
{
    public static void main(String[] args) throws UnknownHostException, IOException 
    {
        String name = args[0];
        Scanner input = new Scanner(System.in);
        
        try
        {
            Socket s=new Socket("127.0.0.1",2221);
            
            DataOutputStream auth_name = new DataOutputStream(s.getOutputStream());
            auth_name.writeUTF(name);
            
            DataInputStream sermes1 = new DataInputStream(s.getInputStream());
            System.out.println(sermes1.readUTF());
            
            DataInputStream sermes2 = new DataInputStream(s.getInputStream());
            System.out.println(sermes2.readUTF());
            
            DataOutputStream stockcount = new DataOutputStream(s.getOutputStream());
            String count = input.nextLine();
            stockcount.writeUTF(count);
                  
            for( int i=0; i<Integer.parseInt(count);i++)
            {
            DataInputStream sermes3 = new DataInputStream(s.getInputStream());
            System.out.println(sermes3.readUTF());
            
            DataOutputStream stockname = new DataOutputStream(s.getOutputStream());;
            stockname.writeUTF(input.nextLine());
            
            DataInputStream output = new DataInputStream(s.getInputStream());
            String sname=output.readUTF();
            
            if(sname.equals("Error,Stock not found in database"))
            System.out.println(sname);
               else 
                {
                        System.out.println("The stock name is :");
                        System.out.println(sname);
            
                        DataInputStream shortform = new DataInputStream(s.getInputStream());
                        String sfname=shortform.readUTF();
                        System.out.println("The stock abbreviation is :");
                        System.out.println(sfname);
            
                        DataInputStream bp = new DataInputStream(s.getInputStream());
                        String bprice=bp.readUTF();
                        System.out.println("The stock buying price is :");
                        System.out.println(bprice);
            
                        DataInputStream sp = new DataInputStream(s.getInputStream());
                        String sprice=sp.readUTF();
                        System.out.println("The stock selling price is :");
                        System.out.println(sprice);  
                }
            }            
    }catch(Exception e) {} 
           
    }
    
    }