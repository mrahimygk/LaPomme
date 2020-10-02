package ir.mrgkrahimy.lapomme.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;

import ir.mrgkrahimy.lapomme.base.TheGame;
import ir.mrgkrahimy.lapomme.helpers.Consts;
import ir.mrgkrahimy.lapomme.helpers.AssetsLoader;

/**
 * Created by Mojtaba on 9/27/2016.
 */
public class SplashScreen extends InputAdapter implements Screen {

    final TheGame game;
    float w,h;
    float time=0.f;

    private Texture logo;

    public SplashScreen(final TheGame game, float w, float h){

        this.game=game;
        this.w=w;
        this.h=h;

        AssetsLoader.load();

        Gdx.input.setInputProcessor(this);

    }
    @Override
    public void show() {

        logo =AssetsLoader.logo;

        game.batch.setProjectionMatrix(game.camera.combined);
        time=0;
    }

    @Override
    public void render(float delta) {

        time += delta;

        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();
        game.batch.draw(logo, -Consts.W / 2, 0);
        game.batch.end();

        if (time > Consts.SPLASH_SCREEN_TIME) {
            logo.dispose();
            AssetsLoader.logo.dispose();
            game.setScreen(new MainGame(game, w, h));
        }

    }

    @Override
    public void resize(int width, int height) {

        game.viewport.update(width, height);
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
        game.setScreen(new MainGame(game, w, h));
        return true;
    }

}
