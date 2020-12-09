package Steps;
import io.cucumber.java.ru.*;
import io.cucumber.java.ParameterType;

public class StepDefs {

    @ParameterType(".+")
    public Categories categories(String category) {
        return Categories.valueOf(category);
    }

    @Пусть("открыт ресурс авито")
    public boolean checkAvito() {
        return false;
    }

    @И("в выпадающем списке категорий выбрана {categories}")
    public boolean checkCategory() {
        return false;
    }

}
