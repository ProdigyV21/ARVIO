package androidx.leanback.widget;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.EditText;
import com.arvio.tv.R;
import java.util.Random;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes3.dex */
public abstract class m1 extends EditText {

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final Pattern f3102p = Pattern.compile("\\S+");

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final r0 f3103q = new r0(Integer.class, "streamPosition", 3);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Random f3104i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public Bitmap f3105l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public Bitmap f3106m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f3107n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public ObjectAnimator f3108o;

    public m1(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, R.style.TextAppearance_Leanback_SearchTextEdit);
        this.f3104i = new Random();
    }

    public int getStreamPosition() {
        return this.f3107n;
    }

    @Override // android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        this.f3105l = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.lb_text_dot_one), (int) (r0.getWidth() * 1.3f), (int) (r0.getHeight() * 1.3f), false);
        this.f3106m = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.lb_text_dot_two), (int) (r0.getWidth() * 1.3f), (int) (r0.getHeight() * 1.3f), false);
        this.f3107n = -1;
        ObjectAnimator objectAnimator = this.f3108o;
        if (objectAnimator != null) {
            objectAnimator.cancel();
        }
        setText("");
    }

    @Override // android.view.View
    public final void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName("androidx.leanback.widget.StreamingTextView");
    }

    @Override // android.widget.TextView
    public void setCustomSelectionActionModeCallback(ActionMode.Callback callback) {
        super.setCustomSelectionActionModeCallback(m2.f0.X(callback, this));
    }

    public void setStreamPosition(int i10) {
        this.f3107n = i10;
        invalidate();
    }
}
