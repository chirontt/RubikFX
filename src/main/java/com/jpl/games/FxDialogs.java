package com.jpl.games;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.StageStyle;

/**
 * Quick, basic dialogs for JavaFX
 *
 */
public class FxDialogs {

    public static void showInformation(String headerText, String message) {
        showInformation("Information", headerText, message);
    }

    public static void showInformation(String title, String headerText, String message) {
        buildAlert(title, headerText, message, AlertType.INFORMATION).showAndWait();
    }

    private static Alert buildAlert(String title, String headerText, String message, AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.initStyle(StageStyle.UTILITY);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(message);
        return alert;
    }

    public static void showWarning(String headerText, String message) {
        showWarning("Warning", headerText, message);
    }

    public static void showWarning(String title, String headerText, String message) {
        buildAlert(title, headerText, message, AlertType.WARNING).showAndWait();
    }

    public static void showError(String title, String message) {
        showError("Error", title, message);
    }

    public static void showError(String title, String headerText, String message) {
        buildAlert(title, headerText, message, AlertType.ERROR).showAndWait();
    }

    public static void showException(String headerText, String message, Exception exception) {
        showException("Exception", headerText, message, exception);
    }

    public static void showException(String title, String headerText, String message, Exception exception) {
        Alert alert = buildAlert(title, headerText, message, AlertType.ERROR);

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        exception.printStackTrace(pw);
        String exceptionText = sw.toString();

        Label label = new Label("Details:");

        TextArea textArea = new TextArea(exceptionText);
        textArea.setEditable(false);
        textArea.setWrapText(true);

        textArea.setMaxWidth(Double.MAX_VALUE);
        textArea.setMaxHeight(Double.MAX_VALUE);
        GridPane.setVgrow(textArea, Priority.ALWAYS);
        GridPane.setHgrow(textArea, Priority.ALWAYS);

        GridPane expContent = new GridPane();
        expContent.setMaxWidth(Double.MAX_VALUE);
        expContent.add(label, 0, 0);
        expContent.add(textArea, 0, 1);

        alert.getDialogPane().setExpandableContent(expContent);

        alert.showAndWait();
    }

    public static ButtonType showConfirm(String headerText, String message, String... options) {
        return showConfirm("Choose an option", headerText, message, options);
    }

    public static ButtonType showConfirm(String title, String headerText, String message, String... options) {
        List<ButtonType> buttons = null;
        if (options == null || options.length == 0) {
            //use default "OK" and "Cancel" button options
        } else {
            buttons = new ArrayList<>();
            for (String option : options) {
                buttons.add(new ButtonType(option));
            }
        }

        return showConfirm(title, headerText, message, buttons==null ? (ButtonType[])null : buttons.toArray(new ButtonType[buttons.size()]));
    }

    public static ButtonType showConfirm(String title, String headerText, String message, ButtonType... buttons) {
        Alert alert = buildAlert(title, headerText, message, AlertType.CONFIRMATION);

        if (buttons != null && buttons.length > 0)
            alert.getButtonTypes().setAll(buttons);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent()) {
            return result.get();
        } else {
        	return null;
        }
    }

    public static String showTextInput(String headerText, String message, String defaultValue) {
    	return showTextInput("Input", headerText, message, defaultValue);
    }

    public static String showTextInput(String title, String headerText, String message, String defaultValue) {
        TextInputDialog dialog = new TextInputDialog(defaultValue);
        dialog.initStyle(StageStyle.UTILITY);
        dialog.setTitle(title);
        dialog.setHeaderText(headerText);
        dialog.setContentText(message);

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            return result.get();
        } else {
            return null;
        }
    }

}
