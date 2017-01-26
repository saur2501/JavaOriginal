/*
 ABHINAV SHRIVASTAVA - MT2015003
 SAURABH DEVGUN - MT2015101
*/
package cn.socketProgramming;

public class RUN_TCP_SERVER {
      
    public static void main(String[] args) throws Exception {
           System.out.println("Invoking TCP Server....");
                 //   tcpserver = new TCPServer();
                    System.out.println("TCP Server running....");
                   
                    while(true)
                    {   TCPServer tcpserver = null;
                        tcpserver.run(); 
                       
                    }
                    
    }
}
