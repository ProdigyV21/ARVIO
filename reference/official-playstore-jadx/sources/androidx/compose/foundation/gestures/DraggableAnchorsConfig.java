package androidx.compose.foundation.gestures;

import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\b\u0005\b\u0007\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u001c\u0010\b\u001a\u00020\u0007*\u00028\u00002\u0006\u0010\u0006\u001a\u00020\u0005H\u0086\u0004¢\u0006\u0004\b\b\u0010\tR&\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00050\n8\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000e¨\u0006\u000f"}, d2 = {"Landroidx/compose/foundation/gestures/DraggableAnchorsConfig;", "T", "", "<init>", "()V", "", "position", "Lx6/t0;", "at", "(Ljava/lang/Object;F)V", "", "anchors", "Ljava/util/Map;", "getAnchors$foundation_release", "()Ljava/util/Map;", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class DraggableAnchorsConfig<T> {
    public static final int $stable = 8;
    private final Map<T, Float> anchors = new LinkedHashMap();

    public final void at(T t2, float f10) {
        this.anchors.put(t2, Float.valueOf(f10));
    }

    public final Map<T, Float> getAnchors$foundation_release() {
        return this.anchors;
    }
}
