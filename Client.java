import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Client{
    public static void main(String[] args) {
        DateTimeFormatter oFormatador = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        try {
            BufferedReader scanDiferenciado = new BufferedReader(new InputStreamReader(System.in));

            int porta = 1234;
            String server = "localhost";
            InetAddress IPAddress = InetAddress.getByName(server);

            byte[] sendData = new byte[1024];
            byte[] receiveData = new byte[1024];
            
            DatagramSocket clientSocket = new DatagramSocket();

            System.out.println("Digite o texto a ser enviado para o servidor: ");
            String message = scanDiferenciado.readLine();
            sendData = message.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData,sendData.length, IPAddress, porta);

            System.out.println("Enviando mensagem...");

            clientSocket.send(sendPacket);

            System.out.println("MENSAGEM ENVIADA");

            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

            clientSocket.receive(receivePacket);

            System.out.println("MENSAGEM RECEBIDA");

            String messageReturned = new String(receivePacket.getData());

            System.out.println("Mensagem: '" + messageReturned + "' as "+ oFormatador.format(LocalDateTime.now()));

            System.out.println("Fim da conexão");

            clientSocket.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}