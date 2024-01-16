/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package paint.brush.project.specifications;

/**
 *
 * @author Merna Hesham
 */
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import paint.brush.project.specifications.Data.*;

public class PaintBrushPanel extends JPanel {

    //Vectors of Shapes Child Class
    Vector<Line> vecLines = new Vector<>();
    Vector<Oval> vecOvals = new Vector<>();
    Vector<Rect> vecRects = new Vector<>();
    Vector<FreeHand> vecFreeHand = new Vector<>();
    Vector<Eraser> vecEraser = new Vector<>();

    //ID for each shape
    private static final int LINE_BTNID = 1;
    private static final int OVAL_BTNID = 2;
    private static final int RECTANGLE_BTNID = 3;
    private static final int FREEHAND_BTNID = 4;
    private static final int ERASER_BTNID = 5;

    //Dimension for eraser, freehand
    private static final int DIMSIZE = 10;

    //Counter for each shape that i draw
    int lineNumbers, ovalNumbers, rectNumbers, freeHandNumbers, eraserNumbers;

    //var in shapes
    int x1, y1, x2, y2;
    int width, height;

    //UI Components
    //Color Btns
    JButton blackColorBtn;
    JButton redColorBtn;
    JButton greenColorBtn;
    JButton blueColorBtn;

    //Color Vars
    Color color = new Color(0, 0, 0);
    Color bgcolor = new Color(0, 0, 0);

    //Shape Btns
    JButton drawLineBtn;
    JButton drawOvalBtn;
    JButton drawRectBtn;

    //Tool Btns
    JButton freeHandBtn;
    JButton eraserBtn;
    JButton clearAllDrawsBtn;

    //Checkboxs
    JCheckBox dottedDrawChecker;
    JCheckBox fillDrawChecker;

    //Track ID btn
    int pressedBtnChecker;

    //Track ID filled checkbox
    int filledDraw;

    //Track ID dotted checkbox
    int dottedDraw;

    //Setting Btns
    JButton undoBtn;
    JButton saveBtn;
    JButton openBtn;

    //Opened Image
    BufferedImage loadedImage;

