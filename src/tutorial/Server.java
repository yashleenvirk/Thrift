package tutorial;


import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TTransportException;

public class Server implements Runnable {
    private static final int PORT = 5278;

    @Override
    public void run() {
            try {
                    TServerSocket serverTransport = new TServerSocket(PORT);
                    AnswerService.Processor processor = new AnswerService.Processor(new AnswerHandler());
                    TServer server = new TThreadPoolServer(new TThreadPoolServer.Args(serverTransport).processor(processor));
                    System.out.println("Starting server on port " + PORT);
                    server.serve();
            } catch (TTransportException e) {
                    e.printStackTrace();
            }
    }

    public static void main(String[] args) {
            new Thread(new Server()).run();
    }
}
