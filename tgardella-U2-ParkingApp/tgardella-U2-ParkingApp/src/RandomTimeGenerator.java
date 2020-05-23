import java.time.LocalTime;
import java.util.Random;

public class RandomTimeGenerator
{
    private final int ZERO_MINUTES = 0;

    public RandomTimeGenerator() {

    }

    public LocalTime generateTimeAtCheckIn() {
        Random rand = new Random();
        int hour = rand.nextInt((12 - 7) + 1) + 7;
        return LocalTime.of(hour, ZERO_MINUTES);
    }

    public LocalTime generateTimeAtCheckOut() {
        Random rand = new Random();
        int hour = rand.nextInt((23 - 13) + 1) + 1;
        return LocalTime.of(hour, ZERO_MINUTES);
    }
}
