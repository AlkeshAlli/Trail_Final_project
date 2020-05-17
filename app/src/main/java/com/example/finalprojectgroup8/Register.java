package com.example.finalprojectgroup8;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Register extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText firstname,lastname;
    EditText emailId, password;
    ImageView logo;
    Button btnSignUp;
    TextView tvSignIn;
    FirebaseAuth mFirebaseAuth;
    String roletext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mFirebaseAuth = FirebaseAuth.getInstance();
        firstname = findViewById(R.id.fname1);
        lastname = findViewById(R.id.lname1);
        emailId = findViewById(R.id.emailid1);
        password = findViewById(R.id.passwordd1);
        btnSignUp = findViewById(R.id.register);
        tvSignIn = findViewById(R.id.tvlogin1);
        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter =ArrayAdapter.createFromResource(this,R.array.registers,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);





        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fname = firstname.getText().toString();
                String lname = lastname.getText().toString();
                String email = emailId.getText().toString();
                String pwd = password.getText().toString();
                if (fname.isEmpty()){
                    firstname.setError(("please enter first name"));
                    firstname.requestFocus();
                }
                else if(lname.isEmpty()){
                    lastname.setError(("please enter last name"));
                    lastname.requestFocus();

                }
                else if(email.isEmpty()){
                    emailId.setError("Please enter email id");
                    emailId.requestFocus();
                }
                else  if(pwd.isEmpty()){
                    password.setError("Please enter your password");
                    password.requestFocus();
                }
                else if(pwd.length()<6){
                    password.setError("Password must be more than 6 characters");
                    password.requestFocus();
                }
                else  if(email.isEmpty() && pwd.isEmpty() && fname.isEmpty()&& lname.isEmpty()){
                    Toast.makeText(Register.this,"Fields Are Empty!",Toast.LENGTH_SHORT).show();
                }
                else  if(!(email.isEmpty() && pwd.isEmpty() && fname.isEmpty() && lname.isEmpty())){
                    mFirebaseAuth.createUserWithEmailAndPassword(email, pwd).addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>() {

                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                Toast.makeText(Register.this,"SignUp Unsuccessful, Please Try Again",Toast.LENGTH_SHORT).show();
                            }
                            else {
                                FirebaseUser fireuser = FirebaseAuth.getInstance().getCurrentUser();

                                Call<ResponseBody> call = Retrofit_Client.getInstance().getApi().register(fireuser.getUid(),roletext,"test intro", emailId.getText().toString());
                                call.enqueue(new Callback<ResponseBody>() {
                                    @Override
                                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                        try {

                                            String s = response.body().string();
                                            Intent intent=new Intent(Register.this,Login.class);
                                            startActivity(intent);
                                            Toast.makeText(Register.this,"Successfully SignedUp",Toast.LENGTH_SHORT).show();

                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }


                                    }

                                    @Override
                                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                                        Toast.makeText(Register.this, "Failed to store in DB", Toast.LENGTH_LONG).show();

                                    }
                                });
                            }
                        }
                    });
                }
                else{
                    Toast.makeText(Register.this,"Error Occurred!",Toast.LENGTH_SHORT).show();

                }
            }
        });

        tvSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Register.this,Login.class);
                startActivity(i);
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        if(text.equalsIgnoreCase("Register As Nanny")){
            roletext="asnanny";
        }else{
            roletext="fornanny";
        }

        Toast.makeText(parent.getContext(),text,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
