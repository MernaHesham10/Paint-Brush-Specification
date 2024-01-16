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
// Shape is parent Class for Child Classes(Line, Rect, Oval, FreeHand, Eraser)
public class Shape {

    Color color = new Color(0, 0, 0);
    int dottedDraw;

    public Shape() {
    }

    public Shape(Color color, int dottedDraw) {
        this.color = color;
        this.dottedDraw = dottedDraw;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getDotted() {
        return dottedDraw;
    }

    public void setDotted(int dottedDraw) {
        this.dottedDraw = dottedDraw;
    }
}
