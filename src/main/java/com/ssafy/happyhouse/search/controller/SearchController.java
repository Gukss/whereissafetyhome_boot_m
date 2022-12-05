package com.ssafy.happyhouse.search.controller;

import com.ssafy.happyhouse.member.service.MemberService;
import com.ssafy.happyhouse.search.dto.*;
import com.ssafy.happyhouse.search.service.AptSearchService;

import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("search")
@Api(value = "board")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST })

public class SearchController {
    private final AptSearchService aptSearchService;
    private final MemberService memberService;

    @Autowired
    public SearchController(AptSearchService aptSearchService, MemberService memberService) {
        this.aptSearchService = aptSearchService;
        this.memberService = memberService;
    }


    @GetMapping("/test")
    public ResponseEntity<ResponseDto>test(){
        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        ResponseDto re = new ResponseDto();
        Map<String, String> temp = new HashMap<>();
        temp.put("test","테스트");
        re.setData(temp);
        re.setStatus(HttpStatus.OK);
        re.setMsg("성공");
        return new ResponseEntity<>(re,headers,HttpStatus.OK);
    }

    /**
     * 주소, 날짜에 해당하는 모든 실거래내역 조회, 테이블 2개 join
     *  mapper: allSearch
     *
     * @param map String sido, gugun, dong, year, month 담은 map
     * @return 아파트 매매정보 리스트를 반환, sidoName, gugunName, dongName, aptName, lat, lng, dealAmount, area, floor, roadName, roadNameBonbun, roadNameBubun
     */
    @GetMapping("/aptlist") //test complete
    @ApiOperation(value = "아파트 정보 리스트 반환", notes = "조건에 맞는 모든 아파트 정보를 반환한다.")

    public List<AptResultInfoDto> getAllAptlist(@ApiParam(
            name="Map",
            value="AptInfoDto",
            type = "query",
            example = "{\"sido\": \"서울특별시\", \"gugun\": \"종로구\", \"dong\": \"사직동\", \"year\": \"2020\", \"month\": \"11\"}")
                                                         @RequestParam Map<String, String> map) throws Exception {
        List<AptResultInfoDto> allList = null;
        String sido = map.get("sido");
        String gugun = map.get("gugun");
        String dong = map.get("dong");
        String year = map.get("year");
        String month = map.get("month");
        AptInfoDto aptInfoDto = new AptInfoDto(sido, gugun, dong, year, month);
  
        allList = aptSearchService.allSearch(aptInfoDto);
        return allList;
    }

    /**
     * 시/도 리스트를 반환한다. dongCode 테이블에서 조회
     *  mapper: sidoNameList
     *
     * @return List<String>에 시/도 리스트를 담아서 반환한다.
     */
    @GetMapping("/sido") //test complete
    @ApiOperation(value = "시,도 리스트 반환", notes = "시,도 리스트를 반환한다.")
    public List<String> sido() throws Exception{
        List<String> sidoList = null;
        sidoList = aptSearchService.sidoNameList();
        return sidoList;
    }

    /**
     * 시/도 값을 String으로 받아서 해당 시/도에 맞는 구/군 리스트를 반환한다. dongCode 테이블에서 조회
     *  mapper: gugunNameList
     *
     * @param map String sido 담은 map
     * @return List<String>에 구/군 리스트를 담아서 반환한다.
     */
    @GetMapping("/gugun") //test complete
    @ApiOperation(value = "구,군 리스트 반환", notes = "해당 시,도에 맞는 구,군을 반환한다.")
    public List<String> gugun(@ApiParam(name="Map", value="sido", type = "query",
            examples = @io.swagger.annotations.Example(value = {@ExampleProperty(value = "{\"sido\": \"서울특별시\"}")})
    )@RequestParam Map<String, String> map) throws Exception{ //json 객체로 "sido":"x" 넘어오면
        List<String> gunguList = null;
        gunguList = aptSearchService.gugunNameList(map.get("sido")); //사용한다.
        return gunguList;
    }

    /**
     * 시/도, 구/군 값을 String으로 받아서 해당하는 동 리스트를 반환한다. dongCode 테이블에서 조회
     * mapper: dongNameList
     *
     * @param map String sido, gugun을 담은 map
     * @return List<String>에 동 리스트를 담아서 반환한다.
     */
    @GetMapping("/dong") //test complete
    @ApiOperation(value = "동 리스트 반환", notes = "해당 구,군에 맞는 동을 반환한다.")
    public List<String> dong(@ApiParam(name="Map", value="sido, gugun", example = "{\"sido\":\"서울특별시\",\"gugun\": \"종로구\"}")
                                                @RequestParam Map<String, String> map) throws Exception{ //json 객체로 "gugun":"x" 넘어오면
        List<String> dongList = null;
        dongList = aptSearchService.dongNameList(map.get("sido"),map.get("gugun")); //사용한다.
        return dongList;
    }

