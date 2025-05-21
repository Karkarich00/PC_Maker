package ru.karkarixnow;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

import java.util.ArrayList;

public class Main extends Game {
    public static final float SCR_WIDTH = 900;
    public static final float SCR_HEIGHT = 1600;
    public static final int MOTHERBOARD = 0;
    public static final int CPU = 1;
    public static final int RAM = 2;
    public static final int SSD = 3;
    public static final int PSU = 4;
    public static final int GPU = 5;
    public static String emptystring = "";

    public SpriteBatch batch;
    public OrthographicCamera camera;
    public Vector3 touch;
    public BitmapFont font;
    public BitmapFont font1;
    public BitmapFont font2;
    ScreenMenu screenMenu;
    ScreenGame screenGame;
    ScreenMotherboard screenMotherboard;
    ScreenCPU screenCPU;
    ScreenRAM screenRAM;
    ScreenSSD screenSSD;
    ScreenPSU screenPSU;
    ScreenComp screenComp;
    public static ArrayList<Comp> lastcomp = new ArrayList<>();
    @Override
    public void create() {
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, SCR_WIDTH, SCR_HEIGHT);
        touch = new Vector3();
        font = new BitmapFont(Gdx.files.internal("last.fnt"));
        font1 = new BitmapFont(Gdx.files.internal("redlast.fnt"));
        font2 = new BitmapFont(Gdx.files.internal("greenlast.fnt"));
        screenMenu = new ScreenMenu(this);
        screenGame = new ScreenGame(this);
        screenMotherboard = new ScreenMotherboard(this);
        screenCPU = new ScreenCPU(this);
        screenRAM = new ScreenRAM(this);
        screenSSD = new ScreenSSD(this);
        screenPSU = new ScreenPSU(this);
        screenComp = new ScreenComp(this);
        setScreen(screenMenu);
    }


    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
    }
}
