import java.io.IOException;
import java.net.*;

public class Client {

    private int port = 20777;
    private final byte[] buf = new byte[2048];

    private DatagramSocket connection;
    private DatagramPacket datagramPacket;

    /**
     * Calls createConnection without reassigning the default port.
     */
    public Client() {
        try {
            createConnection();
        } catch (SocketException e) {
            System.out.println("An exception occurred:" + e);
            e.printStackTrace();
        }
    }

    /**
     * Calls createConnection after assigning a new port to the default port.
     * @param port The port, in decimal, to listen on.
     */
    public Client(int port) {
        this.port = port;
        try {
            createConnection();
        } catch (SocketException e) {
            System.out.println("Oopsy whoopsy, an exception occurred:" + e);
            e.printStackTrace();
        }
    }

    /**
     * Defines a new DatagramSocket and DatagramPacket, for listening on the port and being able to hold packets when
     * they arrive, respectively.
     * @throws SocketException see {@link DatagramSocket}, {@link SocketException}.
     */
    private void createConnection() throws SocketException {
        datagramPacket = new DatagramPacket(buf, buf.length);
        connection = new DatagramSocket(this.port);
    }

    /**
     * Will read all packets from UDP buffer as they arrive, and call a function to act on it
     * @throws IOException
     */
    public void receiveHandlePackets() throws IOException {
        final int packetIdIndex = 5; // From F1 2020 specification
        final String[] packetIdMapping = {
                "Motion",
                "Session",
                "Lap Data",
                "Event",
                "Participants",
                "Car Setups",
                "Car Telemetry",
                "Car Status",
                "Final Classification",
                "Lobby Info"
        };

        while (true) {
            connection.receive(datagramPacket);
            byte[] packetContents = datagramPacket.getData();

            System.out.println(packetIdMapping[packetContents[packetIdIndex]]);
        }
    }

     public static void main(String[] args) {
        Client client;

        if (args.length > 2) { // Only excepting -p or --port at time of writing, so no more than 2 args (-p and port number)
            throw new IllegalArgumentException("Too many arguments.");
        } else if (args.length > 0) { // If args exist, check they are legal args
            switch (args[0]) {
                case "-p": // Overflow to -port as they do the same thing
                case "-port":
                    if (args[1] != null) { // Check args[1] exists, if not, no port number was provided
                        int tempPort = Integer.parseInt(args[1]);
                        if (tempPort >= 0 && tempPort <= 65535) {
                            client = new Client(tempPort);
                        } else {
                            throw new IllegalArgumentException("Port " + tempPort + " out of range (0-65535).");
                        }
                    } else {
                        throw new IllegalArgumentException("A port number is required for -p or --port");
                    }
                    break;
                default: // If port unrecognised, throw exception
                    throw new IllegalArgumentException("Not a valid argument: " + args[0] + ". \n Only arguments accepted are -p XXXXX, -port XXXXX");
            }
        } else { // No args provided, go with default port
            client = new Client();
        }

        try {
            client.receiveHandlePackets();
        } catch (IOException e) {
            System.out.println("An Exception Occurred: \n" + e);
            e.printStackTrace();
        }

    }
}
