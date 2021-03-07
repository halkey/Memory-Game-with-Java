import java.net.*; 
import java.io.*; 







public class Server 
{ 

    Game game = new Game();
    //initialize socket and input stream 
 
    private ServerSocket    my_server= null; 
    private Socket          socket   = null;
    private DataInputStream in       = null;
    private DataOutputStream out     = null; 
 
    // constructor with port 
    public Server(int port) 
    { 

        try
        { 
            int counter = 3;
            my_server = new ServerSocket(port); 
            System.out.println("Server started"); 
  
            System.out.println("Waiting for a client ..."); 
  
            socket = my_server.accept(); 
            System.out.println("Client accepted"); 
  

            in = new DataInputStream(socket.getInputStream()); 
            out = new DataOutputStream(socket.getOutputStream()); 
  
            String line = ""; 
            int result = 0;

            while (true) 
            { 

                line = in.readUTF(); 
                counter--;
                System.out.println("The Number Entered By The User = "+ line);
                System.out.println("The Number Generated Randomly By the Server = "+game.answer);

                int user_value = Integer.parseInt(line);

                result = game.result(user_value);

                out.writeInt(result);
                if(result == 1)
                {
                    System.out.println("User Won The Game");
                    break;
                }   

                else if(result == 2)
                {
                    System.out.println("TOO HIGH! User has to decrease the number.");
                }
                
                else if(result == 3)
                {
                    System.out.println("TOO LOW! User has to increase the number.");
                }

                if(counter ==0)
                {
                    System.out.println("User Couldn't Find The True Number");
                    out.writeInt(game.answer);
                    break;
                }

                


                
            } 
            System.out.println("Closing connection"); 
  
            // close connection 
            socket.close(); 
            in.close(); 
        } 
        catch(IOException i) 
        { 
            System.out.println(i); 
        } 
    } 
  
    public static void main(String args[]) 
    { 
        Server server = new Server(5000);
    } 
} 

class Game {
    int answer;


    public Game()
    {
        answer = (int)(Math.random() * 10 + 1);


    }

    public Integer result(int guess)
    {
        if(guess == answer)
        {

            return 1;
        }

        else if(guess > answer)
        {
            return 2;  
        }

        else   
        {
            return 3;
        }

    }

}
