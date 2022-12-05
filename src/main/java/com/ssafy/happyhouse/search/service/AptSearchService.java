package com.ssafy.happyhouse.search.service;

import java.util.List;
import java.util.Map;

import com.ssafy.happyhouse.search.dto.*;

public interface AptSearchService {
    public List<AptResultInfoDto> allSearch(AptInfoDto aptInfo) throws Exception;
    public List<String> sidoNameList() throws Exception;
    public List<String> gugunNameList(String sidoName) throws Exception;
    public List<String> dongNameList(String sidoName,String gugunName) throws Exception;

    public boolean addInterest(int member_no,String sidoName, String gugunName, String dongName) throws Exception;

    public List<InterestDto> allInterest(int member_no) throws Exception;//관심지역 전체 조회
    public int deleteInterest(int interest_area_no) throws Exception;//관심지역삭제  - 테이블 식별번호로 삭제.

    public List<InterestAptInfoDto> selectInterestApt(String sidoName, String gugunName, String dongName) throws Exception;//관심지역 아파트 정보 가져오기.

    List<StreetLampDto> getStreetLampList();
    List<CCTVDto> getCCTVList();
    public List<PoliceDto> getPoliceList();
    
    List<AptSafetyResultInfoDto> countSafety(List<AptResultInfoDto> allList, List<StreetLampDto> streetLampList, List<CCTVDto> CCTVList, List<PoliceDto> policeList);

}
