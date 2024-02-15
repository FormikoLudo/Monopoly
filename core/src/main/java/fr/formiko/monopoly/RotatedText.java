package fr.formiko.monopoly;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import fr.formiko.utils.WidgetsFactory;

public class RotatedText extends Actor {

    Label label;

    public RotatedText() {
        super();
        this.label = new Label("test", WidgetsFactory.mySkin);

        this.label.setPosition(getX(),getY());

        this.setRotation(45);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        this.label.setColor(Color.RED);
        this.label.draw(batch,parentAlpha);
    }
}
