package aoc.day08;

import aoc.Day;
import aoc.helper.StringHelper;
import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static aoc.helper.LoopHelper.loop;
import static java.lang.Integer.MAX_VALUE;

public class Day08 implements Day {

    public Integer width = 25;
    public Integer tall = 6;

    @Data
    class Image {
        private List<Layer> layers;
        private Integer[][] finalImage;

        public Image() {
            layers = new ArrayList<>();
            this.finalImage = new Integer[tall][width];
            loop(finalImage, (y , x) -> finalImage[y][x] = 2);
        }

        public void printImage() {
            System.out.println("Image : \n");
            loop(finalImage, (y, x) -> {
                if (x==0) System.out.println();
                System.out.print((finalImage[y][x] == 0) ? " " : "□" );
            });
            System.out.println("\n\n");
        }

        public void makeImage(List<String> input){
            int countWidth = 0;
            int countTall = 0;
            int count = 0;
            int layerCount = 0;
            boolean newLayer = true;
            List<Integer> inputIntegers = StringHelper.convertStringToIntegerList(input.get(0));

            while (count < inputIntegers.size()) {
                if (newLayer) {
                    addLayer(new Layer());
                    newLayer = false;
                }

                getLayers().get(layerCount).rows[countTall][countWidth] = inputIntegers.get(count);
                if (finalImage[countTall][countWidth] == 2) {
                    finalImage[countTall][countWidth] = inputIntegers.get(count);
                }

                int numberOfZeroes = (inputIntegers.get(count) == 0) ?
                        getLayers().get(layerCount).getNumberOfZeroes() + 1 :
                        getLayers().get(layerCount).getNumberOfZeroes();
                int numberOfOnes = (inputIntegers.get(count) == 1) ?
                        getLayers().get(layerCount).getNumberOfOnes() + 1 :
                        getLayers().get(layerCount).getNumberOfOnes();
                int numberOfTwos = (inputIntegers.get(count) == 2) ?
                        getLayers().get(layerCount).getNumberOfTwos() + 1 :
                        getLayers().get(layerCount).getNumberOfTwos();

                getLayers().get(layerCount).setNumberOfZeroes(numberOfZeroes);
                getLayers().get(layerCount).setNumberOfOnes(numberOfOnes);
                getLayers().get(layerCount).setNumberOfTwos(numberOfTwos);

                if (countWidth == (width - 1)) {
                    countWidth = 0;
                    if (countTall == (tall - 1)) {
                        countTall = 0;
                        newLayer = true;
                        layerCount++;
                    } else {
                        countTall++;
                    }
                } else {
                    countWidth++;
                }

                count++;
            }
        }

        public void addLayer(Layer inputLayer) {
            layers.add(inputLayer);
        }

        public Layer getLayerWithMinimalZeroes() {
            Integer zeroes = MAX_VALUE;
            Layer result = null;

            for (Layer l : layers) {
                if (l.getNumberOfZeroes() < zeroes) {
                    zeroes = l.getNumberOfZeroes();
                    result = l;
                }
            }

            return result;
        }
    }

    @Data
    class Layer {
        private Integer[][] rows;
        private Integer numberOfZeroes;
        private Integer numberOfOnes;
        private Integer numberOfTwos;

        public Layer() {
            this.rows = new Integer[tall][width];
            this.numberOfZeroes = 0;
            this.numberOfOnes = 0;
            this.numberOfTwos = 0;
        }
    }

    @Override
    public String part1(List<String> input) {
        Image image = new Image();
        image.makeImage(input);
        return input.isEmpty() ? "" : String.valueOf(image.getLayerWithMinimalZeroes().getNumberOfOnes() * image.getLayerWithMinimalZeroes().getNumberOfTwos());
    }

    @Override
    public String part2(List<String> input) {

        Image image = new Image();
        image.makeImage(input);
        image.printImage();

        return input.isEmpty() ? "" : Arrays.deepToString(image.finalImage);
    }
}
