import java.io.*;
import java.util.*;

public class PokemonArena{
	public static ArrayList<Pokemon> PokemonArray = new ArrayList<Pokemon>();
	public static ArrayList<Pokemon> pokemonTeam = new ArrayList<Pokemon>();
	public static Scanner kb = new Scanner(System.in);
	public static Pokemon user;
	public static Pokemon enemy;	
	public static String name;
	public static void main(String [] args) throws IOException{
		
		int answer = 0;
		System.out.println("WELCOME TO POKEMON ARENA!!!");
		System.out.println("\n" + "Please enter your name: ");
		name = kb.nextLine().toUpperCase();
		System.out.println("Hello there, " + name + " are you ready to play pokemon Arena?");
		System.out.println("1.YES" + "\n" + "2.NO");
		answer = kb.nextInt();
		
		if(answer == 1){
			System.out.println("Great, let's begin, " + name + "\n");
			load();
			pickpokemon();
			System.out.println("Are you ready to battle? " + "\n" + "1. YES" + "\n" + "2. NO");
			answer = kb.nextInt();
			if(answer == 1){
				chooseUser();
				playgame();
			}
			else{
				System.out.println("Good bye");
			}
		}
		else if(answer == 2){
			System.out.println("Okay then, goodbye!");
		}
	}

	public static void pickpokemon(){
		int num = 0;
		for(int i = 0; i < PokemonArray.size(); i++){
			System.out.printf("%3d. %s" + "\n",i+1,PokemonArray.get(i));
		}
		
		while(pokemonTeam.size() <4){
			System.out.println("\n" + "Pick a pokemon: ");
			int picked = kb.nextInt();
			if(picked > 1 && picked <= 28){
				System.out.println(PokemonArray.get(picked-1));
				pokemonTeam.add(PokemonArray.get(picked-1));
				PokemonArray.remove(PokemonArray.get(picked));
				for(int i = 0; i < PokemonArray.size(); i++){
					System.out.printf("%3d. %s" + "\n",i+1,PokemonArray.get(i));
				}
				num += 1;
			}
			else if(num == 4){
				break;
			}
			else{
				System.out.println("Please pick a valid choice" );
			}
			
		}
		System.out.println("Your team is" + pokemonTeam);
	}
	public static void chooseUser(){
		if(pokemonTeam.size() > 0){
			System.out.println("Choose a pokemon to fight with: ");
			for (int i = 0; i < pokemonTeam.size(); i++){
		    	System.out.println(i+1 + "." + pokemonTeam.get(i));
	    	}
	    	while (pokemonTeam.size() != 0){ // keeps asking until user gives possible answer
		    	int choice = kb.nextInt();
		    	if (choice > 1 && choice < pokemonTeam.size()){
		    		user = pokemonTeam.get(choice); // takes chosen Pokemon from remaining Pokemon to choose from, and it becomes the user
		    		System.out.println(user.name + "! I choose you!");
		    		break;
		    	}
		    	else{
		    		System.out.println("Invalid entry, pick another Pokemon:");
		    	}
	    	}
		}
	}
	
	
	public static void load() throws IOException{
		int tot = 0;
		Scanner fin = new Scanner(new BufferedReader(new FileReader("pokemon.txt")));
		int numbers = Integer.parseInt(fin.nextLine());

		for (int i=0 ; i<numbers ; i++){
		
			PokemonArray.add(new Pokemon(fin.nextLine()));
		}
	}
	
	public static void playgame(){
		boolean True = true;
		int epick = 0;
		int choice = 0;
		epick = (int)(Math.random()* PokemonArray.size());
		enemy = PokemonArray.get(epick);
		System.out.println("It's " + user.name + " vs " + enemy.name);
		
		//battle
		System.out.println(user.name + " has " + user.energy + "energy");
		System.out.println("\n" + "What would you like to do with this pokemon" + "\n");
		while (True = true){
			System.out.println("1.Attack    2.Pass    3.Retreat");
			choice = kb.nextInt();
			if(user.energy < user.leastEnergy){
				System.out.println("You can not afford this attack");
			}
			else if(choice == 1 && user.energy > user.leastEnergy){
				System.out.println("You have " + user.nums_attack + " attacks to choose from");
				for (int i = 0; i < user.nums_attack; i++){
		    		System.out.println(i+1 + ". " + user.attack.get(i).name + ", Energy: " + user.attack.get(i).energy + ", Damage: " + user.attack.get(i).damage + ", Effect: " + user.attack.get(i).effect);
				}
			}
		}
	}
}