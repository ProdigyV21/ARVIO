package androidx.tv.material3;

import androidx.compose.runtime.MutableState;
import androidx.compose.ui.focus.FocusState;

/* JADX INFO: loaded from: classes3.dex */
public final class c0 extends kotlin.jvm.internal.r implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f5867i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ MutableState f5868l;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ c0(MutableState mutableState, int i10) {
        super(1);
        this.f5867i = i10;
        this.f5868l = mutableState;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        switch (this.f5867i) {
            case 0:
                Boolean bool = (Boolean) obj;
                bool.booleanValue();
                this.f5868l.setValue(bool);
                break;
            case 1:
                this.f5868l.setValue((FocusState) obj);
                break;
            default:
                this.f5868l.setValue(Boolean.valueOf(((FocusState) obj).getHasFocus()));
                break;
        }
        return x6.t0.f22605a;
    }
}
