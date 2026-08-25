package androidx.tv.material3;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.ImageBitmap;
import androidx.compose.ui.graphics.painter.Painter;
import androidx.compose.ui.graphics.vector.ImageVector;

/* JADX INFO: loaded from: classes3.dex */
public final class p extends kotlin.jvm.internal.r implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f6276i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ long f6277l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f6278m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ int f6279n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ Object f6280o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ Object f6281p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ Object f6282q;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ p(Object obj, Object obj2, Object obj3, long j10, int i10, int i11, int i12) {
        super(2);
        this.f6276i = i12;
        this.f6280o = obj;
        this.f6281p = obj2;
        this.f6282q = obj3;
        this.f6277l = j10;
        this.f6278m = i10;
        this.f6279n = i11;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        switch (this.f6276i) {
            case 0:
                ((Number) obj2).intValue();
                r7.p pVar = (r7.p) this.f6280o;
                r7.p pVar2 = (r7.p) this.f6281p;
                r7.p pVar3 = (r7.p) this.f6282q;
                CardContainerKt.m5978CardContainerContentww6aTOc(pVar, pVar2, pVar3, this.f6277l, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(this.f6278m | 1), this.f6279n);
                break;
            case 1:
                ((Number) obj2).intValue();
                ImageVector imageVector = (ImageVector) this.f6280o;
                String str = (String) this.f6281p;
                Modifier modifier = (Modifier) this.f6282q;
                IconKt.m5992Iconww6aTOc(imageVector, str, modifier, this.f6277l, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(this.f6278m | 1), this.f6279n);
                break;
            case 2:
                ((Number) obj2).intValue();
                ImageBitmap imageBitmap = (ImageBitmap) this.f6280o;
                String str2 = (String) this.f6281p;
                Modifier modifier2 = (Modifier) this.f6282q;
                IconKt.m5990Iconww6aTOc(imageBitmap, str2, modifier2, this.f6277l, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(this.f6278m | 1), this.f6279n);
                break;
            default:
                ((Number) obj2).intValue();
                Painter painter = (Painter) this.f6280o;
                String str3 = (String) this.f6281p;
                Modifier modifier3 = (Modifier) this.f6282q;
                IconKt.m5991Iconww6aTOc(painter, str3, modifier3, this.f6277l, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(this.f6278m | 1), this.f6279n);
                break;
        }
        return x6.t0.f22605a;
    }
}
