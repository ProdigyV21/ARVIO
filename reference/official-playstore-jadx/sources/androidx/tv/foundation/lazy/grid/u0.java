package androidx.tv.foundation.lazy.grid;

import androidx.compose.ui.layout.MeasureResult;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public final class u0 implements t0, MeasureResult {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final d0 f5307a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f5308b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final boolean f5309c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final float f5310d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Object f5311e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final int f5312f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ MeasureResult f5313g;

    public u0(d0 d0Var, int i10, boolean z, float f10, MeasureResult measureResult, List list, int i11, int i12) {
        this.f5307a = d0Var;
        this.f5308b = i10;
        this.f5309c = z;
        this.f5310d = f10;
        this.f5311e = list;
        this.f5312f = i11;
        this.f5313g = measureResult;
    }

    @Override // androidx.compose.ui.layout.MeasureResult
    public final Map getAlignmentLines() {
        return this.f5313g.getAlignmentLines();
    }

    @Override // androidx.compose.ui.layout.MeasureResult
    public final int getHeight() {
        return this.f5313g.getHeight();
    }

    @Override // androidx.tv.foundation.lazy.grid.t0
    public final int getTotalItemsCount() {
        return this.f5312f;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.Object, java.util.List] */
    @Override // androidx.tv.foundation.lazy.grid.t0
    public final List getVisibleItemsInfo() {
        return this.f5311e;
    }

    @Override // androidx.compose.ui.layout.MeasureResult
    public final int getWidth() {
        return this.f5313g.getWidth();
    }

    @Override // androidx.compose.ui.layout.MeasureResult
    public final void placeChildren() {
        this.f5313g.placeChildren();
    }
}
