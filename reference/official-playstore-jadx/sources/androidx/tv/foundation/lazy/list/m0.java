package androidx.tv.foundation.lazy.list;

import androidx.compose.animation.core.AnimationState;
import androidx.compose.animation.core.AnimationStateKt;
import androidx.compose.animation.core.AnimationVector1D;
import androidx.compose.animation.core.TwoWayConverter;
import androidx.compose.animation.core.VectorConvertersKt;
import androidx.compose.foundation.gestures.ScrollableState;
import androidx.compose.foundation.gestures.ScrollableStateKt;
import androidx.compose.foundation.interaction.InteractionSourceKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.lazy.layout.LazyLayoutPinnedItemList;
import androidx.compose.foundation.lazy.layout.LazyLayoutPrefetchState;
import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.saveable.ListSaverKt;
import androidx.compose.runtime.saveable.Saver;
import androidx.compose.ui.layout.Remeasurement;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.DensityKt;
import androidx.tv.foundation.lazy.grid.y0;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
public final class m0 implements ScrollableState {
    public static final Saver z = ListSaverKt.listSaver(i0.f5398i, f0.f5388m);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f5427a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public w f5428b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final androidx.tv.foundation.lazy.grid.f0 f5429c;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public float f5433g;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public LazyLayoutPrefetchState.PrefetchHandle f5437l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public boolean f5438m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public Remeasurement f5439n;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final MutableState f5446u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final MutableState f5447v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final LazyLayoutPrefetchState f5448w;
    public ka.k0 x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public AnimationState f5449y;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final a8.e f5430d = new a8.e(this, 5);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final MutableState f5431e = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(a.f5357a, null, 2, null);

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final MutableInteractionSource f5432f = InteractionSourceKt.MutableInteractionSource();

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public Density f5434h = DensityKt.Density(1.0f, 1.0f);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final ScrollableState f5435i = ScrollableStateKt.ScrollableState(new ab.h(this, 6));

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final boolean f5436j = true;
    public int k = -1;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final y0 f5440o = new y0(this, 1);

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final i2.b f5441p = new i2.b();

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final k f5442q = new k(0);

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final i2.i f5443r = new i2.i();

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public long f5444s = ConstraintsKt.Constraints$default(0, 0, 0, 0, 15, null);

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final LazyLayoutPinnedItemList f5445t = new LazyLayoutPinnedItemList();

    public m0(int i10, int i11) {
        this.f5429c = new androidx.tv.foundation.lazy.grid.f0(i10, i11, 1);
        Boolean bool = Boolean.FALSE;
        this.f5446u = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(bool, null, 2, null);
        this.f5447v = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(bool, null, 2, null);
        this.f5448w = new LazyLayoutPrefetchState();
        TwoWayConverter<Float, AnimationVector1D> vectorConverter = VectorConvertersKt.getVectorConverter(kotlin.jvm.internal.i.f19743a);
        Float fValueOf = Float.valueOf(0.0f);
        this.f5449y = AnimationStateKt.AnimationState$default(vectorConverter, fValueOf, fValueOf, 0L, 0L, false, 56, null);
    }

    public final int a() {
        return ((MutableIntState) this.f5429c.f5232b).getIntValue();
    }

    public final int b() {
        return ((MutableIntState) this.f5429c.f5233c).getIntValue();
    }

    public final e0 c() {
        return (e0) this.f5431e.getValue();
    }

    public final Object d(int i10, int i11, d7.d dVar) {
        Object objE = androidx.compose.foundation.gestures.f.e(this, null, new k0(this, i10, i11, null), dVar, 1, null);
        return objE == e7.a.f15033i ? objE : t0.f22605a;
    }

    @Override // androidx.compose.foundation.gestures.ScrollableState
    public final float dispatchRawDelta(float f10) {
        return this.f5435i.dispatchRawDelta(f10);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.compose.foundation.gestures.ScrollableState
    public final boolean getCanScrollBackward() {
        return ((Boolean) this.f5447v.getValue()).booleanValue();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.compose.foundation.gestures.ScrollableState
    public final boolean getCanScrollForward() {
        return ((Boolean) this.f5446u.getValue()).booleanValue();
    }

    @Override // androidx.compose.foundation.gestures.ScrollableState
    public final boolean isScrollInProgress() {
        return this.f5435i.isScrollInProgress();
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
            boolean r0 = r8 instanceof androidx.tv.foundation.lazy.list.j0
            if (r0 == 0) goto L13
            r0 = r8
            androidx.tv.foundation.lazy.list.j0 r0 = (androidx.tv.foundation.lazy.list.j0) r0
            int r1 = r0.f5406p
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.f5406p = r1
            goto L18
        L13:
            androidx.tv.foundation.lazy.list.j0 r0 = new androidx.tv.foundation.lazy.list.j0
            r0.<init>(r5, r8)
        L18:
            java.lang.Object r8 = r0.f5404n
            int r1 = r0.f5406p
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
            r7.p r7 = r0.f5403m
            androidx.compose.foundation.MutatePriority r6 = r0.f5402l
            androidx.tv.foundation.lazy.list.m0 r1 = r0.f5401i
            k2.c.G(r8)
            goto L51
        L3c:
            k2.c.G(r8)
            r0.f5401i = r5
            r0.f5402l = r6
            r0.f5403m = r7
            r0.f5406p = r3
            i2.b r8 = r5.f5441p
            java.lang.Object r8 = r8.a(r0)
            if (r8 != r4) goto L50
            goto L62
        L50:
            r1 = r5
        L51:
            androidx.compose.foundation.gestures.ScrollableState r8 = r1.f5435i
            r1 = 0
            r0.f5401i = r1
            r0.f5402l = r1
            r0.f5403m = r1
            r0.f5406p = r2
            java.lang.Object r6 = r8.scroll(r6, r7, r0)
            if (r6 != r4) goto L63
        L62:
            return r4
        L63:
            x6.t0 r6 = x6.t0.f22605a
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.tv.foundation.lazy.list.m0.scroll(androidx.compose.foundation.MutatePriority, r7.p, d7.d):java.lang.Object");
    }
}
