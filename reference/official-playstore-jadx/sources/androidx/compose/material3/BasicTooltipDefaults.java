package androidx.compose.material3;

import androidx.compose.foundation.MutatorMutex;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u000e\u0010\u0007\u001a\u00020\bX\u0086T¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Landroidx/compose/material3/BasicTooltipDefaults;", "", "()V", "GlobalMutatorMutex", "Landroidx/compose/foundation/MutatorMutex;", "getGlobalMutatorMutex", "()Landroidx/compose/foundation/MutatorMutex;", "TooltipDuration", "", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class BasicTooltipDefaults {
    public static final int $stable = 0;
    public static final long TooltipDuration = 1500;
    public static final BasicTooltipDefaults INSTANCE = new BasicTooltipDefaults();
    private static final MutatorMutex GlobalMutatorMutex = new MutatorMutex();

    private BasicTooltipDefaults() {
    }

    public final MutatorMutex getGlobalMutatorMutex() {
        return GlobalMutatorMutex;
    }
}
