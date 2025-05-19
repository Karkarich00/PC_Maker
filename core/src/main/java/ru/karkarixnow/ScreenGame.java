package ru.karkarixnow;

import static ru.karkarixnow.Main.GPU;
import static ru.karkarixnow.Main.SCR_HEIGHT;
import static ru.karkarixnow.Main.SCR_WIDTH;
import static ru.karkarixnow.Main.lastcomp;

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
    private BitmapFont font1;
    private BitmapFont font2;
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
        font1 = main.font1;
        font2 = main.font2;
        BG = new Texture("Game.png");
        btnNEXT = new Button(font, "NEXT", 700, 100);
        btnBACK = new Button(font, "BACK", 50, 100);
        comps.add(new Comp("NVIDIA RTX 4090", 239000, 5,GPU, font1));
        comps.add(new Comp("RTX 4070 SUPER", 62000, 4,GPU, font1));
        comps.add(new Comp("AMD RX 7800 XT", 56000, 4, GPU, font1));
        comps.add(new Comp("AMD RX 7700 XT", 44000, 3.5, GPU, font1));
        comps.add(new Comp("AMD RX 7600", 27000, 2.5, GPU, font1));
        comps.add(new Comp("RTX 3060 Ti", 30500, 3, GPU, font1));
        comps.add(new Comp("RTX 4080", 120000, 4.5, GPU, font1));
        comps.add(new Comp("RX 6700 XT", 64000, 3, GPU, font1));
        comps.add(new Comp("RTX 4060 CORE", 32000, 3.5, GPU, font1));
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
                for(int i = 0; i < comps.size(); i++){
                    if(comps.get(i).light == 1){
                        comps.get(i).light = 0;
                    }
                }
                main.setScreen(main.screenMenu);
            }
            if(btnNEXT.hit(touch.x, touch.y)){
                for(int i = 0; i < comps.size(); i++){
                    if(comps.get(i).light == 1){
                        lastcomp.add(comps.get(i));
                        main.setScreen(main.screenMotherboard);
                    }
                }
            }
            for(int i = 0; i<comps.size(); i++){
                if(comps.get(i).hit(touch.x, touch.y)){
                    for(int j = 0; j < comps.size(); j++){
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
            font.draw(batch, i+1+" ", 10, 1500-i*150);
            comps.get(i).x = 60;
            comps.get(i).y = 1500-i*150;
            if(comps.get(i).light == 0){
                font1.draw(batch, comps.get(i).name, comps.get(i).x, comps.get(i).y );
            }
            else{
                font2.draw(batch, comps.get(i).name, comps.get(i).x, comps.get(i).y );
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
