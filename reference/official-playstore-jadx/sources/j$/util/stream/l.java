package j$.util.stream;

import j$.util.Spliterator;

/* JADX INFO: loaded from: classes2.dex */
public final class l extends h5 {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ int f18769b = 2;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f18770c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Object f18771d;

    public /* synthetic */ l(l5 l5Var) {
        super(l5Var);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public l(h8 h8Var, l5 l5Var) {
        super(l5Var);
        this.f18771d = h8Var;
        this.f18770c = true;
    }

    @Override // j$.util.stream.h5, j$.util.stream.l5
    public final void c(long j10) {
        switch (this.f18769b) {
            case 0:
                this.f18770c = false;
                this.f18771d = null;
                this.f18711a.c(-1L);
                break;
            case 1:
                this.f18711a.c(-1L);
                break;
            default:
                this.f18711a.c(-1L);
                break;
        }
    }

    @Override // java.util.function.Consumer
    /* JADX INFO: renamed from: accept */
    public final void n(Object obj) throws Exception {
        switch (this.f18769b) {
            case 0:
                l5 l5Var = this.f18711a;
                if (obj == null) {
                    if (this.f18770c) {
                        return;
                    }
                    this.f18770c = true;
                    this.f18771d = null;
                    l5Var.n((Object) null);
                    return;
                }
                Object obj2 = this.f18771d;
                if (obj2 == null || !obj.equals(obj2)) {
                    this.f18771d = obj;
                    l5Var.n(obj);
                    return;
                }
                return;
            case 1:
                Stream stream = (Stream) ((j$.util.function.i) ((r) this.f18771d).f18840t).apply(obj);
                if (stream != null) {
                    try {
                        boolean z = this.f18770c;
                        l5 l5Var2 = this.f18711a;
                        if (!z) {
                            ((Stream) stream.sequential()).forEach(l5Var2);
                        } else {
                            Spliterator spliterator = ((Stream) stream.sequential()).spliterator();
                            while (!l5Var2.f() && spliterator.tryAdvance(l5Var2)) {
                            }
                        }
                    } catch (Throwable th) {
                        try {
                            stream.close();
                            break;
                        } catch (Throwable th2) {
                            th.addSuppressed(th2);
                        }
                        throw th;
                    }
                    break;
                }
                if (stream != null) {
                    stream.close();
                    return;
                }
                return;
            default:
                if (this.f18770c) {
                    boolean zTest = ((h8) this.f18771d).f18718t.test(obj);
                    this.f18770c = zTest;
                    if (zTest) {
                        this.f18711a.n(obj);
                        return;
                    }
                    return;
                }
                return;
        }
    }

    @Override // j$.util.stream.h5, j$.util.stream.l5
    public boolean f() {
        switch (this.f18769b) {
            case 1:
                this.f18770c = true;
                return this.f18711a.f();
            case 2:
                return !this.f18770c || this.f18711a.f();
            default:
                return super.f();
        }
    }

    @Override // j$.util.stream.h5, j$.util.stream.l5
    public void end() {
        switch (this.f18769b) {
            case 0:
                this.f18770c = false;
                this.f18771d = null;
                this.f18711a.end();
                break;
            default:
                super.end();
                break;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public l(r rVar, l5 l5Var) {
        super(l5Var);
        this.f18771d = rVar;
    }
}
