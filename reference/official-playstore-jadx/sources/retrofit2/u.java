package retrofit2;

import java.lang.reflect.Method;
import kotlin.KotlinNullPointerException;

/* JADX INFO: loaded from: classes5.dex */
public final class u implements f, com.google.android.gms.tasks.c {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f21593i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ ka.l f21594l;

    public /* synthetic */ u(ka.l lVar, int i10) {
        this.f21593i = i10;
        this.f21594l = lVar;
    }

    @Override // retrofit2.f
    public void C(c cVar, r0 r0Var) {
        switch (this.f21593i) {
            case 0:
                boolean zK = r0Var.f21578a.k();
                ka.l lVar = this.f21594l;
                if (!zK) {
                    lVar.resumeWith(new x6.c0(new HttpException(r0Var)));
                    return;
                }
                Object obj = r0Var.f21579b;
                if (obj != null) {
                    lVar.resumeWith(obj);
                    return;
                }
                Object objCast = s.class.cast(cVar.request().f15734e.get(s.class));
                if (objCast == null) {
                    KotlinNullPointerException kotlinNullPointerException = new KotlinNullPointerException();
                    kotlin.jvm.internal.p.e(kotlinNullPointerException, kotlin.jvm.internal.p.class.getName());
                    throw kotlinNullPointerException;
                }
                Method method = ((s) objCast).f21580a;
                lVar.resumeWith(new x6.c0(new KotlinNullPointerException("Response from " + method.getDeclaringClass().getName() + '.' + method.getName() + " was null but response body type was declared as non-null")));
                return;
            default:
                this.f21594l.resumeWith(r0Var);
                return;
        }
    }

    @Override // com.google.android.gms.tasks.c
    public void onComplete(com.google.android.gms.tasks.h hVar) {
        Exception excE = hVar.e();
        if (excE != null) {
            this.f21594l.resumeWith(new x6.c0(excE));
        } else if (((com.google.android.gms.tasks.t) hVar).f13883d) {
            this.f21594l.cancel(null);
        } else {
            this.f21594l.resumeWith(hVar.f());
        }
    }

    @Override // retrofit2.f
    public void onFailure(Throwable th) {
        switch (this.f21593i) {
            case 0:
                this.f21594l.resumeWith(new x6.c0(th));
                break;
            default:
                this.f21594l.resumeWith(new x6.c0(th));
                break;
        }
    }
}
