package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import Objects.Book;
import Objects.BookEntry;
import Objects.BookOrderEntry;
import Objects.BookSale;
import Objects.Cart;
import application.DBConnection;
import Objects.Publisher;
import Objects.User;
import Objects.UserEntry;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;


public class UserController implements Initializable{
	@FXML private AnchorPane scene;
    @FXML private Pane Browse_pane;
    @FXML private ImageView Browse_image;
    @FXML private Button Browse_button;
    @FXML private Pane Add_pane;
    @FXML private ImageView Add_image;
    @FXML private Button Add_button;
    @FXML private Pane AddW_pane;
    @FXML private ImageView AddW_image;
    @FXML private Button AddW_button;
    @FXML private AnchorPane Add_Publisher_Scene;
    @FXML private Label Publisher_lbl;
    @FXML private TextField AP_name;
    @FXML private TextField AP_address;
    @FXML private TextField AP_phone;
    @FXML private Button AP_add;
    @FXML private Label AP_result;
    @FXML private AnchorPane Add_Book_Scene;
    @FXML private Label Book_lbl;
    @FXML private TextField AB_isbn;
    @FXML private TextField AB_title;
    @FXML private TextField AB_author;
    @FXML private TextField AB_publisher;
    @FXML private Label AB_result;
    @FXML private TextField AB_threshold;
    @FXML private TextField AB_quantity;
    @FXML private TextField AB_price;
    @FXML private ChoiceBox<String> AB_category;
    @FXML private DatePicker AB_date;
    @FXML private Button AB_add;
    @FXML private AnchorPane Browse_Scene;
    @FXML private TextField B_search;
    @FXML private Button B_Bsearch;
    @FXML private Button B_all;
    @FXML private ChoiceBox<String> B_choice;
    @FXML private TableView<BookEntry> Books_View;
    @FXML private TableColumn<BookEntry, Integer> isbn_book;
    @FXML private TableColumn<BookEntry, String> title_book;
    @FXML private TableColumn<BookEntry, String> publisher_book;
    @FXML private TableColumn<BookEntry, Integer> price_book;
    @FXML private AnchorPane Book_Info;
    @FXML private Label BI_isbn;
    @FXML private Label BI_quantity;
    @FXML private Label BI_title;
    @FXML private Label BI_price;
    @FXML private Label BI_threshold;
    @FXML private Label BI_date;
    @FXML private Label BI_publisher;
    @FXML private Label BI_author;
    @FXML private Label BI_category;
    @FXML private Button BI_add;
    @FXML private TextField BI_cartText;
    @FXML private Button BI_cartP;
    @FXML private Button BI_cartM;
    @FXML private Button BI_update;
    @FXML private Pane Cart_pane;
    @FXML private ImageView Cart_image;
    @FXML private Button Cart_button;
    @FXML private Pane Profile_pane;
    @FXML private ImageView Profile_image;
    @FXML private Button Profile_button;
    @FXML private Pane Notification_pane;
    @FXML private ImageView Notification_image;
    @FXML private Button Notification_button;
    @FXML private AnchorPane Cart_Scene;
    @FXML private AnchorPane Add_Cart;
    @FXML private Pane MQ_Scene;
    @FXML private TextField MQ_text;
    @FXML private Button MQ_add;
    @FXML private Button MQ_cancel;
    @FXML private Pane CO_Scene;
    @FXML private TextField CO_Cnum;
    @FXML private TextField CO_Cdate;
    @FXML private Button CO_add;
    @FXML private Button CO_cancel;
    @FXML private AnchorPane User_Info;
    @FXML private Label UI_user;
    @FXML private Label UI_first;
    @FXML private Label UI_last;
    @FXML private Label UI_email;
    @FXML private Label UI_password;
    @FXML private Label UI_phone;
    @FXML private Label UI_address;
    @FXML private Button UI_update;
    @FXML private Pane UI_Scene;
    @FXML private TextField UI_value;
    @FXML private Button UI_add;
    @FXML private Button UI_cancel;
    @FXML private ChoiceBox<String> UI_choice;
    @FXML private AnchorPane Notification_Scene;
    @FXML private AnchorPane Notif;
    @FXML private Pane Promote_pane;
    @FXML private ImageView Promote_image;
    @FXML private Button Promote_button;
    @FXML private Pane Stat_pane;
    @FXML private ImageView Stat_image;
    @FXML private Button Stat_button;
    @FXML private AnchorPane Promote_Scene;
    @FXML private AnchorPane promote;
    @FXML private Button UI_logout;
    @FXML private AnchorPane Stat_Scene;
    @FXML private AnchorPane Statistics;
    
