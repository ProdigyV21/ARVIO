package androidx.mediarouter.media;

import java.util.ArrayList;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes3.dex */
public abstract class s extends u {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object f4476a = new Object();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Executor f4477b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public b f4478c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public m f4479d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public ArrayList f4480e;

    public final void j(m mVar, ArrayList arrayList) {
        if (mVar == null) {
            throw new NullPointerException("groupRoute must not be null");
        }
        synchronized (this.f4476a) {
            try {
                try {
                    Executor executor = this.f4477b;
                    if (executor != null) {
                        executor.execute(new o(this, this.f4478c, mVar, arrayList, 0));
                    } else {
                        this.f4479d = mVar;
                        this.f4480e = new ArrayList(arrayList);
                    }
                } catch (Throwable th) {
                    th = th;
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                throw th;
            }
        }
    }
}
