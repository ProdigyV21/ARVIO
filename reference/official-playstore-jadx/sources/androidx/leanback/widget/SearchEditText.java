package androidx.leanback.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.KeyEvent;

/* JADX INFO: loaded from: classes3.dex */
public class SearchEditText extends m1 {

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public a f3017r;

    public interface a {
        void a();
    }

    public SearchEditText(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // android.widget.TextView, android.view.View
    public final boolean onKeyPreIme(int i10, KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == 4 && this.f3017r != null) {
            post(new d1(this));
        }
        return super.onKeyPreIme(i10, keyEvent);
    }

    @Override // androidx.leanback.widget.m1, android.widget.TextView
    public /* bridge */ /* synthetic */ void setCustomSelectionActionModeCallback(ActionMode.Callback callback) {
        super.setCustomSelectionActionModeCallback(callback);
    }

    public void setFinalRecognizedText(CharSequence charSequence) {
        setText(charSequence);
        bringPointIntoView(length());
    }

    public void setOnKeyboardDismissListener(a aVar) {
        this.f3017r = aVar;
    }
}
