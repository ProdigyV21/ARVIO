package androidx.compose.foundation.interaction;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001:\u0002\u0002\u0003ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0004À\u0006\u0001"}, d2 = {"Landroidx/compose/foundation/interaction/HoverInteraction;", "Landroidx/compose/foundation/interaction/Interaction;", "Enter", "Exit", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public interface HoverInteraction extends Interaction {

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Landroidx/compose/foundation/interaction/HoverInteraction$Enter;", "Landroidx/compose/foundation/interaction/HoverInteraction;", "()V", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Enter implements HoverInteraction {
        public static final int $stable = 0;
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Landroidx/compose/foundation/interaction/HoverInteraction$Exit;", "Landroidx/compose/foundation/interaction/HoverInteraction;", "enter", "Landroidx/compose/foundation/interaction/HoverInteraction$Enter;", "(Landroidx/compose/foundation/interaction/HoverInteraction$Enter;)V", "getEnter", "()Landroidx/compose/foundation/interaction/HoverInteraction$Enter;", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Exit implements HoverInteraction {
        public static final int $stable = 0;
        private final Enter enter;

        public Exit(Enter enter) {
            this.enter = enter;
        }

        public final Enter getEnter() {
            return this.enter;
        }
    }
}
