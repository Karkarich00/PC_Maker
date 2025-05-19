package ru.karkarixnow;

import static ru.karkarixnow.Main.CPU;
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

import java.util.ArrayList;
import java.util.Arrays;

public class ScreenCPU implements Screen {
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
    ArrayList<Comp> cpu = new ArrayList<>();
    public ScreenCPU(Main main){
        this.main = main;
        batch = main.batch;
        camera = main.camera;
        touch = main.touch;
        font = main.font;
        font1 = main.font1;
        font2 = main.font2;
        BG = new Texture("background2.png");
        btnNEXT = new Button(font, "NEXT", 700, 100);
        btnBACK = new Button(font, "BACK", 100, 100);
        cpu.add(new Comp("AMD Ryzen 5 7600X", 19000, 3.5, CPU , font1));
        cpu.get(cpu.size()-1).sovmest.addAll(Arrays.asList("AMD7"));
        cpu.add(new Comp("AMD Ryzen 7 7800X3D", 40500, 5, CPU , font1));
        cpu.get(cpu.size()-1).sovmest.addAll(Arrays.asList("AMD7"));
        cpu.add(new Comp("AMD Ryzen 9 7950X", 46000, 5, CPU, font1));
        cpu.get(cpu.size()-1).sovmest.addAll(Arrays.asList("AMD7"));

        cpu.add(new Comp("Intel Core i5-13600K", 23000, 4, CPU, font1));
        cpu.get(cpu.size()-1).sovmest.addAll(Arrays.asList("INTEL"));
        cpu.add(new Comp("Intel Core i7-14700K", 37000, 4.5, CPU, font1));
        cpu.get(cpu.size()-1).sovmest.addAll(Arrays.asList("INTEL"));
        cpu.add(new Comp("Intel Core i9-14900K", 45500, 4, CPU, font1));
        cpu.get(cpu.size()-1).sovmest.addAll(Arrays.asList("INTEL"));

        cpu.add(new Comp("AMD Ryzen 5 5600X", 10000, 3, CPU, font1));
        cpu.get(cpu.size()-1).sovmest.addAll(Arrays.asList("AMD5"));
        cpu.add(new Comp("AMD Ryzen 7 5800X3D", 44000, 4.5, CPU, font1));
        cpu.get(cpu.size()-1).sovmest.addAll(Arrays.asList("AMD5"));
        cpu.add(new Comp("AMD Ryzen 9 5950X", 32000, 4.5, CPU, font1));
        cpu.get(cpu.size()-1).sovmest.addAll(Arrays.asList("AMD5"));
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
                main.setScreen(main.screenMotherboard);
            }
            if(btnNEXT.hit(touch.x, touch.y)){
                for(int i = 0; i < cpu.size(); i++){
                    if(cpu.get(i).light == 1 && cpu.get(i).sovmest.get(0) == lastcomp.get(1).sovmest.get(0)){
                        lastcomp.add(cpu.get(i));
                        main.setScreen(main.screenRAM);
                    }
                }
            }
            for(int i = 0; i<cpu.size(); i++){
                if(cpu.get(i).hit(touch.x, touch.y)){
                    for(int j = 0; j < cpu.size(); j++){
                        if(cpu.get(j).light == 1){
                            cpu.get(j).light = 0;
                        }
                    }
                    cpu.get(i).light = 1;
                }
            }

        }
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(BG, 0, 0, SCR_WIDTH, SCR_HEIGHT);
        for(int i = 0; i< cpu.size(); i++){
            font.draw(batch, i+1+" ", 50, 1500-i*150);
            cpu.get(i).x = 100;
            cpu.get(i).y = 1500-i*150;
            if(cpu.get(i).light == 0){
                font1.draw(batch, cpu.get(i).name, cpu.get(i).x, cpu.get(i).y );
            }
            else{
                font2.draw(batch, cpu.get(i).name, cpu.get(i).x, cpu.get(i).y );
            }
        }
        btnBACK.font.draw(batch, btnBACK.text, btnBACK.x, btnBACK.y );
        btnNEXT.font.draw(batch, btnNEXT.text, btnNEXT.x, btnNEXT.y);
        font.draw(batch, "CPU", SCR_WIDTH/2-20, SCR_HEIGHT);
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
