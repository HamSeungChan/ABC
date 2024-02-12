package com.private_lbs.taskmaster.redis.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.private_lbs.taskmaster.redis.exception.RedisErrorCode;
import com.private_lbs.taskmaster.redis.exception.RedisException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class RedisSubService implements MessageListener {
    private final ObjectMapper mapper = new ObjectMapper();
    private final SseEmitters sseEmitters;
    /**
     * @author boyjo
     * @date 2/3/24
     * @method onMessage
     * @param
     * @description SUB중인 채널로부터 메시지 수신
     **/
    @Override
    public void onMessage(Message message, byte[] pattern) {
        try {
            //TODO: 1. AI 가공이 완료되었다는 메시지가 수신 로직 구현 - SSE 필요
            //TODO: 2. 각 상태별 메시지가 수신 로직 구현 - SSE 필요
            String mes=new String(message.getBody(),"UTF-8");
//            sseEmitters.addEmitter();
            sseEmitters.sendMessage(mes);
        } catch (RedisException | IOException e) {
            throw new RedisException(RedisErrorCode.MESSAGE_RECEIVE_FAILED);
        }
    }
}
