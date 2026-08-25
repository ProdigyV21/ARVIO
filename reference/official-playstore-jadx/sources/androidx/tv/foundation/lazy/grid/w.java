package androidx.tv.foundation.lazy.grid;

import androidx.compose.foundation.lazy.layout.LazyLayoutMeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.Constraints;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public final class w {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final p f5318a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final LazyLayoutMeasureScope f5319b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f5320c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ LazyLayoutMeasureScope f5321d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ boolean f5322e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ boolean f5323f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ int f5324g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ int f5325h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ long f5326i;

    public w(p pVar, LazyLayoutMeasureScope lazyLayoutMeasureScope, int i10, boolean z, boolean z5, int i11, int i12, long j10) {
        this.f5321d = lazyLayoutMeasureScope;
        this.f5322e = z;
        this.f5323f = z5;
        this.f5324g = i11;
        this.f5325h = i12;
        this.f5326i = j10;
        this.f5318a = pVar;
        this.f5319b = lazyLayoutMeasureScope;
        this.f5320c = i10;
    }

    public final c0 a(int i10, int i11, long j10) {
        int iM5647getMinHeightimpl;
        p pVar = this.f5318a;
        Object key = pVar.getKey(i10);
        Object contentType = pVar.getContentType(i10);
        List<Placeable> listMo691measure0kLqBqw = this.f5319b.mo691measure0kLqBqw(i10, j10);
        if (Constraints.m5644getHasFixedWidthimpl(j10)) {
            iM5647getMinHeightimpl = Constraints.m5648getMinWidthimpl(j10);
        } else {
            if (!Constraints.m5643getHasFixedHeightimpl(j10)) {
                throw new IllegalArgumentException("does not have fixed height");
            }
            iM5647getMinHeightimpl = Constraints.m5647getMinHeightimpl(j10);
        }
        return new c0(i10, key, this.f5322e, iM5647getMinHeightimpl, i11, this.f5323f, this.f5321d.getLayoutDirection(), this.f5324g, this.f5325h, listMo691measure0kLqBqw, this.f5326i, contentType);
    }
}
