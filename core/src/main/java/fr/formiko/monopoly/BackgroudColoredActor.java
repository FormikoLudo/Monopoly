package fr.formiko.monopoly;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import fr.formiko.utils.WidgetsFactory;
import space.earlygrey.shapedrawer.ShapeDrawer;

public class BackgroudColoredActor extends Actor {
    private ShapeDrawer shapeDrawer;

    public BackgroudColoredActor(float width, float height, Color bgColor, ShapeDrawer shapeDrawer){
        super();
        this.setColor(bgColor);
        this.setSize(width, height);
        this.shapeDrawer = shapeDrawer; // Pass the ShapeDrawer instance through the constructor or some other method

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        shapeDrawer.setDefaultLineWidth(5f);
        shapeDrawer.rectangle(new Rectangle(getX(),getY(),getWidth(),getHeight()),Color.BLACK);
        shapeDrawer.filledRectangle(new Rectangle(getX(),getY(),getWidth(),getHeight()),getColor());
    }
}
