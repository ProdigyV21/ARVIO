package androidx.compose.ui.modifier;

import kotlin.Metadata;
import kotlin.jvm.internal.h;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\b7\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B\u0017\b\u0004\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003¢\u0006\u0004\b\u0005\u0010\u0006R \u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u00038\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u0004\u0010\u0007\u001a\u0004\b\b\u0010\t\u0082\u0001\u0001\n¨\u0006\u000b"}, d2 = {"Landroidx/compose/ui/modifier/ModifierLocal;", "T", "", "Lkotlin/Function0;", "defaultFactory", "<init>", "(Lr7/a;)V", "Lr7/a;", "getDefaultFactory$ui_release", "()Lr7/a;", "Landroidx/compose/ui/modifier/ProvidableModifierLocal;", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public abstract class ModifierLocal<T> {
    public static final int $stable = 0;
    private final r7.a<T> defaultFactory;

    public /* synthetic */ ModifierLocal(r7.a aVar, h hVar) {
        this(aVar);
    }

    public final r7.a<T> getDefaultFactory$ui_release() {
        return this.defaultFactory;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private ModifierLocal(r7.a<? extends T> aVar) {
        this.defaultFactory = aVar;
    }
}
