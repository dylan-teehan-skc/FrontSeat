package MapAndTaxis;

import static MapAndTaxis.Car.taxiJourney;
import static MapAndTaxis.TaxiMap.*;
import static MapAndTaxis.User.getPlayerX;
import static MapAndTaxis.User.getPlayerY;
import static LocationHandling.Review.GiveReview;

public class TaxiDriving {
//    public static void taxiGoToPlayer() throws InterruptedException {
//        moveTaxis();
//        boolean taxiArrived = false;
//
//        for (int i = 0; i < 10 && !taxiArrived; i++) {
//            // Clear the console (print empty lines)
//            clearConsole();
//
//            int closestTaxiIndex = findClosestTaxi();
//
//            // Perform A* pathfinding to get the path
//            List<Node> path = findPathAStar(taxiX[closestTaxiIndex], taxiY[closestTaxiIndex],
//                    getPlayerX(), getPlayerY());
//
//            // Move the taxi along the path
//            moveTaxiAlongPath(closestTaxiIndex, path);
//
//            // Check for nearby taxis
//            taxiArrived = checkIfTaxiArrived(closestTaxiIndex);
//
//            // Print the map
//            printMap(map);
//
//            // Sleep for 2 seconds
//            Thread.sleep(2000);
//        }
//    }
//
//    private static void moveTaxiAlongPath(int taxiIndex, List<Node> path) {
//        if (path.size() > 1) {  // Ensure there is a valid path
//            Node nextMove = path.get(1);
//            int newX = nextMove.getX();
//            int newY = nextMove.getY();
//
//            // Update the taxi position
//            map[taxiX[taxiIndex]][taxiY[taxiIndex]] = "-";
//            taxiX[taxiIndex] = newX;
//            taxiY[taxiIndex] = newY;
//            map[newX][newY] = "T";
//        }
//    }
//
//    private static List<Node> findPathAStar(int startX, int startY, int targetX, int targetY) {
//        PriorityQueue<Node> openSet = new PriorityQueue<>();
//        Set<Node> closedSet = new HashSet<>();
//
//        Node startNode = new Node(startX, startY);
//        Node targetNode = new Node(targetX, targetY);
//
//        openSet.add(startNode);
//
//        while (!openSet.isEmpty()) {
//            Node current = openSet.poll();
//
//            if (current.equals(targetNode)) {
//                return reconstructPath(current);
//            }
//
//            closedSet.add(current);
//
//            for (Node neighbor : getNeighbors(current)) {
//                if (closedSet.contains(neighbor)) {
//                    continue;
//                }
//
//                int tentativeGScore = current.getG() + 1; // Assuming a uniform cost of movement
//
//                if (!openSet.contains(neighbor) || tentativeGScore < neighbor.getG()) {
//                    neighbor.setG(tentativeGScore);
//                    neighbor.setH(manhattanDistance(neighbor, targetNode));
//                    neighbor.setF(neighbor.getG() + neighbor.getH());
//                    neighbor.setParent(current);
//
//                    if (!openSet.contains(neighbor)) {
//                        openSet.add(neighbor);
//                    }
//                }
//            }
//        }
//
//        return Collections.emptyList(); // No path found
//    }
//
//    private static int manhattanDistance(Node a, Node b) {
//        return Math.abs(a.getX() - b.getX()) + Math.abs(a.getY() - b.getY());
//    }
//
//    private static List<Node> reconstructPath(Node current) {
//        List<Node> path = new ArrayList<>();
//        while (current != null) {
//            path.add(current);
//            current = current.getParent();
//        }
//        Collections.reverse(path);
//        return path;
//    }
//
//    private static List<Node> getNeighbors(Node node) {
//        List<Node> neighbors = new ArrayList<>();
//        int x = node.getX();
//        int y = node.getY();
//
//        // Add valid neighbors within the map boundaries
//        if (x > 0) {
//            neighbors.add(new Node(x - 1, y));
//        }
//        if (x < mapSize - 1) {
//            neighbors.add(new Node(x + 1, y));
//        }
//        if (y > 0) {
//            neighbors.add(new Node(x, y - 1));
//        }
//        if (y < mapSize - 1) {
//            neighbors.add(new Node(x, y + 1));
//        }
//
//        return neighbors;
//    }
//
//    // Node class representing a point on the map
//    private static class Node implements Comparable<Node> {
//        private final int x;
//        private final int y;
//        private int g; // Cost from the start node to this node
//        private int h; // Heuristic (estimated cost from this node to the target node)
//        private int f; // Total cost (g + h)
//        private Node parent;
//
//        public Node(int x, int y) {
//            this.x = x;
//            this.y = y;
//        }
//
//        public int getX() {
//            return x;
//        }
//
//        public int getY() {
//            return y;
//        }
//
//        public int getG() {
//            return g;
//        }
//
//        public void setG(int g) {
//            this.g = g;
//        }
//
//        public int getH() {
//            return h;
//        }
//
//        public void setH(int h) {
//            this.h = h;
//        }
//
//        public int getF() {
//            return f;
//        }
//
//        public void setF(int f) {
//            this.f = f;
//        }
//
//        public Node getParent() {
//            return parent;
//        }
//
//        public void setParent(Node parent) {
//            this.parent = parent;
//        }
//
//        @Override
//        public boolean equals(Object o) {
//            if (this == o) return true;
//            if (o == null || getClass() != o.getClass()) return false;
//            Node node = (Node) o;
//            return x == node.x && y == node.y;
//        }
//
//        @Override
//        public int hashCode() {
//            return Objects.hash(x, y);
//        }
//
//        @Override
//        public int compareTo(Node other) {
//            return Integer.compare(this.f, other.f);
//        }
//    }


