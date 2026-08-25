package i2;

import androidx.compose.ui.semantics.CollectionInfo;
import androidx.tv.foundation.lazy.list.k0;
import androidx.tv.material3.w3;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\b`\u0018\u00002\u00020\u0001ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0002À\u0006\u0001"}, d2 = {"Li2/m;", "", "tv-foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public interface m {
    Object a(int i10, k0 k0Var);

    Object b(float f10, w3 w3Var);

    CollectionInfo collectionInfo();

    boolean getCanScrollForward();

    float getCurrentPosition();
}
