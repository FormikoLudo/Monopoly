package fr.formiko.monopoly;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Monopoly extends Game {
    SpriteBatch batch;
    ShapeRenderer shapeRenderer;
    BitmapFont font;
    @Override
    public void create() {
       this.setScreen(new FirstScreen(this));
    }
}
