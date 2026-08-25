package kotlin.reflect.jvm.internal.impl.protobuf;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public final class l {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final /* synthetic */ int f19804b = 0;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Map f19805a;

    static {
        new l(0);
    }

    public l() {
        this.f19805a = new HashMap();
    }

    public final void a(t tVar) {
        this.f19805a.put(new k(tVar.f19824a, tVar.f19827d.f19821i), tVar);
    }

    public l(int i10) {
        this.f19805a = Collections.EMPTY_MAP;
    }
}
