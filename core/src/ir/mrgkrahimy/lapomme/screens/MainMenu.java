package ir.mrgkrahimy.lapomme.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.Iterator;

import ir.mrgkrahimy.lapomme.base.TheGame;
import ir.mrgkrahimy.lapomme.helpers.Consts;
import ir.mrgkrahimy.lapomme.helpers.AssetsLoader;
import ir.mrgkrahimy.lapomme.helpers.Prefs;
import ir.mrgkrahimy.lapomme.ui.SimpleButton;

/**
 * Created by Mojtaba on 9/26/2016.
 */
public class MainMenu extends InputAdapter implements Screen {

    public static final int DROP_TIME = 250000000;
    private final TheGame game;

    private float w,h;

    float radius=10.f;

    private SimpleButton beginButton;
    private Texture bg;
    private ShapeRenderer shapeRenderer;
    private Array<Rectangle> fruitDrops;
    private long lastDropTime;
    private int radiuSign=1;


    public MainMenu(final TheGame game, float w, float h) {
        this.game=game;

        this.w=w;
        this.h=h;

        bg = AssetsLoader.background;

    }

    @Override
    public void show() {

        TextureRegion beginButtonUp = AssetsLoader.beginButton;

        beginButton = new SimpleButton(-Consts.FRUIT_HEIGHT * 2, -h/3,
                Consts.FRUIT_HEIGHT * 4,
                Consts.FRUIT_HEIGHT * 4,
                beginButtonUp,
                beginButtonUp
        );

        shapeRenderer = new ShapeRenderer();


        Gdx.input.setInputProcessor(this);

        fruitDrops = new Array<Rectangle>();
        spawnRaindrop();
    }

    private void spawnRaindrop() {
        Rectangle fruitDrop = new Rectangle();
        fruitDrop.x = MathUtils.random(-w/2, w/2 - Consts.FRUIT_HEIGHT);
        fruitDrop.y = h/2;
        fruitDrop.width = 64;
        fruitDrop.height = 64;
        fruitDrops.add(fruitDrop);
        lastDropTime = TimeUtils.nanoTime();
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        Gdx.gl.glClearColor(0, 0, 0, 1);

        shapeRenderer.setProjectionMatrix(game.camera.combined);

        game.batch.begin();
        game.batch.draw(bg, -w / 2, -h / 2, w, h, 0, 0, bg.getWidth(), bg.getHeight(), false, false);
        game.batch.end();

        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(1, 1, 1, .2f);
        shapeRenderer.rect(-w / 2 + Consts.FRUIT_HEIGHT, -Consts.FRUIT_HEIGHT, w - Consts.FRUIT_HEIGHT * 2, h / 2);
        shapeRenderer.setColor(.9f, .9f, .9f, .1f);
        shapeRenderer.rect(-w / 2 + Consts.FRUIT_HEIGHT * 2, Consts.FRUIT_HEIGHT, w - Consts.FRUIT_HEIGHT * 4, h / 2 - Consts.FRUIT_HEIGHT * 3);
        shapeRenderer.line(-w / 2, -h * 2, 0, w / 2, -h * 2, 0);
        // high score border
        shapeRenderer.setColor(0, 1, 0, .5f);
        shapeRenderer.rect(
                -w / 2 + Consts.FRUIT_HEIGHT * 2.6f,
                Consts.FRUIT_HEIGHT * 1.1f,
                Consts.FRUIT_HEIGHT * 5.6f,
                Consts.FRUIT_HEIGHT * 1.1f);

        // tapping circle
        shapeRenderer.setColor(.7f, .9f, .8f, .5f);
        radius+= radiuSign*2;
        if(radius> w / 4 || radius< Consts.FRUIT_HEIGHT/2) radiuSign=  -1 * radiuSign;
        shapeRenderer.circle(-Consts.FRUIT_HEIGHT * 2+Consts.FRUIT_HEIGHT * 2, -h/3+Consts.FRUIT_HEIGHT * 2,
                radius,100);
        shapeRenderer.end();
        Gdx.gl.glDisable(GL20.GL_BLEND);

        game.batch.begin();
        for(Rectangle fruit: fruitDrops) {
            if (fruit.x % 2 >-1 && fruit.x % 2 <1) {
                game.batch.draw(AssetsLoader.apple, fruit.x, fruit.y,
                        Consts.FRUIT_HEIGHT, Consts.FRUIT_HEIGHT * AssetsLoader.appleAspectRatio);
            }
            else{
                game.batch.draw(AssetsLoader.orange, fruit.x, fruit.y,
                        Consts.FRUIT_HEIGHT, Consts.FRUIT_HEIGHT * AssetsLoader.appleAspectRatio);
            }

        }
        beginButton.draw(game.batch);

        game.font.draw(game.batch, "HighScore: " + Prefs.getMaxScore() + "", -w / 2 + Consts.FRUIT_HEIGHT * 3, Consts.FRUIT_HEIGHT * 2);
        game.batch.end();



        if(TimeUtils.nanoTime() - lastDropTime > DROP_TIME) spawnRaindrop();

        Iterator<Rectangle> iter = fruitDrops.iterator();
        while(iter.hasNext()) {
            Rectangle raindrop = iter.next();
            raindrop.y -= 100 * Gdx.graphics.getDeltaTime();
            if(raindrop.y /*+ Consts.FRUIT_HEIGHT*/ < -h/2) iter.remove();
        }



    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        bg.dispose();
        shapeRenderer.dispose();
    }

    public boolean touchUp (int screenX, int screenY, int pointer, int button) {
        Vector3 touchPos = new Vector3();
        touchPos.set(screenX, screenY, 0);
        game.camera.unproject(touchPos);

        if (beginButton.isClicked((int) touchPos.x, (int) touchPos.y)) {
            game.setScreen(new MainGame(game, w, h));
        }

        return true;
    }
}
