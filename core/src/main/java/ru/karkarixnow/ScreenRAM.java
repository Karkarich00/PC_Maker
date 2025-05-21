package ru.karkarixnow;

import static ru.karkarixnow.Main.RAM;
import static ru.karkarixnow.Main.SCR_HEIGHT;
import static ru.karkarixnow.Main.SCR_WIDTH;
import static ru.karkarixnow.Main.lastcomp;
import static ru.karkarixnow.Main.emptystring;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

import java.util.ArrayList;

public class ScreenRAM implements Screen {
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
    ArrayList<Comp> ram = new ArrayList<>();
    public ScreenRAM(Main main){
        this.main = main;
        batch = main.batch;
        camera = main.camera;
        touch = main.touch;
        font = main.font;
        font1 = main.font1;
        font2 = main.font2;
        BG = new Texture("RAM.png");
        btnNEXT = new Button(font, "NEXT", 700, 100);
        btnBACK = new Button(font, "BACK", 50, 100);
        ram.add(new Comp("ADATA XPG Lancer Blade 32GB", 10500, 3.5, RAM, font1));
        ram.get(ram.size()-1).sovmest.add("DDR5-6000");
        ram.add(new Comp("Kingston Fury Beast 32GB", 22000, 3, RAM, font1));
        ram.get(ram.size()-1).sovmest.add("DDR5-6000");
        ram.add(new Comp("G.Skill Trident Z5 32GB", 10500, 4.5, RAM, font1));
        ram.get(ram.size()-1).sovmest.add("DDR5-6000");
        ram.add(new Comp("ADATA XPG Lancer 64GB", 21700, 4, RAM, font1));
        ram.get(ram.size()-1).sovmest.add("DDR5-6000");
        ram.add(new Comp("Acer Predator Pallas II 32GB", 10800, 3.5, RAM, font1));
        ram.get(ram.size()-1).sovmest.add("DDR5-6000");

        ram.add(new Comp("G.Skill Ripjaws V 32GB", 8000, 3, RAM, font1));
        ram.get(ram.size()-1).sovmest.add("DDR4-3600");
        ram.add(new Comp("G.Skill Trident Z 64GB", 14500, 5, RAM, font1));
        ram.get(ram.size()-1).sovmest.add("DDR4-3600");
        ram.add(new Comp("Corsair Vengeance LPX 32GB", 8500, 3, RAM, font1));
        ram.get(ram.size()-1).sovmest.add("DDR4-3600");
        ram.add(new Comp("Fury Black DDR4 32GB", 12000, 2.5, RAM, font1));
        ram.get(ram.size()-1).sovmest.add("DDR4-3600");
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
                lastcomp.remove(lastcomp.size()-1);
                emptystring = "";
                for(int i = 0; i < ram.size(); i++){
                    if(ram.get(i).light == 1){
                        ram.get(i).light = 0;
                    }
                }
                main.setScreen(main.screenCPU);
            }
            if(btnNEXT.hit(touch.x, touch.y)){
                for(int i = 0; i < ram.size(); i++){
                    if(ram.get(i).light == 1){
                        if(lastcomp.get(0).sovmest.get(0) == "AMD7" | lastcomp.get(0).sovmest.get(0) == "AMD5") {
                            if (ram.get(i).sovmest.get(0) == lastcomp.get(0).sovmest.get(1)) {
                                lastcomp.add(ram.get(i));
                                emptystring = "";
                                main.setScreen(main.screenSSD);
                                for(int j = 0; j < ram.size(); j++){
                                    if(ram.get(i).light == 1){
                                        ram.get(i).light = 0;
                                    }
                                }
                            }
                            else{
                                emptystring = "";
                            }
                        }
                        else if (lastcomp.get(0).sovmest.get(0) == "INTEL") {
                            if (ram.get(i).sovmest.get(0) == lastcomp.get(0).sovmest.get(1) | ram.get(i).sovmest.get(0) == lastcomp.get(0).sovmest.get(2)){
                                lastcomp.add(ram.get(i));
                                emptystring = "";
                                main.setScreen(main.screenSSD);
                                for(int j = 0; j < ram.size(); j++){
                                    if(ram.get(i).light == 1){
                                        ram.get(i).light = 0;
                                    }
                                }
                            } else{
                                emptystring = "";
                            }
                        }
                    }
                }
            }
            for(int i = 0; i<ram.size(); i++){
                if(ram.get(i).hit(touch.x, touch.y)){
                    for(int j = 0; j < ram.size(); j++){
                        if(ram.get(j).light == 1){
                            ram.get(j).light = 0;
                        }
                    }
                    ram.get(i).light = 1;
                    emptystring = "";
                }
            }

        }
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(BG, 0, 0, SCR_WIDTH, SCR_HEIGHT);
        for(int i = 0; i< ram.size(); i++){
            font.draw(batch, i+1+" ", 10, 1500-i*150);
            ram.get(i).x = 60;
            ram.get(i).y = 1500-i*150;
            if(ram.get(i).light == 0){
                font1.draw(batch, ram.get(i).name, ram.get(i).x, ram.get(i).y );
            }
            else{
                font2.draw(batch, ram.get(i).name, ram.get(i).x, ram.get(i).y );
            }
        }
        btnBACK.font.draw(batch, btnBACK.text, btnBACK.x, btnBACK.y );
        btnNEXT.font.draw(batch, btnNEXT.text, btnNEXT.x, btnNEXT.y);
        font.draw(batch, "RAM", SCR_WIDTH/2-75, SCR_HEIGHT);
        font1.draw(batch, emptystring, SCR_WIDTH/2-160, SCR_HEIGHT-1500);
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
