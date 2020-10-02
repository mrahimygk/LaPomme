package ir.mrgkrahimy.lapomme.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import java.util.ArrayList;
import java.util.List;

import ir.mrgkrahimy.lapomme.helpers.Consts;
import ir.mrgkrahimy.lapomme.helpers.AssetsLoader;

/**
 * Created by Mojtaba on 9/26/2016.
 */
public class Basket {

    private BodyDef bodyDef;
    private Body body;

    private Rectangle leftGatherer, rightGatherer;

    private float size;
    private Vector2 position;
    float scale = Consts.BASKET_HEIGHT/60; // 60 is the max h from vertexArrayL[n] lines

    public Basket(World world, float y, float size ){

        y=y* Consts.WORLD_TO_BOX;
        position = new Vector2(-30* Consts.WORLD_TO_BOX,y);

        this.size=size=size* Consts.WORLD_TO_BOX;
        bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;

        bodyDef.position.set(position);
        body = world.createBody(bodyDef);
        body.setFixedRotation(true);
        body.setGravityScale(0);

        // LEFT - LEFT ////////////////////////////////////////////////////////////////////////////
        PolygonShape polygonShapeLL = new PolygonShape();
        Vector2[] vertexArrayLL = new Vector2[4];
        vertexArrayLL[0] = new Vector2(5.f, 0f);
        vertexArrayLL[1] = new Vector2(0.f, 65.f);
        vertexArrayLL[2] = new Vector2(10.f, 5.f);
        vertexArrayLL[3] = new Vector2(5.f, 0f);

        //scale & WORLD_TO_BOX
        for(Vector2 vector:vertexArrayLL){
            vector.x*=Consts.WORLD_TO_BOX;
            vector.y*=Consts.WORLD_TO_BOX;
        }

        for(Vector2 vector:vertexArrayLL){
            vector.x*=scale;
            vector.y*=scale;
        }

        polygonShapeLL.set(vertexArrayLL);

        FixtureDef fixtureDefLL = new FixtureDef();
        fixtureDefLL.shape = polygonShapeLL;

        Fixture fixtureLL = body.createFixture(fixtureDefLL);

        polygonShapeLL.dispose();
        ///////////////////////////////////////////////////////////////////////////////////////////

        // LEFT - Bot ////////////////////////////////////////////////////////////////////////////
        PolygonShape polygonShapeLB = new PolygonShape();
        Vector2[] vertexArrayLB = new Vector2[5];
        vertexArrayLB[0] = new Vector2(5.f, 0f);
        vertexArrayLB[1] = new Vector2(10.f, 5.f);
        vertexArrayLB[2] = new Vector2(45.f, 5.f);
        vertexArrayLB[3] = new Vector2(50.f, 0.f);
        vertexArrayLB[4] = new Vector2(5.f, 0f);

        //: scale & WORLD_TO_BOX
        for(Vector2 vector:vertexArrayLB){
            vector.x*=Consts.WORLD_TO_BOX;
            vector.y*=Consts.WORLD_TO_BOX;
        }

        for(Vector2 vector:vertexArrayLB){
            vector.x*=scale;
            vector.y*=scale;
        }


        polygonShapeLB.set(vertexArrayLB);

        FixtureDef fixtureDefLB = new FixtureDef();
        fixtureDefLB.shape = polygonShapeLB;

        Fixture fixtureLB = body.createFixture(fixtureDefLB);

        polygonShapeLB.dispose();
        ///////////////////////////////////////////////////////////////////////////////////////////

        // LEFT - R ////////////////////////////////////////////////////////////////////////////
        PolygonShape polygonShapeLR = new PolygonShape();
        Vector2[] vertexArrayLR = new Vector2[4];
        vertexArrayLR[0] = new Vector2(50.f, 0f);
        vertexArrayLR[1] = new Vector2(45.f, 5.f);
        vertexArrayLR[2] = new Vector2(50.f, 65.f);
        vertexArrayLR[3] = new Vector2(50.f, 0f);

        //: scale & WORLD_TO_BOX
        for(Vector2 vector:vertexArrayLR){
            vector.x*=Consts.WORLD_TO_BOX;
            vector.y*=Consts.WORLD_TO_BOX;
        }

        for(Vector2 vector:vertexArrayLR){
            vector.x*=scale;
            vector.y*=scale;
        }

        polygonShapeLR.set(vertexArrayLR);

        FixtureDef fixtureDefLR = new FixtureDef();
        fixtureDefLR.shape = polygonShapeLR;

        Fixture fixtureLR = body.createFixture(fixtureDefLR);

        polygonShapeLR.dispose();
        ///////////////////////////////////////////////////////////////////////////////////////////

        // Right - L ////////////////////////////////////////////////////////////////////////////
        PolygonShape polygonShapeRL = new PolygonShape();
        Vector2[] vertexArrayRL = new Vector2[4];

        vertexArrayRL[0] = new Vector2(60.f,0.f);
        vertexArrayRL[1] = new Vector2(55.f,65.f);
        vertexArrayRL[2] = new Vector2(65.f, 5.f);
        vertexArrayRL[3] = new Vector2(60.f,0.f);


        for(Vector2 vector:vertexArrayRL){
            vector.x*=Consts.WORLD_TO_BOX;
            vector.y*=Consts.WORLD_TO_BOX;
        }

        for(Vector2 vector:vertexArrayRL){
            vector.x*=scale;
            vector.y*=scale;
        }

        polygonShapeRL.set(vertexArrayRL);

        FixtureDef fixtureDefRL = new FixtureDef();
        fixtureDefRL.shape = polygonShapeRL;

        Fixture fixtureRL = body.createFixture(fixtureDefRL);

        polygonShapeRL.dispose();
        //////////////////////////////////////////////////////////////////////////////////////////

        // Right - B ////////////////////////////////////////////////////////////////////////////

        PolygonShape polygonShapeRB = new PolygonShape();
        Vector2[] vertexArrayRB = new Vector2[5];

        vertexArrayRB[0] = new Vector2(60.f,0.f);
        vertexArrayRB[1] = new Vector2(65.f,5.f);
        vertexArrayRB[2] = new Vector2(95.f, 5.f);
        vertexArrayRB[3] = new Vector2(100.f, 0.f);
        vertexArrayRB[4] = new Vector2(60.f,0.f);

        //: scale & WORLD_TO_BOX
        for(Vector2 vector:vertexArrayRB){
            vector.x*=Consts.WORLD_TO_BOX;
            vector.y*=Consts.WORLD_TO_BOX;
        }

        for(Vector2 vector:vertexArrayRB){
            vector.x*=scale;
            vector.y*=scale;
        }

        polygonShapeRB.set(vertexArrayRB);

        FixtureDef fixtureDefRB = new FixtureDef();
        fixtureDefRB.shape = polygonShapeRB;

        Fixture fixtureRB = body.createFixture(fixtureDefRB);

        polygonShapeRB.dispose();
        //////////////////////////////////////////////////////////////////////////////////////////

        // Right - R ////////////////////////////////////////////////////////////////////////////
        PolygonShape polygonShapeRR = new PolygonShape();
        Vector2[] vertexArrayRR = new Vector2[4];

        vertexArrayRR[0] = new Vector2(100.f,0.f);
        vertexArrayRR[1] = new Vector2(95.f,5.f);
        vertexArrayRR[2] = new Vector2(100.f, 65.f);
        vertexArrayRR[3] = new Vector2(100.f,0.f);

        //: scale & WORLD_TO_BOX
        for(Vector2 vector:vertexArrayRR){
            vector.x*=Consts.WORLD_TO_BOX;
            vector.y*=Consts.WORLD_TO_BOX;
        }

        for(Vector2 vector:vertexArrayRR){
            vector.x*=scale;
            vector.y*=scale;
        }

        polygonShapeRR.set(vertexArrayRR);

        FixtureDef fixtureDefRR = new FixtureDef();
        fixtureDefRR.shape = polygonShapeRR;

        Fixture fixtureRR = body.createFixture(fixtureDefRR);

        polygonShapeRR.dispose();
        //////////////////////////////////////////////////////////////////////////////////////////

        //: moving body fixture def
        PolygonShape polygonShapeWhole = new PolygonShape();
        Vector2[] vertexArrayBoundaries = new Vector2[4];

        vertexArrayBoundaries[0] = new Vector2(0.f,-100.f);
        vertexArrayBoundaries[1] = new Vector2(100.f,-100.f);
        vertexArrayBoundaries[2] = new Vector2(100.f, 40.f);
        vertexArrayBoundaries[3] = new Vector2(0.f,40.f);

        //: scale & WORLD_TO_BOX
        for(Vector2 vector:vertexArrayBoundaries){
            vector.x*=Consts.WORLD_TO_BOX;
            vector.y*=Consts.WORLD_TO_BOX;
        }

        for(Vector2 vector:vertexArrayBoundaries){
            vector.x*=scale;
            vector.y*=scale;
        }

        polygonShapeWhole.set(vertexArrayBoundaries);

        FixtureDef fixtureDefWhole = new FixtureDef();
        fixtureDefWhole.shape = polygonShapeWhole;

        fixtureDefWhole.filter.groupIndex=Consts.collide_help;

        Fixture fixture = body.createFixture(fixtureDefWhole);

        polygonShapeWhole.dispose();

        ////////////////////////////////////////////////////////////////////////////////

        leftGatherer = new Rectangle(
                (body.getPosition().x - size/2 + size)*Consts.BOX_TO_WORLD,
                (body.getPosition().y - size/2)*Consts.BOX_TO_WORLD,
                (size/2)*Consts.BOX_TO_WORLD,
                (size/10)*Consts.BOX_TO_WORLD
        );

        rightGatherer = new Rectangle(
                (body.getPosition().x - size/2 + size)*Consts.BOX_TO_WORLD,
                (body.getPosition().y - size/2 )*Consts.BOX_TO_WORLD,
                (size/2)*Consts.BOX_TO_WORLD,
                (size/10)*Consts.BOX_TO_WORLD
        );

        body.setGravityScale(0);
    }

