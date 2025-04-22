class Tamagotchi { //all the info for the pet
    private String name;
    private String pronouns;
    private int health;
    private int happiness;
    private int hunger;
    private int energy;
    private boolean isSick;
    private String favoriteFood;
    private String personality;
    private boolean hasVisitorToday;

    private int daysIgnored; // tracks if the pet is being ignored

    //private boolean canGoOnDate;
    private static final int MAX_VALUE = 100; //makes sure stats max out at 100%
 

    public Tamagotchi(String name, String pronouns) {
        this.name = name;
        this.pronouns = pronouns;
        this.health = 50;
        this.happiness = 50;
        this.hunger = 50;
        this.energy = 50; 
        this.isSick = false;
        this.personality = assignRandomPersonality();
        this.daysIgnored = 0;
        this.favoriteFood = getRandomFavoriteFood();
    }
    public int getDaysIgnored() {
        return daysIgnored;
    }


    public void startDayCycle() {
        System.out.println("\n🌅 A new day begins for " + name + "!");
        triggerDailyRandoms();
        resetIgnoredDays();
        changeHunger((int)(Math.random() * 6 - 3));   // ±3
        changeHealth((int)(Math.random() * 6 - 3));
        changeHappiness((int)(Math.random() * 6 - 3));

        System.out.println(getStatus());
    }
    public void endOfDaySummary() {
        System.out.println("\n🌙 End of day summary for " + name + ":");
        System.out.println(getStatus());

        if (happiness >= 70 && health >= 70 && hunger >= 70) {
            System.out.println(name + " sleeps peacefully 😴");
        } else {
            System.out.println(name + " is restless... Health may decline overnight.");
            changeHealth(-5);
        }
    }

    public int getEnergy() {
        return energy;
    }

    public boolean hasVisitor() {
        return hasVisitorToday;
    }

    public void changeEnergy(int amount) {
        energy = Math.max(0, Math.min(energy + amount, MAX_VALUE));
        if (energy < 10) {
            System.out.println(name + " is too tired to do anything.");
        }
    }

    private String getRandomFavoriteFood() {
        String[] options = {"apple", "vegetables", "water", "soda", "candy", "junk food"};
        int index = (int) (Math.random() * options.length);
        return options[index];
    }

    public String getFavoriteFood() {
        return favoriteFood;
    }
    public void giveMedicine() {
        if (isSick) {
            isSick = false;
            changeHealth(+10);
            changeHappiness(-5);
            System.out.println(name + " took medicine and is now cured! (but a bit unhappy)");
        } else {
            System.out.println(name + " is not sick and doesn't need medicine.");
        }
    }

    public void triggerDailyRandoms() {

        // Surprise sickness (neglected pets may get sick)
        if ((happiness < 30 || hunger < 30 || health < 30) && Math.random() < 0.2) {
            isSick = true;
            System.out.println(name + " seems a bit off... maybe sick?");
        }
    }
    public void changeHealth(int amount) { //every turn can/will have an effect on health 
        health = Math.min(health + amount, MAX_VALUE);
        if (health < 45) {
            System.out.println("Warning: " + name + "'s health is low (" + health + ")!");
        }
        checkSickness(); //checks to see if the pet is sick or dead
        checkGameOver(); //ends game if dead
    }
    public void changeHappiness(int amount) { //turns change happiness 
        happiness = Math.min(happiness + amount, MAX_VALUE);
        if (happiness < 45) {
            System.out.println("Warning: " + name + "'s happiness is low (" + happiness + ")!");
        }
        checkSickness(); //sees if pet is sick or dead
        checkGameOver(); //ends game if dead 
    }
    public void changeHunger(int amount) {
        if (hunger >= 100 && amount > 0) {
            System.out.println(name + " is too full and refuses to eat!");
            return;
        }
        hunger = Math.min(hunger + amount, MAX_VALUE);
        if (hunger < 45) {
            System.out.println("Warning: " + name + "'s hunger is low (" + hunger + ")!");
        }
        checkSickness();
        checkGameOver();
    }
    private void checkSickness() { //checks health, happiness, and hunger to see if per is sick
        boolean wasSick = isSick; 
        isSick = (health < 40 || happiness < 40 || hunger < 40); //stats need to be below 40 to be sick
        if (isSick && !wasSick) { 
            System.out.println(name + " is sick! Take better care of your pet."); //tells player pet is sick
        }
    }
    private void checkGameOver() { //if the pet is dead
        if (health <= 0 || happiness <= 0 || hunger <= 0) { //stats need to be at 0 for pet to be dead
            System.out.println(name + " has died. Game Over."); //tells you if the pets dead
            UI.promptPlayAgain(); //gives you an option to play again
        }
    }
    public boolean canGoOnDate() { //checks to see if the pet can go on date
        return happiness >= 100 && health >= 100 && hunger >= 100; //will be able to if stats are all at 100
    }
    public boolean isAlive() { //checks if the pets alive
        return health > 0 && happiness > 0 && hunger > 0; //stats just need to be above 0
    }
    public String getStatus() { //after every turn youll see your pets stats 
        String status = name + " (" + pronouns + ") - Health: " + health + ", Happiness: " + happiness + ", Hunger: " + hunger;
        if (isSick) status += " (Sick)";
        if (canGoOnDate()) status += " Ready for a date!";
        return status;
    }
    public String getName() {
        return name; //for when you pick your pets name
    }
    
    public int getHealth() {
        return health;
    }

    public int getHappiness() {
        return happiness;
    }

    public int getHunger() {
        return hunger;
    }

    public boolean isSick() {
        return isSick;
    }
    private String assignRandomPersonality() {
        String[] types = {"playful", "lazy", "grumpy"};
        return types[(int)(Math.random() * types.length)];
    }

    public String getPersonality() {
        return personality;
    }

    public void incrementIgnoredDays() {
        daysIgnored++;
        if (personality.equals("playful") && daysIgnored >= 2) {
            changeHappiness(-10);
            System.out.println(name + " feels sad from being ignored.");
        }
    }

    public void resetIgnoredDays() {
        daysIgnored = 0;
    }

}

