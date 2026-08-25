package z4;

import android.util.Log;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes4.dex */
public final class e implements com.google.android.gms.common.api.internal.b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final AtomicReference f23180a = new AtomicReference();

    @Override // com.google.android.gms.common.api.internal.b
    public final void a(boolean z) {
        synchronized (g.k) {
            try {
                for (g gVar : new ArrayList(g.f23183l.values())) {
                    if (gVar.f23188e.get()) {
                        Log.d("FirebaseApp", "Notifying background state change listeners.");
                        Iterator it = gVar.f23192i.iterator();
                        while (it.hasNext()) {
                            ((d) it.next()).a(z);
                        }
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
