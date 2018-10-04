import tester.*;

// represents all CakeRecipes
class CakeRecipe {
  double flour;
  double sugar;
  double eggs;
  double butter;
  double milk; // weights in ounces

  // constructor that represents as the amount of flour, sugar, eggs, butter and
  // milk in the recipe
  CakeRecipe(double flour, double sugar, double eggs, double butter, double milk) {
    if (Math.abs(flour - sugar) > 0.001) {
      throw new IllegalArgumentException("Weight of flour must be equal to weight of sugar.");
    }
    if (Math.abs(eggs - butter) > 0.001) {
      throw new IllegalArgumentException("Weight of eggs must be equal to weight of butter.");
    }
    if (Math.abs(eggs + milk - sugar) > 0.001) {
      throw new IllegalArgumentException(
          "Weight of eggs plus the weight of milk must equal the weight of sugar.");
    }
    this.flour = flour;
    this.sugar = sugar;
    this.eggs = eggs;
    this.butter = butter;
    this.milk = milk;
  }

  // constructor that only requires one to enter the amount of flour, eggs and
  // milk
  CakeRecipe(double flour, double eggs, double milk) {
    this(flour, flour, eggs, eggs, milk);
  }

  // constructor that takes in the flour, eggs and milk
  // as volumes rather than weights and a boolean flag areVolumes
  CakeRecipe(double flour, double eggs, double milk, boolean areVolumes) {
    this(flour, eggs, milk);

    if (areVolumes) {
      this.flour = flour * 4.25;
      this.sugar = flour * 7;
      this.eggs = eggs * 1.75;
      this.butter = eggs * 8;
      this.milk = milk * 8;
    }
  }

  // detects if the two CakeRecipes are the same
  boolean sameRecipe(CakeRecipe other) {
    return Math.abs(this.flour - other.flour) <= .001 && Math.abs(this.sugar - other.sugar) <= .001
        && Math.abs(this.eggs - other.eggs) <= .001 && Math.abs(this.butter - other.butter) <= .001
        && Math.abs(this.milk - other.milk) <= .001;
  }
}

// represents all types of CakeRecipes
class ExamplesCakeRecipe {
  CakeRecipe redVelvet = new CakeRecipe(10, 10, 5, 5, 5);
  CakeRecipe redVelvetChocoChip = new CakeRecipe(10, 10, 5, 5, 5);
  CakeRecipe chocolate = new CakeRecipe(22, 22, 11, 11, 11);
  CakeRecipe vanilla = new CakeRecipe(3, 3, 1, 1, 2);
  CakeRecipe confetti = new CakeRecipe(5, 4, 1, true);
  CakeRecipe candyCane = new CakeRecipe(5, 4, 1, true);
  CakeRecipe lemon = new CakeRecipe(12, 5, 7, false);
  CakeRecipe chocolateAndVanilla = new CakeRecipe(17, 8, 9);
  CakeRecipe blueberry = new CakeRecipe(17, 8, 9);
  CakeRecipe strawberryShortcake = new CakeRecipe(66, 12, 54);

  // testing sameRecipe
  boolean testSameRecipe(Tester t) {
    return t.checkExpect(this.redVelvet.sameRecipe(this.chocolate), false)
        && t.checkExpect(this.redVelvet.sameRecipe(this.redVelvetChocoChip), true)
        && t.checkExpect(this.chocolate.sameRecipe(this.vanilla), false)
        && t.checkExpect(this.confetti.sameRecipe(this.candyCane), true)
        && t.checkExpect(this.candyCane.sameRecipe(this.lemon), false)
        && t.checkExpect(this.blueberry.sameRecipe(this.chocolateAndVanilla), true)
        && t.checkExpect(this.chocolateAndVanilla.sameRecipe(this.strawberryShortcake), false);
  }
}