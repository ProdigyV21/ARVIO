package androidx.work.impl.constraints.trackers;

import android.content.Context;
import android.os.Build;

/* JADX INFO: loaded from: classes3.dex */
public final class m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final a f6840a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final a f6841b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final f f6842c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final a f6843d;

    public m(Context context, androidx.work.impl.utils.taskexecutor.d dVar) {
        a aVar = new a(context.getApplicationContext(), dVar, 0);
        a aVar2 = new a(context.getApplicationContext(), dVar, 1);
        Context applicationContext = context.getApplicationContext();
        String str = j.f6837a;
        f iVar = Build.VERSION.SDK_INT >= 24 ? new i(applicationContext, dVar) : new k(applicationContext, dVar);
        a aVar3 = new a(context.getApplicationContext(), dVar, 2);
        this.f6840a = aVar;
        this.f6841b = aVar2;
        this.f6842c = iVar;
        this.f6843d = aVar3;
    }
}
