package com.sep.service;

import com.sep.enums.RequestStatusEnum;
import com.sep.model.*;
import com.sep.repository.RequestRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequestService {
    @Resource
    private RequestRepository requestRepository;

    @Resource
    private IdGenService idGenService;

    public SepReqListVO getBudgetReqList(String username) {

        List<SepRequest> budgetReqList = requestRepository.getRequestByType("budget");

        List<SepReqVO> sepReqVOList = budgetReqList
                .stream()
                .map(r -> new SepReqVO()
                        .setRequestId(String.valueOf(r.getRequestId()))

                        .setRequestName(r.getRequestName())
                        .setRequestDesc(r.getRequestDesc())
                        .setStatus(r.getStatus().getDesc())
                        .setApplicationId(String.valueOf(r.getApplicationId()))
                ).toList();

        return new SepReqListVO()
                .setSepReqList(sepReqVOList);
    }

    public boolean createReq(CreateSepRequest req) {

        SepRequest sepRequest = new SepRequest()
                .setRequestName(req.getRequestName())
                .setRequestDesc(req.getRequestDesc())
                .setApplicationId(Long.parseLong(req.getApplicationId()))
                .setRequestId(idGenService.generateId())
                .setStatus(RequestStatusEnum.REVIEWING)
                .setType(req.getType());

        requestRepository.addRequest(sepRequest);

        return true;
    }

    public SepReqListVO getResourceReqList(String username) {
        List<SepRequest> budgetReqList = requestRepository.getRequestByType("resource");

        List<SepReqVO> sepReqVOList = budgetReqList
                .stream()
                .map(r -> new SepReqVO()
                        .setRequestId(String.valueOf(r.getRequestId()))

                        .setRequestName(r.getRequestName())
                        .setRequestDesc(r.getRequestDesc())
                        .setStatus(r.getStatus().getDesc())
                        .setApplicationId(String.valueOf(r.getApplicationId()))
                ).toList();

        return new SepReqListVO()
                .setSepReqList(sepReqVOList);
    }

    public boolean changeStatus(ChangeReqStatus req) {

        String requestId = req.getRequestId();
        SepRequest sepReq = requestRepository.getById(Long.parseLong(requestId));
        if (sepReq == null) {
            return false;
        }

        if ("approve".equals(req.getAction())) {
            sepReq.setStatus(RequestStatusEnum.APPROVED);
            requestRepository.updateReq(sepReq);
            return true;
        } else if ("reject".equals(req.getAction())) {
            sepReq.setStatus(RequestStatusEnum.REJECTED);
            requestRepository.updateReq(sepReq);
            return true;
        }

        return false;
    }
}
