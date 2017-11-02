package com.DBProject.service;

import com.google.common.collect.ImmutableMap;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by Jatin on 02/11/17.
 */
@Service
public class StudentStageManager {

    public static final ImmutableMap<String, Integer> stageMap =
            new ImmutableMap.Builder<String, Integer>()
                    .put("registered", 1)
                    .put("resumepending", 2)
                    .put("resumeverificationpending", 3)
                    .put("feepending", 4)
                    .put("feeverificationpending", 5)
                    .put("jafeligible", 6)
                    .build();

    public int getNextStage(int stage) {
        if(stage==stageMap.size()) {
            return stage;
        }
        else if(stageMap.containsValue(stage))
            return stage+1;
        else
            return -1;
    }

    public int getCurrentStage(String stage) {
        stage = stage.toLowerCase();
        if(stageMap.containsKey(stage)) {
            return stageMap.get(stage);
        }
        return -1;
    }

    public String getCurrentRep(int stage) {
        for(Map.Entry<String, Integer> entry : stageMap.entrySet()) {
            if(entry.getValue()==stage)
                return entry.getKey();
        }
        return null;
    }
}
