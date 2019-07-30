package com.company;

public class TrafficLight {
    private static int carPosition;

    public static void main(String[] args) {
        String[] roadSituation = trafficLights("C...R............G......", 40);
        for (String s : roadSituation) {
            System.out.println(s);
        }
    }

    public static String[] trafficLights(String road, int n) {
        int elapsedTime = 0;                                                    //Time elapsed from last light switch (G to R or R to G
        road = road.replace('C', '.');
        String currentLights = road;
        String[] roadSituation = new String[n];
        roadSituation[elapsedTime] = moveCar(road);

        for (elapsedTime = 1; elapsedTime < n; elapsedTime++) {
            currentLights = currentLights.replace("C",".");
            if((elapsedTime % 6 == 5)) {
                currentLights = roadSituation[elapsedTime - 1];
                roadSituation[elapsedTime] = switchYellowLights(road);
            }
            else if(elapsedTime %6 == 0){
                currentLights = switchRedGreenLights(currentLights,elapsedTime);
                roadSituation[elapsedTime] = currentLights;
            }
            else {
                roadSituation[elapsedTime] = currentLights;
            }

            if (carPosition < roadSituation[elapsedTime].length() - 1) {
                if(roadSituation[elapsedTime].charAt(carPosition + 1) != 'R') {
                    carPosition++;
                    roadSituation[elapsedTime] = moveCar(roadSituation[elapsedTime]);
                }
                else if(roadSituation[elapsedTime].charAt(carPosition + 1) == 'R') {
                    roadSituation[elapsedTime] = moveCar(roadSituation[elapsedTime]);
                }
            }
        }

        return roadSituation;
    }


    private static String switchYellowLights(String lights) {
        char[] ch = lights.toCharArray();
        for (int i = 0; i < ch.length; i++) {
            if (ch[i] == 'G' || ch[i] == 'R') {
                ch[i] = 'O';
            }
        }
        return String.valueOf(ch);
    }

    private static String moveCar(String simulatedLights) {
        char[] ch = simulatedLights.toCharArray();
        if (carPosition == ch.length)
            return simulatedLights;

        ch[carPosition] = 'C';

        return String.valueOf(ch);
    }

    private static String switchRedGreenLights(String road, int currentTimer) {

        char[] ch = road.toCharArray();
        for (int i = 0; i < road.length(); i++) {
            if(ch[i] == 'R') {
                ch[i] = 'G';
            }
            else if(ch[i] == 'G') {
                ch[i] = 'R';
            }
        }

        return String.valueOf(ch);
    }


}
