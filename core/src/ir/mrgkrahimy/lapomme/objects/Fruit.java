package ir.mrgkrahimy.lapomme.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

import ir.mrgkrahimy.lapomme.helpers.Consts;
import ir.mrgkrahimy.lapomme.helpers.AnimationHandler;
import ir.mrgkrahimy.lapomme.helpers.AssetsLoader;
import ir.mrgkrahimy.lapomme.helpers.HardnessManager;
import ir.mrgkrahimy.lapomme.helpers.Prefs;

/**
 * Created by Mojtaba on 9/26/2016.
 */
public class Fruit {

    public void makeDrawOnCenter() {
        if(type== TYPE.Orange){
            AnimationHandler.setDrawPosition(-Consts.FRUIT_HEIGHT / 2, 0, AnimationHandler.WHICH.ORANGE_WRONG);
        }
        else if(type == TYPE.Apple){
            AnimationHandler.setDrawPosition(-Consts.FRUIT_HEIGHT/2,0, AnimationHandler.WHICH.APPLE_WRONG);
        }
    }

    public enum TYPE {
        Apple, Orange
    }

    private TYPE type;

    private boolean isFalling = false;

    private BodyDef bodyDef;
    private Body body;

    private float size, rectangleScale = 1.5f;
    private float initX, initY;
    private Vector2 position;

    private Rectangle rectangle;

    public Fruit(World world, float initX, float initY, float size, float massDensity, TYPE type) {

        rectangle = new Rectangle(initX, initY, size / rectangleScale, size / rectangleScale);

        this.initX = initX = initX * Consts.WORLD_TO_BOX;
        this.initY = initY = initY * Consts.WORLD_TO_BOX;
        this.size = size = size * Consts.WORLD_TO_BOX;
        this.type = type;

        position = new Vector2(initX, initY);
        bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(position);
        body = world.createBody(bodyDef);

        CircleShape circleShape = new CircleShape();
        circleShape.setRadius((size / 2));

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = circleShape;
        fixtureDef.density = massDensity;
        fixtureDef.friction = 0.4f;
        fixtureDef.restitution = 0.3f;

        fixtureDef.filter.groupIndex=Consts.collide_help;

        body.createFixture(fixtureDef);

        body.setGravityScale(0);

        circleShape.dispose();
    }

    public void fall() {
        if (!isFalling) {
            body.applyForceToCenter(
                    new Vector2(0.f,
                            Consts.GRAVITY / HardnessManager.easiness
                    ), true);

            body.setGravityScale(Prefs.getGravityScale());
        }
        isFalling = true;
    }

    public boolean isFalling(){
        return isFalling;
    }
/*
    public void stopMotion() {
        body.setGravityScale(0);
    }

    public int getId() {
        return id;
    }
*/
    public TYPE getType() {
        return type;
    }

/*
    public void setId(int id) {
        this.id = id;
    }

    public void setType(TYPE type) {
        this.type = type;
    }
*/
    public Body getBody() {
        return body;
    }

    /*
    public BodyDef getBodyDef() {
        return bodyDef;
    }


    public void setBody(Body body) {
        this.body = body;
    }

    public void setBodyDef(BodyDef bodyDef) {
        this.bodyDef = bodyDef;
    }

    public Vector2 getPosition() {
        return position;
    }

    public float getInitX() {
        return initX;
    }

    public float getInitY() {
        return initY;
    }
*/
    public float getSize() {
        return size;
    }
/*
    public boolean hasBody(){
        return body!=null;
    }
*/
    public void draw(SpriteBatch batch, float x, float y, float w, float h) {
        if(type==TYPE.Apple){
            batch.draw(AssetsLoader.apple, x, y, w, h);
        }
        else if(type==TYPE.Orange){
            batch.draw(AssetsLoader.orange,x,y,w,h);
        }

    }

    public void update(){
        float rectX = (body.getPosition().x - size/(rectangleScale*2))*Consts.BOX_TO_WORLD;
        float rectY = (body.getPosition().y - size/(rectangleScale*2))*Consts.BOX_TO_WORLD;
        this.rectangle.setPosition(rectX,rectY);

        position.set(rectX,rectY);

    }

    public Rectangle getRectangle() {
        return rectangle;
    }
}
