package androidx.compose.foundation.lazy.staggeredgrid;

import kotlin.Metadata;
import kotlin.jvm.internal.h;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\b\u0007\u0018\u0000 \u00072\u00020\u0001:\u0001\u0007B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0014\u0010\u0002\u001a\u00020\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\b"}, d2 = {"Landroidx/compose/foundation/lazy/staggeredgrid/StaggeredGridItemSpan;", "", "value", "", "(I)V", "getValue$foundation_release", "()I", "Companion", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class StaggeredGridItemSpan {
    public static final int $stable = 0;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final StaggeredGridItemSpan FullLine = new StaggeredGridItemSpan(0);
    private static final StaggeredGridItemSpan SingleLane = new StaggeredGridItemSpan(1);
    private final int value;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0007\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006¨\u0006\t"}, d2 = {"Landroidx/compose/foundation/lazy/staggeredgrid/StaggeredGridItemSpan$Companion;", "", "()V", "FullLine", "Landroidx/compose/foundation/lazy/staggeredgrid/StaggeredGridItemSpan;", "getFullLine", "()Landroidx/compose/foundation/lazy/staggeredgrid/StaggeredGridItemSpan;", "SingleLane", "getSingleLane", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(h hVar) {
            this();
        }

        public final StaggeredGridItemSpan getFullLine() {
            return StaggeredGridItemSpan.FullLine;
        }

        public final StaggeredGridItemSpan getSingleLane() {
            return StaggeredGridItemSpan.SingleLane;
        }

        private Companion() {
        }
    }

    private StaggeredGridItemSpan(int i10) {
        this.value = i10;
    }

    /* JADX INFO: renamed from: getValue$foundation_release, reason: from getter */
    public final int getValue() {
        return this.value;
    }
}
