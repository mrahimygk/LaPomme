package ir.mrgkrahimy.lapomme.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.QueryCallback;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.joints.MouseJoint;
import com.badlogic.gdx.physics.box2d.joints.MouseJointDef;
import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import ir.mrgkrahimy.lapomme.base.TheGame;
import ir.mrgkrahimy.lapomme.helpers.AnimationHandler;
import ir.mrgkrahimy.lapomme.helpers.AssetsLoader;
import ir.mrgkrahimy.lapomme.helpers.Consts;
import ir.mrgkrahimy.lapomme.helpers.CollisionHandler;
import ir.mrgkrahimy.lapomme.ui.HUD;
import ir.mrgkrahimy.lapomme.helpers.Prefs;
import ir.mrgkrahimy.lapomme.helpers.ScreenshotFactory;
import ir.mrgkrahimy.lapomme.helpers.SoundHandler;
import ir.mrgkrahimy.lapomme.helpers.SpawnHandler;
import ir.mrgkrahimy.lapomme.objects.Basket;
import ir.mrgkrahimy.lapomme.objects.Fruit;
import ir.mrgkrahimy.lapomme.objects.Ground;
import ir.mrgkrahimy.lapomme.objects.Wall;

/**
 * Created by Mojtaba on 9/26/2016.
 */
public class MainGame extends InputAdapter implements Screen {

    final TheGame game;

    private CollisionHandler collisionHandler;
    private SpawnHandler spawnHandler;

    private HUD hud;

    private boolean gameOver=false;
    Pixmap lastScreen;

    int currentScore, maxScore, life = 3;

    private Array<Array> fruitLines;

    private Array<Fruit> fallingFruits;
    List<Rectangle> fruitRectangles;
    private List<Body> bodiesToRemove;
    private Basket basket;
    private Ground ground;
    private MouseJointDef jointDef;

    private float basketLeftX, basketLeftY,basketRightX,basketRightY;

    private World world;

    // Debug renderer
    //private Box2DDebugRenderer debugRenderer;
    //private ShapeRenderer shapeRenderer;

    private float w, h;

    int velocityIterations = 6;   //how strongly to correct velocity
    int positionIterations = 2;   //how strongly to correct position

    public MainGame(final TheGame game, float w, float h) {
        this.w = w;
        this.h = h;
        this.game = game;
    }

