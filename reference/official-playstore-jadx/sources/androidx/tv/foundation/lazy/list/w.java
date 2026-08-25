package androidx.tv.foundation.lazy.list;

import androidx.compose.ui.layout.MeasureResult;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public final class w implements e0, MeasureResult {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final x f5495a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f5496b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final boolean f5497c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final float f5498d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final float f5499e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Object f5500f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final int f5501g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final int f5502h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final int f5503i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final /* synthetic */ MeasureResult f5504j;

    public w(x xVar, int i10, boolean z, float f10, MeasureResult measureResult, float f11, List list, int i11, int i12, int i13) {
        this.f5495a = xVar;
        this.f5496b = i10;
        this.f5497c = z;
        this.f5498d = f10;
        this.f5499e = f11;
        this.f5500f = list;
        this.f5501g = i11;
        this.f5502h = i12;
        this.f5503i = i13;
        this.f5504j = measureResult;
    }

    @Override // androidx.compose.ui.layout.MeasureResult
    public final Map getAlignmentLines() {
        return this.f5504j.getAlignmentLines();
    }

    @Override // androidx.compose.ui.layout.MeasureResult
    public final int getHeight() {
        return this.f5504j.getHeight();
    }

    @Override // androidx.tv.foundation.lazy.list.e0
    public final int getMainAxisItemSpacing() {
        return this.f5503i;
    }

    @Override // androidx.tv.foundation.lazy.list.e0
    public final int getTotalItemsCount() {
        return this.f5502h;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.Object, java.util.List] */
    @Override // androidx.tv.foundation.lazy.list.e0
    public final List getVisibleItemsInfo() {
        return this.f5500f;
    }

    @Override // androidx.compose.ui.layout.MeasureResult
    public final int getWidth() {
        return this.f5504j.getWidth();
    }

    @Override // androidx.compose.ui.layout.MeasureResult
    public final void placeChildren() {
        this.f5504j.placeChildren();
    }
}
