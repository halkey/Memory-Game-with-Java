import java.net.*; 
import java.io.*; 



public class Client 
{ 

   int isnumeric(String x)
    {
        try
        {
            int user_number = Integer.parseInt(x);
            return user_number;
        }

        catch(Exception e)
        {
            return -101010;
        }
    }
    // initialize socket and input output streams 
    Socket socket            = null; 
    DataInputStream  in   = null; 
    DataInputStream  input   = null;
    DataOutputStream out     = null; 
    int counter = 3;
    // constructor to put ip address and port 
    public Client(String address, int port) 
    { 
        // establish a connection 
        try
        { 
            socket = new Socket(address, port); 
 
            
            // takes input from terminal 
            in  = new DataInputStream(System.in); 
            input = new DataInputStream(socket.getInputStream());
  
            // sends output to the socket 
            out    = new DataOutputStream(socket.getOutputStream()); 
        } 
        catch(UnknownHostException u) 
        { 
            System.out.println(u); 
        } 
        catch(IOException i) 
        { 
            System.out.println(i); 
        } 
  
        // string to read message from input 
        String line = ""; 
  
        // keep reading until "Over" is input 
        int result = 0;

        int true_number;
        System.out.println("This is the NUMBER GUESSING GAME.\nYou have 3 chances to find the number that generated randomly by the server.\nChoose any number between 1 to 10 :");
        while (true) 
        {

            try
            { 
     
                line = in.readLine();
                counter--;
              
                
               int user_number = isnumeric(line);
               if(user_number == -101010)
               {
                System.out.println("YOU HAVE TO ENTER TRUE A NUMBER!");
                counter++;
                continue;
               }
                
                if(user_number < 1 || user_number > 10 )
                {
                    System.out.println("Your number has to be between 1 to 10");
                    counter++;
                    continue;
                }

                
                
                out.writeUTF(line);
                result = input.readInt();
                if(result == 1)
                {
                    System.out.println("YOU WON THE GAME!");
                    break;
                }   

                else if(result == 2)
                {
                    System.out.println("TOO HIGH! Decrease your number.");
                }
                
                else if(result == 3)
                {
                    System.out.println("TOO LOW! Increase your number.");
                }
                System.out.println("Remaining Chances = "+ counter);
              
                if(counter ==0)
                {
                    true_number = input.readInt();
                    System.out.println("You Couldn't Find The True Number.\nTrue Number Was: " +true_number +"\nGAME IS OVER!");
                    break;
                }
            } 
            catch(IOException i) 
            { 
                System.out.println(i); 
            }


        } 
        

        // close the connection 
        try
        { 
            in.close(); 
            out.close(); 
            socket.close(); 
        } 
        catch(IOException i) 
        { 
            System.out.println(i); 
        } 
    } 
  
    public static void main(String args[]) 
    { 

        Client client = new Client("127.0.0.1", 5000); 
    } 
} 