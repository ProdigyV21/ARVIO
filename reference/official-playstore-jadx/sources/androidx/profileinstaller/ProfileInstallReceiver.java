package androidx.profileinstaller;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Process;
import android.util.Log;
import androidx.credentials.a;
import java.io.File;
import kb.d;
import v1.g;

/* JADX INFO: loaded from: classes3.dex */
public class ProfileInstallReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        Bundle extras;
        if (intent == null) {
            return;
        }
        String action = intent.getAction();
        if ("androidx.profileinstaller.action.INSTALL_PROFILE".equals(action)) {
            g.t(context, new a(0), new d(this, 19), true);
            return;
        }
        if ("androidx.profileinstaller.action.SKIP_FILE".equals(action)) {
            Bundle extras2 = intent.getExtras();
            if (extras2 != null) {
                String string = extras2.getString("EXTRA_SKIP_FILE_OPERATION");
                if (!"WRITE_SKIP_FILE".equals(string)) {
                    if ("DELETE_SKIP_FILE".equals(string)) {
                        new File(context.getFilesDir(), "profileinstaller_profileWrittenFor_lastUpdateTime.dat").delete();
                        Log.d("ProfileInstaller", "RESULT_DELETE_SKIP_FILE_SUCCESS");
                        setResultCode(11);
                        return;
                    }
                    return;
                }
                d dVar = new d(this, 19);
                try {
                    g.e(context.getPackageManager().getPackageInfo(context.getApplicationContext().getPackageName(), 0), context.getFilesDir());
                    dVar.d(10, null);
                    return;
                } catch (PackageManager.NameNotFoundException e5) {
                    dVar.d(7, e5);
                    return;
                }
            }
            return;
        }
        if ("androidx.profileinstaller.action.SAVE_PROFILE".equals(action)) {
            d dVar2 = new d(this, 19);
            if (Build.VERSION.SDK_INT < 24) {
                dVar2.d(13, null);
                return;
            } else {
                Process.sendSignal(Process.myPid(), 10);
                dVar2.d(12, null);
                return;
            }
        }
        if (!"androidx.profileinstaller.action.BENCHMARK_OPERATION".equals(action) || (extras = intent.getExtras()) == null) {
            return;
        }
        String string2 = extras.getString("EXTRA_BENCHMARK_OPERATION");
        d dVar3 = new d(this, 19);
        if (!"DROP_SHADER_CACHE".equals(string2)) {
            dVar3.d(16, null);
            return;
        }
        int i10 = Build.VERSION.SDK_INT;
        if (g.c(i10 >= 34 ? v1.a.a(context).getCacheDir() : i10 >= 24 ? v1.a.a(context).getCodeCacheDir() : i10 == 23 ? context.getCodeCacheDir() : context.getCacheDir())) {
            dVar3.d(14, null);
        } else {
            dVar3.d(15, null);
        }
    }
}
