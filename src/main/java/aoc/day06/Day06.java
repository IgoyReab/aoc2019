package aoc.day06;

import aoc.Day;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

public class Day06 implements Day {

    @Data
    class SpaceObject {
        private String id;
        private SpaceObject orbits;

        SpaceObject(String id) {
            this.id = id;
        }
    }

    private SpaceObject getSpaceObject(List<SpaceObject> objects, String id){
        for(SpaceObject o : objects) {
            if (o.getId().equals(id)) return o;
        }
        return null;
    }

    private SpaceObject findOrbitObject(SpaceObject object, String id) {
        SpaceObject searchObject = object;
        while ((!searchObject.getId().equals(id)) && (searchObject.getOrbits() != null)) {
            searchObject = searchObject.getOrbits();
        }
        return searchObject;
    }


    private void addSpaceObject(List<SpaceObject> objects, String id, String orbits) {

        SpaceObject spaceObject = getSpaceObject(objects, id);
        if (spaceObject == null) {
            spaceObject = new SpaceObject(id);
            objects.add(spaceObject);
        }

        SpaceObject orbitsObject = getSpaceObject(objects, orbits);
        if (orbitsObject == null) {
           orbitsObject = new SpaceObject(orbits);
           if (!orbits.equals("COM")) objects.add(orbitsObject);
        }
        spaceObject.orbits =  orbitsObject;
    }

    @Override
    public String part1(List<String> input) {
        List<SpaceObject> spaceObjects = new ArrayList<>();

        for (String s : input) {
            String[] xy = s.split("\\)");
            addSpaceObject(spaceObjects, xy[1], xy[0]);
        }

        int sum = 0;
        for (SpaceObject so : spaceObjects) {
            if (so.orbits != null) {
                while (!so.orbits.getId().equals("COM")) {
                    sum++;
                    so = so.orbits;
                }
            }
            sum++;
        }

        return input.isEmpty() ? "" : String.valueOf(sum);
    }

    @Override
    public String part2(List<String> input) {
        List<SpaceObject> spaceObjects = new ArrayList<>();

        for (String s : input) {
            String[] xy = s.split("\\)");
            addSpaceObject(spaceObjects, xy[1], xy[0]);
        }

        SpaceObject mySpaceObject = getSpaceObject(spaceObjects, "YOU");
        assert mySpaceObject != null;
        String myOrbitID = mySpaceObject.orbits.getId();
        SpaceObject SantasSpaceObject = getSpaceObject(spaceObjects, "SAN");
        assert SantasSpaceObject != null;
        String SantasOrbitId = SantasSpaceObject.orbits.getId();


        boolean inSantasObject = false;
        int count = 0;
        while (!myOrbitID.equals(SantasOrbitId)) {

            if (!inSantasObject) {
                SpaceObject reachedSpaceObject = findOrbitObject(SantasSpaceObject, myOrbitID);
                if (!reachedSpaceObject.getId().equals("COM")) {
                    mySpaceObject = reachedSpaceObject;
                    inSantasObject = true;
                } else {
                    mySpaceObject = mySpaceObject.getOrbits();
                    myOrbitID = mySpaceObject.orbits.getId();
                    count++;
                }
            } else {
                SantasSpaceObject = SantasSpaceObject.getOrbits();
                SantasOrbitId = SantasSpaceObject.orbits.getId();
                count++;
            }
        }
        return input.isEmpty() ? "" : String.valueOf(count);
    }


}
