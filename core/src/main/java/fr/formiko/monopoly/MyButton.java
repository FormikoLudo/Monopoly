package fr.formiko.monopoly;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class MyButton extends TextButton {
    private final String key;

    public MyButton(String text, Skin skin, String key) {
        super(text, skin);
        this.key = key;
    }

    public String getKey() {
        return this.key;
    }
}
