package com.arep;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LucasSeqController {

    @Autowired
    private LucasSeqService lucasSeqService;

    @GetMapping("/lucasseq")
    public ResponseEntity<HashMap<String, String>> getLucasSequence(@RequestParam int value) {
        List<Integer> sequence = lucasSeqService.getLucasSequence(value);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < sequence.size(); i++) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(sequence.get(i));
        }
        String output = sb.toString();

        HashMap<String, String> response = new HashMap<>();

        response.put("output", output);
        response.put("input", Integer.toString(value));
        response.put("operation", "Secuencia de Lucas");

        return ResponseEntity.ok(response);
    }
}
