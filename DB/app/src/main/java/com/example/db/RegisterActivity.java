package com.example.db;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity {

  private String userID;
  private String userPassword;
  private String userEmail;
  private String userGender;
  private AlertDialog dialog;
  private boolean validate = false;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_register);

    final EditText IdText = (EditText) findViewById(R.id.idText);
    final EditText PasswordText = (EditText) findViewById(R.id.passwordText);
    final EditText EmailText = (EditText) findViewById(R.id.emailText);
    RadioGroup genderGroup = (RadioGroup) findViewById(R.id.genderGroup);
    int genderGroupID = genderGroup.getCheckedRadioButtonId();
    userGender = ((RadioButton) findViewById(genderGroupID)).getText().toString();

    genderGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
      @Override
      public void onCheckedChanged(RadioGroup group, int checkedId) {
        RadioButton genderButton = (RadioButton) findViewById(checkedId);
        userGender = genderButton.getText().toString();
      }
    });

    final Button validateButton = (Button) findViewById(R.id.validateButton);
    validateButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        String userID = IdText.getText().toString();
        if (validate) {
          return;
        }
        if (userID.equals("")) {
          AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
          dialog = builder.setMessage("아이디는 빈 칸일 수 없습니다.")
                  .setPositiveButton("확인", null)
                  .create();
          dialog.show();
          return;
        }
        Response.Listener<String> responseListener = new Response.Listener<String>() {
          @Override
          public void onResponse(String response) {
            try {
              JSONObject jsonResponse = new JSONObject(response);
              boolean success = jsonResponse.getBoolean("success");
              if (success) {
                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                dialog = builder.setMessage("사용할 수 있는 아이디입니다.")
                        .setPositiveButton("확인", null)
                        .create();
                dialog.show();
                IdText.setEnabled(false);
                validate = true;
                IdText.setBackgroundColor(getResources().getColor(R.color.colorGray));
                validateButton.setBackgroundColor(getResources().getColor(R.color.colorGray));
              } else {
                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                dialog = builder.setMessage("사용할 수 없는 아이디입니다.")
                        .setNegativeButton("확인", null)
                        .create();
                dialog.show();

              }
            } catch (Exception e) {
            e.printStackTrace();
            }
          }
        };
        ValidateRequest validateRequest = new ValidateRequest(userID, responseListener);
        RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
        queue.add(validateRequest);
      }
    });
    Button registerButton = (Button) findViewById(R.id.registerButton);
    registerButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        String userID = IdText.getText().toString();
        String userPassword = PasswordText.getText().toString();
        String userEmail = EmailText.getText().toString();
        if (!validate) {
          AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
          dialog = builder.setMessage("먼저 중복 체크를 해주세요.")
                  .setNegativeButton("확인", null)
                  .create();
          dialog.show();
          return;
        }
        if (userID.equals("") || userPassword.equals("") || userEmail.equals("")) {
          AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
          dialog = builder.setMessage("빈칸 없이 입력해주세요.")
                  .setNegativeButton("확인", null)
                  .create();
          dialog.show();
          return;
        }

        Response.Listener<String> responseListener = new Response.Listener<String>() {
          @Override
          public void onResponse(String response) {
            try {
              JSONObject jsonResponse = new JSONObject(response);
              boolean success = jsonResponse.getBoolean("success");
              if (success) {
                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                dialog = builder.setMessage("회원 등록에 성공했습니다.")
                        .setPositiveButton("확인", null)
                        .create();
                dialog.show();
                 finish();
              } else {
                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                dialog = builder.setMessage("회원 등록에 실패했습니다.")
                        .setNegativeButton("확인", null)
                        .create();
                dialog.show();

              }
            } catch (Exception e) {
              e.printStackTrace();
            }
          }
        };
        RegisterRequest registerRequest = new RegisterRequest(userID, userPassword, userGender, userEmail, responseListener);
        RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
        queue.add(registerRequest);
      }
    });
  }

  @Override
  protected void onStop() {
    super.onStop();
    if (dialog != null) {
      dialog.dismiss();
      dialog = null;
    }

  }



}