    /**
     * 시/도, 구/군, 동을 interest_area에 추가한다.
     * mapper: addInterest
     *
     * @param map String sidoName, gugunName, dongName 담은 map
     * @return httpStatus ok/unauthorized
     */
    @PostMapping("/interest")
    @ApiOperation(value = "사용자의 관심지역 리스트 등록", notes = "사용자가 관심지역을 등록한다.")
    public HttpStatus insertInterest(@ApiParam(
            name="Map{sidoName, gugunName, dongName}", value="InterestDto의 값들",
            example = "{\"sidoName\": \"서울특별시\", \"gugunName\": \"종로구\", \"dongName\": \"사직동\"}")
                                         @RequestBody Map<String, Object> map) throws Exception{
        int member_no = (int)map.get("member_no");
        System.out.println(member_no);
        if(member_no != 0){
            String sidoName = (String)map.get("sidoName");
            String gugunName = (String)map.get("gugunName");
            String dongName = (String)map.get("dongName");
            System.out.println(member_no + " " + sidoName + " " + gugunName + " " + dongName);
            aptSearchService.addInterest(member_no, sidoName, gugunName, dongName);

            //관심목록에 추가하고, 관심목록으로 이동하기
            return HttpStatus.OK;
        }else{
            //로그인 안돼있으면 로그인 화면으로 이동하기
            return HttpStatus.UNAUTHORIZED;
        }
    }

    /**
     * interest_area에서 사용자의 관심지역을 모두 반환한다.
     * mapper: allInterest
     *
     * @param member_no autoIncrement하는 member 테이블의 pk
     * @return 해당 사용자의 관심지역 리스트를 반환한다.
     * @throws Exception
     */
    @GetMapping("/interest")
    @ApiOperation(value = "관심지역 리스트 반환", notes = "로그인된 사용자의 관심지역 리스트를 반환한다.")
    public List<InterestDto> getInterestList(@RequestParam int member_no) throws Exception{
        List<InterestDto> interestList = null;
        if(member_no != 0){
            interestList = new ArrayList<>();
            interestList = aptSearchService.allInterest(member_no);
        }
        return interestList;
    }

    /**
     * interest_area에서 값을 1개 삭제한다.
     * mapper: deleteInterest
     *
     * @param interest_area_no 지우려 하는 관심지역의 pk
     * @return httpStatus ok/?
     * @throws Exception
     */
    @DeleteMapping("/interest")
    @ApiOperation(value = "관심지역 삭제", notes = "관심지역의 id를 받아 해당하는 값을 관심지역리스트에서 삭제한다.")
    public HttpStatus deleteInterest(@ApiParam(name="member_no", value="관심지역 id", example = "1")
                                                          @RequestParam int interest_area_no) throws Exception{
        int res = aptSearchService.deleteInterest(interest_area_no);
        // todo 무조건 ok말고 예외처리해야한다.
        return HttpStatus.OK;
    }

    /**
     * interest_area 테이블에 들어있는 주소로 Allsearch(/aptlist)할 때 와 같이 join2번 해서 아파트 정보 리스트를 가져온다.
     * mapper: selectInterestApt
     *
     * @param map String sidoNaem, gugunName, dongName을 담은 map
     * @return @return 아파트 매매정보 리스트를 반환, sidoName, gugunName, dongName, aptName, lat, lng, dealAmount, area, floor, roadName, roadNameBonbun, roadNameBubun
     */
    @GetMapping("/interest/info")
    @ApiOperation(value = "관심지역의 아파트 정보 반환", notes = "관심지역(sido, gugun, dong Name)을 받아서 해당 지역의 아파트 정보를 반환")
    public List<InterestAptInfoDto> getInterestAptInfo(@ApiParam(
            name="Map", value="시도, 구군, 동 이름",
            type = "query",
            example = "{\"sidoName\": \"서울특별시\", \"gugunName\": \"종로구\", \"dongName\": \"사직동\"}")
            @RequestParam Map<String, String> map) throws Exception{
        String sidoName = map.get("sidoName");
        String gugunName = map.get("gugunName");
        String dongName = map.get("dongName");
        List<InterestAptInfoDto> returnList = null;
        if(sidoName == null || gugunName == null || dongName == null){
            //Todo 비었을 때 어떻게 해주지?
        }
       
        returnList = aptSearchService.selectInterestApt(sidoName,gugunName,dongName);
        return returnList;
    }

    /**
     * 해당 주소에 있는 아파트 마다 반경 2km안의 가로등, cctv, 경찰서 개수를 기준으로 내림차순 정렬을 한 리스트를 반환한다.
     * mapper: allSearch, getStreetLampList, getCCTVList, getPoliceList
     *
     * @param map String sido, gugun, dong, year, month을 담은 map
     * @return 아파트 매매정보 리스트를 반환, sidoName, gugunName, dongName, aptName, lat, lng, dealAmount, area, floor, roadName, roadNameBonbun, roadNameBubun
     */
    @GetMapping("/safety")
    public List<AptSafetyResultInfoDto> getSafetyAptList(@ApiParam(
            name="Map",
            value="AptInfoDto",
            type = "query",
            example = "{\"sido\": \"서울특별시\", \"gugun\": \"종로구\", \"dong\": \"사직동\", \"year\": \"2020\", \"month\": \"11\"}")
                                                       @RequestParam Map<String, String> map) throws Exception {
        List<AptResultInfoDto> allList = null;
        String sido = map.get("sido");
        String gugun = map.get("gugun");
        String dong = map.get("dong");
        String year = map.get("year");
        String month = map.get("month");
        AptInfoDto aptInfoDto = new AptInfoDto(sido, gugun, dong, year, month);

        allList = aptSearchService.allSearch(aptInfoDto);
        List<StreetLampDto> streetLampList = null;
        List<CCTVDto> cctvList = null;
        List<PoliceDto> policeList = null;
        streetLampList = aptSearchService.getStreetLampList();
        cctvList = aptSearchService.getCCTVList();
        policeList = aptSearchService.getPoliceList();
        
        List<AptSafetyResultInfoDto> safetyAptList = aptSearchService.countSafety(allList, streetLampList, cctvList, policeList);
        return safetyAptList;
    }
}
