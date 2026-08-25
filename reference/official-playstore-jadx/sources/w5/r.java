package w5;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.Process;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public abstract class r {
    public static ArrayList a(Context context) {
        int i10 = context.getApplicationInfo().uid;
        String str = context.getApplicationInfo().processName;
        Object systemService = context.getSystemService("activity");
        ActivityManager activityManager = systemService instanceof ActivityManager ? (ActivityManager) systemService : null;
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = activityManager != null ? activityManager.getRunningAppProcesses() : null;
        if (runningAppProcesses == null) {
            runningAppProcesses = kotlin.collections.z.f19728i;
        }
        ArrayList arrayListK0 = kotlin.collections.x.k0(runningAppProcesses);
        ArrayList<ActivityManager.RunningAppProcessInfo> arrayList = new ArrayList();
        for (Object obj : arrayListK0) {
            if (((ActivityManager.RunningAppProcessInfo) obj).uid == i10) {
                arrayList.add(obj);
            }
        }
        ArrayList arrayList2 = new ArrayList(kotlin.collections.s.U(arrayList, 10));
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : arrayList) {
            String str2 = runningAppProcessInfo.processName;
            arrayList2.add(new q(str2, runningAppProcessInfo.pid, runningAppProcessInfo.importance, kotlin.jvm.internal.p.a(str2, str)));
        }
        return arrayList2;
    }

    public static q b(Context context) {
        Object next;
        int iMyPid = Process.myPid();
        Iterator it = a(context).iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            if (((q) next).f22481b == iMyPid) {
                break;
            }
        }
        q qVar = (q) next;
        return qVar == null ? new q(c(), iMyPid, 0, false) : qVar;
    }

    public static String c() throws Throwable {
        String processName;
        int i10 = Build.VERSION.SDK_INT;
        if (i10 >= 33) {
            return Process.myProcessName();
        }
        if (i10 >= 28 && (processName = Application.getProcessName()) != null) {
            return processName;
        }
        String strA = c4.g.a();
        return strA != null ? strA : "";
    }
}
