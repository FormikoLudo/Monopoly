package fr.formiko.monopoly;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import fr.formiko.utils.Finals;
import fr.formiko.utils.WidgetsFactory;

import java.util.ArrayList;
import java.util.List;

/** First screen of the application. Displayed after the application is created. */
public class MainMenuScreen implements Screen {
    private Stage stg;
    private static TextButton currentlySelected;
    private Monopoly game;
    private List<String> buttonKeys;
    private List<MyButton> buttons;

    private int selected;


    public MainMenuScreen(Monopoly monopoly) {
        this.game = monopoly;
        this.buttonKeys = List.of(
            Finals.PLAY_BTN_LABEL,
            Finals.HELP_BTN_LABEL,
            Finals.QUIT_BTN_LABEL
        );
        this.buttons = new ArrayList<>();
        this.selected = 0;
    }

    @Override
    public void show() {

        // Configure a TextButtonStyle and name it DEFAULT_STYLE. Skin resources are stored by type, so this doesn't overwrite the font.


        VerticalGroup vg = new VerticalGroup().fill();
        vg.setX(Gdx.graphics.getWidth()/2.f, Align.center);
        vg.setY(Gdx.graphics.getHeight()/2.f, Align.center);

        stg = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stg);

        int i = 0;
        // Add buttons to the layout
        for (String label: buttonKeys) {
            MyButton b = WidgetsFactory.getButton(label,buttonKeys.get(i),getRunnable(buttonKeys.get(i)));
            this.buttons.add(b);
            vg.addActor(b);
            vg.space(50);
            b.pad(20);
            selectButton(0);

            i++;
        }
        vg.center();
        vg.setColor(Color.RED);
        stg.addActor(vg);


    }

    private Runnable getRunnable(String s) {
        return switch (s) {
            case Finals.HELP_BTN_LABEL -> () -> game.setScreen(new HelpScreen(game));
            case Finals.QUIT_BTN_LABEL -> () -> System.exit(0);
            default -> () -> System.exit(0); // Unreachable state.
        };
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
        if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN) || Gdx.input.isKeyJustPressed(Input.Keys.TAB)){
            this.selected = (this.selected + 1) % this.buttons.size();
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.UP)){
            this.selected = (this.selected == 0)? this.buttons.size() - 1 : selected - 1;
        }
        selectButton(Math.abs(selected));
        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            this.buttons.get(Math.abs(selected)).getListeners().forEach(listener -> {
                if (listener instanceof ClickListener) {
                    ((ClickListener) listener).clicked(null, 0, 0);
                }
            });
        }
        System.out.println("Currently selected : " + this.buttons.get(Math.abs(selected)));
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
        stg.dispose();

    }

    private void selectButton(int index) {
        if (index < 0 || index >= buttons.size()) return;

        // Deselect all buttons
        for (TextButton button : buttons) {
            button.getLabel().setColor(1, 1, 1, 1); // White color
        }

        // Select the current button
        buttons.get(index).getLabel().setColor(1, 0, 0, 1); // Red color for the selected button
        selected = index;
    }

    private void getClickListener(String btnLabel) {

    }
}
