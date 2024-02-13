package fr.formiko.monopoly;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import fr.formiko.model.field.Card;
import fr.formiko.model.field.Domain;
import fr.formiko.model.field.FieldElement;
import fr.formiko.model.field.LuxuryTaxe;
import fr.formiko.utils.Fonts;
import space.earlygrey.shapedrawer.ShapeDrawer;

import java.awt.*;

public class CardView extends Actor {
    public static final BitmapFont FONT = Fonts.getBoldFont(20f);
    public static final BitmapFont REGULAR_FONT = Fonts.getRegularFont(20f);
    public static final BitmapFont PROPERTY_NAME_FONT = Fonts.getBoldFont(20f);

    private ShapeDrawer shapeDrawer;
    FieldElement model;
    public CardView(FieldElement model, ShapeDrawer shapeDrawer){
        this.model = model;
        this.setSize(270,380);
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
        shapeDrawer.filledRectangle(getX(),getY(),getWidth(),getHeight(),Color.WHITE);
        shapeDrawer.setDefaultLineWidth(2f);
        shapeDrawer.rectangle(new Rectangle(getX() + 10f,getY() + 10f,getWidth() * .9f,getHeight() * .9f),Color.BLACK);
        BackgroudColoredActor bgactor = new BackgroudColoredActor(getWidth() * .9f, getHeight() * .2f, Color.BLUE, shapeDrawer);
        bgactor.setPosition(getX() + 10f,getY() + .75f * getHeight());
        bgactor.draw(batch,parentAlpha);

        FONT.draw(batch, "TITRE DE PROPRIÉ",getX() + 15f,getY() + .9f * getHeight());
        REGULAR_FONT.setColor(Color.WHITE);
        PROPERTY_NAME_FONT.draw(batch, model.getName(),getX() + 15f,getY() + .85f * getHeight());
        REGULAR_FONT.setColor(Color.BLACK);
        String [] rows = new String[] {"Loyer","Avec"};
        int j = 0;
        for (float i = .70f; i >= .35f; i -= .05f) {
            REGULAR_FONT.draw(batch, rows[j],getX() + 15f,getY() + i * getHeight());
            if (j == 0)
                j++;
        }
        shapeDrawer.line(getX() + 15f, getY()+.30f *getHeight(),getX()  + 0.9f * this.getWidth(),getY()+.30f *getHeight(),Color.BLACK,2f);
        REGULAR_FONT.draw(batch, "Prix des maisons",getX() + 15f,getY() + .25f * getHeight());
        REGULAR_FONT.draw(batch, "Prix d'1 hôtel",getX() + 15f,getY() + .20f * getHeight());
        REGULAR_FONT.draw(batch, "(plus 4 maisons)",getX() + 15f,getY() + .15f * getHeight());



        //drawHouse(batch,parentAlpha,getX() + 15f,getY() + .4f * getHeight(), false);
        System.out.println("Drawing DOMAIN");

    }

    public void drawHouse(Batch batch, float parentAlpha, float x, float y, boolean hotel) {

        Color color;
        if (hotel)
            color = Color.RED;
        else
            color = Color.GREEN;

        shapeDrawer.filledRectangle(x,y,15,15,color);
        float [] a = {x , y + 15};
        float [] b = {x + 15, y + 15};
        float [] c = {x + 7.5f , y + 30};
        shapeDrawer.triangle(a[0],a[1],b[0],b[1],c[0],c[1],color);
    }
}
