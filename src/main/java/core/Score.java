package core;

public class Score {
    private int Score;
    private int MaxPossible;

    public Score(int max) {
        this.MaxPossible = max;
    }

    public int getScore() {
        return Score;
    }

    public boolean setScore(int score) {
        if(score<=MaxPossible && score>=0){
            this.Score = score;
            return true;
        }
        System.out.println("score exceeded max score");
        return false;
    }

    public int getMaxPossible() {
        return MaxPossible;
    }

    public void setMaxPossible(int maxPossible) {
        MaxPossible = maxPossible;
    }

    public boolean isValid(){
        return Score <=MaxPossible;
    }
}
