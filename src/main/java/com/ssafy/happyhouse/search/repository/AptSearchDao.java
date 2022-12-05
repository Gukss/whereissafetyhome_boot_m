package com.ssafy.happyhouse.search.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.search.dto.AptInfoDto;
import com.ssafy.happyhouse.search.dto.AptResultInfoDto;
import com.ssafy.happyhouse.search.dto.CCTVDto;
import com.ssafy.happyhouse.search.dto.InterestAptInfoDto;
import com.ssafy.happyhouse.search.dto.InterestDto;
import com.ssafy.happyhouse.search.dto.PoliceDto;
import com.ssafy.happyhouse.search.dto.StreetLampDto;

@Mapper
public interface AptSearchDao {
    public List<AptResultInfoDto> allSearch(AptInfoDto aptInfo) throws Exception;
    public List<String> sidoNameList() throws Exception; //시도이름 조회
    public List<String> gugunNameList(String sidoName) throws Exception; //구군이름 조회
    public List<String> dongNameList(Map<String, String> map) throws Exception;// 동이름 조회
    public int insertInterest(Map<String, Object> map) throws Exception; //관심지역 추가
    public List<InterestDto> checkInterest(Map<String, Object> map) throws Exception; //관심지역 중복 체크

    public List<InterestDto> allInterest(int member_no) throws Exception;//관심지역 전체 조회
    public int deleteInterest(int interest_area_no) throws Exception;//관심지역삭제  - 테이블 식별번호로 삭제.

    public List<InterestAptInfoDto> selectInterestApt(Map<String, String> map) throws Exception;//관심지역 아파트 정보 가져오기.
    public List<StreetLampDto> getStreetLampList();

    public List<CCTVDto> getCCTVList();
    
    public List<PoliceDto> getPoliceList();
}
