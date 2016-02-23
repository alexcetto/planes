package planes.view;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import planes.Criterion.Criteria;
import planes.Plane;
import planes.User.User;
import javafx.fxml.FXML;

// @TODO : Faire les requêtes DB pour enregistrer le panier de User

public class UserBasketController {
    @FXML private Button removeSelectedButton;
    @FXML private TableView<Plane> tableProductsBasket;

    public void initBasket(User user) {

        tableProductsBasket.setEditable(true);
//        tableProductsBasket.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        TableColumn<Plane,Criteria> mfr = new TableColumn<>("Manufacturer");
        mfr.setMinWidth(150);
        mfr.setCellValueFactory(
                new PropertyValueFactory<>("manufacturer"));

        TableColumn <Plane,Criteria> model = new TableColumn<>("Model");
        model.setMinWidth(100);
        model.setCellValueFactory(
                new PropertyValueFactory<>("model"));

        TableColumn <Plane,Criteria> price = new TableColumn<>("Price");
        price.setMinWidth(50);
        price.setMaxWidth(50);
        price.setCellValueFactory(
                new PropertyValueFactory<>("price"));


        removeSelectedButton.setOnAction((ActionEvent e) ->
                user.removePlaneToBasket(tableProductsBasket.getSelectionModel().getSelectedItems()));

        ObservableList<Plane> productsBasket = user.getBasket();
        tableProductsBasket.setItems(productsBasket);
        tableProductsBasket.getColumns().addAll(mfr, model, price);

    }
}
