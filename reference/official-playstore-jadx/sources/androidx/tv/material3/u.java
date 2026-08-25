package androidx.tv.material3;

import androidx.compose.ui.draw.CacheDrawScope;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.drawscope.ContentDrawScope;
import androidx.compose.ui.graphics.drawscope.DrawScope;

/* JADX INFO: loaded from: classes3.dex */
public final class u extends kotlin.jvm.internal.r implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f6429i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Brush f6430l;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ u(Brush brush, int i10) {
        super(1);
        this.f6429i = i10;
        this.f6430l = brush;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        switch (this.f6429i) {
            case 0:
                ContentDrawScope contentDrawScope = (ContentDrawScope) obj;
                contentDrawScope.drawContent();
                androidx.compose.ui.graphics.drawscope.c.J(contentDrawScope, this.f6430l, 0L, 0L, 0.0f, null, null, 0, 126, null);
                return x6.t0.f22605a;
            case 1:
                return ((CacheDrawScope) obj).onDrawWithContent(new u(this.f6430l, 0));
            default:
                androidx.compose.ui.graphics.drawscope.c.J((DrawScope) obj, this.f6430l, 0L, 0L, 0.0f, null, null, 0, 126, null);
                return x6.t0.f22605a;
        }
    }
}
