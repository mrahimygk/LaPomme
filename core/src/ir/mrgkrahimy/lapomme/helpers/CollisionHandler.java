package ir.mrgkrahimy.lapomme.helpers;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.List;

/**
 * Created by Mojtaba on 9/30/2016.
 */
public class CollisionHandler {

    List<Rectangle> fruitRectangleList;
    Rectangle basketLeftRectangle,  basketRightRectangle;
    float groundY;

    public CollisionHandler(List<Rectangle> fruitRectangleList, List<Rectangle> basketRectangles, float groundY){

        this.fruitRectangleList=fruitRectangleList;
        this.basketLeftRectangle=basketRectangles.get(0);
        this.basketRightRectangle=basketRectangles.get(1);

        this.groundY = groundY;
    }

    public boolean isFruitCollidedWithBasket(Rectangle fruitRectangle){
        return (fruitRectangle.overlaps(basketLeftRectangle) || fruitRectangle.overlaps(basketRightRectangle));
    }

    public boolean hitLeftGatherer(Rectangle fruitRectangle){
        return fruitRectangle.overlaps(basketLeftRectangle);
    }

    public boolean hitRightGatherer(Rectangle fruitRectangle){
        return fruitRectangle.overlaps(basketRightRectangle);
    }

    public void update(List<Rectangle> fruitRectangleList, List<Rectangle> basketRectangles){
        this.fruitRectangleList=fruitRectangleList;
        this.basketLeftRectangle=basketRectangles.get(0);
        this.basketRightRectangle=basketRectangles.get(1);

    }

    public boolean isFruitCollidedWithGround(Vector2 fruitPos){
        return fruitPos.y < groundY+ Consts.FRUIT_HEIGHT/2*Consts.WORLD_TO_BOX;
    }

}
