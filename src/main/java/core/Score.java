package core;

public class Score {
    private Double Score;
    private Double MaxPossible;

    public Score(double max) {
        this.MaxPossible = max;
    }

    public double getScore() {
        return Score;
    }

    public boolean setScore(double score) {
        if(score<=MaxPossible && score>=0){
            this.Score = score;
            return true;
        }
        System.out.println("score exceeded max score");
        return false;
    }

    public double getMaxPossible() {
        return MaxPossible;
    }

    public void setMaxPossible(double maxPossible) {
        MaxPossible = maxPossible;
    }

    public boolean isValid(){
        return Score <=MaxPossible;
    }
}
