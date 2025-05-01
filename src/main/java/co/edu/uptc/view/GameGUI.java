/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package co.edu.uptc.view;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.InputStream;

public class GameGUI extends Application {

    private static final String FONT_NAME = "Press Start 2P";
    private static final Color DARK_GREEN = Color.rgb(22, 83, 47);
    private static final Color GOLD = Color.rgb(255, 183, 27);
    private static final Color BLACK = Color.BLACK;
    
    @Override
    public void start(Stage primaryStage) {
        // Cargar la fuente externa (necesitas tener el archivo .ttf en tu classpath)
        try {
            Font.loadFont(getClass().getResourceAsStream("/resources/PressStart2P-Regular.ttf"), 14);
        } catch (Exception e) {
            System.err.println("No se pudo cargar la fuente: " + e.getMessage());
        }
        
        // Configurar el contenedor principal
        BorderPane root = new BorderPane();
        
        // Configurar el fondo verde con patrón
        Image backgroundImage = new Image(getClass().getResourceAsStream("/resources/background.png"));
        BackgroundImage bgImage = new BackgroundImage(
                backgroundImage, 
                BackgroundRepeat.REPEAT, 
                BackgroundRepeat.REPEAT, 
                BackgroundPosition.DEFAULT, 
                BackgroundSize.DEFAULT);
        root.setBackground(new Background(bgImage));
        
        // Crear título pixelado "BLACKJACK!"
        Text titleText = new Text("BLACKJACK!");
        titleText.setFont(Font.font(FONT_NAME, 36));
        titleText.setFill(BLACK);
        
        // Crear el borde dorado alrededor del texto
        StackPane titleContainer = new StackPane();
        Text titleOutline = new Text("BLACKJACK!");
        titleOutline.setFont(Font.font(FONT_NAME, 40));
        titleOutline.setFill(GOLD);
        titleContainer.getChildren().addAll(titleOutline, titleText);
        titleContainer.setPadding(new Insets(20));
        
        // Configurar los botones con estilo pixelado
        Button playButton = createPixelButton("Jugar", GOLD, BLACK);
        Button rulesButton = createPixelButton("Reglas?", Color.rgb(150, 60, 60), Color.LIGHTGRAY);
        Button exitButton = createPixelButton("Salir", GOLD, BLACK);
        
        // Crear contenedor para los botones
        VBox buttonContainer = new VBox(20);
        buttonContainer.setAlignment(Pos.CENTER);
        buttonContainer.getChildren().addAll(playButton, rulesButton, exitButton);
        
        // Crear las cartas de Ases en las esquinas
        HBox leftCards = createCardPlacement(true);
        HBox rightCards = createCardPlacement(false);
        
        // Organizar todos los elementos en el layout
        root.setTop(titleContainer);
        root.setCenter(buttonContainer);
        BorderPane.setAlignment(titleContainer, Pos.CENTER);
        BorderPane.setAlignment(buttonContainer, Pos.CENTER);
        
        // Añadir las cartas a las esquinas
        root.setLeft(leftCards);
        root.setRight(rightCards);
        BorderPane.setMargin(leftCards, new Insets(100, 0, 0, 30));
        BorderPane.setMargin(rightCards, new Insets(100, 30, 0, 0));
        
        // Crear la escena
        Scene scene = new Scene(root, 800, 600);
        
        // Configurar la ventana principal
        primaryStage.setTitle("BlackJack Game");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
        
        // Añadir funcionalidad a los botones
        playButton.setOnAction(e -> {
            System.out.println("¡Iniciar juego!");
            // Aquí iría la lógica para iniciar el juego
        });
        
        rulesButton.setOnAction(e -> {
            System.out.println("Mostrar reglas");
            // Aquí iría la lógica para mostrar las reglas
        });
        
        exitButton.setOnAction(e -> {
            System.out.println("Salir del juego");
            primaryStage.close();
        });
    }
    
    private Button createPixelButton(String text, Color bgColor, Color textColor) {
        Button button = new Button(text);
        button.setFont(Font.font(FONT_NAME, 16));
        button.setTextFill(textColor);
        button.setPrefWidth(300);
        button.setPrefHeight(50);
        
        // Crear un estilo pixelado para el botón
        button.setStyle(
            "-fx-background-color: " + toRGBCode(bgColor) + ";" +
            "-fx-border-color: black;" +
            "-fx-border-width: 3px;" +
            "-fx-border-style: solid;" +
            "-fx-padding: 10px;" +
            "-fx-cursor: hand;"
        );
        
        // Añadir efecto al pasar el mouse
        button.setOnMouseEntered(e -> 
            button.setStyle(
                "-fx-background-color: " + toRGBCode(bgColor.brighter()) + ";" +
                "-fx-border-color: white;" +
                "-fx-border-width: 3px;" +
                "-fx-border-style: solid;" +
                "-fx-padding: 10px;" +
                "-fx-cursor: hand;"
            )
        );
        
        button.setOnMouseExited(e -> 
            button.setStyle(
                "-fx-background-color: " + toRGBCode(bgColor) + ";" +
                "-fx-border-color: black;" +
                "-fx-border-width: 3px;" +
                "-fx-border-style: solid;" +
                "-fx-padding: 10px;" +
                "-fx-cursor: hand;"
            )
        );
        
        return button;
    }
    
    private HBox createCardPlacement(boolean isLeft) {
        // Crear las imágenes de las cartas
        // Necesitarás tener estas imágenes en tu classpath
        ImageView spadeAce = new ImageView(new Image(getClass().getResourceAsStream("/resources/ace_spade.png")));
        ImageView heartAce = new ImageView(new Image(getClass().getResourceAsStream("/resources/ace_heart.png")));
        
        spadeAce.setFitHeight(100);
        spadeAce.setFitWidth(70);
        heartAce.setFitHeight(100);
        heartAce.setFitWidth(70);
        
        HBox cardBox = new HBox(-20); // Superposición negativa para que las cartas se solapen
        
        if (isLeft) {
            cardBox.getChildren().addAll(spadeAce, heartAce);
            cardBox.setRotate(-15); // Rotar ligeramente a la izquierda
        } else {
            cardBox.getChildren().addAll(heartAce, spadeAce);
            cardBox.setRotate(15); // Rotar ligeramente a la derecha
        }
        
        return cardBox;
    }
    
    private String toRGBCode(Color color) {
        return String.format("#%02X%02X%02X",
                (int) (color.getRed() * 255),
                (int) (color.getGreen() * 255),
                (int) (color.getBlue() * 255));
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
