package ru.karkarixnow;

import static ru.karkarixnow.Main.SCR_HEIGHT;
import static ru.karkarixnow.Main.SCR_WIDTH;
import static ru.karkarixnow.Main.SSD;
import static ru.karkarixnow.Main.lastcomp;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

import java.util.ArrayList;

public class ScreenSSD implements Screen {
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
    ArrayList<Comp> ssd = new ArrayList<>();
    public ScreenSSD(Main main){
        this.main = main;
        batch = main.batch;
        camera = main.camera;
        touch = main.touch;
        font = main.font;
        font1 = main.font1;
        font2 = main.font2;
        BG = new Texture("SSD.png");
        btnNEXT = new Button(font, "NEXT", 700, 100);
        btnBACK = new Button(font, "BACK", 50, 100);
        ssd.add(new Comp("ADATA LEGEND 960 MAX", 9200, 3.5, SSD, font1));
        ssd.add(new Comp("Samsung 990 PRO", 11500, 5, SSD, font1));
        ssd.add(new Comp("WD Black SN850X", 10000, 4.5, SSD, font1));
        ssd.add(new Comp("Sabrent Rocket 4", 16500, 4, SSD, font1));
        ssd.add(new Comp("Crucial P5 Plus", 5000, 4, SSD, font1));
        ssd.add(new Comp("Samsung 980 PRO", 12000, 4, SSD, font1));
        ssd.add(new Comp("Samsung 970 Evo Plus", 8600, 3.5, SSD, font1));
        ssd.add(new Comp("ADATA XPG GAMMIX S11 Pro", 5800, 3, SSD, font1));
        ssd.add(new Comp("Kingston NV2", 6800, 2, SSD, font1));

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
                for(int i = 0; i < ssd.size(); i++){
                    if(ssd.get(i).light == 1){
                        ssd.get(i).light = 0;
                    }
                }
                main.setScreen(main.screenRAM);
            }
            if(btnNEXT.hit(touch.x, touch.y)){
                for(int i = 0; i < ssd.size(); i++){
                    if(ssd.get(i).light == 1){
                        lastcomp.add(ssd.get(i));
                        main.setScreen(main.screenPSU);
                    }
                }
            }
            for(int i = 0; i<ssd.size(); i++){
                if(ssd.get(i).hit(touch.x, touch.y)){
                    for(int j = 0; j < ssd.size(); j++){
                        if(ssd.get(j).light == 1){
                            ssd.get(j).light = 0;
                        }
                    }
                    ssd.get(i).light = 1;
                }
            }

        }
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(BG, 0, 0, SCR_WIDTH, SCR_HEIGHT);
        for(int i = 0; i< ssd.size(); i++){
            font.draw(batch, i+1+" ", 10, 1500-i*150);
            ssd.get(i).x = 60;
            ssd.get(i).y = 1500-i*150;
            if(ssd.get(i).light == 0){
                font1.draw(batch, ssd.get(i).name, ssd.get(i).x, ssd.get(i).y );
            }
            else{
                font2.draw(batch, ssd.get(i).name, ssd.get(i).x, ssd.get(i).y );
            }
        }
        btnBACK.font.draw(batch, btnBACK.text, btnBACK.x, btnBACK.y );
        btnNEXT.font.draw(batch, btnNEXT.text, btnNEXT.x, btnNEXT.y);
        font.draw(batch, "SSD", SCR_WIDTH/2-20, SCR_HEIGHT);
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
