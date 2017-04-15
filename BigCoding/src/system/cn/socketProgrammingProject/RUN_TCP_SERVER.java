/*
 ABHINAV SHRIVASTAVA - MT2015003
 SAURABH DEVGUN - MT2015101
*/
package system.cn.socketProgrammingProject;
//TODO- not explored
public class RUN_TCP_SERVER {
      
    public static void main(String[] args) throws Exception {
           System.out.println("Invoking TCP Server....");
                 //   tcpserver = new TCPServer();
                    System.out.println("TCP Server running....");
                   
                    while(true)
                    {   TCPServerProject tcpserver = null;
                        tcpserver.run(); 
                       
                    }
                    
    }
}
