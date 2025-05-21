package ru.karkarixnow;

import static ru.karkarixnow.Main.PSU;
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

public class ScreenPSU implements Screen {
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
    ArrayList<Comp> psu = new ArrayList<>();
    public ScreenPSU(Main main){
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
        psu.add(new Comp("Corsair RM750x", 13000, 4.5, PSU, font1));
        psu.add(new Comp("MSI MPG A1000G PCIE5", 18000, 5, PSU, font1));
        psu.add(new Comp("Seasonic Focus GX-850", 13000, 4.5, PSU, font1));
        psu.add(new Comp("EVGA SuperNOVA 1000W", 15000, 4, PSU, font1));
        psu.add(new Comp("MSI A850G", 10000, 4, PSU, font1));
        psu.add(new Comp("Thermaltake Toughpower GF1", 9000, 3.5, PSU, font1));
        psu.add(new Comp("DEEPCOOL PF750", 7000, 3, PSU, font1));
        psu.add(new Comp("be quiet! SYSTEM POWER 10 ", 14500, 4.5, PSU, font1));
        psu.add(new Comp("Corsair RM1000x", 24000, 5, PSU, font1));
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
                for(int i = 0; i < psu.size(); i++){
                    if(psu.get(i).light == 1){
                        psu.get(i).light = 0;
                    }
                }
                main.setScreen(main.screenSSD);
            }
            if(btnNEXT.hit(touch.x, touch.y)){
                for(int i = 0; i < psu.size(); i++){
                    if(psu.get(i).light == 1){
                        lastcomp.add(psu.get(i));
                        main.setScreen(main.screenComp);
                        for(int j = 0; j < psu.size(); j++){
                            if(psu.get(i).light == 1){
                                psu.get(i).light = 0;
                            }
                        }
                    }
                }
            }
            for(int i = 0; i<psu.size(); i++){
                if(psu.get(i).hit(touch.x, touch.y)){
                    for(int j = 0; j < psu.size(); j++){
                        if(psu.get(j).light == 1){
                            psu.get(j).light = 0;
                        }
                    }
                    psu.get(i).light = 1;
                }
            }

        }
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(BG, 0, 0, SCR_WIDTH, SCR_HEIGHT);
        for(int i = 0; i< psu.size(); i++){
            font.draw(batch, i+1+" ", 10, 1500-i*150);
            psu.get(i).x = 60;
            psu.get(i).y = 1500-i*150;
            if(psu.get(i).light == 0){
                font1.draw(batch, psu.get(i).name, psu.get(i).x, psu.get(i).y );
            }
            else{
                font2.draw(batch, psu.get(i).name, psu.get(i).x, psu.get(i).y );
            }
        }
        btnBACK.font.draw(batch, btnBACK.text, btnBACK.x, btnBACK.y );
        btnNEXT.font.draw(batch, btnNEXT.text, btnNEXT.x, btnNEXT.y);
        font.draw(batch, "PSU", SCR_WIDTH/2-75, SCR_HEIGHT);
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
