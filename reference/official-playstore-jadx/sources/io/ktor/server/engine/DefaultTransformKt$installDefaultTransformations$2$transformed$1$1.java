package io.ktor.server.engine;

import d7.d;
import f7.e;
import f7.j;
import io.ktor.http.ParametersBuilder;
import io.ktor.http.content.PartData;
import k2.c;
import kotlin.Metadata;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lio/ktor/http/content/PartData;", "part", "Lx6/t0;", "<anonymous>", "(Lio/ktor/http/content/PartData;)V"}, k = 3, mv = {1, 8, 0})
@e(c = "io.ktor.server.engine.DefaultTransformKt$installDefaultTransformations$2$transformed$1$1", f = "DefaultTransform.kt", l = {}, m = "invokeSuspend")
public final class DefaultTransformKt$installDefaultTransformations$2$transformed$1$1 extends j implements p<PartData, d<? super t0>, Object> {
    final /* synthetic */ ParametersBuilder $this_build;
    /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DefaultTransformKt$installDefaultTransformations$2$transformed$1$1(ParametersBuilder parametersBuilder, d<? super DefaultTransformKt$installDefaultTransformations$2$transformed$1$1> dVar) {
        super(2, dVar);
        this.$this_build = parametersBuilder;
    }

    @Override // f7.a
    public final d<t0> create(Object obj, d<?> dVar) {
        DefaultTransformKt$installDefaultTransformations$2$transformed$1$1 defaultTransformKt$installDefaultTransformations$2$transformed$1$1 = new DefaultTransformKt$installDefaultTransformations$2$transformed$1$1(this.$this_build, dVar);
        defaultTransformKt$installDefaultTransformations$2$transformed$1$1.L$0 = obj;
        return defaultTransformKt$installDefaultTransformations$2$transformed$1$1;
    }

    @Override // r7.p
    public final Object invoke(PartData partData, d<? super t0> dVar) {
        return ((DefaultTransformKt$installDefaultTransformations$2$transformed$1$1) create(partData, dVar)).invokeSuspend(t0.f22605a);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        String name;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        c.G(obj);
        PartData partData = (PartData) this.L$0;
        if ((partData instanceof PartData.FormItem) && (name = partData.getName()) != null) {
            this.$this_build.append(name, ((PartData.FormItem) partData).getValue());
        }
        partData.getDispose().invoke();
        return t0.f22605a;
    }
}
