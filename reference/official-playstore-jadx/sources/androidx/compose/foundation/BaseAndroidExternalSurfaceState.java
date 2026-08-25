package androidx.compose.foundation;

import android.view.Surface;
import d7.d;
import f7.e;
import f7.j;
import io.ktor.http.ContentDisposition;
import java.util.concurrent.CancellationException;
import ka.k0;
import ka.m0;
import ka.v1;
import kotlin.Metadata;
import r7.l;
import r7.p;
import r7.q;
import r7.s;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0003\b\"\u0018\u00002\u00020\u00012\u00020\u0002B\u000f\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006Jw\u0010\u0014\u001a\u00020\u00112f\u0010\u0014\u001ab\b\u0001\u0012\u0004\u0012\u00020\b\u0012\u0013\u0012\u00110\t¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\f\u0012\u0013\u0012\u00110\r¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\u000e\u0012\u0013\u0012\u00110\r¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\u000f\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u0007¢\u0006\u0002\b\u0013H\u0016¢\u0006\u0004\b\u0014\u0010\u0015JV\u0010\u0017\u001a\u00020\u0011*\u00020\t2A\u0010\u0017\u001a=\u0012\u0004\u0012\u00020\t\u0012\u0013\u0012\u00110\r¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\u000e\u0012\u0013\u0012\u00110\r¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\u000f\u0012\u0004\u0012\u00020\u00110\u0016¢\u0006\u0002\b\u0013H\u0016¢\u0006\u0004\b\u0017\u0010\u0018J,\u0010\u001a\u001a\u00020\u0011*\u00020\t2\u0017\u0010\u001a\u001a\u0013\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00110\u0019¢\u0006\u0002\b\u0013H\u0016¢\u0006\u0004\b\u001a\u0010\u001bJ%\u0010\u001c\u001a\u00020\u00112\u0006\u0010\f\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\r¢\u0006\u0004\b\u001c\u0010\u001dJ%\u0010\u001e\u001a\u00020\u00112\u0006\u0010\f\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\r¢\u0006\u0004\b\u001e\u0010\u001dJ\u0015\u0010\u001f\u001a\u00020\u00112\u0006\u0010\f\u001a\u00020\t¢\u0006\u0004\b\u001f\u0010 R\u0017\u0010\u0004\u001a\u00020\u00038\u0006¢\u0006\f\n\u0004\b\u0004\u0010!\u001a\u0004\b\"\u0010#Rx\u0010\u0014\u001ad\b\u0001\u0012\u0004\u0012\u00020\b\u0012\u0013\u0012\u00110\t¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\f\u0012\u0013\u0012\u00110\r¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\u000e\u0012\u0013\u0012\u00110\r¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\u000f\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0012\u0018\u00010\u0007¢\u0006\u0002\b\u00138\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0014\u0010$RS\u0010%\u001a?\u0012\u0004\u0012\u00020\t\u0012\u0013\u0012\u00110\r¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\u000e\u0012\u0013\u0012\u00110\r¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\u000f\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u0016¢\u0006\u0002\b\u00138\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b%\u0010&R)\u0010'\u001a\u0015\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u0019¢\u0006\u0002\b\u00138\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b'\u0010(R\u0018\u0010*\u001a\u0004\u0018\u00010)8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b*\u0010+¨\u0006,"}, d2 = {"Landroidx/compose/foundation/BaseAndroidExternalSurfaceState;", "Landroidx/compose/foundation/AndroidExternalSurfaceScope;", "Landroidx/compose/foundation/SurfaceScope;", "Lka/k0;", "scope", "<init>", "(Lka/k0;)V", "Lkotlin/Function5;", "Landroidx/compose/foundation/SurfaceCoroutineScope;", "Landroid/view/Surface;", "Lx6/y;", ContentDisposition.Parameters.Name, "surface", "", "width", "height", "Ld7/d;", "Lx6/t0;", "", "Lx6/n;", "onSurface", "(Lr7/s;)V", "Lkotlin/Function3;", "onChanged", "(Landroid/view/Surface;Lr7/q;)V", "Lkotlin/Function1;", "onDestroyed", "(Landroid/view/Surface;Lr7/l;)V", "dispatchSurfaceCreated", "(Landroid/view/Surface;II)V", "dispatchSurfaceChanged", "dispatchSurfaceDestroyed", "(Landroid/view/Surface;)V", "Lka/k0;", "getScope", "()Lka/k0;", "Lr7/s;", "onSurfaceChanged", "Lr7/q;", "onSurfaceDestroyed", "Lr7/l;", "Lka/v1;", "job", "Lka/v1;", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
abstract class BaseAndroidExternalSurfaceState implements AndroidExternalSurfaceScope, SurfaceScope {
    private v1 job;
    private s<? super SurfaceCoroutineScope, ? super Surface, ? super Integer, ? super Integer, ? super d<? super t0>, ? extends Object> onSurface;
    private q<? super Surface, ? super Integer, ? super Integer, t0> onSurfaceChanged;
    private l<? super Surface, t0> onSurfaceDestroyed;
    private final k0 scope;

    /* JADX INFO: renamed from: androidx.compose.foundation.BaseAndroidExternalSurfaceState$dispatchSurfaceCreated$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
    @e(c = "androidx.compose.foundation.BaseAndroidExternalSurfaceState$dispatchSurfaceCreated$1", f = "AndroidExternalSurface.android.kt", l = {132, 137}, m = "invokeSuspend")
    public static final class AnonymousClass1 extends j implements p<k0, d<? super t0>, Object> {
        final /* synthetic */ int $height;
        final /* synthetic */ Surface $surface;
        final /* synthetic */ int $width;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(Surface surface, int i10, int i11, d<? super AnonymousClass1> dVar) {
            super(2, dVar);
            this.$surface = surface;
            this.$width = i10;
            this.$height = i11;
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            AnonymousClass1 anonymousClass1 = BaseAndroidExternalSurfaceState.this.new AnonymousClass1(this.$surface, this.$width, this.$height, dVar);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        /* JADX WARN: Code restructure failed: missing block: B:18:0x0064, code lost:
        
            if (r4.invoke(r5, r6, r7, r8, r10) == r3) goto L19;
         */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r11) {
            /*
                r10 = this;
                int r0 = r10.label
                r1 = 2
                r2 = 1
                e7.a r3 = e7.a.f15033i
                if (r0 == 0) goto L20
                if (r0 == r2) goto L18
                if (r0 != r1) goto L10
                k2.c.G(r11)
                goto L67
            L10:
                java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r11.<init>(r0)
                throw r11
            L18:
                java.lang.Object r0 = r10.L$0
                ka.k0 r0 = (ka.k0) r0
                k2.c.G(r11)
                goto L3b
            L20:
                k2.c.G(r11)
                java.lang.Object r11 = r10.L$0
                r0 = r11
                ka.k0 r0 = (ka.k0) r0
                androidx.compose.foundation.BaseAndroidExternalSurfaceState r11 = androidx.compose.foundation.BaseAndroidExternalSurfaceState.this
                ka.v1 r11 = androidx.compose.foundation.BaseAndroidExternalSurfaceState.access$getJob$p(r11)
                if (r11 == 0) goto L3b
                r10.L$0 = r0
                r10.label = r2
                java.lang.Object r11 = ka.m0.h(r11, r10)
                if (r11 != r3) goto L3b
                goto L66
            L3b:
                androidx.compose.foundation.BaseAndroidExternalSurfaceState$dispatchSurfaceCreated$1$receiver$1 r5 = new androidx.compose.foundation.BaseAndroidExternalSurfaceState$dispatchSurfaceCreated$1$receiver$1
                androidx.compose.foundation.BaseAndroidExternalSurfaceState r11 = androidx.compose.foundation.BaseAndroidExternalSurfaceState.this
                r5.<init>(r11, r0)
                androidx.compose.foundation.BaseAndroidExternalSurfaceState r11 = androidx.compose.foundation.BaseAndroidExternalSurfaceState.this
                r7.s r4 = androidx.compose.foundation.BaseAndroidExternalSurfaceState.access$getOnSurface$p(r11)
                if (r4 == 0) goto L67
                android.view.Surface r6 = r10.$surface
                int r11 = r10.$width
                java.lang.Integer r7 = new java.lang.Integer
                r7.<init>(r11)
                int r11 = r10.$height
                java.lang.Integer r8 = new java.lang.Integer
                r8.<init>(r11)
                r11 = 0
                r10.L$0 = r11
                r10.label = r1
                r9 = r10
                java.lang.Object r11 = r4.invoke(r5, r6, r7, r8, r9)
                if (r11 != r3) goto L67
            L66:
                return r3
            L67:
                x6.t0 r11 = x6.t0.f22605a
                return r11
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.BaseAndroidExternalSurfaceState.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d<? super t0> dVar) {
            return ((AnonymousClass1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    public BaseAndroidExternalSurfaceState(k0 k0Var) {
        this.scope = k0Var;
    }

    public final void dispatchSurfaceChanged(Surface surface, int width, int height) {
        q<? super Surface, ? super Integer, ? super Integer, t0> qVar = this.onSurfaceChanged;
        if (qVar != null) {
            qVar.invoke(surface, Integer.valueOf(width), Integer.valueOf(height));
        }
    }

    public final void dispatchSurfaceCreated(Surface surface, int width, int height) {
        if (this.onSurface != null) {
            this.job = m0.p(this.scope, null, 4, new AnonymousClass1(surface, width, height, null), 1);
        }
    }

    public final void dispatchSurfaceDestroyed(Surface surface) {
        l<? super Surface, t0> lVar = this.onSurfaceDestroyed;
        if (lVar != null) {
            lVar.invoke(surface);
        }
        v1 v1Var = this.job;
        if (v1Var != null) {
            v1Var.cancel((CancellationException) null);
        }
        this.job = null;
    }

    public final k0 getScope() {
        return this.scope;
    }

    @Override // androidx.compose.foundation.SurfaceScope
    public void onChanged(Surface surface, q<? super Surface, ? super Integer, ? super Integer, t0> qVar) {
        this.onSurfaceChanged = qVar;
    }

    @Override // androidx.compose.foundation.SurfaceScope
    public void onDestroyed(Surface surface, l<? super Surface, t0> lVar) {
        this.onSurfaceDestroyed = lVar;
    }

    @Override // androidx.compose.foundation.AndroidExternalSurfaceScope
    public void onSurface(s<? super SurfaceCoroutineScope, ? super Surface, ? super Integer, ? super Integer, ? super d<? super t0>, ? extends Object> onSurface) {
        this.onSurface = onSurface;
    }
}