    @Override
    public void show() {

        AnimationHandler.init();
        SoundHandler.init();
        Prefs.init();

        currentScore = 0;

        maxScore= Prefs.getMaxScore();

        // instantiate
        Box2D.init();
        world = new World(new Vector2(0.0f, Consts.GRAVITY), true);
        //debugRenderer = new Box2DDebugRenderer();
        //shapeRenderer = new ShapeRenderer();

        bg = AssetsLoader.background;

        bodiesToRemove = new ArrayList<Body>(Consts.FRUIT_COUNT);

        Array<Fruit> firstLineFruitList = new Array<Fruit>(Consts.FRUIT_COUNT);
        Array<Fruit> secondLineFruitList = new Array<Fruit>(Consts.FRUIT_COUNT);
        Array<Fruit> thirdLineFruitList = new Array<Fruit>(Consts.FRUIT_COUNT);

        //fruitLines = new Array<Array>(Consts.FRUIT_COUNT); //  why twelve? need 3
        fruitLines = new Array<Array>(Consts.FRUIT_LINE_COUNT);
        fallingFruits = new Array<Fruit>(1);

        Array<Wall> walls = new Array<Wall>(2);
        walls.add(new Wall(world, new Vector2(-w / 2, -h / 2)));
        walls.add(new Wall(world, new Vector2(w / 2, -h / 2)));

        float yPos = (h / 2) - (Consts.TOP_GAP);
        for (int i = 0; i < Consts.FRUIT_COUNT; i++) {
            float xPos = Consts.FRUIT_LEFT_PADDING + (i * Consts.FRUIT_HEIGHT) - (Consts.W / 2);
            Fruit fruit;
            if (i % 2 == 0) {
                fruit = new Fruit(world, xPos, yPos, Consts.FRUIT_HEIGHT, 1f, Fruit.TYPE.Apple);
            } else {
                fruit = new Fruit(world, xPos, yPos, Consts.FRUIT_HEIGHT, 1f, Fruit.TYPE.Orange);
            }

            firstLineFruitList.add(fruit);
        }

        yPos -= Consts.FRUIT_H_MARGIN;
        for (int i = 0; i < Consts.FRUIT_COUNT; i++) {
            float xPos = Consts.FRUIT_LEFT_PADDING + (i * Consts.FRUIT_HEIGHT) - (Consts.W / 2);


            Fruit fruit;
            if (i % 2 == 0) {
                fruit = new Fruit(world, xPos, yPos, Consts.FRUIT_HEIGHT, 1f, Fruit.TYPE.Apple);
            } else {
                fruit = new Fruit(world, xPos, yPos, Consts.FRUIT_HEIGHT, 1f, Fruit.TYPE.Orange);
            }

            secondLineFruitList.add(fruit);
        }

        yPos -= Consts.FRUIT_H_MARGIN;
        for (int i = 0; i < Consts.FRUIT_COUNT; i++) {
            float xPos = Consts.FRUIT_LEFT_PADDING + (i * Consts.FRUIT_HEIGHT) - (Consts.W / 2);


            Fruit fruit;
            if (i % 2 == 0) {
                fruit = new Fruit(world, xPos, yPos, Consts.FRUIT_HEIGHT, 1f, Fruit.TYPE.Apple);
            } else {
                fruit = new Fruit(world, xPos, yPos, Consts.FRUIT_HEIGHT, 1f, Fruit.TYPE.Orange);
            }

            thirdLineFruitList.add(fruit);
        }

        // shuffle
        firstLineFruitList.shuffle();
        secondLineFruitList.shuffle();
        thirdLineFruitList.shuffle();

        // : setup an array of arrays (lines)
        fruitLines.add(firstLineFruitList);
        fruitLines.add(secondLineFruitList);
        fruitLines.add(thirdLineFruitList);

        ground = new Ground(world, Consts.GROUND_SIZE,new Vector2(-w /2,-h /2.5f));
        int basketY = (int) (ground.getPosition().y + ground.getSize());
        basket = new Basket(world, basketY, Consts.BASKET_HEIGHT);

        fruitRectangles = new ArrayList<Rectangle>(Consts.FRUIT_COUNT*3);
        for (Fruit fruit : firstLineFruitList) {
            fruitRectangles.add(fruit.getRectangle());
        }
        for (Fruit fruit : secondLineFruitList) {
            fruitRectangles.add(fruit.getRectangle());
        }
        for (Fruit fruit : thirdLineFruitList) {
            fruitRectangles.add(fruit.getRectangle());
        }

        collisionHandler = new CollisionHandler(
                fruitRectangles,
                basket.getRectangles(),
                ground.getPosition().y * Consts.WORLD_TO_BOX
        );

        spawnHandler = new SpawnHandler(fruitLines.get(2), fruitLines.get(1), fruitLines.get(0)); // third line drops first

        //Gdx.input.setInputProcessor(this);

        SoundHandler.playLevelMusic();

        jointDef=new MouseJointDef();
        jointDef.bodyA = ground.getBody();
        jointDef.bodyB = basket.getBody();

        jointDef.collideConnected=true;

        jointDef.maxForce=100;

        hud=new HUD((int) (-w/2)+20,(int) (ground.getPosition().y-ground.getSize()*3), game);

        // set two Inputs for one screen
        InputMultiplexer inputMultiplexer = new InputMultiplexer();

        InputProcessor inputProcessorOne = this;
        InputProcessor inputProcessorTwo = hud;

        inputMultiplexer.addProcessor(inputProcessorOne);
        inputMultiplexer.addProcessor(inputProcessorTwo);

        Gdx.input.setInputProcessor(inputMultiplexer);

    }

