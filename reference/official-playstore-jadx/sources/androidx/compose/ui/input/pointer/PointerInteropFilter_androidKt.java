package androidx.compose.ui.input.pointer;

import android.view.MotionEvent;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.platform.InspectableValueKt;
import androidx.compose.ui.viewinterop.AndroidViewHolder;
import d7.d;
import f7.e;
import f7.h;
import f7.j;
import io.ktor.http.ContentDisposition;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import r7.p;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a3\u0010\u0007\u001a\u00020\u0000*\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00012\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003H\u0007¢\u0006\u0004\b\u0007\u0010\b\u001a\u001b\u0010\u0007\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\n\u001a\u00020\tH\u0001¢\u0006\u0004\b\u0007\u0010\u000b\u001a6\u0010\u0011\u001a\u00020\u0000*\u00020\u00002!\u0010\u0010\u001a\u001d\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u000e\u0012\u0004\u0012\u00020\u000f0\u0003H\u0007¢\u0006\u0004\b\u0011\u0010\u0012¨\u0006\u0013"}, d2 = {"Landroidx/compose/ui/Modifier;", "Landroidx/compose/ui/input/pointer/RequestDisallowInterceptTouchEvent;", "requestDisallowInterceptTouchEvent", "Lkotlin/Function1;", "Landroid/view/MotionEvent;", "", "onTouchEvent", "pointerInteropFilter", "(Landroidx/compose/ui/Modifier;Landroidx/compose/ui/input/pointer/RequestDisallowInterceptTouchEvent;Lr7/l;)Landroidx/compose/ui/Modifier;", "Landroidx/compose/ui/viewinterop/AndroidViewHolder;", "view", "(Landroidx/compose/ui/Modifier;Landroidx/compose/ui/viewinterop/AndroidViewHolder;)Landroidx/compose/ui/Modifier;", "Lx6/y;", ContentDisposition.Parameters.Name, "motionEvent", "Lx6/t0;", "watcher", "motionEventSpy", "(Landroidx/compose/ui/Modifier;Lr7/l;)Landroidx/compose/ui/Modifier;", "ui_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class PointerInteropFilter_androidKt {

    /* JADX INFO: renamed from: androidx.compose.ui.input.pointer.PointerInteropFilter_androidKt$motionEventSpy$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/ui/input/pointer/PointerInputScope;", "Lx6/t0;", "<anonymous>", "(Landroidx/compose/ui/input/pointer/PointerInputScope;)V"}, k = 3, mv = {1, 8, 0})
    @e(c = "androidx.compose.ui.input.pointer.PointerInteropFilter_androidKt$motionEventSpy$1", f = "PointerInteropFilter.android.kt", l = {345}, m = "invokeSuspend")
    public static final class AnonymousClass1 extends j implements p<PointerInputScope, d<? super t0>, Object> {
        final /* synthetic */ l<MotionEvent, t0> $watcher;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX INFO: renamed from: androidx.compose.ui.input.pointer.PointerInteropFilter_androidKt$motionEventSpy$1$1, reason: invalid class name and collision with other inner class name */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;", "Lx6/t0;", "<anonymous>", "(Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;)V"}, k = 3, mv = {1, 8, 0})
        @e(c = "androidx.compose.ui.input.pointer.PointerInteropFilter_androidKt$motionEventSpy$1$1", f = "PointerInteropFilter.android.kt", l = {347}, m = "invokeSuspend")
        public static final class C01181 extends h implements p<AwaitPointerEventScope, d<? super t0>, Object> {
            final /* synthetic */ l<MotionEvent, t0> $watcher;
            private /* synthetic */ Object L$0;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            public C01181(l<? super MotionEvent, t0> lVar, d<? super C01181> dVar) {
                super(2, dVar);
                this.$watcher = lVar;
            }

            @Override // f7.a
            public final d<t0> create(Object obj, d<?> dVar) {
                C01181 c01181 = new C01181(this.$watcher, dVar);
                c01181.L$0 = obj;
                return c01181;
            }

            @Override // r7.p
            public final Object invoke(AwaitPointerEventScope awaitPointerEventScope, d<? super t0> dVar) {
                return ((C01181) create(awaitPointerEventScope, dVar)).invokeSuspend(t0.f22605a);
            }

            /* JADX WARN: Removed duplicated region for block: B:11:0x002d A[RETURN] */
            /* JADX WARN: Removed duplicated region for block: B:14:0x0036  */
            /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:10:0x002b -> B:12:0x002e). Please report as a decompilation issue!!! */
            /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
                jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
                	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
                	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
                	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
                */
            @Override // f7.a
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final java.lang.Object invokeSuspend(java.lang.Object r4) {
                /*
                    r3 = this;
                    int r0 = r3.label
                    r1 = 1
                    if (r0 == 0) goto L17
                    if (r0 != r1) goto Lf
                    java.lang.Object r0 = r3.L$0
                    androidx.compose.ui.input.pointer.AwaitPointerEventScope r0 = (androidx.compose.ui.input.pointer.AwaitPointerEventScope) r0
                    k2.c.G(r4)
                    goto L2e
                Lf:
                    java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                    java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                    r4.<init>(r0)
                    throw r4
                L17:
                    k2.c.G(r4)
                    java.lang.Object r4 = r3.L$0
                    androidx.compose.ui.input.pointer.AwaitPointerEventScope r4 = (androidx.compose.ui.input.pointer.AwaitPointerEventScope) r4
                    r0 = r4
                L1f:
                    androidx.compose.ui.input.pointer.PointerEventPass r4 = androidx.compose.ui.input.pointer.PointerEventPass.Initial
                    r3.L$0 = r0
                    r3.label = r1
                    java.lang.Object r4 = r0.awaitPointerEvent(r4, r3)
                    e7.a r2 = e7.a.f15033i
                    if (r4 != r2) goto L2e
                    return r2
                L2e:
                    androidx.compose.ui.input.pointer.PointerEvent r4 = (androidx.compose.ui.input.pointer.PointerEvent) r4
                    android.view.MotionEvent r4 = r4.getMotionEvent$ui_release()
                    if (r4 == 0) goto L1f
                    r7.l<android.view.MotionEvent, x6.t0> r2 = r3.$watcher
                    r2.invoke(r4)
                    goto L1f
                */
                throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.input.pointer.PointerInteropFilter_androidKt.AnonymousClass1.C01181.invokeSuspend(java.lang.Object):java.lang.Object");
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass1(l<? super MotionEvent, t0> lVar, d<? super AnonymousClass1> dVar) {
            super(2, dVar);
            this.$watcher = lVar;
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$watcher, dVar);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override // r7.p
        public final Object invoke(PointerInputScope pointerInputScope, d<? super t0> dVar) {
            return ((AnonymousClass1) create(pointerInputScope, dVar)).invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                PointerInputScope pointerInputScope = (PointerInputScope) this.L$0;
                pointerInputScope.setInterceptOutOfBoundsChildEvents(true);
                C01181 c01181 = new C01181(this.$watcher, null);
                this.label = 1;
                Object objAwaitPointerEventScope = pointerInputScope.awaitPointerEventScope(c01181, this);
                e7.a aVar = e7.a.f15033i;
                if (objAwaitPointerEventScope == aVar) {
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
    }

    /* JADX INFO: renamed from: androidx.compose.ui.input.pointer.PointerInteropFilter_androidKt$pointerInteropFilter$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0001H\u000b¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"<anonymous>", "Landroidx/compose/ui/Modifier;", "invoke", "(Landroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;I)Landroidx/compose/ui/Modifier;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass2 extends r implements q<Modifier, Composer, Integer, Modifier> {
        final /* synthetic */ l<MotionEvent, Boolean> $onTouchEvent;
        final /* synthetic */ RequestDisallowInterceptTouchEvent $requestDisallowInterceptTouchEvent;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass2(l<? super MotionEvent, Boolean> lVar, RequestDisallowInterceptTouchEvent requestDisallowInterceptTouchEvent) {
            super(3);
            this.$onTouchEvent = lVar;
            this.$requestDisallowInterceptTouchEvent = requestDisallowInterceptTouchEvent;
        }

        @Override // r7.q
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
            return invoke((Modifier) obj, (Composer) obj2, ((Number) obj3).intValue());
        }

        public final Modifier invoke(Modifier modifier, Composer composer, int i10) {
            composer.startReplaceableGroup(374375707);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(374375707, i10, -1, "androidx.compose.ui.input.pointer.pointerInteropFilter.<anonymous> (PointerInteropFilter.android.kt:78)");
            }
            composer.startReplaceableGroup(-492369756);
            Object objRememberedValue = composer.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new PointerInteropFilter();
                composer.updateRememberedValue(objRememberedValue);
            }
            composer.endReplaceableGroup();
            PointerInteropFilter pointerInteropFilter = (PointerInteropFilter) objRememberedValue;
            pointerInteropFilter.setOnTouchEvent(this.$onTouchEvent);
            pointerInteropFilter.setRequestDisallowInterceptTouchEvent(this.$requestDisallowInterceptTouchEvent);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            composer.endReplaceableGroup();
            return pointerInteropFilter;
        }
    }

    /* JADX INFO: renamed from: androidx.compose.ui.input.pointer.PointerInteropFilter_androidKt$pointerInteropFilter$3, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "motionEvent", "Landroid/view/MotionEvent;", "invoke", "(Landroid/view/MotionEvent;)Ljava/lang/Boolean;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass3 extends r implements l<MotionEvent, Boolean> {
        final /* synthetic */ AndroidViewHolder $view;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass3(AndroidViewHolder androidViewHolder) {
            super(1);
            this.$view = androidViewHolder;
        }

        @Override // r7.l
        public final Boolean invoke(MotionEvent motionEvent) {
            boolean zDispatchTouchEvent;
            switch (motionEvent.getActionMasked()) {
                case 0:
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                    zDispatchTouchEvent = this.$view.dispatchTouchEvent(motionEvent);
                    break;
                default:
                    zDispatchTouchEvent = this.$view.dispatchGenericMotionEvent(motionEvent);
                    break;
            }
            return Boolean.valueOf(zDispatchTouchEvent);
        }
    }

    public static final Modifier motionEventSpy(Modifier modifier, l<? super MotionEvent, t0> lVar) {
        return SuspendingPointerInputFilterKt.pointerInput(modifier, lVar, new AnonymousClass1(lVar, null));
    }

    public static final Modifier pointerInteropFilter(Modifier modifier, AndroidViewHolder androidViewHolder) {
        PointerInteropFilter pointerInteropFilter = new PointerInteropFilter();
        pointerInteropFilter.setOnTouchEvent(new AnonymousClass3(androidViewHolder));
        RequestDisallowInterceptTouchEvent requestDisallowInterceptTouchEvent = new RequestDisallowInterceptTouchEvent();
        pointerInteropFilter.setRequestDisallowInterceptTouchEvent(requestDisallowInterceptTouchEvent);
        androidViewHolder.setOnRequestDisallowInterceptTouchEvent$ui_release(requestDisallowInterceptTouchEvent);
        return modifier.then(pointerInteropFilter);
    }

    public static /* synthetic */ Modifier pointerInteropFilter$default(Modifier modifier, RequestDisallowInterceptTouchEvent requestDisallowInterceptTouchEvent, l lVar, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            requestDisallowInterceptTouchEvent = null;
        }
        return pointerInteropFilter(modifier, requestDisallowInterceptTouchEvent, lVar);
    }

    public static final Modifier pointerInteropFilter(Modifier modifier, RequestDisallowInterceptTouchEvent requestDisallowInterceptTouchEvent, l<? super MotionEvent, Boolean> lVar) {
        return ComposedModifierKt.composed(modifier, InspectableValueKt.isDebugInspectorInfoEnabled() ? new PointerInteropFilter_androidKt$pointerInteropFilter$$inlined$debugInspectorInfo$1(requestDisallowInterceptTouchEvent, lVar) : InspectableValueKt.getNoInspectorInfo(), new AnonymousClass2(lVar, requestDisallowInterceptTouchEvent));
    }
}
