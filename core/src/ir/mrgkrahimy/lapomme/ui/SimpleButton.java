package ir.mrgkrahimy.lapomme.ui;

/**
 * Created by Mojtaba on 10/3/2016.
 */

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Mojtaba on 9/3/2016.
 */
public class SimpleButton {
    private float x, y, width, height;

private TextureRegion buttonUp;
private TextureRegion buttonDown;

private Rectangle bounds;

private boolean isPressed = false;

    public SimpleButton(float x, float y, float width, float height,
                        TextureRegion buttonUp, TextureRegion buttonDown) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.buttonUp = buttonUp;
        this.buttonDown = buttonDown;

        bounds = new Rectangle(x, y, width, height);

    }

    public boolean isClicked(int sX, int sY){
        return bounds.contains(sX,sY);
    }

    public void draw(SpriteBatch batch){
        if(isPressed){
            batch.draw(buttonDown, x,y,width,height);
        }else{
            batch.draw(buttonUp, x,y,width,height);
        }
    }

    public boolean isTouchDown(int sX, int sY){
        if(bounds.contains(sX,sY)){
            isPressed=true;
            return true;
        }

        return false;
    }

    public boolean isTouchUp(int sX,int sY){
        if(bounds.contains(sX,sY) && isPressed){
            isPressed=false;
            return true;
        }

        isPressed=false;
        return false;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public Vector2 getPosition(){
        return new Vector2(x,y);
    }
}