    @Override
    public void render(float delta) {

        SoundHandler.check();

        // check if game is over and go to game over scene
        if(gameOver) {
            //  going to game over
            AnimationHandler.resetAllTimes();
            if(currentScore>maxScore) {
                maxScore = currentScore;
                Prefs.setMaxScore(maxScore);
            }

            lastScreen = ScreenshotFactory.getScreenshot(0, 0,
                    Gdx.graphics.getWidth(), Gdx.graphics.getHeight(),
                    false
            );

            Prefs.setCurrentScore(currentScore);
            game.setScreen(new GameOverScene(game, lastScreen, w, h));

        }else {
            // game is not over , so clear prev frame
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
            Gdx.gl.glClearColor(0,0,0,1);
            boolean allDone = spawnHandler.spawn(delta);

            // not all the fruits have been fallen, so continue this scene
            if (!allDone) {


                //: this is working !
                Iterator<Body> iterator = bodiesToRemove.iterator();

                while (iterator.hasNext()) {
                    Body body = iterator.next();

                    if (!world.isLocked() && !body.isActive()) {
                        world.destroyBody(body);
                        iterator.remove();
                    }
                }

                game.batch.setProjectionMatrix(game.camera.combined);
                //shapeRenderer.setProjectionMatrix(game.camera.combined);

                // update fruit's rectangle position here
                for (Array fruitList : fruitLines) {
                    for (Fruit fruit : (Iterable<Fruit>) fruitList) {
                        if(fruit.isFalling()) {
                            fruit.update();
                        }
                    }
                }

                // update basket's rectangles (left and right) position here
                basket.update();

                // update fruit's and basket's rectangle pos referenced in collisionHandler here
                collisionHandler.update(fruitRectangles, basket.getRectangles());

                game.batch.begin();

                //  draw background behind everything
                game.batch.draw(bg, -w / 2, -h / 2, w, h, 0, 0, bg.getWidth(), bg.getHeight(), false, true);

                basket.draw(game.batch);

                // iterate and remove from list if collided
                for (Array fruitList : fruitLines) {
                    Iterator<Fruit> fruitIterator = fruitList.iterator();
                    while (fruitIterator.hasNext()) {

                        Fruit fruit = fruitIterator.next();
                        Rectangle rectangle = fruit.getRectangle();
                        Vector2 fruitPos = fruit.getBody().getPosition();
                        if (collisionHandler.isFruitCollidedWithBasket(rectangle)) {
                            // getting w and h of the fruit and pass them to setDrawSize
                            // ('cause between orange an apple it is varies)
                            float w = fruit.getSize() * Consts.BOX_TO_WORLD;
                            float h = (fruit.getSize() * AssetsLoader.appleAspectRatio) * Consts.BOX_TO_WORLD;
                            AnimationHandler.setDrawSize(w, h);

                            if (collisionHandler.hitLeftGatherer(rectangle)) {
                                if (fruit.getType() == Fruit.TYPE.Apple) {
                                    // apple hitLeftGatherer (correct)
                                    currentScore++;
                                    // draw on basket's left rectangle's center
                                    // pass the left rectangle's position
                                    AnimationHandler.setDrawPosition(
                                            AnimationHandler.RECT.left, // draw where?
                                            basket.getLeftGatherer(), // update draw pos
                                            AnimationHandler.WHICH.APPLE_CORRECT // draw which animation?
                                    );
                                    SoundHandler.play(SoundHandler.SOUND.correct);

                                } else if (fruit.getType() == Fruit.TYPE.Orange) {
                                    // orange hitLeftGatherer (wrong)
                                    life--;
                                    AnimationHandler.setDrawPosition(
                                            AnimationHandler.RECT.left,
                                            basket.getLeftGatherer(),
                                            AnimationHandler.WHICH.ORANGE_WRONG
                                    );
                                    SoundHandler.play(SoundHandler.SOUND.wrong);
                                    if (life <= 0) {
                                        gameOver = true;
                                    }
                                }
                            }
                            // hit orange basket
                            else if (collisionHandler.hitRightGatherer(rectangle)) {
                                if (fruit.getType() == Fruit.TYPE.Orange) {
                                    // orange hitRightGatherer (correct)
                                    currentScore++;
                                    AnimationHandler.setDrawPosition(
                                            AnimationHandler.RECT.right,
                                            basket.getRightGatherer(),
                                            AnimationHandler.WHICH.ORANGE_CORRECT
                                    );
                                    SoundHandler.play(SoundHandler.SOUND.correct);
                                } else if (fruit.getType() == Fruit.TYPE.Apple) {
                                    // Apple hitRightGatherer (wrong)

                                    AnimationHandler.setDrawPosition(
                                            AnimationHandler.RECT.right,
                                            basket.getRightGatherer(),
                                            AnimationHandler.WHICH.APPLE_WRONG
                                    );
                                    SoundHandler.play(SoundHandler.SOUND.wrong);
                                    life--;
                                    if (life <= 0) {
                                        gameOver = true;
                                    }
                                }
                            }

                            fruit.getBody().getPosition().set(10, 10);
                            fruit.update();
                            bodiesToRemove.add(fruit.getBody());
                            fruit.getBody().setActive(false);
                            fruitIterator.remove();
                        }

                        if (!collisionHandler.isFruitCollidedWithGround(fruitPos)) {
                            float x = (fruitPos.x - fruit.getSize() / 2) * Consts.BOX_TO_WORLD;
                            float y = (fruitPos.y - fruit.getSize() / 2) * Consts.BOX_TO_WORLD;
                            float w = fruit.getSize() * Consts.BOX_TO_WORLD;
                            float h = (fruit.getSize() * AssetsLoader.appleAspectRatio) * Consts.BOX_TO_WORLD;

                            fruit.draw(game.batch, x, y, w, h);

                        } else if (collisionHandler.isFruitCollidedWithGround(fruitPos)) {

                            life--;

                            // changing to correct world pixels
                            float w = fruit.getSize() * Consts.BOX_TO_WORLD;
                            float h = (fruit.getSize() * AssetsLoader.appleAspectRatio) * Consts.BOX_TO_WORLD;
                            AnimationHandler.setDrawSize(w, h);
                            fruit.makeDrawOnCenter();
                            SoundHandler.play(SoundHandler.SOUND.wrong);
                            bodiesToRemove.add(fruit.getBody());
                            fruit.getBody().setActive(false);
                            fruitIterator.remove();

                            if (life <= 0) {
                                gameOver = true;
                            }

                        }
                    }
                }
                // draw animations according to previous iteration set
                if(AnimationHandler.drawAppleCorrect){
                    AnimationHandler.draw(AnimationHandler.ANIM.appleCorrect, game.batch,delta);
                }
                if(AnimationHandler.drawAppleWrong){
                    AnimationHandler.draw(AnimationHandler.ANIM.appleWrong, game.batch,delta);
                }
                if (AnimationHandler.drawOrangeCorrect){
                    AnimationHandler.draw(AnimationHandler.ANIM.orangeCorrect, game.batch,delta);
                }
                if(AnimationHandler.drawOrangeWrong){
                    AnimationHandler.draw(AnimationHandler.ANIM.orangeWrong, game.batch,delta);
                }

                ground.draw(game.batch);

                // drawing hud on top of everything
                hud.draw(game.batch, currentScore, life);

                game.batch.end();

                world.step(Consts.step, velocityIterations, positionIterations);

                /*
                Matrix4 c = game.camera.combined.cpy();
                debugRenderer.render(world, c);
                */


            } else {
                // game is successful
                AnimationHandler.resetAllTimes();
                if(currentScore>maxScore){
                    maxScore=currentScore;
                    Prefs.setMaxScore(maxScore);
                }
                Prefs.setCurrentScore(currentScore);
                Prefs.addHardness();
                game.setScreen(new GameEnd(game,w,h));
            }
        }

        // draw animation inside basket:
        // update made in "render" method, 'cause its faster
        updateBasketRectPositions();
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
        //debugRenderer.dispose();
        AssetsLoader.dispose();
        world.dispose();
        bg.dispose();
        game.batch.dispose();

    }

