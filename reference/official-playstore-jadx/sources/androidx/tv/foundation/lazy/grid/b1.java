package androidx.tv.foundation.lazy.grid;

import androidx.compose.foundation.gestures.ScrollableState;
import androidx.compose.foundation.gestures.ScrollableStateKt;
import androidx.compose.foundation.interaction.InteractionSourceKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.lazy.layout.LazyLayoutPinnedItemList;
import androidx.compose.foundation.lazy.layout.LazyLayoutPrefetchState;
import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotIntStateKt;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.runtime.saveable.ListSaverKt;
import androidx.compose.runtime.saveable.Saver;
import androidx.compose.ui.layout.Remeasurement;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.DensityKt;

/* JADX INFO: loaded from: classes3.dex */
public final class b1 implements ScrollableState {
    public static final Saver x = ListSaverKt.listSaver(j.f5254m, x0.f5329l);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final f0 f5153a;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public float f5156d;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f5161i;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public boolean f5164m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public Remeasurement f5165n;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final MutableState f5172u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final MutableState f5173v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final LazyLayoutPrefetchState f5174w;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final MutableState f5154b = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(a.f5142a, null, 2, null);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final MutableInteractionSource f5155c = InteractionSourceKt.MutableInteractionSource();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final MutableIntState f5157e = SnapshotIntStateKt.mutableIntStateOf(0);

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Density f5158f = DensityKt.Density(1.0f, 1.0f);

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f5159g = true;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final ScrollableState f5160h = ScrollableStateKt.ScrollableState(new ab.h(this, 4));

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final boolean f5162j = true;
    public int k = -1;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final MutableVector f5163l = new MutableVector(new LazyLayoutPrefetchState.PrefetchHandle[16], 0);

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final y0 f5166o = new y0(this, 0);

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final i2.b f5167p = new i2.b();

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final MutableState f5168q = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(x0.f5330m, null, 2, null);

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final o f5169r = new o();

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final i2.i f5170s = new i2.i();

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final LazyLayoutPinnedItemList f5171t = new LazyLayoutPinnedItemList();

    public b1(int i10, int i11) {
        this.f5153a = new f0(i10, i11, 0);
        Boolean bool = Boolean.FALSE;
        this.f5172u = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(bool, null, 2, null);
        this.f5173v = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(bool, null, 2, null);
        this.f5174w = new LazyLayoutPrefetchState();
    }

    public static Object a(b1 b1Var, int i10, f7.j jVar) {
        b1Var.getClass();
        Object objE = androidx.compose.foundation.gestures.f.e(b1Var, null, new a1(b1Var, i10, (d7.d) null), jVar, 1, null);
        return objE == e7.a.f15033i ? objE : x6.t0.f22605a;
    }

    public final int b(p pVar, int i10) {
        int index;
        f0 f0Var = this.f5153a;
        Object obj = f0Var.f5235e;
        if (obj == null || pVar.getItemCount() == 0 || ((i10 < pVar.getItemCount() && obj.equals(pVar.getKey(i10))) || (index = pVar.getIndex(obj)) == -1)) {
            index = i10;
        }
        if (i10 != index) {
            ((MutableIntState) f0Var.f5232b).setIntValue(index);
            ((i2.l) f0Var.f5236f).a(i10);
        }
        return index;
    }

    @Override // androidx.compose.foundation.gestures.ScrollableState
    public final float dispatchRawDelta(float f10) {
        return this.f5160h.dispatchRawDelta(f10);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.compose.foundation.gestures.ScrollableState
    public final boolean getCanScrollBackward() {
        return ((Boolean) this.f5173v.getValue()).booleanValue();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.compose.foundation.gestures.ScrollableState
    public final boolean getCanScrollForward() {
        return ((Boolean) this.f5172u.getValue()).booleanValue();
    }

    @Override // androidx.compose.foundation.gestures.ScrollableState
    public final boolean isScrollInProgress() {
        return this.f5160h.isScrollInProgress();
    }

    /* JADX WARN: Code restructure failed: missing block: B:21:0x0060, code lost:
    
        if (r8.scroll(r6, r7, r0) == r4) goto L22;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    @Override // androidx.compose.foundation.gestures.ScrollableState
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object scroll(androidx.compose.foundation.MutatePriority r6, r7.p r7, d7.d r8) {
        /*
            r5 = this;
            boolean r0 = r8 instanceof androidx.tv.foundation.lazy.grid.z0
            if (r0 == 0) goto L13
            r0 = r8
            androidx.tv.foundation.lazy.grid.z0 r0 = (androidx.tv.foundation.lazy.grid.z0) r0
            int r1 = r0.f5348p
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.f5348p = r1
            goto L18
        L13:
            androidx.tv.foundation.lazy.grid.z0 r0 = new androidx.tv.foundation.lazy.grid.z0
            r0.<init>(r5, r8)
        L18:
            java.lang.Object r8 = r0.f5346n
            int r1 = r0.f5348p
            r2 = 2
            r3 = 1
            e7.a r4 = e7.a.f15033i
            if (r1 == 0) goto L3c
            if (r1 == r3) goto L32
            if (r1 != r2) goto L2a
            k2.c.G(r8)
            goto L63
        L2a:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L32:
            r7.p r7 = r0.f5345m
            androidx.compose.foundation.MutatePriority r6 = r0.f5344l
            androidx.tv.foundation.lazy.grid.b1 r1 = r0.f5343i
            k2.c.G(r8)
            goto L51
        L3c:
            k2.c.G(r8)
            r0.f5343i = r5
            r0.f5344l = r6
            r0.f5345m = r7
            r0.f5348p = r3
            i2.b r8 = r5.f5167p
            java.lang.Object r8 = r8.a(r0)
            if (r8 != r4) goto L50
            goto L62
        L50:
            r1 = r5
        L51:
            androidx.compose.foundation.gestures.ScrollableState r8 = r1.f5160h
            r1 = 0
            r0.f5343i = r1
            r0.f5344l = r1
            r0.f5345m = r1
            r0.f5348p = r2
            java.lang.Object r6 = r8.scroll(r6, r7, r0)
            if (r6 != r4) goto L63
        L62:
            return r4
        L63:
            x6.t0 r6 = x6.t0.f22605a
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.tv.foundation.lazy.grid.b1.scroll(androidx.compose.foundation.MutatePriority, r7.p, d7.d):java.lang.Object");
    }
}