    public float getSize() {
        return size;
    }

    public Vector2 getPosition() {
        return position;
    }

    public Body getBody() {
        return body;
    }

    public BodyDef getBodyDef() {
        return bodyDef;
    }

    public Rectangle getLeftGatherer() {
        return leftGatherer;
    }

    public Rectangle getRightGatherer() {
        return rightGatherer;
    }

    public void setSize(float size) {
        this.size = size;
    }

    public List<Rectangle> getRectangles() {
        List<Rectangle> rectangles = new ArrayList<Rectangle>(2);
        rectangles.add(leftGatherer);
        rectangles.add(rightGatherer);
        return rectangles;
    }

    public void update(){
        float rectLX = (body.getPosition().x + size/5 )*Consts.BOX_TO_WORLD;
        float rectLY = (body.getPosition().y + size/5 )*Consts.BOX_TO_WORLD;
        leftGatherer.setPosition(rectLX, rectLY);

        float rectRX = (body.getPosition().x + size*1.1f) * Consts.BOX_TO_WORLD;
        float rectRY = (body.getPosition().y + size/5) * Consts.BOX_TO_WORLD;
        rightGatherer.setPosition(rectRX, rectRY);

        // make basket to move horizontally
        Vector2 vector = body.getLinearVelocity();
        vector.y=0.f;
        body.setLinearVelocity(vector);
    }

    public void stopMoving(){
        body.setLinearVelocity(0, 0);
    }

    public Vector2 getBodyPosition(){
        return body.getPosition();
    }

    public void draw(SpriteBatch batch) {
        batch.draw(
                AssetsLoader.basket,
                getBodyPosition().x * Consts.BOX_TO_WORLD,
                getBodyPosition().y * Consts.BOX_TO_WORLD,
                (getSize() * AssetsLoader.basketAspectRatio) * Consts.BOX_TO_WORLD,
                getSize() * Consts.BOX_TO_WORLD
        );
    }
}
