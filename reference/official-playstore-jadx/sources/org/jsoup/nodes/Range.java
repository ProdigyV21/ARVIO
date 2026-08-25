package org.jsoup.nodes;

import org.jsoup.internal.SharedConstants;

/* JADX INFO: loaded from: classes5.dex */
public class Range {
    static final Range Untracked;
    private static final Position UntrackedPos;
    private final Position end;
    private final Position start;

    public static class AttributeRange {
        static final AttributeRange UntrackedAttr;
        private final Range nameRange;
        private final Range valueRange;

        static {
            Range range = Range.Untracked;
            UntrackedAttr = new AttributeRange(range, range);
        }

        public AttributeRange(Range range, Range range2) {
            this.nameRange = range;
            this.valueRange = range2;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            AttributeRange attributeRange = (AttributeRange) obj;
            if (this.nameRange.equals(attributeRange.nameRange)) {
                return this.valueRange.equals(attributeRange.valueRange);
            }
            return false;
        }

        public int hashCode() {
            return this.valueRange.hashCode() + (this.nameRange.hashCode() * 31);
        }

        public Range nameRange() {
            return this.nameRange;
        }

        public String toString() {
            return nameRange().toString() + "=" + valueRange().toString();
        }

        public Range valueRange() {
            return this.valueRange;
        }
    }

    public static class Position {
        private final int columnNumber;
        private final int lineNumber;
        private final int pos;

        public Position(int i10, int i11, int i12) {
            this.pos = i10;
            this.lineNumber = i11;
            this.columnNumber = i12;
        }

        public int columnNumber() {
            return this.columnNumber;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj != null && getClass() == obj.getClass()) {
                Position position = (Position) obj;
                if (this.pos == position.pos && this.lineNumber == position.lineNumber && this.columnNumber == position.columnNumber) {
                    return true;
                }
            }
            return false;
        }

        public int hashCode() {
            return (((this.pos * 31) + this.lineNumber) * 31) + this.columnNumber;
        }

        public boolean isTracked() {
            return this != Range.UntrackedPos;
        }

        public int lineNumber() {
            return this.lineNumber;
        }

        public int pos() {
            return this.pos;
        }

        public String toString() {
            return this.lineNumber + "," + this.columnNumber + ":" + this.pos;
        }
    }

    static {
        Position position = new Position(-1, -1, -1);
        UntrackedPos = position;
        Untracked = new Range(position, position);
    }

    public Range(Position position, Position position2) {
        this.start = position;
        this.end = position2;
    }

    public static Range of(Node node, boolean z) {
        String str = z ? SharedConstants.RangeKey : SharedConstants.EndRangeKey;
        if (!node.hasAttributes()) {
            return Untracked;
        }
        Object objUserData = node.attributes().userData(str);
        return objUserData != null ? (Range) objUserData : Untracked;
    }

    public Position end() {
        return this.end;
    }

    public int endPos() {
        return this.end.pos;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Range range = (Range) obj;
        if (this.start.equals(range.start)) {
            return this.end.equals(range.end);
        }
        return false;
    }

    public int hashCode() {
        return this.end.hashCode() + (this.start.hashCode() * 31);
    }

    public boolean isImplicit() {
        if (isTracked()) {
            return this.start.equals(this.end);
        }
        return false;
    }

    public boolean isTracked() {
        return this != Untracked;
    }

    public Position start() {
        return this.start;
    }

    public int startPos() {
        return this.start.pos;
    }

    public String toString() {
        return this.start + "-" + this.end;
    }

    @Deprecated
    public void track(Node node, boolean z) {
    }
}
