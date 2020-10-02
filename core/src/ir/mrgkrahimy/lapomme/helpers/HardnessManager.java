package ir.mrgkrahimy.lapomme.helpers;

/**
 * Created by Mojtaba on 10/8/2016.
 */
public class HardnessManager {

    public final static float EASY = 20.0f, MEDIUM = 15.0f, HARD = 10.0f;
    public final static float SPAWN_RATE_EASY = 5.f,SPAWN_RATE_MEDIUM = 4.f,SPAWN_RATE_HARD = 3.f;
    public final static float GRAVITY_EASY = .65f, GRAVITY_MEDIUM =.75f, GRAVITY_HARD =.9f;

    public static final int E = 1, M=2, H=3;

    public static float spawnTime =SPAWN_RATE_EASY;
    public static float easiness = EASY;
    public static float gravityScale = GRAVITY_EASY;

    public enum HARDNESS {
        easy, medium, hard
    }

    public static boolean addHardness() {
        float newEasiness = easiness - 1;
        float newSpawnRate = spawnTime - 0.1f;
        float newGravityScale = gravityScale + 0.05f;

        if (newEasiness > 0 && newSpawnRate > 2.0f && newGravityScale < 1.f) {
            easiness = newEasiness;
            spawnTime = newSpawnRate;
            gravityScale = newGravityScale;

            return true;
        }

        return false;
    }

    public static void setHardness( HARDNESS hardness){
        switch (hardness){
            case easy:
                easiness=EASY;
                break;
            case medium:
                easiness=MEDIUM;
                break;
            case hard:
                easiness=HARD;
                break;
            default:
                easiness=EASY;
        }
    }


}

