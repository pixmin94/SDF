package day8;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class App 
{
    public static void main( String[] args ) throws IOException
    {
        Reader r = new FileReader(args[0]);
        BufferedReader br = new BufferedReader(r);

        Map<String, PlaystoreStats> stats = new HashMap<>();

        br.lines()
            .skip(1)
            .filter(x -> !x.contains("NaN"))
            .map(line -> {
                String[] fields = line.split(",");
                return new PlaystoreEntry(fields[0].trim(), fields[1], Float.parseFloat(fields[2]));
            })
            .collect(Collectors.groupingBy(PlaystoreEntry::category))
            .forEach((category, entry) -> {
                PlaystoreStats s = new PlaystoreStats(category);
                for(PlaystoreEntry e:entry) {
                    s.setStats(e);
                }
                stats.put(category, s);
            })
        ;

        for(String c: stats.keySet()) {
            PlaystoreStats s = stats.get(c);
            System.out.print(c+"\n"+s.getLowestRatingApp()+": "+s.getLowestRating()+" Average rating: "+s.avgRating()+"\n");
            
        }

        // System.out.print();

    }
}
