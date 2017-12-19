package ctrl;

import GUI.MyGameGraphicView;
import ch.eiafr.gl.simulife.gui.UserInterface;
import ch.eiafr.gl.simulife.gui.game.GameMatrixView;
import ch.eiafr.gl.simulife.gui.game.GameTextView;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import world.MyWorld;

public class Controller {

  @FXML
  TextField       tfNbrPenguins;
  @FXML
  TextField       tfRows;
  @FXML
  TextField       tfColumns;
  @FXML
  Label           lblGridSize;
  @FXML
  Label           lblNbrPenguins;
  @FXML
  Label           lblMovesPerTurn;
  @FXML
  Label           lblCross;
  @FXML
  ToggleGroup     gameSelection;
  @FXML
  ToggleGroup     modeSelection;
  @FXML
  ToggleGroup     moveSelection;
  @FXML
  RadioButton     rbChessLife;
  @FXML
  RadioButton     rbWaterWorld;
  @FXML
  RadioButton     rbAuto;
  @FXML
  RadioButton     rbStep;
  @FXML
  RadioButton     rbSingle;
  @FXML
  RadioButton     rbMulti;
  @FXML
  CheckBox        cbGraphic;
  @FXML
  CheckBox        cbMatrix;
  @FXML
  CheckBox        cbText;

  private MyWorld myWorld;
  private int     nbrCols = 8;
  private int     nbrRows = 8;

  @FXML
  private void initialize() {
    gameSelection.selectedToggleProperty()
        .addListener(new ChangeListener<Toggle>() {
          public void changed(ObservableValue<? extends Toggle> ov,
              Toggle old_toggle, Toggle new_toggle) {
            boolean isChessLife = new_toggle.equals(rbChessLife);
            lblMovesPerTurn.setDisable(!isChessLife);
            lblNbrPenguins.setDisable(isChessLife);
            lblGridSize.setDisable(isChessLife);
            lblCross.setDisable(isChessLife);
            rbSingle.setDisable(!isChessLife);
            rbMulti.setDisable(!isChessLife);
            tfRows.setDisable(isChessLife);
            tfColumns.setDisable(isChessLife);
            tfNbrPenguins.setDisable(isChessLife);
          }
        });

    tfNbrPenguins.textProperty().addListener(new ChangeListener<String>() {
      @Override
      public void changed(ObservableValue<? extends String> observable,
          String oldValue, String newValue) {
        if (!newValue.matches("\\d*")) {
          tfNbrPenguins.setText(newValue.replaceAll("[^\\d]", ""));
        }
      }
    });
    tfRows.textProperty().addListener(new ChangeListener<String>() {
      @Override
      public void changed(ObservableValue<? extends String> observable,
          String oldValue, String newValue) {
        if (!newValue.matches("\\d*")) {
          tfRows.setText(newValue.replaceAll("[^\\d]", ""));
        }
      }
    });
    tfColumns.textProperty().addListener(new ChangeListener<String>() {
      @Override
      public void changed(ObservableValue<? extends String> observable,
          String oldValue, String newValue) {
        if (!newValue.matches("\\d*")) {
          tfColumns.setText(newValue.replaceAll("[^\\d]", ""));
        }
      }
    });
  }

  public void startGame() {
    final int MAX_ROWS = 20;
    final int MIN_ROWS = 8;
    final int MAX_COLS = 20;
    final int MIN_COLS = 8;

    boolean isChessLife = gameSelection.getSelectedToggle().equals(rbChessLife);
    boolean isAutoMode = modeSelection.getSelectedToggle().equals(rbAuto);
    boolean isSingleMove = moveSelection.getSelectedToggle().equals(rbSingle);

    if (!isChessLife) {
      String rowsText = tfRows.getText();
      String colsText = tfColumns.getText();
      if (!rowsText.isEmpty()) {
        int rows = Integer.valueOf(rowsText);
        if (rows < MIN_ROWS)
          nbrRows = MIN_ROWS;
        else if (rows > MAX_ROWS)
          nbrRows = MAX_ROWS;
        else
          nbrRows = rows;
      }
      if (!colsText.isEmpty()) {
        int cols = Integer.valueOf(colsText);
        if (cols < MIN_COLS)
          nbrCols = MIN_COLS;
        else if (cols > MAX_COLS)
          nbrCols = MAX_COLS;
        else
          nbrCols = cols;
      }
    }
    
    final int MIN_PENGUINS = 2;
    final int MAX_PENGUINS = nbrCols*nbrRows/4;
    
    String nbPenguinsText = tfNbrPenguins.getText();
    
    int nbrOfPenguins = 2;
    if (!nbPenguinsText.isEmpty()) {
      int nbPenguins = Integer.valueOf(nbPenguinsText);
      if (nbPenguins < MIN_PENGUINS)
        nbrOfPenguins = MIN_PENGUINS;
      else if (nbPenguins > MAX_PENGUINS)
        nbrOfPenguins = MAX_PENGUINS;
      else
        nbrOfPenguins = nbPenguins;
    }
    
    myWorld = new MyWorld(nbrCols, nbrRows, isChessLife, !isAutoMode,
        isSingleMove, nbrOfPenguins);

    if (cbMatrix.isSelected()) {
      UserInterface userGameMatrixView = new UserInterface(
          new GameMatrixView(myWorld.getNbCols(), myWorld.getNbRows()));
      myWorld.addObserver(userGameMatrixView);
    }
    if (cbGraphic.isSelected()) {
      UserInterface userGraphiqueInterface = new UserInterface(
          new MyGameGraphicView(myWorld.getNbCols(), myWorld.getNbRows(),
              isChessLife));
      myWorld.addObserver(userGraphiqueInterface);
    }
    if (cbText.isSelected()) {
      UserInterface userTextInterface = new UserInterface(new GameTextView());
      myWorld.addObserver(userTextInterface);
    }

    myWorld.init(isChessLife);
    myWorld.updateView();
  }

}
