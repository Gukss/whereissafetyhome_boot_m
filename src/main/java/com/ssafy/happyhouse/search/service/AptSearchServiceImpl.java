package com.ssafy.happyhouse.search.service;

import com.ssafy.happyhouse.search.dto.*;
import com.ssafy.happyhouse.search.repository.AptSearchDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AptSearchServiceImpl implements AptSearchService{
    private final double TOTALLAMP = 19316;
    private final double TOTALCCTV = 61540;
    private final double TOTALPOLICE = 2031;
    
    private final double BOUNDARY = 2.0;
    private final AptSearchDao aptSearchDao;

    @Autowired
    public AptSearchServiceImpl(AptSearchDao aptSearchDao) {
        this.aptSearchDao = aptSearchDao;
    }


    //todo 얘는 뭐하는 메소드인가?
    @Override
    public List<AptResultInfoDto> allSearch(AptInfoDto aptInfo) throws Exception {

        List<AptResultInfoDto> aptResultInfoList = aptSearchDao.allSearch(aptInfo);

        int temp, first, second;
        AptResultInfoDto firstDto, secondDto;
        for(int i=0; i<aptResultInfoList.size()-1; i++) {
            for(int j=0; j<aptResultInfoList.size()-1-i; j++) {
                firstDto = aptResultInfoList.get(j);
                secondDto = aptResultInfoList.get(j+1);

                first = Integer.parseInt(firstDto.getDealAmount().replace(",", ""));
                second = Integer.parseInt(secondDto.getDealAmount().replace(",", ""));

                if(first > second) {
                    temp = Integer.parseInt(aptResultInfoList.get(j).getDealAmount().replace(",", ""));
                    aptResultInfoList.add(j, secondDto);
                    aptResultInfoList.remove(j+1);
                    aptResultInfoList.add(j+1, firstDto);
                    aptResultInfoList.remove(j+2);
                }
            }
        }

        return aptResultInfoList;
    }



    @Override
    public List<String> sidoNameList() throws Exception {
        return aptSearchDao.sidoNameList();
    }


    @Override
    public List<String> gugunNameList(String sidoName) throws Exception {
        return aptSearchDao.gugunNameList(sidoName);
    }

    @Override
    public List<String> dongNameList(String sidoName, String gugunName) throws Exception {
        Map<String, String> map = new HashMap();
        map.put("sidoName", sidoName);
        map.put("gugunName", gugunName);
        return aptSearchDao.dongNameList(map);
    }


    /**
     * interest_area 테이블에 데이터를 추가하기 전에 해당 데이터가 있는지 검사하는 메소드
     *
     * @param member_no autoIncresement 하는 member 테이블 pk
     * @param sidoName String
     * @param gugunName String
     * @param dongName String
     * @return boolean t,f
     * @throws Exception
     */
    @Override
    public boolean addInterest(int member_no, String sidoName, String gugunName, String dongName) throws Exception {
        Map<String, Object> map = new HashMap();
        map.put("member_no", member_no);
        map.put("sidoName", sidoName);
        map.put("gugunName", gugunName);
        map.put("dongName", dongName);
        // todo 이거 검사하면 같은 주소가 2개 들어가면 안되는데 들어가는데??
        //추가하려고 하는 데이터를 먼저 조회 해서 있는 지 확인,
        List<InterestDto> checkList = aptSearchDao.checkInterest(map);
        //값이 없다면 추가
        if(checkList != null) {
            aptSearchDao.insertInterest(map);
            return true;
        }else{
            return false;
        }
    }



    @Override
    public List<InterestDto> allInterest(int member_no) throws Exception {
        return aptSearchDao.allInterest(member_no);
    }



    @Override
    public int deleteInterest(int interest_area_no) throws Exception {
        return aptSearchDao.deleteInterest(interest_area_no);
    }


    /**
     * interest_area 테이블에 들어있는 주소로 Allsearch(/aptlist)할 때 와 같이 join2번 해서 아파트 정보 리스트를 가져온다.
     *
     * @param sidoName
     * @param gugunName
     * @param dongName
     * @return
     * @throws Exception
     */
    @Override
    public List<InterestAptInfoDto> selectInterestApt(String sidoName, String gugunName, String dongName) throws Exception {
        Map<String, String> map = new HashMap();
        map.put("sidoName", sidoName);
        map.put("gugunName", gugunName);
        map.put("dongName", dongName);

        List<InterestAptInfoDto> list = aptSearchDao.selectInterestApt(map);
        for(InterestAptInfoDto x: list){
            System.out.println(x.toString());
        }
        return aptSearchDao.selectInterestApt(map);
    }

    @Override
    public List<StreetLampDto> getStreetLampList() {
        return aptSearchDao.getStreetLampList();
    }

    @Override
    public List<CCTVDto> getCCTVList() {
        return aptSearchDao.getCCTVList();
    }
    

    @Override
    public List<PoliceDto> getPoliceList() {
    return aptSearchDao.getPoliceList();
    }

    /**
     * input: 가로등, cctv, 경찰서의 위도, 경도를 가지고 있는 리스트, 주소에 있는 모든 아파트 리스트
     *
     * @param allList
     * @param streetLampList
     * @param CCTVList
     * @param policeList
     * @return 모든 아파트 마다 반경 2km안의 가로등, cctv, 경찰서 개수를 기준으로 내림차순 정렬을 한 리스트를 반환한다.
     */
    @Override
    public List<AptSafetyResultInfoDto> countSafety(List<AptResultInfoDto> allList, List<StreetLampDto> streetLampList, List<CCTVDto> CCTVList, List<PoliceDto> policeList) {
    	  int aptSize = allList.size();
        int lampSize = streetLampList.size();
        int CCTVSize = CCTVList.size();
        int policeSize = policeList.size();
        
        List<AptSafetyResultInfoDto> safetyAptList = new ArrayList<>();
        DistCount[] distcountArr = new DistCount[aptSize];
        
        for (int i = 0; i < aptSize; i++) {
            distcountArr[i] = new DistCount(0.0,0.0,0.0,0.0,0.0,0.0,0.0,i);
        }

        for (int i = 0; i < aptSize; i++) {
            AptResultInfoDto curApt = allList.get(i);
            double curAptLat = Double.parseDouble(curApt.getLat());
            double curAptLng = Double.parseDouble(curApt.getLng());
            //lamp
            for (int j = 0; j < lampSize; j++) {
                StreetLampDto curLamp = streetLampList.get(j);
                double curLampLat = curLamp.getLat();
                double curLampLng = curLamp.getLng();

                double dist = distance(curAptLat, curAptLng, curLampLat, curLampLng);
                if(dist <= BOUNDARY){
                    distcountArr[i].lampCount += 1.0;
                }
            }
            //cctv
            for (int j = 0; j < CCTVSize; j++) {
                CCTVDto curCCTV = CCTVList.get(j);
                double curCCTVLat = curCCTV.getLat();
                double curCCTVLng = curCCTV.getLng();

                double dist = distance(curAptLat, curAptLng, curCCTVLat, curCCTVLng);
                if(dist <= BOUNDARY){
                    distcountArr[i].CCTVCount += 1.0;
                }
            }

            // police
            for (int j = 0; j < policeSize; j++) {
                PoliceDto curPolice = policeList.get(j);
                double curPoliceLat = curPolice.getLat();
                double curPoliceLng = curPolice.getLng();
                double dist = distance(curAptLat, curAptLng, curPoliceLat, curPoliceLng);

                if(dist <= BOUNDARY) {
                  distcountArr[i].policeCount += 1.0;
                }
            }
        }
        
        //스케일링
        for (int i = 0; i < aptSize; i++) {
            distcountArr[i].lampScale = distcountArr[i].lampCount / TOTALLAMP;
            distcountArr[i].CCTVScale = distcountArr[i].CCTVCount / TOTALCCTV;
            distcountArr[i].policeScale = (distcountArr[i].policeCount / TOTALPOLICE)*3; //경찰서 가중치
        }
        for (int i = 0; i < aptSize; i++) {
            distcountArr[i].totalScale += distcountArr[i].lampScale;
            distcountArr[i].totalScale += distcountArr[i].CCTVScale;
            distcountArr[i].totalScale += distcountArr[i].policeScale;
        }
        //내림차 정렬
        Arrays.sort(distcountArr);

        for (int i = 0; i < aptSize; i++) {
            System.out.println("아파트 리스트: "+allList.get(distcountArr[i].idx));
            System.out.println("가로등 숫자: "+distcountArr[i].lampCount);
            System.out.println("CCTV 숫자: "+distcountArr[i].CCTVCount);
            System.out.println("경찰서 숫자: "+distcountArr[i].policeCount);
            AptSafetyResultInfoDto result = new AptSafetyResultInfoDto(
                    allList.get(distcountArr[i].idx).getSidoName(),
                    allList.get(distcountArr[i].idx).getGugunName(),
                    allList.get(distcountArr[i].idx).getDongName(),
                    allList.get(distcountArr[i].idx).getAptName(),
                    allList.get(distcountArr[i].idx).getLat(),
                    allList.get(distcountArr[i].idx).getLng(),
                    allList.get(distcountArr[i].idx).getDealAmount(),
                    allList.get(distcountArr[i].idx).getArea(),
                    allList.get(distcountArr[i].idx).getFloor(),
                    allList.get(distcountArr[i].idx).getRoadName(),
                    allList.get(distcountArr[i].idx).getRoadNameBonbun(),
                    allList.get(distcountArr[i].idx).getRoadNameBubun(),
                    distcountArr[i].lampCount, distcountArr[i].CCTVCount, distcountArr[i].policeCount
                    );
            safetyAptList.add(result);
        }
        //아파트 리스트 전체개수만큼 던지니까 잘라서 사용하기
        return safetyAptList;
    }

    /**
     * 반경 안에 포함된 가로등, 경찰서, cctv 구하기 위해 직선거리 구하는 메소드
     *
     * @param lat1
     * @param lon1
     * @param lat2
     * @param lon2
     * @return 두 좌표간 직선거리, km단위
     */
    private static double distance(double lat1, double lon1, double lat2, double lon2) {
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        dist = dist * 1.609344;
        return (dist);
    }

    /**
     * decimal degree를 radians로 변환
     *
     * @param deg
     * @return
     */
    // This function converts decimal degrees to radians
    private static double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    /**
     * radians를 decimal degrees로 변환
     *
     * @param rad
     * @return
     */
    // This function converts radians to decimal degrees
    private static double rad2deg(double rad) {
        return (rad * 180 / Math.PI);
    }

    public class DistCount implements Comparable<DistCount>{
        int idx;
        double totalScale;
        double lampCount;
        double lampScale;
        double CCTVCount;
        double CCTVScale;
        double policeCount;
        double policeScale;

        public DistCount(double lampCount,double CCTVCount, double lampScale,double CCTVScale, double totalScale, double policeCount,  double policeScale, int idx) {
            this.lampCount = lampCount;
            this.lampScale = lampScale;
            this.CCTVCount = CCTVCount;
            this.CCTVScale = CCTVScale;
            this.policeCount = policeCount;
            this.policeScale = policeScale;
            this.totalScale = totalScale;
            this.idx = idx;
        }

		@Override
		public int compareTo(DistCount o) {
			DistCount d = (DistCount) o;
            Double compare = d.totalScale;
            return compare.compareTo(this.totalScale);
		}
    }

}

