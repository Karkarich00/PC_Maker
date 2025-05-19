package ru.karkarixnow;

import static ru.karkarixnow.Main.*;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public class ScreenMenu implements Screen {
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Vector3 touch;
    private BitmapFont font3;
    private Main main;

    Texture BG;
    Button btnPLAY;
    Button btnSETTINGS;
    Button btnABOUT;
    Button btnEXIT;
    Button btnLEADERBOARD;

    public ScreenMenu(Main main) {
        this.main = main;
        batch = main.batch;
        camera = main.camera;
        touch = main.touch;
        font3 = new BitmapFont(Gdx.files.internal("lastone.fnt"));
        BG = new Texture("Menu.png");

        btnPLAY = new Button(font3, "START",350, 1050);
        btnEXIT = new Button(font3, "EXIT",380, 900);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        if(Gdx.input.justTouched()){
            touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touch);

            if(btnPLAY.hit(touch.x, touch.y)){
                main.setScreen(main.screenGame);
            }
            if(btnEXIT.hit(touch.x, touch.y)){
                Gdx.app.exit();
            }
        }
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(BG, 0, 0, SCR_WIDTH, SCR_HEIGHT);
        font3.draw(batch,"MENU", 350, 1200);
        btnPLAY.font.draw(batch, btnPLAY.text, btnPLAY.x, btnPLAY.y );
        btnEXIT.font.draw(batch, btnEXIT.text, btnEXIT.x, btnEXIT.y );
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
