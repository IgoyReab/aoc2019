package aoc.day12;

import aoc.Day;
import lombok.Data;

import java.util.*;
import java.util.stream.Collectors;

import static java.lang.Math.abs;

public class Day12 implements Day {
    int iterations = 1000;

    @Override
    public String part1(List<String> input) {
        Jupiter jupiter = new Jupiter(input);

        for (int i=0 ; i<iterations; i++) {
            jupiter.applyGravityAndVelocity();
        }

        return input.isEmpty() ? "" : String.valueOf(jupiter.getTotalEnergy());
    }

    @Override
    public String part2(List<String> input) {

        Jupiter jupiter = new Jupiter(input);

        List<Integer> initialX = jupiter.moons.stream().map(moon -> moon.getPosition().getX()).collect(Collectors.toList());
        List<Integer> initialVelocityA = jupiter.moons.stream().map(moon -> moon.getVelocity().getA()).collect(Collectors.toList());
        List<Integer> initialY = jupiter.moons.stream().map(moon -> moon.getPosition().getY()).collect(Collectors.toList());
        List<Integer> initialVelocityB = jupiter.moons.stream().map(moon -> moon.getVelocity().getB()).collect(Collectors.toList());
        List<Integer> initialZ = jupiter.moons.stream().map(moon -> moon.getPosition().getZ()).collect(Collectors.toList());
        List<Integer> initialVelocityC = jupiter.moons.stream().map(moon -> moon.getVelocity().getC()).collect(Collectors.toList());

        long result = 0;
        long xCount = -1;
        long yCount = -1;
        long zCount = -1;

        boolean countFound = false;

        while (!countFound) {
            result++;

            jupiter.applyGravityAndVelocity();

            if (xCount == -1) {
                xCount = backToInitial(initialX, initialVelocityA,
                        jupiter.moons.stream().map(moon -> moon.getPosition().getX()).collect(Collectors.toList()),
                        jupiter.moons.stream().map(moon -> moon.getVelocity().getA()).collect(Collectors.toList()),
                        result);
            }

            if (yCount == -1) {
                yCount = backToInitial(initialY, initialVelocityB,
                        jupiter.moons.stream().map(moon -> moon.getPosition().getY()).collect(Collectors.toList()),
                        jupiter.moons.stream().map(moon -> moon.getVelocity().getB()).collect(Collectors.toList()),
                        result);
            }

            if (zCount == -1) {
                zCount = backToInitial(initialZ, initialVelocityC,
                        jupiter.moons.stream().map(moon -> moon.getPosition().getZ()).collect(Collectors.toList()),
                        jupiter.moons.stream().map(moon -> moon.getVelocity().getC()).collect(Collectors.toList()),
                        result);
            }

            if (xCount != -1 && yCount != -1 && zCount != -1) {
                countFound = true;
            }
        }

        return input.isEmpty() ? "" : String.valueOf(calcLeastCommonMultiple(xCount, yCount, zCount));
    }

   private static long backToInitial(List<Integer> initial, List<Integer> initialVelocity, List<Integer> current, List<Integer> currentVelocity, long result) {
        if(initial.equals(current) && currentVelocity.equals(initialVelocity)) {
            return result;
        }
        return -1;
   }

    private static long calcLeastCommonMultiple(long x, long y, long z) {
        long xyLeastCommonMultiple = (x * y) / calcGreatestCommonDivisor(x, y);

        return (xyLeastCommonMultiple * z) / calcGreatestCommonDivisor(xyLeastCommonMultiple, z);
    }

    private static long calcGreatestCommonDivisor(long x, long y) {
        if (x == 0 || y== 0) {
            return x + y ;
        } else {
            long biggerValue = Math.max(x, y);
            long smallerValue = Math.min(x, y);
            return calcGreatestCommonDivisor(biggerValue % smallerValue, smallerValue);
        }
    }




    @Data
    private class Position {
        private int x;
        private int y;
        private int z;

        public Position(int x, int y, int z){
            this.x = x;
            this.y = y;
            this.z = z;
        }

        public int getPotentialEnergy() {
            return abs(x) + abs(y) + abs(z);
        }
    }

    @Data
    private class Velocity {
        private int a;
        private int b;
        private int c;

        public Velocity(){
            this.a = 0;
            this.b = 0;
            this.c = 0;
        }

        public int getKineticEnergy() {
            return abs(a) + abs(b) + abs(c);
        }
    }

    @Data
    private class Moon{
        private int number;
        private Position position;
        private Velocity velocity;

