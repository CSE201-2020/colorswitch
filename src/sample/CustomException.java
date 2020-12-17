package sample;

public class CustomException {
    public class InactivityTimeoutException extends Exception{
        InactivityTimeoutException(String s) {
            super("Inactivity for 5 minutes !!");
            System.out.println();
        }
    }
    public class NoSavedGames extends Exception{
        NoSavedGames() {
            super("NO Saved Games FOUND !!");
            System.out.println();
        }
    }
    public class NotEnoughStars extends Exception{
        NotEnoughStars() {
            super("not enough stars");
            System.out.println();
        }
    }

}
