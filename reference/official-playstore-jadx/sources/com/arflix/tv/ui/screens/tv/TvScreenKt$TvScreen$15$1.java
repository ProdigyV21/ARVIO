package com.arflix.tv.ui.screens.tv;

import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.MutableState;
import com.arflix.tv.data.model.IptvChannel;
import java.util.List;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.tv.TvScreenKt$TvScreen$15$1", f = "TvScreen.kt", l = {}, m = "invokeSuspend", v = 2)
public final class TvScreenKt$TvScreen$15$1 extends f7.j implements r7.p<ka.k0, d7.d<? super t0>, Object> {
    final /* synthetic */ MutableIntState $channelIndex$delegate;
    final /* synthetic */ List<IptvChannel> $channels;
    final /* synthetic */ MutableState<TvFocusZone> $focusZone$delegate;
    final /* synthetic */ MutableState<String> $playingChannelId$delegate;
    final /* synthetic */ MutableState<String> $selectedChannelId$delegate;
    final /* synthetic */ String $selectedGroup;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TvScreenKt$TvScreen$15$1(String str, List<IptvChannel> list, MutableState<String> mutableState, MutableState<String> mutableState2, MutableIntState mutableIntState, MutableState<TvFocusZone> mutableState3, d7.d<? super TvScreenKt$TvScreen$15$1> dVar) {
        super(2, dVar);
        this.$selectedGroup = str;
        this.$channels = list;
        this.$selectedChannelId$delegate = mutableState;
        this.$playingChannelId$delegate = mutableState2;
        this.$channelIndex$delegate = mutableIntState;
        this.$focusZone$delegate = mutableState3;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        return new TvScreenKt$TvScreen$15$1(this.$selectedGroup, this.$channels, this.$selectedChannelId$delegate, this.$playingChannelId$delegate, this.$channelIndex$delegate, this.$focusZone$delegate, dVar);
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x008b  */
    @Override // f7.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r6) {
        /*
            r5 = this;
            int r0 = r5.label
            if (r0 != 0) goto L9d
            k2.c.G(r6)
            java.lang.String r6 = r5.$selectedGroup
            boolean r6 = kotlin.text.o.h0(r6)
            x6.t0 r0 = x6.t0.f22605a
            if (r6 != 0) goto L9c
            java.util.List<com.arflix.tv.data.model.IptvChannel> r6 = r5.$channels
            boolean r6 = r6.isEmpty()
            if (r6 == 0) goto L1b
            goto L9c
        L1b:
            java.util.List<com.arflix.tv.data.model.IptvChannel> r6 = r5.$channels
            androidx.compose.runtime.MutableState<java.lang.String> r1 = r5.$selectedChannelId$delegate
            java.lang.String r1 = com.arflix.tv.ui.screens.tv.TvScreenKt.access$TvScreen$lambda$20(r1)
            androidx.compose.runtime.MutableState<java.lang.String> r2 = r5.$playingChannelId$delegate
            java.lang.String r2 = com.arflix.tv.ui.screens.tv.TvScreenKt.access$TvScreen$lambda$23(r2)
            int r6 = com.arflix.tv.ui.screens.tv.TvScreenKt.access$preferredIndexFor(r6, r1, r2)
            java.util.List<com.arflix.tv.data.model.IptvChannel> r1 = r5.$channels
            int r1 = t7.a.w(r1)
            r2 = 0
            int r6 = qb.d.n(r6, r2, r1)
            androidx.compose.runtime.MutableIntState r1 = r5.$channelIndex$delegate
            int r1 = com.arflix.tv.ui.screens.tv.TvScreenKt.access$TvScreen$lambda$17(r1)
            if (r1 == r6) goto L57
            androidx.compose.runtime.MutableState<com.arflix.tv.ui.screens.tv.TvFocusZone> r1 = r5.$focusZone$delegate
            com.arflix.tv.ui.screens.tv.TvFocusZone r1 = com.arflix.tv.ui.screens.tv.TvScreenKt.access$TvScreen$lambda$8(r1)
            com.arflix.tv.ui.screens.tv.TvFocusZone r2 = com.arflix.tv.ui.screens.tv.TvFocusZone.GUIDE
            if (r1 == r2) goto L52
            androidx.compose.runtime.MutableState<java.lang.String> r1 = r5.$selectedChannelId$delegate
            java.lang.String r1 = com.arflix.tv.ui.screens.tv.TvScreenKt.access$TvScreen$lambda$20(r1)
            if (r1 != 0) goto L57
        L52:
            androidx.compose.runtime.MutableIntState r1 = r5.$channelIndex$delegate
            com.arflix.tv.ui.screens.tv.TvScreenKt.access$TvScreen$lambda$18(r1, r6)
        L57:
            androidx.compose.runtime.MutableState<java.lang.String> r1 = r5.$selectedChannelId$delegate
            java.lang.String r1 = com.arflix.tv.ui.screens.tv.TvScreenKt.access$TvScreen$lambda$20(r1)
            if (r1 == 0) goto L8b
            java.util.List<com.arflix.tv.data.model.IptvChannel> r1 = r5.$channels
            androidx.compose.runtime.MutableState<java.lang.String> r2 = r5.$selectedChannelId$delegate
            if (r1 == 0) goto L6c
            boolean r3 = r1.isEmpty()
            if (r3 == 0) goto L6c
            goto L8b
        L6c:
            java.util.Iterator r1 = r1.iterator()
        L70:
            boolean r3 = r1.hasNext()
            if (r3 == 0) goto L8b
            java.lang.Object r3 = r1.next()
            com.arflix.tv.data.model.IptvChannel r3 = (com.arflix.tv.data.model.IptvChannel) r3
            java.lang.String r3 = r3.getId()
            java.lang.String r4 = com.arflix.tv.ui.screens.tv.TvScreenKt.access$TvScreen$lambda$20(r2)
            boolean r3 = kotlin.jvm.internal.p.a(r3, r4)
            if (r3 == 0) goto L70
            goto L9c
        L8b:
            androidx.compose.runtime.MutableState<java.lang.String> r1 = r5.$selectedChannelId$delegate
            java.util.List<com.arflix.tv.data.model.IptvChannel> r2 = r5.$channels
            java.lang.Object r6 = r2.get(r6)
            com.arflix.tv.data.model.IptvChannel r6 = (com.arflix.tv.data.model.IptvChannel) r6
            java.lang.String r6 = r6.getId()
            com.arflix.tv.ui.screens.tv.TvScreenKt.access$TvScreen$lambda$21(r1, r6)
        L9c:
            return r0
        L9d:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r0)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.tv.TvScreenKt$TvScreen$15$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super t0> dVar) {
        return ((TvScreenKt$TvScreen$15$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
