package androidx.compose.foundation.gestures;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\b`\u0018\u00002\u00020\u0001J\u001a\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&ø\u0001\u0000¢\u0006\u0004\b\u0006\u0010\u0007J\"\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u0003H&ø\u0001\u0000¢\u0006\u0004\b\u000b\u0010\fø\u0001\u0001\u0082\u0002\r\n\u0005\b¡\u001e0\u0001\n\u0004\b!0\u0001¨\u0006\rÀ\u0006\u0001"}, d2 = {"Landroidx/compose/foundation/gestures/PointerDirectionConfig;", "", "calculateDeltaChange", "", "offset", "Landroidx/compose/ui/geometry/Offset;", "calculateDeltaChange-k-4lQ0M", "(J)F", "calculatePostSlopOffset", "totalPositionChange", "touchSlop", "calculatePostSlopOffset-8S9VItk", "(JF)J", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public interface PointerDirectionConfig {
    /* JADX INFO: renamed from: calculateDeltaChange-k-4lQ0M */
    float mo326calculateDeltaChangek4lQ0M(long offset);

    /* JADX INFO: renamed from: calculatePostSlopOffset-8S9VItk */
    long mo327calculatePostSlopOffset8S9VItk(long totalPositionChange, float touchSlop);
}
