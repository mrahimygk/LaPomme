package ir.mrgkrahimy.lapomme.helpers;

/**
 * Created by Mojtaba on 9/26/2016.
 */
public class Consts {

    public static final int W = 320, H = 480;
    public static final float FRUIT_HEIGHT = W / 16;
    public static final float BASKET_HEIGHT = FRUIT_HEIGHT * 2;
    public static final float SPLASH_SCREEN_TIME = 1.5f;

    public static final int FRUIT_LINE_COUNT = 3;
    public static final float GROUND_SIZE = FRUIT_HEIGHT / 3;

    public static final int FRUIT_COUNT = 14;

    public static final float WORLD_TO_BOX = 0.01f;
    public static final float BOX_TO_WORLD = 100f;

    public static final float GRAVITY = -8.0f;

    public static final int TOP_GAP = (int) (FRUIT_HEIGHT * 1.5f);
    public static final int FRUIT_H_MARGIN = (int) (FRUIT_HEIGHT / 1.5f);
    public static final float FRUIT_LEFT_PADDING = (FRUIT_HEIGHT * 2) - (FRUIT_HEIGHT / 2);

    public static final float animationDuration = 0.8f;
    public static final float GAME_OVER_SCENE_TIME = 3.f;

    public static final short collide_help = -1;

    public static final float step = 1.0f / 60.0f;

    public static final int NUMBER_OF_ROWS = 3;

}