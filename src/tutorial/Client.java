package tutorial;

import java.util.Scanner;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

public class Client {
    private static final int PORT = 5278;

    public static void main(String[] args) {
            try {
                    TTransport transport = new TSocket("127.0.0.1", PORT);
                    TBinaryProtocol protocol = new TBinaryProtocol(transport);
                    AnswerService.Client client = new AnswerService.Client(protocol);
                    transport.open();
                    GamePlay(client);
                    transport.close();
            } catch (TTransportException e) {
                    e.printStackTrace();
            } catch (TException e) {
                    e.printStackTrace();
            }
   
    }

    public static void GamePlay(AnswerService.Client client) throws TException
    {

        System.out.print("Do you want to play a game? \nType yes or no!\n");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        AnswerStruct answer = client.getAnswer(input);
        String x = answer.getAnswerObject();
        if (x.equals("great"))
        		{
        		System.out.println("Oh! That is great!\nLet us start! Type yes or no!\n");
        		System.out.println("Is it a mammal?");
        		String x1 = input(client);
                	if (x1.equals("great"))
                	{
                		System.out.println("Does it have 4 legs?");
                		String x2 = input(client);
                		if (x2.equals("great"))
                		{
                			System.out.println("Does it bark?");
                    		String x3 = input(client);
                			if (x3.equals("great"))
                				System.out.println("The answer is DOG!! \nIt was great playing with you.\nHave a great day!! :)\n");
                			else if (x3.equals("sorry"))
                				Another(client);
                			else wrong(client);
                		}
                		else if (x2.equals("sorry"))
                			Another(client);
                		else wrong(client);
                	}	
                	else if (x1.equals("sorry"))
            			Another(client);
            		else wrong(client);
                		}
    
        else if(x.equals("sorry"))
        {
        	System.out.println("Are you sure?");
        	Scanner scanner4 = new Scanner(System.in);
    		String input4 = scanner4.nextLine();
    		AnswerStruct answer4 = client.getAnswer(input4);
            String x4 = answer4.getAnswerObject();
            if (x4.equals("sorry"))
            {
            	GamePlay(client);
            }
            else if (x4.equals("great"))
			{	
				System.out.println("Okay!! Have a nice day!!\n");
			}
    		else wrong(client);            
            scanner4.close();
        }
		else wrong(client);
        scanner.close();
  	}
    


public static void wrong(AnswerService.Client client) throws TException
{
	System.out.println("Sorry! Wrong input!! Let us start from the scratch...\n");
	GamePlay(client);
	
}

public static String input(AnswerService.Client client) throws TException
{
	Scanner scanner1 = new Scanner(System.in);
	String input1 = scanner1.nextLine();
	AnswerStruct answer1 = client.getAnswer(input1);
    String x1 = answer1.getAnswerObject();
    return x1;
	
}
public static void Another(AnswerService.Client client) throws TException
{
	System.out.println("Oops!! That is not right!! \nPlease try again! :) \nLet us start again!\n");
	GamePlay(client);
}

}