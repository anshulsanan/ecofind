//Wild Rangers
package eco.find;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    public EditText user;
    public EditText passward;
    //private TextView info;
    public Button login;
    public int attempt = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ViewPager viewPager = (ViewPager)findViewById(R.id.imgSlider);
        ImageAdapter adapter = new ImageAdapter(this);
        viewPager.setAdapter(adapter);

        user = (EditText) findViewById(R.id.editText);
        passward = (EditText) findViewById(R.id.editText2);
        //info = (TextView) findViewById(R.id.textView);
        login = (Button) findViewById(R.id.button);

        login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                checkUser(user.getText().toString(),passward.getText().toString());
            }
        });



    }

    private void checkUser(String username, String Passward){

        if((username.equals("admin")) && (Passward.equals("1234"))){
            Intent intent = new Intent(Login.this,Homepage.class);
            startActivity(intent);
        }else{
            attempt--;

            Toast.makeText(getApplicationContext(),"No of attempts remaining: "+String.valueOf(attempt),Toast.LENGTH_LONG).show();

            if(attempt == 0){
                login.setEnabled(false);
                Toast.makeText(getApplicationContext(),"Please restart the app!",Toast.LENGTH_LONG).show();
            }

        }

    }
}
