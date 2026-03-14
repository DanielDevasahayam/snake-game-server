package com.game.service;

import com.game.dto.GameRoomDTO;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class RoomService {

    private Map<String, GameRoomDTO> rooms = new ConcurrentHashMap<>();

    public GameRoomDTO createRoom() {
        String roomId = UUID.randomUUID().toString();
        GameRoomDTO room = new GameRoomDTO();
        room.setRoomId(roomId);
        rooms.put(roomId, room);
        return room;
    }

    public GameRoomDTO joinRoom(String roomId, String player) {
        GameRoomDTO room = rooms.get(roomId);
        room.setOpponent(player);
        return room;
    }

    public GameRoomDTO getRoom(String roomId) {
        return rooms.get(roomId);
    }

}