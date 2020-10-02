package ir.mrgkrahimy.lapomme.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

/**
 * Created by Mojtaba on 10/10/2016.
 */
public class Prefs {
    public static Preferences preferences;

    public static void  init(){
        preferences = Gdx.app.getPreferences("ir.mrgkrahimy.lapomme");
        preferences.flush();
    }

    public static int getMaxScore(){
        return preferences.getInteger("highscore",0);
    }

    public static void setMaxScore(int maxScore){
        preferences.putInteger("highscore", maxScore);
        preferences.flush();
    }

    public static void setSoundOn(){
        preferences.putBoolean("sound", true);
        preferences.flush();
    }

    public static void setSoundOff(){
        preferences.putBoolean("sound", false);
        preferences.flush();
    }

    public static boolean isSoundOn(){
        return preferences.getBoolean("sound");
    }

    public static void setCurrentScore(int currentScore){
        preferences.putInteger("currentScore", currentScore);
        preferences.flush();
    }

    public static int getCurrentScore(){
        return preferences.getInteger("currentScore", 0);
    }

    public static float getEasiness(){
        return preferences.getFloat("easiness", HardnessManager.easiness);
    }

    public static void setEasiness(int easyness){
        HardnessManager.easiness = easyness;
        preferences.putFloat("easiness", HardnessManager.easiness);
        preferences.flush();

    }

    public static void addHardness(){
        HardnessManager.addHardness();
        preferences.putFloat("easiness", HardnessManager.easiness);
        preferences.putFloat("spawnTime", HardnessManager.spawnTime);
        preferences.putFloat("gravityScale",HardnessManager.gravityScale);
        preferences.flush();

    }

    public static float getSpawnTime(){
        return preferences.getFloat("spawnTime", HardnessManager.spawnTime);
    }

    public static void setSpawnTime(float spawnTime) {
        HardnessManager.spawnTime = spawnTime;
        preferences.putFloat("spawnTime", spawnTime);
        preferences.flush();
    }

    public static void setCurrentHardness(HardnessManager.HARDNESS hardness) {
        switch (hardness) {
            case easy:
                HardnessManager.easiness = HardnessManager.EASY;
                setSpawnTime(HardnessManager.SPAWN_RATE_EASY);
                setGravityScale(HardnessManager.GRAVITY_EASY);
                HardnessManager.setHardness(HardnessManager.HARDNESS.easy);
                preferences.putInteger("hardness", HardnessManager.E);
                preferences.flush();
                break;

            case medium:
                HardnessManager.easiness = HardnessManager.MEDIUM;
                setSpawnTime(HardnessManager.SPAWN_RATE_MEDIUM);
                setGravityScale(HardnessManager.GRAVITY_MEDIUM);
                HardnessManager.setHardness(HardnessManager.HARDNESS.medium);
                preferences.putInteger("hardness", HardnessManager.M);
                preferences.flush();
                break;

            case hard:
                HardnessManager.easiness = HardnessManager.HARD;
                setSpawnTime(HardnessManager.SPAWN_RATE_HARD);
                setGravityScale(HardnessManager.GRAVITY_HARD);
                HardnessManager.setHardness(HardnessManager.HARDNESS.hard);
                preferences.putInteger("hardness", HardnessManager.H);
                preferences.flush();
                break;
        }
    }

    public static void setGravityScale(float gravityScale){
        if(gravityScale<1.f){
            HardnessManager.gravityScale = gravityScale;
            preferences.putFloat("gravityScale", gravityScale);
            preferences.flush();
        }
    }

    public static float getGravityScale(){
        return preferences.getFloat("gravityScale", HardnessManager.gravityScale);
    }

    public static int getNumberOfRows(){
        return preferences.getInteger("NUMBER_OF_ROWS", Consts.NUMBER_OF_ROWS);
    }

    public static void setNumberOfRows(int numberOfRows){
        preferences.putInteger("NUMBER_OF_ROWS", numberOfRows);
    }

    public static HardnessManager.HARDNESS getHardness(){
        int hardness = preferences.getInteger("hardness", HardnessManager.E);
        switch (hardness){
            case HardnessManager.E:
                return HardnessManager.HARDNESS.easy;
            case HardnessManager.M:
                return HardnessManager.HARDNESS.medium;
            case HardnessManager.H:
                return HardnessManager.HARDNESS.hard;
            default:
                return HardnessManager.HARDNESS.easy;
        }
    }
}
