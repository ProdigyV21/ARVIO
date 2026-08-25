package androidx.compose.foundation.text2.input;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\f\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0082\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\u0018\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u000eH\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0014"}, d2 = {"Landroidx/compose/foundation/text2/input/MaskCodepointTransformation;", "Landroidx/compose/foundation/text2/input/CodepointTransformation;", "character", "", "(C)V", "getCharacter", "()C", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "transform", "codepointIndex", "codepoint", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final /* data */ class MaskCodepointTransformation implements CodepointTransformation {
    private final char character;

    public MaskCodepointTransformation(char c10) {
        this.character = c10;
    }

    public static /* synthetic */ MaskCodepointTransformation copy$default(MaskCodepointTransformation maskCodepointTransformation, char c10, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            c10 = maskCodepointTransformation.character;
        }
        return maskCodepointTransformation.copy(c10);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final char getCharacter() {
        return this.character;
    }

    public final MaskCodepointTransformation copy(char character) {
        return new MaskCodepointTransformation(character);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof MaskCodepointTransformation) && this.character == ((MaskCodepointTransformation) other).character;
    }

    public final char getCharacter() {
        return this.character;
    }

    public int hashCode() {
        return this.character;
    }

    public String toString() {
        return "MaskCodepointTransformation(character=" + this.character + ')';
    }

    @Override // androidx.compose.foundation.text2.input.CodepointTransformation
    public int transform(int codepointIndex, int codepoint) {
        return this.character;
    }
}
