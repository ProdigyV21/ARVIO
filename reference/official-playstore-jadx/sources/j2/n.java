package j2;

import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.font.GenericFontFamily;

/* JADX INFO: loaded from: classes3.dex */
public abstract class n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final GenericFontFamily f19180a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final GenericFontFamily f19181b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final FontWeight f19182c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final FontWeight f19183d;

    static {
        FontFamily.Companion companion = FontFamily.INSTANCE;
        f19180a = companion.getSansSerif();
        f19181b = companion.getSansSerif();
        FontWeight.Companion companion2 = FontWeight.INSTANCE;
        companion2.getBold();
        f19182c = companion2.getMedium();
        f19183d = companion2.getNormal();
    }
}
