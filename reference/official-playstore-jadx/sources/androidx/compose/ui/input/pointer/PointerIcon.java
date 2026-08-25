package androidx.compose.ui.input.pointer;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\bg\u0018\u0000 \u00022\u00020\u0001:\u0001\u0002ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0003À\u0006\u0001"}, d2 = {"Landroidx/compose/ui/input/pointer/PointerIcon;", "", "Companion", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public interface PointerIcon {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = Companion.$$INSTANCE;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0007\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006R\u0011\u0010\t\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0006R\u0011\u0010\u000b\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u0006¨\u0006\r"}, d2 = {"Landroidx/compose/ui/input/pointer/PointerIcon$Companion;", "", "()V", "Crosshair", "Landroidx/compose/ui/input/pointer/PointerIcon;", "getCrosshair", "()Landroidx/compose/ui/input/pointer/PointerIcon;", "Default", "getDefault", "Hand", "getHand", "Text", "getText", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();
        private static final PointerIcon Default = PointerIcon_androidKt.getPointerIconDefault();
        private static final PointerIcon Crosshair = PointerIcon_androidKt.getPointerIconCrosshair();
        private static final PointerIcon Text = PointerIcon_androidKt.getPointerIconText();
        private static final PointerIcon Hand = PointerIcon_androidKt.getPointerIconHand();

        private Companion() {
        }

        public final PointerIcon getCrosshair() {
            return Crosshair;
        }

        public final PointerIcon getDefault() {
            return Default;
        }

        public final PointerIcon getHand() {
            return Hand;
        }

        public final PointerIcon getText() {
            return Text;
        }
    }
}
