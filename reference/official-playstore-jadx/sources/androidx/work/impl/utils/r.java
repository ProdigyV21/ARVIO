package androidx.work.impl.utils;

import android.content.Context;
import androidx.work.impl.WorkDatabase;
import java.util.UUID;

/* JADX INFO: loaded from: classes3.dex */
public final class r implements m2.k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final androidx.work.impl.utils.taskexecutor.b f7068a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final androidx.work.impl.foreground.a f7069b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final androidx.work.impl.model.q f7070c;

    static {
        m2.t.f("WMFgUpdater");
    }

    public r(WorkDatabase workDatabase, androidx.work.impl.g gVar, androidx.work.impl.utils.taskexecutor.d dVar) {
        this.f7069b = gVar;
        this.f7068a = dVar;
        this.f7070c = workDatabase.t();
    }

    public final androidx.work.impl.utils.futures.k a(Context context, UUID uuid, m2.j jVar) {
        androidx.work.impl.utils.futures.k kVar = new androidx.work.impl.utils.futures.k();
        this.f7068a.d(new q(this, kVar, uuid, jVar, context));
        return kVar;
    }
}
