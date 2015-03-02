package com.example.android.qcircleview;

import android.util.Log;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Created by Chabino on 01/03/2015.
 */
public class ExecuteAsRootBase {
    public static boolean su_on=false;
    public static boolean canRunRootCommands() {
        boolean retval = false;
        Process su;
        try {
            su = Runtime.getRuntime().exec("su");
            DataOutputStream os = new DataOutputStream(su.getOutputStream());
            DataInputStream osRes = new DataInputStream(su.getInputStream());
            if (null != os && null != osRes) {
                os.writeBytes("id\n");
                os.flush();
                String currUid = osRes.readLine();
                boolean exitSu = false;
                if (null == currUid) {
                    retval = false;
                    exitSu = false;
                    su_on=false;
                    Log.i("Root", "Can't get root access or denied by user");
                } else if (currUid.contains("uid=0")) {
                    retval = true;
                    exitSu = true;
                    su_on=true;
                    Log.i("Root", "Root access granted");
                } else {
                    retval = false;
                    exitSu = true;
                    su_on=false;
                    Log.i("Root", "Root access rejected: " + currUid);
                }

                if (exitSu) {
                    os.writeBytes("exit\n");
                    os.flush();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return retval;
    }
}
