package t5;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/* JADX INFO: loaded from: classes4.dex */
public final class c implements ea.d {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static volatile c f21893b;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final HashSet f21894a;

    public c(int i10) {
        switch (i10) {
            case 1:
                this.f21894a = new HashSet();
                break;
            default:
                this.f21894a = new HashSet();
                break;
        }
    }

    public Set a() {
        Set setUnmodifiableSet;
        synchronized (this.f21894a) {
            setUnmodifiableSet = Collections.unmodifiableSet(this.f21894a);
        }
        return setUnmodifiableSet;
    }
}
