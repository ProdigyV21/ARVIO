package io.ktor.http.content;

import androidx.media3.extractor.ts.TsExtractor;
import d7.d;
import f7.e;
import f7.j;
import io.ktor.utils.io.jvm.javaio.PollersKt;
import java.lang.reflect.Method;
import k2.c;
import ka.k0;
import ka.m0;
import ka.x0;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.a;
import r7.l;
import r7.p;
import x6.i0;
import x6.s;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a1\u0010\u0005\u001a\u00020\u00022\u001c\u0010\u0004\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\u0001\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0000H\u0080@ø\u0001\u0000¢\u0006\u0004\b\u0005\u0010\u0006\u001a\u000f\u0010\b\u001a\u00020\u0007H\u0002¢\u0006\u0004\b\b\u0010\t\u001a1\u0010\n\u001a\u00020\u00022\u001c\u0010\u0004\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\u0001\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0000H\u0082@ø\u0001\u0000¢\u0006\u0004\b\n\u0010\u0006\"\u001d\u0010\u000e\u001a\u0004\u0018\u00010\u000b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\u000e\u0010\u000f\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0010"}, d2 = {"Lkotlin/Function1;", "Ld7/d;", "Lx6/t0;", "", "block", "withBlocking", "(Lr7/l;Ld7/d;)Ljava/lang/Object;", "", "safeToRunInPlace", "()Z", "withBlockingAndRedispatch", "Ljava/lang/reflect/Method;", "isParkingAllowedFunction$delegate", "Lx6/s;", "isParkingAllowedFunction", "()Ljava/lang/reflect/Method;", "ktor-http"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class BlockingBridgeKt {
    private static final s isParkingAllowedFunction$delegate = new i0(AnonymousClass2.INSTANCE);

    /* JADX INFO: renamed from: io.ktor.http.content.BlockingBridgeKt$isParkingAllowedFunction$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Ljava/lang/reflect/Method;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass2 extends r implements a<Method> {
        public static final AnonymousClass2 INSTANCE = new AnonymousClass2();

        public AnonymousClass2() {
            super(0);
        }

        @Override // r7.a
        public final Method invoke() {
            try {
                int i10 = PollersKt.f16280a;
                return PollersKt.class.getMethod("isParkingAllowed", null);
            } catch (Throwable unused) {
                return null;
            }
        }
    }

    /* JADX INFO: renamed from: io.ktor.http.content.BlockingBridgeKt$withBlockingAndRedispatch$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
    @e(c = "io.ktor.http.content.BlockingBridgeKt$withBlockingAndRedispatch$2", f = "BlockingBridge.kt", l = {TsExtractor.TS_STREAM_TYPE_MHAS}, m = "invokeSuspend")
    public static final class C18322 extends j implements p<k0, d<? super t0>, Object> {
        final /* synthetic */ l<d<? super t0>, Object> $block;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C18322(l<? super d<? super t0>, ? extends Object> lVar, d<? super C18322> dVar) {
            super(2, dVar);
            this.$block = lVar;
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            return new C18322(this.$block, dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                c.G(obj);
                l<d<? super t0>, Object> lVar = this.$block;
                this.label = 1;
                Object objInvoke = lVar.invoke(this);
                e7.a aVar = e7.a.f15033i;
                if (objInvoke == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                c.G(obj);
            }
            return t0.f22605a;
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d<? super t0> dVar) {
            return ((C18322) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    private static final Method isParkingAllowedFunction() {
        return (Method) isParkingAllowedFunction$delegate.getValue();
    }

    private static final boolean safeToRunInPlace() {
        boolean zA;
        Method methodIsParkingAllowedFunction = isParkingAllowedFunction();
        if (methodIsParkingAllowedFunction == null) {
            return false;
        }
        try {
            zA = kotlin.jvm.internal.p.a(methodIsParkingAllowedFunction.invoke(null, null), Boolean.TRUE);
        } catch (Throwable unused) {
            zA = false;
        }
        return zA;
    }

    public static final Object withBlocking(l<? super d<? super t0>, ? extends Object> lVar, d<? super t0> dVar) throws Throwable {
        boolean zSafeToRunInPlace = safeToRunInPlace();
        t0 t0Var = t0.f22605a;
        e7.a aVar = e7.a.f15033i;
        if (zSafeToRunInPlace) {
            Object objInvoke = lVar.invoke(dVar);
            return objInvoke == aVar ? objInvoke : t0Var;
        }
        Object objWithBlockingAndRedispatch = withBlockingAndRedispatch(lVar, dVar);
        return objWithBlockingAndRedispatch == aVar ? objWithBlockingAndRedispatch : t0Var;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Object withBlockingAndRedispatch(l<? super d<? super t0>, ? extends Object> lVar, d<? super t0> dVar) throws Throwable {
        Object objY = m0.y(x0.f19655d, new C18322(lVar, null), dVar);
        return objY == e7.a.f15033i ? objY : t0.f22605a;
    }
}
