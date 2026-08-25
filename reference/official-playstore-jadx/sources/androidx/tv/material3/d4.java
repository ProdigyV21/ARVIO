package androidx.tv.material3;

import androidx.compose.foundation.lazy.layout.LazyLayoutItemProvider;
import androidx.compose.ui.focus.FocusState;

/* JADX INFO: loaded from: classes3.dex */
public final class d4 extends kotlin.jvm.internal.r implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f5917i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ r7.a f5918l;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ d4(int i10, r7.a aVar) {
        super(1);
        this.f5917i = i10;
        this.f5918l = aVar;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        switch (this.f5917i) {
            case 0:
                if (((FocusState) obj).isFocused()) {
                    this.f5918l.invoke();
                }
                return x6.t0.f22605a;
            default:
                LazyLayoutItemProvider lazyLayoutItemProvider = (LazyLayoutItemProvider) this.f5918l.invoke();
                int itemCount = lazyLayoutItemProvider.getItemCount();
                int i10 = 0;
                while (true) {
                    if (i10 >= itemCount) {
                        i10 = -1;
                    } else if (!kotlin.jvm.internal.p.a(lazyLayoutItemProvider.getKey(i10), obj)) {
                        i10++;
                    }
                }
                return Integer.valueOf(i10);
        }
    }
}
