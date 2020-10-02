package ir.mrgkrahimy.lapomme.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;

import ir.mrgkrahimy.lapomme.base.TheGame;
import ir.mrgkrahimy.lapomme.helpers.Consts;
import ir.mrgkrahimy.lapomme.helpers.AssetsLoader;
import ir.mrgkrahimy.lapomme.ui.SimpleButton;

/**
 * Created by Mojtaba on 10/3/2016.
 */
public class AboutMenu extends InputAdapter implements Screen {

    private final TheGame game;

    private Texture about;
    private SimpleButton menuButton;
    private float w,h;

    public AboutMenu(final TheGame game, float w, float h) {
        this.game=game;

        this.w=w;
        this.h=h;

    }

    @Override
    public void show() {

        about = AssetsLoader.about;

        TextureRegion menuButtonTexture = AssetsLoader.menuButton;

        menuButton = new SimpleButton(w/2-Consts.FRUIT_HEIGHT*4,-h/2,
                Consts.FRUIT_HEIGHT*4,
                Consts.FRUIT_HEIGHT*4,
                menuButtonTexture,
                menuButtonTexture
        );

        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        Gdx.gl.glClearColor(0, 0, 0, 1);

        game.batch.begin();
        game.batch.draw(about, -w / 2, -h / 2, w , h , 0, 0, about.getWidth(), about.getHeight(), false, false);
        menuButton.draw(game.batch);
        game.batch.end();


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

    }

    public boolean touchUp (int screenX, int screenY, int pointer, int button) {

        Vector3 touchPos = new Vector3();
        touchPos.set(screenX, screenY, 0);
        game.camera.unproject(touchPos);

        if (menuButton.isClicked((int) touchPos.x, (int) touchPos.y)) {
            game.setScreen(new MainMenu(game, w, h));
        }

        return true;
    }

}
