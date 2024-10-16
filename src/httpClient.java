import java.io.*;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;

public class httpClient {
    public String protocol, host, filename;
    public int port;
    public long sleepTime = 10;

    public httpClient(int port, String protocol, String host, String filename) {
        this.port = port;
        this.protocol = protocol;
        this.host = host;
        this.filename = filename;
    }

    public void readURL(String firstEltCommandLine) throws MalformedURLException {
        URL url = new URL(firstEltCommandLine);
        port = url.getPort();
        if(port==-1){
            port = url.getDefaultPort();
        }
        protocol = url.getProtocol();
        host = url.getHost();
        filename = url.getFile();
    }

    public void getURL() throws IOException, InterruptedException {
        Socket socket = new Socket(host,port);
        Thread.sleep(sleepTime);

        OutputStream output = socket.getOutputStream();
        PrintWriter to = new PrintWriter(output);

        to.println("GET "+ filename +" HTTP/1.1\r\n"+"Host: "+ host+"\r\n"+"User-Agent: XXX");

        int lastSlashIndex = filename.lastIndexOf('/');
        String newFileName = filename.substring(lastSlashIndex+1);
        OutputStream out = new FileOutputStream(newFileName);
        InputStream from = socket.getInputStream();

        byte[] buf = new byte[4096];
        int bytes_read;
        while((bytes_read = from.read(buf)) != -1){
            out.write(buf,0,bytes_read);
        }

        to.close();
        out.close();
        socket.close();
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        String initiateProtocol = "";
        String initiateHost = "";
        int initiateHttpPort = 0;
        String initiateFileName = "";

        httpClient client  = new httpClient(initiateHttpPort,initiateProtocol,initiateHost,initiateFileName);
        client.readURL(args[0]);
        client.getURL();
    }
}
