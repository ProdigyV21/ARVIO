package androidx.compose.foundation.text;

import androidx.compose.ui.text.input.ImeAction;
import androidx.compose.ui.text.input.ImeOptions;
import androidx.compose.ui.text.input.KeyboardCapitalization;
import androidx.compose.ui.text.input.KeyboardType;
import androidx.compose.ui.text.input.PlatformImeOptions;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.p;
import x6.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u000e\b\u0007\u0018\u0000 ,2\u00020\u0001:\u0001,B;\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0006\u0012\b\b\u0002\u0010\t\u001a\u00020\b\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\n¢\u0006\u0004\b\f\u0010\rB1\b\u0017\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0006\u0012\b\b\u0002\u0010\t\u001a\u00020\b¢\u0006\u0004\b\f\u0010\u000eJ\u0019\u0010\u0013\u001a\u00020\u00102\b\b\u0002\u0010\u000f\u001a\u00020\u0004H\u0000¢\u0006\u0004\b\u0011\u0010\u0012JD\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\t\u001a\u00020\b2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\nø\u0001\u0000¢\u0006\u0004\b\u0014\u0010\u0015J:\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\t\u001a\u00020\bH\u0007ø\u0001\u0000¢\u0006\u0004\b\u0017\u0010\u0018J\u001a\u0010\u001a\u001a\u00020\u00042\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001H\u0096\u0002¢\u0006\u0004\b\u001a\u0010\u001bJ\u000f\u0010\u001d\u001a\u00020\u001cH\u0016¢\u0006\u0004\b\u001d\u0010\u001eJ\u000f\u0010 \u001a\u00020\u001fH\u0016¢\u0006\u0004\b \u0010!R\u001d\u0010\u0003\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\u0003\u0010\"\u001a\u0004\b#\u0010\u001eR\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010$\u001a\u0004\b%\u0010&R\u001d\u0010\u0007\u001a\u00020\u00068\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\u0007\u0010\"\u001a\u0004\b'\u0010\u001eR\u001d\u0010\t\u001a\u00020\b8\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\t\u0010\"\u001a\u0004\b(\u0010\u001eR\u0019\u0010\u000b\u001a\u0004\u0018\u00010\n8\u0006¢\u0006\f\n\u0004\b\u000b\u0010)\u001a\u0004\b*\u0010+\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006-"}, d2 = {"Landroidx/compose/foundation/text/KeyboardOptions;", "", "Landroidx/compose/ui/text/input/KeyboardCapitalization;", "capitalization", "", "autoCorrect", "Landroidx/compose/ui/text/input/KeyboardType;", "keyboardType", "Landroidx/compose/ui/text/input/ImeAction;", "imeAction", "Landroidx/compose/ui/text/input/PlatformImeOptions;", "platformImeOptions", "<init>", "(IZIILandroidx/compose/ui/text/input/PlatformImeOptions;Lkotlin/jvm/internal/h;)V", "(IZIILkotlin/jvm/internal/h;)V", "singleLine", "Landroidx/compose/ui/text/input/ImeOptions;", "toImeOptions$foundation_release", "(Z)Landroidx/compose/ui/text/input/ImeOptions;", "toImeOptions", "copy-ij11fho", "(IZIILandroidx/compose/ui/text/input/PlatformImeOptions;)Landroidx/compose/foundation/text/KeyboardOptions;", "copy", "copy-3m2b7yw", "(IZII)Landroidx/compose/foundation/text/KeyboardOptions;", "other", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "", "toString", "()Ljava/lang/String;", "I", "getCapitalization-IUNYP9k", "Z", "getAutoCorrect", "()Z", "getKeyboardType-PjHm6EE", "getImeAction-eUduSuo", "Landroidx/compose/ui/text/input/PlatformImeOptions;", "getPlatformImeOptions", "()Landroidx/compose/ui/text/input/PlatformImeOptions;", "Companion", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class KeyboardOptions {
    public static final int $stable = 0;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final KeyboardOptions Default = new KeyboardOptions(0, false, 0, 0, null, 31, null);
    private final boolean autoCorrect;
    private final int capitalization;
    private final int imeAction;
    private final int keyboardType;
    private final PlatformImeOptions platformImeOptions;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001c\u0010\u0003\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0005\u0010\u0002\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Landroidx/compose/foundation/text/KeyboardOptions$Companion;", "", "()V", "Default", "Landroidx/compose/foundation/text/KeyboardOptions;", "getDefault$annotations", "getDefault", "()Landroidx/compose/foundation/text/KeyboardOptions;", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(h hVar) {
            this();
        }

        public static /* synthetic */ void getDefault$annotations() {
        }

        public final KeyboardOptions getDefault() {
            return KeyboardOptions.Default;
        }

        private Companion() {
        }
    }

    public /* synthetic */ KeyboardOptions(int i10, boolean z, int i11, int i12, PlatformImeOptions platformImeOptions, h hVar) {
        this(i10, z, i11, i12, platformImeOptions);
    }

    /* JADX INFO: renamed from: copy-3m2b7yw$default, reason: not valid java name */
    public static /* synthetic */ KeyboardOptions m830copy3m2b7yw$default(KeyboardOptions keyboardOptions, int i10, boolean z, int i11, int i12, int i13, Object obj) {
        if ((i13 & 1) != 0) {
            i10 = keyboardOptions.capitalization;
        }
        if ((i13 & 2) != 0) {
            z = keyboardOptions.autoCorrect;
        }
        if ((i13 & 4) != 0) {
            i11 = keyboardOptions.keyboardType;
        }
        if ((i13 & 8) != 0) {
            i12 = keyboardOptions.imeAction;
        }
        return keyboardOptions.m832copy3m2b7yw(i10, z, i11, i12);
    }

    /* JADX INFO: renamed from: copy-ij11fho$default, reason: not valid java name */
    public static /* synthetic */ KeyboardOptions m831copyij11fho$default(KeyboardOptions keyboardOptions, int i10, boolean z, int i11, int i12, PlatformImeOptions platformImeOptions, int i13, Object obj) {
        if ((i13 & 1) != 0) {
            i10 = keyboardOptions.capitalization;
        }
        if ((i13 & 2) != 0) {
            z = keyboardOptions.autoCorrect;
        }
        if ((i13 & 4) != 0) {
            i11 = keyboardOptions.keyboardType;
        }
        if ((i13 & 8) != 0) {
            i12 = keyboardOptions.imeAction;
        }
        if ((i13 & 16) != 0) {
            platformImeOptions = keyboardOptions.platformImeOptions;
        }
        PlatformImeOptions platformImeOptions2 = platformImeOptions;
        int i14 = i11;
        return keyboardOptions.m833copyij11fho(i10, z, i14, i12, platformImeOptions2);
    }

    public static /* synthetic */ ImeOptions toImeOptions$foundation_release$default(KeyboardOptions keyboardOptions, boolean z, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            z = ImeOptions.INSTANCE.getDefault().getSingleLine();
        }
        return keyboardOptions.toImeOptions$foundation_release(z);
    }

    @e
    /* JADX INFO: renamed from: copy-3m2b7yw, reason: not valid java name */
    public final /* synthetic */ KeyboardOptions m832copy3m2b7yw(int capitalization, boolean autoCorrect, int keyboardType, int imeAction) {
        return new KeyboardOptions(capitalization, autoCorrect, keyboardType, imeAction, this.platformImeOptions, (h) null);
    }

    /* JADX INFO: renamed from: copy-ij11fho, reason: not valid java name */
    public final KeyboardOptions m833copyij11fho(int capitalization, boolean autoCorrect, int keyboardType, int imeAction, PlatformImeOptions platformImeOptions) {
        return new KeyboardOptions(capitalization, autoCorrect, keyboardType, imeAction, platformImeOptions, (h) null);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof KeyboardOptions)) {
            return false;
        }
        KeyboardOptions keyboardOptions = (KeyboardOptions) other;
        return KeyboardCapitalization.m5387equalsimpl0(this.capitalization, keyboardOptions.capitalization) && this.autoCorrect == keyboardOptions.autoCorrect && KeyboardType.m5402equalsimpl0(this.keyboardType, keyboardOptions.keyboardType) && ImeAction.m5355equalsimpl0(this.imeAction, keyboardOptions.imeAction) && p.a(this.platformImeOptions, keyboardOptions.platformImeOptions);
    }

    public final boolean getAutoCorrect() {
        return this.autoCorrect;
    }

    /* JADX INFO: renamed from: getCapitalization-IUNYP9k, reason: not valid java name and from getter */
    public final int getCapitalization() {
        return this.capitalization;
    }

    /* JADX INFO: renamed from: getImeAction-eUduSuo, reason: not valid java name and from getter */
    public final int getImeAction() {
        return this.imeAction;
    }

    /* JADX INFO: renamed from: getKeyboardType-PjHm6EE, reason: not valid java name and from getter */
    public final int getKeyboardType() {
        return this.keyboardType;
    }

    public final PlatformImeOptions getPlatformImeOptions() {
        return this.platformImeOptions;
    }

    public int hashCode() {
        int iM5356hashCodeimpl = (ImeAction.m5356hashCodeimpl(this.imeAction) + ((KeyboardType.m5403hashCodeimpl(this.keyboardType) + (((KeyboardCapitalization.m5388hashCodeimpl(this.capitalization) * 31) + (this.autoCorrect ? 1231 : 1237)) * 31)) * 31)) * 31;
        PlatformImeOptions platformImeOptions = this.platformImeOptions;
        return iM5356hashCodeimpl + (platformImeOptions != null ? platformImeOptions.hashCode() : 0);
    }

    public final ImeOptions toImeOptions$foundation_release(boolean singleLine) {
        return new ImeOptions(singleLine, this.capitalization, this.autoCorrect, this.keyboardType, this.imeAction, this.platformImeOptions, (h) null);
    }

    public String toString() {
        return "KeyboardOptions(capitalization=" + ((Object) KeyboardCapitalization.m5389toStringimpl(this.capitalization)) + ", autoCorrect=" + this.autoCorrect + ", keyboardType=" + ((Object) KeyboardType.m5404toStringimpl(this.keyboardType)) + ", imeAction=" + ((Object) ImeAction.m5357toStringimpl(this.imeAction)) + ", platformImeOptions=" + this.platformImeOptions + ')';
    }

    @e
    public /* synthetic */ KeyboardOptions(int i10, boolean z, int i11, int i12, h hVar) {
        this(i10, z, i11, i12);
    }

    private KeyboardOptions(int i10, boolean z, int i11, int i12, PlatformImeOptions platformImeOptions) {
        this.capitalization = i10;
        this.autoCorrect = z;
        this.keyboardType = i11;
        this.imeAction = i12;
        this.platformImeOptions = platformImeOptions;
    }

    public /* synthetic */ KeyboardOptions(int i10, boolean z, int i11, int i12, PlatformImeOptions platformImeOptions, int i13, h hVar) {
        this((i13 & 1) != 0 ? KeyboardCapitalization.INSTANCE.m5396getNoneIUNYP9k() : i10, (i13 & 2) != 0 ? true : z, (i13 & 4) != 0 ? KeyboardType.INSTANCE.m5422getTextPjHm6EE() : i11, (i13 & 8) != 0 ? ImeAction.INSTANCE.m5367getDefaulteUduSuo() : i12, (i13 & 16) != 0 ? null : platformImeOptions, (h) null);
    }

    public /* synthetic */ KeyboardOptions(int i10, boolean z, int i11, int i12, int i13, h hVar) {
        this((i13 & 1) != 0 ? KeyboardCapitalization.INSTANCE.m5396getNoneIUNYP9k() : i10, (i13 & 2) != 0 ? true : z, (i13 & 4) != 0 ? KeyboardType.INSTANCE.m5422getTextPjHm6EE() : i11, (i13 & 8) != 0 ? ImeAction.INSTANCE.m5367getDefaulteUduSuo() : i12, (h) null);
    }

    private KeyboardOptions(int i10, boolean z, int i11, int i12) {
        this(i10, z, i11, i12, (PlatformImeOptions) null, (h) null);
    }
}
