import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        try 
        { 
            System.out.println( "Il client è partito" );
            Socket socket = new Socket("localhost", 4567); //creo il socket e lo connetto al server
            
            Scanner input = new Scanner(System.in); 
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            System.out.println( "Connessione effettuata" );
            int risposta = 0;
            do 
            {
                System.out.println( "Inserisci il numero: " );
                String n = input.nextLine();
                out.writeBytes(n+"\n");
                String confronto = in.readLine();
                risposta = Integer.parseInt(confronto);
                if(risposta == 1)
                {
                    System.out.println( "Numero troppo piccolo" );
                }
                if(risposta == 2)
                {
                    System.out.println( "Numero troppo grande" );
                }
                if(risposta == 3)
                {
                    String c = in.readLine();
                    int r = Integer.parseInt(c);
                    System.out.println( "Numero indovinato in "+r+" tentativi" );
                }
            } while (risposta != 3);
            input.close();
            socket.close();
        } 
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("errore durante l'istanza del server");
            System.exit(1);
        }
    }
}