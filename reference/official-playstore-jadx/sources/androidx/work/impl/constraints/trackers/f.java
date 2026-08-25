package androidx.work.impl.constraints.trackers;

import android.content.Context;
import androidx.activity.s;
import java.util.LinkedHashSet;
import kotlin.collections.x;

/* JADX INFO: loaded from: classes3.dex */
public abstract class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final androidx.work.impl.utils.taskexecutor.d f6827a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Context f6828b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Object f6829c = new Object();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final LinkedHashSet f6830d = new LinkedHashSet();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Object f6831e;

    public f(Context context, androidx.work.impl.utils.taskexecutor.d dVar) {
        this.f6827a = dVar;
        this.f6828b = context.getApplicationContext();
    }

    public abstract Object a();

    public final void b(Object obj) {
        synchronized (this.f6829c) {
            Object obj2 = this.f6831e;
            if (obj2 == null || !obj2.equals(obj)) {
                this.f6831e = obj;
                this.f6827a.f7079d.execute(new s(x.c1(this.f6830d), this, 13));
            }
        }
    }

    public abstract void c();

    public abstract void d();
}
