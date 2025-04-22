import java.util.Scanner; 


class UI {
    public void showStartScreen() {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Welcome to LittleLife!"); //print statement to start the game 

        while (true) {
            String pronouns; //user inputs pronouns 
            while (true) {
                System.out.println("Choose your pet's pronouns: (he/him, they/them, she/her)");
                pronouns = scanner.nextLine().trim().toLowerCase(); //user writes the pets pronouns 
                if (pronouns.equals("he/him") || pronouns.equals("they/them") || pronouns.equals("she/her")) {
                    break;
                }
                System.out.println("Invalid input. Please enter one of the given options."); //if you type something else 
            }

            String petName; //name 
            while (true) {
                System.out.println("Enter your pet's name (Max 15 characters, no numbers or symbols):");
                petName = scanner.nextLine().trim(); //user inputs the pets name 
                if (petName.length() > 0 && petName.length() <= 15) {
                //if (petName.matches("[a-zA-Z ]{1,15}")) {    
                    break;
                }
                System.out.println("Invalid name. Use only letters (A-Z) and spaces, and keep it under 15 characters."); //if the user inputs something else 
            }

            Tamagotchi pet = new Tamagotchi(petName, pronouns); //adds the name and pronouns to the tamagotchi class 
            Player player = new Player("Player1"); //new player starts the game 
            Logic gameLogic = new Logic(pet, player); //new logic starts the logic class 

            while (true) { //while the game is still going 
                System.out.println("\n" + pet.getStatus()); //gets your pets health, happiness, and hunger stats 

                if (!pet.isAlive()) { //checks to see if your pet is dead 
                    System.out.println("Oh no! Your Tamagotchi has died!");
                    break; //ends the game if the pets dead 
                }

                if (pet.canGoOnDate()) { //checks if pet can go on date 
                    System.out.println("Congratulations! Your Tamagotchi is ready for a date!");
                    return; //returns to game
                }

                System.out.println("What do you want to do? (feed/play/exit)"); //gives you your options 
                String category = scanner.nextLine().trim().toLowerCase();

                if (category.equals("exit")) { //if you choose to leave the game 
                    System.out.println("Exiting game. Thanks for playing!");
                    return;
                }

                if (category.equals("feed")) { //feeding option 
                    System.out.println("You can feed multiple times. Type 'done' to stop.");
                    while (true) { //while you want to feed the pet
                        System.out.println("Choose food: (apple, soda, beer, vegetables, candy, water) or type 'done' to stop.");
                        String foodChoice = scanner.nextLine().trim().toLowerCase(); //user types what they want to pick
                        if (foodChoice.equals("done")) break; //if they dont want to feed anymore, it goes back to the start 

                        gameLogic.makeChoice("feed", foodChoice); 
                        System.out.println("\n" + pet.getStatus()); //gets stats before running again 

                        if (pet.canGoOnDate()) {
                            System.out.println("Congratulations! Your Tamagotchi is ready for a date!"); //changing this code anyway
                            System.out.println("The dating section will be coming soon in the final term."); //basically saying that you can go on date 
                            return;
                        }
                    }
                } else if (category.equals("play")) { //if you pick play 
                    System.out.println("You can play multiple times. Type 'done' to stop.");
                    while (true) { //while youre still playing 
                        System.out.println("Choose activity: (fetch, walk, watch movie, play video game) or type 'done' to stop.");
                        String activityChoice = scanner.nextLine().trim().toLowerCase(); //user inputs what they want to play 
                        if (activityChoice.equals("done")) break; //ends this choice if player picks done 

                        gameLogic.makeChoice("play", activityChoice);
                        System.out.println("\n" + pet.getStatus()); //gets stats to see whats changed 

                        if (pet.canGoOnDate()) { //again, planning on changing this code, but its the same thing as before 
                            System.out.println("Congratulations! Your Tamagotchi is ready for a date!");
                            System.out.println("The dating section will be coming soon in the final term.");
                            return;
                        }

                    }
                } else {
                    System.out.println("Invalid choice. Please enter 'feed', 'play', or 'exit'."); //default for if you pick something that's not on the list, pick again
                }
            }
        }

    }

    public static void promptPlayAgain() { //after you kill pet it will give you the option to play again 
        Scanner scanner = new Scanner(System.in);
        System.out.println("Would you like to play again? (yes/no)");
        String choice = scanner.nextLine().trim().toLowerCase(); //user picks yes or no 
        if (choice.equals("yes")) { 
            new UI().showStartScreen(); //starts a new screen/game if you pick yes 
        } else {
            System.out.println("Thanks for playing! Goodbye."); //otherwise, the game fully ends 
            System.exit(0);
        }
    }
}