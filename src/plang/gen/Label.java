package plang.gen;

/**
 * Represents a label.
 */
public final class Label implements CodeGenEntity {
    /**
     * The prefix of a label name
     */
    public static final String LABEL_PREFIX = "LB_";
    /**
     * The global identifier of the last emitted label
     */
    private static int globalIndex;
    /**
     * The identifier of the label.
     */
    private final int id;

    /**
     * Initializes a new {@link Label} with an auto index.
     */
    public Label() {
        id = globalIndex++;
    }

    /**
     * Gets the id of the label.
     *
     * @return The id of the label.
     */
    public int getId() {
        return this.id;
    }

    @Override
    public String toString() {
        return LABEL_PREFIX + id + ":";
    }
}
