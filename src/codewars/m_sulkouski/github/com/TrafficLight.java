package codewars.m_sulkouski.github.com;

import javafx.scene.effect.Light;

import java.util.LinkedList;

public class TrafficLight {


    public static void main(String[] args) {
        String[] roadSituation = trafficLights("C...R............G......", 10);
        for (String s : roadSituation) {
            System.out.println(s);
        }
    }

    public static String[] trafficLights(String road, int n) {
        int elapsedTime = 0;                                                    //Time elapsed from last light switch (G to R or R to G
        String[] roadSituation = new String[n + 1];
        roadSituation[elapsedTime] = road;
        road = road.replace('C', '.');

        Road simulatedRoad = new Road(road);

        for (elapsedTime = 1; elapsedTime <= n; elapsedTime++) {
            roadSituation[elapsedTime] = simulatedRoad.simulateRoadTraffic();
        }


        return roadSituation;
    }

}

class Road {
    private char[] road;
    private LinkedList<Lights> lights = new LinkedList<>();
    private char[] roadSection;
    private int carPosition;

    public Road(String road) {
        char[] template = {'R', 'G', 'Y'};
        for (char ch : road.toCharArray()) {                                // Generating list of lights based on initial road lights pattern
            for (int i = 0; i < template.length; i++) {
                if (template[i] == 'N') {
                    continue;
                }
                if (ch == template[i]) {
                    Lights light = new Lights(ch,road);
                    lights.add(light);
                    template[i] = 'N';
                }
            }
        }

        roadSection = new char[road.length()];
        clearRoadSection();
        this.carPosition = 0;
    }

    private void clearRoadSection() {
        for (int i = 0; i < roadSection.length; i++) {
            roadSection[i] = '.';
        }
    }

    public String simulateRoadTraffic() {
        clearRoadSection();
        for (Lights obj : this.lights) {               //simulate 1 second passed for lights
            obj.passTime();
            obj.simulateLightsControl();
        }


        for (Lights obj : this.lights) {                //add lights to the road
            for (int k : obj.getLocations()) {
                roadSection[k] = obj.getValue();
            }
        }

        if (this.carPosition < roadSection.length - 1) {
            if (roadSection[carPosition + 1] == 'G' || roadSection[carPosition + 1] == '.') {
                carPosition++;
            }
            roadSection[carPosition] = 'C';
        }

        return String.valueOf(roadSection);
    }

}

class Lights {
    private int[] locations;               // contains location for all lights
    private int timeElapsed;               // time elapsed from the initialization
    private char value;                    // current value of group (R for red, G for green, O for orange

    public Lights(char value, String road) {
        int counter = 0;
        this.value = value;
        char[] ch = road.toCharArray();
        for (char c : ch) {
            if (c == this.value) {
                counter++;
            }
        }
        locations = new int[counter];

        for (int i = 0, k = 0; i < ch.length; i++) {
            if (ch[i] == this.value) {
                locations[k++] = i;
            }
        }
    }

    public int[] getLocations() {
        return locations;
    }

    public void passTime() {
        this.timeElapsed++;
    }

    public char getValue() {
        return value;
    }


    public void simulateLightsControl() {
        if (this.timeElapsed == 1 && this.value == 'O') {
            this.value = 'R';
            this.timeElapsed = 0;
        } else if (this.timeElapsed == 5) {
            this.value = this.value == 'R' ? 'G' : 'O';
            this.timeElapsed = 0;
        }
    }
}

