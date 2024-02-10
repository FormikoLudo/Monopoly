package fr.formiko.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import fr.formiko.monopoly.MyButton;

import java.util.Locale;
import java.util.ResourceBundle;

public final class WidgetsFactory {
    private static Skin mySkin  = null;
    private static BitmapFont WIDGET_FONT;
    private static BitmapFont H1_FONT;
    private static BitmapFont H2_FONT;
    private static BitmapFont TEXT_FONT;

    public static Locale LANGUAGE = Locale.of("fr","FR");
    public static ResourceBundle LABELS = ResourceBundle.getBundle("languages/translation",LANGUAGE);
    private static final String DEFAULT_STYLE = "default";
    private static final String H1_STYLE = "h1";
    private static final String H2_STYLE = "h2";
    private static final String WIDGET_STYLE = "widget";

    private WidgetsFactory(){}



    private static void prepareSkin(){
        TEXT_FONT = Fonts.getRegularFont(20.f);
        WIDGET_FONT = Fonts.getRegularFont(40.f);
        H1_FONT = Fonts.getBoldFont(40.f);
        H2_FONT = Fonts.getBoldFont(30.f);
        mySkin =  new Skin(Gdx.files.internal("ui/uiskin.json"));

        mySkin.add(DEFAULT_STYLE, TEXT_FONT);
        mySkin.add(WIDGET_STYLE, WIDGET_FONT);
        mySkin.add(H1_STYLE,H1_FONT);
        mySkin.add(H2_STYLE,H2_FONT);

        Label.LabelStyle defLabelStyle = new Label.LabelStyle(mySkin.get(Label.LabelStyle.class));
        defLabelStyle.font = mySkin.getFont(H1_STYLE);
        mySkin.add(H1_STYLE,defLabelStyle);

        defLabelStyle = new Label.LabelStyle(mySkin.get(Label.LabelStyle.class));
        defLabelStyle.font = mySkin.getFont(H2_STYLE);
        mySkin.add(H2_STYLE,defLabelStyle);

        defLabelStyle = new Label.LabelStyle(mySkin.get(Label.LabelStyle.class));
        defLabelStyle.font = mySkin.getFont(DEFAULT_STYLE);
        mySkin.add(DEFAULT_STYLE,defLabelStyle);



        TextButton.TextButtonStyle textButtonStyle = mySkin.get(TextButton.TextButtonStyle.class);
        textButtonStyle.font = mySkin.getFont(WIDGET_STYLE);
        mySkin.add(WIDGET_STYLE, textButtonStyle);
    }
    public static MyButton getButton(String label, String key, Runnable action) {
        if (mySkin == null)
            prepareSkin();
        MyButton res = new MyButton(LABELS.getString(label), mySkin, key);
        res.pad(20);
        res.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                action.run();
            }
        });
        return res;
    }

    /**
     *
     * @param title same convention as HTML most important is h1
     * @return a label with the given text and the appropriate size.
     */
    public static Label getTile(String text, TextSize title) {
        if (mySkin == null)
            prepareSkin();
        String style = switch (title) {
            case H1 -> H1_STYLE;
            case H2 -> H2_STYLE;
            case P -> DEFAULT_STYLE;
        };
       return new Label(text, mySkin, style);
    }
}
