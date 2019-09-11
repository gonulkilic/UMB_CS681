package edu.umb.cs.cs681.hw17;

import java.nio.file.Path;
import java.util.concurrent.ConcurrentHashMap;

public class AccessCounter {

    private ConcurrentHashMap<Path, Integer> Map = new ConcurrentHashMap<Path, Integer>();


    public void increment(Path aFilepath) {
        Map.computeIfPresent(aFilepath, (Path k, Integer v)->
        { return ++v; });
        Map.computeIfAbsent(aFilepath, (Path k)->{
            return 1;
        });
    }

    public Integer getCount(Path aFilepath) {
        return Map.computeIfPresent(aFilepath, (Path k, Integer v)->
        {return v;});
    }

}
