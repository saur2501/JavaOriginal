/*
 ABHINAV SHRIVASTAVA - MT2015003
 SAURABH DEVGUN - MT2015101
*/

package cn.socketProgramming;

/**
 *
 * @author DELL
 */
public class RUN_UDP_SERVER {
 
    public static void main(String[] args) throws Exception {
        System.out.println("Invoking UDP Server....");
                  //  udpserver = new UDPServer();
                    System.out.println("UDP Server running....");
                    
                    while(true)
                    {   UDPServer udpserver = null;
                        udpserver.run(); 
                    }
    }
}
