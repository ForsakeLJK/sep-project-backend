package com.sep.repository;

import com.sep.model.SepRequest;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RequestRepository {
    private final List<SepRequest> sepRequestList = new ArrayList<>();

    public List<SepRequest> getRequestByType(String type) {
        return sepRequestList.stream()
                .filter(r -> r.getType().equals(type))
                .toList();
    }

    public void addRequest(SepRequest sepRequest) {
        sepRequestList.add(sepRequest);
    }

    public SepRequest getById(Long reqId) {
        return sepRequestList.stream()
                .filter(r -> r.getRequestId().equals(reqId))
                .findFirst()
                .orElse(null);
    }

    public void updateReq(SepRequest sepReq) {
        sepRequestList.removeIf(r -> r.getRequestId().equals(sepReq.getRequestId()));
        sepRequestList.add(sepReq);
    }
}