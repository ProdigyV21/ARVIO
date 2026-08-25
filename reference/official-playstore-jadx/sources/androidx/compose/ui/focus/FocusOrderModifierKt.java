package androidx.compose.ui.focus;

import androidx.compose.ui.Modifier;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a,\u0010\u0006\u001a\u00020\u0000*\u00020\u00002\u0017\u0010\u0005\u001a\u0013\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001¢\u0006\u0002\b\u0004H\u0007¢\u0006\u0004\b\u0006\u0010\u0007\u001a\u001b\u0010\u0006\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\t\u001a\u00020\bH\u0007¢\u0006\u0004\b\u0006\u0010\n\u001a4\u0010\u0006\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\t\u001a\u00020\b2\u0017\u0010\u0005\u001a\u0013\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001¢\u0006\u0002\b\u0004H\u0007¢\u0006\u0004\b\u0006\u0010\u000b¨\u0006\f"}, d2 = {"Landroidx/compose/ui/Modifier;", "Lkotlin/Function1;", "Landroidx/compose/ui/focus/FocusOrder;", "Lx6/t0;", "Lx6/n;", "focusOrderReceiver", "focusOrder", "(Landroidx/compose/ui/Modifier;Lr7/l;)Landroidx/compose/ui/Modifier;", "Landroidx/compose/ui/focus/FocusRequester;", "focusRequester", "(Landroidx/compose/ui/Modifier;Landroidx/compose/ui/focus/FocusRequester;)Landroidx/compose/ui/Modifier;", "(Landroidx/compose/ui/Modifier;Landroidx/compose/ui/focus/FocusRequester;Lr7/l;)Landroidx/compose/ui/Modifier;", "ui_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class FocusOrderModifierKt {

    /* JADX INFO: renamed from: androidx.compose.ui.focus.FocusOrderModifierKt$focusOrder$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/ui/focus/FocusProperties;", "Lx6/t0;", "invoke", "(Landroidx/compose/ui/focus/FocusProperties;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass1 extends r implements l<FocusProperties, t0> {
        final /* synthetic */ FocusOrderToProperties $scope;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(FocusOrderToProperties focusOrderToProperties) {
            super(1);
            this.$scope = focusOrderToProperties;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((FocusProperties) obj);
            return t0.f22605a;
        }

        public final void invoke(FocusProperties focusProperties) {
            this.$scope.apply(focusProperties);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.ui.focus.FocusOrderModifierKt$focusOrder$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/ui/focus/FocusProperties;", "Lx6/t0;", "invoke", "(Landroidx/compose/ui/focus/FocusProperties;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass2 extends r implements l<FocusProperties, t0> {
        final /* synthetic */ FocusOrderToProperties $scope;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(FocusOrderToProperties focusOrderToProperties) {
            super(1);
            this.$scope = focusOrderToProperties;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((FocusProperties) obj);
            return t0.f22605a;
        }

        public final void invoke(FocusProperties focusProperties) {
            this.$scope.apply(focusProperties);
        }
    }

    @x6.e
    public static final Modifier focusOrder(Modifier modifier, l<? super FocusOrder, t0> lVar) {
        return FocusPropertiesKt.focusProperties(modifier, new AnonymousClass1(new FocusOrderToProperties(lVar)));
    }

    @x6.e
    public static final Modifier focusOrder(Modifier modifier, FocusRequester focusRequester) {
        return FocusRequesterModifierKt.focusRequester(modifier, focusRequester);
    }

    @x6.e
    public static final Modifier focusOrder(Modifier modifier, FocusRequester focusRequester, l<? super FocusOrder, t0> lVar) {
        return FocusPropertiesKt.focusProperties(FocusRequesterModifierKt.focusRequester(modifier, focusRequester), new AnonymousClass2(new FocusOrderToProperties(lVar)));
    }
}
