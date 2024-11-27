package com.ibm.reactive;


import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;

import java.time.Duration;
import java.util.List;

public class MultiDataPipeLines {
    public static void main(String[] args) {
        // Example list of user IDs
        List<String> userIds = List.of("user1", "user2", "user3");

        // Create a Multi from the list
        Multi.createFrom().iterable(userIds)
                .onItem().transformToUniAndMerge(userId -> getUserPreferences(userId)
                        .onItem().transformToUni(preferences -> sendNotification(userId, preferences)))
                .subscribe().with(
                        success -> System.out.println("Notification sent: " + success),
                        failure -> System.err.println("Failed to process: " + failure)
                );
    }

    // Simulate fetching user preferences (asynchronous)
    private static Uni<String> getUserPreferences(String userId) {
        return Uni.createFrom().item(() -> "Preferences for " + userId)
                .onItem().delayIt().by(Duration.ofMillis(1000)); // Simulated delay
    }

    // Simulate sending a notification (asynchronous)
    private static Uni<Boolean> sendNotification(String userId, String preferences) {
        return Uni.createFrom().item(() -> {
            System.out.println("Sending notification to " + userId + " with " + preferences);
            return true;
        }).onItem().delayIt().by(Duration.ofMillis(1000));// Simulated delay
    }
}
