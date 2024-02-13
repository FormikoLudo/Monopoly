package fr.formiko.monopoly;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import fr.formiko.model.field.Domain;
import fr.formiko.model.field.LuxuryTaxe;
import fr.formiko.utils.Finals;
import fr.formiko.utils.TextSize;
import fr.formiko.utils.WidgetsFactory;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** First screen of the application. Displayed after the application is created. */
public class HelpScreen implements Screen {
    private Stage stg;
    private Monopoly game;
    private static final float PERCENTAGE_OF_SCREEN_USED = 0.9f;

    private Skin skin  = new Skin(Gdx.files.internal("ui/uiskin.json"));
    private List<Map.Entry<String,TextSize>> helpLabels;
    private List<TextSize> textSizes;

    public HelpScreen(Monopoly monopoly) {
        this.game = monopoly;
        this.helpLabels = List.of(
            new AbstractMap.SimpleEntry<>(Finals.HELP_BTN_LABEL,TextSize.H1),
            new AbstractMap.SimpleEntry<>(Finals.HELP_PLAY_LABEL,TextSize.H1),
            new AbstractMap.SimpleEntry<>(Finals.HELP_HOW_TO_WIN,TextSize.H2),
            new AbstractMap.SimpleEntry<>(Finals.HELP_HOW_TO_WIN_PAR,TextSize.P),
            new AbstractMap.SimpleEntry<>(Finals.HELP_WHO_STARTS_LABEL,TextSize.H2),
            new AbstractMap.SimpleEntry<>(Finals.HELP_WHO_STARTS_PAR,TextSize.P),
            new AbstractMap.SimpleEntry<>(Finals.HELP_YOUR_TURN,TextSize.H2),
            new AbstractMap.SimpleEntry<>(Finals.HELP_ROLL_DICES,TextSize.P),
            new AbstractMap.SimpleEntry<>(Finals.HELP_MOVE_YOUR_PAWN,TextSize.P),
            new AbstractMap.SimpleEntry<>(Finals.HELP_WHERE_ARE_YOU,TextSize.P),
            new AbstractMap.SimpleEntry<>(Finals.HELP_ROLLED_A_DOUBLE,TextSize.EM),
            new AbstractMap.SimpleEntry<>(Finals.HELP_ROLLED_A_DOUBLE_PAR,TextSize.P),
            new AbstractMap.SimpleEntry<>(Finals.HELP_PAY_ATTENTION,TextSize.EM),
            new AbstractMap.SimpleEntry<>(Finals.HELP_THREE_DOUBLES_LEAD_TO_JAIL,TextSize.P),
            new AbstractMap.SimpleEntry<>(Finals.HELP_FINISHED_TURN,TextSize.P),
            new AbstractMap.SimpleEntry<>(Finals.HELP_START_TO_PLAY,TextSize.H2),
            new AbstractMap.SimpleEntry<>(Finals.HELP_START_TO_PLAY_PAR,TextSize.P),
            new AbstractMap.SimpleEntry<>(Finals.HELP_GAME_FIELD_CELLS,TextSize.H1),
            new AbstractMap.SimpleEntry<>(Finals.HELP_PROPERTIES,TextSize.H2),
            new AbstractMap.SimpleEntry<>(Finals.HELP_PROPERTIES_DONT_BELONG_TO_ANYBODY,TextSize.H2),
            new AbstractMap.SimpleEntry<>(Finals.HELP_YOU_WANT_TO_BUY_IT,TextSize.EM),
            new AbstractMap.SimpleEntry<>(Finals.HELP_YOU_WANT_TO_BUY_IT_PAR,TextSize.P),
            new AbstractMap.SimpleEntry<>(Finals.HELP_YOU_DONT_WANT_TO_BUY_IT,TextSize.EM),
            new AbstractMap.SimpleEntry<>(Finals.HELP_YOU_DONT_WANT_TO_BUY_IT_PAR,TextSize.P),
            new AbstractMap.SimpleEntry<>(Finals.HELP_COLLECT_COLOR_GROUPS,TextSize.EM),
            new AbstractMap.SimpleEntry<>(Finals.HELP_YOU_OWN_ALL_GROUP,TextSize.P),
            new AbstractMap.SimpleEntry<>(Finals.HELP_DOUBLE_RENT,TextSize.P)
        );
    }

    @Override
    public void show() {
        this.stg = new Stage(new ScreenViewport(),WidgetsFactory.getBatch());
        Gdx.input.setInputProcessor(stg);
        Table table = new Table();
        table.setFillParent(true);
        stg.addActor(table);


        // Title
        Label titleLabel = new Label("Help", skin);
        table.add(titleLabel).pad(10);
        table.row();
        Table content = new Table();
        for (AbstractMap.Entry<String,TextSize> e : helpLabels) {
            WidgetsFactory.prepareLabelAndAddToTable(e.getKey(),e.getValue(),content,PERCENTAGE_OF_SCREEN_USED);
            if (e.getKey().equals(Finals.HELP_COLLECT_COLOR_GROUPS)) {
                content.add(colorTable().align(Align.left));
                content.row();
            }
        }
        content.row();
        content.add(new CardView(new Domain("RUE DE LA PAIX",0,0,0,0,0),WidgetsFactory.getShapDrawer()));
        ScrollPane scrollPane = new ScrollPane(content, skin);
        table.add(scrollPane).expand().fill().pad(10).height(Gdx.graphics.getHeight() * 0.7f);
        table.row();

        // Back Button
        MyButton backButton = WidgetsFactory.getButton(Finals.BACK_BTN_LABEL,"key",()->{game.setScreen(new MainMenuScreen(game));});
        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("CLICKED BACK BTN");
                game.setScreen(new MainMenuScreen(game)); // Change to your actual main menu screen
            }

            ;
        });
        table.add(backButton).pad(10);

        table.top(); // Align content to the top of th


    }

    private Table colorTable() {
        Table table = new Table();
        Color colors [] = {Color.BROWN,Color.SKY,Color.PINK, Color.ORANGE, Color.RED, Color.YELLOW, Color.GREEN,Color.BLUE};
        for (Color c : colors) {
            table.add(new BackgroudColoredActor(50,10,c,WidgetsFactory.getShapDrawer())).pad(10);
        }
        return table;
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
