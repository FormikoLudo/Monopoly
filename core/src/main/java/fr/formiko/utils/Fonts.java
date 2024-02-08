package fr.formiko.utils;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import java.nio.file.Files;

/**
 * {@summary Class that provide a way to load font.}
 *
 * @author Hydrolien
 * @version 2.4
 * @since 2.4
 */
public class Fonts extends BitmapFont {
    public static final String DEFAULT_COLOR = "[#000000]";


    /**
     * {@summary Load the default font.}
     *
     * @param fontSize size of the font.
     * @return the default font.
     */
    public static BitmapFont getDefaultFont(float fontSize) {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/Noto_Sans/NotoSans-Regular.ttf"));
        FreeTypeFontParameter parameter = new FreeTypeFontParameter();
        parameter.color = Color.WHITE;
        parameter.size = (int) fontSize;
        parameter.characters = FreeTypeFontGenerator.DEFAULT_CHARS;// FreeTypeFontGenerator.DEFAULT_CHARS + every char in the translation files.
        BitmapFont bmf = generator.generateFont(parameter);
        generator.dispose(); // don't forget to dispose to avoid memory leaks!
        return bmf;
    }
}
