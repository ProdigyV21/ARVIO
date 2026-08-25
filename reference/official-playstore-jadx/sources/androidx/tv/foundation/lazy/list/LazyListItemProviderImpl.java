package androidx.tv.foundation.lazy.list;

import androidx.compose.foundation.lazy.layout.IntervalList;
import androidx.compose.foundation.lazy.layout.LazyLayoutPinnableItemKt;
import androidx.compose.foundation.lazy.layout.LazyLayoutPinnedItemList;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.media3.exoplayer.RendererCapabilities;
import java.util.List;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001J\u001f\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004H\u0017¢\u0006\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Landroidx/tv/foundation/lazy/list/LazyListItemProviderImpl;", "Landroidx/tv/foundation/lazy/list/l;", "", "index", "", "key", "Lx6/t0;", "Item", "(ILjava/lang/Object;Landroidx/compose/runtime/Composer;I)V", "tv-foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class LazyListItemProviderImpl implements l {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final m0 f5349a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final a0 f5350b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final d0 f5351c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final i2.r f5352d;

    /* JADX INFO: renamed from: androidx.tv.foundation.lazy.list.LazyListItemProviderImpl$Item$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\u000b¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "invoke", "(Landroidx/compose/runtime/Composer;I)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass1 extends kotlin.jvm.internal.r implements r7.p<Composer, Integer, t0> {

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        public final /* synthetic */ int f5354l;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(int i10) {
            super(2);
            this.f5354l = i10;
        }

        @Override // r7.p
        public final /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return t0.f22605a;
        }

        public final void invoke(Composer composer, int i10) {
            if ((i10 & 3) == 2 && composer.getSkipping()) {
                composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-159945812, i10, -1, "androidx.tv.foundation.lazy.list.LazyListItemProviderImpl.Item.<anonymous> (LazyListItemProvider.kt:83)");
            }
            LazyListItemProviderImpl lazyListItemProviderImpl = LazyListItemProviderImpl.this;
            IntervalList intervals = lazyListItemProviderImpl.f5350b.getIntervals();
            int i11 = this.f5354l;
            IntervalList.Interval interval = intervals.get(i11);
            ((z) interval.getValue()).f5526c.invoke(lazyListItemProviderImpl.f5351c, Integer.valueOf(i11 - interval.getStartIndex()), composer, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    }

    public LazyListItemProviderImpl(m0 m0Var, a0 a0Var, d0 d0Var, i2.r rVar) {
        this.f5349a = m0Var;
        this.f5350b = a0Var;
        this.f5351c = d0Var;
        this.f5352d = rVar;
    }

    @Override // androidx.compose.foundation.lazy.layout.LazyLayoutItemProvider
    public void Item(int i10, Object obj, Composer composer, int i11) {
        int i12;
        int i13;
        Object obj2;
        Composer composerStartRestartGroup = composer.startRestartGroup(202354976);
        if ((i11 & 6) == 0) {
            i12 = (composerStartRestartGroup.changed(i10) ? 4 : 2) | i11;
        } else {
            i12 = i11;
        }
        if ((i11 & 48) == 0) {
            i12 |= composerStartRestartGroup.changedInstance(obj) ? 32 : 16;
        }
        if ((i11 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            i12 |= composerStartRestartGroup.changed(this) ? 256 : 128;
        }
        if ((i12 & 147) == 146 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
            i13 = i10;
            obj2 = obj;
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(202354976, i12, -1, "androidx.tv.foundation.lazy.list.LazyListItemProviderImpl.Item (LazyListItemProvider.kt:81)");
            }
            i13 = i10;
            obj2 = obj;
            LazyLayoutPinnableItemKt.LazyLayoutPinnableItem(obj2, i13, this.f5349a.f5445t, ComposableLambdaKt.composableLambda(composerStartRestartGroup, -159945812, true, new AnonymousClass1(i10)), composerStartRestartGroup, ((i12 << 3) & 112) | ((i12 >> 3) & 14) | 3072 | (LazyLayoutPinnedItemList.$stable << 6));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new m(this, i13, obj2, i11));
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LazyListItemProviderImpl)) {
            return false;
        }
        return kotlin.jvm.internal.p.a(this.f5350b, ((LazyListItemProviderImpl) obj).f5350b);
    }

    @Override // androidx.compose.foundation.lazy.layout.LazyLayoutItemProvider
    public final Object getContentType(int i10) {
        return this.f5350b.getContentType(i10);
    }

    @Override // androidx.tv.foundation.lazy.list.l
    public final List getHeaderIndexes() {
        this.f5350b.getClass();
        return kotlin.collections.z.f19728i;
    }

    @Override // androidx.compose.foundation.lazy.layout.LazyLayoutItemProvider
    public final int getIndex(Object obj) {
        return this.f5352d.getIndex(obj);
    }

    @Override // androidx.compose.foundation.lazy.layout.LazyLayoutItemProvider
    public final int getItemCount() {
        return this.f5350b.getItemCount();
    }

    @Override // androidx.tv.foundation.lazy.list.l
    /* JADX INFO: renamed from: getItemScope, reason: from getter */
    public final d0 getF5351c() {
        return this.f5351c;
    }

    @Override // androidx.compose.foundation.lazy.layout.LazyLayoutItemProvider
    public final Object getKey(int i10) {
        Object objA = this.f5352d.a(i10);
        return objA == null ? this.f5350b.getKey(i10) : objA;
    }

    @Override // androidx.tv.foundation.lazy.list.l
    public final i2.k getKeyIndexMap() {
        return this.f5352d;
    }

    public final int hashCode() {
        return this.f5350b.hashCode();
    }
}
