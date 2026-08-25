package androidx.compose.ui.platform;

import android.view.View;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.Composition;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.CompositionServiceKey;
import androidx.compose.runtime.CompositionServices;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.runtime.tooling.CompositionData;
import androidx.compose.runtime.tooling.InspectionTablesKt;
import androidx.compose.ui.R;
import androidx.compose.ui.platform.AndroidComposeView;
import androidx.lifecycle.w;
import androidx.lifecycle.y;
import androidx.media3.extractor.ts.TsExtractor;
import java.util.Set;
import ka.k0;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\b\u0002\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\u0017\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0001¢\u0006\u0004\b\u0007\u0010\bJ\"\u0010\r\u001a\u00020\n2\u0011\u0010\f\u001a\r\u0012\u0004\u0012\u00020\n0\t¢\u0006\u0002\b\u000bH\u0016¢\u0006\u0004\b\r\u0010\u000eJ\u000f\u0010\u000f\u001a\u00020\nH\u0016¢\u0006\u0004\b\u000f\u0010\u0010J\u001f\u0010\u0015\u001a\u00020\n2\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u0013H\u0016¢\u0006\u0004\b\u0015\u0010\u0016J%\u0010\u001a\u001a\u0004\u0018\u00018\u0000\"\u0004\b\u0000\u0010\u00172\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00028\u00000\u0018H\u0016¢\u0006\u0004\b\u001a\u0010\u001bR\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u001c\u001a\u0004\b\u001d\u0010\u001eR\u0017\u0010\u0006\u001a\u00020\u00018\u0006¢\u0006\f\n\u0004\b\u0006\u0010\u001f\u001a\u0004\b \u0010!R\u0016\u0010#\u001a\u00020\"8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b#\u0010$R\u0018\u0010&\u001a\u0004\u0018\u00010%8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b&\u0010'R!\u0010(\u001a\r\u0012\u0004\u0012\u00020\n0\t¢\u0006\u0002\b\u000b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b(\u0010)R\u0014\u0010,\u001a\u00020\"8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b*\u0010+R\u0014\u0010-\u001a\u00020\"8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b-\u0010+¨\u0006."}, d2 = {"Landroidx/compose/ui/platform/WrappedComposition;", "Landroidx/compose/runtime/Composition;", "Landroidx/lifecycle/w;", "Landroidx/compose/runtime/CompositionServices;", "Landroidx/compose/ui/platform/AndroidComposeView;", "owner", "original", "<init>", "(Landroidx/compose/ui/platform/AndroidComposeView;Landroidx/compose/runtime/Composition;)V", "Lkotlin/Function0;", "Lx6/t0;", "Landroidx/compose/runtime/Composable;", "content", "setContent", "(Lr7/p;)V", "dispose", "()V", "Landroidx/lifecycle/y;", "source", "Landroidx/lifecycle/p;", "event", "onStateChanged", "(Landroidx/lifecycle/y;Landroidx/lifecycle/p;)V", "T", "Landroidx/compose/runtime/CompositionServiceKey;", "key", "getCompositionService", "(Landroidx/compose/runtime/CompositionServiceKey;)Ljava/lang/Object;", "Landroidx/compose/ui/platform/AndroidComposeView;", "getOwner", "()Landroidx/compose/ui/platform/AndroidComposeView;", "Landroidx/compose/runtime/Composition;", "getOriginal", "()Landroidx/compose/runtime/Composition;", "", "disposed", "Z", "Landroidx/lifecycle/r;", "addedToLifecycle", "Landroidx/lifecycle/r;", "lastContent", "Lr7/p;", "getHasInvalidations", "()Z", "hasInvalidations", "isDisposed", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class WrappedComposition implements Composition, w, CompositionServices {
    private androidx.lifecycle.r addedToLifecycle;
    private boolean disposed;
    private r7.p<? super Composer, ? super Integer, t0> lastContent = ComposableSingletons$Wrapper_androidKt.INSTANCE.m4947getLambda1$ui_release();
    private final Composition original;
    private final AndroidComposeView owner;

    /* JADX INFO: renamed from: androidx.compose.ui.platform.WrappedComposition$setContent$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Landroidx/compose/ui/platform/AndroidComposeView$ViewTreeOwners;", "it", "Lx6/t0;", "invoke", "(Landroidx/compose/ui/platform/AndroidComposeView$ViewTreeOwners;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass1 extends kotlin.jvm.internal.r implements r7.l<AndroidComposeView.ViewTreeOwners, t0> {
        final /* synthetic */ r7.p<Composer, Integer, t0> $content;

        /* JADX INFO: renamed from: androidx.compose.ui.platform.WrappedComposition$setContent$1$1, reason: invalid class name and collision with other inner class name */
        @Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\u000b¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "invoke", "(Landroidx/compose/runtime/Composer;I)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
        public static final class C01191 extends kotlin.jvm.internal.r implements r7.p<Composer, Integer, t0> {
            final /* synthetic */ r7.p<Composer, Integer, t0> $content;
            final /* synthetic */ WrappedComposition this$0;

            /* JADX INFO: renamed from: androidx.compose.ui.platform.WrappedComposition$setContent$1$1$1, reason: invalid class name and collision with other inner class name */
            @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
            @f7.e(c = "androidx.compose.ui.platform.WrappedComposition$setContent$1$1$1", f = "Wrapper.android.kt", l = {TsExtractor.TS_STREAM_TYPE_DTS_HD}, m = "invokeSuspend")
            public static final class C01201 extends f7.j implements r7.p<k0, d7.d<? super t0>, Object> {
                int label;
                final /* synthetic */ WrappedComposition this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public C01201(WrappedComposition wrappedComposition, d7.d<? super C01201> dVar) {
                    super(2, dVar);
                    this.this$0 = wrappedComposition;
                }

                @Override // f7.a
                public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
                    return new C01201(this.this$0, dVar);
                }

                @Override // f7.a
                public final Object invokeSuspend(Object obj) throws Throwable {
                    int i10 = this.label;
                    if (i10 == 0) {
                        k2.c.G(obj);
                        AndroidComposeView owner = this.this$0.getOwner();
                        this.label = 1;
                        Object objBoundsUpdatesEventLoop = owner.boundsUpdatesEventLoop(this);
                        e7.a aVar = e7.a.f15033i;
                        if (objBoundsUpdatesEventLoop == aVar) {
                            return aVar;
                        }
                    } else {
                        if (i10 != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        k2.c.G(obj);
                    }
                    return t0.f22605a;
                }

                @Override // r7.p
                public final Object invoke(k0 k0Var, d7.d<? super t0> dVar) {
                    return ((C01201) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
                }
            }

            /* JADX INFO: renamed from: androidx.compose.ui.platform.WrappedComposition$setContent$1$1$2, reason: invalid class name */
            @Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\u000b¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "invoke", "(Landroidx/compose/runtime/Composer;I)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
            public static final class AnonymousClass2 extends kotlin.jvm.internal.r implements r7.p<Composer, Integer, t0> {
                final /* synthetic */ r7.p<Composer, Integer, t0> $content;
                final /* synthetic */ WrappedComposition this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                public AnonymousClass2(WrappedComposition wrappedComposition, r7.p<? super Composer, ? super Integer, t0> pVar) {
                    super(2);
                    this.this$0 = wrappedComposition;
                    this.$content = pVar;
                }

                @Override // r7.p
                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                    invoke((Composer) obj, ((Number) obj2).intValue());
                    return t0.f22605a;
                }

                public final void invoke(Composer composer, int i10) {
                    if ((i10 & 11) == 2 && composer.getSkipping()) {
                        composer.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1193460702, i10, -1, "androidx.compose.ui.platform.WrappedComposition.setContent.<anonymous>.<anonymous>.<anonymous> (Wrapper.android.kt:138)");
                    }
                    AndroidCompositionLocals_androidKt.ProvideAndroidCompositionLocals(this.this$0.getOwner(), this.$content, composer, 8);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            public C01191(WrappedComposition wrappedComposition, r7.p<? super Composer, ? super Integer, t0> pVar) {
                super(2);
                this.this$0 = wrappedComposition;
                this.$content = pVar;
            }

            @Override // r7.p
            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                invoke((Composer) obj, ((Number) obj2).intValue());
                return t0.f22605a;
            }

            public final void invoke(Composer composer, int i10) {
                if ((i10 & 11) == 2 && composer.getSkipping()) {
                    composer.skipToGroupEnd();
                    return;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-2000640158, i10, -1, "androidx.compose.ui.platform.WrappedComposition.setContent.<anonymous>.<anonymous> (Wrapper.android.kt:124)");
                }
                Object tag = this.this$0.getOwner().getTag(R.id.inspection_slot_table_set);
                Set<CompositionData> set = (!(tag instanceof Set) || ((tag instanceof s7.a) && !(tag instanceof s7.h))) ? null : (Set) tag;
                if (set == null) {
                    Object parent = this.this$0.getOwner().getParent();
                    View view = parent instanceof View ? (View) parent : null;
                    Object tag2 = view != null ? view.getTag(R.id.inspection_slot_table_set) : null;
                    set = (!(tag2 instanceof Set) || ((tag2 instanceof s7.a) && !(tag2 instanceof s7.h))) ? null : (Set) tag2;
                }
                if (set != null) {
                    set.add(composer.getCompositionData());
                    composer.collectParameterInformation();
                }
                EffectsKt.LaunchedEffect(this.this$0.getOwner(), new C01201(this.this$0, null), composer, 72);
                CompositionLocalKt.CompositionLocalProvider(InspectionTablesKt.getLocalInspectionTables().provides(set), ComposableLambdaKt.composableLambda(composer, -1193460702, true, new AnonymousClass2(this.this$0, this.$content)), composer, 56);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass1(r7.p<? super Composer, ? super Integer, t0> pVar) {
            super(1);
            this.$content = pVar;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((AndroidComposeView.ViewTreeOwners) obj);
            return t0.f22605a;
        }

        public final void invoke(AndroidComposeView.ViewTreeOwners viewTreeOwners) {
            if (WrappedComposition.this.disposed) {
                return;
            }
            androidx.lifecycle.r lifecycle = viewTreeOwners.getLifecycleOwner().getLifecycle();
            WrappedComposition.this.lastContent = this.$content;
            if (WrappedComposition.this.addedToLifecycle == null) {
                WrappedComposition.this.addedToLifecycle = lifecycle;
                lifecycle.addObserver(WrappedComposition.this);
            } else if (lifecycle.getCurrentState().a(androidx.lifecycle.q.f3268m)) {
                WrappedComposition.this.getOriginal().setContent(ComposableLambdaKt.composableLambdaInstance(-2000640158, true, new C01191(WrappedComposition.this, this.$content)));
            }
        }
    }

    public WrappedComposition(AndroidComposeView androidComposeView, Composition composition) {
        this.owner = androidComposeView;
        this.original = composition;
    }

    @Override // androidx.compose.runtime.Composition
    public void dispose() {
        if (!this.disposed) {
            this.disposed = true;
            this.owner.getView().setTag(R.id.wrapped_composition_tag, null);
            androidx.lifecycle.r rVar = this.addedToLifecycle;
            if (rVar != null) {
                rVar.removeObserver(this);
            }
        }
        this.original.dispose();
    }

    @Override // androidx.compose.runtime.CompositionServices
    public <T> T getCompositionService(CompositionServiceKey<T> key) {
        Composition composition = this.original;
        CompositionServices compositionServices = composition instanceof CompositionServices ? (CompositionServices) composition : null;
        if (compositionServices != null) {
            return (T) compositionServices.getCompositionService(key);
        }
        return null;
    }

    @Override // androidx.compose.runtime.Composition
    public boolean getHasInvalidations() {
        return this.original.getHasInvalidations();
    }

    public final Composition getOriginal() {
        return this.original;
    }

    public final AndroidComposeView getOwner() {
        return this.owner;
    }

    @Override // androidx.compose.runtime.Composition
    /* JADX INFO: renamed from: isDisposed */
    public boolean getDisposed() {
        return this.original.getDisposed();
    }

    @Override // androidx.lifecycle.w
    public void onStateChanged(y source, androidx.lifecycle.p event) {
        if (event == androidx.lifecycle.p.ON_DESTROY) {
            dispose();
        } else {
            if (event != androidx.lifecycle.p.ON_CREATE || this.disposed) {
                return;
            }
            setContent(this.lastContent);
        }
    }

    @Override // androidx.compose.runtime.Composition
    public void setContent(r7.p<? super Composer, ? super Integer, t0> content) {
        this.owner.setOnViewTreeOwnersAvailable(new AnonymousClass1(content));
    }
}
