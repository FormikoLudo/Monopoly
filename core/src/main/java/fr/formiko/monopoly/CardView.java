package fr.formiko.monopoly;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import fr.formiko.model.field.Card;
import fr.formiko.model.field.Domain;
import fr.formiko.model.field.FieldElement;
import fr.formiko.model.field.LuxuryTaxe;
import space.earlygrey.shapedrawer.ShapeDrawer;

public class CardView extends Actor {
    private ShapeDrawer shapeDrawer;
    FieldElement model;
    public CardView(FieldElement model, ShapeDrawer shapeDrawer){
        this.model = model;
        this.setSize(100,160);
        this.shapeDrawer = shapeDrawer;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        System.out.println("canonical name : " + model.getClass().getCanonicalName());
        switch (model.getClass().getCanonicalName().split("\\.")[model.getClass().getCanonicalName().split("\\.").length - 1]) {
            case "Domain":
                drawDomain(batch,parentAlpha);
                break;
            case "LuxuryTaxe":
                drawLuxuryTaxe(batch,parentAlpha);
                break;
        }
    }

    private void drawLuxuryTaxe(Batch batch, float parentAlpha) {
    }

    private void drawDomain(Batch batch, float parentAlpha) {
        BackgroudColoredActor bgcolor = new BackgroudColoredActor(this.getWidth(),10, Color.RED,shapeDrawer);
        bgcolor.setPosition(this.getX(),this.getHeight());
        bgcolor.draw(batch,parentAlpha);
        shapeDrawer.filledRectangle(new Rectangle(getX(),getY(),getWidth(),getHeight()),Color.WHITE);
        shapeDrawer.setDefaultLineWidth(5f);
        shapeDrawer.rectangle(new Rectangle(getX(),getY(),getWidth(),getHeight()),Color.BLACK);
        System.out.println("Drawing DOMAIN");
    }
}
