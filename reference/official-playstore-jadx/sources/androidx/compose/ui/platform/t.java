package androidx.compose.ui.platform;

import android.view.View;
import androidx.compose.runtime.Recomposer;
import androidx.compose.ui.platform.WindowRecomposerFactory;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class t implements WindowRecomposerFactory {
    @Override // androidx.compose.ui.platform.WindowRecomposerFactory
    public final Recomposer createRecomposer(View view) {
        return WindowRecomposerFactory.Companion.LifecycleAware$lambda$0(view);
    }
}
