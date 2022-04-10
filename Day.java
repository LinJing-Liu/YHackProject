public class Day {
    private int month;
    private int dayNum;
    private int dayWeek;
    private int score;

    public Day(int dw, int dn, int m, int s) {
        month = m;
        dayNum = dn;
        dayWeek = dw;
        score = s;
    }

    public int getScore() {
        return score;
    }
}