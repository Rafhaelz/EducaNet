package com.example.educanet1;
//import static android.content.Context.MODE_PRIVATE;
import android.app.Activity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor; //navegar entre os registros
import android.content.ContextWrapper;
import android.database.sqlite.SQLiteOpenHelper;

import static android.content.Context.MODE_PRIVATE;


public class Database extends SQLiteOpenHelper {
    static SQLiteDatabase db = null;
    static Cursor cursor;
    //Nome do banco
    private static final String BANCO_EDUCA = "bancoEduca";
    //Database version
    private static final int VERSAO = 2;

    //constructor
    public Database(Context context){
        super(context, BANCO_EDUCA, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db){}

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1){}

    //abrir BD
    public static void abrirBanco(Activity activity){
        try {
            ContextWrapper cw = new ContextWrapper(activity);
            db = cw.openOrCreateDatabase("bancoEduca", MODE_PRIVATE, null);
        }catch (Exception e){
            CxMsg.mostrar("Erro ao abrir o banco de dados!!" + e.getMessage(), activity);
        }
    }

    public static void fecharDB(){
        db.close();
    }

    //abrir ou criar a tabela
    public static void abrirOuCriarTabela(Activity activity){
        try{
            db.execSQL("CREATE TABLE IF NOT EXISTS alunos (id INTEGER PRIMARY KEY AUTOINCREMENT, curso TEXT, aluno TEXT, media REAL, situacao TEXT)");
        }catch (Exception e){
            CxMsg.mostrar("Erro ao criar tabela!!" + e.getMessage(), activity);
        }
    }

    //inserir registro na tabela alunos
    public static void inserirRegistro(String strCurso, String strAluno, double media, String strSituacao, Activity activity){
        abrirBanco(activity);
        try {
            db.execSQL("INSERT INTO alunos VALUES (NULL,'"+strCurso+"', '"+strAluno+"', '"+media+"', '"+strSituacao+"' )");
        }catch (Exception e){
            CxMsg.mostrar("Erro ao inserir registro!!" + e.getMessage(), activity);
        }finally {
            CxMsg.mostrar("Registro inserido com sucesso!!", activity);
        }
        fecharDB();
    }

    //atributos da class Aluno: curso, aluno, media, situacao!!
    public static Cursor buscarDados(Activity activity){
        abrirBanco(activity);
        cursor = db.query("alunos",
                new String[]{"id", "curso", "aluno", "media", "situacao"},
                null,
                null,
                null,
                null,
                null,
                null
        );
        cursor.moveToFirst();
        return cursor;
    }
}
