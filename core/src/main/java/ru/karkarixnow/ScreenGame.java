package ru.karkarixnow;

import static ru.karkarixnow.Main.GPU;
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

public class ScreenGame implements Screen {
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Vector3 touch;
    private BitmapFont font;
    private Main main;
    Texture BG;
    Button btnBACK;
    Button btnNEXT;
    ArrayList<Comp> comps = new ArrayList<>();

    public ScreenGame(Main main) {
        this.main = main;
        batch = main.batch;
        camera = main.camera;
        touch = main.touch;
        font = main.font;
        BG = new Texture("background2.png");
        btnNEXT = new Button(font, "NEXT", 700, 100);
        btnBACK = new Button(font, "BACK", 100, 100);
        comps.add(new Comp("NVIDIA RTX 4090", 239000, 4,GPU));
        comps.add(new Comp("RTX 4070 SUPER", 62000, 4,GPU));
        comps.add(new Comp("AMD RX 7800 XT ", 56000, 3, GPU));
        comps.add(new Comp("AMD RX 7700 XT", 44000, 2, GPU));
        comps.add(new Comp("AMD RX 7600", 27000, 2, GPU));

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
            if(btnNEXT.hit(touch.x, touch.y)){
                main.setScreen(main.screenCPU);
            }
            for(int i = 0; i<comps.size(); i++){
                if(comps.get(i).hit(touch.x, touch.y)){
                    for(int j = 0; j < comps.size(); i++){
                        if(comps.get(j).light == 1){
                            comps.get(j).light = 0;
                        }
                    }
                    comps.get(i).light = 1;
                }
            }

        }
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(BG, 0, 0, SCR_WIDTH, SCR_HEIGHT);
        for(int i = 0; i< comps.size(); i++){
            font.draw(batch, i+1+" ", 140, 1500-i*300);
            comps.get(i).x = 200;
            comps.get(i).y = 1500-i*300;
            if(comps.get(i).light == 0){
                comps.get(i).font1.draw(batch, comps.get(i).name, comps.get(i).x, comps.get(i).y );
            }
            else{
                comps.get(i).font2.draw(batch, comps.get(i).name, comps.get(i).x, comps.get(i).y );
            }
        }
        btnBACK.font.draw(batch, btnBACK.text, btnBACK.x, btnBACK.y );
        btnNEXT.font.draw(batch, btnNEXT.text, btnNEXT.x, btnNEXT.y);
        font.draw(batch, "GPU", SCR_WIDTH/2-20, SCR_HEIGHT);
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
