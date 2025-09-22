package learning.java.pro.excetpion;

public class LowDailyLimitException extends RuntimeException {

    public LowDailyLimitException(String message) {
        super(message);
    }
}
