import java.util.Scanner;

public class Logic { //actual game play / what the user sees on the screen 
	private Tamagotchi pet; //calls tamagotchi class
	private Player player; //calls player class 
	
	public Logic(Tamagotchi pet, Player player) { //makes everything local to this class 
		this.pet= pet; 
		this.player = player; 
    }

	public void startDay() {
	    pet.triggerDailyRandoms();

	    if (pet.hasVisitor()) {
	        System.out.println("👥 A friend's pet is visiting! You can choose to let them play together.");
	    }
	}
	
	public void runDailyRoutine() {
	    pet.startDayCycle();
	    Scanner scanner = new Scanner(System.in);
	    while (true) {
	        System.out.println("\nWhat would you like to do? (feed/play/medicine/skip)");
	        String action = scanner.nextLine().trim().toLowerCase();

	        if (action.equals("skip")) {
	            pet.incrementIgnoredDays();
	            break;
	        }

	        if (action.equals("feed")) {
	            System.out.println("Enter food (apple, soda, candy, water, junk food, etc.):");
	            String food = scanner.nextLine().trim().toLowerCase();
	            player.feed(pet, food);
	        } else if (action.equals("play")) {
	            System.out.println("Enter activity (walk, fetch, watch movie, play video game):");
	            String activity = scanner.nextLine().trim().toLowerCase();
	            player.play(pet, activity);
	        } else if (action.equals("medicine")) {
	            player.giveMedicine(pet);
	        }  else {
	            System.out.println("Invalid choice.");
	        }

	        if (!pet.isAlive()) return;
	        if (pet.canGoOnDate()) break;
	    }
	    pet.endOfDaySummary();
	}


	public void makeChoice(String category, String choice) {
	    switch (category.toLowerCase()) {
	        case "feed":
	            player.feed(pet, choice);
	            break;
	        case "play":
	            player.play(pet, choice);
	            break;
	        case "date":
	            player.date(pet, category, choice);
	            break;
	        case "medicine":
	            player.giveMedicine(pet);
	            break;
	        default:
	            System.out.println("Invalid category");
	    }

	    if (!pet.isAlive()) {
	        System.out.println(pet.getStatus());
	        System.out.println("Oh no! " + pet.getName() + " has died. Game over!!!");
	        System.exit(0);
	    }

	    if (pet.canGoOnDate()) {
	        System.out.println("Congratulations! " + pet.getName() + " is ready to go on a date! ");
	    }
	}

}
