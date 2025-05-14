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
    private BitmapFont font;
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
        font = main.font;
        BG = new Texture("background1.png");

        btnPLAY = new Button(font, "PLAY",375, 1050);
        btnABOUT = new Button(font, "ABOUT",375, 850);
        btnEXIT = new Button(font, "EXIT",375, 650);
        btnLEADERBOARD = new Button(font, "LEADERBOARD",375, 750);
        btnSETTINGS = new Button(font, "SETTINGS",375, 950);
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
            if(btnSETTINGS.hit(touch.x, touch.y)){
                main.setScreen(main.screenSettings);
            }
            if(btnABOUT.hit(touch.x, touch.y)){
                main.setScreen(main.screenAbout);
            }
            if(btnLEADERBOARD.hit(touch.x, touch.y)){
                    main.setScreen(main.screenLeaderBoard);
            }
            if(btnEXIT.hit(touch.x, touch.y)){
                Gdx.app.exit();
            }
        }
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(BG, 0, 0, SCR_WIDTH, SCR_HEIGHT);
        font.draw(batch,"MENU", 375, 1200);
        btnPLAY.font.draw(batch, btnPLAY.text, btnPLAY.x, btnPLAY.y );
        btnSETTINGS.font.draw(batch, btnSETTINGS.text, btnSETTINGS.x, btnSETTINGS.y );
        btnABOUT.font.draw(batch, btnABOUT.text, btnABOUT.x, btnABOUT.y );
        btnLEADERBOARD.font.draw(batch, btnLEADERBOARD.text, btnLEADERBOARD.x, btnLEADERBOARD.y );
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
