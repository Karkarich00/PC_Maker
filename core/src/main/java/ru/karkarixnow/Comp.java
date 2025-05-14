package ru.karkarixnow;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;

import java.util.ArrayList;
import java.util.List;

public class Comp {
    int price;
    String name;
    int proizv;
    int type;
    List<String> sovmest = new ArrayList<>();
    boolean selected;
    float x = 0;
    float y = 0;
    float width, height;
    BitmapFont font1;
    BitmapFont font2;
    int light;

    public Comp(String name, int price, int proizv, int type){
        this.name = name;
        this.price = price;
        this.proizv = proizv;
        this.type = type;
        this.light = 0;
        this.font1 = new BitmapFont(Gdx.files.internal("red2.fnt"));
        this.font2 = new BitmapFont(Gdx.files.internal("green2.fnt"));
        GlyphLayout glyphLayout = new GlyphLayout(font1, name);
        width = glyphLayout.width;
        height = glyphLayout.height;
    }
    boolean hit(float tx, float ty) {
        return x<tx && tx< x+width && y>ty && ty>y-height;
    }
}
