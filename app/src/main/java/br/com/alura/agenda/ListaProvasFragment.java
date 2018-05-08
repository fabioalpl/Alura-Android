package br.com.alura.agenda;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Arrays;
import java.util.List;

import br.com.alura.agenda.DetalhesProvaActivity;
import br.com.alura.agenda.ProvasActivity;
import br.com.alura.agenda.R;
import br.com.alura.agenda.modelo.Prova;

/**
 * Created by fabio on 06/05/2018.
 */

public class ListaProvasFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lista_provas, container, false);

        List<String> topicosPort = Arrays.asList("Sujeito","Objeto direto", "Objeto Indireto");
        Prova provaPortugues = new Prova("Portugues", "25/05/2018", topicosPort);

        List<String> topicosMat = Arrays.asList("Equacoes de segundo grau","Trigonometria");
        Prova provaMatematica = new Prova("Matematica","27/05/2018",topicosMat);

        List<Prova> provas = Arrays.asList(provaPortugues, provaMatematica);

        ArrayAdapter<Prova> adapter = new ArrayAdapter<Prova>(getContext(), android.R.layout.simple_list_item_1, provas);

        ListView lista = (ListView) view.findViewById(R.id.provas_lista);
        lista.setAdapter(adapter);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Prova prova = (Prova) adapterView.getItemAtPosition(i);
                //Toast.makeText(ProvasActivity.this, "Clicou na prova de: " + prova, Toast.LENGTH_SHORT ).show();
                Intent vaiParaDetalhes = new Intent(getContext(), DetalhesProvaActivity.class);
                vaiParaDetalhes.putExtra("prova", prova);
                startActivity(vaiParaDetalhes);
            }
        });

        return view;
    }
}
