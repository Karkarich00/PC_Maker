package ru.karkarixnow;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;

import java.util.ArrayList;
import java.util.List;

public class Comp {
    int price;
    String name;
    double proizv;
    int type;
    List<String> sovmest = new ArrayList<>();
    float x = 0;
    float y = 0;
    float width, height;
    BitmapFont font;
    int light;

    public Comp(String name, int price, double proizv, int type, BitmapFont font){
        this.name = name;
        this.price = price;
        this.proizv = proizv;
        this.type = type;
        this.light = 0;
        this.font = font;
        GlyphLayout glyphLayout = new GlyphLayout(font, name);
        width = glyphLayout.width;
        height = glyphLayout.height;
    }
    boolean hit(float tx, float ty) {
        return x<tx && tx< x+width && y>ty && ty>y-height;
    }
}
