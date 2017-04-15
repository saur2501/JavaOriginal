/*
 ABHINAV SHRIVASTAVA - MT2015003
 SAURABH DEVGUN - MT2015101
*/
//TODO- not explored
package system.cn.socketProgrammingProject;

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
                    {   UDPServerProject udpserver = null;
                        udpserver.run(); 
                    }
    }
}
