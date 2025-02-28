package com.example;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class Controller {
    private boolean isXTurn = true; // X starter
    private boolean gameWon = false;

    @FXML private Label lbl00;
    @FXML private Label lbl01;
    @FXML private Label lbl02;
    @FXML private Label lbl10;
    @FXML private Label lbl11;
    @FXML private Label lbl12;
    @FXML private Label lbl20;
    @FXML private Label lbl21;
    @FXML private Label lbl22;

    @FXML private Label lblStatus;

    @FXML
    public void initialize() {
   
        addClickEvent(lbl00);
        addClickEvent(lbl01);
        addClickEvent(lbl02);
        addClickEvent(lbl10);
        addClickEvent(lbl11);
        addClickEvent(lbl12);
        addClickEvent(lbl20);
        addClickEvent(lbl21);
        addClickEvent(lbl22);
    }

    private void addClickEvent(Label lbl) {
        lbl.getStyleClass().add("grid-label");
        lbl.setOnMouseClicked((MouseEvent e) -> handleMove(lbl));
    }

    private void handleMove(Label lbl) {
        if (!lbl.getText().isEmpty() || gameWon) return; // Sjekk at feltet er tomt og spillet ikke er vunnet

        lbl.setText(isXTurn ? "X" : "O"); // Sett X eller O
        if (checkWinner()) {
            lblStatus.setText((isXTurn ? "X" : "O") + " wins! 🎉");
            gameWon = true;
        } else {
            isXTurn = !isXTurn; // Bytt tur
            lblStatus.setText(isXTurn ? "X's turn" : "O's turn");
        }
    }

    private boolean checkWinner() {
        String[][] board = {
            { lbl00.getText(), lbl01.getText(), lbl02.getText() },
            { lbl10.getText(), lbl11.getText(), lbl12.getText() },
            { lbl20.getText(), lbl21.getText(), lbl22.getText() }
        };

        // Sjekk rader og kolonner
        for (int i = 0; i < 3; i++) {
            if (board[i][0].equals(board[i][1]) && board[i][1].equals(board[i][2]) && !board[i][0].isEmpty()) return true;
            if (board[0][i].equals(board[1][i]) && board[1][i].equals(board[2][i]) && !board[0][i].isEmpty()) return true;
        }

        // Sjekk diagonaler
        if (board[0][0].equals(board[1][1]) && board[1][1].equals(board[2][2]) && !board[0][0].isEmpty()) return true;
        if (board[0][2].equals(board[1][1]) && board[1][1].equals(board[2][0]) && !board[0][2].isEmpty()) return true;

        return false;
    }

    @FXML
    private void resetGame() {
        lbl00.setText(""); lbl01.setText(""); lbl02.setText("");
        lbl10.setText(""); lbl11.setText(""); lbl12.setText("");
        lbl20.setText(""); lbl21.setText(""); lbl22.setText("");
        isXTurn = true;
        gameWon = false;
        lblStatus.setText("X's turn");
    }
    
}
