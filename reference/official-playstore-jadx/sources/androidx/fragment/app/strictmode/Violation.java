package androidx.fragment.app.strictmode;

import androidx.fragment.app.c0;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b&\u0018\u00002\u00060\u0001j\u0002`\u0002¨\u0006\u0003"}, d2 = {"Landroidx/fragment/app/strictmode/Violation;", "Ljava/lang/RuntimeException;", "Lkotlin/RuntimeException;", "fragment_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public abstract class Violation extends RuntimeException {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final c0 f2841i;

    public Violation(c0 c0Var, String str) {
        super(str);
        this.f2841i = c0Var;
    }
}
