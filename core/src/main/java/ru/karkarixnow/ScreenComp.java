package ru.karkarixnow;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

import java.util.ArrayList;

import static ru.karkarixnow.Main.SCR_HEIGHT;
import static ru.karkarixnow.Main.SCR_WIDTH;
import static ru.karkarixnow.Main.lastcomp;

public class ScreenComp implements Screen {
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Vector3 touch;
    private BitmapFont font;
    private BitmapFont font2;
    private Main main;
    private int summ;
    private double grade;
    Texture BG;
    Button btnBACK;
    Button btnNEXT;

    public ScreenComp(Main main){
        this.main = main;
        batch = main.batch;
        camera = main.camera;
        touch = main.touch;
        font = main.font;
        font2 = main.font2;
        summ = 0;
        grade = 0;
        BG = new Texture("Motherboard.png");
        btnNEXT = new Button(font, "NEXT", SCR_WIDTH/2-60, 100);
    }
    @Override
    public void show() {
        for(int i = 0; i < lastcomp.size(); i++){
            summ += lastcomp.get(i).price;
            grade += lastcomp.get(i).proizv;
        }
        if(grade*10%10==5){
            grade =(int)grade/6+1;
        }else {
            grade = (int)grade/6;
        }
    }
    @Override
    public void render(float delta) {
        if(Gdx.input.justTouched()) {
            touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touch);
            if (btnNEXT.hit(touch.x, touch.y)) {
                main.setScreen(main.screenMenu);
            }
        }
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(BG, 0, 0, SCR_WIDTH, SCR_HEIGHT);
        for(int i = 0; i< lastcomp.size(); i++) {
            font2.draw(batch, "", 10, 1500 - i * 150);
            lastcomp.get(i).x = 60;
            lastcomp.get(i).y = 1500 - i * 200;
            font2.draw(batch, lastcomp.get(i).name+":", lastcomp.get(i).x, lastcomp.get(i).y);
            font2.draw(batch, "RATING: "+lastcomp.get(i).proizv, lastcomp.get(i).x, lastcomp.get(i).y-60);
            font2.draw(batch, "PRICE: "+lastcomp.get(i).price+" RUB", lastcomp.get(i).x, lastcomp.get(i).y-120);
        }
        font2.draw(batch, "TOTAL PRICE: "+summ+" RUB", 60, 200);
        font2.draw(batch, "OVERALL RATING: "+grade, 60, 260);
        btnNEXT.font.draw(batch, btnNEXT.text, btnNEXT.x, btnNEXT.y);
        font.draw(batch, "YOUR PC", SCR_WIDTH/2-150, SCR_HEIGHT);
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
