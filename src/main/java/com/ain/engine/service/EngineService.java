package com.ain.engine.service;

import com.ain.engine.repository.mapper.SqlEngineMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * EngineService
 * Created by Young Gyu Park on 15/9/15.
 */
@Service
public class EngineService {

    private final Map<String, List<String>> codeMap;

    @Autowired
    private SqlEngineMapper sqlEngineMapper;

    public EngineService() {
        codeMap = new HashMap<String, List<String>>();
    }

    @PostConstruct
    public void loadCodeMap(){
        try {
            codeMap.put("CD014", getCodeList("CD014"));		// 충전기 상태
            codeMap.put("CD024", getCodeList("CD024"));		// 충전/V2G구분코드
            codeMap.put("CD025", getCodeList("CD025"));		// 충전요구량확인방법
            codeMap.put("CD026", getCodeList("CD026"));		// 결재방법
            codeMap.put("CD027", getCodeList("CD027"));		// 고장유형코드
            codeMap.put("CD070", getCodeList("CD070"));		// B2G상태코드

            System.out.println("============= EngineService code map start ================");
            for (List<String> c : codeMap.values()){
                System.out.println(String.join(",", c));
            }
            System.out.println("============= EngineService code map end ================");

        } catch (Exception ignore) {}
    }

    private List<String> getCodeList(String cdCl) throws Exception {
        return sqlEngineMapper.selectCodeList(cdCl);
    }

    public boolean isExistCode(String cdCl, String code) {
        System.out.println("============= code map start ================");
        for (List<String> c : codeMap.values()){
            System.out.println(String.join(",", c));
        }
        System.out.println("============= code map end ================");

        List<String> codeList = codeMap.get(cdCl);
        return codeList==null ? false : codeList.contains(code);
    }

}