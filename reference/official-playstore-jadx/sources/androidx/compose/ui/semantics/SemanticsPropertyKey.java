package androidx.compose.ui.semantics;

import io.ktor.http.ContentDisposition;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.r;
import kotlin.reflect.m;
import r7.p;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000e\b\u0007\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B/\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u001e\b\u0002\u0010\u0006\u001a\u0018\u0012\u0006\u0012\u0004\u0018\u00018\u0000\u0012\u0004\u0012\u00028\u0000\u0012\u0006\u0012\u0004\u0018\u00018\u00000\u0005¢\u0006\u0004\b\u0007\u0010\bB\u0019\b\u0010\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\t¢\u0006\u0004\b\u0007\u0010\u000bB7\b\u0010\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\t\u0012\u001c\u0010\u0006\u001a\u0018\u0012\u0006\u0012\u0004\u0018\u00018\u0000\u0012\u0004\u0012\u00028\u0000\u0012\u0006\u0012\u0004\u0018\u00018\u00000\u0005¢\u0006\u0004\b\u0007\u0010\fJ!\u0010\u000f\u001a\u0004\u0018\u00018\u00002\b\u0010\r\u001a\u0004\u0018\u00018\u00002\u0006\u0010\u000e\u001a\u00028\u0000¢\u0006\u0004\b\u000f\u0010\u0010J$\u0010\u0015\u001a\u00028\u00002\u0006\u0010\u0012\u001a\u00020\u00112\n\u0010\u0014\u001a\u0006\u0012\u0002\b\u00030\u0013H\u0086\u0002¢\u0006\u0004\b\u0015\u0010\u0016J,\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u0012\u001a\u00020\u00112\n\u0010\u0014\u001a\u0006\u0012\u0002\b\u00030\u00132\u0006\u0010\u0017\u001a\u00028\u0000H\u0086\u0002¢\u0006\u0004\b\u0019\u0010\u001aJ\u000f\u0010\u001b\u001a\u00020\u0003H\u0016¢\u0006\u0004\b\u001b\u0010\u001cR\u0017\u0010\u0004\u001a\u00020\u00038\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u001d\u001a\u0004\b\u001e\u0010\u001cR0\u0010\u0006\u001a\u0018\u0012\u0006\u0012\u0004\u0018\u00018\u0000\u0012\u0004\u0012\u00028\u0000\u0012\u0006\u0012\u0004\u0018\u00018\u00000\u00058\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u0006\u0010\u001f\u001a\u0004\b \u0010!R$\u0010\n\u001a\u00020\t2\u0006\u0010\"\u001a\u00020\t8\u0000@BX\u0080\u000e¢\u0006\f\n\u0004\b\n\u0010#\u001a\u0004\b$\u0010%¨\u0006&"}, d2 = {"Landroidx/compose/ui/semantics/SemanticsPropertyKey;", "T", "", "", ContentDisposition.Parameters.Name, "Lkotlin/Function2;", "mergePolicy", "<init>", "(Ljava/lang/String;Lr7/p;)V", "", "isImportantForAccessibility", "(Ljava/lang/String;Z)V", "(Ljava/lang/String;ZLr7/p;)V", "parentValue", "childValue", "merge", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;", "thisRef", "Lkotlin/reflect/m;", "property", "getValue", "(Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;Lkotlin/reflect/m;)Ljava/lang/Object;", "value", "Lx6/t0;", "setValue", "(Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;Lkotlin/reflect/m;Ljava/lang/Object;)V", "toString", "()Ljava/lang/String;", "Ljava/lang/String;", "getName", "Lr7/p;", "getMergePolicy$ui_release", "()Lr7/p;", "<set-?>", "Z", "isImportantForAccessibility$ui_release", "()Z", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class SemanticsPropertyKey<T> {
    public static final int $stable = 8;
    private boolean isImportantForAccessibility;
    private final p<T, T, T> mergePolicy;
    private final String name;

    /* JADX INFO: renamed from: androidx.compose.ui.semantics.SemanticsPropertyKey$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0004\n\u0002\b\u0006\u0010\u0000\u001a\u0004\u0018\u0001H\u0001\"\u0004\b\u0000\u0010\u00012\b\u0010\u0002\u001a\u0004\u0018\u0001H\u00012\u0006\u0010\u0003\u001a\u0002H\u0001H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "T", "parentValue", "childValue", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass1 extends r implements p<T, T, T> {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        public AnonymousClass1() {
            super(2);
        }

        @Override // r7.p
        public final T invoke(T t2, T t10) {
            return t2 == null ? t10 : t2;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public SemanticsPropertyKey(String str, p<? super T, ? super T, ? extends T> pVar) {
        this.name = str;
        this.mergePolicy = pVar;
    }

    public final p<T, T, T> getMergePolicy$ui_release() {
        return this.mergePolicy;
    }

    public final String getName() {
        return this.name;
    }

    public final T getValue(SemanticsPropertyReceiver thisRef, m<?> property) {
        return (T) SemanticsPropertiesKt.throwSemanticsGetNotSupported();
    }

    /* JADX INFO: renamed from: isImportantForAccessibility$ui_release, reason: from getter */
    public final boolean getIsImportantForAccessibility() {
        return this.isImportantForAccessibility;
    }

    public final T merge(T parentValue, T childValue) {
        return (T) this.mergePolicy.invoke(parentValue, childValue);
    }

    public final void setValue(SemanticsPropertyReceiver thisRef, m<?> property, T value) {
        thisRef.set(this, value);
    }

    public String toString() {
        return "AccessibilityKey: " + this.name;
    }

    public /* synthetic */ SemanticsPropertyKey(String str, p pVar, int i10, h hVar) {
        this(str, (i10 & 2) != 0 ? AnonymousClass1.INSTANCE : pVar);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public SemanticsPropertyKey(String str, boolean z) {
        this(str, null, 2, 0 == true ? 1 : 0);
        this.isImportantForAccessibility = z;
    }

    public SemanticsPropertyKey(String str, boolean z, p<? super T, ? super T, ? extends T> pVar) {
        this(str, pVar);
        this.isImportantForAccessibility = z;
    }
}
