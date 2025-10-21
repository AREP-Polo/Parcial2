package com.arep;

import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class LucasSeqService {

    public int getLucasNumber(int n) {
        if (n == 0) {
            return 2;
        } else if (n == 1) {
            return 1;
        } else {
            return getLucasNumber(n - 1) + getLucasNumber(n - 2);
        }
    }

    public List<Integer> getLucasSequence(int n) {
        List<Integer> sequence = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            sequence.add(getLucasNumber(i));
        }
        return sequence;
    }
}
