package com.example.educanet1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;


public class TelaInserir extends AppCompatActivity {
    //variaveis para os elementos TelaInserir
    EditText etCurso, etAluno, etNota1, etNota2, etNota3;
    Button btnSalvar, btnConsult, btnFechar;
    //objeto classe Aluno
    Aluno aluno = new Aluno();
    //msg Alert
    CxMsg msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_inserir);

        //pegar elementos pelo id
        etCurso = findViewById(R.id.editCurso);
        etAluno = findViewById(R.id.editAluno);
        etNota1 = findViewById(R.id.editNota1);
        etNota2 = findViewById(R.id.editNota2);
        etNota3 = findViewById(R.id.editNota3);
        btnSalvar = findViewById(R.id.buttonSalvar);
        btnConsult = findViewById(R.id.buttonConsult);
        btnFechar = findViewById(R.id.buttonFechar);

        Database.abrirBanco(this);
        Database.abrirOuCriarTabela(this);
        Database.fecharDB();
    }

    //inserir notas
    public void inserirRegistro(View v){
        //se campos vazios
        if((etCurso.getText().toString().isEmpty()) || (etAluno.getText().toString().isEmpty()) || (etNota1.getText().toString().isEmpty()) || (etNota2.getText().toString().isEmpty()) || (etNota3.getText().toString().isEmpty())){
            msg.mostrar("Campos não podem estar vazios!!", this);
        }else{
            //pegar os valores digitados
            //atributos da class Aluno: curso, aluno, media, situacao!!
            aluno.setCurso(etCurso.getText().toString());
            aluno.setAluno(etAluno.getText().toString());
            aluno.setNota1(Double.parseDouble(etNota1.getText().toString()));
            aluno.setNota2(Double.parseDouble(etNota1.getText().toString()));
            aluno.setNota3(Double.parseDouble(etNota1.getText().toString()));
            //calcular media
            //se nota menor q 0 ou maior q 10
            if((aluno.getNota1() < 0 || aluno.getNota1() > 10) || (aluno.getNota2() < 0 || aluno.getNota2() > 10) || (aluno.getNota3() < 0 || aluno.getNota3() > 10)){
                msg.mostrar("Insira notas entre 0 e 10!!", this);
            }else{
                 //caculo media
                aluno.setMedia(aluno.calcularMedia(aluno.getNota1(), aluno.getNota2(), aluno.getNota3()));
                //definir situação
                if(aluno.getMedia() > 5){
                    aluno.setSituacao("Aprovado");
                }else{
                    aluno.setSituacao("Reprovado");
                }
                //inserir os registros no BD
                //atributos da class Aluno: curso, aluno, media, situacao!!
                Database.inserirRegistro(aluno.getCurso(), aluno.getAluno(), aluno.getMedia(), aluno.getSituacao(), this);
                //limpar os elementos apos o inserção
                etCurso.setText(null);
                etAluno.setText(null);
                etNota1.setText(null);
                etNota2.setText(null);
                etNota3.setText(null);
            }
        }
    }

    //abrir TelaConsulta
    public void abrirTelaConsulta(View v){
        Intent intent = new Intent(this, TelaConsulta.class);
        startActivity(intent);
    }

    //Voltar para login
    public void fecharTela(View v){
        this.finish();
    }
}