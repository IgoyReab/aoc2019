package aoc.day10;

import aoc.Day;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class Day10a implements Day {

    @Data
    private class Asteroid {
        public Asteroid(int x, int y) {
            this.x = x;
            this.y = y;
            this.amountVisible = 0;
            this.visibleFromHere = new ArrayList<>();
        }

        int x;
        int y;
        int amountVisible;

        List<Asteroid> visibleFromHere;

    }

    @Data
    private class Blocked {
        public Blocked(int x, int y) {
            this.x = x;
            this.y = y;
        }

        int x;
        int y;
    }

    @Data
    private class AsteroidList {
        List<Asteroid> asteroids;

        int width;
        int height;

        public AsteroidList(int w, int h) {
            asteroids = new ArrayList<>();
            this.width = w;
            this.height = h;
        }

        public void addAsteroid(Asteroid a){
            asteroids.add(a);
        }
        private boolean findCoordinates(Asteroid a, int x, int y) {
            return ((a.getX() == x) &&  (y == a.getY()));
        }

        public Asteroid getAsteroid(int x, int y){
            List<Asteroid> foundAsteroid = asteroids.stream().filter(asteroid -> findCoordinates(asteroid, x, y)).collect(Collectors.toList());
            return (foundAsteroid.size() > 0) ? foundAsteroid.get(0) : null;
        }

        public void printMap(){
            for (int y=0; y < height; y++) {
                for (int x=0; x < width; x++) {
                    Asteroid a = getAsteroid(x,y);
                    if (x == 0) System.out.println();
                    if (a == null) {
                        System.out.print(".");
                    } else {
                        System.out.print("#");
                    }

                }
            }
            System.out.println();
        }

        public boolean collinear (Asteroid a, Asteroid b, Asteroid c) {
            return ( a.getX() * (b.getY() - c.getY()) +
                     b.getX() * (c.getY() - a.getY()) +
                     c.getX() * (a.getY() - b.getY()) == 0 );
        }

        public void calculateVisible(Asteroid a){
            int i = a.getX();
            int j = a.getY();


        }

        public void calculateAsteroids(){
            for (int y=0; y < height; y++) {
                for (int x=0; x < width; x++) {
                    Asteroid a = getAsteroid(x,y);
                    if (a!=null) calculateVisible(a);
                }
            }
            System.out.println();
        }

    }


    @Override
    public String part1(List<String> input) {
        AsteroidList asteroids= new AsteroidList(input.get(0).length(), input.size());

        for (int y=0; y < input.size(); y++) {
            for (int x=0; x < input.get(y).length(); x++) {
                if (input.get(y).charAt(x) == '#') asteroids.addAsteroid(new Asteroid(x,y));
            }
        }

        asteroids.printMap();
        asteroids.calculateAsteroids();

        return input.isEmpty() ? "" : "";
    }

    @Override
    public String part2(List<String> input) {
        return input.isEmpty() ? "" : "";
    }
}
