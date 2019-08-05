package codewars.m_sulkouski.github.com;

import java.util.ArrayList;
import java.util.Arrays;

public class DirReduction {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(dirReduc(new String[]{"NORTH", "SOUTH", "SOUTH", "EAST", "WEST", "NORTH", "WEST"})));
    }

    public static String[] dirReduc(String[] arr) {
        ArrayList<Direction> rawDirections = new ArrayList<>();
        Direction[] directions = new Direction[4];
        directions[0] = new Direction("NORTH", "SOUTH");
        directions[1] = new Direction("SOUTH", "NORTH");
        directions[2] = new Direction("EAST", "WEST");
        directions[3] = new Direction("WEST", "EAST");

        for (String str : arr) {
            for (Direction direction : directions) {
                if (direction.getDirection().equals(str)) {
                    rawDirections.add(direction);
                    break;
                }
            }
        }

        for (int i = 0; i < rawDirections.size() - 1; i++) {
            if (rawDirections.get(i).getDirection().equals(rawDirections.get(i+1).getOppositeDirection())) {
                rawDirections.remove(i + 1);
                rawDirections.remove(i);
                i = -1;
            }
        }

        String[] optimizedDirections = new String[rawDirections.size()];

        for (int i = 0; i < optimizedDirections.length; i++) {
            optimizedDirections[i] = rawDirections.get(i).getDirection();
        }

        return optimizedDirections;
    }
}


class Direction {
    private final String DIRECTION;
    private final String OPPOSITE_DIRECTION;

    Direction(String directionName, String oppositeDirectionName) {
        this.DIRECTION = directionName;
        this.OPPOSITE_DIRECTION = oppositeDirectionName;
    }

    String getDirection() {
        return DIRECTION;
    }

    public String getOppositeDirection() {
        return OPPOSITE_DIRECTION;
    }
}

