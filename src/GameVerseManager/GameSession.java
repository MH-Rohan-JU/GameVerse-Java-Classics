package GameVerseManager;

public class GameSession {
    private static String currentUser;

    public static void setCurrentUser(String user) {
        currentUser = user;
    }

    public static String getCurrentUser() {
        return currentUser;
    }
}