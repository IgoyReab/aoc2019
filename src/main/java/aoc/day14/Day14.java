package aoc.day14;

import aoc.Day;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

public class Day14 implements Day {

    @Data
    public class Element {
        String name;
        Integer resultingAmount;

        List<Formula> formulas;

        public Element(int a, String e) {
            this.resultingAmount = a;
            this.name = e;
            formulas = new ArrayList<>();
        }

        public void addFormula(int a, String e) {
            formulas.add(new Formula(a, e));
        }
    }

    @Data
    public class Formula {
        String name;
        Integer amount;

        public  Formula (int a, String e) {
            this.name = e;
            this.amount = a;
        }
    }
    List<Element> elements = new ArrayList<>();

    public Element findElement(String n) {
        for (Element f: elements) {
            if (f.getName().equals(n)) return f;
        }
        return null;
    }

    @Override
    public String part1(List<String> input) {
        for(String s : input) {
            String[] s1 = s.split(" => ");
            String[] s2 = s1[1].split(" ");
            elements.add(new Element(Integer.valueOf(s2[0]),s2[1]));
        }

        for(String s : input) {
            String[] s1 = s.split(" => ");
            String[] s2 = s1[1].split(" ");
            String[] s3 = s1[0].split(", ");
            for (String s4 : s3) {
                String[] s5 = s4.split(" ");
                Element foundElement = findElement(s2[1]);
                foundElement.addFormula(Integer.valueOf(s5[0]), s5[1]);
            }
        }

        Element fuel = findElement("FUEL");

        return input.isEmpty() ? "" : "";
    }

    @Override
    public String part2(List<String> input) {
        return input.isEmpty() ? "" : "";
    }
}
