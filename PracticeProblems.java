import java.util.Arrays;

public class PracticeProblems {

    // Problem 1: Hackathon Score Curve Booster
    static void curveScores(int[] scores, int bonus) {
        for (int i = 0; i < scores.length; i++) {
            scores[i] += bonus;
        }
    }

    // Problem 2: Duplicate Team Name Finder
    static String findDuplicateTeam(String[] teamNames) {
        for (int i = 0; i < teamNames.length; i++) {
            for (int j = i + 1; j < teamNames.length; j++) {
                if (teamNames[i].equals(teamNames[j])) {
                    return "Duplicate Found: " + teamNames[i];
                }
            }
        }
        return "No Duplicates Found";
    }

    // Problem 3: Top-3 Podium Finder
    static int[] findTopThreeScores(int[] scores) {
        int first = Integer.MIN_VALUE;
        int second = Integer.MIN_VALUE;
        int third = Integer.MIN_VALUE;

        for (int score : scores) {
            if (score >= first) {
                third = second;
                second = first;
                first = score;
            } else if (score >= second) {
                third = second;
                second = score;
            } else if (score > third) {
                third = score;
            }
        }
        return new int[]{first, second, third};
    }

    // Problem 4: Hackathon Seating Grid Optimizer
    private static double rowAverage(int[] row) {
        if (row.length == 0) {
            return 0.0;
        }
        int sum = 0;
        for (int value : row) {
            sum += value;
        }
        return (double) sum / row.length;
    }

    static String classifyRows(int[][] seatingScores, int threshold) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < seatingScores.length; i++) {
            double average = rowAverage(seatingScores[i]);
            if (i > 0) result.append(" | ");
            result.append("Row ").append(i).append(": ")
                  .append(average < threshold ? "Quiet Zone" : "Buzzing Zone");
        }
        return result.toString();
    }

    // Problem 5: Placement Drive Shortlisting & Ranking Engine
    static class Candidate implements Comparable<Candidate> {
        private final String name;
        private final double cgpa;
        private final int codingScore;

        public Candidate(String name, double cgpa, int codingScore) {
            this.name = name;
            this.cgpa = cgpa;
            this.codingScore = codingScore;
        }

        // Composite score chosen to match the assignment example:
        // CGPA * 10 + coding score * 0.5
        double compositeScore() {
            return cgpa * 10 + codingScore * 0.5;
        }

        @Override
        public int compareTo(Candidate other) {
            return Double.compare(other.compositeScore(), this.compositeScore());
        }

        @Override
        public String toString() {
            return name + " (" + String.format("%.1f", compositeScore()) + ")";
        }
    }

    static boolean isEligible(double cgpa) {
        return cgpa >= 7.5;
    }

    static boolean isEligible(double cgpa, int codingScore) {
        return cgpa >= 6.5 && codingScore >= 60;
    }

    static String shortlistAndRank(Candidate[] candidates) {
        Candidate[] eligible = new Candidate[candidates.length];
        int count = 0;

        for (Candidate candidate : candidates) {
            if (isEligible(candidate.cgpa) ||
                isEligible(candidate.cgpa, candidate.codingScore)) {
                eligible[count++] = candidate;
            }
        }

        eligible = Arrays.copyOf(eligible, count);
        Arrays.sort(eligible);

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < eligible.length; i++) {
            if (i > 0) result.append(" | ");
            result.append(i + 1).append(". ").append(eligible[i]);
        }
        return result.toString();
    }

    public static void main(String[] args) {
        int[] scores = {70, 85, 60};
        curveScores(scores, 10);
        System.out.println("1. " + Arrays.toString(scores));

        System.out.println("2. " + findDuplicateTeam(
                new String[]{"ByteForce", "CodeCrafters", "ByteForce"}));

        System.out.println("3. " + Arrays.toString(
                findTopThreeScores(new int[]{45, 82, 79, 90, 33, 90, 61})));

        int[][] seating = {{40, 50, 45}, {85, 90, 95}, {30, 20, 25}};
        System.out.println("4. " + classifyRows(seating, 60));

        Candidate[] candidates = {
            new Candidate("Aisha", 8.2, 40),
            new Candidate("Rohit", 6.8, 65),
            new Candidate("Meena", 6.0, 90),
            new Candidate("Karan", 7.5, 20)
        };
        System.out.println("5. " + shortlistAndRank(candidates));
    }
}
