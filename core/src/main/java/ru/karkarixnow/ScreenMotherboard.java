package ru.karkarixnow;

import static ru.karkarixnow.Main.MOTHERBOARD;
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

public class ScreenMotherboard implements Screen {
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
    public static ArrayList<Comp> motherboard = new ArrayList<>();

    public ScreenMotherboard(Main main){
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
        motherboard.add(new Comp("ASUS ROG Strix B650-A Gaming WiFi", 25000, 0, MOTHERBOARD, font1));
        motherboard.get(motherboard.size()-1).sovmest.addAll(Arrays.asList("AMD7", "DDR5-6000"));
        motherboard.add(new Comp("MSI MAG B650 Tomahawk WiFi", 21000, 0, MOTHERBOARD, font1));
        motherboard.get(motherboard.size()-1).sovmest.addAll(Arrays.asList("AMD7", "DDR5-6000"));
        motherboard.add(new Comp("Gigabyte B650 AORUS Elite AX", 20000, 0, MOTHERBOARD, font1));
        motherboard.get(motherboard.size()-1).sovmest.addAll(Arrays.asList("AMD7", "DDR5-6000"));
        motherboard.add(new Comp("ASUS TUF Gaming Z790-Plus WiFi", 25000, 0,  MOTHERBOARD, font1));
        motherboard.get(motherboard.size()-1).sovmest.addAll(Arrays.asList("INTEL", "DDR5-6000", "DR4-3600"));
        motherboard.add(new Comp("MSI PRO Z790-P WiFi", 18000, 0,  MOTHERBOARD, font1));
        motherboard.get(motherboard.size()-1).sovmest.addAll(Arrays.asList("INTEL", "DDR5-6000", "DR4-3600"));
        motherboard.add(new Comp("Gigabyte Z790 UD AC", 20000, 0,  MOTHERBOARD, font1));
        motherboard.get(motherboard.size()-1).sovmest.addAll(Arrays.asList("INTEL", "DDR5-6000", "DR4-3600"));
        motherboard.add(new Comp("ASUS ROG Strix B550-F Gaming", 18000, 0,  MOTHERBOARD, font1));
        motherboard.get(motherboard.size()-1).sovmest.addAll(Arrays.asList("AMD5", "DDR4-3600"));
        motherboard.add(new Comp("MSI B550 Tomahawk", 15400, 0,  MOTHERBOARD, font1));
        motherboard.get(motherboard.size()-1).sovmest.addAll(Arrays.asList("AMD5", "DDR4-3600"));
        motherboard.add(new Comp("Gigabyte B550 AORUS Elite V2", 13000, 0,  MOTHERBOARD, font1));
        motherboard.get(motherboard.size()-1).sovmest.addAll(Arrays.asList("AMD5", "DDR4-3600"));



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
                main.setScreen(main.screenGame);
            }
            if(btnNEXT.hit(touch.x, touch.y)){
                for(int i = 0; i < motherboard.size(); i++){
                    if(motherboard.get(i).light == 1){
                        lastcomp.add(motherboard.get(i));
                        main.setScreen(main.screenCPU);
                    }
                }
            }
            for(int i = 0; i<motherboard.size(); i++){
                if(motherboard.get(i).hit(touch.x, touch.y)){
                    for(int j = 0; j < motherboard.size(); j++){
                        if(motherboard.get(j).light == 1){
                            motherboard.get(j).light = 0;
                        }
                    }
                    motherboard.get(i).light = 1;
                }
            }

        }
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(BG, 0, 0, SCR_WIDTH, SCR_HEIGHT);
        for(int i = 0; i< motherboard.size(); i++){
            font.draw(batch, i+1+" ", 50, 1500-i*150);
            motherboard.get(i).x = 100;
            motherboard.get(i).y = 1500-i*150;
            if(motherboard.get(i).light == 0){
                font1.draw(batch, motherboard.get(i).name, motherboard.get(i).x, motherboard.get(i).y );
            }
            else{
                font2.draw(batch, motherboard.get(i).name, motherboard.get(i).x, motherboard.get(i).y );
            }
        }
        btnBACK.font.draw(batch, btnBACK.text, btnBACK.x, btnBACK.y );
        btnNEXT.font.draw(batch, btnNEXT.text, btnNEXT.x, btnNEXT.y);
        font.draw(batch, "MOTHERBOARD", SCR_WIDTH/2-150, SCR_HEIGHT);
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
