package androidx.media3.extractor.text;

import androidx.media3.common.text.Cue;
import com.google.common.base.m;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class a implements m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f3900a;

    public /* synthetic */ a(int i10) {
        this.f3900a = i10;
    }

    @Override // com.google.common.base.m
    public final Object apply(Object obj) {
        switch (this.f3900a) {
            case 0:
                return CuesWithTimingSubtitle.lambda$static$0((CuesWithTiming) obj);
            default:
                return ((Cue) obj).toSerializableBundle();
        }
    }
}
