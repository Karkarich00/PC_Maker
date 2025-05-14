package ru.karkarixnow;

import static ru.karkarixnow.Main.CPU;
import static ru.karkarixnow.Main.SCR_HEIGHT;
import static ru.karkarixnow.Main.SCR_WIDTH;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.sun.tools.javac.jvm.Items;
import java.util.ArrayList;

public class ScreenCPU implements Screen {
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Vector3 touch;
    private BitmapFont font;
    private Main main;
    Texture BG;
    Button btnBACK;
    Button btnNEXT;
    ArrayList<Comp> comps = new ArrayList<>();

    public ScreenCPU(Main main) {
        this.main = main;
        batch = main.batch;
        camera = main.camera;
        touch = main.touch;
        font = main.font;
        BG = new Texture("background2.png");
        btnBACK = new Button(font, "BACK", 100, 100);
        btnNEXT = new Button(font, "NEXT", 700, 100);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        if(Gdx.input.justTouched()){
            touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touch);

            if(btnBACK.hit(touch.x, touch.y)){
                main.setScreen(main.screenGame);
            }
        }
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(BG, 0, 0, SCR_WIDTH, SCR_HEIGHT);
        font.draw(batch,"CPU", SCR_WIDTH/2-20, SCR_HEIGHT);
        btnBACK.font.draw(batch, btnBACK.text, btnBACK.x, btnBACK.y );
        batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
