package com.github.rodrigoaustincascao.whatsappclone.chat;

import com.github.rodrigoaustincascao.whatsappclone.user.User;
import com.github.rodrigoaustincascao.whatsappclone.user.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final ChatRepository chatRepository;
    private final UserRepository userRepository;
    private final ChatMapper chatMapper;

    @Transactional(readOnly=true)
    public List<ChatResponse> getChatsByReceiverId(Authentication currentUser){
        final  String userId = currentUser.getName();
        return chatRepository.findChatsBySenderId(userId)
                .stream()
                .map(c -> chatMapper.toChatResponse(c, userId))
                .toList();
    }

    public String createChat(String senderId, String receiverId){
        Optional<Chat> exitingChat = chatRepository.findChatByReceiverAndSender(senderId, receiverId);
        if(exitingChat.isPresent()){
            return exitingChat.get().getId();
        }

        User sender = userRepository.findByPublicId(senderId)
                .orElseThrow(() -> new EntityNotFoundException("User with id "+ senderId +" not found"));

        User receiver = userRepository.findByPublicId(senderId)
                .orElseThrow(() -> new EntityNotFoundException("User with id "+ receiverId +" not found"));

        Chat chat = new Chat();
        chat.setSender(sender);
        chat.setRecipient(receiver);

        Chat savedChat = chatRepository.save(chat);
        return savedChat.getId();
    }
}
