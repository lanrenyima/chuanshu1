
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class UploadServer {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(10005);
        Socket socket = serverSocket.accept();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedWriter bufw = new BufferedWriter(new FileWriter("server.txt"));

        String lineString = null;
        while ((lineString = bufferedReader.readLine())!= null) {
            bufw.write(lineString);
            bufw.newLine();
            bufw.flush();
        }

        PrintWriter outPrintWriter = new PrintWriter(socket.getOutputStream());
        outPrintWriter.println("上传成功");
        bufw.close();
        socket.close();
        serverSocket.close();

    }

}