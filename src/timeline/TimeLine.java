
package timeline;

import com.sun.javafx.perf.PerformanceTracker;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class TimeLine extends Application {
    
    // Velocity balls
    public final double  FPS=400;  // 5 = 60 FPS ||  400 = 2 FPS
    public static double speedX=10;
    public static double speedY=10;
    
    Circle ball = new Circle();
    Label label = new Label();   
    Scene scene;
    Group root;
    
    @Override
    public void start(Stage stage) {
        //Root settings
        root = new Group();
        root.getChildren().add(ball);
        root.getChildren().add(label);
        
        //Ball settings
        ball.setTranslateX(150);
        ball.setTranslateY(150);
        ball.setRadius(25);
        ball.setFill(Color.BLACK);
        
        //Label settings
        label.setTranslateX(10);
        label.setTranslateY(10);
        
       //Event handle bucle
        EventHandler<ActionEvent> eH = e->{    
            PerformanceTracker perfTracker= PerformanceTracker.getSceneTracker(scene);// Show FPS
            label.setText("FPS (Timeline) = "+perfTracker.getInstantFPS()); // set fps in label
            // Change direccion 
             if (ball.getTranslateX()>400 || ball.getTranslateX()<0) speedX*=-1;
             if (ball.getTranslateY()>350 || ball.getTranslateY()<0) speedY*=-1;

            ball.setTranslateX(ball.getTranslateX()+speedX); 
            ball.setTranslateY(ball.getTranslateY()+speedY); 
            
   
  
            
        };
        //bucle 2 fps > millis less FPS  5 milis = 60 FPS
        Timeline animation = new Timeline(new KeyFrame(Duration.millis(FPS), eH));     
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.play();

       
        
        scene = new Scene(root, 400, 350);
        
        stage.setTitle("Time line");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
   
    
}
