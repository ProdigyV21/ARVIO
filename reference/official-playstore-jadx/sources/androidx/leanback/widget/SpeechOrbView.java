package androidx.leanback.widget;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.View;
import com.arvio.tv.R;

/* JADX INFO: loaded from: classes3.dex */
public class SpeechOrbView extends SearchOrbView {
    public final float C;
    public f1 D;
    public f1 E;
    public int F;
    public boolean G;

    public SpeechOrbView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0);
        this.F = 0;
        this.G = false;
        Resources resources = context.getResources();
        this.C = resources.getFraction(R.fraction.lb_search_bar_speech_orb_max_level_zoom, 1, 1);
        this.E = new f1(resources.getColor(R.color.lb_speech_orb_not_recording), resources.getColor(R.color.lb_speech_orb_not_recording_pulsed), resources.getColor(R.color.lb_speech_orb_not_recording_icon));
        this.D = new f1(resources.getColor(R.color.lb_speech_orb_recording), resources.getColor(R.color.lb_speech_orb_recording), 0);
        c();
    }

    public final void c() {
        setOrbColors(this.E);
        setOrbIcon(getResources().getDrawable(2131231281));
        a(hasFocus());
        View view = this.f3020m;
        view.setScaleX(1.0f);
        view.setScaleY(1.0f);
        this.G = false;
    }

    @Override // androidx.leanback.widget.SearchOrbView
    public int getLayoutResourceId() {
        return R.layout.lb_speech_orb;
    }

    public void setListeningOrbColors(f1 f1Var) {
        this.D = f1Var;
    }

    public void setNotListeningOrbColors(f1 f1Var) {
        this.E = f1Var;
    }

    public void setSoundLevel(int i10) {
        if (this.G) {
            int i11 = this.F;
            if (i10 > i11) {
                this.F = ((i10 - i11) / 2) + i11;
            } else {
                this.F = (int) (i11 * 0.7f);
            }
            float focusedZoom = (((this.C - getFocusedZoom()) * this.F) / 100.0f) + 1.0f;
            View view = this.f3020m;
            view.setScaleX(focusedZoom);
            view.setScaleY(focusedZoom);
        }
    }
}
