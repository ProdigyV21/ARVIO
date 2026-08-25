package coil.intercept;

import coil.ComponentRegistry;
import coil.EventListener;
import coil.fetch.SourceResult;
import coil.intercept.EngineInterceptor;
import coil.request.ImageRequest;
import coil.request.Options;
import d7.d;
import e7.a;
import f7.e;
import f7.j;
import k2.c;
import ka.k0;
import kotlin.Metadata;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lcoil/intercept/EngineInterceptor$ExecuteResult;", "<anonymous>", "(Lka/k0;)Lcoil/intercept/EngineInterceptor$ExecuteResult;"}, k = 3, mv = {1, 9, 0})
@e(c = "coil.intercept.EngineInterceptor$execute$executeResult$1", f = "EngineInterceptor.kt", l = {127}, m = "invokeSuspend")
public final class EngineInterceptor$execute$executeResult$1 extends j implements p<k0, d<? super EngineInterceptor.ExecuteResult>, Object> {
    final /* synthetic */ kotlin.jvm.internal.k0 $components;
    final /* synthetic */ EventListener $eventListener;
    final /* synthetic */ kotlin.jvm.internal.k0 $fetchResult;
    final /* synthetic */ Object $mappedData;
    final /* synthetic */ kotlin.jvm.internal.k0 $options;
    final /* synthetic */ ImageRequest $request;
    int label;
    final /* synthetic */ EngineInterceptor this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public EngineInterceptor$execute$executeResult$1(EngineInterceptor engineInterceptor, kotlin.jvm.internal.k0 k0Var, kotlin.jvm.internal.k0 k0Var2, ImageRequest imageRequest, Object obj, kotlin.jvm.internal.k0 k0Var3, EventListener eventListener, d<? super EngineInterceptor$execute$executeResult$1> dVar) {
        super(2, dVar);
        this.this$0 = engineInterceptor;
        this.$fetchResult = k0Var;
        this.$components = k0Var2;
        this.$request = imageRequest;
        this.$mappedData = obj;
        this.$options = k0Var3;
        this.$eventListener = eventListener;
    }

    @Override // f7.a
    public final d<t0> create(Object obj, d<?> dVar) {
        return new EngineInterceptor$execute$executeResult$1(this.this$0, this.$fetchResult, this.$components, this.$request, this.$mappedData, this.$options, this.$eventListener, dVar);
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
        EngineInterceptor engineInterceptor = this.this$0;
        SourceResult sourceResult = (SourceResult) this.$fetchResult.f19746i;
        ComponentRegistry componentRegistry = (ComponentRegistry) this.$components.f19746i;
        ImageRequest imageRequest = this.$request;
        Object obj2 = this.$mappedData;
        Options options = (Options) this.$options.f19746i;
        EventListener eventListener = this.$eventListener;
        this.label = 1;
        Object objDecode = engineInterceptor.decode(sourceResult, componentRegistry, imageRequest, obj2, options, eventListener, this);
        a aVar = a.f15033i;
        return objDecode == aVar ? aVar : objDecode;
    }

    @Override // r7.p
    public final Object invoke(k0 k0Var, d<? super EngineInterceptor.ExecuteResult> dVar) {
        return ((EngineInterceptor$execute$executeResult$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
