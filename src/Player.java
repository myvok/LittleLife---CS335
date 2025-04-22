import java.util.Scanner;

class Player { //all of the players decision options
    private String name;
    
    public Player(String name) { //gets the pets name
        this.name = name;
    }
    public String getName() {
        return name; //returns name
    }
    public void feed(Tamagotchi pet, String food) {
        if (pet.isSick()) {
            System.out.println(pet.getName() + " is sick and refuses to eat!");
            return;
        }

        switch (food.toLowerCase()) {
            case "apple":
            case "vegetables":
            case "water":
                pet.changeHunger(15);
                pet.changeHealth(15);
                break;
            case "soda":
            case "candy":
                pet.changeHunger(10);
                pet.changeHealth(-10);
                break;
            case "beer":
            case "junk food":
                pet.changeHappiness(5);
                pet.changeHunger(5);
                pet.changeHealth(-5);
                break;
            default:
                System.out.println("Invalid food choice.");
                return;
        }

        if (food.equalsIgnoreCase(pet.getFavoriteFood())) {
            pet.changeHappiness(10);
            System.out.println("It's " + pet.getName() + "'s favorite food! Happiness increased!");
        } else {
            System.out.println("You fed " + pet.getName() + " some " + food + ".");
        }
    }
    public void giveMedicine(Tamagotchi pet) {
        pet.giveMedicine();
    }


    public void play(Tamagotchi pet, String activity) {
        if (pet.isSick()) {
            System.out.println(pet.getName() + " is sick and refuses to play! You need to get some medicine!");
            return;
        }

        if (pet.getEnergy() <= 10) {
            System.out.println(pet.getName() + " is too tired to play!");
            return;
        }

        // Lazy pets dislike lots of activity
        if (pet.getPersonality().equals("lazy") && activity.equals("walk")) {
            System.out.println(pet.getName() + " is lazy and doesn't like long walks.");
            pet.changeHappiness(-5);
        }

        int baseHappiness = 0;
        int baseEnergy = 0;

        switch (activity.toLowerCase()) {
            case "fetch":
            case "walk":
                baseHappiness = 15;
                baseEnergy = -20;
                pet.changeHealth(15);
                pet.changeHunger(-5);
                break;
            case "watch movie":
            case "play video game":
                baseHappiness = 10;
                baseEnergy = -10;
                pet.changeHealth(-5);
                break;
            default:
                System.out.println("Invalid activity choice.");
                return;
        }
        

        // Surprise gift
        if (Math.random() < 0.2) {
            String gift = getRandomGift(pet.getPersonality());
            System.out.println("🎁 Surprise! " + pet.getName() + " found a gift: " + gift + "!");
            pet.changeHappiness(10);
        }


        // Grumpy pets gain extra if you actually please them
        if (pet.getPersonality().equals("grumpy") && baseHappiness > 10) {
            baseHappiness += 5;
            System.out.println(pet.getName() + " (grumpy) surprisingly had a great time!");
        }

        pet.changeHappiness(baseHappiness);
        pet.changeEnergy(baseEnergy);
        System.out.println("You played " + activity + " with " + pet.getName() + "!");

        pet.resetIgnoredDays(); 
    }

    private String getRandomGift(String personality) {
        switch (personality) {
            case "playful": return "squeaky toy";
            case "lazy": return "comfy pillow";
            case "grumpy": return "rare snack";
            default: return "treat";
        }
    }

    public void date(Tamagotchi pet, String dates, String gift){ //player picking date when it's available 
        switch (dates.toLowerCase()) {
            case "movie date": //casual date options 
            case "coffee date":
                pet.changeHappiness(5); //+5 happiness
                pet.changeHealth(05); //+5 health 
                System.out.println( pet.getName() + " is so cool!");
                switch (gift.toLowerCase()) {
                    case "flower": //gift options
                    case "chocolates":
                        pet.changeHappiness(5);
                        System.out.println( pet.getName() + "'s date loved the gift.");
                        break;
            case "dinner date": //fancier options
            case "grab drinks":
                pet.changeHappiness(10); //+10 happiness 
                pet.changeHealth(5); //+5 health 
                System.out.println( pet.getName() + "is lovely <3");
                switch (gift.toLowerCase()) {
                    case "flower": //gift options
                    case "chocolates":
                        pet.changeHappiness(5);
                        System.out.println( pet.getName() + "'s date loved the gift.");
                        break;
            
            default:
                System.out.println("Invalid date choice"); //if you choose anything else 
        }
    
    }
    }
}
}
