/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package paint.brush.project.specifications;

import java.awt.GridLayout;
import javax.swing.JFrame;

/**
 *
 * @author Merna Hesham
 */
public class PaintBrushFrame {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Create obj. from JFrame to create window
        JFrame frame = new JFrame();
        
        // Set title of App windows.
        frame.setTitle("Paint Brush Application");
        
        //Create obj. from JPanel
        PaintBrushPanel brushPanel = new PaintBrushPanel(new GridLayout(2, 1));
        
        // *** properties for jframe(content, size, visiabilty) ***
        
        // Put Jpanel in frame windows
        frame.setContentPane(brushPanel);
        
        // is  Size of frame window (width, height)
        frame.setSize(700, 700);
        
        // to see frame window
        frame.setVisible(true);
        
        //when close window from x btn from start tab, it also closes the run app 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
