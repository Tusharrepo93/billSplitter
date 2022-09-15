package com.fc.billsplit.controller;

import com.fc.billsplit.dto.GroupDetailsDto;
import com.fc.billsplit.service.PostPaymentService;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/postPayment")
public class PostPaymentController {

    @Autowired
    private PostPaymentService postPaymentService;

    @GetMapping(value = "/health")
    public ResponseEntity<String> healthCheckPostPayment(){
        return new ResponseEntity<>("HTTP-OK", HttpStatus.OK);
    }

    @GetMapping(value = "/v1/getGroupDetails/{groupId}")
    public ResponseEntity<GroupDetailsDto> getGroupDetails(@PathVariable("groupId") int groupId) {
        return new ResponseEntity<>(postPaymentService.getGroupDetails(groupId), HttpStatus.OK);
    }

    @PostMapping(value = "/v1/update/groupExpenseDetails")
    public ResponseEntity<String> getGroupDetails(@RequestBody GroupDetailsDto groupDetailsDto) {
        return new ResponseEntity<>(postPaymentService.updateExpenseGroupDetails(groupDetailsDto), HttpStatus.OK);
    }

    public static void sendSms(String message,String number){
        Twilio.init("AC88d54488e86d7aa4f4009bd4e814d988", "52b29612cf453c37d5c79e1cc2b988cc");
        Message message2 = Message.creator(
                        new PhoneNumber("+919034701097"),
                        new PhoneNumber("+19856166318"),
                        "Hi Suraj , Its working !!")
                .create();
        System.out.println(message2.getStatus());
        System.out.println(message2.getErrorMessage());
    }

}
