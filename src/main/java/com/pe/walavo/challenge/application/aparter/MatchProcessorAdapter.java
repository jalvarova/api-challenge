package com.pe.walavo.challenge.application.aparter;


import com.pe.walavo.challenge.application.match.FemaleMatch;
import com.pe.walavo.challenge.application.match.MaleMatch;
import com.pe.walavo.challenge.application.match.MatchProcessor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Component
public class MatchProcessorAdapter {

    private final FemaleMatch femaleMatch;

    private final MaleMatch maleMatch;

    public static final Map<String, MatchProcessor> map = new HashMap<>();

    @PostConstruct
    public void init() {
        map.put("F", femaleMatch);
        map.put("M", maleMatch);
    }

    public MatchProcessor strategy(String type) {
        return map.get(type);
    }

}
