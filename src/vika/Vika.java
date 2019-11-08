/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vika;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.NodeOrientation;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import static javafx.scene.text.FontWeight.BOLD;
import javafx.stage.Stage;

/**
 *
 * @author user
 */
public class Vika extends Application {
    public static void main(String[] args) 
    {
        launch(args);
    }
 
    @Override
    public void start(Stage theStage) 
    {
       Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                   Label secondLabel = new Label("sorry, but your game not found");
              
                StackPane secondaryLayout = new StackPane();
                Canvas screen = new Canvas( 900, 500 );
                secondaryLayout.getChildren().add(screen);
                Scene secondScene = new Scene(secondaryLayout, 900, 500);
                GraphicsContext gc1 = screen.getGraphicsContext2D();
                Image pic = new Image( "fon.jpg" );  
                gc1.drawImage( pic, 0, 0 );
                secondaryLayout.getChildren().add(secondLabel);
                // New window (Stage)
                Stage newWindow = new Stage();
                newWindow.setTitle("Second Stage");
                newWindow.setScene(secondScene);
 
                // Set position of second window, related to primary window.
                newWindow.setX(theStage.getX() + 200);
                newWindow.setY(theStage.getY() + 100);
                secondLabel.getStylesheets().add("css/label.css");
                newWindow.show();
            }
        });
        theStage.setTitle("Hakuna Matata");//название проекта
           Group root = new Group();
    Scene theScene = new Scene( root );
    theStage.setScene( theScene );
         
    Canvas canvas = new Canvas( 900, 500 );
    root.getChildren().add( canvas );
    //Image imageOk = new Image(getClass().getResourceAsStream(""));
    
        /**Button btn = new Button("Begin",new ImageView(imageOk));
        btn.setFont (Font.font("Arial",FontWeight.BOLD,20));
    btn.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
    btn.setShape(new Circle(10,10,120,Color.RED));
    btn.setOnAction(new EventHandler<ActionEvent>(){
        @Override
        public void handle(ActionEvent event){
            System.out.println("Hello World!");
        }
    });*/
  
  
        btn.setText("Begin");
        
        btn.relocate(100, 400);
        Button exit = new Button();
        exit.setText("Close");
        exit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage stage = (Stage) exit.getScene().getWindow();
                stage.close();
            }
        });
        root.getChildren().add(exit);
        exit.relocate(250, 400);
        // btn.setBackground(Background.EMPTY);
        //root.getChildren().add(btn);
        //GraphicsContext gc = canvas.getGraphicsContext2D();
    
    
      
        
    
   
   // btn.setLayoutX(400);
    //btn.setLayoutY(400);
     
    root.getChildren().add(btn);
         
    GraphicsContext gc = canvas.getGraphicsContext2D();
         
    gc.setFill( Color.MAGENTA );
    gc.setStroke( Color.YELLOW );
    gc.setLineWidth(3);
    Font theFont = Font.font( "Calibri", FontWeight.BOLD, 63 );
    gc.setFont( theFont );
    Image photo3 = new Image( "fon.jpg" );  
    gc.drawImage( photo3, 0, 0 );
    gc.fillText( "Hakuna Matata", 200, 50 );
    gc.strokeText( "Hakuna Matata", 200, 50 );
    Image photo = new Image( "timon.png" );
    Image photo1 = new Image( "lion1.png" );
    Image photo2 = new Image( "pumba3.png" );
    
    gc.drawImage( photo, 150, 150 );
         gc.drawImage( photo1, 350, 140 );
         gc.drawImage( photo2, 570, 170 );
         
         gc.setFill( Color.BLACK );
         gc.setLineWidth(3);
         Font in = Font.font("Calibri", FontWeight.BOLD, 18);
           gc.setFont( in );
           gc.fillText( "©Panteleeva V.A. FMFI-b18PIo", 500, 450 );
           root.getStylesheets().add("css/start.css");
         
        theStage.show();
    }

    private void setSize(int i, int i0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }}
    
    

   