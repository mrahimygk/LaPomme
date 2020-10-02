package ir.mrgkrahimy.lapomme.helpers;


import com.badlogic.gdx.utils.Array;

import ir.mrgkrahimy.lapomme.objects.Fruit;

/**
 * Created by Mojtaba on 9/30/2016.
 */
public class SpawnHandler {

    Array<Fruit> line1, line2, line3;

    public float spawnTime;
    public float time = 0.f;

    public SpawnHandler(Array<Fruit> line1, Array<Fruit> line2, Array<Fruit> line3) {
        this.line1 = line1;
        this.line2 = line2;
        this.line3 = line3;

        spawnTime = Prefs.getSpawnTime();
    }

    public boolean spawn(float delta) {

        time += delta;
        if (time >= spawnTime) {
            if (line1.size > 0) {
                line1.get(0).fall();
                time = 0;  //reset time
                return false;
            } else if (line2.size > 0) {
                line2.get(0).fall();
                time = 0;
                return false;
            } else if (line3.size > 0) {
                line3.get(0).fall();
                time = 0;
                return false;
            } else return true;
        } else return false;
    }
}