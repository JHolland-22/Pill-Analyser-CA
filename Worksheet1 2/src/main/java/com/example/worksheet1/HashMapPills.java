package com.example.worksheet1;

import java.util.Scanner;

public class HashMapPills {
    allPillPixels[] hashTable; // Array to store Game objects

    public HashMapPills(int size) {
        hashTable = new allPillPixels[size]; // Constructor to initialize the hash table with a given size
    }

    public int hashFunction(String key) {
        // Hash function to determine the index in the hash table for a given key (game name)
        return Math.abs(key.hashCode()) % hashTable.length;
    }

    public void displayHashTable() {
        // Method to display the contents of the hash table
        System.out.println("Hash Table (using Linear Probing)\n=================");
        for (int i = 0; i < hashTable.length; i++) {
            if (hashTable[i] != null) {
                System.out.println(i + ". " + hashTable[i].getPillName());
            } else {
                System.out.println(i + ". Empty");
            }
        }
    }

    public int add(allPillPixels pill) {
        // Method to add a Game object to the hash table
        int home = hashFunction(pill.getPillName());
        int loc = home;
        do {
            if (hashTable[loc] == null) { // If slot is empty, store the game
                hashTable[loc] = pill;
                return loc; // Return the index where the game is stored
            } else {
                // If slot is occupied, probe ahead by 1 using linear probing
                System.out.println(loc + " full so probing ahead by 1...");
                loc = (loc + 1) % hashTable.length; // Wrap around to the beginning if needed
            }
        } while (loc != home); // Keep probing until we reach the starting position

        return -1; // Failed to add the pill (hash table full)
    }

    public allPillPixels getGameDetails(String pillName) {
        // Method to retrieve a pill object from the hash table based on the pill name
        int home = hashFunction(pillName);
        int loc = home;
        do {
            if (hashTable[loc] != null && hashTable[loc].getPillName().equals(pillName)) {
                // If the game is found at the location, return it
                return hashTable[loc];
            } else {
                loc = (loc + 1) % hashTable.length; // Probe ahead using linear probing
            }
        } while (loc != home && hashTable[loc] != null); // Continue probing until an empty slot or back to start

        return null; // Game not found in the hash table
    }

    public static void main(String[] args) {
        // Main method to demonstrate the usage of the GameHashLP class
        allPillPixels h = new allPillPixels("","",""); // Create a hash table of size 100
        Scanner k = new Scanner(System.in);
        String pillName;

        do {
            // Input game name from the user to add to the hash table
            System.out.print("Enter pill name (0 to exit): ");
            pillName = k.nextLine();

            if (!pillName.equals("0")) {
                // Input game details for the entered game name
                System.out.print("Enter pill details for " + h.getPillName() + ": ");
                String dosage = k.nextLine();
                String pixelCount = k.nextLine();
                allPillPixels pill = new allPillPixels( pillName,  dosage,  pixelCount);

                int loc = h.add(pill); // Add the game to the hash table
                if (loc >= 0) {
                    System.out.println(pillName + " stored at location " + loc);
                } else {
                    System.out.println("Failed to store " + pillName + ". Hash table full.");
                }
            }
        } while (!pillName.equals("0"));

        h.displayHashTable(); // Display the contents of the hash table
    }
}