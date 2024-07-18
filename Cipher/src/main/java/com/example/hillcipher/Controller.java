package com.example.hillcipher;

import javafx.fxml.FXML;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Controller {


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    @FXML
    private Button encrypt;

    @FXML
    private Button decipher;

    @FXML
    private Button clear;

    @FXML
    private TextField key;
    public String getTextKey() {
        return textPropertyKey().get();
    }
    public StringProperty textPropertyKey() {
        return key.textProperty();
    }

    @FXML
    private TextField text;

    @FXML
    private TextField result;

    private HillCipher obj = new HillCipher();
    @FXML
    void initialize() {
        this.encrypt.setOnAction(actionEvent -> {
            System.err.println();
            double sq = Math.sqrt(this.getTextKey().length());
            int size = (int) sq;
            String line = this.text.getText();
            String[] words = line.toLowerCase().split("[.,\\s]+");
            String key = this.key.getText();
            if (obj.check(key, size))
            {
                for (int i = 0; i < words.length; i++)
                {
                    obj.cofact(obj.km, size);
                    String text = obj.performDivision(words[i], size, 1);
                    this.result.appendText(text + " ");
                }
            }
        });

        this.decipher.setOnAction(actionEvent -> {
            System.err.println();
            double sq = Math.sqrt(this.getTextKey().length());
            int size = (int) sq;
            String line = this.text.getText();
            String[] words = line.toLowerCase().split("[.,\\s]+");
            String key = this.key.getText();
            if (obj.check(key, size))
            {
                for (int i = 0; i < words.length; i++)
                {
                    obj.cofact(obj.km, size);
                    String text = obj.performDivision(words[i], size, 2);
                    this.result.appendText(text + " ");
                }
            }
        });

        this.clear.setOnAction(actionEvent -> {
            System.err.println();
            this.text.clear();
            this.result.clear();
        });
    }
}