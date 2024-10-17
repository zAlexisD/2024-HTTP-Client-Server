import java.io.*;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;

public class httpClient {
    // Variables to store URL components
    private String protocol, host, filename;
    private int port;

    // Constructor to initialize the HttpClient with the basic URL components
    public httpClient(int port, String protocol, String host, String filename) {
        this.port = port;
        this.protocol = protocol;
        this.host = host;
        this.filename = filename;
    }

    // This method parses the given URL string and extracts its components
    public void readURL(String firstEltCommandLine) throws MalformedURLException {
        URL url = new URL(firstEltCommandLine);
        port = url.getPort();
        // If no port is specified (port = -1), use the default port for the protocol (80 for HTTP in our case)
        if(port==-1){
            port = url.getDefaultPort();
        }
        protocol = url.getProtocol();
        host = url.getHost();
        filename = url.getFile();
    }

    // This method sends an HTTP GET request and saves the response to a file
    public void getURL() throws IOException {
        // Open a socket connection to the server using the extracted host and port
        try (Socket socket = new Socket(host, port);
             InputStream from = socket.getInputStream();                      // InputStream to read the server response
             OutputStream output = socket.getOutputStream();
             PrintWriter to = new PrintWriter(output, true)) {       // PrintWriter to send the HTTP request

            // getFile() actually returns the path
            String path = filename;

            // Send the HTTP GET request
            to.println("GET " + path + " HTTP/1.1\r\n" + "Host: " + host + "\r\n" + "User-Agent: XXX\r\n\r\n");

            // Determine the filename to save the response to
            int lastSlashIndex = path.lastIndexOf('/');
            String newFileName = path.substring(lastSlashIndex + 1);

            // Save the response data (HTML or file content) to a local file
            try (OutputStream out = new FileOutputStream(newFileName)) {
                byte[] buf = new byte[4096];
                int bytesRead;
                while ((bytesRead = from.read(buf)) != -1) {
                    out.write(buf, 0, bytesRead);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        // Initialize empty variables for HttpClient
        String initiateProtocol = "";
        String initiateHost = "";
        int initiateHttpPort = 0;
        String initiateFileName = "";

        httpClient client  = new httpClient(initiateHttpPort,initiateProtocol,initiateHost,initiateFileName);
        client.readURL(args[0]);
        client.getURL();
    }
}
