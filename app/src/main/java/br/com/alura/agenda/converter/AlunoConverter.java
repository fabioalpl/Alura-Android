package br.com.alura.agenda.converter;

import org.json.JSONException;
import org.json.JSONStringer;

import java.util.List;

import br.com.alura.agenda.modelo.Aluno;

/**
 * Created by p017461 on 13/03/2018.
 */

public class AlunoConverter {

    public String converterParaJson(List<Aluno> alunos) {
        /*String json = "{/n" +
                " \"list\": [\n" +
                " {\n" +
                " \"aluno\": ["
        return null;*/

        JSONStringer js = new JSONStringer();
        try {
            js.object().key("list").array().object().key("aluno").array();
            for (Aluno aluno: alunos){
                js.object();
                js.key("nome").value(aluno.getNome());
                js.key("nota").value(aluno.getNota());
                js.endObject();
            }
            js.endArray().endObject().endArray().endObject();
        } catch (JSONException e){
            e.printStackTrace();
        }
        return js.toString();
    }

    public String converteParaJSONCompleto(Aluno aluno) {
        JSONStringer js = new JSONStringer();

        try {
            js.object()
                    .key("nome").value(aluno.getNome())
                    .key("endereco").value(aluno.getEndereco())
                    .key("site").value(aluno.getSite())
                    .key("telefone").value(aluno.getTelefone())
                    .key("nota").value(aluno.getNota())
                    .endObject();
            return js.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }
}
