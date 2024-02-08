package fr.formiko.monopoly;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import java.time.format.ResolverStyle;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

/** First screen of the application. Displayed after the application is created. */
public class FirstScreen implements Screen {
    private Stage stg;
    private static TextButton currentlySelected;
    private Monopoly game;
    private List<String> buttonLabels;

    private Skin skin  = new Skin(Gdx.files.internal("ui/uiskin.json"));


    public FirstScreen(Monopoly monopoly) {
        this.game = monopoly;
        this.buttonLabels = List.of(Monopoly.LABELS.getString("playbtn"),Monopoly.LABELS.getString("helpbtn"),Monopoly.LABELS.getString("quitbtn") );
    }

    @Override
    public void show() {
        String DEFAULT_STYLE = "default";
        skin.add(DEFAULT_STYLE, Monopoly.font);

        // Configure a TextButtonStyle and name it DEFAULT_STYLE. Skin resources are stored by type, so this doesn't overwrite the font.
        TextButton.TextButtonStyle textButtonStyle = skin.get(TextButton.TextButtonStyle.class);
        textButtonStyle.font = skin.getFont(DEFAULT_STYLE);
        skin.add(DEFAULT_STYLE, textButtonStyle);
        VerticalGroup vg = new VerticalGroup().fill();
        vg.setX(Gdx.graphics.getWidth()/2.f, Align.center);
        vg.setY(Gdx.graphics.getHeight()/2.f, Align.center);
        stg = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stg);

        int i = 0;
        // Add buttons to the layout
        for (String label: buttonLabels) {
            TextButton b = new TextButton(label, skin);

            //b.setFillParent(true);
            vg.addActor(b);
            vg.space(50);
            b.addListener(new ClickListener(){
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    super.clicked(event, x, y);
                    game.setScreen(new HelpScreen(game));
                }
            });
            b.pad(20);
            if(label.equals(Monopoly.LABELS.getString("playbtn"))){
                setSelected(b);


            }
            i++;
        }
        vg.center();
        vg.setColor(Color.RED);
        stg.addActor(vg);

        // Add the layout to the stage

    }

    @Override
    public void render(float delta) {
        handleInput();
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
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

    }
}
