package ua.rubezhanskii.javabookshop.exceptions;

public class StockExhaustedException extends RuntimeException {

    private String message;

    public StockExhaustedException(String message1) {

        this.message = message1;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
