package ir.mrgkrahimy.lapomme.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;

import ir.mrgkrahimy.lapomme.base.TheGame;
import ir.mrgkrahimy.lapomme.helpers.Consts;
import ir.mrgkrahimy.lapomme.helpers.AssetsLoader;
import ir.mrgkrahimy.lapomme.helpers.Prefs;
import ir.mrgkrahimy.lapomme.helpers.SoundHandler;
import ir.mrgkrahimy.lapomme.ui.SimpleButton;

/**
 * Created by Mojtaba on 10/3/2016.
 */
public class GameEnd extends InputAdapter implements Screen {

    final TheGame game;
    private SimpleButton menuButton, aboutButton, settingsButton;

    private ShapeRenderer shapeRenderer;
    private Texture bg;

    private float w,h;

    public GameEnd(final TheGame game, float w, float h) {
        this.game = game;
        this.w = w;
        this.h = h;
        SoundHandler.stopLevelMusic();

    }

    @Override
    public void show() {

        SoundHandler.stopLevelMusic();

        TextureRegion aboutButtonTexture = AssetsLoader.aboutButton;
        TextureRegion menuButtonTexture = AssetsLoader.menuButton;
        TextureRegion settingsButtonTexture = AssetsLoader.settingsButton;

        bg = AssetsLoader.background;

        menuButton = new SimpleButton(-Consts.FRUIT_HEIGHT,-h/4,
                Consts.FRUIT_HEIGHT*4,
                Consts.FRUIT_HEIGHT*4,
                menuButtonTexture,
                menuButtonTexture
        );

        aboutButton = new SimpleButton(-Consts.FRUIT_HEIGHT*2, -h/2.5f,
                Consts.FRUIT_HEIGHT*3,
                Consts.FRUIT_HEIGHT*3,
                aboutButtonTexture,
                aboutButtonTexture
                );

        settingsButton = new SimpleButton(Consts.FRUIT_HEIGHT*1.25f, -3*(h/7.5f),
                Consts.FRUIT_HEIGHT*3.5f,
                Consts.FRUIT_HEIGHT*3.5f,
                settingsButtonTexture,
                settingsButtonTexture
                );

        shapeRenderer = new ShapeRenderer();

        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        Gdx.gl.glClearColor(0, 0, 0, 1);

        shapeRenderer.setProjectionMatrix(game.camera.combined);

        game.batch.begin();

        game.batch.draw(bg, -w / 2, -h / 2, w, h, 0, 0, bg.getWidth(), bg.getHeight(), false, false);
        menuButton.draw(game.batch);
        aboutButton.draw(game.batch);
        settingsButton.draw(game.batch);
        game.font.draw(game.batch, "Score: "+Prefs.getCurrentScore() + "", -w / 2 + Consts.FRUIT_HEIGHT*3, Consts.FRUIT_HEIGHT*2);

        game.batch.end();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(1, 1, 1, 1);
        shapeRenderer.rect(-w / 2 + Consts.FRUIT_HEIGHT, -Consts.FRUIT_HEIGHT, w - Consts.FRUIT_HEIGHT * 2, h / 2 );
        shapeRenderer.rect(-w / 2 + Consts.FRUIT_HEIGHT*2,Consts.FRUIT_HEIGHT , w - Consts.FRUIT_HEIGHT * 4, h / 2 - Consts.FRUIT_HEIGHT*3);
        shapeRenderer.end();
    }

    @Override
    public void resize(int width, int height) {

        game.viewport.update(width,height);
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

        if (menuButton.isClicked((int) touchPos.x, (int) touchPos.y)) {
            game.setScreen(new MainMenu(game, w, h));
        }
        if (aboutButton.isClicked((int) touchPos.x, (int) touchPos.y)) {
            game.setScreen(new AboutMenu(game, w, h));
        }

        if(settingsButton.isClicked((int) touchPos.x, (int) touchPos.y)){
            game.setScreen(new SettingsScreen(game,w,h));
        }
        return true;
    }

}
