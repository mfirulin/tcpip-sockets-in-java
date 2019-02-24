import java.util.Enumeration;
import java.net.*;

public class InetAddressExample {
    
    public static void main(String[] args) {
        // Get the network interfaces and associated addresses for this host
        try {
            Enumeration<NetworkInterface> interfaceList = NetworkInterface.getNetworkInterfaces();
            
            if (interfaceList != null) {
                while (interfaceList.hasMoreElements()) {
                    NetworkInterface iface = interfaceList.nextElement();
                    System.out.println("Interface " + iface.getName() + ":");
                    Enumeration<InetAddress> addressList = iface.getInetAddresses();
                    
                    if (!addressList.hasMoreElements())
                        System.out.println("\t(No addresses for this interface)");
                    
                    while (addressList.hasMoreElements()) {
                        InetAddress address = addressList.nextElement();
                        System.out.print("\tAddress "
                            + ((address instanceof Inet4Address ? "(v4)"
                            : (address instanceof Inet6Address ? "(v6)" : "(?)"))));
                        System.out.println(": " + address.getHostAddress());
                    }
                }
            } else {
                System.out.println("No interfaces found");
            }
        } catch (SocketException se) {  // getNetworkInterfaces can throw SocketException
            System.out.println("Error getting network interfaces: " + se.getMessage());
        }

        // Get name(s)/address(es) of hosts given on command line
        for (String host: args) {
            try {
                System.out.println(host + ":");
                InetAddress[] addressList = InetAddress.getAllByName(host);
                
                for (InetAddress address: addressList) {
                    System.out.println("\t" + address.getHostName() + "/" + 
                        address.getHostAddress());
                }
            } catch (UnknownHostException e) {
                System.out.println("\tUnable to find address for " + host);
            }
        }
    }
}
