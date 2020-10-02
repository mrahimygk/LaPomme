package ir.mrgkrahimy.lapomme.helpers;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

/**
 * Created by Mojtaba on 10/2/2016.
 */
public class SoundHandler {

    private static Sound correct, wrong;
    private static Music level;

    public enum SOUND{
        correct, wrong
    }

    public static void init(){
        correct = AssetsLoader.correctHitSound;
        wrong = AssetsLoader.wrongHitSound;

        level = AssetsLoader.levelMusic;
    }

    public static void play(SOUND sound){
        if(Prefs.isSoundOn()) {
            switch (sound) {
                case correct:
                    correct.play();
                    break;
                case wrong:
                    wrong.play();
            }
        }
    }

    public static void playLevelMusic(){
        if (!level.isPlaying() && Prefs.isSoundOn())
            level.play();
    }

    public static void stopLevelMusic(){
        if (level.isPlaying())
            level.stop();
    }

    public static void check(){
        if (!level.isPlaying() && Prefs.isSoundOn())
            level.play();
            level.setVolume(1.f);
        if(!Prefs.isSoundOn()) level.setVolume(0.f);
    }
}
