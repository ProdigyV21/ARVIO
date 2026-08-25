package androidx.navigation.compose;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.runtime.saveable.SaveableStateHolder;
import androidx.compose.runtime.snapshots.SnapshotStateList;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\u000b¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "invoke", "(Landroidx/compose/runtime/Composer;I)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class DialogHostKt$DialogHost$1$2 extends kotlin.jvm.internal.r implements r7.p<Composer, Integer, t0> {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ androidx.navigation.i f4540i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ SaveableStateHolder f4541l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ SnapshotStateList f4542m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ p f4543n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ o f4544o;

    /* JADX INFO: renamed from: androidx.navigation.compose.DialogHostKt$DialogHost$1$2$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\u000b¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "invoke", "(Landroidx/compose/runtime/Composer;I)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass2 extends kotlin.jvm.internal.r implements r7.p<Composer, Integer, t0> {

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public final /* synthetic */ o f4545i;

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        public final /* synthetic */ androidx.navigation.i f4546l;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(o oVar, androidx.navigation.i iVar) {
            super(2);
            this.f4545i = oVar;
            this.f4546l = iVar;
        }

        @Override // r7.p
        public final /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return t0.f22605a;
        }

        public final void invoke(Composer composer, int i10) {
            if ((i10 & 11) == 2 && composer.getSkipping()) {
                composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-497631156, i10, -1, "androidx.navigation.compose.DialogHost.<anonymous>.<anonymous>.<anonymous> (DialogHost.kt:66)");
            }
            this.f4545i.f4594t.invoke(this.f4546l, composer, 8);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DialogHostKt$DialogHost$1$2(androidx.navigation.i iVar, SaveableStateHolder saveableStateHolder, SnapshotStateList snapshotStateList, p pVar, o oVar) {
        super(2);
        this.f4540i = iVar;
        this.f4541l = saveableStateHolder;
        this.f4542m = snapshotStateList;
        this.f4543n = pVar;
        this.f4544o = oVar;
    }

    @Override // r7.p
    public final /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((Composer) obj, ((Number) obj2).intValue());
        return t0.f22605a;
    }

    public final void invoke(Composer composer, int i10) {
        if ((i10 & 11) == 2 && composer.getSkipping()) {
            composer.skipToGroupEnd();
            return;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1129586364, i10, -1, "androidx.navigation.compose.DialogHost.<anonymous>.<anonymous> (DialogHost.kt:55)");
        }
        p pVar = this.f4543n;
        SnapshotStateList snapshotStateList = this.f4542m;
        androidx.navigation.i iVar = this.f4540i;
        EffectsKt.DisposableEffect(iVar, new h(snapshotStateList, iVar, pVar, 0), composer, 8);
        NavBackStackEntryProviderKt.LocalOwnersProvider(iVar, this.f4541l, ComposableLambdaKt.composableLambda(composer, -497631156, true, new AnonymousClass2(this.f4544o, iVar)), composer, 456);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
    }
}
