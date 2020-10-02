package ir.mrgkrahimy.lapomme.helpers;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by Mojtaba on 10/2/2016.
 */
public class AnimationHandler {

    private static Animation appleCorrect, appleWrong, orangeCorrect, orangeWrong;

    public static boolean drawAppleCorrect = false;
    public static boolean drawAppleWrong = false;
    public static boolean drawOrangeCorrect = false;
    public static boolean drawOrangeWrong = false;

    public static boolean drawOnLeftRect = false;
    public static boolean drawOnRightRect = false;

    private static float appleCorrectState = 0.f;
    private static float appleWrongState = 0.f;
    private static float orangeWrongState = 0.f;
    private static float orangeCorrectState = 0.f;

    public static float playedFramesAC = 0.0f;
    public static float playedFramesAW = 0.0f;
    public static float playedFramesOC = 0.0f;
    public static float playedFramesOW = 0.0f;

    public static float x, y, w, h;

    public enum ANIM {
        appleCorrect, appleWrong, orangeCorrect, orangeWrong
    }

    public enum RECT{
        left,right
    }

    public enum WHICH{
        APPLE_CORRECT, APPLE_WRONG, ORANGE_CORRECT, ORANGE_WRONG
    }

    public static void init() {
        appleCorrect = AssetsLoader.appleCorrectAnimation;
        appleCorrectState = 0.f;

        appleWrong = AssetsLoader.appleWrongAnimation;
        appleWrongState = 0.f;

        orangeWrong = AssetsLoader.orangeWrongAnimation;
        orangeWrongState = 0.f;

        orangeCorrect = AssetsLoader.orangeCorrectAnimation;
        orangeCorrectState = 0.f;
    }

    public static void draw(ANIM animation, SpriteBatch batch, float delta) {
        switch (animation) {
            case appleCorrect:
                appleCorrectState += delta;
                TextureRegion appleCorrectCurFrame = appleCorrect.getKeyFrame(appleCorrectState);
                Color color = batch.getColor();//get current Color, you can't modify directly
                float oldAlpha = color.a; //save its alpha
                //From here you can modify alpha however you want
                color.a = oldAlpha*appleCorrectState; //ex. scale = 0.5 will make alpha halved
                batch.setColor(color); //set it
                batch.draw(appleCorrectCurFrame, x, y, w, h);
                //Set it back to orginial alpha when you're done with your alpha manipulation
                color.a = oldAlpha;
                batch.setColor(color);
                forwardAnimationAC(delta);
                if (playedFramesAC > Consts.animationDuration) {
                    resetTimesAC();
                }
                break;

            case appleWrong:
                appleWrongState += delta;
                TextureRegion appleWrongCurrentFrame = appleWrong.getKeyFrame(appleWrongState);
                color = batch.getColor();
                oldAlpha = color.a; //save its alpha
                //From here you can modify alpha however you want
                color.a = oldAlpha*appleWrongState; //ex. scale = 0.5 will make alpha halved
                batch.setColor(color); //set it
                batch.draw(appleWrongCurrentFrame, x, y, w, h);
                color.a = oldAlpha;
                batch.setColor(color);
                forwardAnimationAW(delta);
                if (playedFramesAW > Consts.animationDuration) {
                    resetTimesAW();
                }
                break;

            case orangeCorrect:
                orangeCorrectState += delta;
                TextureRegion orangeCorrectCurrentFrame = orangeCorrect.getKeyFrame(orangeCorrectState);
                color = batch.getColor();//get current Color, you can't modify directly
                oldAlpha = color.a; //save its alpha
                //From here you can modify alpha however you want
                color.a = oldAlpha*orangeCorrectState; //ex. scale = 0.5 will make alpha halved
                batch.setColor(color); //set it
                batch.draw(orangeCorrectCurrentFrame, x, y, w, h);
                color.a = oldAlpha;
                batch.setColor(color);
                forwardAnimationOC(delta);
                if (playedFramesOC > Consts.animationDuration) {
                    resetTimesOC();
                }
                break;

            case orangeWrong:
                orangeWrongState += delta;
                TextureRegion orangeWrongCurrentFrame = orangeWrong.getKeyFrame(orangeWrongState);
                color = batch.getColor();//get current Color, you can't modify directly
                oldAlpha = color.a; //save its alpha
                //From here you can modify alpha however you want
                color.a = oldAlpha*orangeWrongState; //ex. scale = 0.5 will make alpha halved
                batch.setColor(color); //set it
                batch.draw(orangeWrongCurrentFrame, x, y, w, h);
                color.a = oldAlpha;
                batch.setColor(color);
                forwardAnimationOW(delta);
                if (playedFramesOW > Consts.animationDuration) {
                    resetTimesOW();
                }
                break;
        }
    }

    public static void setDrawSize(float w, float h) {

        AnimationHandler.w = w;
        AnimationHandler.h = h;

        resetTimesAC();
        resetTimesAW();
        resetTimesOC();
        resetTimesOW();
    }

    public static void resetAllTimes(){
        resetTimesAC();
        resetTimesAW();
        resetTimesOC();
        resetTimesOW();
    }
    private static void resetTimesAC() {
        drawAppleCorrect = false;
        playedFramesAC = 0.f;
        appleCorrectState = 0.f;
    }

    public static void resetTimesAW() {
        drawAppleWrong = false;
        playedFramesAW = 0.f;
        appleWrongState = 0.f;
    }

    public static void resetTimesOC() {
        drawOrangeCorrect = false;
        playedFramesOC = 0.f;
        orangeCorrectState = 0.f;
    }

    public static void resetTimesOW() {
        drawOrangeWrong = false;
        playedFramesOW = 0.f;
        orangeWrongState = 0.f;
    }

    private static void forwardAnimationAC(float delta) {
        playedFramesAC += delta;
    }

    private static void forwardAnimationAW(float delta) {
        playedFramesAW += delta;
    }

    private static void forwardAnimationOC(float delta) {
        playedFramesOC += delta;

    }

    private static void forwardAnimationOW(float delta) {
        playedFramesOW += delta;
    }

    public static void updatePos(float x, float y){
        AnimationHandler.x=x;
        AnimationHandler.y=y;

    }

    public static void setDrawPosition(RECT rect,Rectangle rectangle, WHICH which){

        updatePos(rectangle.x, rectangle.y);

        switch (rect){
            case left:
                drawOnRightRect=false;
                drawOnLeftRect = true;
                break;
            case right:
                drawOnLeftRect=false;
                drawOnRightRect = true;
                break;
        }

        switch (which){
            case APPLE_CORRECT:
                drawAppleCorrect=true;
                break;
            case APPLE_WRONG:
                drawAppleWrong=true;
                break;
            case ORANGE_CORRECT:
                drawOrangeCorrect=true;
                break;
            case ORANGE_WRONG:
                drawOrangeWrong=true;
                break;
        }
    }

    public static void setDrawPosition(float x, float y, WHICH which){

        updatePos(x, y);

        resetGathererRectangleCheck();

        switch (which){
            case APPLE_CORRECT:
                drawAppleCorrect=true;
                break;
            case APPLE_WRONG:
                drawAppleWrong=true;
                break;
            case ORANGE_CORRECT:
                drawOrangeCorrect=true;
                break;
            case ORANGE_WRONG:
                drawOrangeWrong=true;
                break;
        }
    }

    public static void resetGathererRectangleCheck(){
        drawOnLeftRect = false;
        drawOnRightRect = false;
    }
}