    public static void taxiGoToPlayer() throws InterruptedException {
        moveTaxis();
        boolean taxiArrived = false;

        for (int i = 0; i < 10 && !taxiArrived; i++) {
            // Clear the console (print empty lines)
            clearConsole();

            int closestTaxiIndex = findClosestTaxi();

            // Move the closest taxi towards the player
            int playerXCoord = getPlayerX();
            int playerYCoord = getPlayerY();
            int taxiXCoord = taxiX[closestTaxiIndex];
            int taxiYCoord = taxiY[closestTaxiIndex];

            int newX, newY;

            if (taxiXCoord < playerXCoord) {
                newX = taxiXCoord + 1;
            } else if (taxiXCoord > playerXCoord) {
                newX = taxiXCoord - 1;
            } else {
                newX = taxiXCoord;
            }

            if (taxiYCoord < playerYCoord) {
                newY = taxiYCoord + 1;
            } else if (taxiYCoord > playerYCoord) {
                newY = taxiYCoord - 1;
            } else {
                newY = taxiYCoord;
            }

            // Check boundaries to prevent going out of the map
            newX = Math.max(0, Math.min(newX, mapSize - 1));
            newY = Math.max(0, Math.min(newY, mapSize - 1));

            // Update the taxi position
            map[taxiX[closestTaxiIndex]][taxiY[closestTaxiIndex]] = "-";
            taxiX[closestTaxiIndex] = newX;
            taxiY[closestTaxiIndex] = newY;
            map[newX][newY] = "T";

            // Check for nearby taxis
            taxiArrived = checkIfTaxiArrived(closestTaxiIndex);

            // Print the map
            printMap(map);

            // Sleep for 2 seconds
            Thread.sleep(2000);
        }
    }

    public static void taxiGoToDestination(int destinationX, int destinationY) throws InterruptedException {
        taxiJourney();
        double distance = Math.sqrt(Math.pow(getPlayerX() - destinationX, 2) + Math.pow(getPlayerY() - destinationY, 2));
        //System.out.println("distance is " + distance);
        int closestTaxiIndex = findClosestTaxi();
        boolean taxiArrived = false;
        boolean repeat = true;

        while (repeat) {
            clearConsole();

            int taxiXCoord = taxiX[closestTaxiIndex];
            int taxiYCoord = taxiY[closestTaxiIndex];

            // Move the closest taxi directly to the destination
            int newX = (taxiXCoord < destinationX) ? taxiXCoord + 1 : (taxiXCoord > destinationX) ? taxiXCoord - 1 : taxiXCoord;
            int newY = (taxiYCoord < destinationY) ? taxiYCoord + 1 : (taxiYCoord > destinationY) ? taxiYCoord - 1 : taxiYCoord;

            // Check boundaries to prevent going out of the map
            newX = Math.max(0, Math.min(newX, mapSize - 1));
            newY = Math.max(0, Math.min(newY, mapSize - 1));

            map[taxiX[closestTaxiIndex]][taxiY[closestTaxiIndex]] = "-";
            taxiX[closestTaxiIndex] = newX;
            taxiY[closestTaxiIndex] = newY;

            // ANSI escape code for red text
            String redColorCode = "\u001B[31m";
            // ANSI escape code to reset the text color to default
            String resetColorCode = "\u001B[0m";

            map[newX][newY] = redColorCode + "T" + resetColorCode;

            // Check if the taxi arrived at the destination
            if (taxiXCoord == destinationX && taxiYCoord == destinationY) {
                taxiArrived = true;
                System.out.println("You have reached your destination");
                System.out.println("You travelled " + distance);

                repeat = false; // Exit the loop when the destination is reached
            } else {
                printMap(map); // Print the map only if the taxi hasn't arrived yet
            }

            // Sleep for 2 seconds
            Thread.sleep(2000);
        }

        // Update the map one last time after reaching the destination
        map[taxiX[closestTaxiIndex]][taxiY[closestTaxiIndex]] = "T";
        GiveReview();

    }
}
