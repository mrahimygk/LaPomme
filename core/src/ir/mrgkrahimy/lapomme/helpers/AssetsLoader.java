package ir.mrgkrahimy.lapomme.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

/**
 * Created by Mojtaba on 9/26/2016.
 */
public class AssetsLoader {

    private static Texture texture;
    public static Texture logo, background, about;

    public static TextureRegion
            basket, ground,
            apple,
            orange,
            score, life,
            soundOff, soundOn,
            easyButtonOn,
            mediumButtonOn,
            hardButtonOn,
            beginButton, aboutButton, menuButton, settingsButton;

    public static Animation
            appleCorrectAnimation, appleWrongAnimation,
            orangeCorrectAnimation, orangeWrongAnimation;

    public static Sound correctHitSound, wrongHitSound, fallSound;
    public static Music levelMusic;

    public static Preferences preferences;

    public static float appleAspectRatio, basketAspectRatio, groundAspectRatio;

    public static void load() {

        texture = new Texture(Gdx.files.internal("image/texture.png"));
        texture.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        background = new Texture(Gdx.files.internal("image/bg_a.jpg"));
        background.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        logo = new Texture(Gdx.files.internal("image/logo.png"));
        logo.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        about = new Texture(Gdx.files.internal("image/about.jpg"));
        logo.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        // game texture & sprites
        apple = new TextureRegion(texture, 0, 1, 120, 157);
        orange = new TextureRegion(texture, 360, 0, 487 - 360, 158);
        ground = new TextureRegion(texture, 224, 185, 626 - 224, 192 - 185);
        basket = new TextureRegion(texture, 0, 158, 223, 285 - 158);

        score = new TextureRegion(texture, 277, 240, 322 - 277, 284 - 240);
        life = new TextureRegion(texture, 225, 240, 276 - 225, 284 - 240);
        soundOn = new TextureRegion(texture, 325, 240, 380 - 325, 44);
        soundOff = new TextureRegion(texture, 383, 240, 422 - 383, 44);
        int y = 193; int width = 82;
        easyButtonOn = new TextureRegion(texture,423,y,width,width);
        mediumButtonOn = new TextureRegion(texture,506,y,width,width);
        hardButtonOn = new TextureRegion(texture,589,y,width,width);

        //  textureRegion instantiate
        Array<TextureRegion> appleCorrectTextureRegion = new Array<TextureRegion>(4);
        Array<TextureRegion> appleWrongTextureRegion = new Array<TextureRegion>(4);
        Array<TextureRegion> orangeCorrectTextureRegion = new Array<TextureRegion>(4);
        Array<TextureRegion> orangeWrongTextureRegion = new Array<TextureRegion>(4);

        // : textureRegion array
        appleCorrectTextureRegion.add(new TextureRegion(texture, 120, 0, 239 - 120, 157));
        appleCorrectTextureRegion.add(apple);
        appleCorrectTextureRegion.add(new TextureRegion(texture, 120, 0, 239 - 120, 157));
        appleCorrectTextureRegion.add(apple);

        appleWrongTextureRegion.add(new TextureRegion(texture, 240, 0, 359 - 240, 157));
        appleWrongTextureRegion.add(apple);
        appleWrongTextureRegion.add(new TextureRegion(texture, 240, 0, 359 - 240, 157));
        appleWrongTextureRegion.add(apple);

        orangeCorrectTextureRegion.add(new TextureRegion(texture, 488, 0, 615 - 488, 158));
        orangeCorrectTextureRegion.add(orange);
        orangeCorrectTextureRegion.add(new TextureRegion(texture, 488, 0, 615 - 488, 158));
        orangeCorrectTextureRegion.add(orange);

        orangeWrongTextureRegion.add(new TextureRegion(texture, 616, 0, 743 - 616, 158));
        orangeWrongTextureRegion.add(orange);
        orangeWrongTextureRegion.add(new TextureRegion(texture, 616, 0, 743 - 616, 158));
        orangeWrongTextureRegion.add(orange);

        // : aspect ratios
        appleAspectRatio = getTextureRatio(apple);
        groundAspectRatio = getTextureRatio(ground);

        basketAspectRatio = getTextureRatio(basket);

        //: animations
        appleCorrectAnimation = new Animation(0.2f, appleCorrectTextureRegion);
        appleWrongAnimation = new Animation(0.2f, appleWrongTextureRegion);
        orangeCorrectAnimation = new Animation(0.2f, orangeCorrectTextureRegion);
        orangeWrongAnimation = new Animation(0.2f, orangeWrongTextureRegion);

        //: sounds
        correctHitSound = Gdx.audio.newSound(Gdx.files.internal("sound/correct.ogg"));
        wrongHitSound = Gdx.audio.newSound(Gdx.files.internal("sound/wrong.ogg"));
        levelMusic = Gdx.audio.newMusic(Gdx.files.internal("sound/level.ogg"));
        levelMusic.setLooping(true);

        //: buttons
        beginButton = new TextureRegion(texture, 0, 287, 179, 179);

        aboutButton = new TextureRegion(texture, 180, 287, 179, 179);

        menuButton = new TextureRegion(texture, 360, 286, 179, 179);

        settingsButton = new TextureRegion(texture, 540, 286, 179, 179);

    }

    private static float getTextureRatio(TextureRegion textureRegion) {
        float w = textureRegion.getRegionWidth();
        float h = textureRegion.getRegionHeight();

        if (w > h)
            return w / h;
        if (h > w)
            return h / w;

        return 1;
    }

    public static void dispose() {

        texture.dispose();
        logo.dispose();
        background.dispose();
        about.dispose();

        levelMusic.dispose();
        correctHitSound.dispose();
        wrongHitSound.dispose();
        fallSound.dispose();

    }
}