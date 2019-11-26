package be.lycoops.vincent.iv;


import com.airhacks.afterburner.injection.Injector;

import be.lycoops.vincent.iv.calculator.CalculatorPresenter;
import be.lycoops.vincent.iv.calculator.CalculatorView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        CalculatorView view = new CalculatorView();
        CalculatorPresenter presenter = (CalculatorPresenter) view.getPresenter();
        Scene scene = new Scene(view.getView());
        presenter.setScene(scene);
        stage.setScene(scene);
        stage.setTitle("Shield Excadrill IV Calculator (base by wartab, Excadrill impl. by Dabomstew)");
        stage.show();
    }

    @Override
    public void stop() throws Exception {
        Injector.forgetAll();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
