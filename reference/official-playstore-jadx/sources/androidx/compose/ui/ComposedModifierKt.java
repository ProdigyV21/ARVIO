package androidx.compose.ui;

import androidx.compose.runtime.Composer;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.platform.InspectableValueKt;
import androidx.compose.ui.platform.InspectorInfo;
import com.google.firebase.crashlytics.internal.metadata.UserMetadata;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\u001aJ\u0010\b\u001a\u00020\u0000*\u00020\u00002\u0019\b\u0002\u0010\u0005\u001a\u0013\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001¢\u0006\u0002\b\u00042\u001c\u0010\u0007\u001a\u0018\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00000\u0001¢\u0006\u0002\b\u0006¢\u0006\u0002\b\u0004¢\u0006\u0004\b\b\u0010\t\u001a^\u0010\b\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u000b\u001a\u00020\n2\b\u0010\r\u001a\u0004\u0018\u00010\f2\u0019\b\u0002\u0010\u0005\u001a\u0013\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001¢\u0006\u0002\b\u00042\u001c\u0010\u0007\u001a\u0018\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00000\u0001¢\u0006\u0002\b\u0006¢\u0006\u0002\b\u0004H\u0007¢\u0006\u0004\b\b\u0010\u000e\u001ah\u0010\b\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u000b\u001a\u00020\n2\b\u0010\r\u001a\u0004\u0018\u00010\f2\b\u0010\u000f\u001a\u0004\u0018\u00010\f2\u0019\b\u0002\u0010\u0005\u001a\u0013\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001¢\u0006\u0002\b\u00042\u001c\u0010\u0007\u001a\u0018\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00000\u0001¢\u0006\u0002\b\u0006¢\u0006\u0002\b\u0004H\u0007¢\u0006\u0004\b\b\u0010\u0010\u001ar\u0010\b\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u000b\u001a\u00020\n2\b\u0010\r\u001a\u0004\u0018\u00010\f2\b\u0010\u000f\u001a\u0004\u0018\u00010\f2\b\u0010\u0011\u001a\u0004\u0018\u00010\f2\u0019\b\u0002\u0010\u0005\u001a\u0013\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001¢\u0006\u0002\b\u00042\u001c\u0010\u0007\u001a\u0018\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00000\u0001¢\u0006\u0002\b\u0006¢\u0006\u0002\b\u0004H\u0007¢\u0006\u0004\b\b\u0010\u0012\u001al\u0010\b\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u000b\u001a\u00020\n2\u0016\u0010\u0014\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\f0\u0013\"\u0004\u0018\u00010\f2\u0019\b\u0002\u0010\u0005\u001a\u0013\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001¢\u0006\u0002\b\u00042\u001c\u0010\u0007\u001a\u0018\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00000\u0001¢\u0006\u0002\b\u0006¢\u0006\u0002\b\u0004H\u0007¢\u0006\u0004\b\b\u0010\u0015\u001a\u001b\u0010\u001a\u001a\u00020\u0000*\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0000H\u0007¢\u0006\u0004\b\u0018\u0010\u0019\u001a\u001b\u0010\u001b\u001a\u00020\u0000*\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0000H\u0007¢\u0006\u0004\b\u001a\u0010\u0019\u001a\u001b\u0010\u001c\u001a\u00020\u0000*\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0000H\u0000¢\u0006\u0004\b\u001c\u0010\u0019¨\u0006\u001d"}, d2 = {"Landroidx/compose/ui/Modifier;", "Lkotlin/Function1;", "Landroidx/compose/ui/platform/InspectorInfo;", "Lx6/t0;", "Lx6/n;", "inspectorInfo", "Landroidx/compose/runtime/Composable;", "factory", "composed", "(Landroidx/compose/ui/Modifier;Lr7/l;Lr7/q;)Landroidx/compose/ui/Modifier;", "", "fullyQualifiedName", "", "key1", "(Landroidx/compose/ui/Modifier;Ljava/lang/String;Ljava/lang/Object;Lr7/l;Lr7/q;)Landroidx/compose/ui/Modifier;", "key2", "(Landroidx/compose/ui/Modifier;Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;Lr7/l;Lr7/q;)Landroidx/compose/ui/Modifier;", "key3", "(Landroidx/compose/ui/Modifier;Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Lr7/l;Lr7/q;)Landroidx/compose/ui/Modifier;", "", UserMetadata.KEYDATA_FILENAME, "(Landroidx/compose/ui/Modifier;Ljava/lang/String;[Ljava/lang/Object;Lr7/l;Lr7/q;)Landroidx/compose/ui/Modifier;", "Landroidx/compose/runtime/Composer;", "modifier", "materializeModifier", "(Landroidx/compose/runtime/Composer;Landroidx/compose/ui/Modifier;)Landroidx/compose/ui/Modifier;", "materialize", "materializeWithCompositionLocalInjection", "materializeWithCompositionLocalInjectionInternal", "ui_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ComposedModifierKt {

    /* JADX INFO: renamed from: androidx.compose.ui.ComposedModifierKt$materialize$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "it", "Landroidx/compose/ui/Modifier$Element;", "invoke", "(Landroidx/compose/ui/Modifier$Element;)Ljava/lang/Boolean;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass1 extends r implements l<Modifier.Element, Boolean> {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        public AnonymousClass1() {
            super(1);
        }

        @Override // r7.l
        public final Boolean invoke(Modifier.Element element) {
            return Boolean.valueOf(!(element instanceof ComposedModifier));
        }
    }

    public static final Modifier composed(Modifier modifier, l<? super InspectorInfo, t0> lVar, q<? super Modifier, ? super Composer, ? super Integer, ? extends Modifier> qVar) {
        return modifier.then(new ComposedModifier(lVar, qVar));
    }

    public static /* synthetic */ Modifier composed$default(Modifier modifier, l lVar, q qVar, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            lVar = InspectableValueKt.getNoInspectorInfo();
        }
        return composed(modifier, lVar, qVar);
    }

    public static final Modifier materializeModifier(Composer composer, Modifier modifier) {
        if (modifier.all(AnonymousClass1.INSTANCE)) {
            return modifier;
        }
        composer.startReplaceableGroup(1219399079);
        Modifier modifier2 = (Modifier) modifier.foldIn(Modifier.INSTANCE, new ComposedModifierKt$materialize$result$1(composer));
        composer.endReplaceableGroup();
        return modifier2;
    }

    public static final Modifier materializeWithCompositionLocalInjectionInternal(Composer composer, Modifier modifier) {
        return modifier == Modifier.INSTANCE ? modifier : materializeModifier(composer, a.a(new CompositionLocalMapInjectionElement(composer.getCurrentCompositionLocalMap()), modifier));
    }

    public static final Modifier composed(Modifier modifier, String str, Object obj, l<? super InspectorInfo, t0> lVar, q<? super Modifier, ? super Composer, ? super Integer, ? extends Modifier> qVar) {
        return modifier.then(new KeyedComposedModifier1(str, obj, lVar, qVar));
    }

    public static /* synthetic */ Modifier composed$default(Modifier modifier, String str, Object obj, l lVar, q qVar, int i10, Object obj2) {
        if ((i10 & 4) != 0) {
            lVar = InspectableValueKt.getNoInspectorInfo();
        }
        return composed(modifier, str, obj, (l<? super InspectorInfo, t0>) lVar, (q<? super Modifier, ? super Composer, ? super Integer, ? extends Modifier>) qVar);
    }

    public static final Modifier composed(Modifier modifier, String str, Object obj, Object obj2, l<? super InspectorInfo, t0> lVar, q<? super Modifier, ? super Composer, ? super Integer, ? extends Modifier> qVar) {
        return modifier.then(new KeyedComposedModifier2(str, obj, obj2, lVar, qVar));
    }

    public static /* synthetic */ Modifier composed$default(Modifier modifier, String str, Object obj, Object obj2, l lVar, q qVar, int i10, Object obj3) {
        if ((i10 & 8) != 0) {
            lVar = InspectableValueKt.getNoInspectorInfo();
        }
        return composed(modifier, str, obj, obj2, lVar, qVar);
    }

    public static final Modifier composed(Modifier modifier, String str, Object obj, Object obj2, Object obj3, l<? super InspectorInfo, t0> lVar, q<? super Modifier, ? super Composer, ? super Integer, ? extends Modifier> qVar) {
        return modifier.then(new KeyedComposedModifier3(str, obj, obj2, obj3, lVar, qVar));
    }

    public static /* synthetic */ Modifier composed$default(Modifier modifier, String str, Object obj, Object obj2, Object obj3, l lVar, q qVar, int i10, Object obj4) {
        if ((i10 & 16) != 0) {
            lVar = InspectableValueKt.getNoInspectorInfo();
        }
        return composed(modifier, str, obj, obj2, obj3, lVar, qVar);
    }

    public static final Modifier composed(Modifier modifier, String str, Object[] objArr, l<? super InspectorInfo, t0> lVar, q<? super Modifier, ? super Composer, ? super Integer, ? extends Modifier> qVar) {
        return modifier.then(new KeyedComposedModifierN(str, objArr, lVar, qVar));
    }

    public static /* synthetic */ Modifier composed$default(Modifier modifier, String str, Object[] objArr, l lVar, q qVar, int i10, Object obj) {
        if ((i10 & 4) != 0) {
            lVar = InspectableValueKt.getNoInspectorInfo();
        }
        return composed(modifier, str, objArr, (l<? super InspectorInfo, t0>) lVar, (q<? super Modifier, ? super Composer, ? super Integer, ? extends Modifier>) qVar);
    }
}
