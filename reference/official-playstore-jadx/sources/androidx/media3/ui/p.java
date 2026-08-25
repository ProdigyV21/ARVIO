package androidx.media3.ui;

import com.google.common.base.r;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class p implements r {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f4258i;

    public /* synthetic */ p(int i10) {
        this.f4258i = i10;
    }

    @Override // com.google.common.base.r
    public final boolean apply(Object obj) {
        switch (this.f4258i) {
            case 0:
                return SubtitleViewUtils.lambda$removeAllEmbeddedStyling$0(obj);
            default:
                return SubtitleViewUtils.lambda$removeEmbeddedFontSizes$1(obj);
        }
    }
}
