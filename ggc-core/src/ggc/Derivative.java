package ggc;

import java.util.*;

/**
 * This class is used to represent a derivative product. A derivative product 
 * has an ID, an aggravation value and a recipe.
 */
public class Derivative extends Product {
    /* Serial number for serialization. */
    private static final long serialVersionUID = 202109192006L;

    /* Derivative product's aggravation value. */
    private double _aggravation;

    /* Derivative product's recipe (as a string). */
    private String _recipeString;

    /* Derivative product's recipe (as a TreeMap). */
    private Map<String, Integer> _recipe = new TreeMap<String, Integer>();

    /**
     * Create a derivative product.
     * @param id product's id.
     * @param aggravation product's aggravation value.
     * @param recipe product's recipe (as a string and treemap).
     */
    public Derivative(String id, double aggravation, String recipe) {
        super(id);
        _aggravation = aggravation;
        _recipeString = recipe;

        /* transform the recipe string into a treemap of key:value pair (key=product ID, value=quantity) */
        String[] array = recipe.split("#"); 
        for (String pair : array) {
            String[] args = pair.split(":");
            _recipe.put(args[0], Integer.parseInt(args[1]));
        }
    }

    /**
     * @return the product's type.
     */
    @Override
    public String getType() {
        return "DERIVATIVE";
    }

    /**
     * @return the product's aggravation value.
     */
    @Override
    public double getAggravation() {
        return _aggravation;
    }
    
     /**
     * @return the drivative product's period variable.
     */
    public int getN() {
        return 3;
    }

    /**
     * @return the product's recipe.
     */
    @Override
    public String getRecipeString() {
        return _recipeString;
    }

}
