package h08.tutor;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * class for h08.
 */
public abstract class AbstractExpression implements Comparable<AbstractExpression> {

    public final int startIndex;

    public AbstractExpression(int startIndex) {
        this.startIndex = startIndex;
    }

    public abstract List<Character> getCharacterList();

    public char[] characters() {
        return getCharacterList().stream().map(String::valueOf).collect(Collectors.joining()).toCharArray();
    }

    public abstract Stream<AbstractExpression> stream();

    public int length() {
        return getCharacterList().size();
    }

    public int startIndex() {
        return startIndex;
    }

    public abstract int getValue();

    public abstract boolean isValidIndex(int index);

    public int nextIndex() {
        return startIndex() + length();
    }

    @Override
    public int compareTo(AbstractExpression o) {
        return length() - o.length();
    }

    @Override
    public String toString() {
        return Arrays.toString(characters());
    }
}
