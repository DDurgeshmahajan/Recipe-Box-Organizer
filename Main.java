/*
Author: Durgesh Mahajan
Date: 2023-10-02
Project: Recipe Box Organizer
*/

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        RecipeManager recipeManager = new RecipeManager();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            printMenu();
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    addNewRecipe(scanner, recipeManager);
                    break;
                case "2":
                    viewAllRecipes(recipeManager);
                    break;
                case "3":
                    searchRecipesByName(scanner, recipeManager);
                    break;
                case "4":
                    searchRecipesByIngredient(scanner, recipeManager);
                    break;
                case "5":
                    deleteRecipe(scanner, recipeManager);
                    break;
                case "6":
                    System.out.println("Exiting the program. Goodbye!\
");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.\
");
                    break;
            }
        }

        scanner.close();
    }

    private static void printMenu() {
        System.out.println("Recipe Box Organizer");
        System.out.println("1. Add a new recipe");
        System.out.println("2. View all recipes");
        System.out.println("3. Search recipes by name");
        System.out.println("4. Search recipes by ingredient");
        System.out.println("5. Delete a recipe");
        System.out.println("6. Exit");
        System.out.print("Choose an option: ");
    }

    private static void addNewRecipe(Scanner scanner, RecipeManager recipeManager) {
        System.out.print("Enter the recipe name: ");
        String name = scanner.nextLine().trim();
        if (name.isEmpty()) {
            System.out.println("Recipe name cannot be empty.\
");
            return;
        }

        System.out.print("Enter the ingredients (comma-separated): ");
        String ingredientsStr = scanner.nextLine().trim();
        String[] ingredientsArray = ingredientsStr.split(",");
        java.util.List<String> ingredients = java.util.Arrays.asList(ingredientsArray);

        System.out.print("Enter the instructions: ");
        String instructions = scanner.nextLine().trim();

        System.out.print("Enter the category: ");
        String category = scanner.nextLine().trim();

        Recipe recipe = new Recipe(name, ingredients, instructions, category);
        recipeManager.addRecipe(recipe);
        System.out.println("Recipe added successfully.\
");
    }

    private static void viewAllRecipes(RecipeManager recipeManager) {
        java.util.List<Recipe> recipes = recipeManager.getAllRecipes();
        if (recipes.isEmpty()) {
            System.out.println("No recipes found.\
");
            return;
        }

        System.out.println("All Recipes:");
        for (Recipe recipe : recipes) {
            System.out.println(recipe);
        }
        System.out.println();
    }

    private static void searchRecipesByName(Scanner scanner, RecipeManager recipeManager) {
        System.out.print("Enter the recipe name to search: ");
        String name = scanner.nextLine().trim();

        java.util.List<Recipe> recipes = recipeManager.searchRecipesByName(name);
        if (recipes.isEmpty()) {
            System.out.println("No recipes found with the name '" + name + "'.\
");
            return;
        }

        System.out.println("Recipes found:");
        for (Recipe recipe : recipes) {
            System.out.println(recipe);
        }
        System.out.println();
    }

    private static void searchRecipesByIngredient(Scanner scanner, RecipeManager recipeManager) {
        System.out.print("Enter the ingredient to search: ");
        String ingredient = scanner.nextLine().trim();

        java.util.List<Recipe> recipes = recipeManager.searchRecipesByIngredient(ingredient);
        if (recipes.isEmpty()) {
            System.out.println("No recipes found with the ingredient '" + ingredient + "'.\
");
            return;
        }

        System.out.println("Recipes found:");
        for (Recipe recipe : recipes) {
            System.out.println(recipe);
        }
        System.out.println();
    }

    private static void deleteRecipe(Scanner scanner, RecipeManager recipeManager) {
        System.out.print("Enter the recipe name to delete: ");
        String name = scanner.nextLine().trim();

        boolean success = recipeManager.deleteRecipe(name);
        if (success) {
            System.out.println("Recipe deleted successfully.\
");
        } else {
            System.out.println("No recipe found with the name '" + name + "'.\
");
        }
    }
}