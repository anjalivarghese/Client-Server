import java.net.Socket;
import java.io.*;

public class ServerThread extends Thread
{
    Socket socket;
    
    ServerThread(Socket socket)
    {
        this.socket = socket;
    }
    
    public void run() 
    {
        String[][] stock = new String[][]
                 {
                  {"3D Systems","DDD","12.89","13.31"},
                  {"3M","MMM","200.52","201.04"},
                  {"8X8","EGHT","21.70","22.30"},
                  {"58.com ADR","WUBA","85.83","87.64"},
                  {"500.com ADR","WBAI","17.57"},
                  {"A10 Networks","ATEN","6.10","8.10"},
                  {"Boston Scientific","BSX","29.71","30.16"},
                  {"FedEx","FDX","247.78","248.72"},
                  {"New Relic","NEWR","87.25","90.36"},
                  {"Phillips 66","PSX","117.92","118.25"}
                  };

             try{
                 
                 DataInputStream auth_name = new DataInputStream(socket.getInputStream());
                 String name = auth_name.readUTF();
                 System.out.println("Client Connected:"+auth_name.readUTF());
                 
                 DataOutputStream message1 = new DataOutputStream(socket.getOutputStream());
                 message1.writeUTF("Welcome to the Stock Market");
                 
                 DataOutputStream message2 = new DataOutputStream(socket.getOutputStream());
                 message2.writeUTF("How many stock do you want to search");
                 
                 DataInputStream stock_count = new DataInputStream(socket.getInputStream());
                 int count=Integer.parseInt(stock_count.readUTF());
                 System.out.println("Client wants to search for "+count+" stocks");
                 
                 for ( int j=0 ; j<count;j++)
                 {
                 
                     DataOutputStream message3 = new DataOutputStream(socket.getOutputStream());
                     message3.writeUTF("Name of the stock you want to search");
                 
                     DataInputStream stock_name = new DataInputStream(socket.getInputStream());
                     String stock_req=stock_name.readUTF();
                     System.out.println("Client searched for the stock "+stock_req);
                 
                     int flag = 1;
                 
                        for ( int i = 0 ; i < stock.length;i++)
                            {   
                 
                                if(stock[i][0].equals(stock_req))
                                {
                                    DataOutputStream stockname = new DataOutputStream(socket.getOutputStream());
                                    stockname.writeUTF(stock[i][0]);
                                    DataOutputStream shortform = new DataOutputStream(socket.getOutputStream());
                                    shortform.writeUTF(stock[i][1]);
                                    DataOutputStream buyingprice = new DataOutputStream(socket.getOutputStream());
                                    buyingprice.writeUTF(stock[i][2]);
                                    DataOutputStream sellingprice = new DataOutputStream(socket.getOutputStream());
                                    sellingprice.writeUTF(stock[i][3]);
                                    break;
                                }
                                else
                                flag = 0;
                            }
                        if(flag == 0)
                            {
                            DataOutputStream error = new DataOutputStream(socket.getOutputStream());
                            error.writeUTF("Error,Stock not found in database");
                            }
                 }
                 socket.close();
            }catch(Exception e ) {}
             }
      }

