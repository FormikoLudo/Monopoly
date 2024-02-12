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
        this.setSize(150,210);
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
        BackgroudColoredActor bgcolor = new BackgroudColoredActor(this.getWidth(),getHeight()/8, Color.RED,shapeDrawer);
        bgcolor.setPosition(this.getX(),this.getHeight());
        bgcolor.draw(batch,parentAlpha);
        shapeDrawer.filledRectangle(new Rectangle(getX(),getY(),getWidth(),getHeight()),Color.WHITE);
        shapeDrawer.setDefaultLineWidth(5f);
        shapeDrawer.rectangle(new Rectangle(getX(),getY(),getWidth(),getHeight()),Color.BLACK);

        drawHouse(batch,parentAlpha,getX(),getY()+120,false);

        drawHouse(batch,parentAlpha,getX(),getY()+90,false);
        drawHouse(batch,parentAlpha,getX() + 20,getY()+90,false);

        drawHouse(batch,parentAlpha,getX(),getY()+60,false);
        drawHouse(batch,parentAlpha,getX() + 20,getY()+60,false);
        drawHouse(batch,parentAlpha,getX() + 40,getY()+60,false);


        drawHouse(batch,parentAlpha,getX(),getY() + 30,false);
        drawHouse(batch,parentAlpha,getX() + 20,getY() + 30,false);
        drawHouse(batch,parentAlpha,getX() + 40,getY() + 30,false);
        drawHouse(batch,parentAlpha,getX() + 60,getY() + 30,false);

        drawHouse(batch,parentAlpha,getX(),getY(),true);

        System.out.println("Drawing DOMAIN");
    }

    public void drawHouse(Batch batch, float parentAlpha, float x, float y, boolean hotel) {

        Color color;
        if (hotel)
            color = Color.RED;
        else
            color = Color.GREEN;

        shapeDrawer.filledRectangle(x + 10,y + 10,10,10,color);
        float [] a = {x + 10, y + 20};
        float [] b = {x + 10 + 10, y + 20};
        float [] c = {x + 15 , y + 30};
        shapeDrawer.triangle(a[0],a[1],b[0],b[1],c[0],c[1],color);
    }
}
