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
// Inherit From Oval Class (FreeHand => Child, Oval => Parent)
public class FreeHand extends Oval {

    public FreeHand() {
    }

    public FreeHand(int x, int y, int width, int height, Color color, int filledDraw, int dottedDraw) {
        super(x, y, width, height, color, filledDraw, dottedDraw);
    }
}
