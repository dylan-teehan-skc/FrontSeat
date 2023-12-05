//package MapAndTaxis;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Random;
//import java.util.Scanner;
//
//public class TaxiMap {
//    private static final int mapSize = 10; // Size of the game board
//    private static final char empty = '.'; // Symbol for an empty space on the board
//    private static final char taxiBody = 'T'; // Symbol for the taxi body on the board
//    private static final char user = 'P'; // Symbol for the user on the board
//    private List<Point> taxis; // List to store taxi positions
//    private Point userPoint; // Point to store the user position
//    private char[][] map; // 2D array to represent the game board
//    private Direction direction; // Direction in which the user moves
//
//    public void TaxiStart() {
//        taxis = new ArrayList<>(); // Initialize the list of taxis
//        map = new char[mapSize][mapSize]; // Initialize the game board
//        direction = Direction.RIGHT; // Set the initial direction to RIGHT
//        InitializeBoard(); // Initialize the game board with empty spaces
//        SpawnTaxis(); // Place taxis on the board
//        SpawnUser(); // Place the user on the board
//    }
//
//    private void InitializeBoard() {
//        for (int i = 0; i < mapSize; i++) {
//            for (int j = 0; j < mapSize; j++) {
//                map[i][j] = empty; // Set all spaces on the board to empty initially
//            }
//        }
//    }
//
//    private void SpawnTaxis() {
//        for (int i = 0; i < 5; i++) {
//            Random random = new Random();
//            int taxiX, taxiY;
//
//            do {
//                taxiX = random.nextInt(mapSize); // Generate a random x-coordinate for a taxi
//                taxiY = random.nextInt(mapSize); // Generate a random y-coordinate for a taxi
//            } while (map[taxiX][taxiY] != empty && isTaxiPositionOccupied(taxiX, taxiY));
//
//            taxis.add(new Point(taxiX, taxiY)); // Add the taxi's position to the list
//            map[taxiX][taxiY] = taxiBody; // Place the taxi on the board
//        }
//    }
//
//    private void SpawnUser() {
//        Random random = new Random();
//        int userX, userY;
//
//        do {
//            userX = random.nextInt(mapSize); // Generate a random x-coordinate for the user
//            userY = random.nextInt(mapSize); // Generate a random y-coordinate for the user
//        } while (map[userX][userY] != empty);
//
//        userPoint = new Point(userX, userY); // Set the user's position
//        map[userX][userY] = user; // Place the user on the board
//    }
//
//    private void printBoard() {
//        for (int i = 0; i < mapSize; i++) {
//            for (int j = 0; j < mapSize; j++) {
//                System.out.print(map[i][j] + " "); // Print the current state of the board
//            }
//            System.out.println();
//        }
//        System.out.println();
//    }
//
//    private void move() {
//        for (Point taxi : taxis) {
//            // Erase the previous position
//            map[taxi.x][taxi.y] = empty;
//
//            // Move the taxi based on the specified direction
//            switch (direction) {
//                case UP:
//                    taxi.x = (taxi.x - 1 + mapSize) % mapSize; // Move up and handle wrapping
//                    break;
//                case DOWN:
//                    taxi.x = (taxi.x + 1) % mapSize; // Move down and handle wrapping
//                    break;
//                case LEFT:
//                    taxi.y = (taxi.y - 1 + mapSize) % mapSize; // Move left and handle wrapping
//                    break;
//                case RIGHT:
//                    taxi.y = (taxi.y + 1) % mapSize; // Move right and handle wrapping
//                    break;
//            }
//
//            // Update the map with the new position
//            map[taxi.x][taxi.y] = taxiBody;
//        }
//
//        checkCollision(); // Check for collisions between taxis and the user
//        updateBoard(); // Update and print the current state of the board
//    }
//
//    private void checkCollision() {
//        for (Point taxi : taxis) {
//            if (taxi.equals(userPoint)) {
//                handleUserCollision(); // Handle collision between a taxi and the user
//            }
//        }
//    }
//
//    private void handleUserCollision() {
//        SpawnUser(); // Respawn the user in a new random position
//    }
//
//    private void updateBoard() {
//        printBoard(); // Print the updated state of the board
//    }
//
//    private void gameOver() {
//        System.out.println("Game Over!"); // Display game over message
//        System.exit(0); // Exit the program
//    }
//
//    private void handleInput() {
//        Scanner scanner = new Scanner(System.in);
//        char input = scanner.next().charAt(0);
//
//        switch (input) {
//            case 'w':
//                direction = Direction.UP; // Set the direction to UP
//                break;
//            case 's':
//                direction = Direction.DOWN; // Set the direction to DOWN
//                break;
//            case 'a':
//                direction = Direction.LEFT; // Set the direction to LEFT
//                break;
//            case 'd':
//                direction = Direction.RIGHT; // Set the direction to RIGHT
//                break;
//            default:
//                System.out.println("Invalid input. Use 'w', 's', 'a', or 'd'.");
//        }
//    }
//
//    public void run() {
//        while (true) {
//            printBoard(); // Print the current state of the board
//            handleInput(); // Handle user input to change the direction
//            move(); // Move taxis and update the board
//        }
//    }
//
//    public static void main(String[] args) {
//        TaxiMap taxiMap = new TaxiMap(); // Create an instance of the TaxiMap class
//        taxiMap.TaxiStart(); // Initialize the game
//        taxiMap.run(); // Start the game loop
//    }
//
//    private boolean isTaxiPositionOccupied(int x, int y) {
//        for (Point taxi : taxis) {
//            if (taxi.x == x && taxi.y == y) {
//                return true; // Check if the given position is occupied by a taxi
//            }
//        }
//        return false;
//    }
//}
