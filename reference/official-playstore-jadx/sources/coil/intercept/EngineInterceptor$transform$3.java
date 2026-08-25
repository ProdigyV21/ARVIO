package coil.intercept;

import coil.EventListener;
import coil.intercept.EngineInterceptor;
import coil.request.ImageRequest;
import coil.request.Options;
import coil.transform.Transformation;
import d7.d;
import f7.e;
import f7.j;
import java.util.List;
import ka.k0;
import kotlin.Metadata;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lcoil/intercept/EngineInterceptor$ExecuteResult;", "<anonymous>", "(Lka/k0;)Lcoil/intercept/EngineInterceptor$ExecuteResult;"}, k = 3, mv = {1, 9, 0})
@e(c = "coil.intercept.EngineInterceptor$transform$3", f = "EngineInterceptor.kt", l = {242}, m = "invokeSuspend")
public final class EngineInterceptor$transform$3 extends j implements p<k0, d<? super EngineInterceptor.ExecuteResult>, Object> {
    final /* synthetic */ EventListener $eventListener;
    final /* synthetic */ Options $options;
    final /* synthetic */ ImageRequest $request;
    final /* synthetic */ EngineInterceptor.ExecuteResult $result;
    final /* synthetic */ List<Transformation> $transformations;
    int I$0;
    int I$1;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    int label;
    final /* synthetic */ EngineInterceptor this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public EngineInterceptor$transform$3(EngineInterceptor engineInterceptor, EngineInterceptor.ExecuteResult executeResult, Options options, List<? extends Transformation> list, EventListener eventListener, ImageRequest imageRequest, d<? super EngineInterceptor$transform$3> dVar) {
        super(2, dVar);
        this.this$0 = engineInterceptor;
        this.$result = executeResult;
        this.$options = options;
        this.$transformations = list;
        this.$eventListener = eventListener;
        this.$request = imageRequest;
    }

    @Override // f7.a
    public final d<t0> create(Object obj, d<?> dVar) {
        EngineInterceptor$transform$3 engineInterceptor$transform$3 = new EngineInterceptor$transform$3(this.this$0, this.$result, this.$options, this.$transformations, this.$eventListener, this.$request, dVar);
        engineInterceptor$transform$3.L$0 = obj;
        return engineInterceptor$transform$3;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0052  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x007c  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:11:0x006e -> B:13:0x0071). Please report as a decompilation issue!!! */
    @Override // f7.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r11) {
        /*
            r10 = this;
            int r0 = r10.label
            r1 = 1
            if (r0 == 0) goto L23
            if (r0 != r1) goto L1b
            int r0 = r10.I$1
            int r2 = r10.I$0
            java.lang.Object r3 = r10.L$2
            coil.request.Options r3 = (coil.request.Options) r3
            java.lang.Object r4 = r10.L$1
            java.util.List r4 = (java.util.List) r4
            java.lang.Object r5 = r10.L$0
            ka.k0 r5 = (ka.k0) r5
            k2.c.G(r11)
            goto L71
        L1b:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r0)
            throw r11
        L23:
            k2.c.G(r11)
            java.lang.Object r11 = r10.L$0
            ka.k0 r11 = (ka.k0) r11
            coil.intercept.EngineInterceptor r0 = r10.this$0
            coil.intercept.EngineInterceptor$ExecuteResult r2 = r10.$result
            android.graphics.drawable.Drawable r2 = r2.getDrawable()
            coil.request.Options r3 = r10.$options
            java.util.List<coil.transform.Transformation> r4 = r10.$transformations
            android.graphics.Bitmap r0 = coil.intercept.EngineInterceptor.access$convertDrawableToBitmap(r0, r2, r3, r4)
            coil.EventListener r2 = r10.$eventListener
            coil.request.ImageRequest r3 = r10.$request
            r2.transformStart(r3, r0)
            java.util.List<coil.transform.Transformation> r2 = r10.$transformations
            coil.request.Options r3 = r10.$options
            int r4 = r2.size()
            r5 = 0
            r9 = r5
            r5 = r11
            r11 = r0
            r0 = r4
            r4 = r2
            r2 = r9
        L50:
            if (r2 >= r0) goto L7c
            java.lang.Object r6 = r4.get(r2)
            coil.transform.Transformation r6 = (coil.transform.Transformation) r6
            coil.size.Size r7 = r3.getSize()
            r10.L$0 = r5
            r10.L$1 = r4
            r10.L$2 = r3
            r10.I$0 = r2
            r10.I$1 = r0
            r10.label = r1
            java.lang.Object r11 = r6.transform(r11, r7, r10)
            e7.a r6 = e7.a.f15033i
            if (r11 != r6) goto L71
            return r6
        L71:
            android.graphics.Bitmap r11 = (android.graphics.Bitmap) r11
            d7.j r6 = r5.getCoroutineContext()
            ka.m0.i(r6)
            int r2 = r2 + r1
            goto L50
        L7c:
            coil.EventListener r0 = r10.$eventListener
            coil.request.ImageRequest r1 = r10.$request
            r0.transformEnd(r1, r11)
            coil.intercept.EngineInterceptor$ExecuteResult r2 = r10.$result
            coil.request.ImageRequest r0 = r10.$request
            android.content.Context r0 = r0.getContext()
            android.content.res.Resources r0 = r0.getResources()
            android.graphics.drawable.BitmapDrawable r3 = new android.graphics.drawable.BitmapDrawable
            r3.<init>(r0, r11)
            r7 = 14
            r8 = 0
            r4 = 0
            r5 = 0
            r6 = 0
            coil.intercept.EngineInterceptor$ExecuteResult r11 = coil.intercept.EngineInterceptor.ExecuteResult.copy$default(r2, r3, r4, r5, r6, r7, r8)
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: coil.intercept.EngineInterceptor$transform$3.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    @Override // r7.p
    public final Object invoke(k0 k0Var, d<? super EngineInterceptor.ExecuteResult> dVar) {
        return ((EngineInterceptor$transform$3) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
