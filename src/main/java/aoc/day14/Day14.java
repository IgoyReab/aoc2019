package aoc.day14;

import aoc.Day;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day14 implements Day {

    public class Element {
        private String name;
        private HashMap<Element, Integer> ingredients = new HashMap<>();
        private int amountGenerated;
        private long leftOver;

        public Element(String name) {
            this.name = name;
        }

        public void addIngredient(Element chem, int amount) {
            ingredients.put(chem, amount);
        }

        public void setAmountGenerated(int amountGenerated) {
            this.amountGenerated = amountGenerated;
        }

        public long calculateAmount(long needed) {
            if (this.name.equals("ORE")) return needed;

            long originalNeeded = needed;
            needed -= leftOver;
            leftOver = leftOver - originalNeeded < 0 ? 0 : leftOver - originalNeeded;

            if(needed <= 0) return 0;

            long times = 1;
            if (needed > amountGenerated) {
                times = needed / amountGenerated;
                if (needed % amountGenerated != 0) times++;
            }

            leftOver += times * amountGenerated - needed;

            long total = 0;

            for (Map.Entry<Element, Integer> ingredient : ingredients.entrySet()) {
                total += ingredient.getKey().calculateAmount(times * ingredient.getValue());
            }

            return total;
        }

        public String getName() {
            return name;
        }
    }

    private Element getOrCreateChem(String name) {
        Element chem;
        if (chems.containsKey(name)) {
            chem = chems.get(name);
        } else {
            chem = new Element(name);
            chems.put(chem.getName(), chem);
        }

        return chem;
    }

    private HashMap<String, Element> chems = new HashMap<>();

    int amountOre = 0;

    @Override
    public String part1(List<String> input) {

        for (String line : input) {
            String[] splitOffResult = line.split(" => ");
            String[] ingredients = splitOffResult[0].split(", ");
            String chemResult = splitOffResult[1];
            String resultName = chemResult.split(" ")[1];
            String resultAmount = chemResult.split(" ")[0];

            Element chem = getOrCreateChem(resultName);
            chem.setAmountGenerated(Integer.parseInt(resultAmount));

            for (String ingredient : ingredients) {
                String[] chemAndAmount = ingredient.split(" ");

                Element iChem = getOrCreateChem(chemAndAmount[1]);
                chem.addIngredient(iChem, Integer.parseInt(chemAndAmount[0]));
            }
        }

        long result = chems.get("FUEL").calculateAmount(1);
        System.out.println("Result = " + result);

        return input.isEmpty() ? "" : String.valueOf(result);
    }

    @Override
    public String part2(List<String> input) {
        for (String line : input) {
            String[] splitOffResult = line.split(" => ");
            String[] ingredients = splitOffResult[0].split(", ");
            String chemResult = splitOffResult[1];
            String resultName = chemResult.split(" ")[1];
            String resultAmount = chemResult.split(" ")[0];

            Element chem = getOrCreateChem(resultName);
            chem.setAmountGenerated(Integer.parseInt(resultAmount));

            for (String ingredient : ingredients) {
                String[] chemAndAmount = ingredient.split(" ");

                Element iChem = getOrCreateChem(chemAndAmount[1]);
                chem.addIngredient(iChem, Integer.parseInt(chemAndAmount[0]));
            }
        }

        long result = chems.get("FUEL").calculateAmount(1);
        long amount = 1000000000000L / result;
        long y=0;
        for (long x=1000000 ; x<1200000 ; x++) {
            result = chems.get("FUEL").calculateAmount(x);
            if (result>=1000000000000L) {
                amount = y;
                break;
            }
            y=x;
        }
        return input.isEmpty() ? "" : String.valueOf(amount);
    }


}
