package in.innotrek.www.mysqlite;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    DatabaseHelper databaseHelper;
    EditText editUsername,editPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        databaseHelper = new DatabaseHelper(this);
        editUsername = findViewById(R.id.editText);
        editPassword = findViewById(R.id.editText2);
    }

    public void TriggerSignUp(View view) {
        Intent intent = new Intent(this,SignupActivity.class);
        startActivity(intent);
    }
    public void getData(String username,String password){
        Cursor cursor = databaseHelper.getData();
        if(cursor.isClosed()) {
            Toast.makeText(this, "Empty Database", Toast.LENGTH_SHORT).show();
        }
        boolean userFound=false;
        while(cursor.moveToNext()){
            if(username.equals(cursor.getString(0)) && password.equals(cursor.getString(1))){
                userFound=true;
            }
        }
        if(userFound) {

            Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this,MainActivity.class);
            intent.putExtra("id",username);
            startActivity(intent);
        }
        else
        {
            Toast.makeText(this, "No User Entry Found. Click on SignUp", Toast.LENGTH_SHORT).show();
        }


    }

    public void TriggerLogin(View view) {
        if(editUsername.getText().length()!=0 && editPassword.getText().length()!=0){
            getData(editUsername.getText().toString().trim(),editPassword.getText().toString().trim());
        }
        else{
            Toast.makeText(this, "Enter Data in both the fields", Toast.LENGTH_SHORT).show();
        }
    }
}
