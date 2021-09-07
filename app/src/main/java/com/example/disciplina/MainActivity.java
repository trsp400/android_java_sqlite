package com.example.disciplina;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{
    // Componente lista da view
    ListView lista;
    // Declaração da intent
    Intent intent;
    // Determina o código da intent
    public static final int ACTIVITY_REQUEST_DISCIPLINA = 1;
    // Declara o objeto de persistência (acesso ao banco)
    private Disciplina_DAO dao ;
    // Vetor com os dados das disciplinas para apresentar na lista
    private String[] disciplinas;
    // Vetor com o id dos registros para identificar o registro
    // para os casos de seleção na lista para alteração ou

    // exclusão
    private long[] idDisiciplinas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lista = (ListView) findViewById(R.id.lista);
        // Altera o título da janela da atividade
        setTitle("Banco de Dados com SQLite!");
        // Cria o objeto de acesso ao banco
        dao = new Disciplina_DAO(this);
        // Abre o banco
        dao.open();
        // Determina que os itens da lista serão clicáveis e
        // prepara o método de controle: onItemClick(..)
        lista.setOnItemClickListener(this); // Clique no item
    }
    // Sempre que a atividade passar pelo método onResume, a lista
    // será atualizada
    @Override
    protected void onResume () {
        dao.open ();
        super.onResume ();
        // Prepara a lista que será exibida ao usuário na tela
        // Busca no banco todos os registros
        List<Disciplina > listaDisciplinas = dao.getAll();
        // Define o tamanho dos vetores de disciplinas e id
        // em função do tamanho (quantidade de registros) da tabela
        disciplinas = new String[listaDisciplinas.size()];
        idDisiciplinas = new long[listaDisciplinas.size()];
        // Primeiro índice dos vetores
        int i =0;
        // Cria um objeto iterator para preencher o vetor de disciplinas
        // com os dados dos registros
        Iterator<Disciplina> iterator = listaDisciplinas.iterator();
        // Para cada registro preencher os vetores
        while (iterator.hasNext()) {
            // Objeto disciplina auxiliar
            Disciplina aux = new Disciplina();
            // Recebe no objeto auxiliar os dados de cada registro
            aux = (Disciplina) iterator.next();
            // Preenche o vetor de disciplinas para visualização na lista
            disciplinas[i] = aux.textoLista();
            // Preenche o vetor de id para identificação do registro
            // ao se escolher um elemento da lista para

            // a alteração ou exclusão
            idDisiciplinas[i] = aux.getId();
            // Próximo item dos vetores
            i++;
        }
        // Carrega o ArrayAdapter com os dados do vetor de disciplinas
        ArrayAdapter <String > adapter = new ArrayAdapter<String >(
                this ,
                android.R.layout.simple_list_item_1 , disciplinas );
        // Preenche a lista com os dados do ArrayAdapter
        lista.setAdapter( adapter );
    }
    // Fecha o recurso de acesso ao banco sempre que a atividade passar
    // por uma pausa
    @Override
    protected void onPause () {
        // Libera o recurso de acesso ao banco
        dao.close();
        super.onPause ();
    }
    // Método para seleção de um item da lista para
    // alteração ou exclusão (atende à interface: AdapterView.OnItemClickListener)
    public void onItemClick(AdapterView<?> parent, View view, int position, long ident) {
        // Position é a posição do item na lista
        // Armazena o índice do elemento escolhido na lista
        long id = idDisiciplinas[position];
        // Preparação da intent com previsão de retorno de dados
        intent = new Intent(getApplicationContext(), TratarDisciplina.
                class);
        // Passa os dados do item escolhido para a atividade de tratamento
        intent.putExtra("acao", 0);
        intent.putExtra("id", id);
        // Chama a atividade
        startActivity(intent);
    }
    // Método de controle para chamada da atividade secundária para inclusão
    public void incluirDisciplina(View v){
        // Intent para chamar a atividade secundária
        intent = new Intent(getApplicationContext(), TratarDisciplina.
                class);
        // Passa os dados do item escolhido para a atividade de tratamento
        intent.putExtra("acao", -1);

        intent.putExtra("id", 0L);
        // Chama a atividade
        startActivity(intent);
    }
    // Método para encerrar o aplicativo
    public void sair(View v){
        finish();
    }
}