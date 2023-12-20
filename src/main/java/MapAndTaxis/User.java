package MapAndTaxis;
// user class extending person
public class User extends Person {
    // user balance, player x and y are created with getter and setters
    private float balance;
    private static int playerX;
    private static int playerY;

    // user constructor
    public User(String name,  int playerX, int playerY, float balance) {
        super(name);
        this.balance = balance;
        this.playerX = playerX;
        this.playerY = playerY;
    }



    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public static int getPlayerX() {
        return playerX;
    }

    public static void setPlayerX(int playerX) {
        User.playerX = playerX;
    }

    public static int getPlayerY() {
        return playerY;
    }

    public static void setPlayerY(int playerY) {
        User.playerY = playerY;
    }
}
