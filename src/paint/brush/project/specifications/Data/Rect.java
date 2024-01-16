/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package paint.brush.project.specifications.Data;

import java.awt.Color;

/**
 *
 * @author Merna Hesham
 */
// Inherit From Shape Class (Rect => Child, Shape => Parent)
public class Rect extends Shape {

    int x, y, width, height, filledDraw;

    public Rect() {
    }

    public Rect(int x, int y, int width, int height, int filledDraw) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.filledDraw = filledDraw;
    }

    public Rect(int x, int y, int width, int height, Color color, int filledDraw, int dottedDraw) {
        super(color, dottedDraw);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.filledDraw = filledDraw;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getFilledDraw() {
        return filledDraw;
    }

    public void setFilledDraw(int filledDraw) {
        this.filledDraw = filledDraw;
    }
    
}
