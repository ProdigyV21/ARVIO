package androidx.core.app;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.util.Log;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: classes3.dex */
public final class f2 implements Iterable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final ArrayList f1985i = new ArrayList();

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final Context f1986l;

    public f2(Context context) {
        this.f1986l = context;
    }

    public final void a(ComponentName componentName) {
        Context context = this.f1986l;
        ArrayList arrayList = this.f1985i;
        int size = arrayList.size();
        try {
            for (Intent intentU = m2.f0.u(context, componentName); intentU != null; intentU = m2.f0.u(context, intentU.getComponent())) {
                arrayList.add(size, intentU);
            }
        } catch (PackageManager.NameNotFoundException e5) {
            Log.e("TaskStackBuilder", "Bad ComponentName while traversing activity parent metadata");
            throw new IllegalArgumentException(e5);
        }
    }

    public final void b() {
        ArrayList arrayList = this.f1985i;
        if (arrayList.isEmpty()) {
            throw new IllegalStateException("No intents added to TaskStackBuilder; cannot startActivities");
        }
        Intent[] intentArr = (Intent[]) arrayList.toArray(new Intent[0]);
        intentArr[0] = new Intent(intentArr[0]).addFlags(268484608);
        this.f1986l.startActivities(intentArr, null);
    }

    @Override // java.lang.Iterable
    public final Iterator iterator() {
        return this.f1985i.iterator();
    }
}
