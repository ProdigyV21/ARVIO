package com.arflix.tv.ui.screens.tv.live;

import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.State;
import androidx.credentials.exceptions.publickeycredential.DomExceptionUtils;
import com.arflix.tv.data.model.IptvNowNext;
import com.arflix.tv.ui.screens.tv.TvViewModel;
import java.io.PrintStream;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.tv.live.LiveTvScreenKt$LiveTvScreen$26$1", f = "LiveTvScreen.kt", l = {1187}, m = "invokeSuspend", v = 2)
public final class LiveTvScreenKt$LiveTvScreen$26$1 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
    final /* synthetic */ List<EnrichedChannel> $guideChannels;
    final /* synthetic */ State<Long> $guideClockMillis$delegate;
    final /* synthetic */ MutableState<Map<String, IptvNowNext>> $indexedGuideNowNextState;
    final /* synthetic */ TvViewModel $viewModel;
    long J$0;
    long J$1;
    long J$2;
    Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LiveTvScreenKt$LiveTvScreen$26$1(List<EnrichedChannel> list, MutableState<Map<String, IptvNowNext>> mutableState, State<Long> state, TvViewModel tvViewModel, d7.d<? super LiveTvScreenKt$LiveTvScreen$26$1> dVar) {
        super(2, dVar);
        this.$guideChannels = list;
        this.$indexedGuideNowNextState = mutableState;
        this.$guideClockMillis$delegate = state;
        this.$viewModel = tvViewModel;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean invokeSuspend$lambda$1(String str) {
        return !kotlin.text.o.h0(str);
    }

    @Override // f7.a
    public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
        return new LiveTvScreenKt$LiveTvScreen$26$1(this.$guideChannels, this.$indexedGuideNowNextState, this.$guideClockMillis$delegate, this.$viewModel, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) throws Throwable {
        Set set;
        long j10;
        int i10 = this.label;
        x6.t0 t0Var = x6.t0.f22605a;
        if (i10 == 0) {
            k2.c.G(obj);
            Set setW = ga.r.W(ga.r.I(new ga.t(new ga.p(this.$guideChannels, 3), new n2(0)), new n2(1)));
            if (setW.isEmpty()) {
                this.$indexedGuideNowNextState.setValue(kotlin.collections.a0.f19683i);
                return t0Var;
            }
            long jLiveTvScreen$lambda$11 = LiveTvScreenKt.LiveTvScreen$lambda$11(this.$guideClockMillis$delegate) - 172800000;
            long jLiveTvScreen$lambda$112 = LiveTvScreenKt.LiveTvScreen$lambda$11(this.$guideClockMillis$delegate) + 172800000;
            long jCurrentTimeMillis = System.currentTimeMillis();
            ra.c cVar = ka.x0.f19655d;
            LiveTvScreenKt$LiveTvScreen$26$1$indexed$1 liveTvScreenKt$LiveTvScreen$26$1$indexed$1 = new LiveTvScreenKt$LiveTvScreen$26$1$indexed$1(this.$viewModel, setW, jLiveTvScreen$lambda$11, jLiveTvScreen$lambda$112, null);
            this.L$0 = setW;
            this.J$0 = jLiveTvScreen$lambda$11;
            this.J$1 = jLiveTvScreen$lambda$112;
            this.J$2 = jCurrentTimeMillis;
            this.label = 1;
            obj = ka.m0.y(cVar, liveTvScreenKt$LiveTvScreen$26$1$indexed$1, this);
            e7.a aVar = e7.a.f15033i;
            if (obj == aVar) {
                return aVar;
            }
            set = setW;
            j10 = jCurrentTimeMillis;
        } else {
            if (i10 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            j10 = this.J$2;
            set = (Set) this.L$0;
            k2.c.G(obj);
        }
        Map<String, IptvNowNext> map = (Map) obj;
        this.$indexedGuideNowNextState.setValue(map);
        PrintStream printStream = System.err;
        int size = map.size();
        int size2 = set.size();
        int size3 = this.$guideChannels.size();
        long jCurrentTimeMillis2 = System.currentTimeMillis() - j10;
        StringBuilder sbV = androidx.compose.foundation.c.v("[TV-Metrics] indexed guide visible=", size, DomExceptionUtils.SEPARATOR, size2, " rows=");
        sbV.append(size3);
        sbV.append(" in ");
        sbV.append(jCurrentTimeMillis2);
        sbV.append("ms");
        printStream.println(sbV.toString());
        return t0Var;
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
        return ((LiveTvScreenKt$LiveTvScreen$26$1) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
    }
}
