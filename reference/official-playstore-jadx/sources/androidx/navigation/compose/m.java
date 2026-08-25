package androidx.navigation.compose;

import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.tv.material3.s3;
import java.util.List;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
public final class m extends kotlin.jvm.internal.r implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f4585i = 0;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ boolean f4586l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f4587m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f4588n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public m(androidx.navigation.i iVar, List list, boolean z) {
        super(1);
        this.f4587m = iVar;
        this.f4586l = z;
        this.f4588n = list;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        switch (this.f4585i) {
            case 0:
                List list = (List) this.f4588n;
                androidx.navigation.i iVar = (androidx.navigation.i) this.f4587m;
                l lVar = new l(iVar, list, this.f4586l);
                iVar.f4687r.addObserver(lVar);
                return new k(iVar, lVar, 0);
            default:
                SemanticsPropertyReceiver semanticsPropertyReceiver = (SemanticsPropertyReceiver) obj;
                SemanticsPropertiesKt.onClick$default(semanticsPropertyReceiver, null, new s3(0, (r7.a) this.f4587m), 1, null);
                SemanticsPropertiesKt.onLongClick$default(semanticsPropertyReceiver, null, new s3(1, (r7.a) this.f4588n), 1, null);
                if (!this.f4586l) {
                    SemanticsPropertiesKt.disabled(semanticsPropertyReceiver);
                }
                return t0.f22605a;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public m(r7.a aVar, r7.a aVar2, boolean z) {
        super(1);
        this.f4586l = z;
        this.f4587m = aVar;
        this.f4588n = aVar2;
    }
}
