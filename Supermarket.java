import javafx.application.Application;
import javafx.stage.*;
import javafx.scene.layout.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.Alert.*;
import javafx.scene.paint.*;
import javafx.geometry.*;
public class Supermarket extends Application
{
    public void start(Stage mainStage)
    {
        // Initialize the warehouse inventory and shopping cart
        Warehouse wh = new Warehouse();
        wh.initialize();
        SupermarketCart cart = new SupermarketCart();
        // Set the main layout and scene
        BorderPane root = new BorderPane();
        Scene mainScene = new Scene(root, 600,500);
        mainStage.setScene(mainScene);
        VBox mainLayout = new VBox();
        root.setCenter(mainLayout);
        mainLayout.setPadding(new Insets(10));
        mainLayout.setSpacing(20);
        mainLayout.setAlignment(Pos.CENTER);
        mainStage.setTitle("Supermarket Manager App");
        // Add a title header
        Label titleLabel = new Label("Supermarket Manager");
        titleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");
        titleLabel.setTextFill(Color.GOLD);
        titleLabel.setBackground(new Background(new BackgroundFill(Color.DARKGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
        // HBox for buying a product
        HBox purchaseBox = new HBox();
        purchaseBox.setAlignment(Pos.CENTER);
        purchaseBox.setStyle("-fx-font-size: 18px;");
        Label purchaseLabel = new Label ("Purchase an item: ");
        purchaseLabel.setStyle("-fx-font-weight: bold;");
        ComboBox<String> productBox = new ComboBox<>();
        productBox.setValue("Apple");
        String[] productNames = {"Apple","Banana","Orange","Grapes","Mango"};
        for (String s: productNames)
        {
            productBox.getItems().add(s);
        }
        Button purchaseButton = new Button("Purchase");
        purchaseButton.setOnAction(e->{
            e.getClass();
            Product toBuy = wh.remove(productBox.getValue());
            Alert alert = new Alert(AlertType.INFORMATION);
            if (toBuy != null)
            {
                cart.add(toBuy);
                alert.setTitle("Purchase Successful");
                alert.setHeaderText("Product Purchased");
                alert.setContentText("You have purchased: " + toBuy.toString());
                alert.showAndWait();
            }
            else
            {
                alert.setTitle("Purchase Failed");
                alert.setHeaderText("Product Not Available");
                alert.setContentText("The product is not available in the warehouse.");
                alert.showAndWait();
            }
        });
        purchaseBox.getChildren().addAll(purchaseLabel,productBox,purchaseButton);
        // Another row to remove items from the cart
        HBox discardBox = new HBox();
        discardBox.setAlignment(Pos.CENTER);
        discardBox.setStyle("-fx-font-size: 18px;");
        Label discardLabel = new Label ("Discard an item from cart: ");
        discardLabel.setStyle("-fx-font-weight: bold;");
        TextField field = new TextField();
        field.setMaxWidth(100);
        Button discardButton = new Button("Discard");
        discardButton.setOnAction(e->{
            e.getClass();
            Product toDiscard = cart.remove(field.getText());
            wh.add(toDiscard);
            Alert alert = new Alert(AlertType.INFORMATION);
            if (toDiscard != null)
            {
                alert.setTitle("Discard Successful");
                alert.setHeaderText("Product Discarded");
                alert.setContentText("You have discarded: " + toDiscard.toString());
                alert.showAndWait();
            }
            else
            {
                alert.setTitle("Discard Failed");
                alert.setHeaderText("Product Not Found in Cart");
                alert.setContentText("The product is not found in the cart.");
                alert.showAndWait();
            }
        });
        discardBox.getChildren().addAll(discardLabel,field,discardButton);
        // Add all elements
        mainLayout.getChildren().addAll(titleLabel,purchaseBox,discardBox);
        // MenuBar is added to see all items in cart/check out/see total cost
        MenuBar topMenu = new MenuBar();
        root.setTop(topMenu);
        Menu fileMenu = new Menu("File");
        MenuItem allItemsItem = new MenuItem("View All Items in Cart");
        allItemsItem.setOnAction((e->{
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Items in Cart");
            alert.setContentText(cart.listAllItems());
            alert.showAndWait();
        }));
        MenuItem checkOutItem = new MenuItem("Checkout/Reset");
        checkOutItem.setOnAction((e->{
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Checking out..");
            alert.setContentText("Your total is: " + cart.getTotalCost());
            alert.showAndWait();
            cart.clear();
            wh.initialize();
        }));
        MenuItem closeItem = new MenuItem("Exit");
        closeItem.setOnAction((e->{
            mainStage.close();
        }));
        fileMenu.getItems().addAll(allItemsItem,checkOutItem,closeItem);
        topMenu.getMenus().addAll(fileMenu); // Side.valueOf(boolean.class,"");
        // Show once all elements are added
        mainStage.show();
    }
    public static void main(String[] args)
    {
        launch(args);
    }
}