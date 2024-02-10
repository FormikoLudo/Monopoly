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


    @Override
    public void create() {
        this.setScreen(new MainMenuScreen(this));
    }
}
