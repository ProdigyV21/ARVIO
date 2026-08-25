package androidx.work.impl.background.systemjob;

import android.content.ComponentName;
import android.content.Context;
import m2.t;
import m2.u;

/* JADX INFO: loaded from: classes3.dex */
public final class a {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final String f6779c = t.f("SystemJobInfoConverter");

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ComponentName f6780a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final m2.a f6781b;

    public a(Context context, u uVar) {
        this.f6781b = uVar;
        this.f6780a = new ComponentName(context.getApplicationContext(), (Class<?>) SystemJobService.class);
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x006b, code lost:
    
        if (r3 < 26) goto L20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x006e, code lost:
    
        if (r3 >= 24) goto L24;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final android.app.job.JobInfo a(androidx.work.impl.model.p r13, int r14) {
        /*
            Method dump skipped, instruction units count: 294
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.work.impl.background.systemjob.a.a(androidx.work.impl.model.p, int):android.app.job.JobInfo");
    }
}
