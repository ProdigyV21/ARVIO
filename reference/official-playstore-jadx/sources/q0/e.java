package q0;

import android.R;
import android.os.Build;
import android.view.accessibility.AccessibilityNodeInfo;
import androidx.media3.exoplayer.source.ProgressiveMediaSource;
import androidx.media3.session.MediaUtils;

/* JADX INFO: loaded from: classes3.dex */
public final class e {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final e f21306c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final e f21307d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final e f21308e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final e f21309f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final e f21310g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final e f21311h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final e f21312i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final e f21313j;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object f21314a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f21315b;

    static {
        new e(null, 1, null, null);
        new e(null, 2, null, null);
        new e(null, 4, null, null);
        new e(null, 8, null, null);
        new e(null, 16, null, null);
        new e(null, 32, null, null);
        f21306c = new e(null, 64, null, null);
        f21307d = new e(null, 128, null, null);
        new e(null, 256, null, m.class);
        new e(null, 512, null, m.class);
        new e(null, 1024, null, n.class);
        new e(null, 2048, null, n.class);
        f21308e = new e(null, 4096, null, null);
        f21309f = new e(null, 8192, null, null);
        new e(null, 16384, null, null);
        new e(null, 32768, null, null);
        new e(null, 65536, null, null);
        new e(null, 131072, null, r.class);
        new e(null, MediaUtils.TRANSACTION_SIZE_LIMIT_IN_BYTES, null, null);
        new e(null, 524288, null, null);
        new e(null, ProgressiveMediaSource.DEFAULT_LOADING_CHECK_INTERVAL_BYTES, null, null);
        new e(null, 2097152, null, s.class);
        int i10 = Build.VERSION.SDK_INT;
        new e(AccessibilityNodeInfo.AccessibilityAction.ACTION_SHOW_ON_SCREEN, R.id.accessibilityActionShowOnScreen, null, null);
        new e(AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_TO_POSITION, R.id.accessibilityActionScrollToPosition, null, p.class);
        f21310g = new e(AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_UP, R.id.accessibilityActionScrollUp, null, null);
        f21311h = new e(AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_LEFT, R.id.accessibilityActionScrollLeft, null, null);
        f21312i = new e(AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_DOWN, R.id.accessibilityActionScrollDown, null, null);
        f21313j = new e(AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_RIGHT, R.id.accessibilityActionScrollRight, null, null);
        new e(i10 >= 29 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_PAGE_UP : null, R.id.accessibilityActionPageUp, null, null);
        new e(i10 >= 29 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_PAGE_DOWN : null, R.id.accessibilityActionPageDown, null, null);
        new e(i10 >= 29 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_PAGE_LEFT : null, R.id.accessibilityActionPageLeft, null, null);
        new e(i10 >= 29 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_PAGE_RIGHT : null, R.id.accessibilityActionPageRight, null, null);
        new e(AccessibilityNodeInfo.AccessibilityAction.ACTION_CONTEXT_CLICK, R.id.accessibilityActionContextClick, null, null);
        new e(i10 >= 24 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_SET_PROGRESS : null, R.id.accessibilityActionSetProgress, null, q.class);
        new e(i10 >= 26 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_MOVE_WINDOW : null, R.id.accessibilityActionMoveWindow, null, o.class);
        new e(i10 >= 28 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_SHOW_TOOLTIP : null, R.id.accessibilityActionShowTooltip, null, null);
        new e(i10 >= 28 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_HIDE_TOOLTIP : null, R.id.accessibilityActionHideTooltip, null, null);
        new e(i10 >= 30 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_PRESS_AND_HOLD : null, R.id.accessibilityActionPressAndHold, null, null);
        new e(i10 >= 30 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_IME_ENTER : null, R.id.accessibilityActionImeEnter, null, null);
        new e(i10 >= 32 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_DRAG_START : null, R.id.accessibilityActionDragStart, null, null);
        new e(i10 >= 32 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_DRAG_DROP : null, R.id.accessibilityActionDragDrop, null, null);
        new e(i10 >= 32 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_DRAG_CANCEL : null, R.id.accessibilityActionDragCancel, null, null);
        new e(i10 >= 33 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_SHOW_TEXT_SUGGESTIONS : null, R.id.accessibilityActionShowTextSuggestions, null, null);
        new e(i10 >= 34 ? h.a() : null, R.id.accessibilityActionScrollInDirection, null, null);
    }

    public e(Object obj, int i10, CharSequence charSequence, Class cls) {
        this.f21315b = i10;
        if (obj == null) {
            this.f21314a = new AccessibilityNodeInfo.AccessibilityAction(i10, charSequence);
        } else {
            this.f21314a = obj;
        }
    }

    public final int a() {
        return ((AccessibilityNodeInfo.AccessibilityAction) this.f21314a).getId();
    }

    public final boolean equals(Object obj) {
        if (obj == null || !(obj instanceof e)) {
            return false;
        }
        Object obj2 = ((e) obj).f21314a;
        Object obj3 = this.f21314a;
        return obj3 == null ? obj2 == null : obj3.equals(obj2);
    }

    public final int hashCode() {
        Object obj = this.f21314a;
        if (obj != null) {
            return obj.hashCode();
        }
        return 0;
    }

    public final String toString() {
        StringBuilder sb2 = new StringBuilder("AccessibilityActionCompat: ");
        String strD = k.d(this.f21315b);
        if (strD.equals("ACTION_UNKNOWN")) {
            Object obj = this.f21314a;
            if (((AccessibilityNodeInfo.AccessibilityAction) obj).getLabel() != null) {
                strD = ((AccessibilityNodeInfo.AccessibilityAction) obj).getLabel().toString();
            }
        }
        sb2.append(strD);
        return sb2.toString();
    }
}