    public PaintBrushPanel(GridLayout gridLayout) {
        //Init. Vars.
        x1 = 0;
        y1 = 0;
        x2 = 0;
        y2 = 0;

        lineNumbers = 0;
        ovalNumbers = 0;
        rectNumbers = 0;
        freeHandNumbers = 0;
        eraserNumbers = 0;

        pressedBtnChecker = 0;

        filledDraw = 0;
        dottedDraw = 0;

        bgcolor = Color.WHITE;

        setLayout(new BorderLayout());
        this.setBackground(bgcolor);
        this.setFocusable(true);

        System.out.println("Default Consructor");

        //Create north Control Panel contains(Color, Shape panel)
        JPanel northControlPanel = new JPanel(new GridLayout(2, 1));

        JPanel colorPanel = new JPanel(); // Adjust the grid layout as per your preference
        blackColorBtn = createColorButton("Black", Color.BLACK);
        redColorBtn = createColorButton("Red", Color.RED);
        greenColorBtn = createColorButton("Green", Color.GREEN);
        blueColorBtn = createColorButton("Blue", Color.BLUE);

        JPanel shapePanel = new JPanel();
        drawLineBtn = createFnButton("Line", LINE_BTNID);
        drawOvalBtn = createFnButton("Oval", OVAL_BTNID);
        drawRectBtn = createFnButton("Rectangle", RECTANGLE_BTNID);

        //Create south Control Panel contains(Tool, setting panel)
        JPanel southControlPanel = new JPanel(new GridLayout(2, 1));

        JPanel toolPanel = new JPanel();
        freeHandBtn = createFnButton("Freehand", FREEHAND_BTNID);
        eraserBtn = createFnButton("Eraser", ERASER_BTNID);
        clearAllDrawsBtn = clearAllButton("Clear All");

        JPanel settingPanel = createSettingPanel();

        //Create checkBox Panel contains(Dotted, Filled Checkbox)
        JPanel checkBoxPanel = createCheckBoxPanel();

        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mousePressed(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                switch (pressedBtnChecker) {
                    case LINE_BTNID:
                        x1 = e.getX();
                        y1 = e.getY();
                        break;
                    case OVAL_BTNID:
                        x1 = e.getX();
                        y1 = e.getY();
                        break;
                    case RECTANGLE_BTNID:
                        x1 = e.getX();
                        y1 = e.getY();
                        break;
                    case FREEHAND_BTNID:
                        x1 = e.getX();
                        y1 = e.getY();
                        width = DIMSIZE;
                        height = DIMSIZE;
                        vecFreeHand.add(freeHandNumbers, new FreeHand(x1, y1, width, height, color, filledDraw, dottedDraw));
                        freeHandNumbers++;
                        updateUI();
                        break;
                    case ERASER_BTNID:
                        x1 = e.getX();
                        y1 = e.getY();
                        width = DIMSIZE;
                        height = DIMSIZE;
                        vecEraser.add(eraserNumbers, new Eraser(x1, y1, width, height, bgcolor, filledDraw, dottedDraw));
                        eraserNumbers++;
                        updateUI();
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                switch (pressedBtnChecker) {
                    case LINE_BTNID:
                        x2 = e.getX();
                        y2 = e.getY();
                        lineNumbers++;
                        break;
                    case OVAL_BTNID:
                        width = e.getX() - x1;
                        height = e.getY() - y1;
                        ovalNumbers++;
                        break;
                    case RECTANGLE_BTNID:
                        width = e.getX() - x1;
                        height = e.getY() - y1;
                        rectNumbers++;
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseExited(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

        });

        //MouseMotionListener
        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                switch (pressedBtnChecker) {
                    case LINE_BTNID:
                        x2 = e.getX();
                        y2 = e.getY();
                        vecLines.add(lineNumbers, new Line(x1, y1, x2, y2, color, dottedDraw));
                        updateUI();
                        break;
                    case OVAL_BTNID:
                        width = e.getX() - x1;
                        height = e.getY() - y1;
                        vecOvals.add(ovalNumbers, new Oval(x1, y1, width, height, color, filledDraw, dottedDraw));
                        updateUI();
                        break;
                    case RECTANGLE_BTNID:
                        width = e.getX() - x1;
                        height = e.getY() - y1;
                        vecRects.add(rectNumbers, new Rect(x1, y1, width, height, color, filledDraw, dottedDraw));
                        updateUI();
                        break;
                    case FREEHAND_BTNID:
                        x1 = e.getX();
                        y1 = e.getY();
                        width = DIMSIZE;
                        height = DIMSIZE;
                        vecFreeHand.add(freeHandNumbers, new FreeHand(x1, y1, width, height, color, filledDraw, dottedDraw));
                        freeHandNumbers++;
                        updateUI();
                        break;
                    case ERASER_BTNID:
                        x1 = e.getX();
                        y1 = e.getY();
                        width = DIMSIZE;
                        height = DIMSIZE;
                        vecEraser.add(eraserNumbers, new Eraser(x1, y1, width, height, bgcolor, filledDraw, dottedDraw));
                        eraserNumbers++;
                        updateUI();
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });

        //Set btns in Color Panel
        colorPanel.add(blackColorBtn);
        colorPanel.add(redColorBtn);
        colorPanel.add(greenColorBtn);
        colorPanel.add(blueColorBtn);

        //Set btns in Shape Panel
        shapePanel.add(drawRectBtn);
        shapePanel.add(drawOvalBtn);
        shapePanel.add(drawLineBtn);

        //Set btns in Tool Panel
        toolPanel.add(freeHandBtn);
        toolPanel.add(eraserBtn);
        toolPanel.add(clearAllDrawsBtn);

        //Set Color, Shape Panels in North Control Panel
        northControlPanel.add(colorPanel);
        northControlPanel.add(shapePanel);
        add(northControlPanel, BorderLayout.NORTH);

        //Set Checkbox Panel in main Panel
        add(checkBoxPanel, BorderLayout.WEST);

        //Set Tool, Stteing Panels in South Control Panel
        southControlPanel.add(toolPanel);
        southControlPanel.add(settingPanel);
        add(southControlPanel, BorderLayout.SOUTH);
    }

    //Function to give ID, functionality(when click btn), and labels for color btns
    private JButton createColorButton(String name, Color inputColor) {
        JButton button = new JButton(name);
        button.addActionListener(e -> {
            color = inputColor;
            System.out.println(name + " is Pressed");
        });
        button.setForeground(Color.WHITE);
        button.setBackground(inputColor);
        button.setFont(new Font("Forte", Font.PLAIN, 15));
        return button;
    }

    //Function to give ID, functionality(when click btn), and labels for btns
    private JButton createFnButton(String pressedShapeBtn, int pressedBtnID) {
        JButton button = new JButton(pressedShapeBtn);
        button.addActionListener(e -> {
            pressedBtnChecker = pressedBtnID;
            System.out.println(pressedShapeBtn + " button is Pressed ");
            System.out.println("flag " + pressedBtnChecker);
        });
        button.setForeground(Color.BLACK);
        button.setBackground(Color.WHITE);
        button.setFont(new Font("Forte", Font.PLAIN, 15));
        return button;
    }

    //Function to give ID, functionality, and labels for clear all btn
    private JButton clearAllButton(String clearAllStr) {
        JButton button = new JButton(clearAllStr);
        button.addActionListener(e -> clearAllDraws());
        button.setForeground(Color.BLACK);
        button.setBackground(Color.WHITE);
        button.setFont(new Font("Forte", Font.PLAIN, 15));
        return button;
    }

    //Function to give functionality for clear all btn to init all var.
    private void clearAllDraws() {
        pressedBtnChecker = 0;
        lineNumbers = 0;
        ovalNumbers = 0;
        rectNumbers = 0;
        freeHandNumbers = 0;
        eraserNumbers = 0;

        vecLines.clear();
        vecRects.clear();
        vecOvals.clear();
        vecFreeHand.clear();
        vecEraser.clear();

        loadedImage = null;

        updateUI();
    }

    //Function to create CheckBox Panel, give functionality for its components
    private JPanel createCheckBoxPanel() {
        JPanel checkBoxPanel = new JPanel();
        checkBoxPanel.add(createDottedDrawChecker());
        checkBoxPanel.add(createFillDrawChecker());
        return checkBoxPanel;
    }

    //Function to create Dotted CheckBox Panel, give functionality for it
    private JCheckBox createDottedDrawChecker() {
        dottedDrawChecker = new JCheckBox("Dotted");
        dottedDrawChecker.addItemListener(e -> dottedDraw = (e.getStateChange() == 1) ? 1 : 0);
        dottedDrawChecker.setFont(new Font("Forte", Font.PLAIN, 15));
        return dottedDrawChecker;
    }

    //Function to create Filled CheckBox Panel, give functionality for it
    private JCheckBox createFillDrawChecker() {
        fillDrawChecker = new JCheckBox("Filled");
        fillDrawChecker.addItemListener(e -> filledDraw = (e.getStateChange() == 1) ? 1 : 0);
        fillDrawChecker.setFont(new Font("Forte", Font.PLAIN, 15));
        return fillDrawChecker;
    }

    //Function to create Setting CheckBox Panel, give functionality for it
    private JPanel createSettingPanel() {
        JPanel settingPanel = new JPanel();
        settingPanel.add(createUndoButton());
        settingPanel.add(createSaveButton());
        settingPanel.add(createOpenButton());
        return settingPanel;
    }

    //Function to create Undo Btn, give functionality for it
    private JButton createUndoButton() {
        undoBtn = new JButton("Undo");
        undoBtn.addActionListener(e -> undo());
        undoBtn.setForeground(Color.BLACK);
        undoBtn.setBackground(Color.WHITE);
        undoBtn.setFont(new Font("Forte", Font.PLAIN, 15));
        return undoBtn;
    }

    //Function to create Save Btn, give functionality for it
    private JButton createSaveButton() {
        saveBtn = new JButton("Save");
        saveBtn.addActionListener(e -> saveImage());
        saveBtn.setForeground(Color.BLACK);
        saveBtn.setBackground(Color.WHITE);
        saveBtn.setFont(new Font("Forte", Font.PLAIN, 15));
        return saveBtn;
    }

    //Function to create Open Btn, give functionality for it
    private JButton createOpenButton() {
        openBtn = new JButton("Open");
        openBtn.addActionListener(e -> openImage());
        openBtn.setForeground(Color.BLACK);
        openBtn.setBackground(Color.WHITE);
        openBtn.setFont(new Font("Forte", Font.PLAIN, 15));
        return openBtn;
    }

    //Function to draw shapes
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.

        g.setColor(color);

        Graphics2D g2d = (Graphics2D) g.create();

        try {
            g2d.setColor(color);
            g2d.setStroke(new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0));

            Stroke dashed = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
            g2d.setStroke(dashed);

            switch (pressedBtnChecker) {
                case LINE_BTNID:
                    g.setColor(color);
                    if (dottedDraw == 0) {
                        g.drawLine(x1, y1, x2, y2);
                    } else if (dottedDraw == 1) {
                        g2d.drawLine(x1, y1, x2, y2);
                    }
                    break;
                case OVAL_BTNID:
                    g.setColor(color);
                    if (filledDraw == 0) {
                        if (dottedDraw == 1) {
                            g2d.drawOval(x1, y1, width, height);
                        } else {
                            g.drawOval(x1, y1, width, height);
                        }
                    } else if (filledDraw == 1) {
                        g.fillOval(x1, y1, width, height);
                    }
                    break;
                case RECTANGLE_BTNID:
                    g.setColor(color);
                    if (filledDraw == 0) {
                        if (dottedDraw == 1) {
                            g2d.drawRect(x1, y1, width, height);
                        } else {
                            g.drawRect(x1, y1, width, height);
                        }
                    } else if (filledDraw == 1) {
                        g.fillRect(x1, y1, width, height);
                    }
                    break;
                case FREEHAND_BTNID:
                    g.setColor(color);
                    g.fillOval(x1, y1, width, height);
                    break;
                case ERASER_BTNID:
                    g.setColor(bgcolor);
                    g.fillOval(x1, y1, width, height);
                    break;

                default:
                    System.out.println("No Flages");
                    break;
            }

            for (int i = 0; i < lineNumbers; i++) {
                if (!vecLines.isEmpty() && vecLines.size() > i) {
                    g.setColor(vecLines.get(i).getColor());
                    if (vecLines.get(i).getDotted() == 0) {
                        g.drawLine(vecLines.get(i).getX1(), vecLines.get(i).getY1(), vecLines.get(i).getX2(), vecLines.get(i).getY2());
                    } else if (vecLines.get(i).getDotted() == 1) {
                        g2d.setColor(vecLines.get(i).getColor());
                        g2d.drawLine(vecLines.get(i).getX1(), vecLines.get(i).getY1(), vecLines.get(i).getX2(), vecLines.get(i).getY2());
                    }
                }
            }

            for (int i = 0; i < ovalNumbers; i++) {
                if (!vecOvals.isEmpty() && vecOvals.size() > i) {
                    g.setColor(vecOvals.get(i).getColor());
                    if (vecOvals.get(i).getFilledDraw() == 0) {
                        if (vecOvals.get(i).getDotted() == 1) {
                            g2d.setColor(vecOvals.get(i).getColor());
                            g2d.drawOval(vecOvals.get(i).getX(), vecOvals.get(i).getY(), vecOvals.get(i).getWidth(), vecOvals.get(i).getHeight());
                        } else {
                            g.drawOval(vecOvals.get(i).getX(), vecOvals.get(i).getY(), vecOvals.get(i).getWidth(), vecOvals.get(i).getHeight());
                        }
                    } else if (vecOvals.get(i).getHeight() == 1) {
                        g.fillOval(vecOvals.get(i).getX(), vecOvals.get(i).getY(), vecOvals.get(i).getWidth(), vecOvals.get(i).getHeight());
                    }
                }
            }

            for (int i = 0; i < rectNumbers; i++) {
                if (!vecRects.isEmpty() && vecRects.size() > i) {
                    g.setColor(vecRects.get(i).getColor());
                    if (vecRects.get(i).getFilledDraw() == 0) {
                        if (vecRects.get(i).getDotted() == 1) {
                            g2d.setColor(vecRects.get(i).getColor());
                            g2d.drawRect(vecRects.get(i).getX(), vecRects.get(i).getY(), vecRects.get(i).getWidth(), vecRects.get(i).getHeight());
                        } else {
                            g.drawRect(vecRects.get(i).getX(), vecRects.get(i).getY(), vecRects.get(i).getWidth(), vecRects.get(i).getHeight());
                        }
                    } else if (vecRects.get(i).getFilledDraw() == 1) {
                        g.fillRect(vecRects.get(i).getX(), vecRects.get(i).getY(), vecRects.get(i).getWidth(), vecRects.get(i).getHeight());
                    }
                }
            }

            for (int i = 0; i < freeHandNumbers; i++)// freeHandBtn
            {
                g.setColor(vecFreeHand.get(i).getColor());
                g.fillOval(vecFreeHand.get(i).getX(), vecFreeHand.get(i).getY(), DIMSIZE, DIMSIZE);
            }

            for (int i = 0; i < eraserNumbers; i++) //eraser
            {
                g.setColor(bgcolor);
                g.fillOval(vecEraser.get(i).getX(), vecEraser.get(i).getY(), DIMSIZE, DIMSIZE);
            }

            if (loadedImage != null) {
                g.drawImage(loadedImage, 0, 0, this);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error Paint Operation: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

        } finally {
            g2d.dispose(); // Ensure proper disposal of the Graphics2D object
        }
    }

