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
    private Button lv7;

    @FXML
    private Button lv8;
    
    @FXML
    private Button lv9;
    
    @FXML
    private Button lv10;

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

    public void levelPlus() {
        pokemon.levelUp();
    }

    public void levelMinus() {
        pokemon.levelDown();
    }

    public void setL7() {
        setBaseLevel(7);
    }

    public void setL8() {
        setBaseLevel(8);
    }
    
    public void setL9() {
        setBaseLevel(9);
    }
    
    public void setL10() {
        setBaseLevel(10);
    }

    private void setBaseLevel(int lv) {
        Button[] btns = { lv7, lv8, lv9, lv10 };
        pokemon.reset(lv);
        natureCalculator.reset();
        pokemon.setHiddenPower(hiddenPowerCalculator.setUnknown());
        history.reset();
        for(int i=0;i<4;i++) {
            btns[i].setDisable(i == lv-7);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        level.setText("L"+pokemon.getLevel());
        pokemon.levelProperty().addListener((o, oldLevel, newLevel) -> {
            level.setText("L" + newLevel);
            updateEffortValues();
        });
        gameService.gameProperty().addListener((o, old, newGame) -> setGame(newGame));
        lv7.setDisable(true);
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
