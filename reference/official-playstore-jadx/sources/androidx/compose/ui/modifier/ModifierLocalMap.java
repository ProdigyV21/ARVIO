package androidx.compose.ui.modifier;

import kotlin.Metadata;
import kotlin.jvm.internal.h;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b7\u0018\u00002\u00020\u0001B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003J,\u0010\u000b\u001a\u00020\b\"\u0004\b\u0000\u0010\u00042\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u00052\u0006\u0010\u0007\u001a\u00028\u0000H \u0002¢\u0006\u0004\b\t\u0010\nJ&\u0010\u000e\u001a\u0004\u0018\u00018\u0000\"\u0004\b\u0000\u0010\u00042\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005H \u0002¢\u0006\u0004\b\f\u0010\rJ\u001c\u0010\u0012\u001a\u00020\u000f2\n\u0010\u0006\u001a\u0006\u0012\u0002\b\u00030\u0005H \u0002¢\u0006\u0004\b\u0010\u0010\u0011\u0082\u0001\u0004\u0013\u0014\u0015\u0016¨\u0006\u0017"}, d2 = {"Landroidx/compose/ui/modifier/ModifierLocalMap;", "", "<init>", "()V", "T", "Landroidx/compose/ui/modifier/ModifierLocal;", "key", "value", "Lx6/t0;", "set$ui_release", "(Landroidx/compose/ui/modifier/ModifierLocal;Ljava/lang/Object;)V", "set", "get$ui_release", "(Landroidx/compose/ui/modifier/ModifierLocal;)Ljava/lang/Object;", "get", "", "contains$ui_release", "(Landroidx/compose/ui/modifier/ModifierLocal;)Z", "contains", "Landroidx/compose/ui/modifier/BackwardsCompatLocalMap;", "Landroidx/compose/ui/modifier/EmptyMap;", "Landroidx/compose/ui/modifier/MultiLocalMap;", "Landroidx/compose/ui/modifier/SingleLocalMap;", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public abstract class ModifierLocalMap {
    public static final int $stable = 0;

    public /* synthetic */ ModifierLocalMap(h hVar) {
        this();
    }

    public abstract boolean contains$ui_release(ModifierLocal<?> key);

    public abstract <T> T get$ui_release(ModifierLocal<T> key);

    /* JADX INFO: renamed from: set$ui_release */
    public abstract <T> void mo4706set$ui_release(ModifierLocal<T> key, T value);

    private ModifierLocalMap() {
    }
}
