import java.io.* ;
import java.net.* ;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.nio.file.WatchEvent.Kind;
import java.nio.file.WatchEvent.Modifier;
import java.util.* ;


final class HttpRequest implements Runnable{
	private Socket socket;
	final static String CRLF = "\r\n";
	
	public HttpRequest(Socket s) {
		this.socket = s;
	}

	public static void main(String argv[]) throws Exception{
		int port = 5000;
		ServerSocket socketServer = new ServerSocket(port); 
	
		
		while(true ){
			System.out.println("Waiting for HTTP Request");
			Socket s = socketServer.accept();
			System.out.println("HTPP Request Accepted");
			HttpRequest request = new HttpRequest(s);
			Thread thread = new Thread(request);
			thread.start();
		}
		
	}

	@Override
	public void run() {
		try{
			processRequest();
		}catch(Exception e){
			System.out.println(e);
		}

	}

	private static void sendBytes(FileInputStream fis, OutputStream os)
			throws Exception {
		// Construct a 1K buffer to hold bytes on their way to the socket.
		byte[] buffer = new byte[1024];
		int bytes = 0;
		// Copy requested file into the socket's output stream.
		while ((bytes = fis.read(buffer)) != -1) {
			os.write(buffer, 0, bytes);
		}
	}
	
	private void processLogin(String msg, GenericMessage userData){
		String args[] = msg.split("+");
		String login = args[0];
		String pass  = args[1];
		
		String query = "SELECT password FROM Usuario WHERE login = "+userData.userId;
		
		GenericMessage gm = DatabaseAcess.requestQuey(query, RequestType.LOGIN);
		
		
		
	}
	
	private void processRequest() throws Exception{
		InputStream is = this.socket.getInputStream();
		DataOutputStream os = new DataOutputStream(this.socket.getOutputStream());
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		
		String requeLine = br.readLine();
		
		StringTokenizer tokens = new StringTokenizer(requeLine);
		tokens.nextToken();
		String fileName = tokens.nextToken();
		fileName = "." + fileName;
		
		FileInputStream fis = null;
		boolean fileExists  = true;
		try{
			fis = new FileInputStream(fileName);		
			System.out.println("Achou o arquivo");
		}catch ( FileNotFoundException e){
			System.out.println( System.getProperty("user.dir"));
			System.out.println("Não achou o arquivo");
			fileExists = false;
		}
		
		String statusLine = null;
		String contentTypeLine = null;
		String entityBody = null;
		if(fileExists){
			statusLine  = "HTTP/1.1 200 OK";
			contentTypeLine = "Content-type: " +
					contentType( fileName ) + CRLF;
		}else{
			statusLine = "HTTP/1.1 404 Not Found" ;
			contentTypeLine = "text/html";
			entityBody = "<HTML>" +
					"<HEAD><TITLE>404 NOT FOUND </TITLE></HEAD>" +
					"<BODY>Not Found</BODY></HTML>";
		}
		os.writeBytes(statusLine+"\n");
		os.writeBytes("Date: "+ System.currentTimeMillis()+"\n");
		os.writeBytes("Server: Redes I Server(Linux)\n");
		os.writeBytes("Connection: close\n");
		os.writeBytes(contentTypeLine);
		os.writeBytes(CRLF);
		
		if(fileExists){
			sendBytes(fis, os);
			fis.close();
			
		}else{
			os.writeBytes("\n");
			os.writeBytes(entityBody);
			try{
				fis.close();
			}catch(Exception e){
				os.writeBytes("\n");
			}
		}
		
		
		System.out.println();
		System.out.println(requeLine);
		
		String headerLine = null;
		while((headerLine = br.readLine()).length()!=0){
			System.out.println(headerLine);
			
		}
		
		os.close();
		br.close();
		this.socket.close();
	}

	private String contentType(String fileName) {
		 
		try {
			return Files.probeContentType(Paths.get(fileName));
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}
	}
}