package aoc.day10;

import aoc.Day;

import java.util.*;
import java.util.stream.Collectors;


public class Day10 implements Day {

    @Override
    public String part1(List<String> input) {

        List<Asteroid> asteroids = getAsteroidFromInput(input);


        for (Asteroid asteroid : asteroids) {
            asteroid.populateAsteroidSlopeChart(asteroids);
        }

        Asteroid maxSightLines = asteroids.stream().max(Comparator.comparing(Asteroid::getSightLinesToOtherAsteroids)).get();

        System.out.println("Asteroid " + maxSightLines.toString() + " has the most sight lines with : " + maxSightLines.sightLinesToOtherAsteroids); //part1


        return input.isEmpty() ? "" : String.valueOf(maxSightLines.sightLinesToOtherAsteroids); //part1
    }

    @Override
    public String part2(List<String> input) {
        List<Asteroid> asteroids = getAsteroidFromInput(input);


        for (Asteroid asteroid : asteroids) {
            asteroid.populateAsteroidSlopeChart(asteroids);
        }

        Asteroid maxSightLines = asteroids.stream().max(Comparator.comparing(Asteroid::getSightLinesToOtherAsteroids)).get();

        List<Asteroid> destroyedAsteroids = asteroids.stream().max(Comparator.
                comparing(Asteroid::getSightLinesToOtherAsteroids)).get().
                destroyAllAsteroidsInSlopeMapInOrder();

        System.out.println("Asteroid " + destroyedAsteroids.get(200 - 1) + " was the 200th to be destroyed" ); //part2


        return input.isEmpty() ? "" : String.valueOf((destroyedAsteroids.get(200 - 1).xCord * 100) + destroyedAsteroids.get(200 - 1).yCord);
    }



    private static List<Asteroid> getAsteroidFromInput(List<String> input) {
        List<Asteroid> asteroids = new ArrayList<>();
        int yCount = 0;

        List<Integer> lineLengths = new ArrayList<>();
        for (String s: input){
            int xCount = 0;
            for (Character character : s.toCharArray()) {
                if (character == '#') {
                    asteroids.add(new Asteroid(xCount, yCount));
                }
                xCount++;
            }

            lineLengths.add(s.length());
            yCount++;
        }

        assertLineLengthsAreAllTheSame(lineLengths);
        return asteroids;
    }

    private static void assertLineLengthsAreAllTheSame(List<Integer> lineLengths) {
        int firstLineLength = lineLengths.get(0);

        for(int i = 1; i < lineLengths.size(); i++) {
            if(firstLineLength != lineLengths.get(i)) {
                throw new RuntimeException("Input lines are not a rectangle.");
            }
        }
    }

    public static class Asteroid {
        private int xCord;
        private int yCord;
        private Map<Double, List<Asteroid>> asteroidSlopeMap;
        private int sightLinesToOtherAsteroids;

        Asteroid(int xCord, int yCord) {
            this.xCord = xCord;
            this.yCord = yCord;
        }

        int getSightLinesToOtherAsteroids() {
            return sightLinesToOtherAsteroids;
        }

        void populateAsteroidSlopeChart(List<Asteroid> asteroids) {
            asteroidSlopeMap = new HashMap<>();

            for(Asteroid asteroid : asteroids) {
                if(!this.equals(asteroid)){
                    double slope = ((double) (this.yCord - asteroid.yCord)) / (this.xCord - asteroid.xCord);
                    if(slope == Double.POSITIVE_INFINITY) {
                        slope = Double.NEGATIVE_INFINITY; //this doesnt matter but helps for part 2
                    }
                    List<Asteroid> slopeList = asteroidSlopeMap.getOrDefault(slope, new ArrayList<>());
                    slopeList.add(asteroid);
                    asteroidSlopeMap.putIfAbsent(slope, slopeList);
                }
            }

            for(List<Asteroid> slopeList : asteroidSlopeMap.values()) {
                slopeList.add(this); //this is important to figure out which ones in this slope the asteroid can find.
                sortSlopList(slopeList);
            }

            sightLinesToOtherAsteroids = findSightLinesToOtherAsteroids();
        }

        private void sortSlopList(List<Asteroid> slopList){
            slopList.sort((o1, o2) -> {
                if(o1.xCord != o2.xCord) {
                    return Integer.compare(o1.xCord, o2.xCord);
                }
                else {
                    return Integer.compare(o1.yCord, o2.yCord);
                }
            });
        }

        private int findSightLinesToOtherAsteroids(){
            int countOfAsteroidsThatCanBeSeen = 0;
            for(List<Asteroid> asteroids : asteroidSlopeMap.values()) {
                int indexOfThisInSlopeList = asteroids.indexOf(this);
                if(indexOfThisInSlopeList > 0) {
                    countOfAsteroidsThatCanBeSeen++;
                }
                if(indexOfThisInSlopeList < asteroids.size() -1) {
                    countOfAsteroidsThatCanBeSeen++;
                }
            }
            return countOfAsteroidsThatCanBeSeen;
        }

        private List<Asteroid> destroyAllAsteroidsInSlopeMapInOrder() {
            List<Asteroid> removedSet = new ArrayList<>();
            List<Double> keySet = asteroidSlopeMap.keySet().stream().sorted(Comparator.comparingDouble(o -> o)).collect(Collectors.toList()); // up in problem text is - infinity :D straight right is 0.

            boolean belowMode = false;
            boolean done = false;
            while (!done) {
                done = true;

                for(double key : keySet) {
                    List<Asteroid> asteroidsInSlope = asteroidSlopeMap.get(key);
                    int thisIndex = asteroidsInSlope.indexOf(this);

                    if(belowMode) {
                        if(thisIndex != 0) {
                            Asteroid removed = asteroidsInSlope.remove(thisIndex - 1);
                            removedSet.add(removed);
                        }
                    }
                    else {
                        if(thisIndex != asteroidsInSlope.size() - 1) {
                            Asteroid removed = asteroidsInSlope.remove(thisIndex + 1);
                            removedSet.add(removed);
                        }
                    }
                    if(asteroidsInSlope.size() > 1) {
                        done = false;
                    }
                }

                belowMode = !belowMode;
            }
            return removedSet;
        }

        @Override
        public boolean equals(Object obj) {
            Asteroid other = (Asteroid) obj;
            return this.xCord == other.xCord && this.yCord == other.yCord;
        }

        @Override
        public String toString(){
            return "(" + xCord + "," + yCord + ")";
        }
    }
}
