package androidx.compose.ui.platform;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0007\bf\u0018\u00002\u00020\u0001R\u001a\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u00028VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0016\u0010\n\u001a\u0004\u0018\u00010\u00078VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0016\u0010\r\u001a\u0004\u0018\u00010\u00018VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u000eÀ\u0006\u0003"}, d2 = {"Landroidx/compose/ui/platform/InspectableValue;", "", "Lga/m;", "Landroidx/compose/ui/platform/ValueElement;", "getInspectableElements", "()Lga/m;", "inspectableElements", "", "getNameFallback", "()Ljava/lang/String;", "nameFallback", "getValueOverride", "()Ljava/lang/Object;", "valueOverride", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public interface InspectableValue {

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class DefaultImpls {
        @Deprecated
        public static ga.m<ValueElement> getInspectableElements(InspectableValue inspectableValue) {
            return m.a(inspectableValue);
        }

        @Deprecated
        public static String getNameFallback(InspectableValue inspectableValue) {
            return m.b(inspectableValue);
        }

        @Deprecated
        public static Object getValueOverride(InspectableValue inspectableValue) {
            return m.c(inspectableValue);
        }
    }

    ga.m<ValueElement> getInspectableElements();

    String getNameFallback();

    Object getValueOverride();
}
