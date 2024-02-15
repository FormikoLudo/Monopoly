package fr.formiko.monopoly;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import fr.formiko.model.field.Card;
import fr.formiko.model.field.Domain;
import fr.formiko.model.field.FieldElement;
import fr.formiko.model.field.LuxuryTaxe;
import fr.formiko.utils.Fonts;
import fr.formiko.utils.Utils;
import fr.formiko.utils.WidgetsFactory;
import space.earlygrey.shapedrawer.ShapeDrawer;

import java.awt.*;

public class CardView extends Group {
    public static final BitmapFont FONT = Fonts.getBoldFont(20f,Color.WHITE);
    public static final BitmapFont REGULAR_FONT = Fonts.getRegularFont(20f, Color.WHITE);
    public static final BitmapFont PROPERTY_NAME_FONT = Fonts.getBoldFont(20f, Color.WHITE);

    private final ShapeDrawer shapeDrawer;
    FieldElement model;
    public CardView(FieldElement model, ShapeDrawer shapeDrawer){
        this.model = model;
        this.setSize(270,380);
        this.shapeDrawer = shapeDrawer;
        addRotatedLabel();
    }
    private void addRotatedLabel () {
        RotatedText rt = new RotatedText();
        rt.setPosition(getX(),getY());
        rt.setRotation(45);
        addActor(rt);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        switch (Utils.getClassBaseName(model)) {
            case "Domain":
            System.out.println("Before calling draw domain");
            drawMortgagedView(batch,parentAlpha);
            //drawDomain(batch,parentAlpha);
            break;
            case "LuxuryTaxe":
            drawLuxuryTaxe(batch,parentAlpha);
            break;
        }
        super.draw(batch, parentAlpha);
    }

    private void drawLuxuryTaxe(Batch batch, float parentAlpha) {
    }

    private void drawDomain(Batch batch, float parentAlpha) {
        System.out.println("Draw DO VIEW");
        shapeDrawer.filledRectangle(getX(),getY(),getWidth(),getHeight(),Color.WHITE);
        shapeDrawer.setDefaultLineWidth(2f);
        shapeDrawer.rectangle(new Rectangle(getX() + 10f,getY() + 10f,getWidth() * .9f,getHeight() * .9f),Color.BLACK);
        BackgroudColoredActor bgactor = new BackgroudColoredActor(getWidth() * .9f, getHeight() * .2f, Color.BLUE, shapeDrawer);
        bgactor.setPosition(getX() + 10f,getY() + .75f * getHeight());
        bgactor.draw(batch,parentAlpha);

        FONT.draw(batch, "TITRE DE PROPRIÉTÉ",getX() + 15f,getY() + .9f * getHeight());
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
        REGULAR_FONT.draw(batch, "HYPOTHEQUE",getX(),getY());
        // Obtenir le glyph du caractère
        BitmapFont.Glyph glyph = REGULAR_FONT.getData().getGlyph('A');

// Obtenir la région du glyph
        TextureRegion textRegion = new TextureRegion(REGULAR_FONT.getRegion());
        batch.draw(textRegion, getX(), getY(), getX(), getY(), getWidth(), getHeight(), 1.f, 1.f, 45);


    }

    private void drawMortgagedView(Batch batch, float parentAlpha) {
        switch (Utils.getClassBaseName(model)) {
            case "Domain":
                drawDomain(batch,parentAlpha);
                System.out.println("Draw MORTGAGED VIEW");
              break;
            case "LuxuryTaxe":
                break;
        }
        shapeDrawer.setColor(Color.RED);

        shapeDrawer.line(
            new Vector2(getX() + 10f, getY() + .6f * getHeight()),
            new Vector2(getX() + .93f * getWidth(),
                getY() + .4f * getHeight())
        );

        // WidgetsFactory.drawRotatedText(REGULAR_FONT,"MORGAGED",getX(),getY(),batch,parentAlpha);

        shapeDrawer.line(new Vector2(getX() + 10f, getY() + .4f * getHeight()),new Vector2(getX() + .9f * getWidth(), getY() + .2f * getHeight()));
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
