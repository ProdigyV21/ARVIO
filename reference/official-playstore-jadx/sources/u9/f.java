package u9;

import com.google.android.gms.internal.auth.d1;
import j8.t0;
import java.util.Collections;

/* JADX INFO: loaded from: classes5.dex */
public final class f extends k implements s {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public volatile fi.iki.elonen.f f22074n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ s8.d f22075o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public f(p pVar, t0 t0Var, s8.d dVar) {
        super(pVar, t0Var);
        this.f22075o = dVar;
        this.f22074n = null;
    }

    public static /* synthetic */ void c(int i10) {
        String str = i10 != 2 ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        Object[] objArr = new Object[i10 != 2 ? 2 : 3];
        if (i10 != 2) {
            objArr[0] = "kotlin/reflect/jvm/internal/impl/storage/LockBasedStorageManager$5";
        } else {
            objArr[0] = "value";
        }
        if (i10 != 2) {
            objArr[1] = "recursionDetected";
        } else {
            objArr[1] = "kotlin/reflect/jvm/internal/impl/storage/LockBasedStorageManager$5";
        }
        if (i10 == 2) {
            objArr[2] = "doPostCompute";
        }
        String str2 = String.format(str, objArr);
        if (i10 == 2) {
            throw new IllegalArgumentException(str2);
        }
    }

    @Override // u9.k
    public final void a(Object obj) {
        fi.iki.elonen.f fVar = new fi.iki.elonen.f();
        fVar.f15416i = obj;
        fVar.f15417l = Thread.currentThread();
        this.f22074n = fVar;
        try {
            if (obj != null) {
                this.f22075o.invoke(obj);
            } else {
                c(2);
                throw null;
            }
        } finally {
            this.f22074n = null;
        }
    }

    @Override // u9.k
    public final d1 b(boolean z) {
        return new d1((Object) new v9.f(Collections.singletonList(x9.k.f22709d)), false);
    }

    @Override // u9.k, r7.a
    public final Object invoke() throws Throwable {
        Object objInvoke;
        fi.iki.elonen.f fVar = this.f22074n;
        if (fVar == null || ((Thread) fVar.f15417l) != Thread.currentThread()) {
            objInvoke = super.invoke();
        } else {
            if (((Thread) fVar.f15417l) != Thread.currentThread()) {
                throw new IllegalStateException("No value in this thread (hasValue should be checked before)");
            }
            objInvoke = fVar.f15416i;
        }
        if (objInvoke != null) {
            return objInvoke;
        }
        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "kotlin/reflect/jvm/internal/impl/storage/LockBasedStorageManager$LockBasedNotNullLazyValueWithPostCompute", "invoke"));
    }
}
