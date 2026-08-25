package com.arflix.tv.util;

import android.app.Activity;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;
import d7.d;
import f7.e;
import f7.j;
import java.util.ArrayList;
import k2.c;
import ka.k0;
import kotlin.Metadata;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Landroid/view/Display$Mode;", "<anonymous>", "(Lka/k0;)Landroid/view/Display$Mode;"}, k = 3, mv = {2, 3, 0})
@e(c = "com.arflix.tv.util.FrameRateUtils$matchFrameRateAndWait$targetMode$1", f = "FrameRateUtils.kt", l = {}, m = "invokeSuspend", v = 2)
public final class FrameRateUtils$matchFrameRateAndWait$targetMode$1 extends j implements p<k0, d<? super Display.Mode>, Object> {
    final /* synthetic */ Activity $activity;
    final /* synthetic */ float $frameRate;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FrameRateUtils$matchFrameRateAndWait$targetMode$1(Activity activity, float f10, d<? super FrameRateUtils$matchFrameRateAndWait$targetMode$1> dVar) {
        super(2, dVar);
        this.$activity = activity;
        this.$frameRate = f10;
    }

    @Override // f7.a
    public final d<t0> create(Object obj, d<?> dVar) {
        return new FrameRateUtils$matchFrameRateAndWait$targetMode$1(this.$activity, this.$frameRate, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        Display display;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        c.G(obj);
        Window window = this.$activity.getWindow();
        if (window == null || (display = window.getDecorView().getDisplay()) == null) {
            return null;
        }
        Display.Mode mode = display.getMode();
        Display.Mode[] supportedModes = display.getSupportedModes();
        ArrayList arrayList = new ArrayList();
        for (Display.Mode mode2 : supportedModes) {
            if (mode2.getPhysicalWidth() == mode.getPhysicalWidth() && mode2.getPhysicalHeight() == mode.getPhysicalHeight()) {
                arrayList.add(mode2);
            }
        }
        if (arrayList.size() <= 1) {
            return null;
        }
        Display.Mode modeChooseBestMode = FrameRateUtils.INSTANCE.chooseBestMode(mode, arrayList, this.$frameRate);
        if (modeChooseBestMode.getModeId() == mode.getModeId()) {
            return null;
        }
        if (FrameRateUtils.originalModeId == null) {
            FrameRateUtils.originalModeId = new Integer(mode.getModeId());
        }
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.preferredDisplayModeId = modeChooseBestMode.getModeId();
        window.setAttributes(attributes);
        return modeChooseBestMode;
    }

    @Override // r7.p
    public final Object invoke(k0 k0Var, d<? super Display.Mode> dVar) {
        return ((FrameRateUtils$matchFrameRateAndWait$targetMode$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
