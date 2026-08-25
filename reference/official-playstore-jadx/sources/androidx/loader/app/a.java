package androidx.loader.app;

import android.os.Bundle;

/* JADX INFO: loaded from: classes3.dex */
public interface a<D> {
    androidx.loader.content.e onCreateLoader(int i10, Bundle bundle);

    void onLoadFinished(androidx.loader.content.e eVar, Object obj);

    void onLoaderReset(androidx.loader.content.e eVar);
}
