package ir.mrgkrahimy.lapomme.ui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import ir.mrgkrahimy.lapomme.helpers.Prefs;

/**
 * Created by Mojtaba on 10/10/2016.
 */
public class ToggleButton {
    private float x, y, width, height;

    private TextureRegion buttonOn;
    private TextureRegion buttonOff;

    private Rectangle bounds;

    private boolean isOn = Prefs.isSoundOn();

    public ToggleButton(float x, float y, float width, float height,
                        TextureRegion buttonOn, TextureRegion buttonOff) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.buttonOn = buttonOn;
        this.buttonOff = buttonOff;

        bounds = new Rectangle(x, y, width, height);

    }

    public boolean isClicked(int sX, int sY){
        boolean clicked = bounds.contains(sX,sY);

        if(clicked) isOn = !isOn;
        return clicked;
    }

    public void draw(SpriteBatch batch){
        if(isOn){
            batch.draw(buttonOn, x,y,width,height);
        }else{
            batch.draw(buttonOff, x,y,width,height);
        }
    }

    public boolean isTouchDown(int sX, int sY){
        if(bounds.contains(sX,sY)){
            isOn =true;
            return true;
        }

        return false;
    }

    public boolean isTouchUp(int sX,int sY){
        if(bounds.contains(sX,sY) && isOn){
            isOn =false;
            return true;
        }

        isOn =false;
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
