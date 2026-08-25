package androidx.compose.material.ripple;

import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Landroidx/compose/material/ripple/RippleTheme;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class RippleThemeKt$LocalRippleTheme$1 extends r implements a<RippleTheme> {
    public static final RippleThemeKt$LocalRippleTheme$1 INSTANCE = new RippleThemeKt$LocalRippleTheme$1();

    public RippleThemeKt$LocalRippleTheme$1() {
        super(0);
    }

    @Override // r7.a
    public final RippleTheme invoke() {
        return DebugRippleTheme.INSTANCE;
    }
}
