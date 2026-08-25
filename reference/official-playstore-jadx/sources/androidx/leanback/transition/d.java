package androidx.leanback.transition;

import android.view.View;

/* JADX INFO: loaded from: classes3.dex */
public final class d extends f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f2919a;

    public /* synthetic */ d(int i10) {
        this.f2919a = i10;
    }

    @Override // androidx.leanback.transition.SlideKitkat.a
    public final float d(View view) {
        switch (this.f2919a) {
            case 0:
                return view.getTranslationY() - view.getHeight();
            default:
                return view.getTranslationY() + view.getHeight();
        }
    }
}
