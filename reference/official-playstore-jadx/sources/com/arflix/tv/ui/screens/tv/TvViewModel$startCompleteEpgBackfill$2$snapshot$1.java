package com.arflix.tv.ui.screens.tv;

import com.arflix.tv.data.model.IptvSnapshot;
import com.arflix.tv.data.repository.IptvLoadProgress;
import com.arflix.tv.data.repository.IptvRepository;
import java.io.PrintStream;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lcom/arflix/tv/data/model/IptvSnapshot;", "<anonymous>", "(Lka/k0;)Lcom/arflix/tv/data/model/IptvSnapshot;"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.tv.TvViewModel$startCompleteEpgBackfill$2$snapshot$1", f = "TvViewModel.kt", l = {1128}, m = "invokeSuspend", v = 2)
public final class TvViewModel$startCompleteEpgBackfill$2$snapshot$1 extends f7.j implements r7.p<ka.k0, d7.d<? super IptvSnapshot>, Object> {
    final /* synthetic */ boolean $largeList;
    int label;
    final /* synthetic */ TvViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TvViewModel$startCompleteEpgBackfill$2$snapshot$1(TvViewModel tvViewModel, boolean z, d7.d<? super TvViewModel$startCompleteEpgBackfill$2$snapshot$1> dVar) {
        super(2, dVar);
        this.this$0 = tvViewModel;
        this.$largeList = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 invokeSuspend$lambda$0(IptvLoadProgress iptvLoadProgress) {
        PrintStream printStream = System.err;
        String message = iptvLoadProgress.getMessage();
        Object percent = iptvLoadProgress.getPercent();
        if (percent == null) {
            percent = "";
        }
        printStream.println(kotlin.text.o.L0("[EPG-Complete] " + message + " " + percent).toString());
        return t0.f22605a;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        return new TvViewModel$startCompleteEpgBackfill$2$snapshot$1(this.this$0, this.$largeList, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        int i10 = this.label;
        if (i10 != 0) {
            if (i10 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
            return obj;
        }
        k2.c.G(obj);
        IptvRepository iptvRepository = this.this$0.getIptvRepository();
        boolean z = !this.$largeList;
        j0 j0Var = new j0(3);
        this.label = 1;
        Object objLoadSnapshot$default = IptvRepository.loadSnapshot$default(iptvRepository, false, true, true, z, j0Var, null, this, 32, null);
        e7.a aVar = e7.a.f15033i;
        return objLoadSnapshot$default == aVar ? aVar : objLoadSnapshot$default;
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super IptvSnapshot> dVar) {
        return ((TvViewModel$startCompleteEpgBackfill$2$snapshot$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
