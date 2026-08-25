package x6;

import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public final class d0 implements Serializable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Object f22580i;

    public /* synthetic */ d0(Object obj) {
        this.f22580i = obj;
    }

    public static final Throwable a(Object obj) {
        if (obj instanceof c0) {
            return ((c0) obj).f22576i;
        }
        return null;
    }

    public static final boolean b(Object obj) {
        return obj instanceof c0;
    }

    public static final boolean c(Object obj) {
        return !(obj instanceof c0);
    }

    public final /* synthetic */ Object d() {
        return this.f22580i;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof d0) {
            return kotlin.jvm.internal.p.a(this.f22580i, ((d0) obj).f22580i);
        }
        return false;
    }

    public final int hashCode() {
        Object obj = this.f22580i;
        if (obj == null) {
            return 0;
        }
        return obj.hashCode();
    }

    public final String toString() {
        Object obj = this.f22580i;
        if (obj instanceof c0) {
            return ((c0) obj).toString();
        }
        return "Success(" + obj + ')';
    }
}
