package androidx.leanback.transition;

import android.util.Property;
import android.view.View;
import androidx.leanback.transition.SlideKitkat;

/* JADX INFO: loaded from: classes3.dex */
public abstract class e implements SlideKitkat.a {
    @Override // androidx.leanback.transition.SlideKitkat.a
    public final Property c() {
        return View.TRANSLATION_X;
    }

    @Override // androidx.leanback.transition.SlideKitkat.a
    public final float e(View view) {
        return view.getTranslationX();
    }
}
