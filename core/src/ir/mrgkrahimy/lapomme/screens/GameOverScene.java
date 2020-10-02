package ir.mrgkrahimy.lapomme.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;

import ir.mrgkrahimy.lapomme.base.TheGame;
import ir.mrgkrahimy.lapomme.helpers.Consts;

/**
 * Created by Mojtaba on 9/26/2016.
 */
public class GameOverScene extends InputAdapter implements Screen {

    final TheGame game;

    float time=0;
    private float w, h;

    Texture bg;

    public GameOverScene(final TheGame game, Pixmap lastScreen, float w, float h){
        this.game=game;
        bg = new Texture(lastScreen);

        this.w=w;
        this.h=h;

    }
    @Override
    public void show() {

        time=0;

    }

    @Override
    public void render(float delta) {

        time+=delta;
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        //Gdx.gl.glClearColor(0, 0, 0, 1);

        game.batch.begin();
        //game.batch.draw(bg, -Consts.W / 2, -Consts.H / 2, bg.getWidth(), bg.getHeight(), 0, 0, bg.getWidth(), bg.getHeight(), false, true);
        game.batch.draw(bg, -w / 2, -h / 2, w , h , 0, 0, bg.getWidth(), bg.getHeight(), false, true);
        game.batch.end();

        if(time>Consts.GAME_OVER_SCENE_TIME /1.5f) {
            bg.dispose();
            game.setScreen(new GameEnd(game, w, h));
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

        bg.dispose();
    }

    public boolean touchUp (int screenX, int screenY, int pointer, int button) {
        bg.dispose();
        game.setScreen(new GameEnd(game, w, h));
        return true;
    }

}
