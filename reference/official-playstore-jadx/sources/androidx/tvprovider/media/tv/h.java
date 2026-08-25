package androidx.tvprovider.media.tv;

import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public abstract class h implements h8.a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public Object f6607i;

    public h(h8.h hVar) {
        if (hVar != null) {
            this.f6607i = hVar;
        } else {
            v(0);
            throw null;
        }
    }

    public static /* synthetic */ void v(int i10) {
        String str = i10 != 1 ? "Argument for @NotNull parameter '%s' of %s.%s must not be null" : "@NotNull method %s.%s must not return null";
        Object[] objArr = new Object[i10 != 1 ? 3 : 2];
        if (i10 != 1) {
            objArr[0] = "annotations";
        } else {
            objArr[0] = "kotlin/reflect/jvm/internal/impl/descriptors/annotations/AnnotatedImpl";
        }
        if (i10 != 1) {
            objArr[1] = "kotlin/reflect/jvm/internal/impl/descriptors/annotations/AnnotatedImpl";
        } else {
            objArr[1] = "getAnnotations";
        }
        if (i10 != 1) {
            objArr[2] = "<init>";
        }
        String str2 = String.format(str, objArr);
        if (i10 == 1) {
            throw new IllegalStateException(str2);
        }
    }

    public void g0(int i10, String str, Exception exc, String str2) {
        for (q2.d dVar : (List) ((io.sentry.android.replay.r) this.f6607i).f16996a) {
            dVar.getClass();
            dVar.a(str2, i10, str, exc);
        }
    }

    @Override // h8.a
    public h8.h getAnnotations() {
        h8.h hVar = (h8.h) this.f6607i;
        if (hVar != null) {
            return hVar;
        }
        v(1);
        throw null;
    }
}
