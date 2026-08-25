package i2;

import androidx.compose.foundation.gestures.ScrollScope;
import kotlin.jvm.internal.f0;
import kotlin.jvm.internal.i0;
import kotlin.jvm.internal.k0;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
public final class e extends f7.j implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public f0 f16036i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public k0 f16037l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public i0 f16038m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public float f16039n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public float f16040o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public float f16041p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f16042q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public int f16043r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public /* synthetic */ Object f16044s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final /* synthetic */ int f16045t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final /* synthetic */ g f16046u;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public e(int i10, g gVar, d7.d dVar) {
        super(2, dVar);
        this.f16045t = i10;
        this.f16046u = gVar;
    }

    public static final boolean g(boolean z, g gVar, int i10) {
        if (z) {
            if (gVar.getFirstVisibleItemIndex() > i10) {
                return true;
            }
            return gVar.getFirstVisibleItemIndex() == i10 && gVar.getFirstVisibleItemScrollOffset() > 0;
        }
        if (gVar.getFirstVisibleItemIndex() < i10) {
            return true;
        }
        return gVar.getFirstVisibleItemIndex() == i10 && gVar.getFirstVisibleItemScrollOffset() < 0;
    }

    @Override // f7.a
    public final d7.d create(Object obj, d7.d dVar) {
        e eVar = new e(this.f16045t, this.f16046u, dVar);
        eVar.f16044s = obj;
        return eVar;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        return ((e) create((ScrollScope) obj, (d7.d) obj2)).invokeSuspend(t0.f22605a);
    }

    /* JADX WARN: Code restructure failed: missing block: B:74:0x01f3, code lost:
    
        if (androidx.compose.animation.core.SuspendAnimationKt.animateTo$default(r1, r1, null, !r10, r4, r36, 2, null) != r14) goto L76;
     */
    /* JADX WARN: Removed duplicated region for block: B:26:0x00ba A[Catch: c -> 0x00d9, TryCatch #1 {c -> 0x00d9, blocks: (B:24:0x00b6, B:26:0x00ba, B:28:0x00c0, B:30:0x00cc, B:40:0x00e5), top: B:83:0x00b6 }] */
    /* JADX WARN: Removed duplicated region for block: B:30:0x00cc A[Catch: c -> 0x00d9, TryCatch #1 {c -> 0x00d9, blocks: (B:24:0x00b6, B:26:0x00ba, B:28:0x00c0, B:30:0x00cc, B:40:0x00e5), top: B:83:0x00b6 }] */
    /* JADX WARN: Removed duplicated region for block: B:37:0x00e0  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x011d  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x011f  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0129  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0136  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0170  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0172  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x01d3  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x01d5  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:58:0x0172 -> B:85:0x017c). Please report as a decompilation issue!!! */
    @Override // f7.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r37) {
        /*
            Method dump skipped, instruction units count: 526
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: i2.e.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
