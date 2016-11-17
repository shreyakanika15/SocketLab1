package clientCase;

import java.net.*;
import java.io.*;

public class ClientEcho {

	public static void main(String[] args) {

		//define variables
		int port = 80;
		BufferedReader bufread = new BufferedReader(new InputStreamReader(System.in));
		String str1=null;  

		//starting of try-catch block
		try{
			System.out.println("Please enter the string to change to uppercase");
			str1 = bufread.readLine();
		}
		catch(Exception exp){

			exp.printStackTrace();

		}
		//for-loop for getting the string to convert to uppercase

		for (int i = 0; i < args.length; i++) {

			try {
				URL u1 = new URL(args[i]);//url object
				if (u1.getPort() != -1) port = u1.getPort();
				if (!(u1.getProtocol().equalsIgnoreCase("http"))) {
					System.err.println("Sorry. Only http is valid");
					continue;
				}
				// Initializing socket
				Socket s = new Socket(u1.getHost(), port);
				OutputStream output = s.getOutputStream();
				PrintWriter p = new PrintWriter(output, false);
				p.print("GET " + u1.getFile()+"/?message="+str1 + " HTTP/1.0\r\n"  );
				p.print("Accept: text/plain, text/html, text/*\r\n");
				p.print("\r\n");
				p.flush();
				InputStream input = s.getInputStream();
				InputStreamReader inpread = new InputStreamReader(input);
				BufferedReader br = new BufferedReader(inpread);
				int a;
				while ((a = br.read()) != -1) {
					System.out.print((char) a);
				}
				s.close();


			}
			// for exception of invalid URL
			catch (MalformedURLException e) {
				System.err.println(args[i] + " is not a valid URL");
			}
			catch (IOException e) {
				System.err.println(e);
			}

		}

	}

}