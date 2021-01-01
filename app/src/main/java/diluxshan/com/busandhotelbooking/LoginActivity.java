package diluxshan.com.busandhotelbooking;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import diluxshan.com.busandhotelbooking.AddBusFragment.AddBusTab;
import diluxshan.com.busandhotelbooking.UserviewFragment.UserDashboardTab;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class LoginActivity extends AppCompatActivity {
    private DatabaseHelper myDb;
     static EditText username,password;
    Button Login,Signin,adminLogin;
    public static  String USERNAME;

    OkHttpClient client = new OkHttpClient();

     static TextView txtString;

    public String url= "https://spring-test-seeker.herokuapp.com/genres";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        myDb = new DatabaseHelper(this);
        username = findViewById(R.id.login_user);
        password = findViewById(R.id.login_pass);
        Login = findViewById(R.id.bt_login);
        Signin = findViewById(R.id.bt_signin);
        adminLogin = findViewById(R.id.Login_as_Admin);

        if(myDb.getLoggedUser()!=""){
            Intent intent = new Intent(getApplicationContext(),UserDashboardTab.class);
            startActivity(intent);
            finish();
        }

        Signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),SignInActivity.class);
                startActivity(intent);
            }
        });

        Login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String user = String.valueOf(username.getText());
                String passwrd = String.valueOf(password.getText());
                boolean isInserted = myDb.CheckAccount(user,passwrd);

                if(isInserted == true) {
                    USERNAME = user;
                    myDb.LoggedInUser("true",user);
                    Intent intent = new Intent(getApplicationContext(),UserDashboardTab.class);
                    finish();
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                } else{
                    Toast.makeText(getApplication(),"Login fail !", Toast.LENGTH_LONG).show();
                }
            }
        });

        adminLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String user = String.valueOf(username.getText());
                String passwrd = String.valueOf(password.getText());

                if((user.equals("admin")&&passwrd.equals("admin"))){

                    Intent intent = new Intent(getApplicationContext(),AddBusTab.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(getApplication(),"Admin Login invalid !", Toast.LENGTH_LONG).show();
                }

            }
        });

        OkHttpHandler okHttpHandler= new OkHttpHandler();
        okHttpHandler.execute(url);

    }

    public static class OkHttpHandler extends AsyncTask {

        OkHttpClient client = new OkHttpClient();

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            System.out.println(o);

        }

        @Override
        protected Object doInBackground(Object[] objects) {

            Request.Builder builder = new Request.Builder();
            builder.url(objects[0].toString());
            Request request = builder.build();

            try {
                Response response = client.newCall(request).execute();
                return response.body().string();
            }catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }
    }
}
