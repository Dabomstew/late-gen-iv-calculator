package be.lycoops.vincent.iv.model;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class EffortValueProvider {
    public static Map<Stat, Integer> getEffortValues(Game game, int level, int baseLevel) {




        Map<Stat, Integer> effortValues = importEffortValues(level, baseLevel);

        if (effortValues != null) {
            return effortValues;
        }
        effortValues = new HashMap<>();

        int hp = 0, atk = 0, def = 0, spd = 0, spAtk = 0, spDef = 0;
        effortValues.put(Stat.HP, hp);
        effortValues.put(Stat.ATK, atk);
        effortValues.put(Stat.DEF, def);
        effortValues.put(Stat.SPD, spd);
        effortValues.put(Stat.SP_ATK, spAtk);
        effortValues.put(Stat.SP_DEF, spDef);
        return effortValues;
    }

    private static final Pattern pattern = Pattern.compile("^(\\d+)\\s+(\\d+)\\s+(\\d+)\\s+(\\d+)\\s+(\\d+)\\s+(\\d+)\\s+(\\d+)");

    private static Map<Stat, Integer> importEffortValues(int level, int baseLevel) {
        File file = new File("effort-values-"+baseLevel+".txt");
        if (!file.exists() || !file.canRead()) {
            return null;
        }
        final Map<Stat, Integer> effortValues = new HashMap<>();

        try (Stream<String> stream = Files.lines(Paths.get("effort-values-"+baseLevel+".txt"))) {
            stream.forEach(line -> {
                Matcher m = pattern.matcher(line);
                if (m.find() && Integer.parseInt(m.group(1)) == level){
                    effortValues.put(Stat.HP, Integer.parseInt(m.group(2)));
                    effortValues.put(Stat.ATK, Integer.parseInt(m.group(3)));
                    effortValues.put(Stat.DEF, Integer.parseInt(m.group(4)));
                    effortValues.put(Stat.SP_ATK, Integer.parseInt(m.group(5)));
                    effortValues.put(Stat.SP_DEF, Integer.parseInt(m.group(6)));
                    effortValues.put(Stat.SPD, Integer.parseInt(m.group(7)));
                }
            });
            if (effortValues.size() == 0) {
                System.out.println("Default values");
                return null;
            }
            System.out.println("Custom values: ");
            System.out.println(effortValues.get(Stat.HP));
            System.out.println(effortValues.get(Stat.ATK));
            System.out.println(effortValues.get(Stat.DEF));
            System.out.println(effortValues.get(Stat.SPD));
            System.out.println(effortValues.get(Stat.SP_ATK));
            System.out.println(effortValues.get(Stat.SP_DEF));
            return effortValues;
        } catch (IOException e) {
            return null;
        }
    }

    public static Map<Stat, Integer> getPikipekEffortValues(Game game, int level) {
        Map<Stat, Integer> effortValues = new HashMap<>();
        int hp = 0, atk = 0, def = 0, spd = 0, spAtk = 0, spDef = 0;
        if (game.equals(Game.MOON)) {
            switch (level) {
                case 2:
                    hp = 0; atk = 0; def = 0; spd = 0; spAtk = 0; spDef = 0; break;
                case 3:
                    hp = 0; atk = 0; def = 0; spd = 0; spAtk = 0; spDef = 0; break;
                case 4:
                    hp = 0; atk = 0; def = 0; spd = 0; spAtk = 0; spDef = 0; break;
                case 5:
                    hp = 0; atk = 0; def = 0; spd = 0; spAtk = 0; spDef = 0; break;
                case 6:
                    hp = 0; atk = 0; def = 0; spd = 0; spAtk = 0; spDef = 0; break;
                default:
                    hp = 0; atk = 0; def = 0; spd = 0; spAtk = 0; spDef = 0; break;
            }
        } else {
            throw new RuntimeException();
        }
        effortValues.put(Stat.HP, hp);
        effortValues.put(Stat.ATK, atk);
        effortValues.put(Stat.DEF, def);
        effortValues.put(Stat.SPD, spd);
        effortValues.put(Stat.SP_ATK, spAtk);
        effortValues.put(Stat.SP_DEF, spDef);
        return effortValues;
    }
}
