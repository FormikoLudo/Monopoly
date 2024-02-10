package fr.formiko.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import fr.formiko.monopoly.Monopoly;
import fr.formiko.monopoly.MyButton;

import java.util.Locale;
import java.util.ResourceBundle;

public final class WidgetsFactory {
    private static Skin mySkin  = null;
    private static final BitmapFont font = Fonts.getDefaultFont(40.f);
    public static Locale LANGUAGE = Locale.of("fr","FR");
    public static ResourceBundle LABELS = ResourceBundle.getBundle("languages/translation",LANGUAGE);

    private WidgetsFactory(){}

    private static void prepareSkin(){
        mySkin =  new Skin(Gdx.files.internal("ui/uiskin.json"));
        String DEFAULT_STYLE = "default";
        mySkin.add(DEFAULT_STYLE, font);
        TextButton.TextButtonStyle textButtonStyle = mySkin.get(TextButton.TextButtonStyle.class);
        textButtonStyle.font = mySkin.getFont(DEFAULT_STYLE);
        mySkin.add(DEFAULT_STYLE, textButtonStyle);
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
}
