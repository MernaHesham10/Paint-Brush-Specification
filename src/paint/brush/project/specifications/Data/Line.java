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
// Inherit From Shape Class (Line => Child, Shape => Parent)
public class Line extends Shape {

    int x1, y1, x2, y2;

    public Line() {
    }

    public Line(int x1, int y1, int x2, int y2, Color color, int dottedDraw) {
        super(color, dottedDraw);
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    public int getX1() {
        return x1;
    }

    public void setX1(int x1) {
        this.x1 = x1;
    }

    public int getY1() {
        return y1;
    }

    public void setY1(int y1) {
        this.y1 = y1;
    }

    public int getX2() {
        return x2;
    }

    public void setX2(int x2) {
        this.x2 = x2;
    }

    public int getY2() {
        return y2;
    }

    public void setY2(int y2) {
        this.y2 = y2;
    }
    
}
