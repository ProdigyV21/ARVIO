package io.ktor.util.debug;

import d7.d;
import d7.j;
import e7.a;
import f7.e;
import f7.j;
import io.ktor.util.debug.plugins.PluginName;
import io.ktor.util.debug.plugins.PluginsTrace;
import k2.c;
import ka.k0;
import ka.m0;
import kotlin.Metadata;
import r7.l;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000.\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a7\u0010\u0005\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u00002\u001c\u0010\u0004\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0001H\u0086@ø\u0001\u0000¢\u0006\u0004\b\u0005\u0010\u0006\u001a?\u0010\t\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u00002\u0006\u0010\b\u001a\u00020\u00072\u001c\u0010\u0004\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0001H\u0086@ø\u0001\u0000¢\u0006\u0004\b\t\u0010\n\u001a?\u0010\u0011\u001a\u00020\u000f\"\b\b\u0000\u0010\f*\u00020\u000b2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00028\u00000\r2\u0012\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u000f0\u0001H\u0086@ø\u0001\u0000¢\u0006\u0004\b\u0011\u0010\u0012\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0013"}, d2 = {"T", "Lkotlin/Function1;", "Ld7/d;", "", "block", "initContextInDebugMode", "(Lr7/l;Ld7/d;)Ljava/lang/Object;", "", "pluginName", "addToContextInDebugMode", "(Ljava/lang/String;Lr7/l;Ld7/d;)Ljava/lang/Object;", "Ld7/j$a;", "Element", "Ld7/j$b;", "key", "Lx6/t0;", "action", "useContextElementInDebugMode", "(Ld7/j$b;Lr7/l;Ld7/d;)Ljava/lang/Object;", "ktor-utils"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ContextUtilsKt {

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: renamed from: io.ktor.util.debug.ContextUtilsKt$addToContextInDebugMode$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0002\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u0000*\u00020\u0001H\u008a@"}, d2 = {"T", "Lka/k0;", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    @e(c = "io.ktor.util.debug.ContextUtilsKt$addToContextInDebugMode$2", f = "ContextUtils.kt", l = {33}, m = "invokeSuspend")
    public static final class AnonymousClass2<T> extends j implements p<k0, d<? super T>, Object> {
        final /* synthetic */ l<d<? super T>, Object> $block;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass2(l<? super d<? super T>, ? extends Object> lVar, d<? super AnonymousClass2> dVar) {
            super(2, dVar);
            this.$block = lVar;
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            return new AnonymousClass2(this.$block, dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 != 0) {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                c.G(obj);
                return obj;
            }
            c.G(obj);
            l<d<? super T>, Object> lVar = this.$block;
            this.label = 1;
            Object objInvoke = lVar.invoke(this);
            a aVar = a.f15033i;
            return objInvoke == aVar ? aVar : objInvoke;
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d<? super T> dVar) {
            return ((AnonymousClass2) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: renamed from: io.ktor.util.debug.ContextUtilsKt$initContextInDebugMode$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0002\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u0000*\u00020\u0001H\u008a@"}, d2 = {"T", "Lka/k0;", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    @e(c = "io.ktor.util.debug.ContextUtilsKt$initContextInDebugMode$2", f = "ContextUtils.kt", l = {20}, m = "invokeSuspend")
    public static final class C19512<T> extends j implements p<k0, d<? super T>, Object> {
        final /* synthetic */ l<d<? super T>, Object> $block;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C19512(l<? super d<? super T>, ? extends Object> lVar, d<? super C19512> dVar) {
            super(2, dVar);
            this.$block = lVar;
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            return new C19512(this.$block, dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 != 0) {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                c.G(obj);
                return obj;
            }
            c.G(obj);
            l<d<? super T>, Object> lVar = this.$block;
            this.label = 1;
            Object objInvoke = lVar.invoke(this);
            a aVar = a.f15033i;
            return objInvoke == aVar ? aVar : objInvoke;
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d<? super T> dVar) {
            return ((C19512) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    public static final <T> Object addToContextInDebugMode(String str, l<? super d<? super T>, ? extends Object> lVar, d<? super T> dVar) {
        return !IntellijIdeaDebugDetector.INSTANCE.isDebuggerConnected() ? lVar.invoke(dVar) : m0.y(dVar.getContext().plus(new PluginName(str)), new AnonymousClass2(lVar, null), dVar);
    }

    public static final <T> Object initContextInDebugMode(l<? super d<? super T>, ? extends Object> lVar, d<? super T> dVar) {
        return !IntellijIdeaDebugDetector.INSTANCE.isDebuggerConnected() ? lVar.invoke(dVar) : m0.y(dVar.getContext().plus(new PluginsTrace(null, 1, null)), new C19512(lVar, null), dVar);
    }

    public static final <Element extends j.a> Object useContextElementInDebugMode(j.b<Element> bVar, l<? super Element, t0> lVar, d<? super t0> dVar) {
        j.a aVar;
        boolean zIsDebuggerConnected = IntellijIdeaDebugDetector.INSTANCE.isDebuggerConnected();
        t0 t0Var = t0.f22605a;
        if (zIsDebuggerConnected && (aVar = dVar.getContext().get(bVar)) != null) {
            lVar.invoke(aVar);
        }
        return t0Var;
    }
}