    private Vector3 tmp = new Vector3();
    private Vector2 tmp2 = new Vector2();
    private MouseJoint mouseJoint;

    private QueryCallback queryCallback = new QueryCallback() {
        @Override
        public boolean reportFixture(Fixture fixture) {
            if(!fixture.testPoint(tmp.x,tmp.y)){
                return true;
            }
            jointDef.bodyB = basket.getBody();

            tmp.y=-1.f;
            jointDef.target.set(tmp.x,tmp.y);

            mouseJoint = (MouseJoint) world.createJoint(jointDef);
            return false;
        }
    };
    @Override
    public boolean touchDown (int screenX, int screenY, int pointer, int button) {

        tmp.set(screenX, screenY, 0);
        game.camera.unproject(tmp);

        tmp.x*= Consts.WORLD_TO_BOX;
        tmp.y*= Consts.WORLD_TO_BOX;

        world.QueryAABB(queryCallback, tmp.x, tmp.y, tmp.x, tmp.y);

        return true;
    }

    @Override
    public boolean touchUp (int screenX, int screenY, int pointer, int button) {
        if(mouseJoint==null){
            return false;
        }

        world.destroyJoint(mouseJoint);
        mouseJoint=null;

        basket.stopMoving();

        return true;
    }

    @Override
    public boolean touchDragged (int screenX, int screenY, int pointer) {
        if (mouseJoint == null) {
            return false;
        }

        tmp.set(screenX, screenY, 0);
        game.camera.unproject(tmp);
        tmp.x *= Consts.WORLD_TO_BOX;
        tmp.y *= Consts.WORLD_TO_BOX;
        tmp.y = -1.f;
        mouseJoint.setTarget(tmp2.set(tmp.x, tmp.y));

        if (AnimationHandler.drawOnLeftRect) {

            AnimationHandler.updatePos(basketLeftX, basketLeftY);
        }
        if (AnimationHandler.drawOnRightRect) {

            AnimationHandler.updatePos(basketRightX, basketRightY);
        }

        return true;
    }

    private Texture bg;

    private void updateBasketRectPositions(){
        basketLeftX = basket.getLeftGatherer().getX();
        basketLeftY = basket.getLeftGatherer().getY();

        basketRightX = basket.getRightGatherer().getX();
        basketRightY = basket.getRightGatherer().getY();
    }
}