    private Font myFont;
    private DBConnection DB;
    private Manager_Operations Manager;
    private double xOffset = 0;
	private double yOffset = 0;
	private Connection myConnection;
	private Book Show_Now;
	private CartHandler CartH;
	private User Current_user;
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		CartH = new CartHandler();
		try {
			DB = new DBConnection();
			myConnection = DB.getConnection();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Manager = new Manager_Operations(myConnection);
		Current_user = UserHandler.Get_Current_User();
		if (Current_user.isPrivilege()) {
			Show(true);
		} else {
			Show(false);
		}
		myFont = Font.loadFont(UserController.class.getResourceAsStream("/Fonts/Home.ttf"), 50.0);
		Publisher_lbl.setFont(myFont);
		Book_lbl.setFont(myFont);
		Numeric_Text(AP_phone, AP_result, 10, "Phone ");
		Limit_text(AP_name, AP_result, 30, "Name ");
		Limit_text(AP_address, AP_result, 40, "Address ");
		Limit_text(AB_title, AB_result, 20, "Title ");
		Limit_text(AB_author, AB_result, 30, "Author ");
		Limit_text(AB_publisher, AB_result, 30, "Publisher ");
		Numeric_Text(AB_isbn, AB_result, 11, "ISBN ");
		Numeric_Text(AB_price, AB_result, 10, "Price ");
		Numeric_Text(AB_quantity, AB_result, 10, "Quantity ");
		Numeric_Text(AB_threshold, AB_result, 10, "Threshold ");
		isbn_book.setCellValueFactory(new PropertyValueFactory<BookEntry, Integer>("Isbn"));
        title_book.setCellValueFactory(new PropertyValueFactory<BookEntry, String>("Title"));
        publisher_book.setCellValueFactory(new PropertyValueFactory<BookEntry, String>("Publisher"));
        price_book.setCellValueFactory(new PropertyValueFactory<BookEntry, Integer>("Price"));
        try {
			Books_View.setItems(getBooks());
		} catch (Exception e1) {
			e1.printStackTrace();
		}
        Books_View.setRowFactory( tv -> {
            TableRow<BookEntry> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    Show_Now =  Manager.searchBook(row.getItem().getIsbn(), "");
                    Set_Book_Info(Show_Now);
                    try {
						deafult();
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
                    Book_Info.setVisible(true);
                }
            });
            return row ;
        });
        B_choice.setItems(FXCollections.observableArrayList("ISBN", "Title", "Category", "Author", "Publisher"));
        AB_category.setItems(FXCollections.observableArrayList("Science", "Art", "Religion", "History", "Geography"));
        UI_choice.setItems(FXCollections.observableArrayList("Email", "FirstName", "LastName", "Password", "Phone", "Address"));
	}
	
	private void Show(boolean b) {
		Add_button.setVisible(b);
		Add_pane.setVisible(b);
		Add_image.setVisible(b);
		AddW_button.setVisible(b);
		AddW_image.setVisible(b);
		AddW_pane.setVisible(b);
		Notification_button.setVisible(b);
		Notification_image.setVisible(b);
		Notification_pane.setVisible(b);
		Stat_button.setVisible(b);
		Stat_image.setVisible(b);
		Stat_pane.setVisible(b);
		Promote_button.setVisible(b);
		Promote_image.setVisible(b);
		Promote_pane.setVisible(b);
		BI_threshold.setVisible(b);
		BI_update.setVisible(b);
	}

	@FXML
    void Add_scene(MouseEvent event) throws FileNotFoundException {
		deafult();
		Book_setActive(true);
    }

    @FXML
    void Browse_Scene(MouseEvent event) throws FileNotFoundException {
    	deafult();
    	Browse_setActive(true);
    }
    
    @FXML
    void Add_Writer_Scene(MouseEvent event) throws FileNotFoundException {
    	deafult();
    	Publisher_setActive(true);
    }
    
    @FXML
    void Profile_scene(MouseEvent event) throws FileNotFoundException {
		deafult();
		Profile_setActive(true);
		UI_user.setText(Current_user.getUsername());
		UI_first.setText("First Name: " + Current_user.getFirstname());
		UI_last.setText("Last Name: " + Current_user.getLastname());
		UI_email.setText("E-Mail: " + Current_user.getEmail());
		UI_password.setText("Password: " + Current_user.getPassword());
		UI_address.setText("Address: " + Current_user.getAddress());
		UI_phone.setText("Phone: " + Current_user.getPhone());
    }

    @FXML
    void Cart_Scene(MouseEvent event) throws FileNotFoundException {
    	deafult();
    	Cart_setActive(true);
    	All_Shopping();
    }
    
    @FXML
    void Notification_Scene(MouseEvent event) throws FileNotFoundException {
    	deafult();
    	Notification_setActive(true);
    	All_Notif();
    }
    
    @FXML
    void Promote_Scene(MouseEvent event) throws FileNotFoundException {
    	deafult();
    	Promote_setActive(true);
    	All_Normal_Users();
    }
    
    @FXML
    void Statistics_Scene(MouseEvent event) throws FileNotFoundException {
    	deafult();
    	Statistics_setActive(true);
    	All_Statistics();
    }
    
    @FXML
    void Add_New_Publisher(MouseEvent event){
    	Publisher publisher = new Publisher(AP_name.getText(), AP_address.getText(), AP_phone.getText());
    	AP_name.setText("");
    	AP_phone.setText("");
    	AP_address.setText("");
    	Manager.Add_Publisher(publisher);
    }
    
    @FXML
    private void Add_New_Book(MouseEvent event) {
    	Book book = new Book(Integer.valueOf(AB_isbn.getText()), AB_title.getText(), AB_author.getText(), AB_publisher.getText(),
    			Integer.valueOf(AB_price.getText()), AB_category.getSelectionModel().getSelectedItem(),
    			Integer.valueOf(AB_quantity.getText()), Integer.valueOf(AB_threshold.getText()), java.sql.Date.valueOf(AB_date.getValue()));
    	AB_author.setText("");
    	AB_isbn.setText("");
    	AB_price.setText("");
    	AB_publisher.setText("");
    	AB_quantity.setText("");
    	AB_threshold.setText("");
    	AB_title.setText("");
    	AB_category.setValue("");
    	AB_date.setValue(null);
    	Manager.Add_Book(book);
    }
    
    @FXML
    void Minus(MouseEvent event) {
    	int i = Integer.valueOf(BI_cartText.getText());
		if (i - 1 >= 1) {
			BI_cartText.setText(String.valueOf(i - 1));
		}
    }

    @FXML
    void Plus(MouseEvent event) {
    	int i = Integer.valueOf(BI_cartText.getText());
		if (i + 1 <= 9) {
			BI_cartText.setText(String.valueOf(i + 1));
		}
    }

    @FXML
    void Update_Book(MouseEvent event) {
    	MQ_Scene.setVisible(true);
    }
    
    @FXML
    void Update_Quantity(MouseEvent event) {
    	int New = Integer.valueOf(MQ_text.getText()) + Show_Now.getQuantity();
    	Manager.modifyBook(Show_Now.getISBN(), New);
    	BI_quantity.setText("Available : " + (Integer.valueOf(MQ_text.getText()) + Show_Now.getQuantity()));
    	MQ_Scene.setVisible(false);
    }
    
    @FXML
    void Cancel_Update_Quantity(MouseEvent event) {
    	MQ_Scene.setVisible(false);
    }
    
    @FXML
    void Check_Out(MouseEvent event) {
    	Add_Cart.getChildren().clear();
		ArrayList<Cart> c = CartH.get_Cart();
		for (int i = 0; i < c.size(); i++) {
			if (c.get(i).getBook().getQuantity() < c.get(i).getQuantity()) {
				return;
			}
		}
		for (int i = 0; i < c.size(); i++) {
    		Manager.modifyBook(c.get(i).getBook().getISBN(), c.get(i).getBook().getQuantity() - c.get(i).getQuantity());
    	}
		Manager.addCustomerOrder(Current_user.getUsername(), c, CartH.get_Total_Cost());
		CartH.Remove_All();
		All_Shopping();
		CO_Scene.setVisible(false);
    }
    
    @FXML
    void Cancel_Check_Out(MouseEvent event) {
    	CO_Scene.setVisible(false);
    }
    
    @FXML
    void Add_To_Cart(MouseEvent event) {
    	CartH.Add_Item(new Cart(Integer.valueOf(BI_cartText.getText()), Show_Now));
    }
    
    @FXML
	private void exit(MouseEvent event) {
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.close();
	}
    
    @FXML
	private void Minimize(MouseEvent event) {
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setIconified(true);
	}
    
    @FXML
    void Scene_Drag(MouseEvent event) {
    	Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    	primaryStage.setOpacity(.7);
		primaryStage.setX(event.getScreenX() - xOffset);
		primaryStage.setY(event.getScreenY() - yOffset);
    }

    @FXML
    void Scene_Pressed(MouseEvent event) {
    	xOffset = event.getSceneX();
		yOffset = event.getSceneY();
    }

    @FXML
    void Scene_Released(MouseEvent event) {
    	Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    	primaryStage.setOpacity(1);
    }
    
    @FXML
    void Search(MouseEvent event) {
    	if (B_choice.getSelectionModel().getSelectedItem() == "ISBN") {
    		Book k = Manager.searchBook(Integer.valueOf(B_search.getText()), "");
    		ObservableList<BookEntry> Books = FXCollections.observableArrayList();
    		Books.add(new BookEntry(k.getISBN(), k.getTitle(), k.getPublisher(), k.getPrice()));
    		Books_View.setItems(Books);
    	} else if (B_choice.getSelectionModel().getSelectedItem() == "Title") {
    		Book k = Manager.searchBook(0, B_search.getText());
    		ObservableList<BookEntry> Books = FXCollections.observableArrayList();
    		Books.add(new BookEntry(k.getISBN(), k.getTitle(), k.getPublisher(), k.getPrice()));
    		Books_View.setItems(Books);
    	} else {
    		Books_View.setItems(Manager.searchGroup(B_choice.getSelectionModel().getSelectedItem(), B_search.getText()));
    	}
    }
    
    @FXML
    void All_Books(MouseEvent event) {
    	Books_View.setItems(getBooks());
    }
    
    @FXML
    void Update_Info(MouseEvent event) {
    	UI_Scene.setVisible(true);
    }
    
    @FXML
    void Update_Personal(MouseEvent event) {
    	switch (UI_choice.getSelectionModel().getSelectedItem()) {
		case "Email":
			UserHandler.change_email_of(Current_user.getUsername(), UI_value.getText());
			break;
		case "FirstName":
			UserHandler.change_first_name_of(Current_user.getUsername(), UI_value.getText());
			break;
		case "LastName":
			UserHandler.change_last_name_of(Current_user.getUsername(), UI_value.getText());
			break;
		case "Password":
			UserHandler.change_password_of(Current_user.getUsername(), UI_value.getText());
			break;
		case "Phone":
			UserHandler.change_phoneNum_of(Current_user.getUsername(), UI_value.getText());
			break;
		case "Address":
			UserHandler.change_shipping_address_of(Current_user.getUsername(), UI_value.getText());
			break;
		default:
			break;
		}
		UI_Scene.setVisible(false);
    }
    
    @FXML
    void LogOut(MouseEvent event) throws IOException {
    	CartH.Remove_All();
    	Parent view = FXMLLoader.load(getClass().getResource("Login.fxml"));
    	Scene scene = new Scene(view);
    	scene.setFill(Color.TRANSPARENT);
    	Stage stage = new Stage();
    	stage.setScene(scene);
    	stage.initStyle(StageStyle.TRANSPARENT);
		stage.show();
    	Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.close();
    }
    
    @FXML
    void Cancel_Update(MouseEvent event) {
    	UI_Scene.setVisible(false);
    }
    
    private void deafult() throws FileNotFoundException {
    	Browse_setActive(false);
    	Book_setActive(false);
    	Publisher_setActive(false);
    	Profile_setActive(false);
    	Notification_setActive(false);
    	Cart_setActive(false);
    	Promote_setActive(false);
    	Statistics_setActive(false);
    	Book_Info.setVisible(false);
    }
    
    private void Browse_setActive(boolean Active) {
    	if (!Active) {
    		Browse_Scene.setVisible(false);
    		Browse_pane.setStyle("-fx-background-color:  transparent; -fx-background-radius: 20 20 20 20;");
        	Browse_button.setTextFill(Color.BLACK);
        	Browse_image.setImage(new Image(UserController.class.getResourceAsStream("/Images/Search_Black.png")));
    	} else {
    		Browse_Scene.setVisible(true);
    		Browse_pane.setStyle("-fx-background-color:  #000000; -fx-background-radius: 20 20 20 20;");
        	Browse_button.setTextFill(Color.WHITE);
        	Browse_image.setImage(new Image(UserController.class.getResourceAsStream("/Images/Search_White.png")));
    	}
    }
    
    private void Book_setActive(boolean Active) {
    	if (!Active) {
    		Add_Book_Scene.setVisible(false);
    		Add_pane.setStyle("-fx-background-color:  transparent; -fx-background-radius: 20 20 20 20;");
        	Add_button.setTextFill(Color.BLACK);
        	Add_image.setImage(new Image(UserController.class.getResourceAsStream("/Images/Add_Book_black.png")));
    	} else {
    		Add_Book_Scene.setVisible(true);
    		Add_pane.setStyle("-fx-background-color:  #000000; -fx-background-radius: 20 20 20 20;");
        	Add_button.setTextFill(Color.WHITE);
        	Add_image.setImage(new Image(UserController.class.getResourceAsStream("/Images/Add_Book_White.png")));
    	}
    }
    
    private void Publisher_setActive(boolean Active) {
    	if (!Active) {
    		Add_Publisher_Scene.setVisible(false);
    		AddW_pane.setStyle("-fx-background-color:  transparent; -fx-background-radius: 20 20 20 20;");
        	AddW_button.setTextFill(Color.BLACK);
        	AddW_image.setImage(new Image(UserController.class.getResourceAsStream("/Images/Add_Writer_Black.png")));
    	} else {
    		Add_Publisher_Scene.setVisible(true);
        	AddW_pane.setStyle("-fx-background-color:  #000000; -fx-background-radius: 20 20 20 20;");
        	AddW_button.setTextFill(Color.WHITE);
        	AddW_image.setImage(new Image(UserController.class.getResourceAsStream("/Images/Add_Writer_White.png")));
    	}
    }
    
    private void Profile_setActive(boolean Active) {
    	if (!Active) {
    		User_Info.setVisible(false);
    		Profile_pane.setStyle("-fx-background-color:  transparent; -fx-background-radius: 20 20 20 20;");
        	Profile_button.setTextFill(Color.BLACK);
        	Profile_image.setImage(new Image(UserController.class.getResourceAsStream("/Images/Profile_Black.png")));
    	} else {
    		User_Info.setVisible(true);
    		Profile_pane.setStyle("-fx-background-color:  #000000; -fx-background-radius: 20 20 20 20;");
    		Profile_button.setTextFill(Color.WHITE);
    		Profile_image.setImage(new Image(UserController.class.getResourceAsStream("/Images/Profile_White.png")));
    	}
    }
    
    private void Cart_setActive(boolean Active) {
    	if (!Active) {
    		Cart_Scene.setVisible(false);
    		Cart_pane.setStyle("-fx-background-color:  transparent; -fx-background-radius: 20 20 20 20;");
        	Cart_button.setTextFill(Color.BLACK);
        	Cart_image.setImage(new Image(UserController.class.getResourceAsStream("/Images/Cart_Black.png")));
    	} else {
    		Cart_Scene.setVisible(true);
    		Cart_pane.setStyle("-fx-background-color:  #000000; -fx-background-radius: 20 20 20 20;");
    		Cart_button.setTextFill(Color.WHITE);
        	Cart_image.setImage(new Image(UserController.class.getResourceAsStream("/Images/Cart_White.png")));
    	}
    }
    
    private void Notification_setActive(boolean Active) {
    	if (!Active) {
    		Notification_Scene.setVisible(false);
    		Notification_pane.setStyle("-fx-background-color:  transparent; -fx-background-radius: 20 20 20 20;");
        	Notification_button.setTextFill(Color.BLACK);
        	Notification_image.setImage(new Image(UserController.class.getResourceAsStream("/Images/Notification_Black.png")));
    	} else {
    		Notification_Scene.setVisible(true);
    		Notification_pane.setStyle("-fx-background-color:  #000000; -fx-background-radius: 20 20 20 20;");
    		Notification_button.setTextFill(Color.WHITE);
        	Notification_image.setImage(new Image(UserController.class.getResourceAsStream("/Images/Notification_White.png")));
    	}
    }
    
    private void Promote_setActive(boolean Active) {
    	if (!Active) {
    		Promote_Scene.setVisible(false);
    		Promote_pane.setStyle("-fx-background-color:  transparent; -fx-background-radius: 20 20 20 20;");
        	Promote_button.setTextFill(Color.BLACK);
        	Promote_image.setImage(new Image(UserController.class.getResourceAsStream("/Images/Promote_B.png")));
    	} else {
    		Promote_Scene.setVisible(true);
    		Promote_pane.setStyle("-fx-background-color:  #000000; -fx-background-radius: 20 20 20 20;");
    		Promote_button.setTextFill(Color.WHITE);
    		Promote_image.setImage(new Image(UserController.class.getResourceAsStream("/Images/Promote_W.png")));
    	}
    }
    
    private void Statistics_setActive(boolean Active) {
    	if (!Active) {
    		Stat_Scene.setVisible(false);
    		Stat_pane.setStyle("-fx-background-color:  transparent; -fx-background-radius: 20 20 20 20;");
    		Stat_button.setTextFill(Color.BLACK);
    		Stat_image.setImage(new Image(UserController.class.getResourceAsStream("/Images/Statistics_B.png")));
    	} else {
    		Stat_Scene.setVisible(true);
    		Stat_pane.setStyle("-fx-background-color:  #000000; -fx-background-radius: 20 20 20 20;");
    		Stat_button.setTextFill(Color.WHITE);
    		Stat_image.setImage(new Image(UserController.class.getResourceAsStream("/Images/Statistics_W.png")));
    	}
    }
    
    private void All_Shopping() {
    	int Max_Y = 10;
    	ArrayList<Cart> cart = CartH.get_Cart();
    	for (int i = 0; i < cart.size(); i++) {
    		Pane p = Add_Pane(Max_Y, cart.get(i));
    		Add_Cart.getChildren().add(p);
    		Max_Y += 160;
    	}
    	Label l = new Label("Total Cost : " + CartH.get_Total_Cost());
    	l.setLayoutX(150);
    	l.setLayoutY(Max_Y);
    	Add_Cart.getChildren().add(l);
    	Button b = new Button("Check Out");
    	b.setLayoutX(450);
    	b.setLayoutY(Max_Y);
    	b.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				CO_Scene.setVisible(true);
			}
		});
    	Add_Cart.getChildren().add(b);
    	Add_Cart.setPrefHeight(Max_Y + 100);
    }
    
    private Pane Add_Pane(int LayoutY, Cart cart) {
    	Pane p = new Pane();
    	p.setStyle("-fx-background-color:  #999999; -fx-background-radius: 20 20 20 20;");
    	p.setPrefHeight(150);
    	p.setPrefWidth(690);
    	p.setLayoutX(10);
    	p.setLayoutY(LayoutY);
    	Label T = new Label(cart.getBook().getTitle());
    	T.setLayoutX(20);
    	T.setLayoutY(15);
    	T.setFont(new Font("System", 20));
    	T.setStyle("-fx-font-weight: bold;");
    	p.getChildren().add(T);
    	Label Pu = new Label(cart.getBook().getPublisher());
    	Pu.setLayoutX(20);
    	Pu.setLayoutY(50);
    	Pu.setFont(new Font("System", 18));
    	p.getChildren().add(Pu);
    	Label Pr = new Label((cart.getQuantity() * cart.getBook().getPrice()) + " L.E");
    	Pr.setLayoutX(60);
    	Pr.setLayoutY(90);
    	Pr.setFont(new Font("System", 24));
    	p.getChildren().add(Pr);
    	Label Q = new Label(String.valueOf(cart.getQuantity()));
    	Q.setLayoutX(520);
    	Q.setLayoutY(35);
    	Q.setStyle("-fx-background-color: #FFFFFF;");
    	Q.setPrefHeight(30);
    	Q.setPrefWidth(60);
    	Q.setAlignment(Pos.CENTER);
    	p.getChildren().add(Q);
    	Button Plus = new Button("+");
    	Plus.setLayoutX(579);
    	Plus.setLayoutY(34);
    	Plus.setPrefWidth(50);
    	Plus.setPrefHeight(30);
    	Plus.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				int i = Integer.valueOf(Q.getText());
				if (i + 1 <= 9) {
					CartH.Update_Item(cart, true);
					Add_Cart.getChildren().clear();
					All_Shopping();
				}
			}
		});
    	p.getChildren().add(Plus);
    	Button Min = new Button("-");
    	Min.setLayoutX(471);
    	Min.setLayoutY(34);
    	Min.setPrefWidth(50);
    	Min.setPrefHeight(30);
    	Min.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				int i = Integer.valueOf(Q.getText());
				if (i - 1 >= 1) {
					CartH.Update_Item(cart, false);
					Add_Cart.getChildren().clear();
					All_Shopping();
				}
			}
		});
    	p.getChildren().add(Min);
    	Button Remove = new Button("Remove");
    	Remove.setStyle("-fx-background-color: #FF0000; -fx-background-radius: 20 20 20 20");
    	Remove.setLayoutX(490);
    	Remove.setLayoutY(92);
    	Remove.setPrefWidth(127);
    	Remove.setPrefHeight(31);
    	Remove.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				CartH.Remove_Item(cart);
				Add_Cart.getChildren().clear();
				All_Shopping();
			}
		});
    	p.getChildren().add(Remove);
    	return p;
    }
    
    private void All_Notif() {
    	int Max_Y = 10;
    	ArrayList<BookOrderEntry> b = Manager.getBookOrders();
    	for (int i = 0; i < b.size(); i++) {
    		Pane p = ADD_NOTIFY(Max_Y, b.get(i));
    		Notif.getChildren().add(p);
    		Max_Y += 160;
    	}
    	Notif.setPrefHeight(Max_Y);
    }
    
    private Pane ADD_NOTIFY(int LayoutY, BookOrderEntry book) {
    	Pane p = new Pane();
    	p.setStyle("-fx-background-color:  #999999; -fx-background-radius: 20 20 20 20;");
    	p.setPrefHeight(150);
    	p.setPrefWidth(690);
    	p.setLayoutX(10);
    	p.setLayoutY(LayoutY);
    	Label T = new Label("Book ISBN: " + book.getBookISBN());
    	T.setLayoutX(20);
    	T.setLayoutY(10);
    	T.setFont(new Font("System", 24));
    	T.setStyle("-fx-font-weight: bold;");
    	p.getChildren().add(T);
    	Label Pu = new Label("Order Date: " + book.getOrderDate());
    	Pu.setLayoutX(20);
    	Pu.setLayoutY(45);
    	Pu.setFont(new Font("System", 22));
    	p.getChildren().add(Pu);
    	Label Pr = new Label("Order Time: " + book.getOrderTime());
    	Pr.setLayoutX(20);
    	Pr.setLayoutY(80);
    	Pr.setFont(new Font("System", 22));
    	p.getChildren().add(Pr);
    	Label Q = new Label("Quantity: " + book.getQuantity());
    	Q.setLayoutX(20);
    	Q.setLayoutY(110);
    	Q.setFont(new Font("System", 24));
    	Q.setStyle("-fx-font-weight: bold;");
    	p.getChildren().add(Q);
    	Button Plus = new Button("Confirm");
    	Plus.setLayoutX(500);
    	Plus.setLayoutY(55);
    	Plus.setPrefWidth(130);
    	Plus.setPrefHeight(40);
    	Plus.setStyle("-fx-background-color:  #00FF00; -fx-background-radius: 20 20 20 20;");
    	Plus.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Manager.confirmOrder(book.getBookISBN());
				Notif.getChildren().clear();
				All_Notif();
			}
		});
    	p.getChildren().add(Plus);
    	return p;
    }
    
    private void All_Normal_Users() {
    	int Max_Y = 10;
    	ArrayList<User> b = UserHandler.getAllUsers();
    	for (int i = 0; i < b.size(); i++) {
    		Pane p = Promote_User(Max_Y, b.get(i));
    		promote.getChildren().add(p);
    		Max_Y += 160;
    	}
    	promote.setPrefHeight(Max_Y);
    }
    
    private Pane Promote_User(int LayoutY, User user) {
    	Pane p = new Pane();
    	p.setStyle("-fx-background-color:  #999999; -fx-background-radius: 20 20 20 20;");
    	p.setPrefHeight(150);
    	p.setPrefWidth(690);
    	p.setLayoutX(10);
    	p.setLayoutY(LayoutY);
    	Label T = new Label("UserName: " + user.getUsername());
    	T.setLayoutX(20);
    	T.setLayoutY(10);
    	T.setFont(new Font("System", 20));
    	p.getChildren().add(T);
    	Label Pu = new Label("Name: " + user.getFirstname() + " " + user.getLastname());
    	Pu.setLayoutX(20);
    	Pu.setLayoutY(35);
    	Pu.setFont(new Font("System", 20));
    	p.getChildren().add(Pu);
    	Label Pr = new Label("Email: " + user.getEmail());
    	Pr.setLayoutX(20);
    	Pr.setLayoutY(60);
    	Pr.setFont(new Font("System", 20));
    	p.getChildren().add(Pr);
    	Label Q = new Label("Phone: " + user.getPhone());
    	Q.setLayoutX(20);
    	Q.setLayoutY(85);
    	Q.setFont(new Font("System", 20));
    	p.getChildren().add(Q);
    	Label M = new Label("Address: " + user.getAddress());
    	M.setLayoutX(20);
    	M.setLayoutY(110);
    	M.setFont(new Font("System", 20));
    	p.getChildren().add(M);
    	Button Plus = new Button("Promote");
    	Plus.setLayoutX(500);
    	Plus.setLayoutY(55);
    	Plus.setPrefWidth(130);
    	Plus.setPrefHeight(40);
    	Plus.setStyle("-fx-background-color:  #00FF00; -fx-background-radius: 20 20 20 20;");
    	Plus.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				UserHandler.promote_user(user.getUsername());
				promote.getChildren().clear();
				All_Normal_Users();
			}
		});
    	p.getChildren().add(Plus);
    	return p;
    }
    
    private void All_Statistics() {
    	Statistics.getChildren().clear();
    	int Max_Y = 80, X = 10, i = 0;
    	ArrayList<BookSale> b = Manager.getBookSales();
    	Label T = new Label("Book Sales For Previous Month");
    	T.setLayoutX(10);
    	T.setLayoutY(10);
    	T.setPrefWidth(690);
    	T.setPrefHeight(40);
    	T.setAlignment(Pos.CENTER);
    	T.setFont(new Font("System", 35));
    	T.setStyle("-fx-border-color: #000000; -fx-border-radius: 15; -fx-border-width: 1.5;");
    	Statistics.getChildren().add(T);
    	for (i = 0; i < b.size(); i++) {
    		Pane p = BookSale_pane(Max_Y, X, i + 1, b.get(i));
    		Statistics.getChildren().add(p);
    		if (i % 2 == 1)
    			Max_Y += 190;
    		if (X == 10)
    			X = 370;
    		else
    			X = 10;
    	}
    	if ((i - 1) % 2 == 0 && i > 0)
    		Max_Y += 190;
    	i = 0;
    	X = 10;
    	ArrayList<Book> m = Manager.getTopSellingBooks();
    	Label M = new Label("Top Selling Books For the last 3 Months");
    	M.setLayoutX(10);
    	M.setLayoutY(Max_Y + 20);
    	M.setPrefWidth(690);
    	M.setPrefHeight(40);
    	M.setAlignment(Pos.CENTER);
    	M.setFont(new Font("System", 35));
    	M.setStyle("-fx-border-color: #000000; -fx-border-radius: 15; -fx-border-width: 1.5;");
    	Statistics.getChildren().add(M);
    	Max_Y += 90;
    	for (i = 0; i < m.size(); i++) {
    		Pane p = Book_pane(Max_Y, X, i + 1, m.get(i));
    		Statistics.getChildren().add(p);
    		if (i % 2 == 1)
    			Max_Y += 140;
    		if (X == 10)
    			X = 370;
    		else
    			X = 10;
    	}
    	if ((i - 1) % 2 == 0 && i > 0)
    		Max_Y += 140;
    	i = 0;
    	X = 10;
    	ArrayList<UserEntry> n = Manager.getTopUsers();
    	Label N = new Label("Top Users For the last 3 Months");
    	N.setLayoutX(10);
    	N.setLayoutY(Max_Y + 20);
    	N.setPrefWidth(690);
    	N.setPrefHeight(40);
    	N.setAlignment(Pos.CENTER);
    	N.setFont(new Font("System", 35));
    	N.setStyle("-fx-border-color: #000000; -fx-border-radius: 15; -fx-border-width: 1.5;");
    	Statistics.getChildren().add(N);
    	Max_Y += 90;
    	for (i = 0; i < n.size(); i++) {
    		Pane p = User_pane(Max_Y, X, i + 1, n.get(i));
    		Statistics.getChildren().add(p);
    		if (i % 2 == 1)
    			Max_Y += 115;
    		if (X == 10)
    			X = 370;
    		else
    			X = 10;
    	}
    	if ((i - 1) % 2 == 0 && i > 0)
    		Max_Y += 115;
    	Button print = new Button("Save Report");
    	print.setLayoutX(280);
    	print.setLayoutY(Max_Y + 10);
    	print.setPrefWidth(150);
    	print.setPrefHeight(40);
    	print.setStyle("-fx-background-radius: 20 20 20 20;");
    	print.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// Jasper Library
				try {
					List<JasperPrint> ll = new ArrayList<>();
					JRBeanCollectionDataSource items_1 = new JRBeanCollectionDataSource(b, false);
					Map<String, Object> parameters = new HashMap<String, Object>();
					parameters.put("CollectionBean", items_1);
					InputStream io = UserController.class.getResourceAsStream("/application/Report.jrxml");
					JasperDesign JD = JRXmlLoader.load(io);
					JasperReport JR = JasperCompileManager.compileReport(JD);
					JasperPrint JPR = JasperFillManager.fillReport(JR,parameters, new JREmptyDataSource());
					ll.add(JPR);
					/*JasperViewer.viewReport(JPR);
					OutputStream O = new FileOutputStream(new File("M:\\Report.pdf"));
					JasperExportManager.exportReportToPdfStream(JPR, O);*/
					
					JRBeanCollectionDataSource items_2 = new JRBeanCollectionDataSource(m, false);
					Map<String, Object> parameters_2 = new HashMap<String, Object>();
					parameters_2.put("CollectionBean", items_2);
					InputStream io_2 = UserController.class.getResourceAsStream("/application/Report2.jrxml");
					JasperDesign JD_2 = JRXmlLoader.load(io_2);
					JasperReport JR_2 = JasperCompileManager.compileReport(JD_2);
					JasperPrint JPR_2 = JasperFillManager.fillReport(JR_2,parameters_2, new JREmptyDataSource());
					ll.add(JPR_2);
					/*JasperViewer.viewReport(JPR_2);
					OutputStream O_2 = new FileOutputStream(new File("M:\\Report_2.pdf"));
					JasperExportManager.exportReportToPdfStream(JPR_2, O_2);*/
					
					JRBeanCollectionDataSource items_3 = new JRBeanCollectionDataSource(n, false);
					Map<String, Object> parameters_3 = new HashMap<String, Object>();
					parameters_3.put("CollectionBean", items_3);
					InputStream io_3 = UserController.class.getResourceAsStream("/application/Report3.jrxml");
					JasperDesign JD_3 = JRXmlLoader.load(io_3);
					JasperReport JR_3 = JasperCompileManager.compileReport(JD_3);
					JasperPrint JPR_3 = JasperFillManager.fillReport(JR_3,parameters_3, new JREmptyDataSource());
					ll.add(JPR_3);
					/*JasperViewer.viewReport(JPR_3);
					OutputStream O_3 = new FileOutputStream(new File("M:\\Report_3.pdf"));
					JasperExportManager.exportReportToPdfStream(JPR_3, O_3);*/
					
					OutputStream O_0 = new FileOutputStream(new File("C:\\Users\\Ahmed\\Desktop\\Report.pdf"));
					JRPdfExporter exporter = new JRPdfExporter();
					exporter.setParameter(JRExporterParameter.JASPER_PRINT_LIST, ll);
					exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, O_0);
					exporter.exportReport();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
    	Statistics.getChildren().add(print);
    	Statistics.setPrefHeight(Max_Y + 60);
    }
    
    private Pane User_pane(int LayoutY, int LayoutX, int i, UserEntry userEntry) {
    	Pane p = new Pane();
    	p.setStyle("-fx-background-color:  #999999; -fx-background-radius: 20 20 20 20;");
    	p.setPrefHeight(105);
    	p.setPrefWidth(330);
    	p.setLayoutX(LayoutX);
    	p.setLayoutY(LayoutY);
    	Label T = new Label("UserName: " + userEntry.getUsername());
    	T.setLayoutX(20);
    	T.setLayoutY(10);
    	T.setFont(new Font("System", 20));
    	p.getChildren().add(T);
    	Label Pu = new Label("Total Amount: " + userEntry.getTotalAmount());
    	Pu.setLayoutX(20);
    	Pu.setLayoutY(35);
    	Pu.setFont(new Font("System", 20));
    	p.getChildren().add(Pu);
    	Label num = new Label(String.valueOf(i));
    	num.setLayoutX(145);
    	num.setLayoutY(65);
    	num.setPrefWidth(40);
    	num.setPrefHeight(40);
    	num.setAlignment(Pos.CENTER);
    	num.setFont(new Font("System", 20));
    	num.setStyle("-fx-border-color: #000000; -fx-border-radius: 15; -fx-border-width: 1.5;");
    	p.getChildren().add(num);
    	return p;
	}

	private Pane Book_pane(int LayoutY, int LayoutX, int i, Book book) {
    	Pane p = new Pane();
    	p.setStyle("-fx-background-color:  #999999; -fx-background-radius: 20 20 20 20;");
    	p.setPrefHeight(130);
    	p.setPrefWidth(330);
    	p.setLayoutX(LayoutX);
    	p.setLayoutY(LayoutY);
    	Label T = new Label("ISBN: " + book.getISBN());
    	T.setLayoutX(20);
    	T.setLayoutY(10);
    	T.setFont(new Font("System", 20));
    	p.getChildren().add(T);
    	Label Pu = new Label("Title: " + book.getTitle());
    	Pu.setLayoutX(20);
    	Pu.setLayoutY(35);
    	Pu.setFont(new Font("System", 20));
    	p.getChildren().add(Pu);
    	Label Pr = new Label("Quantity: " + book.getQuantity());
    	Pr.setLayoutX(20);
    	Pr.setLayoutY(60);
    	Pr.setFont(new Font("System", 20));
    	p.getChildren().add(Pr);
    	Label num = new Label(String.valueOf(i));
    	num.setLayoutX(145);
    	num.setLayoutY(90);
    	num.setPrefWidth(40);
    	num.setPrefHeight(40);
    	num.setAlignment(Pos.CENTER);
    	num.setFont(new Font("System", 20));
    	num.setStyle("-fx-border-color: #000000; -fx-border-radius: 15; -fx-border-width: 1.5;");
    	p.getChildren().add(num);
    	return p;
	}

	private Pane BookSale_pane(int LayoutY, int LayoutX, int i, BookSale book) {
    	Pane p = new Pane();
    	p.setStyle("-fx-background-color:  #999999; -fx-background-radius: 20 20 20 20;");
    	p.setPrefHeight(180);
    	p.setPrefWidth(330);
    	p.setLayoutX(LayoutX);
    	p.setLayoutY(LayoutY);
    	Label T = new Label("UserName: " + book.getUsername());
    	T.setLayoutX(20);
    	T.setLayoutY(10);
    	T.setFont(new Font("System", 20));
    	p.getChildren().add(T);
    	Label Pu = new Label("Book ISBN: " + book.getBookISBN());
    	Pu.setLayoutX(20);
    	Pu.setLayoutY(35);
    	Pu.setFont(new Font("System", 20));
    	p.getChildren().add(Pu);
    	Label Pr = new Label("Book Title: " + book.getTitle());
    	Pr.setLayoutX(20);
    	Pr.setLayoutY(60);
    	Pr.setFont(new Font("System", 20));
    	p.getChildren().add(Pr);
    	Label Q = new Label("Purchase At: " + book.getPurchaseTime() + " " + book.getPurchaseDate());
    	Q.setLayoutX(20);
    	Q.setLayoutY(85);
    	Q.setFont(new Font("System", 20));
    	p.getChildren().add(Q);
    	Label M = new Label("Book Quantity: " + book.getQuantity());
    	M.setLayoutX(20);
    	M.setLayoutY(110);
    	M.setFont(new Font("System", 20));
    	p.getChildren().add(M);
    	Label num = new Label(String.valueOf(i));
    	num.setLayoutX(145);
    	num.setLayoutY(140);
    	num.setPrefWidth(40);
    	num.setPrefHeight(40);
    	num.setAlignment(Pos.CENTER);
    	num.setFont(new Font("System", 20));
    	num.setStyle("-fx-border-color: #000000; -fx-border-radius: 15; -fx-border-width: 1.5;");
    	p.getChildren().add(num);
    	return p;
    }
    private void Set_Book_Info(Book book) {
    	BI_isbn.setText("ISBN: " + book.getISBN());
    	BI_quantity.setText("Available: " + book.getQuantity());
    	BI_threshold.setText("Threshold:  " + book.getThreshold());
    	BI_title.setText(book.getTitle());
    	BI_price.setText("Price :" + book.getPrice());
    	BI_author.setText("Author: " + book.getAuthor());
    	BI_publisher.setText("Publisher: " + book.getPublisher());
    	BI_date.setText("Publication Year: " + book.getDate());
    	BI_category.setText("Category :  " + book.getCategory());
    	BI_cartText.setText("1");
    }
    
    private void Numeric_Text(TextField TextField, Label ErrorLabel, int Lenght, String type) {
		TextField.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				ErrorLabel.setVisible(false);
				if (newValue.length() > Lenght) {
		        	TextField.setText(oldValue);
		        	ErrorLabel.setText(String.valueOf(Lenght) + " Numbers Only !!");
		        	ErrorLabel.setVisible(true);
		        }
				if (!newValue.matches("\\d*")) {
		            TextField.setText(newValue.replaceAll("[^\\d]", ""));
		            ErrorLabel.setText("Numbers Only !!");
		            ErrorLabel.setVisible(true);
		        } 
				
			}
			
		});
	}
    
    private void Limit_text(TextField TextField, Label ErrorLabel, int Lenght, String type) {
		TextField.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				ErrorLabel.setVisible(false);
				if (newValue.length() > Lenght) {
					TextField.setText(oldValue);
					ErrorLabel.setText(type + "Contains " + String.valueOf(Lenght) + "characters Only !!");
					ErrorLabel.setVisible(true);
				}
				
			}
		});
	}
    
    private ObservableList<BookEntry>  getBooks()
    {   
        return Manager.Get_All_books();
    }
}
