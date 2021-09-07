package com.example.disciplina;

import java.util.ArrayList ;
import java.util.List ;
import android.content.ContentValues ;
import android.content.Context ;
import android.database.Cursor ;
import android.database.SQLException ;
import android.database.sqlite . SQLiteDatabase ;
// Camada de dados (Persistência)
// Classe de definição para os acessos ao banco de dados
public class Disciplina_DAO {
    // Define um objeto banco de dados
    private SQLiteDatabase database;
    // Define as colunas da tabela
    private String [] columns = { DisciplinaSQLiteOpenHelper.COLUNA_ID
            ,
            DisciplinaSQLiteOpenHelper.COLUNA_NOME ,
            DisciplinaSQLiteOpenHelper.COLUNA_A1 ,
            DisciplinaSQLiteOpenHelper.COLUNA_A2 ,
            DisciplinaSQLiteOpenHelper.COLUNA_A3 };
    private DisciplinaSQLiteOpenHelper sqliteOpenHelper ;
    // Método construtor
    public Disciplina_DAO(Context context) {

        sqliteOpenHelper = new DisciplinaSQLiteOpenHelper(context);
    }
    // Método para abrir o recurso de banco de dados
    public void open () throws SQLException {
        database = sqliteOpenHelper.getWritableDatabase();
    }
    // Método para fechar o recurso de banco de dados
    public void close() {
        sqliteOpenHelper.close ();
    }
    // Método de inclusão de registro
    public void inserir ( String nome, double a1, double a2, double a3
    ) {
        // Prepara os valores das colunas da tabela para a inserção
        ContentValues values = new ContentValues ();
        values.put ( DisciplinaSQLiteOpenHelper.COLUNA_NOME , nome );
        values.put ( DisciplinaSQLiteOpenHelper.COLUNA_A1 , String.
                valueOf(a1) );
        values.put ( DisciplinaSQLiteOpenHelper.COLUNA_A2 , String.
                valueOf(a2) );
        values.put ( DisciplinaSQLiteOpenHelper.COLUNA_A3 , String.
                valueOf(a3) );
        // Efetua a inclusão com retorno do id do registro
        long insertId = database.insert ( DisciplinaSQLiteOpenHelper.TABELA ,
                null , values );
    }
    // Método para atualização de um registro da tabela
    // Recebe os dados para a inserção
    public void alterar(long id, String nome, double a1, double a2,
                        double a3){
        // Prepara os dados para a atualização
        ContentValues values = new ContentValues ();
        values.put ( DisciplinaSQLiteOpenHelper.COLUNA_NOME , nome );
        values.put ( DisciplinaSQLiteOpenHelper.COLUNA_A1 , String.
                valueOf(a1) );
        values.put ( DisciplinaSQLiteOpenHelper.COLUNA_A2 , String.
                valueOf(a2) );
        values.put ( DisciplinaSQLiteOpenHelper.COLUNA_A3 , String.
                valueOf(a3) );
        // Efetua a alteração do registro, de acordo com o id do registro
        database.update(DisciplinaSQLiteOpenHelper.TABELA , values,
                DisciplinaSQLiteOpenHelper.COLUNA_ID + "=" + id, null);
    }
    // Método para a exclusão de um registro
    // Recebe o id do registro que será excluído
    public void apagar ( long id ) {

        // Exclui o registro a partir do id
        database.delete ( DisciplinaSQLiteOpenHelper.TABELA ,
                DisciplinaSQLiteOpenHelper.COLUNA_ID
                        + " = " + id , null );
    }
    // Método de busca de registro a partir do id
    // Busca os dados de cada registro a partir do id
    // Retorna um objeto Disciplina com os dados do registro
    public Disciplina buscar ( long id ) {
        // Realiza a busca a partir do id do registro
        // Usado para buscar os dados para a montagem da tela e
        // para a alteração ou exclusão do registro
        // O objeto cursor armazena os registros da consulta
        // Como o filtro é o id, apenas um registro será selecionado
        Cursor cursor = database.query( DisciplinaSQLiteOpenHelper.TABELA,
                columns , DisciplinaSQLiteOpenHelper.COLUNA_ID + " = " + id,
                null , null , null , null );
        cursor . moveToFirst ();
        // Cria um objeto Disciplina auxiliar para retornar o objeto
        Disciplina disciplina = new Disciplina ();
        // Preenche os dados do registro do banco
        // nas propriedades do objeto auxiliar
        disciplina.setId ( cursor.getLong (0) );
        disciplina.setNome ( cursor.getString (1) );
        disciplina.setA1 ( cursor.getDouble (2) );
        disciplina.setA2 ( cursor.getDouble (3) );
        disciplina.setA3 ( cursor.getDouble (4) );
        // Fecha o recurso do cursor
        cursor.close();
        // Retorna o objeto auxiliar do tipo Disciplina
        return disciplina ;
    }
    // Método de montagem da lista de registros das disciplinas
    // Monta a lista de disciplinas para a carga da lista na tela principal
    // O método retornará uma lista com todas as disciplinas armazenadas
    // no banco
    public List <Disciplina > getAll () {
        // Prepara um Arraylist para retorno dos registros armazenados no banco

        List <Disciplina > disciplinas = new ArrayList <Disciplina>() ;
        // Objeto cursor para armazenar temporariamente os dados
        // retornados pela consulta
        Cursor cursor = database . query ( DisciplinaSQLiteOpenHelper .
                TABELA , columns , null , null , null , null , null );

        // Passa o ponteiro para o primeiro registro do cursor
        cursor . moveToFirst ();
        // Para cada registro, os dados da tabela são copiados para
        // o objeto Disciplina da lista
        while (!cursor.isAfterLast ()) {
            Disciplina disciplina = new Disciplina ();
            disciplina.setId ( cursor . getLong (0) );
            disciplina.setNome ( cursor . getString (1) );
            disciplina.setA1 ( cursor.getDouble (2) );
            disciplina.setA2 ( cursor.getDouble (3) );
            disciplina.setA3 ( cursor.getDouble (4) );
            disciplinas.add ( disciplina );
            cursor . moveToNext ();
        }
        // Fecha o cursor
        cursor . close ();
        // Retorna à lista de disciplinas
        return disciplinas;
    }
}
