package atmProject;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Gui extends Application {
	Scene scene1, scene2, scene3, scene4, scene5, scene6, scene7;
	int index = 0, j;
	ATM user = new ATM();
	String[] arr = new String[5];

	public static void main(String[] args) {
		launch(args);
	}

	public void setArray(String transaction) {
		int count = 4;
		while (count > 0) {
			arr[count] = arr[count - 1];
			count--;
		}
		arr[0] = transaction;
	}

	@Override
	public void start(Stage primaryStage) {
		//// draw scene1 (login)
		primaryStage.setTitle("Welcome to ATM");
		Label CardNumber = new Label("Card Number :");
		PasswordField CardNumberField = new PasswordField();
		Button submit = new Button("submit");
		Label validNum = new Label();
		GridPane grid = new GridPane();
		grid.add(CardNumber, 1, 0);
		grid.add(CardNumberField, 1, 1);
		grid.add(submit, 1, 2);
		grid.add(validNum, 1, 3);

		// draw scene2 (Menu)
		Label operation = new Label("Select your operation :");
		Button deposit = new Button("Deposit");
		Button withdraw = new Button("Withdraw");
		Button balance = new Button("Balance Inquiry");
		Button next = new Button("Next");
		Button prev = new Button("Previous");
		HBox H1 = new HBox(30);
		H1.getChildren().addAll(deposit, withdraw);
		HBox H2 = new HBox(30);
		H2.getChildren().addAll(prev, next);
		VBox layout = new VBox(20);
		layout.getChildren().addAll(operation, H1, H2, balance);

		// draw scene3 (balance)
		Label showBalance = new Label();
		Button back = new Button("Back");
		VBox layout2 = new VBox(20);
		layout2.getChildren().addAll(showBalance, back);

		// draw scene4 (deposit)
		Label dep = new Label("please enter the amount to deposit");
		TextField amount = new TextField();
		Button enter = new Button("Enter");
		Label text = new Label();
		Button back2 = new Button("Back");
		GridPane grid2 = new GridPane();
		grid2.add(dep, 1, 0);
		grid2.add(amount, 1, 1);
		grid2.add(enter, 2, 1);
		grid2.add(back2, 1, 2);
		grid2.add(text, 1, 3);

		// draw scene5 (withdrawal)
		Label draw = new Label("please enter the amount to withdraw");
		TextField withdrawAmount = new TextField();
		Button enter2 = new Button("Enter");
		Label text2 = new Label();
		Label error = new Label();
		Button back3 = new Button("Back");
		GridPane grid3 = new GridPane();
		grid3.add(draw, 1, 0);
		grid3.add(withdrawAmount, 1, 1);
		grid3.add(enter2, 2, 1);
		grid3.add(back3, 1, 2);
		grid3.add(text2, 1, 3);
		grid3.add(error, 1, 4);

		// draw scene6,scene7 (prev,next)
		Label prevLabel = new Label();
		Label nextLabel = new Label();
		Button back4 = new Button("Back");
		Button back5 = new Button("Back");
		VBox layout4 = new VBox(20);
		layout4.getChildren().addAll(prevLabel, back4);
		VBox layout5 = new VBox(20);
		layout5.getChildren().addAll(nextLabel, back5);

		// Login and validation
		submit.setOnAction(e -> {

			String cardnum = CardNumberField.getText();
			if (cardnum.equals("1111"))
				primaryStage.setScene(scene2);
			else
				validNum.setText("Card Number not found !,please try again");
		});

		// Deposit
		deposit.setOnAction(e -> {
			primaryStage.setScene(scene4);
		});
		enter.setOnAction(e -> {
			try {
				Double.parseDouble(amount.getText());
			} catch (NumberFormatException t) {
				text.setText("INVALID INPUT!");
			}
			double amt = Double.parseDouble(amount.getText());
			user.deposit(amt);
			text.setText("Transaction was made successfully!");
			setArray("Deposit : " + amt);
			index = 0;
		});

		// Withdrawal
		withdraw.setOnAction(e -> {
			primaryStage.setScene(scene5);
		});
		enter2.setOnAction(e -> {
			try {
				Double.parseDouble(withdrawAmount.getText());
			} catch (NumberFormatException t) {
				text2.setText("INVALID INPUT!");
			}
			double amt2 = Double.parseDouble(withdrawAmount.getText());
			if (amt2 <= user.getBalance()) {
				user.withdrawal(amt2);
				text2.setText("Money is withdrawn..");
				setArray("Withdrawal : " + amt2);
				index = 0;
			} else
				error.setText("ERROR!Balance not enough to cover this Amount");
		});

		// Balance Inquiry
		balance.setOnAction(e -> {
			primaryStage.setScene(scene3);
			showBalance.setText("Your Balance is : " + user.getBalance());
		});

		// previous operation
		prev.setOnAction(e -> {
			primaryStage.setScene(scene6);
			if (index > 4 || index < 0) {
				//index = 0 ;
				prevLabel.setText("No Further Transactions to show ! ");
			}
			else
				prevLabel.setText(" " + arr[index]);
			index++;
			j = index - 2;
		});

		// next operation
		next.setOnAction(e -> {
			primaryStage.setScene(scene7);
			if (j < 0 || j > 4)
				nextLabel.setText("No Further transactions to show!");
			else
				nextLabel.setText(" " + arr[j]);
			j--;
		});

		// using back batton to trasition to scene2
		back.setOnAction(e -> {
			primaryStage.setScene(scene2);

		});
		back2.setOnAction(e -> {
			primaryStage.setScene(scene2);

		});
		back3.setOnAction(e -> {
			primaryStage.setScene(scene2);

		});
		back4.setOnAction(e -> {
			primaryStage.setScene(scene2);
		});

		back5.setOnAction(e -> {
			primaryStage.setScene(scene2);
		});

		scene1 = new Scene(grid, 400, 300);
		scene2 = new Scene(layout, 400, 300);
		scene3 = new Scene(layout2, 400, 300);
		scene4 = new Scene(grid2, 400, 300);
		scene5 = new Scene(grid3, 400, 300);
		scene6 = new Scene(layout4, 400, 300);
		scene7 = new Scene(layout5, 400, 300);
		primaryStage.setScene(scene1);
		primaryStage.show();
	}
}
