import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    class mark {
        public int x;
        public int y;
        public int player;
    }

    public static Scanner keyboard = new Scanner(System.in);

    public static void main(String[] args) {

        int mapSize = 9;
        int winCon = 3;

        printTik(mapSize);

        mark[] marks = new mark[mapSize * mapSize];
        createMarks(marks, mapSize);

        addBox(marks);

    }

    private static void createMarks(mark[] marks, int mapSize) {
        //counts left to right, then switches row and continues

        //column switcher
        for (int y = 0; y < mapSize; y++) {

            //row switcher
            for (int x = 0; x < mapSize; x++) {

                marks[y * mapSize + x].y = y;
                marks[y * mapSize + x].x = x;
                marks[y * mapSize + x].player = 0;

            }

        }

    }

    private static void printTik(int size) {

        if (size > 26) {
            System.out.println("ERROR: Size = " + size + " Size cannot be larger than 26");
            size = 26;
        }

        //top row
        System.out.print("==");
        for (int i = 0; i < size; i++) {
            System.out.print(numLetter(i));
            System.out.print("=");
        }
        System.out.println();

        //middle rows
        for (int i = 0; i < size; i++) {
            System.out.print(i + 1);
            for (int j = 0; j < size; j++) {
                System.out.print(" =");
            }
            System.out.println();
        }

        //bottom row
        System.out.print("==");
        for (int i = 0; i + 1 < size; i++) {
            System.out.print("==");
        }
        System.out.println("==");

    }

    private static Character numLetter(int num) {
        char[] numbers = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

        return numbers[num];
    }

    private static void addBox(mark[] marks) {

        System.out.println("write where you want your mark to be like this: B:12");
        String boxLocation = keyboard.nextLine().trim().toUpperCase();

        int[] currentMark;

        while (true) {
            currentMark = checkInputCorrect(boxLocation, marks);
            if (currentMark[0] != -1) {
                break;
            } else {
                System.out.println("ERROR: you did not input correctly");
                System.out.println("write where you want your mark to be like this: B:12");
                boxLocation = keyboard.nextLine().trim().toUpperCase();
            }

        }


    }

    private static int[] checkInputCorrect(String input, mark[] marks) {

        int mapSize = (int) Math.round(Math.sqrt(marks.length));

        //1. has an x and y
        int[] location = checkInputNormal(input);
        if (location[0] == -1) {
            return location;
        }

        int border = -1;

        if (hasNeighbor(location, marks, mapSize)) {

            return location;

        } else {

            return new int[]{-1, 0};

        }

    }

    private static boolean hasNeighbor(int[] location, mark[] marks, int mapSize) {

        int y = location[0];
        int x = location[1];

        mark[] neighborMarks = new mark[9];

        for (int i = 0; i < 3; i++) {

            for (int j = 0; j < 3; j++) {

                neighborMarks[i * 3 + j].x = x + j - 1;
                neighborMarks[i * 3 + j].y = y + i - 1;

            }

        }

        neighborMarks[5] = null;


        for (int i = 0; i < 9; i++) {

            if (neighborMarks[i].x < 0 || neighborMarks[i].x > mapSize - 1) {
                neighborMarks[i] = null;
            } else if (neighborMarks[i].y < 0 || neighborMarks[i].y > mapSize - 1) {
                neighborMarks[i] = null;
            }

        }


        for (mark neighborMark : neighborMarks) {

            for (mark mark : marks) {

                if (neighborMark == null) {
                    break;
                }

                if (neighborMark.y == mark.y && neighborMark.x == mark.x) {

                    if (mark.player != 0) {

                        return false;

                    }

                }

            }

        }

        return true;

    }

    private static int[] checkInputNormal(String input) {

        int[] falseOut = {-1};

        if (!input.contains(":")) {
            return (falseOut);
        }

        int middle = input.indexOf(":");
        char[] letters = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

        //check if x can be a char
        if (middle != 1) {
            return (falseOut);
        }

        char[] xLetter = input.substring(0, middle).toCharArray();

        int x = -1;
        for (int i = 0; i < letters.length; i++) {
            if (xLetter[0] == letters[i]) {
                x = i;
                break;
            }
        }

        if (x == -1) {
            return (falseOut);
        }
        //X is real

        String yLetter = input.substring(middle + 1);
        int y;
        try {
            y = Integer.parseInt(yLetter);
        } catch (NumberFormatException ex) {
            return (falseOut);
        }
        //Y is real


        return new int[]{x, y};

    }

//s3wcyd5 english code
}



