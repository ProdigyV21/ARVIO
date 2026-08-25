package androidx.compose.ui.platform;

import android.content.res.Configuration;
import androidx.compose.runtime.MutableState;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Landroid/content/res/Configuration;", "it", "Lx6/t0;", "invoke", "(Landroid/content/res/Configuration;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class AndroidCompositionLocals_androidKt$ProvideAndroidCompositionLocals$1$1 extends kotlin.jvm.internal.r implements r7.l<Configuration, t0> {
    final /* synthetic */ MutableState<Configuration> $configuration$delegate;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AndroidCompositionLocals_androidKt$ProvideAndroidCompositionLocals$1$1(MutableState<Configuration> mutableState) {
        super(1);
        this.$configuration$delegate = mutableState;
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Configuration) obj);
        return t0.f22605a;
    }

    public final void invoke(Configuration configuration) {
        this.$configuration$delegate.setValue(new Configuration(configuration));
    }
}
