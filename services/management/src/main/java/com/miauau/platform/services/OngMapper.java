package com.miauau.platform.services;

import com.miauau.platform.models.Ong;
import com.miauau.platform.requests.OngRequest;
import com.miauau.platform.responses.OngResponse;
import org.springframework.stereotype.Service;

@Service
public class OngMapper {
    public Ong toEntity (OngRequest request){
        if (request == null) {
            return null;
        }
        return Ong.builder()
                .name(request.name())
                .email(request.email())
                .build();
    }
    public OngResponse toResponse (Ong ong){
        if (ong == null) {
            return null;
        }
        return new OngResponse(ong.getId(), ong.getName(), ong.getEmail());
    }
}
