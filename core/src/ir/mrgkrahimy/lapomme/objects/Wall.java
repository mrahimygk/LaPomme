package ir.mrgkrahimy.lapomme.objects;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import ir.mrgkrahimy.lapomme.helpers.Consts;

/**
 * Created by Mojtaba on 10/6/2016.
 */
public class Wall {
    private BodyDef bodyDef;
    private Body body;


    private float size;
    private Vector2 position;

    public Wall(World world, Vector2 position){
        position.x=position.x* Consts.WORLD_TO_BOX;
        position.y=position.y*Consts.WORLD_TO_BOX;

        bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;

        bodyDef.position.set(position);
        body = world.createBody(bodyDef);
        body.setFixedRotation(true);



        PolygonShape polygonShapeRB = new PolygonShape();
        Vector2[] vertexArrayRB = new Vector2[5];

        vertexArrayRB[0] = new Vector2(-1.f,0.f);
        vertexArrayRB[1] = new Vector2(-1.f,Consts.H/2);
        vertexArrayRB[2] = new Vector2(1.f, Consts.H/2);
        vertexArrayRB[3] = new Vector2(1.f, 0.f);
        vertexArrayRB[4] = new Vector2(-1.f,0.f);

        //: scale & WORLD_TO_BOX
        for(Vector2 vector:vertexArrayRB){
            vector.x*=Consts.WORLD_TO_BOX;
            vector.y*=Consts.WORLD_TO_BOX;
        }

        polygonShapeRB.set(vertexArrayRB);

        FixtureDef fixtureDefRB = new FixtureDef();
        fixtureDefRB.shape = polygonShapeRB;

        Fixture fixtureRB = body.createFixture(fixtureDefRB);

        body.setGravityScale(0);


    }
}
