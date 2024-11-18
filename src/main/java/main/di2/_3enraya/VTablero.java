package main.di2._3enraya;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 *
 * @author rodrigo
 */
public class VTablero extends JFrame {

    private JLabel[] casillas = new JLabel[9]; // 9 celdas del tablero
    private boolean isXTurn = true; // Alternar turnos (X inicia)
    private int turnCount = 0; // Contador de turnos

    public VTablero() {
        initComponents();
    }

    private void initComponents() {
        setTitle("Tres en Raya - Tic Tac Toe"); // Nombre de la ventana
        setIconImage(new ImageIcon("icon.png").getImage()); // Cambiar ícono
        setSize(400, 400); // Tamaño fijo
        setResizable(false); // No se puede maximizar
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 3)); // Diseño de la cuadrícula 3x3

        // Crear las 9 celdas del juego
        for (int i = 0; i < 9; i++) {
            casillas[i] = new JLabel("", SwingConstants.CENTER);
            casillas[i].setFont(new Font("Arial", Font.BOLD, 48));
            casillas[i].setBorder(BorderFactory.createLineBorder(Color.BLACK));
            casillas[i].setOpaque(true);
            casillas[i].setBackground(Color.WHITE);
            int cellIndex = i; // Índice de la celda
            casillas[i].addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    handleMove(cellIndex); // Procesar el clic
                }
            });
            add(casillas[i]);
        }

        setVisible(true);
    }

    // Lógica para manejar el movimiento
    private void handleMove(int cellIndex) {
        if (!casillas[cellIndex].getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Esa casilla ya está ocupada.");
            return;
        }

        // Asignar X o O según el turno
        casillas[cellIndex].setText(isXTurn ? "X" : "O");
        casillas[cellIndex].setForeground(isXTurn ? Color.BLUE : Color.RED);

        isXTurn = !isXTurn; // Cambiar el turno
        turnCount++;

        // Comprobar si hay un ganador o empate
        if (checkWin()) {
            JOptionPane.showMessageDialog(this, "¡" + (isXTurn ? "O" : "X") + " ha ganado!");
            resetGame();
        } else if (turnCount == 9) {
            JOptionPane.showMessageDialog(this, "¡Empate!");
            resetGame();
        }
    }

    // Comprobar si hay un ganador
    private boolean checkWin() {
        int[][] winningCombinations = {
                {0, 1, 2}, {3, 4, 5}, {6, 7, 8}, // Filas
                {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, // Columnas
                {0, 4, 8}, {2, 4, 6}             // Diagonales
        };

        for (int[] combination : winningCombinations) {
            if (!casillas[combination[0]].getText().isEmpty() &&
                    casillas[combination[0]].getText().equals(casillas[combination[1]].getText()) &&
                    casillas[combination[1]].getText().equals(casillas[combination[2]].getText())) {
                return true; // Hay un ganador
            }
        }

        return false; // No hay ganador
    }

    // Reiniciar el juego
    private void resetGame() {
        for (JLabel cell : casillas) {
            cell.setText("");
        }
        isXTurn = true;
        turnCount = 0;
    }
}
