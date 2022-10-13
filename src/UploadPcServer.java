import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

class  ServerThread  extends Thread{
    Socket  socket;
    ServerThread  (Socket  t) {
        socket = t;
        try {
            InputStream inputStream = socket.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
        public  void  run(){
            String ipString = socket.getInetAddress().getHostAddress();
            File dirFile = new File("Users/Desktop/");
            if (!dirFile.exists()) {
                dirFile.mkdirs();
            }
            InputStream inputStream = null;
            try {
                inputStream = socket.getInputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }
            File file = new File(dirFile, ipString + ".mp4");
            FileOutputStream fileOutputStream = null;
            try {
                fileOutputStream = new FileOutputStream(file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        byte[] buf = new byte[1024];
        int len = 0;
        while (true) {
            try {
                if (!((len = inputStream.read(buf))!=-1)) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                fileOutputStream.write(buf ,0 ,len);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

            OutputStream outputStream = null;
            try {
                outputStream = socket.getOutputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
            outputStream.write("上传成功".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

}
public class UploadPcServer {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket=null;
        Socket socket = null;
        while (true){
            try{ serverSocket = new ServerSocket(10007);
            }
            catch(IOException  e1){
                System.out.println("正在监听");//ServerScoket对象不能重复建立
            }
            socket = serverSocket.accept();
            System.out.println("等待客户呼叫");
            System.out.println("客户的地址:"+socket.getInetAddress());
            if(socket!=null){
                new  ServerThread(socket).start();
                System.out.println("连接成功");
            }
        }




    }

}