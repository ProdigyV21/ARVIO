package androidx.compose.material3;

import androidx.compose.runtime.State;
import androidx.compose.ui.geometry.CornerRadiusKt;
import androidx.compose.ui.geometry.RoundRectKt;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.geometry.SizeKt;
import androidx.compose.ui.graphics.Path;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.LayoutDirection;
import io.ktor.http.ContentDisposition;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\b\u001a\u00020\u0005*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u0003H\n¢\u0006\u0004\b\u0006\u0010\u0007"}, d2 = {"Landroidx/compose/ui/graphics/Path;", "Landroidx/compose/ui/geometry/Size;", ContentDisposition.Parameters.Size, "Landroidx/compose/ui/unit/LayoutDirection;", "<anonymous parameter 1>", "Lx6/t0;", "invoke-12SF9DM", "(Landroidx/compose/ui/graphics/Path;JLandroidx/compose/ui/unit/LayoutDirection;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class SearchBar_androidKt$SearchBar$animatedShape$1$1 extends r implements q<Path, Size, LayoutDirection, t0> {
    final /* synthetic */ State<Float> $animationProgress;
    final /* synthetic */ Density $density;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SearchBar_androidKt$SearchBar$animatedShape$1$1(Density density, State<Float> state) {
        super(3);
        this.$density = density;
        this.$animationProgress = state;
    }

    @Override // r7.q
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
        m1821invoke12SF9DM((Path) obj, ((Size) obj2).getPackedValue(), (LayoutDirection) obj3);
        return t0.f22605a;
    }

    /* JADX INFO: renamed from: invoke-12SF9DM, reason: not valid java name */
    public final void m1821invoke12SF9DM(Path path, long j10, LayoutDirection layoutDirection) {
        path.addRoundRect(RoundRectKt.m3291RoundRectsniSvfs(SizeKt.m3326toRectuvyYCjk(j10), CornerRadiusKt.CornerRadius$default(this.$density.mo285toPx0680j_4(Dp.m5678constructorimpl((1 - this.$animationProgress.getValue().floatValue()) * SearchBar_androidKt.SearchBarCornerRadius)), 0.0f, 2, null)));
    }
}
