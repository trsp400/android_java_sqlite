package com.example.disciplina;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
public class TratarDisciplina extends AppCompatActivity {
    // Declaração dos componentes de tela
    EditText ed1, ed2, ed3, ed4;
    Button bt1, bt2;
    // Atributos de informações de acao: 0 : alteração ou exclusão
    // -1 : inclusão
    private int acao;
    // Id do registro para alteração ou exclusão
    // será usado com o método buscar para trazer os dados do registro
    private long id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tratar_disciplina);
        bt1 = (Button) findViewById(R.id.button);
        bt2 = (Button) findViewById(R.id.button2);
        ed1 = (EditText) findViewById(R.id.editText);
        ed2 = (EditText) findViewById(R.id.editText2);
        ed3 = (EditText) findViewById(R.id.editText3);
        ed4 = (EditText) findViewById(R.id.editText4);
        // Recebe os dados da atividade principal
        // e preenche a tela (view)
        acao = getIntent().getExtras().getInt("acao");
        id = getIntent().getExtras().getLong("id");

        // Altera o título e prepara a tela
        if (acao == -1) {
            // Tela de inclusão
            // Usa dados-padrão
            setTitle("Inserir Disciplina");
            bt1.setText("Incluir");
            bt2.setEnabled(false);
            ed1.setText("Nome Disciplina");
            ed2.setText(String.format("%.1f", 0.0));
            ed3.setText(String.format("%.1f", 0.0));
            ed4.setText(String.format("%.1f", 0.0));
        } else {
            // Tela de alteração ou exclusão
            setTitle("Alterar ou Excluir Disciplina");
            // Cria um objeto Disciplina auxiliar para armazenar
            // os dados do registro
            Disciplina aux = new Disciplina();
            // Cria o objeto de acesso ao banco
            Disciplina_DAO dao = new Disciplina_DAO(this);
            // Abre o banco
            dao.open();
            // Faz a consulta pelo id do registro para
            // buscar os dados na tabela
            aux = dao.buscar(id);
            // Preenche os dados do registro na tela
            ed1.setText(aux.getNome());
            ed2.setText(String.format("%.1f", aux.getA1()));
            ed3.setText(String.format("%.1f", aux.getA2()));
            ed4.setText(String.format("%.1f", aux.getA3()));
            // Libera o recurso de acesso ao banco de dados
            dao.close();
        }
    }
    // Método para preparar os dados para retornar à atividade principal
    public void alterarInserir(View v) {
        String nome;
        double a1, a2, a3;
        // Pega os dados preenchidos na tela,
        // para inclusão ou alteração
        nome = ed1.getText().toString();
        a1 = Double.parseDouble(ed2.getText().toString());
        a2 = Double.parseDouble(ed3.getText().toString());
        a3 = Double.parseDouble(ed4.getText().toString());
        // Cria o objeto de acesso ao banco
        Disciplina_DAO dao = new Disciplina_DAO(this);
        // Abre o banco
        dao.open();

        // Determina a ação
        if (acao == -1) { // Ação de inserção
            // Realiza a inclusão da disciplina na tabela
            dao.inserir(nome, a1, a2, a3);
        }
        else{ // Ação de alteração
            // Realiza a alteração do registro correspondente na tabela
            dao.alterar(id, nome, a1, a2, a3);
        }
        // Libera o recurso de acesso ao banco
        dao.close();
        // Encerra a atividade, retornando à atividade principal
        finish();
    }
    // Método para a exclusão de um registro de acordo com o id da disciplina
    public void excluir(View v) {
        // Confere se é uma ação de alteração ou exclusão
        if (acao == 0) {
            // Cria o objeto de acesso ao banco
            Disciplina_DAO dao = new Disciplina_DAO(this);
            // Abre o banco
            dao.open();
            // Realiza a exclusão do registro por meio do id
            dao.apagar(id);
            // Libera o recurso de acesso ao banco
            dao.close();
        }
        // Encerra a atividade, retornando à atividade principal
        finish();
    }
    public void voltar(View v) {
        // Encerra a atividade, retornando à atividade principal
        finish();
    }
}