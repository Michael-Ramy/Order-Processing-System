package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

import application.DBConnection;
import Objects.User;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class LoginController implements Initializable{

	@FXML private AnchorPane scene;
    @FXML private Button signup;
    @FXML private Button signin;
    @FXML private Pane T_pane;
    @FXML private Label SI_lbl;
    @FXML private TextField SI_user;
    @FXML private TextField SI_password;
    @FXML private Button SI_sign;
    @FXML private Button SI_forget;
    @FXML private Label SI_error;
    @FXML private ImageView SI_userImage;
    @FXML private ImageView SI_passImage;
    @FXML private Label SU_lbl;
    @FXML private TextField SU_user;
    @FXML private TextField SU_password;
    @FXML private Button SU_sign;
    @FXML private Label SU_error;
    @FXML private TextField SU_fname;
    @FXML private TextField SU_lname;
    @FXML private TextField SU_email;
    @FXML private TextField SU_phone;
    @FXML private TextField SU_address;
    @FXML private ImageView pane_close;
    @FXML private ImageView pane_down;
    
    private double xOffset = 0;
	private double yOffset = 0;
	private Font myFont;
	private DBConnection DB;
	private Connection myConnection;
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Default();
		myFont = Font.loadFont(LoginController.class.getResourceAsStream("/Fonts/Avocado Creamy.ttf"), 50.0);
		SI_lbl.setFont(myFont);
		SU_lbl.setFont(myFont);
		try {
			DB = new DBConnection();
			myConnection = DB.getConnection();
			UserHandler.set_Connection(myConnection);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
    void SignIn(MouseEvent event) {
		SignIn_SetVisible(true);
		SignUp_SetVisible(false);
		TranslateTransition translate = new TranslateTransition();  
        translate.setByX(500);  
        translate.setDuration(Duration.millis(800));   
        translate.setAutoReverse(true);  
        translate.setNode(T_pane);  
        translate.play();
    }

    @FXML
    void SignUp(MouseEvent event) {
    	SignIn_SetVisible(false);
		SignUp_SetVisible(true);
    	TranslateTransition translate = new TranslateTransition();  
        translate.setByX(-500);  
        translate.setDuration(Duration.millis(800));   
        translate.setAutoReverse(true);  
        translate.setNode(T_pane);  
        translate.play();
    }
    
    @FXML
    private void SignIn_Action(MouseEvent event) throws IOException {
    	if (UserHandler.user_login(SI_user.getText(), SI_password.getText())) {
    		Parent view = FXMLLoader.load(getClass().getResource("User.fxml"));
        	Scene scene = new Scene(view);
        	scene.setFill(Color.TRANSPARENT);
        	Stage stage = new Stage();
        	stage.setScene(scene);
        	stage.initStyle(StageStyle.TRANSPARENT);
    		stage.show();
        	Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    		window.close();
    	} else {
    		SI_error.setText("username or password is incorrect");
    	}
    }
    
    @FXML
    private void SignUp_Action(MouseEvent event) throws IOException {
    	User u = new User(SU_user.getText(), SU_password.getText(), SU_fname.getText(), SU_lname.getText(),
    			SU_email.getText(), SU_phone.getText(), SU_address.getText(), false);
    	if (UserHandler.user_signUp(u)) {
    		SignIn(event);
    	} else {
    		SU_error.setText("SomeThing incorrect");
    	}
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
	private void exit(MouseEvent event) {
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.close();
	}
    
    @FXML
	private void Minimize(MouseEvent event) {
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setIconified(true);
	}

    private void SignIn_SetVisible(boolean visible) {
    	SI_lbl.setVisible(visible);
    	SI_user.setVisible(visible);
    	SI_sign.setVisible(visible);
    	SI_error.setVisible(visible);
    	SI_forget.setVisible(visible);
    	SI_password.setVisible(visible);
    	SI_passImage.setVisible(visible);
    	SI_userImage.setVisible(visible);
    }
    
    private void SignUp_SetVisible(boolean visible) {
    	SU_address.setVisible(visible);
    	SU_email.setVisible(visible);
    	SU_error.setVisible(visible);
    	SU_fname.setVisible(visible);
    	SU_lbl.setVisible(visible);
    	SU_lname.setVisible(visible);
    	SU_password.setVisible(visible);
    	SU_phone.setVisible(visible);
    	SU_sign.setVisible(visible);
    	SU_user.setVisible(visible);
    }
    
    private void Default() {
    	SI_userImage.setImage(new Image(LoginController.class.getResourceAsStream("/Images/User.png")));
    	SI_passImage.setImage(new Image(LoginController.class.getResourceAsStream("/Images/Password.png")));
    	pane_close.setImage(new Image(LoginController.class.getResourceAsStream("/Images/CloseW.png")));
    	pane_down.setImage(new Image(LoginController.class.getResourceAsStream("/Images/DownW.png")));
    	SignIn_SetVisible(true);
    	SignUp_SetVisible(false);
    }
}
