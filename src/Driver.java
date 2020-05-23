import java.io.IOException;

/**
 * Driver Class
 */
public class Driver
{
    public static void main(String[] args) throws IOException {
        Machine start = Machine.getInstance();
        start.parkingGarage();
    }
}
