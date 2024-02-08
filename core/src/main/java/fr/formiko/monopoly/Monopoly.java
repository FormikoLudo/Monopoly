package fr.formiko.monopoly;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import fr.formiko.utils.Fonts;

import java.util.Locale;
import java.util.ResourceBundle;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Monopoly extends Game {
    SpriteBatch batch;
    ShapeRenderer shapeRenderer;

    public static Locale LANGUAGE = new Locale("fr","FR");
    public static ResourceBundle LABELS = ResourceBundle.getBundle("languages/translation",LANGUAGE);
    public static   BitmapFont font;
    @Override
    public void create() {

        font  = Fonts.getDefaultFont(40.f);
       this.setScreen(new FirstScreen(this));
        System.out.println(font);
    }
}
