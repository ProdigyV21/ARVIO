package io.ktor.server.plugins.callloging;

import d7.d;
import e7.a;
import f7.e;
import f7.j;
import io.ktor.server.application.ApplicationCall;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import k2.c;
import ka.k0;
import ka.m0;
import kotlin.Metadata;
import org.slf4j.spi.MDCAdapter;
import r7.l;
import r7.p;
import ta.b;
import x6.c0;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0005\u001aI\u0010\n\u001a\u00020\u00072\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00002\u0006\u0010\u0004\u001a\u00020\u00032\u001e\b\u0004\u0010\t\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0005H\u0080Hø\u0001\u0000¢\u0006\u0004\b\n\u0010\u000b\u001a-\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\r0\f*\b\u0012\u0004\u0012\u00020\u00010\u00002\u0006\u0010\u0004\u001a\u00020\u0003H\u0000¢\u0006\u0004\b\u000e\u0010\u000f\u001a\u0019\u0010\u0010\u001a\u00020\u0007*\b\u0012\u0004\u0012\u00020\u00010\u0000H\u0000¢\u0006\u0004\b\u0010\u0010\u0011\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0012"}, d2 = {"", "Lio/ktor/server/plugins/callloging/MDCEntry;", "mdcEntries", "Lio/ktor/server/application/ApplicationCall;", "call", "Lkotlin/Function1;", "Ld7/d;", "Lx6/t0;", "", "block", "withMDC", "(Ljava/util/List;Lio/ktor/server/application/ApplicationCall;Lr7/l;Ld7/d;)Ljava/lang/Object;", "", "", "setup", "(Ljava/util/List;Lio/ktor/server/application/ApplicationCall;)Ljava/util/Map;", "cleanup", "(Ljava/util/List;)V", "ktor-server-call-logging"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class MDCEntryUtilsKt {

    /* JADX INFO: renamed from: io.ktor.server.plugins.callloging.MDCEntryUtilsKt$withMDC$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
    @e(c = "io.ktor.server.plugins.callloging.MDCEntryUtilsKt$withMDC$2", f = "MDCEntryUtils.kt", l = {21}, m = "invokeSuspend")
    public static final class AnonymousClass2 extends j implements p<k0, d<? super t0>, Object> {
        final /* synthetic */ l<d<? super t0>, Object> $block;
        final /* synthetic */ List<MDCEntry> $mdcEntries;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass2(l<? super d<? super t0>, ? extends Object> lVar, List<MDCEntry> list, d<? super AnonymousClass2> dVar) {
            super(2, dVar);
            this.$block = lVar;
            this.$mdcEntries = list;
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            return new AnonymousClass2(this.$block, this.$mdcEntries, dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            try {
                if (i10 == 0) {
                    c.G(obj);
                    l<d<? super t0>, Object> lVar = this.$block;
                    this.label = 1;
                    Object objInvoke = lVar.invoke(this);
                    a aVar = a.f15033i;
                    if (objInvoke == aVar) {
                        return aVar;
                    }
                } else {
                    if (i10 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    c.G(obj);
                }
                MDCEntryUtilsKt.cleanup(this.$mdcEntries);
                return t0.f22605a;
            } catch (Throwable th) {
                MDCEntryUtilsKt.cleanup(this.$mdcEntries);
                throw th;
            }
        }

        public final Object invokeSuspend$$forInline(Object obj) {
            try {
                this.$block.invoke(this);
                MDCEntryUtilsKt.cleanup(this.$mdcEntries);
                return t0.f22605a;
            } catch (Throwable th) {
                MDCEntryUtilsKt.cleanup(this.$mdcEntries);
                throw th;
            }
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d<? super t0> dVar) {
            return ((AnonymousClass2) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    public static final void cleanup(List<MDCEntry> list) {
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            if (((MDCEntry) it.next()).getName() == null) {
                MDCAdapter mDCAdapter = vc.d.f22336a;
                throw new IllegalArgumentException("key parameter cannot be null");
            }
            if (vc.d.f22336a == null) {
                throw new IllegalStateException("MDCAdapter cannot be null. See also http://www.slf4j.org/codes.html#null_MDCA");
            }
        }
    }

    public static final Map<String, String> setup(List<MDCEntry> list, ApplicationCall applicationCall) {
        Object c0Var;
        HashMap map = new HashMap();
        for (MDCEntry mDCEntry : list) {
            try {
                c0Var = (String) mDCEntry.getProvider().invoke(applicationCall);
            } catch (Throwable th) {
                c0Var = new c0(th);
            }
            if (c0Var instanceof c0) {
                c0Var = null;
            }
            String str = (String) c0Var;
            if (str != null) {
                map.put(mDCEntry.getName(), str);
            }
        }
        return map;
    }

    public static final Object withMDC(List<MDCEntry> list, ApplicationCall applicationCall, l<? super d<? super t0>, ? extends Object> lVar, d<? super t0> dVar) throws Throwable {
        Object objY = m0.y(new b(setup(list, applicationCall)), new AnonymousClass2(lVar, list, null), dVar);
        return objY == a.f15033i ? objY : t0.f22605a;
    }

    private static final Object withMDC$$forInline(List<MDCEntry> list, ApplicationCall applicationCall, l<? super d<? super t0>, ? extends Object> lVar, d<? super t0> dVar) throws Throwable {
        m0.y(new b(setup(list, applicationCall)), new AnonymousClass2(lVar, list, null), dVar);
        return t0.f22605a;
    }
}
