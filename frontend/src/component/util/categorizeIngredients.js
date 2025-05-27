export const categorizeIngredients = (ingredients=[]) => {
    if (!Array.isArray(ingredients)) return {};
    return ingredients.reduce((acc, ingredient) => {
        const categoryName = ingredient.category?.nom || "Autre"; // ✅ safe fallback

        if (!acc[categoryName]) {
            acc[categoryName] = [];
        }

        acc[categoryName].push(ingredient);
        return acc;
    }, {});
};
