import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Server {
    public static void main(String[] args) {
        DateTimeFormatter oFormatador = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        try {
            
            BufferedReader scanDiferenciado = new BufferedReader(new InputStreamReader(System.in));
            int porta = 1234;
            
            DatagramSocket serverSocket = new DatagramSocket(porta);
            byte[] receiveData = new byte[1024];
            byte[] sendData = new byte[1024];

            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

            System.out.println("Esperando por UDP na porta " + porta + "...");
            serverSocket.receive(receivePacket);

            System.out.println("RECEBIDO!");
            String message = new String(receivePacket.getData());
            System.out.println("Mensagem recebida:'" + message + "' as " + oFormatador.format(LocalDateTime.now()));

            System.out.println("Responder com: ");
            String returnMessage = scanDiferenciado.readLine();
            sendData = returnMessage.getBytes();

            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, receivePacket.getAddress(), receivePacket.getPort());

            System.out.println("Enviando mensagem de retorno...");
            serverSocket.send(sendPacket);

            System.out.println("ENVIADO!");
            System.out.println("Fim da conexão");

            serverSocket.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
