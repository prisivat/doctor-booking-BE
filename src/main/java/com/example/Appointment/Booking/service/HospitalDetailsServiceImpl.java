package com.example.Appointment.Booking.service;

import com.example.Appointment.Booking.dao.HospitalDetailsRepository;
import com.example.Appointment.Booking.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class HospitalDetailsServiceImpl implements HospitalDetailsService{

    @Autowired
    HospitalDetailsRepository hospitalDetailsRepository;

//    @Autowired
//    HospitalDetailsCustomRepository hospitalDetailsCustomRepository;


    @Override
    public List<HospitalDetails> getHsptlDtls() {

        List<HospitalDetails>  detailsList = hospitalDetailsRepository.findAll();
//        Map<String,List<HospitalDtl>> listOfHospDtls = new LinkedHashMap<>();
//         detailsList.stream().forEach(hospitalDetails -> {
//             listOfHospDtls.put(hospitalDetails.getLocation(), hospitalDetails.getHospitalDetails());
//
//         });

        return detailsList;
    }

    @Override
    public FilterDetails getFltrDtlsByLocation(String location) {
        HospitalDetails hospitalDetails = hospitalDetailsRepository.findByLocation(location);
        FilterDetails filterDetails = new FilterDetails();
        Set<String> specialist = hospitalDetails.getHospitalDetails().stream().map(i -> i.getSpecialist()).flatMap(i -> i.stream().map(j -> j.getSpclName())).collect(Collectors.toSet());
        List<String> hospNames = hospitalDetails.getHospitalDetails().stream().map(i -> i.getName()).collect(Collectors.toList());
        filterDetails.setSpecialist(specialist);
        filterDetails.setHospitalName(hospNames);
        return filterDetails;
    }

    @Override
    public HospitalDetails getHospitalDetailsByFilter(FilterDetails filterDetails) {
        HospitalDetails hospitalDetails = filterHospDetails(filterDetails);
        return hospitalDetails;
    }

    @Override
    public Set<String> getListOfSpclName(String location) {

        HospitalDetails  detailsList = hospitalDetailsRepository.findByLocation(location);
        Set<String> specialist = detailsList.getHospitalDetails().stream().map(i -> i.getSpecialist()).flatMap(i -> i.stream().map(j -> j.getSpclName())).collect(Collectors.toSet());
//        Map<String, Set<String>> specialistName = new LinkedHashMap<>();
//        detailsList.stream().forEach(hospitalDetails -> {
//            specialistName.put(hospitalDetails.getLocation(), specialist);
//        });
        return specialist;
    }

    @Override
    public List<HospitalDtlBySpclty> getSpecialistDetails(SpecialistName spclList) {
        HospitalDetails  detailsList = hospitalDetailsRepository.findByLocation(spclList.getLocation());
        List<HospitalDtlBySpclty> hospitalDtlBySpclties = new ArrayList<>();

        detailsList.getHospitalDetails().stream().forEach(i -> {
            String docName = i.getName();
            List<Specialist> val = i.getSpecialist().stream().filter(j -> spclList.getSpecialistName().contains(j.getSpclName())).collect(Collectors.toList());
            HospitalDtlBySpclty hospitalDtlBySpclty = new HospitalDtlBySpclty();
            if(val.size() > 0){
           hospitalDtlBySpclty.setHospitalName(docName);
           hospitalDtlBySpclty.setSpecialists(val);
           hospitalDtlBySpclties.add(hospitalDtlBySpclty);
       }
        });
        return hospitalDtlBySpclties;
    }

    private HospitalDetails filterHospDetails(FilterDetails filterDetails){
        HospitalDetails hospitalDetails;
        if(Objects.nonNull(filterDetails.getSpecialist()) && Objects.nonNull(filterDetails.getHospitalName()) && Objects.nonNull(filterDetails.getCost())){
//            hospitalDetails = hospitalDetailsRepository.findByFilters(filterDetails.getLocation(), filterDetails.getSpecialist(), filterDetails.getHospitalName(), filterDetails.getCost());
            hospitalDetails = hospitalDetailsRepository.findByLocationAndHospitalNameAndSpecialist(filterDetails.getLocation(), filterDetails.getHospitalName(), filterDetails.getSpecialist());
            hospitalDetails = costFilter(hospitalDetails, filterDetails);
        } else if (Objects.nonNull(filterDetails.getSpecialist()) && Objects.nonNull(filterDetails.getHospitalName())) {
           hospitalDetails = hospitalDetailsRepository.findByLocationAndHospitalNameAndSpecialist(filterDetails.getLocation(), filterDetails.getHospitalName(), filterDetails.getSpecialist());
        } else if (Objects.nonNull(filterDetails.getSpecialist()) && Objects.nonNull(filterDetails.getCost())) {
            hospitalDetails = hospitalDetailsRepository.findByLocationAndSpcName(filterDetails.getLocation(), filterDetails.getSpecialist());
            hospitalDetails = costFilter(hospitalDetails, filterDetails);
        } else if(Objects.nonNull(filterDetails.getHospitalName()) && Objects.nonNull(filterDetails.getCost())){
           hospitalDetails = hospitalDetailsRepository.findByLocationAndHospitalName(filterDetails.getLocation(), filterDetails.getHospitalName());
            hospitalDetails = costFilter(hospitalDetails, filterDetails);
        } else if(Objects.nonNull(filterDetails.getHospitalName())){
            hospitalDetails = hospitalDetailsRepository.findByLocationAndHospitalName(filterDetails.getLocation(), filterDetails.getHospitalName());
        }  else if(Objects.nonNull(filterDetails.getSpecialist()) ){
            hospitalDetails = hospitalDetailsRepository.findByLocationAndSpcName(filterDetails.getLocation(), filterDetails.getSpecialist());
        } else {
            hospitalDetails = hospitalDetailsRepository.findByLocation(filterDetails.getLocation());
            hospitalDetails = costFilter(hospitalDetails, filterDetails);
        }
        return hospitalDetails;
    }

    private void getHsptlDtlsFilter(HospitalDtl details, List<Specialist> specialistList, List<HospitalDtl> hospitalDtls) {
        HospitalDtl hsDtls = new HospitalDtl();
        hsDtls.setName(details.getName());
        hsDtls.setSpecialist(specialistList);
        hospitalDtls.add(hsDtls);
    }

    private List<Specialist> getSpecialist(HospitalDtl details, FilterDetails filterDetails){
        List<Specialist> specialists = details.getSpecialist().stream().filter(j -> filterDetails.getSpecialist().contains(j.getSpclName())).collect(Collectors.toList());
        return  specialists;
    }

    private HospitalDetails  costFilter(HospitalDetails hsptlDtl, FilterDetails filterDetails) {
        HospitalDetails hospitalDetails = new HospitalDetails();
        hospitalDetails.setLocation(filterDetails.getLocation());
        List<HospitalDtl> hospitalDtlList = new ArrayList<>();

        hsptlDtl.getHospitalDetails().forEach(details -> {
            List<Specialist> specialistList = new ArrayList<>();
                    details.getSpecialist().stream().forEach(specialist1 -> {
                        List<DoctorsList> doctorsLists = new ArrayList<>();
                                List<DocNameAndAvblTime> docNameAndAvblTimes = specialist1.getDoctorsList().stream().flatMap(doctorsList -> doctorsList.getDocNameAndAvblTime().stream().filter(
                                        availTime -> availTime.getCost() <= filterDetails.getCost())).collect(Collectors.toList());
                                if(!docNameAndAvblTimes.isEmpty()){
                                    Specialist specialist = new Specialist();
                                    specialist.setSpclName(specialist1.getSpclName());
                                    DoctorsList doctorsList = new DoctorsList();
                                    doctorsList.setDocNameAndAvblTime(docNameAndAvblTimes);
                                    doctorsLists.add(doctorsList);
                                    specialist.setDoctorsList(doctorsLists);
                                    specialistList.add(specialist);
                                }
                            });


            if(!specialistList.isEmpty()){
                HospitalDtl hospitalDtl = new HospitalDtl();
                hospitalDtl.setName(details.getName());
                hospitalDtl.setSpecialist(specialistList);
                hospitalDtlList.add(hospitalDtl);
            }

        });
        hospitalDetails.setHospitalDetails(hospitalDtlList);

        return hospitalDetails;
    }
}
