package androidx.compose.material3;

import androidx.compose.material3.tokens.TypographyTokensKt;
import androidx.compose.ui.text.TextStyle;
import kotlin.Metadata;
import kotlin.jvm.internal.r;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Landroidx/compose/ui/text/TextStyle;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class TextKt$LocalTextStyle$1 extends r implements r7.a<TextStyle> {
    public static final TextKt$LocalTextStyle$1 INSTANCE = new TextKt$LocalTextStyle$1();

    public TextKt$LocalTextStyle$1() {
        super(0);
    }

    @Override // r7.a
    public final TextStyle invoke() {
        return TypographyTokensKt.getDefaultTextStyle();
    }
}
