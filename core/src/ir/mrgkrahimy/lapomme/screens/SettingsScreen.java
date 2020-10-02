package ir.mrgkrahimy.lapomme.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;

import ir.mrgkrahimy.lapomme.base.TheGame;
import ir.mrgkrahimy.lapomme.helpers.AssetsLoader;
import ir.mrgkrahimy.lapomme.helpers.Consts;
import ir.mrgkrahimy.lapomme.helpers.HardnessManager;
import ir.mrgkrahimy.lapomme.helpers.Prefs;
import ir.mrgkrahimy.lapomme.helpers.SoundHandler;
import ir.mrgkrahimy.lapomme.ui.HardnessButton;
import ir.mrgkrahimy.lapomme.ui.SimpleButton;

/**
 * Created by Mojtaba on 10/16/2016.
 */
public class SettingsScreen extends InputAdapter implements Screen {
    private Texture bg;
    final TheGame game;
    float w,h;

    ShapeRenderer shapeRenderer = new ShapeRenderer();

    private SimpleButton menuButton;
    private HardnessButton hardButton, mediumButton, easyButton;

    public SettingsScreen(final TheGame game, float w, float h){
        this.game=game;

        this.w=w;
        this.h=h;

    }
    @Override
    public void show() {

        SoundHandler.stopLevelMusic();

        TextureRegion menuButtonTexture = AssetsLoader.menuButton;

        TextureRegion easyButtonTexture = AssetsLoader.easyButtonOn;
        TextureRegion mediumButtonTexture = AssetsLoader.mediumButtonOn;
        TextureRegion hardButtonTexture = AssetsLoader.hardButtonOn;

        bg = AssetsLoader.background;

        menuButton = new SimpleButton(-Consts.FRUIT_HEIGHT * 2, -h / 4,
                Consts.FRUIT_HEIGHT * 4,
                Consts.FRUIT_HEIGHT * 4,
                menuButtonTexture,
                menuButtonTexture
        );

        float HardnessButtonSize = Consts.FRUIT_HEIGHT * 3;

        easyButton = new HardnessButton(-HardnessButtonSize - HardnessButtonSize / 2, h / 5,
                HardnessButtonSize,
                HardnessButtonSize,
                easyButtonTexture);

        mediumButton = new HardnessButton(-HardnessButtonSize / 2, h / 5,
                HardnessButtonSize,
                HardnessButtonSize,
                mediumButtonTexture);

        hardButton = new HardnessButton(HardnessButtonSize - HardnessButtonSize / 2, h / 5,
                HardnessButtonSize,
                HardnessButtonSize,
                hardButtonTexture);

        Gdx.input.setInputProcessor(this);

        switch (Prefs.getHardness()){
            case easy:
                mediumButton.setIsOn(false);
                hardButton.setIsOn(false);
                break;
            case medium:
                easyButton.setIsOn(false);
                hardButton.setIsOn(false);
                break;
            case hard:
                mediumButton.setIsOn(false);
                easyButton.setIsOn(false);
                break;

        }
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        Gdx.gl.glClearColor(0, 0, 0, 1);

        shapeRenderer.setProjectionMatrix(game.camera.combined);

        game.batch.begin();

        game.batch.draw(bg, -w / 2, -h / 2, w, h, 0, 0, bg.getWidth(), bg.getHeight(), false, false);
        menuButton.draw(game.batch);
        easyButton.draw(game.batch);
        mediumButton.draw(game.batch);
        hardButton.draw(game.batch);

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

        if (easyButton.isClicked((int) touchPos.x, (int) touchPos.y)) {
            Prefs.setCurrentHardness(HardnessManager.HARDNESS.easy);
            mediumButton.setIsOn(false);
            hardButton.setIsOn(false);
        }
        if (mediumButton.isClicked((int) touchPos.x, (int) touchPos.y)) {
            Prefs.setCurrentHardness(HardnessManager.HARDNESS.medium);
            easyButton.setIsOn(false);
            hardButton.setIsOn(false);
        }
        if (hardButton.isClicked((int) touchPos.x, (int) touchPos.y)) {
            Prefs.setCurrentHardness(HardnessManager.HARDNESS.hard);
            mediumButton.setIsOn(false);
            easyButton.setIsOn(false);
        }

        return true;
    }
}
