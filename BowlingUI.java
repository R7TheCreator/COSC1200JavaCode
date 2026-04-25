/*
Name: Rasul.A
Date: April 24, 2026
Description: Finishing up the final assignment.
 */

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class BowlingUI extends JFrame {
    // constants
    private final int MAX_FRAMES = 10;
    private final int MAX_ROLLS = 21;
    private final int NUM_PLAYERS = 2;

    // variables
    private int[][] playerRolls = new int[NUM_PLAYERS][MAX_ROLLS];
    private int[] rollIndex = new int[NUM_PLAYERS];
    private int currentPlayer = 0;
    private int currentFrame = 0;
    private boolean isFirstRoll = true;

    // UI components
    private JLabel[][] frameLabels = new JLabel[NUM_PLAYERS][MAX_FRAMES];
    private JLabel statusLabel;
    private JTextField pinInput;
    private JButton submitBtn, startBtn, saveBtn, loadBtn, exitBtn;

    public BowlingUI() {
        setTitle("Bowling System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(15, 15));

        // Score board display
        JPanel boardPanel = new JPanel(new GridLayout(NUM_PLAYERS + 1, MAX_FRAMES + 1, 5, 5));
        boardPanel.setBorder(BorderFactory.createTitledBorder("Score Board"));

        boardPanel.add(new JLabel("Player / Frame"));
        for (int i = 1; i <= MAX_FRAMES; i++) boardPanel.add(new JLabel("Frame " + i, SwingConstants.CENTER));

        for (int p = 0; p < NUM_PLAYERS; p++) {
            boardPanel.add(new JLabel("Player " + (p + 1)));
            for (int f = 0; f < MAX_FRAMES; f++) {
                frameLabels[p][f] = new JLabel("-", SwingConstants.CENTER);
                frameLabels[p][f].setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
                boardPanel.add(frameLabels[p][f]);
            }
        }

        // input panels
        JPanel inputPanel = new JPanel(new FlowLayout());
        statusLabel = new JLabel("Player 1, Frame 1, Roll 1. Enter pins (0-10): ");
        pinInput = new JTextField(5);
        submitBtn = new JButton("Submit Roll");

        inputPanel.add(statusLabel);
        inputPanel.add(pinInput);
        inputPanel.add(submitBtn);

        // control buttons
        JPanel controlPanel = new JPanel();
        startBtn = new JButton("Start Game");
        saveBtn = new JButton("Save Game");
        loadBtn = new JButton("Load Game");
        exitBtn = new JButton("Exit");

        controlPanel.add(startBtn);
        controlPanel.add(saveBtn);
        controlPanel.add(loadBtn);
        controlPanel.add(exitBtn);

        // action listeners
        submitBtn.addActionListener(e -> handleRoll());
        startBtn.addActionListener(e -> resetGame());
        saveBtn.addActionListener(e -> saveGame());
        loadBtn.addActionListener(e -> loadGame());
        exitBtn.addActionListener(e -> System.exit(0));

        add(boardPanel, BorderLayout.CENTER);
        add(inputPanel, BorderLayout.NORTH);
        add(controlPanel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
    }

    private void handleRoll() {
        try {
            int pins = Integer.parseInt(pinInput.getText().trim());

            if (pins < 0 || pins > 10) {
                JOptionPane.showMessageDialog(this, "Pins must be between 0 and 10.");
                return;
            }

            if (currentFrame < 9 && !isFirstRoll && (playerRolls[currentPlayer][rollIndex[currentPlayer] - 1] + pins > 10)) {
                JOptionPane.showMessageDialog(this, "Total pins in a frame cannot exceed 10.");
                return;
            }

            playerRolls[currentPlayer][rollIndex[currentPlayer]++] = pins;
            updateScores();
            nextTurn(pins);
            pinInput.setText("");

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number.");
        }
    }

    private void nextTurn(int pins) {
        if (currentFrame < 9) {
            if (isFirstRoll && pins < 10) {
                isFirstRoll = false;
            } else {
                moveToNextPlayer();
            }
        } else {
            int rollsIn10th = rollIndex[currentPlayer] - (9 * 2);
            if (rollsIn10th == 2 && (playerRolls[currentPlayer][18] + playerRolls[currentPlayer][19] < 10)) {
                moveToNextPlayer();
            } else if (rollsIn10th == 3) {
                moveToNextPlayer();
            } else {
                isFirstRoll = false;
            }
        }
        updateStatus();
    }

    private void moveToNextPlayer() {
        isFirstRoll = true;
        currentPlayer++;
        if (currentPlayer >= NUM_PLAYERS) {
            currentPlayer = 0;
            currentFrame++;
        }
        if (currentFrame >= MAX_FRAMES) {
            announceWinner();
        }
    }

    private void updateScores() {
        for (int p = 0; p < NUM_PLAYERS; p++) {
            int totalScore = 0;
            int cursor = 0;
            for (int f = 0; f < MAX_FRAMES; f++) {
                if (cursor >= rollIndex[p]) break;

                if (playerRolls[p][cursor] == 10) { // Strike
                    totalScore += 10 + playerRolls[p][cursor+1] + playerRolls[p][cursor+2];
                    cursor++;
                } else if (playerRolls[p][cursor] + playerRolls[p][cursor+1] == 10) { // Spare
                    totalScore += 10 + playerRolls[p][cursor+2];
                    cursor += 2;
                } else {
                    totalScore += playerRolls[p][cursor] + playerRolls[p][cursor+1];
                    cursor += 2;
                }
                frameLabels[p][f].setText(String.valueOf(totalScore));
            }
        }
    }

    private void updateStatus() {
        if (currentFrame < MAX_FRAMES) {
            statusLabel.setText("Player " + (currentPlayer + 1) + ", Frame " + (currentFrame + 1) + ". Enter pins:");
        } else {
            statusLabel.setText("Game Over!");
            submitBtn.setEnabled(false);
        }
    }

    private void announceWinner() {
        int p1Score = frameLabels[0][9].getText().equals("-") ? 0 : Integer.parseInt(frameLabels[0][9].getText());
        int p2Score = frameLabels[1][9].getText().equals("-") ? 0 : Integer.parseInt(frameLabels[1][9].getText());
        String winner = (p1Score > p2Score) ? "Player 1 Wins!" : (p2Score > p1Score) ? "Player 2 Wins!" : "It's a Tie!";
        JOptionPane.showMessageDialog(this, "Final Scores:\nPlayer 1: " + p1Score + "\nPlayer 2: " + p2Score + "\n\n" + winner);
    }

    private void resetGame() {
        playerRolls = new int[NUM_PLAYERS][MAX_ROLLS];
        rollIndex = new int[NUM_PLAYERS];
        currentPlayer = 0;
        currentFrame = 0;
        isFirstRoll = true;
        submitBtn.setEnabled(true);
        for (int p = 0; p < NUM_PLAYERS; p++) {
            for (int f = 0; f < MAX_FRAMES; f++) frameLabels[p][f].setText("-");
        }
        updateStatus();
    }

    private void saveGame() {
        JFileChooser chooser = new JFileChooser();
        if (chooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(chooser.getSelectedFile()))) {
                out.writeObject(playerRolls);
                out.writeObject(rollIndex);
                out.writeInt(currentPlayer);
                out.writeInt(currentFrame);
                out.writeBoolean(isFirstRoll);
                JOptionPane.showMessageDialog(this, "Game Saved Successfully!");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error saving: " + ex.getMessage());
            }
        }
    }

    private void loadGame() {
        JFileChooser chooser = new JFileChooser();
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(chooser.getSelectedFile()))) {
                playerRolls = (int[][]) in.readObject();
                rollIndex = (int[]) in.readObject();
                currentPlayer = in.readInt();
                currentFrame = in.readInt();
                isFirstRoll = in.readBoolean();
                updateScores();
                updateStatus();
                JOptionPane.showMessageDialog(this, "Game Loaded Successfully!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error loading: " + ex.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BowlingUI().setVisible(true));
    }
}

