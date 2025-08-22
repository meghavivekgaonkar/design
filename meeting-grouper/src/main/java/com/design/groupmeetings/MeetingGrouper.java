/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.design.groupmeetings;

import com.design.groupmeetings.Entity.Type;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author meghagaonkar
 */
public class MeetingGrouper {
    public List<List<Meeting>> groupMeetings(List<Meeting> meetings) {
        List<List<Meeting>> groupedMeetings = new ArrayList<>();
        if (meetings.isEmpty())
            return groupedMeetings;

        List<Meeting> currentGroup = new ArrayList<>();
        currentGroup.add(meetings.get(0));

        for (int i = 1; i < meetings.size(); i++) {
            Meeting currentMeeting = meetings.get(i);
            Meeting lastMeetingInGroup = currentGroup.get(currentGroup.size() - 1);

            // Check merge conditions
            if (canMerge(currentMeeting, lastMeetingInGroup)) {
                currentGroup.add(currentMeeting);
            } else {
                // Cannot merge, so finalize the current group and start a new one
                groupedMeetings.add(currentGroup);
                currentGroup = new ArrayList<>();
                currentGroup.add(currentMeeting);
            }
        }
        // Add the last group after the loop
        groupedMeetings.add(currentGroup);
        return groupedMeetings;
    }
    public boolean canMerge(Meeting newMeeting, Meeting lastMeeting) {
    // Condition 1: Check for consecutive time blocks
    // Allow a 5-minute gap for scheduling buffer
    boolean isConsecutive = newMeeting.getStartTime().isBefore(lastMeeting.getEndTime().plusMinutes(5));

    // Condition 2: Check for shared participants
    boolean hasSharedAttendees = false;
    Set<String> lastAttendees = new HashSet<>();
    for (Entity attendee : lastMeeting.getParticipants()) {
        if (attendee.getType() == Type.PERSON) {
            lastAttendees.add(attendee.getName());  
        }
    }
    for (Entity attendee : newMeeting.getParticipants()) {
        if (attendee.getType() == Type.PERSON && lastAttendees.contains(attendee.getName())) {
            hasSharedAttendees = true;
            break;
        }
    }
    
    return isConsecutive && hasSharedAttendees;
}

    public static void main(String[] args) {
        // Define the Entities (People and Rooms) first
        Entity userA = new Entity(Entity.Type.PERSON, "User A");
        Entity userB = new Entity(Entity.Type.PERSON, "User B");
        Entity userC = new Entity(Entity.Type.PERSON, "User C");
        Entity userD = new Entity(Entity.Type.PERSON, "User D");
        Entity userE = new Entity(Entity.Type.PERSON, "User E");
        Entity userF = new Entity(Entity.Type.PERSON, "User F");

        Entity roomChicago = new Entity(Entity.Type.ROOM, "R:1:Chicago");
        Entity roomNYC = new Entity(Entity.Type.ROOM, "R:2:NYC");
        Entity roomMiami = new Entity(Entity.Type.ROOM, "R:3:Miami");
        // Create the Meeting objects, intentionally out of chronological order
        Meeting meeting1 = new Meeting(
                "m1",
                "Weekly Sync",
                Arrays.asList(userA, userB),
                roomChicago,
                LocalDateTime.of(2025, 8, 25, 9, 0),
                LocalDateTime.of(2025, 8, 25, 9, 30));

        Meeting meeting2 = new Meeting(
                "m2",
                "Client Call",
                Arrays.asList(userB, userC),
                roomChicago,
                LocalDateTime.of(2025, 8, 25, 9, 30),
                LocalDateTime.of(2025, 8, 25, 10, 0));

        Meeting meeting3 = new Meeting(
                "m3",
                "Budget Review",
                Arrays.asList(userE, userF),
                roomMiami,
                LocalDateTime.of(2025, 8, 25, 15, 30),
                LocalDateTime.of(2025, 8, 25, 16, 0));

        Meeting meeting4 = new Meeting(
                "m4",
                "Standup Meeting",
                Arrays.asList(userC, userD),
                roomNYC,
                LocalDateTime.of(2025, 8, 25, 12, 0),
                LocalDateTime.of(2025, 8, 25, 13, 0));

        Meeting meeting5 = new Meeting(
                "m5",
                "Project Kickoff",
                Arrays.asList(userD, userC),
                roomNYC,
                LocalDateTime.of(2025, 8, 25, 13, 0),
                LocalDateTime.of(2025, 8, 25, 14, 0));

        // The final List of Meeting objects to be passed to your algorithm
        List<Meeting> meetings = new ArrayList<>(Arrays.asList(
                meeting5,
                meeting1,
                meeting3,
                meeting2,
                meeting4));
        meetings.sort(Comparator.comparing(Meeting::getStartTime));
        // Create an instance of MeetingGrouper and group the meetings
        MeetingGrouper grouper = new MeetingGrouper();
        List<List<Meeting>> groupedMeetings = grouper.groupMeetings(meetings);
        // Print the grouped meetings
        int i = 1;
        for (List<Meeting> group : groupedMeetings) {
            System.out.println("Group " + i++ + ":");
            for (Meeting meeting : group) {
                System.out.println("  Meeting ID: " + meeting.getMeetingId() +
                        ", Title: " + meeting.getTitle() +
                        ", Start: " + meeting.getStartTime() +
                        ", End: " + meeting.getEndTime() +
                        ", Participants: " + meeting.printParticipants() +
                        ", Room: " + meeting.getRoom().getName());  
            }
        }

    }
}
