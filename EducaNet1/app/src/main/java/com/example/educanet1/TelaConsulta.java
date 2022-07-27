package com.example.educanet1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TelaConsulta extends AppCompatActivity {
    //variaveis para os elementos TelaConsulta
    EditText etId, etCurso, etAluno, etMedia, etSituacao;
    Button btnAnterior, btnProximo, btnVoltar;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_consulta);

        //pegar elementos pelo id
        etId = findViewById(R.id.editId);
        etCurso = findViewById(R.id.editCursoConsulta);
        etAluno = findViewById(R.id.editAlunoConsulta);
        etMedia = findViewById(R.id.editMediaConsulta);
        etSituacao = findViewById(R.id.editSituacaoConsulta);
        btnAnterior = findViewById(R.id.buttonAnterior);
        btnProximo = findViewById(R.id.buttonProximo);
        btnVoltar = findViewById(R.id.buttonVoltarConsulta);

        cursor = Database.buscarDados(this);
        //se tiver registro
        if(cursor.getCount() != 0){
            mostrarDados();
        }else{
            CxMsg.mostrar("Nenhum registro encontrado!!", this);
        }
    }

    public void proximoRegistro(View v){
        try {
            cursor.moveToNext();
            mostrarDados();
        }catch (Exception e){
            if(cursor.isAfterLast()){
                CxMsg.mostrar("Não existem mais registros!!", this);
            }else{
                CxMsg.mostrar("Erro ao navegar pelos registros!!" + e.getMessage(), this);
            }
        }
    }

    public void registroAnterior(View v){
        try {
            cursor.moveToPrevious();
            mostrarDados();
        }catch (Exception e){
            if(cursor.isBeforeFirst()){
                CxMsg.mostrar("Não existem mais registros!!", this);
            }else{
                CxMsg.mostrar("Erro ao navegar pelos registros!!" + e.getMessage(), this);
            }
        }
    }

    public void mostrarDados(){
        etId.setText(cursor.getString(0));
        etCurso.setText(cursor.getString(1));
        etAluno.setText(cursor.getString(2));
        etMedia.setText(cursor.getString(3));
        etSituacao.setText(cursor.getString(4));
        //caso aprovado ou reprovado
        switch (etSituacao.getText().toString()){
            case "Aprovado":
                etSituacao.setTextColor(Color.GREEN);
                break;
            case "Reprovado":
                etSituacao.setTextColor(Color.RED);
                break;
            default:
                Toast.makeText(this, "Situação do aluno indefinida!!", Toast.LENGTH_SHORT);
        }
    }

    //voltar para a tela Inserir
    public void fecharTela(View v){
        this.finish();
    }
}