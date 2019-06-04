package si.um.feri.Util;

import java.math.BigDecimal;
import java.nio.ByteBuffer;
import java.util.Random;
import java.util.UUID;

public final class Util {
    private Util(){};

    private static Random r = new Random();

    public static byte[] generateBinrayUuid() {
        return uuidToBinary(UUID.randomUUID());
    }

    public static byte[] uuidToBinary(UUID uuid) {
        byte[] uuidBytes = new byte[16];
        ByteBuffer.wrap(uuidBytes)
                .putLong(uuid.getMostSignificantBits())
                .putLong(uuid.getLeastSignificantBits());
        return uuidBytes;
    }

    public static UUID binaryToUuid(byte[] bytes) {
        ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
        Long high = byteBuffer.getLong();
        Long low = byteBuffer.getLong();
        return new UUID(high, low);
    }

    /**
     *  Generates a random price between lower and upper rounded to 2 decimal places
     * @param lower lower bound
     * @param upper upper bound
     * @return a random BigDecimal number between lower and upper rounded to 2 decimal places
     */
    public static BigDecimal generateRandomPrice(double lower, double upper) {

        if(lower > upper) {
            System.err.println("generateRandomPrice: the value of lower must be smaller than upper");
            return BigDecimal.valueOf(0d);
        }
        double price = lower + (upper - lower) * r.nextDouble();

        return BigDecimal.valueOf(price).setScale(2, BigDecimal.ROUND_FLOOR);
    }
}

