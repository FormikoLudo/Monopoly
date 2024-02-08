package fr.formiko.monopoly;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFontCache;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

/** First screen of the application. Displayed after the application is created. */
public class HelpScreen implements Screen {
    private Stage stg;
    private Monopoly game;

    private Skin skin  = new Skin(Gdx.files.internal("ui/uiskin.json"));

    public HelpScreen(Monopoly monopoly) {
        this.game = monopoly;
        this.stg = new Stage();
    }

    @Override
    public void show() {
        Table table = new Table();
        table.setPosition(0, Gdx.graphics.getHeight());
        table.add(new Label(Monopoly.LABELS.getString("howtoplay"),skin)).row();
        ScrollPane scrl = new ScrollPane(table);
        stg.addActor(scrl);
    }

    @Override
    public void render(float delta) {
        handleInput();
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(143/256f, 188/256f, 114/256f,1);
        stg.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stg.draw();
    }

    private void handleInput() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)){

        }
    }

    @Override
    public void resize(int width, int height) {
        // Resize your screen here. The parameters represent the new window size.
        stg.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {
        // Invoked when your application is paused.
    }

    @Override
    public void resume() {
        // Invoked when your application is resumed after pause.
    }

    @Override
    public void hide() {
        // This method is called when another screen replaces this one.
    }

    @Override
    public void dispose() {
        // Destroy screen's assets here.
    }
    private void setSelected(TextButton b) {
        Drawable hoverDrawable = b.getStyle().over;
        b.setStyle(new Button.ButtonStyle(hoverDrawable,hoverDrawable,hoverDrawable));
    }
}
