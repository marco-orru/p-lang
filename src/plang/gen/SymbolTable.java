package plang.gen;

import java.util.HashMap;
import java.util.Map;

/**
 * Implements a symbol table for mapping variable names to specific indexes.
 */
public final class SymbolTable {
    /**
     * The index map
     */
    private final Map<String, Integer> indexMap = new HashMap<>();
    /**
     * The number of variables in the symbol table
     */
    private int count;

    /**
     * Adds a variable name to the symbol table.
     *
     * @param varName The name of the variable.
     * @param index   The index of the variable.
     * @throws IllegalArgumentException If the variable has been already declared.
     */
    public void add(String varName, int index) {
        if (!indexMap.containsKey(varName))
            indexMap.put(varName, index);
        else
            throw new IllegalArgumentException("The variable " + varName + " has already been declared");
    }

    /**
     * Lookups the index of the specified variable name
     *
     * @param varName The name of the variable.
     * @return The index of the variable.
     */
    public int lookup(String varName) {
        if (indexMap.containsKey(varName))
            return indexMap.get(varName);

        throw new IllegalArgumentException("The variable " + varName + " has not been declared");
    }

    /**
     * Lookups the index of the specified variable name, adding a new variable if the index is not found.
     *
     * @param varName The name of the variable.
     * @return The index of the variable.
     */
    public int get(String varName) {
        if (indexMap.containsKey(varName))
            return indexMap.get(varName);

        add(varName, count);
        return count++;
    }
}
