

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.PortUnreachableException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
public class UploadPcClient {
    public static void main(String[] args) throws UnknownHostException, IOException {
        Socket socket = null;
        Scanner   scanner=new Scanner(System.in);
        System.out.println("输入服务器的IP和端口号：");
        try {
            socket = new Socket(scanner.next(), Integer.parseInt(scanner.next()));
        }
        catch (PortUnreachableException e){
            System.out.println("无法连接端口");
        }
        System.out.println("连接成功");
        System.out.println("输入你要上传文件在本地的路径：");
        FileInputStream fileInputStream = new FileInputStream(scanner.next());

        OutputStream outputStream = socket.getOutputStream();
        byte[] buf = new byte[1024];
        int len = 0;
        while ((len=fileInputStream.read(buf))!=-1) {
            outputStream.write(buf, 0, len);
        }
        socket.shutdownOutput();

        //读取服务器端发回的数据
        InputStream inputStream = socket.getInputStream();
        byte[] bufin = new byte[1024];
        int lenIn = inputStream.read(bufin);
        String textString = new String(bufin, 0, lenIn);
        System.out.println(textString);
        fileInputStream.close();
        socket.close();

    }

}