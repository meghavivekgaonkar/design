package com.design.groupmeetings;

import java.time.LocalDateTime;
import java.util.List;

public class Meeting {
    String meetingId;
    String title;
    List<Entity> participants;
    Entity room;
    LocalDateTime startTime;
    LocalDateTime endTime;

    public Meeting(String meetingId, String title, List<Entity> participants, Entity room, LocalDateTime startTime, LocalDateTime endTime) {
        this.meetingId = meetingId;
        this.title = title;
        this.participants = participants;
        this.room = room;
        this.startTime = startTime;
        this.endTime = endTime;
    }
    public String getMeetingId() {
        return meetingId;
    }
    public String getTitle() {
        return title;
    }
    public List<Entity> getParticipants() {
        return participants;    
    }
    public Entity getRoom() {
        return room;
    }
    public LocalDateTime getStartTime() {
        return startTime;
    }
    public LocalDateTime getEndTime() {
        return endTime;
    }
    public String printParticipants() {
        StringBuilder sb = new StringBuilder();
        for (Entity participant : participants) {
            sb.append(participant.getName()).append(" ");
        }
        return sb.toString().trim();
    }
}
