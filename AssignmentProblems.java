import java.util.Arrays;

public class AssignmentProblems {

    // Problem 1: Fantasy Team Score Multiplier
    static void applyMultipliers(double[] playerScores, int captainIndex, int viceCaptainIndex) {
        playerScores[captainIndex] *= 2.0;
        playerScores[viceCaptainIndex] *= 1.5;
    }

    // Problem 2: Duplicate Player Pick Checker
    static String findDuplicatePick(String[] playerNames) {
        for (int i = 0; i < playerNames.length; i++) {
            for (int j = i + 1; j < playerNames.length; j++) {
                if (playerNames[i].equals(playerNames[j])) {
                    return "Duplicate Found: " + playerNames[i];
                }
            }
        }
        return "No Duplicates Found";
    }

    // Problem 3: Top Performer Tracker
    static String findMinMaxSpread(int[] scores) {
        int min = scores[0];
        int max = scores[0];

        for (int i = 1; i < scores.length; i++) {
            if (scores[i] < min) min = scores[i];
            if (scores[i] > max) max = scores[i];
        }
        return "Min: " + min + " | Max: " + max + " | Spread: " + (max - min);
    }

    // Problem 4: Match Day Grid Analyzer
    private static double rowAverage(int[] row) {
        if (row.length == 0) {
            return 0.0;
        }
        int sum = 0;
        for (int runs : row) {
            sum += runs;
        }
        return (double) sum / row.length;
    }

    static String classifyMatches(int[][] runsPerOver, int threshold) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < runsPerOver.length; i++) {
            double average = rowAverage(runsPerOver[i]);
            if (i > 0) result.append(" | ");
            result.append("Match ").append(i).append(": ")
                  .append(average >= threshold ? "Power Surge" : "Normal");
        }
        return result.toString();
    }

    // Problem 5: Fantasy League Auto-Draft Ranking Engine
    static class Player implements Comparable<Player> {
        private final String name;
        private final int matchesPlayed;
        private final double battingAverage;
        private final boolean injured;

        public Player(String name, int matchesPlayed, double battingAverage, boolean injured) {
            this.name = name;
            this.matchesPlayed = matchesPlayed;
            this.battingAverage = battingAverage;
            this.injured = injured;
        }

        @Override
        public int compareTo(Player other) {
            return Double.compare(other.battingAverage, this.battingAverage);
        }

        @Override
        public String toString() {
            return name;
        }
    }

    static boolean isDraftable(int matchesPlayed) {
        return matchesPlayed >= 10;
    }

    static boolean isDraftable(int matchesPlayed, boolean injured) {
        return matchesPlayed >= 5 && !injured;
    }

    static String draftAndRank(Player[] players) {
        Player[] draftable = new Player[players.length];
        int count = 0;

        for (Player player : players) {
            if (isDraftable(player.matchesPlayed) ||
                isDraftable(player.matchesPlayed, player.injured)) {
                draftable[count++] = player;
            }
        }

        draftable = Arrays.copyOf(draftable, count);
        Arrays.sort(draftable);

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < draftable.length; i++) {
            if (i > 0) result.append(" | ");
            result.append(i + 1).append(". ").append(draftable[i]);
        }
        return result.toString();
    }

    public static void main(String[] args) {
        double[] scores = {40, 55, 30, 62};
        applyMultipliers(scores, 1, 3);
        System.out.println("1. " + Arrays.toString(scores));

        System.out.println("2. " + findDuplicatePick(
                new String[]{"Kohli", "Bumrah", "Kohli", "Rohit"}));

        System.out.println("3. " + findMinMaxSpread(new int[]{45, 82, 79, 90, 33, 90, 61}));

        int[][] runs = {{4, 6, 8}, {10, 12, 14}, {2, 3, 1}};
        System.out.println("4. " + classifyMatches(runs, 8));

        Player[] players = {
            new Player("Virat", 15, 48.0, false),
            new Player("Rahul", 7, 55.0, false),
            new Player("Sameer", 3, 60.0, false),
            new Player("Dev", 12, 20.0, true)
        };
        System.out.println("5. " + draftAndRank(players));
    }
}
