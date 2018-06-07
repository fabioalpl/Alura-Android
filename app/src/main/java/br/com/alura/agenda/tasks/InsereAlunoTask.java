package br.com.alura.agenda.tasks;

import android.os.AsyncTask;

import br.com.alura.agenda.modelo.Aluno;

public class InsereAlunoTask extends AsyncTask {
    private final Aluno aluno;

    public InsereAlunoTask(Aluno aluno) {

        this.aluno = aluno;
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        return null;
    }
}
