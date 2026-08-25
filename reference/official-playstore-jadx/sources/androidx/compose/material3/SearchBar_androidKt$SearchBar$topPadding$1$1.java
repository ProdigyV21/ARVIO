package androidx.compose.material3;

import androidx.compose.foundation.layout.WindowInsetsKt;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;
import kotlin.jvm.internal.r;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"<anonymous>", "Landroidx/compose/ui/unit/Dp;", "invoke-D9Ej5fM", "()F"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class SearchBar_androidKt$SearchBar$topPadding$1$1 extends r implements r7.a<Dp> {
    final /* synthetic */ Density $density;
    final /* synthetic */ MutableWindowInsets $unconsumedInsets;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SearchBar_androidKt$SearchBar$topPadding$1$1(MutableWindowInsets mutableWindowInsets, Density density) {
        super(0);
        this.$unconsumedInsets = mutableWindowInsets;
        this.$density = density;
    }

    @Override // r7.a
    public /* bridge */ /* synthetic */ Object invoke() {
        return Dp.m5676boximpl(m1822invokeD9Ej5fM());
    }

    /* JADX INFO: renamed from: invoke-D9Ej5fM, reason: not valid java name */
    public final float m1822invokeD9Ej5fM() {
        return Dp.m5678constructorimpl(WindowInsetsKt.asPaddingValues(this.$unconsumedInsets, this.$density).getTop() + SearchBar_androidKt.getSearchBarVerticalPadding());
    }
}