        public Moon(int number, int x, int y, int z) {
            this.number = number;
            position = new Position(x,y,z);
            velocity = new Velocity();
        }

        public void applyVelocity(){
            position.setX(position.getX() + velocity.getA());
            position.setY(position.getY() + velocity.getB());
            position.setZ(position.getZ() + velocity.getC());
        }

        public int getPotentialEnergy(){
            return position.getPotentialEnergy();
        }

        public int getKineticEnergy(){
            return velocity.getKineticEnergy();
        }

        public int getEnergy() {
            return position.getPotentialEnergy() * velocity.getKineticEnergy();
        }
    }

    @Data
    private class MoonPair {
        Moon firstMoon;
        Moon secondMoon;
        public MoonPair(Moon m, Moon n){
            this.firstMoon = m;
            this.secondMoon = n;
        }


        public void applyGravity(){
            int x1 = firstMoon.getPosition().getX();
            int y1 = firstMoon.getPosition().getY();
            int z1 = firstMoon.getPosition().getZ();

            int x2 = secondMoon.getPosition().getX();
            int y2 = secondMoon.getPosition().getY();
            int z2 = secondMoon.getPosition().getZ();

            int a1 = firstMoon.getVelocity().getA();
            int b1 = firstMoon.getVelocity().getB();
            int c1 = firstMoon.getVelocity().getC();

            int a2 = secondMoon.getVelocity().getA();
            int b2 = secondMoon.getVelocity().getB();
            int c2 = secondMoon.getVelocity().getC();

            if (x1 < x2) { a1++; a2--; }
            if (x1 > x2) { a1--; a2++; }
            if (y1 < y2) { b1++; b2--; }
            if (y1 > y2) { b1--; b2++; }
            if (z1 < z2) { c1++; c2--; }
            if (z1 > z2) { c1--; c2++; }

            firstMoon.getVelocity().setA(a1);
            firstMoon.getVelocity().setB(b1);
            firstMoon.getVelocity().setC(c1);

            secondMoon.getVelocity().setA(a2);
            secondMoon.getVelocity().setB(b2);
            secondMoon.getVelocity().setC(c2);
        }
    }


    @Data
    private class Jupiter {
        List<Moon> moons;
        List<MoonPair> moonPairs;

        public Jupiter(List<String> input) {
            moons = new ArrayList<>();
            moonPairs = new ArrayList<>();
            String name = "";

            int count = 0;
            for (String s : input) {
                int x = 0;
                int y = 0;
                int z = 0;
                count++;
                String[] s1 = s.replaceAll("<|>| ", "").split(",");
                for (String s2 : s1) {
                    String[] s3 = s2.split("=");
                    if (s3[0].equals("x")) x = Integer.parseInt(s3[1]);
                    if (s3[0].equals("y")) y = Integer.parseInt(s3[1]);
                    if (s3[0].equals("z")) z = Integer.parseInt(s3[1]);
                }
                addMoon(count, x, y, z);
            }

            addPair(1, 2);
            addPair(1, 3);
            addPair(1, 4);
            addPair(2, 3);
            addPair(2, 4);
            addPair(3, 4);
        }

        public void applyGravityAndVelocity() {
            for (MoonPair p : getMoonPairs()) {
                p.applyGravity();
            }
            for (Moon m : getMoons()) {
                m.applyVelocity();
            }
        }

        public Moon getMoon(int n) {
            for (Moon m : moons) {
                if (m.getNumber() ==n) return m;
            }
            return null;
        }

        private void addMoon(int n, int x, int y, int z) {
            moons.add(new Moon(n, x, y, z));
        }

        public void addPair(int o, int p) {
            moonPairs.add(new MoonPair(getMoon(o), getMoon(p)));
        }

        public void printJupiter() {
            System.out.println();
            for (Moon m : moons) {
                System.out.print("pos =<" + m.getPosition().getX() + "," + m.getPosition().getY() + "," +
                        m.getPosition().getZ() + ">, vel=");
                System.out.print(m.getVelocity().getA() + "," + m.getVelocity().getB() + "," +
                        m.getVelocity().getC() + "> " + "Moon=" + m.getNumber());
                System.out.println(" pot : " + m.getPotentialEnergy() + ";  kin : " + m.getKineticEnergy() +
                        "; " + " total : " + m.getEnergy());
            }
            System.out.println();
        }

        public int getTotalEnergy() {
            int result = 0;
            for (Moon m : moons) {
                result = result + m.getEnergy();
            }
            return result;
        }
    }
}


