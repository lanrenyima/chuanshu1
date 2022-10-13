import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class UploadClient {

    public static void main(String[] args) throws UnknownHostException, IOException {
        Socket socket = new Socket("127.0.0.1", 10005);
        BufferedReader bufferedReader = new BufferedReader(new FileReader("add.txt"));
        PrintWriter outPrintWriter = new PrintWriter(socket.getOutputStream() ,true);

        String lineString = null;
        while ((lineString=bufferedReader.readLine())!=null) {
            outPrintWriter.println(lineString);
        }
        socket.shutdownOutput();
        BufferedReader bufIn =  new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String string = bufIn.readLine();
        System.out.println(string);
        bufferedReader.close();
        socket.close();
    }

}