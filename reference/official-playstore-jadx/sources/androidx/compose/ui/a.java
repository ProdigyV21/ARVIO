package androidx.compose.ui;

import androidx.compose.ui.Modifier;

/* JADX INFO: loaded from: classes.dex */
public abstract /* synthetic */ class a {
    static {
        Modifier.Companion companion = Modifier.INSTANCE;
    }

    public static Modifier a(Modifier modifier, Modifier modifier2) {
        return modifier2 == Modifier.INSTANCE ? modifier : new CombinedModifier(modifier, modifier2);
    }
}
