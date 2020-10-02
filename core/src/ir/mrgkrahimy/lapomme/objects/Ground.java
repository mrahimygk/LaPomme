package ir.mrgkrahimy.lapomme.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;

import ir.mrgkrahimy.lapomme.helpers.AssetsLoader;

/**
 * Created by Mojtaba on 9/27/2016.
 */
public class Ground {

    private float size;
    private Vector2 position;
    BodyDef bodyDef;
    Body body;

    private World world;

    public Ground(World world, float size, Vector2 position){
        this.position = position; //new Vector2(-Consts.W /2,-Consts.H /2.5f);

        this.world=world;

        this.size=size;

        bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        body=world.createBody(bodyDef);

    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public void setSize(float size) {
        this.size = size;
    }


    public Vector2 getPosition() {
        return position;
    }

    public float getSize() {
        return size;
    }

    public Body getBody() {
        return body;
    }


    public void draw(SpriteBatch batch) {
        batch.draw(
                AssetsLoader.ground,
                position.x,
                position.y,
                size * AssetsLoader.groundAspectRatio,
                size
        );

    }
}
