package br.com.alura.agenda.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import android.media.MediaPlayer;
import android.os.Build;
import android.telephony.SmsMessage;
import android.widget.Toast;

import br.com.alura.agenda.R;
import br.com.alura.agenda.dao.AlunoDao;

import static android.Manifest.permission_group.SMS;

/**
 * Created by fabio on 11/03/2018.
 */

public class SMSReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        Object [] pdus = (Object[]) intent.getSerializableExtra("pdus");
        byte [] pdu = (byte[]) pdus[0];
        String formato = (String) intent.getSerializableExtra("format");

        SmsMessage sms = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            sms = SmsMessage.createFromPdu((byte[]) pdus[0], formato);
        } else {
            sms = SmsMessage.createFromPdu((byte[]) pdus[0]);
        }
        //SmsMessage sms = SmsMessage.createFromPdu(pdu, formato);

        String telefone = sms.getDisplayOriginatingAddress();
        AlunoDao dao = new AlunoDao(context);
        if(dao.ehAluno(telefone)) {
            Toast.makeText(context, "Chegou um SMS!", Toast.LENGTH_LONG).show();
            MediaPlayer mp = MediaPlayer.create(context, R.raw.msg);
            mp.start();
        }
        dao.close();
    }
}
