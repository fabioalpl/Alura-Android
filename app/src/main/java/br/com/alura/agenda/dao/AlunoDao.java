package br.com.alura.agenda.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import br.com.alura.agenda.modelo.Aluno;

/**
 * Created by fabio on 26/01/2018.
 */

public class AlunoDao extends SQLiteOpenHelper {

    public AlunoDao(Context context) {
        super(context, "Agenda", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE Alunos (id INTEGER PRIMARY KEY, nome TEXT NOT NULL, endereco TEXT, telefone TEXT, site TEXT, nota REAL);";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String sql = "DROP TABLE IF EXISTS Alunos";
        sqLiteDatabase.execSQL(sql);
        onCreate(sqLiteDatabase);
    }

    public void insere(Aluno aluno) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = new ContentValues();
        dados.put("Nome", aluno.getNome());
        dados.put("Endereco", aluno.getEndereco());
        dados.put("Telefone", aluno.getTelefone());
        dados.put("Site", aluno.getSite());
        dados.put("Nota", aluno.getNota());

        db.insert("Alunos", null, dados );
    }

    public List<Aluno> buscaAlunos() {
        String sql = "SELECT * FROM Alunos";
        SQLiteDatabase db = getReadableDatabase();

        Cursor c = db.rawQuery(sql, null);

        List<Aluno> alunos = new ArrayList<Aluno>();
        while (c.moveToNext()){
            Aluno aluno = new Aluno();
            aluno.setId(c.getLong(c.getColumnIndex("id")));
            aluno.setNome(c.getString(c.getColumnIndex("nome")));
            aluno.setEndereco(c.getString(c.getColumnIndex("endereco")));
            aluno.setTelefone(c.getString(c.getColumnIndex("telefone")));
            aluno.setSite(c.getString(c.getColumnIndex("site")));
            aluno.setNota(c.getDouble(c.getColumnIndex("nota")));

            alunos.add(aluno);
        }
        c.close();

        return alunos;
    }

    public void deleta(Aluno aluno) {
        SQLiteDatabase db = getWritableDatabase();

        String[] params = {aluno.getId().toString()};
        db.delete("Alunos", "id = ?", params);
    }
}
