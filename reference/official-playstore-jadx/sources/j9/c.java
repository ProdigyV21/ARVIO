package j9;

import d8.k;
import g8.h;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import kotlin.collections.z;
import v9.r0;
import w9.i;

/* JADX INFO: loaded from: classes5.dex */
public final class c implements b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final r0 f19406a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public i f19407b;

    public c(r0 r0Var) {
        this.f19406a = r0Var;
        r0Var.c();
    }

    @Override // j9.b
    public final r0 b() {
        return this.f19406a;
    }

    @Override // v9.o0
    public final Collection d() {
        r0 r0Var = this.f19406a;
        return Collections.singletonList(r0Var.c() == 3 ? r0Var.getType() : j().o());
    }

    @Override // v9.o0
    public final /* bridge */ /* synthetic */ h e() {
        return null;
    }

    @Override // v9.o0
    public final boolean f() {
        return false;
    }

    @Override // v9.o0
    public final List getParameters() {
        return z.f19728i;
    }

    @Override // v9.o0
    public final k j() {
        return this.f19406a.getType().I0().j();
    }

    public final String toString() {
        return "CapturedTypeConstructor(" + this.f19406a + ')';
    }
}
