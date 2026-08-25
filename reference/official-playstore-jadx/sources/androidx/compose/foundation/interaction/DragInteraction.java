package androidx.compose.foundation.interaction;

import androidx.media3.extractor.text.ttml.TtmlNode;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001:\u0003\u0002\u0003\u0004ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0005À\u0006\u0001"}, d2 = {"Landroidx/compose/foundation/interaction/DragInteraction;", "Landroidx/compose/foundation/interaction/Interaction;", "Cancel", "Start", "Stop", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public interface DragInteraction extends Interaction {

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Landroidx/compose/foundation/interaction/DragInteraction$Cancel;", "Landroidx/compose/foundation/interaction/DragInteraction;", TtmlNode.START, "Landroidx/compose/foundation/interaction/DragInteraction$Start;", "(Landroidx/compose/foundation/interaction/DragInteraction$Start;)V", "getStart", "()Landroidx/compose/foundation/interaction/DragInteraction$Start;", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Cancel implements DragInteraction {
        public static final int $stable = 0;
        private final Start start;

        public Cancel(Start start) {
            this.start = start;
        }

        public final Start getStart() {
            return this.start;
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Landroidx/compose/foundation/interaction/DragInteraction$Start;", "Landroidx/compose/foundation/interaction/DragInteraction;", "()V", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Start implements DragInteraction {
        public static final int $stable = 0;
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Landroidx/compose/foundation/interaction/DragInteraction$Stop;", "Landroidx/compose/foundation/interaction/DragInteraction;", TtmlNode.START, "Landroidx/compose/foundation/interaction/DragInteraction$Start;", "(Landroidx/compose/foundation/interaction/DragInteraction$Start;)V", "getStart", "()Landroidx/compose/foundation/interaction/DragInteraction$Start;", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Stop implements DragInteraction {
        public static final int $stable = 0;
        private final Start start;

        public Stop(Start start) {
            this.start = start;
        }

        public final Start getStart() {
            return this.start;
        }
    }
}
