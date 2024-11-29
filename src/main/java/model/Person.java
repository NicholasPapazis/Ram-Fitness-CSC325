package model;

public class Person {

    // user info

    private String email;
    private String username;
    private String password;

    // goal targets
    private int exerciseTarget;
    private int calorieTarget;
    private int weightTarget;
    private int sleepTarget;

    // activity during the day
    private int dailyExerciseTime;
    private int dailyCalorieIntake;
    private int dailyWeightLifted;
    private int sleepDuration;

    // streak counter
    private int goalStreak;

    //default constructor
    public Person() {

    }

    // Constructor
    public Person(String email, String username, String password, int exerciseTarget,
                  int calorieTarget, int weightTarget, int sleepTarget) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.exerciseTarget = exerciseTarget;
        this.calorieTarget = calorieTarget;
        this.weightTarget = weightTarget;
        this.sleepTarget = sleepTarget;
        this.dailyExerciseTime = 0;
        this.dailyCalorieIntake = 0;
        this.dailyWeightLifted = 0;
        this.sleepDuration = 0;
        this.goalStreak = 0;
    }

    // getters and setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getExerciseTarget() {
        return exerciseTarget;
    }

    public void setExerciseTarget(int exerciseTarget) {
        this.exerciseTarget = exerciseTarget;
    }

    public int getCalorieTarget() {
        return calorieTarget;
    }

    public void setCalorieTarget(int calorieTarget) {
        this.calorieTarget = calorieTarget;
    }

    public int getWeightTarget() {
        return weightTarget;
    }

    public void setWeightTarget(int weightTarget) {
        this.weightTarget = weightTarget;
    }

    public int getSleepTarget() {
        return sleepTarget;
    }

    public void setSleepTarget(int sleepTarget) {
        this.sleepTarget = sleepTarget;
    }

    public int getDailyExerciseTime() {
        return dailyExerciseTime;
    }

    public void setDailyExerciseTime(int dailyExerciseTime) {
        this.dailyExerciseTime = dailyExerciseTime;
    }

    public int getDailyCalorieIntake() {
        return dailyCalorieIntake;
    }

    public void setDailyCalorieIntake(int dailyCalorieIntake) {
        this.dailyCalorieIntake = dailyCalorieIntake;
    }

    public int getDailyWeightLifted() {
        return dailyWeightLifted;
    }

    public void setDailyWeightLifted(int dailyWeightLifted) {
        this.dailyWeightLifted = dailyWeightLifted;
    }

    public int getSleepDuration() {
        return sleepDuration;
    }

    public void setSleepDuration(int sleepDuration) {
        this.sleepDuration = sleepDuration;
    }

    public int getGoalStreak() {
        return goalStreak;
    }

    public void setGoalStreak(int goalStreak) {
        this.goalStreak = goalStreak;
    }

    // checks if all goals are met to update the streak
    public void updateGoalStreak() {
        if (dailyExerciseTime >= exerciseTarget &&
                dailyCalorieIntake <= calorieTarget &&
                dailyWeightLifted >= weightTarget &&
                sleepDuration >= sleepTarget) {
            goalStreak++;
        } else {
            goalStreak = 0;
        }
    }

}