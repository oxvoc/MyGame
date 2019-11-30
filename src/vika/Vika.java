/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vika;

import java.util.ArrayList;
import java.util.Iterator;
import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import static javax.management.Query.in;

/**
 *
 * @author Ilnar
 */
public class Vika extends Application {

    @Override
    public void start(Stage primaryStage) {
        Button Start = new Button(); // Create and initialize a Button.
        Button How = new Button(); // Create and initialize a Button.
        Button Exit = new Button(); // Create and initialize a Button.
        Start.setText("Start");//sets the new label text
        Start.setOnAction(new EventHandler<ActionEvent>() { //implementing the EventHandler interface

            @Override
            public void handle(ActionEvent event) { //The handle method defines the actions that will be called when the button is clicked
                StackPane secondaryLayout = new StackPane();//creates an empty StackPane object

                Scene newScene = new Scene(secondaryLayout);//creating Scene
                Stage newWindow = new Stage();//creating Stage
                newWindow.setTitle("New Game");//name a new window
                newWindow.setScene(newScene);//setting Scene to newWindow
                newWindow.setX(primaryStage.getX() - 500);// Set position of second window, related to primary window.
                newWindow.setY(primaryStage.getY() - 200);// Set position of second window, related to primary window.
                Canvas screen = new Canvas(1200, 900);//create a bitmap
                secondaryLayout.getChildren().add(screen);//adding  screen
                GraphicsContext gc1 = screen.getGraphicsContext2D();//this method draws the desired shape
                Image map = new Image("fon.jpg");//Creates a new image element
                newWindow.show();//display another window
                ArrayList<String> input = new ArrayList<String>(); //Initialize an ArrayList

                newScene.setOnKeyPressed( //setting text for the label at the pressing of keyboard keys
                        new EventHandler<KeyEvent>() {
                    public void handle(KeyEvent e) {
                        String code = e.getCode().toString();
                        if (!input.contains(code)) {
                            input.add(code);
                        }
                    }
                });
                newScene.setOnKeyReleased(//setting text for the label at the releasing of keyboard keys
                        new EventHandler<KeyEvent>() {
                    public void handle(KeyEvent e) {
                        String code = e.getCode().toString();
                        input.remove(code);
                    }
                });
                Sprite player = new Sprite(); //creating a game object
                player.setImage("pumba3_right.png");//adding an image of the player
                player.setPosition(760, 540);//the task of the coordinates

                ArrayList<Sprite> gusList = new ArrayList<Sprite>();//Initialize an ArrayList
                ArrayList<Sprite> osaList = new ArrayList<Sprite>();//Initialize an ArrayList

                for (int i = 0; i < 50; i++) { //setting coordinates to an object
                    Sprite gus = new Sprite();
                    gus.setImage("gus.png");
                    double px = 1100 * Math.random();
                    double py = 700 * Math.random();
                    gus.setPosition(px, py);
                    gusList.add(gus);
                }
                for (int i = 0; i < 10; i++) { //setting coordinates to an object
                    Sprite osa = new Sprite();
                    osa.setImage("osa.png");
                    double px = 1100 * Math.random();
                    double py = 700 * Math.random();
                    osa.setPosition(px, py);
                    osaList.add(osa);
                }
                IntValue lastNanoTime = new IntValue(System.nanoTime());//Measuring time and falling asleep streams

                IntValue Count = new IntValue(0);//Returns the value of this Integer as an int.

                new AnimationTimer() {
                    public void handle(long currentNanoTime) {
                        // calculate time since last update.
                        double elapsedTime = (currentNanoTime - lastNanoTime.value) / 1000000000.0;//Code optimization through manual timing
                        lastNanoTime.value = currentNanoTime;

                        // game logic
                        int speed = 50;//variable initialization
                        player.setVelocity(0, 0); //the speed limit of equipment
                        if (input.contains("F")) { //conditions for operation of buttons
                            speed = speed * 4;
                           player.setImage("lion1.png");
                            
                        }
                          
                            
                        
                        if (input.contains("LEFT")) { //conditions for operation of buttons
                            player.setImage("pumba3_left.png");
                            player.addVelocity(-speed, 0);
                            
                            if (input.contains("F")) { //conditions for operation of buttons
                            speed = speed * 4;
                            player.setImage("lion1.png");
                            
                        }}
                        if (input.contains("RIGHT")) { //conditions for operation of buttons
                            player.setImage("pumba3_right.png");
                            player.addVelocity(speed, 0);
                            if (input.contains("F")) {
                            speed = speed * 4;
                            player.setImage("lion.png");
                            
                        }
                        }
                        if (input.contains("UP")) { //conditions for operation of buttons
                            player.setImage("pumba3_right.png");
                            player.addVelocity(0, -speed);
                           if (input.contains("F")) {
                            speed = speed * 4;
                           player.setImage("lion1.png");
                            
                        }
                        }
                        if (input.contains("DOWN")) { //conditions for operation of buttons
                            player.setImage("pumba3_right.png");
                            player.addVelocity(0, speed);
                           if (input.contains("F")) {
                            speed = speed * 4;
                            player.setImage("lion1.png");
                            
                       }
                        }

                        player.update(elapsedTime);

                        // collision detection
                        Iterator<Sprite> gusIter = gusList.iterator();//access array elements
                        while (gusIter.hasNext()) {
                            Sprite gus = gusIter.next();
                            if (player.intersects(gus)) {
                                gusIter.remove();
                                Count.value++;
                            }
                        }
                        Iterator<Sprite> osaIter = osaList.iterator();//access array elements
                        while (osaIter.hasNext()) {
                            Sprite osa = osaIter.next();
                            if (player.intersects(osa)) {
                                osaIter.remove();
                                Count.value--;
                            }
                        }

                        // render
                        gc1.clearRect(0, 0, 1000, 1000); //Clears the specified area
                        gc1.drawImage(map, 0, 0);//Draws an image in coordinates (x, y)
                        player.render(gc1);//image acquisition

                        for (Sprite gus : gusList) {
                            gus.render(gc1);
                        }
                        for (Sprite osa : osaList) {
                            osa.render(gc1);
                        }
                        String pointsText = "Insects: " + (1 * Count.value);//scoring with an array
                        gc1.setFill(Color.MAGENTA);//text color
                        gc1.setStroke(Color.YELLOW);//border color
                        gc1.setLineWidth(2);//the width parameter of the text
                        Font theFont = Font.font("Calibri", FontWeight.BOLD, 40);//font settings
                        gc1.setFont(theFont);//the purpose of the font
                        gc1.fillText(pointsText, 800, 36);//fill text at a specified position
                        gc1.strokeText(pointsText, 800, 36);//stroke the specified text at the specified position
                        if (Count.value == 30) { //counter value
                            Image end = new Image("win.jpg");////Creates a new image element
                            gc1.drawImage(end, 300, 200);//Draws an image in coordinates (x, y)
                            GraphicsContext gc1 = screen.getGraphicsContext2D();//this method draws the desired shape
                            String winText = " YOU WIN";
                            gc1.setFill(Color.MAGENTA); //text color
                            gc1.setStroke(Color.YELLOW);//border color
                            gc1.setLineWidth(2);//the width parameter of the text
                            Font winFont = Font.font("Calibri", FontWeight.BOLD, 50);//font settings
                            gc1.setFont(winFont);//the purpose of the font
                            gc1.fillText(winText, 300, 500);//fill text at a specified position
                            gc1.strokeText(winText, 300, 500);//stroke the specified text at the specified position
                            if (input.contains("SPACE")){//conditions for operation of buttons
                                newWindow.close();
                            }

                        }
                    }

                }.start();

            }

        });
        //Button How = new Button();
        How.setText("Help");
        How.setOnAction(new EventHandler<ActionEvent>() { //setting text for the label at the releasing of keyboard keys

            @Override
            public void handle(ActionEvent event) {
                
                Label secondLabel = new Label("Game control:\n" //creating a new text field
                        + "Moving is the arrows\n"
                        + "press F to speed up\n"
                        + "HOW TO PLAY:\n"
                        + "Collect 30 coins to win.\n"
                        + "Wasps take away 1 coin\n"
                        + "press spacebar to exit\n" );
                
         
                          
                StackPane secondaryLayout = new StackPane();//creates an empty StackPane object
                 secondaryLayout.getChildren().add(secondLabel);//adding  secondlabel
                Scene newScene = new Scene(secondaryLayout,682, 383);//setting scene sizes
                Stage newWindow = new Stage();//creating Stage
                newWindow.setTitle("Help");//name a new window
                newWindow.setScene(newScene);//setting Scene to newWindow
                newWindow.setX(primaryStage.getX()+200);// Set position of second window, related to primary window.
                 newWindow.setY(primaryStage.getY()+100);// Set position of second window, related to primary window.
               
               
               
              
                newWindow.show();
               
                
            }
            
        });
        Exit.setText("Exit");
        Exit.setOnAction(new EventHandler<ActionEvent>() { //setting text for the label at the releasing of keyboard keys

            @Override
            public void handle(ActionEvent event) {
                // get a handle to the stage
                Stage stage = (Stage) Exit.getScene().getWindow();
                // do what you have to do
                stage.close();
            }
        });

        primaryStage.setTitle("Vika");//name a new window
        Pane root = new Pane();//location of children
        Scene theScene = new Scene(root);////creating Scene
        primaryStage.setScene(theScene);//setting Scene to primaryStage

        Canvas canvas = new Canvas(1000, 562);//canvas sizes
        root.getChildren().add(canvas);//adding  canvas

        GraphicsContext gc = canvas.getGraphicsContext2D();//this method draws the desired shape
        root.getStylesheets().add("css/start.css");//using css for styling
        Image menuBG = new Image("end.png");////Creates a new image element
        root.getChildren().add(Start);//adding  start
        Start.relocate(10, 70);//setting button coordinates
        root.getChildren().add(How);//adding  how
        How.relocate(400, 70);//setting button coordinates
        root.getChildren().add(Exit);//adding  exit
        Exit.relocate(800, 70);//setting button coordinates
        gc.drawImage(menuBG, 0, 0);//Draws an image in coordinates (x, y)
        String text = "©Panteleeva V.A FMFI-b18PIo";//authorship
        gc.setFill(Color.BLACK);//text color
        gc.setStroke(Color.BLACK);//border color
        Font in = Font.font("Calibri", FontWeight.BOLD, 18);//font settings
        gc.setFont(in);//the purpose of the font
        gc.fillText(text, 630, 500);//fill text at a specified position
        gc.strokeText(text, 630, 500);//stroke the specified text at the specified position
        primaryStage.show();

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
