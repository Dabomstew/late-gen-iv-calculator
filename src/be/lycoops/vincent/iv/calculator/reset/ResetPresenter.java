package be.lycoops.vincent.iv.calculator.reset;

import javax.inject.Inject;

import be.lycoops.vincent.iv.model.HiddenPowerCalculator;
import be.lycoops.vincent.iv.model.History;
import be.lycoops.vincent.iv.model.NatureCalculator;
import be.lycoops.vincent.iv.model.Pokemon;

public class ResetPresenter {

    @Inject
    private Pokemon pokemon;

    @Inject
    private HiddenPowerCalculator hiddenPowerCalculator;

    @Inject
    private NatureCalculator natureCalculator;

    @Inject
    private History history;

    public void reset() {
        pokemon.reset(pokemon.getBaseLevel());
        natureCalculator.reset();
        pokemon.setHiddenPower(hiddenPowerCalculator.setUnknown());
        history.reset();
    }

}
