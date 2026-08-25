package androidx.media3.session;

import android.os.Bundle;
import androidx.media3.common.MediaItem;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class f implements com.google.common.base.m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f3974a;

    public /* synthetic */ f(int i10) {
        this.f3974a = i10;
    }

    @Override // com.google.common.base.m
    public final Object apply(Object obj) {
        switch (this.f3974a) {
            case 0:
                return ((CommandButton) obj).toBundle();
            case 1:
                return ((MediaItem) obj).toBundle();
            case 2:
                return MediaItem.fromBundle((Bundle) obj);
            default:
                return ((MediaItem) obj).toBundleIncludeLocalConfiguration();
        }
    }
}
