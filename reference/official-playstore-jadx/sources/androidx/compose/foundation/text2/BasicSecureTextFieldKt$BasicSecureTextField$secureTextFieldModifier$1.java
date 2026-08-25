package androidx.compose.foundation.text2;

import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.a;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;", "Lx6/t0;", "invoke", "(Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class BasicSecureTextFieldKt$BasicSecureTextField$secureTextFieldModifier$1 extends r implements l<SemanticsPropertyReceiver, t0> {
    public static final BasicSecureTextFieldKt$BasicSecureTextField$secureTextFieldModifier$1 INSTANCE = new BasicSecureTextFieldKt$BasicSecureTextField$secureTextFieldModifier$1();

    /* JADX INFO: renamed from: androidx.compose.foundation.text2.BasicSecureTextFieldKt$BasicSecureTextField$secureTextFieldModifier$1$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"<anonymous>", "", "invoke", "()Ljava/lang/Boolean;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass1 extends r implements a<Boolean> {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        public AnonymousClass1() {
            super(0);
        }

        @Override // r7.a
        public final Boolean invoke() {
            return Boolean.FALSE;
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text2.BasicSecureTextFieldKt$BasicSecureTextField$secureTextFieldModifier$1$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"<anonymous>", "", "invoke", "()Ljava/lang/Boolean;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass2 extends r implements a<Boolean> {
        public static final AnonymousClass2 INSTANCE = new AnonymousClass2();

        public AnonymousClass2() {
            super(0);
        }

        @Override // r7.a
        public final Boolean invoke() {
            return Boolean.FALSE;
        }
    }

    public BasicSecureTextFieldKt$BasicSecureTextField$secureTextFieldModifier$1() {
        super(1);
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((SemanticsPropertyReceiver) obj);
        return t0.f22605a;
    }

    public final void invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
        SemanticsPropertiesKt.password(semanticsPropertyReceiver);
        SemanticsPropertiesKt.copyText$default(semanticsPropertyReceiver, null, AnonymousClass1.INSTANCE, 1, null);
        SemanticsPropertiesKt.cutText$default(semanticsPropertyReceiver, null, AnonymousClass2.INSTANCE, 1, null);
    }
}
