import java.util.Scanner;

public class Task1 {

    public static void main(String[] args) {
        long ipInt =  new Scanner(System.in).nextLong();

        // Преобразование int32  в IPv4
        String ipAddress = integerToIp(ipInt);
        System.out.println("Int32 в IPv4: " + ipAddress);

        // Преобразование IPv4 в int32
        ipInt = ipToInteger(ipAddress);
        System.out.println("IPv4 в int32: " + ipInt);


    }

    // Функция преобразования  IPv4 в int32
    public static long ipToInteger(String ipAddress) {
        String[] octets = ipAddress.split("\\.");

        if (octets.length != 4) {
            throw new IllegalArgumentException("Некорректное IPv4 представление");
        }

        long result = 0;
        for (int i = 0; i < 4; i++) {
            int octet = Integer.parseInt(octets[i]);
            if (octet < 0 || octet > 255) {
                throw new IllegalArgumentException("Некорректное значение в октете: " + octet);
            }
            result = (result << 8) | (octet & 0xFF);
        }

        return result;
    }

    // Функция преобразования int32 в  IPv4
    public static String integerToIp(long ipInt) {
        StringBuilder ipAddress = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            long octet = (ipInt >> ((3 - i) * 8)) & 0xFF;
            ipAddress.append(octet);
            if (i < 3) {
                ipAddress.append(".");
            }
        }
        return ipAddress.toString();
    }
}
