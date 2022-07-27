package com.example.educanet1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //menu
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.about:
                AlertDialog.Builder alert = new AlertDialog.Builder(this);
                alert.setMessage("EducaTech v1.0")
                        .setNeutralButton("OK", null)
                        .show();
                return true;
            case R.id.configs:
                Intent intentConfig = new Intent(this, PreferencesActivity.class);
                startActivity(intentConfig);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //ir para TelaInserir
    public void entrarClicado(View v){
        //acessa o login e senha salvos
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String prefLogin = sharedPreferences.getString("login", "");
        String prefPass = sharedPreferences.getString("password", "");

        String editLogin = ((EditText) findViewById(R.id.editLogin)).getText().toString();
        String editPass = ((EditText) findViewById(R.id.editPassword)).getText().toString();
        //verifica login e senha
        if(editLogin.equals(prefLogin) && editPass.equals(prefPass)){
            Intent intent = new Intent(this, TelaInserir.class);
            EditText inputLogin = findViewById(R.id.editLogin);
            startActivity(intent);
        }else{
            CxMsg.mostrar("Login ou senha inválidos!!", this);
        }
    }
}