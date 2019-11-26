package be.lycoops.vincent.iv.calculator.configuration;


import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

import javax.inject.Inject;

import be.lycoops.vincent.iv.model.EffortValueProvider;
import be.lycoops.vincent.iv.model.Game;
import be.lycoops.vincent.iv.model.GameService;
import be.lycoops.vincent.iv.model.HiddenPowerCalculator;
import be.lycoops.vincent.iv.model.History;
import be.lycoops.vincent.iv.model.NatureCalculator;
import be.lycoops.vincent.iv.model.Pokemon;
import be.lycoops.vincent.iv.model.Stat;
import javafx.beans.property.IntegerProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class ConfigurationPresenter implements Initializable {

    @FXML
    private Label level;
    
    @FXML
    private Button evolved;

    @FXML
    private Button lv42;

    @FXML
    private Button lv43;
    
    @FXML
    private Button lv44;
    
    @FXML
    private Button lv45;

    @Inject
    private Pokemon pokemon;

    @Inject
    private GameService gameService;

    @Inject
    private History history;
    
    @Inject
    private HiddenPowerCalculator hiddenPowerCalculator;

    @Inject
    private NatureCalculator natureCalculator;
    
    public void evolve() {
        pokemon.evolve();
        history.addEvolution();
    }

    public void levelPlus() {
        pokemon.levelUp();
    }

    public void levelMinus() {
        pokemon.levelDown();
    }

    public void setL42() {
        setBaseLevel(42);
    }

    public void setL43() {
        setBaseLevel(43);
    }
    
    public void setL44() {
        setBaseLevel(44);
    }
    
    public void setL45() {
        setBaseLevel(45);
    }

    private void setBaseLevel(int lv) {
        Button[] btns = { lv42, lv43, lv44, lv45 };
        pokemon.reset(lv);
        natureCalculator.reset();
        pokemon.setHiddenPower(hiddenPowerCalculator.setUnknown());
        history.reset();
        for(int i=0;i<4;i++) {
            btns[i].setDisable(i == lv-42);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        level.setText("L"+pokemon.getLevel());
        pokemon.levelProperty().addListener((o, oldLevel, newLevel) -> {
            level.setText("L" + newLevel);
            updateEffortValues();
        });
        pokemon.evolvedProperty().addListener((o, wasEvolved, isEvolved) -> evolved.setDisable(isEvolved));
        gameService.gameProperty().addListener((o, old, newGame) -> setGame(newGame));
        lv42.setDisable(true);
    }

    private void setGame(Game game) {
        updateEffortValues();
    }

    private void updateEffortValues() {
        Map<Stat, IntegerProperty> effortValues = pokemon.getEffortValues();
        Map<Stat, Integer> newEffortValues = EffortValueProvider.getEffortValues(gameService.getGame(), pokemon.getLevel(), pokemon.getBaseLevel());
        effortValues.forEach((stat, effortValue) -> effortValue.set(newEffortValues.get(stat)));
    }
}
