package androidx.compose.foundation.text;

import androidx.compose.foundation.gestures.Orientation;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Landroidx/compose/foundation/text/TextFieldScrollerPosition;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class CoreTextFieldKt$CoreTextField$scrollerPosition$1$1 extends r implements a<TextFieldScrollerPosition> {
    final /* synthetic */ Orientation $orientation;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CoreTextFieldKt$CoreTextField$scrollerPosition$1$1(Orientation orientation) {
        super(0);
        this.$orientation = orientation;
    }

    @Override // r7.a
    public final TextFieldScrollerPosition invoke() {
        return new TextFieldScrollerPosition(this.$orientation, 0.0f, 2, null);
    }
}
