package androidx.compose.ui.semantics;

import androidx.compose.ui.platform.JvmActuals_jvmKt;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.p;
import x6.o;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u001c\n\u0002\u0010&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010(\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0011\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\b\t\b\u0007\u0018\u00002\u00020\u00012\u001a\u0012\u0016\u0012\u0014\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u00030\u0002B\u0007¢\u0006\u0004\b\u0006\u0010\u0007J$\u0010\n\u001a\u00028\u0000\"\u0004\b\u0000\u0010\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004H\u0086\u0002¢\u0006\u0004\b\n\u0010\u000bJ/\u0010\u000e\u001a\u00028\u0000\"\u0004\b\u0000\u0010\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00000\u00042\f\u0010\r\u001a\b\u0012\u0004\u0012\u00028\u00000\f¢\u0006\u0004\b\u000e\u0010\u000fJ3\u0010\u0010\u001a\u0004\u0018\u00018\u0000\"\u0004\b\u0000\u0010\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00000\u00042\u000e\u0010\r\u001a\n\u0012\u0006\u0012\u0004\u0018\u00018\u00000\f¢\u0006\u0004\b\u0010\u0010\u000fJ(\u0010\u0012\u001a\u001a\u0012\u0016\u0012\u0014\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u00030\u0011H\u0096\u0002¢\u0006\u0004\b\u0012\u0010\u0013J,\u0010\u0016\u001a\u00020\u0015\"\u0004\b\u0000\u0010\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00000\u00042\u0006\u0010\u0014\u001a\u00028\u0000H\u0096\u0002¢\u0006\u0004\b\u0016\u0010\u0017J$\u0010\u0019\u001a\u00020\u0018\"\u0004\b\u0000\u0010\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004H\u0086\u0002¢\u0006\u0004\b\u0019\u0010\u001aJ\u000f\u0010\u001d\u001a\u00020\u0018H\u0000¢\u0006\u0004\b\u001b\u0010\u001cJ\u0017\u0010!\u001a\u00020\u00152\u0006\u0010\u001e\u001a\u00020\u0000H\u0000¢\u0006\u0004\b\u001f\u0010 J\u0017\u0010$\u001a\u00020\u00152\u0006\u0010\"\u001a\u00020\u0000H\u0000¢\u0006\u0004\b#\u0010 J\r\u0010%\u001a\u00020\u0000¢\u0006\u0004\b%\u0010&J\u001a\u0010(\u001a\u00020\u00182\b\u0010'\u001a\u0004\u0018\u00010\u0005H\u0096\u0002¢\u0006\u0004\b(\u0010)J\u000f\u0010+\u001a\u00020*H\u0016¢\u0006\u0004\b+\u0010,J\u000f\u0010.\u001a\u00020-H\u0016¢\u0006\u0004\b.\u0010/R&\u00101\u001a\u0014\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u0005008\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b1\u00102R\"\u00103\u001a\u00020\u00188\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b3\u00104\u001a\u0004\b3\u0010\u001c\"\u0004\b5\u00106R\"\u00107\u001a\u00020\u00188\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b7\u00104\u001a\u0004\b7\u0010\u001c\"\u0004\b8\u00106¨\u00069"}, d2 = {"Landroidx/compose/ui/semantics/SemanticsConfiguration;", "Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;", "", "", "Landroidx/compose/ui/semantics/SemanticsPropertyKey;", "", "<init>", "()V", "T", "key", "get", "(Landroidx/compose/ui/semantics/SemanticsPropertyKey;)Ljava/lang/Object;", "Lkotlin/Function0;", "defaultValue", "getOrElse", "(Landroidx/compose/ui/semantics/SemanticsPropertyKey;Lr7/a;)Ljava/lang/Object;", "getOrElseNullable", "", "iterator", "()Ljava/util/Iterator;", "value", "Lx6/t0;", "set", "(Landroidx/compose/ui/semantics/SemanticsPropertyKey;Ljava/lang/Object;)V", "", "contains", "(Landroidx/compose/ui/semantics/SemanticsPropertyKey;)Z", "containsImportantForAccessibility$ui_release", "()Z", "containsImportantForAccessibility", "child", "mergeChild$ui_release", "(Landroidx/compose/ui/semantics/SemanticsConfiguration;)V", "mergeChild", "peer", "collapsePeer$ui_release", "collapsePeer", "copy", "()Landroidx/compose/ui/semantics/SemanticsConfiguration;", "other", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "", "toString", "()Ljava/lang/String;", "", "props", "Ljava/util/Map;", "isMergingSemanticsOfDescendants", "Z", "setMergingSemanticsOfDescendants", "(Z)V", "isClearingSemantics", "setClearingSemantics", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class SemanticsConfiguration implements SemanticsPropertyReceiver, Iterable<Map.Entry<? extends SemanticsPropertyKey<?>, ? extends Object>>, s7.a {
    public static final int $stable = 8;
    private boolean isClearingSemantics;
    private boolean isMergingSemanticsOfDescendants;
    private final Map<SemanticsPropertyKey<?>, Object> props = new LinkedHashMap();

    public final void collapsePeer$ui_release(SemanticsConfiguration peer) {
        if (peer.isMergingSemanticsOfDescendants) {
            this.isMergingSemanticsOfDescendants = true;
        }
        if (peer.isClearingSemantics) {
            this.isClearingSemantics = true;
        }
        for (Map.Entry<SemanticsPropertyKey<?>, Object> entry : peer.props.entrySet()) {
            SemanticsPropertyKey<?> key = entry.getKey();
            Object value = entry.getValue();
            if (!this.props.containsKey(key)) {
                this.props.put(key, value);
            } else if (value instanceof AccessibilityAction) {
                AccessibilityAction accessibilityAction = (AccessibilityAction) this.props.get(key);
                Map<SemanticsPropertyKey<?>, Object> map = this.props;
                String label = accessibilityAction.getLabel();
                if (label == null) {
                    label = ((AccessibilityAction) value).getLabel();
                }
                o action = accessibilityAction.getAction();
                if (action == null) {
                    action = ((AccessibilityAction) value).getAction();
                }
                map.put(key, new AccessibilityAction(label, action));
            }
        }
    }

    public final <T> boolean contains(SemanticsPropertyKey<T> key) {
        return this.props.containsKey(key);
    }

    public final boolean containsImportantForAccessibility$ui_release() {
        Set<SemanticsPropertyKey<?>> setKeySet = this.props.keySet();
        if ((setKeySet instanceof Collection) && setKeySet.isEmpty()) {
            return false;
        }
        Iterator<T> it = setKeySet.iterator();
        while (it.hasNext()) {
            if (((SemanticsPropertyKey) it.next()).getIsImportantForAccessibility()) {
                return true;
            }
        }
        return false;
    }

    public final SemanticsConfiguration copy() {
        SemanticsConfiguration semanticsConfiguration = new SemanticsConfiguration();
        semanticsConfiguration.isMergingSemanticsOfDescendants = this.isMergingSemanticsOfDescendants;
        semanticsConfiguration.isClearingSemantics = this.isClearingSemantics;
        semanticsConfiguration.props.putAll(this.props);
        return semanticsConfiguration;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SemanticsConfiguration)) {
            return false;
        }
        SemanticsConfiguration semanticsConfiguration = (SemanticsConfiguration) other;
        return p.a(this.props, semanticsConfiguration.props) && this.isMergingSemanticsOfDescendants == semanticsConfiguration.isMergingSemanticsOfDescendants && this.isClearingSemantics == semanticsConfiguration.isClearingSemantics;
    }

    public final <T> T get(SemanticsPropertyKey<T> key) {
        T t2 = (T) this.props.get(key);
        if (t2 != null) {
            return t2;
        }
        throw new IllegalStateException("Key not present: " + key + " - consider getOrElse or getOrNull");
    }

    public final <T> T getOrElse(SemanticsPropertyKey<T> key, r7.a<? extends T> defaultValue) {
        T t2 = (T) this.props.get(key);
        return t2 == null ? (T) defaultValue.invoke() : t2;
    }

    public final <T> T getOrElseNullable(SemanticsPropertyKey<T> key, r7.a<? extends T> defaultValue) {
        T t2 = (T) this.props.get(key);
        return t2 == null ? (T) defaultValue.invoke() : t2;
    }

    public int hashCode() {
        return (((this.props.hashCode() * 31) + (this.isMergingSemanticsOfDescendants ? 1231 : 1237)) * 31) + (this.isClearingSemantics ? 1231 : 1237);
    }

    /* JADX INFO: renamed from: isClearingSemantics, reason: from getter */
    public final boolean getIsClearingSemantics() {
        return this.isClearingSemantics;
    }

    /* JADX INFO: renamed from: isMergingSemanticsOfDescendants, reason: from getter */
    public final boolean getIsMergingSemanticsOfDescendants() {
        return this.isMergingSemanticsOfDescendants;
    }

    @Override // java.lang.Iterable
    public Iterator<Map.Entry<? extends SemanticsPropertyKey<?>, ? extends Object>> iterator() {
        return this.props.entrySet().iterator();
    }

    public final void mergeChild$ui_release(SemanticsConfiguration child) {
        for (Map.Entry<SemanticsPropertyKey<?>, Object> entry : child.props.entrySet()) {
            SemanticsPropertyKey<?> key = entry.getKey();
            Object objMerge = key.merge(this.props.get(key), entry.getValue());
            if (objMerge != null) {
                this.props.put(key, objMerge);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.compose.ui.semantics.SemanticsPropertyReceiver
    public <T> void set(SemanticsPropertyKey<T> key, T value) {
        if (!(value instanceof AccessibilityAction) || !contains(key)) {
            this.props.put(key, value);
            return;
        }
        AccessibilityAction accessibilityAction = (AccessibilityAction) this.props.get(key);
        Map<SemanticsPropertyKey<?>, Object> map = this.props;
        AccessibilityAction accessibilityAction2 = (AccessibilityAction) value;
        String label = accessibilityAction2.getLabel();
        if (label == null) {
            label = accessibilityAction.getLabel();
        }
        o action = accessibilityAction2.getAction();
        if (action == null) {
            action = accessibilityAction.getAction();
        }
        map.put(key, new AccessibilityAction(label, action));
    }

    public final void setClearingSemantics(boolean z) {
        this.isClearingSemantics = z;
    }

    public final void setMergingSemanticsOfDescendants(boolean z) {
        this.isMergingSemanticsOfDescendants = z;
    }

    public String toString() {
        String str;
        StringBuilder sb2 = new StringBuilder();
        if (this.isMergingSemanticsOfDescendants) {
            sb2.append("mergeDescendants=true");
            str = ", ";
        } else {
            str = "";
        }
        if (this.isClearingSemantics) {
            sb2.append(str);
            sb2.append("isClearingSemantics=true");
            str = ", ";
        }
        for (Map.Entry<SemanticsPropertyKey<?>, Object> entry : this.props.entrySet()) {
            SemanticsPropertyKey<?> key = entry.getKey();
            Object value = entry.getValue();
            sb2.append(str);
            sb2.append(key.getName());
            sb2.append(" : ");
            sb2.append(value);
            str = ", ";
        }
        return JvmActuals_jvmKt.simpleIdentityToString(this, null) + "{ " + ((Object) sb2) + " }";
    }
}
