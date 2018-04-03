package in.innotrek.www.mysqlite;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class SignupActivity extends AppCompatActivity {
    DatabaseHelper databaseHelper;
    TextView textView;
    EditText editUsername,editPassword,editConfirmPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        databaseHelper = new DatabaseHelper(this);
        textView = findViewById(R.id.textView);
        editUsername = findViewById(R.id.editText3);
        editPassword = findViewById(R.id.editText4);
        editConfirmPassword = findViewById(R.id.editText5);
    }

    public void createAccount(View view) {

        String username = editUsername.getText().toString().trim();
        String password = editPassword.getText().toString().trim();
        String confirmPassword = editConfirmPassword.getText().toString().trim();

        if(password.equals(confirmPassword)) {

            if(username.length()!=0){

                addData(username,password);
            }
            else {
                Toast.makeText(this, "Enter Username", Toast.LENGTH_SHORT).show();
            }
        }
        else {
            Toast.makeText(this, "Passwords Does not match.", Toast.LENGTH_SHORT).show();
        }


    }

    public void addData(String username,String password){
            boolean insertData = databaseHelper.addData(username,password);
            if(insertData){
                Toast.makeText(this, "User Created", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(this, "Something went wrong..!!", Toast.LENGTH_SHORT).show();
            }


    }

}
