package com.example.Ticket.Booking.service;

import com.example.Ticket.Booking.dao.HospitalDetailsCustomRepository;
import com.example.Ticket.Booking.dao.HospitalDetailsRepository;
import com.example.Ticket.Booking.model.*;
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
        Set<String> specalist = hospitalDetails.getHospitalDetails().stream().map(i -> i.getSpecalist()).flatMap(i -> i.stream().map(j -> j.getSpclName())).collect(Collectors.toSet());
        List<String> hospNames = hospitalDetails.getHospitalDetails().stream().map(i -> i.getName()).collect(Collectors.toList());
        filterDetails.setSpecialist(specalist);
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
        Set<String> specalist = detailsList.getHospitalDetails().stream().map(i -> i.getSpecalist()).flatMap(i -> i.stream().map(j -> j.getSpclName())).collect(Collectors.toSet());
//        Map<String, Set<String>> specalistName = new LinkedHashMap<>();
//        detailsList.stream().forEach(hospitalDetails -> {
//            specalistName.put(hospitalDetails.getLocation(), specalist);
//        });
        return specalist;
    }

    @Override
    public List<HospitalDtlBySpclty> getSpecalistDetails(SpecalistName spclList) {
        HospitalDetails  detailsList = hospitalDetailsRepository.findByLocation(spclList.getLocation());
        List<HospitalDtlBySpclty> hospitalDtlBySpclties = new ArrayList<>();

        detailsList.getHospitalDetails().stream().forEach(i -> {
            String docName = i.getName();
            List<Specalist> val = i.getSpecalist().stream().filter(j -> spclList.getSpecalistName().contains(j.getSpclName())).collect(Collectors.toList());
            HospitalDtlBySpclty hospitalDtlBySpclty = new HospitalDtlBySpclty();
            if(val.size() > 0){
           hospitalDtlBySpclty.setHospitalName(docName);
           hospitalDtlBySpclty.setSpecalists(val);
           hospitalDtlBySpclties.add(hospitalDtlBySpclty);
       }
        });
        return hospitalDtlBySpclties;
    }

    private HospitalDetails filterHospDetails(FilterDetails filterDetails){
        HospitalDetails hospitalDetails;
        if(Objects.nonNull(filterDetails.getSpecialist()) && Objects.nonNull(filterDetails.getHospitalName()) && Objects.nonNull(filterDetails.getCost())){
//            hospitalDetails = hospitalDetailsRepository.findByFilters(filterDetails.getLocation(), filterDetails.getSpecialist(), filterDetails.getHospitalName(), filterDetails.getCost());
            hospitalDetails = hospitalDetailsRepository.findByLocationAndHospitalNameAndSpecalist(filterDetails.getLocation(), filterDetails.getHospitalName(), filterDetails.getSpecialist());
            hospitalDetails = costFilter(hospitalDetails, filterDetails);
        } else if (Objects.nonNull(filterDetails.getSpecialist()) && Objects.nonNull(filterDetails.getHospitalName())) {
           hospitalDetails = hospitalDetailsRepository.findByLocationAndHospitalNameAndSpecalist(filterDetails.getLocation(), filterDetails.getHospitalName(), filterDetails.getSpecialist());
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

    private void getHsptlDtlsFilter(HospitalDtl details, List<Specalist> specialistList, List<HospitalDtl> hospitalDtls) {
        HospitalDtl hsDtls = new HospitalDtl();
        hsDtls.setName(details.getName());
        hsDtls.setSpecalist(specialistList);
        hospitalDtls.add(hsDtls);
    }

    private List<Specalist> getSpecialist(HospitalDtl details, FilterDetails filterDetails){
        List<Specalist> specialists = details.getSpecalist().stream().filter(j -> filterDetails.getSpecialist().contains(j.getSpclName())).collect(Collectors.toList());
        return  specialists;
    }

    private HospitalDetails  costFilter(HospitalDetails hsptlDtl, FilterDetails filterDetails) {
        HospitalDetails hospitalDetails = new HospitalDetails();
        hospitalDetails.setLocation(filterDetails.getLocation());
        List<HospitalDtl> hospitalDtlList = new ArrayList<>();

        hsptlDtl.getHospitalDetails().forEach(details -> {
            List<Specalist> specialistList = new ArrayList<>();
                    details.getSpecalist().stream().forEach(specalist1 -> {
                        List<DoctorsList> doctorsLists = new ArrayList<>();
                                List<DocNameAndAvblTime> docNameAndAvblTimes = specalist1.getDoctorsList().stream().flatMap(doctorsList -> doctorsList.getDocNameAndAvblTime().stream().filter(
                                        availTime -> availTime.getCost() <= filterDetails.getCost())).collect(Collectors.toList());
                                if(!docNameAndAvblTimes.isEmpty()){
                                    Specalist specalist = new Specalist();
                                    specalist.setSpclName(specalist1.getSpclName());
                                    DoctorsList doctorsList = new DoctorsList();
                                    doctorsList.setDocNameAndAvblTime(docNameAndAvblTimes);
                                    doctorsLists.add(doctorsList);
                                    specalist.setDoctorsList(doctorsLists);
                                    specialistList.add(specalist);
                                }
                            });


            if(!specialistList.isEmpty()){
                HospitalDtl hospitalDtl = new HospitalDtl();
                hospitalDtl.setName(details.getName());
                hospitalDtl.setSpecalist(specialistList);
                hospitalDtlList.add(hospitalDtl);
            }

        });
        hospitalDetails.setHospitalDetails(hospitalDtlList);

        return hospitalDetails;
    }
}
