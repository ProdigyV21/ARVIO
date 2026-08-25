package com.arflix.tv.ui.components;

import androidx.compose.animation.core.Animatable;
import androidx.compose.foundation.lazy.LazyListScope;
import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.State;
import androidx.compose.ui.graphics.GraphicsLayerScope;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import com.arflix.tv.data.model.PersonDetails;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class m implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f8173i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f8174l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f8175m;

    public /* synthetic */ m(Object obj, Object obj2, int i10) {
        this.f8173i = i10;
        this.f8174l = obj;
        this.f8175m = obj2;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        switch (this.f8173i) {
            case 0:
                return ArvioLoadingScreenKt.ArvioLoadingScreen$lambda$3$0$0$0((Animatable) this.f8174l, (State) this.f8175m, (GraphicsLayerScope) obj);
            case 1:
                return PersonModalKt.PersonModal$lambda$10$1$1$1$1$0((PersonDetails) this.f8174l, (MutableIntState) this.f8175m, (androidx.tv.foundation.lazy.list.g0) obj);
            case 2:
                return PersonModalKt.MobilePersonContent$lambda$0$3$0((PersonDetails) this.f8174l, (r7.p) this.f8175m, (LazyListScope) obj);
            default:
                return PremiumEffectsKt.FloatingParticles$lambda$2$0((List) this.f8174l, (State) this.f8175m, (DrawScope) obj);
        }
    }
}
