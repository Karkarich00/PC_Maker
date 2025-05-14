package ru.karkarixnow;

import static ru.karkarixnow.Main.SCR_HEIGHT;
import static ru.karkarixnow.Main.SCR_WIDTH;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public class ScreenSettings implements Screen {
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Vector3 touch;
    private BitmapFont font;
    private Main main;

    Texture BG;
    Button btnBACK;

    public ScreenSettings(Main main) {
        this.main = main;
        batch = main.batch;
        camera = main.camera;
        touch = main.touch;
        font = main.font;
        BG = new Texture("background2.png");

        btnBACK = new Button(font, "BACK", 250, 400);
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
                main.setScreen(main.screenMenu);
            }
        }
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(BG, 0, 0, SCR_WIDTH, SCR_HEIGHT);
        font.draw(batch,"SETTINGS", 375, 1200);
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
