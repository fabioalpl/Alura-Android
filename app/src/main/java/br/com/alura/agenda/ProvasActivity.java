package br.com.alura.agenda;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Arrays;
import java.util.List;

import br.com.alura.agenda.modelo.Prova;

import static br.com.alura.agenda.R.id.frame_principal;
import static br.com.alura.agenda.R.id.frame_secundario;

public class ProvasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provas);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction tx = fragmentManager.beginTransaction();

        tx.replace(frame_principal, new ListaProvasFragment());
        if (estaNoModoPaisagem()) {
            tx.replace(frame_secundario, new DetalhesProvaFragment());
        }

        tx.commit();
    }

    private boolean estaNoModoPaisagem() {
        return getResources().getBoolean(R.bool.modoPaisagem);
    }

    public void selecionarProva(Prova prova) {
        FragmentManager manager = getSupportFragmentManager();
        if (!estaNoModoPaisagem()) {
            FragmentTransaction tx = manager.beginTransaction();
            DetalhesProvaFragment detalhesProvaFragment = new DetalhesProvaFragment();
            Bundle parametros = new Bundle();

            parametros.putSerializable("prova", prova);
            detalhesProvaFragment.setArguments(parametros);

            tx.replace(R.id.frame_principal, detalhesProvaFragment);
            tx.addToBackStack(null);
            tx.commit();
        } else {
            DetalhesProvaFragment detalhesFragment =
                    (DetalhesProvaFragment) manager.findFragmentById(R.id.frame_secundario);
            detalhesFragment.populaCamposCom(prova);
        }
    }
}
