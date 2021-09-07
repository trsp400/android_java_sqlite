package com.example.disciplina;
import android.content.Context ;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
public class DisciplinaSQLiteOpenHelper extends SQLiteOpenHelper {
    // Definição da tabela e dos atributos
    // Os espaços antes e depois dos nomes são importantes,
    // pois será criada uma QUERY (consulta) com esses identificadores

    public static final String TABELA =  "Disciplina ";
    public static final String COLUNA_ID = "id" ;
    public static final String COLUNA_NOME = "nome";
    public static final String COLUNA_A1 = "a1";
    public static final String COLUNA_A2 = "a2";
    public static final String COLUNA_A3 = "a3";
    // Define o nome do banco de dados
    private static final String DATABASE_NAME = "disciplinas.db";
    // Define a versão do banco
    // O valor deve ser incrementado somente em casos de alterações
    // na estrutura do banco (tabelas e atributos)
    private static final int DATABASE_VERSION = 1;
    // String de criação da tabela no banco de dados
    private static final String CRIAR_BANCO = "create table "
            + TABELA + "("
            + COLUNA_ID + " integer primary key autoincrement , "
            + COLUNA_NOME + " text not null , "
            + COLUNA_A1 + " double not null , "
            + COLUNA_A2 + " double not null , "
            + COLUNA_A3 + " double not null ) ;";
    // Método construtor da classe para criação ou atualização do banco
    public DisciplinaSQLiteOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    // Cria o banco de dados, caso não exista
    @Override
    public void onCreate ( SQLiteDatabase database ) {
        database . execSQL ( CRIAR_BANCO );
    }
    // Atualiza o banco de dados, caso seja uma nova versão
    @Override
    public void onUpgrade (SQLiteDatabase db, int oldVersion, int newVersion) {
        db. execSQL ("DROP TABLE IF EXISTS " + TABELA );
        onCreate (db);
    }
}