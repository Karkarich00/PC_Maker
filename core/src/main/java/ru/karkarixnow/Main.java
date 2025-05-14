package ru.karkarixnow;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public class Main extends Game {
    public static final float SCR_WIDTH = 900;
    public static final float SCR_HEIGHT = 1600;
    public static final int MOTHERBOARD = 0;
    public static final int CPU = 1;
    public static final int RAM = 2;
    public static final int SSD = 3;
    public static final int PSU = 4;
    public static final int GPU = 5;

    public SpriteBatch batch;
    public OrthographicCamera camera;
    public Vector3 touch;
    public BitmapFont font;
    ScreenMenu screenMenu;
    ScreenGame screenGame;
    ScreenSettings screenSettings;
    ScreenLeaderBoard screenLeaderBoard;
    ScreenAbout screenAbout;
    ScreenCPU screenCPU;
    @Override
    public void create() {
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, SCR_WIDTH, SCR_HEIGHT);
        touch = new Vector3();
        font = new BitmapFont(Gdx.files.internal("last.fnt"));

        screenMenu = new ScreenMenu(this);
        screenGame = new ScreenGame(this);
        screenAbout = new ScreenAbout(this);
        screenLeaderBoard = new ScreenLeaderBoard(this);
        screenSettings = new ScreenSettings(this);
        screenCPU = new ScreenCPU(this);
        setScreen(screenMenu);
    }


    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
    }
}
