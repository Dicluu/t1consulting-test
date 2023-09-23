package com.example.demo.services;

import com.example.demo.InvalidValueException;
import org.springframework.stereotype.Service;

import java.util.*;
@Service
public class IndexServiceImpl implements IndexService{

    @Override
    public List calculate(String value) {
        HashMap<String, Integer> result = new HashMap<>();
        String[] chars = value.toLowerCase().split("");
        if (value.length() >= 100) {
            throw new InvalidValueException("input value can't be longer than 100 chars");
        }
        if (value.length() == 0) {
            throw new InvalidValueException("input value must be not empty");
        }
        for (int i = 0; i < value.length(); i++) {
            if (!result.containsKey(chars[i])) {
                isInteger(chars[i]);
                result.put(chars[i], 1);
            } else {
                result.put(chars[i],result.get(chars[i])+1);
            }
        }

        List sorted = new ArrayList(result.entrySet());
        Collections.sort(sorted, new Comparator<Map.Entry<Integer, Integer>>() {
            @Override
            public int compare(Map.Entry<Integer, Integer> a, Map.Entry<Integer, Integer> b) {
                return b.getValue() - a.getValue();
            }
        });
        return sorted;
    }

    private void isInteger(String value) {
        try {
            Integer.parseInt(value);
            throw new InvalidValueException("input value can't contain integer");
        } catch (NumberFormatException e) {

        }
    }
}