    // Functionality of Undo 
    private void undo() {
        try {
            if (pressedBtnChecker == FREEHAND_BTNID && freeHandNumbers > 0) {
                vecFreeHand.remove(freeHandNumbers - 1);
                freeHandNumbers--;
                updateUI();
            } else if (pressedBtnChecker == ERASER_BTNID && eraserNumbers > 0) {
                vecEraser.remove(eraserNumbers - 1);
                eraserNumbers--;
                updateUI();
            } else if (pressedBtnChecker == LINE_BTNID && lineNumbers > 0) {
                vecLines.remove(lineNumbers - 1);
                lineNumbers--;
                updateUI();
            } else if (pressedBtnChecker == OVAL_BTNID && ovalNumbers > 0) {
                vecOvals.remove(ovalNumbers - 1);
                ovalNumbers--;
                updateUI();
            } else if (pressedBtnChecker == RECTANGLE_BTNID && rectNumbers > 0) {
                vecRects.remove(rectNumbers - 1);
                rectNumbers--;
                updateUI();
            }
            //undoBtn.setEnabled(lineNumbers > 0 || ovalNumbers > 0 || rectNumbers > 0 || freeHandNumbers > 0 || eraserNumbers > 0);

        } catch (IndexOutOfBoundsException e) {
            //e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error Undo Operation: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Functionality of Save 
    private void saveImage() {
        try {
            BufferedImage image = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2 = image.createGraphics();
            this.paint(g2);
            g2.dispose();

            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileFilter(new FileNameExtensionFilter("PNG Images", "png"));
            int userSelection = fileChooser.showSaveDialog(null);

            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File fileToSave = fileChooser.getSelectedFile();

                if (fileToSave.exists()) {
                    int result = JOptionPane.showConfirmDialog(null,
                            "The file already exists. Do you want to overwrite it?",
                            "File Exists",
                            JOptionPane.YES_NO_OPTION);

                    if (result != JOptionPane.YES_OPTION) {
                        return;
                    }
                }

                ImageIO.write(image, "png", fileToSave);
            }
        } catch (IOException e) {
            //e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error saving image: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Functionality of Open 
    private void openImage() {
        try {
            JFileChooser fileChooser = new JFileChooser();
            int userSelection = fileChooser.showOpenDialog(null);

            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File fileToOpen = fileChooser.getSelectedFile();

                BufferedImage originalImage = ImageIO.read(fileToOpen);

                loadedImage = new BufferedImage(originalImage.getWidth(), originalImage.getHeight(), BufferedImage.TYPE_INT_ARGB);
                Graphics2D gLoadedImage = loadedImage.createGraphics();

                gLoadedImage.drawImage(originalImage, 0, 0, null);
                gLoadedImage.dispose();

                updateUI();

            }
        } catch (IOException e) {
            //e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error Open image: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

        }
    }
}
