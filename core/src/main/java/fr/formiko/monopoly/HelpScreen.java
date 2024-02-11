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
import fr.formiko.utils.Finals;
import fr.formiko.utils.TextSize;
import fr.formiko.utils.WidgetsFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** First screen of the application. Displayed after the application is created. */
public class HelpScreen implements Screen {
    private Stage stg;
    private Monopoly game;
    private static final float PERCENTAGE_OF_SCREEN_USED = 0.9f;

    private Skin skin  = new Skin(Gdx.files.internal("ui/uiskin.json"));
    private List<String> helpLabels;
    private List<TextSize> textSizes;

    public HelpScreen(Monopoly monopoly) {
        this.game = monopoly;
        this.helpLabels = List.of(Finals.HELP_BTN_LABEL,Finals.HELP_PLAY_LABEL,Finals.HELP_HOW_TO_WIN,Finals.HELP_HOW_TO_WIN_PAR,
                                  Finals.HELP_WHO_STARTS_LABEL,Finals.HELP_WHO_STARTS_PAR,Finals.HELP_YOUR_TURN,Finals.HELP_ROLL_DICES,
                                  Finals.HELP_MOVE_YOUR_PAWN,Finals.HELP_WHERE_ARE_YOU);
        this.textSizes = List.of(TextSize.H1,TextSize.H1,TextSize.H2,TextSize.P,TextSize.H2,TextSize.P,TextSize.H2,TextSize.P,TextSize.P,TextSize.P);

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
        for (int i = 0; i < helpLabels.size(); i++) {
            WidgetsFactory.prepareLabelAndAddToTable(helpLabels.get(i),textSizes.get(i),content,PERCENTAGE_OF_SCREEN_USED);

        }
        WidgetsFactory.prepareLabelAndAddToTable(Finals.HELP_ROLLED_A_DOUBLE,TextSize.EM,content,PERCENTAGE_OF_SCREEN_USED);
        WidgetsFactory.prepareLabelAndAddToTable(Finals.HELP_ROLLED_A_DOUBLE_PAR,TextSize.P,content,PERCENTAGE_OF_SCREEN_USED);
        WidgetsFactory.prepareLabelAndAddToTable(Finals.HELP_PAY_ATTENTION,TextSize.EM,content,PERCENTAGE_OF_SCREEN_USED);
        WidgetsFactory.prepareLabelAndAddToTable(Finals.HELP_THREE_DOUBLES_LEAD_TO_JAIL,TextSize.P,content,PERCENTAGE_OF_SCREEN_USED);

        WidgetsFactory.prepareLabelAndAddToTable(Finals.HELP_FINISHED_TURN,TextSize.P,content,PERCENTAGE_OF_SCREEN_USED);

        WidgetsFactory.prepareLabelAndAddToTable(Finals.HELP_START_TO_PLAY,TextSize.H2,content,PERCENTAGE_OF_SCREEN_USED);
        WidgetsFactory.prepareLabelAndAddToTable(Finals.HELP_START_TO_PLAY_PAR, TextSize.P,content,PERCENTAGE_OF_SCREEN_USED);

        WidgetsFactory.prepareLabelAndAddToTable(Finals.HELP_GAME_FIELD_CELLS,TextSize.H1,content,PERCENTAGE_OF_SCREEN_USED);
        WidgetsFactory.prepareLabelAndAddToTable(Finals.HELP_PROPERTIES,TextSize.H2,content,PERCENTAGE_OF_SCREEN_USED);
        WidgetsFactory.prepareLabelAndAddToTable(Finals.HELP_PROPERTIES_PAR,TextSize.P,content,PERCENTAGE_OF_SCREEN_USED);
        WidgetsFactory.prepareLabelAndAddToTable(Finals.HELP_PROPERTIES_DONT_BELONG_TO_ANYBODY,TextSize.H2,content,PERCENTAGE_OF_SCREEN_USED);
        WidgetsFactory.prepareLabelAndAddToTable(Finals.HELP_PROPERTIES_DONT_BELONG_TO_ANYBODY_PAR,TextSize.P,content,PERCENTAGE_OF_SCREEN_USED);
        WidgetsFactory.prepareLabelAndAddToTable(Finals.HELP_YOU_WANT_TO_BUY_IT,TextSize.EM,content,PERCENTAGE_OF_SCREEN_USED);
        WidgetsFactory.prepareLabelAndAddToTable(Finals.HELP_YOU_WANT_TO_BUY_IT_PAR,TextSize.P,content,PERCENTAGE_OF_SCREEN_USED);
        WidgetsFactory.prepareLabelAndAddToTable(Finals.HELP_YOU_DONT_WANT_TO_BUY_IT,TextSize.EM,content,PERCENTAGE_OF_SCREEN_USED);
        WidgetsFactory.prepareLabelAndAddToTable(Finals.HELP_YOU_DONT_WANT_TO_BUY_IT_PAR,TextSize.P,content,PERCENTAGE_OF_SCREEN_USED);
        WidgetsFactory.prepareLabelAndAddToTable(Finals.HELP_COLLECT_COLOR_GROUPS,TextSize.EM,content,PERCENTAGE_OF_SCREEN_USED);
        Table colorTable = colorTable();

        content.add(colorTable);
        content.row();
        //WidgetsFactory.prepareLabelAndAddToTable(Finals.HELP_COLLECT_COLOR_GROUPS_FIRST_PAR,TextSize.EM,content,PERCENTAGE_OF_SCREEN_USED);
        WidgetsFactory.prepareLabelAndAddToTable(Finals.HELP_ACTION_CELLS,TextSize.H2,content,PERCENTAGE_OF_SCREEN_USED);

        WidgetsFactory.prepareLabelAndAddToTable(Finals.HELP_BUILDINGS,TextSize.H1,content,PERCENTAGE_OF_SCREEN_USED);
        WidgetsFactory.prepareLabelAndAddToTable(Finals.HELP_I_CAN_NOT_PAY_ANYMORE,TextSize.H1,content,PERCENTAGE_OF_SCREEN_USED);
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
        if (Gdx.input.isKeyJustPressed(Input.Keys.UP)){}
        else if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)){

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
}
