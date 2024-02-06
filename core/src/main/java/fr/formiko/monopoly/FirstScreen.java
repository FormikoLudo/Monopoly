package fr.formiko.monopoly;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import java.util.List;

/** First screen of the application. Displayed after the application is created. */
public class FirstScreen implements Screen {
    private Stage stg;
    private Monopoly game;
    private List<String> buttonLabels;

    private VerticalGroup layout;
    private Skin skin  = new Skin(Gdx.files.internal("ui/uiskin.json"));

    public FirstScreen(Monopoly monopoly) {
        this.game = monopoly;
        this.buttonLabels = List.of("Play","Help", "Quit");

    }

    @Override
    public void show() {
        stg = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stg);

        int i = 0;
        // Add buttons to the layout
        for (String label: buttonLabels.reversed()) {
            TextButton b = new TextButton(label, skin);
            b.setX(600);
            b.setY(150 * i + 300);
            b.setSize(300,100);
            b.addListener(new ClickListener(){
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    super.clicked(event, x, y);
                    System.exit(0);
                }
            });
            stg.addActor(b);
            i++;
        }

        // Add the layout to the stage

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stg.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stg.draw();
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
}
